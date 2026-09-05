package com.topfreegames.bikerace.e;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/* JADX INFO: compiled from: GenericDialog.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class n extends a {
    public n(Context context, String str, String str2, o oVar) {
        this(context, str, str2, null, oVar, null, false);
    }

    public n(Context context, String str, String str2, String str3, o oVar, o oVar2) {
        this(context, str, str2, str3, oVar, oVar2, false);
    }

    public n(Context context, String str, String str2, String str3, o oVar, o oVar2, boolean z) {
        super(context, 2131492932);
        View viewInflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903062, (ViewGroup) null);
        a(context, viewInflate);
        TextView textView = (TextView) viewInflate.findViewById(2131296343);
        textView.setText(str);
        textView.setGravity(z ? 3 : 17);
        View viewFindViewById = viewInflate.findViewById(2131296346);
        View viewFindViewById2 = viewInflate.findViewById(2131296348);
        boolean z2 = str2 != null;
        boolean z3 = str3 != null;
        if (z2 && z3) {
            viewFindViewById.setVisibility(4);
            viewFindViewById2.setVisibility(0);
            Button button = (Button) viewInflate.findViewById(2131296349);
            Button button2 = (Button) viewInflate.findViewById(2131296350);
            a(button, str2, oVar);
            a(button2, str3, oVar2);
        } else {
            viewFindViewById.setVisibility(0);
            viewFindViewById2.setVisibility(4);
            Button button3 = (Button) viewInflate.findViewById(2131296347);
            if (z2) {
                a(button3, str2, oVar);
            } else if (z3) {
                a(button3, str3, oVar2);
            }
        }
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setContentView(viewInflate);
    }

    private void a(Button button, String str, final o oVar) {
        button.setText(String.valueOf(str) + " ");
        button.setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.e.n.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                n.this.dismiss();
                final o oVar2 = oVar;
                new Thread(new Runnable() { // from class: com.topfreegames.bikerace.e.n.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        n.this.dismiss();
                        if (oVar2 != null) {
                            oVar2.a();
                        }
                    }
                }).start();
            }
        });
    }
}
