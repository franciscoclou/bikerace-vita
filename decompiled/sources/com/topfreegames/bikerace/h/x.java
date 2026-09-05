package com.topfreegames.bikerace.h;

import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: compiled from: World.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected ArrayList<a> f1258a;
    private int b;
    private int c;
    private int d;

    public x(int i, a[] aVarArr, int i2, int i3) {
        this(i, (ArrayList<a>) new ArrayList(Arrays.asList(aVarArr)), i2, i3);
    }

    public x(int i, ArrayList<a> arrayList, int i2, int i3) {
        this.f1258a = null;
        this.b = -1;
        this.c = -1;
        this.d = 0;
        if (arrayList == null) {
            throw new IllegalArgumentException("Levels cannot be null!");
        }
        this.d = i;
        this.b = i2;
        this.c = i3;
        this.f1258a = arrayList;
    }

    public a a(int i) {
        if (i < this.f1258a.size() && i >= 0) {
            return this.f1258a.get(i);
        }
        throw new IllegalArgumentException("Invalid value: " + i);
    }

    public int a(boolean z) {
        return z ? this.b : this.c;
    }
}
