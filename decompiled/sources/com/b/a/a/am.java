package com.b.a.a;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class am {
    am() {
    }

    public JSONObject a() throws Throwable {
        FileInputStream fileInputStream;
        JSONObject jSONObject;
        FileInputStream fileInputStream2 = null;
        cm.a().b().a("Crashlytics", "Reading cached settings...");
        try {
            File file = new File(cm.a().i(), "com.crashlytics.settings.json");
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                try {
                    try {
                        jSONObject = new JSONObject(ba.a(fileInputStream));
                        fileInputStream2 = fileInputStream;
                    } catch (Exception e) {
                        e = e;
                        cm.a().b().a("Crashlytics", "Failed to fetch cached settings", e);
                        ba.a(fileInputStream, "Error while closing settings cache file.");
                        return null;
                    }
                } catch (Throwable th) {
                    th = th;
                    fileInputStream2 = fileInputStream;
                    ba.a(fileInputStream2, "Error while closing settings cache file.");
                    throw th;
                }
            } else {
                cm.a().b().a("Crashlytics", "No cached settings found.");
                jSONObject = null;
            }
            ba.a(fileInputStream2, "Error while closing settings cache file.");
            return jSONObject;
        } catch (Exception e2) {
            e = e2;
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            ba.a(fileInputStream2, "Error while closing settings cache file.");
            throw th;
        }
    }

    public void a(long j, JSONObject jSONObject) throws Throwable {
        FileWriter fileWriter;
        cm.a().b().a("Crashlytics", "Writing settings to cache file...");
        if (jSONObject != null) {
            FileWriter fileWriter2 = null;
            try {
                jSONObject.put("expires_at", j);
                fileWriter = new FileWriter(new File(cm.a().i(), "com.crashlytics.settings.json"));
                try {
                    try {
                        fileWriter.write(jSONObject.toString());
                        fileWriter.flush();
                        ba.a((Closeable) fileWriter, "Failed to close settings writer.");
                    } catch (Exception e) {
                        e = e;
                        cm.a().b().a("Crashlytics", "Failed to cache settings", e);
                        ba.a((Closeable) fileWriter, "Failed to close settings writer.");
                    }
                } catch (Throwable th) {
                    th = th;
                    fileWriter2 = fileWriter;
                    ba.a((Closeable) fileWriter2, "Failed to close settings writer.");
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                fileWriter = null;
            } catch (Throwable th2) {
                th = th2;
                ba.a((Closeable) fileWriter2, "Failed to close settings writer.");
                throw th;
            }
        }
    }
}
