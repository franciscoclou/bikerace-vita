#!/usr/bin/env python3
"""Validate the VPK's sce_sys artwork before it gets packaged.

scePromoterUtility rejects a package at 99% with error 0x8010113D when these
images are truecolour PNGs. They must be 8-bit palette (colour type 3, with a
PLTE chunk) at exactly the sizes Sony specifies -- the same format VitaShell's
own VPK ships. Regenerate them with scripts/make_livearea.sh.
"""

import struct
import sys

EXPECTED = {
    "sce_sys/icon0.png": (128, 128),
    "sce_sys/livearea/contents/startup.png": (280, 158),
    "sce_sys/livearea/contents/bg.png": (840, 500),
}


def check(path, want_w, want_h):
    try:
        data = open(path, "rb").read()
    except OSError as exc:
        return [str(exc)]

    if data[:8] != b"\x89PNG\r\n\x1a\n":
        return ["not a PNG"]

    width, height, depth, colour_type = struct.unpack(">IIBB", data[16:26])
    problems = []
    if (width, height) != (want_w, want_h):
        problems.append(f"is {width}x{height}, must be {want_w}x{want_h}")
    if colour_type != 3:
        problems.append(f"colour type {colour_type}, must be 3 (palette)")
    if depth != 8:
        problems.append(f"bit depth {depth}, must be 8")
    if b"PLTE" not in data[:4096]:
        problems.append("no PLTE chunk")
    return problems


def main():
    failed = False
    for path, (w, h) in EXPECTED.items():
        problems = check(path, w, h)
        if problems:
            failed = True
            print(f"  FAIL {path}: {'; '.join(problems)}")
        else:
            print(f"  ok   {path}")
    if failed:
        print()
        print("  These would install-fail on the Vita with error 0x8010113D.")
        print("  Fix: ./scripts/make_livearea.sh")
        return 1
    return 0


if __name__ == "__main__":
    sys.exit(main())
