package com.chartboost.sdk.impl;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.chartboost.sdk.Chartboost;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class m {
    public static boolean a() {
        NetworkInfo activeNetworkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) Chartboost.sharedChartboost().getContext().getSystemService("connectivity");
            return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnectedOrConnecting()) ? false : true;
        } catch (Exception e) {
            return true;
        }
    }

    public static int b() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) Chartboost.sharedChartboost().getContext().getSystemService("connectivity");
            if (connectivityManager == null) {
                return -1;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
                return 0;
            }
            return activeNetworkInfo.getType() == 1 ? 1 : 2;
        } catch (Exception e) {
            return -1;
        }
    }
}
