package com.google.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.webkit.WebView;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.ads.ak;
import com.google.ads.al;
import com.google.ads.searchads.SearchAdRequest;
import com.google.ads.util.AdUtil;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    boolean f671a;
    private String b;
    private String c;
    private String d;
    private String e;
    private boolean f;
    private f g;
    private AdRequest h;
    private WebView i;
    private com.google.ads.l j;
    private String k;
    private String l;
    private LinkedList<String> m;
    private String n;
    private AdSize o;
    private boolean p;
    private volatile boolean q;
    private boolean r;
    private AdRequest.ErrorCode s;
    private boolean t;
    private int u;
    private Thread v;
    private boolean w;
    private d x;

    public enum d {
        ONLINE_USING_BUFFERED_ADS("online_buffered"),
        ONLINE_SERVER_REQUEST("online_request"),
        OFFLINE_USING_BUFFERED_ADS("offline_buffered"),
        OFFLINE_EMPTY("offline_empty");

        public String e;

        d(String str) {
            this.e = str;
        }
    }

    public synchronized void a(boolean z) {
        this.p = z;
    }

    protected c() {
        this.p = false;
        this.x = d.ONLINE_SERVER_REQUEST;
    }

    public c(com.google.ads.l lVar) {
        this.p = false;
        this.x = d.ONLINE_SERVER_REQUEST;
        this.j = lVar;
        this.k = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.m = new LinkedList<>();
        this.s = null;
        this.t = false;
        this.u = -1;
        this.f = false;
        this.r = false;
        this.n = null;
        this.o = null;
        if (lVar.f696a.a().c.a() != null) {
            this.i = new AdWebView(lVar.f696a.a(), null);
            this.i.setWebViewClient(i.a(lVar.f696a.a().b.a(), a.b, false, false));
            this.i.setVisibility(8);
            this.i.setWillNotDraw(true);
            this.g = new f(lVar);
            return;
        }
        this.i = null;
        this.g = null;
        com.google.ads.util.b.e("activity was null while trying to create an AdLoader.");
    }

    protected synchronized void a(String str) {
        this.m.add(str);
    }

    protected void a() {
        com.google.ads.util.b.a("AdLoader cancelled.");
        if (this.i != null) {
            this.i.stopLoading();
            this.i.destroy();
        }
        if (this.v != null) {
            this.v.interrupt();
            this.v = null;
        }
        if (this.g != null) {
            this.g.a();
        }
        this.q = true;
    }

    protected void a(AdRequest adRequest) {
        this.h = adRequest;
        this.q = false;
        this.v = new Thread(this);
        this.v.start();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:116:0x02b9 A[Catch: all -> 0x003a, DONT_GENERATE, TRY_ENTER, TRY_LEAVE, TryCatch #5 {, blocks: (B:4:0x0004, B:6:0x0008, B:11:0x0019, B:13:0x002d, B:14:0x0038, B:19:0x003d, B:21:0x0079, B:23:0x0085, B:24:0x0089, B:26:0x0093, B:27:0x0097, B:29:0x00a1, B:30:0x00a5, B:32:0x00af, B:34:0x00b7, B:53:0x0109, B:55:0x0111, B:35:0x00ba, B:37:0x00c4, B:39:0x00cc, B:40:0x00e1, B:42:0x00e5, B:44:0x00e9, B:45:0x00ed, B:48:0x00ff, B:49:0x0102, B:51:0x0106, B:69:0x015e, B:71:0x0162, B:72:0x0168, B:74:0x016b, B:76:0x016f, B:77:0x0191, B:79:0x0194, B:81:0x01ac, B:83:0x01b4, B:84:0x01bf, B:66:0x0145, B:67:0x015b, B:86:0x01c2, B:87:0x01e2, B:88:0x01e5, B:90:0x01e9, B:92:0x020d, B:93:0x023c, B:94:0x0243, B:96:0x0247, B:98:0x024b, B:100:0x024f, B:102:0x025a, B:114:0x02b5, B:116:0x02b9, B:118:0x02bc, B:120:0x02c0, B:121:0x02c6, B:123:0x02c9, B:125:0x02cd, B:126:0x02ef, B:130:0x030f, B:132:0x0313, B:134:0x0317, B:135:0x0330, B:137:0x0333, B:139:0x0337, B:141:0x0341, B:143:0x034b, B:144:0x036f, B:146:0x0372, B:148:0x0384, B:150:0x0388, B:151:0x0393, B:153:0x0396, B:155:0x03b4, B:156:0x03d2, B:158:0x03d5, B:160:0x03d9, B:161:0x03e1, B:162:0x03fa, B:164:0x03fe, B:166:0x0402, B:168:0x041a, B:170:0x0420, B:172:0x042b, B:178:0x0449, B:180:0x044d, B:59:0x0121, B:181:0x0452, B:175:0x0430, B:176:0x0446, B:105:0x025f, B:106:0x0275, B:128:0x02f2, B:108:0x0278, B:109:0x0288, B:110:0x0292, B:111:0x029f, B:112:0x02b2, B:62:0x0125, B:63:0x0141, B:8:0x000c, B:9:0x0017, B:58:0x0116), top: B:194:0x0004, inners: #0, #1, #2, #3, #4 }] */
    /* JADX WARN: Code duplicated, block: B:118:0x02bc A[Catch: all -> 0x003a, Throwable -> 0x0115, TRY_ENTER, TryCatch #4 {Throwable -> 0x0115, blocks: (B:4:0x0004, B:6:0x0008, B:11:0x0019, B:13:0x002d, B:19:0x003d, B:21:0x0079, B:23:0x0085, B:24:0x0089, B:26:0x0093, B:27:0x0097, B:29:0x00a1, B:30:0x00a5, B:32:0x00af, B:34:0x00b7, B:53:0x0109, B:55:0x0111, B:35:0x00ba, B:37:0x00c4, B:39:0x00cc, B:40:0x00e1, B:42:0x00e5, B:44:0x00e9, B:45:0x00ed, B:48:0x00ff, B:49:0x0102, B:69:0x015e, B:71:0x0162, B:74:0x016b, B:76:0x016f, B:79:0x0194, B:81:0x01ac, B:83:0x01b4, B:66:0x0145, B:86:0x01c2, B:87:0x01e2, B:88:0x01e5, B:90:0x01e9, B:92:0x020d, B:93:0x023c, B:94:0x0243, B:96:0x0247, B:98:0x024b, B:100:0x024f, B:102:0x025a, B:114:0x02b5, B:118:0x02bc, B:120:0x02c0, B:123:0x02c9, B:125:0x02cd, B:130:0x030f, B:132:0x0313, B:134:0x0317, B:137:0x0333, B:139:0x0337, B:141:0x0341, B:143:0x034b, B:146:0x0372, B:148:0x0384, B:150:0x0388, B:153:0x0396, B:155:0x03b4, B:158:0x03d5, B:160:0x03d9, B:161:0x03e1, B:162:0x03fa, B:164:0x03fe, B:166:0x0402, B:168:0x041a, B:170:0x0420, B:172:0x042b, B:178:0x0449, B:180:0x044d, B:181:0x0452, B:175:0x0430, B:105:0x025f, B:128:0x02f2, B:108:0x0278, B:109:0x0288, B:110:0x0292, B:111:0x029f, B:62:0x0125, B:8:0x000c), top: B:193:0x0004, outer: #5 }] */
    /* JADX WARN: Code duplicated, block: B:120:0x02c0 A[Catch: all -> 0x003a, Throwable -> 0x0115, TRY_LEAVE, TryCatch #4 {Throwable -> 0x0115, blocks: (B:4:0x0004, B:6:0x0008, B:11:0x0019, B:13:0x002d, B:19:0x003d, B:21:0x0079, B:23:0x0085, B:24:0x0089, B:26:0x0093, B:27:0x0097, B:29:0x00a1, B:30:0x00a5, B:32:0x00af, B:34:0x00b7, B:53:0x0109, B:55:0x0111, B:35:0x00ba, B:37:0x00c4, B:39:0x00cc, B:40:0x00e1, B:42:0x00e5, B:44:0x00e9, B:45:0x00ed, B:48:0x00ff, B:49:0x0102, B:69:0x015e, B:71:0x0162, B:74:0x016b, B:76:0x016f, B:79:0x0194, B:81:0x01ac, B:83:0x01b4, B:66:0x0145, B:86:0x01c2, B:87:0x01e2, B:88:0x01e5, B:90:0x01e9, B:92:0x020d, B:93:0x023c, B:94:0x0243, B:96:0x0247, B:98:0x024b, B:100:0x024f, B:102:0x025a, B:114:0x02b5, B:118:0x02bc, B:120:0x02c0, B:123:0x02c9, B:125:0x02cd, B:130:0x030f, B:132:0x0313, B:134:0x0317, B:137:0x0333, B:139:0x0337, B:141:0x0341, B:143:0x034b, B:146:0x0372, B:148:0x0384, B:150:0x0388, B:153:0x0396, B:155:0x03b4, B:158:0x03d5, B:160:0x03d9, B:161:0x03e1, B:162:0x03fa, B:164:0x03fe, B:166:0x0402, B:168:0x041a, B:170:0x0420, B:172:0x042b, B:178:0x0449, B:180:0x044d, B:181:0x0452, B:175:0x0430, B:105:0x025f, B:128:0x02f2, B:108:0x0278, B:109:0x0288, B:110:0x0292, B:111:0x029f, B:62:0x0125, B:8:0x000c), top: B:193:0x0004, outer: #5 }] */
    /* JADX WARN: Code duplicated, block: B:123:0x02c9 A[Catch: all -> 0x003a, Throwable -> 0x0115, TRY_ENTER, TryCatch #4 {Throwable -> 0x0115, blocks: (B:4:0x0004, B:6:0x0008, B:11:0x0019, B:13:0x002d, B:19:0x003d, B:21:0x0079, B:23:0x0085, B:24:0x0089, B:26:0x0093, B:27:0x0097, B:29:0x00a1, B:30:0x00a5, B:32:0x00af, B:34:0x00b7, B:53:0x0109, B:55:0x0111, B:35:0x00ba, B:37:0x00c4, B:39:0x00cc, B:40:0x00e1, B:42:0x00e5, B:44:0x00e9, B:45:0x00ed, B:48:0x00ff, B:49:0x0102, B:69:0x015e, B:71:0x0162, B:74:0x016b, B:76:0x016f, B:79:0x0194, B:81:0x01ac, B:83:0x01b4, B:66:0x0145, B:86:0x01c2, B:87:0x01e2, B:88:0x01e5, B:90:0x01e9, B:92:0x020d, B:93:0x023c, B:94:0x0243, B:96:0x0247, B:98:0x024b, B:100:0x024f, B:102:0x025a, B:114:0x02b5, B:118:0x02bc, B:120:0x02c0, B:123:0x02c9, B:125:0x02cd, B:130:0x030f, B:132:0x0313, B:134:0x0317, B:137:0x0333, B:139:0x0337, B:141:0x0341, B:143:0x034b, B:146:0x0372, B:148:0x0384, B:150:0x0388, B:153:0x0396, B:155:0x03b4, B:158:0x03d5, B:160:0x03d9, B:161:0x03e1, B:162:0x03fa, B:164:0x03fe, B:166:0x0402, B:168:0x041a, B:170:0x0420, B:172:0x042b, B:178:0x0449, B:180:0x044d, B:181:0x0452, B:175:0x0430, B:105:0x025f, B:128:0x02f2, B:108:0x0278, B:109:0x0288, B:110:0x0292, B:111:0x029f, B:62:0x0125, B:8:0x000c), top: B:193:0x0004, outer: #5 }] */
    /* JADX WARN: Code duplicated, block: B:125:0x02cd A[Catch: all -> 0x003a, Throwable -> 0x0115, TRY_LEAVE, TryCatch #4 {Throwable -> 0x0115, blocks: (B:4:0x0004, B:6:0x0008, B:11:0x0019, B:13:0x002d, B:19:0x003d, B:21:0x0079, B:23:0x0085, B:24:0x0089, B:26:0x0093, B:27:0x0097, B:29:0x00a1, B:30:0x00a5, B:32:0x00af, B:34:0x00b7, B:53:0x0109, B:55:0x0111, B:35:0x00ba, B:37:0x00c4, B:39:0x00cc, B:40:0x00e1, B:42:0x00e5, B:44:0x00e9, B:45:0x00ed, B:48:0x00ff, B:49:0x0102, B:69:0x015e, B:71:0x0162, B:74:0x016b, B:76:0x016f, B:79:0x0194, B:81:0x01ac, B:83:0x01b4, B:66:0x0145, B:86:0x01c2, B:87:0x01e2, B:88:0x01e5, B:90:0x01e9, B:92:0x020d, B:93:0x023c, B:94:0x0243, B:96:0x0247, B:98:0x024b, B:100:0x024f, B:102:0x025a, B:114:0x02b5, B:118:0x02bc, B:120:0x02c0, B:123:0x02c9, B:125:0x02cd, B:130:0x030f, B:132:0x0313, B:134:0x0317, B:137:0x0333, B:139:0x0337, B:141:0x0341, B:143:0x034b, B:146:0x0372, B:148:0x0384, B:150:0x0388, B:153:0x0396, B:155:0x03b4, B:158:0x03d5, B:160:0x03d9, B:161:0x03e1, B:162:0x03fa, B:164:0x03fe, B:166:0x0402, B:168:0x041a, B:170:0x0420, B:172:0x042b, B:178:0x0449, B:180:0x044d, B:181:0x0452, B:175:0x0430, B:105:0x025f, B:128:0x02f2, B:108:0x0278, B:109:0x0288, B:110:0x0292, B:111:0x029f, B:62:0x0125, B:8:0x000c), top: B:193:0x0004, outer: #5 }] */
    /* JADX WARN: Code duplicated, block: B:132:0x0313 A[Catch: all -> 0x003a, Throwable -> 0x0115, TryCatch #4 {Throwable -> 0x0115, blocks: (B:4:0x0004, B:6:0x0008, B:11:0x0019, B:13:0x002d, B:19:0x003d, B:21:0x0079, B:23:0x0085, B:24:0x0089, B:26:0x0093, B:27:0x0097, B:29:0x00a1, B:30:0x00a5, B:32:0x00af, B:34:0x00b7, B:53:0x0109, B:55:0x0111, B:35:0x00ba, B:37:0x00c4, B:39:0x00cc, B:40:0x00e1, B:42:0x00e5, B:44:0x00e9, B:45:0x00ed, B:48:0x00ff, B:49:0x0102, B:69:0x015e, B:71:0x0162, B:74:0x016b, B:76:0x016f, B:79:0x0194, B:81:0x01ac, B:83:0x01b4, B:66:0x0145, B:86:0x01c2, B:87:0x01e2, B:88:0x01e5, B:90:0x01e9, B:92:0x020d, B:93:0x023c, B:94:0x0243, B:96:0x0247, B:98:0x024b, B:100:0x024f, B:102:0x025a, B:114:0x02b5, B:118:0x02bc, B:120:0x02c0, B:123:0x02c9, B:125:0x02cd, B:130:0x030f, B:132:0x0313, B:134:0x0317, B:137:0x0333, B:139:0x0337, B:141:0x0341, B:143:0x034b, B:146:0x0372, B:148:0x0384, B:150:0x0388, B:153:0x0396, B:155:0x03b4, B:158:0x03d5, B:160:0x03d9, B:161:0x03e1, B:162:0x03fa, B:164:0x03fe, B:166:0x0402, B:168:0x041a, B:170:0x0420, B:172:0x042b, B:178:0x0449, B:180:0x044d, B:181:0x0452, B:175:0x0430, B:105:0x025f, B:128:0x02f2, B:108:0x0278, B:109:0x0288, B:110:0x0292, B:111:0x029f, B:62:0x0125, B:8:0x000c), top: B:193:0x0004, outer: #5 }] */
    /* JADX WARN: Code duplicated, block: B:134:0x0317 A[Catch: all -> 0x003a, Throwable -> 0x0115, TRY_LEAVE, TryCatch #4 {Throwable -> 0x0115, blocks: (B:4:0x0004, B:6:0x0008, B:11:0x0019, B:13:0x002d, B:19:0x003d, B:21:0x0079, B:23:0x0085, B:24:0x0089, B:26:0x0093, B:27:0x0097, B:29:0x00a1, B:30:0x00a5, B:32:0x00af, B:34:0x00b7, B:53:0x0109, B:55:0x0111, B:35:0x00ba, B:37:0x00c4, B:39:0x00cc, B:40:0x00e1, B:42:0x00e5, B:44:0x00e9, B:45:0x00ed, B:48:0x00ff, B:49:0x0102, B:69:0x015e, B:71:0x0162, B:74:0x016b, B:76:0x016f, B:79:0x0194, B:81:0x01ac, B:83:0x01b4, B:66:0x0145, B:86:0x01c2, B:87:0x01e2, B:88:0x01e5, B:90:0x01e9, B:92:0x020d, B:93:0x023c, B:94:0x0243, B:96:0x0247, B:98:0x024b, B:100:0x024f, B:102:0x025a, B:114:0x02b5, B:118:0x02bc, B:120:0x02c0, B:123:0x02c9, B:125:0x02cd, B:130:0x030f, B:132:0x0313, B:134:0x0317, B:137:0x0333, B:139:0x0337, B:141:0x0341, B:143:0x034b, B:146:0x0372, B:148:0x0384, B:150:0x0388, B:153:0x0396, B:155:0x03b4, B:158:0x03d5, B:160:0x03d9, B:161:0x03e1, B:162:0x03fa, B:164:0x03fe, B:166:0x0402, B:168:0x041a, B:170:0x0420, B:172:0x042b, B:178:0x0449, B:180:0x044d, B:181:0x0452, B:175:0x0430, B:105:0x025f, B:128:0x02f2, B:108:0x0278, B:109:0x0288, B:110:0x0292, B:111:0x029f, B:62:0x0125, B:8:0x000c), top: B:193:0x0004, outer: #5 }] */
    /* JADX WARN: Code duplicated, block: B:137:0x0333 A[Catch: all -> 0x003a, Throwable -> 0x0115, TRY_ENTER, TryCatch #4 {Throwable -> 0x0115, blocks: (B:4:0x0004, B:6:0x0008, B:11:0x0019, B:13:0x002d, B:19:0x003d, B:21:0x0079, B:23:0x0085, B:24:0x0089, B:26:0x0093, B:27:0x0097, B:29:0x00a1, B:30:0x00a5, B:32:0x00af, B:34:0x00b7, B:53:0x0109, B:55:0x0111, B:35:0x00ba, B:37:0x00c4, B:39:0x00cc, B:40:0x00e1, B:42:0x00e5, B:44:0x00e9, B:45:0x00ed, B:48:0x00ff, B:49:0x0102, B:69:0x015e, B:71:0x0162, B:74:0x016b, B:76:0x016f, B:79:0x0194, B:81:0x01ac, B:83:0x01b4, B:66:0x0145, B:86:0x01c2, B:87:0x01e2, B:88:0x01e5, B:90:0x01e9, B:92:0x020d, B:93:0x023c, B:94:0x0243, B:96:0x0247, B:98:0x024b, B:100:0x024f, B:102:0x025a, B:114:0x02b5, B:118:0x02bc, B:120:0x02c0, B:123:0x02c9, B:125:0x02cd, B:130:0x030f, B:132:0x0313, B:134:0x0317, B:137:0x0333, B:139:0x0337, B:141:0x0341, B:143:0x034b, B:146:0x0372, B:148:0x0384, B:150:0x0388, B:153:0x0396, B:155:0x03b4, B:158:0x03d5, B:160:0x03d9, B:161:0x03e1, B:162:0x03fa, B:164:0x03fe, B:166:0x0402, B:168:0x041a, B:170:0x0420, B:172:0x042b, B:178:0x0449, B:180:0x044d, B:181:0x0452, B:175:0x0430, B:105:0x025f, B:128:0x02f2, B:108:0x0278, B:109:0x0288, B:110:0x0292, B:111:0x029f, B:62:0x0125, B:8:0x000c), top: B:193:0x0004, outer: #5 }] */
    /* JADX WARN: Code duplicated, block: B:148:0x0384 A[Catch: all -> 0x003a, Throwable -> 0x0115, TryCatch #4 {Throwable -> 0x0115, blocks: (B:4:0x0004, B:6:0x0008, B:11:0x0019, B:13:0x002d, B:19:0x003d, B:21:0x0079, B:23:0x0085, B:24:0x0089, B:26:0x0093, B:27:0x0097, B:29:0x00a1, B:30:0x00a5, B:32:0x00af, B:34:0x00b7, B:53:0x0109, B:55:0x0111, B:35:0x00ba, B:37:0x00c4, B:39:0x00cc, B:40:0x00e1, B:42:0x00e5, B:44:0x00e9, B:45:0x00ed, B:48:0x00ff, B:49:0x0102, B:69:0x015e, B:71:0x0162, B:74:0x016b, B:76:0x016f, B:79:0x0194, B:81:0x01ac, B:83:0x01b4, B:66:0x0145, B:86:0x01c2, B:87:0x01e2, B:88:0x01e5, B:90:0x01e9, B:92:0x020d, B:93:0x023c, B:94:0x0243, B:96:0x0247, B:98:0x024b, B:100:0x024f, B:102:0x025a, B:114:0x02b5, B:118:0x02bc, B:120:0x02c0, B:123:0x02c9, B:125:0x02cd, B:130:0x030f, B:132:0x0313, B:134:0x0317, B:137:0x0333, B:139:0x0337, B:141:0x0341, B:143:0x034b, B:146:0x0372, B:148:0x0384, B:150:0x0388, B:153:0x0396, B:155:0x03b4, B:158:0x03d5, B:160:0x03d9, B:161:0x03e1, B:162:0x03fa, B:164:0x03fe, B:166:0x0402, B:168:0x041a, B:170:0x0420, B:172:0x042b, B:178:0x0449, B:180:0x044d, B:181:0x0452, B:175:0x0430, B:105:0x025f, B:128:0x02f2, B:108:0x0278, B:109:0x0288, B:110:0x0292, B:111:0x029f, B:62:0x0125, B:8:0x000c), top: B:193:0x0004, outer: #5 }] */
    /* JADX WARN: Code duplicated, block: B:150:0x0388 A[Catch: all -> 0x003a, Throwable -> 0x0115, TRY_LEAVE, TryCatch #4 {Throwable -> 0x0115, blocks: (B:4:0x0004, B:6:0x0008, B:11:0x0019, B:13:0x002d, B:19:0x003d, B:21:0x0079, B:23:0x0085, B:24:0x0089, B:26:0x0093, B:27:0x0097, B:29:0x00a1, B:30:0x00a5, B:32:0x00af, B:34:0x00b7, B:53:0x0109, B:55:0x0111, B:35:0x00ba, B:37:0x00c4, B:39:0x00cc, B:40:0x00e1, B:42:0x00e5, B:44:0x00e9, B:45:0x00ed, B:48:0x00ff, B:49:0x0102, B:69:0x015e, B:71:0x0162, B:74:0x016b, B:76:0x016f, B:79:0x0194, B:81:0x01ac, B:83:0x01b4, B:66:0x0145, B:86:0x01c2, B:87:0x01e2, B:88:0x01e5, B:90:0x01e9, B:92:0x020d, B:93:0x023c, B:94:0x0243, B:96:0x0247, B:98:0x024b, B:100:0x024f, B:102:0x025a, B:114:0x02b5, B:118:0x02bc, B:120:0x02c0, B:123:0x02c9, B:125:0x02cd, B:130:0x030f, B:132:0x0313, B:134:0x0317, B:137:0x0333, B:139:0x0337, B:141:0x0341, B:143:0x034b, B:146:0x0372, B:148:0x0384, B:150:0x0388, B:153:0x0396, B:155:0x03b4, B:158:0x03d5, B:160:0x03d9, B:161:0x03e1, B:162:0x03fa, B:164:0x03fe, B:166:0x0402, B:168:0x041a, B:170:0x0420, B:172:0x042b, B:178:0x0449, B:180:0x044d, B:181:0x0452, B:175:0x0430, B:105:0x025f, B:128:0x02f2, B:108:0x0278, B:109:0x0288, B:110:0x0292, B:111:0x029f, B:62:0x0125, B:8:0x000c), top: B:193:0x0004, outer: #5 }] */
    /* JADX WARN: Code duplicated, block: B:153:0x0396 A[Catch: all -> 0x003a, Throwable -> 0x0115, TRY_ENTER, TryCatch #4 {Throwable -> 0x0115, blocks: (B:4:0x0004, B:6:0x0008, B:11:0x0019, B:13:0x002d, B:19:0x003d, B:21:0x0079, B:23:0x0085, B:24:0x0089, B:26:0x0093, B:27:0x0097, B:29:0x00a1, B:30:0x00a5, B:32:0x00af, B:34:0x00b7, B:53:0x0109, B:55:0x0111, B:35:0x00ba, B:37:0x00c4, B:39:0x00cc, B:40:0x00e1, B:42:0x00e5, B:44:0x00e9, B:45:0x00ed, B:48:0x00ff, B:49:0x0102, B:69:0x015e, B:71:0x0162, B:74:0x016b, B:76:0x016f, B:79:0x0194, B:81:0x01ac, B:83:0x01b4, B:66:0x0145, B:86:0x01c2, B:87:0x01e2, B:88:0x01e5, B:90:0x01e9, B:92:0x020d, B:93:0x023c, B:94:0x0243, B:96:0x0247, B:98:0x024b, B:100:0x024f, B:102:0x025a, B:114:0x02b5, B:118:0x02bc, B:120:0x02c0, B:123:0x02c9, B:125:0x02cd, B:130:0x030f, B:132:0x0313, B:134:0x0317, B:137:0x0333, B:139:0x0337, B:141:0x0341, B:143:0x034b, B:146:0x0372, B:148:0x0384, B:150:0x0388, B:153:0x0396, B:155:0x03b4, B:158:0x03d5, B:160:0x03d9, B:161:0x03e1, B:162:0x03fa, B:164:0x03fe, B:166:0x0402, B:168:0x041a, B:170:0x0420, B:172:0x042b, B:178:0x0449, B:180:0x044d, B:181:0x0452, B:175:0x0430, B:105:0x025f, B:128:0x02f2, B:108:0x0278, B:109:0x0288, B:110:0x0292, B:111:0x029f, B:62:0x0125, B:8:0x000c), top: B:193:0x0004, outer: #5 }] */
    /* JADX WARN: Code duplicated, block: B:155:0x03b4 A[Catch: all -> 0x003a, Throwable -> 0x0115, TRY_LEAVE, TryCatch #4 {Throwable -> 0x0115, blocks: (B:4:0x0004, B:6:0x0008, B:11:0x0019, B:13:0x002d, B:19:0x003d, B:21:0x0079, B:23:0x0085, B:24:0x0089, B:26:0x0093, B:27:0x0097, B:29:0x00a1, B:30:0x00a5, B:32:0x00af, B:34:0x00b7, B:53:0x0109, B:55:0x0111, B:35:0x00ba, B:37:0x00c4, B:39:0x00cc, B:40:0x00e1, B:42:0x00e5, B:44:0x00e9, B:45:0x00ed, B:48:0x00ff, B:49:0x0102, B:69:0x015e, B:71:0x0162, B:74:0x016b, B:76:0x016f, B:79:0x0194, B:81:0x01ac, B:83:0x01b4, B:66:0x0145, B:86:0x01c2, B:87:0x01e2, B:88:0x01e5, B:90:0x01e9, B:92:0x020d, B:93:0x023c, B:94:0x0243, B:96:0x0247, B:98:0x024b, B:100:0x024f, B:102:0x025a, B:114:0x02b5, B:118:0x02bc, B:120:0x02c0, B:123:0x02c9, B:125:0x02cd, B:130:0x030f, B:132:0x0313, B:134:0x0317, B:137:0x0333, B:139:0x0337, B:141:0x0341, B:143:0x034b, B:146:0x0372, B:148:0x0384, B:150:0x0388, B:153:0x0396, B:155:0x03b4, B:158:0x03d5, B:160:0x03d9, B:161:0x03e1, B:162:0x03fa, B:164:0x03fe, B:166:0x0402, B:168:0x041a, B:170:0x0420, B:172:0x042b, B:178:0x0449, B:180:0x044d, B:181:0x0452, B:175:0x0430, B:105:0x025f, B:128:0x02f2, B:108:0x0278, B:109:0x0288, B:110:0x0292, B:111:0x029f, B:62:0x0125, B:8:0x000c), top: B:193:0x0004, outer: #5 }] */
    /* JADX WARN: Code duplicated, block: B:158:0x03d5 A[Catch: all -> 0x003a, Throwable -> 0x0115, TRY_ENTER, TryCatch #4 {Throwable -> 0x0115, blocks: (B:4:0x0004, B:6:0x0008, B:11:0x0019, B:13:0x002d, B:19:0x003d, B:21:0x0079, B:23:0x0085, B:24:0x0089, B:26:0x0093, B:27:0x0097, B:29:0x00a1, B:30:0x00a5, B:32:0x00af, B:34:0x00b7, B:53:0x0109, B:55:0x0111, B:35:0x00ba, B:37:0x00c4, B:39:0x00cc, B:40:0x00e1, B:42:0x00e5, B:44:0x00e9, B:45:0x00ed, B:48:0x00ff, B:49:0x0102, B:69:0x015e, B:71:0x0162, B:74:0x016b, B:76:0x016f, B:79:0x0194, B:81:0x01ac, B:83:0x01b4, B:66:0x0145, B:86:0x01c2, B:87:0x01e2, B:88:0x01e5, B:90:0x01e9, B:92:0x020d, B:93:0x023c, B:94:0x0243, B:96:0x0247, B:98:0x024b, B:100:0x024f, B:102:0x025a, B:114:0x02b5, B:118:0x02bc, B:120:0x02c0, B:123:0x02c9, B:125:0x02cd, B:130:0x030f, B:132:0x0313, B:134:0x0317, B:137:0x0333, B:139:0x0337, B:141:0x0341, B:143:0x034b, B:146:0x0372, B:148:0x0384, B:150:0x0388, B:153:0x0396, B:155:0x03b4, B:158:0x03d5, B:160:0x03d9, B:161:0x03e1, B:162:0x03fa, B:164:0x03fe, B:166:0x0402, B:168:0x041a, B:170:0x0420, B:172:0x042b, B:178:0x0449, B:180:0x044d, B:181:0x0452, B:175:0x0430, B:105:0x025f, B:128:0x02f2, B:108:0x0278, B:109:0x0288, B:110:0x0292, B:111:0x029f, B:62:0x0125, B:8:0x000c), top: B:193:0x0004, outer: #5 }] */
    /* JADX WARN: Code duplicated, block: B:160:0x03d9 A[Catch: all -> 0x003a, Throwable -> 0x0115, TryCatch #4 {Throwable -> 0x0115, blocks: (B:4:0x0004, B:6:0x0008, B:11:0x0019, B:13:0x002d, B:19:0x003d, B:21:0x0079, B:23:0x0085, B:24:0x0089, B:26:0x0093, B:27:0x0097, B:29:0x00a1, B:30:0x00a5, B:32:0x00af, B:34:0x00b7, B:53:0x0109, B:55:0x0111, B:35:0x00ba, B:37:0x00c4, B:39:0x00cc, B:40:0x00e1, B:42:0x00e5, B:44:0x00e9, B:45:0x00ed, B:48:0x00ff, B:49:0x0102, B:69:0x015e, B:71:0x0162, B:74:0x016b, B:76:0x016f, B:79:0x0194, B:81:0x01ac, B:83:0x01b4, B:66:0x0145, B:86:0x01c2, B:87:0x01e2, B:88:0x01e5, B:90:0x01e9, B:92:0x020d, B:93:0x023c, B:94:0x0243, B:96:0x0247, B:98:0x024b, B:100:0x024f, B:102:0x025a, B:114:0x02b5, B:118:0x02bc, B:120:0x02c0, B:123:0x02c9, B:125:0x02cd, B:130:0x030f, B:132:0x0313, B:134:0x0317, B:137:0x0333, B:139:0x0337, B:141:0x0341, B:143:0x034b, B:146:0x0372, B:148:0x0384, B:150:0x0388, B:153:0x0396, B:155:0x03b4, B:158:0x03d5, B:160:0x03d9, B:161:0x03e1, B:162:0x03fa, B:164:0x03fe, B:166:0x0402, B:168:0x041a, B:170:0x0420, B:172:0x042b, B:178:0x0449, B:180:0x044d, B:181:0x0452, B:175:0x0430, B:105:0x025f, B:128:0x02f2, B:108:0x0278, B:109:0x0288, B:110:0x0292, B:111:0x029f, B:62:0x0125, B:8:0x000c), top: B:193:0x0004, outer: #5 }] */
    /* JADX WARN: Code duplicated, block: B:180:0x044d A[Catch: all -> 0x003a, Throwable -> 0x0115, TryCatch #4 {Throwable -> 0x0115, blocks: (B:4:0x0004, B:6:0x0008, B:11:0x0019, B:13:0x002d, B:19:0x003d, B:21:0x0079, B:23:0x0085, B:24:0x0089, B:26:0x0093, B:27:0x0097, B:29:0x00a1, B:30:0x00a5, B:32:0x00af, B:34:0x00b7, B:53:0x0109, B:55:0x0111, B:35:0x00ba, B:37:0x00c4, B:39:0x00cc, B:40:0x00e1, B:42:0x00e5, B:44:0x00e9, B:45:0x00ed, B:48:0x00ff, B:49:0x0102, B:69:0x015e, B:71:0x0162, B:74:0x016b, B:76:0x016f, B:79:0x0194, B:81:0x01ac, B:83:0x01b4, B:66:0x0145, B:86:0x01c2, B:87:0x01e2, B:88:0x01e5, B:90:0x01e9, B:92:0x020d, B:93:0x023c, B:94:0x0243, B:96:0x0247, B:98:0x024b, B:100:0x024f, B:102:0x025a, B:114:0x02b5, B:118:0x02bc, B:120:0x02c0, B:123:0x02c9, B:125:0x02cd, B:130:0x030f, B:132:0x0313, B:134:0x0317, B:137:0x0333, B:139:0x0337, B:141:0x0341, B:143:0x034b, B:146:0x0372, B:148:0x0384, B:150:0x0388, B:153:0x0396, B:155:0x03b4, B:158:0x03d5, B:160:0x03d9, B:161:0x03e1, B:162:0x03fa, B:164:0x03fe, B:166:0x0402, B:168:0x041a, B:170:0x0420, B:172:0x042b, B:178:0x0449, B:180:0x044d, B:181:0x0452, B:175:0x0430, B:105:0x025f, B:128:0x02f2, B:108:0x0278, B:109:0x0288, B:110:0x0292, B:111:0x029f, B:62:0x0125, B:8:0x000c), top: B:193:0x0004, outer: #5 }] */
    /* JADX WARN: Code duplicated, block: B:181:0x0452 A[Catch: all -> 0x003a, Throwable -> 0x0115, TRY_LEAVE, TryCatch #4 {Throwable -> 0x0115, blocks: (B:4:0x0004, B:6:0x0008, B:11:0x0019, B:13:0x002d, B:19:0x003d, B:21:0x0079, B:23:0x0085, B:24:0x0089, B:26:0x0093, B:27:0x0097, B:29:0x00a1, B:30:0x00a5, B:32:0x00af, B:34:0x00b7, B:53:0x0109, B:55:0x0111, B:35:0x00ba, B:37:0x00c4, B:39:0x00cc, B:40:0x00e1, B:42:0x00e5, B:44:0x00e9, B:45:0x00ed, B:48:0x00ff, B:49:0x0102, B:69:0x015e, B:71:0x0162, B:74:0x016b, B:76:0x016f, B:79:0x0194, B:81:0x01ac, B:83:0x01b4, B:66:0x0145, B:86:0x01c2, B:87:0x01e2, B:88:0x01e5, B:90:0x01e9, B:92:0x020d, B:93:0x023c, B:94:0x0243, B:96:0x0247, B:98:0x024b, B:100:0x024f, B:102:0x025a, B:114:0x02b5, B:118:0x02bc, B:120:0x02c0, B:123:0x02c9, B:125:0x02cd, B:130:0x030f, B:132:0x0313, B:134:0x0317, B:137:0x0333, B:139:0x0337, B:141:0x0341, B:143:0x034b, B:146:0x0372, B:148:0x0384, B:150:0x0388, B:153:0x0396, B:155:0x03b4, B:158:0x03d5, B:160:0x03d9, B:161:0x03e1, B:162:0x03fa, B:164:0x03fe, B:166:0x0402, B:168:0x041a, B:170:0x0420, B:172:0x042b, B:178:0x0449, B:180:0x044d, B:181:0x0452, B:175:0x0430, B:105:0x025f, B:128:0x02f2, B:108:0x0278, B:109:0x0288, B:110:0x0292, B:111:0x029f, B:62:0x0125, B:8:0x000c), top: B:193:0x0004, outer: #5 }] */
    /* JADX WARN: Code duplicated, block: B:183:0x0476  */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Not found exit edge by exit block: B:163:0x03fc
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.checkLoopExits(LoopRegionMaker.java:272)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeLoopRegion(LoopRegionMaker.java:237)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:80)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:92)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.addCases(SwitchRegionMaker.java:127)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:75)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:115)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.SynchronizedRegionMaker.process(SynchronizedRegionMaker.java:87)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:121)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:49)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    @Override // java.lang.Runnable
    public void run() {
        /*
            Method dump skipped, instruction units count: 1160
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.internal.c.run():void");
    }

    /* JADX INFO: renamed from: com.google.ads.internal.c$3, reason: invalid class name */
    /* synthetic */ class AnonymousClass3 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f674a = new int[d.values().length];

        static {
            try {
                f674a[d.ONLINE_SERVER_REQUEST.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f674a[d.ONLINE_USING_BUFFERED_ADS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f674a[d.OFFLINE_USING_BUFFERED_ADS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f674a[d.OFFLINE_EMPTY.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    protected void b() {
        try {
            if (TextUtils.isEmpty(this.e)) {
                com.google.ads.util.b.b("Got a mediation response with no content type. Aborting mediation.");
                a(AdRequest.ErrorCode.INTERNAL_ERROR, false);
            } else if (!this.e.startsWith("application/json")) {
                com.google.ads.util.b.b("Got a mediation response with a content type: '" + this.e + "'. Expected something starting with 'application/json'. Aborting mediation.");
                a(AdRequest.ErrorCode.INTERNAL_ERROR, false);
            } else {
                final com.google.ads.c cVarA = com.google.ads.c.a(this.c);
                a(this.d, cVarA, this.j.f696a.a().b.a().j());
                com.google.ads.m.a().c.a().post(new Runnable() { // from class: com.google.ads.internal.c.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (c.this.i != null) {
                            c.this.i.stopLoading();
                            c.this.i.destroy();
                        }
                        c.this.j.f696a.a().b.a().a(c.this.n);
                        if (c.this.o != null) {
                            c.this.j.f696a.a().g.a().b(c.this.o);
                        }
                        c.this.j.f696a.a().b.a().a(cVarA);
                    }
                });
            }
        } catch (JSONException e) {
            com.google.ads.util.b.b("AdLoader can't parse gWhirl server configuration.", e);
            a(AdRequest.ErrorCode.INTERNAL_ERROR, false);
        }
    }

    static void a(String str, com.google.ads.c cVar, com.google.ads.d dVar) {
        if (str != null && !str.contains("no-store") && !str.contains("no-cache")) {
            Matcher matcher = Pattern.compile("max-age\\s*=\\s*(\\d+)").matcher(str);
            if (matcher.find()) {
                try {
                    int i = Integer.parseInt(matcher.group(1));
                    dVar.a(cVar, i);
                    com.google.ads.util.b.c(String.format(Locale.US, "Caching gWhirl configuration for: %d seconds", Integer.valueOf(i)));
                    return;
                } catch (NumberFormatException e) {
                    com.google.ads.util.b.b("Caught exception trying to parse cache control directive. Overflow?", e);
                    return;
                }
            }
            com.google.ads.util.b.c("Unrecognized cacheControlDirective: '" + str + "'. Not caching configuration.");
        }
    }

    public String a(Map<String, Object> map, Activity activity) throws n {
        Context applicationContext = activity.getApplicationContext();
        g gVarN = this.j.f696a.a().b.a().n();
        long jM = gVarN.m();
        if (jM > 0) {
            map.put("prl", Long.valueOf(jM));
        }
        long jN = gVarN.n();
        if (jN > 0) {
            map.put("prnl", Long.valueOf(jN));
        }
        Object objL = gVarN.l();
        if (objL != null) {
            map.put("ppcl", objL);
        }
        Object objK = gVarN.k();
        if (objK != null) {
            map.put("pcl", objK);
        }
        long j = gVarN.j();
        if (j > 0) {
            map.put("pcc", Long.valueOf(j));
        }
        map.put("preqs", Long.valueOf(gVarN.o()));
        map.put("oar", Long.valueOf(gVarN.p()));
        map.put("bas_on", Long.valueOf(gVarN.s()));
        map.put("bas_off", Long.valueOf(gVarN.v()));
        if (gVarN.y()) {
            map.put("aoi_timeout", "true");
        }
        if (gVarN.A()) {
            map.put("aoi_nofill", "true");
        }
        Object objD = gVarN.D();
        if (objD != null) {
            map.put("pit", objD);
        }
        map.put("ptime", Long.valueOf(g.E()));
        gVarN.a();
        gVarN.i();
        if (this.j.f696a.a().b()) {
            map.put("format", "interstitial_mb");
        } else {
            AdSize adSizeC = this.j.f696a.a().g.a().c();
            if (adSizeC.isFullWidth()) {
                map.put("smart_w", "full");
            }
            if (adSizeC.isAutoHeight()) {
                map.put("smart_h", "auto");
            }
            if (!adSizeC.isCustomAdSize()) {
                map.put("format", adSizeC.toString());
            } else {
                HashMap map2 = new HashMap();
                map2.put("w", Integer.valueOf(adSizeC.getWidth()));
                map2.put("h", Integer.valueOf(adSizeC.getHeight()));
                map.put("ad_frame", map2);
            }
        }
        map.put("slotname", this.j.f696a.a().h.a());
        map.put("js", "afma-sdk-a-v6.4.1");
        try {
            int i = applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 0).versionCode;
            String strF = AdUtil.f(applicationContext);
            if (!TextUtils.isEmpty(strF)) {
                map.put("mv", strF);
            }
            String strA = com.google.ads.m.a().f697a.a();
            if (!TextUtils.isEmpty(strA)) {
                map.put("imbf", strA);
            }
            map.put("msid", applicationContext.getPackageName());
            map.put("app_name", i + ".android." + applicationContext.getPackageName());
            map.put("isu", AdUtil.a(applicationContext));
            Object objD2 = AdUtil.d(applicationContext);
            if (objD2 == null) {
                objD2 = "null";
            }
            map.put("net", objD2);
            String strE = AdUtil.e(applicationContext);
            if (strE != null && strE.length() != 0) {
                map.put("cap", strE);
            }
            map.put("u_audio", Integer.valueOf(AdUtil.g(applicationContext).ordinal()));
            DisplayMetrics displayMetricsA = AdUtil.a(activity);
            map.put("u_sd", Float.valueOf(displayMetricsA.density));
            map.put("u_h", Integer.valueOf(AdUtil.a(applicationContext, displayMetricsA)));
            map.put("u_w", Integer.valueOf(AdUtil.b(applicationContext, displayMetricsA)));
            map.put("hl", Locale.getDefault().getLanguage());
            com.google.ads.n nVarA = this.j.f696a.a();
            ak akVarA = nVarA.r.a();
            if (akVarA == null) {
                akVarA = ak.a("afma-sdk-a-v6.4.1", activity);
                nVarA.r.a(akVarA);
                nVarA.s.a(new al(akVarA));
            }
            map.put("ms", akVarA.a(applicationContext));
            if (this.j.f696a.a().j != null && this.j.f696a.a().j.a() != null) {
                AdView adViewA = this.j.f696a.a().j.a();
                if (adViewA.getParent() != null) {
                    int[] iArr = new int[2];
                    adViewA.getLocationOnScreen(iArr);
                    int i2 = iArr[0];
                    int i3 = iArr[1];
                    DisplayMetrics displayMetrics = this.j.f696a.a().f.a().getResources().getDisplayMetrics();
                    int i4 = (!adViewA.isShown() || adViewA.getWidth() + i2 <= 0 || adViewA.getHeight() + i3 <= 0 || i2 > displayMetrics.widthPixels || i3 > displayMetrics.heightPixels) ? 0 : 1;
                    HashMap map3 = new HashMap();
                    map3.put("x", Integer.valueOf(i2));
                    map3.put("y", Integer.valueOf(i3));
                    map3.put("width", Integer.valueOf(adViewA.getWidth()));
                    map3.put("height", Integer.valueOf(adViewA.getHeight()));
                    map3.put("visible", Integer.valueOf(i4));
                    map.put("ad_pos", map3);
                }
            }
            StringBuilder sb = new StringBuilder();
            AdSize[] adSizeArrA = this.j.f696a.a().n.a();
            if (adSizeArrA != null) {
                for (AdSize adSize : adSizeArrA) {
                    if (sb.length() != 0) {
                        sb.append("|");
                    }
                    sb.append(adSize.getWidth() + "x" + adSize.getHeight());
                }
                map.put("sz", sb.toString());
            }
            TelephonyManager telephonyManager = (TelephonyManager) applicationContext.getSystemService("phone");
            String networkOperator = telephonyManager.getNetworkOperator();
            if (!TextUtils.isEmpty(networkOperator)) {
                map.put("carrier", networkOperator);
            }
            map.put("pt", Integer.valueOf(telephonyManager.getPhoneType()));
            map.put("gnt", Integer.valueOf(telephonyManager.getNetworkType()));
            if (AdUtil.c()) {
                map.put("simulator", 1);
            }
            map.put("session_id", com.google.ads.b.a().b().toString());
            map.put("seq_num", com.google.ads.b.a().c().toString());
            if (this.j.f696a.a().g.a().b()) {
                map.put("swipeable", 1);
            }
            if (this.j.f696a.a().t.a().booleanValue()) {
                map.put("d_imp_hdr", 1);
            }
            String strA2 = AdUtil.a(map);
            String str = this.j.f696a.a().d.a().b.a().o.a().booleanValue() ? g() + d() + "(" + strA2 + ");" + h() : g() + e() + d() + "(" + strA2 + ");" + h();
            com.google.ads.util.b.c("adRequestUrlHtml: " + str);
            return str;
        } catch (PackageManager.NameNotFoundException e) {
            throw new n(this, "NameNotFoundException");
        }
    }

    private String d() {
        return this.h instanceof SearchAdRequest ? "AFMA_buildAdURL" : "AFMA_buildAdURL";
    }

    private String e() {
        return this.h instanceof SearchAdRequest ? "AFMA_getSdkConstants();" : "AFMA_getSdkConstants();";
    }

    private String f() {
        return this.h instanceof SearchAdRequest ? "http://www.gstatic.com/safa/" : "http://media.admob.com/";
    }

    private String g() {
        return this.h instanceof SearchAdRequest ? "<html><head><script src=\"http://www.gstatic.com/safa/sdk-core-v40.js\"></script><script>" : "<html><head><script src=\"http://media.admob.com/sdk-core-v40.js\"></script><script>";
    }

    private String h() {
        return this.h instanceof SearchAdRequest ? "</script></head><body></body></html>" : "</script></head><body></body></html>";
    }

    protected void a(AdRequest.ErrorCode errorCode, boolean z) {
        com.google.ads.m.a().c.a().post(new m(this.j.f696a.a().b.a(), this.i, this.g, errorCode, z));
    }

    private void b(String str, String str2) {
        com.google.ads.m.a().c.a().post(new o(this, this.i, str2, str));
    }

    private void i() {
        AdWebView adWebViewL = this.j.f696a.a().b.a().l();
        this.j.f696a.a().b.a().m().c(true);
        this.j.f696a.a().b.a().n().h();
        com.google.ads.m.a().c.a().post(new o(this, adWebViewL, this.b, this.c));
    }

    private void j() {
        com.google.ads.m.a().c.a().post(new p(this, this.j.f696a.a().b.a(), this.i, this.m, this.u, this.r, this.n, this.o));
    }

    protected synchronized void b(boolean z) {
        this.f = z;
    }

    protected synchronized void b(String str) {
        this.e = str;
    }

    protected synchronized void a(String str, String str2) {
        this.b = str2;
        this.c = str;
        notify();
    }

    protected synchronized void c(String str) {
        this.d = str;
    }

    public synchronized void d(String str) {
        this.k = str;
        notify();
    }

    public synchronized void e(String str) {
        this.l = str;
    }

    public synchronized void f(String str) {
        this.n = str;
    }

    public synchronized void a(AdSize adSize) {
        this.o = adSize;
    }

    public synchronized void a(AdRequest.ErrorCode errorCode) {
        this.s = errorCode;
        notify();
    }

    protected synchronized void c() {
        this.t = true;
        notify();
    }

    public synchronized void c(boolean z) {
        this.r = z;
    }

    public synchronized void a(int i) {
        this.u = i;
    }

    public synchronized void d(boolean z) {
        this.w = z;
    }

    public synchronized void a(d dVar) {
        this.x = dVar;
    }

    public synchronized void e(boolean z) {
        this.f671a = z;
    }
}
