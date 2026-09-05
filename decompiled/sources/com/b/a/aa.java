package com.b.a;

import com.b.a.a.bu;
import com.b.a.a.bw;
import com.b.a.a.bx;
import com.b.a.a.ck;
import com.b.a.a.cm;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class aa extends com.b.a.a.y implements z {
    public aa(String str, String str2, bu buVar) {
        super(str, str2, buVar, bw.POST);
    }

    @Override // com.b.a.z
    public final boolean a(y yVar) {
        bx bxVar;
        bx bxVarA = b().a("X-CRASHLYTICS-API-KEY", yVar.f352a).a("X-CRASHLYTICS-API-CLIENT-TYPE", "android").a("X-CRASHLYTICS-API-CLIENT-VERSION", d.a().f());
        Iterator<Map.Entry<String, String>> it = yVar.b.e().entrySet().iterator();
        while (true) {
            bxVar = bxVarA;
            if (!it.hasNext()) {
                break;
            }
            bxVarA = bxVar.a(it.next());
        }
        ad adVar = yVar.b;
        bx bxVarB = bxVar.a("report[file]", adVar.b(), "application/octet-stream", adVar.d()).b("report[identifier]", adVar.c());
        cm.a().b().a("Crashlytics", "Sending report to: " + a());
        int iB = bxVarB.b();
        cm.a().b().a("Crashlytics", "Create report request ID: " + bxVarB.a("X-REQUEST-ID"));
        cm.a().b().a("Crashlytics", "Result was: " + iB);
        return ck.a(iB) == 0;
    }
}
