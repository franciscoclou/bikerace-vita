package com.topfreegames.bikerace.views;

import android.graphics.Bitmap;

/* JADX INFO: compiled from: MultiplayerResultView.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class r implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ MultiplayerResultView f1416a;
    private Bitmap b;
    private String c;

    public r(MultiplayerResultView multiplayerResultView, Bitmap bitmap, String str) {
        this.f1416a = multiplayerResultView;
        this.b = null;
        this.c = null;
        this.b = bitmap;
        this.c = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.b != null) {
            if (this.c.equals(this.f1416a.t)) {
                this.f1416a.b.setImageBitmap(this.b);
            } else if (this.c.equals(this.f1416a.u)) {
                this.f1416a.c.setImageBitmap(this.b);
            }
        }
    }
}
