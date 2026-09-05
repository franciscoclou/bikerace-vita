package android.support.v4.view.a;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;

/* JADX INFO: compiled from: AccessibilityNodeProviderCompatJellyBean.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class p {
    public static Object a(final q qVar) {
        return new AccessibilityNodeProvider() { // from class: android.support.v4.view.a.p.1
            @Override // android.view.accessibility.AccessibilityNodeProvider
            public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
                return (AccessibilityNodeInfo) qVar.a(i);
            }

            @Override // android.view.accessibility.AccessibilityNodeProvider
            public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
                return qVar.a(str, i);
            }

            @Override // android.view.accessibility.AccessibilityNodeProvider
            public boolean performAction(int i, int i2, Bundle bundle) {
                return qVar.a(i, i2, bundle);
            }
        };
    }
}
