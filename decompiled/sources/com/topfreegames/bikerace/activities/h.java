package com.topfreegames.bikerace.activities;

/* JADX INFO: compiled from: CustomLevelsActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class h extends com.topfreegames.bikerace.f.j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ CustomLevelsActivity f1090a;
    private com.topfreegames.bikerace.f.a f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(CustomLevelsActivity customLevelsActivity, com.topfreegames.bikerace.f.a aVar) {
        super(null, null);
        this.f1090a = customLevelsActivity;
        this.f = null;
        this.f = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        String strH = this.f.h();
        if (strH == null) {
            return;
        }
        this.f1090a.a(strH, new j(this.f1090a, false));
        if (this.c != null) {
            this.c.a(this.f);
        }
    }
}
