package com.amazonaws.e;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class l implements t<Long, c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static l f108a;

    public static l a() {
        if (f108a == null) {
            f108a = new l();
        }
        return f108a;
    }

    @Override // com.amazonaws.e.t
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Long unmarshall(c cVar) {
        String strB = cVar.b();
        if (strB == null) {
            return null;
        }
        return Long.valueOf(Long.parseLong(strB));
    }
}
