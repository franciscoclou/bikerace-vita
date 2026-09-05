package com.topfreegames.bikerace.worldcup.views;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class WorldCupDailyBonusItemView extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f1472a;
    private int b;
    private int c;
    private View.OnClickListener d;
    private LinearLayout e;
    private TextView f;
    private TextView g;
    private Context h;
    private Button i;
    private View.OnClickListener j;

    public WorldCupDailyBonusItemView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    public WorldCupDailyBonusItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1472a = false;
        this.b = 1;
        this.c = -1;
        this.d = null;
        this.j = new View.OnClickListener() { // from class: com.topfreegames.bikerace.worldcup.views.WorldCupDailyBonusItemView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (WorldCupDailyBonusItemView.this.c == WorldCupDailyBonusItemView.this.b) {
                    WorldCupDailyBonusItemView.this.f1472a = true;
                    WorldCupDailyBonusItemView.this.a();
                    if (WorldCupDailyBonusItemView.this.d != null) {
                        WorldCupDailyBonusItemView.this.d.onClick(view);
                    }
                }
            }
        };
        a(attributeSet);
        this.h = context;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        if (this.b == 5) {
            layoutInflater.inflate(2130903116, this);
        } else {
            layoutInflater.inflate(2130903117, this);
        }
        this.e = (LinearLayout) findViewById(2131296828);
        this.f = (TextView) findViewById(2131296829);
        this.g = (TextView) findViewById(2131296832);
        this.i = (Button) findViewById(2131296831);
        this.i.setOnClickListener(this.j);
        setOnClickListener(this.j);
        a();
    }

    private void a(AttributeSet attributeSet) {
        this.b = getContext().obtainStyledAttributes(attributeSet, com.topfreegames.b.b.DailyBonusView).getInt(0, 1);
    }

    public void a() {
        this.e.setBackgroundResource(getBackgroundImageId());
        this.f.setText(getDayTitleText());
        this.g.setText(getDescriptionText());
        if (this.c == this.b && !this.f1472a) {
            this.i.setVisibility(0);
            this.g.setVisibility(8);
        } else {
            this.i.setVisibility(8);
            this.g.setVisibility(0);
        }
    }

    public void setCurrentDay(int i) {
        this.c = i;
        a();
    }

    public void setCollectListener(View.OnClickListener onClickListener) {
        this.d = onClickListener;
    }

    private String getDayTitleText() {
        Resources resources = this.h.getResources();
        String string = resources.getString(2131100074);
        if (this.b == 2) {
            return resources.getString(2131100075);
        }
        if (this.b == 3) {
            return resources.getString(2131100076);
        }
        if (this.b == 4) {
            return resources.getString(2131100077);
        }
        if (this.b == 5) {
            return resources.getString(2131100078);
        }
        return string;
    }

    private String getDescriptionText() {
        Resources resources = this.h.getResources();
        String string = resources.getString(2131100079);
        if (this.c == this.b && !this.f1472a) {
            return "";
        }
        if (this.c + 1 == this.b) {
            return resources.getString(2131100080);
        }
        if (this.c + 2 == this.b) {
            return resources.getString(2131100081);
        }
        if (this.c + 3 == this.b) {
            return resources.getString(2131100082);
        }
        if (this.c + 4 == this.b) {
            return resources.getString(2131100083);
        }
        return string;
    }

    private int getBackgroundImageId() {
        if (this.b == 5) {
            if ((this.f1472a && this.c == this.b) || this.c > this.b) {
                return 2130838013;
            }
            if (this.c == this.b && !this.f1472a) {
                return 2130838078;
            }
            return 2130837954;
        }
        if ((this.f1472a && this.c == this.b) || this.c > this.b) {
            return 2130838012;
        }
        if (this.c == this.b && !this.f1472a) {
            return 2130838077;
        }
        return 2130837953;
    }
}
