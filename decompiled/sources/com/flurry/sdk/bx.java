package com.flurry.sdk;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import com.flurry.android.FlurryAgent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class bx implements cl.a, ei.a, fd, Thread.UncaughtExceptionHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f503a = bx.class.getSimpleName();
    private static bx b;
    private boolean d;
    private cl e;
    private cd g;
    private String c = "";
    private Map<String, cl> f = new HashMap();

    public class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f504a;
    }

    private bx() {
        fe.a().a(this);
        eq.a().a(this);
        p();
    }

    private void p() {
        ei eiVarA = eh.a();
        this.d = ((Boolean) eiVarA.a("CaptureUncaughtExceptions")).booleanValue();
        eiVarA.a("CaptureUncaughtExceptions", (ei.a) this);
        ex.a(4, f503a, "initSettings, CrashReportingEnabled = " + this.d);
        String str = (String) eiVarA.a("VesionName");
        eiVarA.a("VesionName", (ei.a) this);
        ep.a(str);
        ex.a(4, f503a, "initSettings, VersionName = " + str);
    }

    @Override // com.flurry.sdk.ei.a
    public void a(String str, Object obj) {
        if (str.equals("CaptureUncaughtExceptions")) {
            this.d = ((Boolean) obj).booleanValue();
            ex.a(4, f503a, "onSettingUpdate, CrashReportingEnabled = " + this.d);
        } else {
            if (str.equals("VesionName")) {
                String str2 = (String) obj;
                ep.a(str2);
                ex.a(4, f503a, "onSettingUpdate, VersionName = " + str2);
                return;
            }
            ex.a(6, f503a, "onSettingUpdate internal error!");
        }
    }

    public static bx a() {
        if (b == null) {
            b = new bx();
        }
        return b;
    }

    public int b() {
        int iIntValue = ((Integer) eh.a().a("AgentVersion")).intValue();
        ex.a(4, f503a, "getAgentVersion() = " + iIntValue);
        return iIntValue;
    }

    int c() {
        return 3;
    }

    int d() {
        return 3;
    }

    int e() {
        return 3;
    }

    String f() {
        return this.c;
    }

    public String g() {
        String str;
        if (f().length() > 0) {
            str = ".";
        } else {
            str = "";
        }
        return String.format(Locale.getDefault(), "Flurry_Android_%d_%d.%d.%d%s%s", Integer.valueOf(b()), Integer.valueOf(c()), Integer.valueOf(d()), Integer.valueOf(e()), str, f());
    }

    public void a(Context context, String str) {
        cl clVar;
        fe.a().b();
        en.a().b();
        q();
        if (this.f.isEmpty()) {
            en.a().c();
        }
        if (this.f.containsKey(str)) {
            clVar = this.f.get(str);
        } else {
            clVar = new cl(context, str, this);
            clVar.a(b(context));
            this.f.put(str, clVar);
        }
        clVar.c();
        a(clVar);
    }

    public void a(Context context) {
        cl clVarH = h();
        if (clVarH != null) {
            clVarH.d();
        }
    }

    private void q() {
        if (this.g == null) {
            this.g = new cd();
        }
    }

    private Map<String, List<String>> b(Context context) {
        Bundle extras;
        if (!(context instanceof Activity) || (extras = ((Activity) context).getIntent().getExtras()) == null) {
            return null;
        }
        ex.a(3, f503a, "Launch Options Bundle is present " + extras.toString());
        HashMap map = new HashMap();
        for (String str : extras.keySet()) {
            if (str != null) {
                Object obj = extras.get(str);
                String string = obj != null ? obj.toString() : "null";
                map.put(str, new ArrayList(Arrays.asList(string)));
                ex.a(3, f503a, "Launch options Key: " + str + ". Its value: " + string);
            }
        }
        return map;
    }

    void a(cl clVar) {
        this.e = clVar;
    }

    public cl h() {
        return this.e;
    }

    public void a(String str) {
        cl clVarH = h();
        if (clVarH != null) {
            clVarH.a(str, null, false);
        }
    }

    public void a(String str, Map<String, String> map) {
        cl clVarH = h();
        if (clVarH != null) {
            clVarH.a(str, map, false);
        }
    }

    public void a(String str, boolean z) {
        cl clVarH = h();
        if (clVarH != null) {
            clVarH.a(str, null, z);
        }
    }

    public void a(String str, Map<String, String> map, boolean z) {
        cl clVarH = h();
        if (clVarH != null) {
            clVarH.a(str, map, z);
        }
    }

    public void b(String str) {
        cl clVarH = h();
        if (clVarH != null) {
            clVarH.a(str, (Map<String, String>) null);
        }
    }

    public void b(String str, Map<String, String> map) {
        cl clVarH = h();
        if (clVarH != null) {
            clVarH.a(str, map);
        }
    }

    @Deprecated
    public void a(String str, String str2, String str3) {
        StackTraceElement[] stackTraceElementArr;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null || stackTrace.length <= 2) {
            stackTraceElementArr = stackTrace;
        } else {
            stackTraceElementArr = new StackTraceElement[stackTrace.length - 2];
            System.arraycopy(stackTrace, 2, stackTraceElementArr, 0, stackTraceElementArr.length);
        }
        Throwable th = new Throwable(str2);
        th.setStackTrace(stackTraceElementArr);
        cl clVarH = h();
        if (clVarH != null) {
            clVarH.a(str, str2, str3, th);
        }
    }

    public void a(String str, String str2, Throwable th) {
        cl clVarH = h();
        if (clVarH != null) {
            clVarH.a(str, str2, th.getClass().getName(), th);
        }
    }

    public void c(String str) {
        cl clVarH = h();
        if (clVarH != null) {
            clVarH.a(str, null, false);
        }
    }

    public void c(String str, Map<String, String> map) {
        cl clVarH = h();
        if (clVarH != null) {
            clVarH.a(str, map, false);
        }
    }

    public void i() {
        cl clVarH = h();
        if (clVarH != null) {
            clVarH.g();
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        th.printStackTrace();
        if (this.d) {
            String message = "";
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null && stackTrace.length > 0) {
                StringBuilder sb = new StringBuilder();
                if (th.getMessage() != null) {
                    sb.append(" (" + th.getMessage() + ")\n");
                }
                message = sb.toString();
            } else if (th.getMessage() != null) {
                message = th.getMessage();
            }
            FlurryAgent.onError("uncaught", message, th);
        }
        for (cl clVar : new HashMap(this.f).values()) {
            if (clVar != null) {
                clVar.e();
            }
        }
        en.a().f();
    }

    public String j() {
        cl clVarH = h();
        if (clVarH == null) {
            return null;
        }
        return clVarH.k();
    }

    public String k() {
        cl clVarH = h();
        if (clVarH == null) {
            return null;
        }
        return clVarH.l();
    }

    public String l() {
        cl clVarH = h();
        if (clVarH == null) {
            return null;
        }
        return clVarH.m();
    }

    public static int m() {
        return 0;
    }

    public Location n() {
        return en.a().e();
    }

    public cd o() {
        return this.g;
    }

    @Override // com.flurry.sdk.fd
    public void b(boolean z) {
    }

    @Override // com.flurry.sdk.cl.a
    public void d(String str) {
        if (!this.f.containsKey(str)) {
            ex.a(6, f503a, "Ended session is not in the session map! Maybe it was already destroyed.");
        } else {
            cl clVarH = h();
            if (clVarH != null && TextUtils.equals(clVarH.k(), str)) {
                a((cl) null);
            }
            this.f.remove(str);
        }
        if (this.f.isEmpty()) {
            ex.a(5, f503a, "LocationProvider is going to be unsubscribed");
            en.a().d();
        }
    }

    public void a(boolean z) {
        ex.a(z);
    }
}
