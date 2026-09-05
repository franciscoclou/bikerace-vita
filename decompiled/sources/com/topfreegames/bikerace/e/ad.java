package com.topfreegames.bikerace.e;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/* JADX INFO: compiled from: WorldCupCurrencyDialog.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ad extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private View.OnClickListener f1178a;
    private View.OnClickListener b;
    private View.OnClickListener c;

    public ad(Context context, com.topfreegames.bikerace.worldcup.p pVar, View.OnClickListener onClickListener) {
        View viewInflate;
        super(context, 2131492932);
        this.b = new View.OnClickListener() { // from class: com.topfreegames.bikerace.e.ad.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ad.this.f1178a != null) {
                    ad.this.f1178a.onClick(view);
                }
                ad.this.dismiss();
            }
        };
        this.c = new View.OnClickListener() { // from class: com.topfreegames.bikerace.e.ad.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ad.this.dismiss();
            }
        };
        this.f1178a = onClickListener;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        if (pVar == com.topfreegames.bikerace.worldcup.p.ORDINARY) {
            viewInflate = layoutInflater.inflate(2130903115, (ViewGroup) null);
        } else {
            viewInflate = layoutInflater.inflate(2130903114, (ViewGroup) null);
        }
        ((Button) viewInflate.findViewById(2131296824)).setOnClickListener(this.c);
        ((Button) viewInflate.findViewById(2131296825)).setOnClickListener(this.b);
        a(context, viewInflate);
        setContentView(viewInflate);
    }
}
