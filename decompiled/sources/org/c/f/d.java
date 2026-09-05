package org.c.f;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: HMACSha1SignatureService.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class d implements e {
    @Override // org.c.f.e
    public String a(String str, String str2, String str3) {
        try {
            org.c.g.c.a(str, "Base string cant be null or empty string");
            org.c.g.c.a(str2, "Api secret cant be null or empty string");
            return b(str, String.valueOf(org.c.g.b.a(str2)) + '&' + org.c.g.b.a(str3));
        } catch (Exception e) {
            throw new org.c.b.d(str, e);
        }
    }

    @Override // org.c.f.e
    public String a(String str, String str2) {
        try {
            return b(str, str2);
        } catch (Exception e) {
            return "";
        }
    }

    private String b(String str, String str2) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(XMLStreamWriterImpl.UTF_8), "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(secretKeySpec);
        return a(mac.doFinal(str.getBytes(XMLStreamWriterImpl.UTF_8))).replace("\r\n", "");
    }

    private String a(byte[] bArr) {
        return a.a().a(bArr);
    }

    @Override // org.c.f.e
    public String a() {
        return "HMAC-SHA1";
    }
}
