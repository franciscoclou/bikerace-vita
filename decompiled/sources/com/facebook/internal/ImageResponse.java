package com.facebook.internal;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ImageResponse {
    private Bitmap bitmap;
    private Exception error;
    private boolean isCachedRedirect;
    private ImageRequest request;

    ImageResponse(ImageRequest imageRequest, Exception exc, boolean z, Bitmap bitmap) {
        this.request = imageRequest;
        this.error = exc;
        this.bitmap = bitmap;
        this.isCachedRedirect = z;
    }

    public ImageRequest getRequest() {
        return this.request;
    }

    public Exception getError() {
        return this.error;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public boolean isCachedRedirect() {
        return this.isCachedRedirect;
    }
}
