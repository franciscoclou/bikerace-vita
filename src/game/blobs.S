@ Data files linked straight into the executable, so a code-only patch never
@ needs an asset transfer to the Vita.
@   data/levels.bin       scripts/dumplevels.sh
@   data/font_*.bin       scripts/makefonts.sh
    .section .rodata
    .balign 4

    .global br_levels_blob_start
    .global br_levels_blob_end
br_levels_blob_start:
    .incbin "data/levels.bin"
br_levels_blob_end:

    .balign 4
    .global br_font_display_start
    .global br_font_display_end
br_font_display_start:
    .incbin "data/font_display.bin"
br_font_display_end:

    .balign 4
    .global br_font_body_start
    .global br_font_body_end
br_font_body_start:
    .incbin "data/font_body.bin"
br_font_body_end:

    .byte 0
