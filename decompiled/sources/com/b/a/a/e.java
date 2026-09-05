package com.b.a.a;

import android.annotation.TargetApi;
import android.app.Application;
import com.amazonaws.javax.xml.stream.XMLStreamConstants;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
@TargetApi(XMLStreamConstants.NOTATION_DECLARATION)
final class e extends n {
    private final Application b;
    private final Application.ActivityLifecycleCallbacks c;

    public e(Application application, String str, String str2, String str3, String str4, String str5, String str6, String str7, j jVar, bu buVar) {
        this(application, str, str2, str3, str4, str5, str6, str7, jVar, bg.b("Crashlytics Trace Manager"), buVar);
    }

    private e(Application application, String str, String str2, String str3, String str4, String str5, String str6, String str7, j jVar, ScheduledExecutorService scheduledExecutorService, bu buVar) {
        super(str, str2, str3, str4, str5, str6, str7, jVar, scheduledExecutorService, buVar);
        this.c = new f(this);
        this.b = application;
        ba.c("Registering activity lifecycle callbacks for session analytics.");
        application.registerActivityLifecycleCallbacks(this.c);
    }

    @Override // com.b.a.a.n
    final void a() {
        ba.c("Unregistering activity lifecycle callbacks for session analytics");
        this.b.unregisterActivityLifecycleCallbacks(this.c);
        super.a();
    }
}
