package com.topfreegames.bikerace.h;

import android.content.Context;
import com.amazonaws.javax.xml.stream.XMLStreamConstants;

/* JADX INFO: compiled from: WorldFactory.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f1259a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    public static final int[] b = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
    public static final int[] c = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
    public static final int[] d = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 999};

    public static x a(int i, Context context) {
        switch (i) {
            case 1:
                return new x(1, d.a(), 2130837540, 2130837541);
            case 2:
                return new x(2, o.a(), 2130837542, 2130837543);
            case 3:
                return new x(3, p.a(), 2130837544, 2130837545);
            case 4:
                return new x(4, q.a(), 2130837546, 2130837547);
            case 5:
                return new x(5, r.a(), 2130837548, 2130837549);
            case 6:
                return new x(6, s.a(), 2130837550, 2130837551);
            case 7:
                return new x(7, t.a(), 2130837540, 2130837541);
            case 8:
                return new x(8, u.a(), 2130837542, 2130837543);
            case 9:
                return new x(9, v.a(), 2130837544, 2130837545);
            case XMLStreamConstants.ATTRIBUTE /* 10 */:
                return new x(10, e.a(), 2130837546, 2130837547);
            case XMLStreamConstants.DTD /* 11 */:
                return new x(11, f.a(), 2130837548, 2130837549);
            case XMLStreamConstants.CDATA /* 12 */:
                return new x(12, g.a(), 2130837550, 2130837551);
            case XMLStreamConstants.NAMESPACE /* 13 */:
                return new x(13, h.a(), 2130837556, 2130837557);
            case XMLStreamConstants.NOTATION_DECLARATION /* 14 */:
                return new x(14, i.a(), 2130837556, 2130837557);
            case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
                return new x(15, j.a(), 2130837556, 2130837557);
            case 16:
                return new x(16, k.a(), 2130837554, 2130837555);
            case 17:
                return new x(17, l.a(), 2130837558, 2130837559);
            case 18:
                return new x(18, m.a(), 2130837556, 2130837557);
            case 19:
                return new x(19, n.a(), 2130837552, 2130837553);
            case 999:
                return new com.topfreegames.bikerace.h.a.r(com.topfreegames.bikerace.h.a.l.a(context), 2130837540, 2130837541);
            default:
                return null;
        }
    }

    public static boolean a(int i) {
        for (int i2 : d) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }

    public static int b(int i) {
        if (i == 999) {
            return com.topfreegames.bikerace.h.a.q.a().c();
        }
        return 8;
    }

    public static String a(Context context, int i) {
        switch (i) {
            case 1:
                return context.getString(2131099822);
            case 2:
                return context.getString(2131099824);
            case 3:
                return context.getString(2131099826);
            case 4:
                return context.getString(2131099828);
            case 5:
                return context.getString(2131099830);
            case 6:
                return context.getString(2131099832);
            case 7:
                return context.getString(2131099834);
            case 8:
                return context.getString(2131099836);
            case 9:
                return context.getString(2131099838);
            case XMLStreamConstants.ATTRIBUTE /* 10 */:
                return context.getString(2131099840);
            case XMLStreamConstants.DTD /* 11 */:
                return context.getString(2131099842);
            case XMLStreamConstants.CDATA /* 12 */:
                return context.getString(2131099844);
            case XMLStreamConstants.NAMESPACE /* 13 */:
                return context.getString(2131099845);
            case XMLStreamConstants.NOTATION_DECLARATION /* 14 */:
                return context.getString(2131099846);
            case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
                return context.getString(2131099847);
            case 16:
                return context.getString(2131099848);
            case 17:
                return context.getString(2131099849);
            case 18:
                return context.getString(2131099850);
            case 19:
                return context.getString(2131099851);
            case 999:
                return context.getString(2131099852);
            default:
                return "Undefined";
        }
    }
}
