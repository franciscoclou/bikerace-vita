package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.a;
import com.amazonaws.c.f;
import com.amazonaws.h;
import com.amazonaws.j;
import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.amazonaws.services.dynamodb.model.AttributeValue;
import com.amazonaws.services.dynamodb.model.Condition;
import com.amazonaws.services.dynamodb.model.Key;
import com.amazonaws.services.dynamodb.model.ScanRequest;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ScanRequestMarshaller {
    private String getString(String str) {
        return str == null ? "" : str;
    }

    public j<ScanRequest> marshall(ScanRequest scanRequest) {
        if (scanRequest == null) {
            throw new a("Invalid argument passed to marshall(...)");
        }
        h hVar = new h(scanRequest, "AmazonDynamoDB");
        hVar.a("X-Amz-Target", "DynamoDB_20111205.Scan");
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
            if (scanRequest.getTableName() != null) {
                fVar.a("TableName").a((Object) scanRequest.getTableName());
            }
            List<String> attributesToGet = scanRequest.getAttributesToGet();
            if (attributesToGet != null && attributesToGet.size() > 0) {
                fVar.a("AttributesToGet");
                fVar.a();
                for (String str2 : attributesToGet) {
                    if (str2 != null) {
                        fVar.a((Object) str2);
                    }
                }
                fVar.b();
            }
            if (scanRequest.getLimit() != null) {
                fVar.a("Limit").a(scanRequest.getLimit());
            }
            if (scanRequest.isCount() != null) {
                fVar.a("Count").a(scanRequest.isCount());
            }
            if (scanRequest.getScanFilter() != null) {
                fVar.a("ScanFilter");
                fVar.d();
                for (Map.Entry<String, Condition> entry : scanRequest.getScanFilter().entrySet()) {
                    if (entry.getValue() != null) {
                        fVar.a(entry.getKey());
                        fVar.d();
                        List<AttributeValue> attributeValueList = entry.getValue().getAttributeValueList();
                        if (attributeValueList != null && attributeValueList.size() > 0) {
                            fVar.a("AttributeValueList");
                            fVar.a();
                            for (AttributeValue attributeValue : attributeValueList) {
                                if (attributeValue != null) {
                                    fVar.d();
                                    if (attributeValue.getS() != null) {
                                        fVar.a("S").a((Object) attributeValue.getS());
                                    }
                                    if (attributeValue.getN() != null) {
                                        fVar.a("N").a((Object) attributeValue.getN());
                                    }
                                    if (attributeValue.getB() != null) {
                                        fVar.a("B").a(attributeValue.getB());
                                    }
                                    List<String> ss = attributeValue.getSS();
                                    if (ss != null && ss.size() > 0) {
                                        fVar.a("SS");
                                        fVar.a();
                                        for (String str3 : ss) {
                                            if (str3 != null) {
                                                fVar.a((Object) str3);
                                            }
                                        }
                                        fVar.b();
                                    }
                                    List<String> ns = attributeValue.getNS();
                                    if (ns != null && ns.size() > 0) {
                                        fVar.a("NS");
                                        fVar.a();
                                        for (String str4 : ns) {
                                            if (str4 != null) {
                                                fVar.a((Object) str4);
                                            }
                                        }
                                        fVar.b();
                                    }
                                    List<ByteBuffer> bs = attributeValue.getBS();
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
                            fVar.b();
                        }
                        if (entry.getValue().getComparisonOperator() != null) {
                            fVar.a("ComparisonOperator").a((Object) entry.getValue().getComparisonOperator());
                        }
                        fVar.c();
                    }
                }
                fVar.c();
            }
            Key exclusiveStartKey = scanRequest.getExclusiveStartKey();
            if (exclusiveStartKey != null) {
                fVar.a("ExclusiveStartKey");
                fVar.d();
                AttributeValue hashKeyElement = exclusiveStartKey.getHashKeyElement();
                if (hashKeyElement != null) {
                    fVar.a("HashKeyElement");
                    fVar.d();
                    if (hashKeyElement.getS() != null) {
                        fVar.a("S").a((Object) hashKeyElement.getS());
                    }
                    if (hashKeyElement.getN() != null) {
                        fVar.a("N").a((Object) hashKeyElement.getN());
                    }
                    if (hashKeyElement.getB() != null) {
                        fVar.a("B").a(hashKeyElement.getB());
                    }
                    List<String> ss2 = hashKeyElement.getSS();
                    if (ss2 != null && ss2.size() > 0) {
                        fVar.a("SS");
                        fVar.a();
                        for (String str5 : ss2) {
                            if (str5 != null) {
                                fVar.a((Object) str5);
                            }
                        }
                        fVar.b();
                    }
                    List<String> ns2 = hashKeyElement.getNS();
                    if (ns2 != null && ns2.size() > 0) {
                        fVar.a("NS");
                        fVar.a();
                        for (String str6 : ns2) {
                            if (str6 != null) {
                                fVar.a((Object) str6);
                            }
                        }
                        fVar.b();
                    }
                    List<ByteBuffer> bs2 = hashKeyElement.getBS();
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
                AttributeValue rangeKeyElement = exclusiveStartKey.getRangeKeyElement();
                if (rangeKeyElement != null) {
                    fVar.a("RangeKeyElement");
                    fVar.d();
                    if (rangeKeyElement.getS() != null) {
                        fVar.a("S").a((Object) rangeKeyElement.getS());
                    }
                    if (rangeKeyElement.getN() != null) {
                        fVar.a("N").a((Object) rangeKeyElement.getN());
                    }
                    if (rangeKeyElement.getB() != null) {
                        fVar.a("B").a(rangeKeyElement.getB());
                    }
                    List<String> ss3 = rangeKeyElement.getSS();
                    if (ss3 != null && ss3.size() > 0) {
                        fVar.a("SS");
                        fVar.a();
                        for (String str7 : ss3) {
                            if (str7 != null) {
                                fVar.a((Object) str7);
                            }
                        }
                        fVar.b();
                    }
                    List<String> ns3 = rangeKeyElement.getNS();
                    if (ns3 != null && ns3.size() > 0) {
                        fVar.a("NS");
                        fVar.a();
                        for (String str8 : ns3) {
                            if (str8 != null) {
                                fVar.a((Object) str8);
                            }
                        }
                        fVar.b();
                    }
                    List<ByteBuffer> bs3 = rangeKeyElement.getBS();
                    if (bs3 != null && bs3.size() > 0) {
                        fVar.a("BS");
                        fVar.a();
                        for (ByteBuffer byteBuffer3 : bs3) {
                            if (byteBuffer3 != null) {
                                fVar.a(byteBuffer3);
                            }
                        }
                        fVar.b();
                    }
                    fVar.c();
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
