package com.topfreegames.bikerace;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: RuntimeTestConfig.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class bg {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Map<bi, bh> f1136a;
    private static int b;

    static {
        HashMap map = new HashMap();
        map.put(bi.FAKE_NUM_STARS, new bh(true, 240));
        map.put(bi.FAKE_NUM_MULTI_WINS, new bh(true, 5123));
        map.put(bi.FAKE_NUM_GEMS, new bh(true, 500));
        map.put(bi.FAKE_NUM_COINS, new bh(true, 100));
        map.put(bi.MULTIPLAYER_FORCE_SELECT_HIGHEST_LEVEL, new bh(false, 0));
        f1136a = Collections.unmodifiableMap(map);
        b = 0;
    }

    public static boolean a(bi biVar) {
        ap.d();
        return false;
    }

    public static int b(bi biVar) {
        Integer num;
        if (a(biVar)) {
            try {
                num = (Integer) f1136a.get(biVar).f1137a;
                if (num == null) {
                    num = 0;
                }
            } catch (Exception e) {
                num = 0;
            }
            return num.intValue();
        }
        throw new IllegalStateException("Should not be here if we are not debugging!!");
    }
}
