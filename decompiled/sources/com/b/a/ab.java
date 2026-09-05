package com.b.a;

import android.content.Context;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class ab {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f315a;
    private final com.b.a.a.ap b;

    public ab(Context context, com.b.a.a.ap apVar) {
        this.f315a = context;
        this.b = apVar;
    }

    public final String a() {
        return a("com.crashlytics.CrashSubmissionPromptTitle", this.b.f261a);
    }

    public final String b() {
        return a("com.crashlytics.CrashSubmissionPromptMessage", this.b.b);
    }

    public final String c() {
        return a("com.crashlytics.CrashSubmissionSendTitle", this.b.c);
    }

    public final String d() {
        return a("com.crashlytics.CrashSubmissionAlwaysSendTitle", this.b.g);
    }

    public final String e() {
        return a("com.crashlytics.CrashSubmissionCancelTitle", this.b.e);
    }

    private String a(String str, String str2) {
        String strA = com.b.a.a.ba.a(this.f315a, str);
        return strA == null || strA.length() == 0 ? str2 : strA;
    }
}
