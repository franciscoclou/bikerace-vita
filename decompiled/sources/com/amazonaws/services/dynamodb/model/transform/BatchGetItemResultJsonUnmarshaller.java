package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.e.c;
import com.amazonaws.e.f;
import com.amazonaws.e.m;
import com.amazonaws.e.t;
import com.amazonaws.services.dynamodb.model.BatchGetItemResult;
import org.codehaus.jackson.JsonToken;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class BatchGetItemResultJsonUnmarshaller implements t<BatchGetItemResult, c> {
    private static BatchGetItemResultJsonUnmarshaller instance;

    public static BatchGetItemResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new BatchGetItemResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public BatchGetItemResult unmarshall(c cVar) {
        BatchGetItemResult batchGetItemResult = new BatchGetItemResult();
        int iA = cVar.a();
        int i = iA + 1;
        JsonToken jsonTokenC = cVar.f98a;
        if (jsonTokenC == null) {
            jsonTokenC = cVar.c();
        }
        while (jsonTokenC != null) {
            if (jsonTokenC == JsonToken.FIELD_NAME || jsonTokenC == JsonToken.START_OBJECT) {
                if (cVar.a("Responses", i)) {
                    batchGetItemResult.setResponses(new f(m.a(), BatchResponseJsonUnmarshaller.getInstance()).unmarshall(cVar));
                }
                if (cVar.a("UnprocessedKeys", i)) {
                    batchGetItemResult.setUnprocessedKeys(new f(m.a(), KeysAndAttributesJsonUnmarshaller.getInstance()).unmarshall(cVar));
                }
            } else if ((jsonTokenC == JsonToken.END_ARRAY || jsonTokenC == JsonToken.END_OBJECT) && cVar.a() <= iA) {
                break;
            }
            jsonTokenC = cVar.c();
        }
        return batchGetItemResult;
    }
}
