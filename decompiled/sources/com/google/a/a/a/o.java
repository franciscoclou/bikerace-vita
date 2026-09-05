package com.google.a.a.a;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.facebook.AppEventsConstants;
import com.facebook.widget.PlacePickerFragment;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: compiled from: EasyTracker.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class o extends au {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static o f594a;
    private static String b;
    private final ae c;
    private boolean d;
    private boolean e;
    private int f;
    private long g;
    private long h;
    private Context i;
    private final Map<String, String> j;
    private al k;
    private aq l;
    private k m;
    private Timer n;
    private TimerTask o;
    private boolean p;
    private boolean q;

    private o(Context context) {
        this(context, new am(context), ae.a(context), u.a(), null);
    }

    private o(Context context, al alVar, ae aeVar, aq aqVar, av avVar) {
        super("easy_tracker", null, avVar == null ? aeVar : avVar);
        this.e = false;
        this.f = 0;
        this.j = new HashMap();
        this.p = false;
        this.q = false;
        if (b != null) {
            alVar.d(b);
        }
        this.c = aeVar;
        a(context, alVar, aqVar);
        this.m = new k() { // from class: com.google.a.a.a.o.1
            @Override // com.google.a.a.a.k
            public long a() {
                return System.currentTimeMillis();
            }
        };
    }

    public static o a(Context context) {
        if (f594a == null) {
            f594a = new o(context);
        }
        return f594a;
    }

    boolean a() {
        return this.g == 0 || (this.g > 0 && this.m.a() > this.h + this.g);
    }

    private void c() {
        aj ajVarA;
        ah.c("Starting EasyTracker.");
        String strA = this.k.a("ga_trackingId");
        if (TextUtils.isEmpty(strA)) {
            strA = this.k.a("ga_api_key");
        }
        a("&tid", strA);
        ah.c("[EasyTracker] trackingId loaded: " + strA);
        String strA2 = this.k.a("ga_appName");
        if (!TextUtils.isEmpty(strA2)) {
            ah.c("[EasyTracker] app name loaded: " + strA2);
            a("&an", strA2);
        }
        String strA3 = this.k.a("ga_appVersion");
        if (strA3 != null) {
            ah.c("[EasyTracker] app version loaded: " + strA3);
            a("&av", strA3);
        }
        String strA4 = this.k.a("ga_logLevel");
        if (strA4 != null && (ajVarA = a(strA4)) != null) {
            ah.c("[EasyTracker] log level loaded: " + ajVarA);
            this.c.d().a(ajVarA);
        }
        Double dB = this.k.b("ga_sampleFrequency");
        if (dB == null) {
            dB = new Double(this.k.a("ga_sampleRate", 100));
        }
        if (dB.doubleValue() != 100.0d) {
            a("&sf", Double.toString(dB.doubleValue()));
        }
        ah.c("[EasyTracker] sample rate loaded: " + dB);
        int iA = this.k.a("ga_dispatchPeriod", 1800);
        ah.c("[EasyTracker] dispatch period loaded: " + iA);
        this.l.a(iA);
        this.g = this.k.a("ga_sessionTimeout", 30) * PlacePickerFragment.DEFAULT_RADIUS_IN_METERS;
        ah.c("[EasyTracker] session timeout loaded: " + this.g);
        this.e = this.k.c("ga_autoActivityTracking") || this.k.c("ga_auto_activity_tracking");
        ah.c("[EasyTracker] auto activity tracking loaded: " + this.e);
        boolean zC = this.k.c("ga_anonymizeIp");
        if (zC) {
            a("&aip", AppEventsConstants.EVENT_PARAM_VALUE_YES);
            ah.c("[EasyTracker] anonymize ip loaded: " + zC);
        }
        this.d = this.k.c("ga_reportUncaughtExceptions");
        if (this.d) {
            Thread.setDefaultUncaughtExceptionHandler(new r(this, this.l, Thread.getDefaultUncaughtExceptionHandler(), this.i));
            ah.c("[EasyTracker] report uncaught exceptions loaded: " + this.d);
        }
        this.c.a(this.k.c("ga_dryRun"));
    }

    private aj a(String str) {
        try {
            return aj.valueOf(str.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private void a(Context context, al alVar, aq aqVar) {
        if (context == null) {
            ah.a("Context cannot be null");
        }
        this.i = context.getApplicationContext();
        this.l = aqVar;
        this.k = alVar;
        c();
    }

    public void a(Activity activity) {
        ac.a().a(ad.EASY_TRACKER_ACTIVITY_START);
        d();
        if (!this.p && this.f == 0 && a()) {
            this.q = true;
        }
        this.p = true;
        this.f++;
        if (this.e) {
            HashMap map = new HashMap();
            map.put("&t", "appview");
            ac.a().a(true);
            a("&cd", c(activity));
            a(map);
            ac.a().a(false);
        }
    }

    public void b(Activity activity) {
        ac.a().a(ad.EASY_TRACKER_ACTIVITY_STOP);
        this.f--;
        this.f = Math.max(0, this.f);
        this.h = this.m.a();
        if (this.f == 0) {
            d();
            this.o = new p(this);
            this.n = new Timer("waitForActivityStart");
            this.n.schedule(this.o, 1000L);
        }
    }

    private synchronized void d() {
        if (this.n != null) {
            this.n.cancel();
            this.n = null;
        }
    }

    private String c(Activity activity) {
        String canonicalName = activity.getClass().getCanonicalName();
        if (this.j.containsKey(canonicalName)) {
            return this.j.get(canonicalName);
        }
        String strA = this.k.a(canonicalName);
        if (strA == null) {
            strA = canonicalName;
        }
        this.j.put(canonicalName, strA);
        return strA;
    }

    @Override // com.google.a.a.a.au
    public void a(Map<String, String> map) {
        if (this.q) {
            map.put("&sc", "start");
            this.q = false;
        }
        super.a(map);
    }
}
