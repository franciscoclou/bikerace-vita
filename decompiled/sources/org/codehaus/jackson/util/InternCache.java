package org.codehaus.jackson.util;

import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class InternCache extends LinkedHashMap<String, String> {
    private static final int MAX_ENTRIES = 192;
    public static final InternCache instance = new InternCache();

    private InternCache() {
        super(MAX_ENTRIES, 0.8f, true);
    }

    @Override // java.util.LinkedHashMap
    protected boolean removeEldestEntry(Map.Entry<String, String> entry) {
        return size() > MAX_ENTRIES;
    }

    public synchronized String intern(String str) {
        String strIntern;
        strIntern = get(str);
        if (strIntern == null) {
            strIntern = str.intern();
            put(strIntern, strIntern);
        }
        return strIntern;
    }
}
