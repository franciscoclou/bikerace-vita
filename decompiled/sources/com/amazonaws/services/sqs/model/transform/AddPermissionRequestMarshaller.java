package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.a;
import com.amazonaws.f.k;
import com.amazonaws.h;
import com.amazonaws.j;
import com.amazonaws.services.sqs.model.AddPermissionRequest;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AddPermissionRequestMarshaller {
    public j<AddPermissionRequest> marshall(AddPermissionRequest addPermissionRequest) {
        int i = 1;
        if (addPermissionRequest == null) {
            throw new a("Invalid argument passed to marshall(...)");
        }
        h hVar = new h(addPermissionRequest, "AmazonSQS");
        hVar.b("Action", "AddPermission");
        hVar.b("Version", "2011-10-01");
        if (addPermissionRequest.getQueueUrl() != null) {
            hVar.b("QueueUrl", k.a(addPermissionRequest.getQueueUrl()));
        }
        if (addPermissionRequest.getLabel() != null) {
            hVar.b("Label", k.a(addPermissionRequest.getLabel()));
        }
        int i2 = 1;
        for (String str : addPermissionRequest.getAWSAccountIds()) {
            if (str != null) {
                hVar.b("AWSAccountId." + i2, k.a(str));
            }
            i2++;
        }
        for (String str2 : addPermissionRequest.getActions()) {
            if (str2 != null) {
                hVar.b("ActionName." + i, k.a(str2));
            }
            i++;
        }
        return hVar;
    }
}
