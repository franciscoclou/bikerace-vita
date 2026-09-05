package com.amazonaws.a;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class m extends f implements o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Date f81a;

    private String a() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return this.f81a != null ? simpleDateFormat.format(this.f81a) : simpleDateFormat.format(new Date());
    }

    private String a(com.amazonaws.j<?> jVar) {
        URI uriF = jVar.f();
        Map<String, String> mapD = jVar.d();
        StringBuilder sb = new StringBuilder();
        sb.append("POST").append("\n");
        sb.append(getCanonicalizedEndpoint(uriF)).append("\n");
        sb.append(b(jVar)).append("\n");
        sb.append(getCanonicalizedQueryString(mapD));
        return sb.toString();
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

    private String b(com.amazonaws.j<?> jVar) {
        String str = jVar.f().getPath() != null ? "" + jVar.f().getPath() : "";
        if (jVar.c() != null) {
            if (str.length() > 0 && !str.endsWith("/") && !jVar.c().startsWith("/")) {
                str = str + "/";
            }
            str = str + jVar.c();
        }
        if (!str.startsWith("/")) {
            str = "/" + str;
        }
        return str.startsWith("//") ? str.substring(1) : str;
    }

    public void a(com.amazonaws.j<?> jVar, n nVar, p pVar, b bVar) {
        String strA;
        if (bVar instanceof g) {
            return;
        }
        b bVarSanitizeCredentials = sanitizeCredentials(bVar);
        jVar.b("AWSAccessKeyId", bVarSanitizeCredentials.a());
        jVar.b("SignatureVersion", nVar.toString());
        jVar.b("Timestamp", a());
        if (bVarSanitizeCredentials instanceof e) {
            addSessionCredentials(jVar, (e) bVarSanitizeCredentials);
        }
        if (nVar.equals(n.V1)) {
            strA = a(jVar.d());
        } else {
            if (!nVar.equals(n.V2)) {
                throw new com.amazonaws.a("Invalid Signature Version specified");
            }
            jVar.b("SignatureMethod", pVar.toString());
            strA = a(jVar);
        }
        jVar.b("Signature", signAndBase64Encode(strA, bVarSanitizeCredentials.b(), pVar));
    }

    @Override // com.amazonaws.a.f
    protected void addSessionCredentials(com.amazonaws.j<?> jVar, e eVar) {
        jVar.b("SecurityToken", eVar.c());
    }

    @Override // com.amazonaws.a.o
    public void sign(com.amazonaws.j<?> jVar, b bVar) {
        a(jVar, n.V2, p.HmacSHA256, bVar);
    }
}
