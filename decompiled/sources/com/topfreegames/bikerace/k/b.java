package com.topfreegames.bikerace.k;

import com.topfreegames.bikerace.ap;
import java.util.ArrayList;

/* JADX INFO: compiled from: RemoteData.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class b implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ a f1278a;
    private e b;
    private ArrayList<String> c = new ArrayList<>();
    private ArrayList<Exception> d = new ArrayList<>();

    public b(a aVar, e eVar) {
        this.f1278a = aVar;
        this.b = eVar;
    }

    public void a() {
        if (this.b != null) {
            if (this.d.size() > 0) {
                this.b.a(this.d.get(0));
            } else if (this.c.size() > 0) {
                this.b.a(this.c.get(0));
            } else {
                this.b.b();
            }
        }
    }

    @Override // com.topfreegames.bikerace.k.e
    public void a(Exception exc) {
        this.d.add(exc);
        if (ap.d()) {
            exc.printStackTrace();
        }
    }

    @Override // com.topfreegames.bikerace.k.e
    public void a(String str) {
        this.c.add(str);
        if (ap.d()) {
            System.err.println("Remote Data failed: " + str);
        }
    }

    @Override // com.topfreegames.bikerace.k.e
    public void b() {
    }
}
