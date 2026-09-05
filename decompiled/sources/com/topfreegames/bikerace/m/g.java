package com.topfreegames.bikerace.m;

import android.util.Base64;
import java.io.UnsupportedEncodingException;

/* JADX INFO: compiled from: ObfuscationUtils.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class g {
    public static String a(String str, String str2, byte[] bArr) {
        try {
            byte[] bArrDecode = Base64.decode(str.getBytes(str2), 0);
            for (int i = 0; i < bArrDecode.length; i++) {
                bArrDecode[i] = (byte) (bArrDecode[i] ^ bArr[i % bArr.length]);
            }
            return new String(bArrDecode, str2);
        } catch (UnsupportedEncodingException e) {
            return null;
        } catch (IllegalArgumentException e2) {
            return null;
        }
    }
}
