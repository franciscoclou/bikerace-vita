package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.e.r;
import com.amazonaws.e.t;
import com.amazonaws.javax.xml.stream.events.XMLEvent;
import com.amazonaws.services.sqs.model.DeleteMessageBatchResult;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class DeleteMessageBatchResultStaxUnmarshaller implements t<DeleteMessageBatchResult, r> {
    private static DeleteMessageBatchResultStaxUnmarshaller instance;

    public static DeleteMessageBatchResultStaxUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteMessageBatchResultStaxUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public DeleteMessageBatchResult unmarshall(r rVar) {
        DeleteMessageBatchResult deleteMessageBatchResult = new DeleteMessageBatchResult();
        int iB = rVar.b();
        int i = iB + 1;
        if (rVar.c()) {
            i += 2;
        }
        while (true) {
            XMLEvent xMLEventD = rVar.d();
            if (xMLEventD.isEndDocument()) {
                return deleteMessageBatchResult;
            }
            if (xMLEventD.isAttribute() || xMLEventD.isStartElement()) {
                if (rVar.a("DeleteMessageBatchResultEntry", i)) {
                    deleteMessageBatchResult.getSuccessful().add(DeleteMessageBatchResultEntryStaxUnmarshaller.getInstance().unmarshall(rVar));
                } else if (rVar.a("BatchResultErrorEntry", i)) {
                    deleteMessageBatchResult.getFailed().add(BatchResultErrorEntryStaxUnmarshaller.getInstance().unmarshall(rVar));
                }
            } else if (xMLEventD.isEndElement() && rVar.b() < iB) {
                return deleteMessageBatchResult;
            }
        }
    }
}
