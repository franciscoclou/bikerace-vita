package com.topfreegames.e;

import java.util.Comparator;

/* JADX INFO: compiled from: TopFacebookUserComparator.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class m implements Comparator<l> {
    @Override // java.util.Comparator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(l lVar, l lVar2) {
        return com.topfreegames.bikerace.m.f.a(lVar.b()).compareTo(com.topfreegames.bikerace.m.f.a(lVar2.b()));
    }
}
