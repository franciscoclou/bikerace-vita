package com.topfreegames.bikerace.e;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

/* JADX INFO: compiled from: FindDialog.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class k extends a {
    public k(final Context context, final String str, final l lVar, final l lVar2, final l lVar3, final l lVar4, final m mVar) {
        super(context, 2131492932);
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        if (str == null) {
            throw new IllegalArgumentException("Link cannot be null!");
        }
        View viewInflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903073, (ViewGroup) null);
        a(context, viewInflate);
        final TextView textView = (TextView) viewInflate.findViewById(2131296441);
        textView.setFocusable(true);
        textView.setFocusableInTouchMode(true);
        textView.setText(str);
        textView.requestFocus();
        View viewFindViewById = viewInflate.findViewById(2131296442);
        View viewFindViewById2 = viewInflate.findViewById(2131296444);
        View viewFindViewById3 = viewInflate.findViewById(2131296446);
        View viewFindViewById4 = viewInflate.findViewById(2131296448);
        View viewFindViewById5 = viewInflate.findViewById(2131296451);
        final EditText editText = (EditText) viewInflate.findViewById(2131296450);
        editText.clearFocus();
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.topfreegames.bikerace.e.k.1
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView2, int i, KeyEvent keyEvent) {
                if ((i == 0 || i == 6) && textView2.getText().length() > 0 && mVar != null) {
                    if (!mVar.a(textView2.getText().toString())) {
                        textView2.setError(context.getResources().getString(2131099892));
                        return true;
                    }
                    k.this.a(context, editText, textView);
                    return true;
                }
                return true;
            }
        });
        viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.e.k.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (lVar != null) {
                    lVar.a(str);
                }
                k.this.a(context, editText, textView);
            }
        });
        viewFindViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.e.k.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (lVar2 != null) {
                    lVar2.a(str);
                }
                k.this.a(context, editText, textView);
            }
        });
        viewFindViewById3.setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.e.k.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (lVar3 != null) {
                    lVar3.a(str);
                }
                k.this.a(context, editText, textView);
            }
        });
        viewFindViewById4.setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.e.k.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (lVar4 != null) {
                    lVar4.a(str);
                }
                k.this.a(context, editText, textView);
            }
        });
        viewFindViewById5.setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.e.k.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                k.this.a(context, editText, textView);
            }
        });
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setContentView(viewInflate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, EditText editText, View view) {
        ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(editText.getWindowToken(), 0);
        editText.setText("");
        editText.clearFocus();
        view.requestFocus();
        cancel();
    }
}
