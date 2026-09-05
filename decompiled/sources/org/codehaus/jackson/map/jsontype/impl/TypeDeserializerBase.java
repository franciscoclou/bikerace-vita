package org.codehaus.jackson.map.jsontype.impl;

import java.util.HashMap;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;
import org.codehaus.jackson.type.JavaType;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class TypeDeserializerBase extends TypeDeserializer {
    protected final JavaType _baseType;
    protected final HashMap<String, JsonDeserializer<Object>> _deserializers = new HashMap<>();
    protected final TypeIdResolver _idResolver;
    protected final BeanProperty _property;

    @Override // org.codehaus.jackson.map.TypeDeserializer
    public abstract JsonTypeInfo.As getTypeInclusion();

    protected TypeDeserializerBase(JavaType javaType, TypeIdResolver typeIdResolver, BeanProperty beanProperty) {
        this._baseType = javaType;
        this._idResolver = typeIdResolver;
        this._property = beanProperty;
    }

    public String baseTypeName() {
        return this._baseType.getRawClass().getName();
    }

    @Override // org.codehaus.jackson.map.TypeDeserializer
    public String getPropertyName() {
        return null;
    }

    @Override // org.codehaus.jackson.map.TypeDeserializer
    public TypeIdResolver getTypeIdResolver() {
        return this._idResolver;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[').append(getClass().getName());
        sb.append("; base-type:").append(this._baseType);
        sb.append("; id-resolver: ").append(this._idResolver);
        sb.append(']');
        return sb.toString();
    }

    protected final JsonDeserializer<Object> _findDeserializer(DeserializationContext deserializationContext, String str) {
        JsonDeserializer<Object> jsonDeserializerFindValueDeserializer;
        synchronized (this._deserializers) {
            jsonDeserializerFindValueDeserializer = this._deserializers.get(str);
            if (jsonDeserializerFindValueDeserializer == null) {
                JavaType javaTypeTypeFromId = this._idResolver.typeFromId(str);
                if (javaTypeTypeFromId == null) {
                    throw deserializationContext.unknownTypeException(this._baseType, str);
                }
                if (this._baseType != null && this._baseType.getClass() == javaTypeTypeFromId.getClass()) {
                    javaTypeTypeFromId = this._baseType.narrowBy(javaTypeTypeFromId.getRawClass());
                }
                jsonDeserializerFindValueDeserializer = deserializationContext.getDeserializerProvider().findValueDeserializer(deserializationContext.getConfig(), javaTypeTypeFromId, this._property);
                this._deserializers.put(str, jsonDeserializerFindValueDeserializer);
            }
        }
        return jsonDeserializerFindValueDeserializer;
    }
}
