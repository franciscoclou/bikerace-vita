package com.chartboost.sdk;

import android.app.Activity;
import android.os.Bundle;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class ChartboostActivity extends Activity implements ChartboostDelegate {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Chartboost f374a;

    protected abstract String getChartboostAppID();

    protected abstract String getChartboostAppSignature();

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f374a = Chartboost.sharedChartboost();
        this.f374a.onCreate(this, getChartboostAppID(), getChartboostAppSignature(), this);
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        this.f374a.onStart(this);
        this.f374a.startSession();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        this.f374a.onStop(this);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.f374a.onDestroy(this);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (!this.f374a.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public boolean shouldRequestInterstitial(String str) {
        return true;
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public boolean shouldDisplayInterstitial(String str) {
        return true;
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public void didCacheInterstitial(String str) {
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public void didFailToLoadInterstitial(String str) {
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public void didDismissInterstitial(String str) {
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public void didCloseInterstitial(String str) {
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public void didClickInterstitial(String str) {
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public void didShowInterstitial(String str) {
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public boolean shouldDisplayLoadingViewForMoreApps() {
        return true;
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public boolean shouldRequestMoreApps() {
        return true;
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public void didCacheMoreApps() {
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public boolean shouldDisplayMoreApps() {
        return true;
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public void didFailToLoadMoreApps() {
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public void didDismissMoreApps() {
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public void didCloseMoreApps() {
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public void didClickMoreApps() {
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public void didShowMoreApps() {
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public boolean shouldRequestInterstitialsInFirstSession() {
        return true;
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public void didFailToLoadUrl(String str) {
    }
}
