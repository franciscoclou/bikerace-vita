package com.topfreegames.engine.c;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: compiled from: ComposedBody.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected ArrayList<a> f1562a = new ArrayList<>();
    protected com.topfreegames.engine.a.b b = new com.topfreegames.engine.a.b();
    protected com.topfreegames.engine.a.b c = new com.topfreegames.engine.a.b();
    protected float d = 0.0f;
    protected float e = 0.0f;
    protected HashMap<a, com.topfreegames.engine.a.b> f = new HashMap<>();
    protected com.topfreegames.engine.a.b g = new com.topfreegames.engine.a.b();

    public void a(a aVar) {
        this.f1562a.add(aVar);
        this.f.put(aVar, new com.topfreegames.engine.a.b());
    }

    public void b(a aVar) {
        this.f1562a.remove(aVar);
        this.f.remove(aVar);
    }

    public void a() {
        this.b.a(0.0f, 0.0f);
        this.c.a(0.0f, 0.0f);
        int size = this.f1562a.size();
        float f = 0.0f;
        for (int i = 0; i < size; i++) {
            a aVar = this.f1562a.get(i);
            this.g.a(aVar.f1561a).b(aVar.c);
            this.b.b(this.g);
            this.g.a(aVar.b).b(aVar.c);
            this.c.b(this.g);
            f += aVar.c;
        }
        this.b.c(f);
        this.c.c(f);
        this.d = 0.0f;
        this.e = 0.0f;
        for (int i2 = 0; i2 < size; i2++) {
            a aVar2 = this.f1562a.get(i2);
            com.topfreegames.engine.a.b bVar = this.f.get(aVar2);
            bVar.a(aVar2.f1561a).c(this.b);
            this.d = (bVar.d(bVar) * aVar2.c) + this.d;
            this.e = (aVar2.c * aVar2.f1561a.e(aVar2.b)) + this.e;
        }
        this.e -= this.b.e(this.c) * f;
    }

    public float a(float f, float f2, float f3) {
        a();
        return (((f - this.e) * f2) * f3) / this.d;
    }

    public void a(float f) {
        int size = this.f1562a.size();
        for (int i = 0; i < size; i++) {
            a aVar = this.f1562a.get(i);
            this.g.a(this.f.get(aVar));
            this.g.a(1.5707964f).b(f);
            aVar.b.b(this.g);
        }
    }

    public void b(float f) {
        int size = this.f1562a.size();
        for (int i = 0; i < size; i++) {
            this.f1562a.get(i).a(f);
        }
        a();
    }

    public void a(com.topfreegames.engine.a.b bVar, float f) {
        int size = this.f1562a.size();
        for (int i = 0; i < size; i++) {
            this.f1562a.get(i).a(bVar, f);
        }
    }

    public com.topfreegames.engine.a.b b() {
        a();
        return this.b;
    }

    public com.topfreegames.engine.a.b c() {
        a();
        return this.c;
    }
}
