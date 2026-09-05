package org.a.a.a.a;

/* JADX INFO: compiled from: MqttClient.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class g {
    static final String c = g.class.getName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected e f1602a;
    protected long b = -1;
    public org.a.a.a.a.b.a d = org.a.a.a.a.b.b.a("org.eclipse.paho.client.mqttv3.internal.nls.logcat", c);

    public g(String str, String str2, h hVar) {
        this.f1602a = null;
        this.f1602a = new e(str, str2, hVar);
    }

    public void a() {
        a(new i());
    }

    public void a(i iVar) {
        this.f1602a.a(iVar, (Object) null, (a) null).a(c());
    }

    public void b() {
        a(30000L);
    }

    public void a(long j) {
        this.f1602a.a(j, (Object) null, (a) null).a();
    }

    public void a(String str) {
        a(new String[]{str}, new int[]{1});
    }

    public void a(String[] strArr, int[] iArr) {
        this.f1602a.a(strArr, iArr, (Object) null, (a) null).a(c());
    }

    public void a(String str, l lVar) {
        this.f1602a.a(str, lVar, (Object) null, (a) null).a(c());
    }

    public long c() {
        return this.b;
    }

    public void a(f fVar) {
        this.f1602a.a(fVar);
    }

    public static String d() {
        return e.b();
    }
}
