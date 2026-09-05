package com.amazonaws.javax.xml.stream.dtd.nonvalidating;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class XMLSimpleType {
    public static final short DEFAULT_TYPE_DEFAULT = 3;
    public static final short DEFAULT_TYPE_FIXED = 1;
    public static final short DEFAULT_TYPE_IMPLIED = 0;
    public static final short DEFAULT_TYPE_REQUIRED = 2;
    public static final short TYPE_CDATA = 0;
    public static final short TYPE_ENTITY = 1;
    public static final short TYPE_ENUMERATION = 2;
    public static final short TYPE_ID = 3;
    public static final short TYPE_IDREF = 4;
    public static final short TYPE_NAMED = 7;
    public static final short TYPE_NMTOKEN = 5;
    public static final short TYPE_NOTATION = 6;
    public short defaultType;
    public String defaultValue;
    public String[] enumeration;
    public boolean list;
    public String name;
    public String nonNormalizedDefaultValue;
    public short type;

    public void setValues(short s, String str, String[] strArr, boolean z, short s2, String str2, String str3) {
        this.type = s;
        this.name = str;
        if (strArr != null && strArr.length > 0) {
            this.enumeration = new String[strArr.length];
            System.arraycopy(strArr, 0, this.enumeration, 0, this.enumeration.length);
        } else {
            this.enumeration = null;
        }
        this.list = z;
        this.defaultType = s2;
        this.defaultValue = str2;
        this.nonNormalizedDefaultValue = str3;
    }

    public void setValues(XMLSimpleType xMLSimpleType) {
        this.type = xMLSimpleType.type;
        this.name = xMLSimpleType.name;
        if (xMLSimpleType.enumeration != null && xMLSimpleType.enumeration.length > 0) {
            this.enumeration = new String[xMLSimpleType.enumeration.length];
            System.arraycopy(xMLSimpleType.enumeration, 0, this.enumeration, 0, this.enumeration.length);
        } else {
            this.enumeration = null;
        }
        this.list = xMLSimpleType.list;
        this.defaultType = xMLSimpleType.defaultType;
        this.defaultValue = xMLSimpleType.defaultValue;
        this.nonNormalizedDefaultValue = xMLSimpleType.nonNormalizedDefaultValue;
    }

    public void clear() {
        this.type = (short) -1;
        this.name = null;
        this.enumeration = null;
        this.list = false;
        this.defaultType = (short) -1;
        this.defaultValue = null;
        this.nonNormalizedDefaultValue = null;
    }
}
