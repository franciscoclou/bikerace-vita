package com.topfreegames.bikerace.d;

import android.content.Context;
import android.provider.Settings;
import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: CryptoHelper.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Cipher f1169a = null;
    private static Cipher b = null;

    private static byte[] c(Context context) {
        byte[] bArr;
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        if (string != null) {
            string.toLowerCase(Locale.ENGLISH);
        }
        if (string == null || string.length() <= 14 || string.equals("9774d56d682e549c")) {
            bArr = null;
        } else {
            try {
                bArr = new byte[16];
                byte[] bytes = string.getBytes(XMLStreamWriterImpl.UTF_8);
                for (int i = 0; i < bArr.length; i++) {
                    if (i < bytes.length) {
                        bArr[i] = bytes[i];
                    } else {
                        bArr[i] = (byte) (127 / i);
                    }
                }
            } catch (UnsupportedEncodingException e) {
                bArr = null;
            }
        }
        if (bArr == null) {
            return new byte[]{120, -36, 78, 84, 67, -12, 57, 47, 78, -98, 96, 1, 35, 32, 49, 18};
        }
        return bArr;
    }

    public static synchronized Cipher a(Context context) {
        if (f1169a == null) {
            f1169a = a(context, true);
        }
        return f1169a;
    }

    public static synchronized Cipher b(Context context) {
        if (b == null) {
            b = a(context, false);
        }
        return b;
    }

    private static Cipher a(Context context, boolean z) throws NoSuchPaddingException, NoSuchAlgorithmException {
        SecretKeySpec secretKeySpec;
        Cipher cipher;
        if (0 == 0 || 0 == 0) {
            try {
                secretKeySpec = new SecretKeySpec(c(context), "AES");
                cipher = Cipher.getInstance("AES");
            } catch (NoSuchAlgorithmException e) {
                secretKeySpec = null;
                cipher = null;
            } catch (NoSuchPaddingException e2) {
                secretKeySpec = null;
                cipher = null;
            }
        } else {
            secretKeySpec = null;
            cipher = null;
        }
        try {
            cipher.init(z ? 1 : 2, secretKeySpec);
            return cipher;
        } catch (InvalidKeyException e3) {
            return null;
        }
    }
}
