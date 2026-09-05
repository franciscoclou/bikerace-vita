package com.topfreegames.e.b.a;

import android.app.Activity;
import android.os.Bundle;
import com.facebook.FacebookException;
import com.facebook.Session;
import com.facebook.widget.WebDialog;
import com.topfreegames.bikerace.ap;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: TopFacebookAppRequestCreateUserUserHandler.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class e extends com.topfreegames.e.b.f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private d f1510a;
    private String b;
    private String c;
    private String d;
    private WeakReference<Activity> e;

    public e(d dVar, String str, String str2, String str3, long j, Activity activity) {
        this.f1510a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        if (str == null) {
            throw new IllegalArgumentException("UserId cannot be null!");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("Message cannot be null!");
        }
        if (j < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative!");
        }
        this.f1510a = dVar;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = new WeakReference<>(activity);
    }

    public void a() {
        try {
            Activity activity = this.e.get();
            Session activeSession = Session.getActiveSession();
            if (activeSession != null && activity != null) {
                final WebDialog.RequestsDialogBuilder requestsDialogBuilder = new WebDialog.RequestsDialogBuilder(activity, activeSession);
                requestsDialogBuilder.setMessage(this.c);
                requestsDialogBuilder.setTo(this.b);
                if (this.d != null) {
                    requestsDialogBuilder.setData(this.d);
                }
                requestsDialogBuilder.setOnCompleteListener(new WebDialog.OnCompleteListener() { // from class: com.topfreegames.e.b.a.e.1
                    @Override // com.facebook.widget.WebDialog.OnCompleteListener
                    public void onComplete(Bundle bundle, FacebookException facebookException) {
                        e.this.a(facebookException == null);
                    }
                });
                activity.runOnUiThread(new Runnable() { // from class: com.topfreegames.e.b.a.e.2
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            requestsDialogBuilder.build().show();
                        } catch (Exception e) {
                            if (ap.d()) {
                                e.printStackTrace();
                            }
                            e.this.a(false);
                        }
                    }
                });
            }
        } catch (Exception e) {
            a(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        if (this.f1510a != null) {
            this.f1510a.a(z);
        }
    }

    @Override // com.topfreegames.e.b.f
    public void d() {
        this.f1510a = null;
    }
}
