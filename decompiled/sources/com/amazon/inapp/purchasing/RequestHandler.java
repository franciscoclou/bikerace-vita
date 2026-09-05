package com.amazon.inapp.purchasing;

import java.util.Set;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
interface RequestHandler {
    void sendContentDownloadRequest(String str, String str2, String str3);

    void sendGetUserIdRequest(String str);

    void sendItemDataRequest(Set<String> set, String str);

    void sendPurchaseRequest(String str, String str2);

    void sendPurchaseResponseReceivedRequest(String str);

    void sendPurchaseUpdatesRequest(Offset offset, String str);
}
