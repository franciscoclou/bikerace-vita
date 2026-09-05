package com.amazonaws.f;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f127a;

    public h(String str) {
        this.f127a = str;
    }

    public String a() {
        return this.f127a;
    }

    public boolean a(String str) {
        if (!this.f127a.startsWith(str)) {
            return false;
        }
        this.f127a = this.f127a.substring(str.length());
        return true;
    }

    public boolean b(String str) {
        if (!this.f127a.startsWith(str)) {
            return false;
        }
        while (this.f127a.startsWith(str)) {
            this.f127a = this.f127a.substring(str.length());
        }
        return true;
    }

    public boolean c(String str) {
        int iIndexOf = this.f127a.indexOf(str);
        if (iIndexOf < 0) {
            return false;
        }
        this.f127a = this.f127a.substring(iIndexOf + str.length());
        return true;
    }
}
