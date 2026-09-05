package com.chartboost.sdk.impl;

import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class z extends ai {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final Logger f500a = Logger.getLogger("com.mongodb");
    static final boolean b = Boolean.getBoolean("DEBUG.MONGO");
    public static final ByteOrder c;
    static final int d;
    static final ba e;

    static {
        if (f500a.getLevel() == null) {
            if (b) {
                f500a.setLevel(Level.ALL);
            } else {
                f500a.setLevel(Level.WARNING);
            }
        }
        c = ByteOrder.LITTLE_ENDIAN;
        d = Integer.parseInt(System.getProperty("MONGO.POOLSIZE", "10"));
        e = new ba(-1, -1, -1);
    }
}
