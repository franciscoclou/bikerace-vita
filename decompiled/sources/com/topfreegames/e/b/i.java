package com.topfreegames.e.b;

import android.graphics.Bitmap;
import android.util.Log;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObjectList;
import java.util.Enumeration;
import org.json.JSONObject;

/* JADX INFO: compiled from: TopFacebookSeveralUsersInfoRequestHandler.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class i implements Request.Callback, b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ h f1529a;

    private i(h hVar) {
        this.f1529a = hVar;
    }

    /* synthetic */ i(h hVar, i iVar) {
        this(hVar);
    }

    @Override // com.facebook.Request.Callback
    public void onCompleted(Response response) {
        if (response != null && response.getError() == null) {
            try {
                GraphObjectList<GraphObject> graphObjectList = response.getGraphObjectList();
                int size = graphObjectList.size();
                for (int i = 0; i < size; i++) {
                    JSONObject innerJSONObject = graphObjectList.get(i).getInnerJSONObject();
                    String string = innerJSONObject.getString("uid");
                    this.f1529a.f1527a.put(string, new com.topfreegames.e.l(string, innerJSONObject.getString("name"), null));
                    if (this.f1529a.g) {
                        this.f1529a.d++;
                        a aVar = new a(this, string, this.f1529a.i);
                        this.f1529a.c.put(string, aVar);
                        c.b().a(aVar);
                    }
                }
                if (this.f1529a.g) {
                    if (this.f1529a.b != null) {
                        Enumeration enumerationKeys = this.f1529a.b.keys();
                        while (enumerationKeys.hasMoreElements()) {
                            String str = (String) enumerationKeys.nextElement();
                            com.topfreegames.e.l lVar = (com.topfreegames.e.l) this.f1529a.b.get(str);
                            if (lVar != null && lVar.c() == null) {
                                this.f1529a.d++;
                                a aVar2 = new a(this, str, this.f1529a.i);
                                this.f1529a.c.put(str, aVar2);
                                aVar2.c();
                            }
                        }
                        if (this.f1529a.d != 0) {
                            return;
                        }
                        this.f1529a.a(false);
                        return;
                    }
                    return;
                }
                this.f1529a.a(false);
                return;
            } catch (Exception e) {
                Log.e("TopFacebookSeveralUsersInfoRequestListener", "An exception occurred while retrieving the users info:" + e.toString());
                this.f1529a.a(false);
                return;
            }
        }
        this.f1529a.a(false);
    }

    @Override // com.topfreegames.e.b.b
    public void a(Bitmap bitmap, String str, a aVar, boolean z) {
        this.f1529a.e++;
        ((com.topfreegames.e.l) this.f1529a.f1527a.get(str)).a(bitmap);
        if (this.f1529a.e != this.f1529a.d) {
            return;
        }
        this.f1529a.a(false);
    }

    @Override // com.topfreegames.e.b.e
    public void a() {
        if (this.f1529a.f != null) {
            this.f1529a.f.a();
        }
    }
}
