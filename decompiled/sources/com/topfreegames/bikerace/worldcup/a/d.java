package com.topfreegames.bikerace.worldcup.a;

import android.view.View;
import com.topfreegames.bikerace.worldcup.o;
import com.topfreegames.bikerace.worldcup.p;
import com.topfreegames.bikerace.worldcup.r;

/* JADX INFO: compiled from: WorldCupShopBikeMode.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class d implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ c f1428a;
    private final com.topfreegames.bikerace.c b;

    public d(c cVar, com.topfreegames.bikerace.c cVar2) {
        this.f1428a = cVar;
        this.b = cVar2;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        o oVarA = o.a();
        p pVarA = oVarA.a(this.b);
        if (pVarA == p.RARE && oVarA.p()) {
            this.f1428a.f1432a.a(r.SLOT_RARE);
        } else if (pVarA == p.ORDINARY && oVarA.o()) {
            this.f1428a.f1432a.a(r.SLOT_ORDINARY);
        }
    }
}
