package android.support.v4.view;

import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

/* JADX INFO: compiled from: AccessibilityDelegateCompatIcs.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface g {
    void a(View view, int i);

    void a(View view, Object obj);

    boolean a(View view, AccessibilityEvent accessibilityEvent);

    boolean a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent);

    void b(View view, AccessibilityEvent accessibilityEvent);

    void c(View view, AccessibilityEvent accessibilityEvent);

    void d(View view, AccessibilityEvent accessibilityEvent);
}
