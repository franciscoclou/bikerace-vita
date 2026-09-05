package org.codehaus.jackson.map.ser;

import com.facebook.internal.ServerProtocol;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.schema.SchemaAware;
import org.codehaus.jackson.type.JavaType;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class SerializerBase<T> extends JsonSerializer<T> implements SchemaAware {
    protected final Class<T> _handledType;

    public abstract JsonNode getSchema(SerializerProvider serializerProvider, Type type);

    @Override // org.codehaus.jackson.map.JsonSerializer
    public abstract void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider);

    protected SerializerBase(Class<T> cls) {
        this._handledType = cls;
    }

    protected SerializerBase(JavaType javaType) {
        this._handledType = (Class<T>) javaType.getRawClass();
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected SerializerBase(Class<?> cls, boolean z) {
        this._handledType = cls;
    }

    @Override // org.codehaus.jackson.map.JsonSerializer
    public final Class<T> handledType() {
        return this._handledType;
    }

    protected ObjectNode createObjectNode() {
        return JsonNodeFactory.instance.objectNode();
    }

    protected ObjectNode createSchemaNode(String str) {
        ObjectNode objectNodeCreateObjectNode = createObjectNode();
        objectNodeCreateObjectNode.put(ServerProtocol.DIALOG_PARAM_TYPE, str);
        return objectNodeCreateObjectNode;
    }

    protected ObjectNode createSchemaNode(String str, boolean z) {
        ObjectNode objectNodeCreateSchemaNode = createSchemaNode(str);
        if (!z) {
            objectNodeCreateSchemaNode.put("required", !z);
        }
        return objectNodeCreateSchemaNode;
    }

    protected boolean isDefaultSerializer(JsonSerializer<?> jsonSerializer) {
        return (jsonSerializer == null || jsonSerializer.getClass().getAnnotation(JacksonStdImpl.class) == null) ? false : true;
    }

    public void wrapAndThrow(SerializerProvider serializerProvider, Throwable th, Object obj, String str) throws IOException {
        Throwable cause = th;
        while ((cause instanceof InvocationTargetException) && cause.getCause() != null) {
            cause = cause.getCause();
        }
        if (cause instanceof Error) {
            throw ((Error) cause);
        }
        boolean z = serializerProvider == null || serializerProvider.isEnabled(SerializationConfig.Feature.WRAP_EXCEPTIONS);
        if (cause instanceof IOException) {
            if (!z || !(cause instanceof JsonMappingException)) {
                throw ((IOException) cause);
            }
        } else if (!z && (cause instanceof RuntimeException)) {
            throw ((RuntimeException) cause);
        }
        throw JsonMappingException.wrapWithPath(cause, obj, str);
    }

    public void wrapAndThrow(SerializerProvider serializerProvider, Throwable th, Object obj, int i) throws IOException {
        Throwable cause = th;
        while ((cause instanceof InvocationTargetException) && cause.getCause() != null) {
            cause = cause.getCause();
        }
        if (cause instanceof Error) {
            throw ((Error) cause);
        }
        boolean z = serializerProvider == null || serializerProvider.isEnabled(SerializationConfig.Feature.WRAP_EXCEPTIONS);
        if (cause instanceof IOException) {
            if (!z || !(cause instanceof JsonMappingException)) {
                throw ((IOException) cause);
            }
        } else if (!z && (cause instanceof RuntimeException)) {
            throw ((RuntimeException) cause);
        }
        throw JsonMappingException.wrapWithPath(cause, obj, i);
    }

    @Deprecated
    public void wrapAndThrow(Throwable th, Object obj, String str) throws IOException {
        wrapAndThrow((SerializerProvider) null, th, obj, str);
    }

    @Deprecated
    public void wrapAndThrow(Throwable th, Object obj, int i) throws IOException {
        wrapAndThrow((SerializerProvider) null, th, obj, i);
    }
}
