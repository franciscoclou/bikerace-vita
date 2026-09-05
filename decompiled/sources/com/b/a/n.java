package com.b.a;

import java.io.File;
import java.io.FilenameFilter;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class n implements FilenameFilter {
    n() {
    }

    @Override // java.io.FilenameFilter
    public final boolean accept(File file, String str) {
        return bc.d.matcher(str).matches();
    }
}
