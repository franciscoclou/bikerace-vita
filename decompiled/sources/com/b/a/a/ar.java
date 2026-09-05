package com.b.a.a;

import android.content.Context;
import java.security.GeneralSecurityException;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import javax.crypto.Cipher;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class ar {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AtomicReference<aw> f263a;
    private av b;
    private boolean c;

    /* synthetic */ ar(byte b) {
        this();
    }

    public static ar a() {
        return as.f264a;
    }

    private ar() {
        this.f263a = new AtomicReference<>();
        this.c = false;
    }

    public final synchronized ar a(Context context, bu buVar, String str, String str2, String str3) {
        if (!this.c) {
            if (this.b == null) {
                String strA = ck.a(context, false);
                String packageName = context.getPackageName();
                String installerPackageName = context.getPackageManager().getInstallerPackageName(packageName);
                this.b = new av(new ay(strA, a(strA, packageName, context), ba.a(ba.i(context)), str2, str, bh.a(installerPackageName).a(), ba.g(context)), new bg(), new ax(), new am(), new an(str3, String.format(Locale.US, "https://settings.crashlytics.com/spi/v2/platforms/android/apps/%s/settings", packageName), buVar));
            }
            this.c = true;
        }
        return this;
    }

    public final aw b() {
        return this.f263a.get();
    }

    public final <T> T a(at<T> atVar, T t) {
        aw awVar = this.f263a.get();
        return awVar == null ? t : atVar.a(awVar);
    }

    public final synchronized boolean c() {
        aw awVarA;
        awVarA = this.b.a();
        this.f263a.set(awVarA);
        return awVarA != null;
    }

    public final synchronized boolean d() {
        aw awVarA;
        awVarA = this.b.a(au.SKIP_CACHE_LOOKUP);
        this.f263a.set(awVarA);
        if (awVarA == null) {
            cm.a().b().a("Crashlytics", "Failed to force reload of settings from Crashlytics.", (Throwable) null);
        }
        return awVarA != null;
    }

    private static String a(String str, String str2, Context context) {
        try {
            Cipher cipherB = ba.b(1, ba.a(str + str2.replaceAll("\\.", new StringBuffer("slc").reverse().toString())));
            JSONObject jSONObject = new JSONObject();
            bn bnVar = new bn(context);
            try {
                jSONObject.put("APPLICATION_INSTALLATION_UUID".toLowerCase(Locale.US), bnVar.b());
            } catch (Exception e) {
                cm.a().b().a("Crashlytics", "Could not write application id to JSON", e);
            }
            for (Map.Entry<bo, String> entry : bnVar.f().entrySet()) {
                try {
                    jSONObject.put(entry.getKey().name().toLowerCase(Locale.US), entry.getValue());
                } catch (Exception e2) {
                    cm.a().b().a("Crashlytics", "Could not write value to JSON: " + entry.getKey().name(), e2);
                }
            }
            try {
                jSONObject.put("os_version", bnVar.c());
            } catch (Exception e3) {
                cm.a().b().a("Crashlytics", "Could not write OS version to JSON", e3);
            }
            try {
                jSONObject.put("model", bnVar.d());
            } catch (Exception e4) {
                cm.a().b().a("Crashlytics", "Could not write model to JSON", e4);
            }
            if (jSONObject.length() <= 0) {
                return "";
            }
            try {
                return ba.a(cipherB.doFinal(jSONObject.toString().getBytes()));
            } catch (GeneralSecurityException e5) {
                cm.a().b().a("Crashlytics", "Could not encrypt IDs", e5);
                return "";
            }
        } catch (GeneralSecurityException e6) {
            cm.a().b().a("Crashlytics", "Could not create cipher to encrypt headers.", e6);
            return "";
        }
    }
}
