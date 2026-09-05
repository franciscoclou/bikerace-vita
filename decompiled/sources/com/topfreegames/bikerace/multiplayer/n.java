package com.topfreegames.bikerace.multiplayer;

import java.util.Comparator;

/* JADX INFO: compiled from: MultiplayerDataComparator.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class n implements Comparator<l> {
    @Override // java.util.Comparator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(l lVar, l lVar2) {
        boolean zU = lVar.u();
        boolean zU2 = lVar2.u();
        if (zU && !zU2) {
            return -1;
        }
        if (!zU && zU2) {
            return 1;
        }
        boolean zC = lVar.c();
        boolean zC2 = lVar2.c();
        if (zC && !zC2) {
            return -1;
        }
        if (!zC && zC2) {
            return 1;
        }
        int iP = lVar.p() + lVar.j();
        int iP2 = lVar2.p() + lVar2.j();
        if (iP > iP2) {
            return -1;
        }
        if (iP < iP2) {
            return 1;
        }
        boolean zStartsWith = lVar.f().startsWith("b");
        boolean zStartsWith2 = lVar2.f().startsWith("b");
        if (!zStartsWith && zStartsWith2) {
            return -1;
        }
        if (!zStartsWith || zStartsWith2) {
            return lVar.g().compareTo(lVar2.g());
        }
        return 1;
    }
}
