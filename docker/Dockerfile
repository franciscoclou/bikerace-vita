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
RUN apk add --no-cache bash python3 || true

WORKDIR /src
