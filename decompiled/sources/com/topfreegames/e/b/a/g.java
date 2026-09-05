package com.topfreegames.e.b.a;

import android.os.Bundle;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Session;
import com.topfreegames.bikerace.ap;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: compiled from: TopFacebookAppRequestDeleteHandler.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class g extends com.topfreegames.e.b.f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private f f1513a;
    private String b;
    private long c;
    private Timer d = null;

    public g(f fVar, String str, long j) {
        this.f1513a = null;
        this.b = null;
        this.c = 0L;
        if (str == null) {
            throw new IllegalArgumentException("Message cannot be null!");
        }
        if (j < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative!");
        }
        this.f1513a = fVar;
        this.b = str;
        this.c = j;
    }

    public void a() {
        try {
            final Session activeSession = Session.getActiveSession();
            if (activeSession != null) {
                final String str = "/" + this.b;
                final Thread thread = new Thread(new Runnable() { // from class: com.topfreegames.e.b.a.g.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            Request.executeAndWait(new Request(activeSession, str, new Bundle(), HttpMethod.DELETE, new h(g.this, null)));
                        } catch (Exception e) {
                            if (ap.d()) {
                                e.printStackTrace();
                            }
                            g.this.a(false, true);
                        }
                    }
                });
                thread.start();
                this.d = new Timer();
                this.d.schedule(new TimerTask() { // from class: com.topfreegames.e.b.a.g.2
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        synchronized (g.this) {
                            thread.interrupt();
                            g.this.d = null;
                            g.this.a(false, true);
                        }
                    }
                }, this.c);
            } else {
                a(false, true);
            }
        } catch (Exception e) {
            if (ap.d()) {
                e.printStackTrace();
            }
            a(false, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, boolean z2) {
        if (this.d != null) {
            this.d.cancel();
            this.d = null;
        }
        if (this.f1513a != null) {
            this.f1513a.a(this.b, z);
        }
    }

    @Override // com.topfreegames.e.b.f
    public void d() {
        this.f1513a = null;
        if (this.d != null) {
            this.d.cancel();
            this.d = null;
        }
    }
}
