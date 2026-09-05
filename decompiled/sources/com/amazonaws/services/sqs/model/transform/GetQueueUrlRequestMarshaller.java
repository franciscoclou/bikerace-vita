package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.a;
import com.amazonaws.f.k;
import com.amazonaws.h;
import com.amazonaws.j;
import com.amazonaws.services.sqs.model.GetQueueUrlRequest;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class GetQueueUrlRequestMarshaller {
    public j<GetQueueUrlRequest> marshall(GetQueueUrlRequest getQueueUrlRequest) {
        if (getQueueUrlRequest == null) {
            throw new a("Invalid argument passed to marshall(...)");
        }
        h hVar = new h(getQueueUrlRequest, "AmazonSQS");
        hVar.b("Action", "GetQueueUrl");
        hVar.b("Version", "2011-10-01");
        if (getQueueUrlRequest.getQueueName() != null) {
            hVar.b("QueueName", k.a(getQueueUrlRequest.getQueueName()));
        }
        if (getQueueUrlRequest.getQueueOwnerAWSAccountId() != null) {
            hVar.b("QueueOwnerAWSAccountId", k.a(getQueueUrlRequest.getQueueOwnerAWSAccountId()));
        }
        return hVar;
    }
}
