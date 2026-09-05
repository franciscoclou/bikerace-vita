package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.a;
import com.amazonaws.f.k;
import com.amazonaws.h;
import com.amazonaws.j;
import com.amazonaws.services.sqs.model.ChangeMessageVisibilityBatchRequest;
import com.amazonaws.services.sqs.model.ChangeMessageVisibilityBatchRequestEntry;
import java.util.Iterator;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ChangeMessageVisibilityBatchRequestMarshaller {
    public j<ChangeMessageVisibilityBatchRequest> marshall(ChangeMessageVisibilityBatchRequest changeMessageVisibilityBatchRequest) {
        if (changeMessageVisibilityBatchRequest == null) {
            throw new a("Invalid argument passed to marshall(...)");
        }
        h hVar = new h(changeMessageVisibilityBatchRequest, "AmazonSQS");
        hVar.b("Action", "ChangeMessageVisibilityBatch");
        hVar.b("Version", "2011-10-01");
        if (changeMessageVisibilityBatchRequest.getQueueUrl() != null) {
            hVar.b("QueueUrl", k.a(changeMessageVisibilityBatchRequest.getQueueUrl()));
        }
        int i = 1;
        Iterator<ChangeMessageVisibilityBatchRequestEntry> it = changeMessageVisibilityBatchRequest.getEntries().iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return hVar;
            }
            ChangeMessageVisibilityBatchRequestEntry next = it.next();
            if (next != null) {
                if (next.getId() != null) {
                    hVar.b("ChangeMessageVisibilityBatchRequestEntry." + i2 + ".Id", k.a(next.getId()));
                }
                if (next.getReceiptHandle() != null) {
                    hVar.b("ChangeMessageVisibilityBatchRequestEntry." + i2 + ".ReceiptHandle", k.a(next.getReceiptHandle()));
                }
                if (next.getVisibilityTimeout() != null) {
                    hVar.b("ChangeMessageVisibilityBatchRequestEntry." + i2 + ".VisibilityTimeout", k.a(next.getVisibilityTimeout()));
                }
            }
            i = i2 + 1;
        }
    }
}
