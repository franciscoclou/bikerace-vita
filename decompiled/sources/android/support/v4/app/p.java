package android.support.v4.app;

import android.os.Bundle;
import android.util.Log;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: LoaderManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class p extends n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static boolean f21a = false;
    final android.support.v4.c.m<q> b = new android.support.v4.c.m<>();
    final android.support.v4.c.m<q> c = new android.support.v4.c.m<>();
    final String d;
    e e;
    boolean f;
    boolean g;
    boolean h;

    p(String str, e eVar, boolean z) {
        this.d = str;
        this.e = eVar;
        this.f = z;
    }

    void a(e eVar) {
        this.e = eVar;
    }

    private q b(int i, Bundle bundle, o<Object> oVar) {
        q qVar = new q(this, i, bundle, oVar);
        qVar.d = oVar.onCreateLoader(i, bundle);
        return qVar;
    }

    private q c(int i, Bundle bundle, o<Object> oVar) {
        try {
            this.h = true;
            q qVarB = b(i, bundle, oVar);
            a(qVarB);
            return qVarB;
        } finally {
            this.h = false;
        }
    }

    void a(q qVar) {
        this.b.b(qVar.f22a, qVar);
        if (this.f) {
            qVar.a();
        }
    }

    @Override // android.support.v4.app.n
    public <D> android.support.v4.a.a<D> a(int i, Bundle bundle, o<D> oVar) {
        if (this.h) {
            throw new IllegalStateException("Called while creating a loader");
        }
        q qVarA = this.b.a(i);
        if (f21a) {
            Log.v("LoaderManager", "initLoader in " + this + ": args=" + bundle);
        }
        if (qVarA == null) {
            qVarA = c(i, bundle, oVar);
            if (f21a) {
                Log.v("LoaderManager", "  Created new loader " + qVarA);
            }
        } else {
            if (f21a) {
                Log.v("LoaderManager", "  Re-using existing loader " + qVarA);
            }
            qVarA.c = oVar;
        }
        if (qVarA.e && this.f) {
            qVarA.b(qVarA.d, qVarA.g);
        }
        return (android.support.v4.a.a<D>) qVarA.d;
    }

    void b() {
        if (f21a) {
            Log.v("LoaderManager", "Starting in " + this);
        }
        if (this.f) {
            RuntimeException runtimeException = new RuntimeException("here");
            runtimeException.fillInStackTrace();
            Log.w("LoaderManager", "Called doStart when already started: " + this, runtimeException);
        } else {
            this.f = true;
            for (int iB = this.b.b() - 1; iB >= 0; iB--) {
                this.b.e(iB).a();
            }
        }
    }

    void c() {
        if (f21a) {
            Log.v("LoaderManager", "Stopping in " + this);
        }
        if (!this.f) {
            RuntimeException runtimeException = new RuntimeException("here");
            runtimeException.fillInStackTrace();
            Log.w("LoaderManager", "Called doStop when not started: " + this, runtimeException);
        } else {
            for (int iB = this.b.b() - 1; iB >= 0; iB--) {
                this.b.e(iB).e();
            }
            this.f = false;
        }
    }

    void d() {
        if (f21a) {
            Log.v("LoaderManager", "Retaining in " + this);
        }
        if (!this.f) {
            RuntimeException runtimeException = new RuntimeException("here");
            runtimeException.fillInStackTrace();
            Log.w("LoaderManager", "Called doRetain when not started: " + this, runtimeException);
        } else {
            this.g = true;
            this.f = false;
            for (int iB = this.b.b() - 1; iB >= 0; iB--) {
                this.b.e(iB).b();
            }
        }
    }

    void e() {
        if (this.g) {
            if (f21a) {
                Log.v("LoaderManager", "Finished Retaining in " + this);
            }
            this.g = false;
            for (int iB = this.b.b() - 1; iB >= 0; iB--) {
                this.b.e(iB).c();
            }
        }
    }

    void f() {
        for (int iB = this.b.b() - 1; iB >= 0; iB--) {
            this.b.e(iB).k = true;
        }
    }

    void g() {
        for (int iB = this.b.b() - 1; iB >= 0; iB--) {
            this.b.e(iB).d();
        }
    }

    void h() {
        if (!this.g) {
            if (f21a) {
                Log.v("LoaderManager", "Destroying Active in " + this);
            }
            for (int iB = this.b.b() - 1; iB >= 0; iB--) {
                this.b.e(iB).f();
            }
            this.b.c();
        }
        if (f21a) {
            Log.v("LoaderManager", "Destroying Inactive in " + this);
        }
        for (int iB2 = this.c.b() - 1; iB2 >= 0; iB2--) {
            this.c.e(iB2).f();
        }
        this.c.c();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(XMLChar.MASK_NCNAME);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        android.support.v4.c.c.a(this.e, sb);
        sb.append("}}");
        return sb.toString();
    }

    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        if (this.b.b() > 0) {
            printWriter.print(str);
            printWriter.println("Active Loaders:");
            String str2 = str + "    ";
            for (int i = 0; i < this.b.b(); i++) {
                q qVarE = this.b.e(i);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(this.b.d(i));
                printWriter.print(": ");
                printWriter.println(qVarE.toString());
                qVarE.a(str2, fileDescriptor, printWriter, strArr);
            }
        }
        if (this.c.b() > 0) {
            printWriter.print(str);
            printWriter.println("Inactive Loaders:");
            String str3 = str + "    ";
            for (int i2 = 0; i2 < this.c.b(); i2++) {
                q qVarE2 = this.c.e(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(this.c.d(i2));
                printWriter.print(": ");
                printWriter.println(qVarE2.toString());
                qVarE2.a(str3, fileDescriptor, printWriter, strArr);
            }
        }
    }

    @Override // android.support.v4.app.n
    public boolean a() {
        int iB = this.b.b();
        boolean z = false;
        for (int i = 0; i < iB; i++) {
            q qVarE = this.b.e(i);
            z |= qVarE.h && !qVarE.f;
        }
        return z;
    }
}
