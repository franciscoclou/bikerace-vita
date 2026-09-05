package com.amazonaws.c;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.conn.ClientConnectionManager;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class k extends Thread {
    private static ArrayList<ClientConnectionManager> b = new ArrayList<>();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final Log f91a = LogFactory.getLog(k.class);

    private k() {
        super("java-sdk-http-connection-reaper");
        setDaemon(true);
        start();
    }

    public static synchronized void a(ClientConnectionManager clientConnectionManager) {
        b.remove(clientConnectionManager);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        List list;
        while (true) {
            try {
                Thread.sleep(60000L);
                synchronized (k.class) {
                    list = (List) b.clone();
                }
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    try {
                        ((ClientConnectionManager) it.next()).closeIdleConnections(60L, TimeUnit.SECONDS);
                    } catch (Throwable th) {
                        f91a.warn("Unable to close idle connections", th);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
