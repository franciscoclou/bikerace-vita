package com.b.a;

import java.io.File;
import java.io.FilenameFilter;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class v implements FilenameFilter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f350a;

    public v(String str) {
        this.f350a = str;
    }

    @Override // java.io.FilenameFilter
    public final boolean accept(File file, String str) {
        return str.contains(this.f350a) && !str.endsWith(".cls_temp");
    }
}
