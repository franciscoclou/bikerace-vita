package com.amazonaws.a;

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

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected static final com.amazonaws.f.e f75a = new com.amazonaws.f.e();
    private static final Log c = LogFactory.getLog(a.class);
    private String b;

    private String c(com.amazonaws.j<?> jVar) {
        StringBuilder sb = new StringBuilder();
        sb.append("SignedHeaders=");
        boolean z = true;
        Iterator<String> it = a(jVar).iterator();
        while (true) {
            boolean z2 = z;
            if (!it.hasNext()) {
                return sb.toString();
            }
            String next = it.next();
            if (!z2) {
                sb.append(";");
            }
            sb.append(next);
            z = false;
        }
    }

    protected List<String> a(com.amazonaws.j<?> jVar) {
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

    @Override // com.amazonaws.a.f
    protected void addSessionCredentials(com.amazonaws.j<?> jVar, e eVar) {
        jVar.a("x-amz-security-token", eVar.c());
    }

    protected String b(com.amazonaws.j<?> jVar) {
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

    @Override // com.amazonaws.a.o
    public void sign(com.amazonaws.j<?> jVar, b bVar) {
        if (bVar instanceof g) {
            return;
        }
        b bVarSanitizeCredentials = sanitizeCredentials(bVar);
        p pVar = p.HmacSHA256;
        UUID.randomUUID().toString();
        String strB = f75a.b(new Date());
        if (this.b != null) {
            strB = this.b;
        }
        jVar.a("Date", strB);
        jVar.a("X-Amz-Date", strB);
        String host = jVar.f().getHost();
        if (com.amazonaws.f.f.a(jVar.f())) {
            host = host + ":" + jVar.f().getPort();
        }
        jVar.a("Host", host);
        if (bVarSanitizeCredentials instanceof e) {
            addSessionCredentials(jVar, (e) bVarSanitizeCredentials);
        }
        String str = jVar.e().toString() + "\n" + getCanonicalizedResourcePath(jVar.c()) + "\n" + getCanonicalizedQueryString(jVar.d()) + "\n" + b(jVar) + "\n" + getRequestPayloadWithoutQueryParams(jVar);
        byte[] bArrHash = hash(str);
        c.debug("Calculated StringToSign: " + str);
        String strSignAndBase64Encode = signAndBase64Encode(bArrHash, bVarSanitizeCredentials.b(), pVar);
        StringBuilder sb = new StringBuilder();
        sb.append("AWS3").append(" ");
        sb.append("AWSAccessKeyId=" + bVarSanitizeCredentials.a() + ",");
        sb.append("Algorithm=" + pVar.toString() + ",");
        sb.append(c(jVar) + ",");
        sb.append("Signature=" + strSignAndBase64Encode);
        jVar.a("X-Amzn-Authorization", sb.toString());
    }
}
