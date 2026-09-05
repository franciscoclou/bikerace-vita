package com.topfreegames.bikerace.multiplayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: RankingManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class am {
    public static List<an> a(o oVar, List<com.topfreegames.e.l> list, Map<String, com.topfreegames.bikerace.b.b> map) {
        ArrayList arrayList = new ArrayList();
        for (com.topfreegames.e.l lVar : list) {
            String strA = lVar.a();
            com.topfreegames.bikerace.b.b bVarB = map.get(strA);
            if (bVarB == null) {
                bVarB = oVar.b(strA);
                map.put(strA, bVarB);
            }
            Integer numB = bVarB.b();
            if (numB == null) {
                numB = 0;
            }
            arrayList.add(new an(bVarB.c(), lVar.b(), numB.intValue()));
        }
        String strG = oVar.g();
        com.topfreegames.bikerace.b.b bVarB2 = map.get(strG);
        if (bVarB2 == null) {
            bVarB2 = oVar.b(strG);
            map.put(strG, bVarB2);
        }
        Integer numB2 = bVarB2.b();
        if (numB2 == null) {
            numB2 = 0;
        }
        arrayList.add(new an(strG, oVar.h(), numB2.intValue()));
        Collections.sort(arrayList, new ao());
        return arrayList;
    }

    public static List<an> a(o oVar, Map<String, com.topfreegames.bikerace.b.b> map) {
        ArrayList arrayList = new ArrayList();
        List<l> listD = oVar.d();
        synchronized (listD) {
            for (l lVar : listD) {
                String strF = lVar.f();
                com.topfreegames.bikerace.b.b bVarB = map.get(strF);
                if (bVarB == null) {
                    bVarB = oVar.b(strF);
                    map.put(strF, bVarB);
                }
                com.topfreegames.bikerace.b.b bVar = bVarB;
                Integer numB = bVar.b();
                if (numB == null) {
                    numB = 0;
                }
                arrayList.add(new an(bVar.c(), lVar.g(), numB.intValue()));
            }
        }
        String strG = oVar.g();
        com.topfreegames.bikerace.b.b bVarB2 = map.get(strG);
        if (bVarB2 == null) {
            bVarB2 = oVar.b(strG);
            map.put(strG, bVarB2);
        }
        Integer numB2 = bVarB2.b();
        if (numB2 == null) {
            numB2 = 0;
        }
        arrayList.add(new an(oVar.g(), oVar.h(), numB2.intValue()));
        Collections.sort(arrayList, new ao());
        return arrayList;
    }
}
