package com.google.ads;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface Ad {
    boolean isReady();

    void loadAd(AdRequest adRequest);

    void setAdListener(AdListener adListener);

    void stopLoading();
}
