package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.e.c;
import com.amazonaws.e.t;
import com.amazonaws.services.dynamodb.model.DeleteRequest;
import org.codehaus.jackson.JsonToken;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class DeleteRequestJsonUnmarshaller implements t<DeleteRequest, c> {
    private static DeleteRequestJsonUnmarshaller instance;

    public static DeleteRequestJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteRequestJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public DeleteRequest unmarshall(c cVar) {
        DeleteRequest deleteRequest = new DeleteRequest();
        int iA = cVar.a();
        int i = iA + 1;
        JsonToken jsonTokenC = cVar.f98a;
        if (jsonTokenC == null) {
            jsonTokenC = cVar.c();
        }
        while (jsonTokenC != null) {
            if (jsonTokenC == JsonToken.FIELD_NAME || jsonTokenC == JsonToken.START_OBJECT) {
                if (cVar.a("Key", i)) {
                    cVar.c();
                    deleteRequest.setKey(KeyJsonUnmarshaller.getInstance().unmarshall(cVar));
                }
            } else if ((jsonTokenC == JsonToken.END_ARRAY || jsonTokenC == JsonToken.END_OBJECT) && cVar.a() <= iA) {
                break;
            }
            jsonTokenC = cVar.c();
        }
        return deleteRequest;
    }
}
