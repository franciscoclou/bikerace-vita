package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.a;
import com.amazonaws.c.f;
import com.amazonaws.h;
import com.amazonaws.j;
import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.amazonaws.services.dynamodb.model.ListTablesRequest;
import java.io.StringWriter;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ListTablesRequestMarshaller {
    private String getString(String str) {
        return str == null ? "" : str;
    }

    public j<ListTablesRequest> marshall(ListTablesRequest listTablesRequest) {
        if (listTablesRequest == null) {
            throw new a("Invalid argument passed to marshall(...)");
        }
        h hVar = new h(listTablesRequest, "AmazonDynamoDB");
        hVar.a("X-Amz-Target", "DynamoDB_20111205.ListTables");
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
            if (listTablesRequest.getExclusiveStartTableName() != null) {
                fVar.a("ExclusiveStartTableName").a((Object) listTablesRequest.getExclusiveStartTableName());
            }
            if (listTablesRequest.getLimit() != null) {
                fVar.a("Limit").a(listTablesRequest.getLimit());
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
