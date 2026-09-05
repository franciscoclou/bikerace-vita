package com.applovin.impl.a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class au {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected static List f192a = new ArrayList();
    protected final d b;
    protected final Context c;
    protected final com.applovin.a.j d;

    au(d dVar) {
        if (dVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.b = dVar;
        this.d = dVar.f();
        this.c = dVar.h();
    }

    static boolean a(String str, Context context) {
        if (str == null) {
            throw new IllegalArgumentException("No permission name specified");
        }
        if (context == null) {
            throw new IllegalArgumentException("No context specified");
        }
        return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
    }

    List a() {
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        List<ResolveInfo> listQueryIntentActivities = this.c.getPackageManager().queryIntentActivities(intent, 0);
        if (listQueryIntentActivities == null || listQueryIntentActivities.isEmpty()) {
            return Collections.emptyList();
        }
        if (f192a.size() == listQueryIntentActivities.size() && f192a.size() > 0 && ((ax) f192a.get(0)).f195a.equals(listQueryIntentActivities.get(0).activityInfo.name)) {
            return new ArrayList(f192a);
        }
        ArrayList arrayList = new ArrayList(listQueryIntentActivities.size());
        HashSet hashSet = new HashSet();
        for (ResolveInfo resolveInfo : listQueryIntentActivities) {
            try {
                long jLastModified = new File(resolveInfo.activityInfo.applicationInfo.sourceDir).lastModified();
                ax axVar = new ax();
                axVar.c = ah.b(resolveInfo.activityInfo.packageName, this.b);
                axVar.d = jLastModified;
                axVar.f195a = resolveInfo.activityInfo.name;
                if (!hashSet.contains(axVar.c)) {
                    arrayList.add(axVar);
                    hashSet.add(axVar.c);
                }
            } catch (Throwable th) {
                this.d.a("DataCollector", "Unable to read information for app \"" + resolveInfo + "\"", th);
            }
        }
        try {
            Collections.sort(arrayList, new av(this));
        } catch (Throwable th2) {
            this.d.a("DataCollector", "Unable to sort applications", th2);
        }
        f192a = arrayList;
        return arrayList;
    }

    boolean a(String str) {
        return a(str, this.c);
    }

    ay b() {
        TelephonyManager telephonyManager;
        ay ayVar = new ay();
        ayVar.f196a = d();
        ayVar.b = Settings.Secure.getString(this.c.getContentResolver(), "android_id");
        ayVar.j = Locale.getDefault();
        ayVar.c = Build.MODEL;
        ayVar.d = Build.VERSION.RELEASE;
        ayVar.e = Build.MANUFACTURER;
        ayVar.g = Build.VERSION.SDK_INT;
        ayVar.f = Build.DEVICE;
        if (a("android.permission.READ_PHONE_STATE") && (telephonyManager = (TelephonyManager) this.c.getSystemService("phone")) != null) {
            ayVar.h = telephonyManager.getSimCountryIso().toUpperCase();
            String networkOperatorName = telephonyManager.getNetworkOperatorName();
            try {
                ayVar.i = URLEncoder.encode(networkOperatorName, XMLStreamWriterImpl.UTF_8);
            } catch (UnsupportedEncodingException e) {
                ayVar.i = networkOperatorName;
            }
        }
        return ayVar;
    }

    ax c() {
        ApplicationInfo applicationInfo = this.c.getApplicationInfo();
        long jLastModified = new File(applicationInfo.sourceDir).lastModified();
        PackageManager packageManager = this.c.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(this.c.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
        }
        ax axVar = new ax();
        axVar.c = applicationInfo.packageName;
        axVar.d = jLastModified;
        axVar.f195a = String.valueOf(packageManager.getApplicationLabel(applicationInfo));
        axVar.b = packageInfo != null ? packageInfo.versionName : "";
        return axVar;
    }

    String d() {
        TelephonyManager telephonyManager;
        return (!a("android.permission.READ_PHONE_STATE") || (telephonyManager = (TelephonyManager) this.c.getSystemService("phone")) == null) ? "" : telephonyManager.getDeviceId();
    }

    aw e() {
        Object objInvoke;
        try {
            Class<?> cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
            if (cls != null && (objInvoke = cls.getMethod("getAdvertisingIdInfo", Context.class).invoke(null, this.c)) != null) {
                Class<?> cls2 = objInvoke.getClass();
                Object objInvoke2 = cls2.getMethod("isLimitAdTrackingEnabled", null).invoke(objInvoke, null);
                Object objInvoke3 = cls2.getMethod("getId", null).invoke(objInvoke, null);
                aw awVar = new aw();
                String str = (String) objInvoke3;
                String str2 = str == null ? "" : str;
                awVar.f194a = ((Boolean) objInvoke2).booleanValue();
                awVar.b = str2;
                return awVar;
            }
        } catch (ClassNotFoundException e) {
            this.d.c("DataCollector", "Could not collect Google Advertising ID. Please integrate the Google Play Services SDK into your application. More info can be found online at http://developer.android.com/google/play-services/setup.html", e);
        } catch (Throwable th) {
            this.d.b("DataCollector", "Unable to collect advertising ID because of: ", th);
        }
        aw awVar2 = new aw();
        awVar2.b = "";
        awVar2.f194a = false;
        return awVar2;
    }
}
