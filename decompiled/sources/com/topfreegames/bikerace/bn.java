package com.topfreegames.bikerace;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: UnlockConditions.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class bn {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final bo f1161a = new bo(9, 0, 0, null);
    private static final Map<Integer, bo> b;

    static {
        HashMap map = new HashMap();
        map.put(1, new bo(0, 0, 0, null));
        map.put(2, new bo(12, 0, 0, null));
        map.put(3, new bo(28, 0, 0, null));
        map.put(4, new bo(44, 2, 0, null));
        map.put(5, new bo(60, 6, 0, null));
        map.put(6, new bo(84, 20, 0, null));
        map.put(7, new bo(108, 0, 1, null));
        map.put(8, new bo(132, 0, 3, null));
        map.put(9, new bo(156, 0, 0, c.NINJA));
        map.put(10, new bo(180, 0, 0, c.SILVER));
        map.put(15, new bo(66, 0, 0, null));
        map.put(11, new bo(204, 0, 0, c.BEAT));
        map.put(12, new bo(228, 750, 0, null));
        map.put(13, new bo(66, 0, 0, null));
        map.put(14, new bo(66, 0, 0, null));
        map.put(18, new bo(66, 0, 0, null));
        map.put(17, new bo(66, 0, 0, null));
        map.put(19, new bo(66, 0, 0, null));
        map.put(999, new bo(24, 0, 0, null));
        b = Collections.unmodifiableMap(map);
    }

    public static bo a(int i) {
        return b.get(Integer.valueOf(i));
    }

    public static bo a() {
        return f1161a;
    }
}
