package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.e.c;
import com.amazonaws.e.f;
import com.amazonaws.e.m;
import com.amazonaws.e.t;
import com.amazonaws.services.dynamodb.model.PutRequest;
import org.codehaus.jackson.JsonToken;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class PutRequestJsonUnmarshaller implements t<PutRequest, c> {
    private static PutRequestJsonUnmarshaller instance;

    public static PutRequestJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new PutRequestJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public PutRequest unmarshall(c cVar) {
        PutRequest putRequest = new PutRequest();
        int iA = cVar.a();
        int i = iA + 1;
        JsonToken jsonTokenC = cVar.f98a;
        if (jsonTokenC == null) {
            jsonTokenC = cVar.c();
        }
        while (jsonTokenC != null) {
            if (jsonTokenC == JsonToken.FIELD_NAME || jsonTokenC == JsonToken.START_OBJECT) {
                if (cVar.a("Item", i)) {
                    putRequest.setItem(new f(m.a(), AttributeValueJsonUnmarshaller.getInstance()).unmarshall(cVar));
                }
            } else if ((jsonTokenC == JsonToken.END_ARRAY || jsonTokenC == JsonToken.END_OBJECT) && cVar.a() <= iA) {
                break;
            }
            jsonTokenC = cVar.c();
        }
        return putRequest;
    }
}
