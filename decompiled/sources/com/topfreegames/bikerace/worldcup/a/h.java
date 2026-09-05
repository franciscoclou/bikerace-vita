package com.topfreegames.bikerace.worldcup.a;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.bikerace.activities.MultiplayerMainActivity;
import com.topfreegames.bikerace.activities.WorldCupShopActivity;
import com.topfreegames.bikerace.bb;
import com.topfreegames.bikerace.e.ad;
import com.topfreegames.bikerace.v;
import com.topfreegames.bikerace.worldcup.l;
import com.topfreegames.bikerace.worldcup.o;
import com.topfreegames.bikerace.worldcup.p;
import com.topfreegames.bikerace.worldcup.r;
import com.topfreegames.bikerace.worldcup.views.SlotListView;
import com.topfreegames.bikerace.worldcup.views.WorldCupBikeItemView;
import com.topfreegames.bikerace.worldcup.views.WorldCupCollectPartView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/* JADX INFO: compiled from: WorldCupShopSlotMode.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class h extends g {
    private static /* synthetic */ int[] H;
    private com.topfreegames.bikerace.c A;
    private p B;
    private View.OnClickListener C;
    private View.OnClickListener D;
    private AbsListView.OnScrollListener E;
    private View.OnClickListener F;
    private com.topfreegames.bikerace.worldcup.i G;
    private View c;
    private View d;
    private TextView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private TextView i;
    private ImageView j;
    private ImageView k;
    private ImageView l;
    private ImageView[] m;
    private ImageView[] n;
    private SlotListView o;
    private j p;
    private int q;
    private int r;
    private com.topfreegames.bikerace.worldcup.a s;
    private int t;
    private int u;
    private WorldCupCollectPartView v;
    private WorldCupBikeItemView w;
    private v x;
    private boolean y;
    private boolean z;

    static /* synthetic */ int[] f() {
        int[] iArr = H;
        if (iArr == null) {
            iArr = new int[p.valuesCustom().length];
            try {
                iArr[p.ORDINARY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[p.RARE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            H = iArr;
        }
        return iArr;
    }

    public h(WorldCupShopActivity worldCupShopActivity, p pVar) {
        super(worldCupShopActivity);
        this.m = new ImageView[5];
        this.n = new ImageView[5];
        this.q = -1;
        this.r = -1;
        this.t = -1;
        this.u = -2;
        this.y = false;
        this.z = false;
        this.C = new View.OnClickListener() { // from class: com.topfreegames.bikerace.worldcup.a.h.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                h.this.w.setVisibility(8);
                h.this.c.setVisibility(0);
            }
        };
        this.D = new View.OnClickListener() { // from class: com.topfreegames.bikerace.worldcup.a.h.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (h.this.B == p.ORDINARY) {
                    Intent intent = new Intent();
                    intent.setClass(h.this.f1432a, MultiplayerMainActivity.class);
                    h.this.f1432a.a(intent);
                    return;
                }
                h.this.f1432a.a(r.GEMSHOP);
            }
        };
        this.E = new AbsListView.OnScrollListener() { // from class: com.topfreegames.bikerace.worldcup.a.h.3
            /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0000. Please report as an issue. */
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i) {
                switch (i) {
                }
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (i + 1 < h.this.t || h.this.t <= 0) {
                    if (i + 1 != h.this.u && !h.this.y) {
                        ((ListView) absListView).setSelectionFromTop(h.this.u, (h.this.r - h.this.q) / 2);
                        return;
                    }
                    return;
                }
                absListView.smoothScrollBy(0, 0);
                ((ListView) absListView).setSelectionFromTop(h.this.t, (h.this.r - h.this.q) / 2);
                h.this.u = h.this.t;
                h.this.t = -1;
                h.this.y = false;
                h.this.b.postDelayed(new Runnable() { // from class: com.topfreegames.bikerace.worldcup.a.h.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        h.this.x.m();
                        h.this.l();
                    }
                }, 250L);
            }
        };
        this.F = new View.OnClickListener() { // from class: com.topfreegames.bikerace.worldcup.a.h.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (h.this.r < 0) {
                    h.this.r = h.this.o.getHeight();
                }
                if (!h.this.y) {
                    o oVarA = o.a();
                    com.topfreegames.bikerace.worldcup.j jVarB = oVarA.b(h.this.B);
                    if (oVarA.j() < jVarB.b()) {
                        new ad(h.this.f1432a, h.this.B, h.this.D).show();
                    } else if (oVarA.h() < jVarB.a()) {
                        new ad(h.this.f1432a, h.this.B, h.this.D).show();
                    } else {
                        o.a().a(h.this.B, new com.topfreegames.bikerace.worldcup.k() { // from class: com.topfreegames.bikerace.worldcup.a.h.4.1
                            @Override // com.topfreegames.bikerace.worldcup.k
                            public void a() {
                                h.this.f1432a.j();
                            }

                            @Override // com.topfreegames.bikerace.worldcup.k
                            public void a(int i, com.topfreegames.bikerace.worldcup.a aVar) {
                                h.this.v.a(i, aVar);
                                h.this.a(aVar);
                            }

                            @Override // com.topfreegames.bikerace.worldcup.k
                            public void a(com.topfreegames.bikerace.worldcup.a aVar) {
                                h.this.v.setCollectedPart(aVar);
                                h.this.a(aVar);
                            }
                        }, new com.topfreegames.bikerace.worldcup.d() { // from class: com.topfreegames.bikerace.worldcup.a.h.4.2
                            @Override // com.topfreegames.bikerace.worldcup.d
                            public void a(com.topfreegames.bikerace.c cVar) {
                                h.this.A = cVar;
                                h.this.z = true;
                            }
                        });
                    }
                }
            }
        };
        this.G = new com.topfreegames.bikerace.worldcup.i() { // from class: com.topfreegames.bikerace.worldcup.a.h.5
            @Override // com.topfreegames.bikerace.worldcup.i
            public void a(long j) {
                h.this.b.post(new Runnable() { // from class: com.topfreegames.bikerace.worldcup.a.h.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        h.this.h();
                    }
                });
            }

            @Override // com.topfreegames.bikerace.worldcup.i
            public void a(com.topfreegames.bikerace.c cVar, p pVar2, long j) {
                h.this.b.post(new Runnable() { // from class: com.topfreegames.bikerace.worldcup.a.h.5.2
                    @Override // java.lang.Runnable
                    public void run() {
                        h.this.g();
                    }
                });
            }
        };
        if (pVar == null) {
            throw new IllegalArgumentException("Type cannot be null!");
        }
        this.B = pVar;
        this.x = ((BikeRaceApplication) worldCupShopActivity.getApplication()).b();
    }

    @Override // com.topfreegames.bikerace.worldcup.a.g
    protected int a() {
        return 2130903122;
    }

    @Override // com.topfreegames.bikerace.worldcup.a.g
    protected void b() {
        this.p = new j(this, this.f1432a, 0, k());
        this.o = (SlotListView) this.b.findViewById(2131296882);
        this.o.setAdapter((ListAdapter) this.p);
        this.o.setOnScrollListener(this.E);
        this.c = this.b.findViewById(2131296891);
        this.c.setClickable(true);
        this.c.setEnabled(true);
        this.g = (TextView) this.b.findViewById(2131296884);
        this.k = (ImageView) this.b.findViewById(2131296883);
        this.j = (ImageView) this.b.findViewById(2131296887);
        this.d = this.b.findViewById(2131296885);
        this.e = (TextView) this.b.findViewById(2131296888);
        this.f = (TextView) this.b.findViewById(2131296889);
        this.l = (ImageView) this.b.findViewById(2131296904);
        this.h = (TextView) this.b.findViewById(2131296905);
        this.i = (TextView) this.b.findViewById(2131296886);
        this.n[0] = (ImageView) this.b.findViewById(2131296893);
        this.n[1] = (ImageView) this.b.findViewById(2131296894);
        this.n[2] = (ImageView) this.b.findViewById(2131296895);
        this.n[3] = (ImageView) this.b.findViewById(2131296896);
        this.n[4] = (ImageView) this.b.findViewById(2131296897);
        this.m[0] = (ImageView) this.b.findViewById(2131296899);
        this.m[1] = (ImageView) this.b.findViewById(2131296900);
        this.m[2] = (ImageView) this.b.findViewById(2131296901);
        this.m[3] = (ImageView) this.b.findViewById(2131296902);
        this.m[4] = (ImageView) this.b.findViewById(2131296903);
        this.v = (WorldCupCollectPartView) this.b.findViewById(2131296906);
        this.w = (WorldCupBikeItemView) this.b.findViewById(2131296907);
    }

    @Override // com.topfreegames.bikerace.worldcup.a.g
    public void a(Bundle bundle) {
        i();
        this.y = false;
        this.c.setOnClickListener(this.F);
        this.c.setVisibility(0);
        g();
        o.a().v().a(this.G);
        this.b.invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        com.topfreegames.bikerace.worldcup.h hVarV = o.a().v();
        if (hVarV.d() == this.B) {
            com.topfreegames.bikerace.c cVarC = hVarV.c();
            this.i.setText(String.valueOf(l.c(com.topfreegames.c.a.a().getTime())) + this.f1432a.getResources().getString(2131100094));
            this.j.setImageResource(l.a(cVarC));
            this.e.setText(l.b(this.f1432a, cVarC));
            h();
            this.d.setVisibility(0);
            return;
        }
        this.d.setVisibility(4);
    }

    @Override // com.topfreegames.bikerace.worldcup.a.g
    public void c() {
        o.a().v().b(this.G);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        this.f.setText(l.a(o.a().v().e()));
    }

    private void i() {
        int i;
        int color;
        int i2;
        int i3;
        int i4;
        int i5;
        int iA;
        com.topfreegames.bikerace.worldcup.j jVarB = o.a().b(this.B);
        switch (f()[this.B.ordinal()]) {
            case 2:
                i = 2130837945;
                color = Color.parseColor("#0BED5D");
                i2 = 2131100093;
                i3 = 2130837976;
                i4 = 2130837932;
                i5 = 2130837739;
                iA = jVarB.a();
                break;
            default:
                i = 2130837944;
                color = Color.parseColor("#FFC12E");
                i2 = 2131100092;
                i3 = 2130837973;
                i4 = 2130837933;
                i5 = 2130837661;
                iA = jVarB.b();
                break;
        }
        this.k.setImageResource(i);
        this.g.setText(i2);
        this.g.setTextColor(color);
        this.c.setBackgroundResource(i3);
        this.d.setBackgroundResource(i4);
        this.l.setImageResource(i5);
        this.h.setText(" x" + iA);
        j();
    }

    private void j() {
        bb bbVarA = ((BikeRaceApplication) this.f1432a.getApplication()).a(false);
        int iA = com.topfreegames.bikerace.worldcup.e.a(this.B, bbVarA);
        for (int i = 0; i < this.n.length; i++) {
            if (i < iA) {
                this.n[i].setVisibility(0);
            } else {
                this.n[i].setVisibility(8);
            }
        }
        int iB = com.topfreegames.bikerace.worldcup.e.b(this.B, bbVarA);
        for (int i2 = 0; i2 < this.m.length; i2++) {
            if (i2 < iB) {
                this.m[i2].setVisibility(0);
            } else {
                this.m[i2].setVisibility(8);
            }
        }
    }

    private com.topfreegames.bikerace.worldcup.a[] k() {
        ArrayList arrayList = new ArrayList(Arrays.asList(o.a().b(this.B).e()));
        Collections.sort(arrayList, new Comparator<com.topfreegames.bikerace.worldcup.a>() { // from class: com.topfreegames.bikerace.worldcup.a.h.6
            @Override // java.util.Comparator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(com.topfreegames.bikerace.worldcup.a aVar, com.topfreegames.bikerace.worldcup.a aVar2) {
                if (aVar == aVar2) {
                    return 0;
                }
                int i = -aVar.d();
                int i2 = -aVar2.d();
                if (i > i2) {
                    return -1;
                }
                if (i < i2) {
                    return 1;
                }
                int iOrdinal = aVar.a().ordinal();
                int iOrdinal2 = aVar2.a().ordinal();
                if (iOrdinal > iOrdinal2) {
                    return -1;
                }
                return iOrdinal < iOrdinal2 ? 1 : 0;
            }
        });
        ArrayList arrayList2 = new ArrayList();
        while (arrayList.size() > 0) {
            com.topfreegames.bikerace.worldcup.a aVar = (com.topfreegames.bikerace.worldcup.a) arrayList.get(0);
            if (aVar != null) {
                arrayList2.add(aVar);
                arrayList.remove(0);
            }
            if (arrayList.size() > 0) {
                int size = arrayList.size() - 1;
                com.topfreegames.bikerace.worldcup.a aVar2 = (com.topfreegames.bikerace.worldcup.a) arrayList.get(size);
                if (aVar2 != null) {
                    arrayList2.add(aVar2);
                    arrayList.remove(size);
                }
            }
        }
        return (com.topfreegames.bikerace.worldcup.a[]) arrayList2.toArray(new com.topfreegames.bikerace.worldcup.a[arrayList2.size()]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.topfreegames.bikerace.worldcup.a aVar) {
        this.s = new com.topfreegames.bikerace.worldcup.a(aVar);
        int iA = this.p.a(aVar);
        int firstVisiblePosition = this.o.getFirstVisiblePosition() + 1;
        this.t = iA + (this.p.f1446a.length - this.p.a(this.p.a(firstVisiblePosition))) + (this.p.f1446a.length * 4) + firstVisiblePosition;
        this.o.smoothScrollBy(this.p.f1446a.length * 10 * this.q, 15000);
        this.f1432a.a(false);
        this.c.setVisibility(4);
        this.y = true;
        this.x.l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        if (this.s != null) {
            this.v.setListener(new k(this, this.s));
            this.v.setVisibility(0);
            this.s = null;
        }
        this.c.setVisibility(0);
        this.f1432a.a(true);
    }

    @Override // com.topfreegames.bikerace.worldcup.a.g
    protected boolean d() {
        return false;
    }

    @Override // com.topfreegames.bikerace.worldcup.a.g
    public void e() {
    }
}
