package com.b.a;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class as implements com.b.a.a.at<Boolean> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ d f326a;

    as(d dVar) {
        this.f326a = dVar;
    }

    @Override // com.b.a.a.at
    public final /* synthetic */ Boolean a(com.b.a.a.aw awVar) {
        if (!awVar.d.f260a) {
            return false;
        }
        d dVar = this.f326a;
        return Boolean.valueOf(d.n() ? false : true);
    }
}
