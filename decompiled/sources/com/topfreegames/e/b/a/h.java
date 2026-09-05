package com.topfreegames.e.b.a;

import com.facebook.Request;
import com.facebook.Response;

/* JADX INFO: compiled from: TopFacebookAppRequestDeleteHandler.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class h implements Request.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ g f1516a;

    private h(g gVar) {
        this.f1516a = gVar;
    }

    /* synthetic */ h(g gVar, h hVar) {
        this(gVar);
    }

    @Override // com.facebook.Request.Callback
    public void onCompleted(Response response) {
        if (response != null) {
            if (response.getError() == null) {
                this.f1516a.a(false, false);
            } else {
                this.f1516a.a(false, true);
            }
        }
    }
}
