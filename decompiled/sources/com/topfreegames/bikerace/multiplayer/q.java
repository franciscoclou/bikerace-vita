package com.topfreegames.bikerace.multiplayer;

import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.bikerace.ap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: MultiplayerManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ o f1324a;
    private aa c;
    private List<l> b = new ArrayList();
    private final int d = 200;
    private Comparator<l> e = new n();
    private int f = 0;
    private int g = 0;

    public final int a() {
        return 200;
    }

    public aa b() {
        return this.c;
    }

    public int c() {
        return this.f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        synchronized (this.b) {
            for (l lVar : this.b) {
                if (lVar.b().equals(str)) {
                    this.b.remove(lVar);
                    return;
                }
            }
        }
    }

    public boolean a(String str) {
        return !this.c.c(str);
    }

    public void a(final List<String> list, final boolean z) {
        if (this.f1324a.f != null) {
            this.f1324a.f.a(false);
        }
        this.c.a(this.f1324a.g(), new ad() { // from class: com.topfreegames.bikerace.multiplayer.q.1
            private boolean b = true;
            private List<String> c = new ArrayList();

            @Override // com.topfreegames.bikerace.multiplayer.ad
            public void a() {
                if (q.this.f1324a.f != null) {
                    q.this.f1324a.f.b(false);
                }
                com.topfreegames.bikerace.a.f.a(q.this.f1324a.p).a("AchievNumberFacebookFriends", q.this.f);
                if (!z) {
                    return;
                }
                q.this.a((List<String>) list);
            }

            @Override // com.topfreegames.bikerace.multiplayer.ad
            public void a(List<com.topfreegames.bikerace.b.a> list2, int i, int i2) {
                if (list2 != null) {
                    if (this.b) {
                        q.this.f = 0;
                        q.this.g = 0;
                        this.b = false;
                    }
                    r rVarA = q.this.a(list2, list);
                    q.this.f += rVarA.c;
                    q.this.f1324a.u.a((Collection<l>) rVarA.b);
                    if (q.this.f1324a.f != null) {
                        q.this.f1324a.f.a(rVarA.b, i, i2, false);
                    }
                    ArrayList arrayList = new ArrayList();
                    Iterator<com.topfreegames.bikerace.b.a> it = list2.iterator();
                    while (it.hasNext()) {
                        arrayList.add(it.next().a());
                    }
                    List<String> listA = q.this.c.a(arrayList);
                    for (l lVar : rVarA.b) {
                        q.this.g += lVar.p();
                        this.c.add(lVar.b());
                        lVar.c(!listA.contains(lVar.b()));
                    }
                }
            }
        });
    }

    public q(o oVar, aa aaVar) {
        this.f1324a = oVar;
        this.c = null;
        this.c = aaVar;
    }

    public List<l> d() {
        List<l> list;
        synchronized (this.b) {
            list = this.b;
        }
        return list;
    }

    public void a(l lVar) {
        l lVarA = this.f1324a.a(lVar.b());
        synchronized (this.b) {
            if (lVarA != null) {
                this.b.remove(lVarA);
                this.b.add(lVar);
            } else {
                this.b.add(lVar);
            }
            throw th;
        }
    }

    public void a(Collection<l> collection) {
        synchronized (this.b) {
            Iterator<l> it = collection.iterator();
            while (it.hasNext()) {
                a(it.next());
            }
            int size = this.b.size();
            if (size > 200) {
                Collections.sort(this.b, this.e);
                for (int i = size - 200; i > 0; i--) {
                    this.b.remove(200);
                }
            }
        }
    }

    public void e() {
        this.c.b();
    }

    public void a(final boolean z) {
        if (this.b.size() == 0) {
            if (!this.f1324a.a()) {
                a((List<String>) null, z);
                return;
            } else {
                this.f1324a.d.b(false, new com.topfreegames.e.a.l() { // from class: com.topfreegames.bikerace.multiplayer.q.2
                    @Override // com.topfreegames.e.a.l
                    public void a(final Dictionary<String, com.topfreegames.e.l> dictionary, com.topfreegames.e.e eVar) {
                        final ArrayList arrayList = new ArrayList();
                        if (dictionary != null) {
                            Enumeration<String> enumerationKeys = dictionary.keys();
                            while (enumerationKeys.hasMoreElements()) {
                                arrayList.add(enumerationKeys.nextElement());
                            }
                        }
                        final boolean z2 = z;
                        new Thread(new Runnable() { // from class: com.topfreegames.bikerace.multiplayer.q.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (q.this.f1324a.m) {
                                    q.this.a((Dictionary<String, com.topfreegames.e.l>) dictionary);
                                    q.this.f1324a.m = false;
                                }
                                q.this.a(arrayList, z2);
                            }
                        }).start();
                    }
                }, (Object) null);
                return;
            }
        }
        if (this.f1324a.f != null) {
            synchronized (this.b) {
                this.f1324a.f.a(this.b, this.b.size(), this.b.size(), false);
                this.f1324a.f.b(false);
            }
        }
        if (z) {
            if (!this.f1324a.a()) {
                a((List<String>) null);
            } else {
                this.f1324a.d.b(false, new com.topfreegames.e.a.l() { // from class: com.topfreegames.bikerace.multiplayer.q.3
                    @Override // com.topfreegames.e.a.l
                    public void a(final Dictionary<String, com.topfreegames.e.l> dictionary, com.topfreegames.e.e eVar) {
                        final ArrayList arrayList = new ArrayList();
                        if (dictionary != null) {
                            Enumeration<String> enumerationKeys = dictionary.keys();
                            while (enumerationKeys.hasMoreElements()) {
                                arrayList.add(enumerationKeys.nextElement());
                            }
                        }
                        final boolean z2 = z;
                        new Thread(new Runnable() { // from class: com.topfreegames.bikerace.multiplayer.q.3.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (q.this.f1324a.m) {
                                    q.this.a((Dictionary<String, com.topfreegames.e.l>) dictionary);
                                    q.this.f1324a.m = false;
                                }
                                q.this.a(arrayList, z2);
                            }
                        }).start();
                    }
                }, (Object) null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final List<String> list) {
        if (this.f1324a.f != null) {
            this.f1324a.f.a(true);
        }
        final ArrayList arrayList = new ArrayList();
        Iterator<l> it = this.f1324a.u.d().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().b());
        }
        this.c.a(this.f1324a.g(), new ah() { // from class: com.topfreegames.bikerace.multiplayer.q.4
            private boolean b = true;

            @Override // com.topfreegames.bikerace.multiplayer.ah
            public void a() {
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    q.this.e((String) it2.next());
                }
                if (q.this.f1324a.f != null) {
                    q.this.f1324a.f.a(arrayList);
                    q.this.f1324a.f.b(true);
                }
                q.this.f1324a.a(q.this.g);
                com.topfreegames.bikerace.a.f fVarA = com.topfreegames.bikerace.a.f.a(q.this.f1324a.p);
                fVarA.a("AchievNumberFacebookFriends", q.this.f);
                fVarA.a("AchievMultiplayerWins", q.this.f1324a.k());
            }

            @Override // com.topfreegames.bikerace.multiplayer.ah
            public void a(List<com.topfreegames.bikerace.b.a> list2, int i, int i2) {
                if (list2 != null) {
                    if (this.b) {
                        q.this.f = 0;
                        q.this.g = 0;
                        this.b = false;
                    }
                    r rVarA = q.this.a(list2, list);
                    q.this.f += rVarA.c;
                    q.this.f1324a.u.a((Collection<l>) rVarA.b);
                    for (l lVar : rVarA.b) {
                        lVar.c(true);
                        arrayList.remove(lVar.b());
                        q qVar = q.this;
                        qVar.g = lVar.p() + qVar.g;
                    }
                    if (q.this.f1324a.f == null) {
                        return;
                    }
                    q.this.f1324a.f.a(rVarA.b, i, i2, true);
                }
            }
        });
    }

    public void b(l lVar) {
        this.c.b(lVar.a());
        a(lVar);
        lVar.c(((BikeRaceApplication) this.f1324a.p).e());
    }

    public void f() {
        synchronized (this.b) {
            this.b.clear();
        }
    }

    public void b(String str) {
        if (str != null) {
            synchronized (this.b) {
                l lVar = null;
                for (l lVar2 : this.b) {
                    if (str.equals(lVar2.b())) {
                        this.c.a(lVar2.a(), (ac) null);
                        lVar = lVar2;
                    }
                }
                if (lVar != null) {
                    this.b.remove(lVar);
                }
            }
        }
        a(false);
    }

    public void a(String str, String str2) {
        this.c.a(str, str2);
    }

    public com.topfreegames.bikerace.b.b c(String str) {
        return this.c.b(str);
    }

    public boolean a(String str, int i) {
        return this.c.b(str, i);
    }

    public boolean b(String str, int i) {
        return this.c.a(str, i);
    }

    public boolean c(String str, int i) {
        return this.c.c(str, i);
    }

    public com.topfreegames.bikerace.b.b d(String str) {
        return this.c.a(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public r a(List<com.topfreegames.bikerace.b.a> list, Collection<String> collection) {
        r rVar = new r(this, null);
        Iterator<com.topfreegames.bikerace.b.a> it = list.iterator();
        while (it.hasNext()) {
            l lVar = new l(it.next(), this.f1324a.g());
            if (lVar.A()) {
                lVar.B();
            }
            boolean zContains = false;
            if (collection != null) {
                zContains = collection.contains(lVar.f());
            }
            lVar.a(zContains);
            rVar.b.add(lVar);
            if (zContains) {
                rVar.c++;
            }
        }
        return rVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Dictionary<String, com.topfreegames.e.l> dictionary) {
        try {
            if (((BikeRaceApplication) this.f1324a.p).e()) {
                String strG = this.f1324a.g();
                Enumeration<com.topfreegames.e.l> enumerationElements = dictionary.elements();
                while (enumerationElements.hasMoreElements()) {
                    com.topfreegames.e.l lVarNextElement = enumerationElements.nextElement();
                    com.topfreegames.bikerace.push.g.a(this.f1324a.h(), lVarNextElement.a(), com.topfreegames.bikerace.m.f.a(lVarNextElement.b()), com.topfreegames.bikerace.push.d.FIRST_LOGIN, this.f1324a.f1316a.sqs());
                }
                this.c.a(strG, this.c.b(strG).a().intValue());
            }
        } catch (Exception e) {
            if (ap.d()) {
                e.printStackTrace();
            }
        }
    }
}
