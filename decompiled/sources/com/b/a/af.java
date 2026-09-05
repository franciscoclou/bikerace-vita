package com.b.a;

import com.b.a.a.ck;
import com.b.a.a.cm;
import com.facebook.AppEventsConstants;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class af {
    private final Object d = new Object();
    private final z e;
    private Thread f;
    private static final FilenameFilter b = new ag();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final Map<String, String> f318a = Collections.singletonMap("X-CRASHLYTICS-INVALID-SESSION", AppEventsConstants.EVENT_PARAM_VALUE_YES);
    private static final short[] c = {10, 20, 30, 60, 120, 300};

    static /* synthetic */ Thread a(af afVar, Thread thread) {
        afVar.f = null;
        return null;
    }

    public af(z zVar) {
        if (zVar == null) {
            throw new IllegalArgumentException("createReportCall must not be null.");
        }
        this.e = zVar;
    }

    public final synchronized void a(float f) {
        if (this.f == null) {
            this.f = new Thread(new ah(this, f), "Crashlytics Report Uploader");
            this.f.start();
        }
    }

    final boolean a(ad adVar) {
        boolean z = false;
        synchronized (this.d) {
            try {
                boolean zA = this.e.a(new y(ck.a(cm.a().w(), cm.a().g()), adVar));
                cm.a().b().b("Crashlytics", "Crashlytics report upload " + (zA ? "complete: " : "FAILED: ") + adVar.b());
                if (zA) {
                    adVar.a();
                    z = true;
                }
            } catch (Exception e) {
                cm.a().b().a("Crashlytics", "Error occurred sending report " + adVar, e);
            }
        }
        return z;
    }

    final List<ad> a() {
        File[] fileArrListFiles;
        cm.a().b().a("Crashlytics", "Checking for crash reports...");
        synchronized (this.d) {
            fileArrListFiles = cm.a().i().listFiles(b);
        }
        LinkedList linkedList = new LinkedList();
        for (File file : fileArrListFiles) {
            cm.a().b().a("Crashlytics", "Found crash report " + file.getPath());
            linkedList.add(new ad(file));
        }
        if (linkedList.size() == 0) {
            cm.a().b().a("Crashlytics", "No reports found.");
        }
        return linkedList;
    }
}
