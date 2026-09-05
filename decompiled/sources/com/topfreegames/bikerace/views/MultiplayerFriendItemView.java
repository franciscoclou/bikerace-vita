package com.topfreegames.bikerace.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class MultiplayerFriendItemView extends RelativeLayout {
    private static Drawable l = null;
    private static /* synthetic */ int[] m;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ImageView f1366a;
    private ImageView b;
    private TextView c;
    private ImageView d;
    private ImageView e;
    private TextView f;
    private String g;
    private String h;
    private String i;
    private l j;
    private l k;

    static /* synthetic */ int[] a() {
        int[] iArr = m;
        if (iArr == null) {
            iArr = new int[m.valuesCustom().length];
            try {
                iArr[m.BOTTOM.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[m.MIDDLE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[m.SINGLE.ordinal()] = 4;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[m.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            m = iArr;
        }
        return iArr;
    }

    public MultiplayerFriendItemView(Context context, AttributeSet attributeSet, int i) {
        this(context, (l) null, (l) null);
    }

    public MultiplayerFriendItemView(Context context, l lVar, l lVar2) {
        super(context);
        this.f1366a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903075, this);
        this.f1366a = (ImageView) findViewById(2131296471);
        this.b = (ImageView) findViewById(2131296472);
        this.c = (TextView) findViewById(2131296473);
        this.f = (TextView) findViewById(2131296474);
        this.j = lVar;
        this.k = lVar2;
        if (l == null) {
            l = this.b.getDrawable();
        }
        this.d = (ImageView) findViewById(2131296475);
        this.d.setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.views.MultiplayerFriendItemView.1
            /* JADX WARN: Type inference failed for: r0v2, types: [com.topfreegames.bikerace.views.MultiplayerFriendItemView$1$1] */
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MultiplayerFriendItemView.this.j != null) {
                    new Thread() { // from class: com.topfreegames.bikerace.views.MultiplayerFriendItemView.1.1
                        @Override // java.lang.Thread, java.lang.Runnable
                        public void run() {
                            MultiplayerFriendItemView.this.j.a(MultiplayerFriendItemView.this.g, MultiplayerFriendItemView.this);
                        }
                    }.start();
                }
            }
        });
        this.e = (ImageView) findViewById(2131296476);
        this.e.setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.views.MultiplayerFriendItemView.2
            /* JADX WARN: Type inference failed for: r0v2, types: [com.topfreegames.bikerace.views.MultiplayerFriendItemView$2$1] */
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MultiplayerFriendItemView.this.k != null) {
                    new Thread() { // from class: com.topfreegames.bikerace.views.MultiplayerFriendItemView.2.1
                        @Override // java.lang.Thread, java.lang.Runnable
                        public void run() {
                            MultiplayerFriendItemView.this.k.a(MultiplayerFriendItemView.this.g, MultiplayerFriendItemView.this);
                        }
                    }.start();
                }
            }
        });
    }

    public void a(String str, String str2, String str3, boolean z, m mVar) {
        this.g = str;
        this.h = com.topfreegames.bikerace.m.f.a(str2);
        this.c.setText(String.valueOf(this.h) + " ");
        this.i = str3;
        setPosition(mVar);
        if (z) {
            b();
        } else {
            c();
        }
    }

    public String getMultiplayerItemId() {
        return this.g;
    }

    public String getFriendName() {
        return this.h;
    }

    public String getFriendId() {
        return this.i;
    }

    public void setAvatarImage(Bitmap bitmap) {
        if (bitmap != null) {
            this.b.setImageBitmap(bitmap);
        } else {
            this.b.setImageDrawable(l);
        }
    }

    private void b() {
        this.d.setVisibility(0);
        this.e.setVisibility(8);
        this.f.setText(getResources().getString(2131099753));
    }

    private void c() {
        this.d.setVisibility(8);
        this.e.setVisibility(0);
        this.f.setText(getResources().getString(2131099754));
    }

    private void setPosition(m mVar) {
        switch (a()[mVar.ordinal()]) {
            case 1:
                this.f1366a.setBackgroundResource(2130837822);
                break;
            case 2:
            default:
                this.f1366a.setBackgroundResource(2130837821);
                break;
            case 3:
                this.f1366a.setBackgroundResource(2130837820);
                break;
            case 4:
                this.f1366a.setBackgroundResource(2130837822);
                break;
        }
    }
}
