package com.b.a;

import java.io.File;
import java.io.FilenameFilter;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class bd implements FilenameFilter {
    bd() {
    }

    @Override // java.io.FilenameFilter
    public final boolean accept(File file, String str) {
        return str.length() == 39 && str.endsWith(".cls");
    }
}
