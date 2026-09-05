package com.topfreegames.bikerace.worldcup.a;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.bikerace.activities.WorldCupShopActivity;
import com.topfreegames.bikerace.bb;
import com.topfreegames.bikerace.worldcup.o;
import com.topfreegames.bikerace.worldcup.views.WorldCupGemItemView;
import com.topfreegames.bikerace.z;

/* JADX INFO: compiled from: WorldCupGemShopMode.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a extends g {
    private static final int[] c = {1, 6, 12, 30, 60, 86, 1};
    private WorldCupGemItemView[] d;
    private z e;
    private bb f;
    private View.OnClickListener g;

    public a(WorldCupShopActivity worldCupShopActivity) {
        super(worldCupShopActivity);
        this.d = new WorldCupGemItemView[7];
        this.g = new View.OnClickListener() { // from class: com.topfreegames.bikerace.worldcup.a.a.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                o oVarA = o.a();
                int iAv = a.this.f.av();
                if (oVarA.j() >= iAv) {
                    a.this.e.i(1);
                    a.this.e.j(-iAv);
                    a.this.f1432a.j();
                }
            }
        };
        BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) worldCupShopActivity.getApplication();
        this.e = bikeRaceApplication.a();
        this.f = bikeRaceApplication.a(false);
    }

    @Override // com.topfreegames.bikerace.worldcup.a.g
    public int a() {
        return 2130903127;
    }

    @Override // com.topfreegames.bikerace.worldcup.a.g
    public void b() {
        Resources resources = this.f1432a.getResources();
        this.d[0] = (WorldCupGemItemView) this.b.findViewById(2131296945);
        this.d[0].setPurchaseListener(new b(this, resources.getString(2131100062)));
        this.d[0].setGemValue(c[0]);
        this.d[1] = (WorldCupGemItemView) this.b.findViewById(2131296944);
        this.d[1].setPurchaseListener(new b(this, resources.getString(2131100064)));
        this.d[1].setGemValue(c[1]);
        this.d[2] = (WorldCupGemItemView) this.b.findViewById(2131296943);
        this.d[2].setPurchaseListener(new b(this, resources.getString(2131100066)));
        this.d[2].setGemValue(c[2]);
        this.d[3] = (WorldCupGemItemView) this.b.findViewById(2131296948);
        this.d[3].setPurchaseListener(new b(this, resources.getString(2131100068)));
        this.d[3].setGemValue(c[3]);
        this.d[4] = (WorldCupGemItemView) this.b.findViewById(2131296947);
        this.d[4].setPurchaseListener(new b(this, resources.getString(2131100070)));
        this.d[4].setGemValue(c[4]);
        this.d[5] = (WorldCupGemItemView) this.b.findViewById(2131296946);
        this.d[5].setPurchaseListener(new b(this, resources.getString(2131100072)));
        this.d[5].setGemValue(c[5]);
        this.d[6] = (WorldCupGemItemView) this.b.findViewById(2131296949);
        this.d[6].setPurchaseListener(this.g);
        this.d[6].setGemValue(c[6]);
        this.d[6].setPriceInCoins(this.f.av());
    }

    @Override // com.topfreegames.bikerace.worldcup.a.g
    public void a(Bundle bundle) {
    }

    @Override // com.topfreegames.bikerace.worldcup.a.g
    public void c() {
    }

    @Override // com.topfreegames.bikerace.worldcup.a.g
    protected boolean d() {
        return false;
    }

    @Override // com.topfreegames.bikerace.worldcup.a.g
    public void e() {
    }
}
