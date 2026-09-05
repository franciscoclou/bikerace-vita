package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.e.c;
import com.amazonaws.e.f;
import com.amazonaws.e.j;
import com.amazonaws.e.m;
import com.amazonaws.e.t;
import com.amazonaws.services.dynamodb.model.DeleteItemResult;
import org.codehaus.jackson.JsonToken;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class DeleteItemResultJsonUnmarshaller implements t<DeleteItemResult, c> {
    private static DeleteItemResultJsonUnmarshaller instance;

    public static DeleteItemResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteItemResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public DeleteItemResult unmarshall(c cVar) {
        DeleteItemResult deleteItemResult = new DeleteItemResult();
        int iA = cVar.a();
        int i = iA + 1;
        JsonToken jsonTokenC = cVar.f98a;
        if (jsonTokenC == null) {
            jsonTokenC = cVar.c();
        }
        while (jsonTokenC != null) {
            if (jsonTokenC == JsonToken.FIELD_NAME || jsonTokenC == JsonToken.START_OBJECT) {
                if (cVar.a("Attributes", i)) {
                    deleteItemResult.setAttributes(new f(m.a(), AttributeValueJsonUnmarshaller.getInstance()).unmarshall(cVar));
                }
                if (cVar.a("ConsumedCapacityUnits", i)) {
                    cVar.c();
                    deleteItemResult.setConsumedCapacityUnits(j.a().unmarshall(cVar));
                }
            } else if ((jsonTokenC == JsonToken.END_ARRAY || jsonTokenC == JsonToken.END_OBJECT) && cVar.a() <= iA) {
                break;
            }
            jsonTokenC = cVar.c();
        }
        return deleteItemResult;
    }
}
