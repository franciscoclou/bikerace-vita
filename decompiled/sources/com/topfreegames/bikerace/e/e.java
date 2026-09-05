package com.topfreegames.bikerace.e;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.topfreegames.bikerace.activities.BikeRaceApplication;

/* JADX INFO: compiled from: CustomLevelsOfferDialog.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class e extends a {
    public e(Context context, final f fVar, final f fVar2, final f fVar3) {
        super(context, 2131492932);
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        View viewInflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903059, (ViewGroup) null);
        a(context, viewInflate);
        viewInflate.findViewById(2131296327).setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.e.e.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (fVar != null) {
                    fVar.a();
                }
                e.this.cancel();
            }
        });
        viewInflate.findViewById(2131296329).setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.e.e.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (fVar2 != null) {
                    fVar2.a();
                }
                e.this.cancel();
            }
        });
        viewInflate.findViewById(2131296331).setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.e.e.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (fVar3 != null) {
                    fVar3.a();
                }
                e.this.cancel();
            }
        });
        viewInflate.findViewById(2131296333).setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.e.e.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                e.this.cancel();
            }
        });
        ((TextView) viewInflate.findViewById(2131296326)).setText(String.format(context.getString(2131099918), Integer.valueOf(((BikeRaceApplication) context.getApplicationContext()).a(false).y())));
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setContentView(viewInflate);
    }
}
