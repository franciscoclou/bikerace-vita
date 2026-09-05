package com.topfreegames.e.b.a;

import android.util.Log;
import com.facebook.Request;
import com.facebook.Response;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: TopFacebookAppRequestCreateAppUserHandler.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class c implements Request.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ b f1509a;

    private c(b bVar) {
        this.f1509a = bVar;
    }

    /* synthetic */ c(b bVar, c cVar) {
        this(bVar);
    }

    @Override // com.facebook.Request.Callback
    public void onCompleted(Response response) {
        if (response != null) {
            if (response.getError() == null) {
                HashMap map = new HashMap();
                try {
                    JSONObject innerJSONObject = response.getGraphObject().getInnerJSONObject();
                    String string = innerJSONObject.getString("request");
                    JSONArray jSONArray = innerJSONObject.getJSONArray("to");
                    for (int i = 0; i < jSONArray.length(); i++) {
                        String string2 = jSONArray.getString(i);
                        map.put(string2, String.format("%s_%s", string, string2));
                    }
                    return;
                } catch (Exception e) {
                    Log.d(b.class.getSimpleName(), "An error ocurred while parsing the JSON string: " + e);
                    return;
                } finally {
                    this.f1509a.a(map);
                }
            }
            this.f1509a.a(null);
        }
    }
}
