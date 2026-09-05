package com.topfreegames.bikerace.worldcup.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.topfreegames.bikerace.worldcup.l;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class WorldCupSlotItemView extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ImageView f1478a;
    private TextView b;
    private TextView c;
    private ImageView d;
    private ImageView[] e;

    public WorldCupSlotItemView(Context context) {
        super(context);
        this.e = new ImageView[5];
        a();
    }

    public WorldCupSlotItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = new ImageView[5];
        a();
    }

    private void a() {
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(2130903119, this);
        this.f1478a = (ImageView) findViewById(2131296844);
        this.b = (TextView) findViewById(2131296845);
        this.d = (ImageView) findViewById(2131296853);
        this.c = (TextView) findViewById(2131296846);
        this.e[0] = (ImageView) findViewById(2131296848);
        this.e[1] = (ImageView) findViewById(2131296849);
        this.e[2] = (ImageView) findViewById(2131296850);
        this.e[3] = (ImageView) findViewById(2131296851);
        this.e[4] = (ImageView) findViewById(2131296852);
    }

    public void setup(com.topfreegames.bikerace.worldcup.a aVar) {
        Context context = getContext();
        com.topfreegames.bikerace.c cVarA = aVar.a();
        this.c.setText(l.a(context, aVar));
        this.d.setImageResource(l.a(aVar, true));
        this.f1478a.setImageResource(l.a(cVarA));
        this.b.setText(l.b(context, cVarA));
        a(aVar.c());
    }

    private void a(int i) {
        int i2 = 1;
        while (true) {
            int i3 = i2;
            if (i3 <= 5) {
                this.e[i3 - 1].setVisibility(i3 <= i ? 0 : 8);
                i2 = i3 + 1;
            } else {
                return;
            }
        }
    }
}
