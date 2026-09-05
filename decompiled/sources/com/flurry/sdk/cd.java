package com.flurry.sdk;

import android.widget.Toast;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class cd extends cf implements ei.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static String f510a;
    static String b = "http://data.flurry.com/aap.do";
    static String c = "https://data.flurry.com/aap.do";
    private boolean j;

    public cd() {
        this(null);
    }

    cd(cf.a aVar) {
        super("Analytics", cd.class.getSimpleName());
        this.i = "AnalyticsData_";
        b();
        a(aVar);
    }

    private void b() {
        ei eiVarA = eh.a();
        this.j = ((Boolean) eiVarA.a("UseHttps")).booleanValue();
        eiVarA.a("UseHttps", (ei.a) this);
        ex.a(4, d, "initSettings, UseHttps = " + this.j);
        String str = (String) eiVarA.a("ReportUrl");
        eiVarA.a("ReportUrl", (ei.a) this);
        b(str);
        ex.a(4, d, "initSettings, ReportUrl = " + str);
    }

    @Override // com.flurry.sdk.ei.a
    public void a(String str, Object obj) {
        if (str.equals("UseHttps")) {
            this.j = ((Boolean) obj).booleanValue();
            ex.a(4, d, "onSettingUpdate, UseHttps = " + this.j);
        } else {
            if (str.equals("ReportUrl")) {
                String str2 = (String) obj;
                b(str2);
                ex.a(4, d, "onSettingUpdate, ReportUrl = " + str2);
                return;
            }
            ex.a(6, d, "onSettingUpdate internal error!");
        }
    }

    private void b(String str) {
        if (str != null && !str.endsWith(".do")) {
            ex.a(5, d, "overriding analytics agent report URL without an endpoint, are you sure?");
        }
        f510a = str;
    }

    String a() {
        if (f510a != null) {
            return f510a;
        }
        if (this.j) {
            return c;
        }
        return b;
    }

    @Override // com.flurry.sdk.cf
    protected void a(byte[] bArr, String str, String str2) {
        String strA = a();
        ex.a(4, d, "FlurryDataSender: start upload data " + bArr + " with id = " + str + " to " + strA);
        this.g.submit(new ce(strA, str, str2, bArr, new ch() { // from class: com.flurry.sdk.cd.1
            @Override // com.flurry.sdk.ch
            public void a(final int i, String str3, String str4, String str5) {
                ex.e(cf.d, "FlurryDataSender: report " + str4 + " sent. HTTP response: " + i + " : " + str3);
                if (ex.c() <= 3 && ex.d()) {
                    eg.a().a(new Runnable() { // from class: com.flurry.sdk.cd.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Toast.makeText(eg.a().b(), "SD HTTP Response Code: " + i, 0).show();
                        }
                    });
                }
                cd.this.a(str4, str5, i);
                cd.this.d();
            }

            @Override // com.flurry.sdk.ch
            public void a(String str3, String str4) {
                ex.e(cf.d, "FlurryDataSender: could not send report " + str3);
                cd.this.b(str3, str4);
            }
        }));
    }

    @Override // com.flurry.sdk.cf
    protected void a(String str, String str2, final int i) {
        this.f.submit(new Runnable() { // from class: com.flurry.sdk.cd.2
            @Override // java.lang.Runnable
            public void run() {
                cl clVarH;
                if (i == 200 && (clVarH = bx.a().h()) != null) {
                    clVarH.b();
                }
            }
        });
        super.a(str, str2, i);
    }
}
