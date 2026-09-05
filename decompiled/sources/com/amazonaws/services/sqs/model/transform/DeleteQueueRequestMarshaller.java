package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.a;
import com.amazonaws.f.k;
import com.amazonaws.h;
import com.amazonaws.j;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class DeleteQueueRequestMarshaller {
    public j<DeleteQueueRequest> marshall(DeleteQueueRequest deleteQueueRequest) {
        if (deleteQueueRequest == null) {
            throw new a("Invalid argument passed to marshall(...)");
        }
        h hVar = new h(deleteQueueRequest, "AmazonSQS");
        hVar.b("Action", "DeleteQueue");
        hVar.b("Version", "2011-10-01");
        if (deleteQueueRequest.getQueueUrl() != null) {
            hVar.b("QueueUrl", k.a(deleteQueueRequest.getQueueUrl()));
        }
        return hVar;
    }
}
