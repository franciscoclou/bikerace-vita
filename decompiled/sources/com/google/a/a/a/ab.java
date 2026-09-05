package com.google.a.a.a;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.AppEventsConstants;
import com.google.android.gms.analytics.internal.Command;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: compiled from: GAThread.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ab extends Thread implements h {
    private static ab g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final LinkedBlockingQueue<Runnable> f568a;
    private volatile boolean b;
    private volatile boolean c;
    private volatile List<Command> d;
    private volatile String e;
    private volatile String f;
    private volatile ar h;
    private final Context i;

    static ab a(Context context) {
        if (g == null) {
            g = new ab(context);
        }
        return g;
    }

    private ab(Context context) {
        super("GAThread");
        this.f568a = new LinkedBlockingQueue<>();
        this.b = false;
        this.c = false;
        if (context != null) {
            this.i = context.getApplicationContext();
        } else {
            this.i = context;
        }
        start();
    }

    protected void e() {
        this.h.f();
        this.d = new ArrayList();
        this.d.add(new Command("appendVersion", "&_v".substring(1), "ma3.0.1"));
        this.d.add(new Command("appendQueueTime", "&qt".substring(1), null));
        this.d.add(new Command("appendCacheBuster", "&z".substring(1), null));
    }

    @Override // com.google.a.a.a.h
    public void a(Map<String, String> map) {
        final HashMap map2 = new HashMap(map);
        String str = map.get("&ht");
        if (str != null) {
            try {
                Long.valueOf(str).longValue();
            } catch (NumberFormatException e) {
                str = null;
            }
        }
        if (str == null) {
            map2.put("&ht", Long.toString(System.currentTimeMillis()));
        }
        a(new Runnable() { // from class: com.google.a.a.a.ab.1
            @Override // java.lang.Runnable
            public void run() {
                if (TextUtils.isEmpty((CharSequence) map2.get("&cid"))) {
                    map2.put("&cid", ab.this.f);
                }
                if (!ae.a(ab.this.i).c() && !ab.this.c((Map<String, String>) map2)) {
                    if (!TextUtils.isEmpty(ab.this.e)) {
                        ac.a().a(true);
                        map2.putAll(new ak().a(ab.this.e).a());
                        ac.a().a(false);
                        ab.this.e = null;
                    }
                    ab.this.d((Map<String, String>) map2);
                    ab.this.h.a(ag.a((Map<String, String>) map2), Long.valueOf((String) map2.get("&ht")).longValue(), ab.this.b((Map<String, String>) map2), ab.this.d);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b(Map<String, String> map) {
        return (!map.containsKey("useSecure") || aw.a(map.get("useSecure"), true)) ? "https:" : "http:";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(Map<String, String> map) {
        if (map.get("&sf") == null) {
            return false;
        }
        double dA = aw.a(map.get("&sf"), 100.0d);
        if (dA < 100.0d && a(map.get("&cid")) % 10000 >= dA * 100.0d) {
            ah.c(String.format("%s hit sampled out", map.get("&t") == null ? "unknown" : map.get("&t")));
            return true;
        }
        return false;
    }

    static int a(String str) {
        int i = 1;
        if (!TextUtils.isEmpty(str)) {
            i = 0;
            for (int length = str.length() - 1; length >= 0; length--) {
                char cCharAt = str.charAt(length);
                i = ((i << 6) & 268435455) + cCharAt + (cCharAt << 14);
                int i2 = 266338304 & i;
                if (i2 != 0) {
                    i ^= i2 >> 21;
                }
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Map<String, String> map) {
        i iVarA = i.a();
        aw.a(map, "&an", iVarA.a("&an"));
        aw.a(map, "&av", iVarA.a("&av"));
        aw.a(map, "&aid", iVarA.a("&aid"));
        aw.a(map, "&aiid", iVarA.a("&aiid"));
        map.put("&v", AppEventsConstants.EVENT_PARAM_VALUE_YES);
    }

    @Override // com.google.a.a.a.h
    public void a() {
        a(new Runnable() { // from class: com.google.a.a.a.ab.2
            @Override // java.lang.Runnable
            public void run() {
                ab.this.h.c();
            }
        });
    }

    @Override // com.google.a.a.a.h
    public void b() {
        a(new Runnable() { // from class: com.google.a.a.a.ab.3
            @Override // java.lang.Runnable
            public void run() {
                ab.this.h.e();
            }
        });
    }

    void a(Runnable runnable) {
        this.f568a.add(runnable);
    }

    static String b(Context context) {
        String str = null;
        try {
            FileInputStream fileInputStreamOpenFileInput = context.openFileInput("gaInstallData");
            byte[] bArr = new byte[8192];
            int i = fileInputStreamOpenFileInput.read(bArr, 0, 8192);
            if (fileInputStreamOpenFileInput.available() > 0) {
                ah.a("Too much campaign data, ignoring it.");
                fileInputStreamOpenFileInput.close();
                context.deleteFile("gaInstallData");
            } else {
                fileInputStreamOpenFileInput.close();
                context.deleteFile("gaInstallData");
                if (i <= 0) {
                    ah.d("Campaign file is empty.");
                } else {
                    String str2 = new String(bArr, 0, i);
                    ah.b("Campaign found: " + str2);
                    str = str2;
                }
            }
        } catch (FileNotFoundException e) {
            ah.b("No campaign data found.");
        } catch (IOException e2) {
            ah.a("Error reading campaign data.");
            context.deleteFile("gaInstallData");
        }
        return str;
    }

    private String a(Throwable th) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        th.printStackTrace(printStream);
        printStream.flush();
        return new String(byteArrayOutputStream.toByteArray());
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            ah.d("sleep interrupted in GAThread initialize");
        }
        try {
            if (this.h == null) {
                this.h = new v(this.i, this);
            }
            e();
            this.f = j.a().a("&cid");
            this.e = b(this.i);
        } catch (Throwable th) {
            ah.a("Error initializing the GAThread: " + a(th));
            ah.a("Google Analytics will not start up.");
            this.b = true;
        }
        while (!this.c) {
            try {
                try {
                    Runnable runnableTake = this.f568a.take();
                    if (!this.b) {
                        runnableTake.run();
                    }
                } catch (InterruptedException e2) {
                    ah.b(e2.toString());
                }
            } catch (Throwable th2) {
                ah.a("Error on GAThread: " + a(th2));
                ah.a("Google Analytics is shutting down.");
                this.b = true;
            }
        }
    }

    @Override // com.google.a.a.a.h
    public LinkedBlockingQueue<Runnable> c() {
        return this.f568a;
    }

    @Override // com.google.a.a.a.h
    public Thread d() {
        return this;
    }
}
