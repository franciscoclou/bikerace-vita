package com.topfreegames.bikerace.worldcup.a;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.topfreegames.bikerace.activities.WorldCupShopActivity;
import com.topfreegames.bikerace.worldcup.o;
import com.topfreegames.bikerace.worldcup.r;
import com.topfreegames.bikerace.worldcup.views.WorldCupDailyBonusItemView;

/* JADX INFO: compiled from: WorldCupShopDailyBonusMode.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class f extends g {
    private WorldCupShopActivity c;
    private com.topfreegames.bikerace.worldcup.f d;
    private WorldCupDailyBonusItemView[] e;
    private int f;
    private Button g;
    private View.OnClickListener h;
    private View.OnClickListener i;

    public f(WorldCupShopActivity worldCupShopActivity) {
        super(worldCupShopActivity);
        this.e = new WorldCupDailyBonusItemView[5];
        this.f = 1;
        this.h = new View.OnClickListener() { // from class: com.topfreegames.bikerace.worldcup.a.f.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (f.this.c != null) {
                    f.this.c.a(r.BIKE);
                }
            }
        };
        this.i = new View.OnClickListener() { // from class: com.topfreegames.bikerace.worldcup.a.f.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (f.this.d.a()) {
                    f.this.d.b();
                    f.this.c.j();
                    f.this.a((Bundle) null);
                }
            }
        };
        this.c = worldCupShopActivity;
        this.d = o.a().g();
    }

    @Override // com.topfreegames.bikerace.worldcup.a.g
    protected int a() {
        return 2130903126;
    }

    @Override // com.topfreegames.bikerace.worldcup.a.g
    protected void b() {
        this.e[0] = (WorldCupDailyBonusItemView) this.b.findViewById(2131296936);
        this.e[0].setCollectListener(this.i);
        this.e[1] = (WorldCupDailyBonusItemView) this.b.findViewById(2131296937);
        this.e[1].setCollectListener(this.i);
        this.e[2] = (WorldCupDailyBonusItemView) this.b.findViewById(2131296938);
        this.e[2].setCollectListener(this.i);
        this.e[3] = (WorldCupDailyBonusItemView) this.b.findViewById(2131296939);
        this.e[3].setCollectListener(this.i);
        this.e[4] = (WorldCupDailyBonusItemView) this.b.findViewById(2131296940);
        this.e[4].setCollectListener(this.i);
        this.g = (Button) this.b.findViewById(2131296941);
        this.g.setOnClickListener(this.h);
        a((Bundle) null);
    }

    @Override // com.topfreegames.bikerace.worldcup.a.g
    public void a(Bundle bundle) {
        this.f = this.d.c();
        for (int i = 0; i < this.e.length; i++) {
            this.e[i].setCurrentDay((this.d.a() ? 1 : 0) + this.f);
            this.e[i].a();
        }
        this.g.setVisibility(this.d.a() ? 8 : 0);
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
