package com.b.a.a;

import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class g extends y implements m {
    public g(String str, String str2, bu buVar) {
        super(str, str2, buVar, bw.POST);
    }

    @Override // com.b.a.a.m
    public final boolean a(String str, List<File> list) throws Throwable {
        bx bxVarA = b().a("X-CRASHLYTICS-API-CLIENT-TYPE", "android").a("X-CRASHLYTICS-API-CLIENT-VERSION", cm.a().f()).a("X-CRASHLYTICS-API-KEY", str);
        int i = 0;
        for (File file : list) {
            ba.c("Adding analytics session file " + file.getName() + " to multipart POST");
            bxVarA.a("session_analytics_file_" + i, file.getName(), "application/vnd.crashlytics.android.events", file);
            i++;
        }
        ba.c("Sending " + list.size() + " analytics files to " + a());
        int iB = bxVarA.b();
        ba.c("Response code for analytics file send is " + iB);
        return ck.a(iB) == 0;
    }
}
