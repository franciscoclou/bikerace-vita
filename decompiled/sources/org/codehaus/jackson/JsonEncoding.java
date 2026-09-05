package org.codehaus.jackson;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum JsonEncoding {
    UTF8(XMLStreamWriterImpl.UTF_8, false),
    UTF16_BE("UTF-16BE", true),
    UTF16_LE("UTF-16LE", false),
    UTF32_BE("UTF-32BE", true),
    UTF32_LE("UTF-32LE", false);

    protected final boolean _bigEndian;
    protected final String _javaName;

    JsonEncoding(String str, boolean z) {
        this._javaName = str;
        this._bigEndian = z;
    }

    public String getJavaName() {
        return this._javaName;
    }

    public boolean isBigEndian() {
        return this._bigEndian;
    }
}
