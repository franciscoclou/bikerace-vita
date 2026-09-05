package com.heyzap.sdk.ads;

import android.content.res.Configuration;
import android.view.View;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class HeyzapInterstitialActivity extends a {
    private r f;

    @Override // com.heyzap.sdk.ads.a
    public /* bridge */ /* synthetic */ void a(String str, String str2) {
        super.a(str, str2);
    }

    @Override // com.heyzap.sdk.ads.a
    public /* bridge */ /* synthetic */ void c() {
        super.c();
    }

    @Override // com.heyzap.sdk.ads.a
    public /* bridge */ /* synthetic */ void d() {
        super.d();
    }

    @Override // com.heyzap.sdk.ads.a
    public /* bridge */ /* synthetic */ void e() {
        super.e();
    }

    @Override // com.heyzap.sdk.ads.a, android.app.Activity, android.content.ComponentCallbacks
    public /* bridge */ /* synthetic */ void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // com.heyzap.sdk.ads.a
    public Boolean a() {
        this.f = new r(this, new l(this));
        this.f.a((o) this.f762a);
        return true;
    }

    @Override // com.heyzap.sdk.ads.a
    public View b() {
        return this.f;
    }
}
