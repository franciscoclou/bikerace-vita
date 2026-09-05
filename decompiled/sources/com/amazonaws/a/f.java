package com.amazonaws.a;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class f implements o {
    protected static final String DEFAULT_ENCODING = "UTF-8";

    protected abstract void addSessionCredentials(com.amazonaws.j<?> jVar, e eVar);

    protected byte[] getBinaryRequestPayload(com.amazonaws.j<?> jVar) {
        if (!com.amazonaws.f.f.a(jVar)) {
            return getBinaryRequestPayloadWithoutQueryParams(jVar);
        }
        String strB = com.amazonaws.f.f.b(jVar);
        if (strB == null) {
            return new byte[0];
        }
        try {
            return strB.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new com.amazonaws.a("Unable to encode string into bytes");
        }
    }

    protected InputStream getBinaryRequestPayloadStream(com.amazonaws.j<?> jVar) {
        if (!com.amazonaws.f.f.a(jVar)) {
            return getBinaryRequestPayloadStreamWithoutQueryParams(jVar);
        }
        String strB = com.amazonaws.f.f.b(jVar);
        if (strB == null) {
            return new ByteArrayInputStream(new byte[0]);
        }
        try {
            return new ByteArrayInputStream(strB.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new com.amazonaws.a("Unable to encode string into bytes");
        }
    }

    protected InputStream getBinaryRequestPayloadStreamWithoutQueryParams(com.amazonaws.j<?> jVar) {
        try {
            InputStream inputStreamH = jVar.h();
            if (inputStreamH == null) {
                return new ByteArrayInputStream(new byte[0]);
            }
            if (inputStreamH instanceof com.amazonaws.f.j) {
                return (com.amazonaws.f.j) inputStreamH;
            }
            if (inputStreamH.markSupported()) {
                return jVar.h();
            }
            throw new com.amazonaws.a("Unable to read request payload to sign request.");
        } catch (Exception e) {
            throw new com.amazonaws.a("Unable to read request payload to sign request: " + e.getMessage(), e);
        }
    }

    protected byte[] getBinaryRequestPayloadWithoutQueryParams(com.amazonaws.j<?> jVar) {
        InputStream binaryRequestPayloadStreamWithoutQueryParams = getBinaryRequestPayloadStreamWithoutQueryParams(jVar);
        try {
            binaryRequestPayloadStreamWithoutQueryParams.mark(-1);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[5120];
            while (true) {
                int i = binaryRequestPayloadStreamWithoutQueryParams.read(bArr);
                if (i == -1) {
                    byteArrayOutputStream.close();
                    binaryRequestPayloadStreamWithoutQueryParams.reset();
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr, 0, i);
            }
        } catch (Exception e) {
            throw new com.amazonaws.a("Unable to read request payload to sign request: " + e.getMessage(), e);
        }
    }

    protected String getCanonicalizedEndpoint(URI uri) {
        String lowerCase = uri.getHost().toLowerCase();
        return com.amazonaws.f.f.a(uri) ? lowerCase + ":" + uri.getPort() : lowerCase;
    }

    protected String getCanonicalizedQueryString(com.amazonaws.j<?> jVar) {
        return com.amazonaws.f.f.a(jVar) ? "" : getCanonicalizedQueryString(jVar.d());
    }

    protected String getCanonicalizedQueryString(Map<String, String> map) {
        TreeMap treeMap = new TreeMap();
        treeMap.putAll(map);
        StringBuilder sb = new StringBuilder();
        Iterator it = treeMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            sb.append(com.amazonaws.f.f.a(str, false));
            sb.append("=");
            sb.append(com.amazonaws.f.f.a(str2, false));
            if (it.hasNext()) {
                sb.append("&");
            }
        }
        return sb.toString();
    }

    protected String getCanonicalizedResourcePath(String str) {
        return (str == null || str.length() == 0) ? "/" : com.amazonaws.f.f.a(str, true);
    }

    protected String getRequestPayload(com.amazonaws.j<?> jVar) {
        return newString(getBinaryRequestPayload(jVar));
    }

    protected String getRequestPayloadWithoutQueryParams(com.amazonaws.j<?> jVar) {
        return newString(getBinaryRequestPayloadWithoutQueryParams(jVar));
    }

    protected byte[] hash(InputStream inputStream) {
        try {
            DigestInputStream digestInputStream = new DigestInputStream(inputStream, MessageDigest.getInstance("SHA-256"));
            while (digestInputStream.read(new byte[1024]) > -1) {
            }
            return digestInputStream.getMessageDigest().digest();
        } catch (Exception e) {
            throw new com.amazonaws.a("Unable to compute hash while signing request: " + e.getMessage(), e);
        }
    }

    protected byte[] hash(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            return messageDigest.digest();
        } catch (Exception e) {
            throw new com.amazonaws.a("Unable to compute hash while signing request: " + e.getMessage(), e);
        }
    }

    protected byte[] hash(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (Exception e) {
            throw new com.amazonaws.a("Unable to compute hash while signing request: " + e.getMessage(), e);
        }
    }

    protected String newString(byte[] bArr) {
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new com.amazonaws.a("Unable to encode bytes to String", e);
        }
    }

    protected b sanitizeCredentials(b bVar) {
        String strA;
        String strB;
        String strC;
        synchronized (bVar) {
            strA = bVar.a();
            strB = bVar.b();
            strC = bVar instanceof e ? ((e) bVar).c() : null;
        }
        String strTrim = strB != null ? strB.trim() : strB;
        String strTrim2 = strA != null ? strA.trim() : strA;
        if (strC != null) {
            strC = strC.trim();
        }
        return bVar instanceof e ? new i(strTrim2, strTrim, strC) : new h(strTrim2, strTrim);
    }

    protected byte[] sign(String str, byte[] bArr, p pVar) {
        try {
            return sign(str.getBytes("UTF-8"), bArr, pVar);
        } catch (Exception e) {
            throw new com.amazonaws.a("Unable to calculate a request signature: " + e.getMessage(), e);
        }
    }

    protected byte[] sign(byte[] bArr, byte[] bArr2, p pVar) {
        try {
            Mac mac = Mac.getInstance(pVar.toString());
            mac.init(new SecretKeySpec(bArr2, pVar.toString()));
            return mac.doFinal(bArr);
        } catch (Exception e) {
            throw new com.amazonaws.a("Unable to calculate a request signature: " + e.getMessage(), e);
        }
    }

    protected String signAndBase64Encode(String str, String str2, p pVar) {
        try {
            return signAndBase64Encode(str.getBytes("UTF-8"), str2, pVar);
        } catch (UnsupportedEncodingException e) {
            throw new com.amazonaws.a("Unable to calculate a request signature: " + e.getMessage(), e);
        }
    }

    protected String signAndBase64Encode(byte[] bArr, String str, p pVar) {
        try {
            return new String(org.apache.commons.a.a.a.a(sign(bArr, str.getBytes("UTF-8"), pVar)));
        } catch (Exception e) {
            throw new com.amazonaws.a("Unable to calculate a request signature: " + e.getMessage(), e);
        }
    }
}
