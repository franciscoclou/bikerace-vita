package org.a.a.a.a;

/* JADX INFO: compiled from: MqttException.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class k extends Exception {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1604a;
    private Throwable b;

    public k(int i) {
        this.f1604a = i;
    }

    public k(Throwable th) {
        this.f1604a = 0;
        this.b = th;
    }

    public k(int i, Throwable th) {
        this.f1604a = i;
        this.b = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.b;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return org.a.a.a.a.a.k.a(this.f1604a);
    }

    @Override // java.lang.Throwable
    public String toString() {
        String str = getMessage() + " (" + this.f1604a + ")";
        if (this.b != null) {
            return str + " - " + this.b.toString();
        }
        return str;
    }
}
