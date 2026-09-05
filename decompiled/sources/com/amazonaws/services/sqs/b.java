package com.amazonaws.services.sqs;

import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface b extends a {
    Future<ReceiveMessageResult> a(ReceiveMessageRequest receiveMessageRequest);
}
