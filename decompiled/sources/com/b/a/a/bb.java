package com.b.a.a;

import java.io.File;
import java.util.Comparator;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class bb implements Comparator<File> {
    bb() {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(File file, File file2) {
        return (int) (file.lastModified() - file2.lastModified());
    }
}
