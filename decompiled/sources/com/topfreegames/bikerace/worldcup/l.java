package com.topfreegames.bikerace.worldcup;

import android.content.Context;
import android.content.res.Resources;
import com.topfreegames.bikerace.ap;
import com.topfreegames.bikerace.bb;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: compiled from: WorldCupHelper.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final long f1456a;
    private static /* synthetic */ int[] b;
    private static /* synthetic */ int[] c;
    private static /* synthetic */ int[] d;

    static /* synthetic */ int[] c() {
        int[] iArr = b;
        if (iArr == null) {
            iArr = new int[com.topfreegames.bikerace.worldcup.views.e.valuesCustom().length];
            try {
                iArr[com.topfreegames.bikerace.worldcup.views.e.BIKES.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[com.topfreegames.bikerace.worldcup.views.e.GEM_SHOP.ordinal()] = 4;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[com.topfreegames.bikerace.worldcup.views.e.SLOT_ORDINARY.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[com.topfreegames.bikerace.worldcup.views.e.SLOT_RARE.ordinal()] = 3;
            } catch (NoSuchFieldError e4) {
            }
            b = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] d() {
        int[] iArr = c;
        if (iArr == null) {
            iArr = new int[com.topfreegames.bikerace.c.valuesCustom().length];
            try {
                iArr[com.topfreegames.bikerace.c.ACROBATIC.ordinal()] = 12;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.ARMY.ordinal()] = 17;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.BEAT.ordinal()] = 13;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.BRONZE.ordinal()] = 8;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.COP.ordinal()] = 6;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.EASTER.ordinal()] = 21;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.GHOST.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.GIRL.ordinal()] = 11;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.GOLD.ordinal()] = 10;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.HALLOWEEN.ordinal()] = 18;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.KIDS.ordinal()] = 3;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.NINJA.ordinal()] = 5;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.NOT_USED_IOS_DAILY_WORLD_BIKE_INDEX_HOLDER.ordinal()] = 22;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.REGULAR.ordinal()] = 1;
            } catch (NoSuchFieldError e14) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.RETRO.ordinal()] = 7;
            } catch (NoSuchFieldError e15) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.SANTA.ordinal()] = 20;
            } catch (NoSuchFieldError e16) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.SILVER.ordinal()] = 9;
            } catch (NoSuchFieldError e17) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.SPAM.ordinal()] = 14;
            } catch (NoSuchFieldError e18) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.SUPER.ordinal()] = 2;
            } catch (NoSuchFieldError e19) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.THANKSGIVING.ordinal()] = 19;
            } catch (NoSuchFieldError e20) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.ULTRA.ordinal()] = 15;
            } catch (NoSuchFieldError e21) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_ARGENTINA.ordinal()] = 35;
            } catch (NoSuchFieldError e22) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_AUSTRALIA.ordinal()] = 25;
            } catch (NoSuchFieldError e23) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_BELGIUM.ordinal()] = 32;
            } catch (NoSuchFieldError e24) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_BRAZIL.ordinal()] = 29;
            } catch (NoSuchFieldError e25) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_ENGLAND.ordinal()] = 24;
            } catch (NoSuchFieldError e26) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_FRANCE.ordinal()] = 27;
            } catch (NoSuchFieldError e27) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_GERMANY.ordinal()] = 28;
            } catch (NoSuchFieldError e28) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_ITALY.ordinal()] = 34;
            } catch (NoSuchFieldError e29) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_JAPAN.ordinal()] = 31;
            } catch (NoSuchFieldError e30) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_MEXICO.ordinal()] = 33;
            } catch (NoSuchFieldError e31) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_NETHERLANDS.ordinal()] = 26;
            } catch (NoSuchFieldError e32) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_SPAIN.ordinal()] = 30;
            } catch (NoSuchFieldError e33) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_USA.ordinal()] = 23;
            } catch (NoSuchFieldError e34) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.ZOMBIE.ordinal()] = 16;
            } catch (NoSuchFieldError e35) {
            }
            c = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] e() {
        int[] iArr = d;
        if (iArr == null) {
            iArr = new int[b.valuesCustom().length];
            try {
                iArr[b.BACK.ordinal()] = 4;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[b.FRONT.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[b.HELMET.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[b.SUIT.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            d = iArr;
        }
        return iArr;
    }

    static {
        long time = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            time = simpleDateFormat.parse(new String("2014/07/04 00:00:00".getBytes("ASCII"), "ASCII")).getTime();
        } catch (UnsupportedEncodingException e) {
            if (ap.d()) {
                e.printStackTrace();
            }
        } catch (ParseException e2) {
            if (ap.d()) {
                e2.printStackTrace();
            }
        }
        f1456a = time;
    }

    public static int a(com.topfreegames.bikerace.worldcup.views.e eVar) {
        switch (c()[eVar.ordinal()]) {
            case 1:
                return 2130838032;
            case 2:
                return 2130838034;
            case 3:
                return 2130838035;
            case 4:
                return 2130838033;
            default:
                return 0;
        }
    }

    public static String a(Context context, com.topfreegames.bikerace.worldcup.views.e eVar) {
        Resources resources = context.getResources();
        switch (c()[eVar.ordinal()]) {
            case 1:
                return resources.getString(2131100087);
            case 2:
                return resources.getString(2131100088);
            case 3:
                return resources.getString(2131100089);
            case 4:
                return resources.getString(2131100090);
            default:
                return "";
        }
    }

    public static int a(com.topfreegames.bikerace.c cVar) {
        switch (d()[cVar.ordinal()]) {
            case 23:
                return 2130838005;
            case 24:
                return 2130838001;
            case 25:
                return 2130837995;
            case 26:
                return 2130838000;
            case 27:
                return 2130837999;
            case 28:
                return 2130837993;
            case 29:
                return 2130837997;
            case 30:
                return 2130837998;
            case 31:
                return 2130838003;
            case 32:
                return 2130837996;
            case 33:
                return 2130838004;
            case 34:
                return 2130838002;
            case 35:
                return 2130837994;
            default:
                return -1;
        }
    }

    public static String a(Context context, com.topfreegames.bikerace.c cVar) {
        switch (d()[cVar.ordinal()]) {
            case 23:
                return context.getString(2131100046);
            case 24:
                return context.getString(2131100047);
            case 25:
                return context.getString(2131100051);
            case 26:
                return context.getString(2131100048);
            case 27:
                return context.getString(2131100052);
            case 28:
                return context.getString(2131100049);
            case 29:
                return context.getString(2131100050);
            case 30:
                return context.getString(2131100044);
            case 31:
                return context.getString(2131100045);
            case 32:
                return context.getString(2131100055);
            case 33:
                return context.getString(2131100056);
            case 34:
                return context.getString(2131100054);
            case 35:
                return context.getString(2131100053);
            default:
                return "";
        }
    }

    public static String b(Context context, com.topfreegames.bikerace.c cVar) {
        switch (d()[cVar.ordinal()]) {
            case 23:
                return context.getString(2131100033);
            case 24:
                return context.getString(2131100034);
            case 25:
                return context.getString(2131100038);
            case 26:
                return context.getString(2131100035);
            case 27:
                return context.getString(2131100039);
            case 28:
                return context.getString(2131100036);
            case 29:
                return context.getString(2131100037);
            case 30:
                return context.getString(2131100031);
            case 31:
                return context.getString(2131100032);
            case 32:
                return context.getString(2131100043);
            case 33:
                return context.getString(2131100042);
            case 34:
                return context.getString(2131100041);
            case 35:
                return context.getString(2131100040);
            default:
                return "";
        }
    }

    public static String a(Context context, a aVar) {
        switch (e()[aVar.b().ordinal()]) {
            case 1:
                return context.getString(2131100057);
            case 2:
                return context.getString(2131100058);
            case 3:
                return context.getString(2131100059);
            case 4:
                return context.getString(2131100060);
            default:
                return "";
        }
    }

    public static int a(a aVar, boolean z) {
        switch (e()[aVar.b().ordinal()]) {
            case 1:
                return z ? e(aVar) : f(aVar);
            case 2:
                return z ? c(aVar) : d(aVar);
            case 3:
                return z ? g(aVar) : h(aVar);
            case 4:
                return z ? a(aVar) : b(aVar);
            default:
                return -1;
        }
    }

    public static String a(long j) {
        if (j < 0) {
            return null;
        }
        long j2 = j / 86400000;
        long j3 = j - (86400000 * j2);
        long j4 = j3 / 3600000;
        long j5 = j3 - (3600000 * j4);
        long j6 = j5 / 60000;
        long j7 = (j5 - (60000 * j6)) / 1000;
        if (j2 > 0) {
            return String.format(Locale.US, "%dd %dh %dm %ds", Long.valueOf(j2), Long.valueOf(j4), Long.valueOf(j6), Long.valueOf(j7));
        }
        if (j4 > 0) {
            return String.format(Locale.US, "%dh %dm %ds", Long.valueOf(j4), Long.valueOf(j6), Long.valueOf(j7));
        }
        return j6 > 0 ? String.format(Locale.US, "%dm %ds", Long.valueOf(j6), Long.valueOf(j7)) : String.format(Locale.US, "%ds", Long.valueOf(j7));
    }

    public static com.topfreegames.bikerace.c[] a() {
        return new com.topfreegames.bikerace.c[]{com.topfreegames.bikerace.c.WORLDCUP_AUSTRALIA, com.topfreegames.bikerace.c.WORLDCUP_BRAZIL, com.topfreegames.bikerace.c.WORLDCUP_USA, com.topfreegames.bikerace.c.WORLDCUP_FRANCE, com.topfreegames.bikerace.c.WORLDCUP_GERMANY, com.topfreegames.bikerace.c.WORLDCUP_JAPAN, com.topfreegames.bikerace.c.WORLDCUP_NETHERLANDS, com.topfreegames.bikerace.c.WORLDCUP_SPAIN, com.topfreegames.bikerace.c.WORLDCUP_ENGLAND, com.topfreegames.bikerace.c.WORLDCUP_ARGENTINA, com.topfreegames.bikerace.c.WORLDCUP_BELGIUM, com.topfreegames.bikerace.c.WORLDCUP_ITALY, com.topfreegames.bikerace.c.WORLDCUP_MEXICO};
    }

    public static int b(com.topfreegames.bikerace.c cVar) {
        switch (d()[cVar.ordinal()]) {
            case 23:
                return 2130837715;
            case 24:
                return 2130837709;
            case 25:
            default:
                return 2130837702;
            case 26:
                return 2130837708;
            case 27:
                return 2130837707;
            case 28:
                return 2130837699;
            case 29:
                return 2130837705;
            case 30:
                return 2130837706;
            case 31:
                return 2130837712;
            case 32:
                return 2130837703;
            case 33:
                return 2130837713;
            case 34:
                return 2130837710;
            case 35:
                return 2130837700;
        }
    }

    public static String c(Context context, com.topfreegames.bikerace.c cVar) {
        switch (d()[cVar.ordinal()]) {
            case 23:
                return context.getString(2131100111);
            case 24:
                return context.getString(2131100110);
            case 25:
                return context.getString(2131100108);
            case 29:
                return context.getString(2131100109);
            case 34:
                return context.getString(2131100107);
            default:
                return " ";
        }
    }

    private static int a(a aVar) {
        switch (d()[aVar.a().ordinal()]) {
            case 24:
                return 2130838070;
            case 25:
                return 2130838065;
            case 26:
                return 2130838069;
            case 27:
                return 2130838068;
            case 28:
                return 2130838064;
            case 29:
                return 2130838066;
            case 30:
                return 2130838067;
            case 31:
                return 2130838071;
            case 32:
                return 2130837815;
            case 33:
                return 2130837817;
            case 34:
                return 2130837816;
            case 35:
                return 2130837814;
            default:
                return 2130838072;
        }
    }

    private static int b(a aVar) {
        switch (d()[aVar.a().ordinal()]) {
            case 24:
                return 2130838061;
            case 25:
                return 2130838056;
            case 26:
                return 2130838060;
            case 27:
                return 2130838059;
            case 28:
                return 2130838055;
            case 29:
                return 2130838057;
            case 30:
                return 2130838058;
            case 31:
                return 2130838062;
            case 32:
                return 2130837811;
            case 33:
                return 2130837813;
            case 34:
                return 2130837812;
            case 35:
                return 2130837810;
            default:
                return 2130838063;
        }
    }

    private static int c(a aVar) {
        switch (d()[aVar.a().ordinal()]) {
            case 24:
                return 2130838029;
            case 25:
                return 2130838024;
            case 26:
                return 2130838028;
            case 27:
                return 2130838027;
            case 28:
                return 2130838023;
            case 29:
                return 2130838025;
            case 30:
                return 2130838026;
            case 31:
                return 2130838030;
            case 32:
                return 2130837754;
            case 33:
                return 2130837756;
            case 34:
                return 2130837755;
            case 35:
                return 2130837753;
            default:
                return 2130838031;
        }
    }

    private static int d(a aVar) {
        switch (d()[aVar.a().ordinal()]) {
            case 24:
                return 2130838020;
            case 25:
                return 2130838015;
            case 26:
                return 2130838019;
            case 27:
                return 2130838018;
            case 28:
                return 2130838014;
            case 29:
                return 2130838016;
            case 30:
                return 2130838017;
            case 31:
                return 2130838021;
            case 32:
                return 2130837750;
            case 33:
                return 2130837752;
            case 34:
                return 2130837751;
            case 35:
                return 2130837749;
            default:
                return 2130838022;
        }
    }

    private static int e(a aVar) {
        switch (d()[aVar.a().ordinal()]) {
            case 24:
                return 2130837970;
            case 25:
                return 2130837965;
            case 26:
                return 2130837969;
            case 27:
                return 2130837968;
            case 28:
                return 2130837964;
            case 29:
                return 2130837966;
            case 30:
                return 2130837967;
            case 31:
                return 2130837971;
            case 32:
                return 2130837565;
            case 33:
                return 2130837567;
            case 34:
                return 2130837566;
            case 35:
                return 2130837564;
            default:
                return 2130837972;
        }
    }

    private static int f(a aVar) {
        switch (d()[aVar.a().ordinal()]) {
            case 24:
                return 2130837961;
            case 25:
                return 2130837956;
            case 26:
                return 2130837960;
            case 27:
                return 2130837959;
            case 28:
                return 2130837955;
            case 29:
                return 2130837957;
            case 30:
                return 2130837958;
            case 31:
                return 2130837962;
            case 32:
                return 2130837561;
            case 33:
                return 2130837563;
            case 34:
                return 2130837562;
            case 35:
                return 2130837560;
            default:
                return 2130837963;
        }
    }

    private static int g(a aVar) {
        switch (d()[aVar.a().ordinal()]) {
            case 24:
                return 2130838052;
            case 25:
                return 2130838047;
            case 26:
                return 2130838051;
            case 27:
                return 2130838050;
            case 28:
                return 2130838046;
            case 29:
                return 2130838048;
            case 30:
                return 2130838049;
            case 31:
                return 2130838053;
            case 32:
                return 2130837807;
            case 33:
                return 2130837809;
            case 34:
                return 2130837808;
            case 35:
                return 2130837806;
            default:
                return 2130838054;
        }
    }

    private static int h(a aVar) {
        switch (d()[aVar.a().ordinal()]) {
            case 24:
                return 2130838043;
            case 25:
                return 2130838038;
            case 26:
                return 2130838042;
            case 27:
                return 2130838041;
            case 28:
                return 2130838037;
            case 29:
                return 2130838039;
            case 30:
                return 2130838040;
            case 31:
                return 2130838044;
            case 32:
                return 2130837803;
            case 33:
                return 2130837805;
            case 34:
                return 2130837804;
            case 35:
                return 2130837802;
            default:
                return 2130838045;
        }
    }

    public static boolean b(long j) {
        return f1456a < j && j < f1456a + 86400000;
    }

    public static long b() {
        return f1456a;
    }

    public static int c(long j) {
        return b(j) ? 4 : 2;
    }

    public static boolean a(long j, bb bbVar) {
        return b(j) && a(bbVar, j);
    }

    public static boolean a(bb bbVar, long j) {
        long jAe = bbVar.ae();
        return jAe - j > 0 && 86400000 > jAe - j;
    }
}
