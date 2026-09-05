package com.topfreegames.bikerace.worldcup.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.topfreegames.bikerace.worldcup.l;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class WorldCupBikePartView extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1470a;
    private boolean b;
    private com.topfreegames.bikerace.worldcup.a c;
    private TextView d;
    private RelativeLayout e;
    private ImageView[] f;
    private ImageView g;
    private ImageView h;

    public WorldCupBikePartView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    public WorldCupBikePartView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1470a = 0;
        this.f = new ImageView[5];
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903112, this);
        this.e = (RelativeLayout) findViewById(2131296815);
        this.d = (TextView) findViewById(2131296804);
        this.f[0] = (ImageView) findViewById(2131296806);
        this.f[1] = (ImageView) findViewById(2131296807);
        this.f[2] = (ImageView) findViewById(2131296808);
        this.f[3] = (ImageView) findViewById(2131296809);
        this.f[4] = (ImageView) findViewById(2131296810);
        this.g = (ImageView) findViewById(2131296816);
        this.h = (ImageView) findViewById(2131296811);
    }

    public void a(com.topfreegames.bikerace.worldcup.a aVar, boolean z) {
        this.c = aVar;
        this.f1470a = aVar.c();
        this.b = z;
    }

    public void a() {
        if (this.c != null) {
            if (this.b) {
                this.g.setVisibility(8);
                this.e.setBackgroundResource(2130837938);
            } else {
                this.g.setVisibility(0);
                this.e.setBackgroundResource(2130837937);
            }
            this.d.setText(l.a(getContext(), this.c));
            this.h.setImageResource(l.a(this.c, this.b));
            b();
        }
    }

    private void b() {
        int length = this.f.length - 1;
        for (int i = length; i >= 0; i--) {
            this.f[i].setVisibility(length - i < this.f1470a ? 0 : 4);
        }
    }
}
