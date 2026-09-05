package com.google.a.a.a;

import java.util.SortedSet;
import java.util.TreeSet;

/* JADX INFO: compiled from: GAUsage.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ac {
    private static final ac d = new ac();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private SortedSet<ad> f572a = new TreeSet();
    private StringBuilder b = new StringBuilder();
    private boolean c = false;

    public static ac a() {
        return d;
    }

    private ac() {
    }

    public synchronized void a(boolean z) {
        this.c = z;
    }

    public synchronized void a(ad adVar) {
        if (!this.c) {
            this.f572a.add(adVar);
            this.b.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(adVar.ordinal()));
        }
    }

    public synchronized String b() {
        StringBuilder sb;
        sb = new StringBuilder();
        int i = 6;
        int iOrdinal = 0;
        while (this.f572a.size() > 0) {
            ad adVarFirst = this.f572a.first();
            this.f572a.remove(adVarFirst);
            int iOrdinal2 = adVarFirst.ordinal();
            while (iOrdinal2 >= i) {
                sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(iOrdinal));
                i += 6;
                iOrdinal = 0;
            }
            iOrdinal += 1 << (adVarFirst.ordinal() % 6);
        }
        if (iOrdinal > 0 || sb.length() == 0) {
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(iOrdinal));
        }
        this.f572a.clear();
        return sb.toString();
    }

    public synchronized String c() {
        String string;
        if (this.b.length() > 0) {
            this.b.insert(0, ".");
        }
        string = this.b.toString();
        this.b = new StringBuilder();
        return string;
    }
}
