package com.applovin.impl.a;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class af {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final CharSequence f182a;
    private final com.applovin.a.j b;
    private final Map c = new HashMap(1);
    private ag d;

    public af(CharSequence charSequence, com.applovin.a.j jVar) {
        if (charSequence == null) {
            throw new IllegalArgumentException("No template specified");
        }
        if (jVar == null) {
            throw new IllegalArgumentException("No logger specified");
        }
        this.f182a = charSequence;
        this.b = jVar;
    }

    private String a(String str) {
        String lowerCase;
        String strTrim;
        int iIndexOf = str.indexOf(58);
        if (iIndexOf > 0) {
            lowerCase = str.substring(0, iIndexOf).trim();
            strTrim = iIndexOf + 1 < str.length() ? str.substring(iIndexOf + 1).trim() : "";
        } else {
            lowerCase = str.trim().toLowerCase();
            strTrim = null;
        }
        if (this.d != null) {
            String strA = this.d.a(lowerCase);
            this.b.a("TemplateProcessor", lowerCase + " was resolved to \"" + (strA != null ? strA.substring(0, Math.min(strA.length(), 30)) : "") + "\"");
            if (strA != null) {
                return strA;
            }
        }
        String str2 = (String) this.c.get(lowerCase);
        if (str2 != null) {
            return str2;
        }
        String str3 = (String) this.c.get(lowerCase.toLowerCase());
        return str3 == null ? strTrim : str3;
    }

    public String a() {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        for (int i = 0; i < this.f182a.length(); i++) {
            char cCharAt = this.f182a.charAt(i);
            if (z3) {
                if (cCharAt == '%') {
                    if (z) {
                        stringBuffer2.append("%");
                        z = false;
                    } else {
                        z = true;
                    }
                } else if (cCharAt != '>') {
                    stringBuffer2.append(cCharAt);
                } else if (z) {
                    String string = stringBuffer2.toString();
                    stringBuffer2.setLength(0);
                    String strA = a(string);
                    if (strA != null) {
                        stringBuffer.append(strA);
                    } else {
                        stringBuffer.append("");
                        this.b.c("TemplateProcessor", "Unable to resolve \"" + string + "\", using empty string");
                    }
                    z = false;
                    z3 = false;
                } else {
                    stringBuffer2.append(">");
                }
            } else if (cCharAt == '<') {
                if (z2) {
                    stringBuffer.append("<");
                    z2 = false;
                } else {
                    z2 = true;
                }
            } else if (cCharAt != '%') {
                if (z2) {
                    stringBuffer.append("<");
                    z2 = false;
                }
                stringBuffer.append(cCharAt);
            } else if (z2) {
                z2 = false;
                z3 = true;
            } else {
                stringBuffer.append(cCharAt);
            }
        }
        if (z2) {
            stringBuffer.append("<");
        }
        if (z3) {
            stringBuffer.append("<%").append(stringBuffer2);
            if (z) {
                stringBuffer.append("%");
            }
        }
        return stringBuffer.toString();
    }

    void a(Map map) {
        if (map == null) {
            throw new IllegalArgumentException("No variables specified");
        }
        this.c.putAll(map);
    }
}
