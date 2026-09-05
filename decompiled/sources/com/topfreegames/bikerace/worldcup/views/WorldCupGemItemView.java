package com.topfreegames.bikerace.worldcup.views;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class WorldCupGemItemView extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1474a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private View f;
    private Context g;

    public WorldCupGemItemView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    public WorldCupGemItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1474a = 1;
        a(attributeSet);
        this.g = context;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903118, this);
        this.b = (TextView) findViewById(2131296834);
        this.c = (TextView) findViewById(2131296841);
        this.d = (TextView) findViewById(2131296836);
        this.e = (TextView) findViewById(2131296843);
        this.f = findViewById(2131296842);
        a();
    }

    public void setPurchaseListener(View.OnClickListener onClickListener) {
        setOnClickListener(onClickListener);
    }

    private void a() {
        ImageView imageView;
        if (this.f1474a == 1) {
            imageView = (ImageView) findViewById(2131296840);
        } else if (this.f1474a == 2 || this.f1474a == 3) {
            imageView = (ImageView) findViewById(2131296839);
        } else {
            imageView = (ImageView) findViewById(2131296838);
        }
        imageView.setImageResource(getImageId());
        imageView.setVisibility(0);
        this.b.setText(getTitleText());
    }

    private void a(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, com.topfreegames.b.b.GemView);
        this.f1474a = typedArrayObtainStyledAttributes.getInt(0, 1);
        typedArrayObtainStyledAttributes.recycle();
    }

    private String getTitleText() {
        Resources resources = this.g.getResources();
        String string = resources.getString(2131100061);
        if (this.f1474a == 2) {
            return resources.getString(2131100063);
        }
        if (this.f1474a == 3) {
            return resources.getString(2131100065);
        }
        if (this.f1474a == 4) {
            return resources.getString(2131100067);
        }
        if (this.f1474a == 5) {
            return resources.getString(2131100069);
        }
        if (this.f1474a == 6) {
            return resources.getString(2131100071);
        }
        return string;
    }

    public void setPrice(String str) {
        this.c.setText(str);
    }

    public void setGemValue(int i) {
        this.d.setText(new StringBuilder(String.valueOf(i)).toString());
    }

    public void setPriceInCoins(int i) {
        this.c.setVisibility(8);
        this.e.setText(" " + Integer.toString(i) + " ");
        this.f.setVisibility(0);
    }

    private int getImageId() {
        if (this.f1474a == 2) {
            return 2130838007;
        }
        if (this.f1474a == 3) {
            return 2130838008;
        }
        if (this.f1474a == 4) {
            return 2130838009;
        }
        if (this.f1474a == 5) {
            return 2130838010;
        }
        if (this.f1474a != 6) {
            return 2130838006;
        }
        return 2130838011;
    }
}
