package org.codehaus.jackson.map.ser;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.RandomAccess;
import java.util.TimeZone;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.ContextualSerializer;
import org.codehaus.jackson.map.JsonSerializable;
import org.codehaus.jackson.map.JsonSerializableWithType;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializerFactory;
import org.codehaus.jackson.map.Serializers;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ext.OptionalHandlerFactory;
import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.jsontype.NamedType;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.map.ser.impl.IndexedStringListSerializer;
import org.codehaus.jackson.map.ser.impl.InetAddressSerializer;
import org.codehaus.jackson.map.ser.impl.ObjectArraySerializer;
import org.codehaus.jackson.map.ser.impl.StringCollectionSerializer;
import org.codehaus.jackson.map.ser.impl.TimeZoneSerializer;
import org.codehaus.jackson.map.type.ArrayType;
import org.codehaus.jackson.map.type.CollectionLikeType;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.MapLikeType;
import org.codehaus.jackson.map.type.MapType;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.map.util.EnumValues;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.util.TokenBuffer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class BasicSerializerFactory extends SerializerFactory {
    protected static final HashMap<String, JsonSerializer<?>> _arraySerializers;
    protected static final HashMap<String, JsonSerializer<?>> _concrete = new HashMap<>();
    protected static final HashMap<String, Class<? extends JsonSerializer<?>>> _concreteLazy = new HashMap<>();
    protected OptionalHandlerFactory optionalHandlers = OptionalHandlerFactory.instance;

    @Override // org.codehaus.jackson.map.SerializerFactory
    public abstract JsonSerializer<Object> createSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanProperty beanProperty);

    protected abstract Iterable<Serializers> customSerializers();

    static {
        _concrete.put(String.class.getName(), new StdSerializers.StringSerializer());
        ToStringSerializer toStringSerializer = ToStringSerializer.instance;
        _concrete.put(StringBuffer.class.getName(), toStringSerializer);
        _concrete.put(StringBuilder.class.getName(), toStringSerializer);
        _concrete.put(Character.class.getName(), toStringSerializer);
        _concrete.put(Character.TYPE.getName(), toStringSerializer);
        _concrete.put(Boolean.TYPE.getName(), new StdSerializers.BooleanSerializer(true));
        _concrete.put(Boolean.class.getName(), new StdSerializers.BooleanSerializer(false));
        StdSerializers.IntegerSerializer integerSerializer = new StdSerializers.IntegerSerializer();
        _concrete.put(Integer.class.getName(), integerSerializer);
        _concrete.put(Integer.TYPE.getName(), integerSerializer);
        _concrete.put(Long.class.getName(), StdSerializers.LongSerializer.instance);
        _concrete.put(Long.TYPE.getName(), StdSerializers.LongSerializer.instance);
        _concrete.put(Byte.class.getName(), StdSerializers.IntLikeSerializer.instance);
        _concrete.put(Byte.TYPE.getName(), StdSerializers.IntLikeSerializer.instance);
        _concrete.put(Short.class.getName(), StdSerializers.IntLikeSerializer.instance);
        _concrete.put(Short.TYPE.getName(), StdSerializers.IntLikeSerializer.instance);
        _concrete.put(Float.class.getName(), StdSerializers.FloatSerializer.instance);
        _concrete.put(Float.TYPE.getName(), StdSerializers.FloatSerializer.instance);
        _concrete.put(Double.class.getName(), StdSerializers.DoubleSerializer.instance);
        _concrete.put(Double.TYPE.getName(), StdSerializers.DoubleSerializer.instance);
        StdSerializers.NumberSerializer numberSerializer = new StdSerializers.NumberSerializer();
        _concrete.put(BigInteger.class.getName(), numberSerializer);
        _concrete.put(BigDecimal.class.getName(), numberSerializer);
        _concrete.put(Calendar.class.getName(), StdSerializers.CalendarSerializer.instance);
        _concrete.put(Date.class.getName(), StdSerializers.UtilDateSerializer.instance);
        _concrete.put(java.sql.Date.class.getName(), new StdSerializers.SqlDateSerializer());
        _concrete.put(Time.class.getName(), new StdSerializers.SqlTimeSerializer());
        _concrete.put(Timestamp.class.getName(), StdSerializers.UtilDateSerializer.instance);
        for (Map.Entry<Class<?>, Object> entry : new JdkSerializers().provide()) {
            Object value = entry.getValue();
            if (value instanceof JsonSerializer) {
                _concrete.put(entry.getKey().getName(), (JsonSerializer) value);
            } else if (value instanceof Class) {
                _concreteLazy.put(entry.getKey().getName(), (Class) value);
            } else {
                throw new IllegalStateException("Internal error: unrecognized value of type " + entry.getClass().getName());
            }
        }
        _concreteLazy.put(TokenBuffer.class.getName(), StdSerializers.TokenBufferSerializer.class);
        _arraySerializers = new HashMap<>();
        _arraySerializers.put(boolean[].class.getName(), new ArraySerializers.BooleanArraySerializer());
        _arraySerializers.put(byte[].class.getName(), new ArraySerializers.ByteArraySerializer());
        _arraySerializers.put(char[].class.getName(), new ArraySerializers.CharArraySerializer());
        _arraySerializers.put(short[].class.getName(), new ArraySerializers.ShortArraySerializer());
        _arraySerializers.put(int[].class.getName(), new ArraySerializers.IntArraySerializer());
        _arraySerializers.put(long[].class.getName(), new ArraySerializers.LongArraySerializer());
        _arraySerializers.put(float[].class.getName(), new ArraySerializers.FloatArraySerializer());
        _arraySerializers.put(double[].class.getName(), new ArraySerializers.DoubleArraySerializer());
    }

    protected BasicSerializerFactory() {
    }

    @Override // org.codehaus.jackson.map.SerializerFactory
    public TypeSerializer createTypeSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanProperty beanProperty) {
        Collection<NamedType> collectionCollectAndResolveSubtypes;
        AnnotatedClass classInfo = ((BasicBeanDescription) serializationConfig.introspectClassAnnotations(javaType.getRawClass())).getClassInfo();
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder<?> typeResolverBuilderFindTypeResolver = annotationIntrospector.findTypeResolver(serializationConfig, classInfo, javaType);
        if (typeResolverBuilderFindTypeResolver == null) {
            typeResolverBuilderFindTypeResolver = serializationConfig.getDefaultTyper(javaType);
            collectionCollectAndResolveSubtypes = null;
        } else {
            collectionCollectAndResolveSubtypes = serializationConfig.getSubtypeResolver().collectAndResolveSubtypes(classInfo, serializationConfig, annotationIntrospector);
        }
        if (typeResolverBuilderFindTypeResolver == null) {
            return null;
        }
        return typeResolverBuilderFindTypeResolver.buildTypeSerializer(serializationConfig, javaType, collectionCollectAndResolveSubtypes, beanProperty);
    }

    public final JsonSerializer<?> getNullSerializer() {
        return NullSerializer.instance;
    }

    public final JsonSerializer<?> findSerializerByLookup(JavaType javaType, SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z) {
        String name = javaType.getRawClass().getName();
        JsonSerializer<?> jsonSerializer = _concrete.get(name);
        if (jsonSerializer == null) {
            Class<? extends JsonSerializer<?>> cls = _concreteLazy.get(name);
            if (cls != null) {
                try {
                    return cls.newInstance();
                } catch (Exception e) {
                    throw new IllegalStateException("Failed to instantiate standard serializer (of type " + cls.getName() + "): " + e.getMessage(), e);
                }
            }
            return null;
        }
        return jsonSerializer;
    }

    public final JsonSerializer<?> findSerializerByPrimaryType(JavaType javaType, SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z) {
        Class<?> rawClass = javaType.getRawClass();
        if (JsonSerializable.class.isAssignableFrom(rawClass)) {
            if (JsonSerializableWithType.class.isAssignableFrom(rawClass)) {
                return StdSerializers.SerializableWithTypeSerializer.instance;
            }
            return StdSerializers.SerializableSerializer.instance;
        }
        AnnotatedMethod annotatedMethodFindJsonValueMethod = basicBeanDescription.findJsonValueMethod();
        if (annotatedMethodFindJsonValueMethod != null) {
            Method annotated = annotatedMethodFindJsonValueMethod.getAnnotated();
            if (serializationConfig.isEnabled(SerializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                ClassUtil.checkAndFixAccess(annotated);
            }
            return new JsonValueSerializer(annotated, findSerializerFromAnnotation(serializationConfig, annotatedMethodFindJsonValueMethod, beanProperty), beanProperty);
        }
        if (InetAddress.class.isAssignableFrom(rawClass)) {
            return InetAddressSerializer.instance;
        }
        if (TimeZone.class.isAssignableFrom(rawClass)) {
            return TimeZoneSerializer.instance;
        }
        JsonSerializer<?> jsonSerializerFindSerializer = this.optionalHandlers.findSerializer(serializationConfig, javaType);
        if (jsonSerializerFindSerializer == null) {
            if (Number.class.isAssignableFrom(rawClass)) {
                return StdSerializers.NumberSerializer.instance;
            }
            if (Enum.class.isAssignableFrom(rawClass)) {
                return EnumSerializer.construct(rawClass, serializationConfig, basicBeanDescription);
            }
            if (Calendar.class.isAssignableFrom(rawClass)) {
                return StdSerializers.CalendarSerializer.instance;
            }
            if (Date.class.isAssignableFrom(rawClass)) {
                return StdSerializers.UtilDateSerializer.instance;
            }
            return null;
        }
        return jsonSerializerFindSerializer;
    }

    public final JsonSerializer<?> findSerializerByAddonType(SerializationConfig serializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z) {
        Class<?> rawClass = javaType.getRawClass();
        if (Iterator.class.isAssignableFrom(rawClass)) {
            return buildIteratorSerializer(serializationConfig, javaType, basicBeanDescription, beanProperty, z);
        }
        if (Iterable.class.isAssignableFrom(rawClass)) {
            return buildIterableSerializer(serializationConfig, javaType, basicBeanDescription, beanProperty, z);
        }
        if (CharSequence.class.isAssignableFrom(rawClass)) {
            return ToStringSerializer.instance;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected JsonSerializer<Object> findSerializerFromAnnotation(SerializationConfig serializationConfig, Annotated annotated, BeanProperty beanProperty) {
        Object objFindSerializer = serializationConfig.getAnnotationIntrospector().findSerializer(annotated);
        if (objFindSerializer == null) {
            return null;
        }
        if (objFindSerializer instanceof JsonSerializer) {
            JsonSerializer<Object> jsonSerializer = (JsonSerializer) objFindSerializer;
            if (jsonSerializer instanceof ContextualSerializer) {
                return ((ContextualSerializer) jsonSerializer).createContextual(serializationConfig, beanProperty);
            }
            return jsonSerializer;
        }
        if (!(objFindSerializer instanceof Class)) {
            throw new IllegalStateException("AnnotationIntrospector returned value of type " + objFindSerializer.getClass().getName() + "; expected type JsonSerializer or Class<JsonSerializer> instead");
        }
        Class<? extends JsonSerializer<?>> cls = (Class) objFindSerializer;
        if (!JsonSerializer.class.isAssignableFrom(cls)) {
            throw new IllegalStateException("AnnotationIntrospector returned Class " + cls.getName() + "; expected Class<JsonSerializer>");
        }
        JsonSerializer<Object> jsonSerializerSerializerInstance = serializationConfig.serializerInstance(annotated, cls);
        if (jsonSerializerSerializerInstance instanceof ContextualSerializer) {
            return ((ContextualSerializer) jsonSerializerSerializerInstance).createContextual(serializationConfig, beanProperty);
        }
        return jsonSerializerSerializerInstance;
    }

    public JsonSerializer<?> buildContainerSerializer(SerializationConfig serializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z) {
        boolean zUsesStaticTyping;
        TypeSerializer typeSerializerCreateTypeSerializer = createTypeSerializer(serializationConfig, javaType.getContentType(), beanProperty);
        if (typeSerializerCreateTypeSerializer != null) {
            zUsesStaticTyping = false;
        } else {
            zUsesStaticTyping = !z ? usesStaticTyping(serializationConfig, basicBeanDescription, typeSerializerCreateTypeSerializer, beanProperty) : z;
        }
        JsonSerializer<Object> jsonSerializerFindContentSerializer = findContentSerializer(serializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
        if (javaType.isMapLikeType()) {
            MapLikeType mapLikeType = (MapLikeType) javaType;
            JsonSerializer<Object> jsonSerializerFindKeySerializer = findKeySerializer(serializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
            if (mapLikeType.isTrueMapType()) {
                return buildMapSerializer(serializationConfig, (MapType) mapLikeType, basicBeanDescription, beanProperty, zUsesStaticTyping, jsonSerializerFindKeySerializer, typeSerializerCreateTypeSerializer, jsonSerializerFindContentSerializer);
            }
            return buildMapLikeSerializer(serializationConfig, mapLikeType, basicBeanDescription, beanProperty, zUsesStaticTyping, jsonSerializerFindKeySerializer, typeSerializerCreateTypeSerializer, jsonSerializerFindContentSerializer);
        }
        if (javaType.isCollectionLikeType()) {
            CollectionLikeType collectionLikeType = (CollectionLikeType) javaType;
            if (collectionLikeType.isTrueCollectionType()) {
                return buildCollectionSerializer(serializationConfig, (CollectionType) collectionLikeType, basicBeanDescription, beanProperty, zUsesStaticTyping, typeSerializerCreateTypeSerializer, jsonSerializerFindContentSerializer);
            }
            return buildCollectionLikeSerializer(serializationConfig, collectionLikeType, basicBeanDescription, beanProperty, zUsesStaticTyping, typeSerializerCreateTypeSerializer, jsonSerializerFindContentSerializer);
        }
        if (javaType.isArrayType()) {
            return buildArraySerializer(serializationConfig, (ArrayType) javaType, basicBeanDescription, beanProperty, zUsesStaticTyping, typeSerializerCreateTypeSerializer, jsonSerializerFindContentSerializer);
        }
        return null;
    }

    protected JsonSerializer<?> buildCollectionLikeSerializer(SerializationConfig serializationConfig, CollectionLikeType collectionLikeType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        Iterator<Serializers> it = customSerializers().iterator();
        while (it.hasNext()) {
            JsonSerializer<?> jsonSerializerFindCollectionLikeSerializer = it.next().findCollectionLikeSerializer(serializationConfig, collectionLikeType, basicBeanDescription, beanProperty, typeSerializer, jsonSerializer);
            if (jsonSerializerFindCollectionLikeSerializer != null) {
                return jsonSerializerFindCollectionLikeSerializer;
            }
        }
        return null;
    }

    protected JsonSerializer<?> buildCollectionSerializer(SerializationConfig serializationConfig, CollectionType collectionType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        Iterator<Serializers> it = customSerializers().iterator();
        while (it.hasNext()) {
            JsonSerializer<?> jsonSerializerFindCollectionSerializer = it.next().findCollectionSerializer(serializationConfig, collectionType, basicBeanDescription, beanProperty, typeSerializer, jsonSerializer);
            if (jsonSerializerFindCollectionSerializer != null) {
                return jsonSerializerFindCollectionSerializer;
            }
        }
        Class<?> rawClass = collectionType.getRawClass();
        if (EnumSet.class.isAssignableFrom(rawClass)) {
            return buildEnumSetSerializer(serializationConfig, collectionType, basicBeanDescription, beanProperty, z, typeSerializer, jsonSerializer);
        }
        Class<?> rawClass2 = collectionType.getContentType().getRawClass();
        if (isIndexedList(rawClass)) {
            if (rawClass2 == String.class) {
                return new IndexedStringListSerializer(beanProperty);
            }
            return ContainerSerializers.indexedListSerializer(collectionType.getContentType(), z, typeSerializer, beanProperty, jsonSerializer);
        }
        if (rawClass2 == String.class) {
            return new StringCollectionSerializer(beanProperty);
        }
        return ContainerSerializers.collectionSerializer(collectionType.getContentType(), z, typeSerializer, beanProperty, jsonSerializer);
    }

    protected JsonSerializer<?> buildEnumSetSerializer(SerializationConfig serializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        JavaType contentType = javaType.getContentType();
        if (!contentType.isEnumType()) {
            contentType = null;
        }
        return ContainerSerializers.enumSetSerializer(contentType, beanProperty);
    }

    protected boolean isIndexedList(Class<?> cls) {
        return RandomAccess.class.isAssignableFrom(cls);
    }

    protected JsonSerializer<?> buildMapLikeSerializer(SerializationConfig serializationConfig, MapLikeType mapLikeType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer2) {
        Iterator<Serializers> it = customSerializers().iterator();
        while (it.hasNext()) {
            JsonSerializer<?> jsonSerializerFindMapLikeSerializer = it.next().findMapLikeSerializer(serializationConfig, mapLikeType, basicBeanDescription, beanProperty, jsonSerializer, typeSerializer, jsonSerializer2);
            if (jsonSerializerFindMapLikeSerializer != null) {
                return jsonSerializerFindMapLikeSerializer;
            }
        }
        return null;
    }

    protected JsonSerializer<?> buildMapSerializer(SerializationConfig serializationConfig, MapType mapType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer2) {
        Iterator<Serializers> it = customSerializers().iterator();
        while (it.hasNext()) {
            JsonSerializer<?> jsonSerializerFindMapSerializer = it.next().findMapSerializer(serializationConfig, mapType, basicBeanDescription, beanProperty, jsonSerializer, typeSerializer, jsonSerializer2);
            if (jsonSerializerFindMapSerializer != null) {
                return jsonSerializerFindMapSerializer;
            }
        }
        if (EnumMap.class.isAssignableFrom(mapType.getRawClass())) {
            return buildEnumMapSerializer(serializationConfig, mapType, basicBeanDescription, beanProperty, z, typeSerializer, jsonSerializer2);
        }
        return MapSerializer.construct(serializationConfig.getAnnotationIntrospector().findPropertiesToIgnore(basicBeanDescription.getClassInfo()), mapType, z, typeSerializer, beanProperty, jsonSerializer, jsonSerializer2);
    }

    protected JsonSerializer<?> buildEnumMapSerializer(SerializationConfig serializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        JavaType keyType = javaType.getKeyType();
        EnumValues enumValuesConstruct = null;
        if (keyType.isEnumType()) {
            enumValuesConstruct = EnumValues.construct(keyType.getRawClass(), serializationConfig.getAnnotationIntrospector());
        }
        return new EnumMapSerializer(javaType.getContentType(), z, enumValuesConstruct, typeSerializer, beanProperty, jsonSerializer);
    }

    protected JsonSerializer<?> buildArraySerializer(SerializationConfig serializationConfig, ArrayType arrayType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        Class<?> rawClass = arrayType.getRawClass();
        if (String[].class == rawClass) {
            return new ArraySerializers.StringArraySerializer(beanProperty);
        }
        JsonSerializer<?> jsonSerializer2 = _arraySerializers.get(rawClass.getName());
        return jsonSerializer2 == null ? new ObjectArraySerializer(arrayType.getContentType(), z, typeSerializer, beanProperty, jsonSerializer) : jsonSerializer2;
    }

    protected JsonSerializer<?> buildIteratorSerializer(SerializationConfig serializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z) {
        JavaType javaTypeContainedType = javaType.containedType(0);
        if (javaTypeContainedType == null) {
            javaTypeContainedType = TypeFactory.unknownType();
        }
        TypeSerializer typeSerializerCreateTypeSerializer = createTypeSerializer(serializationConfig, javaTypeContainedType, beanProperty);
        return ContainerSerializers.iteratorSerializer(javaTypeContainedType, usesStaticTyping(serializationConfig, basicBeanDescription, typeSerializerCreateTypeSerializer, beanProperty), typeSerializerCreateTypeSerializer, beanProperty);
    }

    protected JsonSerializer<?> buildIterableSerializer(SerializationConfig serializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z) {
        JavaType javaTypeContainedType = javaType.containedType(0);
        if (javaTypeContainedType == null) {
            javaTypeContainedType = TypeFactory.unknownType();
        }
        TypeSerializer typeSerializerCreateTypeSerializer = createTypeSerializer(serializationConfig, javaTypeContainedType, beanProperty);
        return ContainerSerializers.iterableSerializer(javaTypeContainedType, usesStaticTyping(serializationConfig, basicBeanDescription, typeSerializerCreateTypeSerializer, beanProperty), typeSerializerCreateTypeSerializer, beanProperty);
    }

    protected <T extends JavaType> T modifyTypeByAnnotation(SerializationConfig serializationConfig, Annotated annotated, T t) {
        Class<?> clsFindSerializationType = serializationConfig.getAnnotationIntrospector().findSerializationType(annotated);
        if (clsFindSerializationType != null) {
            try {
                t = (T) t.widenBy(clsFindSerializationType);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Failed to widen type " + t + " with concrete-type annotation (value " + clsFindSerializationType.getName() + "), method '" + annotated.getName() + "': " + e.getMessage());
            }
        }
        return (T) modifySecondaryTypesByAnnotation(serializationConfig, annotated, t);
    }

    protected static <T extends JavaType> T modifySecondaryTypesByAnnotation(SerializationConfig serializationConfig, Annotated annotated, T t) {
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        if (t.isContainerType()) {
            Class<?> clsFindSerializationKeyType = annotationIntrospector.findSerializationKeyType(annotated, t.getKeyType());
            if (clsFindSerializationKeyType != null) {
                if (!(t instanceof MapType)) {
                    throw new IllegalArgumentException("Illegal key-type annotation: type " + t + " is not a Map type");
                }
                try {
                    t = (T) ((MapType) t).widenKey(clsFindSerializationKeyType);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Failed to narrow key type " + t + " with key-type annotation (" + clsFindSerializationKeyType.getName() + "): " + e.getMessage());
                }
            }
            Class<?> clsFindSerializationContentType = annotationIntrospector.findSerializationContentType(annotated, t.getContentType());
            if (clsFindSerializationContentType == null) {
                return t;
            }
            try {
                return (T) t.widenContentsBy(clsFindSerializationContentType);
            } catch (IllegalArgumentException e2) {
                throw new IllegalArgumentException("Failed to narrow content type " + t + " with content-type annotation (" + clsFindSerializationContentType.getName() + "): " + e2.getMessage());
            }
        }
        return t;
    }

    protected static JsonSerializer<Object> findKeySerializer(SerializationConfig serializationConfig, Annotated annotated, BeanProperty beanProperty) {
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        Class<? extends JsonSerializer<?>> clsFindKeySerializer = annotationIntrospector.findKeySerializer(annotated);
        if ((clsFindKeySerializer == null || clsFindKeySerializer == JsonSerializer.None.class) && beanProperty != null) {
            clsFindKeySerializer = annotationIntrospector.findKeySerializer(beanProperty.getMember());
        }
        if (clsFindKeySerializer == null || clsFindKeySerializer == JsonSerializer.None.class) {
            return null;
        }
        return serializationConfig.serializerInstance(annotated, clsFindKeySerializer);
    }

    protected static JsonSerializer<Object> findContentSerializer(SerializationConfig serializationConfig, Annotated annotated, BeanProperty beanProperty) {
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        Class<? extends JsonSerializer<?>> clsFindContentSerializer = annotationIntrospector.findContentSerializer(annotated);
        if ((clsFindContentSerializer == null || clsFindContentSerializer == JsonSerializer.None.class) && beanProperty != null) {
            clsFindContentSerializer = annotationIntrospector.findContentSerializer(beanProperty.getMember());
        }
        if (clsFindContentSerializer == null || clsFindContentSerializer == JsonSerializer.None.class) {
            return null;
        }
        return serializationConfig.serializerInstance(annotated, clsFindContentSerializer);
    }

    protected boolean usesStaticTyping(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, TypeSerializer typeSerializer, BeanProperty beanProperty) {
        if (typeSerializer != null) {
            return false;
        }
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        JsonSerialize.Typing typingFindSerializationTyping = annotationIntrospector.findSerializationTyping(basicBeanDescription.getClassInfo());
        if (typingFindSerializationTyping != null) {
            if (typingFindSerializationTyping == JsonSerialize.Typing.STATIC) {
                return true;
            }
        } else if (serializationConfig.isEnabled(SerializationConfig.Feature.USE_STATIC_TYPING)) {
            return true;
        }
        if (beanProperty == null) {
            return false;
        }
        JavaType type = beanProperty.getType();
        if (!type.isContainerType()) {
            return false;
        }
        if (annotationIntrospector.findSerializationContentType(beanProperty.getMember(), beanProperty.getType()) != null) {
            return true;
        }
        return (type instanceof MapType) && annotationIntrospector.findSerializationKeyType(beanProperty.getMember(), beanProperty.getType()) != null;
    }
}
