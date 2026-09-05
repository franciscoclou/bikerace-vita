package android.support.v4.view.a;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import com.facebook.internal.NativeProtocol;

/* JADX INFO: compiled from: AccessibilityNodeInfoCompat.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final c f39a;
    private final Object b;

    static {
        if (Build.VERSION.SDK_INT >= 19) {
            f39a = new f();
            return;
        }
        if (Build.VERSION.SDK_INT >= 18) {
            f39a = new e();
            return;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            f39a = new d();
        } else if (Build.VERSION.SDK_INT >= 14) {
            f39a = new b();
        } else {
            f39a = new g();
        }
    }

    static a a(Object obj) {
        if (obj != null) {
            return new a(obj);
        }
        return null;
    }

    public a(Object obj) {
        this.b = obj;
    }

    public Object a() {
        return this.b;
    }

    public static a a(a aVar) {
        return a(f39a.a(aVar.b));
    }

    public void a(View view) {
        f39a.c(this.b, view);
    }

    public void b(View view) {
        f39a.a(this.b, view);
    }

    public int b() {
        return f39a.b(this.b);
    }

    public void a(int i) {
        f39a.a(this.b, i);
    }

    public void b(int i) {
        f39a.b(this.b, i);
    }

    public int c() {
        return f39a.r(this.b);
    }

    public void c(View view) {
        f39a.b(this.b, view);
    }

    public void a(Rect rect) {
        f39a.a(this.b, rect);
    }

    public void b(Rect rect) {
        f39a.c(this.b, rect);
    }

    public void c(Rect rect) {
        f39a.b(this.b, rect);
    }

    public void d(Rect rect) {
        f39a.d(this.b, rect);
    }

    public boolean d() {
        return f39a.g(this.b);
    }

    public boolean e() {
        return f39a.h(this.b);
    }

    public boolean f() {
        return f39a.k(this.b);
    }

    public void a(boolean z) {
        f39a.c(this.b, z);
    }

    public boolean g() {
        return f39a.l(this.b);
    }

    public void b(boolean z) {
        f39a.d(this.b, z);
    }

    public boolean h() {
        return f39a.s(this.b);
    }

    public void c(boolean z) {
        f39a.g(this.b, z);
    }

    public boolean i() {
        return f39a.t(this.b);
    }

    public void d(boolean z) {
        f39a.h(this.b, z);
    }

    public boolean j() {
        return f39a.p(this.b);
    }

    public void e(boolean z) {
        f39a.f(this.b, z);
    }

    public boolean k() {
        return f39a.i(this.b);
    }

    public void f(boolean z) {
        f39a.a(this.b, z);
    }

    public boolean l() {
        return f39a.m(this.b);
    }

    public void g(boolean z) {
        f39a.e(this.b, z);
    }

    public boolean m() {
        return f39a.j(this.b);
    }

    public void h(boolean z) {
        f39a.b(this.b, z);
    }

    public boolean n() {
        return f39a.n(this.b);
    }

    public boolean o() {
        return f39a.o(this.b);
    }

    public CharSequence p() {
        return f39a.e(this.b);
    }

    public void a(CharSequence charSequence) {
        f39a.c(this.b, charSequence);
    }

    public CharSequence q() {
        return f39a.c(this.b);
    }

    public void b(CharSequence charSequence) {
        f39a.a(this.b, charSequence);
    }

    public CharSequence r() {
        return f39a.f(this.b);
    }

    public CharSequence s() {
        return f39a.d(this.b);
    }

    public void c(CharSequence charSequence) {
        f39a.b(this.b, charSequence);
    }

    public void t() {
        f39a.q(this.b);
    }

    public String u() {
        return f39a.u(this.b);
    }

    public int hashCode() {
        if (this.b == null) {
            return 0;
        }
        return this.b.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            a aVar = (a) obj;
            if (this.b == null) {
                return aVar.b == null;
            }
            return this.b.equals(aVar.b);
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        Rect rect = new Rect();
        a(rect);
        sb.append("; boundsInParent: " + rect);
        c(rect);
        sb.append("; boundsInScreen: " + rect);
        sb.append("; packageName: ").append(p());
        sb.append("; className: ").append(q());
        sb.append("; text: ").append(r());
        sb.append("; contentDescription: ").append(s());
        sb.append("; viewId: ").append(u());
        sb.append("; checkable: ").append(d());
        sb.append("; checked: ").append(e());
        sb.append("; focusable: ").append(f());
        sb.append("; focused: ").append(g());
        sb.append("; selected: ").append(j());
        sb.append("; clickable: ").append(k());
        sb.append("; longClickable: ").append(l());
        sb.append("; enabled: ").append(m());
        sb.append("; password: ").append(n());
        sb.append("; scrollable: " + o());
        sb.append("; [");
        int iB = b();
        while (iB != 0) {
            int iNumberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(iB);
            iB &= iNumberOfTrailingZeros ^ (-1);
            sb.append(c(iNumberOfTrailingZeros));
            if (iB != 0) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private static String c(int i) {
        switch (i) {
            case 1:
                return "ACTION_FOCUS";
            case 2:
                return "ACTION_CLEAR_FOCUS";
            case 4:
                return "ACTION_SELECT";
            case 8:
                return "ACTION_CLEAR_SELECTION";
            case 16:
                return "ACTION_CLICK";
            case 32:
                return "ACTION_LONG_CLICK";
            case 64:
                return "ACTION_ACCESSIBILITY_FOCUS";
            case XMLChar.MASK_NCNAME /* 128 */:
                return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
            case 256:
                return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
            case 512:
                return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
            case 1024:
                return "ACTION_NEXT_HTML_ELEMENT";
            case 2048:
                return "ACTION_PREVIOUS_HTML_ELEMENT";
            case 4096:
                return "ACTION_SCROLL_FORWARD";
            case 8192:
                return "ACTION_SCROLL_BACKWARD";
            case 16384:
                return "ACTION_COPY";
            case 32768:
                return "ACTION_PASTE";
            case NativeProtocol.MESSAGE_GET_ACCESS_TOKEN_REQUEST /* 65536 */:
                return "ACTION_CUT";
            case 131072:
                return "ACTION_SET_SELECTION";
            default:
                return "ACTION_UNKNOWN";
        }
    }
}
