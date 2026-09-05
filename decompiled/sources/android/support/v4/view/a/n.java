package android.support.v4.view.a;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: AccessibilityNodeProviderCompat.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class n extends o {
    n() {
    }

    @Override // android.support.v4.view.a.o, android.support.v4.view.a.l
    public Object a(final k kVar) {
        return r.a(new s() { // from class: android.support.v4.view.a.n.1
            @Override // android.support.v4.view.a.s
            public boolean a(int i, int i2, Bundle bundle) {
                return kVar.a(i, i2, bundle);
            }

            @Override // android.support.v4.view.a.s
            public List<Object> a(String str, int i) {
                List<a> listA = kVar.a(str, i);
                ArrayList arrayList = new ArrayList();
                int size = listA.size();
                for (int i2 = 0; i2 < size; i2++) {
                    arrayList.add(listA.get(i2).a());
                }
                return arrayList;
            }

            @Override // android.support.v4.view.a.s
            public Object a(int i) {
                a aVarA = kVar.a(i);
                if (aVarA == null) {
                    return null;
                }
                return aVarA.a();
            }
        });
    }
}
