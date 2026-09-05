package com.applovin.impl.adview;

import android.util.Log;
import android.view.View;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class aa implements View.OnLongClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ y f220a;

    aa(y yVar) {
        this.f220a = yVar;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        Log.d("AdWebView", "Received a LongClick event.");
        return true;
    }
}
