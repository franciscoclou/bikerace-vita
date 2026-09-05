package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.e.r;
import com.amazonaws.e.t;
import com.amazonaws.javax.xml.stream.events.XMLEvent;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ReceiveMessageResultStaxUnmarshaller implements t<ReceiveMessageResult, r> {
    private static ReceiveMessageResultStaxUnmarshaller instance;

    public static ReceiveMessageResultStaxUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ReceiveMessageResultStaxUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public ReceiveMessageResult unmarshall(r rVar) {
        ReceiveMessageResult receiveMessageResult = new ReceiveMessageResult();
        int iB = rVar.b();
        int i = iB + 1;
        if (rVar.c()) {
            i += 2;
        }
        while (true) {
            XMLEvent xMLEventD = rVar.d();
            if (xMLEventD.isEndDocument()) {
                return receiveMessageResult;
            }
            if (xMLEventD.isAttribute() || xMLEventD.isStartElement()) {
                if (rVar.a("Message", i)) {
                    receiveMessageResult.getMessages().add(MessageStaxUnmarshaller.getInstance().unmarshall(rVar));
                }
            } else if (xMLEventD.isEndElement() && rVar.b() < iB) {
                return receiveMessageResult;
            }
        }
    }
}
