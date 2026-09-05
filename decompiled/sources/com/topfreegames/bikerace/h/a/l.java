package com.topfreegames.bikerace.h.a;

import android.content.Context;
import java.util.ArrayList;

/* JADX INFO: compiled from: UserLevelFactoryUserWorld.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class l {
    public static c[] a(Context context) {
        ArrayList arrayList = new ArrayList();
        q qVarA = q.a();
        int iC = qVarA.c();
        for (int i = 0; i < iC; i++) {
            arrayList.add(qVarA.b(i));
        }
        return (c[]) arrayList.toArray(new c[0]);
    }
}
