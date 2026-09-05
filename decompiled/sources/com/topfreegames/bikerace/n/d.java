package com.topfreegames.bikerace.n;

import android.os.AsyncTask;
import java.io.IOException;

/* JADX INFO: compiled from: ABTestManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class d extends AsyncTask<String, Void, Boolean> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ a f1343a;
    private c b;

    public d(a aVar, c cVar) {
        this.f1343a = aVar;
        this.b = cVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Boolean doInBackground(String... strArr) {
        try {
            this.f1343a.b(strArr[0]);
            if (this.b != null) {
                this.b.a();
            }
            return true;
        } catch (IOException e) {
            return false;
        } catch (Exception e2) {
            return false;
        }
    }
}
