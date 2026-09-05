package org.codehaus.jackson.map.ser;

import org.codehaus.jackson.map.TypeSerializer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class ContainerSerializerBase<T> extends SerializerBase<T> {
    public abstract ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer);

    protected ContainerSerializerBase(Class<T> cls) {
        super(cls);
    }

    protected ContainerSerializerBase(Class<?> cls, boolean z) {
        super(cls, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ContainerSerializerBase<?> withValueTypeSerializer(TypeSerializer typeSerializer) {
        return typeSerializer == null ? this : _withValueTypeSerializer(typeSerializer);
    }
}
