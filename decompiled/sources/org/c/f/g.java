package org.c.f;

/* JADX INFO: compiled from: TimestampServiceImpl.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class g implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private h f1629a = new h();

    @Override // org.c.f.f
    public String b() {
        return String.valueOf(c().longValue() + ((long) this.f1629a.b().intValue()));
    }

    @Override // org.c.f.f
    public String a() {
        return String.valueOf(c());
    }

    private Long c() {
        return Long.valueOf(this.f1629a.a().longValue() / 1000);
    }
}
