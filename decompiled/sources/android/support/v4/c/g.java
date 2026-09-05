package android.support.v4.c;

import java.util.Iterator;

/* JADX INFO: compiled from: MapCollections.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class g<T> implements Iterator<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final int f30a;
    int b;
    int c;
    boolean d = false;
    final /* synthetic */ f e;

    g(f fVar, int i) {
        this.e = fVar;
        this.f30a = i;
        this.b = fVar.a();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.c < this.b;
    }

    @Override // java.util.Iterator
    public T next() {
        T t = (T) this.e.a(this.c, this.f30a);
        this.c++;
        this.d = true;
        return t;
    }

    @Override // java.util.Iterator
    public void remove() {
        if (!this.d) {
            throw new IllegalStateException();
        }
        this.c--;
        this.b--;
        this.d = false;
        this.e.a(this.c);
    }
}
