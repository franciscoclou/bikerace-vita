package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.a;
import com.amazonaws.f.k;
import com.amazonaws.h;
import com.amazonaws.j;
import com.amazonaws.services.sqs.model.ChangeMessageVisibilityRequest;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ChangeMessageVisibilityRequestMarshaller {
    public j<ChangeMessageVisibilityRequest> marshall(ChangeMessageVisibilityRequest changeMessageVisibilityRequest) {
        if (changeMessageVisibilityRequest == null) {
            throw new a("Invalid argument passed to marshall(...)");
        }
        h hVar = new h(changeMessageVisibilityRequest, "AmazonSQS");
        hVar.b("Action", "ChangeMessageVisibility");
        hVar.b("Version", "2011-10-01");
        if (changeMessageVisibilityRequest.getQueueUrl() != null) {
            hVar.b("QueueUrl", k.a(changeMessageVisibilityRequest.getQueueUrl()));
        }
        if (changeMessageVisibilityRequest.getReceiptHandle() != null) {
            hVar.b("ReceiptHandle", k.a(changeMessageVisibilityRequest.getReceiptHandle()));
        }
        if (changeMessageVisibilityRequest.getVisibilityTimeout() != null) {
            hVar.b("VisibilityTimeout", k.a(changeMessageVisibilityRequest.getVisibilityTimeout()));
        }
        return hVar;
    }
}
