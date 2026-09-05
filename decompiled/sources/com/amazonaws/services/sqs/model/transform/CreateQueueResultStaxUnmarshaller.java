package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.e.p;
import com.amazonaws.e.r;
import com.amazonaws.e.t;
import com.amazonaws.javax.xml.stream.events.XMLEvent;
import com.amazonaws.services.sqs.model.CreateQueueResult;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class CreateQueueResultStaxUnmarshaller implements t<CreateQueueResult, r> {
    private static CreateQueueResultStaxUnmarshaller instance;

    public static CreateQueueResultStaxUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateQueueResultStaxUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public CreateQueueResult unmarshall(r rVar) {
        CreateQueueResult createQueueResult = new CreateQueueResult();
        int iB = rVar.b();
        int i = iB + 1;
        if (rVar.c()) {
            i += 2;
        }
        while (true) {
            XMLEvent xMLEventD = rVar.d();
            if (xMLEventD.isEndDocument()) {
                return createQueueResult;
            }
            if (xMLEventD.isAttribute() || xMLEventD.isStartElement()) {
                if (rVar.a("QueueUrl", i)) {
                    createQueueResult.setQueueUrl(p.a().unmarshall(rVar));
                }
            } else if (xMLEventD.isEndElement() && rVar.b() < iB) {
                return createQueueResult;
            }
        }
    }
}
