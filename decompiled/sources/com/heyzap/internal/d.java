package com.heyzap.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.amazonaws.javax.xml.stream.XMLStreamConstants;

/* JADX INFO: compiled from: Connectivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class d {
    public static boolean a(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String b(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return null;
        }
        int type = activeNetworkInfo.getType();
        int subtype = activeNetworkInfo.getSubtype();
        if (type == 1) {
            return "wifi";
        }
        if (type != 0) {
            return null;
        }
        switch (subtype) {
            case 1:
                return "gprs";
            case 2:
                return "edge";
            case 3:
                return "umts";
            case 4:
                return "cdma";
            case 5:
            case 6:
                return "evdo";
            case 7:
                return "rtt";
            case 8:
                return "hsdpa";
            case 9:
                return "hsupa";
            case XMLStreamConstants.ATTRIBUTE /* 10 */:
                return "hspa";
            case XMLStreamConstants.DTD /* 11 */:
                return "iden";
            case XMLStreamConstants.CDATA /* 12 */:
                return "evdo_b";
            case XMLStreamConstants.NAMESPACE /* 13 */:
                return "lte";
            case XMLStreamConstants.NOTATION_DECLARATION /* 14 */:
                return "ehrpd";
            case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
                return "hspap";
            default:
                return null;
        }
    }
}
