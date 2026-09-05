package org.a.a.a.a.a;

/* JADX INFO: compiled from: ClientComms.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class b implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    a f1572a;
    Thread b;
    org.a.a.a.a.p c;
    org.a.a.a.a.a.b.d d;
    final /* synthetic */ a e;

    b(a aVar, a aVar2, org.a.a.a.a.p pVar, org.a.a.a.a.a.b.d dVar) {
        this.e = aVar;
        this.f1572a = null;
        this.b = null;
        this.f1572a = aVar2;
        this.c = pVar;
        this.d = dVar;
        this.b = new Thread(this, "MQTT Con: " + aVar.g().a());
    }

    void a() {
        this.b.start();
    }

    @Override // java.lang.Runnable
    public void run() {
        org.a.a.a.a.k kVarA = null;
        this.e.m.a(a.l, "connectBG:run", "220");
        try {
            for (org.a.a.a.a.j jVar : this.e.i.b()) {
                jVar.f1606a.a((org.a.a.a.a.k) null);
            }
            this.e.i.a(this.c, this.d);
            this.e.c.a();
            this.e.d = new f(this.f1572a, this.e.g, this.e.i, this.e.c.b());
            this.e.d.a("MQTT Rec: " + this.e.g().a());
            this.e.e = new g(this.f1572a, this.e.g, this.e.i, this.e.c.c());
            this.e.e.a("MQTT Snd: " + this.e.g().a());
            this.e.f.a("MQTT Call: " + this.e.g().a());
            this.e.a(this.d, this.c);
        } catch (org.a.a.a.a.k e) {
            this.e.m.a(a.l, "connectBG:run", "212", null, e);
            kVarA = e;
        } catch (Exception e2) {
            this.e.m.a(a.l, "connectBG:run", "209", null, e2);
            kVarA = i.a(e2);
        }
        if (kVarA != null) {
            this.e.a(this.c, kVarA);
        }
    }
}
