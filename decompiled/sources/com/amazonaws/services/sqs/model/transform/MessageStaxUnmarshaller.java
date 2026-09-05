package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.e.e;
import com.amazonaws.e.p;
import com.amazonaws.e.r;
import com.amazonaws.e.t;
import com.amazonaws.javax.xml.stream.events.XMLEvent;
import com.amazonaws.services.sqs.model.Message;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class MessageStaxUnmarshaller implements t<Message, r> {
    private static MessageStaxUnmarshaller instance;

    class AttributesMapEntryUnmarshaller implements t<Map.Entry<String, String>, r> {
        private static AttributesMapEntryUnmarshaller instance;

        private AttributesMapEntryUnmarshaller() {
        }

        public static AttributesMapEntryUnmarshaller getInstance() {
            if (instance == null) {
                instance = new AttributesMapEntryUnmarshaller();
            }
            return instance;
        }

        @Override // com.amazonaws.e.t
        public Map.Entry<String, String> unmarshall(r rVar) {
            int iB = rVar.b();
            int i = iB + 1;
            e eVar = new e();
            while (true) {
                XMLEvent xMLEventD = rVar.d();
                if (!xMLEventD.isEndDocument()) {
                    if (!xMLEventD.isAttribute() && !xMLEventD.isStartElement()) {
                        if (xMLEventD.isEndElement() && rVar.b() < iB) {
                            break;
                        }
                    } else if (rVar.a("Name", i)) {
                        eVar.a(p.a().unmarshall(rVar));
                    } else if (rVar.a("Value", i)) {
                        eVar.setValue(p.a().unmarshall(rVar));
                    }
                } else {
                    break;
                }
            }
            return eVar;
        }
    }

    public static MessageStaxUnmarshaller getInstance() {
        if (instance == null) {
            instance = new MessageStaxUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public Message unmarshall(r rVar) {
        Message message = new Message();
        int iB = rVar.b();
        int i = iB + 1;
        if (rVar.c()) {
            i += 2;
        }
        while (true) {
            XMLEvent xMLEventD = rVar.d();
            if (xMLEventD.isEndDocument()) {
                return message;
            }
            if (xMLEventD.isAttribute() || xMLEventD.isStartElement()) {
                if (rVar.a("MessageId", i)) {
                    message.setMessageId(p.a().unmarshall(rVar));
                } else if (rVar.a("ReceiptHandle", i)) {
                    message.setReceiptHandle(p.a().unmarshall(rVar));
                } else if (rVar.a("MD5OfBody", i)) {
                    message.setMD5OfBody(p.a().unmarshall(rVar));
                } else if (rVar.a("Body", i)) {
                    message.setBody(p.a().unmarshall(rVar));
                } else if (rVar.a("Attribute", i)) {
                    Map.Entry<String, String> entryUnmarshall = AttributesMapEntryUnmarshaller.getInstance().unmarshall(rVar);
                    message.getAttributes().put(entryUnmarshall.getKey(), entryUnmarshall.getValue());
                }
            } else if (xMLEventD.isEndElement() && rVar.b() < iB) {
                return message;
            }
        }
    }
}
