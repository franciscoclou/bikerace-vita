package com.amazonaws.f.a;

import com.amazonaws.javax.xml.stream.XMLStreamConstants;
import com.facebook.AppEventsConstants;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f119a = new d();
    private Map b;

    public c() {
        this.b = new HashMap();
    }

    /* JADX WARN: Switch 'out' block B:7:0x0015 for B:15:0x0045 already processed. Defaulting to fallback option. */
    /* JADX WARN: Switch 'out' block B:7:0x0015 for B:8:0x0019 already processed. Defaulting to fallback option. */
    public c(e eVar) throws b {
        this();
        if (eVar.d() != '{') {
            throw eVar.a("A JSONObject text must begin with '{'");
        }
        while (true) {
            switch (eVar.d()) {
                case 0:
                    throw eVar.a("A JSONObject text must end with '}'");
                case '}':
                    return;
                default:
                    eVar.a();
                    String string = eVar.e().toString();
                    char cD = eVar.d();
                    if (cD == '=') {
                        if (eVar.c() != '>') {
                            eVar.a();
                        }
                    } else if (cD != ':') {
                        throw eVar.a("Expected a ':' after a key");
                    }
                    b(string, eVar.e());
                    switch (eVar.d()) {
                        case ',':
                        case ';':
                            if (eVar.d() == '}') {
                                return;
                            } else {
                                eVar.a();
                            }
                            break;
                        case '}':
                            return;
                        default:
                            throw eVar.a("Expected a ',' or '}'");
                    }
                    break;
            }
        }
    }

    public c(Object obj) {
        this();
        d(obj);
    }

    public c(String str) {
        this(new e(str));
    }

    public c(Map map) {
        this.b = new HashMap();
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                this.b.put(entry.getKey(), c(entry.getValue()));
            }
        }
    }

    public static String a(Number number) throws b {
        if (number == null) {
            throw new b("Null pointer");
        }
        a((Object) number);
        String string = number.toString();
        if (string.indexOf(46) <= 0 || string.indexOf(101) >= 0 || string.indexOf(69) >= 0) {
            return string;
        }
        while (string.endsWith(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
            string = string.substring(0, string.length() - 1);
        }
        return string.endsWith(".") ? string.substring(0, string.length() - 1) : string;
    }

    static void a(Object obj) throws b {
        if (obj != null) {
            if (obj instanceof Double) {
                if (((Double) obj).isInfinite() || ((Double) obj).isNaN()) {
                    throw new b("JSON does not allow non-finite numbers.");
                }
            } else if (obj instanceof Float) {
                if (((Float) obj).isInfinite() || ((Float) obj).isNaN()) {
                    throw new b("JSON does not allow non-finite numbers.");
                }
            }
        }
    }

    static String b(Object obj) {
        if (obj == null || obj.equals(null)) {
            return "null";
        }
        if (obj instanceof Number) {
            return a((Number) obj);
        }
        if ((obj instanceof Boolean) || (obj instanceof c) || (obj instanceof a)) {
            return obj.toString();
        }
        if (obj instanceof Map) {
            return new c((Map) obj).toString();
        }
        if (obj instanceof Collection) {
            return new a((Collection) obj).toString();
        }
        return obj.getClass().isArray() ? new a(obj).toString() : e(obj.toString());
    }

    static Object c(Object obj) {
        try {
            if (obj == null) {
                return f119a;
            }
            if ((obj instanceof c) || (obj instanceof a) || f119a.equals(obj) || (obj instanceof String) || (obj instanceof Byte) || (obj instanceof Character) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Boolean) || (obj instanceof Float) || (obj instanceof Double)) {
                return obj;
            }
            if (obj instanceof Collection) {
                return new a((Collection) obj);
            }
            if (obj.getClass().isArray()) {
                return new a(obj);
            }
            if (obj instanceof Map) {
                return new c((Map) obj);
            }
            Package r0 = obj.getClass().getPackage();
            String name = r0 != null ? r0.getName() : "";
            return (name.startsWith("java.") || name.startsWith("javax.") || obj.getClass().getClassLoader() == null) ? obj.toString() : new c(obj);
        } catch (Exception e) {
            return null;
        }
    }

    private void d(Object obj) {
        Class<?> cls = obj.getClass();
        for (Method method : cls.getClassLoader() != null ? cls.getMethods() : cls.getDeclaredMethods()) {
            try {
                if (Modifier.isPublic(method.getModifiers())) {
                    String name = method.getName();
                    String strSubstring = "";
                    if (name.startsWith("get")) {
                        strSubstring = (name.equals("getClass") || name.equals("getDeclaringClass")) ? "" : name.substring(3);
                    } else if (name.startsWith("is")) {
                        strSubstring = name.substring(2);
                    }
                    if (strSubstring.length() > 0 && Character.isUpperCase(strSubstring.charAt(0)) && method.getParameterTypes().length == 0) {
                        this.b.put(strSubstring.length() == 1 ? strSubstring.toLowerCase() : !Character.isUpperCase(strSubstring.charAt(1)) ? strSubstring.substring(0, 1).toLowerCase() + strSubstring.substring(1) : strSubstring, c(method.invoke(obj, (Object[]) null)));
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    public static String e(String str) {
        int i = 0;
        if (str == null || str.length() == 0) {
            return "\"\"";
        }
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length + 4);
        stringBuffer.append('\"');
        char c = 0;
        while (i < length) {
            char cCharAt = str.charAt(i);
            switch (cCharAt) {
                case '\b':
                    stringBuffer.append("\\b");
                    break;
                case '\t':
                    stringBuffer.append("\\t");
                    break;
                case XMLStreamConstants.ATTRIBUTE /* 10 */:
                    stringBuffer.append("\\n");
                    break;
                case XMLStreamConstants.CDATA /* 12 */:
                    stringBuffer.append("\\f");
                    break;
                case XMLStreamConstants.NAMESPACE /* 13 */:
                    stringBuffer.append("\\r");
                    break;
                case '\"':
                case '\\':
                    stringBuffer.append('\\');
                    stringBuffer.append(cCharAt);
                    break;
                case '/':
                    if (c == '<') {
                        stringBuffer.append('\\');
                    }
                    stringBuffer.append(cCharAt);
                    break;
                default:
                    if (cCharAt >= ' ' && (cCharAt < 128 || cCharAt >= 160)) {
                        stringBuffer.append(cCharAt);
                    } else {
                        String str2 = "000" + Integer.toHexString(cCharAt);
                        stringBuffer.append("\\u" + str2.substring(str2.length() - 4));
                    }
                    break;
            }
            i++;
            c = cCharAt;
        }
        stringBuffer.append('\"');
        return stringBuffer.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Object g(String str) {
        if (str.equals("")) {
            return str;
        }
        if (str.equalsIgnoreCase("true")) {
            return Boolean.TRUE;
        }
        if (str.equalsIgnoreCase("false")) {
            return Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("null")) {
            return f119a;
        }
        char cCharAt = str.charAt(0);
        if ((cCharAt < '0' || cCharAt > '9') && cCharAt != '.' && cCharAt != '-' && cCharAt != '+') {
            return str;
        }
        if (cCharAt == '0' && str.length() > 2 && (str.charAt(1) == 'x' || str.charAt(1) == 'X')) {
            try {
                return new Integer(Integer.parseInt(str.substring(2), 16));
            } catch (Exception e) {
            }
        }
        try {
            if (str.indexOf(46) > -1 || str.indexOf(101) > -1 || str.indexOf(69) > -1) {
                str = Double.valueOf(str);
            } else {
                Long l = new Long(str);
                str = l.longValue() == ((long) l.intValue()) ? new Integer(l.intValue()) : l;
            }
            return str;
        } catch (Exception e2) {
            return str;
        }
    }

    public c a(String str, Object obj) throws b {
        if (str == null) {
            throw new b("Null key.");
        }
        if (obj != null) {
            a(obj);
            this.b.put(str, obj);
        } else {
            f(str);
        }
        return this;
    }

    public Object a(String str) throws b {
        Object objD = d(str);
        if (objD == null) {
            throw new b("JSONObject[" + e(str) + "] not found.");
        }
        return objD;
    }

    public Iterator a() {
        return this.b.keySet().iterator();
    }

    public c b(String str, Object obj) throws b {
        if (str != null && obj != null) {
            if (d(str) != null) {
                throw new b("Duplicate key \"" + str + "\"");
            }
            a(str, obj);
        }
        return this;
    }

    public String b(String str) {
        return a(str).toString();
    }

    public boolean c(String str) {
        return this.b.containsKey(str);
    }

    public Object d(String str) {
        if (str == null) {
            return null;
        }
        return this.b.get(str);
    }

    public Object f(String str) {
        return this.b.remove(str);
    }

    public String toString() {
        try {
            Iterator itA = a();
            StringBuffer stringBuffer = new StringBuffer("{");
            while (itA.hasNext()) {
                if (stringBuffer.length() > 1) {
                    stringBuffer.append(',');
                }
                Object next = itA.next();
                stringBuffer.append(e(next.toString()));
                stringBuffer.append(':');
                stringBuffer.append(b(this.b.get(next)));
            }
            stringBuffer.append('}');
            return stringBuffer.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
