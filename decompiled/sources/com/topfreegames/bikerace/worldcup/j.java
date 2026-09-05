package com.topfreegames.bikerace.worldcup;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/* JADX INFO: compiled from: SlotMachine.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class j {
    private static final Random g = new Random();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1455a;
    private int b;
    private int c;
    private int d;
    private int e;
    private Map<a, Integer> f;

    j(int i, int i2, int i3, int i4, int i5) {
        this.f1455a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
        d();
    }

    a a(com.topfreegames.bikerace.c cVar) {
        int iIntValue = 0;
        Iterator<Map.Entry<a, Integer>> it = this.f.entrySet().iterator();
        int iIntValue2 = 0;
        while (it.hasNext()) {
            iIntValue2 = it.next().getValue().intValue() + iIntValue2;
        }
        a[] aVarArrC = c.c(cVar);
        int length = aVarArrC.length;
        int i = 0;
        int iIntValue3 = 0;
        while (i < length) {
            Integer num = this.f.get(aVarArrC[i]);
            i++;
            iIntValue3 = num != null ? num.intValue() + iIntValue3 : iIntValue3;
        }
        int iC = l.c(com.topfreegames.c.a.a().getTime());
        int iMax = Math.max(((iIntValue2 - iIntValue3) * iC) / (iIntValue2 - (iC * iIntValue3)), 1);
        o oVarA = o.a();
        for (Map.Entry<a, Integer> entry : this.f.entrySet()) {
            iIntValue += entry.getValue().intValue() * ((entry.getKey().a().equals(cVar) && oVarA.t()) ? iMax : 1);
        }
        int iNextInt = g.nextInt(iIntValue);
        int i2 = iNextInt;
        for (Map.Entry<a, Integer> entry2 : this.f.entrySet()) {
            int iIntValue4 = i2 - (entry2.getValue().intValue() * ((entry2.getKey().a().equals(cVar) && oVarA.t()) ? iMax : 1));
            if (iIntValue4 <= 0) {
                return entry2.getKey();
            }
            i2 = iIntValue4;
        }
        return null;
    }

    public int a() {
        return this.d;
    }

    public int b() {
        return this.c;
    }

    int c() {
        return this.e;
    }

    public void d() {
        this.f = new HashMap();
        for (Map.Entry<a, Integer> entry : e.f1451a.entrySet()) {
            int iIntValue = entry.getValue().intValue();
            if (iIntValue >= this.f1455a && iIntValue <= this.b) {
                this.f.put(entry.getKey(), Integer.valueOf(iIntValue));
            }
        }
    }

    public a[] e() {
        return (a[]) this.f.keySet().toArray(new a[this.f.size()]);
    }

    public boolean a(a aVar) {
        Iterator<a> it = this.f.keySet().iterator();
        while (it.hasNext()) {
            if (it.next().equals(aVar)) {
                return true;
            }
        }
        return false;
    }
}
