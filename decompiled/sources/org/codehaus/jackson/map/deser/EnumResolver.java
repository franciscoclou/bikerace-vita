package org.codehaus.jackson.map.deser;

import java.lang.Enum;
import java.util.HashMap;
import org.codehaus.jackson.map.AnnotationIntrospector;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class EnumResolver<T extends Enum<T>> {
    protected final Class<T> _enumClass;
    protected final T[] _enums;
    protected final HashMap<String, T> _enumsById;

    private EnumResolver(Class<T> cls, T[] tArr, HashMap<String, T> map) {
        this._enumClass = cls;
        this._enums = tArr;
        this._enumsById = map;
    }

    public static <ET extends Enum<ET>> EnumResolver<ET> constructFor(Class<ET> cls, AnnotationIntrospector annotationIntrospector) {
        ET[] enumConstants = cls.getEnumConstants();
        if (enumConstants == null) {
            throw new IllegalArgumentException("No enum constants for class " + cls.getName());
        }
        HashMap map = new HashMap();
        for (ET et : enumConstants) {
            map.put(annotationIntrospector.findEnumValue(et), et);
        }
        return new EnumResolver<>(cls, enumConstants, map);
    }

    public static <ET extends Enum<ET>> EnumResolver<ET> constructUsingToString(Class<ET> cls) {
        ET[] enumConstants = cls.getEnumConstants();
        HashMap map = new HashMap();
        int length = enumConstants.length;
        while (true) {
            length--;
            if (length >= 0) {
                ET et = enumConstants[length];
                map.put(et.toString(), et);
            } else {
                return new EnumResolver<>(cls, enumConstants, map);
            }
        }
    }

    public static EnumResolver<?> constructUnsafe(Class<?> cls, AnnotationIntrospector annotationIntrospector) {
        return constructFor(cls, annotationIntrospector);
    }

    public static EnumResolver<?> constructUnsafeUsingToString(Class<?> cls) {
        return constructUsingToString(cls);
    }

    public T findEnum(String str) {
        return this._enumsById.get(str);
    }

    public T getEnum(int i) {
        if (i < 0 || i >= this._enums.length) {
            return null;
        }
        return this._enums[i];
    }

    public Class<T> getEnumClass() {
        return this._enumClass;
    }

    public int lastValidIndex() {
        return this._enums.length - 1;
    }
}
