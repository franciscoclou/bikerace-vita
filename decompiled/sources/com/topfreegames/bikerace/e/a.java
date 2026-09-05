package com.topfreegames.bikerace.e;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;

/* JADX INFO: compiled from: BaseDialog.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class a extends Dialog {
    public a(Context context, int i) {
        super(context, i);
    }

    protected final void a(Context context, View view) {
        com.topfreegames.bikerace.activities.n.a(getContext(), view);
    }

    @Override // android.app.Dialog
    public void show() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(getWindow().getAttributes());
        layoutParams.width = -1;
        layoutParams.height = -1;
        getWindow().setAttributes(layoutParams);
        try {
            super.show();
        } catch (Exception e) {
        }
    }
}
