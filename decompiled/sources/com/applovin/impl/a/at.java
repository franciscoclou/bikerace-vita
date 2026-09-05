package com.applovin.impl.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.io.InputStream;
import java.util.Scanner;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class at {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f191a = {7, 4, 2, 1, 11};
    private static final int[] b = {5, 6, 10, 3, 9, 8, 14};
    private static final int[] c = {15, 12, 13};
    private static final String d = at.class.getSimpleName();

    private static NetworkInfo a(Context context) {
        ConnectivityManager connectivityManager;
        if (!au.a("android.permission.ACCESS_NETWORK_STATE", context) || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null) {
            return null;
        }
        return connectivityManager.getActiveNetworkInfo();
    }

    static String a(d dVar) {
        String str;
        NetworkInfo networkInfoA = a(dVar.h());
        if (networkInfoA == null) {
            return "unknown";
        }
        int type = networkInfoA != null ? networkInfoA.getType() : Integer.MAX_VALUE;
        int subtype = networkInfoA != null ? networkInfoA.getSubtype() : 0;
        if (type == 1) {
            str = "wifi";
        } else if (type != 0) {
            str = "unknown";
        } else if (a(subtype, f191a)) {
            str = "2g";
        } else if (a(subtype, b)) {
            str = "3g";
        } else {
            str = a(subtype, c) ? "4g" : "mobile";
        }
        dVar.f().a(d, "Network " + type + "/" + subtype + " resolved to " + str);
        return str;
    }

    static String a(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        Scanner scanner = new Scanner(inputStream);
        StringBuffer stringBuffer = new StringBuffer();
        while (scanner.hasNextLine()) {
            stringBuffer.append(scanner.nextLine());
        }
        return stringBuffer.toString();
    }

    static String a(String str, d dVar) {
        if (str == null) {
            throw new IllegalArgumentException("No endpoint specified");
        }
        if (dVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        String str2 = (String) dVar.a(j.e);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append((String) dVar.a(j.k));
        stringBuffer.append(str);
        if (str2 == null || str2.length() <= 0) {
            stringBuffer.append("?api_key=");
            stringBuffer.append(dVar.a());
        } else {
            stringBuffer.append("?device_token=");
            stringBuffer.append(str2);
        }
        return stringBuffer.toString();
    }

    static JSONObject a(JSONObject jSONObject) {
        return (JSONObject) jSONObject.getJSONArray("results").get(0);
    }

    static void a(int i, d dVar) {
        m mVarG = dVar.g();
        if (i == 401) {
            mVarG.a(j.c, "");
            mVarG.a(j.e, "");
            mVarG.a(j.m, 0L);
            mVarG.b();
            return;
        }
        if (i == 418) {
            mVarG.a(j.f204a, true);
            mVarG.b();
        } else if (i >= 400 && i < 500) {
            dVar.p();
        } else if (i == 0) {
            dVar.p();
        }
    }

    static void a(JSONObject jSONObject, d dVar) {
        if (jSONObject == null) {
            throw new IllegalArgumentException("No response specified");
        }
        if (dVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        try {
            if (jSONObject.has("settings")) {
                m mVarG = dVar.g();
                mVarG.a(jSONObject.getJSONObject("settings"));
                mVarG.b();
                dVar.f().a(d, "New settings processed");
            }
        } catch (JSONException e) {
            dVar.f().b(d, "Unable to parse settings out of API response", e);
        }
    }

    private static boolean a(int i, int[] iArr) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    static String b(String str, d dVar) {
        if (str == null) {
            throw new IllegalArgumentException("No endpoint specified");
        }
        if (dVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append((String) dVar.a(j.l));
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    static void b(int i, d dVar) {
        if (i == 418) {
            m mVarG = dVar.g();
            mVarG.a(j.f204a, true);
            mVarG.b();
        }
    }
}
