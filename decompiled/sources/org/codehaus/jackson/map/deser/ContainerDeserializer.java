package org.codehaus.jackson.map.deser;

import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.type.JavaType;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class ContainerDeserializer<T> extends StdDeserializer<T> {
    public abstract JsonDeserializer<Object> getContentDeserializer();

    public abstract JavaType getContentType();

    protected ContainerDeserializer(Class<?> cls) {
        super(cls);
    }
}
