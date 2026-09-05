package com.applovin.impl.a;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ab implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ v f178a;
    private final String b;
    private final x c;
    private final w d;

    ab(v vVar, x xVar, w wVar) {
        this.f178a = vVar;
        this.b = xVar.e();
        this.c = xVar;
        this.d = wVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            aq.a();
            if (this.f178a.f214a.m()) {
                this.f178a.b.b(this.b, "Task re-scheduled...");
                this.f178a.a(this.c, this.d, 2000L);
                return;
            }
            if (!this.f178a.f214a.b()) {
                if (this.f178a.f214a.n()) {
                    this.f178a.f214a.o();
                } else {
                    this.f178a.b.c(this.b, "Task not executed, SDK is disabled");
                }
                this.c.a_();
                return;
            }
            this.f178a.b.b(this.b, "Task started execution...");
            this.c.run();
            long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
            this.f178a.b.b(this.b, "Task executed successfully in " + jCurrentTimeMillis2 + "ms.");
            o oVarL = this.f178a.f214a.l();
            oVarL.a(this.b + "_count");
            oVarL.a(this.b + "_time", jCurrentTimeMillis2);
        } catch (Throwable th) {
            this.f178a.b.b(this.b, "Task failed execution in " + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms.", th);
        }
    }
}
