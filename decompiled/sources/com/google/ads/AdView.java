package com.google.ads;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.ads.internal.AdWebView;
import com.google.ads.util.AdUtil;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AdView extends RelativeLayout implements Ad {
    private static final com.google.ads.internal.a b = com.google.ads.internal.a.f669a.b();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected com.google.ads.internal.d f615a;

    public AdView(Activity activity, AdSize adSize, String str) {
        super(activity.getApplicationContext());
        try {
            a(activity, adSize, (AttributeSet) null);
            b(activity, adSize, null);
            a(activity, adSize, str);
        } catch (com.google.ads.internal.b e) {
            a(activity, e.c("Could not initialize AdView"), adSize, (AttributeSet) null);
            e.a("Could not initialize AdView");
        }
    }

    protected AdView(Activity activity, AdSize[] adSizeArr, String str) {
        this(activity, new AdSize(0, 0), str);
        a(adSizeArr);
    }

    public AdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public AdView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    void a(Context context, String str, int i, AdSize adSize, AttributeSet attributeSet) {
        if (adSize == null) {
            adSize = AdSize.BANNER;
        }
        AdSize adSizeCreateAdSize = AdSize.createAdSize(adSize, context.getApplicationContext());
        if (getChildCount() == 0) {
            TextView textView = attributeSet == null ? new TextView(context) : new TextView(context, attributeSet);
            textView.setGravity(17);
            textView.setText(str);
            textView.setTextColor(i);
            textView.setBackgroundColor(-16777216);
            LinearLayout linearLayout = attributeSet == null ? new LinearLayout(context) : new LinearLayout(context, attributeSet);
            linearLayout.setGravity(17);
            LinearLayout linearLayout2 = attributeSet == null ? new LinearLayout(context) : new LinearLayout(context, attributeSet);
            linearLayout2.setGravity(17);
            linearLayout2.setBackgroundColor(i);
            int iA = AdUtil.a(context, adSizeCreateAdSize.getWidth());
            int iA2 = AdUtil.a(context, adSizeCreateAdSize.getHeight());
            linearLayout.addView(textView, iA - 2, iA2 - 2);
            linearLayout2.addView(linearLayout);
            addView(linearLayout2, iA, iA2);
        }
    }

    private boolean a(Context context, AdSize adSize, AttributeSet attributeSet) {
        if (AdUtil.c(context)) {
            return true;
        }
        a(context, "You must have AdActivity declared in AndroidManifest.xml with configChanges.", adSize, attributeSet);
        return false;
    }

    private boolean b(Context context, AdSize adSize, AttributeSet attributeSet) {
        if (AdUtil.b(context)) {
            return true;
        }
        a(context, "You must have INTERNET and ACCESS_NETWORK_STATE permissions in AndroidManifest.xml.", adSize, attributeSet);
        return false;
    }

    public void destroy() {
        this.f615a.b();
    }

    private void a(Context context, String str, AdSize adSize, AttributeSet attributeSet) {
        com.google.ads.util.b.b(str);
        a(context, str, -65536, adSize, attributeSet);
    }

    AdSize[] a(String str) {
        AdSize adSize;
        String[] strArrSplit = str.split(",");
        AdSize[] adSizeArr = new AdSize[strArrSplit.length];
        for (int i = 0; i < strArrSplit.length; i++) {
            String strTrim = strArrSplit[i].trim();
            if (strTrim.matches("^(\\d+|FULL_WIDTH)\\s*[xX]\\s*(\\d+|AUTO_HEIGHT)$")) {
                String[] strArrSplit2 = strTrim.split("[xX]");
                strArrSplit2[0] = strArrSplit2[0].trim();
                strArrSplit2[1] = strArrSplit2[1].trim();
                try {
                    adSize = new AdSize("FULL_WIDTH".equals(strArrSplit2[0]) ? -1 : Integer.parseInt(strArrSplit2[0]), "AUTO_HEIGHT".equals(strArrSplit2[1]) ? -2 : Integer.parseInt(strArrSplit2[1]));
                } catch (NumberFormatException e) {
                    return null;
                }
            } else if ("BANNER".equals(strTrim)) {
                adSize = AdSize.BANNER;
            } else if ("SMART_BANNER".equals(strTrim)) {
                adSize = AdSize.SMART_BANNER;
            } else if ("IAB_MRECT".equals(strTrim)) {
                adSize = AdSize.IAB_MRECT;
            } else if ("IAB_BANNER".equals(strTrim)) {
                adSize = AdSize.IAB_BANNER;
            } else if ("IAB_LEADERBOARD".equals(strTrim)) {
                adSize = AdSize.IAB_LEADERBOARD;
            } else {
                adSize = "IAB_WIDE_SKYSCRAPER".equals(strTrim) ? AdSize.IAB_WIDE_SKYSCRAPER : null;
            }
            if (adSize == null) {
                return null;
            }
            adSizeArr[i] = adSize;
        }
        return adSizeArr;
    }

    private void a(Context context, AttributeSet attributeSet) {
        AdSize[] adSizeArr;
        com.google.ads.internal.b bVar;
        if (attributeSet != null) {
            try {
                String strB = b("adSize", context, attributeSet, true);
                AdSize[] adSizeArrA = a(strB);
                if (adSizeArrA != null) {
                    try {
                        if (adSizeArrA.length != 0) {
                            if (!a("adUnitId", attributeSet)) {
                                throw new com.google.ads.internal.b("Required XML attribute \"adUnitId\" missing", true);
                            }
                            if (isInEditMode()) {
                                a(context, "Ads by Google", -1, adSizeArrA[0], attributeSet);
                                return;
                            }
                            String strB2 = b("adUnitId", context, attributeSet, true);
                            boolean zA = a("loadAdOnCreate", context, attributeSet, false);
                            if (context instanceof Activity) {
                                Activity activity = (Activity) context;
                                a(activity, adSizeArrA[0], attributeSet);
                                b(activity, adSizeArrA[0], attributeSet);
                                if (adSizeArrA.length == 1) {
                                    a(activity, adSizeArrA[0], strB2);
                                } else {
                                    a(activity, new AdSize(0, 0), strB2);
                                    a(adSizeArrA);
                                }
                                if (zA) {
                                    Set<String> setC = c("testDevices", context, attributeSet, false);
                                    if (setC.contains("TEST_EMULATOR")) {
                                        setC.remove("TEST_EMULATOR");
                                        setC.add(AdRequest.TEST_EMULATOR);
                                    }
                                    loadAd(new AdRequest().setTestDevices(setC).setKeywords(c("keywords", context, attributeSet, false)));
                                    return;
                                }
                                return;
                            }
                            throw new com.google.ads.internal.b("AdView was initialized with a Context that wasn't an Activity.", true);
                        }
                    } catch (com.google.ads.internal.b e) {
                        bVar = e;
                        adSizeArr = adSizeArrA;
                        a(context, bVar.c("Could not initialize AdView"), (adSizeArr == null || adSizeArr.length <= 0) ? AdSize.BANNER : adSizeArr[0], attributeSet);
                        bVar.a("Could not initialize AdView");
                        if (!isInEditMode()) {
                            bVar.b("Could not initialize AdView");
                            return;
                        }
                        return;
                    }
                }
                throw new com.google.ads.internal.b("Attribute \"adSize\" invalid: " + strB, true);
            } catch (com.google.ads.internal.b e2) {
                adSizeArr = null;
                bVar = e2;
            }
        }
    }

    private boolean a(String str, Context context, AttributeSet attributeSet, boolean z) throws com.google.ads.internal.b {
        String attributeValue = attributeSet.getAttributeValue("http://schemas.android.com/apk/lib/com.google.ads", str);
        boolean attributeBooleanValue = attributeSet.getAttributeBooleanValue("http://schemas.android.com/apk/lib/com.google.ads", str, z);
        if (attributeValue != null) {
            String packageName = context.getPackageName();
            if (attributeValue.matches("^@([^:]+)\\:(.*)$")) {
                packageName = attributeValue.replaceFirst("^@([^:]+)\\:(.*)$", "$1");
                attributeValue = attributeValue.replaceFirst("^@([^:]+)\\:(.*)$", "@$2");
            }
            if (attributeValue.startsWith("@bool/")) {
                String strSubstring = attributeValue.substring("@bool/".length());
                TypedValue typedValue = new TypedValue();
                try {
                    getResources().getValue(packageName + ":bool/" + strSubstring, typedValue, true);
                    if (typedValue.type == 18) {
                        return typedValue.data != 0;
                    }
                    throw new com.google.ads.internal.b("Resource " + str + " was not a boolean: " + typedValue, true);
                } catch (Resources.NotFoundException e) {
                    throw new com.google.ads.internal.b("Could not find resource for " + str + ": " + attributeValue, true, e);
                }
            }
        }
        return attributeBooleanValue;
    }

    private String b(String str, Context context, AttributeSet attributeSet, boolean z) throws com.google.ads.internal.b {
        String attributeValue = attributeSet.getAttributeValue("http://schemas.android.com/apk/lib/com.google.ads", str);
        if (attributeValue != null) {
            String packageName = context.getPackageName();
            if (attributeValue.matches("^@([^:]+)\\:(.*)$")) {
                packageName = attributeValue.replaceFirst("^@([^:]+)\\:(.*)$", "$1");
                attributeValue = attributeValue.replaceFirst("^@([^:]+)\\:(.*)$", "@$2");
            }
            if (attributeValue.startsWith("@string/")) {
                String strSubstring = attributeValue.substring("@string/".length());
                TypedValue typedValue = new TypedValue();
                try {
                    getResources().getValue(packageName + ":string/" + strSubstring, typedValue, true);
                    if (typedValue.string != null) {
                        attributeValue = typedValue.string.toString();
                    } else {
                        throw new com.google.ads.internal.b("Resource " + str + " was not a string: " + typedValue, true);
                    }
                } catch (Resources.NotFoundException e) {
                    throw new com.google.ads.internal.b("Could not find resource for " + str + ": " + attributeValue, true, e);
                }
            }
        }
        if (z && attributeValue == null) {
            throw new com.google.ads.internal.b("Required XML attribute \"" + str + "\" missing", true);
        }
        return attributeValue;
    }

    private Set<String> c(String str, Context context, AttributeSet attributeSet, boolean z) throws com.google.ads.internal.b {
        String strB = b(str, context, attributeSet, z);
        HashSet hashSet = new HashSet();
        if (strB != null) {
            String[] strArrSplit = strB.split(",");
            for (String str2 : strArrSplit) {
                String strTrim = str2.trim();
                if (strTrim.length() != 0) {
                    hashSet.add(strTrim);
                }
            }
        }
        return hashSet;
    }

    private boolean a(String str, AttributeSet attributeSet) {
        return attributeSet.getAttributeValue("http://schemas.android.com/apk/lib/com.google.ads", str) != null;
    }

    private void a(Activity activity, AdSize adSize, String str) {
        FrameLayout frameLayout = new FrameLayout(activity);
        frameLayout.setFocusable(false);
        this.f615a = new com.google.ads.internal.d(this, activity, adSize, str, frameLayout, false);
        setGravity(17);
        try {
            ViewGroup viewGroupA = com.google.ads.internal.k.a(activity, this.f615a);
            if (viewGroupA != null) {
                viewGroupA.addView(frameLayout, -2, -2);
                addView(viewGroupA, -2, -2);
            } else {
                addView(frameLayout, -2, -2);
            }
        } catch (VerifyError e) {
            com.google.ads.util.b.a("Gestures disabled: Not supported on this version of Android.", e);
            addView(frameLayout, -2, -2);
        }
    }

    @Override // com.google.ads.Ad
    public boolean isReady() {
        if (this.f615a == null) {
            return false;
        }
        return this.f615a.s();
    }

    public boolean isRefreshing() {
        if (this.f615a == null) {
            return false;
        }
        return this.f615a.t();
    }

    @Override // com.google.ads.Ad
    public void loadAd(AdRequest adRequest) {
        if (this.f615a != null) {
            if (isRefreshing()) {
                this.f615a.f();
            }
            this.f615a.a(adRequest);
        }
    }

    @Override // com.google.ads.Ad
    public void setAdListener(AdListener adListener) {
        this.f615a.i().o.a(adListener);
    }

    protected void setAppEventListener(AppEventListener appEventListener) {
        this.f615a.i().p.a(appEventListener);
    }

    protected void setSwipeableEventListener(SwipeableAdListener swipeableAdListener) {
        this.f615a.i().q.a(swipeableAdListener);
    }

    protected void setSupportedAdSizes(AdSize... adSizeArr) {
        if (this.f615a.i().n.a() == null) {
            com.google.ads.util.b.e("Warning: Tried to set supported ad sizes on a single-size AdView. AdSizes ignored. To create a multi-sized AdView, use an AdView constructor that takes in an AdSize[] array.");
        } else {
            a(adSizeArr);
        }
    }

    private void a(AdSize... adSizeArr) {
        AdSize[] adSizeArr2 = new AdSize[adSizeArr.length];
        for (int i = 0; i < adSizeArr.length; i++) {
            adSizeArr2[i] = AdSize.createAdSize(adSizeArr[i], getContext());
        }
        this.f615a.i().n.a(adSizeArr2);
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        AdWebView adWebViewL;
        if (!isInEditMode() && (adWebViewL = this.f615a.l()) != null) {
            adWebViewL.setVisibility(0);
        }
        super.onMeasure(i, i2);
    }

    @Override // com.google.ads.Ad
    public void stopLoading() {
        if (this.f615a != null) {
            this.f615a.C();
        }
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (!isInEditMode() && this.f615a.i().g.a().b() && i != 0 && this.f615a.i().l.a() != null && this.f615a.i().e.a() != null) {
            if (AdActivity.isShowing() && !AdActivity.leftApplication()) {
                b.a(this.f615a.i().e.a(), "onopeninapp", null);
            } else {
                b.a(this.f615a.i().e.a(), "onleaveapp", null);
            }
        }
    }
}
