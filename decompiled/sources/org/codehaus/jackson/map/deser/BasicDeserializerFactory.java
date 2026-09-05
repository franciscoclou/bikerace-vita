package org.codehaus.jackson.map.deser;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.ContextualDeserializer;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializerFactory;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.KeyDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.deser.impl.StringCollectionDeserializer;
import org.codehaus.jackson.map.ext.OptionalHandlerFactory;
import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.AnnotatedParameter;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.jsontype.NamedType;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.map.type.ArrayType;
import org.codehaus.jackson.map.type.CollectionLikeType;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.MapLikeType;
import org.codehaus.jackson.map.type.MapType;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.type.JavaType;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class BasicDeserializerFactory extends DeserializerFactory {
    protected static final HashMap<JavaType, JsonDeserializer<Object>> _arrayDeserializers;
    static final HashMap<String, Class<? extends Collection>> _collectionFallbacks;
    protected OptionalHandlerFactory optionalHandlers = OptionalHandlerFactory.instance;
    static final HashMap<JavaType, JsonDeserializer<Object>> _simpleDeserializers = StdDeserializers.constructAll();
    static final HashMap<String, Class<? extends Map>> _mapFallbacks = new HashMap<>();

    protected abstract JsonDeserializer<?> _findCustomArrayDeserializer(ArrayType arrayType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer);

    protected abstract JsonDeserializer<?> _findCustomCollectionDeserializer(CollectionType collectionType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer);

    protected abstract JsonDeserializer<?> _findCustomCollectionLikeDeserializer(CollectionLikeType collectionLikeType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer);

    protected abstract JsonDeserializer<?> _findCustomEnumDeserializer(Class<?> cls, DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty);

    protected abstract JsonDeserializer<?> _findCustomMapDeserializer(MapType mapType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer);

    protected abstract JsonDeserializer<?> _findCustomMapLikeDeserializer(MapLikeType mapLikeType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer);

    protected abstract JsonDeserializer<?> _findCustomTreeNodeDeserializer(Class<? extends JsonNode> cls, DeserializationConfig deserializationConfig, BeanProperty beanProperty);

    protected abstract JavaType mapAbstractType(DeserializationConfig deserializationConfig, JavaType javaType);

    @Override // org.codehaus.jackson.map.DeserializerFactory
    public abstract DeserializerFactory withConfig(DeserializerFactory.Config config);

    static {
        _mapFallbacks.put(Map.class.getName(), LinkedHashMap.class);
        _mapFallbacks.put(ConcurrentMap.class.getName(), ConcurrentHashMap.class);
        _mapFallbacks.put(SortedMap.class.getName(), TreeMap.class);
        _mapFallbacks.put("java.util.NavigableMap", TreeMap.class);
        try {
            _mapFallbacks.put(Class.forName("java.util.ConcurrentNavigableMap").getName(), (Class<? extends Map>) Class.forName("java.util.ConcurrentSkipListMap"));
        } catch (ClassNotFoundException e) {
        }
        _collectionFallbacks = new HashMap<>();
        _collectionFallbacks.put(Collection.class.getName(), ArrayList.class);
        _collectionFallbacks.put(List.class.getName(), ArrayList.class);
        _collectionFallbacks.put(Set.class.getName(), HashSet.class);
        _collectionFallbacks.put(SortedSet.class.getName(), TreeSet.class);
        _collectionFallbacks.put(Queue.class.getName(), LinkedList.class);
        _collectionFallbacks.put("java.util.Deque", LinkedList.class);
        _collectionFallbacks.put("java.util.NavigableSet", TreeSet.class);
        _arrayDeserializers = ArrayDeserializers.getAll();
    }

    protected BasicDeserializerFactory() {
    }

    @Override // org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer<?> createArrayDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, ArrayType arrayType, BeanProperty beanProperty) {
        JavaType contentType = arrayType.getContentType();
        JsonDeserializer<Object> jsonDeserializerFindValueDeserializer = (JsonDeserializer) contentType.getValueHandler();
        if (jsonDeserializerFindValueDeserializer == null) {
            JsonDeserializer<?> jsonDeserializer = _arrayDeserializers.get(contentType);
            if (jsonDeserializer != null) {
                JsonDeserializer<?> jsonDeserializer_findCustomArrayDeserializer = _findCustomArrayDeserializer(arrayType, deserializationConfig, deserializerProvider, beanProperty, null, null);
                return jsonDeserializer_findCustomArrayDeserializer != null ? jsonDeserializer_findCustomArrayDeserializer : jsonDeserializer;
            }
            if (contentType.isPrimitive()) {
                throw new IllegalArgumentException("Internal error: primitive type (" + arrayType + ") passed, no array deserializer found");
            }
        }
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        TypeDeserializer typeDeserializerFindTypeDeserializer = typeDeserializer == null ? findTypeDeserializer(deserializationConfig, contentType, beanProperty) : typeDeserializer;
        JsonDeserializer<?> jsonDeserializer_findCustomArrayDeserializer2 = _findCustomArrayDeserializer(arrayType, deserializationConfig, deserializerProvider, beanProperty, typeDeserializerFindTypeDeserializer, jsonDeserializerFindValueDeserializer);
        if (jsonDeserializer_findCustomArrayDeserializer2 == null) {
            if (jsonDeserializerFindValueDeserializer == null) {
                jsonDeserializerFindValueDeserializer = deserializerProvider.findValueDeserializer(deserializationConfig, contentType, beanProperty);
            }
            return new ArrayDeserializer(arrayType, jsonDeserializerFindValueDeserializer, typeDeserializerFindTypeDeserializer);
        }
        return jsonDeserializer_findCustomArrayDeserializer2;
    }

    @Override // org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer<?> createCollectionDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, CollectionType collectionType, BeanProperty beanProperty) {
        Class<?> cls;
        CollectionType collectionType2 = (CollectionType) mapAbstractType(deserializationConfig, collectionType);
        Class<?> rawClass = collectionType2.getRawClass();
        BasicBeanDescription basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectClassAnnotations(rawClass);
        JsonDeserializer<?> jsonDeserializerFindDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
        if (jsonDeserializerFindDeserializerFromAnnotation != null) {
            return jsonDeserializerFindDeserializerFromAnnotation;
        }
        CollectionType collectionType3 = (CollectionType) modifyTypeByAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), collectionType2, null);
        JavaType contentType = collectionType3.getContentType();
        JsonDeserializer<Object> jsonDeserializerFindValueDeserializer = (JsonDeserializer) contentType.getValueHandler();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        TypeDeserializer typeDeserializerFindTypeDeserializer = typeDeserializer == null ? findTypeDeserializer(deserializationConfig, contentType, beanProperty) : typeDeserializer;
        JsonDeserializer<?> jsonDeserializer_findCustomCollectionDeserializer = _findCustomCollectionDeserializer(collectionType3, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty, typeDeserializerFindTypeDeserializer, jsonDeserializerFindValueDeserializer);
        if (jsonDeserializer_findCustomCollectionDeserializer == null) {
            if (jsonDeserializerFindValueDeserializer == null) {
                if (EnumSet.class.isAssignableFrom(rawClass)) {
                    return new EnumSetDeserializer(constructEnumResolver(contentType.getRawClass(), deserializationConfig));
                }
                jsonDeserializerFindValueDeserializer = deserializerProvider.findValueDeserializer(deserializationConfig, contentType, beanProperty);
            }
            if (collectionType3.isInterface() || collectionType3.isAbstract()) {
                cls = (Class) _collectionFallbacks.get(rawClass.getName());
                if (cls == null) {
                    throw new IllegalArgumentException("Can not find a deserializer for non-concrete Collection type " + collectionType3);
                }
            } else {
                cls = rawClass;
            }
            Constructor constructorFindConstructor = ClassUtil.findConstructor(cls, deserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS));
            if (contentType.getRawClass() == String.class) {
                return new StringCollectionDeserializer(collectionType3, jsonDeserializerFindValueDeserializer, constructorFindConstructor);
            }
            return new CollectionDeserializer(collectionType3, jsonDeserializerFindValueDeserializer, typeDeserializerFindTypeDeserializer, constructorFindConstructor);
        }
        return jsonDeserializer_findCustomCollectionDeserializer;
    }

    @Override // org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer<?> createCollectionLikeDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, CollectionLikeType collectionLikeType, BeanProperty beanProperty) {
        CollectionLikeType collectionLikeType2 = (CollectionLikeType) mapAbstractType(deserializationConfig, collectionLikeType);
        BasicBeanDescription basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectClassAnnotations(collectionLikeType2.getRawClass());
        JsonDeserializer<?> jsonDeserializerFindDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
        if (jsonDeserializerFindDeserializerFromAnnotation != null) {
            return jsonDeserializerFindDeserializerFromAnnotation;
        }
        CollectionLikeType collectionLikeType3 = (CollectionLikeType) modifyTypeByAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), collectionLikeType2, null);
        JavaType contentType = collectionLikeType3.getContentType();
        JsonDeserializer<?> jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        return _findCustomCollectionLikeDeserializer(collectionLikeType3, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty, typeDeserializer == null ? findTypeDeserializer(deserializationConfig, contentType, beanProperty) : typeDeserializer, jsonDeserializer);
    }

    @Override // org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer<?> createMapDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, MapType mapType, BeanProperty beanProperty) {
        BasicBeanDescription basicBeanDescription;
        MapType mapType2 = (MapType) mapAbstractType(deserializationConfig, mapType);
        BasicBeanDescription basicBeanDescription2 = (BasicBeanDescription) deserializationConfig.introspectForCreation(mapType2);
        JsonDeserializer<?> jsonDeserializerFindDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, basicBeanDescription2.getClassInfo(), beanProperty);
        if (jsonDeserializerFindDeserializerFromAnnotation != null) {
            return jsonDeserializerFindDeserializerFromAnnotation;
        }
        MapType mapType3 = (MapType) modifyTypeByAnnotation(deserializationConfig, basicBeanDescription2.getClassInfo(), mapType2, null);
        JavaType keyType = mapType3.getKeyType();
        JavaType contentType = mapType3.getContentType();
        JsonDeserializer<Object> jsonDeserializerFindValueDeserializer = (JsonDeserializer) contentType.getValueHandler();
        KeyDeserializer keyDeserializer = (KeyDeserializer) keyType.getValueHandler();
        KeyDeserializer keyDeserializerFindKeyDeserializer = keyDeserializer == null ? deserializerProvider.findKeyDeserializer(deserializationConfig, keyType, beanProperty) : keyDeserializer;
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        TypeDeserializer typeDeserializerFindTypeDeserializer = typeDeserializer == null ? findTypeDeserializer(deserializationConfig, contentType, beanProperty) : typeDeserializer;
        JsonDeserializer<?> jsonDeserializer_findCustomMapDeserializer = _findCustomMapDeserializer(mapType3, deserializationConfig, deserializerProvider, basicBeanDescription2, beanProperty, keyDeserializerFindKeyDeserializer, typeDeserializerFindTypeDeserializer, jsonDeserializerFindValueDeserializer);
        if (jsonDeserializer_findCustomMapDeserializer == null) {
            if (jsonDeserializerFindValueDeserializer == null) {
                jsonDeserializerFindValueDeserializer = deserializerProvider.findValueDeserializer(deserializationConfig, contentType, beanProperty);
            }
            Class<?> rawClass = mapType3.getRawClass();
            if (EnumMap.class.isAssignableFrom(rawClass)) {
                Class<?> rawClass2 = keyType.getRawClass();
                if (rawClass2 == null || !rawClass2.isEnum()) {
                    throw new IllegalArgumentException("Can not construct EnumMap; generic (key) type not available");
                }
                return new EnumMapDeserializer(constructEnumResolver(rawClass2, deserializationConfig), jsonDeserializerFindValueDeserializer);
            }
            if (mapType3.isInterface() || mapType3.isAbstract()) {
                Class<? extends Map> cls = _mapFallbacks.get(rawClass.getName());
                if (cls == null) {
                    throw new IllegalArgumentException("Can not find a deserializer for non-concrete Map type " + mapType3);
                }
                MapType mapType4 = (MapType) mapType3.forcedNarrowBy(cls);
                basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectForCreation(mapType4);
                mapType3 = mapType4;
            } else {
                basicBeanDescription = basicBeanDescription2;
            }
            boolean zIsEnabled = deserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS);
            Constructor<?> constructorFindDefaultConstructor = basicBeanDescription.findDefaultConstructor();
            if (constructorFindDefaultConstructor != null && zIsEnabled) {
                ClassUtil.checkAndFixAccess(constructorFindDefaultConstructor);
            }
            MapDeserializer mapDeserializer = new MapDeserializer(mapType3, constructorFindDefaultConstructor, keyDeserializerFindKeyDeserializer, jsonDeserializerFindValueDeserializer, typeDeserializerFindTypeDeserializer);
            mapDeserializer.setIgnorableProperties(deserializationConfig.getAnnotationIntrospector().findPropertiesToIgnore(basicBeanDescription.getClassInfo()));
            mapDeserializer.setCreators(findMapCreators(deserializationConfig, basicBeanDescription));
            return mapDeserializer;
        }
        return jsonDeserializer_findCustomMapDeserializer;
    }

    @Override // org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer<?> createMapLikeDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, MapLikeType mapLikeType, BeanProperty beanProperty) {
        MapLikeType mapLikeType2 = (MapLikeType) mapAbstractType(deserializationConfig, mapLikeType);
        BasicBeanDescription basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectForCreation(mapLikeType2);
        JsonDeserializer<?> jsonDeserializerFindDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
        if (jsonDeserializerFindDeserializerFromAnnotation != null) {
            return jsonDeserializerFindDeserializerFromAnnotation;
        }
        MapLikeType mapLikeType3 = (MapLikeType) modifyTypeByAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), mapLikeType2, null);
        JavaType keyType = mapLikeType3.getKeyType();
        JavaType contentType = mapLikeType3.getContentType();
        JsonDeserializer<?> jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        KeyDeserializer keyDeserializer = (KeyDeserializer) keyType.getValueHandler();
        KeyDeserializer keyDeserializerFindKeyDeserializer = keyDeserializer == null ? deserializerProvider.findKeyDeserializer(deserializationConfig, keyType, beanProperty) : keyDeserializer;
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        return _findCustomMapLikeDeserializer(mapLikeType3, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty, keyDeserializerFindKeyDeserializer, typeDeserializer == null ? findTypeDeserializer(deserializationConfig, contentType, beanProperty) : typeDeserializer, jsonDeserializer);
    }

    @Override // org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer<?> createEnumDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) {
        BasicBeanDescription basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectForCreation(javaType);
        JsonDeserializer<?> jsonDeserializerFindDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
        if (jsonDeserializerFindDeserializerFromAnnotation != null) {
            return jsonDeserializerFindDeserializerFromAnnotation;
        }
        Class<?> rawClass = javaType.getRawClass();
        JsonDeserializer<?> jsonDeserializer_findCustomEnumDeserializer = _findCustomEnumDeserializer(rawClass, deserializationConfig, basicBeanDescription, beanProperty);
        if (jsonDeserializer_findCustomEnumDeserializer != null) {
            return jsonDeserializer_findCustomEnumDeserializer;
        }
        for (AnnotatedMethod annotatedMethod : basicBeanDescription.getFactoryMethods()) {
            if (deserializationConfig.getAnnotationIntrospector().hasCreatorAnnotation(annotatedMethod)) {
                if (annotatedMethod.getParameterCount() == 1 && annotatedMethod.getRawType().isAssignableFrom(rawClass)) {
                    return EnumDeserializer.deserializerForCreator(deserializationConfig, rawClass, annotatedMethod);
                }
                throw new IllegalArgumentException("Unsuitable method (" + annotatedMethod + ") decorated with @JsonCreator (for Enum type " + rawClass.getName() + ")");
            }
        }
        return new EnumDeserializer(constructEnumResolver(rawClass, deserializationConfig));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer<?> createTreeDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) {
        Class<?> rawClass = javaType.getRawClass();
        JsonDeserializer<?> jsonDeserializer_findCustomTreeNodeDeserializer = _findCustomTreeNodeDeserializer(rawClass, deserializationConfig, beanProperty);
        return jsonDeserializer_findCustomTreeNodeDeserializer != null ? jsonDeserializer_findCustomTreeNodeDeserializer : JsonNodeDeserializer.getDeserializer(rawClass);
    }

    protected JsonDeserializer<Object> findStdBeanDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) {
        JavaType javaTypeUnknownType;
        JsonDeserializer<Object> jsonDeserializer = _simpleDeserializers.get(javaType);
        if (jsonDeserializer == null) {
            Class<?> rawClass = javaType.getRawClass();
            if (rawClass == Class.class) {
                return new StdDeserializer.ClassDeserializer();
            }
            if (AtomicReference.class.isAssignableFrom(rawClass)) {
                JavaType[] javaTypeArrFindTypeParameters = deserializationConfig.getTypeFactory().findTypeParameters(javaType, AtomicReference.class);
                if (javaTypeArrFindTypeParameters == null || javaTypeArrFindTypeParameters.length < 1) {
                    javaTypeUnknownType = TypeFactory.unknownType();
                } else {
                    javaTypeUnknownType = javaTypeArrFindTypeParameters[0];
                }
                return new StdDeserializer.AtomicReferenceDeserializer(javaTypeUnknownType, beanProperty);
            }
            JsonDeserializer<?> jsonDeserializerFindDeserializer = this.optionalHandlers.findDeserializer(javaType, deserializationConfig, deserializerProvider);
            if (jsonDeserializerFindDeserializer == null) {
                return null;
            }
            return jsonDeserializerFindDeserializer;
        }
        return jsonDeserializer;
    }

    @Override // org.codehaus.jackson.map.DeserializerFactory
    public TypeDeserializer findTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) {
        Collection<NamedType> collectionCollectAndResolveSubtypes;
        TypeResolverBuilder<?> typeResolverBuilder;
        AnnotatedClass classInfo = ((BasicBeanDescription) deserializationConfig.introspectClassAnnotations(javaType.getRawClass())).getClassInfo();
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder<?> typeResolverBuilderFindTypeResolver = annotationIntrospector.findTypeResolver(deserializationConfig, classInfo, javaType);
        if (typeResolverBuilderFindTypeResolver == null) {
            TypeResolverBuilder<?> defaultTyper = deserializationConfig.getDefaultTyper(javaType);
            if (defaultTyper == null) {
                return null;
            }
            typeResolverBuilder = defaultTyper;
            collectionCollectAndResolveSubtypes = null;
        } else {
            collectionCollectAndResolveSubtypes = deserializationConfig.getSubtypeResolver().collectAndResolveSubtypes(classInfo, deserializationConfig, annotationIntrospector);
            typeResolverBuilder = typeResolverBuilderFindTypeResolver;
        }
        return typeResolverBuilder.buildTypeDeserializer(deserializationConfig, javaType, collectionCollectAndResolveSubtypes, beanProperty);
    }

    public TypeDeserializer findPropertyTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, AnnotatedMember annotatedMember, BeanProperty beanProperty) {
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder<?> typeResolverBuilderFindPropertyTypeResolver = annotationIntrospector.findPropertyTypeResolver(deserializationConfig, annotatedMember, javaType);
        if (typeResolverBuilderFindPropertyTypeResolver == null) {
            return findTypeDeserializer(deserializationConfig, javaType, beanProperty);
        }
        return typeResolverBuilderFindPropertyTypeResolver.buildTypeDeserializer(deserializationConfig, javaType, deserializationConfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedMember, deserializationConfig, annotationIntrospector), beanProperty);
    }

    public TypeDeserializer findPropertyContentTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, AnnotatedMember annotatedMember, BeanProperty beanProperty) {
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder<?> typeResolverBuilderFindPropertyContentTypeResolver = annotationIntrospector.findPropertyContentTypeResolver(deserializationConfig, annotatedMember, javaType);
        JavaType contentType = javaType.getContentType();
        if (typeResolverBuilderFindPropertyContentTypeResolver == null) {
            return findTypeDeserializer(deserializationConfig, contentType, beanProperty);
        }
        return typeResolverBuilderFindPropertyContentTypeResolver.buildTypeDeserializer(deserializationConfig, contentType, deserializationConfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedMember, deserializationConfig, annotationIntrospector), beanProperty);
    }

    protected JsonDeserializer<Object> findDeserializerFromAnnotation(DeserializationConfig deserializationConfig, Annotated annotated, BeanProperty beanProperty) {
        Object objFindDeserializer = deserializationConfig.getAnnotationIntrospector().findDeserializer(annotated);
        if (objFindDeserializer != null) {
            return _constructDeserializer(deserializationConfig, annotated, beanProperty, objFindDeserializer);
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v2, types: [org.codehaus.jackson.map.JsonDeserializer<java.lang.Object>] */
    /* JADX WARN: Type inference failed for: r7v3, types: [org.codehaus.jackson.map.JsonDeserializer, org.codehaus.jackson.map.JsonDeserializer<java.lang.Object>] */
    JsonDeserializer<Object> _constructDeserializer(DeserializationConfig deserializationConfig, Annotated annotated, BeanProperty beanProperty, Object obj) {
        if (obj instanceof JsonDeserializer) {
            ?? r7 = (JsonDeserializer) obj;
            if (r7 instanceof ContextualDeserializer) {
                return ((ContextualDeserializer) r7).createContextual(deserializationConfig, beanProperty);
            }
            return r7;
        }
        if (!(obj instanceof Class)) {
            throw new IllegalStateException("AnnotationIntrospector returned deserializer definition of type " + obj.getClass().getName() + "; expected type JsonDeserializer or Class<JsonDeserializer> instead");
        }
        Class<? extends JsonDeserializer<?>> cls = (Class) obj;
        if (!JsonDeserializer.class.isAssignableFrom(cls)) {
            throw new IllegalStateException("AnnotationIntrospector returned Class " + cls.getName() + "; expected Class<JsonDeserializer>");
        }
        Object objDeserializerInstance = deserializationConfig.deserializerInstance(annotated, cls);
        if (objDeserializerInstance instanceof ContextualDeserializer) {
            objDeserializerInstance = ((ContextualDeserializer) objDeserializerInstance).createContextual(deserializationConfig, beanProperty);
        }
        return objDeserializerInstance;
    }

    protected <T extends JavaType> T modifyTypeByAnnotation(DeserializationConfig deserializationConfig, Annotated annotated, T t, String str) throws JsonMappingException {
        JavaType javaTypeNarrowBy;
        Class<? extends JsonDeserializer<?>> clsFindContentDeserializer;
        Class<? extends KeyDeserializer> clsFindKeyDeserializer;
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        Class<?> clsFindDeserializationType = annotationIntrospector.findDeserializationType(annotated, t, str);
        if (clsFindDeserializationType != null) {
            try {
                javaTypeNarrowBy = t.narrowBy(clsFindDeserializationType);
            } catch (IllegalArgumentException e) {
                throw new JsonMappingException("Failed to narrow type " + t + " with concrete-type annotation (value " + clsFindDeserializationType.getName() + "), method '" + annotated.getName() + "': " + e.getMessage(), null, e);
            }
        } else {
            javaTypeNarrowBy = t;
        }
        if (javaTypeNarrowBy.isContainerType()) {
            Class<?> clsFindDeserializationKeyType = annotationIntrospector.findDeserializationKeyType(annotated, javaTypeNarrowBy.getKeyType(), str);
            if (clsFindDeserializationKeyType != null) {
                if (!(javaTypeNarrowBy instanceof MapType)) {
                    throw new JsonMappingException("Illegal key-type annotation: type " + javaTypeNarrowBy + " is not a Map type");
                }
                try {
                    javaTypeNarrowBy = (T) javaTypeNarrowBy.narrowKey(clsFindDeserializationKeyType);
                } catch (IllegalArgumentException e2) {
                    throw new JsonMappingException("Failed to narrow key type " + javaTypeNarrowBy + " with key-type annotation (" + clsFindDeserializationKeyType.getName() + "): " + e2.getMessage(), null, e2);
                }
            }
            JavaType keyType = javaTypeNarrowBy.getKeyType();
            if (keyType != null && keyType.getValueHandler() == null && (clsFindKeyDeserializer = annotationIntrospector.findKeyDeserializer(annotated)) != null && clsFindKeyDeserializer != KeyDeserializer.None.class) {
                keyType.setValueHandler(deserializationConfig.keyDeserializerInstance(annotated, clsFindKeyDeserializer));
            }
            Class<?> clsFindDeserializationContentType = annotationIntrospector.findDeserializationContentType(annotated, javaTypeNarrowBy.getContentType(), str);
            if (clsFindDeserializationContentType != null) {
                try {
                    javaTypeNarrowBy = javaTypeNarrowBy.narrowContentsBy(clsFindDeserializationContentType);
                } catch (IllegalArgumentException e3) {
                    throw new JsonMappingException("Failed to narrow content type " + javaTypeNarrowBy + " with content-type annotation (" + clsFindDeserializationContentType.getName() + "): " + e3.getMessage(), null, e3);
                }
            }
            if (javaTypeNarrowBy.getContentType().getValueHandler() == null && (clsFindContentDeserializer = annotationIntrospector.findContentDeserializer(annotated)) != null && clsFindContentDeserializer != JsonDeserializer.None.class) {
                javaTypeNarrowBy.getContentType().setValueHandler(deserializationConfig.deserializerInstance(annotated, clsFindContentDeserializer));
            }
        }
        return (T) javaTypeNarrowBy;
    }

    protected JavaType resolveType(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, JavaType javaType, AnnotatedMember annotatedMember, BeanProperty beanProperty) {
        TypeDeserializer typeDeserializerFindTypeDeserializer;
        TypeDeserializer typeDeserializerFindPropertyContentTypeDeserializer;
        Class<? extends KeyDeserializer> clsFindKeyDeserializer;
        if (javaType.isContainerType()) {
            AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
            JavaType keyType = javaType.getKeyType();
            if (keyType != null && (clsFindKeyDeserializer = annotationIntrospector.findKeyDeserializer(annotatedMember)) != null && clsFindKeyDeserializer != KeyDeserializer.None.class) {
                keyType.setValueHandler(deserializationConfig.keyDeserializerInstance(annotatedMember, clsFindKeyDeserializer));
            }
            Class<? extends JsonDeserializer<?>> clsFindContentDeserializer = annotationIntrospector.findContentDeserializer(annotatedMember);
            if (clsFindContentDeserializer != null && clsFindContentDeserializer != JsonDeserializer.None.class) {
                javaType.getContentType().setValueHandler(deserializationConfig.deserializerInstance(annotatedMember, clsFindContentDeserializer));
            }
            if ((annotatedMember instanceof AnnotatedMember) && (typeDeserializerFindPropertyContentTypeDeserializer = findPropertyContentTypeDeserializer(deserializationConfig, javaType, annotatedMember, beanProperty)) != null) {
                javaType = javaType.withContentTypeHandler(typeDeserializerFindPropertyContentTypeDeserializer);
            }
        }
        if (annotatedMember instanceof AnnotatedMember) {
            typeDeserializerFindTypeDeserializer = findPropertyTypeDeserializer(deserializationConfig, javaType, annotatedMember, beanProperty);
        } else {
            typeDeserializerFindTypeDeserializer = findTypeDeserializer(deserializationConfig, javaType, null);
        }
        if (typeDeserializerFindTypeDeserializer != null) {
            return javaType.withTypeHandler(typeDeserializerFindTypeDeserializer);
        }
        return javaType;
    }

    protected EnumResolver<?> constructEnumResolver(Class<?> cls, DeserializationConfig deserializationConfig) {
        return deserializationConfig.isEnabled(DeserializationConfig.Feature.READ_ENUMS_USING_TO_STRING) ? EnumResolver.constructUnsafeUsingToString(cls) : EnumResolver.constructUnsafe(cls, deserializationConfig.getAnnotationIntrospector());
    }

    protected CreatorContainer findMapCreators(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription) {
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        CreatorContainer creatorContainer = new CreatorContainer(basicBeanDescription, deserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS));
        for (AnnotatedConstructor annotatedConstructor : basicBeanDescription.getConstructors()) {
            int parameterCount = annotatedConstructor.getParameterCount();
            if (parameterCount >= 1 && annotationIntrospector.hasCreatorAnnotation(annotatedConstructor)) {
                SettableBeanProperty[] settableBeanPropertyArr = new SettableBeanProperty[parameterCount];
                int i = 0;
                int i2 = 0;
                while (i2 < parameterCount) {
                    AnnotatedParameter parameter = annotatedConstructor.getParameter(i2);
                    String strFindPropertyNameForParam = parameter == null ? null : annotationIntrospector.findPropertyNameForParam(parameter);
                    if (strFindPropertyNameForParam == null || strFindPropertyNameForParam.length() == 0) {
                        throw new IllegalArgumentException("Parameter #" + i2 + " of constructor " + annotatedConstructor + " has no property name annotation: must have for @JsonCreator for a Map type");
                    }
                    settableBeanPropertyArr[i2] = constructCreatorProperty(deserializationConfig, basicBeanDescription, strFindPropertyNameForParam, i2, parameter);
                    i2++;
                    i++;
                }
                creatorContainer.addPropertyConstructor(annotatedConstructor, settableBeanPropertyArr);
            }
        }
        for (AnnotatedMethod annotatedMethod : basicBeanDescription.getFactoryMethods()) {
            int parameterCount2 = annotatedMethod.getParameterCount();
            if (parameterCount2 >= 1 && annotationIntrospector.hasCreatorAnnotation(annotatedMethod)) {
                SettableBeanProperty[] settableBeanPropertyArr2 = new SettableBeanProperty[parameterCount2];
                int i3 = 0;
                int i4 = 0;
                while (i4 < parameterCount2) {
                    AnnotatedParameter parameter2 = annotatedMethod.getParameter(i4);
                    String strFindPropertyNameForParam2 = parameter2 == null ? null : annotationIntrospector.findPropertyNameForParam(parameter2);
                    if (strFindPropertyNameForParam2 == null || strFindPropertyNameForParam2.length() == 0) {
                        throw new IllegalArgumentException("Parameter #" + i4 + " of factory method " + annotatedMethod + " has no property name annotation: must have for @JsonCreator for a Map type");
                    }
                    settableBeanPropertyArr2[i4] = constructCreatorProperty(deserializationConfig, basicBeanDescription, strFindPropertyNameForParam2, i4, parameter2);
                    i4++;
                    i3++;
                }
                creatorContainer.addPropertyFactory(annotatedMethod, settableBeanPropertyArr2);
            }
        }
        return creatorContainer;
    }

    protected SettableBeanProperty constructCreatorProperty(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, String str, int i, AnnotatedParameter annotatedParameter) throws JsonMappingException {
        JavaType javaTypeConstructType = deserializationConfig.getTypeFactory().constructType(annotatedParameter.getParameterType(), basicBeanDescription.bindingsForBeanType());
        BeanProperty.Std std = new BeanProperty.Std(str, javaTypeConstructType, basicBeanDescription.getClassAnnotations(), annotatedParameter);
        JavaType javaTypeResolveType = resolveType(deserializationConfig, basicBeanDescription, javaTypeConstructType, annotatedParameter, std);
        if (javaTypeResolveType != javaTypeConstructType) {
            std = std.withType(javaTypeResolveType);
        }
        JsonDeserializer<Object> jsonDeserializerFindDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, annotatedParameter, std);
        JavaType javaTypeModifyTypeByAnnotation = modifyTypeByAnnotation(deserializationConfig, annotatedParameter, javaTypeResolveType, str);
        SettableBeanProperty.CreatorProperty creatorProperty = new SettableBeanProperty.CreatorProperty(str, javaTypeModifyTypeByAnnotation, findTypeDeserializer(deserializationConfig, javaTypeModifyTypeByAnnotation, std), basicBeanDescription.getClassAnnotations(), annotatedParameter, i);
        if (jsonDeserializerFindDeserializerFromAnnotation != null) {
            creatorProperty.setValueDeserializer(jsonDeserializerFindDeserializerFromAnnotation);
        }
        return creatorProperty;
    }
}
