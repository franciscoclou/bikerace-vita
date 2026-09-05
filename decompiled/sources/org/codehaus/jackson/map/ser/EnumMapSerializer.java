package org.codehaus.jackson.map.ser;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ResolvableSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.map.util.EnumValues;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.schema.JsonSchema;
import org.codehaus.jackson.schema.SchemaAware;
import org.codehaus.jackson.type.JavaType;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
@JacksonStdImpl
public class EnumMapSerializer extends ContainerSerializerBase<EnumMap<? extends Enum<?>, ?>> implements ResolvableSerializer {
    protected final EnumValues _keyEnums;
    protected final BeanProperty _property;
    protected final boolean _staticTyping;
    protected JsonSerializer<Object> _valueSerializer;
    protected final JavaType _valueType;
    protected final TypeSerializer _valueTypeSerializer;

    @Deprecated
    public EnumMapSerializer(JavaType javaType, boolean z, EnumValues enumValues, TypeSerializer typeSerializer, BeanProperty beanProperty) {
        this(javaType, z, enumValues, typeSerializer, beanProperty, null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EnumMapSerializer(JavaType javaType, boolean z, EnumValues enumValues, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
        super(EnumMap.class, false);
        boolean z2 = false;
        if (z || (javaType != null && javaType.isFinal())) {
            z2 = true;
        }
        this._staticTyping = z2;
        this._valueType = javaType;
        this._keyEnums = enumValues;
        this._valueTypeSerializer = typeSerializer;
        this._property = beanProperty;
        this._valueSerializer = jsonSerializer;
    }

    @Override // org.codehaus.jackson.map.ser.ContainerSerializerBase
    public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return new EnumMapSerializer(this._valueType, this._staticTyping, this._keyEnums, typeSerializer, this._property);
    }

    @Override // org.codehaus.jackson.map.ser.SerializerBase, org.codehaus.jackson.map.JsonSerializer
    public void serialize(EnumMap<? extends Enum<?>, ?> enumMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeStartObject();
        if (!enumMap.isEmpty()) {
            serializeContents(enumMap, jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndObject();
    }

    @Override // org.codehaus.jackson.map.JsonSerializer
    public void serializeWithType(EnumMap<? extends Enum<?>, ?> enumMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        typeSerializer.writeTypePrefixForObject(enumMap, jsonGenerator);
        if (!enumMap.isEmpty()) {
            serializeContents(enumMap, jsonGenerator, serializerProvider);
        }
        typeSerializer.writeTypeSuffixForObject(enumMap, jsonGenerator);
    }

    protected void serializeContents(EnumMap<? extends Enum<?>, ?> enumMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        JsonSerializer<Object> jsonSerializer;
        Class<?> cls;
        JsonSerializer<Object> jsonSerializer2;
        Class<?> cls2 = null;
        if (this._valueSerializer != null) {
            serializeContentsUsing(enumMap, jsonGenerator, serializerProvider, this._valueSerializer);
            return;
        }
        EnumValues enumValues = this._keyEnums;
        Iterator it = enumMap.entrySet().iterator();
        EnumValues enumValues2 = enumValues;
        JsonSerializer<Object> jsonSerializerFindValueSerializer = null;
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Enum<?> r1 = (Enum) entry.getKey();
            EnumValues enumValues3 = enumValues2 == null ? ((EnumSerializer) ((SerializerBase) serializerProvider.findValueSerializer(r1.getDeclaringClass(), this._property))).getEnumValues() : enumValues2;
            jsonGenerator.writeFieldName(enumValues3.serializedValueFor(r1));
            Object value = entry.getValue();
            if (value == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
                cls = cls2;
                jsonSerializer2 = jsonSerializerFindValueSerializer;
            } else {
                Class<?> cls3 = value.getClass();
                if (cls3 == cls2) {
                    cls3 = cls2;
                    jsonSerializer = jsonSerializerFindValueSerializer;
                } else {
                    jsonSerializerFindValueSerializer = serializerProvider.findValueSerializer(cls3, this._property);
                    jsonSerializer = jsonSerializerFindValueSerializer;
                }
                try {
                    jsonSerializerFindValueSerializer.serialize(value, jsonGenerator, serializerProvider);
                    cls = cls3;
                    jsonSerializer2 = jsonSerializer;
                } catch (Exception e) {
                    wrapAndThrow(serializerProvider, e, enumMap, ((Enum) entry.getKey()).name());
                    cls = cls3;
                    jsonSerializer2 = jsonSerializer;
                }
            }
            enumValues2 = enumValues3;
            cls2 = cls;
            jsonSerializerFindValueSerializer = jsonSerializer2;
        }
    }

    protected void serializeContentsUsing(EnumMap<? extends Enum<?>, ?> enumMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) {
        EnumValues enumValues = this._keyEnums;
        Iterator it = enumMap.entrySet().iterator();
        EnumValues enumValues2 = enumValues;
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Enum<?> r1 = (Enum) entry.getKey();
            if (enumValues2 == null) {
                enumValues2 = ((EnumSerializer) ((SerializerBase) serializerProvider.findValueSerializer(r1.getDeclaringClass(), this._property))).getEnumValues();
            }
            jsonGenerator.writeFieldName(enumValues2.serializedValueFor(r1));
            Object value = entry.getValue();
            if (value == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
            } else {
                try {
                    jsonSerializer.serialize(value, jsonGenerator, serializerProvider);
                } catch (Exception e) {
                    wrapAndThrow(serializerProvider, e, enumMap, ((Enum) entry.getKey()).name());
                }
            }
        }
    }

    @Override // org.codehaus.jackson.map.ResolvableSerializer
    public void resolve(SerializerProvider serializerProvider) {
        if (this._staticTyping && this._valueSerializer == null) {
            this._valueSerializer = serializerProvider.findValueSerializer(this._valueType, this._property);
        }
    }

    @Override // org.codehaus.jackson.map.ser.SerializerBase, org.codehaus.jackson.schema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        ObjectNode objectNodeCreateSchemaNode = createSchemaNode("object", true);
        if (type instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            if (actualTypeArguments.length == 2) {
                JavaType javaTypeConstructType = serializerProvider.constructType(actualTypeArguments[0]);
                JavaType javaTypeConstructType2 = serializerProvider.constructType(actualTypeArguments[1]);
                ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
                Enum<?>[] enumArr = (Enum[]) javaTypeConstructType.getRawClass().getEnumConstants();
                for (Enum<?> r7 : enumArr) {
                    Object objFindValueSerializer = serializerProvider.findValueSerializer(javaTypeConstructType2.getRawClass(), this._property);
                    objectNode.put(serializerProvider.getConfig().getAnnotationIntrospector().findEnumValue(r7), objFindValueSerializer instanceof SchemaAware ? ((SchemaAware) objFindValueSerializer).getSchema(serializerProvider, null) : JsonSchema.getDefaultSchemaNode());
                }
                objectNodeCreateSchemaNode.put("properties", objectNode);
            }
        }
        return objectNodeCreateSchemaNode;
    }
}
