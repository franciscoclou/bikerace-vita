package com.topfreegames.bikerace.billing.google;

import android.os.Handler;
import android.util.Log;

/* JADX INFO: compiled from: GoogleBillingManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class k extends l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    com.topfreegames.bikerace.billing.a f1151a;
    final /* synthetic */ j b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(j jVar, com.topfreegames.bikerace.billing.a aVar, Handler handler) {
        super(aVar, handler);
        this.b = jVar;
        this.f1151a = aVar;
    }

    @Override // com.topfreegames.bikerace.billing.google.l
    public void a(boolean z) {
    }

    @Override // com.topfreegames.bikerace.billing.google.l
    public void a(b bVar, String str, int i, long j, String str2) {
        if (bVar == b.PURCHASED) {
            this.f1151a.a(str, i);
            return;
        }
        if (bVar == b.CANCELED) {
            this.f1151a.b(str);
        } else if (bVar == b.REFUNDED) {
            this.f1151a.d(str);
        } else {
            this.f1151a.c(str);
        }
    }

    @Override // com.topfreegames.bikerace.billing.google.l
    public void a(h hVar, c cVar) {
        if (cVar == c.RESULT_USER_CANCELED) {
            this.f1151a.g();
        } else if (cVar != c.RESULT_OK) {
            this.f1151a.h();
        }
    }

    @Override // com.topfreegames.bikerace.billing.google.l
    public void a(i iVar, c cVar) {
        if (cVar == c.RESULT_SERVICE_UNAVAILABLE) {
            this.f1151a.i();
        } else if (cVar != c.RESULT_OK) {
            Log.e("BillingManager", "Restore transactions failed: " + cVar);
        }
    }
}
