package android.support.v4.app;

import android.os.Bundle;

/* JADX INFO: compiled from: LoaderManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface o<D> {
    android.support.v4.a.a<D> onCreateLoader(int i, Bundle bundle);

    void onLoadFinished(android.support.v4.a.a<D> aVar, D d);

    void onLoaderReset(android.support.v4.a.a<D> aVar);
}
