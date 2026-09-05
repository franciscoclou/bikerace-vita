package com.heyzap.internal;

import android.app.ProgressDialog;

/* JADX INFO: compiled from: HeyzapProgressDialog.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class j extends ProgressDialog {
    @Override // android.app.Dialog
    public void show() {
        try {
            super.show();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override // android.app.Dialog
    public void hide() {
        try {
            super.hide();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        try {
            super.dismiss();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
