package com.topfreegames.e.b;

import android.os.Bundle;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.RequestAsyncTask;
import com.facebook.Session;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: compiled from: TopFacebookSeveralUsersInfoRequestHandler.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class h extends f {
    private Dictionary<String, com.topfreegames.e.l> b;
    private j f;
    private boolean g;
    private Timer h;
    private long i;
    private int d = 0;
    private int e = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Dictionary<String, com.topfreegames.e.l> f1527a = new Hashtable();
    private Dictionary<String, a> c = new Hashtable();

    public h(j jVar) {
        this.f = jVar;
    }

    public void a(List<String> list, boolean z, Dictionary<String, com.topfreegames.e.l> dictionary, long j) {
        i iVar = null;
        this.i = j;
        this.h = new Timer();
        this.h.schedule(new TimerTask() { // from class: com.topfreegames.e.b.h.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                synchronized (this) {
                    h.this.h = null;
                    h.this.a(true);
                }
            }
        }, j);
        this.g = z;
        this.b = dictionary;
        if (this.b != null) {
            Enumeration<String> enumerationKeys = this.b.keys();
            while (enumerationKeys.hasMoreElements()) {
                String strNextElement = enumerationKeys.nextElement();
                this.f1527a.put(strNextElement, this.b.get(strNextElement));
            }
        }
        Session activeSession = Session.getActiveSession();
        if (activeSession != null && list != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT uid, name FROM user WHERE ");
            for (int i = 0; i < list.size(); i++) {
                String str = list.get(i);
                if (i != 0) {
                    sb.append(" OR ");
                }
                sb.append("uid = ");
                sb.append(str);
            }
            Bundle bundle = new Bundle();
            bundle.putString("method", "fql.query");
            bundle.putString("query", sb.toString());
            new RequestAsyncTask(new Request(activeSession, null, bundle, HttpMethod.GET, new i(this, iVar))).execute(new Void[0]);
            return;
        }
        a(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        synchronized (this) {
            if (this.h != null) {
                this.h.cancel();
                this.h = null;
            }
            if (this.f != null) {
                this.f.a(this.f1527a, this, z);
            }
        }
    }

    @Override // com.topfreegames.e.b.f
    public void d() {
        this.f = null;
    }
}
