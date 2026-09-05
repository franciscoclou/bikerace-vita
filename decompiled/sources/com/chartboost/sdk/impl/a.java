package com.chartboost.sdk.impl;

import android.content.Intent;
import android.net.Uri;
import com.chartboost.sdk.Chartboost;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import java.util.Date;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public JSONObject f406a;
    public Date b;
    public b c;
    public c d;
    public String e;
    public com.chartboost.sdk.c f;
    public InterfaceC0006a g;
    public u h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;

    /* JADX INFO: renamed from: com.chartboost.sdk.impl.a$a, reason: collision with other inner class name */
    public interface InterfaceC0006a {
        void a(a aVar);

        void a(a aVar, String str, JSONObject jSONObject);

        void b(a aVar);

        void c(a aVar);
    }

    public enum b {
        CBImpressionStateOther,
        CBImpressionStateWaitingForDisplay,
        CBImpressionStateDisplayedByDefaultController,
        CBImpressionStateWaitingForDismissal,
        CBImpressionStateWaitingForCaching,
        CBImpressionStateCached;

        /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
        public static b[] valuesCustom() {
            b[] bVarArrValuesCustom = values();
            int length = bVarArrValuesCustom.length;
            b[] bVarArr = new b[length];
            System.arraycopy(bVarArrValuesCustom, 0, bVarArr, 0, length);
            return bVarArr;
        }
    }

    public enum c {
        CBImpressionTypeOther,
        CBImpressionTypeInterstitial,
        CBImpressionTypeMoreApps;

        /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
        public static c[] valuesCustom() {
            c[] cVarArrValuesCustom = values();
            int length = cVarArrValuesCustom.length;
            c[] cVarArr = new c[length];
            System.arraycopy(cVarArrValuesCustom, 0, cVarArr, 0, length);
            return cVarArr;
        }
    }

    public a(JSONObject jSONObject, c cVar, InterfaceC0006a interfaceC0006a, b bVar, String str, boolean z) {
        jSONObject = jSONObject == null ? new JSONObject() : jSONObject;
        this.c = bVar;
        this.e = str;
        this.f406a = jSONObject;
        this.b = new Date();
        this.g = interfaceC0006a;
        this.d = cVar;
        this.l = z;
        this.i = false;
        this.j = false;
        this.k = false;
        boolean zEquals = jSONObject.optString(ServerProtocol.DIALOG_PARAM_TYPE, "").equals("native");
        if (zEquals && this.d == c.CBImpressionTypeInterstitial) {
            this.f = new com.chartboost.sdk.impl.b(this);
        } else if (zEquals && this.d == c.CBImpressionTypeMoreApps) {
            this.f = new h(this);
        } else {
            this.f = new x(this);
        }
        this.f.c = new com.chartboost.sdk.c.a() { // from class: com.chartboost.sdk.impl.a.1
            @Override // com.chartboost.sdk.c.a
            public void a() {
                if (this.g != null) {
                    this.g.a(this);
                }
            }
        };
        this.f.f401a = new com.chartboost.sdk.c.a() { // from class: com.chartboost.sdk.impl.a.2
            @Override // com.chartboost.sdk.c.a
            public void a() {
                if (this.g != null) {
                    this.g.b(this);
                }
            }
        };
        this.f.b = new com.chartboost.sdk.c.InterfaceC0005c() { // from class: com.chartboost.sdk.impl.a.3
            @Override // com.chartboost.sdk.c.InterfaceC0005c
            public void a(String str2, JSONObject jSONObject2) {
                if (str2 == null) {
                    str2 = this.f406a.optString("link");
                }
                String strOptString = this.f406a.optString("deep-link");
                if (strOptString != null && !strOptString.equals("")) {
                    try {
                        if (Chartboost.sharedChartboost().getContext().getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(strOptString)), NativeProtocol.MESSAGE_GET_ACCESS_TOKEN_REQUEST).size() > 0) {
                            str2 = strOptString;
                        }
                    } catch (Exception e) {
                    }
                }
                this.g.a(this, str2, jSONObject2);
            }
        };
        this.f.d = new com.chartboost.sdk.c.a() { // from class: com.chartboost.sdk.impl.a.4
            @Override // com.chartboost.sdk.c.a
            public void a() {
                if (this.g != null) {
                    this.g.c(this);
                }
            }
        };
        this.f.a(jSONObject);
    }

    public boolean a() {
        this.i = true;
        this.j = true;
        this.k = true;
        this.f.a();
        if (this.f.d() != null) {
            return true;
        }
        this.i = false;
        this.j = false;
        this.k = false;
        return false;
    }

    public void b() {
        if (this.h != null) {
            this.h.a();
            try {
                if (this.f.d().getParent() != null) {
                    this.h.removeView(this.f.d());
                }
            } catch (Exception e) {
            }
        }
        if (this.f != null) {
            this.f.c();
        }
        this.f406a = null;
        this.b = null;
        this.g = null;
        this.f = null;
        this.h = null;
    }

    public void c() {
        if (this.h != null) {
            this.h.a();
            try {
                if (this.f.d().getParent() != null) {
                    this.h.removeView(this.f.d());
                }
            } catch (Exception e) {
            }
        }
        if (this.f != null) {
            this.f.e();
        }
    }
}
