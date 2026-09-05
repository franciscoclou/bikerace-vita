package com.google.ads;

import android.content.Context;
import android.net.Uri;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class al {
    private ai d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f624a = "googleads.g.doubleclick.net";
    private String b = "/pagead/ads";
    private String[] c = {".doubleclick.net", ".googleadservices.com", ".googlesyndication.com"};
    private bf e = new bf();

    public al(ai aiVar) {
        this.d = aiVar;
    }

    public boolean a(Uri uri) {
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            String host = uri.getHost();
            for (String str : this.c) {
                if (host.endsWith(str)) {
                    return true;
                }
            }
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public void a(String str) {
        this.c = str.split(",");
    }

    public Uri a(Uri uri, Context context) throws am {
        try {
            return a(uri, context, uri.getQueryParameter("ai"), true);
        } catch (UnsupportedOperationException e) {
            throw new am("Provided Uri is not in a valid state");
        }
    }

    private Uri a(Uri uri, String str, String str2) {
        String string = uri.toString();
        int iIndexOf = string.indexOf("&adurl");
        if (iIndexOf == -1) {
            iIndexOf = string.indexOf("?adurl");
        }
        if (iIndexOf != -1) {
            return Uri.parse(string.substring(0, iIndexOf + 1) + str + "=" + str2 + "&" + string.substring(iIndexOf + 1));
        }
        return uri.buildUpon().appendQueryParameter(str, str2).build();
    }

    private Uri a(Uri uri, Context context, String str, boolean z) throws am {
        String strA;
        try {
            if (uri.getQueryParameter("ms") != null) {
                throw new am("Query parameter already exists: ms");
            }
            if (z) {
                strA = this.d.a(context, str);
            } else {
                strA = this.d.a(context);
            }
            return a(uri, "ms", strA);
        } catch (UnsupportedOperationException e) {
            throw new am("Provided Uri is not in a valid state");
        }
    }
}
