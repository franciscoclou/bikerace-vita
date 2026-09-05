package com.b.a;

import android.util.Log;
import com.b.a.a.cm;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class al {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f321a;
    private boolean b;

    public al(String str, boolean z) {
        this.f321a = str;
        this.b = z;
    }

    public final void a(String str, String str2) {
        if (com.b.a.a.ba.e(this.f321a) && this.b) {
            Log.e("Crashlytics", ".");
            Log.e("Crashlytics", ".     |  | ");
            Log.e("Crashlytics", ".     |  |");
            Log.e("Crashlytics", ".     |  |");
            Log.e("Crashlytics", ".   \\ |  | /");
            Log.e("Crashlytics", ".    \\    /");
            Log.e("Crashlytics", ".     \\  /");
            Log.e("Crashlytics", ".      \\/");
            Log.e("Crashlytics", ".");
            Log.e("Crashlytics", "This app relies on Crashlytics. Configure your build environment here: ");
            Log.e("Crashlytics", String.format("https://crashlytics.com/register/%s/android/%s", str, str2));
            Log.e("Crashlytics", ".");
            Log.e("Crashlytics", ".      /\\");
            Log.e("Crashlytics", ".     /  \\");
            Log.e("Crashlytics", ".    /    \\");
            Log.e("Crashlytics", ".   / |  | \\");
            Log.e("Crashlytics", ".     |  |");
            Log.e("Crashlytics", ".     |  |");
            Log.e("Crashlytics", ".     |  |");
            Log.e("Crashlytics", ".");
            throw new f(str, str2);
        }
        if (!this.b) {
            cm.a().b().a("Crashlytics", "Configured not to require a build ID.");
        }
    }
}
