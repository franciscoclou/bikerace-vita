package com.topfreegames.bikerace;

import android.os.AsyncTask;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: RemoteConfig.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class bf extends AsyncTask<String, Void, Boolean> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ bb f1135a;

    private bf(bb bbVar) {
        this.f1135a = bbVar;
    }

    /* synthetic */ bf(bb bbVar, bf bfVar) {
        this(bbVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Boolean doInBackground(String... strArr) throws Throwable {
        try {
            this.f1135a.g(strArr[0]);
            if (this.f1135a.f1132a != null) {
                this.f1135a.f1132a.a();
            }
            return true;
        } catch (IOException e) {
            return false;
        } catch (XmlPullParserException e2) {
            return false;
        }
    }
}
