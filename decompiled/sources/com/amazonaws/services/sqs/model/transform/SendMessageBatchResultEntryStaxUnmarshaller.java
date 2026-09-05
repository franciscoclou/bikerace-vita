package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.e.p;
import com.amazonaws.e.r;
import com.amazonaws.e.t;
import com.amazonaws.javax.xml.stream.events.XMLEvent;
import com.amazonaws.services.sqs.model.SendMessageBatchResultEntry;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class SendMessageBatchResultEntryStaxUnmarshaller implements t<SendMessageBatchResultEntry, r> {
    private static SendMessageBatchResultEntryStaxUnmarshaller instance;

    public static SendMessageBatchResultEntryStaxUnmarshaller getInstance() {
        if (instance == null) {
            instance = new SendMessageBatchResultEntryStaxUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public SendMessageBatchResultEntry unmarshall(r rVar) {
        SendMessageBatchResultEntry sendMessageBatchResultEntry = new SendMessageBatchResultEntry();
        int iB = rVar.b();
        int i = iB + 1;
        if (rVar.c()) {
            i += 2;
        }
        while (true) {
            XMLEvent xMLEventD = rVar.d();
            if (xMLEventD.isEndDocument()) {
                return sendMessageBatchResultEntry;
            }
            if (xMLEventD.isAttribute() || xMLEventD.isStartElement()) {
                if (rVar.a("Id", i)) {
                    sendMessageBatchResultEntry.setId(p.a().unmarshall(rVar));
                } else if (rVar.a("MessageId", i)) {
                    sendMessageBatchResultEntry.setMessageId(p.a().unmarshall(rVar));
                } else if (rVar.a("MD5OfMessageBody", i)) {
                    sendMessageBatchResultEntry.setMD5OfMessageBody(p.a().unmarshall(rVar));
                }
            } else if (xMLEventD.isEndElement() && rVar.b() < iB) {
                return sendMessageBatchResultEntry;
            }
        }
    }
}
