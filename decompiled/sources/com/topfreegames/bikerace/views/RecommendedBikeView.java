package com.topfreegames.bikerace.views;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class RecommendedBikeView extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ImageView f1379a;
    private ImageView b;
    private TextView c;
    private com.topfreegames.bikerace.c d;
    private RelativeLayout e;
    private Context f;

    public RecommendedBikeView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    public RecommendedBikeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = com.topfreegames.bikerace.c.REGULAR;
        this.f = context;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903086, this);
        this.e = (RelativeLayout) findViewById(2131296623);
        this.c = (TextView) findViewById(2131296628);
        this.f1379a = (ImageView) findViewById(2131296626);
        this.b = (ImageView) findViewById(2131296627);
    }

    public RecommendedBikeView(com.topfreegames.bikerace.c cVar, Context context) {
        this(context, (AttributeSet) null);
        setBikeType(cVar);
    }

    public void setBikeType(com.topfreegames.bikerace.c cVar) {
        if (cVar != null) {
            this.d = cVar;
            a();
        }
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.f1379a.setOnClickListener(onClickListener);
        this.e.setOnClickListener(onClickListener);
    }

    private void a() {
        this.c.setText(getBikeName());
        this.b.setImageResource(getBikeImageResourceId());
    }

    public com.topfreegames.bikerace.c getBikeType() {
        return this.d;
    }

    private int getBikeImageResourceId() {
        if (this.d == com.topfreegames.bikerace.c.SUPER) {
            return 2130837533;
        }
        if (this.d != com.topfreegames.bikerace.c.GHOST) {
            return 2130837535;
        }
        return 2130837515;
    }

    private String getBikeName() {
        Resources resources = this.f.getResources();
        String string = "";
        if (this.d == com.topfreegames.bikerace.c.SUPER) {
            string = resources.getString(2131099770);
        } else if (this.d == com.topfreegames.bikerace.c.GHOST) {
            string = resources.getString(2131099773);
        } else if (this.d == com.topfreegames.bikerace.c.ULTRA) {
            string = resources.getString(2131099802);
        }
        return " " + string + " ";
    }
}
