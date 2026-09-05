package org.codehaus.jackson.map;

import org.codehaus.jackson.JsonGenerator;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class JsonSerializer<T> {

    public abstract class None extends JsonSerializer<Object> {
    }

    public abstract void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider);

    public void serializeWithType(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        serialize(t, jsonGenerator, serializerProvider);
    }

    public Class<T> handledType() {
        return null;
    }
}
