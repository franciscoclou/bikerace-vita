package org.a.a.a.a.a;

/* JADX INFO: compiled from: ClientComms.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class c implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Thread f1586a = null;
    org.a.a.a.a.a.b.e b;
    long c;
    org.a.a.a.a.p d;
    final /* synthetic */ a e;

    c(a aVar, org.a.a.a.a.a.b.e eVar, long j, org.a.a.a.a.p pVar) {
        this.e = aVar;
        this.b = eVar;
        this.c = j;
        this.d = pVar;
    }

    void a() {
        this.f1586a = new Thread(this, "MQTT Disc: " + this.e.g().a());
        this.f1586a.start();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // java.lang.Runnable
    public void run() {
        this.e.m.a(a.l, "disconnectBG:run", "221");
        this.e.g.b(this.c);
        try {
            this.e.a(this.b, this.d);
            this.d.f1606a.g();
        } catch (org.a.a.a.a.k e) {
        } finally {
            this.d.f1606a.a(null, null);
            this.e.a(this.d, (org.a.a.a.a.k) null);
        }
    }
}
