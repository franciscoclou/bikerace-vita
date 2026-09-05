package com.amazonaws.e;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class k implements t<Integer, c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static k f107a;

    public static k a() {
        if (f107a == null) {
            f107a = new k();
        }
        return f107a;
    }

    @Override // com.amazonaws.e.t
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer unmarshall(c cVar) {
        String strB = cVar.b();
        if (strB == null) {
            return null;
        }
        return Integer.valueOf(Integer.parseInt(strB));
    }
}
