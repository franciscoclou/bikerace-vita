package com.b.a.a;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final w f302a;
    private final bg b;
    private aj c;
    private final bi d;
    private final int e;
    private List<bj> f;

    private j(w wVar, bg bgVar, bi biVar, int i) {
        this.f = new CopyOnWriteArrayList();
        this.f302a = wVar;
        this.d = biVar;
        this.b = bgVar;
        this.b.a();
        this.e = 100;
    }

    j(w wVar, bg bgVar, bi biVar) {
        this(wVar, bgVar, biVar, 100);
    }

    final void a(u uVar) throws Throwable {
        byte[] bArrA = this.f302a.a(uVar);
        int length = bArrA.length;
        if (!this.d.a(length, e())) {
            ba.a(4, String.format(Locale.US, "session analytics events file is %d bytes, new event is %d bytes, this is over flush limit of %d, rolling it over", Integer.valueOf(this.d.a()), Integer.valueOf(length), Integer.valueOf(e())));
            a();
        }
        this.d.a(bArrA);
    }

    final void a(bj bjVar) {
        if (bjVar != null) {
            this.f.add(bjVar);
        }
    }

    final boolean a() throws Throwable {
        boolean z = true;
        String str = null;
        if (this.d.b()) {
            z = false;
        } else {
            str = "sa_" + UUID.randomUUID().toString() + "_" + this.b.a() + ".tap";
            this.d.a(str);
            ba.a(4, String.format(Locale.US, "generated new to-send analytics file %s", str));
            this.b.a();
        }
        a(str);
        return z;
    }

    private int e() {
        if (this.c == null) {
            return 8000;
        }
        return this.c.c;
    }

    final void a(aj ajVar) {
        this.c = ajVar;
    }

    private void a(String str) {
        Iterator<bj> it = this.f.iterator();
        while (it.hasNext()) {
            try {
                it.next().c();
            } catch (Exception e) {
                cm.a().b().a("Crashlytics", "One of the roll over listeners threw an exception", e);
            }
        }
    }

    final List<File> b() {
        return this.d.a(1);
    }

    final void a(List<File> list) {
        this.d.a(list);
    }

    final void c() {
        this.d.a(this.d.c());
        this.d.d();
    }

    final void d() {
        List<File> listC = this.d.c();
        if (listC.size() > this.e) {
            int size = listC.size() - this.e;
            ba.c(String.format(Locale.US, "Found %d files in session analytics roll over directory, this is greater than %d, deleting %d oldest files", Integer.valueOf(listC.size()), Integer.valueOf(this.e), Integer.valueOf(size)));
            TreeSet treeSet = new TreeSet(new k(this));
            for (File file : listC) {
                treeSet.add(new l(this, file, b(file.getName())));
            }
            ArrayList arrayList = new ArrayList();
            Iterator it = treeSet.iterator();
            while (it.hasNext()) {
                arrayList.add(((l) it.next()).f303a);
                if (arrayList.size() == size) {
                    break;
                }
            }
            this.d.a(arrayList);
        }
    }

    private static long b(String str) {
        String[] strArrSplit = str.split("_");
        if (strArrSplit.length != 3) {
            return 0L;
        }
        try {
            return Long.valueOf(strArrSplit[2]).longValue();
        } catch (NumberFormatException e) {
            return 0L;
        }
    }
}
