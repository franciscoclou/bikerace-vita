package org.codehaus.jackson.map;

import org.codehaus.jackson.JsonGenerator;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
@Deprecated
public interface JsonSerializable {
    void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider);
}
