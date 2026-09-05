package com.google.ads;

import com.facebook.AppEventsConstants;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class g {

    public enum a {
        AD,
        NO_FILL,
        ERROR,
        TIMEOUT,
        NOT_FOUND,
        EXCEPTION
    }

    public static String a(String str, String str2, Boolean bool, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        String strReplaceAll = str.replaceAll("@gw_adlocid@", str2).replaceAll("@gw_qdata@", str6).replaceAll("@gw_sdkver@", "afma-sdk-a-v6.4.1").replaceAll("@gw_sessid@", str7).replaceAll("@gw_seqnum@", str8).replaceAll("@gw_devid@", str3);
        if (str5 != null) {
            strReplaceAll = strReplaceAll.replaceAll("@gw_adnetid@", str5);
        }
        if (str4 != null) {
            strReplaceAll = strReplaceAll.replaceAll("@gw_allocid@", str4);
        }
        if (str9 != null) {
            strReplaceAll = strReplaceAll.replaceAll("@gw_adt@", str9);
        }
        if (str10 != null) {
            strReplaceAll = strReplaceAll.replaceAll("@gw_aec@", str10);
        }
        if (bool != null) {
            return strReplaceAll.replaceAll("@gw_adnetrefresh@", bool.booleanValue() ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
        return strReplaceAll;
    }

    public static <T> T a(String str, Class<T> cls) {
        return cls.cast(Class.forName(str).newInstance());
    }
}
