package com.amazonaws.e;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class j implements t<Double, c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static j f106a;

    public static j a() {
        if (f106a == null) {
            f106a = new j();
        }
        return f106a;
    }

    @Override // com.amazonaws.e.t
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Double unmarshall(c cVar) {
        String strB = cVar.b();
        if (strB == null) {
            return null;
        }
        return Double.valueOf(Double.parseDouble(strB));
    }
}
