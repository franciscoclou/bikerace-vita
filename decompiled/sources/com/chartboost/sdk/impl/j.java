package com.chartboost.sdk.impl;

import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.util.SparseArray;
import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.chartboost.sdk.Chartboost;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static com.chartboost.sdk.Libraries.e.a f465a = com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.Libraries.e.a(), new com.chartboost.sdk.Libraries.e.c() { // from class: com.chartboost.sdk.impl.j.1
        @Override // com.chartboost.sdk.Libraries.e.a
        public boolean a(Object obj) {
            int iIntValue = ((Number) obj).intValue();
            return iIntValue >= 200 && iIntValue < 300;
        }

        @Override // com.chartboost.sdk.Libraries.e.a
        public String a() {
            return "Must be a valid status code (>=200 && <300)";
        }
    });
    private static int g = 0;
    private String b;
    private String c;
    private SparseArray<a> d;
    private int e;
    private Handler f = Chartboost.sharedChartboost().getHandler();

    public interface b {
        void a(k kVar, String str);

        void a(JSONObject jSONObject, k kVar);
    }

    public abstract class c implements b {
        @Override // com.chartboost.sdk.impl.j.b
        public void a(k kVar, String str) {
        }
    }

    class a implements Serializable {
        private k b;
        private JSONObject c = null;
        private Integer d;
        private b e;

        public a(int i, k kVar, b bVar) {
            this.d = Integer.valueOf(i);
            this.b = kVar;
            this.e = bVar;
        }

        public void a(String str) {
            JSONArray jSONArray;
            if (this.b.f() && j.this.c != null) {
                SharedPreferences sharedPreferencesA = com.chartboost.sdk.Libraries.d.a();
                String str2 = "CBQueuedRequests-" + j.this.c;
                try {
                    JSONObject jSONObjectH = this.b.h();
                    if (jSONObjectH != null) {
                        String string = sharedPreferencesA.getString(str2, null);
                        if (string != null) {
                            try {
                                jSONArray = new JSONArray(new JSONTokener(string));
                            } catch (Exception e) {
                                jSONArray = new JSONArray();
                            }
                        } else {
                            jSONArray = new JSONArray();
                        }
                        jSONArray.put(jSONObjectH);
                        SharedPreferences.Editor editorEdit = sharedPreferencesA.edit();
                        editorEdit.putString(str2, jSONArray.toString());
                        editorEdit.commit();
                        return;
                    }
                    return;
                } catch (Exception e2) {
                    return;
                }
            }
            if (this.e != null) {
                this.e.a(this.b, str);
            }
        }
    }

    public j(String str, String str2) {
        this.b = str == null ? "https://live.chartboost.com" : str;
        this.c = str2;
        this.d = new SparseArray<>();
        this.e = 1;
    }

    public void a(k kVar) {
        a(kVar, null);
    }

    public void a(k kVar, b bVar) {
        int i = this.e;
        this.e = i + 1;
        a aVar = new a(i, kVar, bVar);
        if (!m.a()) {
            aVar.a("network unreachable");
            return;
        }
        this.d.put(i, aVar);
        l.a().execute(new d(aVar));
    }

    public void a() {
        SharedPreferences sharedPreferencesA;
        String str;
        String string;
        if (m.a() && (string = (sharedPreferencesA = com.chartboost.sdk.Libraries.d.a()).getString((str = "CBQueuedRequests-" + this.c), null)) != null) {
            SharedPreferences.Editor editorEdit = sharedPreferencesA.edit();
            editorEdit.putString(str, null);
            editorEdit.commit();
            try {
                JSONArray jSONArray = new JSONArray(new JSONTokener(string));
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        k kVarA = k.a(jSONArray.getJSONObject(i));
                        if (kVarA != null) {
                            a(kVarA);
                        }
                    } catch (Exception e) {
                        Log.w("Chartboost", "Retrying request failed");
                    }
                }
            } catch (Exception e2) {
                Log.w("Chartboost", "Retrying request list failed");
            }
        }
    }

    class d implements Runnable {
        private a b;

        public d(a aVar) {
            this.b = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i;
            HttpResponse httpResponseExecute;
            String str;
            String string;
            k kVar = this.b.b;
            HttpPost httpPost = new HttpPost(String.valueOf(j.this.b) + kVar.a());
            httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");
            httpPost.setHeader("Accept", "application/json; charset=UTF-8");
            httpPost.setHeader("X-Chartboost-Client", "Chartboost-Android-SDK 3.4.0");
            httpPost.setHeader("X-Chartboost-API", "3.3.0");
            Map<String, Object> mapE = kVar.e();
            if (mapE != null) {
                for (Map.Entry<String, Object> entry : mapE.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue().toString());
                }
            }
            synchronized (j.this) {
                i = j.g + 1;
                j.g = i;
            }
            try {
                try {
                    if (kVar.d() != null) {
                        StringEntity stringEntity = new StringEntity(kVar.d().toString());
                        stringEntity.setContentType(new BasicHeader("Content-Type", "application/json"));
                        httpPost.setEntity(stringEntity);
                    } else {
                        Log.i("HTTP Request Body " + i + " " + kVar.c(), "<empty>");
                    }
                    httpResponseExecute = l.b().execute(httpPost);
                    try {
                        int statusCode = httpResponseExecute.getStatusLine().getStatusCode();
                        HttpEntity entity = httpResponseExecute.getEntity();
                        try {
                            InputStream content = entity.getContent();
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(content, XMLStreamWriterImpl.UTF_8), 2048);
                            StringBuilder sb = new StringBuilder(content.available());
                            while (true) {
                                String line = bufferedReader.readLine();
                                if (line == null) {
                                    break;
                                } else {
                                    sb.append(line).append("\n");
                                }
                            }
                            bufferedReader.close();
                            string = sb.toString();
                        } catch (Exception e) {
                            string = httpResponseExecute.toString();
                        }
                        if (statusCode < 300 && statusCode >= 200) {
                            if (string != null) {
                                JSONObject jSONObject = new JSONObject(new JSONTokener(string));
                                com.chartboost.sdk.Libraries.e.a aVarG = this.b.b.g();
                                StringBuilder sb2 = new StringBuilder();
                                if (aVarG == null || aVarG.a(jSONObject, sb2)) {
                                    this.b.c = jSONObject;
                                    str = null;
                                } else {
                                    str = "Json response failed validation: " + sb2.toString();
                                }
                            } else {
                                str = "Response is not a valid json object";
                            }
                            com.chartboost.sdk.Libraries.d.a(entity);
                        } else {
                            Log.w("Chartboost", "Request failed. Response code: " + statusCode + ", body: " + string);
                            com.chartboost.sdk.Libraries.d.a(entity);
                            str = "Request failed. Response code: " + statusCode + " is not valid (>=200 and <300)";
                        }
                    } catch (Exception e2) {
                        e = e2;
                        str = "Exception on http request: " + e.getLocalizedMessage();
                        Log.e("Chartboost", str);
                        com.chartboost.sdk.Libraries.d.a(httpResponseExecute);
                    }
                } finally {
                    j.this.d.remove(this.b.d.intValue());
                }
            } catch (Exception e3) {
                e = e3;
                httpResponseExecute = null;
            }
            a(this.b.c != null, str);
        }

        private void a(final boolean z, final String str) {
            j.this.f.post(new Runnable() { // from class: com.chartboost.sdk.impl.j.d.1
                @Override // java.lang.Runnable
                public void run() {
                    a aVar = d.this.b;
                    if (z && aVar.c != null) {
                        if (aVar.e != null) {
                            aVar.e.a(aVar.c, aVar.b);
                            return;
                        }
                        return;
                    }
                    aVar.a(str);
                }
            });
        }
    }
}
