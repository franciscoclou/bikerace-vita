package com.b.a;

import android.content.DialogInterface;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class ax implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ aw f329a;

    ax(aw awVar) {
        this.f329a = awVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f329a.f328a.a(true);
        dialogInterface.dismiss();
    }
}
