package org.codehaus.jackson.map.ser;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.ContextualSerializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializerFactory;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.ser.impl.ReadOnlyClassToSerializerMap;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.map.util.RootNameLookup;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.schema.JsonSchema;
import org.codehaus.jackson.schema.SchemaAware;
import org.codehaus.jackson.type.JavaType;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class StdSerializerProvider extends SerializerProvider {
    static final boolean CACHE_UNKNOWN_MAPPINGS = false;
    protected DateFormat _dateFormat;
    protected JsonSerializer<Object> _keySerializer;
    protected final ReadOnlyClassToSerializerMap _knownSerializers;
    protected JsonSerializer<Object> _nullKeySerializer;
    protected JsonSerializer<Object> _nullValueSerializer;
    protected final RootNameLookup _rootNames;
    protected final SerializerCache _serializerCache;
    protected final SerializerFactory _serializerFactory;
    protected JsonSerializer<Object> _unknownTypeSerializer;
    public static final JsonSerializer<Object> DEFAULT_NULL_KEY_SERIALIZER = new FailingSerializer("Null key for a Map not allowed in JSON (use a converting NullKeySerializer?)");
    public static final JsonSerializer<Object> DEFAULT_KEY_SERIALIZER = new StdKeySerializer();
    public static final JsonSerializer<Object> DEFAULT_UNKNOWN_SERIALIZER = new SerializerBase<Object>(Object.class) { // from class: org.codehaus.jackson.map.ser.StdSerializerProvider.1
        @Override // org.codehaus.jackson.map.ser.SerializerBase, org.codehaus.jackson.map.JsonSerializer
        public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws JsonMappingException {
            if (serializerProvider.isEnabled(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS)) {
                failForEmpty(obj);
            }
            jsonGenerator.writeStartObject();
            jsonGenerator.writeEndObject();
        }

        @Override // org.codehaus.jackson.map.JsonSerializer
        public final void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws JsonMappingException {
            if (serializerProvider.isEnabled(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS)) {
                failForEmpty(obj);
            }
            typeSerializer.writeTypePrefixForObject(obj, jsonGenerator);
            typeSerializer.writeTypeSuffixForObject(obj, jsonGenerator);
        }

        @Override // org.codehaus.jackson.map.ser.SerializerBase, org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return null;
        }

        protected void failForEmpty(Object obj) throws JsonMappingException {
            throw new JsonMappingException("No serializer found for class " + obj.getClass().getName() + " and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS) )");
        }
    };

    public StdSerializerProvider() {
        super(null);
        this._unknownTypeSerializer = DEFAULT_UNKNOWN_SERIALIZER;
        this._keySerializer = DEFAULT_KEY_SERIALIZER;
        this._nullValueSerializer = NullSerializer.instance;
        this._nullKeySerializer = DEFAULT_NULL_KEY_SERIALIZER;
        this._serializerFactory = null;
        this._serializerCache = new SerializerCache();
        this._knownSerializers = null;
        this._rootNames = new RootNameLookup();
    }

    protected StdSerializerProvider(SerializationConfig serializationConfig, StdSerializerProvider stdSerializerProvider, SerializerFactory serializerFactory) {
        super(serializationConfig);
        this._unknownTypeSerializer = DEFAULT_UNKNOWN_SERIALIZER;
        this._keySerializer = DEFAULT_KEY_SERIALIZER;
        this._nullValueSerializer = NullSerializer.instance;
        this._nullKeySerializer = DEFAULT_NULL_KEY_SERIALIZER;
        if (serializationConfig == null) {
            throw new NullPointerException();
        }
        this._serializerFactory = serializerFactory;
        this._serializerCache = stdSerializerProvider._serializerCache;
        this._unknownTypeSerializer = stdSerializerProvider._unknownTypeSerializer;
        this._keySerializer = stdSerializerProvider._keySerializer;
        this._nullValueSerializer = stdSerializerProvider._nullValueSerializer;
        this._nullKeySerializer = stdSerializerProvider._nullKeySerializer;
        this._rootNames = stdSerializerProvider._rootNames;
        this._knownSerializers = this._serializerCache.getReadOnlyLookupMap();
    }

    protected StdSerializerProvider createInstance(SerializationConfig serializationConfig, SerializerFactory serializerFactory) {
        return new StdSerializerProvider(serializationConfig, this, serializerFactory);
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public void setDefaultKeySerializer(JsonSerializer<Object> jsonSerializer) {
        if (jsonSerializer == null) {
            throw new IllegalArgumentException("Can not pass null JsonSerializer");
        }
        this._keySerializer = jsonSerializer;
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public void setNullValueSerializer(JsonSerializer<Object> jsonSerializer) {
        if (jsonSerializer == null) {
            throw new IllegalArgumentException("Can not pass null JsonSerializer");
        }
        this._nullValueSerializer = jsonSerializer;
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public void setNullKeySerializer(JsonSerializer<Object> jsonSerializer) {
        if (jsonSerializer == null) {
            throw new IllegalArgumentException("Can not pass null JsonSerializer");
        }
        this._nullKeySerializer = jsonSerializer;
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public final void serializeValue(SerializationConfig serializationConfig, JsonGenerator jsonGenerator, Object obj, SerializerFactory serializerFactory) throws IOException {
        if (serializerFactory == null) {
            throw new IllegalArgumentException("Can not pass null serializerFactory");
        }
        StdSerializerProvider stdSerializerProviderCreateInstance = createInstance(serializationConfig, serializerFactory);
        if (stdSerializerProviderCreateInstance.getClass() != getClass()) {
            throw new IllegalStateException("Broken serializer provider: createInstance returned instance of type " + stdSerializerProviderCreateInstance.getClass() + "; blueprint of type " + getClass());
        }
        stdSerializerProviderCreateInstance._serializeValue(jsonGenerator, obj);
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public final void serializeValue(SerializationConfig serializationConfig, JsonGenerator jsonGenerator, Object obj, JavaType javaType, SerializerFactory serializerFactory) throws IOException {
        if (serializerFactory == null) {
            throw new IllegalArgumentException("Can not pass null serializerFactory");
        }
        StdSerializerProvider stdSerializerProviderCreateInstance = createInstance(serializationConfig, serializerFactory);
        if (stdSerializerProviderCreateInstance.getClass() != getClass()) {
            throw new IllegalStateException("Broken serializer provider: createInstance returned instance of type " + stdSerializerProviderCreateInstance.getClass() + "; blueprint of type " + getClass());
        }
        stdSerializerProviderCreateInstance._serializeValue(jsonGenerator, obj, javaType);
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public JsonSchema generateJsonSchema(Class<?> cls, SerializationConfig serializationConfig, SerializerFactory serializerFactory) {
        if (cls == null) {
            throw new IllegalArgumentException("A class must be provided");
        }
        StdSerializerProvider stdSerializerProviderCreateInstance = createInstance(serializationConfig, serializerFactory);
        if (stdSerializerProviderCreateInstance.getClass() != getClass()) {
            throw new IllegalStateException("Broken serializer provider: createInstance returned instance of type " + stdSerializerProviderCreateInstance.getClass() + "; blueprint of type " + getClass());
        }
        Object objFindValueSerializer = stdSerializerProviderCreateInstance.findValueSerializer(cls, (BeanProperty) null);
        JsonNode schema = objFindValueSerializer instanceof SchemaAware ? ((SchemaAware) objFindValueSerializer).getSchema(stdSerializerProviderCreateInstance, null) : JsonSchema.getDefaultSchemaNode();
        if (!(schema instanceof ObjectNode)) {
            throw new IllegalArgumentException("Class " + cls.getName() + " would not be serialized as a JSON object and therefore has no schema");
        }
        return new JsonSchema((ObjectNode) schema);
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public boolean hasSerializerFor(SerializationConfig serializationConfig, Class<?> cls, SerializerFactory serializerFactory) {
        return createInstance(serializationConfig, serializerFactory)._findExplicitUntypedSerializer(cls, null) != null;
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public int cachedSerializersCount() {
        return this._serializerCache.size();
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public void flushCachedSerializers() {
        this._serializerCache.flush();
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer<Object> findValueSerializer(Class<?> cls, BeanProperty beanProperty) {
        JsonSerializer<Object> jsonSerializerUntypedValueSerializer = this._knownSerializers.untypedValueSerializer(cls);
        return (jsonSerializerUntypedValueSerializer == null && (jsonSerializerUntypedValueSerializer = this._serializerCache.untypedValueSerializer(cls)) == null && (jsonSerializerUntypedValueSerializer = this._serializerCache.untypedValueSerializer(this._config.constructType(cls))) == null && (jsonSerializerUntypedValueSerializer = _createAndCacheUntypedSerializer(cls, beanProperty)) == null) ? getUnknownTypeSerializer(cls) : _handleContextualResolvable(jsonSerializerUntypedValueSerializer, beanProperty);
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer<Object> findValueSerializer(JavaType javaType, BeanProperty beanProperty) {
        JsonSerializer<Object> jsonSerializerUntypedValueSerializer = this._knownSerializers.untypedValueSerializer(javaType);
        return (jsonSerializerUntypedValueSerializer == null && (jsonSerializerUntypedValueSerializer = this._serializerCache.untypedValueSerializer(javaType)) == null && (jsonSerializerUntypedValueSerializer = _createAndCacheUntypedSerializer(javaType, beanProperty)) == null) ? getUnknownTypeSerializer(javaType.getRawClass()) : _handleContextualResolvable(jsonSerializerUntypedValueSerializer, beanProperty);
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer<Object> findTypedValueSerializer(Class<?> cls, boolean z, BeanProperty beanProperty) {
        JsonSerializer<Object> jsonSerializerTypedValueSerializer = this._knownSerializers.typedValueSerializer(cls);
        if (jsonSerializerTypedValueSerializer == null && (jsonSerializerTypedValueSerializer = this._serializerCache.typedValueSerializer(cls)) == null) {
            JsonSerializer<Object> jsonSerializerFindValueSerializer = findValueSerializer(cls, beanProperty);
            TypeSerializer typeSerializerCreateTypeSerializer = this._serializerFactory.createTypeSerializer(this._config, this._config.constructType(cls), beanProperty);
            jsonSerializerTypedValueSerializer = typeSerializerCreateTypeSerializer != null ? new WrappedSerializer(typeSerializerCreateTypeSerializer, jsonSerializerFindValueSerializer) : jsonSerializerFindValueSerializer;
            if (z) {
                this._serializerCache.addTypedSerializer(cls, jsonSerializerTypedValueSerializer);
            }
        }
        return jsonSerializerTypedValueSerializer;
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer<Object> findTypedValueSerializer(JavaType javaType, boolean z, BeanProperty beanProperty) {
        JsonSerializer<Object> jsonSerializerTypedValueSerializer = this._knownSerializers.typedValueSerializer(javaType);
        if (jsonSerializerTypedValueSerializer == null && (jsonSerializerTypedValueSerializer = this._serializerCache.typedValueSerializer(javaType)) == null) {
            JsonSerializer<Object> jsonSerializerFindValueSerializer = findValueSerializer(javaType, beanProperty);
            TypeSerializer typeSerializerCreateTypeSerializer = this._serializerFactory.createTypeSerializer(this._config, javaType, beanProperty);
            jsonSerializerTypedValueSerializer = typeSerializerCreateTypeSerializer != null ? new WrappedSerializer(typeSerializerCreateTypeSerializer, jsonSerializerFindValueSerializer) : jsonSerializerFindValueSerializer;
            if (z) {
                this._serializerCache.addTypedSerializer(javaType, jsonSerializerTypedValueSerializer);
            }
        }
        return jsonSerializerTypedValueSerializer;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer<Object> findKeySerializer(JavaType javaType, BeanProperty beanProperty) {
        JsonSerializer<Object> jsonSerializerCreateKeySerializer = this._serializerFactory.createKeySerializer(this._config, javaType, beanProperty);
        JsonSerializer<Object> jsonSerializer = jsonSerializerCreateKeySerializer;
        if (jsonSerializerCreateKeySerializer == null) {
            jsonSerializer = this._keySerializer;
        }
        if (jsonSerializer instanceof ContextualSerializer) {
            return ((ContextualSerializer) jsonSerializer).createContextual(this._config, beanProperty);
        }
        return jsonSerializer;
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer<Object> getNullKeySerializer() {
        return this._nullKeySerializer;
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer<Object> getNullValueSerializer() {
        return this._nullValueSerializer;
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer<Object> getUnknownTypeSerializer(Class<?> cls) {
        return this._unknownTypeSerializer;
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public final void defaultSerializeDateValue(long j, JsonGenerator jsonGenerator) {
        if (isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)) {
            jsonGenerator.writeNumber(j);
            return;
        }
        if (this._dateFormat == null) {
            this._dateFormat = (DateFormat) this._config.getDateFormat().clone();
        }
        jsonGenerator.writeString(this._dateFormat.format(new Date(j)));
    }

    @Override // org.codehaus.jackson.map.SerializerProvider
    public final void defaultSerializeDateValue(Date date, JsonGenerator jsonGenerator) {
        if (isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)) {
            jsonGenerator.writeNumber(date.getTime());
            return;
        }
        if (this._dateFormat == null) {
            this._dateFormat = (DateFormat) this._config.getDateFormat().clone();
        }
        jsonGenerator.writeString(this._dateFormat.format(date));
    }

    protected void _serializeValue(JsonGenerator jsonGenerator, Object obj) throws IOException {
        JsonSerializer<Object> jsonSerializerFindTypedValueSerializer;
        boolean zIsEnabled;
        if (obj == null) {
            jsonSerializerFindTypedValueSerializer = getNullValueSerializer();
            zIsEnabled = false;
        } else {
            jsonSerializerFindTypedValueSerializer = findTypedValueSerializer(obj.getClass(), true, (BeanProperty) null);
            zIsEnabled = this._config.isEnabled(SerializationConfig.Feature.WRAP_ROOT_VALUE);
            if (zIsEnabled) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeFieldName(this._rootNames.findRootName(obj.getClass(), this._config));
            }
        }
        try {
            jsonSerializerFindTypedValueSerializer.serialize(obj, jsonGenerator, this);
            if (zIsEnabled) {
                jsonGenerator.writeEndObject();
            }
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            String message = e2.getMessage();
            if (message == null) {
                message = "[no message for " + e2.getClass().getName() + "]";
            }
            throw new JsonMappingException(message, e2);
        }
    }

    protected void _serializeValue(JsonGenerator jsonGenerator, Object obj, JavaType javaType) throws IOException {
        JsonSerializer<Object> jsonSerializerFindTypedValueSerializer;
        boolean zIsEnabled;
        if (obj == null) {
            jsonSerializerFindTypedValueSerializer = getNullValueSerializer();
            zIsEnabled = false;
        } else {
            if (!javaType.getRawClass().isAssignableFrom(obj.getClass())) {
                _reportIncompatibleRootType(obj, javaType);
            }
            jsonSerializerFindTypedValueSerializer = findTypedValueSerializer(javaType, true, (BeanProperty) null);
            zIsEnabled = this._config.isEnabled(SerializationConfig.Feature.WRAP_ROOT_VALUE);
            if (zIsEnabled) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeFieldName(this._rootNames.findRootName(javaType, this._config));
            }
        }
        try {
            jsonSerializerFindTypedValueSerializer.serialize(obj, jsonGenerator, this);
            if (zIsEnabled) {
                jsonGenerator.writeEndObject();
            }
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            String message = e2.getMessage();
            if (message == null) {
                message = "[no message for " + e2.getClass().getName() + "]";
            }
            throw new JsonMappingException(message, e2);
        }
    }

    protected void _reportIncompatibleRootType(Object obj, JavaType javaType) throws JsonMappingException {
        if (javaType.isPrimitive() && ClassUtil.wrapperType(javaType.getRawClass()).isAssignableFrom(obj.getClass())) {
        } else {
            throw new JsonMappingException("Incompatible types: declared root type (" + javaType + ") vs " + obj.getClass().getName());
        }
    }

    protected JsonSerializer<Object> _findExplicitUntypedSerializer(Class<?> cls, BeanProperty beanProperty) {
        JsonSerializer<Object> jsonSerializerUntypedValueSerializer = this._knownSerializers.untypedValueSerializer(cls);
        if (jsonSerializerUntypedValueSerializer == null) {
            JsonSerializer<Object> jsonSerializerUntypedValueSerializer2 = this._serializerCache.untypedValueSerializer(cls);
            if (jsonSerializerUntypedValueSerializer2 == null) {
                try {
                    return _createAndCacheUntypedSerializer(cls, beanProperty);
                } catch (Exception e) {
                    return null;
                }
            }
            return jsonSerializerUntypedValueSerializer2;
        }
        return jsonSerializerUntypedValueSerializer;
    }

    protected JsonSerializer<Object> _createAndCacheUntypedSerializer(Class<?> cls, BeanProperty beanProperty) throws JsonMappingException {
        try {
            JsonSerializer<Object> jsonSerializer_createUntypedSerializer = _createUntypedSerializer(this._config.constructType(cls), beanProperty);
            if (jsonSerializer_createUntypedSerializer != null) {
                this._serializerCache.addAndResolveNonTypedSerializer(cls, jsonSerializer_createUntypedSerializer, this);
            }
            return jsonSerializer_createUntypedSerializer;
        } catch (IllegalArgumentException e) {
            throw new JsonMappingException(e.getMessage(), null, e);
        }
    }

    protected JsonSerializer<Object> _createAndCacheUntypedSerializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        try {
            JsonSerializer<Object> jsonSerializer_createUntypedSerializer = _createUntypedSerializer(javaType, beanProperty);
            if (jsonSerializer_createUntypedSerializer != null) {
                this._serializerCache.addAndResolveNonTypedSerializer(javaType, jsonSerializer_createUntypedSerializer, this);
            }
            return jsonSerializer_createUntypedSerializer;
        } catch (IllegalArgumentException e) {
            throw new JsonMappingException(e.getMessage(), null, e);
        }
    }

    protected JsonSerializer<Object> _createUntypedSerializer(JavaType javaType, BeanProperty beanProperty) {
        return this._serializerFactory.createSerializer(this._config, javaType, beanProperty);
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 301. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
        */
    protected org.codehaus.jackson.map.JsonSerializer<java.lang.Object> _handleContextualResolvable(org.codehaus.jackson.map.JsonSerializer<java.lang.Object> r3, org.codehaus.jackson.map.BeanProperty r4) {
        /*
            r2 = this;
            boolean r0 = r3 instanceof org.codehaus.jackson.map.ContextualSerializer
            if (r0 != 0) goto L5
        L4:
            return r3
        L5:
            r0 = r3
            org.codehaus.jackson.map.ContextualSerializer r0 = (org.codehaus.jackson.map.ContextualSerializer) r0
            org.codehaus.jackson.map.SerializationConfig r1 = r2._config
            org.codehaus.jackson.map.JsonSerializer r1 = r0.createContextual(r1, r4)
            if (r1 == r3) goto L1c
            boolean r0 = r1 instanceof org.codehaus.jackson.map.ResolvableSerializer
            if (r0 == 0) goto L1a
            r0 = r1
            org.codehaus.jackson.map.ResolvableSerializer r0 = (org.codehaus.jackson.map.ResolvableSerializer) r0
            r0.resolve(r2)
        L1a:
            r3 = r1
            goto L4
        L1c:
            r1 = r3
            goto L1a
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.map.ser.StdSerializerProvider._handleContextualResolvable(org.codehaus.jackson.map.JsonSerializer, org.codehaus.jackson.map.BeanProperty):org.codehaus.jackson.map.JsonSerializer");
    }

    final class WrappedSerializer extends JsonSerializer<Object> {
        protected final JsonSerializer<Object> _serializer;
        protected final TypeSerializer _typeSerializer;

        public WrappedSerializer(TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
            this._typeSerializer = typeSerializer;
            this._serializer = jsonSerializer;
        }

        @Override // org.codehaus.jackson.map.JsonSerializer
        public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            this._serializer.serializeWithType(obj, jsonGenerator, serializerProvider, this._typeSerializer);
        }

        @Override // org.codehaus.jackson.map.JsonSerializer
        public void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
            this._serializer.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
        }

        @Override // org.codehaus.jackson.map.JsonSerializer
        public Class<Object> handledType() {
            return Object.class;
        }
    }
}
