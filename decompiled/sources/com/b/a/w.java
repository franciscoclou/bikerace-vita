package com.b.a;

import java.io.File;
import java.io.FilenameFilter;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class w implements FilenameFilter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f351a;

    public w(String str) {
        this.f351a = str;
    }

    @Override // java.io.FilenameFilter
    public final boolean accept(File file, String str) {
        return (str.equals(new StringBuilder().append(this.f351a).append(".cls").toString()) || !str.contains(this.f351a) || str.endsWith(".cls_temp")) ? false : true;
    }
}
