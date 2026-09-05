package com.applovin.impl.a;

import com.google.ads.AdActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ac extends x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Collection f179a = Arrays.asList(')', ']', '\"', '\'', ' ');
    private static volatile int b = 0;
    private final JSONObject g;
    private final com.applovin.a.d h;

    ac(JSONObject jSONObject, com.applovin.a.d dVar, d dVar2) {
        super("RenderAd", dVar2);
        this.g = jSONObject;
        this.h = dVar;
    }

    private float a(String str, com.applovin.a.g gVar, int i) {
        if (gVar.equals(com.applovin.a.g.b)) {
            return 0.5f;
        }
        return (gVar.equals(com.applovin.a.g.f156a) && str != null && i == -1) ? 0.5f : 0.0f;
    }

    private com.applovin.impl.adview.ad a(int i) {
        return i == 1 ? com.applovin.impl.adview.ad.WhiteXOnTransparentGrey : com.applovin.impl.adview.ad.WhiteXOnOpaqueBlack;
    }

    private com.applovin.impl.adview.ad a(String str) {
        return str != null ? com.applovin.impl.adview.ad.WhiteXOnTransparentGrey : com.applovin.impl.adview.ad.WhiteXOnOpaqueBlack;
    }

    private String a(String str, String str2) {
        File fileA = ah.a(str2, this.d.h(), true);
        if (fileA == null) {
            return null;
        }
        if (fileA.exists()) {
            this.e.a(this.c, "Loaded " + str2 + " from cache: file://" + fileA.getAbsolutePath());
            return "file://" + fileA.getAbsolutePath();
        }
        if (a(fileA, str + str2)) {
            return "file://" + fileA.getAbsolutePath();
        }
        return null;
    }

    private String a(String str, Map map) {
        af afVar = new af(str, this.e);
        afVar.a(map);
        String strA = afVar.a();
        return ((Boolean) this.d.a(j.H)).booleanValue() ? b(strA) : strA;
    }

    private List a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        return arrayList;
    }

    private Map a(List list) {
        HashMap map = new HashMap(list.size());
        for (int i = 0; i < list.size(); i++) {
            map.put(b(i), list.get(i));
        }
        return map;
    }

    private void a(JSONObject jSONObject) throws JSONException {
        int i;
        float fA;
        com.applovin.impl.adview.ad adVarA;
        String string;
        String string2;
        int i2 = 0;
        String string3 = jSONObject.getString(AdActivity.HTML_PARAM);
        com.applovin.a.f fVarA = jSONObject.has("size") ? com.applovin.a.f.a(jSONObject.getString("size")) : com.applovin.a.f.f155a;
        String strC = null;
        if (string3 == null || string3.length() <= 0) {
            this.e.d(this.c, "No HTML received for requested ad");
            b();
            return;
        }
        if (!jSONObject.has("redirect_urls")) {
            this.e.d(this.c, "Ad server has not returned a redirect URL");
            b();
            return;
        }
        List listA = a((JSONArray) jSONObject.get("redirect_urls"));
        if (jSONObject.has("video") && (string2 = jSONObject.getString("video")) != null && !string2.isEmpty()) {
            strC = c(string2);
        }
        String strA = a(string3, a(listA));
        b bVarValueOf = jSONObject.has("ad_target") ? b.valueOf(jSONObject.getString("ad_target").toUpperCase()) : b.DEFAULT;
        com.applovin.a.g gVarA = jSONObject.has("ad_type") ? com.applovin.a.g.a(jSONObject.getString("ad_type").toUpperCase()) : com.applovin.a.g.f156a;
        int i3 = jSONObject.has("ad_id") ? jSONObject.getInt("ad_id") : -1;
        if (jSONObject.has("countdown_length")) {
            try {
                i = jSONObject.getInt("countdown_length");
            } catch (JSONException e) {
                i = 0;
            }
        } else {
            i = 0;
        }
        if (jSONObject.has("close_delay")) {
            try {
                i2 = jSONObject.getInt("close_delay");
            } catch (JSONException e2) {
            }
        }
        if (jSONObject.has("close_delay_graphic")) {
            try {
                fA = jSONObject.getInt("close_delay_graphic");
            } catch (JSONException e3) {
                fA = a(strC, gVarA, i2);
            }
        } else {
            fA = a(strC, gVarA, i2);
        }
        if (jSONObject.has("close_style")) {
            try {
                adVarA = a(jSONObject.getInt("close_style"));
            } catch (JSONException e4) {
                adVarA = a(strC);
            }
        } else {
            adVarA = a(strC);
        }
        if (jSONObject.has("clcodes")) {
            try {
                string = ((JSONArray) jSONObject.get("clcodes")).getString(0);
            } catch (JSONException e5) {
                string = "";
            }
        } else {
            string = "";
        }
        a(new c().a(strA).a(fVarA).a(gVarA).a(listA).b(strC).a(bVarValueOf).a(adVarA).a(i2).b(fA).a(i).b(i3).c(string).a());
    }

    /* JADX WARN: Code duplicated, block: B:104:0x011b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:106:0x0116 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:125:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:94:0x0111 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    private boolean a(File file, String str) throws Throwable {
        HttpURLConnection httpURLConnection;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2;
        InputStream inputStream = null;
        this.e.a(this.c, "Starting caching of " + str + " into " + file.getAbsoluteFile());
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
                try {
                    httpURLConnection2.setConnectTimeout(((Integer) this.d.a(j.t)).intValue());
                    httpURLConnection2.setReadTimeout(((Integer) this.d.a(j.v)).intValue());
                    httpURLConnection2.setDefaultUseCaches(true);
                    httpURLConnection2.setUseCaches(true);
                    httpURLConnection2.setAllowUserInteraction(false);
                    httpURLConnection2.setInstanceFollowRedirects(true);
                    inputStream = httpURLConnection2.getInputStream();
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = inputStream.read(bArr, 0, bArr.length);
                        if (i < 0) {
                            break;
                        }
                        try {
                            fileOutputStream.write(bArr, 0, i);
                        } catch (Exception e) {
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (Exception e2) {
                                }
                            }
                            b();
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Exception e3) {
                                }
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (Exception e4) {
                                }
                            }
                            if (httpURLConnection2 != null) {
                                try {
                                    httpURLConnection2.disconnect();
                                } catch (Exception e5) {
                                }
                            }
                            return false;
                        }
                    }
                    fileOutputStream.flush();
                    this.e.a(this.c, "Caching completed for " + file);
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e6) {
                        }
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e7) {
                        }
                    }
                    if (httpURLConnection2 != null) {
                        try {
                            httpURLConnection2.disconnect();
                        } catch (Exception e8) {
                        }
                    }
                    return true;
                } catch (IOException e9) {
                    fileOutputStream2 = fileOutputStream;
                    e = e9;
                    httpURLConnection = httpURLConnection2;
                    try {
                        this.e.b(this.c, "Failed to cache \"" + str + "\" into \"" + file.getAbsolutePath() + "\"", e);
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Exception e10) {
                            }
                        }
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (Exception e11) {
                            }
                        }
                        if (httpURLConnection != null) {
                            try {
                                httpURLConnection.disconnect();
                            } catch (Exception e12) {
                            }
                        }
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Exception e13) {
                            }
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e14) {
                            }
                        }
                        if (httpURLConnection != null) {
                            throw th;
                        }
                        try {
                            httpURLConnection.disconnect();
                            throw th;
                        } catch (Exception e15) {
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    httpURLConnection = httpURLConnection2;
                    th = th2;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (httpURLConnection != null) {
                        throw th;
                    }
                    httpURLConnection.disconnect();
                    throw th;
                }
            } catch (IOException e16) {
                e = e16;
                httpURLConnection = null;
                fileOutputStream2 = fileOutputStream;
            } catch (Throwable th3) {
                th = th3;
                httpURLConnection = null;
            }
        } catch (IOException e17) {
            e = e17;
            httpURLConnection = null;
            fileOutputStream2 = null;
        } catch (Throwable th4) {
            th = th4;
            httpURLConnection = null;
            fileOutputStream = null;
        }
    }

    private String b(int i) {
        return i == 0 ? "click" : "click" + i;
    }

    private String b(String str) {
        StringBuffer stringBuffer = new StringBuffer(str);
        for (String str2 : ((String) this.d.a(j.I)).split(",")) {
            int i = 0;
            int iIndexOf = 0;
            while (iIndexOf < stringBuffer.length() && (iIndexOf = stringBuffer.indexOf(str2, i)) != -1) {
                int length = stringBuffer.length();
                i = iIndexOf;
                while (!f179a.contains(Character.valueOf(stringBuffer.charAt(i))) && i < length) {
                    i++;
                }
                if (i <= iIndexOf || i == length) {
                    this.e.a(this.c, "Unable to cache resource; ad HTML is invalid.");
                } else {
                    String strA = a(str2, stringBuffer.substring(str2.length() + iIndexOf, i));
                    if (strA != null) {
                        stringBuffer.replace(iIndexOf, i, strA);
                    }
                }
            }
        }
        return stringBuffer.toString();
    }

    private String c(String str) {
        String str2 = "alvideo" + b + str.substring(str.lastIndexOf(".") + 1, str.length());
        File fileA = ah.a(str2, this.d.h(), false);
        fileA.delete();
        if (!a(fileA, str)) {
            return null;
        }
        b = (b + 1) % 5;
        return str2;
    }

    protected void a(com.applovin.a.a aVar) {
        if (this.h != null) {
            this.h.a(aVar);
        }
    }

    protected void b() {
        try {
            if (this.h != null) {
                this.h.a(-6);
            }
        } catch (Throwable th) {
            this.e.b(this.c, "Unable process a failure to recieve an ad", th);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        this.e.a(this.c, "Rendering ad...");
        try {
            a(this.g);
        } catch (IllegalArgumentException e) {
            this.e.b(this.c, "Ad response is not valid", e);
            b();
        } catch (JSONException e2) {
            this.e.b(this.c, "Unable to parse ad service response", e2);
            b();
        } catch (Exception e3) {
            this.e.b(this.c, "Unable to render ad", e3);
            b();
        }
    }
}
