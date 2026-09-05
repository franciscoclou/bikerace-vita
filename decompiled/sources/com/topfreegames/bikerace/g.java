package com.topfreegames.bikerace;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: compiled from: Game.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private float f1236a;
    private int b;
    private int c;
    private boolean d;
    private float e;
    private SharedPreferences f;

    public g(Context context) {
        this.f = null;
        if (context != null) {
            this.f = context.getSharedPreferences("com.topfreegames.bikerace.instr", 0);
        }
        a();
    }

    public void a(boolean z, boolean z2, float f, a aVar) {
        if (z2 && f > 1.0f) {
            this.d = false;
        }
        float fB = aVar.b() - this.f1236a;
        this.f1236a = (fB - (Math.round(fB / 6.2831855f) * 6.2831855f)) + this.f1236a;
        if (this.f1236a > 6.2831855f) {
            this.c++;
            this.f1236a -= 6.2831855f;
        } else if (this.f1236a < -6.2831855f) {
            this.b++;
            this.f1236a += 6.2831855f;
        }
        this.e = aVar.d().c();
    }

    public void a() {
        this.f1236a = 0.0f;
        this.b = 0;
        this.c = 0;
        this.d = true;
        this.e = 0.0f;
    }

    public int b() {
        return this.c;
    }

    public int c() {
        return this.b;
    }

    public int d() {
        return this.d ? 1 : 0;
    }

    public float e() {
        return this.e;
    }

    public void a(String str, int i) {
        if (this.f != null) {
            SharedPreferences.Editor editorEdit = this.f.edit();
            editorEdit.putInt(str, i);
            editorEdit.commit();
        }
    }

    public int a(String str) {
        if (this.f != null) {
            return this.f.getInt(str, -1);
        }
        return -1;
    }
}
