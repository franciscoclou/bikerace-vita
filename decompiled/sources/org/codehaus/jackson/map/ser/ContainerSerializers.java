package org.codehaus.jackson.map.ser;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ResolvableSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.map.ser.impl.PropertySerializerMap;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.schema.JsonSchema;
import org.codehaus.jackson.schema.SchemaAware;
import org.codehaus.jackson.type.JavaType;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class ContainerSerializers {
    private ContainerSerializers() {
    }

    public static ContainerSerializerBase<?> indexedListSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
        return new IndexedListSerializer(javaType, z, typeSerializer, beanProperty, jsonSerializer);
    }

    public static ContainerSerializerBase<?> collectionSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
        return new CollectionSerializer(javaType, z, typeSerializer, beanProperty, jsonSerializer);
    }

    public static ContainerSerializerBase<?> iteratorSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty) {
        return new IteratorSerializer(javaType, z, typeSerializer, beanProperty);
    }

    public static ContainerSerializerBase<?> iterableSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty) {
        return new IterableSerializer(javaType, z, typeSerializer, beanProperty);
    }

    public static JsonSerializer<?> enumSetSerializer(JavaType javaType, BeanProperty beanProperty) {
        return new EnumSetSerializer(javaType, beanProperty);
    }

    public abstract class AsArraySerializer<T> extends ContainerSerializerBase<T> implements ResolvableSerializer {
        protected PropertySerializerMap _dynamicSerializers;
        protected JsonSerializer<Object> _elementSerializer;
        protected final JavaType _elementType;
        protected final BeanProperty _property;
        protected final boolean _staticTyping;
        protected final TypeSerializer _valueTypeSerializer;

        protected abstract void serializeContents(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider);

        @Deprecated
        protected AsArraySerializer(Class<?> cls, JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty) {
            this(cls, javaType, z, typeSerializer, beanProperty, null);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        protected AsArraySerializer(Class<?> cls, JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
            super(cls, false);
            boolean z2 = false;
            this._elementType = javaType;
            if (z || (javaType != null && javaType.isFinal())) {
                z2 = true;
            }
            this._staticTyping = z2;
            this._valueTypeSerializer = typeSerializer;
            this._property = beanProperty;
            this._elementSerializer = jsonSerializer;
            this._dynamicSerializers = PropertySerializerMap.emptyMap();
        }

        @Override // org.codehaus.jackson.map.ser.SerializerBase, org.codehaus.jackson.map.JsonSerializer
        public final void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeStartArray();
            serializeContents(t, jsonGenerator, serializerProvider);
            jsonGenerator.writeEndArray();
        }

        @Override // org.codehaus.jackson.map.JsonSerializer
        public final void serializeWithType(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
            typeSerializer.writeTypePrefixForArray(t, jsonGenerator);
            serializeContents(t, jsonGenerator, serializerProvider);
            typeSerializer.writeTypeSuffixForArray(t, jsonGenerator);
        }

        /* JADX WARN: Code duplicated, block: B:25:0x0056  */
        @Override // org.codehaus.jackson.map.ser.SerializerBase, org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            JavaType contentType;
            JsonNode defaultSchemaNode;
            ObjectNode objectNodeCreateSchemaNode = createSchemaNode("array", true);
            if (type != null) {
                contentType = serializerProvider.constructType(type).getContentType();
                if (contentType == null && (type instanceof ParameterizedType)) {
                    Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
                    if (actualTypeArguments.length == 1) {
                        contentType = serializerProvider.constructType(actualTypeArguments[0]);
                    }
                }
            } else {
                contentType = null;
            }
            if (contentType == null && this._elementType != null) {
                contentType = this._elementType;
            }
            if (contentType != null) {
                if (contentType.getRawClass() != Object.class) {
                    Object objFindValueSerializer = serializerProvider.findValueSerializer(contentType, this._property);
                    if (objFindValueSerializer instanceof SchemaAware) {
                        defaultSchemaNode = ((SchemaAware) objFindValueSerializer).getSchema(serializerProvider, null);
                    } else {
                        defaultSchemaNode = null;
                    }
                } else {
                    defaultSchemaNode = null;
                }
                if (defaultSchemaNode == null) {
                    defaultSchemaNode = JsonSchema.getDefaultSchemaNode();
                }
                objectNodeCreateSchemaNode.put("items", defaultSchemaNode);
            }
            return objectNodeCreateSchemaNode;
        }

        @Override // org.codehaus.jackson.map.ResolvableSerializer
        public void resolve(SerializerProvider serializerProvider) {
            if (this._staticTyping && this._elementType != null && this._elementSerializer == null) {
                this._elementSerializer = serializerProvider.findValueSerializer(this._elementType, this._property);
            }
        }

        protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, Class<?> cls, SerializerProvider serializerProvider) {
            PropertySerializerMap.SerializerAndMapResult serializerAndMapResultFindAndAddSerializer = propertySerializerMap.findAndAddSerializer(cls, serializerProvider, this._property);
            if (propertySerializerMap != serializerAndMapResultFindAndAddSerializer.map) {
                this._dynamicSerializers = serializerAndMapResultFindAndAddSerializer.map;
            }
            return serializerAndMapResultFindAndAddSerializer.serializer;
        }

        protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, JavaType javaType, SerializerProvider serializerProvider) {
            PropertySerializerMap.SerializerAndMapResult serializerAndMapResultFindAndAddSerializer = propertySerializerMap.findAndAddSerializer(javaType, serializerProvider, this._property);
            if (propertySerializerMap != serializerAndMapResultFindAndAddSerializer.map) {
                this._dynamicSerializers = serializerAndMapResultFindAndAddSerializer.map;
            }
            return serializerAndMapResultFindAndAddSerializer.serializer;
        }
    }

    @JacksonStdImpl
    public class IndexedListSerializer extends AsArraySerializer<List<?>> {
        public IndexedListSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
            super(List.class, javaType, z, typeSerializer, beanProperty, jsonSerializer);
        }

        @Override // org.codehaus.jackson.map.ser.ContainerSerializerBase
        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new IndexedListSerializer(this._elementType, this._staticTyping, typeSerializer, this._property, this._elementSerializer);
        }

        @Override // org.codehaus.jackson.map.ser.ContainerSerializers.AsArraySerializer
        public void serializeContents(List<?> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            JsonSerializer<Object> jsonSerializer_findAndAddDynamic;
            if (this._elementSerializer != null) {
                serializeContentsUsing(list, jsonGenerator, serializerProvider, this._elementSerializer);
                return;
            }
            if (this._valueTypeSerializer != null) {
                serializeTypedContents(list, jsonGenerator, serializerProvider);
                return;
            }
            int size = list.size();
            if (size != 0) {
                int i = 0;
                try {
                    PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
                    while (i < size) {
                        Object obj = list.get(i);
                        if (obj == null) {
                            serializerProvider.defaultSerializeNull(jsonGenerator);
                        } else {
                            Class<?> cls = obj.getClass();
                            JsonSerializer<Object> jsonSerializerSerializerFor = propertySerializerMap.serializerFor(cls);
                            if (jsonSerializerSerializerFor == null) {
                                if (this._elementType.hasGenericTypes()) {
                                    jsonSerializer_findAndAddDynamic = _findAndAddDynamic(propertySerializerMap, this._elementType.forcedNarrowBy(cls), serializerProvider);
                                } else {
                                    jsonSerializer_findAndAddDynamic = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                                }
                                JsonSerializer<Object> jsonSerializer = jsonSerializer_findAndAddDynamic;
                                propertySerializerMap = this._dynamicSerializers;
                                jsonSerializerSerializerFor = jsonSerializer;
                            }
                            jsonSerializerSerializerFor.serialize(obj, jsonGenerator, serializerProvider);
                        }
                        i++;
                    }
                } catch (Exception e) {
                    wrapAndThrow(serializerProvider, e, list, i);
                }
            }
        }

        public void serializeContentsUsing(List<?> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) {
            int size = list.size();
            if (size != 0) {
                TypeSerializer typeSerializer = this._valueTypeSerializer;
                for (int i = 0; i < size; i++) {
                    Object obj = list.get(i);
                    if (obj == null) {
                        try {
                            serializerProvider.defaultSerializeNull(jsonGenerator);
                        } catch (Exception e) {
                            wrapAndThrow(serializerProvider, e, list, i);
                        }
                    } else if (typeSerializer == null) {
                        jsonSerializer.serialize(obj, jsonGenerator, serializerProvider);
                    } else {
                        jsonSerializer.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
                    }
                }
            }
        }

        public void serializeTypedContents(List<?> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            JsonSerializer<Object> jsonSerializer_findAndAddDynamic;
            int size = list.size();
            if (size != 0) {
                int i = 0;
                try {
                    TypeSerializer typeSerializer = this._valueTypeSerializer;
                    PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
                    while (i < size) {
                        Object obj = list.get(i);
                        if (obj == null) {
                            serializerProvider.defaultSerializeNull(jsonGenerator);
                        } else {
                            Class<?> cls = obj.getClass();
                            JsonSerializer<Object> jsonSerializerSerializerFor = propertySerializerMap.serializerFor(cls);
                            if (jsonSerializerSerializerFor == null) {
                                if (this._elementType.hasGenericTypes()) {
                                    jsonSerializer_findAndAddDynamic = _findAndAddDynamic(propertySerializerMap, this._elementType.forcedNarrowBy(cls), serializerProvider);
                                } else {
                                    jsonSerializer_findAndAddDynamic = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                                }
                                JsonSerializer<Object> jsonSerializer = jsonSerializer_findAndAddDynamic;
                                propertySerializerMap = this._dynamicSerializers;
                                jsonSerializerSerializerFor = jsonSerializer;
                            }
                            jsonSerializerSerializerFor.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
                        }
                        i++;
                    }
                } catch (Exception e) {
                    wrapAndThrow(serializerProvider, e, list, i);
                }
            }
        }
    }

    @JacksonStdImpl
    public class CollectionSerializer extends AsArraySerializer<Collection<?>> {
        @Deprecated
        public CollectionSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty) {
            this(javaType, z, typeSerializer, beanProperty, null);
        }

        public CollectionSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
            super(Collection.class, javaType, z, typeSerializer, beanProperty, jsonSerializer);
        }

        @Override // org.codehaus.jackson.map.ser.ContainerSerializerBase
        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new CollectionSerializer(this._elementType, this._staticTyping, typeSerializer, this._property);
        }

        @Override // org.codehaus.jackson.map.ser.ContainerSerializers.AsArraySerializer
        public void serializeContents(Collection<?> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            JsonSerializer<Object> jsonSerializer_findAndAddDynamic;
            if (this._elementSerializer != null) {
                serializeContentsUsing(collection, jsonGenerator, serializerProvider, this._elementSerializer);
                return;
            }
            Iterator<?> it = collection.iterator();
            if (it.hasNext()) {
                PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
                TypeSerializer typeSerializer = this._valueTypeSerializer;
                int i = 0;
                do {
                    try {
                        Object next = it.next();
                        if (next == null) {
                            serializerProvider.defaultSerializeNull(jsonGenerator);
                        } else {
                            Class<?> cls = next.getClass();
                            JsonSerializer<Object> jsonSerializerSerializerFor = propertySerializerMap.serializerFor(cls);
                            if (jsonSerializerSerializerFor == null) {
                                if (this._elementType.hasGenericTypes()) {
                                    jsonSerializer_findAndAddDynamic = _findAndAddDynamic(propertySerializerMap, this._elementType.forcedNarrowBy(cls), serializerProvider);
                                } else {
                                    jsonSerializer_findAndAddDynamic = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                                }
                                JsonSerializer<Object> jsonSerializer = jsonSerializer_findAndAddDynamic;
                                propertySerializerMap = this._dynamicSerializers;
                                jsonSerializerSerializerFor = jsonSerializer;
                            }
                            if (typeSerializer == null) {
                                jsonSerializerSerializerFor.serialize(next, jsonGenerator, serializerProvider);
                            } else {
                                jsonSerializerSerializerFor.serializeWithType(next, jsonGenerator, serializerProvider, typeSerializer);
                            }
                        }
                        i++;
                    } catch (Exception e) {
                        wrapAndThrow(serializerProvider, e, collection, i);
                        return;
                    }
                } while (it.hasNext());
            }
        }

        public void serializeContentsUsing(Collection<?> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) {
            Iterator<?> it = collection.iterator();
            if (it.hasNext()) {
                TypeSerializer typeSerializer = this._valueTypeSerializer;
                int i = 0;
                do {
                    Object next = it.next();
                    if (next == null) {
                        try {
                            serializerProvider.defaultSerializeNull(jsonGenerator);
                        } catch (Exception e) {
                            wrapAndThrow(serializerProvider, e, collection, i);
                        }
                    } else if (typeSerializer == null) {
                        jsonSerializer.serialize(next, jsonGenerator, serializerProvider);
                    } else {
                        jsonSerializer.serializeWithType(next, jsonGenerator, serializerProvider, typeSerializer);
                    }
                    i++;
                } while (it.hasNext());
            }
        }
    }

    @JacksonStdImpl
    public class IteratorSerializer extends AsArraySerializer<Iterator<?>> {
        public IteratorSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty) {
            super(Iterator.class, javaType, z, typeSerializer, beanProperty);
        }

        @Override // org.codehaus.jackson.map.ser.ContainerSerializerBase
        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new IteratorSerializer(this._elementType, this._staticTyping, typeSerializer, this._property);
        }

        @Override // org.codehaus.jackson.map.ser.ContainerSerializers.AsArraySerializer
        public void serializeContents(Iterator<?> it, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            JsonSerializer<Object> jsonSerializer;
            Class<?> cls = null;
            if (it.hasNext()) {
                TypeSerializer typeSerializer = this._valueTypeSerializer;
                JsonSerializer<Object> jsonSerializerFindValueSerializer = null;
                do {
                    Object next = it.next();
                    if (next == null) {
                        serializerProvider.defaultSerializeNull(jsonGenerator);
                    } else {
                        Class<?> cls2 = next.getClass();
                        if (cls2 == cls) {
                            jsonSerializer = jsonSerializerFindValueSerializer;
                        } else {
                            jsonSerializerFindValueSerializer = serializerProvider.findValueSerializer(cls2, this._property);
                            cls = cls2;
                            jsonSerializer = jsonSerializerFindValueSerializer;
                        }
                        if (typeSerializer == null) {
                            jsonSerializer.serialize(next, jsonGenerator, serializerProvider);
                        } else {
                            jsonSerializer.serializeWithType(next, jsonGenerator, serializerProvider, typeSerializer);
                        }
                    }
                } while (it.hasNext());
            }
        }
    }

    @JacksonStdImpl
    public class IterableSerializer extends AsArraySerializer<Iterable<?>> {
        public IterableSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty) {
            super(Iterable.class, javaType, z, typeSerializer, beanProperty);
        }

        @Override // org.codehaus.jackson.map.ser.ContainerSerializerBase
        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new IterableSerializer(this._elementType, this._staticTyping, typeSerializer, this._property);
        }

        @Override // org.codehaus.jackson.map.ser.ContainerSerializers.AsArraySerializer
        public void serializeContents(Iterable<?> iterable, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            JsonSerializer<Object> jsonSerializer;
            Class<?> cls = null;
            Iterator<?> it = iterable.iterator();
            if (it.hasNext()) {
                TypeSerializer typeSerializer = this._valueTypeSerializer;
                JsonSerializer<Object> jsonSerializerFindValueSerializer = null;
                do {
                    Object next = it.next();
                    if (next == null) {
                        serializerProvider.defaultSerializeNull(jsonGenerator);
                    } else {
                        Class<?> cls2 = next.getClass();
                        if (cls2 == cls) {
                            jsonSerializer = jsonSerializerFindValueSerializer;
                        } else {
                            jsonSerializerFindValueSerializer = serializerProvider.findValueSerializer(cls2, this._property);
                            cls = cls2;
                            jsonSerializer = jsonSerializerFindValueSerializer;
                        }
                        if (typeSerializer == null) {
                            jsonSerializer.serialize(next, jsonGenerator, serializerProvider);
                        } else {
                            jsonSerializer.serializeWithType(next, jsonGenerator, serializerProvider, typeSerializer);
                        }
                    }
                } while (it.hasNext());
            }
        }
    }

    public class EnumSetSerializer extends AsArraySerializer<EnumSet<? extends Enum<?>>> {
        public EnumSetSerializer(JavaType javaType, BeanProperty beanProperty) {
            super(EnumSet.class, javaType, true, null, beanProperty);
        }

        @Override // org.codehaus.jackson.map.ser.ContainerSerializerBase
        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return this;
        }

        @Override // org.codehaus.jackson.map.ser.ContainerSerializers.AsArraySerializer
        public void serializeContents(EnumSet<? extends Enum<?>> enumSet, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            JsonSerializer<Object> jsonSerializer = this._elementSerializer;
            Iterator it = enumSet.iterator();
            JsonSerializer<Object> jsonSerializerFindValueSerializer = jsonSerializer;
            while (it.hasNext()) {
                Enum r0 = (Enum) it.next();
                if (jsonSerializerFindValueSerializer == null) {
                    jsonSerializerFindValueSerializer = serializerProvider.findValueSerializer(r0.getDeclaringClass(), this._property);
                }
                jsonSerializerFindValueSerializer.serialize(r0, jsonGenerator, serializerProvider);
            }
        }
    }
}
