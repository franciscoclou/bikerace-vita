package com.topfreegames.engine.d;

import java.util.ArrayList;

/* JADX INFO: compiled from: Mesh.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected ArrayList<g> f1566a = new ArrayList<>();

    public void a(g gVar) {
        this.f1566a.add(gVar);
    }

    public int a() {
        return this.f1566a.size();
    }

    public g a(int i) {
        return this.f1566a.get(i);
    }
}
