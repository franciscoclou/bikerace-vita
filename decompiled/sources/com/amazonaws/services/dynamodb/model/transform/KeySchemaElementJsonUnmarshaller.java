package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.e.c;
import com.amazonaws.e.m;
import com.amazonaws.e.t;
import com.amazonaws.services.dynamodb.model.KeySchemaElement;
import org.codehaus.jackson.JsonToken;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class KeySchemaElementJsonUnmarshaller implements t<KeySchemaElement, c> {
    private static KeySchemaElementJsonUnmarshaller instance;

    public static KeySchemaElementJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new KeySchemaElementJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public KeySchemaElement unmarshall(c cVar) {
        KeySchemaElement keySchemaElement = new KeySchemaElement();
        int iA = cVar.a();
        int i = iA + 1;
        JsonToken jsonTokenC = cVar.f98a;
        if (jsonTokenC == null) {
            jsonTokenC = cVar.c();
        }
        while (jsonTokenC != null) {
            if (jsonTokenC == JsonToken.FIELD_NAME || jsonTokenC == JsonToken.START_OBJECT) {
                if (cVar.a("AttributeName", i)) {
                    cVar.c();
                    keySchemaElement.setAttributeName(m.a().unmarshall(cVar));
                }
                if (cVar.a("AttributeType", i)) {
                    cVar.c();
                    keySchemaElement.setAttributeType(m.a().unmarshall(cVar));
                }
            } else if ((jsonTokenC == JsonToken.END_ARRAY || jsonTokenC == JsonToken.END_OBJECT) && cVar.a() <= iA) {
                break;
            }
            jsonTokenC = cVar.c();
        }
        return keySchemaElement;
    }
}
