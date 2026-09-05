package org.c.f;

import android.util.Base64;

/* JADX INFO: compiled from: DatatypeConverterEncoder.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c extends a {
    @Override // org.c.f.a
    public String a(byte[] bArr) {
        return Base64.encodeToString(bArr, 0);
    }

    @Override // org.c.f.a
    public String c() {
        return "DatatypeConverter";
    }
}
