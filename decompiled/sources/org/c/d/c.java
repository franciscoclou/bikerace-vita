package org.c.d;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: OAuthRequest.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Map<String, String> f1616a;
    private String b;

    public c(l lVar, String str) {
        super(lVar, str);
        this.f1616a = new HashMap();
    }

    public void a(String str, String str2) {
        this.f1616a.put(b(str), str2);
    }

    private String b(String str) {
        if (str.startsWith("oauth_") || str.equals("scope")) {
            return str;
        }
        throw new IllegalArgumentException(String.format("OAuth parameters must either be '%s' or start with '%s'", "scope", "oauth_"));
    }

    public Map<String, String> a() {
        return this.f1616a;
    }

    public String b() {
        return this.b;
    }

    @Override // org.c.d.f
    public String toString() {
        return String.format("@OAuthRequest(%s, %s)", k(), g());
    }
}
