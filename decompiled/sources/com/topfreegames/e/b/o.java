package com.topfreegames.e.b;

import android.util.Log;
import com.facebook.Request;
import com.facebook.Response;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;

/* JADX INFO: compiled from: TopFacebookUserInfoRequestHandler.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class o implements Request.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ n f1536a;

    public o(n nVar) {
        this.f1536a = nVar;
    }

    @Override // com.facebook.Request.Callback
    public void onCompleted(Response response) {
        p pVar;
        if (response != null && response.getError() == null) {
            try {
                JSONObject innerJSONObject = response.getGraphObject().getInnerJSONObject();
                this.f1536a.c = innerJSONObject.getString("id");
                this.f1536a.d = innerJSONObject.getString("name");
                synchronized (this) {
                    try {
                        this.f1536a.g = false;
                        if (!this.f1536a.f) {
                            this.f1536a.f();
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return;
            } catch (Exception e) {
                Log.e("TopFacebookUserInfoRequestListener", "An exception occured while retrieving the user information: " + e.toString());
                try {
                    JSONObject jSONObject = response.getGraphObject().getInnerJSONObject().getJSONObject("error");
                    String string = jSONObject.getString("code");
                    String string2 = jSONObject.getString("error_subcode");
                    if ("190".equals(string) && "460".equals(string2) && this.f1536a.f1534a != null && (pVar = (p) this.f1536a.f1534a.get()) != null) {
                        pVar.a();
                    }
                } catch (Exception e2) {
                    Log.v("TopFacebookUserInfoRequestListener", "The exception is not an authentication exception");
                }
                if (this.f1536a.h && this.f1536a.j < 2) {
                    new Timer().schedule(new TimerTask() { // from class: com.topfreegames.e.b.o.1
                        @Override // java.util.TimerTask, java.lang.Runnable
                        public void run() {
                            o.this.f1536a.a(o.this.f1536a.b);
                        }
                    }, 1000L);
                    this.f1536a.j++;
                    return;
                }
                this.f1536a.a(null, this.f1536a.h, false);
                return;
            }
        }
        this.f1536a.a(null, this.f1536a.h, false);
    }
}
