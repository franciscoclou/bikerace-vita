package com.topfreegames.bikerace.worldcup.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.topfreegames.bikerace.worldcup.l;
import com.topfreegames.bikerace.worldcup.o;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class WorldCupBikeItemView extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.topfreegames.bikerace.c f1469a;
    private ImageView b;
    private TextView c;
    private ImageView d;
    private Button e;
    private Button f;
    private ToggleButton g;
    private TextView h;
    private boolean i;
    private Map<com.topfreegames.bikerace.worldcup.b, WorldCupBikePartView> j;
    private boolean k;

    public WorldCupBikeItemView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    public WorldCupBikeItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k = false;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903110, this);
        this.j = new HashMap();
        this.b = (ImageView) findViewById(2131296791);
        this.c = (TextView) findViewById(2131296792);
        this.e = (Button) findViewById(2131296794);
        this.f = (Button) findViewById(2131296801);
        this.g = (ToggleButton) findViewById(2131296800);
        this.d = (ImageView) findViewById(2131296795);
        this.h = (TextView) findViewById(2131296793);
        this.j.put(com.topfreegames.bikerace.worldcup.b.SUIT, (WorldCupBikePartView) findViewById(2131296796));
        this.j.put(com.topfreegames.bikerace.worldcup.b.HELMET, (WorldCupBikePartView) findViewById(2131296797));
        this.j.put(com.topfreegames.bikerace.worldcup.b.BACK, (WorldCupBikePartView) findViewById(2131296798));
        this.j.put(com.topfreegames.bikerace.worldcup.b.FRONT, (WorldCupBikePartView) findViewById(2131296799));
    }

    public void a(boolean z, boolean z2) {
        this.i = z;
        this.g.setChecked(z2);
        a();
    }

    private void a() {
        if (this.i) {
            Iterator<com.topfreegames.bikerace.worldcup.b> it = this.j.keySet().iterator();
            while (it.hasNext()) {
                this.j.get(it.next()).setVisibility(4);
            }
            this.d.setVisibility(0);
            if (!this.k) {
                this.e.setVisibility(4);
                this.g.setVisibility(0);
                return;
            }
            return;
        }
        com.topfreegames.bikerace.worldcup.a[] aVarArrA = o.a().i().a(this.f1469a);
        com.topfreegames.bikerace.worldcup.a[] aVarArrB = o.a().i().b(this.f1469a);
        this.d.setVisibility(4);
        if (!this.k) {
            this.e.setVisibility(0);
            this.g.setVisibility(4);
        }
        for (int i = 0; i < aVarArrA.length; i++) {
            if (this.j.containsKey(aVarArrA[i].b())) {
                WorldCupBikePartView worldCupBikePartView = this.j.get(aVarArrA[i].b());
                worldCupBikePartView.a(aVarArrA[i], true);
                worldCupBikePartView.a();
            }
        }
        for (int i2 = 0; i2 < aVarArrB.length; i2++) {
            WorldCupBikePartView worldCupBikePartView2 = this.j.get(aVarArrB[i2].b());
            worldCupBikePartView2.a(aVarArrB[i2], false);
            worldCupBikePartView2.a();
        }
    }

    public void a(com.topfreegames.bikerace.c cVar, View.OnClickListener onClickListener) {
        this.f1469a = cVar;
        this.i = false;
        this.k = true;
        this.g.setVisibility(4);
        this.e.setVisibility(4);
        this.f.setVisibility(0);
        this.f.setOnClickListener(onClickListener);
        this.b.setImageResource(l.a(this.f1469a));
        this.c.setText(l.b(getContext(), this.f1469a));
        this.d.setImageResource(l.b(cVar));
        this.h.setText(l.c(getContext(), this.f1469a));
        a();
    }

    public void a(com.topfreegames.bikerace.c cVar, boolean z, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        this.f1469a = cVar;
        this.i = z;
        this.g.setOnClickListener(onClickListener);
        this.e.setOnClickListener(onClickListener2);
        this.b.setImageResource(l.a(this.f1469a));
        this.c.setText(l.b(getContext(), this.f1469a));
        this.d.setImageResource(l.b(cVar));
        this.h.setText(l.c(getContext(), this.f1469a));
    }

    public com.topfreegames.bikerace.c getBikeType() {
        return this.f1469a;
    }
}
