package com.topfreegames.bikerace.multiplayer;

import java.util.Comparator;

/* JADX INFO: compiled from: RankingManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ao implements Comparator<an> {
    @Override // java.util.Comparator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(an anVar, an anVar2) {
        if (anVar.c > anVar2.c) {
            return -1;
        }
        if (anVar.c < anVar2.c) {
            return 1;
        }
        return com.topfreegames.bikerace.m.f.a(anVar.f1309a).compareTo(com.topfreegames.bikerace.m.f.a(anVar2.f1309a));
    }
}
