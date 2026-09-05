package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.e.p;
import com.amazonaws.e.r;
import com.amazonaws.e.t;
import com.amazonaws.javax.xml.stream.events.XMLEvent;
import com.amazonaws.services.sqs.model.DeleteMessageBatchResultEntry;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class DeleteMessageBatchResultEntryStaxUnmarshaller implements t<DeleteMessageBatchResultEntry, r> {
    private static DeleteMessageBatchResultEntryStaxUnmarshaller instance;

    public static DeleteMessageBatchResultEntryStaxUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteMessageBatchResultEntryStaxUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public DeleteMessageBatchResultEntry unmarshall(r rVar) {
        DeleteMessageBatchResultEntry deleteMessageBatchResultEntry = new DeleteMessageBatchResultEntry();
        int iB = rVar.b();
        int i = iB + 1;
        if (rVar.c()) {
            i += 2;
        }
        while (true) {
            XMLEvent xMLEventD = rVar.d();
            if (xMLEventD.isEndDocument()) {
                return deleteMessageBatchResultEntry;
            }
            if (xMLEventD.isAttribute() || xMLEventD.isStartElement()) {
                if (rVar.a("Id", i)) {
                    deleteMessageBatchResultEntry.setId(p.a().unmarshall(rVar));
                }
            } else if (xMLEventD.isEndElement() && rVar.b() < iB) {
                return deleteMessageBatchResultEntry;
            }
        }
    }
}
