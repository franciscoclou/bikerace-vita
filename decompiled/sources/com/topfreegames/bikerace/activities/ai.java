package com.topfreegames.bikerace.activities;

/* JADX INFO: compiled from: ShopActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ai implements com.topfreegames.bikerace.e.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ ShopActivity f1063a;

    private ai(ShopActivity shopActivity) {
        this.f1063a = shopActivity;
    }

    /* synthetic */ ai(ShopActivity shopActivity, ai aiVar) {
        this(shopActivity);
    }

    @Override // com.topfreegames.bikerace.e.d
    public void a(com.topfreegames.bikerace.c cVar) {
        if (cVar == com.topfreegames.bikerace.c.REGULAR) {
            return;
        }
        this.f1063a.c(this.f1063a.a(cVar));
        if (!this.f1063a.u.a(cVar)) {
            this.f1063a.u.c(cVar);
            this.f1063a.c(cVar);
        }
    }
}
