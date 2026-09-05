package com.b.a;

import java.io.File;
import java.util.Comparator;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class m implements Comparator<File> {
    m() {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(File file, File file2) {
        return file.getName().compareTo(file2.getName());
    }
}
