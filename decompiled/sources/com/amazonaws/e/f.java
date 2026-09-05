package com.amazonaws.e;

import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.JsonToken;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class f<K, V> implements t<Map<K, V>, c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final t<K, c> f102a;
    private final t<V, c> b;

    public f(t<K, c> tVar, t<V, c> tVar2) {
        this.f102a = tVar;
        this.b = tVar2;
    }

    @Override // com.amazonaws.e.t
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Map<K, V> unmarshall(c cVar) {
        HashMap map = new HashMap();
        int iA = cVar.a();
        while (true) {
            JsonToken jsonTokenC = cVar.c();
            if (jsonTokenC == null) {
                break;
            }
            if (jsonTokenC == JsonToken.FIELD_NAME) {
                map.put(this.f102a.unmarshall(cVar), this.b.unmarshall(cVar));
            } else if (jsonTokenC == JsonToken.END_ARRAY || jsonTokenC == JsonToken.END_OBJECT) {
                if (cVar.a() <= iA) {
                    break;
                }
            }
        }
        return map;
    }
}
