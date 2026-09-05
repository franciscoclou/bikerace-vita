package com.heyzap.sdk.ads.a;

import com.heyzap.internal.k;
import java.util.Timer;
import java.util.TimerTask;
import org.a.a.a.a.g;
import org.a.a.a.a.l;

/* JADX INFO: compiled from: FramePing.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    g f771a;
    long b;
    c c;
    private boolean d;

    public b(a aVar, String str, c cVar) {
        try {
            this.c = cVar;
            final String str2 = String.format("%s/pong", str);
            k.a("mqtt client attempting to connect to host for ping:", aVar.a());
            this.f771a = new g(aVar.a(), g.d(), new org.a.a.a.a.c.a());
            this.f771a.a(new org.a.a.a.a.f() { // from class: com.heyzap.sdk.ads.a.b.1
                @Override // org.a.a.a.a.f
                public void a(Throwable th) {
                    b.this.a(th);
                }

                @Override // org.a.a.a.a.f
                public void a(org.a.a.a.a.c cVar2) {
                }

                @Override // org.a.a.a.a.f
                public void a(String str3, l lVar) {
                    k.a("got message", str3);
                    if (str2.equals(str3)) {
                        b.this.a(System.currentTimeMillis() - b.this.b);
                    }
                }
            });
            this.f771a.a();
            this.f771a.a(str2);
            this.b = System.currentTimeMillis();
            org.b.a.c cVar2 = new org.b.a.c();
            cVar2.a("channel", str2);
            this.f771a.a("ping", new l(cVar2.toString().getBytes()));
            new Timer().schedule(new TimerTask() { // from class: com.heyzap.sdk.ads.a.b.2
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    if (!b.this.d) {
                        b.this.a(new RuntimeException("Connection timed out"));
                    }
                }
            }, 1000L);
        } catch (org.a.a.a.a.k e) {
            e.printStackTrace();
            a(e);
        } catch (org.b.a.b e2) {
            e2.printStackTrace();
            a(e2);
        }
    }

    public synchronized void a(long j) {
        if (!this.d) {
            try {
                this.c.a(j);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            a();
        }
    }

    public void a(Throwable th) {
        if (!this.d) {
            try {
                this.c.a(th);
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
            a();
        }
    }

    public void a() {
        new Thread(new Runnable() { // from class: com.heyzap.sdk.ads.a.b.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    b.this.f771a.b();
                } catch (org.a.a.a.a.k e) {
                    e.printStackTrace();
                }
            }
        }).start();
        this.d = true;
    }
}
