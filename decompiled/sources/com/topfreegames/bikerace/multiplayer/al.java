package com.topfreegames.bikerace.multiplayer;

/* JADX INFO: compiled from: RaceRecorder.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class al {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ak f1308a = new ak();

    public void a(com.topfreegames.bikerace.a aVar, float f) {
        a(aVar, f, false);
    }

    private void a(com.topfreegames.bikerace.a aVar, float f, boolean z) {
        int i = (int) (10.0f * f);
        if (this.f1308a.b() - 1 != i || z) {
            if (this.f1308a.b() <= i || z) {
                float fPow = (float) Math.pow(10.0d, 2.0d);
                long j = (long) (aVar.f812a.f1561a.f1553a * fPow);
                long j2 = (long) (aVar.f812a.f1561a.b * fPow);
                long jB = (long) (fPow * aVar.b());
                for (int iB = this.f1308a.b(); iB < i; iB++) {
                    this.f1308a.a(new a(j, j2, jB));
                }
                this.f1308a.a(new a(j, j2, jB));
            }
        }
    }

    public ak b(com.topfreegames.bikerace.a aVar, float f) {
        a aVarA;
        a(aVar, f, true);
        if (this.f1308a.b() <= 0) {
            aVarA = null;
        } else {
            aVarA = this.f1308a.a();
        }
        if (aVarA != null) {
            this.f1308a.a(new a(aVarA.f1299a, aVarA.b, aVarA.c));
            this.f1308a.a(new a(aVarA.f1299a, aVarA.b, aVarA.c));
            this.f1308a.a(new a(aVarA.f1299a, aVarA.b, aVarA.c));
        }
        ak akVar = this.f1308a;
        this.f1308a = new ak();
        this.f1308a.d();
        return akVar;
    }
}
