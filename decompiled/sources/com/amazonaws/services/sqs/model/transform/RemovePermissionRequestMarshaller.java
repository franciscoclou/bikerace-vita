package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.a;
import com.amazonaws.f.k;
import com.amazonaws.h;
import com.amazonaws.j;
import com.amazonaws.services.sqs.model.RemovePermissionRequest;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class RemovePermissionRequestMarshaller {
    public j<RemovePermissionRequest> marshall(RemovePermissionRequest removePermissionRequest) {
        if (removePermissionRequest == null) {
            throw new a("Invalid argument passed to marshall(...)");
        }
        h hVar = new h(removePermissionRequest, "AmazonSQS");
        hVar.b("Action", "RemovePermission");
        hVar.b("Version", "2011-10-01");
        if (removePermissionRequest.getQueueUrl() != null) {
            hVar.b("QueueUrl", k.a(removePermissionRequest.getQueueUrl()));
        }
        if (removePermissionRequest.getLabel() != null) {
            hVar.b("Label", k.a(removePermissionRequest.getLabel()));
        }
        return hVar;
    }
}
