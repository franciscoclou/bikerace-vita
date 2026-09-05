package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.e.c;
import com.amazonaws.e.d;
import com.amazonaws.e.f;
import com.amazonaws.e.m;
import com.amazonaws.e.t;
import com.amazonaws.services.dynamodb.model.BatchWriteItemResult;
import org.codehaus.jackson.JsonToken;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class BatchWriteItemResultJsonUnmarshaller implements t<BatchWriteItemResult, c> {
    private static BatchWriteItemResultJsonUnmarshaller instance;

    public static BatchWriteItemResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new BatchWriteItemResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public BatchWriteItemResult unmarshall(c cVar) {
        BatchWriteItemResult batchWriteItemResult = new BatchWriteItemResult();
        int iA = cVar.a();
        int i = iA + 1;
        JsonToken jsonTokenC = cVar.f98a;
        if (jsonTokenC == null) {
            jsonTokenC = cVar.c();
        }
        while (jsonTokenC != null) {
            if (jsonTokenC == JsonToken.FIELD_NAME || jsonTokenC == JsonToken.START_OBJECT) {
                if (cVar.a("Responses", i)) {
                    batchWriteItemResult.setResponses(new f(m.a(), BatchWriteResponseJsonUnmarshaller.getInstance()).unmarshall(cVar));
                }
                if (cVar.a("UnprocessedItems", i)) {
                    batchWriteItemResult.setUnprocessedItems(new f(m.a(), new d(WriteRequestJsonUnmarshaller.getInstance())).unmarshall(cVar));
                }
            } else if ((jsonTokenC == JsonToken.END_ARRAY || jsonTokenC == JsonToken.END_OBJECT) && cVar.a() <= iA) {
                break;
            }
            jsonTokenC = cVar.c();
        }
        return batchWriteItemResult;
    }
}
