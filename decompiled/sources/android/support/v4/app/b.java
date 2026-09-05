package android.support.v4.app;

import android.util.Log;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;

/* JADX INFO: compiled from: BackStackRecord.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class b extends m implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final k f12a;
    c b;
    c c;
    int d;
    int e;
    int f;
    int g;
    int h;
    int i;
    int j;
    boolean k;
    String m;
    boolean n;
    int p;
    CharSequence q;
    int r;
    CharSequence s;
    boolean l = true;
    int o = -1;

    public String toString() {
        StringBuilder sb = new StringBuilder(XMLChar.MASK_NCNAME);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.o >= 0) {
            sb.append(" #");
            sb.append(this.o);
        }
        if (this.m != null) {
            sb.append(" ");
            sb.append(this.m);
        }
        sb.append("}");
        return sb.toString();
    }

    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        a(str, printWriter, true);
    }

    public void a(String str, PrintWriter printWriter, boolean z) {
        String str2;
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.m);
            printWriter.print(" mIndex=");
            printWriter.print(this.o);
            printWriter.print(" mCommitted=");
            printWriter.println(this.n);
            if (this.i != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.i));
                printWriter.print(" mTransitionStyle=#");
                printWriter.println(Integer.toHexString(this.j));
            }
            if (this.e != 0 || this.f != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.e));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.f));
            }
            if (this.g != 0 || this.h != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.g));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.h));
            }
            if (this.p != 0 || this.q != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.p));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.q);
            }
            if (this.r != 0 || this.s != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.r));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.s);
            }
        }
        if (this.b != null) {
            printWriter.print(str);
            printWriter.println("Operations:");
            String str3 = str + "    ";
            int i = 0;
            c cVar = this.b;
            while (cVar != null) {
                switch (cVar.c) {
                    case 0:
                        str2 = "NULL";
                        break;
                    case 1:
                        str2 = "ADD";
                        break;
                    case 2:
                        str2 = "REPLACE";
                        break;
                    case 3:
                        str2 = "REMOVE";
                        break;
                    case 4:
                        str2 = "HIDE";
                        break;
                    case 5:
                        str2 = "SHOW";
                        break;
                    case 6:
                        str2 = "DETACH";
                        break;
                    case 7:
                        str2 = "ATTACH";
                        break;
                    default:
                        str2 = "cmd=" + cVar.c;
                        break;
                }
                printWriter.print(str);
                printWriter.print("  Op #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.print(str2);
                printWriter.print(" ");
                printWriter.println(cVar.d);
                if (z) {
                    if (cVar.e != 0 || cVar.f != 0) {
                        printWriter.print(str);
                        printWriter.print("enterAnim=#");
                        printWriter.print(Integer.toHexString(cVar.e));
                        printWriter.print(" exitAnim=#");
                        printWriter.println(Integer.toHexString(cVar.f));
                    }
                    if (cVar.g != 0 || cVar.h != 0) {
                        printWriter.print(str);
                        printWriter.print("popEnterAnim=#");
                        printWriter.print(Integer.toHexString(cVar.g));
                        printWriter.print(" popExitAnim=#");
                        printWriter.println(Integer.toHexString(cVar.h));
                    }
                }
                if (cVar.i != null && cVar.i.size() > 0) {
                    for (int i2 = 0; i2 < cVar.i.size(); i2++) {
                        printWriter.print(str3);
                        if (cVar.i.size() == 1) {
                            printWriter.print("Removed: ");
                        } else {
                            if (i2 == 0) {
                                printWriter.println("Removed:");
                            }
                            printWriter.print(str3);
                            printWriter.print("  #");
                            printWriter.print(i2);
                            printWriter.print(": ");
                        }
                        printWriter.println(cVar.i.get(i2));
                    }
                }
                cVar = cVar.f13a;
                i++;
            }
        }
    }

    public b(k kVar) {
        this.f12a = kVar;
    }

    void a(c cVar) {
        if (this.b == null) {
            this.c = cVar;
            this.b = cVar;
        } else {
            cVar.b = this.c;
            this.c.f13a = cVar;
            this.c = cVar;
        }
        cVar.e = this.e;
        cVar.f = this.f;
        cVar.g = this.g;
        cVar.h = this.h;
        this.d++;
    }

    @Override // android.support.v4.app.m
    public m a(int i, Fragment fragment, String str) {
        a(i, fragment, str, 1);
        return this;
    }

    private void a(int i, Fragment fragment, String str, int i2) {
        fragment.mFragmentManager = this.f12a;
        if (str != null) {
            if (fragment.mTag != null && !str.equals(fragment.mTag)) {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.mTag + " now " + str);
            }
            fragment.mTag = str;
        }
        if (i != 0) {
            if (fragment.mFragmentId != 0 && fragment.mFragmentId != i) {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.mFragmentId + " now " + i);
            }
            fragment.mFragmentId = i;
            fragment.mContainerId = i;
        }
        c cVar = new c();
        cVar.c = i2;
        cVar.d = fragment;
        a(cVar);
    }

    @Override // android.support.v4.app.m
    public m a(Fragment fragment) {
        c cVar = new c();
        cVar.c = 6;
        cVar.d = fragment;
        a(cVar);
        return this;
    }

    @Override // android.support.v4.app.m
    public m b(Fragment fragment) {
        c cVar = new c();
        cVar.c = 7;
        cVar.d = fragment;
        a(cVar);
        return this;
    }

    void a(int i) {
        if (this.k) {
            if (k.f17a) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i);
            }
            for (c cVar = this.b; cVar != null; cVar = cVar.f13a) {
                if (cVar.d != null) {
                    cVar.d.mBackStackNesting += i;
                    if (k.f17a) {
                        Log.v("FragmentManager", "Bump nesting of " + cVar.d + " to " + cVar.d.mBackStackNesting);
                    }
                }
                if (cVar.i != null) {
                    for (int size = cVar.i.size() - 1; size >= 0; size--) {
                        Fragment fragment = cVar.i.get(size);
                        fragment.mBackStackNesting += i;
                        if (k.f17a) {
                            Log.v("FragmentManager", "Bump nesting of " + fragment + " to " + fragment.mBackStackNesting);
                        }
                    }
                }
            }
        }
    }

    @Override // android.support.v4.app.m
    public int a() {
        return a(false);
    }

    int a(boolean z) {
        if (this.n) {
            throw new IllegalStateException("commit already called");
        }
        if (k.f17a) {
            Log.v("FragmentManager", "Commit: " + this);
            a("  ", (FileDescriptor) null, new PrintWriter(new android.support.v4.c.d("FragmentManager")), (String[]) null);
        }
        this.n = true;
        if (this.k) {
            this.o = this.f12a.a(this);
        } else {
            this.o = -1;
        }
        this.f12a.a(this, z);
        return this.o;
    }

    @Override // java.lang.Runnable
    public void run() {
        Fragment fragment;
        if (k.f17a) {
            Log.v("FragmentManager", "Run: " + this);
        }
        if (this.k && this.o < 0) {
            throw new IllegalStateException("addToBackStack() called after commit()");
        }
        a(1);
        for (c cVar = this.b; cVar != null; cVar = cVar.f13a) {
            switch (cVar.c) {
                case 1:
                    Fragment fragment2 = cVar.d;
                    fragment2.mNextAnim = cVar.e;
                    this.f12a.a(fragment2, false);
                    break;
                case 2:
                    Fragment fragment3 = cVar.d;
                    if (this.f12a.g != null) {
                        fragment = fragment3;
                        for (int i = 0; i < this.f12a.g.size(); i++) {
                            Fragment fragment4 = this.f12a.g.get(i);
                            if (k.f17a) {
                                Log.v("FragmentManager", "OP_REPLACE: adding=" + fragment + " old=" + fragment4);
                            }
                            if (fragment == null || fragment4.mContainerId == fragment.mContainerId) {
                                if (fragment4 == fragment) {
                                    fragment = null;
                                    cVar.d = null;
                                } else {
                                    if (cVar.i == null) {
                                        cVar.i = new ArrayList<>();
                                    }
                                    cVar.i.add(fragment4);
                                    fragment4.mNextAnim = cVar.f;
                                    if (this.k) {
                                        fragment4.mBackStackNesting++;
                                        if (k.f17a) {
                                            Log.v("FragmentManager", "Bump nesting of " + fragment4 + " to " + fragment4.mBackStackNesting);
                                        }
                                    }
                                    this.f12a.a(fragment4, this.i, this.j);
                                }
                            }
                        }
                    } else {
                        fragment = fragment3;
                    }
                    if (fragment != null) {
                        fragment.mNextAnim = cVar.e;
                        this.f12a.a(fragment, false);
                    }
                    break;
                case 3:
                    Fragment fragment5 = cVar.d;
                    fragment5.mNextAnim = cVar.f;
                    this.f12a.a(fragment5, this.i, this.j);
                    break;
                case 4:
                    Fragment fragment6 = cVar.d;
                    fragment6.mNextAnim = cVar.f;
                    this.f12a.b(fragment6, this.i, this.j);
                    break;
                case 5:
                    Fragment fragment7 = cVar.d;
                    fragment7.mNextAnim = cVar.e;
                    this.f12a.c(fragment7, this.i, this.j);
                    break;
                case 6:
                    Fragment fragment8 = cVar.d;
                    fragment8.mNextAnim = cVar.f;
                    this.f12a.d(fragment8, this.i, this.j);
                    break;
                case 7:
                    Fragment fragment9 = cVar.d;
                    fragment9.mNextAnim = cVar.e;
                    this.f12a.e(fragment9, this.i, this.j);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + cVar.c);
            }
        }
        this.f12a.a(this.f12a.n, this.i, this.j, true);
        if (this.k) {
            this.f12a.b(this);
        }
    }

    public void b(boolean z) {
        if (k.f17a) {
            Log.v("FragmentManager", "popFromBackStack: " + this);
            a("  ", (FileDescriptor) null, new PrintWriter(new android.support.v4.c.d("FragmentManager")), (String[]) null);
        }
        a(-1);
        for (c cVar = this.c; cVar != null; cVar = cVar.b) {
            switch (cVar.c) {
                case 1:
                    Fragment fragment = cVar.d;
                    fragment.mNextAnim = cVar.h;
                    this.f12a.a(fragment, k.c(this.i), this.j);
                    break;
                case 2:
                    Fragment fragment2 = cVar.d;
                    if (fragment2 != null) {
                        fragment2.mNextAnim = cVar.h;
                        this.f12a.a(fragment2, k.c(this.i), this.j);
                    }
                    if (cVar.i != null) {
                        for (int i = 0; i < cVar.i.size(); i++) {
                            Fragment fragment3 = cVar.i.get(i);
                            fragment3.mNextAnim = cVar.g;
                            this.f12a.a(fragment3, false);
                        }
                    }
                    break;
                case 3:
                    Fragment fragment4 = cVar.d;
                    fragment4.mNextAnim = cVar.g;
                    this.f12a.a(fragment4, false);
                    break;
                case 4:
                    Fragment fragment5 = cVar.d;
                    fragment5.mNextAnim = cVar.g;
                    this.f12a.c(fragment5, k.c(this.i), this.j);
                    break;
                case 5:
                    Fragment fragment6 = cVar.d;
                    fragment6.mNextAnim = cVar.h;
                    this.f12a.b(fragment6, k.c(this.i), this.j);
                    break;
                case 6:
                    Fragment fragment7 = cVar.d;
                    fragment7.mNextAnim = cVar.g;
                    this.f12a.e(fragment7, k.c(this.i), this.j);
                    break;
                case 7:
                    Fragment fragment8 = cVar.d;
                    fragment8.mNextAnim = cVar.g;
                    this.f12a.d(fragment8, k.c(this.i), this.j);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + cVar.c);
            }
        }
        if (z) {
            this.f12a.a(this.f12a.n, k.c(this.i), this.j, true);
        }
        if (this.o >= 0) {
            this.f12a.b(this.o);
            this.o = -1;
        }
    }

    public String b() {
        return this.m;
    }
}
