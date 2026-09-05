package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.a;
import com.amazonaws.c.f;
import com.amazonaws.h;
import com.amazonaws.j;
import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.amazonaws.services.dynamodb.model.AttributeValue;
import com.amazonaws.services.dynamodb.model.BatchWriteItemRequest;
import com.amazonaws.services.dynamodb.model.DeleteRequest;
import com.amazonaws.services.dynamodb.model.Key;
import com.amazonaws.services.dynamodb.model.PutRequest;
import com.amazonaws.services.dynamodb.model.WriteRequest;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class BatchWriteItemRequestMarshaller {
    private String getString(String str) {
        return str == null ? "" : str;
    }

    public j<BatchWriteItemRequest> marshall(BatchWriteItemRequest batchWriteItemRequest) {
        if (batchWriteItemRequest == null) {
            throw new a("Invalid argument passed to marshall(...)");
        }
        h hVar = new h(batchWriteItemRequest, "AmazonDynamoDB");
        hVar.a("X-Amz-Target", "DynamoDB_20111205.BatchWriteItem");
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
            if (batchWriteItemRequest.getRequestItems() != null) {
                fVar.a("RequestItems");
                fVar.d();
                for (Map.Entry<String, List<WriteRequest>> entry : batchWriteItemRequest.getRequestItems().entrySet()) {
                    if (entry.getValue() != null) {
                        fVar.a(entry.getKey());
                        fVar.a();
                        for (WriteRequest writeRequest : entry.getValue()) {
                            if (writeRequest != null) {
                                fVar.d();
                                PutRequest putRequest = writeRequest.getPutRequest();
                                if (putRequest != null) {
                                    fVar.a("PutRequest");
                                    fVar.d();
                                    if (putRequest.getItem() != null) {
                                        fVar.a("Item");
                                        fVar.d();
                                        for (Map.Entry<String, AttributeValue> entry2 : putRequest.getItem().entrySet()) {
                                            if (entry2.getValue() != null) {
                                                fVar.a(entry2.getKey());
                                                fVar.d();
                                                if (entry2.getValue().getS() != null) {
                                                    fVar.a("S").a((Object) entry2.getValue().getS());
                                                }
                                                if (entry2.getValue().getN() != null) {
                                                    fVar.a("N").a((Object) entry2.getValue().getN());
                                                }
                                                if (entry2.getValue().getB() != null) {
                                                    fVar.a("B").a(entry2.getValue().getB());
                                                }
                                                List<String> ss = entry2.getValue().getSS();
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
                                                List<String> ns = entry2.getValue().getNS();
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
                                                List<ByteBuffer> bs = entry2.getValue().getBS();
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
                                    fVar.c();
                                }
                                DeleteRequest deleteRequest = writeRequest.getDeleteRequest();
                                if (deleteRequest != null) {
                                    fVar.a("DeleteRequest");
                                    fVar.d();
                                    Key key = deleteRequest.getKey();
                                    if (key != null) {
                                        fVar.a("Key");
                                        fVar.d();
                                        AttributeValue hashKeyElement = key.getHashKeyElement();
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
                                                for (String str4 : ss2) {
                                                    if (str4 != null) {
                                                        fVar.a((Object) str4);
                                                    }
                                                }
                                                fVar.b();
                                            }
                                            List<String> ns2 = hashKeyElement.getNS();
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
                                        AttributeValue rangeKeyElement = key.getRangeKeyElement();
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
                                                for (String str6 : ss3) {
                                                    if (str6 != null) {
                                                        fVar.a((Object) str6);
                                                    }
                                                }
                                                fVar.b();
                                            }
                                            List<String> ns3 = rangeKeyElement.getNS();
                                            if (ns3 != null && ns3.size() > 0) {
                                                fVar.a("NS");
                                                fVar.a();
                                                for (String str7 : ns3) {
                                                    if (str7 != null) {
                                                        fVar.a((Object) str7);
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
                                }
                                fVar.c();
                            }
                        }
                        fVar.b();
                    }
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
