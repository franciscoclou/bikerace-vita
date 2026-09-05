package com.google.ads;

import android.app.Activity;
import com.facebook.AppEventsConstants;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class bi implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final h f644a;
    private final String b;
    private final AdRequest c;
    private final HashMap<String, String> d;
    private final boolean e;
    private final WeakReference<Activity> f;

    private static boolean a(Map<String, String> map) {
        String strRemove = map.remove("gwhirl_share_location");
        if (AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(strRemove)) {
            return true;
        }
        if (strRemove != null && !AppEventsConstants.EVENT_PARAM_VALUE_NO.equals(strRemove)) {
            com.google.ads.util.b.b("Received an illegal value, '" + strRemove + "', for the special share location parameter from mediation server (expected '0' or '1'). Will not share the location.");
        }
        return false;
    }

    public bi(h hVar, Activity activity, String str, AdRequest adRequest, HashMap<String, String> map) {
        this.f644a = hVar;
        this.b = str;
        this.f = new WeakReference<>(activity);
        this.c = adRequest;
        this.d = new HashMap<>(map);
        this.e = a(this.d);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            com.google.ads.util.b.a("Trying to instantiate: " + this.b);
            a((MediationAdapter) g.a(this.b, MediationAdapter.class));
        } catch (ClassNotFoundException e) {
            a("Cannot find adapter class '" + this.b + "'. Did you link the ad network's mediation adapter? Skipping ad network.", e, g.a.NOT_FOUND);
        } catch (Throwable th) {
            a("Error while creating adapter and loading ad from ad network. Skipping ad network.", th, g.a.EXCEPTION);
        }
    }

    private void a(String str, Throwable th, g.a aVar) {
        com.google.ads.util.b.b(str, th);
        this.f644a.a(false, aVar);
    }

    private <T extends NetworkExtras, U extends MediationServerParameters> void a(MediationAdapter<T, U> mediationAdapter) throws bj, MediationServerParameters.MappingException {
        MediationServerParameters mediationServerParameters;
        Activity activity = this.f.get();
        if (activity == null) {
            throw new bj("Activity became null while trying to instantiate adapter.");
        }
        this.f644a.a((MediationAdapter<?, ?>) mediationAdapter);
        Class<SERVER_PARAMETERS> serverParametersType = mediationAdapter.getServerParametersType();
        if (serverParametersType != 0) {
            MediationServerParameters mediationServerParameters2 = (MediationServerParameters) serverParametersType.newInstance();
            mediationServerParameters2.load(this.d);
            mediationServerParameters = mediationServerParameters2;
        } else {
            mediationServerParameters = null;
        }
        Class<T> additionalParametersType = mediationAdapter.getAdditionalParametersType();
        NetworkExtras networkExtras = additionalParametersType != null ? (NetworkExtras) this.c.getNetworkExtras(additionalParametersType) : null;
        MediationAdRequest mediationAdRequest = new MediationAdRequest(this.c, activity, this.e);
        if (this.f644a.f661a.a()) {
            if (!(mediationAdapter instanceof MediationInterstitialAdapter)) {
                throw new bj("Adapter " + this.b + " doesn't support the MediationInterstitialAdapter interface.");
            }
            ((MediationInterstitialAdapter) mediationAdapter).requestInterstitialAd(new bl(this.f644a), activity, mediationServerParameters, mediationAdRequest, networkExtras);
        } else {
            if (!(mediationAdapter instanceof MediationBannerAdapter)) {
                throw new bj("Adapter " + this.b + " doesn't support the MediationBannerAdapter interface");
            }
            ((MediationBannerAdapter) mediationAdapter).requestBannerAd(new bk(this.f644a), activity, mediationServerParameters, this.f644a.f661a.c(), mediationAdRequest, networkExtras);
        }
        this.f644a.k();
    }
}
