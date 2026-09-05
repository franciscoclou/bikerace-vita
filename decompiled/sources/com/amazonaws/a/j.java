package com.amazonaws.a;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class j implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f79a = "AwsCredentials.properties";
    private final String b;

    public j() {
        this(f79a);
    }

    public j(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Credentials file path cannot be null");
        }
        if (str.startsWith("/")) {
            this.b = str;
        } else {
            this.b = "/" + str;
        }
    }

    @Override // com.amazonaws.a.c
    public b getCredentials() {
        InputStream resourceAsStream = getClass().getResourceAsStream(this.b);
        if (resourceAsStream == null) {
            throw new com.amazonaws.a("Unable to load AWS credentials from the " + this.b + " file on the classpath");
        }
        try {
            return new l(resourceAsStream);
        } catch (IOException e) {
            throw new com.amazonaws.a("Unable to load AWS credentials from the " + this.b + " file on the classpath", e);
        }
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.b + ")";
    }
}
