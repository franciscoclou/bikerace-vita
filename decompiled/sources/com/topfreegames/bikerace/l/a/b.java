package com.topfreegames.bikerace.l.a;

import com.amazonaws.services.dynamodb.model.AttributeValue;
import com.amazonaws.services.dynamodb.model.Key;
import com.amazonaws.services.dynamodb.model.PutItemRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: DynamoRepository.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class b {
    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            int i2 = ((bArr[i] >> 4) & 15) << 4;
            int i3 = bArr[i] & 15;
            if (i2 == 0) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(i2 | i3));
        }
        return sb.toString();
    }

    public static String c(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            return a(messageDigest.digest()).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected PutItemRequest a(String str, String str2, Map<String, AttributeValue> map) {
        HashMap map2 = new HashMap();
        for (String str3 : map.keySet()) {
            map2.put(str3, map.get(str3));
        }
        map2.put("id", new AttributeValue(str));
        map2.put("md5", new AttributeValue(c(str.toLowerCase())));
        return new PutItemRequest(str2, map2);
    }

    protected static Key d(String str) {
        return new Key(new AttributeValue().withS(str), new AttributeValue().withS(c(str.toLowerCase())));
    }
}
