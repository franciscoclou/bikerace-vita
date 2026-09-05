package org.codehaus.jackson.map;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class TypeSerializer {
    public abstract String getPropertyName();

    public abstract TypeIdResolver getTypeIdResolver();

    public abstract JsonTypeInfo.As getTypeInclusion();

    public abstract void writeTypePrefixForArray(Object obj, JsonGenerator jsonGenerator);

    public abstract void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator);

    public abstract void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator);

    public abstract void writeTypeSuffixForArray(Object obj, JsonGenerator jsonGenerator);

    public abstract void writeTypeSuffixForObject(Object obj, JsonGenerator jsonGenerator);

    public abstract void writeTypeSuffixForScalar(Object obj, JsonGenerator jsonGenerator);

    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator, Class<?> cls) {
        writeTypePrefixForScalar(obj, jsonGenerator);
    }

    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator, Class<?> cls) {
        writeTypePrefixForObject(obj, jsonGenerator);
    }

    public void writeTypePrefixForArray(Object obj, JsonGenerator jsonGenerator, Class<?> cls) {
        writeTypePrefixForArray(obj, jsonGenerator);
    }
}
