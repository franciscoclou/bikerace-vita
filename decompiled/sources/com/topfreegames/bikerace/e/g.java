package com.topfreegames.bikerace.e;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/* JADX INFO: compiled from: EasterEggLocationDialog.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class g extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private h f1191a;
    private ListView b;

    public g(Context context) {
        super(context, 2131492932);
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        View viewInflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(2130903060, (ViewGroup) null);
        viewInflate.findViewById(2131296334).setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.e.g.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                g.this.cancel();
            }
        });
        this.f1191a = new h(this, getContext(), 0, a());
        this.b = (ListView) viewInflate.findViewById(2131296337);
        this.b.setClickable(false);
        this.b.setFocusable(false);
        this.b.setDividerHeight(0);
        this.b.setAdapter((ListAdapter) this.f1191a);
        a(getContext(), viewInflate);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setContentView(viewInflate);
    }

    private i[] a() {
        Context context = getContext();
        return new i[]{new i(1, 5, 1, context), new i(2, 6, 2, context), new i(3, 8, 3, context), new i(4, 6, 4, context), new i(5, 4, 5, context), new i(6, 3, 6, context), new i(7, 8, 7, context), new i(8, 8, 8, context), new i(9, 8, 9, context), new i(10, 3, 10, context), new i(11, 6, 11, context), new i(12, 4, 12, context), new i(13, 8, 17, context), new i(14, 5, 18, context), new i(18, 6, 13, context), new i(17, 7, 14, context), new i(16, 8, 15, context), new i(15, 4, 16, context), new i(19, 1, 19, context), new i(19, 2, 20, context), new i(19, 3, 21, context), new i(19, 4, 22, context), new i(19, 5, 23, context), new i(19, 6, 24, context), new i(19, 7, 25, context), new i(19, 8, 26, context)};
    }
}
