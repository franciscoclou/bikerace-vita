package com.topfreegames.bikerace.e;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/* JADX INFO: compiled from: GenericInputDialog.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class q extends a {
    public q(Context context, String str, String str2, String str3, r rVar, r rVar2) {
        super(context, 2131492932);
        View viewInflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903064, (ViewGroup) null);
        a(context, viewInflate);
        ((TextView) viewInflate.findViewById(2131296366)).setText(str);
        View viewFindViewById = viewInflate.findViewById(2131296370);
        View viewFindViewById2 = viewInflate.findViewById(2131296372);
        EditText editText = (EditText) viewInflate.findViewById(2131296367);
        boolean z = str2 != null;
        boolean z2 = str3 != null;
        if (z && z2) {
            viewFindViewById.setVisibility(4);
            viewFindViewById2.setVisibility(0);
            Button button = (Button) viewInflate.findViewById(2131296373);
            Button button2 = (Button) viewInflate.findViewById(2131296374);
            a(button, str2, editText, rVar);
            a(button2, str3, editText, rVar2);
        } else {
            viewFindViewById.setVisibility(0);
            viewFindViewById2.setVisibility(4);
            Button button3 = (Button) viewInflate.findViewById(2131296371);
            if (z) {
                a(button3, str2, editText, rVar);
            } else if (z2) {
                a(button3, str3, editText, rVar2);
            }
        }
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setContentView(viewInflate);
    }

    private void a(Button button, String str, final EditText editText, final r rVar) {
        button.setText(str);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.e.q.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                q.this.dismiss();
                final r rVar2 = rVar;
                final EditText editText2 = editText;
                new Thread(new Runnable() { // from class: com.topfreegames.bikerace.e.q.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        q.this.dismiss();
                        if (rVar2 != null) {
                            rVar2.a(editText2.getText().toString());
                        }
                    }
                }).start();
            }
        });
    }
}
