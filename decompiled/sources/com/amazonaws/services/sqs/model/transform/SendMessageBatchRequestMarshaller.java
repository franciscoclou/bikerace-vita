package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.a;
import com.amazonaws.f.k;
import com.amazonaws.h;
import com.amazonaws.j;
import com.amazonaws.services.sqs.model.SendMessageBatchRequest;
import com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry;
import java.util.Iterator;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class SendMessageBatchRequestMarshaller {
    public j<SendMessageBatchRequest> marshall(SendMessageBatchRequest sendMessageBatchRequest) {
        if (sendMessageBatchRequest == null) {
            throw new a("Invalid argument passed to marshall(...)");
        }
        h hVar = new h(sendMessageBatchRequest, "AmazonSQS");
        hVar.b("Action", "SendMessageBatch");
        hVar.b("Version", "2011-10-01");
        if (sendMessageBatchRequest.getQueueUrl() != null) {
            hVar.b("QueueUrl", k.a(sendMessageBatchRequest.getQueueUrl()));
        }
        int i = 1;
        Iterator<SendMessageBatchRequestEntry> it = sendMessageBatchRequest.getEntries().iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return hVar;
            }
            SendMessageBatchRequestEntry next = it.next();
            if (next != null) {
                if (next.getId() != null) {
                    hVar.b("SendMessageBatchRequestEntry." + i2 + ".Id", k.a(next.getId()));
                }
                if (next.getMessageBody() != null) {
                    hVar.b("SendMessageBatchRequestEntry." + i2 + ".MessageBody", k.a(next.getMessageBody()));
                }
                if (next.getDelaySeconds() != null) {
                    hVar.b("SendMessageBatchRequestEntry." + i2 + ".DelaySeconds", k.a(next.getDelaySeconds()));
                }
            }
            i = i2 + 1;
        }
    }
}
