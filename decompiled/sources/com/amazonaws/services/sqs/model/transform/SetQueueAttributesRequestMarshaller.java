package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.a;
import com.amazonaws.f.k;
import com.amazonaws.h;
import com.amazonaws.j;
import com.amazonaws.services.sqs.model.SetQueueAttributesRequest;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class SetQueueAttributesRequestMarshaller {
    public j<SetQueueAttributesRequest> marshall(SetQueueAttributesRequest setQueueAttributesRequest) {
        if (setQueueAttributesRequest == null) {
            throw new a("Invalid argument passed to marshall(...)");
        }
        h hVar = new h(setQueueAttributesRequest, "AmazonSQS");
        hVar.b("Action", "SetQueueAttributes");
        hVar.b("Version", "2011-10-01");
        if (setQueueAttributesRequest.getQueueUrl() != null) {
            hVar.b("QueueUrl", k.a(setQueueAttributesRequest.getQueueUrl()));
        }
        if (setQueueAttributesRequest != null && setQueueAttributesRequest.getAttributes() != null) {
            int i = 1;
            Iterator<Map.Entry<String, String>> it = setQueueAttributesRequest.getAttributes().entrySet().iterator();
            while (true) {
                int i2 = i;
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<String, String> next = it.next();
                if (next.getKey() != null) {
                    hVar.b("Attribute." + i2 + ".Name", k.a(next.getKey()));
                }
                if (next.getValue() != null) {
                    hVar.b("Attribute." + i2 + ".Value", k.a(next.getValue()));
                }
                i = i2 + 1;
            }
        }
        return hVar;
    }
}
