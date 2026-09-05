package com.b.a;

import java.io.File;
import java.io.FilenameFilter;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class i implements FilenameFilter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ String f342a;

    i(bc bcVar, String str) {
        this.f342a = str;
    }

    @Override // java.io.FilenameFilter
    public final boolean accept(File file, String str) {
        return str.startsWith(this.f342a);
    }
}
