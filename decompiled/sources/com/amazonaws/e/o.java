package com.amazonaws.e;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class o implements t<Boolean, r> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static o f111a;

    public static o a() {
        if (f111a == null) {
            f111a = new o();
        }
        return f111a;
    }

    @Override // com.amazonaws.e.t
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Boolean unmarshall(r rVar) {
        String strA = rVar.a();
        if (strA == null) {
            return null;
        }
        return Boolean.valueOf(Boolean.parseBoolean(strA));
    }
}
