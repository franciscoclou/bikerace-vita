package com.topfreegames.e.a;

import java.util.Comparator;

/* JADX INFO: compiled from: TopFacebookManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class e implements Comparator<com.topfreegames.e.l> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ a f1501a;

    private e(a aVar) {
        this.f1501a = aVar;
    }

    /* synthetic */ e(a aVar, e eVar) {
        this(aVar);
    }

    @Override // java.util.Comparator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(com.topfreegames.e.l lVar, com.topfreegames.e.l lVar2) {
        String strB = lVar.b();
        String strB2 = lVar2.b();
        if (strB2 == null) {
            return -1;
        }
        if (strB == null) {
            return 1;
        }
        return strB.compareTo(strB2);
    }
}
