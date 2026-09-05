package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.a;
import com.amazonaws.f.k;
import com.amazonaws.h;
import com.amazonaws.j;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import java.util.Iterator;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ReceiveMessageRequestMarshaller {
    public j<ReceiveMessageRequest> marshall(ReceiveMessageRequest receiveMessageRequest) {
        if (receiveMessageRequest == null) {
            throw new a("Invalid argument passed to marshall(...)");
        }
        h hVar = new h(receiveMessageRequest, "AmazonSQS");
        hVar.b("Action", "ReceiveMessage");
        hVar.b("Version", "2011-10-01");
        if (receiveMessageRequest.getQueueUrl() != null) {
            hVar.b("QueueUrl", k.a(receiveMessageRequest.getQueueUrl()));
        }
        int i = 1;
        Iterator<String> it = receiveMessageRequest.getAttributeNames().iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                break;
            }
            String next = it.next();
            if (next != null) {
                hVar.b("AttributeName." + i2, k.a(next));
            }
            i = i2 + 1;
        }
        if (receiveMessageRequest.getMaxNumberOfMessages() != null) {
            hVar.b("MaxNumberOfMessages", k.a(receiveMessageRequest.getMaxNumberOfMessages()));
        }
        if (receiveMessageRequest.getVisibilityTimeout() != null) {
            hVar.b("VisibilityTimeout", k.a(receiveMessageRequest.getVisibilityTimeout()));
        }
        return hVar;
    }
}
