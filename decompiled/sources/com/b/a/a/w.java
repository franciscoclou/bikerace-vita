package com.b.a.a;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.facebook.internal.ServerProtocol;
import java.io.IOException;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class w {
    w() {
    }

    public final byte[] a(u uVar) throws IOException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appBundleId", uVar.f310a);
            jSONObject.put("executionId", uVar.b);
            jSONObject.put("installationId", uVar.c);
            jSONObject.put("androidId", uVar.d);
            jSONObject.put("osVersion", uVar.e);
            jSONObject.put("deviceModel", uVar.f);
            jSONObject.put("appVersionCode", uVar.g);
            jSONObject.put("appVersionName", uVar.h);
            jSONObject.put("timestamp", uVar.i);
            jSONObject.put(ServerProtocol.DIALOG_PARAM_TYPE, uVar.j.toString());
            jSONObject.put("details", a(uVar.k));
            return jSONObject.toString().getBytes(XMLStreamWriterImpl.UTF_8);
        } catch (JSONException e) {
            throw new IOException(e.getMessage());
        }
    }

    private static JSONObject a(Map<String, String> map) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            jSONObject.put(entry.getKey(), entry.getValue());
        }
        return jSONObject;
    }
}
