package android.support.v4.view;

import android.database.DataSetObserver;

/* JADX INFO: compiled from: ViewPager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class bj extends DataSetObserver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ ViewPager f52a;

    private bj(ViewPager viewPager) {
        this.f52a = viewPager;
    }

    @Override // android.database.DataSetObserver
    public void onChanged() {
        this.f52a.a();
    }

    @Override // android.database.DataSetObserver
    public void onInvalidated() {
        this.f52a.a();
    }
}
