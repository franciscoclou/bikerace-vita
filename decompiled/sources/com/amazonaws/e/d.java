package com.amazonaws.e;

import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.JsonToken;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class d<T> implements t<List<T>, c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final t<T, c> f100a;

    public d(t<T, c> tVar) {
        this.f100a = tVar;
    }

    @Override // com.amazonaws.e.t
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public List<T> unmarshall(c cVar) {
        ArrayList arrayList = new ArrayList();
        int iA = cVar.a();
        int i = iA + 1;
        while (true) {
            JsonToken jsonTokenC = cVar.c();
            if (jsonTokenC == null) {
                break;
            }
            if (jsonTokenC != JsonToken.START_ARRAY) {
                if (jsonTokenC != JsonToken.END_ARRAY && jsonTokenC != JsonToken.END_OBJECT) {
                    arrayList.add(this.f100a.unmarshall(cVar));
                } else if (cVar.a() < iA) {
                    break;
                }
            }
        }
        return arrayList;
    }
}
