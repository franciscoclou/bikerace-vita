package com.b.a;

import android.content.res.Resources;
import com.b.a.a.bu;
import com.b.a.a.bw;
import com.b.a.a.bx;
import com.b.a.a.ck;
import com.b.a.a.cm;
import java.io.InputStream;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
abstract class ae extends com.b.a.a.y {
    public ae(String str, String str2, bu buVar, bw bwVar) {
        super(str, str2, buVar, bwVar);
    }

    public final boolean a(ak akVar) {
        bx bxVarA = a(b().a("X-CRASHLYTICS-API-KEY", akVar.f320a).a("X-CRASHLYTICS-API-CLIENT-TYPE", "android").a("X-CRASHLYTICS-API-CLIENT-VERSION", cm.a().f()), akVar);
        cm.a().b().a("Crashlytics", "Sending app info to " + a());
        if (akVar.j != null) {
            cm.a().b().a("Crashlytics", "App icon hash is " + akVar.j.f316a);
            cm.a().b().a("Crashlytics", "App icon size is " + akVar.j.c + "x" + akVar.j.d);
        }
        int iB = bxVarA.b();
        cm.a().b().a("Crashlytics", ("POST".equals(bxVarA.d()) ? "Create" : "Update") + " app request ID: " + bxVarA.a("X-REQUEST-ID"));
        cm.a().b().a("Crashlytics", "Result was " + iB);
        return ck.a(iB) == 0;
    }

    private static bx a(bx bxVar, ak akVar) {
        bx bxVarB = bxVar.b("app[identifier]", akVar.b).b("app[name]", akVar.f).b("app[display_version]", akVar.c).b("app[build_version]", akVar.d).a("app[source]", Integer.valueOf(akVar.g)).b("app[minimum_sdk_version]", akVar.h).b("app[built_sdk_version]", akVar.i);
        if (!com.b.a.a.ba.e(akVar.e)) {
            bxVarB.b("app[instance_identifier]", akVar.e);
        }
        if (akVar.j != null) {
            InputStream inputStreamOpenRawResource = null;
            try {
                inputStreamOpenRawResource = cm.a().w().getResources().openRawResource(akVar.j.b);
                bxVarB.b("app[icon][hash]", akVar.j.f316a).a("app[icon][data]", "icon.png", "application/octet-stream", inputStreamOpenRawResource).a("app[icon][width]", Integer.valueOf(akVar.j.c)).a("app[icon][height]", Integer.valueOf(akVar.j.d));
            } catch (Resources.NotFoundException e) {
                cm.a().b().a("Crashlytics", "Failed to find app icon with resource ID: " + akVar.j.b, e);
            } finally {
                com.b.a.a.ba.a(inputStreamOpenRawResource, "Failed to close app icon InputStream.");
            }
        }
        return bxVarB;
    }
}
