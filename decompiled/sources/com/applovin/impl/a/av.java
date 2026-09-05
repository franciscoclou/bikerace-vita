package com.applovin.impl.a;

import java.util.Comparator;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class av implements Comparator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ au f193a;

    av(au auVar) {
        this.f193a = auVar;
    }

    @Override // java.util.Comparator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(ax axVar, ax axVar2) {
        if (axVar.d > axVar2.d) {
            return -1;
        }
        return axVar.d < axVar2.d ? 1 : 0;
    }
}
