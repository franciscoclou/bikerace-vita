package com.applovin.adview;

import android.content.DialogInterface;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class d implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ AppLovinConfirmationActivity f162a;

    d(AppLovinConfirmationActivity appLovinConfirmationActivity) {
        this.f162a = appLovinConfirmationActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.f162a.finish();
    }
}
