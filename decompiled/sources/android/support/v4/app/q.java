package android.support.v4.app;

import android.os.Bundle;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

/* JADX INFO: compiled from: LoaderManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class q implements android.support.v4.a.b<Object> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final int f22a;
    final Bundle b;
    o<Object> c;
    android.support.v4.a.a<Object> d;
    boolean e;
    boolean f;
    Object g;
    boolean h;
    boolean i;
    boolean j;
    boolean k;
    boolean l;
    boolean m;
    q n;
    final /* synthetic */ p o;

    public q(p pVar, int i, Bundle bundle, o<Object> oVar) {
        this.o = pVar;
        this.f22a = i;
        this.b = bundle;
        this.c = oVar;
    }

    void a() {
        if (this.i && this.j) {
            this.h = true;
            return;
        }
        if (!this.h) {
            this.h = true;
            if (p.f21a) {
                Log.v("LoaderManager", "  Starting: " + this);
            }
            if (this.d == null && this.c != null) {
                this.d = this.c.onCreateLoader(this.f22a, this.b);
            }
            if (this.d != null) {
                if (this.d.getClass().isMemberClass() && !Modifier.isStatic(this.d.getClass().getModifiers())) {
                    throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + this.d);
                }
                if (!this.m) {
                    this.d.registerListener(this.f22a, this);
                    this.m = true;
                }
                this.d.startLoading();
            }
        }
    }

    void b() {
        if (p.f21a) {
            Log.v("LoaderManager", "  Retaining: " + this);
        }
        this.i = true;
        this.j = this.h;
        this.h = false;
        this.c = null;
    }

    void c() {
        if (this.i) {
            if (p.f21a) {
                Log.v("LoaderManager", "  Finished Retaining: " + this);
            }
            this.i = false;
            if (this.h != this.j && !this.h) {
                e();
            }
        }
        if (this.h && this.e && !this.k) {
            b(this.d, this.g);
        }
    }

    void d() {
        if (this.h && this.k) {
            this.k = false;
            if (this.e) {
                b(this.d, this.g);
            }
        }
    }

    void e() {
        if (p.f21a) {
            Log.v("LoaderManager", "  Stopping: " + this);
        }
        this.h = false;
        if (!this.i && this.d != null && this.m) {
            this.m = false;
            this.d.unregisterListener(this);
            this.d.stopLoading();
        }
    }

    void f() {
        String str;
        if (p.f21a) {
            Log.v("LoaderManager", "  Destroying: " + this);
        }
        this.l = true;
        boolean z = this.f;
        this.f = false;
        if (this.c != null && this.d != null && this.e && z) {
            if (p.f21a) {
                Log.v("LoaderManager", "  Reseting: " + this);
            }
            if (this.o.e != null) {
                String str2 = this.o.e.b.u;
                this.o.e.b.u = "onLoaderReset";
                str = str2;
            } else {
                str = null;
            }
            try {
                this.c.onLoaderReset(this.d);
                if (this.o.e != null) {
                    this.o.e.b.u = str;
                }
            } catch (Throwable th) {
                if (this.o.e != null) {
                    this.o.e.b.u = str;
                }
                throw th;
            }
        }
        this.c = null;
        this.g = null;
        this.e = false;
        if (this.d != null) {
            if (this.m) {
                this.m = false;
                this.d.unregisterListener(this);
            }
            this.d.reset();
        }
        if (this.n != null) {
            this.n.f();
        }
    }

    @Override // android.support.v4.a.b
    public void a(android.support.v4.a.a<Object> aVar, Object obj) {
        if (p.f21a) {
            Log.v("LoaderManager", "onLoadComplete: " + this);
        }
        if (this.l) {
            if (p.f21a) {
                Log.v("LoaderManager", "  Ignoring load complete -- destroyed");
                return;
            }
            return;
        }
        if (this.o.b.a(this.f22a) != this) {
            if (p.f21a) {
                Log.v("LoaderManager", "  Ignoring load complete -- not active");
                return;
            }
            return;
        }
        q qVar = this.n;
        if (qVar != null) {
            if (p.f21a) {
                Log.v("LoaderManager", "  Switching to pending loader: " + qVar);
            }
            this.n = null;
            this.o.b.b(this.f22a, null);
            f();
            this.o.a(qVar);
            return;
        }
        if (this.g != obj || !this.e) {
            this.g = obj;
            this.e = true;
            if (this.h) {
                b(aVar, obj);
            }
        }
        q qVarA = this.o.c.a(this.f22a);
        if (qVarA != null && qVarA != this) {
            qVarA.f = false;
            qVarA.f();
            this.o.c.c(this.f22a);
        }
        if (this.o.e != null && !this.o.a()) {
            this.o.e.b.d();
        }
    }

    void b(android.support.v4.a.a<Object> aVar, Object obj) {
        String str;
        if (this.c != null) {
            if (this.o.e == null) {
                str = null;
            } else {
                String str2 = this.o.e.b.u;
                this.o.e.b.u = "onLoadFinished";
                str = str2;
            }
            try {
                if (p.f21a) {
                    Log.v("LoaderManager", "  onLoadFinished in " + aVar + ": " + aVar.dataToString(obj));
                }
                this.c.onLoadFinished(aVar, obj);
                if (this.o.e != null) {
                    this.o.e.b.u = str;
                }
                this.f = true;
            } catch (Throwable th) {
                if (this.o.e != null) {
                    this.o.e.b.u = str;
                }
                throw th;
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append("LoaderInfo{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" #");
        sb.append(this.f22a);
        sb.append(" : ");
        android.support.v4.c.c.a(this.d, sb);
        sb.append("}}");
        return sb.toString();
    }

    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mId=");
        printWriter.print(this.f22a);
        printWriter.print(" mArgs=");
        printWriter.println(this.b);
        printWriter.print(str);
        printWriter.print("mCallbacks=");
        printWriter.println(this.c);
        printWriter.print(str);
        printWriter.print("mLoader=");
        printWriter.println(this.d);
        if (this.d != null) {
            this.d.dump(str + "  ", fileDescriptor, printWriter, strArr);
        }
        if (this.e || this.f) {
            printWriter.print(str);
            printWriter.print("mHaveData=");
            printWriter.print(this.e);
            printWriter.print("  mDeliveredData=");
            printWriter.println(this.f);
            printWriter.print(str);
            printWriter.print("mData=");
            printWriter.println(this.g);
        }
        printWriter.print(str);
        printWriter.print("mStarted=");
        printWriter.print(this.h);
        printWriter.print(" mReportNextStart=");
        printWriter.print(this.k);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.l);
        printWriter.print(str);
        printWriter.print("mRetaining=");
        printWriter.print(this.i);
        printWriter.print(" mRetainingStarted=");
        printWriter.print(this.j);
        printWriter.print(" mListenerRegistered=");
        printWriter.println(this.m);
        if (this.n != null) {
            printWriter.print(str);
            printWriter.println("Pending Loader ");
            printWriter.print(this.n);
            printWriter.println(":");
            this.n.a(str + "  ", fileDescriptor, printWriter, strArr);
        }
    }
}
