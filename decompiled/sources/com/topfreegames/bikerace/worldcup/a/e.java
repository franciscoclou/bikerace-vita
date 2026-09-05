package com.topfreegames.bikerace.worldcup.a;

import android.os.Bundle;
import android.view.View;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.bikerace.z;

/* JADX INFO: compiled from: WorldCupShopBikeMode.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class e implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ c f1429a;
    private final com.topfreegames.bikerace.c b;

    public e(c cVar, com.topfreegames.bikerace.c cVar2) {
        this.f1429a = cVar;
        this.b = cVar2;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        z zVarA = ((BikeRaceApplication) this.f1429a.f1432a.getApplication()).a();
        zVarA.c(zVarA.e() != this.b ? this.b : com.topfreegames.bikerace.c.REGULAR);
        this.f1429a.a((Bundle) null);
    }
}
