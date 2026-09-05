package com.applovin.impl.adview;

import android.app.Activity;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b implements com.applovin.adview.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Object f228a = new Object();
    private static WeakReference b = new WeakReference(null);
    private static WeakReference c = new WeakReference(null);

    @Override // com.applovin.adview.c
    public com.applovin.adview.b a(com.applovin.a.k kVar, Activity activity) {
        e eVar;
        if (kVar == null) {
            kVar = com.applovin.a.k.b(activity);
        }
        synchronized (f228a) {
            eVar = (e) b.get();
            if (eVar != null && eVar.f() && c.get() == activity) {
                kVar.f().c("InterstitialAdDialogCreator", "An interstitial dialog is already showing, returning it");
            } else {
                eVar = new e(kVar, activity);
                b = new WeakReference(eVar);
                c = new WeakReference(activity);
            }
        }
        return eVar;
    }
}
