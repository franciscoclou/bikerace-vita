package org.c.c;

import java.util.Map;

/* JADX INFO: compiled from: HeaderExtractorImpl.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class e implements d {
    @Override // org.c.c.d
    public String a(org.c.d.c cVar) {
        b(cVar);
        Map<String, String> mapA = cVar.a();
        StringBuilder sb = new StringBuilder(mapA.size() * 20);
        sb.append("OAuth ");
        for (Map.Entry<String, String> entry : mapA.entrySet()) {
            if (sb.length() > "OAuth ".length()) {
                sb.append(", ");
            }
            sb.append(String.format("%s=\"%s\"", entry.getKey(), org.c.g.b.a(entry.getValue())));
        }
        if (cVar.b() != null && !cVar.b().isEmpty()) {
            sb.append(", ");
            sb.append(String.format("%s=\"%s\"", "realm", cVar.b()));
        }
        return sb.toString();
    }

    private void b(org.c.d.c cVar) {
        org.c.g.c.a(cVar, "Cannot extract a header from a null object");
        if (cVar.a() == null || cVar.a().size() <= 0) {
            throw new org.c.b.c(cVar);
        }
    }
}
