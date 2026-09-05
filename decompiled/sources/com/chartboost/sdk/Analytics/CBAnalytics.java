package com.chartboost.sdk.Analytics;

import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.d;
import com.chartboost.sdk.impl.j;
import com.chartboost.sdk.impl.k;
import java.math.BigDecimal;
import java.util.Date;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class CBAnalytics {
    public static final String TAG = "Chartboost Analytics";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static CBAnalytics f366a = null;
    private j b = new j(null, "CBAnalytics");

    public static synchronized CBAnalytics sharedAnalytics() {
        if (f366a == null) {
            f366a = new CBAnalytics();
        }
        return f366a;
    }

    private CBAnalytics() {
        this.b.a();
    }

    private String a(double d, int i, int i2) {
        return new StringBuilder(String.valueOf(new BigDecimal(d).setScale(i, i2).doubleValue())).toString();
    }

    public Boolean recordPaymentTransaction(String str, String str2, double d, String str3, int i, Object obj) {
        Chartboost chartboostSharedChartboost = Chartboost.sharedChartboost();
        if (chartboostSharedChartboost.getContext() == null) {
            throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before calling recordPaymentTransaction().");
        }
        if (chartboostSharedChartboost.getAppID() == null || chartboostSharedChartboost.getAppSignature() == null) {
            return false;
        }
        k kVar = new k("api/purchase");
        kVar.a(chartboostSharedChartboost.getContext());
        kVar.a("product_id", (Object) str);
        kVar.a("title", (Object) str2);
        kVar.a("price", (Object) a(d, 2, 4));
        kVar.a("currency", (Object) str3);
        kVar.a("quantity", (Object) new StringBuilder(String.valueOf(i)).toString());
        kVar.a("timestamp", Integer.valueOf(Long.valueOf(new Date().getTime() / 1000).intValue()));
        if (obj != null) {
            kVar.a("meta", d.a(obj));
        }
        kVar.b(chartboostSharedChartboost.getAppID(), chartboostSharedChartboost.getAppSignature());
        this.b.a(kVar);
        return true;
    }

    public Boolean trackEvent(String str) {
        return trackEvent(str, 1.0d, null);
    }

    public Boolean trackEvent(String str, double d) {
        return trackEvent(str, d, null);
    }

    public Boolean trackEvent(String str, double d, Object obj) {
        Chartboost chartboostSharedChartboost = Chartboost.sharedChartboost();
        if (chartboostSharedChartboost.getContext() == null) {
            throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before calling trackEvent().");
        }
        if (chartboostSharedChartboost.getAppID() == null || chartboostSharedChartboost.getAppSignature() == null) {
            return false;
        }
        k kVar = new k("api/event");
        kVar.a(chartboostSharedChartboost.getContext());
        kVar.a("key", (Object) str);
        kVar.a("value", (Object) new StringBuilder(String.valueOf(d)).toString());
        kVar.a("timestamp", (Object) new StringBuilder(String.valueOf(System.currentTimeMillis() / 1000.0d)).toString());
        if (obj != null) {
            kVar.a("meta", d.a(obj));
        }
        kVar.b(chartboostSharedChartboost.getAppID(), chartboostSharedChartboost.getAppSignature());
        kVar.a(true);
        this.b.a(kVar);
        return true;
    }
}
