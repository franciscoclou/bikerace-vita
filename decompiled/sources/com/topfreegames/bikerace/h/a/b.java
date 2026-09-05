package com.topfreegames.bikerace.h.a;

import android.content.Context;
import android.content.SharedPreferences;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.bikerace.bb;
import java.util.Date;
import java.util.GregorianCalendar;

/* JADX INFO: compiled from: FreeTracksManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private SharedPreferences f1243a;
    private bb b;

    public b(Context context) {
        this.f1243a = null;
        this.b = null;
        this.f1243a = context.getSharedPreferences("com.topfreegames.bikerace.dailytracks", 0);
        this.b = ((BikeRaceApplication) context.getApplicationContext()).a(false);
    }

    public int a() {
        long j = this.f1243a.getLong("BY4NA", -1L);
        if (j < 0 || com.topfreegames.c.a.a().getTime() - j > 86400000) {
            SharedPreferences.Editor editorEdit = this.f1243a.edit();
            editorEdit.putLong("BY4NA", a(com.topfreegames.c.a.a()).getTime());
            editorEdit.putInt("I8FWH", this.b.y());
            editorEdit.commit();
        }
        return c();
    }

    private static Date a(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(11, 0);
        gregorianCalendar.set(12, 0);
        gregorianCalendar.set(13, 0);
        gregorianCalendar.set(14, 0);
        return gregorianCalendar.getTime();
    }

    public int b() {
        if (!f()) {
            g();
        }
        return c();
    }

    private boolean f() {
        int iH = h();
        if (iH <= 0) {
            return false;
        }
        SharedPreferences.Editor editorEdit = this.f1243a.edit();
        editorEdit.putInt("I8FWH", iH - 1);
        editorEdit.commit();
        return true;
    }

    private boolean g() {
        int i = i();
        if (i <= 0) {
            return false;
        }
        SharedPreferences.Editor editorEdit = this.f1243a.edit();
        editorEdit.putInt("D64MJ", i - 1);
        editorEdit.commit();
        return true;
    }

    private int h() {
        return this.f1243a.getInt("I8FWH", 0);
    }

    private int i() {
        return this.f1243a.getInt("D64MJ", 0);
    }

    public int c() {
        return h() + i();
    }

    public int d() {
        return a(10);
    }

    public int e() {
        return a(1);
    }

    private int a(int i) {
        int i2 = i() + i;
        SharedPreferences.Editor editorEdit = this.f1243a.edit();
        editorEdit.putInt("D64MJ", i2);
        editorEdit.commit();
        return i2;
    }
}
