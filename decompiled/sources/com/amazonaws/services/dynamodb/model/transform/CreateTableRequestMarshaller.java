package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.a;
import com.amazonaws.c.f;
import com.amazonaws.h;
import com.amazonaws.j;
import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.amazonaws.services.dynamodb.model.CreateTableRequest;
import com.amazonaws.services.dynamodb.model.KeySchema;
import com.amazonaws.services.dynamodb.model.KeySchemaElement;
import com.amazonaws.services.dynamodb.model.ProvisionedThroughput;
import java.io.StringWriter;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class CreateTableRequestMarshaller {
    private String getString(String str) {
        return str == null ? "" : str;
    }

    public j<CreateTableRequest> marshall(CreateTableRequest createTableRequest) {
        if (createTableRequest == null) {
            throw new a("Invalid argument passed to marshall(...)");
        }
        h hVar = new h(createTableRequest, "AmazonDynamoDB");
        hVar.a("X-Amz-Target", "DynamoDB_20111205.CreateTable");
        hVar.a("Content-Type", "application/x-amz-json-1.0");
        hVar.a(f.POST);
        String strReplaceAll = "".replaceAll("//", "/");
        if (strReplaceAll.contains("?")) {
            String strSubstring = strReplaceAll.substring(strReplaceAll.indexOf("?") + 1);
            strReplaceAll = strReplaceAll.substring(0, strReplaceAll.indexOf("?"));
            String[] strArrSplit = strSubstring.split("[;&]");
            for (String str : strArrSplit) {
                String[] strArrSplit2 = str.split("=");
                if (strArrSplit2.length == 2) {
                    hVar.b(strArrSplit2[0], strArrSplit2[1]);
                } else {
                    hVar.b(str, null);
                }
            }
        }
        hVar.a(strReplaceAll);
        try {
            StringWriter stringWriter = new StringWriter();
            com.amazonaws.f.a.f fVar = new com.amazonaws.f.a.f(stringWriter);
            fVar.d();
            if (createTableRequest.getTableName() != null) {
                fVar.a("TableName").a((Object) createTableRequest.getTableName());
            }
            KeySchema keySchema = createTableRequest.getKeySchema();
            if (keySchema != null) {
                fVar.a("KeySchema");
                fVar.d();
                KeySchemaElement hashKeyElement = keySchema.getHashKeyElement();
                if (hashKeyElement != null) {
                    fVar.a("HashKeyElement");
                    fVar.d();
                    if (hashKeyElement.getAttributeName() != null) {
                        fVar.a("AttributeName").a((Object) hashKeyElement.getAttributeName());
                    }
                    if (hashKeyElement.getAttributeType() != null) {
                        fVar.a("AttributeType").a((Object) hashKeyElement.getAttributeType());
                    }
                    fVar.c();
                }
                KeySchemaElement rangeKeyElement = keySchema.getRangeKeyElement();
                if (rangeKeyElement != null) {
                    fVar.a("RangeKeyElement");
                    fVar.d();
                    if (rangeKeyElement.getAttributeName() != null) {
                        fVar.a("AttributeName").a((Object) rangeKeyElement.getAttributeName());
                    }
                    if (rangeKeyElement.getAttributeType() != null) {
                        fVar.a("AttributeType").a((Object) rangeKeyElement.getAttributeType());
                    }
                    fVar.c();
                }
                fVar.c();
            }
            ProvisionedThroughput provisionedThroughput = createTableRequest.getProvisionedThroughput();
            if (provisionedThroughput != null) {
                fVar.a("ProvisionedThroughput");
                fVar.d();
                if (provisionedThroughput.getReadCapacityUnits() != null) {
                    fVar.a("ReadCapacityUnits").a(provisionedThroughput.getReadCapacityUnits());
                }
                if (provisionedThroughput.getWriteCapacityUnits() != null) {
                    fVar.a("WriteCapacityUnits").a(provisionedThroughput.getWriteCapacityUnits());
                }
                fVar.c();
            }
            fVar.c();
            String string = stringWriter.toString();
            byte[] bytes = string.getBytes(XMLStreamWriterImpl.UTF_8);
            hVar.a(new com.amazonaws.f.j(string));
            hVar.a("Content-Length", Integer.toString(bytes.length));
            return hVar;
        } catch (Throwable th) {
            throw new a("Unable to marshall request to JSON: " + th.getMessage(), th);
        }
    }
}
