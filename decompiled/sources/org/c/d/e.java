package org.c.d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: ParameterList.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<d> f1618a;

    public e() {
        this.f1618a = new ArrayList();
    }

    e(List<d> list) {
        this.f1618a = new ArrayList(list);
    }

    public e(Map<String, String> map) {
        this();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            this.f1618a.add(new d(entry.getKey(), entry.getValue()));
        }
    }

    public void a(String str, String str2) {
        this.f1618a.add(new d(str, str2));
    }

    public String a(String str) {
        org.c.g.c.a((Object) str, "Cannot append to null URL");
        String strB = b();
        if (!strB.equals("")) {
            return String.valueOf(String.valueOf(str) + (str.indexOf(63) != -1 ? "&" : '?')) + strB;
        }
        return str;
    }

    public String a() {
        return org.c.g.b.a(b());
    }

    public String b() {
        if (this.f1618a.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<d> it = this.f1618a.iterator();
        while (it.hasNext()) {
            sb.append('&').append(it.next().a());
        }
        return sb.toString().substring(1);
    }

    public void a(e eVar) {
        this.f1618a.addAll(eVar.f1618a);
    }

    public void b(String str) {
        if (str != null && str.length() > 0) {
            for (String str2 : str.split("&")) {
                String[] strArrSplit = str2.split("=");
                this.f1618a.add(new d(org.c.g.b.b(strArrSplit[0]), strArrSplit.length > 1 ? org.c.g.b.b(strArrSplit[1]) : ""));
            }
        }
    }

    public e c() {
        e eVar = new e(this.f1618a);
        Collections.sort(eVar.f1618a);
        return eVar;
    }
}
