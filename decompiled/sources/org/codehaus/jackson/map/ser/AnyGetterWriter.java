package org.codehaus.jackson.map.ser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AnyGetterWriter {
    protected final Method _anyGetter;
    protected final MapSerializer _serializer;

    public AnyGetterWriter(AnnotatedMethod annotatedMethod, MapSerializer mapSerializer) {
        this._anyGetter = annotatedMethod.getAnnotated();
        this._serializer = mapSerializer;
    }

    public void getAndSerialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IllegalAccessException, JsonMappingException, InvocationTargetException {
        Object objInvoke = this._anyGetter.invoke(obj, new Object[0]);
        if (objInvoke != null) {
            if (!(objInvoke instanceof Map)) {
                throw new JsonMappingException("Value returned by 'any-getter' (" + this._anyGetter.getName() + "()) not java.util.Map but " + objInvoke.getClass().getName());
            }
            this._serializer.serializeFields((Map) objInvoke, jsonGenerator, serializerProvider);
        }
    }

    public void resolve(SerializerProvider serializerProvider) {
        this._serializer.resolve(serializerProvider);
    }
}
