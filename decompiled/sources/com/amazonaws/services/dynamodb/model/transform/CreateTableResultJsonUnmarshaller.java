package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.e.c;
import com.amazonaws.e.t;
import com.amazonaws.services.dynamodb.model.CreateTableResult;
import org.codehaus.jackson.JsonToken;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class CreateTableResultJsonUnmarshaller implements t<CreateTableResult, c> {
    private static CreateTableResultJsonUnmarshaller instance;

    public static CreateTableResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateTableResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public CreateTableResult unmarshall(c cVar) {
        CreateTableResult createTableResult = new CreateTableResult();
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
                    createTableResult.setTableDescription(TableDescriptionJsonUnmarshaller.getInstance().unmarshall(cVar));
                }
            } else if ((jsonTokenC == JsonToken.END_ARRAY || jsonTokenC == JsonToken.END_OBJECT) && cVar.a() <= iA) {
                break;
            }
            jsonTokenC = cVar.c();
        }
        return createTableResult;
    }
}
