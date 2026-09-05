package org.c.b;

/* JADX INFO: compiled from: OAuthParametersMissingException.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c extends b {
    public c(org.c.d.c cVar) {
        super(String.format("Could not find oauth parameters in request: %s. OAuth parameters must be specified with the addOAuthParameter() method", cVar));
    }
}
