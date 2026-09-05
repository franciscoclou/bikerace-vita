package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.e.p;
import com.amazonaws.e.r;
import com.amazonaws.e.t;
import com.amazonaws.javax.xml.stream.events.XMLEvent;
import com.amazonaws.services.sqs.model.ListQueuesResult;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ListQueuesResultStaxUnmarshaller implements t<ListQueuesResult, r> {
    private static ListQueuesResultStaxUnmarshaller instance;

    public static ListQueuesResultStaxUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListQueuesResultStaxUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public ListQueuesResult unmarshall(r rVar) {
        ListQueuesResult listQueuesResult = new ListQueuesResult();
        int iB = rVar.b();
        int i = iB + 1;
        if (rVar.c()) {
            i += 2;
        }
        while (true) {
            XMLEvent xMLEventD = rVar.d();
            if (xMLEventD.isEndDocument()) {
                return listQueuesResult;
            }
            if (xMLEventD.isAttribute() || xMLEventD.isStartElement()) {
                if (rVar.a("QueueUrl", i)) {
                    listQueuesResult.getQueueUrls().add(p.a().unmarshall(rVar));
                }
            } else if (xMLEventD.isEndElement() && rVar.b() < iB) {
                return listQueuesResult;
            }
        }
    }
}
