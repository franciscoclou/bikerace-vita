package com.topfreegames.e.b;

import android.graphics.Bitmap;
import android.os.Bundle;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Session;
import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: compiled from: TopFacebookUserInfoRequestHandler.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class n extends f implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private WeakReference<p> f1534a;
    private String b;
    private String c;
    private String d;
    private Bitmap e;
    private boolean f;
    private boolean g;
    private boolean h;
    private Timer i;
    private int j = 0;
    private final int k = 2;
    private long l;
    private boolean m;

    public n(WeakReference<p> weakReference, String str, boolean z, Bitmap bitmap, long j) {
        this.f1534a = weakReference;
        this.e = bitmap;
        this.b = str;
        this.l = j;
        this.m = z;
    }

    public String b() {
        return this.b;
    }

    public WeakReference<p> c() {
        return this.f1534a;
    }

    public void a(WeakReference<p> weakReference) {
        this.f1534a = weakReference;
    }

    public void e() {
        if (this.b != null) {
            this.i = new Timer();
            this.i.schedule(new TimerTask() { // from class: com.topfreegames.e.b.n.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        if (!n.this.g) {
                            n.this.f();
                        } else {
                            n.this.a(null, n.this.h, true);
                        }
                    }
                }
            }, this.l);
            this.f = this.m;
            this.g = true;
            if (this.b.equals("me")) {
                this.h = true;
            } else {
                this.h = false;
            }
            if (this.h) {
                this.j = 1;
            }
            a(this.b);
            if (this.m) {
                c.b().a(new a(this, this.b, this.l));
                return;
            }
            return;
        }
        a(null, false, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        a(new com.topfreegames.e.l(this.c, this.d, this.e), this.h, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.topfreegames.e.l lVar, boolean z, boolean z2) {
        p pVar;
        synchronized (this) {
            if (this.f1534a != null && (pVar = this.f1534a.get()) != null) {
                pVar.a(lVar, this, z, z2);
            }
            if (this.i != null) {
                this.i.cancel();
                this.i = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("fields", "id,name");
        Session activeSession = Session.getActiveSession();
        if (activeSession == null || !activeSession.isOpened()) {
            activeSession = null;
        }
        Request.executeAndWait(new Request(activeSession, str, bundle, HttpMethod.GET, new o(this)));
    }

    @Override // com.topfreegames.e.b.b
    public void a(Bitmap bitmap, String str, a aVar, boolean z) {
        this.e = bitmap;
        synchronized (this) {
            this.f = false;
            if (!this.g) {
                f();
            }
        }
    }

    @Override // com.topfreegames.e.b.e
    public void a() {
        p pVar;
        if (this.f1534a != null && (pVar = this.f1534a.get()) != null) {
            pVar.a();
        }
    }

    @Override // com.topfreegames.e.b.f
    public void d() {
        this.f1534a = null;
    }
}
