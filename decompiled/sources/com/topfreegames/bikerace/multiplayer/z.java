package com.topfreegames.bikerace.multiplayer;

import android.os.Binder;

/* JADX INFO: compiled from: MultiplayerNotificationService.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class z extends Binder {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ MultiplayerNotificationService f1340a;

    public z(MultiplayerNotificationService multiplayerNotificationService) {
        this.f1340a = multiplayerNotificationService;
    }

    MultiplayerNotificationService a() {
        return this.f1340a;
    }
}
