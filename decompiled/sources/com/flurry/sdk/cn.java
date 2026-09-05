package com.flurry.sdk;

import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class cn {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Timer f530a;
    private a b;
    private b c;

    public interface b {
        void n();
    }

    class a extends TimerTask {
        private b b;

        a(b bVar) {
            this.b = bVar;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            cn.this.a();
            if (this.b != null) {
                this.b.n();
            }
        }
    }

    cn(b bVar) {
        this.c = bVar;
    }

    public synchronized void a(long j) {
        if (b()) {
            a();
        }
        this.f530a = new Timer("FlurrySessionTimer");
        this.b = new a(this.c);
        this.f530a.schedule(this.b, j);
    }

    public synchronized void a() {
        if (this.f530a != null) {
            this.f530a.cancel();
            this.f530a = null;
        }
        this.b = null;
    }

    public boolean b() {
        return this.f530a != null;
    }
}
