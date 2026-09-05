package com.b.a.a;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class d extends az {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ c f299a;

    d(c cVar) {
        this.f299a = cVar;
    }

    @Override // com.b.a.a.az
    public final void a() {
        try {
            c.a(this.f299a);
        } catch (Exception e) {
            cm.a().b().a("Crashlytics", "Problem encountered during Crashlytics initialization.", e);
        }
    }
}
