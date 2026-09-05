package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.e.c;
import com.amazonaws.e.t;
import com.amazonaws.services.dynamodb.model.DescribeTableResult;
import org.codehaus.jackson.JsonToken;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class DescribeTableResultJsonUnmarshaller implements t<DescribeTableResult, c> {
    private static DescribeTableResultJsonUnmarshaller instance;

    public static DescribeTableResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeTableResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public DescribeTableResult unmarshall(c cVar) {
        DescribeTableResult describeTableResult = new DescribeTableResult();
        int iA = cVar.a();
        int i = iA + 1;
        JsonToken jsonTokenC = cVar.f98a;
        if (jsonTokenC == null) {
            jsonTokenC = cVar.c();
        }
        while (jsonTokenC != null) {
            if (jsonTokenC == JsonToken.FIELD_NAME || jsonTokenC == JsonToken.START_OBJECT) {
                if (cVar.a("Table", i)) {
                    cVar.c();
                    describeTableResult.setTable(TableDescriptionJsonUnmarshaller.getInstance().unmarshall(cVar));
                }
            } else if ((jsonTokenC == JsonToken.END_ARRAY || jsonTokenC == JsonToken.END_OBJECT) && cVar.a() <= iA) {
                break;
            }
            jsonTokenC = cVar.c();
        }
        return describeTableResult;
    }
}
