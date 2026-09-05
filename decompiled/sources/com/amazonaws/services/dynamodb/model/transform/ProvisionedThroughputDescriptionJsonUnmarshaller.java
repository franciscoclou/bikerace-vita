package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.e.c;
import com.amazonaws.e.i;
import com.amazonaws.e.l;
import com.amazonaws.e.t;
import com.amazonaws.services.dynamodb.model.ProvisionedThroughputDescription;
import org.codehaus.jackson.JsonToken;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ProvisionedThroughputDescriptionJsonUnmarshaller implements t<ProvisionedThroughputDescription, c> {
    private static ProvisionedThroughputDescriptionJsonUnmarshaller instance;

    public static ProvisionedThroughputDescriptionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ProvisionedThroughputDescriptionJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.e.t
    public ProvisionedThroughputDescription unmarshall(c cVar) {
        ProvisionedThroughputDescription provisionedThroughputDescription = new ProvisionedThroughputDescription();
        int iA = cVar.a();
        int i = iA + 1;
        JsonToken jsonTokenC = cVar.f98a;
        if (jsonTokenC == null) {
            jsonTokenC = cVar.c();
        }
        while (jsonTokenC != null) {
            if (jsonTokenC == JsonToken.FIELD_NAME || jsonTokenC == JsonToken.START_OBJECT) {
                if (cVar.a("LastIncreaseDateTime", i)) {
                    cVar.c();
                    provisionedThroughputDescription.setLastIncreaseDateTime(i.a().unmarshall(cVar));
                }
                if (cVar.a("LastDecreaseDateTime", i)) {
                    cVar.c();
                    provisionedThroughputDescription.setLastDecreaseDateTime(i.a().unmarshall(cVar));
                }
                if (cVar.a("ReadCapacityUnits", i)) {
                    cVar.c();
                    provisionedThroughputDescription.setReadCapacityUnits(l.a().unmarshall(cVar));
                }
                if (cVar.a("WriteCapacityUnits", i)) {
                    cVar.c();
                    provisionedThroughputDescription.setWriteCapacityUnits(l.a().unmarshall(cVar));
                }
            } else if ((jsonTokenC == JsonToken.END_ARRAY || jsonTokenC == JsonToken.END_OBJECT) && cVar.a() <= iA) {
                break;
            }
            jsonTokenC = cVar.c();
        }
        return provisionedThroughputDescription;
    }
}
