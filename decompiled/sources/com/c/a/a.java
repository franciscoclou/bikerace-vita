package com.c.a;

import android.app.Activity;
import android.util.Log;
import java.util.ArrayList;

/* JADX INFO: compiled from: BaseInterstitialManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class a {
    private static /* synthetic */ int[] i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private d f353a;
    private f b;
    private h c;
    private ArrayList<h> e;
    private int d = 0;
    private boolean f = false;
    private boolean g = false;
    private int h = 2;

    static /* synthetic */ int[] f() {
        int[] iArr = i;
        if (iArr == null) {
            iArr = new int[b.valuesCustom().length];
            try {
                iArr[b.PRIORITY_LIST.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[b.ROUND_ROBIN.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            i = iArr;
        }
        return iArr;
    }

    protected a() {
    }

    public boolean a() {
        g();
        int size = this.e.size();
        boolean zE = true;
        for (int i2 = 0; i2 < size; i2++) {
            zE &= this.e.get(i2).e();
        }
        return zE;
    }

    public void b() {
        g();
        int size = this.e.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.e.get(i2).d();
        }
    }

    public void c() {
        g();
        int size = this.e.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.e.get(i2).b();
        }
    }

    public void d() {
        g();
        int size = this.e.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.e.get(i2).c();
        }
    }

    public void a(Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("activity cannot be null!");
        }
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 < this.e.size()) {
                h hVar = this.e.get(i3);
                if (this.b.a(hVar)) {
                    hVar.a(activity);
                }
                i2 = i3 + 1;
            } else {
                this.g = true;
                return;
            }
        }
    }

    private void g() {
        if (!this.f) {
            throw new IllegalStateException("Call setup() first!");
        }
        if (!this.g) {
            throw new IllegalStateException("Call onActivityCreate() first!");
        }
    }

    protected void a(String str) {
        g();
        if (this.e.size() >= 1 && this.c != null && this.b.a() && this.c != null && !this.c.c(str)) {
            this.c.b(str);
        }
    }

    protected void b(String str) {
        g();
        if (this.e.size() >= 1 && this.b.a()) {
            int i2 = 0;
            while (true) {
                int i3 = i2;
                if (i3 < this.e.size()) {
                    h hVar = this.e.get(i3);
                    if (hVar != null && !hVar.c(str)) {
                        hVar.b(str);
                    }
                    i2 = i3 + 1;
                } else {
                    return;
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void a(ArrayList<h> arrayList, b bVar, int i2, final g gVar, f fVar) {
        c cVar = null;
        Object[] objArr = 0;
        if (arrayList == null) {
            throw new IllegalArgumentException("List cannot be null!");
        }
        if (fVar == null) {
            throw new IllegalArgumentException("Conditions cannot be null!");
        }
        this.b = fVar;
        this.e = new ArrayList<>();
        this.h = i2;
        switch (f()[bVar.ordinal()]) {
            case 1:
                this.f353a = new e(this, objArr == true ? 1 : 0);
                break;
            case 2:
                this.f353a = new c(this, cVar);
                break;
        }
        g gVar2 = new g() { // from class: com.c.a.a.1
            @Override // com.c.a.g
            public void a(String str) {
                gVar.a(str);
            }

            @Override // com.c.a.g
            public void b(String str) {
                gVar.b(str);
            }

            @Override // com.c.a.g
            public void c(String str) {
                gVar.c(str);
            }

            @Override // com.c.a.g
            public void d(String str) {
                gVar.d(str);
            }
        };
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            h hVar = arrayList.get(i3);
            if (fVar.a(hVar)) {
                hVar.a(gVar2);
                this.e.add(hVar);
            }
        }
        if (arrayList.size() > 0) {
            this.c = arrayList.get(0);
        } else {
            Log.d("BaseInterstitialManager", "ProvidersList size is 0");
        }
        this.f = true;
    }

    protected void c(String str) {
        g();
        if (this.b.a() && this.e.size() > 0) {
            this.f353a.a(str);
        }
    }

    public boolean e() {
        g();
        if (this.b.a()) {
            return this.f353a.b("SinglePlayer_EndLevel");
        }
        return false;
    }
}
