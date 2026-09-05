# Build environment for the PS Vita port.
# The host vitasdk cannot run here (its binaries require glibc 2.38, this
# machine has 2.35), so every compile happens inside this image.
FROM vitasdk/vitasdk:latest

# Everything vitaGL needs, plus image/audio decoding for the asset pipeline.
RUN vdpm vitaGL     && \
    vdpm vitashark  && \
    vdpm SceShaccCgExt && \
    vdpm mathneon   && \
    vdpm zlib       && \
    vdpm libpng     && \
    vdpm vita2d     || true

# Run as the invoking user so build artifacts are not root-owned.
# Host-side helpers. The crash-dump symboliser runs in here too, because it
# needs arm-vita-eabi-addr2line and arm-vita-eabi-objdump.
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
        bash python3 python3-pyelftools imagemagick && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /src
