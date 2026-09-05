package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.a;
import com.amazonaws.f.k;
import com.amazonaws.h;
import com.amazonaws.j;
import com.amazonaws.services.sqs.model.SendMessageRequest;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class SendMessageRequestMarshaller {
    public j<SendMessageRequest> marshall(SendMessageRequest sendMessageRequest) {
        if (sendMessageRequest == null) {
            throw new a("Invalid argument passed to marshall(...)");
        }
        h hVar = new h(sendMessageRequest, "AmazonSQS");
        hVar.b("Action", "SendMessage");
        hVar.b("Version", "2011-10-01");
        if (sendMessageRequest.getQueueUrl() != null) {
            hVar.b("QueueUrl", k.a(sendMessageRequest.getQueueUrl()));
        }
        if (sendMessageRequest.getMessageBody() != null) {
            hVar.b("MessageBody", k.a(sendMessageRequest.getMessageBody()));
        }
        if (sendMessageRequest.getDelaySeconds() != null) {
            hVar.b("DelaySeconds", k.a(sendMessageRequest.getDelaySeconds()));
        }
        return hVar;
    }
}
