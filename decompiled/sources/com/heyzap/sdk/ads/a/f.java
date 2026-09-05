package com.heyzap.sdk.ads.a;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: FramePingManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class f extends ArrayList<e> {
    public org.b.a.a a() {
        org.b.a.a aVar = new org.b.a.a();
        Iterator<e> it = iterator();
        while (it.hasNext()) {
            aVar.a(it.next().a());
        }
        return aVar;
    }
}
