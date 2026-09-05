package com.topfreegames.e.b.a;

import android.os.Bundle;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Session;
import com.topfreegames.bikerace.ap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: compiled from: TopFacebookAppRequestCreateAppUserHandler.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class b extends com.topfreegames.e.b.f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a f1506a;
    private String b;
    private String c;
    private String d;
    private long e;
    private Timer f;

    public void a() {
        try {
            final Session activeSession = Session.getActiveSession();
            if (activeSession != null) {
                final String str = String.valueOf(this.b) + "/apprequests";
                final Bundle bundle = new Bundle();
                bundle.putString("access_key", activeSession.getAccessToken());
                bundle.putString("message", this.c);
                if (this.d != null) {
                    bundle.putString("data", this.d);
                }
                final Thread thread = new Thread(new Runnable() { // from class: com.topfreegames.e.b.a.b.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            Request.executeAndWait(new Request(activeSession, str, bundle, HttpMethod.POST, new c(b.this, null)));
                        } catch (Exception e) {
                            if (ap.d()) {
                                e.printStackTrace();
                            }
                            b.this.a(null);
                        }
                    }
                });
                thread.start();
                this.f = new Timer();
                this.f.schedule(new TimerTask() { // from class: com.topfreegames.e.b.a.b.2
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        synchronized (b.this) {
                            thread.interrupt();
                            b.this.f = null;
                            b.this.a(null);
                        }
                    }
                }, this.e);
                return;
            }
            a(null);
        } catch (Exception e) {
            if (ap.d()) {
                e.printStackTrace();
            }
            a(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Map<String, String> map) {
        if (this.f != null) {
            this.f.cancel();
            this.f = null;
        }
        if (this.f1506a != null) {
            this.f1506a.a(map);
        }
    }

    @Override // com.topfreegames.e.b.f
    public void d() {
        this.f1506a = null;
        if (this.f != null) {
            this.f.cancel();
            this.f = null;
        }
    }
}
