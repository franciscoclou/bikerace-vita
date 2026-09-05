package android.support.v4.view.a;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;

/* JADX INFO: compiled from: AccessibilityNodeProviderCompatKitKat.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class r {
    public static Object a(final s sVar) {
        return new AccessibilityNodeProvider() { // from class: android.support.v4.view.a.r.1
            @Override // android.view.accessibility.AccessibilityNodeProvider
            public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
                return (AccessibilityNodeInfo) sVar.a(i);
            }

            @Override // android.view.accessibility.AccessibilityNodeProvider
            public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
                return sVar.a(str, i);
            }

            @Override // android.view.accessibility.AccessibilityNodeProvider
            public boolean performAction(int i, int i2, Bundle bundle) {
                return sVar.a(i, i2, bundle);
            }
        };
    }
}
