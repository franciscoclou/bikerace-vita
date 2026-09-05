package com.amazonaws.f;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final SimpleDateFormat f125a = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    protected final SimpleDateFormat b = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    protected final SimpleDateFormat c = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);

    public e() {
        this.f125a.setTimeZone(new SimpleTimeZone(0, "GMT"));
        this.c.setTimeZone(new SimpleTimeZone(0, "GMT"));
        this.b.setTimeZone(new SimpleTimeZone(0, "GMT"));
    }

    public String a(Date date) {
        String str;
        synchronized (this.f125a) {
            str = this.f125a.format(date);
        }
        return str;
    }

    public String b(Date date) {
        String str;
        synchronized (this.c) {
            str = this.c.format(date);
        }
        return str;
    }
}
