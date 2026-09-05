package com.topfreegames.e.b;

import android.graphics.Bitmap;
import android.util.Log;
import com.facebook.FacebookRequestError;
import com.facebook.Request;
import com.facebook.Response;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: TopFacebookUserFriendsRequestHandler.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class l implements Request.Callback, b {
    private static /* synthetic */ int[] b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ k f1533a;

    private l(k kVar) {
        this.f1533a = kVar;
    }

    /* synthetic */ l(k kVar, l lVar) {
        this(kVar);
    }

    static /* synthetic */ int[] b() {
        int[] iArr = b;
        if (iArr == null) {
            iArr = new int[com.topfreegames.e.e.valuesCustom().length];
            try {
                iArr[com.topfreegames.e.e.ALL_FRIENDS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[com.topfreegames.e.e.FRIENDS_DONT_HAVE_APP.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[com.topfreegames.e.e.FRIENDS_HAVE_APP.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            b = iArr;
        }
        return iArr;
    }

    @Override // com.facebook.Request.Callback
    public void onCompleted(Response response) {
        if (response != null && response.getError() == null) {
            try {
                switch (b()[this.f1533a.g.ordinal()]) {
                    case 1:
                        JSONArray jSONArray = (JSONArray) ((JSONObject) response.getGraphObject().getInnerJSONObject().get("friends")).get("data");
                        for (int i = 0; i < jSONArray.length(); i++) {
                            JSONObject jSONObject = jSONArray.getJSONObject(i);
                            String string = jSONObject.getString("id");
                            this.f1533a.b.put(string, new com.topfreegames.e.l(string, jSONObject.getString("name"), null));
                            if (this.f1533a.h) {
                                this.f1533a.d++;
                                a aVar = new a(this, string, this.f1533a.i);
                                this.f1533a.c.put(string, aVar);
                                c.b().a(aVar);
                            }
                        }
                        if (!this.f1533a.h) {
                            this.f1533a.a(false);
                        }
                        break;
                    case 2:
                    case 3:
                        JSONArray jSONArray2 = response.getGraphObject().getInnerJSONObject().getJSONArray("data");
                        int length = jSONArray2.length();
                        for (int i2 = 0; i2 < length; i2++) {
                            JSONObject jSONObject2 = jSONArray2.getJSONObject(i2);
                            String string2 = jSONObject2.getString("uid");
                            this.f1533a.b.put(string2, new com.topfreegames.e.l(string2, jSONObject2.getString("name"), null));
                            if (this.f1533a.h) {
                                this.f1533a.d++;
                                this.f1533a.c.put(string2, new a(this, string2, this.f1533a.i));
                            }
                        }
                        if (!this.f1533a.h) {
                            this.f1533a.a(false);
                        }
                        break;
                }
                return;
            } catch (Exception e) {
                Log.e("TopFacebookUserFriendsRequestListener", "An exception occurred while retrieving the user friends:" + e.toString());
                this.f1533a.a(false);
                return;
            }
        }
        FacebookRequestError error = response.getError();
        if (error.getErrorCode() == 190 && error.getSubErrorCode() == 460 && this.f1533a.f != null) {
            this.f1533a.f.a();
        }
        this.f1533a.a(false);
    }

    @Override // com.topfreegames.e.b.b
    public void a(Bitmap bitmap, String str, a aVar, boolean z) {
        this.f1533a.e++;
        ((com.topfreegames.e.l) this.f1533a.b.get(str)).a(bitmap);
        if (this.f1533a.e == this.f1533a.d) {
            this.f1533a.a(false);
        }
    }

    @Override // com.topfreegames.e.b.e
    public void a() {
        if (this.f1533a.f != null) {
            this.f1533a.f.a();
        }
    }
}
