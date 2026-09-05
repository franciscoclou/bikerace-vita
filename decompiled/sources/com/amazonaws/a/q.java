package com.amazonaws.a;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class q implements c {
    @Override // com.amazonaws.a.c
    public b getCredentials() {
        if (System.getProperty("aws.accessKeyId") == null || System.getProperty("aws.secretKey") == null) {
            throw new com.amazonaws.a("Unable to load AWS credentials from Java system properties (aws.accessKeyId and aws.secretKey)");
        }
        return new h(System.getProperty("aws.accessKeyId"), System.getProperty("aws.secretKey"));
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
