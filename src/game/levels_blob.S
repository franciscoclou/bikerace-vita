@ data/levels.bin, linked straight into the executable so that a code-only
@ patch never needs an asset transfer to the Vita. Regenerate the file with
@ scripts/dumplevels.sh.
    .section .rodata
    .balign 4
    .global br_levels_blob_start
    .global br_levels_blob_end
br_levels_blob_start:
    .incbin "data/levels.bin"
br_levels_blob_end:
    .byte 0
