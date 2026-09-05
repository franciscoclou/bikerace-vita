package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.e.c;
import com.amazonaws.e.d;
import com.amazonaws.e.h;
import com.amazonaws.e.m;
import com.amazonaws.e.t;
import com.amazonaws.services.dynamodb.model.AttributeValue;
import org.codehaus.jackson.JsonToken;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AttributeValueJsonUnmarshaller implements t<AttributeValue, c> {
    private static AttributeValueJsonUnmarshaller instance;

    public static AttributeValueJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AttributeValueJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public AttributeValue unmarshall(c cVar) {
        AttributeValue attributeValue = new AttributeValue();
        int iA = cVar.a();
        int i = iA + 1;
        JsonToken jsonTokenC = cVar.f98a;
        if (jsonTokenC == null) {
            jsonTokenC = cVar.c();
        }
        while (jsonTokenC != null) {
            if (jsonTokenC == JsonToken.FIELD_NAME || jsonTokenC == JsonToken.START_OBJECT) {
                if (cVar.a("S", i)) {
                    cVar.c();
                    attributeValue.setS(m.a().unmarshall(cVar));
                }
                if (cVar.a("N", i)) {
                    cVar.c();
                    attributeValue.setN(m.a().unmarshall(cVar));
                }
                if (cVar.a("B", i)) {
                    cVar.c();
                    attributeValue.setB(h.a().unmarshall(cVar));
                }
                if (cVar.a("SS", i)) {
                    attributeValue.setSS(new d(m.a()).unmarshall(cVar));
                }
                if (cVar.a("NS", i)) {
                    attributeValue.setNS(new d(m.a()).unmarshall(cVar));
                }
                if (cVar.a("BS", i)) {
                    attributeValue.setBS(new d(h.a()).unmarshall(cVar));
                }
            } else if ((jsonTokenC == JsonToken.END_ARRAY || jsonTokenC == JsonToken.END_OBJECT) && cVar.a() <= iA) {
                break;
            }
            jsonTokenC = cVar.c();
        }
        return attributeValue;
    }
}
