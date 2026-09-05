package com.amazonaws.f.a;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ArrayList f117a;

    public a() {
        this.f117a = new ArrayList();
    }

    /* JADX WARN: Switch 'out' block B:14:0x0026 for B:18:0x003b already processed. Defaulting to fallback option. */
    public a(e eVar) throws b {
        char c;
        this();
        char cD = eVar.d();
        if (cD == '[') {
            c = ']';
        } else {
            if (cD != '(') {
                throw eVar.a("A JSONArray text must start with '['");
            }
            c = ')';
        }
        if (eVar.d() == ']') {
            return;
        }
        eVar.a();
        while (true) {
            if (eVar.d() == ',') {
                eVar.a();
                this.f117a.add(null);
            } else {
                eVar.a();
                this.f117a.add(eVar.e());
            }
            char cD2 = eVar.d();
            switch (cD2) {
                case ')':
                case ']':
                    if (c != cD2) {
                        throw eVar.a("Expected a '" + new Character(c) + "'");
                    }
                    return;
                case ',':
                case ';':
                    if (eVar.d() == ']') {
                        return;
                    } else {
                        eVar.a();
                    }
                    break;
                default:
                    throw eVar.a("Expected a ',' or ']'");
            }
        }
    }

    public a(Object obj) throws b {
        this();
        if (!obj.getClass().isArray()) {
            throw new b("JSONArray initial value should be a string or collection or array.");
        }
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            a(c.c(Array.get(obj, i)));
        }
    }

    public a(Collection collection) {
        this.f117a = new ArrayList();
        if (collection != null) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                this.f117a.add(c.c(it.next()));
            }
        }
    }

    public int a() {
        return this.f117a.size();
    }

    public a a(Object obj) {
        this.f117a.add(obj);
        return this;
    }

    public String a(String str) {
        int iA = a();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < iA; i++) {
            if (i > 0) {
                stringBuffer.append(str);
            }
            stringBuffer.append(c.b(this.f117a.get(i)));
        }
        return stringBuffer.toString();
    }

    public String toString() {
        try {
            return '[' + a(",") + ']';
        } catch (Exception e) {
            return null;
        }
    }
}
