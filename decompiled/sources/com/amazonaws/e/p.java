package com.amazonaws.e;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class p implements t<String, r> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static p f112a;

    public static p a() {
        if (f112a == null) {
            f112a = new p();
        }
        return f112a;
    }

    @Override // com.amazonaws.e.t
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String unmarshall(r rVar) {
        return rVar.a();
    }
}
