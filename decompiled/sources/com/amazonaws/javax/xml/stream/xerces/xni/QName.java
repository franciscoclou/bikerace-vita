package com.amazonaws.javax.xml.stream.xerces.xni;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class QName implements Cloneable {
    public char[] characters = null;
    public String localpart;
    public String prefix;
    public String rawname;
    public String uri;

    public QName() {
        clear();
    }

    public QName(String str, String str2, String str3, String str4) {
        setValues(str, str2, str3, str4);
    }

    public QName(QName qName) {
        setValues(qName);
    }

    public void setValues(QName qName) {
        this.prefix = qName.prefix;
        this.localpart = qName.localpart;
        this.rawname = qName.rawname;
        this.uri = qName.uri;
        this.characters = qName.characters;
    }

    public void setValues(String str, String str2, String str3, String str4) {
        this.prefix = str;
        this.localpart = str2;
        this.rawname = str3;
        this.uri = str4;
    }

    public void clear() {
        this.prefix = null;
        this.localpart = null;
        this.rawname = null;
        this.uri = null;
    }

    public Object clone() {
        return new QName(this);
    }

    public int hashCode() {
        return this.uri != null ? this.uri.hashCode() + this.localpart.hashCode() : this.rawname.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof QName) {
            QName qName = (QName) obj;
            if (qName.uri != null) {
                return this.uri == qName.uri && this.localpart == qName.localpart;
            }
            if (this.uri == null) {
                return this.rawname == qName.rawname;
            }
        }
        return false;
    }

    public String toString() {
        boolean z = true;
        StringBuffer stringBuffer = new StringBuffer();
        boolean z2 = false;
        if (this.prefix != null) {
            stringBuffer.append(new StringBuffer().append("prefix=\"").append(this.prefix).append('\"').toString());
            z2 = true;
        }
        if (this.localpart != null) {
            if (z2) {
                stringBuffer.append(',');
            }
            stringBuffer.append(new StringBuffer().append("localpart=\"").append(this.localpart).append('\"').toString());
            z2 = true;
        }
        if (this.rawname != null) {
            if (z2) {
                stringBuffer.append(',');
            }
            stringBuffer.append(new StringBuffer().append("rawname=\"").append(this.rawname).append('\"').toString());
        } else {
            z = z2;
        }
        if (this.uri != null) {
            if (z) {
                stringBuffer.append(',');
            }
            stringBuffer.append(new StringBuffer().append("uri=\"").append(this.uri).append('\"').toString());
        }
        return stringBuffer.toString();
    }
}
