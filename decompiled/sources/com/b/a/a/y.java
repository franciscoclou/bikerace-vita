package com.b.a.a;

import java.util.Collections;
import java.util.Map;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f313a = "Crashlytics Android SDK/" + cm.a().f();
    private static final Pattern b = Pattern.compile("http(s?)://[^\\/]+", 2);
    private final String c;
    private final bu d;
    private final bw e;
    private final String f;

    public y(String str, String str2, bu buVar, bw bwVar) {
        if (str2 == null) {
            throw new IllegalArgumentException("url must not be null.");
        }
        if (buVar == null) {
            throw new IllegalArgumentException("requestFactory must not be null.");
        }
        this.f = str;
        this.c = ba.e(this.f) ? str2 : b.matcher(str2).replaceFirst(this.f);
        this.d = buVar;
        this.e = bwVar;
    }

    protected final String a() {
        return this.c;
    }

    protected final bx b() {
        return a(Collections.emptyMap());
    }

    protected final bx a(Map<String, String> map) {
        return this.d.a(this.e, this.c, map).a(false).a(10000).a("User-Agent", f313a).a("X-CRASHLYTICS-DEVELOPER-TOKEN", "bca6990fc3c15a8105800c0673517a4b579634a1");
    }
}
