package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.a;
import com.amazonaws.f.k;
import com.amazonaws.h;
import com.amazonaws.j;
import com.amazonaws.services.sqs.model.GetQueueAttributesRequest;
import java.util.Iterator;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class GetQueueAttributesRequestMarshaller {
    public j<GetQueueAttributesRequest> marshall(GetQueueAttributesRequest getQueueAttributesRequest) {
        if (getQueueAttributesRequest == null) {
            throw new a("Invalid argument passed to marshall(...)");
        }
        h hVar = new h(getQueueAttributesRequest, "AmazonSQS");
        hVar.b("Action", "GetQueueAttributes");
        hVar.b("Version", "2011-10-01");
        if (getQueueAttributesRequest.getQueueUrl() != null) {
            hVar.b("QueueUrl", k.a(getQueueAttributesRequest.getQueueUrl()));
        }
        int i = 1;
        Iterator<String> it = getQueueAttributesRequest.getAttributeNames().iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return hVar;
            }
            String next = it.next();
            if (next != null) {
                hVar.b("AttributeName." + i2, k.a(next));
            }
            i = i2 + 1;
        }
    }
}
