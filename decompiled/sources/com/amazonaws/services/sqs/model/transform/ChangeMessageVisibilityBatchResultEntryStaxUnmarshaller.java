package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.e.p;
import com.amazonaws.e.r;
import com.amazonaws.e.t;
import com.amazonaws.javax.xml.stream.events.XMLEvent;
import com.amazonaws.services.sqs.model.ChangeMessageVisibilityBatchResultEntry;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ChangeMessageVisibilityBatchResultEntryStaxUnmarshaller implements t<ChangeMessageVisibilityBatchResultEntry, r> {
    private static ChangeMessageVisibilityBatchResultEntryStaxUnmarshaller instance;

    public static ChangeMessageVisibilityBatchResultEntryStaxUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ChangeMessageVisibilityBatchResultEntryStaxUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public ChangeMessageVisibilityBatchResultEntry unmarshall(r rVar) {
        ChangeMessageVisibilityBatchResultEntry changeMessageVisibilityBatchResultEntry = new ChangeMessageVisibilityBatchResultEntry();
        int iB = rVar.b();
        int i = iB + 1;
        if (rVar.c()) {
            i += 2;
        }
        while (true) {
            XMLEvent xMLEventD = rVar.d();
            if (xMLEventD.isEndDocument()) {
                return changeMessageVisibilityBatchResultEntry;
            }
            if (xMLEventD.isAttribute() || xMLEventD.isStartElement()) {
                if (rVar.a("Id", i)) {
                    changeMessageVisibilityBatchResultEntry.setId(p.a().unmarshall(rVar));
                }
            } else if (xMLEventD.isEndElement() && rVar.b() < iB) {
                return changeMessageVisibilityBatchResultEntry;
            }
        }
    }
}
