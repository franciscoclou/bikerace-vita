package com.topfreegames.bikerace.activities;

/* JADX INFO: compiled from: MainActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class q implements com.topfreegames.bikerace.e.o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ MainActivity f1101a;

    private q(MainActivity mainActivity) {
        this.f1101a = mainActivity;
    }

    /* synthetic */ q(MainActivity mainActivity, q qVar) {
        this(mainActivity);
    }

    @Override // com.topfreegames.bikerace.e.o
    public void a() {
        try {
            this.f1101a.finish();
            ((BikeRaceApplication) this.f1101a.getApplication()).d().b();
            ((BikeRaceApplication) this.f1101a.getApplication()).b().i();
        } catch (Error e) {
            if (com.topfreegames.bikerace.ap.d()) {
                e.printStackTrace();
            }
            ((BikeRaceApplication) this.f1101a.getApplication()).d().a(getClass().getName(), "QuitDialog", e);
            throw e;
        } catch (Exception e2) {
            if (com.topfreegames.bikerace.ap.d()) {
                e2.printStackTrace();
            }
            ((BikeRaceApplication) this.f1101a.getApplication()).d().a(getClass().getName(), "QuitDialog", e2);
        }
    }
}
