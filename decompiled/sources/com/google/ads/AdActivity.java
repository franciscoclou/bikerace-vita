package com.google.ads;

import android.R;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import com.facebook.AppEventsConstants;
import com.google.ads.internal.AdVideoView;
import com.google.ads.internal.AdWebView;
import com.google.ads.util.AdUtil;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AdActivity extends Activity implements View.OnClickListener {
    public static final String BASE_URL_PARAM = "baseurl";
    public static final String COMPONENT_NAME_PARAM = "c";
    public static final String CUSTOM_CLOSE_PARAM = "custom_close";
    public static final String HTML_PARAM = "html";
    public static final String INTENT_ACTION_PARAM = "i";
    public static final String INTENT_EXTRAS_PARAM = "e";
    public static final String INTENT_FLAGS_PARAM = "f";
    public static final String ORIENTATION_PARAM = "o";
    public static final String PACKAGE_NAME_PARAM = "p";
    public static final String TYPE_PARAM = "m";
    public static final String URL_PARAM = "u";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final com.google.ads.internal.a f610a = com.google.ads.internal.a.f669a.b();
    private static final Object b = new Object();
    private static AdActivity c = null;
    private static com.google.ads.internal.d d = null;
    private static AdActivity e = null;
    private static AdActivity f = null;
    private static final StaticMethodWrapper g = new StaticMethodWrapper();
    private AdWebView h;
    private FrameLayout i;
    private int j;
    private boolean l;
    private long m;
    private RelativeLayout n;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private AdVideoView t;
    private ViewGroup k = null;
    private AdActivity o = null;

    public class StaticMethodWrapper {
        public boolean isShowing() {
            boolean z;
            synchronized (AdActivity.b) {
                z = AdActivity.e != null;
            }
            return z;
        }

        public boolean leftApplication() {
            boolean z;
            synchronized (AdActivity.b) {
                z = AdActivity.c != null;
            }
            return z;
        }

        public void launchAdActivity(com.google.ads.internal.d dVar, com.google.ads.internal.e eVar) {
            synchronized (AdActivity.b) {
                if (AdActivity.d == null) {
                    com.google.ads.internal.d unused = AdActivity.d = dVar;
                } else if (AdActivity.d != dVar) {
                    com.google.ads.util.b.b("Tried to launch a new AdActivity with a different AdManager.");
                    return;
                }
                Activity activityA = dVar.i().c.a();
                if (activityA == null) {
                    com.google.ads.util.b.e("activity was null while launching an AdActivity.");
                    return;
                }
                Intent intent = new Intent(activityA.getApplicationContext(), (Class<?>) AdActivity.class);
                intent.putExtra("com.google.ads.AdOpener", eVar.a());
                try {
                    com.google.ads.util.b.a("Launching AdActivity.");
                    activityA.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    com.google.ads.util.b.b("Activity not found.", e);
                }
            }
        }
    }

    protected View a(int i, boolean z) {
        this.j = (int) TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
        this.i = new FrameLayout(getApplicationContext());
        this.i.setMinimumWidth(this.j);
        this.i.setMinimumHeight(this.j);
        this.i.setOnClickListener(this);
        setCustomClose(z);
        return this.i;
    }

    private void a(String str) {
        com.google.ads.util.b.b(str);
        finish();
    }

    private void a(String str, Throwable th) {
        com.google.ads.util.b.b(str, th);
        finish();
    }

    public AdVideoView getAdVideoView() {
        return this.t;
    }

    public AdWebView getOpeningAdWebView() {
        AdWebView adWebView = null;
        if (this.o != null) {
            return this.o.h;
        }
        synchronized (b) {
            if (d == null) {
                com.google.ads.util.b.e("currentAdManager was null while trying to get the opening AdWebView.");
            } else {
                AdWebView adWebViewL = d.l();
                if (adWebViewL != this.h) {
                    adWebView = adWebViewL;
                }
            }
        }
        return adWebView;
    }

    public static boolean isShowing() {
        return g.isShowing();
    }

    public static boolean leftApplication() {
        return g.leftApplication();
    }

    public static void launchAdActivity(com.google.ads.internal.d dVar, com.google.ads.internal.e eVar) {
        g.launchAdActivity(dVar, eVar);
    }

    protected void a(HashMap<String, String> map, com.google.ads.internal.d dVar) {
        int i;
        if (map == null) {
            a("Could not get the paramMap in launchIntent()");
            return;
        }
        Intent intent = new Intent();
        String str = map.get(URL_PARAM);
        String str2 = map.get(TYPE_PARAM);
        String str3 = map.get(INTENT_ACTION_PARAM);
        String str4 = map.get(PACKAGE_NAME_PARAM);
        String str5 = map.get(COMPONENT_NAME_PARAM);
        String str6 = map.get(INTENT_FLAGS_PARAM);
        String str7 = map.get(INTENT_EXTRAS_PARAM);
        boolean z = !TextUtils.isEmpty(str);
        boolean z2 = !TextUtils.isEmpty(str2);
        if (z && z2) {
            intent.setDataAndType(Uri.parse(str), str2);
        } else if (z) {
            intent.setData(Uri.parse(str));
        } else if (z2) {
            intent.setType(str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            intent.setAction(str3);
        } else if (z) {
            intent.setAction("android.intent.action.VIEW");
        }
        if (!TextUtils.isEmpty(str4) && AdUtil.f712a >= 4) {
            com.google.ads.util.e.a(intent, str4);
        }
        if (!TextUtils.isEmpty(str5)) {
            String[] strArrSplit = str5.split("/");
            if (strArrSplit.length < 2) {
                com.google.ads.util.b.e("Warning: Could not parse component name from open GMSG: " + str5);
            }
            intent.setClassName(strArrSplit[0], strArrSplit[1]);
        }
        if (!TextUtils.isEmpty(str6)) {
            try {
                i = Integer.parseInt(str6);
            } catch (NumberFormatException e2) {
                com.google.ads.util.b.e("Warning: Could not parse flags from open GMSG: " + str6);
                i = 0;
            }
            intent.addFlags(i);
        }
        if (!TextUtils.isEmpty(str7)) {
            try {
                JSONObject jSONObject = new JSONObject(str7);
                JSONArray jSONArrayNames = jSONObject.names();
                for (int i2 = 0; i2 < jSONArrayNames.length(); i2++) {
                    String string = jSONArrayNames.getString(i2);
                    JSONObject jSONObject2 = jSONObject.getJSONObject(string);
                    int i3 = jSONObject2.getInt("t");
                    switch (i3) {
                        case 1:
                            intent.putExtra(string, jSONObject2.getBoolean("v"));
                            break;
                        case 2:
                            intent.putExtra(string, jSONObject2.getDouble("v"));
                            break;
                        case 3:
                            intent.putExtra(string, jSONObject2.getInt("v"));
                            break;
                        case 4:
                            intent.putExtra(string, jSONObject2.getLong("v"));
                            break;
                        case 5:
                            intent.putExtra(string, jSONObject2.getString("v"));
                            break;
                        default:
                            com.google.ads.util.b.e("Warning: Unknown type in extras from open GMSG: " + string + " (type: " + i3 + ")");
                            break;
                    }
                }
            } catch (JSONException e3) {
                com.google.ads.util.b.e("Warning: Could not parse extras from open GMSG: " + str7);
            }
        }
        if (intent.filterEquals(new Intent())) {
            a("Tried to launch empty intent.");
            return;
        }
        try {
            com.google.ads.util.b.a("Launching an intent from AdActivity: " + intent);
            startActivity(intent);
            a(dVar);
        } catch (ActivityNotFoundException e4) {
            a(e4.getMessage(), e4);
        }
    }

    protected void a(com.google.ads.internal.d dVar) {
        this.h = null;
        this.m = SystemClock.elapsedRealtime();
        this.p = true;
        synchronized (b) {
            if (c == null) {
                c = this;
                dVar.w();
            }
        }
    }

    protected AdVideoView a(Activity activity) {
        return new AdVideoView(activity, this.h);
    }

    public void moveAdVideoView(int i, int i2, int i3, int i4) {
        if (this.t != null) {
            this.t.setLayoutParams(a(i, i2, i3, i4));
            this.t.requestLayout();
        }
    }

    public void newAdVideoView(int i, int i2, int i3, int i4) {
        if (this.t == null) {
            this.t = a(this);
            this.n.addView(this.t, 0, a(i, i2, i3, i4));
            synchronized (b) {
                if (d == null) {
                    com.google.ads.util.b.e("currentAdManager was null while trying to get the opening AdWebView.");
                } else {
                    d.m().b(false);
                }
            }
        }
    }

    private RelativeLayout.LayoutParams a(int i, int i2, int i3, int i4) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i3, i4);
        layoutParams.setMargins(i, i2, 0, 0);
        layoutParams.addRule(10);
        layoutParams.addRule(9);
        return layoutParams;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        finish();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        boolean zJ;
        int iO;
        boolean z = false;
        super.onCreate(bundle);
        this.l = false;
        synchronized (b) {
            if (d != null) {
                com.google.ads.internal.d dVar = d;
                if (e == null) {
                    e = this;
                    dVar.v();
                }
                if (this.o == null && f != null) {
                    this.o = f;
                }
                f = this;
                if ((dVar.i().a() && e == this) || (dVar.i().b() && this.o == e)) {
                    dVar.x();
                }
                boolean zR = dVar.r();
                m.a aVarA = dVar.i().d.a().b.a();
                this.s = AdUtil.f712a >= aVarA.b.a().intValue();
                this.r = AdUtil.f712a >= aVarA.d.a().intValue();
                this.n = null;
                this.p = false;
                this.q = true;
                this.t = null;
                Bundle bundleExtra = getIntent().getBundleExtra("com.google.ads.AdOpener");
                if (bundleExtra == null) {
                    a("Could not get the Bundle used to create AdActivity.");
                    return;
                }
                com.google.ads.internal.e eVar = new com.google.ads.internal.e(bundleExtra);
                String strB = eVar.b();
                HashMap<String, String> mapC = eVar.c();
                if (strB.equals("intent")) {
                    a(mapC, dVar);
                    return;
                }
                this.n = new RelativeLayout(getApplicationContext());
                if (strB.equals("webapp")) {
                    this.h = new AdWebView(dVar.i(), null);
                    com.google.ads.internal.i iVarA = com.google.ads.internal.i.a(dVar, com.google.ads.internal.a.d, true, !zR);
                    iVarA.d(true);
                    if (zR) {
                        iVarA.a(true);
                    }
                    this.h.setWebViewClient(iVarA);
                    String str = mapC.get(URL_PARAM);
                    String str2 = mapC.get(BASE_URL_PARAM);
                    String str3 = mapC.get(HTML_PARAM);
                    if (str != null) {
                        this.h.loadUrl(str);
                    } else if (str3 != null) {
                        this.h.loadDataWithBaseURL(str2, str3, "text/html", "utf-8", null);
                    } else {
                        a("Could not get the URL or HTML parameter to show a web app.");
                        return;
                    }
                    String str4 = mapC.get(ORIENTATION_PARAM);
                    if (PACKAGE_NAME_PARAM.equals(str4)) {
                        iO = AdUtil.b();
                    } else if ("l".equals(str4)) {
                        iO = AdUtil.a();
                    } else if (this == e) {
                        iO = dVar.o();
                    } else {
                        iO = -1;
                    }
                    a(this.h, false, iO, zR, mapC != null && AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(mapC.get(CUSTOM_CLOSE_PARAM)));
                    return;
                }
                if (strB.equals("interstitial") || strB.equals("expand")) {
                    this.h = dVar.l();
                    int iO2 = dVar.o();
                    if (strB.equals("expand")) {
                        this.h.setIsExpandedMraid(true);
                        this.q = false;
                        if (mapC != null && AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(mapC.get(CUSTOM_CLOSE_PARAM))) {
                            z = true;
                        }
                        if (!this.r || this.s) {
                            zJ = z;
                        } else {
                            com.google.ads.util.b.a("Re-enabling hardware acceleration on expanding MRAID WebView.");
                            this.h.h();
                            zJ = z;
                        }
                    } else {
                        zJ = this.h.j();
                    }
                    a(this.h, true, iO2, zR, zJ);
                    return;
                }
                a("Unknown AdOpener, <action: " + strB + ">");
                return;
            }
            a("Could not get currentAdManager.");
        }
    }

    protected void a(AdWebView adWebView, boolean z, int i, boolean z2, boolean z3) {
        requestWindowFeature(1);
        Window window = getWindow();
        window.setFlags(1024, 1024);
        if (AdUtil.f712a >= 11) {
            if (this.r) {
                com.google.ads.util.b.a("Enabling hardware acceleration on the AdActivity window.");
                com.google.ads.util.g.a(window);
            } else {
                com.google.ads.util.b.a("Disabling hardware acceleration on the AdActivity WebView.");
                adWebView.g();
            }
        }
        ViewParent parent = adWebView.getParent();
        if (parent != null) {
            if (z2) {
                if (parent instanceof ViewGroup) {
                    this.k = (ViewGroup) parent;
                    this.k.removeView(adWebView);
                } else {
                    a("MRAID banner was not a child of a ViewGroup.");
                    return;
                }
            } else {
                a("Interstitial created with an AdWebView that has a parent.");
                return;
            }
        }
        if (adWebView.i() != null) {
            a("Interstitial created with an AdWebView that is already in use by another AdActivity.");
            return;
        }
        setRequestedOrientation(i);
        adWebView.setAdActivity(this);
        View viewA = a(z2 ? 50 : 32, z3);
        this.n.addView(adWebView, -1, -1);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        if (z2) {
            layoutParams.addRule(10);
            layoutParams.addRule(11);
        } else {
            layoutParams.addRule(10);
            layoutParams.addRule(9);
        }
        this.n.addView(viewA, layoutParams);
        this.n.setKeepScreenOn(true);
        setContentView(this.n);
        this.n.getRootView().setBackgroundColor(-16777216);
        if (z) {
            f610a.a(adWebView);
        }
    }

    @Override // android.app.Activity
    public void onDestroy() {
        if (this.n != null) {
            this.n.removeAllViews();
        }
        if (isFinishing()) {
            e();
            if (this.q && this.h != null) {
                this.h.stopLoading();
                this.h.destroy();
                this.h = null;
            }
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    public void onPause() {
        if (isFinishing()) {
            e();
        }
        super.onPause();
    }

    private void e() {
        if (!this.l) {
            if (this.h != null) {
                f610a.b(this.h);
                this.h.setAdActivity(null);
                this.h.setIsExpandedMraid(false);
                if (!this.q && this.n != null && this.k != null) {
                    if (this.r && !this.s) {
                        com.google.ads.util.b.a("Disabling hardware acceleration on collapsing MRAID WebView.");
                        this.h.g();
                    } else if (!this.r && this.s) {
                        com.google.ads.util.b.a("Re-enabling hardware acceleration on collapsing MRAID WebView.");
                        this.h.h();
                    }
                    this.n.removeView(this.h);
                    this.k.addView(this.h);
                }
            }
            if (this.t != null) {
                this.t.e();
                this.t = null;
            }
            if (this == c) {
                c = null;
            }
            f = this.o;
            synchronized (b) {
                if (d != null && this.q && this.h != null) {
                    if (this.h == d.l()) {
                        d.a();
                    }
                    this.h.stopLoading();
                }
                if (this == e) {
                    e = null;
                    if (d != null) {
                        d.u();
                        d = null;
                    } else {
                        com.google.ads.util.b.e("currentAdManager is null while trying to destroy AdActivity.");
                    }
                }
            }
            this.l = true;
            com.google.ads.util.b.a("AdActivity is closing.");
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        if (this.p && z && SystemClock.elapsedRealtime() - this.m > 250) {
            com.google.ads.util.b.d("Launcher AdActivity got focus and is closing.");
            finish();
        }
        super.onWindowFocusChanged(z);
    }

    public void setCustomClose(boolean z) {
        if (this.i != null) {
            this.i.removeAllViews();
            if (!z) {
                ImageButton imageButton = new ImageButton(this);
                imageButton.setImageResource(R.drawable.btn_dialog);
                imageButton.setBackgroundColor(0);
                imageButton.setOnClickListener(this);
                imageButton.setPadding(0, 0, 0, 0);
                this.i.addView(imageButton, new FrameLayout.LayoutParams(this.j, this.j, 17));
            }
        }
    }
}
