package com.amazon.aws.tvmclient;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.a.a.a;
import org.apache.commons.a.a.b;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AESEncryption {
    public static final String ENCRYPTION_ALGORITHM = "AES/CBC/PKCS5Padding";

    public static String unwrap(String str, String str2) {
        byte[] bArrB = a.b(str.getBytes());
        byte[] bArr = new byte[16];
        byte[] bArr2 = new byte[bArrB.length - 16];
        System.arraycopy(bArrB, 0, bArr, 0, 16);
        System.arraycopy(bArrB, 16, bArr2, 0, bArrB.length - 16);
        return new String(decrypt(bArr2, str2, bArr));
    }

    public static byte[] decrypt(byte[] bArr, String str, byte[] bArr2) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidParameterSpecException, InvalidKeyException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance("AES");
        algorithmParameters.init(new IvParameterSpec(bArr2));
        cipher.init(2, getKey(str), algorithmParameters);
        return cipher.doFinal(bArr);
    }

    private static SecretKeySpec getKey(String str) {
        return new SecretKeySpec(b.a(str.toCharArray()), "AES");
    }
}
