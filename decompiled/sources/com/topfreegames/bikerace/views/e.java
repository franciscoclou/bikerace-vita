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

/* JADX INFO: compiled from: GiftItemView.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class e extends RelativeLayout {
    private static Drawable l = null;
    private static /* synthetic */ int[] o;
    private static /* synthetic */ int[] p;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ImageView f1398a;
    private ImageView b;
    private TextView c;
    private TextView d;
    private View e;
    private TextView f;
    private View g;
    private TextView h;
    private TextView i;
    private View j;
    private f k;
    private long m;
    private WeakReference<com.topfreegames.bikerace.f.a> n;

    static /* synthetic */ int[] a() {
        int[] iArr = o;
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
            o = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] b() {
        int[] iArr = p;
        if (iArr == null) {
            iArr = new int[g.valuesCustom().length];
            try {
                iArr[g.BOTTOM.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[g.MIDDLE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[g.SINGLE.ordinal()] = 4;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[g.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            p = iArr;
        }
        return iArr;
    }

    public e(Context context, f fVar, final View.OnClickListener onClickListener) {
        super(context);
        this.f1398a = null;
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
        this.m = 0L;
        this.n = null;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903066, this);
        this.f1398a = (ImageView) findViewById(2131296379);
        this.b = (ImageView) findViewById(2131296381);
        this.c = (TextView) findViewById(2131296382);
        this.d = (TextView) findViewById(2131296390);
        this.f = (TextView) findViewById(2131296384);
        this.i = (TextView) findViewById(2131296387);
        this.h = (TextView) findViewById(2131296386);
        this.g = findViewById(2131296385);
        this.j = findViewById(2131296388);
        if (l == null) {
            l = this.b.getDrawable();
        }
        this.k = fVar;
        this.e = findViewById(2131296383);
        this.e.setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.views.e.1
            /* JADX WARN: Type inference failed for: r0v2, types: [com.topfreegames.bikerace.views.e$1$1] */
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (e.this.k != null) {
                    new Thread() { // from class: com.topfreegames.bikerace.views.e.1.1
                        @Override // java.lang.Thread, java.lang.Runnable
                        public void run() {
                            e.this.k.a(e.this);
                        }
                    }.start();
                }
            }
        });
        this.g.setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.views.e.2
            /* JADX WARN: Type inference failed for: r0v1, types: [com.topfreegames.bikerace.views.e$2$1] */
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (onClickListener != null) {
                    final View.OnClickListener onClickListener2 = onClickListener;
                    new Thread() { // from class: com.topfreegames.bikerace.views.e.2.1
                        @Override // java.lang.Thread, java.lang.Runnable
                        public void run() {
                            onClickListener2.onClick(null);
                        }
                    }.start();
                }
            }
        });
    }

    public void a(com.topfreegames.bikerace.f.a aVar, g gVar, long j) {
        if (aVar != null) {
            this.n = new WeakReference<>(aVar);
            this.m = j;
            setFriendName(aVar.b());
            com.topfreegames.bikerace.f.b bVarG = aVar.g();
            setMessage(bVarG);
            if (aVar.e()) {
                this.e.setVisibility(8);
                this.g.setVisibility(0);
                a(aVar);
            } else {
                this.g.setVisibility(8);
                this.e.setVisibility(0);
                setButtonCaption(bVarG);
            }
            setPosition(gVar);
        }
    }

    public com.topfreegames.bikerace.f.a getGift() {
        if (this.n == null) {
            return null;
        }
        return this.n.get();
    }

    private void setFriendName(String str) {
        this.c.setText(com.topfreegames.bikerace.m.f.a(str));
    }

    private void setMessage(com.topfreegames.bikerace.f.b bVar) {
        if (bVar != null && this.n.get() != null) {
            this.d.setText(this.n.get().a(getContext()));
        }
    }

    private void setButtonCaption(com.topfreegames.bikerace.f.b bVar) {
        if (bVar != null) {
            String string = null;
            switch (a()[bVar.ordinal()]) {
                case 1:
                    string = getContext().getString(2131099946);
                    break;
                case 2:
                    string = getContext().getString(2131099947);
                    break;
                case 3:
                    string = getContext().getString(2131099948);
                    break;
            }
            if (string != null) {
                this.f.setText(string);
            }
        }
    }

    private void a(com.topfreegames.bikerace.f.a aVar) {
        String string = null;
        switch (a()[aVar.g().ordinal()]) {
            case 1:
                string = getContext().getString(2131099949);
                break;
            case 2:
                string = getContext().getString(2131099950);
                break;
            case 3:
                string = getContext().getString(2131099951);
                break;
        }
        this.h.setText(string);
        long jA = aVar.a(this.m) / 60000;
        long j = jA / 60;
        this.i.setText(String.format("%d:%02d ", Long.valueOf(j), Long.valueOf(jA - (60 * j))));
    }

    public void setAvatarImage(Bitmap bitmap) {
        if (bitmap != null) {
            this.b.setImageBitmap(bitmap);
        } else {
            this.b.setImageDrawable(l);
        }
    }

    private void setPosition(g gVar) {
        switch (b()[gVar.ordinal()]) {
            case 1:
                this.f1398a.setBackgroundResource(2130837743);
                break;
            case 2:
            default:
                this.f1398a.setBackgroundResource(2130837741);
                break;
            case 3:
                this.f1398a.setBackgroundResource(2130837740);
                break;
            case 4:
                this.f1398a.setBackgroundResource(2130837742);
                break;
        }
    }
}
