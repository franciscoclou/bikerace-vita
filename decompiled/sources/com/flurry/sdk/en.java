package com.flurry.sdk;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class en implements ei.a {
    private static final String c = en.class.getSimpleName();
    private static en l;
    boolean b;
    private LocationManager f;
    private Criteria g;
    private Location h;
    private String j;
    private final long d = 1800000;
    private final long e = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    boolean f539a = false;
    private int k = 0;
    private a i = new a();

    private en() {
        ei eiVarA = eh.a();
        this.g = (Criteria) eiVarA.a("LocationCriteria");
        eiVarA.a("LocationCriteria", (ei.a) this);
        ex.a(4, c, "initSettings, LocationCriteria = " + this.g);
        this.b = ((Boolean) eiVarA.a("ReportLocation")).booleanValue();
        eiVarA.a("ReportLocation", (ei.a) this);
        ex.a(4, c, "initSettings, ReportLocation = " + this.b);
    }

    public static synchronized en a() {
        if (l == null) {
            l = new en();
        }
        return l;
    }

    public synchronized void b() {
        if (this.f == null) {
            this.f = (LocationManager) eg.a().b().getSystemService("location");
        }
    }

    public synchronized void c() {
        ex.a(4, c, "Location provider subscribed");
        this.k++;
        if (!this.f539a) {
            h();
        }
    }

    public synchronized void d() {
        ex.a(4, c, "Location provider unsubscribed");
        if (this.k <= 0) {
            ex.a(6, c, "Error! Unsubscribed too many times!");
        } else {
            this.k--;
            if (this.k == 0) {
                g();
            }
        }
    }

    private void g() {
        this.f.removeUpdates(this.i);
        this.f539a = false;
        ex.a(4, c, "LocationProvider stoped");
    }

    private void h() {
        if (this.b) {
            Context contextB = eg.a().b();
            if (contextB.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0 || contextB.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0) {
                g();
                String strI = i();
                a(strI);
                this.h = b(strI);
                this.f539a = true;
                ex.a(4, c, "LocationProvider started");
            }
        }
    }

    private String i() {
        String bestProvider;
        Criteria criteria = this.g;
        if (criteria == null) {
            criteria = new Criteria();
        }
        if (TextUtils.isEmpty(this.j)) {
            bestProvider = this.f.getBestProvider(criteria, true);
        } else {
            bestProvider = this.j;
        }
        ex.a(4, c, "provider = " + bestProvider);
        return bestProvider;
    }

    private void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f.requestLocationUpdates(str, 1800000L, 0.0f, this.i, Looper.getMainLooper());
        }
    }

    private Location b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.f.getLastKnownLocation(str);
    }

    public Location e() {
        Location location = null;
        if (this.b) {
            Location locationB = b(i());
            if (locationB != null) {
                this.h = locationB;
            }
            location = this.h;
        }
        ex.a(4, c, "getLocation() = " + location);
        return location;
    }

    public void f() {
        this.k = 0;
        g();
    }

    class a implements LocationListener {
        public a() {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            if (location != null) {
                en.this.h = location;
            }
        }
    }

    @Override // com.flurry.sdk.ei.a
    public void a(String str, Object obj) {
        if (str.equals("LocationCriteria")) {
            this.g = (Criteria) obj;
            ex.a(4, c, "onSettingUpdate, LocationCriteria = " + this.g);
            if (this.f539a) {
                h();
                return;
            }
            return;
        }
        if (str.equals("ReportLocation")) {
            this.b = ((Boolean) obj).booleanValue();
            ex.a(4, c, "onSettingUpdate, ReportLocation = " + this.b);
            if (this.b) {
                if (!this.f539a && this.k > 0) {
                    h();
                    return;
                }
                return;
            }
            g();
            return;
        }
        ex.a(6, c, "LocationProvider internal error! Had to be LocationCriteria or ReportLocation key.");
    }
}
