package com.flurry.sdk;

import android.content.Context;
import java.io.File;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class by {
    private static final String b = by.class.getSimpleName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    boolean f505a;
    private final bz c;
    private final File d;
    private String e;

    public by() {
        this(eg.a().b());
    }

    public by(Context context) {
        this.c = new bz();
        this.d = context.getFileStreamPath(".flurryinstallreceiver.");
        ex.a(3, b, "Referrer file name if it exists:  " + this.d);
    }

    public synchronized void a() {
        this.d.delete();
        this.e = null;
        this.f505a = true;
    }

    public synchronized Map<String, List<String>> a(boolean z) {
        Map<String, List<String>> mapA;
        b();
        mapA = this.c.a(this.e);
        if (z) {
            a();
        }
        return mapA;
    }

    public synchronized void a(String str) {
        this.f505a = true;
        b(str);
        c();
    }

    private void b(String str) {
        if (str != null) {
            this.e = str;
        }
    }

    private void b() throws Throwable {
        if (!this.f505a) {
            this.f505a = true;
            ex.a(4, b, "Loading referrer info from file: " + this.d.getAbsolutePath());
            String strB = et.b(this.d);
            ex.a(b, "Referrer file contents: " + strB);
            b(strB);
        }
    }

    private void c() throws Throwable {
        et.a(this.d, this.e);
    }
}
