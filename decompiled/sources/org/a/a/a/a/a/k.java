package org.a.a.a.a.a;

/* JADX INFO: compiled from: MessageCatalog.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static k f1593a = null;

    protected abstract String b(int i);

    public static final String a(int i) {
        if (f1593a == null) {
            if (i.a("java.util.ResourceBundle")) {
                try {
                    f1593a = (k) Class.forName("org.a.a.a.a.a.m").newInstance();
                } catch (Exception e) {
                    return "";
                }
            } else if (i.a("org.eclipse.paho.client.mqttv3.internal.MIDPCatalog")) {
                try {
                    f1593a = (k) Class.forName("org.eclipse.paho.client.mqttv3.internal.MIDPCatalog").newInstance();
                } catch (Exception e2) {
                    return "";
                }
            }
        }
        return f1593a.b(i);
    }
}
