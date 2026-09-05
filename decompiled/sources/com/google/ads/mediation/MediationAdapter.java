package com.google.ads.mediation;

import com.google.ads.mediation.MediationServerParameters;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface MediationAdapter<ADDITIONAL_PARAMETERS, SERVER_PARAMETERS extends MediationServerParameters> {
    void destroy();

    Class<ADDITIONAL_PARAMETERS> getAdditionalParametersType();

    Class<SERVER_PARAMETERS> getServerParametersType();
}
