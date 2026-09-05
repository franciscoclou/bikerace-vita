package com.topfreegames.bikerace.worldcup;

import android.os.Bundle;
import android.view.View;
import com.topfreegames.bikerace.activities.WorldCupShopActivity;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: WorldCupViewManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Map<r, com.topfreegames.bikerace.worldcup.a.g> f1467a = new HashMap();
    private r b;
    private View c;

    public q(WorldCupShopActivity worldCupShopActivity) {
        this.f1467a.put(r.BIKE, new com.topfreegames.bikerace.worldcup.a.c(worldCupShopActivity));
        this.f1467a.put(r.GEMSHOP, new com.topfreegames.bikerace.worldcup.a.a(worldCupShopActivity));
        this.f1467a.put(r.SLOT_ORDINARY, new com.topfreegames.bikerace.worldcup.a.h(worldCupShopActivity, p.ORDINARY));
        this.f1467a.put(r.SLOT_RARE, new com.topfreegames.bikerace.worldcup.a.h(worldCupShopActivity, p.RARE));
        this.f1467a.put(r.DAILY_BONUS, new com.topfreegames.bikerace.worldcup.a.f(worldCupShopActivity));
    }

    public View a(r rVar, Bundle bundle) {
        if (rVar != this.b) {
            com.topfreegames.bikerace.worldcup.a.g gVar = this.f1467a.get(rVar);
            gVar.c();
            this.c = gVar.b(bundle);
            this.b = rVar;
        } else {
            this.f1467a.get(this.b).a(bundle);
        }
        return this.c;
    }
}
