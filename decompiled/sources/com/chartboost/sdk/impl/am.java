package com.chartboost.sdk.impl;

import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class am implements aj {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final String[] f420a = new String[XMLChar.MASK_NCNAME];
    private byte[] b = new byte[1024];
    private byte[] c = new byte[1024];
    private as d = new as();

    static {
        a((byte) 48, (byte) 57);
        a((byte) 97, (byte) 122);
        a((byte) 65, (byte) 90);
    }

    static void a(byte b, byte b2) {
        while (b < b2) {
            f420a[b] = String.valueOf("") + ((char) b);
            b = (byte) (b + 1);
        }
    }
}
