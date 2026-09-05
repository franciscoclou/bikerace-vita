package com.b.a;

import com.b.a.a.cm;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ad {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final File f317a;
    private final Map<String, String> b;

    public ad(File file) {
        this(file, Collections.emptyMap());
    }

    public ad(File file, Map<String, String> map) {
        this.f317a = file;
        this.b = new HashMap(map);
        if (this.f317a.length() == 0) {
            this.b.putAll(af.f318a);
        }
    }

    public File d() {
        return this.f317a;
    }

    public String b() {
        return d().getName();
    }

    public String c() {
        String strB = b();
        return strB.substring(0, strB.lastIndexOf(46));
    }

    public Map<String, String> e() {
        return Collections.unmodifiableMap(this.b);
    }

    public boolean a() {
        cm.a().b().a("Crashlytics", "Removing report at " + this.f317a.getPath());
        return this.f317a.delete();
    }
}
