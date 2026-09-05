package com.topfreegames.bikerace.multiplayer;

import com.amazonaws.services.dynamodb.model.ConditionalCheckFailedException;
import com.topfreegames.bikerace.ap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* JADX INFO: compiled from: MultiplayerService.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class aa {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.topfreegames.bikerace.t f1300a;
    private com.topfreegames.bikerace.l.b b;
    private com.topfreegames.bikerace.l.c c;
    private com.topfreegames.bikerace.l.f d;
    private com.topfreegames.bikerace.l.f e;
    private ab k;
    private ab l;
    private boolean f = false;
    private boolean g = false;
    private Future<Void> h = null;
    private Future<Void> i = null;
    private volatile boolean m = false;
    private volatile boolean n = false;
    private ExecutorService j = Executors.newSingleThreadExecutor();

    public aa(com.topfreegames.bikerace.l.b bVar, com.topfreegames.bikerace.l.f fVar, ab abVar, ab abVar2, com.topfreegames.bikerace.t tVar) {
        this.f1300a = null;
        this.k = null;
        this.l = null;
        this.b = bVar;
        this.d = fVar;
        this.k = abVar;
        this.l = abVar2;
        this.f1300a = tVar;
    }

    public ab a() {
        return this.l;
    }

    public com.topfreegames.bikerace.b.b a(String str) {
        if (!this.k.a()) {
            return null;
        }
        try {
            return this.d.a(str);
        } catch (Exception e) {
            e.printStackTrace();
            this.f1300a.b("remoteGetUser", e);
            return null;
        }
    }

    public com.topfreegames.bikerace.b.b b(String str) {
        com.topfreegames.bikerace.b.b bVar = null;
        try {
            try {
                com.topfreegames.bikerace.b.b bVarA = a(str);
                if (bVarA != null) {
                    com.topfreegames.bikerace.b.b bVarD = d(str);
                    if (bVarD != null && bVarD.a() != bVarA.a()) {
                        Integer numA = bVarD.a();
                        if (numA == null) {
                            numA = 0;
                        }
                        a(str, numA.intValue());
                        bVarA.a(numA);
                    }
                    this.e.a(bVarA, new Date());
                    bVar = bVarA;
                }
                if (bVar == null) {
                    return d(str);
                }
                return bVar;
            } catch (Exception e) {
                if (ap.d()) {
                    e.printStackTrace();
                }
                this.f1300a.b("getUser", e);
                if (0 != 0) {
                    return null;
                }
                return d(str);
            }
        } catch (Throwable th) {
            if (0 == 0) {
                d(str);
            }
            throw th;
        }
    }

    private com.topfreegames.bikerace.b.b d(String str) {
        try {
            try {
                com.topfreegames.bikerace.b.b bVarA = this.e.a(str);
                if (bVarA == null) {
                    return new com.topfreegames.bikerace.b.b(str);
                }
                return bVarA;
            } catch (Exception e) {
                e.printStackTrace();
                this.f1300a.b("getUserFromFallback", e);
                if (0 != 0) {
                    return null;
                }
                return new com.topfreegames.bikerace.b.b(str);
            }
        } catch (Throwable th) {
            if (0 == 0) {
                new com.topfreegames.bikerace.b.b(str);
            }
            throw th;
        }
    }

    public boolean c(String str) {
        try {
            return this.d.b(str);
        } catch (Exception e) {
            if (ap.d()) {
                e.printStackTrace();
            }
            this.f1300a.b("checkUserExists", e);
            return false;
        }
    }

    public void a(String str, ah ahVar) {
        if (this.l.a() && this.k.a() && !this.f) {
            synchronized (this) {
                this.f = true;
                ag agVar = new ag(this, str, ahVar);
                if (this.h == null || this.h.isDone() || this.h.isCancelled()) {
                    this.m = false;
                    this.h = this.j.submit(agVar);
                }
            }
        }
    }

    public void a(String str, ad adVar) {
        if (!this.g) {
            synchronized (this) {
                this.g = true;
                af afVar = new af(this, str, adVar);
                if (this.i == null || this.i.isDone() || this.i.isCancelled()) {
                    this.n = false;
                    this.i = this.j.submit(afVar);
                }
            }
        }
    }

    public void b() {
        d();
        c();
    }

    public void c() {
        if (this.h != null) {
            this.h.cancel(true);
            this.h = null;
            this.c.a();
            this.f = false;
            this.m = true;
        }
    }

    public void d() {
        if (this.i != null) {
            this.i.cancel(true);
            this.i = null;
            this.c.a();
            this.g = false;
            this.n = true;
        }
    }

    private List<String> a(String str, boolean z) throws InterruptedException {
        Set<String> setD;
        com.topfreegames.bikerace.b.b bVarB = b(str);
        if (bVarB == null || (setD = bVarB.d()) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(setD.size());
        Iterator<String> it = setD.iterator();
        while (it.hasNext()) {
            arrayList.add(com.topfreegames.bikerace.b.a.a(str, it.next()));
            if (z && (this.m || Thread.interrupted())) {
                throw new InterruptedException();
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, ah ahVar, boolean z) throws InterruptedException {
        List<List> linkedList;
        List<com.topfreegames.bikerace.b.a> listA;
        List<String> listA2 = a(str, true);
        if (listA2 != null) {
            Collections.shuffle(listA2);
            int size = listA2.size();
            int i = 0;
            if (z) {
                int i2 = (int) (((double) size) / 10.0d);
                if (i2 > 25) {
                    i2 = 25;
                } else if (i2 < 4) {
                    i2 = 4;
                }
                linkedList = com.topfreegames.bikerace.m.e.a(listA2, i2);
            } else {
                linkedList = new LinkedList();
                linkedList.add(listA2);
                listA2.size();
            }
            for (List list : linkedList) {
                int size2 = list.size() + i;
                try {
                    try {
                        try {
                            List<com.topfreegames.bikerace.b.a> arrayList = new ArrayList<>(list.size());
                            List<com.topfreegames.bikerace.b.a> listA3 = this.c.a((String[]) list.toArray(new String[0]));
                            ArrayList arrayList2 = new ArrayList(list);
                            Date date = new Date();
                            if (listA3 != null) {
                                for (com.topfreegames.bikerace.b.a aVar : listA3) {
                                    try {
                                        c(aVar);
                                        arrayList.add(aVar);
                                        arrayList2.remove(aVar.a());
                                        this.c.a(aVar, date, date);
                                    } catch (ConditionalCheckFailedException e) {
                                        e.printStackTrace();
                                        this.f1300a.b("retrieveGameSesison", e);
                                    }
                                    if (this.m || Thread.interrupted()) {
                                        throw new InterruptedException();
                                    }
                                }
                            }
                            if (this.m || Thread.interrupted()) {
                                throw new InterruptedException();
                            }
                            if (!arrayList2.isEmpty()) {
                                List<com.topfreegames.bikerace.b.a> listA4 = this.b.a((String[]) arrayList2.toArray(new String[0]), str, true);
                                arrayList.addAll(listA4);
                                Iterator<com.topfreegames.bikerace.b.a> it = listA4.iterator();
                                while (it.hasNext()) {
                                    this.c.a(it.next(), date, date);
                                    if (this.m || Thread.interrupted()) {
                                        throw new InterruptedException();
                                    }
                                }
                            }
                            if (arrayList != null) {
                                listA = arrayList;
                            } else {
                                if (this.m || Thread.interrupted()) {
                                    throw new InterruptedException();
                                }
                                listA = a((String[]) list.toArray(new String[0]), str);
                            }
                            if (listA != null && ahVar != null) {
                                ahVar.a(listA, size2, size);
                            }
                            i = size2;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            this.f1300a.b("retrieveGameSessions", e2);
                            if (0 != 0) {
                                listA = null;
                            } else {
                                if (this.m || Thread.interrupted()) {
                                    throw new InterruptedException();
                                }
                                listA = a((String[]) list.toArray(new String[0]), str);
                            }
                        }
                    } catch (InterruptedException e3) {
                        throw e3;
                    }
                } catch (Throwable th) {
                    if (0 == 0) {
                        if (this.m || Thread.interrupted()) {
                            throw new InterruptedException();
                        }
                        a((String[]) list.toArray(new String[0]), str);
                    }
                    throw th;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, ad adVar, boolean z) throws InterruptedException {
        List<List> linkedList;
        com.topfreegames.bikerace.b.b bVarD = d(str);
        if (!bVarD.d().isEmpty()) {
            Set<String> setD = bVarD.d();
            ArrayList arrayList = new ArrayList(setD.size());
            Iterator<String> it = setD.iterator();
            while (it.hasNext()) {
                arrayList.add(com.topfreegames.bikerace.b.a.a(str, it.next()));
                if (this.n || Thread.interrupted()) {
                    throw new InterruptedException();
                }
            }
            Collections.shuffle(arrayList);
            int size = arrayList.size();
            if (z) {
                int i = (int) (((double) size) / 10.0d);
                if (i > 25) {
                    i = 25;
                } else if (i < 4) {
                    i = 4;
                }
                linkedList = com.topfreegames.bikerace.m.e.a(arrayList, i);
            } else {
                linkedList = new LinkedList();
                linkedList.add(arrayList);
                arrayList.size();
            }
            int size2 = 0;
            for (List list : linkedList) {
                size2 += list.size();
                List<com.topfreegames.bikerace.b.a> listA = a((String[]) list.toArray(new String[0]), str);
                if (listA != null && adVar != null) {
                    adVar.a(listA, size2, size);
                }
                if (this.n || Thread.interrupted()) {
                    throw new InterruptedException();
                }
            }
        }
    }

    private List<com.topfreegames.bikerace.b.a> a(String[] strArr, String str) {
        try {
            try {
                try {
                    List<com.topfreegames.bikerace.b.a> listA = this.c.a(strArr, str, true);
                    if (listA == null) {
                        return Collections.emptyList();
                    }
                    return listA;
                } catch (InterruptedException e) {
                    throw e;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                this.f1300a.b("getGameSessionFromFallback", e2);
                if (0 != 0) {
                    return null;
                }
                return Collections.emptyList();
            }
        } catch (Throwable th) {
            if (0 == 0) {
                Collections.emptyList();
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(com.topfreegames.bikerace.b.a aVar) {
        this.b.a(aVar);
        if (aVar.J()) {
            this.d.a(aVar.c(), aVar.f());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(com.topfreegames.bikerace.b.a aVar) {
        try {
            this.d.d(aVar.c(), aVar.e().intValue());
            this.d.d(aVar.f(), aVar.h().intValue());
            this.d.b(aVar.c(), aVar.f());
            this.b.b(aVar);
        } catch (com.amazonaws.b e) {
            if (ap.d()) {
                e.printStackTrace();
            }
            this.f1300a.b("remoteDelete", e);
        } catch (com.amazonaws.a e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
            this.f1300a.b("remoteDelete", e2);
        }
    }

    public void a(final com.topfreegames.bikerace.b.a aVar, final ac acVar) {
        this.e.d(aVar.c(), aVar.e().intValue());
        this.e.d(aVar.f(), aVar.h().intValue());
        this.c.b(aVar);
        try {
            this.e.b(aVar.c(), aVar.f());
        } catch (NullPointerException e) {
            this.e.b(aVar.f(), aVar.c());
        }
        if (this.k.a()) {
            new Thread(new Runnable() { // from class: com.topfreegames.bikerace.multiplayer.aa.1
                @Override // java.lang.Runnable
                public void run() {
                    aa.this.d(aVar);
                    if (acVar != null) {
                        acVar.a();
                    }
                }
            }).start();
        }
    }

    public void a(com.topfreegames.bikerace.b.a aVar) {
        f(aVar);
        this.j.submit(new ai(this, aVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(com.topfreegames.bikerace.b.a aVar) {
        try {
            ((com.topfreegames.bikerace.l.a.a) this.b).a(aVar, false);
            this.d.a(aVar.c(), aVar.f());
            a(aVar, new Date());
        } catch (com.amazonaws.a e) {
            this.f1300a.b("unconditionalPush", e);
            if (ap.d()) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.topfreegames.bikerace.b.a aVar, Date date) {
        try {
            this.c.a(aVar, date, date);
        } catch (Exception e) {
            this.f1300a.b("localPush", e);
            if (ap.d()) {
                e.printStackTrace();
            }
        }
    }

    private void f(com.topfreegames.bikerace.b.a aVar) {
        try {
            this.c.a(aVar);
        } catch (Exception e) {
            this.f1300a.b("localPush", e);
            if (ap.d()) {
                e.printStackTrace();
            }
        }
    }

    public void b(com.topfreegames.bikerace.b.a aVar) {
        try {
            this.c.a(aVar);
            if (this.k.a()) {
                this.j.submit(new ae(this, aVar));
            }
        } catch (Exception e) {
            this.f1300a.b("put", e);
            if (ap.d()) {
                e.printStackTrace();
            }
        }
    }

    public boolean a(String str, int i) {
        try {
            this.e.a(str, i);
        } catch (Exception e) {
            if (ap.d()) {
                e.printStackTrace();
            }
            this.f1300a.b("updatePoor", e);
        }
        if (this.k.a()) {
            try {
                this.d.a(str, i);
                return true;
            } catch (com.amazonaws.a e2) {
                e2.printStackTrace();
                this.f1300a.b("updatePoor", e2);
            }
        }
        return false;
    }

    public boolean b(String str, int i) {
        try {
            this.e.b(str, i);
        } catch (Exception e) {
            if (ap.d()) {
                e.printStackTrace();
            }
            this.f1300a.b("updateRanking", e);
        }
        if (this.k.a()) {
            try {
                this.d.b(str, i);
                return true;
            } catch (Exception e2) {
                if (ap.d()) {
                    e2.printStackTrace();
                }
                this.f1300a.b("updateRanking", e2);
            }
        }
        return false;
    }

    public boolean c(String str, int i) {
        try {
            this.e.c(str, i);
        } catch (Exception e) {
            if (ap.d()) {
                e.printStackTrace();
            }
            this.f1300a.b("updateErased", e);
        }
        if (this.k.a()) {
            try {
                this.d.c(str, i);
                return true;
            } catch (com.amazonaws.a e2) {
                e2.printStackTrace();
                this.f1300a.b("updateErased", e2);
            }
        }
        return false;
    }

    public void a(com.topfreegames.bikerace.l.f fVar) {
        this.e = fVar;
    }

    public void a(com.topfreegames.bikerace.l.c cVar) {
        this.c = cVar;
    }

    public void a(String str, String str2) {
        if (str != null && str2 != null) {
            try {
                List<String> listA = a(str, false);
                if (listA != null) {
                    Collections.shuffle(listA);
                    Iterator it = com.topfreegames.bikerace.m.e.a(listA, 25).iterator();
                    while (it.hasNext()) {
                        for (com.topfreegames.bikerace.b.a aVar : this.b.a((String[]) ((List) it.next()).toArray(new String[0]), str, true)) {
                            a(aVar, (ac) null);
                            com.topfreegames.bikerace.b.a aVar2 = new com.topfreegames.bikerace.b.a(aVar);
                            a(aVar2, str, str2);
                            a(aVar2);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                this.f1300a.b("migrate", e);
            }
        }
    }

    private void a(com.topfreegames.bikerace.b.a aVar, String str, String str2) {
        if (aVar != null && str != null && str2 != null) {
            String strA = null;
            if (str.equals(aVar.c())) {
                strA = com.topfreegames.bikerace.b.a.a(str2, aVar.f());
                aVar.c(str2);
            } else if (str.equals(aVar.f())) {
                strA = com.topfreegames.bikerace.b.a.a(str2, aVar.c());
                aVar.e(str2);
            }
            aVar.b(strA);
            if (str.equals(aVar.m())) {
                aVar.g(str2);
            } else if (str.equals(aVar.w())) {
                aVar.j(str2);
            }
        }
    }

    public List<String> a(List<String> list) {
        ArrayList arrayList = new ArrayList();
        try {
            Iterator<com.topfreegames.bikerace.b.a> it = this.c.a((String[]) list.toArray(new String[list.size()])).iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().a());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            this.f1300a.b("listNotSynced", e);
        }
        return arrayList;
    }
}
