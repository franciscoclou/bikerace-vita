package android.support.v4.view;

import android.view.View;
import java.util.Comparator;

/* JADX INFO: compiled from: ViewPager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class bk implements Comparator<View> {
    bk() {
    }

    @Override // java.util.Comparator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(View view, View view2) {
        bf bfVar = (bf) view.getLayoutParams();
        bf bfVar2 = (bf) view2.getLayoutParams();
        if (bfVar.f51a != bfVar2.f51a) {
            return bfVar.f51a ? 1 : -1;
        }
        return bfVar.e - bfVar2.e;
    }
}
