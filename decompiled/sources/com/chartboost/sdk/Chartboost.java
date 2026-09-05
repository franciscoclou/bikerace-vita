package com.chartboost.sdk;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import com.chartboost.sdk.Analytics.CBAnalytics;
import com.chartboost.sdk.Libraries.CBOrientation;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.impl.j;
import com.chartboost.sdk.impl.k;
import com.chartboost.sdk.impl.o;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class Chartboost {
    private static Chartboost d = null;
    protected j b;
    protected Handler c;
    private com.chartboost.sdk.b e;
    private com.chartboost.sdk.a i;
    private String j;
    private String k;
    private ChartboostDelegate l;
    private CBOrientation o;
    private boolean p;
    private Context f = null;
    private CBImpressionActivity g = null;
    private List<com.chartboost.sdk.b.a> h = new ArrayList();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected b f368a = null;
    private int m = 30000;
    private boolean n = false;
    private boolean q = false;
    private boolean r = false;
    private String s = null;
    private boolean t = false;
    private SparseBooleanArray u = new SparseBooleanArray();
    private SparseArray<com.chartboost.sdk.b> v = new SparseArray<>();
    private boolean w = false;
    private long x = 0;
    private long y = 0;
    private boolean z = false;
    private Runnable A = new a(this, null);

    public interface CBAPIResponseCallback {
        void onFailure(String str);

        void onSuccess(JSONObject jSONObject);
    }

    class b extends WeakReference<Activity> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f373a;

        public b(Activity activity) {
            super(activity);
            this.f373a = activity.hashCode();
        }

        public boolean a(Activity activity) {
            return activity != null && activity.hashCode() == this.f373a;
        }

        public boolean a(b bVar) {
            return bVar != null && bVar.a() == this.f373a;
        }

        public int a() {
            return this.f373a;
        }

        public int hashCode() {
            return a();
        }

        public Context b() {
            Context context = (Context) get();
            if (context == null) {
                return Chartboost.sharedChartboost().getContext();
            }
            return context;
        }
    }

    public static synchronized Chartboost sharedChartboost() {
        if (d == null) {
            d = new Chartboost();
        }
        return d;
    }

    private Chartboost() {
        d = this;
        this.c = new Handler();
        this.b = new j(null, null);
        this.i = new com.chartboost.sdk.a(this);
    }

    public CBAnalytics getAnalytics() {
        return CBAnalytics.sharedAnalytics();
    }

    public void startSession() {
        if (this.f368a == null) {
            throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before calling startSession().");
        }
        long jNanoTime = (long) (System.nanoTime() / 1000000.0d);
        if (jNanoTime - this.x >= 10000 && jNanoTime - this.y >= 60000) {
            this.y = jNanoTime;
            SharedPreferences sharedPreferencesA = com.chartboost.sdk.Libraries.d.a();
            int i = sharedPreferencesA.getInt("cbPrefSessionCount", 0) + 1;
            SharedPreferences.Editor editorEdit = sharedPreferencesA.edit();
            editorEdit.putInt("cbPrefSessionCount", i);
            editorEdit.commit();
            k kVar = new k("api/install");
            kVar.a(this.f368a.b());
            kVar.b(getAppID(), getAppSignature());
            kVar.a(e.a("status", j.f465a));
            this.b.a(kVar, new j.c() { // from class: com.chartboost.sdk.Chartboost.1
                @Override // com.chartboost.sdk.impl.j.b
                public void a(JSONObject jSONObject, k kVar2) {
                    String strOptString;
                    if (com.chartboost.sdk.Libraries.d.a(Chartboost.this.getContext()) && (strOptString = jSONObject.optString("latest-sdk-version")) != null && !strOptString.equals("") && !strOptString.equals("3.4.0")) {
                        Log.w("Chartboost", String.format("WARNING: you have an outdated version of the SDK (Current: %s, Latest: %s). Get the latest version at chartboost.com/support/sdk#android", strOptString, "3.4.0"));
                    }
                }
            });
        }
    }

    public Context getContext() {
        return this.f;
    }

    public void onCreate(Activity activity, String str, String str2, ChartboostDelegate chartboostDelegate) {
        if (!this.q && !com.chartboost.sdk.Libraries.d.d()) {
            throw new IllegalStateException("It is illegal to call this method from any thread other than the UI thread. Please call it from the onCreate() method of your host activity.");
        }
        if (this.f368a != null && !this.f368a.a(activity) && e()) {
            b(this.f368a);
            a(this.f368a, false);
        }
        this.c.removeCallbacks(this.A);
        this.f368a = new b(activity);
        this.f = activity.getApplicationContext();
        setAppID(str);
        setAppSignature(str2);
        setDelegate(chartboostDelegate);
    }

    private boolean e() {
        return a(this.f368a);
    }

    private boolean a(b bVar) {
        Boolean boolValueOf;
        if (bVar == null || (boolValueOf = Boolean.valueOf(this.u.get(bVar.a()))) == null) {
            return false;
        }
        return boolValueOf.booleanValue();
    }

    private void a(Activity activity, boolean z) {
        if (activity != null) {
            a(activity.hashCode(), z);
        }
    }

    private void a(b bVar, boolean z) {
        if (bVar != null) {
            a(bVar.a(), z);
        }
    }

    private void a(int i, boolean z) {
        this.u.put(i, z);
    }

    private void b(b bVar, boolean z) {
        com.chartboost.sdk.b bVar2;
        int iA = bVar.a();
        com.chartboost.sdk.b bVar3 = this.v.get(iA);
        if (bVar3 == null && z) {
            if (this.e != null) {
                com.chartboost.sdk.b bVar4 = this.e;
                this.e = null;
                bVar4.a((Activity) bVar.get());
                bVar2 = bVar4;
            } else {
                bVar2 = new com.chartboost.sdk.b(this, (Activity) bVar.get());
            }
            this.v.put(iA, bVar2);
            return;
        }
        if (bVar3 != null && !z) {
            com.chartboost.sdk.b bVar5 = this.v.get(iA);
            this.v.remove(iA);
            bVar5.a((Activity) null);
            if (this.e == null) {
                this.e = bVar5;
            }
        }
    }

    protected com.chartboost.sdk.b a() {
        Activity activityC = c();
        if (activityC == null) {
            return null;
        }
        return this.v.get(activityC.hashCode());
    }

    public void onStart(Activity activity) {
        if (!this.q && !com.chartboost.sdk.Libraries.d.d()) {
            throw new IllegalStateException("It is illegal to call this method from any thread other than the UI thread. Please call it from the onStart() method of your host activity.");
        }
        this.c.removeCallbacks(this.A);
        if (this.f368a != null && !this.f368a.a(activity) && e()) {
            b(this.f368a);
            a(this.f368a, false);
        }
        a(activity, true);
        this.f368a = new b(activity);
        this.f = activity.getApplicationContext();
        if (!this.n) {
            a(activity);
        }
    }

    protected void a(Activity activity) {
        boolean z;
        this.f = activity.getApplicationContext();
        if (!(activity instanceof CBImpressionActivity)) {
            this.f368a = new b(activity);
            a(this.f368a, true);
        } else {
            a((CBImpressionActivity) activity);
        }
        this.c.removeCallbacks(this.A);
        if (activity != null && d(activity)) {
            b(new b(activity), true);
            if (activity instanceof CBImpressionActivity) {
                com.chartboost.sdk.b bVarA = a();
                if (bVarA != null) {
                    for (int i = 0; i < this.h.size(); i++) {
                        bVarA.a(this.h.get(i));
                    }
                    this.h.clear();
                }
                this.z = false;
            }
            if (this.w) {
                this.w = false;
                z = true;
            } else {
                z = false;
            }
            if (this.i.a() != null && this.i.a().c == com.chartboost.sdk.impl.a.b.CBImpressionStateWaitingForDisplay && this.i.a().a()) {
                z = false;
            }
            if (z) {
                a(new com.chartboost.sdk.b.a(true, null));
            }
        }
    }

    public void onStop(Activity activity) {
        if (!this.q && !com.chartboost.sdk.Libraries.d.d()) {
            throw new IllegalStateException("It is illegal to call this method from any thread other than the UI thread. Please call it from the onStop() method of your host activity.");
        }
        b bVar = new b(activity);
        if (a(bVar)) {
            b(bVar);
        }
    }

    private void b(b bVar) {
        if (!this.n) {
            c(bVar);
        }
        if (!(bVar.get() instanceof CBImpressionActivity)) {
            a(bVar, false);
        }
        this.x = (long) (System.nanoTime() / 1000000.0d);
    }

    protected void b(Activity activity) {
        c(new b(activity));
    }

    private void c(b bVar) {
        com.chartboost.sdk.b bVarA = a();
        if (d(bVar) && bVarA != null) {
            b(bVar, false);
            this.w = false;
            if (bVarA.a()) {
                bVarA.a(false);
                this.w = true;
            }
            if (this.i.a() != null) {
                bVarA.a(this.i.a());
            }
            if (bVar.get() instanceof CBImpressionActivity) {
                d();
            }
        }
        if (!(bVar.get() instanceof CBImpressionActivity)) {
            a(bVar, false);
        }
    }

    public boolean onBackPressed() {
        if (!this.q && !com.chartboost.sdk.Libraries.d.d()) {
            throw new IllegalStateException("It is illegal to call this method from any thread other than the UI thread. Please call it from the onBackPressed() method of your host activity.");
        }
        if (this.f368a == null) {
            throw new IllegalStateException("The Chartboost methods onCreate(), onStart(), onStop(), and onDestroy() must be called in the corresponding methods of your activity in order for Chartboost to function properly.");
        }
        if (!this.n) {
            return b();
        }
        if (!this.z) {
            return false;
        }
        this.z = false;
        b();
        return true;
    }

    protected boolean b() {
        if (this.i.a() != null && this.i.a().c == com.chartboost.sdk.impl.a.b.CBImpressionStateDisplayedByDefaultController) {
            a(new Runnable() { // from class: com.chartboost.sdk.Chartboost.2
                @Override // java.lang.Runnable
                public void run() {
                    Chartboost.this.i.d();
                }
            });
            return true;
        }
        final com.chartboost.sdk.b bVarA = a();
        if (bVarA != null && bVarA.a()) {
            a(new Runnable() { // from class: com.chartboost.sdk.Chartboost.3
                @Override // java.lang.Runnable
                public void run() {
                    bVarA.a(true);
                }
            });
            return true;
        }
        return false;
    }

    public void onDestroy(Activity activity) {
        this.c.postDelayed(this.A, 10000L);
        c(activity);
    }

    protected void c(Activity activity) {
        b(new b(activity), false);
    }

    class a implements Runnable {
        private int b;
        private int c;
        private int d;
        private int e;

        private a() {
            this.b = Chartboost.this.g == null ? -1 : Chartboost.this.g.hashCode();
            this.c = Chartboost.this.f368a == null ? -1 : Chartboost.this.f368a.hashCode();
            this.d = Chartboost.this.l == null ? -1 : Chartboost.this.l.hashCode();
            this.e = Chartboost.this.e != null ? Chartboost.this.e.hashCode() : -1;
        }

        /* synthetic */ a(Chartboost chartboost, a aVar) {
            this();
        }

        @Override // java.lang.Runnable
        public void run() {
            if (Chartboost.this.getContext() != null) {
                Chartboost.this.clearImageCache();
            }
            if (Chartboost.this.f368a != null && Chartboost.this.f368a.hashCode() == this.c) {
                Chartboost.this.f368a = null;
            }
            if (Chartboost.this.g != null && Chartboost.this.g.hashCode() == this.b) {
                Chartboost.this.g = null;
            }
            if (Chartboost.this.l != null && Chartboost.this.l.hashCode() == this.d) {
                Chartboost.this.l = null;
            }
            if (Chartboost.this.e != null && Chartboost.this.e.hashCode() == this.e) {
                Chartboost.this.e = null;
            }
        }
    }

    public void clearImageCache() {
        if (getContext() == null) {
            throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before calling clearImageCache().");
        }
        o.a().b();
    }

    public void clearCache() {
        this.i.e();
    }

    public void cacheInterstitial() {
        cacheInterstitial("Default");
    }

    public void cacheInterstitial(String str) {
        if (this.f368a == null) {
            throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before calling cacheInterstitial().");
        }
        this.i.a(str);
    }

    public void showInterstitial() {
        showInterstitial("Default");
    }

    public void showInterstitial(String str) {
        if (this.f368a == null) {
            throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before calling showInterstitial().");
        }
        this.i.b(str);
    }

    public boolean hasCachedInterstitial() {
        return hasCachedInterstitial("Default");
    }

    public boolean hasCachedMoreApps() {
        return this.i.c();
    }

    public boolean hasCachedInterstitial(String str) {
        return this.i.c(str);
    }

    public void cacheMoreApps() {
        if (this.f368a == null) {
            throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before calling cacheMoreApps().");
        }
        this.i.a(true);
    }

    public void showMoreApps() {
        if (this.f368a == null) {
            throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before calling showMoreApps().");
        }
        this.i.b();
    }

    public void showMoreAppsData(CBAPIResponseCallback cBAPIResponseCallback) {
        d.a(cBAPIResponseCallback);
    }

    private void cacheInterstitialDataBatch(String str, int i, CBAPIResponseCallback cBAPIResponseCallback) {
        d.a(str, i, cBAPIResponseCallback);
    }

    private void cacheInterstitialData(String str, CBAPIResponseCallback cBAPIResponseCallback) {
        d.a(str, cBAPIResponseCallback);
    }

    private void showInterstitialData(String str, CBAPIResponseCallback cBAPIResponseCallback) {
        d.b(str, cBAPIResponseCallback);
    }

    public int getTimeout() {
        return this.m;
    }

    public void setTimeout(int i) {
        this.m = i;
    }

    public void setFramework(String str) {
        this.s = str;
    }

    public String getFramework() {
        return this.s == null ? "native" : this.s;
    }

    public String getAppID() {
        return this.j;
    }

    public void setAppID(String str) {
        this.j = str;
    }

    public String getAppSignature() {
        return this.k;
    }

    public void setAppSignature(String str) {
        this.k = str;
    }

    public ChartboostDelegate getDelegate() {
        return this.l;
    }

    public void setDelegate(ChartboostDelegate chartboostDelegate) {
        this.l = chartboostDelegate;
    }

    public Handler getHandler() {
        return this.c;
    }

    public boolean getImpressionsUseActivities() {
        return this.n;
    }

    public void setImpressionsUseActivities(boolean z) {
        this.n = z;
    }

    public boolean getIgnoreErrors() {
        return this.q;
    }

    public void setIgnoreErrors(boolean z) {
        this.q = z;
    }

    public boolean getAnimationsOff() {
        return this.r;
    }

    public void setAnimationsOff(boolean z) {
        this.r = z;
    }

    public void setIdentityTrackingDisabledOnThisDevice(boolean z) {
        SharedPreferences.Editor editorEdit = com.chartboost.sdk.Libraries.d.a().edit();
        editorEdit.putBoolean("cbIdentityTrackingDisabled", z);
        editorEdit.commit();
    }

    public boolean isIdentityTrackingDisabledOnThisDevice() {
        return com.chartboost.sdk.Libraries.d.c();
    }

    public void setOrientation(CBOrientation cBOrientation) {
        this.p = cBOrientation != CBOrientation.UNSPECIFIED;
        this.o = cBOrientation;
    }

    public CBOrientation getOrientation() {
        if (getContext() == null) {
            throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before calling getOrientation().");
        }
        return (!this.p || this.o == CBOrientation.UNSPECIFIED) ? com.chartboost.sdk.Libraries.d.c(getContext()) : this.o;
    }

    public CBOrientation.Difference getForcedOrientationDifference() {
        if (!this.p) {
            return CBOrientation.Difference.ANGLE_0;
        }
        CBOrientation cBOrientationC = com.chartboost.sdk.Libraries.d.c(getContext());
        CBOrientation orientation = getOrientation();
        if (orientation == CBOrientation.UNSPECIFIED || orientation == cBOrientationC) {
            return CBOrientation.Difference.ANGLE_0;
        }
        if (orientation == cBOrientationC.rotate90()) {
            return CBOrientation.Difference.ANGLE_90;
        }
        if (orientation == cBOrientationC.rotate180()) {
            return CBOrientation.Difference.ANGLE_180;
        }
        return CBOrientation.Difference.ANGLE_270;
    }

    protected Activity c() {
        return this.n ? this.g : (Activity) this.f368a.get();
    }

    private boolean d(Activity activity) {
        if (this.n) {
            return this.g == activity;
        }
        if (this.f368a == null) {
            return activity == null;
        }
        return this.f368a.a(activity);
    }

    private boolean d(b bVar) {
        if (this.n) {
            if (bVar == null) {
                return this.g == null;
            }
            return bVar.a(this.g);
        }
        if (this.f368a == null) {
            return bVar == null;
        }
        return this.f368a.a(bVar);
    }

    protected void a(CBImpressionActivity cBImpressionActivity) {
        if (!this.t) {
            this.f = cBImpressionActivity.getApplicationContext();
            this.g = cBImpressionActivity;
            this.t = true;
        }
        this.c.removeCallbacks(this.A);
    }

    protected void d() {
        if (this.t) {
            this.g = null;
            this.t = false;
        }
    }

    protected void a(com.chartboost.sdk.b.a aVar) {
        if (getImpressionsUseActivities()) {
            com.chartboost.sdk.b bVarA = a();
            if (c() != null && this.t && bVarA != null) {
                bVarA.a(aVar);
                return;
            }
            if (e()) {
                Activity activity = (Activity) this.f368a.get();
                if (activity == null) {
                    if (aVar.b.f.d != null) {
                        aVar.b.f.d.a();
                        return;
                    }
                    return;
                } else {
                    this.h.add(aVar);
                    Intent intent = new Intent(activity, (Class<?>) CBImpressionActivity.class);
                    intent.putExtra(CBImpressionActivity.PARAM_FULLSCREEN, ((activity.getWindow().getAttributes().flags & 1024) != 0) && !((activity.getWindow().getAttributes().flags & 2048) != 0));
                    try {
                        activity.startActivity(intent);
                        this.z = true;
                        return;
                    } catch (ActivityNotFoundException e) {
                        throw new RuntimeException("Chartboost impression activity not declared in manifest. Please add the following inside your manifest's <application> tag: \n<activity android:name=\"com.chartboost.sdk.CBImpressionActivity\" android:theme=\"@android:style/Theme.Translucent.NoTitleBar\" android:excludeFromRecents=\"true\" />");
                    }
                }
            }
            return;
        }
        com.chartboost.sdk.b bVarA2 = a();
        if (bVarA2 != null) {
            bVarA2.a(aVar);
        }
    }

    protected void a(com.chartboost.sdk.impl.a aVar) {
        this.i.a(aVar);
    }

    protected Activity getHostActivity() {
        if (this.f368a != null) {
            return (Activity) this.f368a.get();
        }
        return null;
    }

    private void a(Runnable runnable) {
        if (!com.chartboost.sdk.Libraries.d.d()) {
            this.c.post(runnable);
        } else {
            runnable.run();
        }
    }
}
