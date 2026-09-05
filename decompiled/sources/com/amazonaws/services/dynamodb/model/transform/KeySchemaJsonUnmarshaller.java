package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.e.c;
import com.amazonaws.e.t;
import com.amazonaws.services.dynamodb.model.KeySchema;
import org.codehaus.jackson.JsonToken;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class KeySchemaJsonUnmarshaller implements t<KeySchema, c> {
    private static KeySchemaJsonUnmarshaller instance;

    public static KeySchemaJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new KeySchemaJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public KeySchema unmarshall(c cVar) {
        KeySchema keySchema = new KeySchema();
        int iA = cVar.a();
        int i = iA + 1;
        JsonToken jsonTokenC = cVar.f98a;
        if (jsonTokenC == null) {
            jsonTokenC = cVar.c();
        }
        while (jsonTokenC != null) {
            if (jsonTokenC == JsonToken.FIELD_NAME || jsonTokenC == JsonToken.START_OBJECT) {
                if (cVar.a("HashKeyElement", i)) {
                    cVar.c();
                    keySchema.setHashKeyElement(KeySchemaElementJsonUnmarshaller.getInstance().unmarshall(cVar));
                }
                if (cVar.a("RangeKeyElement", i)) {
                    cVar.c();
                    keySchema.setRangeKeyElement(KeySchemaElementJsonUnmarshaller.getInstance().unmarshall(cVar));
                }
            } else if ((jsonTokenC == JsonToken.END_ARRAY || jsonTokenC == JsonToken.END_OBJECT) && cVar.a() <= iA) {
                break;
            }
            jsonTokenC = cVar.c();
        }
        return keySchema;
    }
}
