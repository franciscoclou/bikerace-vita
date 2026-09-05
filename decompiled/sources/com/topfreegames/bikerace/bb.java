package com.topfreegames.bikerace;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import com.facebook.widget.PlacePickerFragment;
import com.google.ads.AdRequest;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: compiled from: RemoteConfig.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class bb {
    private static final byte[] c = {-107, 72, -45, 43, 116, 103};
    private static final byte[] d = {-107, 72, -45, 43, 116, 103};
    private static final byte[] e = {-107, 72, -45, 43, 116, 103};
    private static final byte[] f = {-107, 72, -45, 43, 116, 103};
    private static final byte[] g = {-107, 72, -45, 43, 116, 103};
    private static final byte[] h = {63, -56, 49, -102, -87, 66};
    private static final String i = ax.a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private be f1132a;
    private Context b;

    public bb(Context context) {
        this.b = null;
        this.b = context.getApplicationContext();
    }

    private static long b(String str) {
        long time = 0;
        try {
            byte[] bArrDecode = Base64.decode(str.getBytes("ASCII"), 0);
            for (int i2 = 0; i2 < bArrDecode.length; i2++) {
                bArrDecode[i2] = (byte) (bArrDecode[i2] ^ g[i2 % g.length]);
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            time = simpleDateFormat.parse(new String(bArrDecode, "ASCII")).getTime();
            return time;
        } catch (UnsupportedEncodingException e2) {
            return time;
        } catch (ParseException e3) {
            return time;
        }
    }

    private static long c(String str) {
        long time = 0;
        try {
            byte[] bArrDecode = Base64.decode(str.getBytes("ASCII"), 0);
            for (int i2 = 0; i2 < bArrDecode.length; i2++) {
                bArrDecode[i2] = (byte) (bArrDecode[i2] ^ c[i2 % c.length]);
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            time = simpleDateFormat.parse(new String(bArrDecode, "ASCII")).getTime();
            return time;
        } catch (UnsupportedEncodingException e2) {
            return time;
        } catch (ParseException e3) {
            return time;
        }
    }

    private static long d(String str) {
        long time = 0;
        try {
            byte[] bArrDecode = Base64.decode(str.getBytes("ASCII"), 0);
            for (int i2 = 0; i2 < bArrDecode.length; i2++) {
                bArrDecode[i2] = (byte) (bArrDecode[i2] ^ d[i2 % d.length]);
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            time = simpleDateFormat.parse(new String(bArrDecode, "ASCII")).getTime();
            return time;
        } catch (UnsupportedEncodingException e2) {
            return time;
        } catch (ParseException e3) {
            return time;
        }
    }

    private static long e(String str) {
        long time = 0;
        try {
            byte[] bArrDecode = Base64.decode(str.getBytes("ASCII"), 0);
            for (int i2 = 0; i2 < bArrDecode.length; i2++) {
                bArrDecode[i2] = (byte) (bArrDecode[i2] ^ e[i2 % e.length]);
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            time = simpleDateFormat.parse(new String(bArrDecode, "ASCII")).getTime();
            return time;
        } catch (UnsupportedEncodingException e2) {
            return time;
        } catch (ParseException e3) {
            return time;
        }
    }

    private static long f(String str) {
        long time = 0;
        try {
            byte[] bArrDecode = Base64.decode(str.getBytes("ASCII"), 0);
            for (int i2 = 0; i2 < bArrDecode.length; i2++) {
                bArrDecode[i2] = (byte) (bArrDecode[i2] ^ f[i2 % f.length]);
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            time = simpleDateFormat.parse(new String(bArrDecode, "ASCII")).getTime();
            return time;
        } catch (UnsupportedEncodingException e2) {
            return time;
        } catch (ParseException e3) {
            return time;
        }
    }

    public boolean a() {
        return i("DisableAds4x").toLowerCase(Locale.US).equals("true");
    }

    public boolean b() {
        return i("LocalNotificationEnable").toLowerCase(Locale.US).equals("true");
    }

    public String c() {
        String strI = i("LocalNotificationRetMsg");
        if (strI.equals("")) {
            return null;
        }
        return strI;
    }

    public int[] d() {
        int i2 = 0;
        String[] strArrSplit = i("LocalNotificationRetDays").toLowerCase(Locale.US).trim().split(";");
        if (strArrSplit.length == 0) {
            return null;
        }
        try {
            int[] iArr = new int[strArrSplit.length];
            int length = strArrSplit.length;
            int i3 = 0;
            while (i2 < length) {
                int i4 = i3 + 1;
                iArr[i3] = Integer.parseInt(strArrSplit[i2]);
                i2++;
                i3 = i4;
            }
            return iArr;
        } catch (Exception e2) {
            if (!ap.d()) {
                return null;
            }
            e2.printStackTrace();
            return null;
        }
    }

    public boolean e() {
        return i("InterstitialEnable").toLowerCase(Locale.US).equals("true");
    }

    public long f() {
        try {
            return Long.parseLong(i("GiftExpire"));
        } catch (Exception e2) {
            return 86400000L;
        }
    }

    public long g() {
        return c(i("TimeOfferBike1End"));
    }

    public String h() {
        String strI = i("TimeOfferBike1ExpMsg");
        if (strI == null || strI.equals("")) {
            return "This bike is no longer available";
        }
        return strI;
    }

    public String i() {
        String strI = i("TimeOfferBike1NotMsg");
        if (strI == null || strI.equals("")) {
            return "Last day to get the Halloween Bike. Don't miss it.";
        }
        return strI;
    }

    public long j() {
        long jC = c(i("TimeOfferBike1Start"));
        if (jC > 0) {
            return jC;
        }
        return Long.MAX_VALUE;
    }

    public String k() {
        String strI = i(AdRequest.LOGTAG);
        if (strI == null || strI == "") {
            return "ca-app-pub-2672799608801999/3215209462";
        }
        return strI;
    }

    public int l() {
        String strTrim = i("InterstitialsProviders").toLowerCase(Locale.US).trim();
        if (strTrim.equals("")) {
            return 0;
        }
        return strTrim.split(";").length;
    }

    public boolean a(String str) {
        return i("InterstitialsProviders").toLowerCase(Locale.US).contains(str.toLowerCase(Locale.US));
    }

    public long m() {
        try {
            return Long.parseLong(i("InterstitialHoursAfterInstall")) * 60 * 60 * 1000;
        } catch (Exception e2) {
            return 43200000L;
        }
    }

    public int n() {
        try {
            return Integer.parseInt(i("InterstitialMinMRaces"));
        } catch (Exception e2) {
            return 1;
        }
    }

    public long o() {
        try {
            return Long.parseLong(i("InterstitialMinMTime"));
        } catch (Exception e2) {
            return 60000L;
        }
    }

    public int p() {
        try {
            return Integer.parseInt(i("InterstitialMinSRaces"));
        } catch (Exception e2) {
            return 2;
        }
    }

    public long q() {
        try {
            return Long.parseLong(i("InterstitialMinSTime"));
        } catch (Exception e2) {
            return 60000L;
        }
    }

    public boolean r() {
        return i("InterstitialUsePriorityList").equals("true");
    }

    public int s() {
        try {
            return Integer.parseInt(i("InterstitialMaxRetries"));
        } catch (Exception e2) {
            return 2;
        }
    }

    public ArrayList<String> t() {
        String strTrim = i("InterstitialsProviders").toLowerCase(Locale.US).trim();
        return strTrim.length() == 0 ? new ArrayList<>() : new ArrayList<>(Arrays.asList(strTrim.split(";")));
    }

    public int u() {
        try {
            return Integer.parseInt(i("InterstitialShowGroup"));
        } catch (Exception e2) {
            return Integer.MAX_VALUE;
        }
    }

    public int v() {
        try {
            return Integer.parseInt(i("InterstitialInstallGroup"));
        } catch (Exception e2) {
            return 0;
        }
    }

    public int w() {
        return a("FPSGroup", 1);
    }

    public String x() {
        if (ap.t()) {
            try {
                byte[] bArrDecode = Base64.decode(i("ABExtraData").getBytes("ASCII"), 0);
                for (int i2 = 0; i2 < bArrDecode.length; i2++) {
                    bArrDecode[i2] = (byte) (bArrDecode[i2] ^ h[i2 % h.length]);
                }
                return new String(bArrDecode, "ASCII").replace("\\n", "\n");
            } catch (UnsupportedEncodingException e2) {
                return "";
            }
        }
        return "";
    }

    public int y() {
        try {
            return Integer.parseInt(i("TracksDay"));
        } catch (NumberFormatException e2) {
            return 3;
        }
    }

    public String z() {
        return i("RatePopupMsg").replace("\\n", "\n");
    }

    public String A() {
        return i("RatePopupNo");
    }

    public String B() {
        return i("RatePopupYes");
    }

    public String C() {
        return i("RatePopupExtraMsg");
    }

    public String D() {
        return i("RatePopupExtraNo");
    }

    public String E() {
        return i("RatePopupExtraYes");
    }

    public boolean F() {
        return i("RateTwoStepEnable_").toLowerCase(Locale.US).equals("true");
    }

    public boolean G() {
        return !i("RatePopupYesPos").toLowerCase(Locale.US).trim().equals("right");
    }

    public boolean H() {
        return !i("RatePopupExtraYesPos").toLowerCase(Locale.US).trim().equals("right");
    }

    public int I() {
        try {
            return Integer.parseInt(i("RatePopupMinRaces"));
        } catch (NumberFormatException e2) {
            return ap.k();
        }
    }

    public long J() {
        return d(i("TimeOfferBike2End"));
    }

    public String K() {
        String strI = i("TimeOfferBike2ExpMsg");
        if (strI == null || strI.equals("")) {
            return "This bike is no longer available";
        }
        return strI;
    }

    public String L() {
        String strI = i("TimeOfferBike2NotMsg");
        if (strI == null || strI.equals("")) {
            return "Last day to get the Thanksgiving Bike. Don't miss it.";
        }
        return strI;
    }

    public long M() {
        long jE = e(i("TimeOfferBike2Start"));
        if (jE > 0) {
            return jE;
        }
        return Long.MAX_VALUE;
    }

    public long N() {
        return e(i("TimeOfferBike3End"));
    }

    public String O() {
        String strI = i("TimeOfferBike3ExpMsg");
        if (strI == null || strI.equals("")) {
            return "This bike is no longer available";
        }
        return strI;
    }

    public String P() {
        String strI = i("TimeOfferBike3NotMsg");
        if (strI == null || strI.equals("")) {
            return "Last day to get the Santa's Bike. Don't miss it.";
        }
        return strI;
    }

    public long Q() {
        long jE = e(i("TimeOfferBike3Start"));
        if (jE > 0) {
            return jE;
        }
        return Long.MAX_VALUE;
    }

    public long R() {
        return f(i("TimeOfferBike4End"));
    }

    public String S() {
        String strI = i("TimeOfferBike4ExpMsg");
        if (strI == null || strI.equals("")) {
            return "This bike is no longer available";
        }
        return strI;
    }

    public long T() {
        long jF = f(i("TimeOfferBike4Start"));
        if (jF > 0) {
            return jF;
        }
        return Long.MAX_VALUE;
    }

    public boolean U() {
        return ap.t() && !i("ABExtraData").equals("");
    }

    public boolean V() {
        return !i("TimeOfferBike1Over").toLowerCase(Locale.US).equals("false");
    }

    public boolean W() {
        return !i("TimeOfferBike1H").toLowerCase(Locale.US).equals("true");
    }

    public boolean X() {
        return i("OfferPopupEnable").toLowerCase(Locale.US).equals("true");
    }

    public boolean Y() {
        return !i("TimeOfferBike2Over").toLowerCase(Locale.US).equals("false");
    }

    public boolean Z() {
        return !i("TimeOfferBike2H").toLowerCase(Locale.US).equals("true");
    }

    public boolean aa() {
        return !i("TimeOfferBike3Over").toLowerCase(Locale.US).equals("false");
    }

    public boolean ab() {
        return !i("TimeOfferBike3H").toLowerCase(Locale.US).equals("true");
    }

    public boolean ac() {
        return !i("TimeOfferBike4Over").toLowerCase(Locale.US).equals("false");
    }

    public boolean ad() {
        return !i("TimeOfferBike4H").toLowerCase(Locale.US).equals("true");
    }

    public long ae() {
        return b(i("WorldCupEnd"));
    }

    public boolean af() {
        return i("WorldCupH").toLowerCase(Locale.US).equals("true");
    }

    public int ag() {
        return a("WorldCupCMMin", 251);
    }

    public int ah() {
        return a("WorldCupCMMax", Integer.MAX_VALUE);
    }

    public int ai() {
        return a("WorldCupCMSoftCost", 3);
    }

    public int aj() {
        return a("WorldCupCMHardCost", 0);
    }

    public int ak() {
        return a("WorldCupCMSoftRest", 1);
    }

    public int al() {
        return a("WorldCupRMMin", 0);
    }

    public int am() {
        return a("WorldCupRMMax", 2222);
    }

    public int an() {
        return a("WorldCupRMSoftCost", 0);
    }

    public int ao() {
        return a("WorldCupRMHardCost", 1);
    }

    public int ap() {
        return a("WorldCupRMSoftRest", 3);
    }

    public long aq() {
        String str;
        String strI = i("WorldCupChanceStart");
        if (strI.equals("")) {
            str = "2014/05/15 00:00:00";
        } else {
            try {
                byte[] bArrDecode = Base64.decode(strI.getBytes("ASCII"), 0);
                for (int i2 = 0; i2 < bArrDecode.length; i2++) {
                    bArrDecode[i2] = (byte) (bArrDecode[i2] ^ g[i2 % g.length]);
                }
                str = new String(bArrDecode, "ASCII");
            } catch (UnsupportedEncodingException e2) {
                str = "2014/05/15 00:00:00";
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            return simpleDateFormat.parse(str).getTime();
        } catch (ParseException e3) {
            return 0L;
        }
    }

    public c[] ar() {
        int i2 = 0;
        String strTrim = i("WorldCupChanceSeq").toLowerCase(Locale.US).trim();
        if (strTrim.equals("")) {
            return ba.f1131a;
        }
        String[] strArrSplit = strTrim.split(";");
        c[] cVarArr = new c[strArrSplit.length];
        int length = strArrSplit.length;
        int i3 = 0;
        while (i2 < length) {
            cVarArr[i3] = c.valuesCustom()[Integer.parseInt(strArrSplit[i2])];
            i2++;
            i3++;
        }
        return cVarArr;
    }

    public long as() {
        return a("WorldCupChanceT", 86400000L);
    }

    public String at() {
        String strI = i("WorldCupPromoRegular");
        if (strI.equals("")) {
            return "Twice the chance to get the %s's Bike! TODAY ONLY!";
        }
        return strI;
    }

    public String au() {
        String strI = i("WorldCupPromoLastDay");
        if (strI.equals("")) {
            return "Last day to get World Tour Bikes. Don't miss it";
        }
        return strI;
    }

    public int av() {
        int i2 = 360;
        try {
            byte[] bArrDecode = Base64.decode(i("WorldCupExCG").getBytes("ASCII"), 0);
            if (bArrDecode.length <= 0) {
                return 360;
            }
            for (int i3 = 0; i3 < bArrDecode.length; i3++) {
                bArrDecode[i3] = (byte) (bArrDecode[i3] ^ g[i3 % g.length]);
            }
            i2 = Integer.parseInt(new String(bArrDecode, "ASCII"));
            return i2;
        } catch (UnsupportedEncodingException e2) {
            return i2;
        } catch (NumberFormatException e3) {
            return i2;
        }
    }

    public boolean aw() {
        try {
            return i("FakePokeEnabled").toLowerCase(Locale.US).equals("true");
        } catch (Exception e2) {
            return false;
        }
    }

    public long ax() {
        try {
            return Integer.parseInt(i("FakePokeHours")) * 60 * 60 * PlacePickerFragment.DEFAULT_RADIUS_IN_METERS;
        } catch (Exception e2) {
            return 86400000L;
        }
    }

    public boolean ay() {
        return i("OfferRecommendEnable").toLowerCase(Locale.US).equals("true");
    }

    public int az() {
        try {
            return Integer.parseInt(i("OfferRecommendMinLevel"));
        } catch (Exception e2) {
            return 7;
        }
    }

    public int aA() {
        try {
            return Integer.parseInt(i("OfferRecommendMinWorld"));
        } catch (Exception e2) {
            return 0;
        }
    }

    public int aB() {
        try {
            return Integer.parseInt(i("OfferRecommendMinDie"));
        } catch (Exception e2) {
            return 4;
        }
    }

    public boolean aC() {
        return i("WorldCupShowCurrencyAfterExpire").toLowerCase(Locale.US).equals("true");
    }

    public void aD() {
        if (aG()) {
            new bf(this, null).execute(i);
        }
    }

    public boolean aE() {
        return !i("LocalizationDisabled").toLowerCase(Locale.US).equals("true");
    }

    public String aF() {
        try {
            String strI = i("RatePopupTestName");
            if (strI.trim().equals("")) {
                return "no_test";
            }
            return strI;
        } catch (Exception e2) {
            return "no_test";
        }
    }

    private boolean aG() {
        try {
            return new Date().getTime() - Long.parseLong(i("LastUpdate")) > 3600000;
        } catch (NumberFormatException e2) {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(String str) throws Throwable {
        InputStream inputStreamH;
        InputStream inputStream = null;
        listA = null;
        List<bd> listA = null;
        bc bcVar = new bc(null);
        try {
            inputStreamH = h(str);
            try {
                listA = bcVar.a(inputStreamH);
                if (inputStreamH != null) {
                    inputStreamH.close();
                }
            } catch (Exception e2) {
                if (inputStreamH != null) {
                    inputStreamH.close();
                }
            } catch (Throwable th) {
                inputStream = inputStreamH;
                th = th;
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        } catch (Exception e3) {
            inputStreamH = null;
        } catch (Throwable th2) {
            th = th2;
        }
        if (listA != null) {
            for (bd bdVar : listA) {
                a(bdVar.f1134a, bdVar.b);
            }
        }
    }

    private InputStream h(String str) throws ProtocolException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setReadTimeout(10000);
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setDoInput(true);
        httpURLConnection.connect();
        return httpURLConnection.getInputStream();
    }

    private String i(String str) {
        return this.b.getSharedPreferences("com.topfreegames.bikerace.config", 0).getString(str, "").replace("\\n", "\n");
    }

    private void a(String str, String str2) {
        SharedPreferences.Editor editorEdit = this.b.getSharedPreferences("com.topfreegames.bikerace.config", 0).edit();
        editorEdit.putString(str, str2);
        editorEdit.putString("LastUpdate", Long.toString(new Date().getTime()));
        editorEdit.commit();
    }

    private int a(String str, int i2) {
        try {
            return Integer.parseInt(i(str));
        } catch (NumberFormatException e2) {
            return i2;
        }
    }

    private long a(String str, long j) {
        try {
            return Long.parseLong(i(str));
        } catch (NumberFormatException e2) {
            return j;
        }
    }

    public void a(be beVar) {
        this.f1132a = beVar;
    }
}
