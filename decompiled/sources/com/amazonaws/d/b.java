package com.amazonaws.d;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f97a = new b();

    @Override // com.amazonaws.d.a
    public int a(int i) {
        if (i == 0) {
            return 0;
        }
        return ((int) Math.pow(2.0d, i - 1)) * 50;
    }
}
