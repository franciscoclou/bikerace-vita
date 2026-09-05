package com.topfreegames.bikerace.worldcup.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.topfreegames.bikerace.worldcup.l;
import com.topfreegames.bikerace.worldcup.o;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class WorldCupShopTabView extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ImageView f1475a;
    private ImageView b;
    private ImageView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private View g;
    private View h;
    private View i;
    private boolean j;

    public WorldCupShopTabView(Context context) {
        super(context);
        a();
    }

    public WorldCupShopTabView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public WorldCupShopTabView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(2130903123, this);
        this.f1475a = (ImageView) findViewById(2131296911);
        this.b = (ImageView) findViewById(2131296913);
        this.d = (TextView) findViewById(2131296914);
        this.c = (ImageView) findViewById(2131296917);
        this.e = (TextView) findViewById(2131296912);
        this.g = findViewById(2131296915);
        this.h = findViewById(2131296910);
        this.i = findViewById(2131296918);
        this.f = (TextView) findViewById(2131296916);
    }

    public void setup(e eVar) {
        this.b.setImageResource(l.a(eVar));
        this.d.setText(l.a(getContext(), eVar));
        a((com.topfreegames.bikerace.c) null);
        a(0);
    }

    @Override // android.view.View
    public void setSelected(boolean z) {
        if (super.isEnabled()) {
            this.j = z;
            if (z) {
                this.f1475a.setImageResource(2130837936);
                this.h.setVisibility(8);
                this.i.setVisibility(0);
            } else {
                this.f1475a.setImageResource(2130837934);
                this.h.setVisibility(0);
                this.i.setVisibility(8);
            }
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (z) {
            setSelected(this.j);
        } else {
            this.f1475a.setImageResource(2130837935);
        }
    }

    public void a(final int i) {
        this.e.post(new Runnable() { // from class: com.topfreegames.bikerace.worldcup.views.WorldCupShopTabView.1
            @Override // java.lang.Runnable
            public void run() {
                if (i <= 0 || !o.a().t()) {
                    WorldCupShopTabView.this.e.setVisibility(8);
                } else {
                    WorldCupShopTabView.this.e.setVisibility(0);
                    WorldCupShopTabView.this.e.setText(Integer.toString(i));
                }
            }
        });
    }

    public void a(final com.topfreegames.bikerace.c cVar) {
        this.g.post(new Runnable() { // from class: com.topfreegames.bikerace.worldcup.views.WorldCupShopTabView.2
            @Override // java.lang.Runnable
            public void run() {
                if (cVar == null || !o.a().t()) {
                    WorldCupShopTabView.this.g.setVisibility(4);
                    return;
                }
                long time = com.topfreegames.c.a.a().getTime();
                WorldCupShopTabView.this.g.setVisibility(0);
                WorldCupShopTabView.this.c.setImageResource(l.a(cVar));
                WorldCupShopTabView.this.f.setText(String.valueOf(l.c(time)) + WorldCupShopTabView.this.getContext().getResources().getString(2131100091));
            }
        });
    }
}
