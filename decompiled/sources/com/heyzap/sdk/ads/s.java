package com.heyzap.sdk.ads;

import android.content.Context;
import android.view.KeyEvent;
import android.webkit.WebView;

/* JADX INFO: compiled from: InterstitialWebView.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class s extends WebView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ r f797a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(r rVar, Context context) {
        super(context);
        this.f797a = rVar;
        setBackgroundColor(0);
    }

    @Override // android.webkit.WebView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return this.f797a.onKeyDown(i, keyEvent);
    }
}
