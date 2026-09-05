package com.amazonaws.e;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class m implements t<String, c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static m f109a;

    public static m a() {
        if (f109a == null) {
            f109a = new m();
        }
        return f109a;
    }

    @Override // com.amazonaws.e.t
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String unmarshall(c cVar) {
        return cVar.b();
    }
}
