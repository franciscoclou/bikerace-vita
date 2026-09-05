package com.topfreegames.d.b;

import com.amazonaws.a.e;
import com.amazonaws.a.g;
import com.amazonaws.a.m;
import com.amazonaws.a.n;
import com.amazonaws.a.p;
import com.amazonaws.j;
import com.topfreegames.bikerace.bm;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

/* JADX INFO: compiled from: NTPQueryStringBuilder.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class a extends m {
    a() {
    }

    @Override // com.amazonaws.a.m
    public void a(j<?> jVar, n nVar, p pVar, com.amazonaws.a.b bVar) {
        String strA;
        if (!(bVar instanceof g)) {
            com.amazonaws.a.b bVarSanitizeCredentials = sanitizeCredentials(bVar);
            jVar.b("AWSAccessKeyId", bVarSanitizeCredentials.a());
            jVar.b("SignatureVersion", nVar.toString());
            jVar.b("Timestamp", a());
            if (bVarSanitizeCredentials instanceof e) {
                addSessionCredentials(jVar, (e) bVarSanitizeCredentials);
            }
            if (nVar.equals(n.V1)) {
                strA = a(jVar.d());
            } else if (nVar.equals(n.V2)) {
                jVar.b("SignatureMethod", pVar.toString());
                strA = a(jVar);
            } else {
                throw new com.amazonaws.a("Invalid Signature Version specified");
            }
            jVar.b("Signature", signAndBase64Encode(strA, bVarSanitizeCredentials.b(), pVar));
        }
    }

    private String a(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        treeMap.putAll(map);
        for (String str : treeMap.keySet()) {
            sb.append(str);
            sb.append((String) treeMap.get(str));
        }
        return sb.toString();
    }

    private String a(j<?> jVar) {
        URI uriF = jVar.f();
        Map<String, String> mapD = jVar.d();
        StringBuilder sb = new StringBuilder();
        sb.append("POST").append("\n");
        sb.append(getCanonicalizedEndpoint(uriF)).append("\n");
        sb.append(b(jVar)).append("\n");
        sb.append(getCanonicalizedQueryString(mapD));
        return sb.toString();
    }

    private String b(j<?> jVar) {
        String str = "";
        if (jVar.f().getPath() != null) {
            str = String.valueOf("") + jVar.f().getPath();
        }
        if (jVar.c() != null) {
            if (str.length() > 0 && !str.endsWith("/") && !jVar.c().startsWith("/")) {
                str = String.valueOf(str) + "/";
            }
            str = String.valueOf(str) + jVar.c();
        }
        if (!str.startsWith("/")) {
            str = "/" + str;
        }
        if (str.startsWith("//")) {
            return str.substring(1);
        }
        return str;
    }

    private String a() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = new Date();
        if (bm.d()) {
            date = com.topfreegames.c.a.a();
        }
        return simpleDateFormat.format(date);
    }
}
