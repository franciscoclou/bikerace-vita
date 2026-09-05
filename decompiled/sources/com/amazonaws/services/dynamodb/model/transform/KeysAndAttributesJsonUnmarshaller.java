package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.e.c;
import com.amazonaws.e.d;
import com.amazonaws.e.m;
import com.amazonaws.e.t;
import com.amazonaws.services.dynamodb.model.KeysAndAttributes;
import org.codehaus.jackson.JsonToken;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class KeysAndAttributesJsonUnmarshaller implements t<KeysAndAttributes, c> {
    private static KeysAndAttributesJsonUnmarshaller instance;

    public static KeysAndAttributesJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new KeysAndAttributesJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public KeysAndAttributes unmarshall(c cVar) {
        KeysAndAttributes keysAndAttributes = new KeysAndAttributes();
        int iA = cVar.a();
        int i = iA + 1;
        JsonToken jsonTokenC = cVar.f98a;
        if (jsonTokenC == null) {
            jsonTokenC = cVar.c();
        }
        while (jsonTokenC != null) {
            if (jsonTokenC == JsonToken.FIELD_NAME || jsonTokenC == JsonToken.START_OBJECT) {
                if (cVar.a("Keys", i)) {
                    keysAndAttributes.setKeys(new d(KeyJsonUnmarshaller.getInstance()).unmarshall(cVar));
                }
                if (cVar.a("AttributesToGet", i)) {
                    keysAndAttributes.setAttributesToGet(new d(m.a()).unmarshall(cVar));
                }
            } else if ((jsonTokenC == JsonToken.END_ARRAY || jsonTokenC == JsonToken.END_OBJECT) && cVar.a() <= iA) {
                break;
            }
            jsonTokenC = cVar.c();
        }
        return keysAndAttributes;
    }
}
