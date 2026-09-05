package com.b.a.a;

import com.facebook.internal.NativeProtocol;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ax {
    ax() {
    }

    public aw a(bg bgVar, JSONObject jSONObject) throws JSONException {
        int iOptInt = jSONObject.optInt("settings_version", 0);
        int iOptInt2 = jSONObject.optInt("cache_duration", 3600);
        JSONObject jSONObject2 = jSONObject.getJSONObject("app");
        String string = jSONObject2.getString("identifier");
        String string2 = jSONObject2.getString("status");
        String string3 = jSONObject2.getString(NativeProtocol.IMAGE_URL_KEY);
        String string4 = jSONObject2.getString("reports_url");
        boolean zOptBoolean = jSONObject2.optBoolean("update_required", false);
        ak akVar = null;
        if (jSONObject2.has("icon") && jSONObject2.getJSONObject("icon").has("hash")) {
            JSONObject jSONObject3 = jSONObject2.getJSONObject("icon");
            akVar = new ak(jSONObject3.getString("hash"), jSONObject3.getInt("width"), jSONObject3.getInt("height"));
        }
        al alVar = new al(string, string2, string3, string4, zOptBoolean, akVar);
        JSONObject jSONObject4 = jSONObject.getJSONObject("session");
        aq aqVar = new aq(jSONObject4.optInt("log_buffer_size", 64000), jSONObject4.optInt("max_chained_exception_depth", 8), jSONObject4.optInt("max_custom_exception_events", 64), jSONObject4.optInt("max_custom_key_value_pairs", 64), jSONObject4.optInt("identifier_mask", 255), jSONObject4.optBoolean("send_session_without_crash", false));
        JSONObject jSONObject5 = jSONObject.getJSONObject("prompt");
        ap apVar = new ap(jSONObject5.optString("title", "Send Crash Report?"), jSONObject5.optString("message", "Looks like we crashed! Please help us fix the problem by sending a crash report."), jSONObject5.optString("send_button_title", "Send"), jSONObject5.optBoolean("show_cancel_button", true), jSONObject5.optString("cancel_button_title", "Don't Send"), jSONObject5.optBoolean("show_always_send_button", true), jSONObject5.optString("always_send_button_title", "Always Send"));
        JSONObject jSONObject6 = jSONObject.getJSONObject("features");
        ao aoVar = new ao(jSONObject6.optBoolean("prompt_enabled", false), jSONObject6.optBoolean("collect_logged_exceptions", true), jSONObject6.optBoolean("collect_reports", true), jSONObject6.optBoolean("collect_analytics", false));
        JSONObject jSONObject7 = jSONObject.getJSONObject("analytics");
        return new aw(jSONObject.has("expires_at") ? jSONObject.getLong("expires_at") : bgVar.a() + (((long) iOptInt2) * 1000), alVar, aqVar, apVar, aoVar, new aj(jSONObject7.optString(NativeProtocol.IMAGE_URL_KEY, "https://e.crashlytics.com/spi/v2/events"), jSONObject7.optInt("flush_interval_secs", 600), jSONObject7.optInt("max_byte_size_per_file", 8000), jSONObject7.optInt("max_file_count_per_send", 1), jSONObject7.optInt("max_pending_send_file_count", 100)), iOptInt, iOptInt2);
    }

    public JSONObject a(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject(jSONObject.toString());
        jSONObject2.getJSONObject("features").remove("collect_analytics");
        jSONObject2.remove("analytics");
        return jSONObject2;
    }
}
