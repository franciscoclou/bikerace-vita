package com.google.ads.doubleclick;

import com.google.ads.mediation.admob.AdMobAdapterExtras;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class DfpExtras extends AdMobAdapterExtras {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f649a;

    @Override // com.google.ads.mediation.admob.AdMobAdapterExtras
    public /* bridge */ /* synthetic */ AdMobAdapterExtras setExtras(Map map) {
        return setExtras((Map<String, Object>) map);
    }

    public DfpExtras() {
    }

    public DfpExtras(DfpExtras dfpExtras) {
        super(dfpExtras);
        if (dfpExtras != null) {
            this.f649a = dfpExtras.f649a;
        }
    }

    public String getPublisherProvidedId() {
        return this.f649a;
    }

    public DfpExtras setPublisherProvidedId(String str) {
        this.f649a = str;
        return this;
    }

    @Override // com.google.ads.mediation.admob.AdMobAdapterExtras
    public DfpExtras setPlusOneOptOut(boolean z) {
        super.setPlusOneOptOut(z);
        return this;
    }

    @Override // com.google.ads.mediation.admob.AdMobAdapterExtras
    public DfpExtras setUseExactAdSize(boolean z) {
        super.setUseExactAdSize(z);
        return this;
    }

    @Override // com.google.ads.mediation.admob.AdMobAdapterExtras
    public DfpExtras setExtras(Map<String, Object> map) {
        super.setExtras(map);
        return this;
    }

    @Override // com.google.ads.mediation.admob.AdMobAdapterExtras
    public DfpExtras clearExtras() {
        super.clearExtras();
        return this;
    }

    @Override // com.google.ads.mediation.admob.AdMobAdapterExtras
    public DfpExtras addExtra(String str, Object obj) {
        super.addExtra(str, obj);
        return this;
    }
}
