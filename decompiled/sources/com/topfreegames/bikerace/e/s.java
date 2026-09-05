package com.topfreegames.bikerace.e;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: GiftsDialog.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class s extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.topfreegames.bikerace.views.f f1209a;
    private View.OnClickListener b;
    private v c;
    private WeakReference<Activity> d;
    private z e;
    private t f;
    private x g;
    private u h;
    private TextView i;
    private ListView j;
    private long k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(Activity activity, com.topfreegames.bikerace.f.a[] aVarArr, long j, z zVar, t tVar, x xVar, u uVar) {
        super(activity, 2131492932);
        w wVar = null;
        this.f1209a = new com.topfreegames.bikerace.views.f() { // from class: com.topfreegames.bikerace.e.s.1
            private static /* synthetic */ int[] b;

            static /* synthetic */ int[] a() {
                int[] iArr = b;
                if (iArr == null) {
                    iArr = new int[com.topfreegames.bikerace.f.b.valuesCustom().length];
                    try {
                        iArr[com.topfreegames.bikerace.f.b.ASK_TRACK.ordinal()] = 3;
                    } catch (NoSuchFieldError e) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.f.b.GIVE_ONE_TRACK.ordinal()] = 1;
                    } catch (NoSuchFieldError e2) {
                    }
                    try {
                        iArr[com.topfreegames.bikerace.f.b.GIVE_SPECIFIC_TRACK.ordinal()] = 2;
                    } catch (NoSuchFieldError e3) {
                    }
                    b = iArr;
                }
                return iArr;
            }

            @Override // com.topfreegames.bikerace.views.f
            public void a(com.topfreegames.bikerace.views.e eVar) {
                y yVar = null;
                com.topfreegames.bikerace.f.a gift = eVar.getGift();
                Activity activity2 = (Activity) s.this.d.get();
                if (activity2 != null) {
                    switch (a()[gift.g().ordinal()]) {
                        case 1:
                            if (s.this.f != null) {
                                s.this.f.a(gift, new y(s.this, gift, yVar));
                            }
                            break;
                        case 2:
                            if (s.this.g != null) {
                                s.this.g.a(gift, new y(s.this, gift, yVar));
                            }
                            s.this.cancel();
                            break;
                        case 3:
                            if (s.this.e != null) {
                                s.this.e.a(gift, new y(s.this, gift, yVar));
                            }
                            break;
                    }
                }
                activity2.runOnUiThread(new Runnable() { // from class: com.topfreegames.bikerace.e.s.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        s.this.c.notifyDataSetChanged();
                    }
                });
            }
        };
        this.b = new View.OnClickListener() { // from class: com.topfreegames.bikerace.e.s.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (s.this.h == null) {
                    return;
                }
                s.this.h.a();
            }
        };
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = 0L;
        if (activity == null) {
            throw new IllegalArgumentException("Activity cannot be null!");
        }
        if (zVar == null) {
            throw new IllegalArgumentException("Send listener cannot be null!");
        }
        if (tVar == null) {
            throw new IllegalArgumentException("Collect listener cannot be null!");
        }
        if (xVar == null) {
            throw new IllegalArgumentException("Play listener cannot be null!");
        }
        if (aVarArr == null) {
            throw new IllegalArgumentException("Gifts cannot be null!");
        }
        this.d = new WeakReference<>(activity);
        this.e = zVar;
        this.f = tVar;
        this.g = xVar;
        this.h = uVar;
        this.k = j;
        View viewInflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(2130903067, (ViewGroup) null);
        a(getContext(), viewInflate);
        viewInflate.findViewById(2131296333).setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.e.s.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                s.this.cancel();
            }
        });
        this.i = (TextView) viewInflate.findViewById(2131296395);
        this.c = new v(this, getContext(), 0, aVarArr);
        this.j = (ListView) viewInflate.findViewById(2131296394);
        this.j.setClickable(false);
        this.j.setFocusable(false);
        this.j.setDividerHeight(0);
        this.j.setOnScrollListener(new w(this, wVar));
        this.j.setAdapter((ListAdapter) this.c);
        a();
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setContentView(viewInflate);
    }

    private void a() {
        this.i.post(new Runnable() { // from class: com.topfreegames.bikerace.e.s.4
            @Override // java.lang.Runnable
            public void run() {
                s.this.i.setText(s.this.getContext().getString(2131099943, Integer.valueOf(s.this.c.getCount())));
            }
        });
    }
}
