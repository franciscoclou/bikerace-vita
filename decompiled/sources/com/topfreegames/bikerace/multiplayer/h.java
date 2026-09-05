package com.topfreegames.bikerace.multiplayer;

import com.topfreegames.bikerace.bg;
import com.topfreegames.bikerace.bi;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: LevelRandomizer.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class h {
    protected static Integer a(Integer num, Integer num2) {
        if (num == null || num2 == null) {
            return null;
        }
        return Integer.valueOf(((num.intValue() - 1) * 8) + (num2.intValue() - 1));
    }

    protected static int a(int i, int i2, int i3, int i4) {
        int i5;
        if (i3 < 35) {
            i5 = 9;
        } else if (i3 < 70) {
            i5 = 24;
        } else {
            i5 = 50;
        }
        if (i >= i5) {
            i5 = i;
        }
        if (i5 < i2) {
            i2 = i5;
        }
        if (i2 <= 10) {
            return 6;
        }
        if (i2 <= 25) {
            return 17;
        }
        if (i4 > 6) {
            if (i3 < 240 || i2 < 2000) {
                return (i3 < 204 || i2 < 800) ? 63 : 79;
            }
            return 151;
        }
        if (i4 > 5) {
            return 63;
        }
        if (i4 > 4) {
            return 55;
        }
        return 47;
    }

    protected static int a(int i) {
        return (((int) (Math.random() * 1000.0d)) % i) + 1;
    }

    public static i a(List<l> list, com.topfreegames.bikerace.b.b bVar, com.topfreegames.bikerace.b.b bVar2, int i, int i2, int i3) {
        Integer numValueOf;
        Integer numValueOf2;
        ArrayList arrayList = new ArrayList();
        Iterator<l> it = list.iterator();
        while (it.hasNext()) {
            com.topfreegames.bikerace.b.a aVarA = it.next().a();
            arrayList.add(a(aVarA.u(), aVarA.v()));
            arrayList.add(a(aVarA.k(), aVarA.l()));
        }
        Integer numA = bVar.a();
        Integer numA2 = bVar2.a();
        if (numA == null) {
            numA = 0;
        }
        if (numA2 == null) {
            numA2 = 0;
        }
        Integer numB = bVar.b();
        Integer numB2 = bVar2.b();
        if (numB == null) {
            numValueOf = 0;
        } else {
            numValueOf = Integer.valueOf(numB.intValue() - (numA.intValue() / 2));
        }
        if (numB2 == null) {
            numValueOf2 = 0;
        } else {
            numValueOf2 = Integer.valueOf(numB2.intValue() - (numA2.intValue() / 2));
        }
        if (i >= i2) {
            i = i2;
        }
        int iA = a(numValueOf.intValue(), numValueOf2.intValue(), i3, i);
        if (bg.a(bi.MULTIPLAYER_FORCE_SELECT_HIGHEST_LEVEL)) {
            return new i(iA);
        }
        for (int i4 = 0; i4 < 10; i4++) {
            int iA2 = a(iA);
            if (!arrayList.contains(Integer.valueOf(iA2))) {
                return new i(iA2);
            }
        }
        return new i(a(iA));
    }
}
