package com.amazonaws.a;

import java.util.LinkedList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class d implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Log f76a = LogFactory.getLog(d.class);
    private List<c> b = new LinkedList();

    public d(c... cVarArr) {
        if (cVarArr == null || cVarArr.length == 0) {
            throw new IllegalArgumentException("No credential providers specified");
        }
        for (c cVar : cVarArr) {
            this.b.add(cVar);
        }
    }

    @Override // com.amazonaws.a.c
    public b getCredentials() {
        for (c cVar : this.b) {
            try {
                b credentials = cVar.getCredentials();
                if (credentials.a() != null && credentials.b() != null) {
                    f76a.debug("Loading credentials from " + cVar.toString());
                    return credentials;
                }
            } catch (Exception e) {
                f76a.debug("Unable to load credentials from " + cVar.toString() + ": " + e.getMessage());
            }
        }
        throw new com.amazonaws.a("Unable to load AWS credentials from any provider in the chain");
    }
}
