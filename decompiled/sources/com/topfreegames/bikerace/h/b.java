package com.topfreegames.bikerace.h;

import android.graphics.RectF;
import android.util.SparseBooleanArray;
import java.util.ArrayList;

/* JADX INFO: compiled from: LevelBoards.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ArrayList<com.topfreegames.bikerace.d> f1256a = new ArrayList<>();
    private RectF b = new RectF(9999999.0f, -9999999.0f, -9999999.0f, 9999999.0f);
    private ArrayList<b> c = null;

    public void a(com.topfreegames.bikerace.d dVar) {
        this.f1256a.add(dVar);
        b(dVar);
    }

    public void a(com.topfreegames.bikerace.d dVar, int i) {
        this.f1256a.add(i, dVar);
        b(dVar);
    }

    public void a(b bVar) {
        int iD = bVar.d();
        for (int i = 0; i < iD; i++) {
            a(bVar.f1256a.get(i));
        }
    }

    public RectF a() {
        return this.b;
    }

    public com.topfreegames.bikerace.d a(int i) {
        return this.f1256a.get(i);
    }

    public com.topfreegames.bikerace.d b() {
        return this.f1256a.get(0);
    }

    public com.topfreegames.bikerace.d c() {
        return this.f1256a.get(this.f1256a.size() - 1);
    }

    public int d() {
        return this.f1256a.size();
    }

    private void b(com.topfreegames.bikerace.d dVar) {
        this.b.left = Math.min(Math.min(this.b.left, dVar.f1168a.f1553a), dVar.b.f1553a);
        this.b.right = Math.max(Math.max(this.b.right, dVar.f1168a.f1553a), dVar.b.f1553a);
        this.b.top = Math.max(Math.max(this.b.top, dVar.f1168a.b), dVar.b.b);
        this.b.bottom = Math.min(Math.min(this.b.bottom, dVar.f1168a.b), dVar.b.b);
    }

    void e() {
        this.c = b(this);
    }

    private static ArrayList<b> b(b bVar) {
        int iD = bVar.d();
        float fWidth = (1.05f * bVar.b.width()) / 100.0f;
        ArrayList<b> arrayList = new ArrayList<>(100);
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
        for (int i = 0; i < iD; i++) {
            sparseBooleanArray.put(i, false);
        }
        float f = bVar.b.left;
        float f2 = f + fWidth;
        float f3 = f;
        for (int i2 = 0; i2 < 100; i2++) {
            b bVar2 = new b();
            for (int i3 = 0; i3 < iD; i3++) {
                com.topfreegames.bikerace.d dVarA = bVar.a(i3);
                if (!sparseBooleanArray.get(i3) && dVarA.f1168a.f1553a >= f3 && dVarA.f1168a.f1553a <= f2) {
                    bVar2.a(dVarA);
                    sparseBooleanArray.put(i3, true);
                }
            }
            f3 += fWidth;
            f2 += fWidth;
            if (bVar2.f1256a.size() > 0) {
                arrayList.add(bVar2);
            }
        }
        int i4 = 0;
        while (i4 < arrayList.size() && i4 + 1 < arrayList.size()) {
            b bVar3 = arrayList.get(i4);
            int iD2 = bVar3.d();
            if (iD2 < 30) {
                b bVar4 = arrayList.get(i4 + 1);
                if (iD2 + bVar4.d() <= 30) {
                    bVar3.a(bVar4);
                    arrayList.remove(i4 + 1);
                } else {
                    i4++;
                }
            } else {
                i4++;
            }
        }
        int size = arrayList.size();
        for (int i5 = 0; i5 < size; i5++) {
            b bVar5 = arrayList.get(i5);
            bVar5.b.top = -99999.9f;
            bVar5.b.bottom = 99999.9f;
        }
        return arrayList;
    }

    public void a(RectF rectF, b bVar) {
        int size = this.c.size();
        bVar.f1256a.clear();
        for (int i = 0; i < size; i++) {
            b bVar2 = this.c.get(i);
            if (RectF.intersects(rectF, bVar2.b)) {
                bVar.f1256a.addAll(bVar2.f1256a);
            }
        }
    }
}
