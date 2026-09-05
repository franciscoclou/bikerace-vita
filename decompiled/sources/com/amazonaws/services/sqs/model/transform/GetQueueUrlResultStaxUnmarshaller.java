package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.e.p;
import com.amazonaws.e.r;
import com.amazonaws.e.t;
import com.amazonaws.javax.xml.stream.events.XMLEvent;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class GetQueueUrlResultStaxUnmarshaller implements t<GetQueueUrlResult, r> {
    private static GetQueueUrlResultStaxUnmarshaller instance;

    public static GetQueueUrlResultStaxUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetQueueUrlResultStaxUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public GetQueueUrlResult unmarshall(r rVar) {
        GetQueueUrlResult getQueueUrlResult = new GetQueueUrlResult();
        int iB = rVar.b();
        int i = iB + 1;
        if (rVar.c()) {
            i += 2;
        }
        while (true) {
            XMLEvent xMLEventD = rVar.d();
            if (xMLEventD.isEndDocument()) {
                return getQueueUrlResult;
            }
            if (xMLEventD.isAttribute() || xMLEventD.isStartElement()) {
                if (rVar.a("QueueUrl", i)) {
                    getQueueUrlResult.setQueueUrl(p.a().unmarshall(rVar));
                }
            } else if (xMLEventD.isEndElement() && rVar.b() < iB) {
                return getQueueUrlResult;
            }
        }
    }
}
