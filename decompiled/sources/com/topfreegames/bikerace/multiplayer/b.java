package com.topfreegames.bikerace.multiplayer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.topfreegames.bikerace.bb;
import java.util.ArrayList;

/* JADX INFO: compiled from: FakePokeGenerator.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private o f1310a;
    private Context b;
    private bb c;

    public b(o oVar, Context context, bb bbVar) {
        this.f1310a = oVar;
        this.b = context;
        this.c = bbVar;
    }

    public void a() {
        if (this.f1310a.p()) {
            l[] lVarArr = (l[]) this.f1310a.d().toArray(new l[this.f1310a.d().size()]);
            ArrayList arrayList = new ArrayList();
            for (l lVar : lVarArr) {
                if (a(lVar)) {
                    arrayList.add(lVar);
                }
            }
            if (arrayList.size() > 0) {
                b((l) arrayList.get(com.topfreegames.bikerace.m.h.a(arrayList.size())));
                this.f1310a.q();
            }
        }
    }

    public static boolean a(l lVar) {
        if (lVar == null) {
            return false;
        }
        m mVarT = lVar.t();
        return mVarT == m.READY || mVarT == m.SHOW_RESULT;
    }

    private void b(l lVar) {
        a(lVar.b(), this.c.ax() + System.currentTimeMillis(), "com.topfreegames.bikerace.multiplayer.fakepoke", 856423);
    }

    private void a(String str, long j, String str2, int i) {
        Intent intent = new Intent(str2);
        intent.putExtra("com.topfreegames.bikerace.MultiplayerGameId", str);
        ((AlarmManager) this.b.getSystemService("alarm")).set(0, j, PendingIntent.getBroadcast(this.b, i, intent, 134217728));
    }
}
