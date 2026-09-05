package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.e.r;
import com.amazonaws.e.t;
import com.amazonaws.javax.xml.stream.events.XMLEvent;
import com.amazonaws.services.sqs.model.SendMessageBatchResult;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class SendMessageBatchResultStaxUnmarshaller implements t<SendMessageBatchResult, r> {
    private static SendMessageBatchResultStaxUnmarshaller instance;

    public static SendMessageBatchResultStaxUnmarshaller getInstance() {
        if (instance == null) {
            instance = new SendMessageBatchResultStaxUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public SendMessageBatchResult unmarshall(r rVar) {
        SendMessageBatchResult sendMessageBatchResult = new SendMessageBatchResult();
        int iB = rVar.b();
        int i = iB + 1;
        if (rVar.c()) {
            i += 2;
        }
        while (true) {
            XMLEvent xMLEventD = rVar.d();
            if (xMLEventD.isEndDocument()) {
                return sendMessageBatchResult;
            }
            if (xMLEventD.isAttribute() || xMLEventD.isStartElement()) {
                if (rVar.a("SendMessageBatchResultEntry", i)) {
                    sendMessageBatchResult.getSuccessful().add(SendMessageBatchResultEntryStaxUnmarshaller.getInstance().unmarshall(rVar));
                } else if (rVar.a("BatchResultErrorEntry", i)) {
                    sendMessageBatchResult.getFailed().add(BatchResultErrorEntryStaxUnmarshaller.getInstance().unmarshall(rVar));
                }
            } else if (xMLEventD.isEndElement() && rVar.b() < iB) {
                return sendMessageBatchResult;
            }
        }
    }
}
