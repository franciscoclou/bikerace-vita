package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.a;
import com.amazonaws.f.k;
import com.amazonaws.h;
import com.amazonaws.j;
import com.amazonaws.services.sqs.model.ListQueuesRequest;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ListQueuesRequestMarshaller {
    public j<ListQueuesRequest> marshall(ListQueuesRequest listQueuesRequest) {
        if (listQueuesRequest == null) {
            throw new a("Invalid argument passed to marshall(...)");
        }
        h hVar = new h(listQueuesRequest, "AmazonSQS");
        hVar.b("Action", "ListQueues");
        hVar.b("Version", "2011-10-01");
        if (listQueuesRequest.getQueueNamePrefix() != null) {
            hVar.b("QueueNamePrefix", k.a(listQueuesRequest.getQueueNamePrefix()));
        }
        return hVar;
    }
}
