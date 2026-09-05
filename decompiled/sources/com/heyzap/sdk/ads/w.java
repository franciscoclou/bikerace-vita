package com.heyzap.sdk.ads;

import android.content.Context;
import android.net.Uri;
import android.os.Looper;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: Video.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class w extends c {
    public static String g = "video";
    private Context h;
    private Boolean i;
    private String j;
    private Integer k;
    private int l;
    private int m;
    private ArrayList<String> n;
    private ArrayList<String> o;
    private Integer p;
    private Integer q;
    private Integer r;
    private Integer s;
    private Boolean t;
    private Boolean u;
    private Boolean v;
    private Boolean w;
    private String x;
    private com.heyzap.internal.e y;
    private d z;

    public w(JSONObject jSONObject) {
        super(jSONObject);
        this.i = false;
        this.k = 0;
        this.l = -1;
        this.m = -1;
        this.n = new ArrayList<>();
        this.o = new ArrayList<>();
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = true;
        this.u = false;
        this.v = true;
        this.w = false;
        this.x = null;
        this.y = null;
        this.z = null;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public w(Context context, JSONObject jSONObject, d dVar) throws Exception {
        this(jSONObject);
        this.h = context;
        this.b = g;
        this.z = dVar;
        if (jSONObject.has("interstitial")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("interstitial");
            if (jSONObject2.has("meta")) {
                JSONObject jSONObject3 = jSONObject2.getJSONObject("meta");
                this.l = jSONObject3.optInt("height", this.l);
                this.m = jSONObject3.optInt("width", this.m);
            }
            this.j = jSONObject2.getString("html_data");
            this.k = Integer.valueOf(jSONObject2.optInt("background_color", this.k.intValue()));
        }
        if (jSONObject.has("video")) {
            JSONObject jSONObject4 = jSONObject.getJSONObject("video");
            if (jSONObject4.has("meta")) {
                JSONObject jSONObject5 = jSONObject4.getJSONObject("meta");
                this.p = Integer.valueOf(jSONObject5.optInt("width", this.p.intValue()));
                this.q = Integer.valueOf(jSONObject5.optInt("height", this.q.intValue()));
                this.r = Integer.valueOf(jSONObject5.optInt("length", this.r.intValue()));
            }
            this.s = Integer.valueOf(jSONObject4.optInt("lockout_time", 0));
            this.t = Boolean.valueOf(jSONObject4.optBoolean("allow_skip", this.t.booleanValue()));
            this.u = Boolean.valueOf(jSONObject4.optBoolean("allow_hide", this.u.booleanValue()));
            this.v = Boolean.valueOf(jSONObject4.optBoolean("allow_click", this.v.booleanValue()));
            this.w = Boolean.valueOf(jSONObject4.optBoolean("post_roll_interstitial", this.w.booleanValue()));
            if (jSONObject4.has("static_url")) {
                JSONArray jSONArray = jSONObject4.getJSONArray("static_url");
                for (int i = 0; i < jSONArray.length(); i++) {
                    this.n.add(jSONArray.getString(i));
                }
                if (this.n.size() > 0) {
                    u();
                }
            }
            if (jSONObject4.has("streaming_url")) {
                JSONArray jSONArray2 = jSONObject4.getJSONArray("streaming_url");
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    this.o.add(jSONArray2.getString(i2));
                }
            }
            if (this.n.size() == 0 && this.o.size() == 0) {
                throw new Exception("No video URLs.");
            }
        }
    }

    public String j() {
        return this.j;
    }

    public int k() {
        return this.m;
    }

    public int l() {
        return this.l;
    }

    public int m() {
        return this.k.intValue();
    }

    public Boolean n() {
        return this.t;
    }

    public Boolean o() {
        return this.u;
    }

    public Boolean p() {
        return this.v;
    }

    public int q() {
        return this.s.intValue();
    }

    public Boolean r() {
        return this.w;
    }

    public String s() {
        return this.x;
    }

    public Uri t() {
        if (this.o.size() > 0) {
            return Uri.parse(this.o.get(0));
        }
        return null;
    }

    public void u() {
        new Thread(new Runnable() { // from class: com.heyzap.sdk.ads.w.1
            @Override // java.lang.Runnable
            public void run() {
                Looper.prepare();
                try {
                    File file = new File(u.f799a.getCacheDir() + "/heyzap");
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    final String str = "heyzap/video-" + w.this.d();
                    w.this.y = new com.heyzap.internal.e(u.f799a, new com.heyzap.internal.f() { // from class: com.heyzap.sdk.ads.w.1.1
                        @Override // com.heyzap.internal.f
                        public void a(Integer num) {
                        }

                        @Override // com.heyzap.internal.f
                        public void a(URL url) {
                            com.heyzap.internal.k.b("(DOWNLOADING) " + w.this.d() + " -> " + url.toString());
                        }

                        @Override // com.heyzap.internal.f
                        public void a(com.heyzap.internal.e eVar, URL url, Throwable th) {
                            com.heyzap.internal.k.b("(ERROR) " + url.toString() + " -> " + th.toString());
                            if (th.getMessage().equals("No space left on device")) {
                                com.heyzap.internal.k.b("Dumping caches.");
                                u.d().c();
                            }
                            if (w.this.z != null) {
                                w.this.z.a(th);
                            }
                        }

                        @Override // com.heyzap.internal.f
                        public void a(URL url, String str2, long j) {
                            com.heyzap.internal.k.b("(CACHED) " + w.this.d() + " -> " + url.toString() + " in " + String.valueOf((int) (j / 1000)) + " seconds.");
                            w.this.x = str2;
                            if (new File(u.f799a.getCacheDir() + "/" + str2).exists()) {
                                if (w.this.z != null) {
                                    w.this.z.a(w.this);
                                }
                            } else {
                                Exception exc = new Exception("File does not exist.");
                                if (w.this.z != null) {
                                    w.this.z.a(exc);
                                }
                            }
                        }

                        @Override // com.heyzap.internal.f
                        public void b(URL url) {
                            com.heyzap.internal.k.b("(CANCELLED) " + w.this.d());
                            new File(u.f799a.getCacheDir() + "/" + str).delete();
                        }
                    });
                    if (w.this.n.size() > 0) {
                        w.this.y.execute((String) w.this.n.get(0), str);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void v() {
        if (this.y != null) {
            this.y.cancel(true);
        }
    }

    @Override // com.heyzap.sdk.ads.c
    public void b() throws Exception {
        com.heyzap.internal.k.b("(CLEANUP) " + d());
        v();
        File cacheDir = u.f799a.getCacheDir();
        if (this.x != null) {
            File file = new File(cacheDir, this.x);
            if (file.exists() && !file.delete()) {
                throw new Exception("Failed to delete file!");
            }
        }
    }

    public Boolean a(Boolean bool, int i, int i2, Boolean bool2) {
        if (this.i.booleanValue()) {
            com.heyzap.internal.k.b("Already sent video complete successfully");
            return false;
        }
        com.heyzap.a.g gVar = new com.heyzap.a.g();
        gVar.a("impression_id", d());
        gVar.a("promoted_android_package", e());
        gVar.a("promoted_game_package", e());
        gVar.a("video_duration_seconds", i2);
        gVar.a("watched_duration_seconds", i);
        gVar.a("video_finished", bool2.booleanValue() ? "true" : "false");
        gVar.a("lockout_time_seconds", (int) (((double) this.s.intValue()) / 1000.0d));
        gVar.a("tag", this.d);
        if (bool.booleanValue()) {
            gVar.a("incentivized", "true");
        }
        com.heyzap.internal.a.b(u.f799a, u.e + "/event/video_impression_complete", gVar, new com.heyzap.internal.b() { // from class: com.heyzap.sdk.ads.w.2
            @Override // com.heyzap.a.f
            public void a(JSONObject jSONObject) {
                try {
                    if (jSONObject.getInt("status") == 200) {
                        com.heyzap.internal.k.b("(COMPLETE) [" + w.this.d + "] Impression ID:" + w.this.d());
                        w.this.i = true;
                    }
                } catch (JSONException e) {
                }
            }
        });
        return true;
    }
}
