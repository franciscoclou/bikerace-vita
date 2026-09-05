package com.topfreegames.bikerace.activities;

/* JADX INFO: compiled from: CustomLevelsActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class j implements com.topfreegames.bikerace.h.a.j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ CustomLevelsActivity f1092a;
    private boolean b;
    private Runnable c;
    private Runnable d;

    public j(CustomLevelsActivity customLevelsActivity, boolean z) {
        this.f1092a = customLevelsActivity;
        this.b = true;
        this.c = null;
        this.d = null;
        this.b = z;
    }

    public j(CustomLevelsActivity customLevelsActivity, boolean z, Runnable runnable, Runnable runnable2) {
        this(customLevelsActivity, z);
        this.c = runnable;
        this.d = runnable2;
    }

    @Override // com.topfreegames.bikerace.h.a.j
    public void a(String str, com.topfreegames.bikerace.h.a.c cVar, byte[] bArr) {
        int iC;
        com.topfreegames.bikerace.h.a.q qVarA = com.topfreegames.bikerace.h.a.q.a();
        int iA = qVarA.a(str);
        if (iA < 0) {
            qVarA.a(str, bArr);
            this.f1092a.t.b(str);
            if (this.f1092a.t.getCount() <= 0) {
                this.f1092a.m();
            }
            if (this.b) {
                this.f1092a.b(this.f1092a.D.b());
            }
            iC = qVarA.c();
        } else {
            iC = iA + 1;
        }
        if (this.c != null) {
            this.c.run();
        }
        this.f1092a.c(iC);
    }

    @Override // com.topfreegames.bikerace.h.a.j
    public void a() {
        this.f1092a.a(f.ERROR_DOWNLOAD_TRACK.ordinal());
        this.f1092a.k();
        if (this.d != null) {
            this.d.run();
        }
    }
}
