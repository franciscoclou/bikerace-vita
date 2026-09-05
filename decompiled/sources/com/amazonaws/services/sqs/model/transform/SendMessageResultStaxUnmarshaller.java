package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.e.p;
import com.amazonaws.e.r;
import com.amazonaws.e.t;
import com.amazonaws.javax.xml.stream.events.XMLEvent;
import com.amazonaws.services.sqs.model.SendMessageResult;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class SendMessageResultStaxUnmarshaller implements t<SendMessageResult, r> {
    private static SendMessageResultStaxUnmarshaller instance;

    public static SendMessageResultStaxUnmarshaller getInstance() {
        if (instance == null) {
            instance = new SendMessageResultStaxUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public SendMessageResult unmarshall(r rVar) {
        SendMessageResult sendMessageResult = new SendMessageResult();
        int iB = rVar.b();
        int i = iB + 1;
        if (rVar.c()) {
            i += 2;
        }
        while (true) {
            XMLEvent xMLEventD = rVar.d();
            if (xMLEventD.isEndDocument()) {
                return sendMessageResult;
            }
            if (xMLEventD.isAttribute() || xMLEventD.isStartElement()) {
                if (rVar.a("MD5OfMessageBody", i)) {
                    sendMessageResult.setMD5OfMessageBody(p.a().unmarshall(rVar));
                } else if (rVar.a("MessageId", i)) {
                    sendMessageResult.setMessageId(p.a().unmarshall(rVar));
                }
            } else if (xMLEventD.isEndElement() && rVar.b() < iB) {
                return sendMessageResult;
            }
        }
    }
}
