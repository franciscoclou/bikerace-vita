package com.b.a;

import android.content.DialogInterface;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class az implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ aw f331a;

    az(aw awVar) {
        this.f331a = awVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        d dVar = this.f331a.b;
        d.a(true);
        this.f331a.f328a.a(true);
        dialogInterface.dismiss();
    }
}
