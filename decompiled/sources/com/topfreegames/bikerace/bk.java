package com.topfreegames.bikerace;

import android.graphics.RectF;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: TextureCoordinates.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class bk {
    public static final RectF A;
    public static final RectF B;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final RectF f1158a = new RectF(0.001953125f, 0.31445312f, 0.8979492f, 0.6269531f);
    public static final RectF b = new RectF(0.001953125f, 9.765625E-4f, 0.8979492f, 0.31347656f);
    public static final RectF c = new RectF(0.0014648438f, 0.6279297f, 0.47021484f, 0.9404297f);
    public static final RectF d = new RectF(0.9536133f, 0.08544922f, 0.9995117f, 0.114746094f);
    public static final RectF e = new RectF(0.9536133f, 0.115722656f, 0.9995117f, 0.14501953f);
    public static final RectF f = new RectF(0.9536133f, 0.1459961f, 0.9995117f, 0.17529297f);
    public static final RectF g = new RectF(0.9536133f, 0.17626953f, 0.9995117f, 0.2055664f);
    public static final RectF h = new RectF(0.9536133f, 0.20654297f, 0.9995117f, 0.23583984f);
    public static final RectF i = new RectF(0.9536133f, 0.2368164f, 0.9995117f, 0.26611328f);
    public static final RectF j = new RectF(0.9536133f, 0.26708984f, 0.9995117f, 0.29638672f);
    public static final RectF k = new RectF(0.9536133f, 0.29736328f, 0.9995117f, 0.32666016f);
    public static final RectF l = new RectF(0.9868164f, 0.42529297f, 0.99853516f, 0.32763672f);
    public static final RectF m = new RectF(0.9003906f, 0.1977539f, 0.9511719f, 0.2529297f);
    public static final RectF n = new RectF(0.9003906f, 0.08544922f, 0.9511719f, 0.140625f);
    public static final RectF o = new RectF(0.9003906f, 0.3100586f, 0.9511719f, 0.36523438f);
    public static final RectF p = new RectF(0.9003906f, 0.25390625f, 0.9511719f, 0.30908203f);
    public static final RectF q = new RectF(0.9003906f, 0.14160156f, 0.9511719f, 0.19677734f);
    public static final RectF r = new RectF(0.9003906f, 0.36621094f, 0.9511719f, 0.42138672f);
    public static final RectF s = new RectF(0.9003906f, 0.53466797f, 0.9511719f, 0.58984375f);
    public static final RectF t = new RectF(0.9003906f, 0.47851562f, 0.9511719f, 0.5336914f);
    public static final RectF u = new RectF(0.9003906f, 0.42236328f, 0.9511719f, 0.47753906f);
    public static final RectF v = new RectF(0.95654297f, 0.32763672f, 0.98583984f, 0.3569336f);
    public static final RectF w = new RectF(0.95654297f, 0.35888672f, 0.98583984f, 0.3876953f);
    public static final RectF x = new RectF(0.95703125f, 0.39160156f, 0.98535156f, 0.41992188f);
    public static final RectF y = new RectF(0.0f, 0.0f, 1.0f, 1.0f);
    public static final Map<c, RectF> z;

    static {
        HashMap map = new HashMap();
        map.put(c.REGULAR, new RectF(0.0f, 0.0f, 0.7890625f, 0.6640625f));
        map.put(c.KIDS, new RectF(0.0f, 0.0f, 0.7890625f, 0.703125f));
        map.put(c.SUPER, new RectF(0.0f, 0.0f, 0.8984375f, 0.6640625f));
        map.put(c.GHOST, new RectF(0.0f, 0.0f, 0.78125f, 0.6640625f));
        map.put(c.COP, new RectF(0.0f, 0.0f, 0.859375f, 0.68359375f));
        map.put(c.NINJA, new RectF(0.0f, 0.0f, 0.80078125f, 0.64453125f));
        map.put(c.RETRO, new RectF(0.0f, 0.0f, 0.7890625f, 0.67578125f));
        map.put(c.BRONZE, new RectF(0.0f, 0.0f, 0.734375f, 0.65625f));
        map.put(c.SILVER, new RectF(0.0f, 0.0f, 0.734375f, 0.65625f));
        map.put(c.GOLD, new RectF(0.0f, 0.0f, 0.734375f, 0.65625f));
        map.put(c.SPAM, new RectF(0.0f, 0.0f, 0.8359375f, 0.7109375f));
        map.put(c.BEAT, new RectF(0.0f, 0.0f, 1.0f, 0.61328125f));
        map.put(c.GIRL, new RectF(0.0f, 0.0f, 0.7890625f, 0.66796875f));
        map.put(c.ACROBATIC, new RectF(0.0f, 0.0f, 0.765625f, 0.63671875f));
        map.put(c.ZOMBIE, new RectF(0.0f, 0.0f, 0.76171875f, 0.6640625f));
        map.put(c.ULTRA, new RectF(0.0f, 0.0f, 1.0f, 0.67578125f));
        map.put(c.ARMY, new RectF(0.0f, 0.0f, 1.0f, 0.61328125f));
        map.put(c.HALLOWEEN, new RectF(0.0f, 0.0f, 0.81640625f, 0.70703125f));
        map.put(c.THANKSGIVING, new RectF(0.0f, 0.0f, 0.828125f, 0.7734375f));
        map.put(c.SANTA, new RectF(0.0f, 0.0f, 0.69140625f, 0.65625f));
        map.put(c.EASTER, new RectF(0.0f, 0.0f, 0.89453125f, 0.703125f));
        map.put(c.WORLDCUP_AUSTRALIA, new RectF(0.0f, 0.0f, 0.8125f, 0.72265625f));
        map.put(c.WORLDCUP_BRAZIL, new RectF(0.0f, 0.0f, 0.80078125f, 0.7265625f));
        map.put(c.WORLDCUP_USA, new RectF(0.0f, 0.0f, 0.8359375f, 0.74609375f));
        map.put(c.WORLDCUP_FRANCE, new RectF(0.0f, 0.0f, 0.7890625f, 0.72265625f));
        map.put(c.WORLDCUP_GERMANY, new RectF(0.0f, 0.0f, 0.84375f, 0.75f));
        map.put(c.WORLDCUP_JAPAN, new RectF(0.0f, 0.0f, 0.83984375f, 0.75390625f));
        map.put(c.WORLDCUP_NETHERLANDS, new RectF(0.0f, 0.0f, 0.83203125f, 0.828125f));
        map.put(c.WORLDCUP_SPAIN, new RectF(0.0f, 0.0f, 0.79296875f, 0.74609375f));
        map.put(c.WORLDCUP_ENGLAND, new RectF(0.0f, 0.0f, 0.7890625f, 0.7421875f));
        map.put(c.WORLDCUP_ARGENTINA, new RectF(0.0f, 0.0f, 0.80078125f, 0.7578125f));
        map.put(c.WORLDCUP_ITALY, new RectF(0.0f, 0.0f, 0.7890625f, 0.7734375f));
        map.put(c.WORLDCUP_BELGIUM, new RectF(0.0f, 0.0f, 0.83984375f, 0.75f));
        map.put(c.WORLDCUP_MEXICO, new RectF(0.0f, 0.0f, 0.76171875f, 0.73828125f));
        map.put(c.NOT_USED_IOS_DAILY_WORLD_BIKE_INDEX_HOLDER, new RectF(0.0f, 0.0f, 0.7890625f, 0.6640625f));
        if (ap.d()) {
            for (c cVar : c.valuesCustom()) {
                if (!map.containsKey(cVar)) {
                    throw new Error("Missing coords " + cVar.toString());
                }
            }
        }
        z = Collections.unmodifiableMap(map);
        A = new RectF(0.001953125f, 0.94384766f, 0.008300781f, 0.99560547f);
        B = new RectF(0.4897461f, 0.6298828f, 0.54785156f, 0.7050781f);
    }
}
