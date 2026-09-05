package com.topfreegames.bikerace;

/* JADX INFO: compiled from: Board.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public com.topfreegames.engine.a.b f1168a = new com.topfreegames.engine.a.b();
    public com.topfreegames.engine.a.b b = new com.topfreegames.engine.a.b();
    private com.topfreegames.engine.a.b c = new com.topfreegames.engine.a.b();
    private com.topfreegames.engine.a.b d = new com.topfreegames.engine.a.b();

    public d(d dVar) {
        if (dVar == null) {
            throw new IllegalArgumentException("Board cannot be null!");
        }
        this.f1168a.a(dVar.f1168a);
        this.b.a(dVar.b);
    }

    public d(com.topfreegames.engine.a.b bVar, com.topfreegames.engine.a.b bVar2) {
        if (bVar == null) {
            throw new IllegalArgumentException("V1 cannot be null!");
        }
        if (bVar2 == null) {
            throw new IllegalArgumentException("V2 cannot be null!");
        }
        this.f1168a.a(bVar);
        this.b.a(bVar2);
    }

    public float a() {
        return (float) Math.atan2(this.b.b - this.f1168a.b, this.b.f1553a - this.f1168a.f1553a);
    }
}
