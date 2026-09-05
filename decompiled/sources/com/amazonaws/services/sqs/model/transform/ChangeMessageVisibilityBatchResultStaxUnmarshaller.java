package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.e.r;
import com.amazonaws.e.t;
import com.amazonaws.javax.xml.stream.events.XMLEvent;
import com.amazonaws.services.sqs.model.ChangeMessageVisibilityBatchResult;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ChangeMessageVisibilityBatchResultStaxUnmarshaller implements t<ChangeMessageVisibilityBatchResult, r> {
    private static ChangeMessageVisibilityBatchResultStaxUnmarshaller instance;

    public static ChangeMessageVisibilityBatchResultStaxUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ChangeMessageVisibilityBatchResultStaxUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public ChangeMessageVisibilityBatchResult unmarshall(r rVar) {
        ChangeMessageVisibilityBatchResult changeMessageVisibilityBatchResult = new ChangeMessageVisibilityBatchResult();
        int iB = rVar.b();
        int i = iB + 1;
        if (rVar.c()) {
            i += 2;
        }
        while (true) {
            XMLEvent xMLEventD = rVar.d();
            if (xMLEventD.isEndDocument()) {
                return changeMessageVisibilityBatchResult;
            }
            if (xMLEventD.isAttribute() || xMLEventD.isStartElement()) {
                if (rVar.a("ChangeMessageVisibilityBatchResultEntry", i)) {
                    changeMessageVisibilityBatchResult.getSuccessful().add(ChangeMessageVisibilityBatchResultEntryStaxUnmarshaller.getInstance().unmarshall(rVar));
                } else if (rVar.a("BatchResultErrorEntry", i)) {
                    changeMessageVisibilityBatchResult.getFailed().add(BatchResultErrorEntryStaxUnmarshaller.getInstance().unmarshall(rVar));
                }
            } else if (xMLEventD.isEndElement() && rVar.b() < iB) {
                return changeMessageVisibilityBatchResult;
            }
        }
    }
}
