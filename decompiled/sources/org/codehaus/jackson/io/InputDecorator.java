package org.codehaus.jackson.io;

import java.io.InputStream;
import java.io.Reader;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class InputDecorator {
    public abstract InputStream decorate(IOContext iOContext, InputStream inputStream);

    public abstract InputStream decorate(IOContext iOContext, byte[] bArr, int i, int i2);

    public abstract Reader decorate(IOContext iOContext, Reader reader);
}
