package com.topfreegames.e.b;

import android.os.Bundle;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Session;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: compiled from: TopFacebookUserFriendsRequestHandler.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class k extends f {
    private static /* synthetic */ int[] j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Timer f1530a;
    private m f;
    private com.topfreegames.e.e g;
    private boolean h;
    private long i;
    private int d = 0;
    private int e = 0;
    private Dictionary<String, com.topfreegames.e.l> b = new Hashtable();
    private Dictionary<String, a> c = new Hashtable();

    static /* synthetic */ int[] a() {
        int[] iArr = j;
        if (iArr == null) {
            iArr = new int[com.topfreegames.e.e.valuesCustom().length];
            try {
                iArr[com.topfreegames.e.e.ALL_FRIENDS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[com.topfreegames.e.e.FRIENDS_DONT_HAVE_APP.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[com.topfreegames.e.e.FRIENDS_HAVE_APP.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            j = iArr;
        }
        return iArr;
    }

    public k(m mVar) {
        this.f = mVar;
    }

    public void a(String str, boolean z, com.topfreegames.e.e eVar, long j2) {
        this.i = j2;
        this.d = 0;
        this.e = 0;
        this.g = eVar;
        this.h = z;
        boolean z2 = true;
        final Session activeSession = Session.getActiveSession();
        if (activeSession != null) {
            final Bundle bundle = new Bundle();
            switch (a()[this.g.ordinal()]) {
                case 1:
                    bundle.putString("fields", "friends");
                    break;
                case 2:
                    bundle.putString("q", "SELECT uid, name, is_app_user FROM user WHERE uid in (SELECT uid2 FROM friend WHERE uid1 = me()) AND is_app_user = 1");
                    break;
                case 3:
                    bundle.putString("q", "SELECT uid, name, is_app_user FROM user WHERE uid in (SELECT uid2 FROM friend WHERE uid1 = me()) AND is_app_user = 0");
                    break;
                default:
                    a(false);
                    bundle = null;
                    z2 = false;
                    break;
            }
            if (bundle != null) {
                new Thread(new Runnable() { // from class: com.topfreegames.e.b.k.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            Request.executeAndWait(new Request(activeSession, "/fql", bundle, HttpMethod.GET, new l(k.this, null)));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } else {
            a(false);
        }
        if (z2) {
            this.f1530a = new Timer();
            this.f1530a.schedule(new TimerTask() { // from class: com.topfreegames.e.b.k.2
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        k.this.f1530a = null;
                        k.this.a(true);
                    }
                }
            }, j2);
        }
    }

    void a(boolean z) {
        synchronized (this) {
            if (this.f1530a != null) {
                this.f1530a.cancel();
                this.f1530a = null;
            }
            if (this.f != null) {
                this.f.a(this.b, this, this.g, z);
            }
        }
    }

    @Override // com.topfreegames.e.b.f
    public void d() {
        this.f = null;
    }
}
