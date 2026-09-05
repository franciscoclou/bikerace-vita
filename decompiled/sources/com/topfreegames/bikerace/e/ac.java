package com.topfreegames.bikerace.e;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/* JADX INFO: compiled from: UserAccountDialog.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ac extends a {
    public ac(Context context, String str, Bitmap bitmap, final View.OnClickListener onClickListener, boolean z) {
        super(context, 2131492932);
        View viewInflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903101, (ViewGroup) null);
        a(context, viewInflate);
        ImageView imageView = (ImageView) viewInflate.findViewById(2131296738);
        TextView textView = (TextView) viewInflate.findViewById(2131296739);
        View viewFindViewById = viewInflate.findViewById(2131296743);
        Button button = (Button) viewInflate.findViewById(2131296742);
        textView.setText(str);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
        if (z) {
            button.setText(context.getResources().getString(2131099699));
        } else {
            button.setText(context.getResources().getString(2131099698));
        }
        button.setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.e.ac.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                onClickListener.onClick(view);
                ac.this.dismiss();
            }
        });
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.e.ac.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ac.this.dismiss();
            }
        });
        setContentView(viewInflate);
    }
}
