package com.b.a;

import com.b.a.a.bt;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class be implements bt {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ byte[] f336a;
    private /* synthetic */ int[] b;

    be(bc bcVar, byte[] bArr, int[] iArr) {
        this.f336a = bArr;
        this.b = iArr;
    }

    @Override // com.b.a.a.bt
    public final void a(InputStream inputStream, int i) throws IOException {
        try {
            inputStream.read(this.f336a, this.b[0], i);
            int[] iArr = this.b;
            iArr[0] = iArr[0] + i;
        } finally {
            inputStream.close();
        }
    }
}
