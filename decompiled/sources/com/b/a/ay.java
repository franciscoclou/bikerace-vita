package com.b.a;

import android.content.DialogInterface;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class ay implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ aw f330a;

    ay(aw awVar) {
        this.f330a = awVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f330a.f328a.a(false);
        dialogInterface.dismiss();
    }
}
