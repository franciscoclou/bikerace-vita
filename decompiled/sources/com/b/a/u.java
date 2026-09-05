package com.b.a;

import java.io.File;
import java.io.FilenameFilter;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class u implements FilenameFilter {
    private u() {
    }

    /* synthetic */ u(byte b) {
        this();
    }

    @Override // java.io.FilenameFilter
    public final boolean accept(File file, String str) {
        return !bc.f335a.accept(file, str) && bc.d.matcher(str).matches();
    }
}
