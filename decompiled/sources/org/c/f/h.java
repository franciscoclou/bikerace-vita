package org.c.f;

import java.util.Random;

/* JADX INFO: compiled from: TimestampServiceImpl.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Random f1630a = new Random();

    h() {
    }

    Long a() {
        return Long.valueOf(System.currentTimeMillis());
    }

    Integer b() {
        return Integer.valueOf(this.f1630a.nextInt());
    }
}
