package com.topfreegames.e;

import android.graphics.Bitmap;

/* JADX INFO: compiled from: TopFacebookUser.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f1551a;
    private String b;
    private Bitmap c;

    public l(String str, String str2, Bitmap bitmap) {
        this.f1551a = str;
        this.b = str2;
        this.c = bitmap;
    }

    public String a() {
        return this.f1551a;
    }

    public String b() {
        return this.b;
    }

    public Bitmap c() {
        return this.c;
    }

    public void a(Bitmap bitmap) {
        this.c = bitmap;
    }

    public void a(String str) {
        this.b = str;
    }
}
