package com.topfreegames.bikerace.worldcup.a;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.HorizontalScrollView;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.bikerace.activities.WorldCupShopActivity;
import com.topfreegames.bikerace.worldcup.l;
import com.topfreegames.bikerace.worldcup.views.WorldCupBikeItemView;
import com.topfreegames.bikerace.z;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: compiled from: WorldCupShopBikeMode.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c extends g {
    private List<com.topfreegames.bikerace.c> c;
    private WorldCupBikeItemView[] d;
    private z e;
    private boolean f;
    private Comparator<com.topfreegames.bikerace.c> g;

    public c(WorldCupShopActivity worldCupShopActivity) {
        super(worldCupShopActivity);
        this.c = new ArrayList(Arrays.asList(l.a()));
        this.d = new WorldCupBikeItemView[l.a().length];
        this.f = false;
        this.g = new Comparator<com.topfreegames.bikerace.c>() { // from class: com.topfreegames.bikerace.worldcup.a.c.1
            @Override // java.util.Comparator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(com.topfreegames.bikerace.c cVar, com.topfreegames.bikerace.c cVar2) {
                boolean zA = c.this.e.a(cVar);
                boolean zA2 = c.this.e.a(cVar2);
                if (!zA && zA2) {
                    return -1;
                }
                if (zA && !zA2) {
                    return 1;
                }
                int iA = c.this.a(cVar);
                int iA2 = c.this.a(cVar2);
                if (iA <= iA2) {
                    return iA2 > iA ? 1 : 0;
                }
                return -1;
            }
        };
        this.e = ((BikeRaceApplication) worldCupShopActivity.getApplication()).a();
    }

    @Override // com.topfreegames.bikerace.worldcup.a.g
    protected int a() {
        return 2130903125;
    }

    @Override // com.topfreegames.bikerace.worldcup.a.g
    protected void b() {
    }

    @Override // com.topfreegames.bikerace.worldcup.a.g
    public void e() {
        if (!this.f) {
            ViewStub viewStub = (ViewStub) this.b.findViewById(2131296678);
            if (viewStub != null) {
                viewStub.inflate();
                this.d[0] = (WorldCupBikeItemView) this.b.findViewById(2131296868);
                this.d[1] = (WorldCupBikeItemView) this.b.findViewById(2131296869);
                this.d[2] = (WorldCupBikeItemView) this.b.findViewById(2131296870);
                this.d[3] = (WorldCupBikeItemView) this.b.findViewById(2131296871);
                this.d[4] = (WorldCupBikeItemView) this.b.findViewById(2131296872);
                this.d[5] = (WorldCupBikeItemView) this.b.findViewById(2131296873);
                this.d[6] = (WorldCupBikeItemView) this.b.findViewById(2131296874);
                this.d[7] = (WorldCupBikeItemView) this.b.findViewById(2131296875);
                this.d[8] = (WorldCupBikeItemView) this.b.findViewById(2131296876);
                this.d[9] = (WorldCupBikeItemView) this.b.findViewById(2131296877);
                this.d[10] = (WorldCupBikeItemView) this.b.findViewById(2131296878);
                this.d[11] = (WorldCupBikeItemView) this.b.findViewById(2131296879);
                this.d[12] = (WorldCupBikeItemView) this.b.findViewById(2131296880);
                this.b.findViewById(2131296677).setVisibility(8);
            }
            this.f = true;
        }
    }

    @Override // com.topfreegames.bikerace.worldcup.a.g
    public void a(Bundle bundle) {
        com.topfreegames.bikerace.c cVarA;
        if (this.f) {
            Collections.sort(this.c, this.g);
            if (bundle == null) {
                cVarA = null;
            } else {
                cVarA = com.topfreegames.bikerace.c.a(bundle.getInt("com.topfreegames.bikerace.ShopCenter"));
            }
            for (int i = 0; i < this.d.length; i++) {
                if (this.d[i] != null) {
                    com.topfreegames.bikerace.c cVar = this.c.get(i);
                    this.d[i].a(cVar, !this.e.a(cVar), new e(this, cVar), new d(this, cVar));
                    if (cVar.equals(cVarA)) {
                        a(this.d[i]);
                    }
                }
            }
            z zVarA = ((BikeRaceApplication) this.f1432a.getApplication()).a();
            com.topfreegames.bikerace.c cVarE = zVarA.e();
            for (int i2 = 0; i2 < this.d.length; i2++) {
                if (this.d[i2] != null) {
                    com.topfreegames.bikerace.c cVar2 = this.c.get(i2);
                    this.d[i2].a(!zVarA.a(cVar2), cVarE == cVar2);
                }
            }
        }
    }

    private void a(final View view) {
        this.f1432a.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.worldcup.a.c.2
            @Override // java.lang.Runnable
            public void run() {
                final HorizontalScrollView horizontalScrollView;
                if (view != null && (horizontalScrollView = (HorizontalScrollView) c.this.b.findViewById(2131296632)) != null) {
                    final View view2 = view;
                    horizontalScrollView.post(new Runnable() { // from class: com.topfreegames.bikerace.worldcup.a.c.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            horizontalScrollView.scrollTo(((view2.getLeft() + view2.getRight()) - horizontalScrollView.getWidth()) / 2, 0);
                        }
                    });
                    c.this.b.invalidate();
                }
            }
        });
    }

    @Override // com.topfreegames.bikerace.worldcup.a.g
    public void c() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(com.topfreegames.bikerace.c cVar) {
        int iD = 0;
        for (com.topfreegames.bikerace.worldcup.a aVar : com.topfreegames.bikerace.worldcup.c.c(cVar)) {
            iD -= aVar.d();
        }
        return iD;
    }

    @Override // com.topfreegames.bikerace.worldcup.a.g
    protected boolean d() {
        return true;
    }
}
