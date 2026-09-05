package com.google.a.a.a;

import android.content.Context;
import android.text.TextUtils;

/* JADX INFO: compiled from: ParameterLoaderImpl.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class am implements al {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f579a;
    private String b;

    public am(Context context) {
        if (context == null) {
            throw new NullPointerException("Context cannot be null");
        }
        this.f579a = context.getApplicationContext();
    }

    private int a(String str, String str2) {
        if (this.f579a == null) {
            return 0;
        }
        return this.f579a.getResources().getIdentifier(str, str2, this.b != null ? this.b : this.f579a.getPackageName());
    }

    @Override // com.google.a.a.a.al
    public String a(String str) {
        int iA = a(str, "string");
        if (iA == 0) {
            return null;
        }
        return this.f579a.getString(iA);
    }

    @Override // com.google.a.a.a.al
    public boolean c(String str) {
        int iA = a(str, "bool");
        if (iA == 0) {
            return false;
        }
        return "true".equalsIgnoreCase(this.f579a.getString(iA));
    }

    @Override // com.google.a.a.a.al
    public int a(String str, int i) {
        int iA = a(str, "integer");
        if (iA != 0) {
            try {
                return Integer.parseInt(this.f579a.getString(iA));
            } catch (NumberFormatException e) {
                ah.d("NumberFormatException parsing " + this.f579a.getString(iA));
                return i;
            }
        }
        return i;
    }

    @Override // com.google.a.a.a.al
    public Double b(String str) {
        String strA = a(str);
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        try {
            return Double.valueOf(Double.parseDouble(strA));
        } catch (NumberFormatException e) {
            ah.d("NumberFormatException parsing " + strA);
            return null;
        }
    }

    @Override // com.google.a.a.a.al
    public void d(String str) {
        this.b = str;
    }
}
