package com.chartboost.sdk.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class be {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final ConcurrentMap<Class<?>, List<Class<?>>> f446a = bh.c();

    public static <T> List<Class<?>> a(Class<T> cls) {
        ConcurrentMap<Class<?>, List<Class<?>>> concurrentMapA = a();
        while (true) {
            List<Class<?>> list = concurrentMapA.get(cls);
            if (list != null) {
                return list;
            }
            concurrentMapA.putIfAbsent(cls, b(cls));
        }
    }

    private static List<Class<?>> b(Class<?> cls) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Object.class);
        a(cls, arrayList);
        Collections.reverse(arrayList);
        return Collections.unmodifiableList(new ArrayList(arrayList));
    }

    private static <T> void a(Class<T> cls, List<Class<?>> list) {
        if (cls != null && cls != Object.class) {
            Class<?>[] interfaces = cls.getInterfaces();
            for (int length = interfaces.length - 1; length >= 0; length--) {
                a(interfaces[length], list);
            }
            a(cls.getSuperclass(), list);
            if (!list.contains(cls)) {
                list.add(cls);
            }
        }
    }

    private static ConcurrentMap<Class<?>, List<Class<?>>> a() {
        return f446a;
    }
}
