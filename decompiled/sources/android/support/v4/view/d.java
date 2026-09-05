package android.support.v4.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

/* JADX INFO: compiled from: AccessibilityDelegateCompat.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class d extends b {
    d() {
    }

    @Override // android.support.v4.view.b, android.support.v4.view.e, android.support.v4.view.c
    public Object a(final a aVar) {
        return h.a(new i() { // from class: android.support.v4.view.d.1
            @Override // android.support.v4.view.i
            public boolean a(View view, AccessibilityEvent accessibilityEvent) {
                return aVar.b(view, accessibilityEvent);
            }

            @Override // android.support.v4.view.i
            public void b(View view, AccessibilityEvent accessibilityEvent) {
                aVar.d(view, accessibilityEvent);
            }

            @Override // android.support.v4.view.i
            public void a(View view, Object obj) {
                aVar.a(view, new android.support.v4.view.a.a(obj));
            }

            @Override // android.support.v4.view.i
            public void c(View view, AccessibilityEvent accessibilityEvent) {
                aVar.c(view, accessibilityEvent);
            }

            @Override // android.support.v4.view.i
            public boolean a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
                return aVar.a(viewGroup, view, accessibilityEvent);
            }

            @Override // android.support.v4.view.i
            public void a(View view, int i) {
                aVar.a(view, i);
            }

            @Override // android.support.v4.view.i
            public void d(View view, AccessibilityEvent accessibilityEvent) {
                aVar.a(view, accessibilityEvent);
            }

            @Override // android.support.v4.view.i
            public Object a(View view) {
                android.support.v4.view.a.k kVarA = aVar.a(view);
                if (kVarA != null) {
                    return kVarA.a();
                }
                return null;
            }

            @Override // android.support.v4.view.i
            public boolean a(View view, int i, Bundle bundle) {
                return aVar.a(view, i, bundle);
            }
        });
    }

    @Override // android.support.v4.view.e, android.support.v4.view.c
    public android.support.v4.view.a.k a(Object obj, View view) {
        Object objA = h.a(obj, view);
        if (objA != null) {
            return new android.support.v4.view.a.k(objA);
        }
        return null;
    }

    @Override // android.support.v4.view.e, android.support.v4.view.c
    public boolean a(Object obj, View view, int i, Bundle bundle) {
        return h.a(obj, view, i, bundle);
    }
}
