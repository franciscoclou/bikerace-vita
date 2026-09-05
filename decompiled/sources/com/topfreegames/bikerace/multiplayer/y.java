package com.topfreegames.bikerace.multiplayer;

/* JADX INFO: compiled from: MultiplayerManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class y implements ab {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f1339a;

    private y() {
        this.f1339a = false;
    }

    /* synthetic */ y(y yVar) {
        this();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        this.f1339a = z;
    }

    @Override // com.topfreegames.bikerace.multiplayer.ab
    public boolean a() {
        return this.f1339a;
    }
}
