package com.amazon.aws.tvmclient;

import com.amazonaws.f.f;
import com.topfreegames.bikerace.bm;
import com.topfreegames.c.a;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class GetTokenRequest extends Request {
    private final String endpoint;
    private final String key;
    private final String uid;
    private final boolean useSSL;

    public GetTokenRequest(String str, boolean z, String str2, String str3) {
        this.endpoint = str;
        this.useSSL = z;
        this.uid = str2;
        this.key = str3;
    }

    @Override // com.amazon.aws.tvmclient.Request
    public String buildRequestUrl() {
        StringBuilder sb = new StringBuilder(this.useSSL ? "https://" : "http://");
        sb.append(this.endpoint);
        sb.append("/");
        String timestamp = Utilities.getTimestamp();
        if (bm.d()) {
            timestamp = Utilities.getTimestamp(a.a());
        }
        String signature = Utilities.getSignature(timestamp, this.key);
        sb.append("gettoken");
        sb.append("?uid=" + f.a(this.uid, false));
        sb.append("&timestamp=" + f.a(timestamp, false));
        sb.append("&signature=" + f.a(signature, false));
        return sb.toString();
    }
}
