package com.topfreegames.engine.a;

/* JADX INFO: compiled from: Utils.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static b f1552a = new b();
    private static b b = new b();
    private static b c = new b();
    private static b d = new b();
    private static b e = new b();
    private static b f = new b();

    public static float a(b bVar, b bVar2, b bVar3) {
        f1552a.a(bVar2).c(bVar);
        b.a(bVar3).c(bVar2);
        return f1552a.e(b);
    }

    public static boolean a(b bVar, b bVar2, b bVar3, b bVar4) {
        return a(bVar, bVar2, bVar3) * a(bVar, bVar2, bVar4) <= 0.0f && a(bVar3, bVar4, bVar) * a(bVar3, bVar4, bVar2) <= 0.0f;
    }

    public static b b(b bVar, b bVar2, b bVar3) {
        d.a(bVar3).c(bVar2);
        e.a(bVar).c(bVar3);
        float fD = d.d(e);
        e.a(bVar).c(bVar2);
        if (d.d(e) * fD < 0.0f) {
            c.a(e.a(d, f)).b(bVar2);
        } else if (fD > 0.0f) {
            c.a(bVar3);
        } else {
            c.a(bVar2);
        }
        return c;
    }
}
