package com.topfreegames.bikerace.h.a;

import android.os.AsyncTask;
import com.topfreegames.bikerace.ap;

/* JADX INFO: compiled from: UserLevelDownloader.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class g extends AsyncTask<Void, Void, a[]> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private f f1246a;

    public g(f fVar) {
        this.f1246a = null;
        this.f1246a = fVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public a[] doInBackground(Void... voidArr) {
        try {
            return d.f();
        } catch (Exception e) {
            if (ap.d()) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(a[] aVarArr) {
        synchronized (d.f1244a) {
            d.f1244a.remove(this);
            d.e();
        }
        if (this.f1246a != null) {
            this.f1246a.a(aVarArr);
        }
    }
}
