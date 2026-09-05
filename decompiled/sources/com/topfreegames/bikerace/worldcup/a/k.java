package com.topfreegames.bikerace.worldcup.a;

import android.view.View;

/* JADX INFO: compiled from: WorldCupShopSlotMode.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class k implements View.OnClickListener {
    private static /* synthetic */ int[] c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    com.topfreegames.bikerace.worldcup.a f1448a;
    final /* synthetic */ h b;

    static /* synthetic */ int[] a() {
        int[] iArr = c;
        if (iArr == null) {
            iArr = new int[com.topfreegames.bikerace.worldcup.views.d.valuesCustom().length];
            try {
                iArr[com.topfreegames.bikerace.worldcup.views.d.COIN.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[com.topfreegames.bikerace.worldcup.views.d.PART.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            c = iArr;
        }
        return iArr;
    }

    public k(h hVar, com.topfreegames.bikerace.worldcup.a aVar) {
        this.b = hVar;
        this.f1448a = aVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (a()[this.b.v.getCollectType().ordinal()]) {
            case 1:
                this.b.x.n();
                if (this.f1448a != null && !this.b.z) {
                    this.b.c.setVisibility(4);
                    this.b.w.a(this.f1448a.a(), this.b.C);
                    this.b.w.setVisibility(0);
                }
                break;
            default:
                this.b.x.o();
                break;
        }
        this.b.f1432a.j();
        if (this.b.z) {
            this.b.c.setClickable(false);
            this.b.f1432a.a(this.b.A, new i(this.b, this.b.A));
            this.b.z = false;
            this.b.A = null;
        }
        this.b.v.setVisibility(8);
    }
}
