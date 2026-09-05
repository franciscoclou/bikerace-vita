package com.topfreegames.d.a;

import com.amazonaws.a.g;
import com.amazonaws.a.p;
import com.amazonaws.f.e;
import com.amazonaws.f.f;
import com.amazonaws.j;
import com.topfreegames.bikerace.bm;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* JADX INFO: compiled from: NTPAWS3Signer.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class a extends com.amazonaws.a.a {
    protected static final e b = new e();
    private static final Log c = LogFactory.getLog(com.amazonaws.a.a.class);

    a() {
    }

    @Override // com.amazonaws.a.a, com.amazonaws.a.o
    public void sign(j<?> jVar, com.amazonaws.a.b bVar) {
        if (!(bVar instanceof g)) {
            com.amazonaws.a.b bVarSanitizeCredentials = sanitizeCredentials(bVar);
            p pVar = p.HmacSHA256;
            UUID.randomUUID().toString();
            String strB = b.b(new Date());
            if (bm.d()) {
                strB = b.b(com.topfreegames.c.a.a());
            }
            jVar.a("Date", strB);
            jVar.a("X-Amz-Date", strB);
            String host = jVar.f().getHost();
            if (f.a(jVar.f())) {
                host = String.valueOf(host) + ":" + jVar.f().getPort();
            }
            jVar.a("Host", host);
            if (bVarSanitizeCredentials instanceof com.amazonaws.a.e) {
                addSessionCredentials(jVar, (com.amazonaws.a.e) bVarSanitizeCredentials);
            }
            String str = String.valueOf(jVar.e().toString()) + "\n" + getCanonicalizedResourcePath(jVar.c()) + "\n" + getCanonicalizedQueryString(jVar.d()) + "\n" + b(jVar) + "\n" + getRequestPayloadWithoutQueryParams(jVar);
            byte[] bArrHash = hash(str);
            c.debug("Calculated StringToSign: " + str);
            String strSignAndBase64Encode = signAndBase64Encode(bArrHash, bVarSanitizeCredentials.b(), pVar);
            StringBuilder sb = new StringBuilder();
            sb.append("AWS3").append(" ");
            sb.append("AWSAccessKeyId=" + bVarSanitizeCredentials.a() + ",");
            sb.append("Algorithm=" + pVar.toString() + ",");
            sb.append(String.valueOf(c(jVar)) + ",");
            sb.append("Signature=" + strSignAndBase64Encode);
            jVar.a("X-Amzn-Authorization", sb.toString());
        }
    }

    private String c(j<?> jVar) {
        StringBuilder sb = new StringBuilder();
        sb.append("SignedHeaders=");
        boolean z = true;
        Iterator<String> it = a(jVar).iterator();
        while (true) {
            boolean z2 = z;
            if (it.hasNext()) {
                String next = it.next();
                if (!z2) {
                    sb.append(";");
                }
                sb.append(next);
                z = false;
            } else {
                return sb.toString();
            }
        }
    }

    @Override // com.amazonaws.a.a
    protected List<String> a(j<?> jVar) {
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<String, String>> it = jVar.b().entrySet().iterator();
        while (it.hasNext()) {
            String key = it.next().getKey();
            String lowerCase = key.toLowerCase();
            if (lowerCase.startsWith("x-amz") || lowerCase.equals("host")) {
                arrayList.add(key);
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    @Override // com.amazonaws.a.a
    protected String b(j<?> jVar) {
        List<String> listA = a(jVar);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= listA.size()) {
                break;
            }
            listA.set(i2, listA.get(i2).toLowerCase());
            i = i2 + 1;
        }
        TreeMap treeMap = new TreeMap();
        for (Map.Entry<String, String> entry : jVar.b().entrySet()) {
            if (listA.contains(entry.getKey().toLowerCase())) {
                treeMap.put(entry.getKey().toLowerCase(), entry.getValue());
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry2 : treeMap.entrySet()) {
            sb.append(((String) entry2.getKey()).toLowerCase()).append(":").append((String) entry2.getValue()).append("\n");
        }
        return sb.toString();
    }

    @Override // com.amazonaws.a.a, com.amazonaws.a.f
    protected void addSessionCredentials(j<?> jVar, com.amazonaws.a.e eVar) {
        jVar.a("x-amz-security-token", eVar.c());
    }
}
