package com.amazonaws.services.sqs;

import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface a {
    SendMessageResult a(SendMessageRequest sendMessageRequest);

    void a(DeleteMessageRequest deleteMessageRequest);
}
