package com.amazonaws;

import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final Map<String, String> f153a;

    public l(Map<String, String> map) {
        this.f153a = map;
    }

    public String a() {
        return this.f153a.get("AWS_REQUEST_ID");
    }

    public String toString() {
        return this.f153a == null ? "{}" : this.f153a.toString();
    }
}
