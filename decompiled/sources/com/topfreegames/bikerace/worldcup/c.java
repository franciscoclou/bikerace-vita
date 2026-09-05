package com.topfreegames.bikerace.worldcup;

import com.topfreegames.bikerace.ap;
import com.topfreegames.bikerace.z;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: compiled from: BikePartCollection.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private z f1450a;
    private HashMap<a, Integer> b = new HashMap<>();

    c(z zVar) {
        if (zVar == null) {
            throw new IllegalArgumentException("Data cannot be null!");
        }
        this.f1450a = zVar;
        a();
    }

    void a(a aVar, d dVar) {
        a(aVar, true, dVar);
    }

    private void a(a aVar, boolean z, d dVar) {
        int iValueOf;
        if (aVar == null) {
            throw new IllegalArgumentException("Part cannot be null!");
        }
        if (dVar == null) {
            throw new IllegalArgumentException("Listener cannot be null!");
        }
        Integer num = this.b.get(aVar);
        if (num == null) {
            iValueOf = 1;
        } else {
            iValueOf = Integer.valueOf(num.intValue() + 1);
        }
        this.b.put(aVar, iValueOf);
        com.topfreegames.bikerace.c cVarA = aVar.a();
        if (d(cVarA)) {
            dVar.a(cVarA);
        }
        b();
        if (z) {
            try {
                com.topfreegames.bikerace.k.a.a().a(aVar);
            } catch (Exception e) {
                if (ap.d()) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean a(a aVar) {
        Integer num = this.b.get(aVar);
        return num != null && num.intValue() > 0;
    }

    public a[] a(com.topfreegames.bikerace.c cVar) {
        ArrayList arrayList = new ArrayList();
        for (b bVar : b.valuesCustom()) {
            a aVar = new a(cVar, bVar);
            Integer num = this.b.get(aVar);
            if (num != null && num.intValue() > 0) {
                arrayList.add(aVar);
            }
        }
        return (a[]) arrayList.toArray(new a[arrayList.size()]);
    }

    public a[] b(com.topfreegames.bikerace.c cVar) {
        ArrayList arrayList = new ArrayList();
        for (b bVar : b.valuesCustom()) {
            a aVar = new a(cVar, bVar);
            Integer num = this.b.get(aVar);
            if (num == null || num.intValue() <= 0) {
                arrayList.add(aVar);
            }
        }
        return (a[]) arrayList.toArray(new a[arrayList.size()]);
    }

    public void a() {
        for (com.topfreegames.bikerace.c cVar : com.topfreegames.bikerace.c.valuesCustom()) {
            for (b bVar : b.valuesCustom()) {
                a aVar = new a(cVar, bVar);
                this.b.put(aVar, Integer.valueOf(this.f1450a.a(aVar)));
            }
        }
    }

    public void b() {
        this.f1450a.a(this.b);
    }

    private boolean d(com.topfreegames.bikerace.c cVar) {
        for (a aVar : c(cVar)) {
            Integer num = this.b.get(aVar);
            if (num == null || num.intValue() <= 0) {
                return false;
            }
        }
        return true;
    }

    public static a[] c(com.topfreegames.bikerace.c cVar) {
        int i = 0;
        a[] aVarArr = new a[b.valuesCustom().length];
        b[] bVarArrValuesCustom = b.valuesCustom();
        int length = bVarArrValuesCustom.length;
        int i2 = 0;
        while (i < length) {
            aVarArr[i2] = new a(cVar, bVarArrValuesCustom[i]);
            i++;
            i2++;
        }
        return aVarArr;
    }
}
