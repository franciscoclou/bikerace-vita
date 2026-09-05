package com.topfreegames.bikerace.multiplayer;

import android.content.Context;
import com.topfreegames.bikerace.activities.BikeRaceApplication;

/* JADX INFO: compiled from: MultiplayerManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class x implements ab {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f1338a;

    private x(Context context) {
        this.f1338a = null;
        this.f1338a = context.getApplicationContext();
    }

    /* synthetic */ x(Context context, x xVar) {
        this(context);
    }

    @Override // com.topfreegames.bikerace.multiplayer.ab
    public boolean a() {
        return ((BikeRaceApplication) this.f1338a).e();
    }
}
