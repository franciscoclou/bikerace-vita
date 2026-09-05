package com.google.a.a.a;

import android.content.Context;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: GoogleAnalytics.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ae extends av {
    private static ae g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f574a;
    private h b;
    private Context c;
    private volatile Boolean d;
    private final Map<String, au> e;
    private ai f;

    protected ae(Context context) {
        this(context, ab.a(context));
    }

    private ae(Context context, h hVar) {
        this.d = false;
        this.e = new HashMap();
        if (context == null) {
            throw new IllegalArgumentException("context cannot be null");
        }
        this.c = context.getApplicationContext();
        this.b = hVar;
        i.a(this.c);
        ap.a(this.c);
        j.a(this.c);
        this.f = new l();
    }

    public static ae a(Context context) {
        ae aeVar;
        synchronized (ae.class) {
            if (g == null) {
                g = new ae(context);
            }
            aeVar = g;
        }
        return aeVar;
    }

    static ae a() {
        ae aeVar;
        synchronized (ae.class) {
            aeVar = g;
        }
        return aeVar;
    }

    public void a(boolean z) {
        ac.a().a(ad.SET_DRY_RUN);
        this.f574a = z;
    }

    public boolean b() {
        ac.a().a(ad.GET_DRY_RUN);
        return this.f574a;
    }

    @Override // com.google.a.a.a.av
    void a(Map<String, String> map) {
        synchronized (this) {
            try {
                if (map == null) {
                    throw new IllegalArgumentException("hit cannot be null");
                }
                aw.a(map, "&ul", aw.a(Locale.getDefault()));
                aw.a(map, "&sr", ap.a().a("&sr"));
                map.put("&_u", ac.a().c());
                ac.a().b();
                this.b.a(map);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean c() {
        ac.a().a(ad.GET_APP_OPT_OUT);
        return this.d.booleanValue();
    }

    public ai d() {
        return this.f;
    }
}
