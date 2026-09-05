package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.a;
import com.amazonaws.c.f;
import com.amazonaws.h;
import com.amazonaws.j;
import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.amazonaws.services.dynamodb.model.AttributeValue;
import com.amazonaws.services.dynamodb.model.ExpectedAttributeValue;
import com.amazonaws.services.dynamodb.model.PutItemRequest;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class PutItemRequestMarshaller {
    private String getString(String str) {
        return str == null ? "" : str;
    }

    public j<PutItemRequest> marshall(PutItemRequest putItemRequest) {
        if (putItemRequest == null) {
            throw new a("Invalid argument passed to marshall(...)");
        }
        h hVar = new h(putItemRequest, "AmazonDynamoDB");
        hVar.a("X-Amz-Target", "DynamoDB_20111205.PutItem");
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
            if (putItemRequest.getTableName() != null) {
                fVar.a("TableName").a((Object) putItemRequest.getTableName());
            }
            if (putItemRequest.getItem() != null) {
                fVar.a("Item");
                fVar.d();
                for (Map.Entry<String, AttributeValue> entry : putItemRequest.getItem().entrySet()) {
                    if (entry.getValue() != null) {
                        fVar.a(entry.getKey());
                        fVar.d();
                        if (entry.getValue().getS() != null) {
                            fVar.a("S").a((Object) entry.getValue().getS());
                        }
                        if (entry.getValue().getN() != null) {
                            fVar.a("N").a((Object) entry.getValue().getN());
                        }
                        if (entry.getValue().getB() != null) {
                            fVar.a("B").a(entry.getValue().getB());
                        }
                        List<String> ss = entry.getValue().getSS();
                        if (ss != null && ss.size() > 0) {
                            fVar.a("SS");
                            fVar.a();
                            for (String str2 : ss) {
                                if (str2 != null) {
                                    fVar.a((Object) str2);
                                }
                            }
                            fVar.b();
                        }
                        List<String> ns = entry.getValue().getNS();
                        if (ns != null && ns.size() > 0) {
                            fVar.a("NS");
                            fVar.a();
                            for (String str3 : ns) {
                                if (str3 != null) {
                                    fVar.a((Object) str3);
                                }
                            }
                            fVar.b();
                        }
                        List<ByteBuffer> bs = entry.getValue().getBS();
                        if (bs != null && bs.size() > 0) {
                            fVar.a("BS");
                            fVar.a();
                            for (ByteBuffer byteBuffer : bs) {
                                if (byteBuffer != null) {
                                    fVar.a(byteBuffer);
                                }
                            }
                            fVar.b();
                        }
                        fVar.c();
                    }
                }
                fVar.c();
            }
            if (putItemRequest.getExpected() != null) {
                fVar.a("Expected");
                fVar.d();
                for (Map.Entry<String, ExpectedAttributeValue> entry2 : putItemRequest.getExpected().entrySet()) {
                    if (entry2.getValue() != null) {
                        fVar.a(entry2.getKey());
                        fVar.d();
                        AttributeValue value = entry2.getValue().getValue();
                        if (value != null) {
                            fVar.a("Value");
                            fVar.d();
                            if (value.getS() != null) {
                                fVar.a("S").a((Object) value.getS());
                            }
                            if (value.getN() != null) {
                                fVar.a("N").a((Object) value.getN());
                            }
                            if (value.getB() != null) {
                                fVar.a("B").a(value.getB());
                            }
                            List<String> ss2 = value.getSS();
                            if (ss2 != null && ss2.size() > 0) {
                                fVar.a("SS");
                                fVar.a();
                                for (String str4 : ss2) {
                                    if (str4 != null) {
                                        fVar.a((Object) str4);
                                    }
                                }
                                fVar.b();
                            }
                            List<String> ns2 = value.getNS();
                            if (ns2 != null && ns2.size() > 0) {
                                fVar.a("NS");
                                fVar.a();
                                for (String str5 : ns2) {
                                    if (str5 != null) {
                                        fVar.a((Object) str5);
                                    }
                                }
                                fVar.b();
                            }
                            List<ByteBuffer> bs2 = value.getBS();
                            if (bs2 != null && bs2.size() > 0) {
                                fVar.a("BS");
                                fVar.a();
                                for (ByteBuffer byteBuffer2 : bs2) {
                                    if (byteBuffer2 != null) {
                                        fVar.a(byteBuffer2);
                                    }
                                }
                                fVar.b();
                            }
                            fVar.c();
                        }
                        if (entry2.getValue().isExists() != null) {
                            fVar.a("Exists").a(entry2.getValue().isExists());
                        }
                        fVar.c();
                    }
                }
                fVar.c();
            }
            if (putItemRequest.getReturnValues() != null) {
                fVar.a("ReturnValues").a((Object) putItemRequest.getReturnValues());
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
