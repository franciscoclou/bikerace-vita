package org.codehaus.jackson.sym;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class NameN extends Name {
    final int mQuadLen;
    final int[] mQuads;

    NameN(String str, int i, int[] iArr, int i2) {
        super(str, i);
        if (i2 < 3) {
            throw new IllegalArgumentException("Qlen must >= 3");
        }
        this.mQuads = iArr;
        this.mQuadLen = i2;
    }

    @Override // org.codehaus.jackson.sym.Name
    public boolean equals(int i) {
        return false;
    }

    @Override // org.codehaus.jackson.sym.Name
    public boolean equals(int i, int i2) {
        return false;
    }

    @Override // org.codehaus.jackson.sym.Name
    public boolean equals(int[] iArr, int i) {
        if (i != this.mQuadLen) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2] != this.mQuads[i2]) {
                return false;
            }
        }
        return true;
    }
}
