package com.amazonaws;

import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class h<T> implements j<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f135a;
    private URI d;
    private String e;
    private final e f;
    private InputStream h;
    private Map<String, String> b = new HashMap();
    private Map<String, String> c = new HashMap();
    private com.amazonaws.c.f g = com.amazonaws.c.f.POST;

    public h(e eVar, String str) {
        this.e = str;
        this.f = eVar;
    }

    @Override // com.amazonaws.j
    public e a() {
        return this.f;
    }

    @Override // com.amazonaws.j
    public void a(com.amazonaws.c.f fVar) {
        this.g = fVar;
    }

    @Override // com.amazonaws.j
    public void a(InputStream inputStream) {
        this.h = inputStream;
    }

    @Override // com.amazonaws.j
    public void a(String str) {
        this.f135a = str;
    }

    @Override // com.amazonaws.j
    public void a(String str, String str2) {
        this.c.put(str, str2);
    }

    @Override // com.amazonaws.j
    public void a(URI uri) {
        this.d = uri;
    }

    @Override // com.amazonaws.j
    public void a(Map<String, String> map) {
        this.c.clear();
        this.c.putAll(map);
    }

    @Override // com.amazonaws.j
    public Map<String, String> b() {
        return this.c;
    }

    @Override // com.amazonaws.j
    public void b(String str, String str2) {
        this.b.put(str, str2);
    }

    @Override // com.amazonaws.j
    public void b(Map<String, String> map) {
        this.b.clear();
        this.b.putAll(map);
    }

    @Override // com.amazonaws.j
    public String c() {
        return this.f135a;
    }

    @Override // com.amazonaws.j
    public Map<String, String> d() {
        return this.b;
    }

    @Override // com.amazonaws.j
    public com.amazonaws.c.f e() {
        return this.g;
    }

    @Override // com.amazonaws.j
    public URI f() {
        return this.d;
    }

    @Override // com.amazonaws.j
    public String g() {
        return this.e;
    }

    @Override // com.amazonaws.j
    public InputStream h() {
        return this.h;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(e().toString() + " ");
        sb.append(f().toString() + " ");
        sb.append("/" + (c() != null ? c() : "") + " ");
        if (!d().isEmpty()) {
            sb.append("Parameters: (");
            for (String str : d().keySet()) {
                sb.append(str + ": " + d().get(str) + ", ");
            }
            sb.append(") ");
        }
        if (!b().isEmpty()) {
            sb.append("Headers: (");
            for (String str2 : b().keySet()) {
                sb.append(str2 + ": " + b().get(str2) + ", ");
            }
            sb.append(") ");
        }
        return sb.toString();
    }
}
