package com.b.a.a;

import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class ab implements aa {
    ab() {
    }

    @Override // com.b.a.a.aa
    public final HttpURLConnection a(URL url) {
        return (HttpURLConnection) url.openConnection();
    }
}
