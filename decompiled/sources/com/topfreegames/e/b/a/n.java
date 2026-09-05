package com.topfreegames.e.b.a;

import android.os.Bundle;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Session;
import com.topfreegames.bikerace.ap;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: compiled from: TopFacebookAppRequestReadHandler.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class n extends com.topfreegames.e.b.f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private m f1521a;
    private String b;
    private long c;
    private Timer d = null;

    public n(m mVar, String str, long j) {
        this.f1521a = null;
        this.b = null;
        this.c = 0L;
        if (str == null) {
            throw new IllegalArgumentException("UserId cannot be null!");
        }
        if (j < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative!");
        }
        this.f1521a = mVar;
        this.b = str;
        this.c = j;
    }

    public void a() {
        final Session activeSession = Session.getActiveSession();
        if (activeSession != null) {
            final String str = String.valueOf(this.b) + "/apprequests";
            final Thread thread = new Thread(new Runnable() { // from class: com.topfreegames.e.b.a.n.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Request.executeAndWait(new Request(activeSession, str, new Bundle(), HttpMethod.GET, new o(n.this, null)));
                    } catch (Exception e) {
                        if (ap.d()) {
                            e.printStackTrace();
                        }
                        n.this.a(null, null, null, null, null, true);
                    }
                }
            });
            thread.start();
            this.d = new Timer();
            this.d.schedule(new TimerTask() { // from class: com.topfreegames.e.b.a.n.2
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    synchronized (n.this) {
                        n.this.d = null;
                        thread.interrupt();
                        n.this.a(null, null, null, null, null, true);
                    }
                }
            }, this.c);
            return;
        }
        a(null, null, null, null, null, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, String str3, String str4, String str5, boolean z) {
        if (this.d != null) {
            this.d.cancel();
            this.d = null;
        }
        if (this.f1521a != null && !z) {
            this.f1521a.a(str, str2, str3, str4, str5);
        }
    }

    @Override // com.topfreegames.e.b.f
    public void d() {
        this.f1521a = null;
        if (this.d != null) {
            this.d.cancel();
            this.d = null;
        }
    }
}
