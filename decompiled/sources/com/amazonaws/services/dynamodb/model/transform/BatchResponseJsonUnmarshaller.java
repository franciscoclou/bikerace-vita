package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.e.c;
import com.amazonaws.e.d;
import com.amazonaws.e.f;
import com.amazonaws.e.j;
import com.amazonaws.e.m;
import com.amazonaws.e.t;
import com.amazonaws.services.dynamodb.model.BatchResponse;
import org.codehaus.jackson.JsonToken;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class BatchResponseJsonUnmarshaller implements t<BatchResponse, c> {
    private static BatchResponseJsonUnmarshaller instance;

    public static BatchResponseJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new BatchResponseJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public BatchResponse unmarshall(c cVar) {
        BatchResponse batchResponse = new BatchResponse();
        int iA = cVar.a();
        int i = iA + 1;
        JsonToken jsonTokenC = cVar.f98a;
        if (jsonTokenC == null) {
            jsonTokenC = cVar.c();
        }
        while (jsonTokenC != null) {
            if (jsonTokenC == JsonToken.FIELD_NAME || jsonTokenC == JsonToken.START_OBJECT) {
                if (cVar.a("Items", i)) {
                    batchResponse.setItems(new d(new f(m.a(), AttributeValueJsonUnmarshaller.getInstance())).unmarshall(cVar));
                }
                if (cVar.a("ConsumedCapacityUnits", i)) {
                    cVar.c();
                    batchResponse.setConsumedCapacityUnits(j.a().unmarshall(cVar));
                }
            } else if ((jsonTokenC == JsonToken.END_ARRAY || jsonTokenC == JsonToken.END_OBJECT) && cVar.a() <= iA) {
                break;
            }
            jsonTokenC = cVar.c();
        }
        return batchResponse;
    }
}
