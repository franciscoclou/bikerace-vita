package org.codehaus.jackson.map.ser;

import java.lang.reflect.Type;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ResolvableSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.schema.JsonSchema;
import org.codehaus.jackson.schema.SchemaAware;
import org.codehaus.jackson.type.JavaType;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class BeanSerializer extends SerializerBase<Object> implements ResolvableSerializer, SchemaAware {
    protected static final BeanPropertyWriter[] NO_PROPS = new BeanPropertyWriter[0];
    protected final AnyGetterWriter _anyGetterWriter;
    protected final BeanPropertyWriter[] _filteredProps;
    protected final Object _propertyFilterId;
    protected final BeanPropertyWriter[] _props;

    public BeanSerializer(JavaType javaType, BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2, AnyGetterWriter anyGetterWriter, Object obj) {
        super(javaType);
        this._props = beanPropertyWriterArr;
        this._filteredProps = beanPropertyWriterArr2;
        this._anyGetterWriter = anyGetterWriter;
        this._propertyFilterId = obj;
    }

    public BeanSerializer(Class<?> cls, BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2, AnyGetterWriter anyGetterWriter, Object obj) {
        super(cls);
        this._props = beanPropertyWriterArr;
        this._filteredProps = beanPropertyWriterArr2;
        this._anyGetterWriter = anyGetterWriter;
        this._propertyFilterId = obj;
    }

    protected BeanSerializer(BeanSerializer beanSerializer) {
        this((Class<?>) beanSerializer._handledType, beanSerializer._props, beanSerializer._filteredProps, beanSerializer._anyGetterWriter, beanSerializer._propertyFilterId);
    }

    public static BeanSerializer createDummy(Class<?> cls) {
        return new BeanSerializer(cls, NO_PROPS, (BeanPropertyWriter[]) null, (AnyGetterWriter) null, (Object) null);
    }

    @Override // org.codehaus.jackson.map.ser.SerializerBase, org.codehaus.jackson.map.JsonSerializer
    public final void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws JsonMappingException {
        jsonGenerator.writeStartObject();
        if (this._propertyFilterId != null) {
            serializeFieldsFiltered(obj, jsonGenerator, serializerProvider);
        } else {
            serializeFields(obj, jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndObject();
    }

    @Override // org.codehaus.jackson.map.JsonSerializer
    public void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws JsonMappingException {
        typeSerializer.writeTypePrefixForObject(obj, jsonGenerator);
        if (this._propertyFilterId != null) {
            serializeFieldsFiltered(obj, jsonGenerator, serializerProvider);
        } else {
            serializeFields(obj, jsonGenerator, serializerProvider);
        }
        typeSerializer.writeTypeSuffixForObject(obj, jsonGenerator);
    }

    protected void serializeFields(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws JsonMappingException {
        BeanPropertyWriter[] beanPropertyWriterArr;
        if (this._filteredProps != null && serializerProvider.getSerializationView() != null) {
            beanPropertyWriterArr = this._filteredProps;
        } else {
            beanPropertyWriterArr = this._props;
        }
        int i = 0;
        try {
            int length = beanPropertyWriterArr.length;
            while (i < length) {
                BeanPropertyWriter beanPropertyWriter = beanPropertyWriterArr[i];
                if (beanPropertyWriter != null) {
                    beanPropertyWriter.serializeAsField(obj, jsonGenerator, serializerProvider);
                }
                i++;
            }
            if (this._anyGetterWriter != null) {
                this._anyGetterWriter.getAndSerialize(obj, jsonGenerator, serializerProvider);
            }
        } catch (Exception e) {
            wrapAndThrow(serializerProvider, e, obj, i == beanPropertyWriterArr.length ? "[anySetter]" : beanPropertyWriterArr[i].getName());
        } catch (StackOverflowError e2) {
            JsonMappingException jsonMappingException = new JsonMappingException("Infinite recursion (StackOverflowError)");
            jsonMappingException.prependPath(new JsonMappingException.Reference(obj, i == beanPropertyWriterArr.length ? "[anySetter]" : beanPropertyWriterArr[i].getName()));
            throw jsonMappingException;
        }
    }

    protected void serializeFieldsFiltered(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws JsonMappingException {
        BeanPropertyWriter[] beanPropertyWriterArr;
        if (this._filteredProps != null && serializerProvider.getSerializationView() != null) {
            beanPropertyWriterArr = this._filteredProps;
        } else {
            beanPropertyWriterArr = this._props;
        }
        BeanPropertyFilter beanPropertyFilterFindFilter = findFilter(serializerProvider);
        int i = 0;
        try {
            int length = beanPropertyWriterArr.length;
            while (i < length) {
                BeanPropertyWriter beanPropertyWriter = beanPropertyWriterArr[i];
                if (beanPropertyWriter != null) {
                    beanPropertyFilterFindFilter.serializeAsField(obj, jsonGenerator, serializerProvider, beanPropertyWriter);
                }
                i++;
            }
            if (this._anyGetterWriter != null) {
                this._anyGetterWriter.getAndSerialize(obj, jsonGenerator, serializerProvider);
            }
        } catch (Exception e) {
            wrapAndThrow(serializerProvider, e, obj, i == beanPropertyWriterArr.length ? "[anySetter]" : beanPropertyWriterArr[i].getName());
        } catch (StackOverflowError e2) {
            JsonMappingException jsonMappingException = new JsonMappingException("Infinite recursion (StackOverflowError)");
            jsonMappingException.prependPath(new JsonMappingException.Reference(obj, i == beanPropertyWriterArr.length ? "[anySetter]" : beanPropertyWriterArr[i].getName()));
            throw jsonMappingException;
        }
    }

    protected BeanPropertyFilter findFilter(SerializerProvider serializerProvider) throws JsonMappingException {
        Object obj = this._propertyFilterId;
        FilterProvider filterProvider = serializerProvider.getFilterProvider();
        if (filterProvider == null) {
            throw new JsonMappingException("Can not resolve BeanPropertyFilter with id '" + obj + "'; no FilterProvider configured");
        }
        BeanPropertyFilter beanPropertyFilterFindFilter = filterProvider.findFilter(obj);
        if (beanPropertyFilterFindFilter == null) {
            throw new JsonMappingException("No filter configured with id '" + obj + "' (type " + obj.getClass().getName() + ")");
        }
        return beanPropertyFilterFindFilter;
    }

    @Override // org.codehaus.jackson.map.ser.SerializerBase, org.codehaus.jackson.schema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        ObjectNode objectNodeCreateSchemaNode = createSchemaNode("object", true);
        ObjectNode objectNode = objectNodeCreateSchemaNode.objectNode();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this._props.length) {
                BeanPropertyWriter beanPropertyWriter = this._props[i2];
                JavaType serializationType = beanPropertyWriter.getSerializationType();
                Type genericPropertyType = serializationType == null ? beanPropertyWriter.getGenericPropertyType() : serializationType.getRawClass();
                Object serializer = beanPropertyWriter.getSerializer();
                if (serializer == null) {
                    Class<?> rawSerializationType = beanPropertyWriter.getRawSerializationType();
                    if (rawSerializationType == null) {
                        rawSerializationType = beanPropertyWriter.getPropertyType();
                    }
                    serializer = serializerProvider.findValueSerializer(rawSerializationType, beanPropertyWriter);
                }
                objectNode.put(beanPropertyWriter.getName(), serializer instanceof SchemaAware ? ((SchemaAware) serializer).getSchema(serializerProvider, genericPropertyType) : JsonSchema.getDefaultSchemaNode());
                i = i2 + 1;
            } else {
                objectNodeCreateSchemaNode.put("properties", objectNode);
                return objectNodeCreateSchemaNode;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0043 A[PHI: r0
      0x0043: PHI (r0v7 org.codehaus.jackson.type.JavaType) = (r0v6 org.codehaus.jackson.type.JavaType), (r0v16 org.codehaus.jackson.type.JavaType) binds: [B:12:0x0023, B:14:0x0031] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // org.codehaus.jackson.map.ResolvableSerializer
    public void resolve(SerializerProvider serializerProvider) {
        JsonSerializer<Object> jsonSerializerFindValueSerializer;
        BeanPropertyWriter beanPropertyWriter;
        TypeSerializer typeSerializer;
        int length = this._filteredProps == null ? 0 : this._filteredProps.length;
        int length2 = this._props.length;
        for (int i = 0; i < length2; i++) {
            BeanPropertyWriter beanPropertyWriter2 = this._props[i];
            if (!beanPropertyWriter2.hasSerializer()) {
                JavaType serializationType = beanPropertyWriter2.getSerializationType();
                if (serializationType == null) {
                    serializationType = serializerProvider.constructType(beanPropertyWriter2.getGenericPropertyType());
                    if (!serializationType.isFinal()) {
                        if (serializationType.isContainerType() || serializationType.containedTypeCount() > 0) {
                            beanPropertyWriter2.setNonTrivialBaseType(serializationType);
                        }
                    } else {
                        jsonSerializerFindValueSerializer = serializerProvider.findValueSerializer(serializationType, beanPropertyWriter2);
                        if (serializationType.isContainerType() && (typeSerializer = (TypeSerializer) serializationType.getContentType().getTypeHandler()) != null && (jsonSerializerFindValueSerializer instanceof ContainerSerializerBase)) {
                            jsonSerializerFindValueSerializer = ((ContainerSerializerBase) jsonSerializerFindValueSerializer).withValueTypeSerializer(typeSerializer);
                        }
                        this._props[i] = beanPropertyWriter2.withSerializer(jsonSerializerFindValueSerializer);
                        if (i >= length && (beanPropertyWriter = this._filteredProps[i]) != null) {
                            this._filteredProps[i] = beanPropertyWriter.withSerializer(jsonSerializerFindValueSerializer);
                        }
                    }
                } else {
                    jsonSerializerFindValueSerializer = serializerProvider.findValueSerializer(serializationType, beanPropertyWriter2);
                    if (serializationType.isContainerType()) {
                        jsonSerializerFindValueSerializer = ((ContainerSerializerBase) jsonSerializerFindValueSerializer).withValueTypeSerializer(typeSerializer);
                    }
                    this._props[i] = beanPropertyWriter2.withSerializer(jsonSerializerFindValueSerializer);
                    if (i >= length) {
                    }
                }
            }
        }
        if (this._anyGetterWriter != null) {
            this._anyGetterWriter.resolve(serializerProvider);
        }
    }

    public String toString() {
        return "BeanSerializer for " + handledType().getName();
    }
}
