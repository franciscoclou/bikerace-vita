package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.e.c;
import com.amazonaws.e.d;
import com.amazonaws.e.m;
import com.amazonaws.e.t;
import com.amazonaws.services.dynamodb.model.ListTablesResult;
import org.codehaus.jackson.JsonToken;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ListTablesResultJsonUnmarshaller implements t<ListTablesResult, c> {
    private static ListTablesResultJsonUnmarshaller instance;

    public static ListTablesResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListTablesResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public ListTablesResult unmarshall(c cVar) {
        ListTablesResult listTablesResult = new ListTablesResult();
        int iA = cVar.a();
        int i = iA + 1;
        JsonToken jsonTokenC = cVar.f98a;
        if (jsonTokenC == null) {
            jsonTokenC = cVar.c();
        }
        while (jsonTokenC != null) {
            if (jsonTokenC == JsonToken.FIELD_NAME || jsonTokenC == JsonToken.START_OBJECT) {
                if (cVar.a("TableNames", i)) {
                    listTablesResult.setTableNames(new d(m.a()).unmarshall(cVar));
                }
                if (cVar.a("LastEvaluatedTableName", i)) {
                    cVar.c();
                    listTablesResult.setLastEvaluatedTableName(m.a().unmarshall(cVar));
                }
            } else if ((jsonTokenC == JsonToken.END_ARRAY || jsonTokenC == JsonToken.END_OBJECT) && cVar.a() <= iA) {
                break;
            }
            jsonTokenC = cVar.c();
        }
        return listTablesResult;
    }
}
