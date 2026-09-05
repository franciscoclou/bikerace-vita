package com.topfreegames.bikerace.activities;

/* JADX INFO: compiled from: ShopActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class am implements com.topfreegames.bikerace.e.o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ ShopActivity f1068a;
    private String b;

    public am(ShopActivity shopActivity, String str) {
        this.f1068a = shopActivity;
        this.b = null;
        this.b = str;
    }

    @Override // com.topfreegames.bikerace.e.o
    public void a() {
        if (this.f1068a.c != null) {
            this.f1068a.c.a(this.b);
            ((BikeRaceApplication) this.f1068a.getApplication()).d().a(this.b);
        }
    }
}
