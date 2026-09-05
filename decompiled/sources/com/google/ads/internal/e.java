package com.google.ads.internal;

import android.os.Bundle;
import java.io.Serializable;
import java.util.HashMap;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f677a;
    private HashMap<String, String> b;

    public e(Bundle bundle) {
        this.f677a = bundle.getString("action");
        this.b = a(bundle.getSerializable("params"));
    }

    public e(String str) {
        this.f677a = str;
    }

    public e(String str, HashMap<String, String> map) {
        this(str);
        this.b = map;
    }

    private HashMap<String, String> a(Serializable serializable) {
        if (serializable instanceof HashMap) {
            return (HashMap) serializable;
        }
        return null;
    }

    public Bundle a() {
        Bundle bundle = new Bundle();
        bundle.putString("action", this.f677a);
        bundle.putSerializable("params", this.b);
        return bundle;
    }

    public String b() {
        return this.f677a;
    }

    public HashMap<String, String> c() {
        return this.b;
    }
}
