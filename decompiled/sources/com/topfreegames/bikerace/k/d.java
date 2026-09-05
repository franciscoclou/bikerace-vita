package com.topfreegames.bikerace.k;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.c.d.i;
import org.c.d.k;

/* JADX INFO: compiled from: RemoteData.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class d extends org.c.e.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Pattern f1279a = Pattern.compile("mac=\"(\\S+)\"");
    private static final Pattern b = Pattern.compile("hash=\"(\\S+)\"");

    private d() {
    }

    /* synthetic */ d(d dVar) {
        this();
    }

    @Override // org.c.e.d
    public boolean a(k kVar, String str, org.c.d.c cVar, i iVar, org.c.f.e eVar) {
        String strA = a(iVar);
        String strB = b(iVar);
        String strC = c(iVar);
        String str2 = cVar.a().get("oauth_signature");
        String strA2 = eVar.a(strC, kVar.b());
        return strA.equals(eVar.a(new StringBuilder(String.valueOf(str2)).append("&").append(strA2).toString(), kVar.b())) && strB.equals(strA2);
    }

    private static String a(i iVar) {
        return a(iVar, f1279a);
    }

    private static String b(i iVar) {
        return a(iVar, b);
    }

    private static String a(i iVar, Pattern pattern) {
        Matcher matcher = pattern.matcher(iVar.a("Server-Authorization"));
        return (!matcher.find() || matcher.groupCount() < 1) ? "" : matcher.group(1).trim();
    }

    private static String c(i iVar) {
        String lowerCase;
        String strA = iVar.a("Content-Type");
        if (strA == null) {
            lowerCase = "";
        } else {
            lowerCase = strA.split(";")[0].trim().toLowerCase();
        }
        return String.valueOf(lowerCase) + "\n" + iVar.b();
    }
}
