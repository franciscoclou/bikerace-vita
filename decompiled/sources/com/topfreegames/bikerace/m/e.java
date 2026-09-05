package com.topfreegames.bikerace.m;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: ListUtils.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class e {
    public static final <T> List<List<T>> a(List<T> list, int i, Class<? extends List> cls) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxListSize must be greater than zero");
        }
        Object[] array = list.toArray();
        int iCeil = (int) Math.ceil(((double) array.length) / ((double) i));
        ArrayList arrayList = new ArrayList(iCeil);
        for (int i2 = 0; i2 < iCeil; i2++) {
            int i3 = i2 * i;
            arrayList.add(a(a(array, i3, Math.min(i3 + i, array.length)), cls));
        }
        return arrayList;
    }

    public static final <T> List<List<T>> a(List<T> list, int i) {
        return a(list, i, (Class<? extends List>) ArrayList.class);
    }

    private static <T> List<T> a(T[] tArr, Class<? extends List> cls) {
        List<T> listAsList = Arrays.asList(tArr);
        try {
            List<T> listNewInstance = cls.newInstance();
            listNewInstance.addAll(listAsList);
            return listNewInstance;
        } catch (IllegalAccessException e) {
            return listAsList;
        } catch (InstantiationException e2) {
            return listAsList;
        }
    }

    public static <T> T[] a(T[] tArr, int i, int i2) {
        return (T[]) a(tArr, i, i2, tArr.getClass());
    }

    public static <T, U> T[] a(U[] uArr, int i, int i2, Class<? extends T[]> cls) {
        T[] tArr;
        int i3 = i2 - i;
        if (i3 < 0) {
            throw new IllegalArgumentException(String.valueOf(i) + " > " + i2);
        }
        if (cls == Object[].class) {
            tArr = (T[]) new Object[i3];
        } else {
            tArr = (T[]) ((Object[]) Array.newInstance(cls.getComponentType(), i3));
        }
        System.arraycopy(uArr, i, tArr, 0, Math.min(uArr.length - i, i3));
        return tArr;
    }
}
