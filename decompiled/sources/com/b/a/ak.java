package com.b.a;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ak {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f320a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final int g;
    public final String h;
    public final String i;
    public final ac j;

    public static void a(File file, FilenameFilter filenameFilter, int i, Comparator<File> comparator) {
        File[] fileArrListFiles = file.listFiles(filenameFilter);
        if (fileArrListFiles != null && fileArrListFiles.length > i) {
            Arrays.sort(fileArrListFiles, comparator);
            int length = fileArrListFiles.length;
            for (File file2 : fileArrListFiles) {
                if (length > i) {
                    file2.delete();
                    length--;
                } else {
                    return;
                }
            }
        }
    }

    public ak(String str, String str2, String str3, String str4, String str5, String str6, int i, String str7, String str8, ac acVar) {
        this.f320a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = i;
        this.h = str7;
        this.i = str8;
        this.j = acVar;
    }
}
