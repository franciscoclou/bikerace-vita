package org.codehaus.jackson.sym;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class Name1 extends Name {
    static final Name1 sEmptyName = new Name1("", 0, 0);
    final int mQuad;

    Name1(String str, int i, int i2) {
        super(str, i);
        this.mQuad = i2;
    }

    static final Name1 getEmptyName() {
        return sEmptyName;
    }

    @Override // org.codehaus.jackson.sym.Name
    public boolean equals(int i) {
        return i == this.mQuad;
    }

    @Override // org.codehaus.jackson.sym.Name
    public boolean equals(int i, int i2) {
        return i == this.mQuad && i2 == 0;
    }

    @Override // org.codehaus.jackson.sym.Name
    public boolean equals(int[] iArr, int i) {
        return i == 1 && iArr[0] == this.mQuad;
    }
}
