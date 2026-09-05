package android.support.v4.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

/* JADX INFO: compiled from: AccessibilityDelegateCompat.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
interface c {
    android.support.v4.view.a.k a(Object obj, View view);

    Object a();

    Object a(a aVar);

    void a(Object obj, View view, int i);

    void a(Object obj, View view, android.support.v4.view.a.a aVar);

    boolean a(Object obj, View view, int i, Bundle bundle);

    boolean a(Object obj, View view, AccessibilityEvent accessibilityEvent);

    boolean a(Object obj, ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent);

    void b(Object obj, View view, AccessibilityEvent accessibilityEvent);

    void c(Object obj, View view, AccessibilityEvent accessibilityEvent);

    void d(Object obj, View view, AccessibilityEvent accessibilityEvent);
}
