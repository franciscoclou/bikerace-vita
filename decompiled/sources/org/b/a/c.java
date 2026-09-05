package org.b.a;

import com.amazonaws.javax.xml.stream.XMLStreamConstants;
import com.facebook.AppEventsConstants;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: JSONObject.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f1611a = new d();
    private Map b;

    public c() {
        this.b = new HashMap();
    }

    public c(Map map) {
        this.b = new HashMap();
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                Object value = entry.getValue();
                if (value != null) {
                    this.b.put(entry.getKey(), c(value));
                }
            }
        }
    }

    public c(Object obj) {
        this();
        d(obj);
    }

    public Iterator a() {
        return this.b.keySet().iterator();
    }

    public static String a(Number number) throws b {
        if (number == null) {
            throw new b("Null pointer");
        }
        a((Object) number);
        String string = number.toString();
        if (string.indexOf(46) > 0 && string.indexOf(101) < 0 && string.indexOf(69) < 0) {
            while (string.endsWith(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                string = string.substring(0, string.length() - 1);
            }
            if (string.endsWith(".")) {
                return string.substring(0, string.length() - 1);
            }
            return string;
        }
        return string;
    }

    private void d(Object obj) {
        String lowerCase;
        Class<?> cls = obj.getClass();
        for (Method method : cls.getClassLoader() != null ? cls.getMethods() : cls.getDeclaredMethods()) {
            try {
                if (Modifier.isPublic(method.getModifiers())) {
                    String name = method.getName();
                    String strSubstring = "";
                    if (name.startsWith("get")) {
                        if (name.equals("getClass") || name.equals("getDeclaringClass")) {
                            strSubstring = "";
                        } else {
                            strSubstring = name.substring(3);
                        }
                    } else if (name.startsWith("is")) {
                        strSubstring = name.substring(2);
                    }
                    if (strSubstring.length() > 0 && Character.isUpperCase(strSubstring.charAt(0)) && method.getParameterTypes().length == 0) {
                        if (strSubstring.length() == 1) {
                            lowerCase = strSubstring.toLowerCase();
                        } else {
                            lowerCase = !Character.isUpperCase(strSubstring.charAt(1)) ? strSubstring.substring(0, 1).toLowerCase() + strSubstring.substring(1) : strSubstring;
                        }
                        Object objInvoke = method.invoke(obj, (Object[]) null);
                        if (objInvoke != null) {
                            this.b.put(lowerCase, c(objInvoke));
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    public c a(String str, int i) throws b {
        a(str, new Integer(i));
        return this;
    }

    public c a(String str, long j) throws b {
        a(str, new Long(j));
        return this;
    }

    public c a(String str, Object obj) throws b {
        if (str == null) {
            throw new b("Null key.");
        }
        if (obj != null) {
            a(obj);
            this.b.put(str, obj);
        } else {
            b(str);
        }
        return this;
    }

    public static String a(String str) {
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
                    if (cCharAt < ' ' || ((cCharAt >= 128 && cCharAt < 160) || (cCharAt >= 8192 && cCharAt < 8448))) {
                        String str2 = "000" + Integer.toHexString(cCharAt);
                        stringBuffer.append("\\u" + str2.substring(str2.length() - 4));
                    } else {
                        stringBuffer.append(cCharAt);
                    }
                    break;
            }
            i++;
            c = cCharAt;
        }
        stringBuffer.append('\"');
        return stringBuffer.toString();
    }

    public Object b(String str) {
        return this.b.remove(str);
    }

    public static void a(Object obj) throws b {
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

    public String toString() {
        try {
            Iterator itA = a();
            StringBuffer stringBuffer = new StringBuffer("{");
            while (itA.hasNext()) {
                if (stringBuffer.length() > 1) {
                    stringBuffer.append(',');
                }
                Object next = itA.next();
                stringBuffer.append(a(next.toString()));
                stringBuffer.append(':');
                stringBuffer.append(b(this.b.get(next)));
            }
            stringBuffer.append('}');
            return stringBuffer.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public static String b(Object obj) throws b {
        if (obj == null || obj.equals(null)) {
            return "null";
        }
        if (obj instanceof e) {
            try {
                String strA = ((e) obj).a();
                if (strA instanceof String) {
                    return strA;
                }
                throw new b("Bad value from toJSONString: " + ((Object) strA));
            } catch (Exception e) {
                throw new b(e);
            }
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
        if (obj.getClass().isArray()) {
            return new a(obj).toString();
        }
        return a(obj.toString());
    }

    public static Object c(Object obj) {
        try {
            if (obj == null) {
                return f1611a;
            }
            if (!(obj instanceof c) && !(obj instanceof a) && !f1611a.equals(obj) && !(obj instanceof e) && !(obj instanceof Byte) && !(obj instanceof Character) && !(obj instanceof Short) && !(obj instanceof Integer) && !(obj instanceof Long) && !(obj instanceof Boolean) && !(obj instanceof Float) && !(obj instanceof Double) && !(obj instanceof String)) {
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
                if (name.startsWith("java.") || name.startsWith("javax.") || obj.getClass().getClassLoader() == null) {
                    return obj.toString();
                }
                return new c(obj);
            }
            return obj;
        } catch (Exception e) {
            return null;
        }
    }
}
