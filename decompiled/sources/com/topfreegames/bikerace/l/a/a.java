package com.topfreegames.bikerace.l.a;

import android.util.Log;
import com.amazonaws.services.dynamodb.model.AttributeValue;
import com.amazonaws.services.dynamodb.model.BatchGetItemRequest;
import com.amazonaws.services.dynamodb.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodb.model.DeleteItemRequest;
import com.amazonaws.services.dynamodb.model.ExpectedAttributeValue;
import com.amazonaws.services.dynamodb.model.KeysAndAttributes;
import com.amazonaws.services.dynamodb.model.PutItemRequest;
import com.amazonaws.services.dynamodb.model.PutItemResult;
import com.facebook.AppEventsConstants;
import com.topfreegames.bikerace.m.e;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: DynamoGameSessionRepository.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a extends b implements com.topfreegames.bikerace.l.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f1281a;
    private final com.amazonaws.services.dynamodb.a b;

    public a(String str, com.amazonaws.services.dynamodb.a aVar) {
        this.f1281a = str;
        this.b = aVar;
    }

    @Override // com.topfreegames.bikerace.l.b
    public List<com.topfreegames.bikerace.b.a> a(String[] strArr, String str, boolean z) {
        List listAsList = Arrays.asList(strArr);
        Collections.shuffle(listAsList);
        List listA = e.a(listAsList, 30);
        ArrayList arrayList = new ArrayList(strArr.length);
        Iterator it = listA.iterator();
        while (it.hasNext()) {
            arrayList.addAll(a((List) it.next(), str, z, 0));
        }
        return arrayList;
    }

    private List<com.topfreegames.bikerace.b.a> a(List<String> list, String str, boolean z, int i) {
        BatchGetItemRequest batchGetItemRequest = new BatchGetItemRequest();
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(d(it.next()));
        }
        HashMap map = new HashMap();
        map.put(this.f1281a, new KeysAndAttributes().withKeys(arrayList));
        batchGetItemRequest.setRequestItems(map);
        List<Map<String, AttributeValue>> items = this.b.a(batchGetItemRequest).getResponses().get(this.f1281a).getItems();
        ArrayList arrayList2 = new ArrayList(items.size());
        ArrayList arrayList3 = new ArrayList();
        arrayList3.addAll(list);
        Iterator<Map<String, AttributeValue>> it2 = items.iterator();
        while (it2.hasNext()) {
            com.topfreegames.bikerace.b.a aVarA = a(it2.next());
            a(aVarA, str);
            Log.d("DynamoGameSessionRepository", String.format("Retrived GameSession(id:%s) from remote database.", aVarA.a()));
            if (!z || aVarA.i().booleanValue()) {
                arrayList2.add(aVarA);
            }
            arrayList3.remove(aVarA.a());
        }
        if (arrayList3.size() > 0 && i < 2) {
            arrayList2.addAll(a(arrayList3, str, z, i + 1));
        }
        return arrayList2;
    }

    private void a(com.topfreegames.bikerace.b.a aVar, String str) {
        if (aVar != null && !aVar.a(false)) {
            Log.e("DynamoGameSessionRepository", String.format("GameSession(id:%s) is not valid for playing", aVar.a()));
            aVar.m(str);
            a(aVar, false);
        }
    }

    /* JADX WARN: Code duplicated, block: B:29:0x00b1  */
    public void a(com.topfreegames.bikerace.b.a aVar, boolean z) {
        PutItemResult putItemResultA;
        PutItemRequest putItemRequestA = a(aVar.a(), this.f1281a, c(aVar));
        if (!z || aVar.J()) {
            putItemResultA = null;
        } else {
            Integer numT = aVar.t();
            if (aVar.j() != null && aVar.D() == null && aVar.E() == null && numT.intValue() > 0) {
                numT = Integer.valueOf(numT.intValue() - 1);
            }
            HashMap map = new HashMap();
            map.put("ltn", new ExpectedAttributeValue(new AttributeValue(numT.toString())));
            putItemRequestA.setExpected(map);
            if (aVar.D() == null && aVar.E() == null) {
                putItemResultA = null;
            } else {
                try {
                    putItemResultA = this.b.a(putItemRequestA);
                } catch (ConditionalCheckFailedException e) {
                    e.printStackTrace();
                    HashMap map2 = new HashMap();
                    map2.put("ltn", new ExpectedAttributeValue(new AttributeValue(Integer.toString(numT.intValue() - 1))));
                    putItemRequestA.setExpected(map2);
                    putItemResultA = null;
                }
            }
        }
        if (putItemResultA == null) {
            this.b.a(putItemRequestA);
        }
        Log.i("DynamoGameSessionRepository", String.format("Saved gameSession(id:%s) in remote database.", aVar.a()));
    }

    @Override // com.topfreegames.bikerace.l.b
    public void a(com.topfreegames.bikerace.b.a aVar) {
        a(aVar, true);
    }

    private com.topfreegames.bikerace.b.a a(Map<String, AttributeValue> map) {
        com.topfreegames.bikerace.b.a aVar = new com.topfreegames.bikerace.b.a();
        if (map.get("id") != null) {
            aVar.b(map.get("id").getS());
        }
        if (map.get("pids") != null) {
            aVar.a(map.get("pids").getSS());
        }
        if (map.get("cru") != null) {
            aVar.c(map.get("cru").getS());
        }
        if (map.get("cna") != null) {
            aVar.d(map.get("cna").getS());
        }
        if (map.get("cwc") != null) {
            aVar.a(Integer.valueOf(Integer.parseInt(map.get("cwc").getS())));
        }
        if (map.get("crv") != null) {
            aVar.m(Integer.valueOf(Integer.parseInt(map.get("crv").getS())));
        }
        if (map.get("opu") != null) {
            aVar.e(map.get("opu").getS());
        }
        if (map.get("ona") != null) {
            aVar.f(map.get("ona").getS());
        }
        if (map.get("owc") != null) {
            aVar.b(Integer.valueOf(Integer.parseInt(map.get("owc").getS())));
        }
        if (map.get("opv") != null) {
            aVar.n(Integer.valueOf(Integer.parseInt(map.get("opv").getS())));
        }
        if (map.get("gia") != null) {
            aVar.a(Boolean.valueOf(map.get("gia").getS().equals(AppEventsConstants.EVENT_PARAM_VALUE_YES)));
        } else {
            aVar.a((Boolean) true);
        }
        if (map.get("ctn") != null) {
            aVar.c(Integer.valueOf(Integer.parseInt(map.get("ctn").getS())));
        }
        if (map.get("ctw") != null) {
            aVar.d(Integer.valueOf(Integer.parseInt(map.get("ctw").getS())));
        }
        if (map.get("ctl") != null) {
            aVar.e(Integer.valueOf(Integer.parseInt(map.get("ctl").getS())));
        }
        if (map.get("ctcid") != null) {
            aVar.g(map.get("ctcid").getS());
        }
        if (map.get("ctct") != null) {
            aVar.a(Float.valueOf(Float.parseFloat(map.get("ctct").getS())));
        }
        if (map.get("ctcd") != null) {
            aVar.b(com.topfreegames.bikerace.l.d.a(map.get("ctcd").getS()));
        }
        if (map.get("ctot") != null) {
            aVar.b(Float.valueOf(Float.parseFloat(map.get("ctot").getS())));
        }
        if (map.get("ctod") != null) {
            aVar.c(com.topfreegames.bikerace.l.d.a(map.get("ctod").getS()));
        }
        if (map.get("ctcm") != null) {
            aVar.f(Integer.valueOf(Integer.parseInt(map.get("ctcm").getS())));
        }
        if (map.get("ctom") != null) {
            aVar.g(Integer.valueOf(Integer.parseInt(map.get("ctom").getS())));
        }
        if (map.get("ctccm") != null) {
            aVar.h(map.get("ctccm").getS());
        }
        if (map.get("ctocm") != null) {
            aVar.i(map.get("ctocm").getS());
        }
        if (map.get("ltn") != null) {
            aVar.h(Integer.valueOf(Integer.parseInt(map.get("ltn").getS())));
        }
        if (map.get("ltw") != null) {
            aVar.i(Integer.valueOf(Integer.parseInt(map.get("ltw").getS())));
        }
        if (map.get("ltl") != null) {
            aVar.j(Integer.valueOf(Integer.parseInt(map.get("ltl").getS())));
        }
        if (map.get("ltcid") != null) {
            aVar.j(map.get("ltcid").getS());
        }
        if (map.get("ltct") != null) {
            aVar.c(Float.valueOf(Float.parseFloat(map.get("ltct").getS())));
        }
        if (map.get("ltcd") != null) {
            aVar.d(com.topfreegames.bikerace.l.d.a(map.get("ltcd").getS()));
        }
        if (map.get("ltot") != null) {
            aVar.d(Float.valueOf(Float.parseFloat(map.get("ltot").getS())));
        }
        if (map.get("ltod") != null) {
            aVar.e(com.topfreegames.bikerace.l.d.a(map.get("ltod").getS()));
        }
        if (map.get("ltcm") != null) {
            aVar.k(Integer.valueOf(Integer.parseInt(map.get("ltcm").getS())));
        }
        if (map.get("ltom") != null) {
            aVar.l(Integer.valueOf(Integer.parseInt(map.get("ltom").getS())));
        }
        if (map.get("ltccm") != null) {
            aVar.k(map.get("ltccm").getS());
        }
        if (map.get("ltocm") != null) {
            aVar.l(map.get("ltocm").getS());
        }
        return aVar;
    }

    private Map<String, AttributeValue> c(com.topfreegames.bikerace.b.a aVar) {
        HashMap map = new HashMap();
        if (aVar.b() != null) {
            map.put("pids", new AttributeValue(aVar.b()));
        }
        if (aVar.c() != null) {
            map.put("cru", new AttributeValue(aVar.c()));
        }
        if (aVar.d() != null) {
            map.put("cna", new AttributeValue(aVar.d()));
        }
        if (aVar.e() != null) {
            map.put("cwc", new AttributeValue(Integer.toString(aVar.e().intValue())));
        }
        if (aVar.H() != null) {
            map.put("crv", new AttributeValue(Integer.toString(aVar.H().intValue())));
        }
        if (aVar.f() != null) {
            map.put("opu", new AttributeValue(aVar.f()));
        }
        if (aVar.g() != null) {
            map.put("ona", new AttributeValue(aVar.g()));
        }
        if (aVar.h() != null) {
            map.put("owc", new AttributeValue(Integer.toString(aVar.h().intValue())));
        }
        if (aVar.I() != null) {
            map.put("opv", new AttributeValue(Integer.toString(aVar.I().intValue())));
        }
        if (aVar.i() != null) {
            map.put("gia", new AttributeValue(aVar.i().booleanValue() ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO));
        }
        if (aVar.j() != null) {
            map.put("ctn", new AttributeValue(Integer.toString(aVar.j().intValue())));
        }
        if (aVar.k() != null) {
            map.put("ctw", new AttributeValue(Integer.toString(aVar.k().intValue())));
        }
        if (aVar.l() != null) {
            map.put("ctl", new AttributeValue(Integer.toString(aVar.l().intValue())));
        }
        if (aVar.m() != null) {
            map.put("ctcid", new AttributeValue(aVar.m()));
        }
        if (aVar.n() != null) {
            map.put("ctct", new AttributeValue(Float.toString(aVar.n().floatValue())));
        }
        if (aVar.D() != null) {
            map.put("ctcd", new AttributeValue(com.topfreegames.bikerace.l.d.a(aVar.D())));
        }
        if (aVar.o() != null) {
            map.put("ctot", new AttributeValue(Float.toString(aVar.o().floatValue())));
        }
        if (aVar.E() != null) {
            map.put("ctod", new AttributeValue(com.topfreegames.bikerace.l.d.a(aVar.E())));
        }
        if (aVar.p() != null) {
            map.put("ctcm", new AttributeValue(Integer.toString(aVar.p().intValue())));
        }
        if (aVar.q() != null) {
            map.put("ctom", new AttributeValue(Integer.toString(aVar.q().intValue())));
        }
        if (aVar.r() != null) {
            map.put("ctccm", new AttributeValue(aVar.r()));
        }
        if (aVar.s() != null) {
            map.put("ctocm", new AttributeValue(aVar.s()));
        }
        if (aVar.t() != null) {
            map.put("ltn", new AttributeValue(Integer.toString(aVar.t().intValue())));
        }
        if (aVar.u() != null) {
            map.put("ltw", new AttributeValue(Integer.toString(aVar.u().intValue())));
        }
        if (aVar.v() != null) {
            map.put("ltl", new AttributeValue(Integer.toString(aVar.v().intValue())));
        }
        if (aVar.w() != null) {
            map.put("ltcid", new AttributeValue(aVar.w()));
        }
        if (aVar.x() != null) {
            map.put("ltct", new AttributeValue(Float.toString(aVar.x().floatValue())));
        }
        if (aVar.F() != null) {
            map.put("ltcd", new AttributeValue(com.topfreegames.bikerace.l.d.a(aVar.F())));
        }
        if (aVar.y() != null) {
            map.put("ltot", new AttributeValue(Float.toString(aVar.y().floatValue())));
        }
        if (aVar.G() != null) {
            map.put("ltod", new AttributeValue(com.topfreegames.bikerace.l.d.a(aVar.G())));
        }
        if (aVar.z() != null) {
            map.put("ltcm", new AttributeValue(Integer.toString(aVar.z().intValue())));
        }
        if (aVar.A() != null) {
            map.put("ltom", new AttributeValue(Integer.toString(aVar.A().intValue())));
        }
        if (aVar.B() != null) {
            map.put("ltccm", new AttributeValue(aVar.B()));
        }
        if (aVar.C() != null) {
            map.put("ltocm", new AttributeValue(aVar.C()));
        }
        return map;
    }

    @Override // com.topfreegames.bikerace.l.b
    public int b(com.topfreegames.bikerace.b.a aVar) {
        this.b.a(new DeleteItemRequest().withTableName(this.f1281a).withKey(d(aVar.a())));
        return 1;
    }
}
