package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.a;
import com.amazonaws.f.k;
import com.amazonaws.h;
import com.amazonaws.j;
import com.amazonaws.services.sqs.model.DeleteMessageBatchRequest;
import com.amazonaws.services.sqs.model.DeleteMessageBatchRequestEntry;
import java.util.Iterator;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class DeleteMessageBatchRequestMarshaller {
    public j<DeleteMessageBatchRequest> marshall(DeleteMessageBatchRequest deleteMessageBatchRequest) {
        if (deleteMessageBatchRequest == null) {
            throw new a("Invalid argument passed to marshall(...)");
        }
        h hVar = new h(deleteMessageBatchRequest, "AmazonSQS");
        hVar.b("Action", "DeleteMessageBatch");
        hVar.b("Version", "2011-10-01");
        if (deleteMessageBatchRequest.getQueueUrl() != null) {
            hVar.b("QueueUrl", k.a(deleteMessageBatchRequest.getQueueUrl()));
        }
        int i = 1;
        Iterator<DeleteMessageBatchRequestEntry> it = deleteMessageBatchRequest.getEntries().iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return hVar;
            }
            DeleteMessageBatchRequestEntry next = it.next();
            if (next != null) {
                if (next.getId() != null) {
                    hVar.b("DeleteMessageBatchRequestEntry." + i2 + ".Id", k.a(next.getId()));
                }
                if (next.getReceiptHandle() != null) {
                    hVar.b("DeleteMessageBatchRequestEntry." + i2 + ".ReceiptHandle", k.a(next.getReceiptHandle()));
                }
            }
            i = i2 + 1;
        }
    }
}
