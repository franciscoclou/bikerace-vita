package com.topfreegames.bikerace.f;

/* JADX INFO: compiled from: GiftDescriptor.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f1227a;
    private b b;
    private String c;

    public d(String str, b bVar, String str2) {
        this.f1227a = null;
        this.b = null;
        this.c = null;
        if (bVar == b.GIVE_SPECIFIC_TRACK && str2 == null) {
            throw new IllegalArgumentException("Track cannot be null for this type of gift!");
        }
        this.f1227a = str;
        this.b = bVar;
        this.c = str2;
    }

    public d(String str, b bVar) {
        this(str, bVar, null);
    }

    public b a() {
        return this.b;
    }

    public String b() {
        return this.f1227a == null ? "" : this.f1227a;
    }

    public String c() {
        return this.c;
    }
}
