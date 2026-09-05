package com.topfreegames.bikerace.c;

/* JADX INFO: compiled from: Collectible.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f1164a;
    int b;
    int c;
    b d;
    b e = b.NOT_COLLECTED;
    boolean f;
    float g;
    private c h;
    private com.topfreegames.engine.a.b i;
    private com.topfreegames.engine.a.b j;

    public static a a(int i, int i2, int i3, com.topfreegames.engine.a.b bVar) {
        return new a(i, i2, i3, c.EASTER_EGG, bVar, new com.topfreegames.engine.a.b(0.3f, 0.3882353f), true, 0.3f);
    }

    private a(int i, int i2, int i3, c cVar, com.topfreegames.engine.a.b bVar, com.topfreegames.engine.a.b bVar2, boolean z, float f) {
        this.f1164a = i;
        this.h = cVar;
        this.i = bVar;
        this.j = bVar2;
        this.b = i2;
        this.c = i3;
        this.d = d.a().a(this.b, this.c, this.f1164a);
        this.f = z;
        this.g = f;
    }

    public com.topfreegames.engine.a.b a() {
        return this.i;
    }

    public c b() {
        return this.h;
    }

    public com.topfreegames.engine.a.b c() {
        return this.j;
    }

    public void a(boolean z) {
        if (z) {
            if (this.d == b.NOT_COLLECTED) {
                this.d = b.JUST_COLLECTED;
            }
        } else if (this.e == b.NOT_COLLECTED) {
            this.e = b.JUST_COLLECTED;
        }
    }

    public int d() {
        return this.f1164a;
    }
}
