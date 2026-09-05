package com.amazonaws.a;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class h implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f77a;
    private final String b;

    public h(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("Access key cannot be null.");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("Secret key cannot be null.");
        }
        this.f77a = str;
        this.b = str2;
    }

    @Override // com.amazonaws.a.b
    public String a() {
        return this.f77a;
    }

    @Override // com.amazonaws.a.b
    public String b() {
        return this.b;
    }
}
