package com.topfreegames.bikerace.worldcup.a;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.topfreegames.bikerace.activities.WorldCupShopActivity;
import com.topfreegames.bikerace.activities.n;

/* JADX INFO: compiled from: WorldCupShopMode.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected WorldCupShopActivity f1432a;
    protected View b;

    protected abstract int a();

    public abstract void a(Bundle bundle);

    protected abstract void b();

    public abstract void c();

    protected abstract boolean d();

    public abstract void e();

    protected g(WorldCupShopActivity worldCupShopActivity) {
        if (worldCupShopActivity == null) {
            throw new IllegalArgumentException("Activity cannot be null!");
        }
        this.f1432a = worldCupShopActivity;
    }

    public View b(final Bundle bundle) {
        if (this.b == null) {
            this.b = ((LayoutInflater) this.f1432a.getSystemService("layout_inflater")).inflate(a(), (ViewGroup) null);
            b();
        }
        if (d()) {
            this.b.postDelayed(new Runnable() { // from class: com.topfreegames.bikerace.worldcup.a.g.1
                @Override // java.lang.Runnable
                public void run() {
                    g.this.e();
                    n.a(g.this.f1432a, g.this.b);
                    g.this.a(bundle);
                }
            }, 300L);
        } else {
            a(bundle);
        }
        return this.b;
    }
}
