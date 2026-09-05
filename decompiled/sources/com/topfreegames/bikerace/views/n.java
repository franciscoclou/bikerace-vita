package com.topfreegames.bikerace.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: MultiplayerGameItemView.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class n extends RelativeLayout {
    private static Drawable m = null;
    private static /* synthetic */ int[] o;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ImageView f1407a;
    private ImageView b;
    private TextView c;
    private View d;
    private View e;
    private View f;
    private TextView g;
    private TextView h;
    private TextView i;
    private o j;
    private o k;
    private o l;
    private WeakReference<com.topfreegames.bikerace.multiplayer.l> n;

    static /* synthetic */ int[] b() {
        int[] iArr = o;
        if (iArr == null) {
            iArr = new int[p.valuesCustom().length];
            try {
                iArr[p.BOTTOM.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[p.MIDDLE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[p.SINGLE.ordinal()] = 4;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[p.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            o = iArr;
        }
        return iArr;
    }

    public n(Context context, o oVar, o oVar2, o oVar3) {
        super(context);
        this.f1407a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.n = null;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903076, this);
        this.f1407a = (ImageView) findViewById(2131296478);
        this.b = (ImageView) findViewById(2131296480);
        this.c = (TextView) findViewById(2131296482);
        this.h = (TextView) findViewById(2131296487);
        this.i = (TextView) findViewById(2131296489);
        this.g = (TextView) findViewById(2131296485);
        this.e = findViewById(2131296484);
        this.f = findViewById(2131296481);
        if (m == null) {
            m = this.b.getDrawable();
        }
        this.j = oVar;
        this.k = oVar2;
        this.l = oVar3;
        this.d = findViewById(2131296483);
        this.d.setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.views.n.1
            /* JADX WARN: Type inference failed for: r0v2, types: [com.topfreegames.bikerace.views.n$1$1] */
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (n.this.j != null) {
                    new Thread() { // from class: com.topfreegames.bikerace.views.n.1.1
                        @Override // java.lang.Thread, java.lang.Runnable
                        public void run() {
                            n.this.j.a(n.this);
                        }
                    }.start();
                }
            }
        });
        this.e.setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.views.n.2
            /* JADX WARN: Type inference failed for: r0v2, types: [com.topfreegames.bikerace.views.n$2$1] */
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (n.this.k != null) {
                    new Thread() { // from class: com.topfreegames.bikerace.views.n.2.1
                        @Override // java.lang.Thread, java.lang.Runnable
                        public void run() {
                            n.this.k.a(n.this);
                        }
                    }.start();
                }
            }
        });
        this.f.setVisibility(8);
        this.f.setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.views.n.3
            /* JADX WARN: Type inference failed for: r0v2, types: [com.topfreegames.bikerace.views.n$3$1] */
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (n.this.l != null) {
                    new Thread() { // from class: com.topfreegames.bikerace.views.n.3.1
                        @Override // java.lang.Thread, java.lang.Runnable
                        public void run() {
                            n.this.l.a(n.this);
                        }
                    }.start();
                }
            }
        });
    }

    public void a(com.topfreegames.bikerace.multiplayer.l lVar, p pVar, com.topfreegames.bikerace.multiplayer.o oVar) {
        this.n = new WeakReference<>(lVar);
        a(lVar.p(), lVar.j());
        setFriendName(String.valueOf(lVar.g()) + " ");
        e();
        com.topfreegames.bikerace.multiplayer.m mVarT = lVar.t();
        if (mVarT == com.topfreegames.bikerace.multiplayer.m.READY || mVarT == com.topfreegames.bikerace.multiplayer.m.SHOW_RESULT) {
            c();
        } else if (oVar.a(getContext(), lVar.f())) {
            d();
        } else if (lVar.x()) {
            a();
        } else {
            f();
        }
        setPosition(pVar);
    }

    public WeakReference<com.topfreegames.bikerace.multiplayer.l> getMultiplayerDataReference() {
        return this.n;
    }

    private void setFriendName(String str) {
        this.c.setText(com.topfreegames.bikerace.m.f.a(str));
    }

    public void setAvatarImage(Bitmap bitmap) {
        if (bitmap != null) {
            this.b.setImageBitmap(bitmap);
        } else {
            this.b.setImageDrawable(m);
        }
    }

    private void a(int i, int i2) {
        if (i >= 0) {
            this.h.setText(" " + Integer.toString(i) + " ");
        } else {
            this.h.setText("-");
        }
        if (i2 >= 0) {
            this.i.setText(" " + Integer.toString(i2) + " ");
        } else {
            this.i.setText("-");
        }
    }

    private void c() {
        this.d.setVisibility(0);
        this.e.setVisibility(8);
        this.g.setVisibility(8);
    }

    private void d() {
        this.d.setVisibility(8);
        this.e.setVisibility(0);
        this.g.setVisibility(8);
    }

    public void a() {
        this.d.setVisibility(8);
        this.e.setVisibility(8);
        this.g.setVisibility(0);
        this.g.setText(getResources().getString(2131099703));
    }

    private void e() {
        this.f.setVisibility(0);
    }

    private void f() {
        this.d.setVisibility(8);
        this.e.setVisibility(8);
        this.g.setVisibility(0);
        this.g.setText(getResources().getString(2131099704));
    }

    private void setPosition(p pVar) {
        switch (b()[pVar.ordinal()]) {
            case 1:
                this.f1407a.setBackgroundResource(2130837822);
                break;
            case 2:
            default:
                this.f1407a.setBackgroundResource(2130837821);
                break;
            case 3:
                this.f1407a.setBackgroundResource(2130837820);
                break;
            case 4:
                this.f1407a.setBackgroundResource(2130837822);
                break;
        }
    }
}
