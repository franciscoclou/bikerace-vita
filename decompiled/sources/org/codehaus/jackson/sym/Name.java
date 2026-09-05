package org.codehaus.jackson.sym;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class Name {
    protected final int _hashCode;
    protected final String _name;

    public abstract boolean equals(int i);

    public abstract boolean equals(int i, int i2);

    public abstract boolean equals(int[] iArr, int i);

    protected Name(String str, int i) {
        this._name = str;
        this._hashCode = i;
    }

    public String getName() {
        return this._name;
    }

    public String toString() {
        return this._name;
    }

    public final int hashCode() {
        return this._hashCode;
    }

    public boolean equals(Object obj) {
        return obj == this;
    }
}
