package org.b.a;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: compiled from: JSONArray.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ArrayList f1609a;

    public a() {
        this.f1609a = new ArrayList();
    }

    public a(Collection collection) {
        this.f1609a = new ArrayList();
        if (collection != null) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                this.f1609a.add(c.c(it.next()));
            }
        }
    }

    public a(Object obj) throws b {
        this();
        if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                a(c.c(Array.get(obj, i)));
            }
            return;
        }
        throw new b("JSONArray initial value should be a string or collection or array.");
    }

    public String a(String str) {
        int iA = a();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < iA; i++) {
            if (i > 0) {
                stringBuffer.append(str);
            }
            stringBuffer.append(c.b(this.f1609a.get(i)));
        }
        return stringBuffer.toString();
    }

    public int a() {
        return this.f1609a.size();
    }

    public a a(Object obj) {
        this.f1609a.add(obj);
        return this;
    }

    public String toString() {
        try {
            return '[' + a(",") + ']';
        } catch (Exception e) {
            return null;
        }
    }
}
