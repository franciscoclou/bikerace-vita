package com.google.ads;

import com.amazon.aws.tvmclient.AESEncryption;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class an {

    public class a extends Exception {
        public a() {
        }

        public a(Throwable th) {
            super(th);
        }
    }

    static void a(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (bArr[i] ^ 68);
        }
    }

    public static byte[] a(String str) throws a {
        byte[] bArrA = aq.a(str);
        if (bArrA.length != 32) {
            throw new a();
        }
        byte[] bArr = new byte[16];
        ByteBuffer.wrap(bArrA, 4, 16).get(bArr);
        a(bArr);
        return bArr;
    }

    public static byte[] a(byte[] bArr, String str) throws a {
        if (bArr.length != 16) {
            throw new a();
        }
        try {
            byte[] bArrA = aq.a(str);
            if (bArrA.length <= 16) {
                throw new a();
            }
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bArrA.length);
            byteBufferAllocate.put(bArrA);
            byteBufferAllocate.flip();
            byte[] bArr2 = new byte[16];
            byte[] bArr3 = new byte[bArrA.length - 16];
            byteBufferAllocate.get(bArr2);
            byteBufferAllocate.get(bArr3);
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
            Cipher cipher = Cipher.getInstance(AESEncryption.ENCRYPTION_ALGORITHM);
            cipher.init(2, secretKeySpec, new IvParameterSpec(bArr2));
            return cipher.doFinal(bArr3);
        } catch (InvalidAlgorithmParameterException e) {
            throw new a(e);
        } catch (InvalidKeyException e2) {
            throw new a(e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new a(e3);
        } catch (BadPaddingException e4) {
            throw new a(e4);
        } catch (IllegalBlockSizeException e5) {
            throw new a(e5);
        } catch (NoSuchPaddingException e6) {
            throw new a(e6);
        }
    }
}
