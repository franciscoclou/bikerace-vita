package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.e.c;
import com.amazonaws.e.i;
import com.amazonaws.e.l;
import com.amazonaws.e.m;
import com.amazonaws.e.t;
import com.amazonaws.services.dynamodb.model.TableDescription;
import org.codehaus.jackson.JsonToken;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class TableDescriptionJsonUnmarshaller implements t<TableDescription, c> {
    private static TableDescriptionJsonUnmarshaller instance;

    public static TableDescriptionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new TableDescriptionJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public TableDescription unmarshall(c cVar) {
        TableDescription tableDescription = new TableDescription();
        int iA = cVar.a();
        int i = iA + 1;
        JsonToken jsonTokenC = cVar.f98a;
        if (jsonTokenC == null) {
            jsonTokenC = cVar.c();
        }
        while (jsonTokenC != null) {
            if (jsonTokenC == JsonToken.FIELD_NAME || jsonTokenC == JsonToken.START_OBJECT) {
                if (cVar.a("TableName", i)) {
                    cVar.c();
                    tableDescription.setTableName(m.a().unmarshall(cVar));
                }
                if (cVar.a("KeySchema", i)) {
                    cVar.c();
                    tableDescription.setKeySchema(KeySchemaJsonUnmarshaller.getInstance().unmarshall(cVar));
                }
                if (cVar.a("TableStatus", i)) {
                    cVar.c();
                    tableDescription.setTableStatus(m.a().unmarshall(cVar));
                }
                if (cVar.a("CreationDateTime", i)) {
                    cVar.c();
                    tableDescription.setCreationDateTime(i.a().unmarshall(cVar));
                }
                if (cVar.a("ProvisionedThroughput", i)) {
                    cVar.c();
                    tableDescription.setProvisionedThroughput(ProvisionedThroughputDescriptionJsonUnmarshaller.getInstance().unmarshall(cVar));
                }
                if (cVar.a("TableSizeBytes", i)) {
                    cVar.c();
                    tableDescription.setTableSizeBytes(l.a().unmarshall(cVar));
                }
                if (cVar.a("ItemCount", i)) {
                    cVar.c();
                    tableDescription.setItemCount(l.a().unmarshall(cVar));
                }
            } else if ((jsonTokenC == JsonToken.END_ARRAY || jsonTokenC == JsonToken.END_OBJECT) && cVar.a() <= iA) {
                break;
            }
            jsonTokenC = cVar.c();
        }
        return tableDescription;
    }
}
