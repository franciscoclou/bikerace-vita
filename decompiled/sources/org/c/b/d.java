package org.c.b;

/* JADX INFO: compiled from: OAuthSignatureException.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class d extends b {
    public d(String str, Exception exc) {
        super(String.format("Error while signing string: %s", str), exc);
    }
}
