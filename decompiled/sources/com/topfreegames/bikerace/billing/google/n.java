package com.topfreegames.bikerace.billing.google;

import android.text.TextUtils;
import android.util.Log;
import com.topfreegames.bikerace.ap;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: Security.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final SecureRandom f1156a = new SecureRandom();
    private static HashSet<Long> b = new HashSet<>();

    public static long a() {
        long jNextLong = f1156a.nextLong();
        b.add(Long.valueOf(jNextLong));
        return jNextLong;
    }

    public static void a(long j) {
        b.remove(Long.valueOf(j));
    }

    public static boolean b(long j) {
        return b.contains(Long.valueOf(j));
    }

    public static ArrayList<o> a(String str, String str2) {
        boolean z;
        int length;
        String str3;
        if (str == null) {
            Log.e("Security", "data is null");
            return null;
        }
        if (TextUtils.isEmpty(str2)) {
            z = false;
        } else {
            if (ap.t()) {
                str3 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjROQY4HznKC329J1FMjZAKc91PxUz9aDm2NaUqhHpGh3OZNnnUF6CCVUFdLIQgfHljwt1TKL1SOWBkRP++W3PLr/iVoe36k0GWyJpqsy/xGKrlsBiqN2kKMq54btzTCuSUhitLE6F4hifKYRkotSCNQApTTiQtYSnYgft3Eoln1E8lAVzwNcLAz91jhgwD2SxbyKT1dImy4jDXZA8RlZwXKoTkrK4UHFa2wHH/H9VbnCuyUmV1qJYTp4XKcg5e2y/OfJBQzBrVomN1V+KYYcqmY99ZpUQk+1zw4bXIE0sBQx5BCutULVZCWrJi1U/WRjL7YCicada+rX6IjQNVHlWwIDAQAB";
            } else {
                str3 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAixXz6PC/tHb9zp+InXEGEECGMmlClRgVX+Z9+W1YZFHR9ePILU/JnSrsT7wtmHi5rg64zDpe4WJe3BY7k7acjRCHdm+VGJBUtFqHNJpPnfR0DbnrZwRyA/Li+IMvQVnCgCFLJh6VFeeO6jEC9pZeo9Hovb5QgXpAZ9pCemN4pJWPPumEKKFWC1Xfv0bo2e2raYNypKkg3EgYdiB4s2U1GdKCa7xkHLJ3C9tFF8Jfmp6qtveHCGmXCpTHOeXJNgf9lIEVF9xtxi1IRzrz6UAldjDsuyf88hBo/tnQR7FEtVxDG2CAxNX+GfkwY/G6LFSLdVACt6EwpgnHjmGyweiUnwIDAQAB";
            }
            boolean zA = a(a(str3), str, str2);
            if (!zA) {
                Log.w("Security", "signature does not match data.");
                return null;
            }
            z = zA;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            long jOptLong = jSONObject.optLong("nonce");
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("orders");
            if (jSONArrayOptJSONArray == null) {
                length = 0;
            } else {
                length = jSONArrayOptJSONArray.length();
            }
            if (!b(jOptLong)) {
                Log.w("Security", "Nonce not found: " + jOptLong);
                return null;
            }
            ArrayList<o> arrayList = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                try {
                    JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i);
                    b bVarA = b.a(jSONObject2.getInt("purchaseState"));
                    String string = jSONObject2.getString("productId");
                    long j = jSONObject2.getLong("purchaseTime");
                    String strOptString = jSONObject2.optString("orderId", "");
                    String string2 = null;
                    if (jSONObject2.has("notificationId")) {
                        string2 = jSONObject2.getString("notificationId");
                    }
                    String strOptString2 = jSONObject2.optString("developerPayload", null);
                    if (bVarA != b.PURCHASED || z) {
                        arrayList.add(new o(bVarA, string2, string, strOptString, j, strOptString2));
                    }
                } catch (JSONException e) {
                    Log.e("Security", "JSON exception: ", e);
                    return null;
                }
            }
            a(jOptLong);
            return arrayList;
        } catch (JSONException e2) {
            return null;
        }
    }

    public static PublicKey a(String str) {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(com.topfreegames.bikerace.m.c.a(str)));
        } catch (com.topfreegames.bikerace.m.d e) {
            Log.e("Security", "Base64 decoding failed.");
            throw new IllegalArgumentException(e);
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException(e2);
        } catch (InvalidKeySpecException e3) {
            Log.e("Security", "Invalid key specification.");
            throw new IllegalArgumentException(e3);
        }
    }

    public static boolean a(PublicKey publicKey, String str, String str2) {
        try {
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initVerify(publicKey);
            signature.update(str.getBytes());
            if (!signature.verify(com.topfreegames.bikerace.m.c.a(str2))) {
                Log.e("Security", "Signature verification failed.");
                return false;
            }
            return true;
        } catch (com.topfreegames.bikerace.m.d e) {
            Log.e("Security", "Base64 decoding failed.");
            return false;
        } catch (InvalidKeyException e2) {
            Log.e("Security", "Invalid key specification.");
            return false;
        } catch (NoSuchAlgorithmException e3) {
            Log.e("Security", "NoSuchAlgorithmException.");
            return false;
        } catch (SignatureException e4) {
            Log.e("Security", "Signature exception.");
            return false;
        }
    }
}
