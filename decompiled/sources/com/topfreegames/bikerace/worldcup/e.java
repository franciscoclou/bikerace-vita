package com.topfreegames.bikerace.worldcup;

import android.annotation.SuppressLint;
import com.topfreegames.bikerace.bb;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: BikePartsLikelihood.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
@SuppressLint({"UseSparseArrays"})
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Map<a, Integer> f1451a;
    private static Map<Integer, Integer> b;

    static {
        HashMap map = new HashMap();
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_AUSTRALIA, b.SUIT), 200);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_AUSTRALIA, b.FRONT), 250);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_AUSTRALIA, b.HELMET), 1667);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_AUSTRALIA, b.BACK), 4167);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_BRAZIL, b.SUIT), 143);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_BRAZIL, b.FRONT), 200);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_BRAZIL, b.HELMET), 833);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_BRAZIL, b.BACK), 1111);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_USA, b.SUIT), 50);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_USA, b.FRONT), 125);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_USA, b.HELMET), 167);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_USA, b.BACK), 167);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_FRANCE, b.SUIT), 833);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_FRANCE, b.FRONT), 3333);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_FRANCE, b.HELMET), 3333);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_FRANCE, b.BACK), 66667);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_GERMANY, b.SUIT), 2083);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_GERMANY, b.FRONT), 3333);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_GERMANY, b.HELMET), 66667);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_GERMANY, b.BACK), 66667);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_JAPAN, b.SUIT), 66667);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_JAPAN, b.FRONT), 66667);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_JAPAN, b.HELMET), 66667);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_JAPAN, b.BACK), 66667);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_NETHERLANDS, b.SUIT), 333);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_NETHERLANDS, b.FRONT), 667);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_NETHERLANDS, b.HELMET), 2222);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_NETHERLANDS, b.BACK), 5556);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_SPAIN, b.SUIT), 16667);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_SPAIN, b.FRONT), 66667);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_SPAIN, b.HELMET), 66667);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_SPAIN, b.BACK), 66667);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_ENGLAND, b.SUIT), 111);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_ENGLAND, b.FRONT), 167);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_ENGLAND, b.HELMET), 200);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_ENGLAND, b.BACK), 1111);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_ARGENTINA, b.SUIT), 1667);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_ARGENTINA, b.FRONT), 3333);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_ARGENTINA, b.HELMET), 16667);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_ARGENTINA, b.BACK), 66667);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_ITALY, b.SUIT), 200);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_ITALY, b.FRONT), 200);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_ITALY, b.HELMET), 833);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_ITALY, b.BACK), 3333);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_BELGIUM, b.SUIT), 1667);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_BELGIUM, b.FRONT), 1667);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_BELGIUM, b.HELMET), 3333);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_BELGIUM, b.BACK), 66667);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_MEXICO, b.SUIT), 16667);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_MEXICO, b.FRONT), 16667);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_MEXICO, b.HELMET), 66667);
        map.put(new a(com.topfreegames.bikerace.c.WORLDCUP_MEXICO, b.BACK), 66667);
        f1451a = Collections.unmodifiableMap(map);
        HashMap map2 = new HashMap();
        map2.put(5, 125);
        map2.put(4, 250);
        map2.put(3, 2222);
        map2.put(2, 16667);
        map2.put(1, 1000000);
        b = Collections.unmodifiableMap(map2);
    }

    static void a(Map<a, Integer> map) {
        if (map != null && map.size() == f1451a.size()) {
            f1451a = Collections.unmodifiableMap(map);
        }
    }

    static void a(List<Integer> list) {
        if (list != null && list.size() == 5) {
            HashMap map = new HashMap();
            map.put(5, list.get(0));
            map.put(4, list.get(1));
            map.put(3, list.get(2));
            map.put(2, list.get(3));
            map.put(1, list.get(4));
            b = Collections.unmodifiableMap(map);
        }
    }

    public static int a(a aVar) {
        int iIntValue = f1451a.get(aVar).intValue();
        for (int i = 5; i > 0; i--) {
            if (iIntValue <= b.get(Integer.valueOf(i)).intValue()) {
                return i;
            }
        }
        return 0;
    }

    public static int b(a aVar) {
        return f1451a.get(aVar).intValue();
    }

    public static int a(p pVar, bb bbVar) {
        int i = 5;
        int iAm = pVar == p.RARE ? bbVar.am() : bbVar.ah();
        while (1 < i && iAm > b.get(Integer.valueOf(i)).intValue()) {
            i--;
        }
        return i;
    }

    public static int b(p pVar, bb bbVar) {
        int i = 5;
        int iAl = pVar == p.RARE ? bbVar.al() : bbVar.ag();
        while (1 < i && iAl > b.get(Integer.valueOf(i)).intValue()) {
            i--;
        }
        return i;
    }
}
