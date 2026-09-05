package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.e.e;
import com.amazonaws.e.p;
import com.amazonaws.e.r;
import com.amazonaws.e.t;
import com.amazonaws.javax.xml.stream.events.XMLEvent;
import com.amazonaws.services.sqs.model.GetQueueAttributesResult;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class GetQueueAttributesResultStaxUnmarshaller implements t<GetQueueAttributesResult, r> {
    private static GetQueueAttributesResultStaxUnmarshaller instance;

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

    public static GetQueueAttributesResultStaxUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetQueueAttributesResultStaxUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public GetQueueAttributesResult unmarshall(r rVar) {
        GetQueueAttributesResult getQueueAttributesResult = new GetQueueAttributesResult();
        int iB = rVar.b();
        int i = iB + 1;
        if (rVar.c()) {
            i += 2;
        }
        while (true) {
            XMLEvent xMLEventD = rVar.d();
            if (xMLEventD.isEndDocument()) {
                return getQueueAttributesResult;
            }
            if (xMLEventD.isAttribute() || xMLEventD.isStartElement()) {
                if (rVar.a("Attribute", i)) {
                    Map.Entry<String, String> entryUnmarshall = AttributesMapEntryUnmarshaller.getInstance().unmarshall(rVar);
                    getQueueAttributesResult.getAttributes().put(entryUnmarshall.getKey(), entryUnmarshall.getValue());
                }
            } else if (xMLEventD.isEndElement() && rVar.b() < iB) {
                return getQueueAttributesResult;
            }
        }
    }
}
