package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.e.c;
import com.amazonaws.e.d;
import com.amazonaws.e.f;
import com.amazonaws.e.j;
import com.amazonaws.e.k;
import com.amazonaws.e.m;
import com.amazonaws.e.t;
import com.amazonaws.services.dynamodb.model.QueryResult;
import org.codehaus.jackson.JsonToken;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class QueryResultJsonUnmarshaller implements t<QueryResult, c> {
    private static QueryResultJsonUnmarshaller instance;

    public static QueryResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new QueryResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public QueryResult unmarshall(c cVar) {
        QueryResult queryResult = new QueryResult();
        int iA = cVar.a();
        int i = iA + 1;
        JsonToken jsonTokenC = cVar.f98a;
        if (jsonTokenC == null) {
            jsonTokenC = cVar.c();
        }
        while (jsonTokenC != null) {
            if (jsonTokenC == JsonToken.FIELD_NAME || jsonTokenC == JsonToken.START_OBJECT) {
                if (cVar.a("Items", i)) {
                    queryResult.setItems(new d(new f(m.a(), AttributeValueJsonUnmarshaller.getInstance())).unmarshall(cVar));
                }
                if (cVar.a("Count", i)) {
                    cVar.c();
                    queryResult.setCount(k.a().unmarshall(cVar));
                }
                if (cVar.a("LastEvaluatedKey", i)) {
                    cVar.c();
                    queryResult.setLastEvaluatedKey(KeyJsonUnmarshaller.getInstance().unmarshall(cVar));
                }
                if (cVar.a("ConsumedCapacityUnits", i)) {
                    cVar.c();
                    queryResult.setConsumedCapacityUnits(j.a().unmarshall(cVar));
                }
            } else if ((jsonTokenC == JsonToken.END_ARRAY || jsonTokenC == JsonToken.END_OBJECT) && cVar.a() <= iA) {
                break;
            }
            jsonTokenC = cVar.c();
        }
        return queryResult;
    }
}
