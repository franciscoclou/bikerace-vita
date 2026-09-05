package com.google.ads;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface AdListener {
    void onDismissScreen(Ad ad);

    void onFailedToReceiveAd(Ad ad, AdRequest.ErrorCode errorCode);

    void onLeaveApplication(Ad ad);

    void onPresentScreen(Ad ad);

    void onReceiveAd(Ad ad);
}
