package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.e.c;
import com.amazonaws.e.j;
import com.amazonaws.e.t;
import com.amazonaws.services.dynamodb.model.BatchWriteResponse;
import org.codehaus.jackson.JsonToken;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class BatchWriteResponseJsonUnmarshaller implements t<BatchWriteResponse, c> {
    private static BatchWriteResponseJsonUnmarshaller instance;

    public static BatchWriteResponseJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new BatchWriteResponseJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public BatchWriteResponse unmarshall(c cVar) {
        BatchWriteResponse batchWriteResponse = new BatchWriteResponse();
        int iA = cVar.a();
        int i = iA + 1;
        JsonToken jsonTokenC = cVar.f98a;
        if (jsonTokenC == null) {
            jsonTokenC = cVar.c();
        }
        while (jsonTokenC != null) {
            if (jsonTokenC == JsonToken.FIELD_NAME || jsonTokenC == JsonToken.START_OBJECT) {
                if (cVar.a("ConsumedCapacityUnits", i)) {
                    cVar.c();
                    batchWriteResponse.setConsumedCapacityUnits(j.a().unmarshall(cVar));
                }
            } else if ((jsonTokenC == JsonToken.END_ARRAY || jsonTokenC == JsonToken.END_OBJECT) && cVar.a() <= iA) {
                break;
            }
            jsonTokenC = cVar.c();
        }
        return batchWriteResponse;
    }
}
