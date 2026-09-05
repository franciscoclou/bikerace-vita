package android.support.v4.view;

import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

/* JADX INFO: compiled from: AccessibilityDelegateCompat.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class b extends e {
    b() {
    }

    @Override // android.support.v4.view.e, android.support.v4.view.c
    public Object a() {
        return f.a();
    }

    @Override // android.support.v4.view.e, android.support.v4.view.c
    public Object a(final a aVar) {
        return f.a(new g() { // from class: android.support.v4.view.b.1
            @Override // android.support.v4.view.g
            public boolean a(View view, AccessibilityEvent accessibilityEvent) {
                return aVar.b(view, accessibilityEvent);
            }

            @Override // android.support.v4.view.g
            public void b(View view, AccessibilityEvent accessibilityEvent) {
                aVar.d(view, accessibilityEvent);
            }

            @Override // android.support.v4.view.g
            public void a(View view, Object obj) {
                aVar.a(view, new android.support.v4.view.a.a(obj));
            }

            @Override // android.support.v4.view.g
            public void c(View view, AccessibilityEvent accessibilityEvent) {
                aVar.c(view, accessibilityEvent);
            }

            @Override // android.support.v4.view.g
            public boolean a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
                return aVar.a(viewGroup, view, accessibilityEvent);
            }

            @Override // android.support.v4.view.g
            public void a(View view, int i) {
                aVar.a(view, i);
            }

            @Override // android.support.v4.view.g
            public void d(View view, AccessibilityEvent accessibilityEvent) {
                aVar.a(view, accessibilityEvent);
            }
        });
    }

    @Override // android.support.v4.view.e, android.support.v4.view.c
    public boolean a(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        return f.a(obj, view, accessibilityEvent);
    }

    @Override // android.support.v4.view.e, android.support.v4.view.c
    public void b(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        f.b(obj, view, accessibilityEvent);
    }

    @Override // android.support.v4.view.e, android.support.v4.view.c
    public void a(Object obj, View view, android.support.v4.view.a.a aVar) {
        f.a(obj, view, aVar.a());
    }

    @Override // android.support.v4.view.e, android.support.v4.view.c
    public void c(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        f.c(obj, view, accessibilityEvent);
    }

    @Override // android.support.v4.view.e, android.support.v4.view.c
    public boolean a(Object obj, ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return f.a(obj, viewGroup, view, accessibilityEvent);
    }

    @Override // android.support.v4.view.e, android.support.v4.view.c
    public void a(Object obj, View view, int i) {
        f.a(obj, view, i);
    }

    @Override // android.support.v4.view.e, android.support.v4.view.c
    public void d(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        f.d(obj, view, accessibilityEvent);
    }
}
