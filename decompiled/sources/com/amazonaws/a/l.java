package com.amazonaws.a;

import java.io.InputStream;
import java.util.Properties;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class l implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f80a;
    private final String b;

    public l(InputStream inputStream) {
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            try {
                inputStream.close();
            } catch (Exception e) {
            }
            if (properties.getProperty("accessKey") == null || properties.getProperty("secretKey") == null) {
                throw new IllegalArgumentException("The specified properties data doesn't contain the expected properties 'accessKey' and 'secretKey'.");
            }
            this.f80a = properties.getProperty("accessKey");
            this.b = properties.getProperty("secretKey");
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (Exception e2) {
            }
            throw th;
        }
    }

    @Override // com.amazonaws.a.b
    public String a() {
        return this.f80a;
    }

    @Override // com.amazonaws.a.b
    public String b() {
        return this.b;
    }
}
