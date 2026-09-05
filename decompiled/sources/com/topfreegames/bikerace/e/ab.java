package com.topfreegames.bikerace.e;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/* JADX INFO: compiled from: StartMultiplayerGameDialog.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ab extends Dialog {
    public ab(Context context, final View.OnClickListener onClickListener, final View.OnClickListener onClickListener2, final View.OnClickListener onClickListener3) {
        super(context, 2131492932);
        View viewInflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903083, (ViewGroup) null);
        a(context, viewInflate);
        View viewFindViewById = viewInflate.findViewById(2131296551);
        View viewFindViewById2 = viewInflate.findViewById(2131296552);
        View viewFindViewById3 = viewInflate.findViewById(2131296553);
        View viewFindViewById4 = viewInflate.findViewById(2131296554);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.e.ab.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ab.this.cancel();
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
            }
        });
        viewFindViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.e.ab.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ab.this.cancel();
                if (onClickListener2 != null) {
                    onClickListener2.onClick(view);
                }
            }
        });
        viewFindViewById3.setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.e.ab.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ab.this.cancel();
                if (onClickListener3 != null) {
                    onClickListener3.onClick(view);
                }
            }
        });
        viewFindViewById4.setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.e.ab.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ab.this.cancel();
            }
        });
        setContentView(viewInflate);
    }

    protected final void a(Context context, View view) {
        Typeface typefaceCreateFromAsset = Typeface.createFromAsset(context.getAssets(), context.getResources().getString(2131099651));
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt instanceof TextView) {
                    ((TextView) childAt).setTypeface(typefaceCreateFromAsset);
                } else if (childAt instanceof ViewGroup) {
                    a(context, childAt);
                }
            }
        }
    }
}
