cmake_minimum_required(VERSION 3.16)

# This project is always built inside the vitasdk Docker image
# (see scripts/build.sh) -- the host toolchain in ~/vitasdk cannot run on
# Ubuntu 22.04 because its binaries need glibc 2.38.
if(NOT DEFINED CMAKE_TOOLCHAIN_FILE)
  if(DEFINED ENV{VITASDK})
    set(CMAKE_TOOLCHAIN_FILE "$ENV{VITASDK}/share/vita.toolchain.cmake"
        CACHE PATH "toolchain file")
  else()
    message(FATAL_ERROR "VITASDK is not set. Build with ./scripts/build.sh")
  endif()
endif()

project(bikerace C CXX)
include("$ENV{VITASDK}/share/vita.cmake" REQUIRED)

set(VITA_APP_NAME  "Bike Race")
set(VITA_TITLEID   "BIKR00001")
set(VITA_VERSION   "01.00")

# --- tunables --------------------------------------------------------------
# Where the game sends its live UDP log. Passed in by scripts/build.sh so the
# PC's IP can change without editing sources.
set(BR_LOG_HOST "192.168.1.149" CACHE STRING "PC IP that receives debug logs")
set(BR_LOG_PORT "18194"         CACHE STRING "UDP port for debug logs")
option(BR_VERBOSE_LOG "Compile in per-frame LOGV() output" OFF)

set(CMAKE_C_STANDARD 99)
add_compile_options(
  -Wall -Wextra -Wno-unused-parameter
  -ffast-math -fno-strict-aliasing
  -mtune=cortex-a9 -mfpu=neon
)
# -g keeps the DWARF needed to symbolise psp2core dumps. It never reaches the
# Vita: vita-elf-create strips before packaging.
add_compile_options(-g -O2 -fno-omit-frame-pointer)

add_compile_definitions(
  BR_LOG_HOST="${BR_LOG_HOST}"
  BR_LOG_PORT=${BR_LOG_PORT}
)
if(BR_VERBOSE_LOG)
  add_compile_definitions(BR_VERBOSE_LOG=1)
endif()

include_directories(src)

enable_language(ASM)

file(GLOB_RECURSE BR_SOURCES
  ${CMAKE_SOURCE_DIR}/src/*.c
  ${CMAKE_SOURCE_DIR}/src/*.cpp
  ${CMAKE_SOURCE_DIR}/src/*.S
)

# levels_blob.S pulls in data/levels.bin with .incbin, so the assembler needs
# the repo root on its include path, and the blob is a build dependency.
set_source_files_properties(${CMAKE_SOURCE_DIR}/src/game/levels_blob.S
  PROPERTIES
    COMPILE_OPTIONS "-Wa,-I,${CMAKE_SOURCE_DIR}"
    OBJECT_DEPENDS  "${CMAKE_SOURCE_DIR}/data/levels.bin")

add_executable(${PROJECT_NAME} ${BR_SOURCES})

# vitaGL ships a C++ shader translator, so the final link must go through
# the C++ driver even though the game itself is C.
set_target_properties(${PROJECT_NAME} PROPERTIES LINKER_LANGUAGE CXX)

target_link_libraries(${PROJECT_NAME}
  vitaGL
  vitashark
  SceShaccCgExt
  mathneon
  taihen_stub
  SceShaccCg_stub
  png
  z
  m
  SceGxm_stub
  SceDisplay_stub
  SceSysmodule_stub
  SceCtrl_stub
  SceTouch_stub
  SceMotion_stub
  SceAudio_stub
  SceCommonDialog_stub
  SceKernelDmacMgr_stub
  SceAppMgr_stub
  ScePower_stub
  SceNet_stub
  SceNetCtl_stub
  SceIme_stub
)

vita_create_self(eboot.bin ${PROJECT_NAME} UNSAFE)

vita_create_vpk(BikeRace.vpk ${VITA_TITLEID} eboot.bin
  VERSION ${VITA_VERSION}
  NAME ${VITA_APP_NAME}
  FILE ${CMAKE_SOURCE_DIR}/sce_sys/icon0.png                sce_sys/icon0.png
  FILE ${CMAKE_SOURCE_DIR}/sce_sys/livearea/contents/bg.png sce_sys/livearea/contents/bg.png
  FILE ${CMAKE_SOURCE_DIR}/sce_sys/livearea/contents/startup.png sce_sys/livearea/contents/startup.png
  FILE ${CMAKE_SOURCE_DIR}/sce_sys/livearea/contents/template.xml sce_sys/livearea/contents/template.xml
)
