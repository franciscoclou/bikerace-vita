package com.b.a;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import com.b.a.a.bg;
import com.b.a.a.bh;
import com.b.a.a.bn;
import com.b.a.a.bo;
import com.b.a.a.bp;
import com.b.a.a.ck;
import com.b.a.a.cm;
import com.facebook.AppEventsConstants;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.Flushable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class bc implements Thread.UncaughtExceptionHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final FilenameFilter f335a = new bd();
    private static Comparator<File> b = new k();
    private static Comparator<File> c = new m();
    private static final Pattern d;
    private static final Map<String, String> e;
    private static final am f;
    private final AtomicInteger g;
    private final AtomicBoolean h;
    private final int i;
    private final Thread.UncaughtExceptionHandler j;
    private final File k;
    private final File l;
    private final AtomicBoolean m;
    private final String n;
    private final BroadcastReceiver o;
    private final BroadcastReceiver p;
    private final am q;
    private final am r;
    private final ExecutorService s;
    private ActivityManager.RunningAppProcessInfo t;
    private bp u;
    private boolean v;
    private Thread[] w;
    private List<StackTraceElement[]> x;
    private StackTraceElement[] y;

    static /* synthetic */ void a(bc bcVar, Date date, Thread thread, Throwable th) throws Throwable {
        ao aoVar;
        ao aoVar2;
        aq aqVarA = null;
        try {
            try {
                new File(bcVar.k, "crash_marker").createNewFile();
                String strM = bcVar.m();
                if (strM != null) {
                    d.b(strM);
                    aoVar = new ao(bcVar.k, strM + "SessionCrash");
                    try {
                        aqVarA = aq.a(aoVar);
                        bcVar.a(date, aqVarA, thread, th, "crash", true);
                        aoVar2 = aoVar;
                    } catch (Exception e2) {
                        e = e2;
                        cm.a().b().a("Crashlytics", "An error occurred in the fatal exception logger", e);
                        bcVar.a(e, aoVar);
                        com.b.a.a.ba.a(aqVarA, "Failed to flush to session begin file.");
                        com.b.a.a.ba.a((Closeable) aoVar, "Failed to close fatal exception file output stream.");
                    }
                } else {
                    cm.a().b().a("Crashlytics", "Tried to write a fatal exception while no session was open.", (Throwable) null);
                    aoVar2 = null;
                }
                com.b.a.a.ba.a(aqVarA, "Failed to flush to session begin file.");
                com.b.a.a.ba.a((Closeable) aoVar2, "Failed to close fatal exception file output stream.");
            } catch (Throwable th2) {
                th = th2;
                com.b.a.a.ba.a((Flushable) null, "Failed to flush to session begin file.");
                com.b.a.a.ba.a((Closeable) null, "Failed to close fatal exception file output stream.");
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            aoVar = null;
        } catch (Throwable th3) {
            th = th3;
            com.b.a.a.ba.a((Flushable) null, "Failed to flush to session begin file.");
            com.b.a.a.ba.a((Closeable) null, "Failed to close fatal exception file output stream.");
            throw th;
        }
        bcVar.l();
        bcVar.k();
        ak.a(bcVar.k, f335a, 4, c);
        if (d.a().m()) {
            return;
        }
        bcVar.o();
    }

    static /* synthetic */ void b(bc bcVar, Date date, Thread thread, Throwable th) throws Throwable {
        ao aoVar;
        aq aqVarA = null;
        String strM = bcVar.m();
        if (strM == null) {
            cm.a().b().a("Crashlytics", "Tried to write a non-fatal exception while no session was open.", (Throwable) null);
            return;
        }
        d.a(strM);
        try {
            cm.a().b().a("Crashlytics", "Crashlytics is logging non-fatal exception \"" + th + "\" from thread " + thread.getName());
            ao aoVar2 = new ao(bcVar.k, strM + "SessionEvent" + com.b.a.a.ba.a(bcVar.g.getAndIncrement()));
            try {
                aqVarA = aq.a(aoVar2);
                bcVar.a(date, aqVarA, thread, th, "error", false);
                com.b.a.a.ba.a(aqVarA, "Failed to flush to non-fatal file.");
                com.b.a.a.ba.a((Closeable) aoVar2, "Failed to close non-fatal file output stream.");
            } catch (Exception e2) {
                e = e2;
                aoVar = aoVar2;
                try {
                    cm.a().b().a("Crashlytics", "An error occurred in the non-fatal exception logger", e);
                    bcVar.a(e, aoVar);
                    com.b.a.a.ba.a(aqVarA, "Failed to flush to non-fatal file.");
                    com.b.a.a.ba.a((Closeable) aoVar, "Failed to close non-fatal file output stream.");
                } catch (Throwable th2) {
                    th = th2;
                    com.b.a.a.ba.a(aqVarA, "Failed to flush to non-fatal file.");
                    com.b.a.a.ba.a((Closeable) aoVar, "Failed to close non-fatal file output stream.");
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                aoVar = aoVar2;
                com.b.a.a.ba.a(aqVarA, "Failed to flush to non-fatal file.");
                com.b.a.a.ba.a((Closeable) aoVar, "Failed to close non-fatal file output stream.");
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            aoVar = null;
        } catch (Throwable th4) {
            th = th4;
            aoVar = null;
        }
        try {
            bcVar.a(strM, 64);
        } catch (Exception e4) {
            cm.a().b().a("Crashlytics", "An error occurred when trimming non-fatal files.", e4);
        }
    }

    static {
        new n();
        d = Pattern.compile("([\\d|A-Z|a-z]{12}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{12}).+");
        e = Collections.singletonMap("X-CRASHLYTICS-SEND-FLAGS", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        f = am.a(AppEventsConstants.EVENT_PARAM_VALUE_NO);
    }

    public bc(Thread.UncaughtExceptionHandler uncaughtExceptionHandler, e eVar, String str) {
        this(uncaughtExceptionHandler, eVar, bg.a("Crashlytics Exception Handler"), str);
    }

    private bc(Thread.UncaughtExceptionHandler uncaughtExceptionHandler, e eVar, ExecutorService executorService, String str) {
        this.g = new AtomicInteger(0);
        this.h = new AtomicBoolean(false);
        this.j = uncaughtExceptionHandler;
        this.s = executorService;
        this.m = new AtomicBoolean(false);
        this.k = cm.a().i();
        this.l = new File(this.k, "initialization_marker");
        this.n = String.format(Locale.US, "Crashlytics Android SDK/%s", d.a().f());
        this.i = 8;
        cm.a().b().a("Crashlytics", "Checking for previous crash marker.");
        File file = new File(cm.a().i(), "crash_marker");
        if (file.exists()) {
            file.delete();
            if (eVar != null) {
                try {
                    eVar.a();
                } catch (Exception e2) {
                    cm.a().b().a("Crashlytics", "Exception thrown by CrashlyticsListener while notifying of previous crash.", e2);
                }
            }
        }
        this.q = am.a(d.g());
        this.r = str == null ? null : am.a(str.replace("-", ""));
        this.p = new o(this);
        IntentFilter intentFilter = new IntentFilter("android.intent.action.ACTION_POWER_CONNECTED");
        this.o = new p(this);
        IntentFilter intentFilter2 = new IntentFilter("android.intent.action.ACTION_POWER_DISCONNECTED");
        d.a().w().registerReceiver(this.p, intentFilter);
        d.a().w().registerReceiver(this.o, intentFilter2);
        this.h.set(true);
    }

    final boolean a() {
        return this.m.get();
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final synchronized void uncaughtException(Thread thread, Throwable th) {
        this.m.set(true);
        try {
            try {
                cm.a().b().a("Crashlytics", "Crashlytics is handling uncaught exception \"" + th + "\" from thread " + thread.getName());
                if (!this.h.getAndSet(true)) {
                    cm.a().b().a("Crashlytics", "Unregistering power receivers.");
                    d.a().w().unregisterReceiver(this.p);
                    d.a().w().unregisterReceiver(this.o);
                }
                a(new q(this, new Date(), thread, th));
                cm.a().b().a("Crashlytics", "Crashlytics completed exception processing. Invoking default exception handler.");
                this.j.uncaughtException(thread, th);
                this.m.set(false);
            } catch (Exception e2) {
                cm.a().b().a("Crashlytics", "An error occurred in the uncaught exception handler", e2);
                cm.a().b().a("Crashlytics", "Crashlytics completed exception processing. Invoking default exception handler.");
                this.j.uncaughtException(thread, th);
                this.m.set(false);
            }
        } catch (Throwable th2) {
            cm.a().b().a("Crashlytics", "Crashlytics completed exception processing. Invoking default exception handler.");
            this.j.uncaughtException(thread, th);
            this.m.set(false);
            throw th2;
        }
    }

    final boolean b() {
        return ((Boolean) a(new r(this))).booleanValue();
    }

    final void a(Thread thread, Throwable th) {
        a(new s(this, new Date(), thread, th));
    }

    private am a(bp bpVar) {
        if (bpVar == null) {
            return null;
        }
        int[] iArr = {0};
        byte[] bArr = new byte[bpVar.a()];
        try {
            bpVar.a(new be(this, bArr, iArr));
        } catch (IOException e2) {
            cm.a().b().a("Crashlytics", "A problem occurred while reading the Crashlytics log file.", e2);
        }
        return am.a(bArr, 0, iArr[0]);
    }

    final void c() {
        b(new bf(this));
    }

    final void d() {
        b(new a(this));
    }

    final void e() {
        b(new b(this));
    }

    final boolean f() {
        return ((Boolean) a(new c(this))).booleanValue();
    }

    final boolean g() {
        return n().length > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v13, types: [java.io.Flushable] */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v23, types: [com.b.a.aq, java.io.Flushable] */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v27, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    public void k() throws Throwable {
        ao aoVar;
        aq aqVarA;
        ?? A;
        ao aoVar2;
        ao aoVar3;
        ao aoVar4;
        aq aqVar = null;
         = 0;
        ?? r2 = 0;
        aqVar = null;
        ao aoVar5 = null;
        Date date = new Date();
        String string = new an(d.a().d()).toString();
        cm.a().b().a("Crashlytics", "Opening an new session with ID " + string);
        try {
            aoVar = new ao(cm.a().i(), string + "BeginSession");
            try {
                aqVarA = aq.a(aoVar);
                try {
                    aqVarA.a(1, am.a(this.n));
                    aqVarA.a(2, am.a(string));
                    aqVarA.a(3, date.getTime() / 1000);
                    com.b.a.a.ba.a(aqVarA, "Failed to flush to session begin file.");
                    com.b.a.a.ba.a((Closeable) aoVar, "Failed to close begin session file.");
                    try {
                        aoVar2 = new ao(cm.a().i(), string + "SessionApp");
                        try {
                            A = aq.a(aoVar2);
                            try {
                                am amVarA = am.a(d.g());
                                am amVarA2 = am.a(d.j());
                                am amVarA3 = am.a(d.i());
                                am.a(d.k());
                                am.a(d.a().w().getPackageCodePath());
                                am amVarA4 = am.a(d.a().d().b());
                                int iA = bh.a(d.h()).a();
                                A.g(7, 2);
                                int iB = aq.b(1, amVarA) + 0 + aq.b(2, amVarA2) + aq.b(3, amVarA3);
                                int iP = p();
                                A.b(iB + iP + aq.a(5) + aq.c(iP) + aq.b(6, amVarA4) + aq.e(10, iA));
                                A.a(1, amVarA);
                                A.a(2, amVarA2);
                                A.a(3, amVarA3);
                                A.g(5, 2);
                                A.b(p());
                                A.a(1, ck.a(d.a().w(), false));
                                A.a(6, amVarA4);
                                A.b(10, iA);
                                com.b.a.a.ba.a((Flushable) A, "Failed to flush to session app file.");
                                com.b.a.a.ba.a((Closeable) aoVar2, "Failed to close session app file.");
                                try {
                                    try {
                                        aoVar4 = new ao(cm.a().i(), string + "SessionOS");
                                        try {
                                            aq aqVarA2 = aq.a(aoVar4);
                                            am amVarA5 = am.a(Build.VERSION.RELEASE);
                                            am amVarA6 = am.a(Build.VERSION.CODENAME);
                                            boolean zE = com.b.a.a.ba.e();
                                            aqVarA2.g(8, 2);
                                            aqVarA2.b(aq.e(1, 3) + 0 + aq.b(2, amVarA5) + aq.b(3, amVarA6) + aq.b(4, zE));
                                            aqVarA2.b(1, 3);
                                            aqVarA2.a(2, amVarA5);
                                            aqVarA2.a(3, amVarA6);
                                            aqVarA2.a(4, zE);
                                            com.b.a.a.ba.a(aqVarA2, "Failed to flush to session OS file.");
                                            com.b.a.a.ba.a((Closeable) aoVar4, "Failed to close session OS file.");
                                            c(string);
                                        } catch (Exception e2) {
                                            e = e2;
                                            a(e, aoVar4);
                                            throw e;
                                        }
                                    } catch (Throwable th) {
                                        th = th;
                                        com.b.a.a.ba.a((Flushable) null, "Failed to flush to session OS file.");
                                        com.b.a.a.ba.a((Closeable) A, "Failed to close session OS file.");
                                        throw th;
                                    }
                                } catch (Exception e3) {
                                    e = e3;
                                    aoVar4 = null;
                                } catch (Throwable th2) {
                                    th = th2;
                                    A = 0;
                                    com.b.a.a.ba.a((Flushable) null, "Failed to flush to session OS file.");
                                    com.b.a.a.ba.a((Closeable) A, "Failed to close session OS file.");
                                    throw th;
                                }
                            } catch (Exception e4) {
                                e = e4;
                                r2 = A;
                                aoVar3 = aoVar2;
                                try {
                                    a(e, aoVar3);
                                    throw e;
                                } catch (Throwable th3) {
                                    th = th3;
                                    aoVar2 = aoVar3;
                                    A = r2;
                                    com.b.a.a.ba.a((Flushable) A, "Failed to flush to session app file.");
                                    com.b.a.a.ba.a((Closeable) aoVar2, "Failed to close session app file.");
                                    throw th;
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                com.b.a.a.ba.a((Flushable) A, "Failed to flush to session app file.");
                                com.b.a.a.ba.a((Closeable) aoVar2, "Failed to close session app file.");
                                throw th;
                            }
                        } catch (Exception e5) {
                            e = e5;
                            aoVar3 = aoVar2;
                        } catch (Throwable th5) {
                            th = th5;
                            A = 0;
                        }
                    } catch (Exception e6) {
                        e = e6;
                        aoVar3 = null;
                    } catch (Throwable th6) {
                        th = th6;
                        A = 0;
                        aoVar2 = null;
                    }
                } catch (Exception e7) {
                    e = e7;
                    aoVar5 = aoVar;
                    try {
                        a(e, aoVar5);
                        throw e;
                    } catch (Throwable th7) {
                        th = th7;
                        aoVar = aoVar5;
                        aqVar = aqVarA;
                        com.b.a.a.ba.a(aqVar, "Failed to flush to session begin file.");
                        com.b.a.a.ba.a((Closeable) aoVar, "Failed to close begin session file.");
                        throw th;
                    }
                } catch (Throwable th8) {
                    th = th8;
                    aqVar = aqVarA;
                    com.b.a.a.ba.a(aqVar, "Failed to flush to session begin file.");
                    com.b.a.a.ba.a((Closeable) aoVar, "Failed to close begin session file.");
                    throw th;
                }
            } catch (Exception e8) {
                e = e8;
                aqVarA = null;
                aoVar5 = aoVar;
            } catch (Throwable th9) {
                th = th9;
            }
        } catch (Exception e9) {
            e = e9;
            aqVarA = null;
        } catch (Throwable th10) {
            th = th10;
            aoVar = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() throws Throwable {
        OutputStream aoVar;
        ao aoVar2;
        aq aqVar;
        ao aoVar3;
        File[] fileArrA;
        HashSet hashSet = new HashSet();
        File[] fileArrN = n();
        Arrays.sort(fileArrN, b);
        int iMin = Math.min(8, fileArrN.length);
        for (int i = 0; i < iMin; i++) {
            hashSet.add(a(fileArrN[i]));
        }
        for (File file : a(new u((byte) 0))) {
            String name = file.getName();
            Matcher matcher = d.matcher(name);
            matcher.matches();
            if (!hashSet.contains(matcher.group(1))) {
                cm.a().b().a("Crashlytics", "Trimming open session file: " + name);
                file.delete();
            }
        }
        String strM = m();
        if (strM != null) {
            aq aqVarA = null;
            try {
                aoVar = new ao(this.k, strM + "SessionUser");
                try {
                    try {
                        aqVarA = aq.a(aoVar);
                        String strP = d.a().p();
                        String strR = d.a().r();
                        String strQ = d.a().q();
                        if (strP == null && strR == null && strQ == null) {
                            com.b.a.a.ba.a(aqVarA, "Failed to flush session user file.");
                            com.b.a.a.ba.a((Closeable) aoVar, "Failed to close session user file.");
                        } else {
                            if (strP == null) {
                                strP = "";
                            }
                            am amVarA = am.a(strP);
                            am amVarA2 = strR == null ? null : am.a(strR);
                            am amVarA3 = strQ == null ? null : am.a(strQ);
                            int iB = aq.b(1, amVarA) + 0;
                            if (amVarA2 != null) {
                                iB += aq.b(2, amVarA2);
                            }
                            if (amVarA3 != null) {
                                iB += aq.b(3, amVarA3);
                            }
                            aqVarA.g(6, 2);
                            aqVarA.b(iB);
                            aqVarA.a(1, amVarA);
                            if (amVarA2 != null) {
                                aqVarA.a(2, amVarA2);
                            }
                            if (amVarA3 != null) {
                                aqVarA.a(3, amVarA3);
                            }
                            com.b.a.a.ba.a(aqVarA, "Failed to flush session user file.");
                            com.b.a.a.ba.a((Closeable) aoVar, "Failed to close session user file.");
                        }
                        com.b.a.a.aq aqVarU = d.a().u();
                        if (aqVarU != null) {
                            int i2 = aqVarU.f262a;
                            cm.a().b().a("Crashlytics", "Closing all open sessions.");
                            File[] fileArrN2 = n();
                            if (fileArrN2 != null && fileArrN2.length > 0) {
                                for (File file2 : fileArrN2) {
                                    String strA = a(file2);
                                    cm.a().b().a("Crashlytics", "Closing session: " + strA);
                                    cm.a().b().a("Crashlytics", "Collecting session parts for ID " + strA);
                                    File[] fileArrA2 = a(new v(strA + "SessionCrash"));
                                    boolean z = fileArrA2 != null && fileArrA2.length > 0;
                                    cm.a().b().a("Crashlytics", String.format(Locale.US, "Session %s has fatal exception: %s", strA, Boolean.valueOf(z)));
                                    File[] fileArrA3 = a(new v(strA + "SessionEvent"));
                                    boolean z2 = fileArrA3 != null && fileArrA3.length > 0;
                                    cm.a().b().a("Crashlytics", String.format(Locale.US, "Session %s has non-fatal exceptions: %s", strA, Boolean.valueOf(z2)));
                                    if (z || z2) {
                                        aq aqVar2 = null;
                                        try {
                                            aoVar2 = new ao(this.k, strA);
                                            try {
                                                try {
                                                    aq aqVarA2 = aq.a(aoVar2);
                                                    try {
                                                        cm.a().b().a("Crashlytics", "Collecting SessionStart data for session ID " + strA);
                                                        a(aqVarA2, file2);
                                                        aqVarA2.a(4, new Date().getTime() / 1000);
                                                        aqVarA2.a(5, z);
                                                        a(aqVarA2, strA);
                                                        if (z2) {
                                                            if (fileArrA3.length > i2) {
                                                                cm.a().b().a("Crashlytics", String.format(Locale.US, "Trimming down to %d logged exceptions.", Integer.valueOf(i2)));
                                                                a(strA, i2);
                                                                fileArrA = a(new v(strA + "SessionEvent"));
                                                            } else {
                                                                fileArrA = fileArrA3;
                                                            }
                                                            a(aqVarA2, fileArrA, strA);
                                                        }
                                                        if (z) {
                                                            a(aqVarA2, fileArrA2[0]);
                                                        }
                                                        aqVarA2.a(11, 1);
                                                        aqVarA2.b(12, 3);
                                                        com.b.a.a.ba.a(aqVarA2, "Error flushing session file stream");
                                                        com.b.a.a.ba.a((Closeable) aoVar2, "Failed to close CLS file");
                                                    } catch (Exception e2) {
                                                        e = e2;
                                                        aqVar = aqVarA2;
                                                        aoVar3 = aoVar2;
                                                        try {
                                                            cm.a().b().a("Crashlytics", "Failed to write session file for session ID: " + strA, e);
                                                            a(e, aoVar3);
                                                            com.b.a.a.ba.a(aqVar, "Error flushing session file stream");
                                                            a(aoVar3);
                                                        } catch (Throwable th) {
                                                            th = th;
                                                            aoVar2 = aoVar3;
                                                            aqVar2 = aqVar;
                                                            com.b.a.a.ba.a(aqVar2, "Error flushing session file stream");
                                                            com.b.a.a.ba.a((Closeable) aoVar2, "Failed to close CLS file");
                                                            throw th;
                                                        }
                                                    }
                                                } catch (Exception e3) {
                                                    e = e3;
                                                    aqVar = null;
                                                    aoVar3 = aoVar2;
                                                }
                                            } catch (Throwable th2) {
                                                th = th2;
                                                com.b.a.a.ba.a(aqVar2, "Error flushing session file stream");
                                                com.b.a.a.ba.a((Closeable) aoVar2, "Failed to close CLS file");
                                                throw th;
                                            }
                                        } catch (Exception e4) {
                                            e = e4;
                                            aqVar = null;
                                            aoVar3 = null;
                                        } catch (Throwable th3) {
                                            th = th3;
                                            aoVar2 = null;
                                        }
                                    } else {
                                        cm.a().b().a("Crashlytics", "No events present for session ID " + strA);
                                    }
                                    cm.a().b().a("Crashlytics", "Removing session part files for ID " + strA);
                                    a(strA);
                                }
                                return;
                            }
                            return;
                        }
                        cm.a().b().a("Crashlytics", "No session begin files found.");
                    } catch (Exception e5) {
                        e = e5;
                        a(e, aoVar);
                        throw e;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    com.b.a.a.ba.a(aqVarA, "Failed to flush session user file.");
                    com.b.a.a.ba.a((Closeable) aoVar, "Failed to close session user file.");
                    throw th;
                }
            } catch (Exception e6) {
                e = e6;
                aoVar = null;
            } catch (Throwable th5) {
                th = th5;
                aoVar = null;
                com.b.a.a.ba.a(aqVarA, "Failed to flush session user file.");
                com.b.a.a.ba.a((Closeable) aoVar, "Failed to close session user file.");
                throw th;
            }
        } else {
            cm.a().b().a("Crashlytics", "Unable to close session. Settings are not loaded.");
        }
    }

    private String m() {
        File[] fileArrA = a(new v("BeginSession"));
        Arrays.sort(fileArrA, b);
        if (fileArrA.length > 0) {
            return a(fileArrA[0]);
        }
        return null;
    }

    private static String a(File file) {
        return file.getName().substring(0, 35);
    }

    private static void a(ao aoVar) {
        if (aoVar != null) {
            try {
                aoVar.a();
            } catch (IOException e2) {
                cm.a().b().a("Crashlytics", "Error closing session file stream in the presence of an exception", e2);
            }
        }
    }

    private void a(aq aqVar, File[] fileArr, String str) throws Throwable {
        Arrays.sort(fileArr, com.b.a.a.ba.f270a);
        for (File file : fileArr) {
            try {
                cm.a().b().a("Crashlytics", String.format(Locale.US, "Found Non Fatal for session ID %s in %s ", str, file.getName()));
                a(aqVar, file);
            } catch (Exception e2) {
                cm.a().b().a("Crashlytics", "Error writting non-fatal to session.", e2);
            }
        }
    }

    private void a(aq aqVar, String str) throws Throwable {
        for (String str2 : new String[]{"SessionUser", "SessionApp", "SessionOS", "SessionDevice"}) {
            File[] fileArrA = a(new v(str + str2));
            if (fileArrA.length == 0) {
                cm.a().b().a("Crashlytics", "Can't find " + str2 + " data for session ID " + str, (Throwable) null);
            } else {
                cm.a().b().a("Crashlytics", "Collecting " + str2 + " data for session ID " + str);
                a(aqVar, fileArrA[0]);
            }
        }
    }

    private void a(String str) {
        for (File file : a(new w(str))) {
            file.delete();
        }
    }

    private File[] n() {
        return a(new v("BeginSession"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File[] a(FilenameFilter filenameFilter) {
        File[] fileArrListFiles = this.k.listFiles(filenameFilter);
        return fileArrListFiles == null ? new File[0] : fileArrListFiles;
    }

    private void o() {
        for (File file : a(f335a)) {
            cm.a().b().a("Crashlytics", "Attempting to send crash report at time of crash...");
            new Thread(new g(this, file), "Crashlytics Report Uploader").start();
        }
    }

    private void a(Throwable th, OutputStream outputStream) throws Throwable {
        PrintWriter printWriter;
        if (outputStream != null) {
            try {
                try {
                    printWriter = new PrintWriter(outputStream);
                    try {
                        a(th, printWriter);
                        com.b.a.a.ba.a((Closeable) printWriter, "Failed to close stack trace writer.");
                    } catch (Exception e2) {
                        e = e2;
                        cm.a().b().a("Crashlytics", "Failed to create PrintWriter", e);
                        com.b.a.a.ba.a((Closeable) printWriter, "Failed to close stack trace writer.");
                    }
                } catch (Throwable th2) {
                    th = th2;
                    com.b.a.a.ba.a((Closeable) printWriter, "Failed to close stack trace writer.");
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                printWriter = null;
            } catch (Throwable th3) {
                th = th3;
                printWriter = null;
                com.b.a.a.ba.a((Closeable) printWriter, "Failed to close stack trace writer.");
                throw th;
            }
        }
    }

    private static void a(Throwable th, Writer writer) {
        boolean z = true;
        while (th != null) {
            try {
                String localizedMessage = th.getLocalizedMessage();
                String strReplaceAll = localizedMessage == null ? null : localizedMessage.replaceAll("(\r\n|\n|\f)", " ");
                writer.write((z ? "" : "Caused by: ") + th.getClass().getName() + ": " + (strReplaceAll != null ? strReplaceAll : "") + "\n");
                for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                    writer.write("\tat " + stackTraceElement.toString() + "\n");
                }
                th = th.getCause();
                z = false;
            } catch (Exception e2) {
                cm.a().b().a("Crashlytics", "Could not write stack trace", e2);
                return;
            }
        }
    }

    private static int p() {
        return aq.b(1, am.a(ck.a(d.a().w(), cm.a().g()))) + 0;
    }

    private static am b(String str) {
        if (str == null) {
            return null;
        }
        return am.a(str);
    }

    private void c(String str) throws Throwable {
        aq aqVarA;
        ao aoVar;
        ao aoVar2 = null;
        aq aqVar = null;
        try {
            aoVar = new ao(cm.a().i(), str + "SessionDevice");
            try {
                aqVarA = aq.a(aoVar);
                try {
                    StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
                    int iB = com.b.a.a.ba.b();
                    am amVarB = b(Build.MODEL);
                    am amVarB2 = b(Build.MANUFACTURER);
                    am amVarB3 = b(Build.PRODUCT);
                    int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
                    long jC = com.b.a.a.ba.c();
                    long blockCount = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
                    boolean zD = com.b.a.a.ba.d();
                    bn bnVarD = d.a().d();
                    am amVarA = am.a(bnVarD.e());
                    Map<bo, String> mapF = bnVarD.f();
                    int iF = com.b.a.a.ba.f();
                    aqVarA.g(9, 2);
                    aqVarA.b(a(iB, amVarA, amVarB, iAvailableProcessors, jC, blockCount, zD, mapF, iF, amVarB2, amVarB3));
                    aqVarA.a(1, amVarA);
                    aqVarA.b(3, iB);
                    aqVarA.a(4, amVarB);
                    aqVarA.a(5, iAvailableProcessors);
                    aqVarA.a(6, jC);
                    aqVarA.a(7, blockCount);
                    aqVarA.a(10, zD);
                    for (Map.Entry<bo, String> entry : mapF.entrySet()) {
                        aqVarA.g(11, 2);
                        aqVarA.b(a(entry.getKey(), entry.getValue()));
                        aqVarA.b(1, entry.getKey().f);
                        aqVarA.a(2, am.a(entry.getValue()));
                    }
                    aqVarA.a(12, iF);
                    if (amVarB2 != null) {
                        aqVarA.a(13, amVarB2);
                    }
                    if (amVarB3 != null) {
                        aqVarA.a(14, amVarB3);
                    }
                    com.b.a.a.ba.a(aqVarA, "Failed to flush session device info.");
                    com.b.a.a.ba.a((Closeable) aoVar, "Failed to close session device file.");
                } catch (Exception e2) {
                    e = e2;
                    aqVar = aqVarA;
                    aoVar2 = aoVar;
                    try {
                        a(e, aoVar2);
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        aqVarA = aqVar;
                        aoVar = aoVar2;
                        com.b.a.a.ba.a(aqVarA, "Failed to flush session device info.");
                        com.b.a.a.ba.a((Closeable) aoVar, "Failed to close session device file.");
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    com.b.a.a.ba.a(aqVarA, "Failed to flush session device info.");
                    com.b.a.a.ba.a((Closeable) aoVar, "Failed to close session device file.");
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                aoVar2 = aoVar;
            } catch (Throwable th3) {
                th = th3;
                aqVarA = null;
            }
        } catch (Exception e4) {
            e = e4;
        } catch (Throwable th4) {
            th = th4;
            aqVarA = null;
            aoVar = null;
        }
    }

    private static int a(bo boVar, String str) {
        return aq.e(1, boVar.f) + aq.b(2, am.a(str));
    }

    private int a(int i, am amVar, am amVar2, int i2, long j, long j2, boolean z, Map<bo, String> map, int i3, am amVar3, am amVar4) {
        int i4;
        int iB = (amVar2 == null ? 0 : aq.b(4, amVar2)) + aq.e(3, i) + aq.b(1, amVar) + 0 + aq.d(5, i2) + aq.b(6, j) + aq.b(7, j2) + aq.b(10, z);
        if (map != null) {
            Iterator<Map.Entry<bo, String>> it = map.entrySet().iterator();
            while (true) {
                i4 = iB;
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<bo, String> next = it.next();
                int iA = a(next.getKey(), next.getValue());
                iB = iA + aq.a(11) + aq.c(iA) + i4;
            }
        } else {
            i4 = iB;
        }
        return (amVar4 == null ? 0 : aq.b(14, amVar4)) + i4 + aq.d(12, i3) + (amVar3 == null ? 0 : aq.b(13, amVar3));
    }

    private static void a(aq aqVar, File file) throws Throwable {
        FileInputStream fileInputStream;
        int i;
        if (file.exists()) {
            byte[] bArr = new byte[(int) file.length()];
            try {
                fileInputStream = new FileInputStream(file);
                int i2 = 0;
                while (i2 < bArr.length && (i = fileInputStream.read(bArr, i2, bArr.length - i2)) >= 0) {
                    try {
                        i2 += i;
                    } catch (Throwable th) {
                        th = th;
                        com.b.a.a.ba.a(fileInputStream, "Failed to close file input stream.");
                        throw th;
                    }
                }
                com.b.a.a.ba.a(fileInputStream, "Failed to close file input stream.");
                aqVar.a(bArr);
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = null;
            }
        } else {
            cm.a().b().a("Crashlytics", "Tried to include a file that doesn't exist: " + file.getName(), (Throwable) null);
        }
    }

    private void a(String str, int i) {
        ak.a(this.k, new v(str + "SessionEvent"), i, c);
    }

    private void a(Date date, aq aqVar, Thread thread, Throwable th, String str, boolean z) {
        Map<String, String> treeMap;
        long time = date.getTime() / 1000;
        float fB = com.b.a.a.ba.b(d.a().w());
        int iA = com.b.a.a.ba.a(this.v);
        boolean zC = com.b.a.a.ba.c(d.a().w());
        int i = d.a().w().getResources().getConfiguration().orientation;
        long jC = com.b.a.a.ba.c() - com.b.a.a.ba.a(d.a().w());
        long jB = com.b.a.a.ba.b(Environment.getDataDirectory().getPath());
        this.t = com.b.a.a.ba.a(d.g(), d.a().w());
        this.x = new LinkedList();
        this.y = th.getStackTrace();
        if (z) {
            Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
            this.w = new Thread[allStackTraces.size()];
            int i2 = 0;
            Iterator<Map.Entry<Thread, StackTraceElement[]>> it = allStackTraces.entrySet().iterator();
            while (true) {
                int i3 = i2;
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<Thread, StackTraceElement[]> next = it.next();
                this.w[i3] = next.getKey();
                this.x.add(next.getValue());
                i2 = i3 + 1;
            }
        } else {
            this.w = new Thread[0];
        }
        am amVarA = a(this.u);
        if (amVarA == null) {
            cm.a().b().a("Crashlytics", "No log data to include with this event.");
        }
        com.b.a.a.ba.a(this.u, "There was a problem closing the Crashlytics log file.");
        this.u = null;
        if (!com.b.a.a.ba.a(d.a().w(), "com.crashlytics.CollectCustomKeys", true)) {
            treeMap = new TreeMap<>();
        } else {
            Map<String, String> mapB = d.a().b();
            treeMap = (mapB == null || mapB.size() <= 1) ? mapB : new TreeMap<>(mapB);
        }
        aqVar.g(10, 2);
        int iB = aq.b(1, time) + 0 + aq.b(2, am.a(str));
        int iA2 = a(thread, th, treeMap);
        int iA3 = iB + iA2 + aq.a(3) + aq.c(iA2);
        int iA4 = a(fB, iA, zC, i, jC, jB);
        int iA5 = iA3 + iA4 + aq.a(5) + aq.c(iA4);
        if (amVarA != null) {
            int iB2 = aq.b(1, amVarA);
            iA5 += iB2 + aq.a(6) + aq.c(iB2);
        }
        aqVar.b(iA5);
        aqVar.a(1, time);
        aqVar.a(2, am.a(str));
        aqVar.g(3, 2);
        aqVar.b(a(thread, th, treeMap));
        a(aqVar, thread, th);
        if (treeMap != null && !treeMap.isEmpty()) {
            a(aqVar, treeMap);
        }
        if (this.t != null) {
            aqVar.a(3, this.t.importance != 100);
        }
        aqVar.a(4, d.a().w().getResources().getConfiguration().orientation);
        aqVar.g(5, 2);
        aqVar.b(a(fB, iA, zC, i, jC, jB));
        aqVar.a(1, fB);
        aqVar.c(2, iA);
        aqVar.a(3, zC);
        aqVar.a(4, i);
        aqVar.a(5, jC);
        aqVar.a(6, jB);
        if (amVarA != null) {
            aqVar.g(6, 2);
            aqVar.b(aq.b(1, amVarA));
            aqVar.a(1, amVarA);
        }
    }

    private void a(aq aqVar, Thread thread, Throwable th) {
        aqVar.g(1, 2);
        aqVar.b(b(thread, th));
        a(aqVar, thread, this.y, 4, true);
        int length = this.w.length;
        for (int i = 0; i < length; i++) {
            a(aqVar, this.w[i], this.x.get(i), 0, false);
        }
        a(aqVar, th, 1, 2);
        aqVar.g(3, 2);
        aqVar.b(r());
        aqVar.a(1, f);
        aqVar.a(2, f);
        aqVar.a(3, 0L);
        aqVar.g(4, 2);
        aqVar.b(q());
        aqVar.a(1, 0L);
        aqVar.a(2, 0L);
        aqVar.a(3, this.q);
        if (this.r != null) {
            aqVar.a(4, this.r);
        }
    }

    private void a(aq aqVar, Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            aqVar.g(2, 2);
            aqVar.b(a(entry.getKey(), entry.getValue()));
            aqVar.a(1, am.a(entry.getKey()));
            String value = entry.getValue();
            if (value == null) {
                value = "";
            }
            aqVar.a(2, am.a(value));
        }
    }

    private int q() {
        int iB = aq.b(1, 0L) + 0 + aq.b(2, 0L) + aq.b(3, this.q);
        if (this.r != null) {
            return iB + aq.b(4, this.r);
        }
        return iB;
    }

    private void a(aq aqVar, Throwable th, int i, int i2) {
        int i3 = 0;
        aqVar.g(i2, 2);
        aqVar.b(a(th, 1));
        aqVar.a(1, am.a(th.getClass().getName()));
        String localizedMessage = th.getLocalizedMessage();
        if (localizedMessage != null) {
            aqVar.a(3, am.a(localizedMessage));
        }
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            a(aqVar, 4, stackTraceElement, true);
        }
        Throwable cause = th.getCause();
        if (cause != null) {
            if (i < 8) {
                a(aqVar, cause, i + 1, 6);
                return;
            }
            while (cause != null) {
                cause = cause.getCause();
                i3++;
            }
            aqVar.a(7, i3);
        }
    }

    private int a(Thread thread, StackTraceElement[] stackTraceElementArr, int i, boolean z) {
        int iD = aq.d(2, i) + aq.b(1, am.a(thread.getName()));
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            int iA = a(stackTraceElement, z);
            iD += iA + aq.a(3) + aq.c(iA);
        }
        return iD;
    }

    private void a(aq aqVar, Thread thread, StackTraceElement[] stackTraceElementArr, int i, boolean z) {
        aqVar.g(1, 2);
        aqVar.b(a(thread, stackTraceElementArr, i, z));
        aqVar.a(1, am.a(thread.getName()));
        aqVar.a(2, i);
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            a(aqVar, 3, stackTraceElement, z);
        }
    }

    private static int a(StackTraceElement stackTraceElement, boolean z) {
        int iB;
        if (stackTraceElement.isNativeMethod()) {
            iB = aq.b(1, Math.max(stackTraceElement.getLineNumber(), 0)) + 0;
        } else {
            iB = aq.b(1, 0L) + 0;
        }
        int iB2 = iB + aq.b(2, am.a(stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName()));
        if (stackTraceElement.getFileName() != null) {
            iB2 += aq.b(3, am.a(stackTraceElement.getFileName()));
        }
        return aq.d(5, z ? 2 : 0) + ((stackTraceElement.isNativeMethod() || stackTraceElement.getLineNumber() <= 0) ? iB2 : iB2 + aq.b(4, stackTraceElement.getLineNumber()));
    }

    private void a(aq aqVar, int i, StackTraceElement stackTraceElement, boolean z) {
        aqVar.g(i, 2);
        aqVar.b(a(stackTraceElement, z));
        if (stackTraceElement.isNativeMethod()) {
            aqVar.a(1, Math.max(stackTraceElement.getLineNumber(), 0));
        } else {
            aqVar.a(1, 0L);
        }
        aqVar.a(2, am.a(stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName()));
        if (stackTraceElement.getFileName() != null) {
            aqVar.a(3, am.a(stackTraceElement.getFileName()));
        }
        if (!stackTraceElement.isNativeMethod() && stackTraceElement.getLineNumber() > 0) {
            aqVar.a(4, stackTraceElement.getLineNumber());
        }
        aqVar.a(5, z ? 4 : 0);
    }

    private int a(Thread thread, Throwable th, Map<String, String> map) {
        int iB;
        int iB2 = b(thread, th);
        int iA = iB2 + aq.a(1) + aq.c(iB2) + 0;
        if (map != null) {
            Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
            while (true) {
                iB = iA;
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<String, String> next = it.next();
                int iA2 = a(next.getKey(), next.getValue());
                iA = iA2 + aq.a(2) + aq.c(iA2) + iB;
            }
        } else {
            iB = iA;
        }
        if (this.t != null) {
            iB += aq.b(3, this.t.importance != 100);
        }
        return aq.d(4, d.a().w().getResources().getConfiguration().orientation) + iB;
    }

    private int b(Thread thread, Throwable th) {
        int iA = a(thread, this.y, 4, true);
        int iA2 = iA + aq.a(1) + aq.c(iA) + 0;
        int length = this.w.length;
        int iA3 = iA2;
        for (int i = 0; i < length; i++) {
            int iA4 = a(this.w[i], this.x.get(i), 0, false);
            iA3 += iA4 + aq.a(1) + aq.c(iA4);
        }
        int iA5 = a(th, 1);
        int iA6 = iA5 + aq.a(2) + aq.c(iA5) + iA3;
        int iR = r();
        int iA7 = iA6 + iR + aq.a(3) + aq.c(iR);
        int iQ = q();
        return iA7 + iQ + aq.a(3) + aq.c(iQ);
    }

    private static int a(String str, String str2) {
        int iB = aq.b(1, am.a(str));
        if (str2 == null) {
            str2 = "";
        }
        return iB + aq.b(2, am.a(str2));
    }

    private static int a(float f2, int i, boolean z, int i2, long j, long j2) {
        return aq.b(1, f2) + 0 + aq.f(2, i) + aq.b(3, z) + aq.d(4, i2) + aq.b(5, j) + aq.b(6, j2);
    }

    private int a(Throwable th, int i) {
        int i2 = 0;
        int iB = aq.b(1, am.a(th.getClass().getName())) + 0;
        String localizedMessage = th.getLocalizedMessage();
        if (localizedMessage != null) {
            iB += aq.b(3, am.a(localizedMessage));
        }
        StackTraceElement[] stackTrace = th.getStackTrace();
        int length = stackTrace.length;
        int i3 = 0;
        while (i3 < length) {
            int iA = a(stackTrace[i3], true);
            i3++;
            iB = iA + aq.a(4) + aq.c(iA) + iB;
        }
        Throwable cause = th.getCause();
        if (cause != null) {
            if (i < 8) {
                int iA2 = a(cause, i + 1);
                return iB + iA2 + aq.a(6) + aq.c(iA2);
            }
            while (cause != null) {
                cause = cause.getCause();
                i2++;
            }
            return iB + aq.d(7, i2);
        }
        return iB;
    }

    private static int r() {
        return aq.b(1, f) + 0 + aq.b(2, f) + aq.b(3, 0L);
    }

    final void h() {
        a(new h(this));
    }

    final void a(File[] fileArr) {
        File file = new File(cm.a().i(), "invalidClsFiles");
        if (file.exists()) {
            if (file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    file2.delete();
                }
            }
            file.delete();
        }
        for (File file3 : fileArr) {
            cm.a().b().a("Crashlytics", "Found invalid session part file: " + file3);
            String strA = a(file3);
            i iVar = new i(this, strA);
            cm.a().b().a("Crashlytics", "Deleting all part files for invalid session: " + strA);
            for (File file4 : a(iVar)) {
                cm.a().b().a("Crashlytics", "Deleting session file: " + file4);
                file4.delete();
            }
        }
    }

    private <T> T a(Callable<T> callable) {
        try {
            return this.s.submit(callable).get();
        } catch (RejectedExecutionException e2) {
            cm.a().b().a("Crashlytics", "Executor is shut down because we're handling a fatal crash.");
            return null;
        } catch (Exception e3) {
            cm.a().b().a("Crashlytics", "Failed to execute task.", e3);
            return null;
        }
    }

    private Future<?> a(Runnable runnable) {
        try {
            return this.s.submit(new j(this, runnable));
        } catch (RejectedExecutionException e2) {
            cm.a().b().a("Crashlytics", "Executor is shut down because we're handling a fatal crash.");
            return null;
        }
    }

    private <T> Future<T> b(Callable<T> callable) {
        try {
            return this.s.submit(new l(this, callable));
        } catch (RejectedExecutionException e2) {
            cm.a().b().a("Crashlytics", "Executor is shut down because we're handling a fatal crash.");
            return null;
        }
    }
}
