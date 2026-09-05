package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.a;
import com.amazonaws.f.k;
import com.amazonaws.h;
import com.amazonaws.j;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class DeleteMessageRequestMarshaller {
    public j<DeleteMessageRequest> marshall(DeleteMessageRequest deleteMessageRequest) {
        if (deleteMessageRequest == null) {
            throw new a("Invalid argument passed to marshall(...)");
        }
        h hVar = new h(deleteMessageRequest, "AmazonSQS");
        hVar.b("Action", "DeleteMessage");
        hVar.b("Version", "2011-10-01");
        if (deleteMessageRequest.getQueueUrl() != null) {
            hVar.b("QueueUrl", k.a(deleteMessageRequest.getQueueUrl()));
        }
        if (deleteMessageRequest.getReceiptHandle() != null) {
            hVar.b("ReceiptHandle", k.a(deleteMessageRequest.getReceiptHandle()));
        }
        return hVar;
    }
}
