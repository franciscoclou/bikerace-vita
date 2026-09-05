package org.codehaus.jackson.map;

import org.codehaus.jackson.JsonGenerator;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface JsonSerializableWithType extends JsonSerializable {
    void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer);
}
