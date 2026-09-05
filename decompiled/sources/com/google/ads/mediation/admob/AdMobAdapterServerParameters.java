package com.google.ads.mediation.admob;

import com.google.ads.mediation.MediationServerParameters;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class AdMobAdapterServerParameters extends MediationServerParameters {

    @MediationServerParameters.Parameter(name = "pubid")
    public String adUnitId;

    @MediationServerParameters.Parameter(name = "mad_hac", required = false)
    public String allowHouseAds = null;
}
