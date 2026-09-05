package com.topfreegames.bikerace.multiplayer;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: RaceRecord.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ak {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ArrayList<a> f1307a;

    public ak() {
        this.f1307a = new ArrayList<>();
    }

    public ak(ak akVar) {
        this.f1307a = new ArrayList<>(akVar.f1307a);
    }

    public ak(List<a> list) {
        this.f1307a = new ArrayList<>(list);
    }

    public void a(a aVar) {
        this.f1307a.add(aVar);
    }

    public a a(int i) {
        return this.f1307a.get(i);
    }

    public a a() {
        return this.f1307a.get(this.f1307a.size() - 1);
    }

    public int b() {
        return this.f1307a.size();
    }

    public ArrayList<a> c() {
        return new ArrayList<>(this.f1307a);
    }

    public void d() {
        this.f1307a.clear();
    }
}
