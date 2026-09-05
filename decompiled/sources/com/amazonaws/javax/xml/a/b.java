package com.amazonaws.javax.xml.a;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import java.io.Serializable;
import java.security.AccessController;
import java.security.PrivilegedAction;

/* JADX INFO: compiled from: QName.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b implements Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final long f137a;
    private static boolean b;
    private final String c;
    private final String d;
    private final String e;

    static {
        b = true;
        try {
            String str = (String) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.amazonaws.javax.xml.a.b.1
                @Override // java.security.PrivilegedAction
                public Object run() {
                    return System.getProperty("com.sun.xml.namespace.QName.useCompatibleSerialVersionUID");
                }
            });
            b = str == null || !str.equals(XMLStreamWriterImpl.DEFAULT_XML_VERSION);
        } catch (Exception e) {
            b = true;
        }
        if (b) {
            f137a = -9120448754896609940L;
        } else {
            f137a = 4418622981026545151L;
        }
    }

    public b(String str, String str2) {
        this(str, str2, "");
    }

    public b(String str, String str2, String str3) {
        if (str == null) {
            this.c = "";
        } else {
            this.c = str;
        }
        if (str2 == null) {
            throw new IllegalArgumentException("local part cannot be \"null\" when creating a QName");
        }
        this.d = str2;
        if (str3 == null) {
            throw new IllegalArgumentException("prefix cannot be \"null\" when creating a QName");
        }
        this.e = str3;
    }

    public b(String str) {
        this("", str, "");
    }

    public String a() {
        return this.c;
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.e;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.d.equals(bVar.d) && this.c.equals(bVar.c);
    }

    public final int hashCode() {
        return this.c.hashCode() ^ this.d.hashCode();
    }

    public String toString() {
        return this.c.equals("") ? this.d : "{" + this.c + "}" + this.d;
    }

    public static b a(String str) {
        if (str == null) {
            throw new IllegalArgumentException("cannot create QName from \"null\" or \"\" String");
        }
        if (str.length() == 0) {
            return new b("", str, "");
        }
        if (str.charAt(0) != '{') {
            return new b("", str, "");
        }
        if (str.startsWith("{}")) {
            throw new IllegalArgumentException("Namespace URI .equals(XMLConstants.NULL_NS_URI), .equals(\"\"), only the local part, \"" + str.substring("".length() + 2) + "\", should be provided.");
        }
        int iIndexOf = str.indexOf(125);
        if (iIndexOf == -1) {
            throw new IllegalArgumentException("cannot create QName from \"" + str + "\", missing closing \"}\"");
        }
        return new b(str.substring(1, iIndexOf), str.substring(iIndexOf + 1), "");
    }
}
