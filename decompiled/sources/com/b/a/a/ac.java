package com.b.a.a;

import java.io.IOException;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class ac extends RuntimeException {
    @Override // java.lang.Throwable
    public final /* bridge */ /* synthetic */ Throwable getCause() {
        return (IOException) super.getCause();
    }

    protected ac(IOException iOException) {
        super(iOException);
    }
}
