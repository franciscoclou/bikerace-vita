package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.e.c;
import com.amazonaws.e.f;
import com.amazonaws.e.j;
import com.amazonaws.e.m;
import com.amazonaws.e.t;
import com.amazonaws.services.dynamodb.model.UpdateItemResult;
import org.codehaus.jackson.JsonToken;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class UpdateItemResultJsonUnmarshaller implements t<UpdateItemResult, c> {
    private static UpdateItemResultJsonUnmarshaller instance;

    public static UpdateItemResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateItemResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public UpdateItemResult unmarshall(c cVar) {
        UpdateItemResult updateItemResult = new UpdateItemResult();
        int iA = cVar.a();
        int i = iA + 1;
        JsonToken jsonTokenC = cVar.f98a;
        if (jsonTokenC == null) {
            jsonTokenC = cVar.c();
        }
        while (jsonTokenC != null) {
            if (jsonTokenC == JsonToken.FIELD_NAME || jsonTokenC == JsonToken.START_OBJECT) {
                if (cVar.a("Attributes", i)) {
                    updateItemResult.setAttributes(new f(m.a(), AttributeValueJsonUnmarshaller.getInstance()).unmarshall(cVar));
                }
                if (cVar.a("ConsumedCapacityUnits", i)) {
                    cVar.c();
                    updateItemResult.setConsumedCapacityUnits(j.a().unmarshall(cVar));
                }
            } else if ((jsonTokenC == JsonToken.END_ARRAY || jsonTokenC == JsonToken.END_OBJECT) && cVar.a() <= iA) {
                break;
            }
            jsonTokenC = cVar.c();
        }
        return updateItemResult;
    }
}
