package com.google.ads;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AdSize {
    public static final int AUTO_HEIGHT = -2;
    public static final int FULL_WIDTH = -1;
    public static final int LANDSCAPE_AD_HEIGHT = 32;
    public static final int LARGE_AD_HEIGHT = 90;
    public static final int PORTRAIT_AD_HEIGHT = 50;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f614a;
    private final int b;
    private boolean c;
    private boolean d;
    private boolean e;
    private String f;
    public static final AdSize SMART_BANNER = new AdSize(-1, -2, "mb");
    public static final AdSize BANNER = new AdSize(320, 50, "mb");
    public static final AdSize IAB_MRECT = new AdSize(300, 250, "as");
    public static final AdSize IAB_BANNER = new AdSize(468, 60, "as");
    public static final AdSize IAB_LEADERBOARD = new AdSize(728, 90, "as");
    public static final AdSize IAB_WIDE_SKYSCRAPER = new AdSize(160, 600, "as");

    public AdSize(int i, int i2) {
        this(i, i2, null);
        if (a()) {
            this.e = false;
            this.f = "mb";
        } else {
            this.e = true;
        }
    }

    private AdSize(int i, int i2, String str) {
        this.f614a = i;
        this.b = i2;
        this.f = str;
        this.c = i == -1;
        this.d = i2 == -2;
        this.e = false;
    }

    public static AdSize createAdSize(AdSize adSize, Context context) {
        if (context == null || !adSize.a()) {
            return adSize.a() ? BANNER : adSize;
        }
        AdSize adSize2 = new AdSize(adSize.c ? a(context) : adSize.getWidth(), adSize.d ? b(context) : adSize.getHeight(), adSize.f);
        adSize2.d = adSize.d;
        adSize2.c = adSize.c;
        adSize2.e = adSize.e;
        return adSize2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AdSize)) {
            return false;
        }
        AdSize adSize = (AdSize) obj;
        return this.f614a == adSize.f614a && this.b == adSize.b;
    }

    public int hashCode() {
        return (Integer.valueOf(this.f614a).hashCode() << 16) | (Integer.valueOf(this.b).hashCode() & 65535);
    }

    public int getWidth() {
        if (this.f614a < 0) {
            throw new UnsupportedOperationException("Ad size was not set before getWidth() was called.");
        }
        return this.f614a;
    }

    public int getHeight() {
        if (this.b < 0) {
            throw new UnsupportedOperationException("Ad size was not set before getHeight() was called.");
        }
        return this.b;
    }

    private boolean a() {
        return this.f614a < 0 || this.b < 0;
    }

    public boolean isFullWidth() {
        return this.c;
    }

    public boolean isAutoHeight() {
        return this.d;
    }

    public boolean isCustomAdSize() {
        return this.e;
    }

    public String toString() {
        return getWidth() + "x" + getHeight() + (this.f == null ? "" : "_" + this.f);
    }

    public int getWidthInPixels(Context context) {
        return (int) TypedValue.applyDimension(1, this.f614a, context.getResources().getDisplayMetrics());
    }

    public int getHeightInPixels(Context context) {
        return (int) TypedValue.applyDimension(1, this.b, context.getResources().getDisplayMetrics());
    }

    public boolean isSizeAppropriate(int i, int i2) {
        return ((double) i) <= ((double) this.f614a) * 1.25d && ((double) i) >= ((double) this.f614a) * 0.8d && ((double) i2) <= ((double) this.b) * 1.25d && ((double) i2) >= ((double) this.b) * 0.8d;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0039  */
    public AdSize findBestSize(AdSize... adSizeArr) {
        double d;
        AdSize adSize;
        AdSize adSize2 = null;
        double d2 = 0.0d;
        if (adSizeArr != null) {
            int length = adSizeArr.length;
            int i = 0;
            while (i < length) {
                AdSize adSize3 = adSizeArr[i];
                if (isSizeAppropriate(adSize3.f614a, adSize3.b)) {
                    d = (((double) adSize3.f614a) * ((double) adSize3.b)) / (((double) this.f614a) * ((double) this.b));
                    if (d > 1.0d) {
                        d = 1.0d / d;
                    }
                    if (d > d2) {
                        adSize = adSize3;
                    } else {
                        d = d2;
                        adSize = adSize2;
                    }
                } else {
                    d = d2;
                    adSize = adSize2;
                }
                i++;
                adSize2 = adSize;
                d2 = d;
            }
        }
        return adSize2;
    }

    private static int a(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) (displayMetrics.widthPixels / displayMetrics.density);
    }

    private static int b(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int i = (int) (displayMetrics.heightPixels / displayMetrics.density);
        if (i <= 400) {
            return 32;
        }
        if (i <= 720) {
            return 50;
        }
        return 90;
    }
}
