package com.topfreegames.bikerace.multiplayer;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class MultiplayerNotificationService extends Service {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final z f1297a = new z(this);
    private Handler b = null;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f1297a;
    }

    @Override // android.app.Service
    public void onCreate() {
        this.b = new Handler();
        super.onCreate();
    }

    public void a(final String str) {
        if (this.b != null) {
            this.b.post(new Runnable() { // from class: com.topfreegames.bikerace.multiplayer.MultiplayerNotificationService.1
                @Override // java.lang.Runnable
                public void run() {
                    Toast.makeText(MultiplayerNotificationService.this.getApplicationContext(), str, 1).show();
                }
            });
        }
    }
}
