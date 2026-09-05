package com.topfreegames.bikerace.views;

import java.util.Comparator;

/* JADX INFO: compiled from: UserLevelItemView.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class w implements Comparator<v> {
    @Override // java.util.Comparator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(v vVar, v vVar2) {
        if (vVar.e() && !vVar2.e()) {
            return -1;
        }
        if (!vVar.e() && vVar2.e()) {
            return 1;
        }
        if (vVar.e() && vVar2.e()) {
            return 0;
        }
        if (!vVar.c() || vVar2.c()) {
            return (vVar.c() || !vVar2.c()) ? 0 : 1;
        }
        return -1;
    }
}
