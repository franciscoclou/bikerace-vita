package com.amazonaws.javax.xml.stream.xerces.xni;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class XMLString {
    public char[] ch;
    public int length;
    public int offset;

    public XMLString() {
    }

    public XMLString(char[] cArr, int i, int i2) {
        setValues(cArr, i, i2);
    }

    public XMLString(XMLString xMLString) {
        setValues(xMLString);
    }

    public void setValues(char[] cArr, int i, int i2) {
        this.ch = cArr;
        this.offset = i;
        this.length = i2;
    }

    public void setValues(XMLString xMLString) {
        setValues(xMLString.ch, xMLString.offset, xMLString.length);
    }

    public void clear() {
        this.ch = null;
        this.offset = 0;
        this.length = -1;
    }

    public boolean equals(char[] cArr, int i, int i2) {
        if (cArr == null || this.length != i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.ch[this.offset + i3] != cArr[i + i3]) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(String str) {
        if (str == null || this.length != str.length()) {
            return false;
        }
        for (int i = 0; i < this.length; i++) {
            if (this.ch[this.offset + i] != str.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return this.length > 0 ? new String(this.ch, this.offset, this.length) : "";
    }
}
