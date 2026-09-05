package com.topfreegames.e.b.a;

import com.facebook.Request;
import com.facebook.Response;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: TopFacebookAppRequestReadHandler.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class o implements Request.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ n f1524a;

    private o(n nVar) {
        this.f1524a = nVar;
    }

    /* synthetic */ o(n nVar, o oVar) {
        this(nVar);
    }

    @Override // com.facebook.Request.Callback
    public void onCompleted(Response response) {
        String string;
        String string2;
        String str;
        String string3;
        int i = 0;
        if (response != null) {
            if (response.getError() == null) {
                try {
                    JSONArray jSONArray = response.getGraphObject().getInnerJSONObject().getJSONArray("data");
                    while (true) {
                        int i2 = i;
                        if (i2 < jSONArray.length()) {
                            try {
                                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                                String string4 = jSONObject.getString("id");
                                try {
                                    JSONObject jSONObject2 = jSONObject.getJSONObject("from");
                                    string = jSONObject2.getString("name");
                                    try {
                                        string2 = jSONObject2.getString("id");
                                        str = string;
                                    } catch (Exception e) {
                                        string2 = null;
                                        str = string;
                                    }
                                } catch (Exception e2) {
                                    string = null;
                                }
                                String string5 = jSONObject.getString("message");
                                try {
                                    string3 = jSONObject.getString("data");
                                } catch (Exception e3) {
                                    string3 = null;
                                }
                                this.f1524a.a(string4, string2, str, string5, string3, false);
                            } catch (Exception e4) {
                            }
                            i = i2 + 1;
                        } else {
                            return;
                        }
                    }
                } catch (Exception e5) {
                    this.f1524a.a(null, null, null, null, null, true);
                }
            } else {
                this.f1524a.a(null, null, null, null, null, true);
            }
        }
    }
}
