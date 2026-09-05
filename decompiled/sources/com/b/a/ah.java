package com.b.a;

import com.b.a.a.cm;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class ah extends com.b.a.a.az {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float f319a;
    private /* synthetic */ af b;

    ah(af afVar, float f) {
        this.b = afVar;
        this.f319a = f;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:26:0x00b5 A[Catch: Exception -> 0x008e, TryCatch #1 {Exception -> 0x008e, blocks: (B:2:0x0000, B:5:0x002b, B:6:0x0034, B:8:0x0048, B:10:0x004e, B:12:0x0054, B:13:0x007e, B:15:0x0084, B:24:0x00af, B:26:0x00b5, B:28:0x00c3, B:29:0x00ed, B:31:0x00f3, B:32:0x00ff, B:34:0x010b, B:36:0x0144, B:39:0x014b, B:22:0x00a6), top: B:43:0x0000, inners: #0, #2 }] */
    /* JADX WARN: Code duplicated, block: B:31:0x00f3 A[Catch: Exception -> 0x008e, LOOP:2: B:29:0x00ed->B:31:0x00f3, LOOP_END, TryCatch #1 {Exception -> 0x008e, blocks: (B:2:0x0000, B:5:0x002b, B:6:0x0034, B:8:0x0048, B:10:0x004e, B:12:0x0054, B:13:0x007e, B:15:0x0084, B:24:0x00af, B:26:0x00b5, B:28:0x00c3, B:29:0x00ed, B:31:0x00f3, B:32:0x00ff, B:34:0x010b, B:36:0x0144, B:39:0x014b, B:22:0x00a6), top: B:43:0x0000, inners: #0, #2 }] */
    /* JADX WARN: Code duplicated, block: B:47:0x010b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:51:0x00af A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:8:0x0048 A[Catch: Exception -> 0x008e, TryCatch #1 {Exception -> 0x008e, blocks: (B:2:0x0000, B:5:0x002b, B:6:0x0034, B:8:0x0048, B:10:0x004e, B:12:0x0054, B:13:0x007e, B:15:0x0084, B:24:0x00af, B:26:0x00b5, B:28:0x00c3, B:29:0x00ed, B:31:0x00f3, B:32:0x00ff, B:34:0x010b, B:36:0x0144, B:39:0x014b, B:22:0x00a6), top: B:43:0x0000, inners: #0, #2 }] */
    @Override // com.b.a.a.az
    public final void a() {
        bc bcVarO;
        List<ad> listA;
        int i;
        Iterator<ad> it;
        try {
            cm.a().b().a("Crashlytics", "Starting report processing in " + this.f319a + " second(s)...");
            if (this.f319a > 0.0f) {
                try {
                    Thread.sleep((long) (this.f319a * 1000.0f));
                    d dVarA = d.a();
                    bcVarO = dVarA.o();
                    listA = this.b.a();
                    if (!bcVarO.a()) {
                        if (!listA.isEmpty() || dVarA.s()) {
                            i = 0;
                            while (!listA.isEmpty() && !d.a().o().a()) {
                                cm.a().b().a("Crashlytics", "Attempting to send " + listA.size() + " report(s)");
                                it = listA.iterator();
                                while (it.hasNext()) {
                                    this.b.a(it.next());
                                }
                                listA = this.b.a();
                                if (!listA.isEmpty()) {
                                    int i2 = i + 1;
                                    long j = af.c[Math.min(i, af.c.length - 1)];
                                    cm.a().b().a("Crashlytics", "Report submisson: scheduling delayed retry in " + j + " seconds");
                                    try {
                                        Thread.sleep(j * 1000);
                                        i = i2;
                                    } catch (InterruptedException e) {
                                        Thread.currentThread().interrupt();
                                    }
                                }
                            }
                        } else {
                            cm.a().b().a("Crashlytics", "User declined to send. Removing " + listA.size() + " Report(s).");
                            Iterator<ad> it2 = listA.iterator();
                            while (it2.hasNext()) {
                                it2.next().a();
                            }
                        }
                    }
                } catch (InterruptedException e2) {
                    Thread.currentThread().interrupt();
                }
            } else {
                d dVarA2 = d.a();
                bcVarO = dVarA2.o();
                listA = this.b.a();
                if (!bcVarO.a()) {
                    if (listA.isEmpty()) {
                        i = 0;
                        while (!listA.isEmpty()) {
                            cm.a().b().a("Crashlytics", "Attempting to send " + listA.size() + " report(s)");
                            it = listA.iterator();
                            while (it.hasNext()) {
                                this.b.a(it.next());
                            }
                            listA = this.b.a();
                            if (!listA.isEmpty()) {
                                int i3 = i + 1;
                                long j2 = af.c[Math.min(i, af.c.length - 1)];
                                cm.a().b().a("Crashlytics", "Report submisson: scheduling delayed retry in " + j2 + " seconds");
                                Thread.sleep(j2 * 1000);
                                i = i3;
                            }
                        }
                    } else {
                        i = 0;
                        while (!listA.isEmpty()) {
                            cm.a().b().a("Crashlytics", "Attempting to send " + listA.size() + " report(s)");
                            it = listA.iterator();
                            while (it.hasNext()) {
                                this.b.a(it.next());
                            }
                            listA = this.b.a();
                            if (!listA.isEmpty()) {
                                int i4 = i + 1;
                                long j3 = af.c[Math.min(i, af.c.length - 1)];
                                cm.a().b().a("Crashlytics", "Report submisson: scheduling delayed retry in " + j3 + " seconds");
                                Thread.sleep(j3 * 1000);
                                i = i4;
                            }
                        }
                    }
                }
            }
        } catch (Exception e3) {
            cm.a().b().a("Crashlytics", "An unexpected error occurred while attempting to upload crash reports.", e3);
        }
        af.a(this.b, null);
    }
}
