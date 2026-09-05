package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.e.c;
import com.amazonaws.e.f;
import com.amazonaws.e.j;
import com.amazonaws.e.m;
import com.amazonaws.e.t;
import com.amazonaws.services.dynamodb.model.GetItemResult;
import org.codehaus.jackson.JsonToken;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class GetItemResultJsonUnmarshaller implements t<GetItemResult, c> {
    private static GetItemResultJsonUnmarshaller instance;

    public static GetItemResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetItemResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public GetItemResult unmarshall(c cVar) {
        GetItemResult getItemResult = new GetItemResult();
        int iA = cVar.a();
        int i = iA + 1;
        JsonToken jsonTokenC = cVar.f98a;
        if (jsonTokenC == null) {
            jsonTokenC = cVar.c();
        }
        while (jsonTokenC != null) {
            if (jsonTokenC == JsonToken.FIELD_NAME || jsonTokenC == JsonToken.START_OBJECT) {
                if (cVar.a("Item", i)) {
                    getItemResult.setItem(new f(m.a(), AttributeValueJsonUnmarshaller.getInstance()).unmarshall(cVar));
                }
                if (cVar.a("ConsumedCapacityUnits", i)) {
                    cVar.c();
                    getItemResult.setConsumedCapacityUnits(j.a().unmarshall(cVar));
                }
            } else if ((jsonTokenC == JsonToken.END_ARRAY || jsonTokenC == JsonToken.END_OBJECT) && cVar.a() <= iA) {
                break;
            }
            jsonTokenC = cVar.c();
        }
        return getItemResult;
    }
}
