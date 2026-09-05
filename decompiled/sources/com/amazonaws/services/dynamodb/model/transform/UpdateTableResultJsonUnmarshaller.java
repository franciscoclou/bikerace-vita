package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.e.c;
import com.amazonaws.e.t;
import com.amazonaws.services.dynamodb.model.UpdateTableResult;
import org.codehaus.jackson.JsonToken;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class UpdateTableResultJsonUnmarshaller implements t<UpdateTableResult, c> {
    private static UpdateTableResultJsonUnmarshaller instance;

    public static UpdateTableResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateTableResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public UpdateTableResult unmarshall(c cVar) {
        UpdateTableResult updateTableResult = new UpdateTableResult();
        int iA = cVar.a();
        int i = iA + 1;
        JsonToken jsonTokenC = cVar.f98a;
        if (jsonTokenC == null) {
            jsonTokenC = cVar.c();
        }
        while (jsonTokenC != null) {
            if (jsonTokenC == JsonToken.FIELD_NAME || jsonTokenC == JsonToken.START_OBJECT) {
                if (cVar.a("TableDescription", i)) {
                    cVar.c();
                    updateTableResult.setTableDescription(TableDescriptionJsonUnmarshaller.getInstance().unmarshall(cVar));
                }
            } else if ((jsonTokenC == JsonToken.END_ARRAY || jsonTokenC == JsonToken.END_OBJECT) && cVar.a() <= iA) {
                break;
            }
            jsonTokenC = cVar.c();
        }
        return updateTableResult;
    }
}
