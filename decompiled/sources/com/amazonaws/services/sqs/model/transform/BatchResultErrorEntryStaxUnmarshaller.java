package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.e.o;
import com.amazonaws.e.p;
import com.amazonaws.e.r;
import com.amazonaws.e.t;
import com.amazonaws.javax.xml.stream.events.XMLEvent;
import com.amazonaws.services.sqs.model.BatchResultErrorEntry;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class BatchResultErrorEntryStaxUnmarshaller implements t<BatchResultErrorEntry, r> {
    private static BatchResultErrorEntryStaxUnmarshaller instance;

    public static BatchResultErrorEntryStaxUnmarshaller getInstance() {
        if (instance == null) {
            instance = new BatchResultErrorEntryStaxUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public BatchResultErrorEntry unmarshall(r rVar) {
        BatchResultErrorEntry batchResultErrorEntry = new BatchResultErrorEntry();
        int iB = rVar.b();
        int i = iB + 1;
        if (rVar.c()) {
            i += 2;
        }
        while (true) {
            XMLEvent xMLEventD = rVar.d();
            if (xMLEventD.isEndDocument()) {
                return batchResultErrorEntry;
            }
            if (xMLEventD.isAttribute() || xMLEventD.isStartElement()) {
                if (rVar.a("Id", i)) {
                    batchResultErrorEntry.setId(p.a().unmarshall(rVar));
                } else if (rVar.a("SenderFault", i)) {
                    batchResultErrorEntry.setSenderFault(o.a().unmarshall(rVar));
                } else if (rVar.a("Code", i)) {
                    batchResultErrorEntry.setCode(p.a().unmarshall(rVar));
                } else if (rVar.a("Message", i)) {
                    batchResultErrorEntry.setMessage(p.a().unmarshall(rVar));
                }
            } else if (xMLEventD.isEndElement() && rVar.b() < iB) {
                return batchResultErrorEntry;
            }
        }
    }
}
