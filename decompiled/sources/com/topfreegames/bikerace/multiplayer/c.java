package com.topfreegames.bikerace.multiplayer;

import android.content.Context;
import android.util.Log;
import com.topfreegames.engine.data.DataNode;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* JADX INFO: compiled from: GuestManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static c f1311a;
    private String b;
    private Context c;
    private String d = null;

    public static c a(Context context) {
        if (f1311a == null) {
            f1311a = new c(context);
        }
        return f1311a;
    }

    private c(Context context) {
        this.c = context;
        a.a.a.a.a(this.c);
        f();
    }

    public String a() {
        if (this.b == null) {
            this.b = d();
        }
        return this.b;
    }

    public String b() {
        if (this.d == null) {
            this.d = c();
            e();
        }
        return this.d;
    }

    private String d() {
        return ("bg" + a.a.a.a.a("com.topfreegames")).toLowerCase();
    }

    public void a(String str) {
        if (str != null && !str.equals("")) {
            this.d = str;
            if (this.d.length() > 20) {
                this.d = this.d.substring(0, 20);
            }
            e();
        }
    }

    public String c() {
        return String.valueOf("Guest_") + (((int) (Math.random() * 1000000.0d)) % 99999);
    }

    public static boolean b(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith("b");
    }

    private void e() {
        try {
            DataNode dataNode = new DataNode("rootData");
            dataNode.putString("guestName", b());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(this.c.openFileOutput("GuestManagerData.dat", 0));
            objectOutputStream.writeObject(dataNode);
            objectOutputStream.close();
        } catch (Exception e) {
            Log.d("GuestManager.persistInformation", "Exception occurred while persisting information" + e.toString());
        }
    }

    private void f() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(this.c.openFileInput("GuestManagerData.dat"));
            DataNode dataNode = (DataNode) objectInputStream.readObject();
            objectInputStream.close();
            this.d = dataNode.getString("guestName");
        } catch (Exception e) {
            Log.d("GuestManager.getPersistedInformation", "Exception occurred while getting persisted information: " + e.toString());
        }
    }
}
