package com.amazonaws;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class e {
    private com.amazonaws.a.b credentials;
    private String delegationToken;
    private final k requestClientOptions = new k();

    public Map<String, String> copyPrivateRequestParameters() {
        HashMap map = new HashMap();
        if (this.delegationToken != null) {
            map.put("SecurityToken", this.delegationToken);
        }
        return map;
    }

    public String getDelegationToken() {
        return this.delegationToken;
    }

    public k getRequestClientOptions() {
        return this.requestClientOptions;
    }

    public com.amazonaws.a.b getRequestCredentials() {
        return this.credentials;
    }

    public void setDelegationToken(String str) {
        this.delegationToken = str;
    }

    public void setRequestCredentials(com.amazonaws.a.b bVar) {
        this.credentials = bVar;
    }
}
