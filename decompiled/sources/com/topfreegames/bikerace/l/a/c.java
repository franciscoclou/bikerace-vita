package com.topfreegames.bikerace.l.a;

import android.util.Log;
import com.amazonaws.services.dynamodb.model.AttributeAction;
import com.amazonaws.services.dynamodb.model.AttributeValue;
import com.amazonaws.services.dynamodb.model.AttributeValueUpdate;
import com.amazonaws.services.dynamodb.model.ComparisonOperator;
import com.amazonaws.services.dynamodb.model.Condition;
import com.amazonaws.services.dynamodb.model.Key;
import com.amazonaws.services.dynamodb.model.QueryRequest;
import com.amazonaws.services.dynamodb.model.UpdateItemRequest;
import com.facebook.AppEventsConstants;
import com.topfreegames.bikerace.l.f;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: DynamoUserRepository.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c extends b implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ boolean f1282a;
    private final String b;
    private final com.amazonaws.services.dynamodb.a c;

    static {
        f1282a = !c.class.desiredAssertionStatus();
    }

    public c(String str, com.amazonaws.services.dynamodb.a aVar) {
        if (str == null || "".equals(str)) {
            throw new IllegalArgumentException("Missing 'tableName' argument.");
        }
        if (aVar == null) {
            throw new IllegalArgumentException("Missing 'AmazonDynamoDBClient' argument.");
        }
        this.b = str;
        this.c = aVar;
    }

    @Override // com.topfreegames.bikerace.l.f
    public com.topfreegames.bikerace.b.b a(String str) {
        Key keyD = d(str);
        List<Map<String, AttributeValue>> items = this.c.a(new QueryRequest().withTableName(this.b).withHashKeyValue(keyD.getHashKeyElement()).withRangeKeyCondition(new Condition().withComparisonOperator(ComparisonOperator.EQ).withAttributeValueList(keyD.getRangeKeyElement()))).getItems();
        if (!items.isEmpty()) {
            Map<String, AttributeValue> map = items.get(0);
            com.topfreegames.bikerace.b.b bVar = new com.topfreegames.bikerace.b.b();
            bVar.a(map.get("id").getS());
            if (map.get("adts") != null) {
                bVar.a(new HashSet(map.get("adts").getSS()));
            }
            if (map.get("dts") != null) {
                bVar.b(new HashSet(map.get("dts").getSS()));
            }
            if (map.get("ops") != null) {
                bVar.c(new HashSet(map.get("ops").getSS()));
            }
            if (map.get("bad") != null) {
                String n = map.get("bad").getN();
                if (n == null) {
                    n = AppEventsConstants.EVENT_PARAM_VALUE_NO;
                }
                bVar.a(Integer.valueOf(n));
            }
            if (map.get("grk") != null) {
                String n2 = map.get("grk").getN();
                if (n2 == null) {
                    n2 = AppEventsConstants.EVENT_PARAM_VALUE_NO;
                }
                bVar.b(Integer.valueOf(n2));
            }
            if (map.get("werased") != null) {
                String n3 = map.get("werased").getN();
                if (n3 == null) {
                    n3 = AppEventsConstants.EVENT_PARAM_VALUE_NO;
                }
                bVar.c(Integer.valueOf(n3));
            }
            Log.i("DynamoUserRepository", String.format("Retrived User(id:%s) from remote database.", bVar.c()));
            return bVar;
        }
        com.topfreegames.bikerace.b.b bVar2 = new com.topfreegames.bikerace.b.b(str);
        Log.i("DynamoUserRepository", String.format("User(id:%s) not found on remote database. Returning a blank user.", bVar2.c()));
        return bVar2;
    }

    @Override // com.topfreegames.bikerace.l.f
    public void a(String str, String str2) {
        a(str, str2, AttributeAction.ADD);
        a(str2, str, AttributeAction.ADD);
    }

    @Override // com.topfreegames.bikerace.l.f
    public void b(String str, String str2) {
        a(str, str2, AttributeAction.DELETE);
        a(str2, str, AttributeAction.DELETE);
    }

    private boolean e(String str) {
        return str != null && str.matches("^[0-9a-f]+$");
    }

    private void a(String str, String str2, AttributeAction attributeAction) {
        try {
            if (!f1282a && !e(str)) {
                throw new AssertionError();
            }
            if (!f1282a && !e(str2)) {
                throw new AssertionError();
            }
            if (str.equals(str2)) {
                throw new IllegalArgumentException("Invalid user ids. Can't associate user with himself.");
            }
            AttributeValueUpdate attributeValueUpdate = new AttributeValueUpdate(new AttributeValue((List<String>) Arrays.asList(str2)), attributeAction);
            Key keyD = d(str);
            HashMap map = new HashMap();
            map.put("ops", attributeValueUpdate);
            this.c.a(new UpdateItemRequest(this.b, keyD, map));
        } catch (com.amazonaws.a e) {
            Log.e("DynamoUserRepository", e.getMessage());
            Throwable cause = e.getCause();
            if (cause != null) {
                Throwable cause2 = cause.getCause();
                if (cause2 != null && IOException.class.equals(cause2.getClass())) {
                    throw new d();
                }
                return;
            }
            throw e;
        }
    }

    private void a(String str, String str2, int i) {
        try {
            if (!f1282a && !e(str)) {
                throw new AssertionError();
            }
            AttributeValueUpdate attributeValueUpdate = new AttributeValueUpdate(new AttributeValue().withN(Integer.toString(i)), AttributeAction.PUT);
            Key keyD = d(str);
            HashMap map = new HashMap();
            map.put(str2, attributeValueUpdate);
            this.c.a(new UpdateItemRequest(this.b, keyD, map));
        } catch (com.amazonaws.a e) {
            Log.e("DynamoUserRepository", e.getMessage());
            Throwable cause = e.getCause();
            if (cause != null && IOException.class.equals(cause.getClass())) {
                throw new d();
            }
            throw e;
        }
    }

    @Override // com.topfreegames.bikerace.l.f
    public void a(String str, int i) {
        a(str, "bad", i);
    }

    @Override // com.topfreegames.bikerace.l.f
    public void b(String str, int i) {
        a(str, "grk", i);
    }

    public void c(String str, String str2) {
        b(str, str2, AttributeAction.ADD);
    }

    public void d(String str, String str2) {
        b(str, str2, AttributeAction.DELETE);
    }

    private void b(String str, String str2, AttributeAction attributeAction) {
        try {
            if (!f1282a && !e(str)) {
                throw new AssertionError();
            }
            AttributeValueUpdate attributeValueUpdate = new AttributeValueUpdate(new AttributeValue((List<String>) Arrays.asList(str2)), attributeAction);
            Key keyD = d(str);
            HashMap map = new HashMap();
            map.put("adts", attributeValueUpdate);
            this.c.a(new UpdateItemRequest(this.b, keyD, map));
        } catch (com.amazonaws.a e) {
            Log.e("DynamoUserRepository", e.getMessage());
            Throwable cause = e.getCause();
            if (cause != null && cause.getClass().equals(IOException.class)) {
                throw new d();
            }
            throw new d();
        }
    }

    @Override // com.topfreegames.bikerace.l.f
    public void a(com.topfreegames.bikerace.b.b bVar, Date date) {
        throw new Error("Interface not implemented.");
    }

    @Override // com.topfreegames.bikerace.l.f
    public void d(String str, int i) {
        com.topfreegames.bikerace.b.b bVarA = a(str);
        Integer numE = null;
        if (bVarA != null) {
            numE = bVarA.e();
        }
        if (numE == null) {
            numE = 0;
        }
        a(str, "werased", numE.intValue() + i);
    }

    @Override // com.topfreegames.bikerace.l.f
    public void c(String str, int i) {
        a(str, "werased", i);
    }

    @Override // com.topfreegames.bikerace.l.f
    public boolean b(String str) {
        Key keyD = d(str);
        return !this.c.a(new QueryRequest().withTableName(this.b).withHashKeyValue(keyD.getHashKeyElement()).withRangeKeyCondition(new Condition().withComparisonOperator(ComparisonOperator.EQ).withAttributeValueList(keyD.getRangeKeyElement()))).getItems().isEmpty();
    }
}
