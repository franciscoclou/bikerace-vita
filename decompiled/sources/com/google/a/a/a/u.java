package com.google.a.a.a;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.facebook.widget.PlacePickerFragment;

/* JADX INFO: compiled from: GAServiceManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class u extends aq {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Object f599a = new Object();
    private static u o;
    private Context b;
    private f c;
    private volatile h d;
    private boolean g;
    private String h;
    private Handler l;
    private t m;
    private int e = 1800;
    private boolean f = true;
    private boolean i = true;
    private boolean j = true;
    private g k = new g() { // from class: com.google.a.a.a.u.1
        @Override // com.google.a.a.a.g
        public void a(boolean z) {
            u.this.a(z, u.this.i);
        }
    };
    private boolean n = false;

    public static u a() {
        if (o == null) {
            o = new u();
        }
        return o;
    }

    private u() {
    }

    private void g() {
        this.m = new t(this);
        this.m.a(this.b);
    }

    private void h() {
        this.l = new Handler(this.b.getMainLooper(), new Handler.Callback() { // from class: com.google.a.a.a.u.2
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                if (1 == message.what && u.f599a.equals(message.obj)) {
                    ac.a().a(true);
                    u.this.c();
                    ac.a().a(false);
                    if (u.this.e > 0 && !u.this.n) {
                        u.this.l.sendMessageDelayed(u.this.l.obtainMessage(1, u.f599a), u.this.e * PlacePickerFragment.DEFAULT_RADIUS_IN_METERS);
                    }
                }
                return true;
            }
        });
        if (this.e > 0) {
            this.l.sendMessageDelayed(this.l.obtainMessage(1, f599a), this.e * PlacePickerFragment.DEFAULT_RADIUS_IN_METERS);
        }
    }

    synchronized void a(Context context, h hVar) {
        if (this.b == null) {
            this.b = context.getApplicationContext();
            if (this.d == null) {
                this.d = hVar;
                if (this.f) {
                    c();
                    this.f = false;
                }
                if (this.g) {
                    d();
                    this.g = false;
                }
            }
        }
    }

    synchronized f b() {
        if (this.c == null) {
            if (this.b == null) {
                throw new IllegalStateException("Cant get a store unless we have a context");
            }
            this.c = new an(this.k, this.b);
            if (this.h != null) {
                this.c.b().a(this.h);
                this.h = null;
            }
        }
        if (this.l == null) {
            h();
        }
        if (this.m == null && this.j) {
            g();
        }
        return this.c;
    }

    @Override // com.google.a.a.a.aq
    @Deprecated
    public synchronized void c() {
        if (this.d == null) {
            ah.c("Dispatch call queued. Dispatch will run once initialization is complete.");
            this.f = true;
        } else {
            ac.a().a(ad.DISPATCH);
            this.d.a();
        }
    }

    @Override // com.google.a.a.a.aq
    @Deprecated
    public synchronized void a(int i) {
        if (this.l == null) {
            ah.c("Dispatch period set with null handler. Dispatch will run once initialization is complete.");
            this.e = i;
        } else {
            ac.a().a(ad.SET_DISPATCH_PERIOD);
            if (!this.n && this.i && this.e > 0) {
                this.l.removeMessages(1, f599a);
            }
            this.e = i;
            if (i > 0 && !this.n && this.i) {
                this.l.sendMessageDelayed(this.l.obtainMessage(1, f599a), i * PlacePickerFragment.DEFAULT_RADIUS_IN_METERS);
            }
        }
    }

    @Deprecated
    public void d() {
        if (this.d == null) {
            ah.c("setForceLocalDispatch() queued. It will be called once initialization is complete.");
            this.g = true;
        } else {
            ac.a().a(ad.SET_FORCE_LOCAL_DISPATCH);
            this.d.b();
        }
    }

    synchronized void a(boolean z, boolean z2) {
        if (this.n != z || this.i != z2) {
            if ((z || !z2) && this.e > 0) {
                this.l.removeMessages(1, f599a);
            }
            if (!z && z2 && this.e > 0) {
                this.l.sendMessageDelayed(this.l.obtainMessage(1, f599a), this.e * PlacePickerFragment.DEFAULT_RADIUS_IN_METERS);
            }
            ah.c("PowerSaveMode " + ((z || !z2) ? "initiated." : "terminated."));
            this.n = z;
            this.i = z2;
        }
    }

    @Override // com.google.a.a.a.aq
    synchronized void a(boolean z) {
        a(this.n, z);
    }

    @Override // com.google.a.a.a.aq
    synchronized void e() {
        if (!this.n && this.i && this.e > 0) {
            this.l.removeMessages(1, f599a);
            this.l.sendMessage(this.l.obtainMessage(1, f599a));
        }
    }
}
