package com.topfreegames.bikerace.k;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.util.SparseBooleanArray;
import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.google.ads.AdActivity;
import com.topfreegames.bikerace.ap;
import com.topfreegames.bikerace.z;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.crypto.Cipher;
import org.c.d.g;
import org.c.d.i;
import org.c.d.k;
import org.c.d.l;
import org.c.d.m;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: RemoteData.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ boolean f1273a;
    private static final Map<com.topfreegames.bikerace.c, String> b;
    private static final Map<String, com.topfreegames.bikerace.c> c;
    private static final Map<com.topfreegames.bikerace.worldcup.b, String> d;
    private static final Map<String, com.topfreegames.bikerace.worldcup.b> e;
    private static final Map<com.topfreegames.bikerace.c, String> f;
    private static final Map<String, com.topfreegames.bikerace.c> g;
    private static final List<com.topfreegames.bikerace.c> h;
    private static a w;
    private SharedPreferences j;
    private Cipher k;
    private Cipher l;
    private z o;
    private ConnectivityManager p;
    private ExecutorService q;
    private org.c.e.c r;
    private k s;
    private k t;
    private String u;
    private e i = null;
    private SparseBooleanArray m = new SparseBooleanArray();
    private HashMap<com.topfreegames.bikerace.worldcup.a, Boolean> n = new HashMap<>();
    private long v = 0;

    static {
        f1273a = !a.class.desiredAssertionStatus();
        HashMap map = new HashMap();
        map.put(com.topfreegames.bikerace.c.GHOST, "ghost");
        map.put(com.topfreegames.bikerace.c.NINJA, "ninja");
        map.put(com.topfreegames.bikerace.c.COP, "cop");
        map.put(com.topfreegames.bikerace.c.RETRO, "retro");
        map.put(com.topfreegames.bikerace.c.BRONZE, "bronze");
        map.put(com.topfreegames.bikerace.c.SILVER, "silver");
        map.put(com.topfreegames.bikerace.c.GOLD, "gold");
        map.put(com.topfreegames.bikerace.c.GIRL, "girl");
        map.put(com.topfreegames.bikerace.c.ACROBATIC, "acrobatic");
        map.put(com.topfreegames.bikerace.c.BEAT, "hog");
        map.put(com.topfreegames.bikerace.c.SPAM, "high_tech");
        map.put(com.topfreegames.bikerace.c.ZOMBIE, "zombie");
        map.put(com.topfreegames.bikerace.c.ARMY, "army");
        map.put(com.topfreegames.bikerace.c.HALLOWEEN, "halloween");
        map.put(com.topfreegames.bikerace.c.THANKSGIVING, "thanksgiving");
        map.put(com.topfreegames.bikerace.c.SANTA, "santa");
        map.put(com.topfreegames.bikerace.c.EASTER, "easter");
        map.put(com.topfreegames.bikerace.c.WORLDCUP_USA, "wc_usa");
        map.put(com.topfreegames.bikerace.c.WORLDCUP_ENGLAND, "wc_england");
        map.put(com.topfreegames.bikerace.c.WORLDCUP_AUSTRALIA, "wc_australia");
        map.put(com.topfreegames.bikerace.c.WORLDCUP_NETHERLANDS, "wc_netherlands");
        map.put(com.topfreegames.bikerace.c.WORLDCUP_FRANCE, "wc_france");
        map.put(com.topfreegames.bikerace.c.WORLDCUP_GERMANY, "wc_germany");
        map.put(com.topfreegames.bikerace.c.WORLDCUP_BRAZIL, "wc_brazil");
        map.put(com.topfreegames.bikerace.c.WORLDCUP_SPAIN, "wc_spain");
        map.put(com.topfreegames.bikerace.c.WORLDCUP_JAPAN, "wc_japan");
        map.put(com.topfreegames.bikerace.c.WORLDCUP_BELGIUM, "wc_belgium");
        map.put(com.topfreegames.bikerace.c.WORLDCUP_MEXICO, "wc_mexico");
        map.put(com.topfreegames.bikerace.c.WORLDCUP_ITALY, "wc_italy");
        map.put(com.topfreegames.bikerace.c.WORLDCUP_ARGENTINA, "wc_argentina");
        map.put(com.topfreegames.bikerace.c.NOT_USED_IOS_DAILY_WORLD_BIKE_INDEX_HOLDER, "dailybonus");
        b = Collections.unmodifiableMap(map);
        HashMap map2 = new HashMap();
        for (Map.Entry<com.topfreegames.bikerace.c, String> entry : b.entrySet()) {
            map2.put(entry.getValue(), entry.getKey());
        }
        c = Collections.unmodifiableMap(map2);
        if (!f1273a && b.size() == com.topfreegames.bikerace.c.valuesCustom().length) {
            throw new AssertionError();
        }
        HashMap map3 = new HashMap();
        map3.put(com.topfreegames.bikerace.worldcup.b.BACK, "back");
        map3.put(com.topfreegames.bikerace.worldcup.b.FRONT, "front");
        map3.put(com.topfreegames.bikerace.worldcup.b.HELMET, "helmet");
        map3.put(com.topfreegames.bikerace.worldcup.b.SUIT, "suit");
        d = Collections.unmodifiableMap(map3);
        HashMap map4 = new HashMap();
        for (Map.Entry<com.topfreegames.bikerace.worldcup.b, String> entry2 : d.entrySet()) {
            map4.put(entry2.getValue(), entry2.getKey());
        }
        e = Collections.unmodifiableMap(map4);
        HashMap map5 = new HashMap();
        map5.put(com.topfreegames.bikerace.c.WORLDCUP_ARGENTINA, "argentina");
        map5.put(com.topfreegames.bikerace.c.WORLDCUP_AUSTRALIA, "australia");
        map5.put(com.topfreegames.bikerace.c.WORLDCUP_BELGIUM, "belgium");
        map5.put(com.topfreegames.bikerace.c.WORLDCUP_BRAZIL, "brazil");
        map5.put(com.topfreegames.bikerace.c.WORLDCUP_ENGLAND, "england");
        map5.put(com.topfreegames.bikerace.c.WORLDCUP_FRANCE, "france");
        map5.put(com.topfreegames.bikerace.c.WORLDCUP_GERMANY, "germany");
        map5.put(com.topfreegames.bikerace.c.WORLDCUP_ITALY, "italy");
        map5.put(com.topfreegames.bikerace.c.WORLDCUP_JAPAN, "japan");
        map5.put(com.topfreegames.bikerace.c.WORLDCUP_MEXICO, "mexico");
        map5.put(com.topfreegames.bikerace.c.WORLDCUP_NETHERLANDS, "netherlands");
        map5.put(com.topfreegames.bikerace.c.WORLDCUP_SPAIN, "spain");
        map5.put(com.topfreegames.bikerace.c.WORLDCUP_USA, "usa");
        f = Collections.unmodifiableMap(map5);
        HashMap map6 = new HashMap();
        for (Map.Entry<com.topfreegames.bikerace.c, String> entry3 : f.entrySet()) {
            map6.put(entry3.getValue(), entry3.getKey());
        }
        g = Collections.unmodifiableMap(map6);
        ArrayList arrayList = new ArrayList();
        arrayList.add(com.topfreegames.bikerace.c.REGULAR);
        arrayList.add(com.topfreegames.bikerace.c.SUPER);
        arrayList.add(com.topfreegames.bikerace.c.KIDS);
        arrayList.add(com.topfreegames.bikerace.c.ULTRA);
        h = Collections.unmodifiableList(arrayList);
    }

    private a(Context context, z zVar) {
        c cVar = null;
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        if (zVar == null) {
            throw new IllegalArgumentException("Data cannot be null!");
        }
        this.p = (ConnectivityManager) context.getSystemService("connectivity");
        this.j = context.getSharedPreferences("remote", 0);
        this.o = zVar;
        this.k = com.topfreegames.bikerace.d.a.a(context);
        this.l = com.topfreegames.bikerace.d.a.b(context);
        this.r = new org.c.a.a().a(new c(cVar)).a("brclient1.0").b("bricifurgateongbrtrgtntrntuwlil").a();
    }

    public static a a() {
        a aVar;
        synchronized (a.class) {
            if (w == null) {
                throw new IllegalStateException("Call init() first!");
            }
            aVar = w;
        }
        return aVar;
    }

    public static void a(Context context, z zVar) {
        if (w == null) {
            synchronized (a.class) {
                if (w == null) {
                    w = new a(context, zVar);
                }
            }
        }
    }

    private static String b(com.topfreegames.bikerace.c cVar) {
        return String.format(Locale.US, "bws_%d", Integer.valueOf(cVar.ordinal()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(i iVar) {
        return iVar.g() ? String.format(Locale.US, "Corrupted: %s - %s", iVar.e(), iVar.b()) : String.format(Locale.US, "%d: %s - %s", Integer.valueOf(iVar.d()), iVar.e(), iVar.b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String c(String str) {
        return String.format(Locale.US, "http://data-api.topfreegames.com/api/v1/players/%s", str);
    }

    public void a(String str) {
        if (ap.d() && "" != 0 && !"".equals("")) {
            str = "";
        }
        String strE = "";
        String string = this.j.getString("lui", "");
        if (!string.equals("")) {
            try {
                strE = e(string);
            } catch (Exception e2) {
                if (ap.d()) {
                    e2.printStackTrace();
                }
            }
        }
        if (!strE.equals(str)) {
            k();
            l();
            try {
                this.j.edit().putString("lui", d(str)).commit();
            } catch (Exception e3) {
                if (ap.d()) {
                    e3.printStackTrace();
                }
            }
            a((e) null);
        }
        this.u = str;
    }

    public void a(e eVar) {
        boolean zE = e();
        boolean z = com.topfreegames.c.a.a().getTime() - this.v > 0;
        if (zE && z) {
            this.v = com.topfreegames.c.a.a().getTime();
            final b bVar = new b(this, eVar);
            a(b(bVar));
            e(bVar);
            d(bVar);
            a(new Runnable() { // from class: com.topfreegames.bikerace.k.a.1
                @Override // java.lang.Runnable
                public void run() {
                    bVar.a();
                }
            });
            return;
        }
        if (eVar != null) {
            if (!zE) {
                eVar.a("User not defined");
            } else if (!z) {
                eVar.a("Avoiding many consecutive syncs. Wait some time before trying again.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(JSONObject... jSONObjectArr) throws JSONException {
        if (jSONObjectArr.length > 1) {
            throw new UnsupportedOperationException("Server does not support multiple commands per operation (yet)!");
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(AdActivity.ORIENTATION_PARAM, jSONObjectArr[0]);
        return jSONObject.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject a(ArrayList<com.topfreegames.bikerace.c> arrayList) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("bikes");
        Iterator<com.topfreegames.bikerace.c> it = arrayList.iterator();
        while (it.hasNext()) {
            jSONArray.put(b.get(it.next()));
        }
        return a("sadd", jSONArray);
    }

    private JSONObject a(String str, JSONArray jSONArray) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(AdActivity.COMPONENT_NAME_PARAM, str);
        jSONObject.put(AdActivity.PACKAGE_NAME_PARAM, jSONArray);
        return jSONObject;
    }

    private Runnable b(final e eVar) {
        return new Runnable() { // from class: com.topfreegames.bikerace.k.a.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    i iVarA = a.this.a((String) null, a.c(a.this.u), l.GET);
                    if (iVarA.a() && !iVarA.g()) {
                        ArrayList<com.topfreegames.bikerace.c> arrayList = new ArrayList();
                        a.this.a(iVarA.b(), (ArrayList<com.topfreegames.bikerace.c>) arrayList, (ArrayList<com.topfreegames.bikerace.worldcup.a>) new ArrayList());
                        for (com.topfreegames.bikerace.c cVar : arrayList) {
                            if (a.this.o.a(cVar)) {
                                a.this.o.d(cVar);
                            }
                            a.this.d(cVar);
                        }
                        if (eVar != null) {
                            eVar.b();
                            return;
                        }
                        return;
                    }
                    if (eVar != null) {
                        eVar.a(a.b(iVarA));
                    }
                } catch (Exception e2) {
                    if (eVar != null) {
                        eVar.a(e2);
                    }
                }
            }
        };
    }

    private Runnable c(final e eVar) {
        return new Runnable() { // from class: com.topfreegames.bikerace.k.a.3
            @Override // java.lang.Runnable
            public void run() {
                boolean z = false;
                try {
                    ArrayList arrayList = new ArrayList();
                    for (com.topfreegames.bikerace.c cVar : com.topfreegames.bikerace.c.valuesCustom()) {
                        if (!a.this.o.a(cVar) && !a.this.c(cVar) && !a.h.contains(cVar)) {
                            arrayList.add(cVar);
                        }
                    }
                    if (arrayList.size() > 0) {
                        i iVarA = a.this.a(a.this.a(a.this.a((ArrayList<com.topfreegames.bikerace.c>) arrayList)), a.c(a.this.u), l.POST);
                        if (iVarA.a() && !iVarA.g()) {
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                a.this.d((com.topfreegames.bikerace.c) it.next());
                            }
                            z = true;
                        } else if (eVar != null) {
                            eVar.a(a.b(iVarA));
                        }
                    } else {
                        z = true;
                    }
                    if (z && eVar != null) {
                        eVar.b();
                    }
                } catch (Exception e2) {
                    if (eVar != null) {
                        eVar.a(e2);
                    }
                }
            }
        };
    }

    private boolean e() {
        return (this.u == null || this.u.equals("") || this.r == null) ? false : true;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0040  */
    private void a(JSONArray jSONArray, ArrayList<com.topfreegames.bikerace.worldcup.a> arrayList) {
        com.topfreegames.bikerace.worldcup.a aVar;
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            String[] strArrSplit = jSONArray.getString(i).split("_");
            if (strArrSplit.length != 2) {
                aVar = null;
            } else {
                com.topfreegames.bikerace.c cVar = g.get(strArrSplit[0]);
                com.topfreegames.bikerace.worldcup.b bVar = e.get(strArrSplit[1]);
                if (cVar == null || bVar == null) {
                    aVar = null;
                } else {
                    aVar = new com.topfreegames.bikerace.worldcup.a(cVar, bVar);
                }
            }
            if (aVar != null) {
                arrayList.add(aVar);
            }
        }
    }

    private void b(JSONArray jSONArray, ArrayList<com.topfreegames.bikerace.c> arrayList) throws JSONException {
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            com.topfreegames.bikerace.c cVar = c.get(jSONArray.getString(i));
            if (cVar != null) {
                arrayList.add(cVar);
            }
        }
    }

    private synchronized void a(Runnable runnable) {
        if (f()) {
            try {
                this.q.execute(runnable);
            } catch (Exception e2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(com.topfreegames.bikerace.c cVar) {
        return this.j.getBoolean(b(cVar), false);
    }

    private boolean f() {
        NetworkInfo activeNetworkInfo = this.p.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(com.topfreegames.bikerace.c cVar) {
        this.j.edit().putBoolean(b(cVar), true).commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, ArrayList<com.topfreegames.bikerace.c> arrayList, ArrayList<com.topfreegames.bikerace.worldcup.a> arrayList2) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        if (jSONObject.has("bikes")) {
            b(jSONObject.getJSONArray("bikes"), arrayList);
        }
        if (jSONObject.has("bike_parts")) {
            a(jSONObject.getJSONArray("bike_parts"), arrayList2);
        }
    }

    private void g() throws Exception {
        try {
            HashMap map = new HashMap();
            map.put("userId", this.u);
            this.t = this.r.a(map);
            this.r.a(this.t);
            j();
        } catch (Exception e2) {
            l();
            throw e2;
        }
    }

    private void h() throws Exception {
        try {
            this.s = this.r.a(this.t, new m(this.t.a()));
            i();
        } catch (Exception e2) {
            k();
            throw e2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public i a(String str, String str2, l lVar) throws Exception {
        org.c.d.c cVar = new org.c.d.c(lVar, c(this.u));
        if (str != null && str.length() > 0) {
            cVar.a(g.CONTENT_JSON);
            cVar.a(str);
        }
        if (this.t == null || this.t.d()) {
            n();
            if (this.t == null) {
                g();
            }
        }
        if (this.s == null || this.s.d()) {
            m();
            if (this.s == null) {
                h();
            }
        }
        this.r.a(this.s, cVar);
        i iVarC = cVar.c();
        if (!this.r.a(this.s, cVar, iVarC)) {
            iVarC.f();
        }
        if (!iVarC.a() && iVarC.d() == 401) {
            k();
            l();
        }
        return iVarC;
    }

    private void d(e eVar) {
    }

    private void e(e eVar) {
        a(c(eVar));
    }

    private void a(com.topfreegames.bikerace.c cVar, boolean z) {
        this.m.put(cVar.ordinal(), z);
    }

    private void a(com.topfreegames.bikerace.worldcup.a aVar, boolean z) {
        this.n.put(aVar, Boolean.valueOf(z));
    }

    public void a(com.topfreegames.bikerace.c cVar) {
        if (e()) {
            a(cVar, true);
            e(this.i);
        }
    }

    public void a(com.topfreegames.bikerace.worldcup.a aVar) {
        if (e()) {
            a(aVar, true);
            d(this.i);
        }
    }

    private void i() throws UnsupportedEncodingException {
        if (this.s != null) {
            String strD = d(this.s.a());
            this.j.edit().putString("at", strD).putString("as", d(this.s.b())).putLong("ae", this.s.c()).commit();
        }
    }

    private void j() throws UnsupportedEncodingException {
        if (this.t != null) {
            String strD = d(this.t.a());
            this.j.edit().putString("rt", strD).putString("rs", d(this.t.b())).putLong("re", this.t.c()).commit();
        }
    }

    private void k() {
        this.s = null;
        this.j.edit().putString("at", "").putString("as", "").putLong("ae", 0L).commit();
    }

    private void l() {
        this.t = null;
        this.j.edit().putString("rt", "").putString("rs", "").putLong("re", 0L).commit();
    }

    private void m() {
        k kVar = null;
        String string = this.j.getString("at", "");
        if (!string.equals("")) {
            String string2 = this.j.getString("as", "");
            if (!string2.equals("")) {
                k kVar2 = new k(e(string), e(string2), this.j.getLong("ae", 0L));
                if (!kVar2.d()) {
                    kVar = kVar2;
                }
            }
        }
        this.s = kVar;
    }

    private void n() {
        k kVar = null;
        String string = this.j.getString("rt", "");
        if (!string.equals("")) {
            String string2 = this.j.getString("rs", "");
            if (!string2.equals("")) {
                k kVar2 = new k(e(string), e(string2), this.j.getLong("re", 0L));
                if (!kVar2.d()) {
                    kVar = kVar2;
                }
            }
        }
        this.t = kVar;
    }

    private String d(String str) throws UnsupportedEncodingException {
        byte[] bytes = str.trim().getBytes(XMLStreamWriterImpl.UTF_8);
        return Base64.encodeToString(this.k.doFinal(bytes, 0, bytes.length), 0).trim();
    }

    private String e(String str) {
        byte[] bArrDecode = Base64.decode(str.trim(), 0);
        return new String(this.l.doFinal(bArrDecode, 0, bArrDecode.length), XMLStreamWriterImpl.UTF_8);
    }

    public void b() {
        if (this.q == null || this.q.isShutdown()) {
            this.q = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.topfreegames.bikerace.k.a.4
                @Override // java.util.concurrent.ThreadFactory
                public Thread newThread(Runnable runnable) {
                    Thread thread = new Thread(runnable);
                    thread.setPriority(4);
                    return thread;
                }
            });
        }
    }

    public void c() {
        if (this.q != null) {
            this.q.shutdownNow();
        }
    }
}
