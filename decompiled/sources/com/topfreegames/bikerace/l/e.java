package com.topfreegames.bikerace.l;

import java.util.Collection;
import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: UserJsonSerde.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class e {
    public static JSONObject a(com.topfreegames.bikerace.b.b bVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray((Collection) bVar.d());
        jSONObject.put("id", bVar.c());
        jSONObject.put("ops", jSONArray);
        jSONObject.put("bad", bVar.a());
        jSONObject.put("grk", bVar.b());
        jSONObject.put("werased", bVar.e());
        return jSONObject;
    }

    public static com.topfreegames.bikerace.b.b a(JSONObject jSONObject) {
        com.topfreegames.bikerace.b.b bVar = new com.topfreegames.bikerace.b.b();
        bVar.a(jSONObject.getString("id"));
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("ops");
        if (jSONArrayOptJSONArray != null) {
            HashSet hashSet = new HashSet();
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                hashSet.add(jSONArrayOptJSONArray.getString(i));
            }
            bVar.c(hashSet);
        }
        if (jSONObject.has("bad")) {
            bVar.a(Integer.valueOf(jSONObject.getInt("bad")));
        }
        if (jSONObject.has("grk")) {
            bVar.b(Integer.valueOf(jSONObject.getInt("grk")));
        }
        if (jSONObject.has("werased")) {
            bVar.c(Integer.valueOf(jSONObject.getInt("werased")));
        }
        return bVar;
    }
}
