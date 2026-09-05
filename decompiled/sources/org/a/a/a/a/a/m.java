package org.a.a.a.a.a;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/* JADX INFO: compiled from: ResourceBundleCatalog.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class m extends k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ResourceBundle f1594a = ResourceBundle.getBundle("org.eclipse.paho.client.mqttv3.internal.nls.messages");

    @Override // org.a.a.a.a.a.k
    protected String b(int i) {
        try {
            return this.f1594a.getString(Integer.toString(i));
        } catch (MissingResourceException e) {
            return "MqttException";
        }
    }
}
