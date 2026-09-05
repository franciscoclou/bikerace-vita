package com.topfreegames.bikerace.push;

import com.amazonaws.services.sqs.model.SendMessageRequest;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: PushSender.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class g {
    public static void a(String str, String str2, String str3, d dVar, com.amazonaws.services.sqs.b bVar) {
        HashMap map = new HashMap();
        map.put(c.e(), str);
        map.put(c.f(), str2);
        map.put(c.g(), str3);
        map.put(c.h(), c.a(dVar));
        a(c.c(), map, bVar);
        if (c.a()) {
            System.out.println("Sending push: friendName=" + str3 + ", friendId=" + str2 + ", type=" + dVar);
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.topfreegames.bikerace.push.g$1] */
    private static void a(final String str, final Map<String, String> map, final com.amazonaws.services.sqs.b bVar) {
        new Thread() { // from class: com.topfreegames.bikerace.push.g.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    JSONObject jSONObject = new JSONObject();
                    for (Map.Entry entry : map.entrySet()) {
                        jSONObject.put((String) entry.getKey(), entry.getValue());
                    }
                    SendMessageRequest sendMessageRequest = new SendMessageRequest();
                    sendMessageRequest.setQueueUrl(str);
                    sendMessageRequest.setMessageBody(jSONObject.toString());
                    bVar.a(sendMessageRequest);
                } catch (com.amazonaws.b e) {
                    if (c.a()) {
                        e.printStackTrace();
                    }
                } catch (com.amazonaws.a e2) {
                    if (c.a()) {
                        e2.printStackTrace();
                    }
                } catch (JSONException e3) {
                    if (c.a()) {
                        e3.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
