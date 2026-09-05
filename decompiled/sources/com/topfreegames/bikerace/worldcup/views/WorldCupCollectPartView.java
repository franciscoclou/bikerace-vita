package com.topfreegames.bikerace.worldcup.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.topfreegames.bikerace.worldcup.l;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class WorldCupCollectPartView extends LinearLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private TextView f1471a;
    private TextView b;
    private ImageView c;
    private ImageView d;
    private ImageView[] e;
    private Button f;
    private View g;
    private TextView h;
    private Context i;
    private com.topfreegames.bikerace.worldcup.a j;

    public WorldCupCollectPartView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.e = new ImageView[5];
    }

    public WorldCupCollectPartView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new ImageView[5];
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903111, this);
        this.i = context;
        this.f1471a = (TextView) findViewById(2131296803);
        this.b = (TextView) findViewById(2131296804);
        this.c = (ImageView) findViewById(2131296811);
        this.d = (ImageView) findViewById(2131296802);
        this.f = (Button) findViewById(2131296812);
        this.g = findViewById(2131296813);
        this.e[0] = (ImageView) findViewById(2131296806);
        this.e[1] = (ImageView) findViewById(2131296807);
        this.e[2] = (ImageView) findViewById(2131296808);
        this.e[3] = (ImageView) findViewById(2131296809);
        this.e[4] = (ImageView) findViewById(2131296810);
        this.h = (TextView) findViewById(2131296814);
    }

    public void setListener(View.OnClickListener onClickListener) {
        this.f.setOnClickListener(onClickListener);
        this.g.setOnClickListener(onClickListener);
    }

    public void setCollectedPart(com.topfreegames.bikerace.worldcup.a aVar) {
        setBikePart(aVar);
        this.g.setVisibility(8);
        this.f.setVisibility(0);
    }

    public void a(int i, com.topfreegames.bikerace.worldcup.a aVar) {
        setBikePart(aVar);
        this.h.setText(getContext().getResources().getString(2131100112, Integer.valueOf(i)));
        this.g.setVisibility(0);
        this.f.setVisibility(8);
    }

    private void setBikePart(com.topfreegames.bikerace.worldcup.a aVar) {
        this.j = aVar;
        int iC = aVar.c();
        int i = 0;
        while (i < this.e.length) {
            this.e[i].setVisibility(iC > i ? 0 : 8);
            i++;
        }
        this.d.setImageResource(l.a(aVar.a()));
        this.f1471a.setText(l.b(this.i, aVar.a()));
        this.c.setImageResource(l.a(aVar, true));
        this.b.setText(l.a(this.i, aVar));
    }

    public com.topfreegames.bikerace.worldcup.a getBikePart() {
        return this.j;
    }

    public d getCollectType() {
        return this.g.getVisibility() == 0 ? d.COIN : d.PART;
    }
}
