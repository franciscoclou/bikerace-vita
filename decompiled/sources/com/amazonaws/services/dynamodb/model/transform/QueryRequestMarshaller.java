package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.a;
import com.amazonaws.c.f;
import com.amazonaws.h;
import com.amazonaws.j;
import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.amazonaws.services.dynamodb.model.AttributeValue;
import com.amazonaws.services.dynamodb.model.Condition;
import com.amazonaws.services.dynamodb.model.Key;
import com.amazonaws.services.dynamodb.model.QueryRequest;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class QueryRequestMarshaller {
    private String getString(String str) {
        return str == null ? "" : str;
    }

    public j<QueryRequest> marshall(QueryRequest queryRequest) {
        if (queryRequest == null) {
            throw new a("Invalid argument passed to marshall(...)");
        }
        h hVar = new h(queryRequest, "AmazonDynamoDB");
        hVar.a("X-Amz-Target", "DynamoDB_20111205.Query");
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
            if (queryRequest.getTableName() != null) {
                fVar.a("TableName").a((Object) queryRequest.getTableName());
            }
            List<String> attributesToGet = queryRequest.getAttributesToGet();
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
            if (queryRequest.getLimit() != null) {
                fVar.a("Limit").a(queryRequest.getLimit());
            }
            if (queryRequest.isConsistentRead() != null) {
                fVar.a("ConsistentRead").a(queryRequest.isConsistentRead());
            }
            if (queryRequest.isCount() != null) {
                fVar.a("Count").a(queryRequest.isCount());
            }
            AttributeValue hashKeyValue = queryRequest.getHashKeyValue();
            if (hashKeyValue != null) {
                fVar.a("HashKeyValue");
                fVar.d();
                if (hashKeyValue.getS() != null) {
                    fVar.a("S").a((Object) hashKeyValue.getS());
                }
                if (hashKeyValue.getN() != null) {
                    fVar.a("N").a((Object) hashKeyValue.getN());
                }
                if (hashKeyValue.getB() != null) {
                    fVar.a("B").a(hashKeyValue.getB());
                }
                List<String> ss = hashKeyValue.getSS();
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
                List<String> ns = hashKeyValue.getNS();
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
                List<ByteBuffer> bs = hashKeyValue.getBS();
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
            Condition rangeKeyCondition = queryRequest.getRangeKeyCondition();
            if (rangeKeyCondition != null) {
                fVar.a("RangeKeyCondition");
                fVar.d();
                List<AttributeValue> attributeValueList = rangeKeyCondition.getAttributeValueList();
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
                            List<String> ss2 = attributeValue.getSS();
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
                            List<String> ns2 = attributeValue.getNS();
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
                            List<ByteBuffer> bs2 = attributeValue.getBS();
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
                    }
                    fVar.b();
                }
                if (rangeKeyCondition.getComparisonOperator() != null) {
                    fVar.a("ComparisonOperator").a((Object) rangeKeyCondition.getComparisonOperator());
                }
                fVar.c();
            }
            if (queryRequest.isScanIndexForward() != null) {
                fVar.a("ScanIndexForward").a(queryRequest.isScanIndexForward());
            }
            Key exclusiveStartKey = queryRequest.getExclusiveStartKey();
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
                    List<String> ss3 = hashKeyElement.getSS();
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
                    List<String> ns3 = hashKeyElement.getNS();
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
                    List<ByteBuffer> bs3 = hashKeyElement.getBS();
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
                    List<String> ss4 = rangeKeyElement.getSS();
                    if (ss4 != null && ss4.size() > 0) {
                        fVar.a("SS");
                        fVar.a();
                        for (String str9 : ss4) {
                            if (str9 != null) {
                                fVar.a((Object) str9);
                            }
                        }
                        fVar.b();
                    }
                    List<String> ns4 = rangeKeyElement.getNS();
                    if (ns4 != null && ns4.size() > 0) {
                        fVar.a("NS");
                        fVar.a();
                        for (String str10 : ns4) {
                            if (str10 != null) {
                                fVar.a((Object) str10);
                            }
                        }
                        fVar.b();
                    }
                    List<ByteBuffer> bs4 = rangeKeyElement.getBS();
                    if (bs4 != null && bs4.size() > 0) {
                        fVar.a("BS");
                        fVar.a();
                        for (ByteBuffer byteBuffer4 : bs4) {
                            if (byteBuffer4 != null) {
                                fVar.a(byteBuffer4);
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
