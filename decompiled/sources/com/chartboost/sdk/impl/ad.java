package com.chartboost.sdk.impl;

import java.util.Iterator;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ad extends ac {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private bf<ah> f414a = new bf<>();

    ad() {
    }

    void a(Class cls, ah ahVar) {
        this.f414a.a(cls, ahVar);
    }

    @Override // com.chartboost.sdk.impl.ah
    public void a(Object obj, StringBuilder sb) {
        Object objA = z.a(obj);
        if (objA == null) {
            sb.append(" null ");
            return;
        }
        ah ahVarA = null;
        Iterator<Class<?>> it = bf.a((Class) objA.getClass()).iterator();
        while (it.hasNext()) {
            ahVarA = this.f414a.a(it.next());
            if (ahVarA != null) {
                break;
            }
        }
        if (ahVarA == null && objA.getClass().isArray()) {
            ahVarA = this.f414a.a((Object) Object[].class);
        }
        if (ahVarA == null) {
            throw new RuntimeException("json can't serialize type : " + objA.getClass());
        }
        ahVarA.a(objA, sb);
    }
}
