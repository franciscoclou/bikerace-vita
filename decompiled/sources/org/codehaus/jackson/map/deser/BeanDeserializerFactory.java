package org.codehaus.jackson.map.deser;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.AbstractTypeResolver;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializerFactory;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.Deserializers;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.KeyDeserializer;
import org.codehaus.jackson.map.KeyDeserializers;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import org.codehaus.jackson.map.introspect.AnnotatedField;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.AnnotatedParameter;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.introspect.VisibilityChecker;
import org.codehaus.jackson.map.type.ArrayType;
import org.codehaus.jackson.map.type.CollectionLikeType;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.MapLikeType;
import org.codehaus.jackson.map.type.MapType;
import org.codehaus.jackson.map.util.ArrayBuilders;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.type.JavaType;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class BeanDeserializerFactory extends BasicDeserializerFactory {
    private static final Class<?>[] INIT_CAUSE_PARAMS = {Throwable.class};
    public static final BeanDeserializerFactory instance = new BeanDeserializerFactory(null);
    protected final DeserializerFactory.Config _factoryConfig;

    public class ConfigImpl extends DeserializerFactory.Config {
        protected final AbstractTypeResolver[] _abstractTypeResolvers;
        protected final Deserializers[] _additionalDeserializers;
        protected final KeyDeserializers[] _additionalKeyDeserializers;
        protected final BeanDeserializerModifier[] _modifiers;
        protected static final KeyDeserializers[] NO_KEY_DESERIALIZERS = new KeyDeserializers[0];
        protected static final BeanDeserializerModifier[] NO_MODIFIERS = new BeanDeserializerModifier[0];
        protected static final AbstractTypeResolver[] NO_ABSTRACT_TYPE_RESOLVERS = new AbstractTypeResolver[0];

        public ConfigImpl() {
            this(null, null, null, null);
        }

        protected ConfigImpl(Deserializers[] deserializersArr, KeyDeserializers[] keyDeserializersArr, BeanDeserializerModifier[] beanDeserializerModifierArr, AbstractTypeResolver[] abstractTypeResolverArr) {
            this._additionalDeserializers = deserializersArr == null ? BeanDeserializerFactory.NO_DESERIALIZERS : deserializersArr;
            this._additionalKeyDeserializers = keyDeserializersArr == null ? NO_KEY_DESERIALIZERS : keyDeserializersArr;
            this._modifiers = beanDeserializerModifierArr == null ? NO_MODIFIERS : beanDeserializerModifierArr;
            this._abstractTypeResolvers = abstractTypeResolverArr == null ? NO_ABSTRACT_TYPE_RESOLVERS : abstractTypeResolverArr;
        }

        @Override // org.codehaus.jackson.map.DeserializerFactory.Config
        public DeserializerFactory.Config withAdditionalDeserializers(Deserializers deserializers) {
            if (deserializers == null) {
                throw new IllegalArgumentException("Can not pass null Deserializers");
            }
            return new ConfigImpl((Deserializers[]) ArrayBuilders.insertInListNoDup(this._additionalDeserializers, deserializers), this._additionalKeyDeserializers, this._modifiers, this._abstractTypeResolvers);
        }

        @Override // org.codehaus.jackson.map.DeserializerFactory.Config
        public DeserializerFactory.Config withAdditionalKeyDeserializers(KeyDeserializers keyDeserializers) {
            if (keyDeserializers == null) {
                throw new IllegalArgumentException("Can not pass null KeyDeserializers");
            }
            return new ConfigImpl(this._additionalDeserializers, (KeyDeserializers[]) ArrayBuilders.insertInListNoDup(this._additionalKeyDeserializers, keyDeserializers), this._modifiers, this._abstractTypeResolvers);
        }

        @Override // org.codehaus.jackson.map.DeserializerFactory.Config
        public DeserializerFactory.Config withDeserializerModifier(BeanDeserializerModifier beanDeserializerModifier) {
            if (beanDeserializerModifier == null) {
                throw new IllegalArgumentException("Can not pass null modifier");
            }
            return new ConfigImpl(this._additionalDeserializers, this._additionalKeyDeserializers, (BeanDeserializerModifier[]) ArrayBuilders.insertInListNoDup(this._modifiers, beanDeserializerModifier), this._abstractTypeResolvers);
        }

        @Override // org.codehaus.jackson.map.DeserializerFactory.Config
        public DeserializerFactory.Config withAbstractTypeResolver(AbstractTypeResolver abstractTypeResolver) {
            if (abstractTypeResolver == null) {
                throw new IllegalArgumentException("Can not pass null resolver");
            }
            return new ConfigImpl(this._additionalDeserializers, this._additionalKeyDeserializers, this._modifiers, (AbstractTypeResolver[]) ArrayBuilders.insertInListNoDup(this._abstractTypeResolvers, abstractTypeResolver));
        }

        @Override // org.codehaus.jackson.map.DeserializerFactory.Config
        public boolean hasDeserializers() {
            return this._additionalDeserializers.length > 0;
        }

        @Override // org.codehaus.jackson.map.DeserializerFactory.Config
        public boolean hasKeyDeserializers() {
            return this._additionalKeyDeserializers.length > 0;
        }

        @Override // org.codehaus.jackson.map.DeserializerFactory.Config
        public boolean hasDeserializerModifiers() {
            return this._modifiers.length > 0;
        }

        @Override // org.codehaus.jackson.map.DeserializerFactory.Config
        public boolean hasAbstractTypeResolvers() {
            return this._abstractTypeResolvers.length > 0;
        }

        @Override // org.codehaus.jackson.map.DeserializerFactory.Config
        public Iterable<Deserializers> deserializers() {
            return ArrayBuilders.arrayAsIterable(this._additionalDeserializers);
        }

        @Override // org.codehaus.jackson.map.DeserializerFactory.Config
        public Iterable<KeyDeserializers> keyDeserializers() {
            return ArrayBuilders.arrayAsIterable(this._additionalKeyDeserializers);
        }

        @Override // org.codehaus.jackson.map.DeserializerFactory.Config
        public Iterable<BeanDeserializerModifier> deserializerModifiers() {
            return ArrayBuilders.arrayAsIterable(this._modifiers);
        }

        @Override // org.codehaus.jackson.map.DeserializerFactory.Config
        public Iterable<AbstractTypeResolver> abstractTypeResolvers() {
            return ArrayBuilders.arrayAsIterable(this._abstractTypeResolvers);
        }
    }

    @Deprecated
    public BeanDeserializerFactory() {
        this(null);
    }

    public BeanDeserializerFactory(DeserializerFactory.Config config) {
        this._factoryConfig = config == null ? new ConfigImpl() : config;
    }

    @Override // org.codehaus.jackson.map.DeserializerFactory
    public final DeserializerFactory.Config getConfig() {
        return this._factoryConfig;
    }

    @Override // org.codehaus.jackson.map.deser.BasicDeserializerFactory, org.codehaus.jackson.map.DeserializerFactory
    public DeserializerFactory withConfig(DeserializerFactory.Config config) {
        if (this._factoryConfig != config) {
            if (getClass() != BeanDeserializerFactory.class) {
                throw new IllegalStateException("Subtype of BeanDeserializerFactory (" + getClass().getName() + ") has not properly overridden method 'withAdditionalDeserializers': can not instantiate subtype with additional deserializer definitions");
            }
            return new BeanDeserializerFactory(config);
        }
        return this;
    }

    @Override // org.codehaus.jackson.map.DeserializerFactory
    public KeyDeserializer createKeyDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) {
        if (this._factoryConfig.hasKeyDeserializers()) {
            BasicBeanDescription basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectClassAnnotations(javaType.getRawClass());
            Iterator<KeyDeserializers> it = this._factoryConfig.keyDeserializers().iterator();
            while (it.hasNext()) {
                KeyDeserializer keyDeserializerFindKeyDeserializer = it.next().findKeyDeserializer(javaType, deserializationConfig, basicBeanDescription, beanProperty);
                if (keyDeserializerFindKeyDeserializer != null) {
                    return keyDeserializerFindKeyDeserializer;
                }
            }
        }
        return null;
    }

    @Override // org.codehaus.jackson.map.deser.BasicDeserializerFactory
    protected JsonDeserializer<?> _findCustomArrayDeserializer(ArrayType arrayType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        Iterator<Deserializers> it = this._factoryConfig.deserializers().iterator();
        while (it.hasNext()) {
            JsonDeserializer<?> jsonDeserializerFindArrayDeserializer = it.next().findArrayDeserializer(arrayType, deserializationConfig, deserializerProvider, beanProperty, typeDeserializer, jsonDeserializer);
            if (jsonDeserializerFindArrayDeserializer != null) {
                return jsonDeserializerFindArrayDeserializer;
            }
        }
        return null;
    }

    @Override // org.codehaus.jackson.map.deser.BasicDeserializerFactory
    protected JsonDeserializer<?> _findCustomCollectionDeserializer(CollectionType collectionType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        Iterator<Deserializers> it = this._factoryConfig.deserializers().iterator();
        while (it.hasNext()) {
            JsonDeserializer<?> jsonDeserializerFindCollectionDeserializer = it.next().findCollectionDeserializer(collectionType, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty, typeDeserializer, jsonDeserializer);
            if (jsonDeserializerFindCollectionDeserializer != null) {
                return jsonDeserializerFindCollectionDeserializer;
            }
        }
        return null;
    }

    @Override // org.codehaus.jackson.map.deser.BasicDeserializerFactory
    protected JsonDeserializer<?> _findCustomCollectionLikeDeserializer(CollectionLikeType collectionLikeType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        Iterator<Deserializers> it = this._factoryConfig.deserializers().iterator();
        while (it.hasNext()) {
            JsonDeserializer<?> jsonDeserializerFindCollectionLikeDeserializer = it.next().findCollectionLikeDeserializer(collectionLikeType, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty, typeDeserializer, jsonDeserializer);
            if (jsonDeserializerFindCollectionLikeDeserializer != null) {
                return jsonDeserializerFindCollectionLikeDeserializer;
            }
        }
        return null;
    }

    @Override // org.codehaus.jackson.map.deser.BasicDeserializerFactory
    protected JsonDeserializer<?> _findCustomEnumDeserializer(Class<?> cls, DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty) {
        Iterator<Deserializers> it = this._factoryConfig.deserializers().iterator();
        while (it.hasNext()) {
            JsonDeserializer<?> jsonDeserializerFindEnumDeserializer = it.next().findEnumDeserializer(cls, deserializationConfig, basicBeanDescription, beanProperty);
            if (jsonDeserializerFindEnumDeserializer != null) {
                return jsonDeserializerFindEnumDeserializer;
            }
        }
        return null;
    }

    @Override // org.codehaus.jackson.map.deser.BasicDeserializerFactory
    protected JsonDeserializer<?> _findCustomMapDeserializer(MapType mapType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        Iterator<Deserializers> it = this._factoryConfig.deserializers().iterator();
        while (it.hasNext()) {
            JsonDeserializer<?> jsonDeserializerFindMapDeserializer = it.next().findMapDeserializer(mapType, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty, keyDeserializer, typeDeserializer, jsonDeserializer);
            if (jsonDeserializerFindMapDeserializer != null) {
                return jsonDeserializerFindMapDeserializer;
            }
        }
        return null;
    }

    @Override // org.codehaus.jackson.map.deser.BasicDeserializerFactory
    protected JsonDeserializer<?> _findCustomMapLikeDeserializer(MapLikeType mapLikeType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        Iterator<Deserializers> it = this._factoryConfig.deserializers().iterator();
        while (it.hasNext()) {
            JsonDeserializer<?> jsonDeserializerFindMapLikeDeserializer = it.next().findMapLikeDeserializer(mapLikeType, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty, keyDeserializer, typeDeserializer, jsonDeserializer);
            if (jsonDeserializerFindMapLikeDeserializer != null) {
                return jsonDeserializerFindMapLikeDeserializer;
            }
        }
        return null;
    }

    @Override // org.codehaus.jackson.map.deser.BasicDeserializerFactory
    protected JsonDeserializer<?> _findCustomTreeNodeDeserializer(Class<? extends JsonNode> cls, DeserializationConfig deserializationConfig, BeanProperty beanProperty) {
        Iterator<Deserializers> it = this._factoryConfig.deserializers().iterator();
        while (it.hasNext()) {
            JsonDeserializer<?> jsonDeserializerFindTreeNodeDeserializer = it.next().findTreeNodeDeserializer(cls, deserializationConfig, beanProperty);
            if (jsonDeserializerFindTreeNodeDeserializer != null) {
                return jsonDeserializerFindTreeNodeDeserializer;
            }
        }
        return null;
    }

    protected JsonDeserializer<Object> _findCustomBeanDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty) {
        Iterator<Deserializers> it = this._factoryConfig.deserializers().iterator();
        while (it.hasNext()) {
            JsonDeserializer<?> jsonDeserializerFindBeanDeserializer = it.next().findBeanDeserializer(javaType, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty);
            if (jsonDeserializerFindBeanDeserializer != null) {
                return jsonDeserializerFindBeanDeserializer;
            }
        }
        return null;
    }

    @Override // org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer<Object> createBeanDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) {
        BasicBeanDescription basicBeanDescription;
        JavaType javaTypeMaterializeAbstractType;
        if (javaType.isAbstract()) {
            javaType = mapAbstractType(deserializationConfig, javaType);
        }
        BasicBeanDescription basicBeanDescription2 = (BasicBeanDescription) deserializationConfig.introspect(javaType);
        JsonDeserializer<Object> jsonDeserializerFindDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, basicBeanDescription2.getClassInfo(), beanProperty);
        if (jsonDeserializerFindDeserializerFromAnnotation != null) {
            return jsonDeserializerFindDeserializerFromAnnotation;
        }
        JavaType javaTypeModifyTypeByAnnotation = modifyTypeByAnnotation(deserializationConfig, basicBeanDescription2.getClassInfo(), javaType, null);
        if (javaTypeModifyTypeByAnnotation.getRawClass() != javaType.getRawClass()) {
            basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspect(javaTypeModifyTypeByAnnotation);
        } else {
            basicBeanDescription = basicBeanDescription2;
            javaTypeModifyTypeByAnnotation = javaType;
        }
        JsonDeserializer<Object> jsonDeserializer_findCustomBeanDeserializer = _findCustomBeanDeserializer(javaTypeModifyTypeByAnnotation, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty);
        if (jsonDeserializer_findCustomBeanDeserializer == null) {
            if (javaTypeModifyTypeByAnnotation.isThrowable()) {
                return buildThrowableDeserializer(deserializationConfig, javaTypeModifyTypeByAnnotation, basicBeanDescription, beanProperty);
            }
            if (javaTypeModifyTypeByAnnotation.isAbstract() && (javaTypeMaterializeAbstractType = materializeAbstractType(deserializationConfig, basicBeanDescription)) != null) {
                return buildBeanDeserializer(deserializationConfig, javaTypeMaterializeAbstractType, (BasicBeanDescription) deserializationConfig.introspect(javaTypeMaterializeAbstractType), beanProperty);
            }
            JsonDeserializer<Object> jsonDeserializerFindStdBeanDeserializer = findStdBeanDeserializer(deserializationConfig, deserializerProvider, javaTypeModifyTypeByAnnotation, beanProperty);
            if (jsonDeserializerFindStdBeanDeserializer == null) {
                if (isPotentialBeanType(javaTypeModifyTypeByAnnotation.getRawClass())) {
                    return buildBeanDeserializer(deserializationConfig, javaTypeModifyTypeByAnnotation, basicBeanDescription, beanProperty);
                }
                return null;
            }
            return jsonDeserializerFindStdBeanDeserializer;
        }
        return jsonDeserializer_findCustomBeanDeserializer;
    }

    @Override // org.codehaus.jackson.map.deser.BasicDeserializerFactory
    protected JavaType mapAbstractType(DeserializationConfig deserializationConfig, JavaType javaType) {
        while (true) {
            JavaType javaType_mapAbstractType2 = _mapAbstractType2(deserializationConfig, javaType);
            if (javaType_mapAbstractType2 == null) {
                return javaType;
            }
            Class<?> rawClass = javaType.getRawClass();
            Class<?> rawClass2 = javaType_mapAbstractType2.getRawClass();
            if (rawClass == rawClass2 || !rawClass.isAssignableFrom(rawClass2)) {
                throw new IllegalArgumentException("Invalid abstract type resolution from " + javaType + " to " + javaType_mapAbstractType2 + ": latter is not a subtype of former");
            }
            javaType = javaType_mapAbstractType2;
        }
    }

    protected JavaType _mapAbstractType2(DeserializationConfig deserializationConfig, JavaType javaType) {
        JavaType javaTypeFindTypeMapping;
        Class<?> rawClass = javaType.getRawClass();
        if (this._factoryConfig.hasAbstractTypeResolvers()) {
            Iterator<AbstractTypeResolver> it = this._factoryConfig.abstractTypeResolvers().iterator();
            while (it.hasNext()) {
                JavaType javaTypeFindTypeMapping2 = it.next().findTypeMapping(deserializationConfig, javaType);
                if (javaTypeFindTypeMapping2 != null && javaTypeFindTypeMapping2.getRawClass() != rawClass) {
                    return javaTypeFindTypeMapping2;
                }
            }
        }
        AbstractTypeResolver abstractTypeResolver = deserializationConfig.getAbstractTypeResolver();
        if (abstractTypeResolver == null || (javaTypeFindTypeMapping = abstractTypeResolver.findTypeMapping(deserializationConfig, javaType)) == null || javaTypeFindTypeMapping.getRawClass() == rawClass) {
            return null;
        }
        return javaTypeFindTypeMapping;
    }

    protected JavaType materializeAbstractType(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription) {
        JavaType javaTypeResolveAbstractType;
        AbstractTypeResolver abstractTypeResolver = deserializationConfig.getAbstractTypeResolver();
        if (abstractTypeResolver == null && !this._factoryConfig.hasAbstractTypeResolvers()) {
            return null;
        }
        JavaType type = basicBeanDescription.getType();
        if (deserializationConfig.getAnnotationIntrospector().findTypeResolver(deserializationConfig, basicBeanDescription.getClassInfo(), type) != null) {
            return null;
        }
        if (abstractTypeResolver == null || (javaTypeResolveAbstractType = abstractTypeResolver.resolveAbstractType(deserializationConfig, type)) == null) {
            Iterator<AbstractTypeResolver> it = this._factoryConfig.abstractTypeResolvers().iterator();
            while (it.hasNext()) {
                JavaType javaTypeResolveAbstractType2 = it.next().resolveAbstractType(deserializationConfig, type);
                if (javaTypeResolveAbstractType2 != null) {
                    return javaTypeResolveAbstractType2;
                }
            }
            return null;
        }
        return javaTypeResolveAbstractType;
    }

    public JsonDeserializer<Object> buildBeanDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty) {
        BeanDeserializerBuilder beanDeserializerBuilder;
        if (javaType.isAbstract()) {
            return new AbstractDeserializer(javaType);
        }
        BeanDeserializerBuilder beanDeserializerBuilderConstructBeanDeserializerBuilder = constructBeanDeserializerBuilder(basicBeanDescription);
        beanDeserializerBuilderConstructBeanDeserializerBuilder.setCreators(findDeserializerCreators(deserializationConfig, basicBeanDescription));
        addBeanProps(deserializationConfig, basicBeanDescription, beanDeserializerBuilderConstructBeanDeserializerBuilder);
        addReferenceProperties(deserializationConfig, basicBeanDescription, beanDeserializerBuilderConstructBeanDeserializerBuilder);
        if (this._factoryConfig.hasDeserializerModifiers()) {
            Iterator<BeanDeserializerModifier> it = this._factoryConfig.deserializerModifiers().iterator();
            while (true) {
                beanDeserializerBuilder = beanDeserializerBuilderConstructBeanDeserializerBuilder;
                if (!it.hasNext()) {
                    break;
                }
                beanDeserializerBuilderConstructBeanDeserializerBuilder = it.next().updateBuilder(deserializationConfig, basicBeanDescription, beanDeserializerBuilder);
            }
        } else {
            beanDeserializerBuilder = beanDeserializerBuilderConstructBeanDeserializerBuilder;
        }
        JsonDeserializer<?> jsonDeserializerBuild = beanDeserializerBuilder.build(beanProperty);
        if (!this._factoryConfig.hasDeserializerModifiers()) {
            return jsonDeserializerBuild;
        }
        Iterator<BeanDeserializerModifier> it2 = this._factoryConfig.deserializerModifiers().iterator();
        while (true) {
            JsonDeserializer<?> jsonDeserializer = jsonDeserializerBuild;
            if (it2.hasNext()) {
                jsonDeserializerBuild = it2.next().modifyDeserializer(deserializationConfig, basicBeanDescription, jsonDeserializer);
            } else {
                return jsonDeserializer;
            }
        }
    }

    public JsonDeserializer<Object> buildThrowableDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty) {
        BeanDeserializerBuilder beanDeserializerBuilder;
        SettableBeanProperty settableBeanPropertyConstructSettableProperty;
        BeanDeserializerBuilder beanDeserializerBuilderConstructBeanDeserializerBuilder = constructBeanDeserializerBuilder(basicBeanDescription);
        beanDeserializerBuilderConstructBeanDeserializerBuilder.setCreators(findDeserializerCreators(deserializationConfig, basicBeanDescription));
        addBeanProps(deserializationConfig, basicBeanDescription, beanDeserializerBuilderConstructBeanDeserializerBuilder);
        AnnotatedMethod annotatedMethodFindMethod = basicBeanDescription.findMethod("initCause", INIT_CAUSE_PARAMS);
        if (annotatedMethodFindMethod != null && (settableBeanPropertyConstructSettableProperty = constructSettableProperty(deserializationConfig, basicBeanDescription, "cause", annotatedMethodFindMethod)) != null) {
            beanDeserializerBuilderConstructBeanDeserializerBuilder.addProperty(settableBeanPropertyConstructSettableProperty);
        }
        beanDeserializerBuilderConstructBeanDeserializerBuilder.addIgnorable("localizedMessage");
        beanDeserializerBuilderConstructBeanDeserializerBuilder.addIgnorable("message");
        if (this._factoryConfig.hasDeserializerModifiers()) {
            Iterator<BeanDeserializerModifier> it = this._factoryConfig.deserializerModifiers().iterator();
            while (true) {
                beanDeserializerBuilder = beanDeserializerBuilderConstructBeanDeserializerBuilder;
                if (!it.hasNext()) {
                    break;
                }
                beanDeserializerBuilderConstructBeanDeserializerBuilder = it.next().updateBuilder(deserializationConfig, basicBeanDescription, beanDeserializerBuilder);
            }
        } else {
            beanDeserializerBuilder = beanDeserializerBuilderConstructBeanDeserializerBuilder;
        }
        JsonDeserializer<?> jsonDeserializerBuild = beanDeserializerBuilder.build(beanProperty);
        if (jsonDeserializerBuild instanceof BeanDeserializer) {
            jsonDeserializerBuild = new ThrowableDeserializer((BeanDeserializer) jsonDeserializerBuild);
        }
        if (!this._factoryConfig.hasDeserializerModifiers()) {
            return jsonDeserializerBuild;
        }
        Iterator<BeanDeserializerModifier> it2 = this._factoryConfig.deserializerModifiers().iterator();
        while (true) {
            JsonDeserializer<?> jsonDeserializer = jsonDeserializerBuild;
            if (it2.hasNext()) {
                jsonDeserializerBuild = it2.next().modifyDeserializer(deserializationConfig, basicBeanDescription, jsonDeserializer);
            } else {
                return jsonDeserializer;
            }
        }
    }

    protected BeanDeserializerBuilder constructBeanDeserializerBuilder(BasicBeanDescription basicBeanDescription) {
        return new BeanDeserializerBuilder(basicBeanDescription);
    }

    protected CreatorContainer findDeserializerCreators(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription) {
        Constructor<?> constructorFindDefaultConstructor;
        boolean zIsEnabled = deserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS);
        CreatorContainer creatorContainer = new CreatorContainer(basicBeanDescription, zIsEnabled);
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        if (basicBeanDescription.getType().isConcrete() && (constructorFindDefaultConstructor = basicBeanDescription.findDefaultConstructor()) != null) {
            if (zIsEnabled) {
                ClassUtil.checkAndFixAccess(constructorFindDefaultConstructor);
            }
            creatorContainer.setDefaultConstructor(constructorFindDefaultConstructor);
        }
        VisibilityChecker<?> defaultVisibilityChecker = deserializationConfig.getDefaultVisibilityChecker();
        if (!deserializationConfig.isEnabled(DeserializationConfig.Feature.AUTO_DETECT_CREATORS)) {
            defaultVisibilityChecker = defaultVisibilityChecker.withCreatorVisibility(JsonAutoDetect.Visibility.NONE);
        }
        VisibilityChecker<?> visibilityCheckerFindAutoDetectVisibility = deserializationConfig.getAnnotationIntrospector().findAutoDetectVisibility(basicBeanDescription.getClassInfo(), defaultVisibilityChecker);
        _addDeserializerConstructors(deserializationConfig, basicBeanDescription, visibilityCheckerFindAutoDetectVisibility, annotationIntrospector, creatorContainer);
        _addDeserializerFactoryMethods(deserializationConfig, basicBeanDescription, visibilityCheckerFindAutoDetectVisibility, annotationIntrospector, creatorContainer);
        return creatorContainer;
    }

    protected void _addDeserializerConstructors(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, VisibilityChecker<?> visibilityChecker, AnnotationIntrospector annotationIntrospector, CreatorContainer creatorContainer) {
        for (AnnotatedConstructor annotatedConstructor : basicBeanDescription.getConstructors()) {
            int parameterCount = annotatedConstructor.getParameterCount();
            if (parameterCount >= 1) {
                boolean zHasCreatorAnnotation = annotationIntrospector.hasCreatorAnnotation(annotatedConstructor);
                boolean zIsCreatorVisible = visibilityChecker.isCreatorVisible(annotatedConstructor);
                if (parameterCount == 1) {
                    AnnotatedParameter parameter = annotatedConstructor.getParameter(0);
                    String strFindPropertyNameForParam = annotationIntrospector.findPropertyNameForParam(parameter);
                    if (strFindPropertyNameForParam == null || strFindPropertyNameForParam.length() == 0) {
                        Class<?> parameterClass = annotatedConstructor.getParameterClass(0);
                        if (parameterClass == String.class) {
                            if (zHasCreatorAnnotation || zIsCreatorVisible) {
                                creatorContainer.addStringConstructor(annotatedConstructor);
                            }
                        } else if (parameterClass == Integer.TYPE || parameterClass == Integer.class) {
                            if (zHasCreatorAnnotation || zIsCreatorVisible) {
                                creatorContainer.addIntConstructor(annotatedConstructor);
                            }
                        } else if (parameterClass == Long.TYPE || parameterClass == Long.class) {
                            if (zHasCreatorAnnotation || zIsCreatorVisible) {
                                creatorContainer.addLongConstructor(annotatedConstructor);
                            }
                        } else if (zHasCreatorAnnotation) {
                            creatorContainer.addDelegatingConstructor(annotatedConstructor);
                        }
                    } else {
                        creatorContainer.addPropertyConstructor(annotatedConstructor, new SettableBeanProperty[]{constructCreatorProperty(deserializationConfig, basicBeanDescription, strFindPropertyNameForParam, 0, parameter)});
                    }
                } else if (zHasCreatorAnnotation || zIsCreatorVisible) {
                    SettableBeanProperty[] settableBeanPropertyArr = new SettableBeanProperty[parameterCount];
                    int i = 0;
                    boolean z = false;
                    boolean z2 = false;
                    while (i < parameterCount) {
                        AnnotatedParameter parameter2 = annotatedConstructor.getParameter(i);
                        String strFindPropertyNameForParam2 = parameter2 == null ? null : annotationIntrospector.findPropertyNameForParam(parameter2);
                        boolean z3 = z2 | (strFindPropertyNameForParam2 == null || strFindPropertyNameForParam2.length() == 0);
                        boolean z4 = z | (!z3);
                        if (z3 && (z4 || zHasCreatorAnnotation)) {
                            throw new IllegalArgumentException("Argument #" + i + " of constructor " + annotatedConstructor + " has no property name annotation; must have name when multiple-paramater constructor annotated as Creator");
                        }
                        settableBeanPropertyArr[i] = constructCreatorProperty(deserializationConfig, basicBeanDescription, strFindPropertyNameForParam2, i, parameter2);
                        i++;
                        z2 = z3;
                        z = z4;
                    }
                    if (z) {
                        creatorContainer.addPropertyConstructor(annotatedConstructor, settableBeanPropertyArr);
                    }
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:42:0x0086  */
    protected void _addDeserializerFactoryMethods(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, VisibilityChecker<?> visibilityChecker, AnnotationIntrospector annotationIntrospector, CreatorContainer creatorContainer) {
        int i;
        String strFindPropertyNameForParam;
        for (AnnotatedMethod annotatedMethod : basicBeanDescription.getFactoryMethods()) {
            int parameterCount = annotatedMethod.getParameterCount();
            if (parameterCount >= 1) {
                boolean zHasCreatorAnnotation = annotationIntrospector.hasCreatorAnnotation(annotatedMethod);
                if (parameterCount == 1) {
                    String strFindPropertyNameForParam2 = annotationIntrospector.findPropertyNameForParam(annotatedMethod.getParameter(0));
                    if (strFindPropertyNameForParam2 == null || strFindPropertyNameForParam2.length() == 0) {
                        Class<?> parameterClass = annotatedMethod.getParameterClass(0);
                        if (parameterClass == String.class) {
                            if (zHasCreatorAnnotation || visibilityChecker.isCreatorVisible(annotatedMethod)) {
                                creatorContainer.addStringFactory(annotatedMethod);
                            }
                        } else if (parameterClass == Integer.TYPE || parameterClass == Integer.class) {
                            if (zHasCreatorAnnotation || visibilityChecker.isCreatorVisible(annotatedMethod)) {
                                creatorContainer.addIntFactory(annotatedMethod);
                            }
                        } else if (parameterClass == Long.TYPE || parameterClass == Long.class) {
                            if (zHasCreatorAnnotation || visibilityChecker.isCreatorVisible(annotatedMethod)) {
                                creatorContainer.addLongFactory(annotatedMethod);
                            }
                        } else if (annotationIntrospector.hasCreatorAnnotation(annotatedMethod)) {
                            creatorContainer.addDelegatingFactory(annotatedMethod);
                        }
                    } else {
                        SettableBeanProperty[] settableBeanPropertyArr = new SettableBeanProperty[parameterCount];
                        for (i = 0; i < parameterCount; i++) {
                            AnnotatedParameter parameter = annotatedMethod.getParameter(i);
                            strFindPropertyNameForParam = annotationIntrospector.findPropertyNameForParam(parameter);
                            if (strFindPropertyNameForParam != null || strFindPropertyNameForParam.length() == 0) {
                                throw new IllegalArgumentException("Argument #" + i + " of factory method " + annotatedMethod + " has no property name annotation; must have when multiple-paramater static method annotated as Creator");
                            }
                            settableBeanPropertyArr[i] = constructCreatorProperty(deserializationConfig, basicBeanDescription, strFindPropertyNameForParam, i, parameter);
                        }
                        creatorContainer.addPropertyFactory(annotatedMethod, settableBeanPropertyArr);
                    }
                } else if (annotationIntrospector.hasCreatorAnnotation(annotatedMethod)) {
                    SettableBeanProperty[] settableBeanPropertyArr2 = new SettableBeanProperty[parameterCount];
                    while (i < parameterCount) {
                        AnnotatedParameter parameter2 = annotatedMethod.getParameter(i);
                        strFindPropertyNameForParam = annotationIntrospector.findPropertyNameForParam(parameter2);
                        if (strFindPropertyNameForParam != null) {
                        }
                        throw new IllegalArgumentException("Argument #" + i + " of factory method " + annotatedMethod + " has no property name annotation; must have when multiple-paramater static method annotated as Creator");
                    }
                    creatorContainer.addPropertyFactory(annotatedMethod, settableBeanPropertyArr2);
                } else {
                    continue;
                }
            }
        }
    }

    protected void addBeanProps(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, BeanDeserializerBuilder beanDeserializerBuilder) {
        VisibilityChecker<?> defaultVisibilityChecker = deserializationConfig.getDefaultVisibilityChecker();
        if (!deserializationConfig.isEnabled(DeserializationConfig.Feature.AUTO_DETECT_SETTERS)) {
            defaultVisibilityChecker = defaultVisibilityChecker.withSetterVisibility(JsonAutoDetect.Visibility.NONE);
        }
        if (!deserializationConfig.isEnabled(DeserializationConfig.Feature.AUTO_DETECT_FIELDS)) {
            defaultVisibilityChecker = defaultVisibilityChecker.withFieldVisibility(JsonAutoDetect.Visibility.NONE);
        }
        VisibilityChecker<?> visibilityCheckerFindAutoDetectVisibility = deserializationConfig.getAnnotationIntrospector().findAutoDetectVisibility(basicBeanDescription.getClassInfo(), defaultVisibilityChecker);
        LinkedHashMap<String, AnnotatedMethod> linkedHashMapFindSetters = basicBeanDescription.findSetters(visibilityCheckerFindAutoDetectVisibility);
        AnnotatedMethod annotatedMethodFindAnySetter = basicBeanDescription.findAnySetter();
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        Boolean boolFindIgnoreUnknownProperties = annotationIntrospector.findIgnoreUnknownProperties(basicBeanDescription.getClassInfo());
        if (boolFindIgnoreUnknownProperties != null) {
            beanDeserializerBuilder.setIgnoreUnknownProperties(boolFindIgnoreUnknownProperties.booleanValue());
        }
        HashSet hashSetArrayToSet = ArrayBuilders.arrayToSet(annotationIntrospector.findPropertiesToIgnore(basicBeanDescription.getClassInfo()));
        Iterator it = hashSetArrayToSet.iterator();
        while (it.hasNext()) {
            beanDeserializerBuilder.addIgnorable((String) it.next());
        }
        AnnotatedClass classInfo = basicBeanDescription.getClassInfo();
        Iterator<AnnotatedMethod> it2 = classInfo.ignoredMemberMethods().iterator();
        while (it2.hasNext()) {
            String strOkNameForSetter = basicBeanDescription.okNameForSetter(it2.next());
            if (strOkNameForSetter != null) {
                beanDeserializerBuilder.addIgnorable(strOkNameForSetter);
            }
        }
        Iterator<AnnotatedField> it3 = classInfo.ignoredFields().iterator();
        while (it3.hasNext()) {
            beanDeserializerBuilder.addIgnorable(it3.next().getName());
        }
        HashMap map = new HashMap();
        for (Map.Entry<String, AnnotatedMethod> entry : linkedHashMapFindSetters.entrySet()) {
            String key = entry.getKey();
            if (!hashSetArrayToSet.contains(key)) {
                AnnotatedMethod value = entry.getValue();
                if (isIgnorableType(deserializationConfig, basicBeanDescription, value.getParameterClass(0), map)) {
                    beanDeserializerBuilder.addIgnorable(key);
                } else {
                    SettableBeanProperty settableBeanPropertyConstructSettableProperty = constructSettableProperty(deserializationConfig, basicBeanDescription, key, value);
                    if (settableBeanPropertyConstructSettableProperty != null) {
                        beanDeserializerBuilder.addProperty(settableBeanPropertyConstructSettableProperty);
                    }
                }
            }
        }
        if (annotatedMethodFindAnySetter != null) {
            beanDeserializerBuilder.setAnySetter(constructAnySetter(deserializationConfig, basicBeanDescription, annotatedMethodFindAnySetter));
        }
        HashSet hashSet = new HashSet(linkedHashMapFindSetters.keySet());
        for (Map.Entry<String, AnnotatedField> entry2 : basicBeanDescription.findDeserializableFields(visibilityCheckerFindAutoDetectVisibility, hashSet).entrySet()) {
            String key2 = entry2.getKey();
            if (!hashSetArrayToSet.contains(key2) && !beanDeserializerBuilder.hasProperty(key2)) {
                AnnotatedField value2 = entry2.getValue();
                if (isIgnorableType(deserializationConfig, basicBeanDescription, value2.getRawType(), map)) {
                    beanDeserializerBuilder.addIgnorable(key2);
                } else {
                    SettableBeanProperty settableBeanPropertyConstructSettableProperty2 = constructSettableProperty(deserializationConfig, basicBeanDescription, key2, value2);
                    if (settableBeanPropertyConstructSettableProperty2 != null) {
                        beanDeserializerBuilder.addProperty(settableBeanPropertyConstructSettableProperty2);
                        hashSet.add(key2);
                    }
                }
            }
        }
        if (deserializationConfig.isEnabled(DeserializationConfig.Feature.USE_GETTERS_AS_SETTERS)) {
            for (Map.Entry<String, AnnotatedMethod> entry3 : basicBeanDescription.findGetters(visibilityCheckerFindAutoDetectVisibility, hashSet).entrySet()) {
                AnnotatedMethod value3 = entry3.getValue();
                Class<?> rawType = value3.getRawType();
                if (Collection.class.isAssignableFrom(rawType) || Map.class.isAssignableFrom(rawType)) {
                    String key3 = entry3.getKey();
                    if (!hashSetArrayToSet.contains(key3) && !beanDeserializerBuilder.hasProperty(key3)) {
                        beanDeserializerBuilder.addProperty(constructSetterlessProperty(deserializationConfig, basicBeanDescription, key3, value3));
                        hashSet.add(key3);
                    }
                }
            }
        }
    }

    protected void addReferenceProperties(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, BeanDeserializerBuilder beanDeserializerBuilder) {
        Map<String, AnnotatedMember> mapFindBackReferenceProperties = basicBeanDescription.findBackReferenceProperties();
        if (mapFindBackReferenceProperties != null) {
            for (Map.Entry<String, AnnotatedMember> entry : mapFindBackReferenceProperties.entrySet()) {
                String key = entry.getKey();
                AnnotatedMember value = entry.getValue();
                if (value instanceof AnnotatedMethod) {
                    beanDeserializerBuilder.addBackReferenceProperty(key, constructSettableProperty(deserializationConfig, basicBeanDescription, value.getName(), (AnnotatedMethod) value));
                } else {
                    beanDeserializerBuilder.addBackReferenceProperty(key, constructSettableProperty(deserializationConfig, basicBeanDescription, value.getName(), (AnnotatedField) value));
                }
            }
        }
    }

    protected SettableAnyProperty constructAnySetter(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, AnnotatedMethod annotatedMethod) {
        if (deserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            annotatedMethod.fixAccess();
        }
        JavaType javaTypeResolveType = basicBeanDescription.bindingsForBeanType().resolveType(annotatedMethod.getParameterType(1));
        BeanProperty.Std std = new BeanProperty.Std(annotatedMethod.getName(), javaTypeResolveType, basicBeanDescription.getClassAnnotations(), annotatedMethod);
        JavaType javaTypeResolveType2 = resolveType(deserializationConfig, basicBeanDescription, javaTypeResolveType, annotatedMethod, std);
        JsonDeserializer<Object> jsonDeserializerFindDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, annotatedMethod, std);
        if (jsonDeserializerFindDeserializerFromAnnotation != null) {
            SettableAnyProperty settableAnyProperty = new SettableAnyProperty(std, annotatedMethod, javaTypeResolveType2);
            settableAnyProperty.setValueDeserializer(jsonDeserializerFindDeserializerFromAnnotation);
            return settableAnyProperty;
        }
        return new SettableAnyProperty(std, annotatedMethod, modifyTypeByAnnotation(deserializationConfig, annotatedMethod, javaTypeResolveType2, std.getName()));
    }

    protected SettableBeanProperty constructSettableProperty(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, String str, AnnotatedMethod annotatedMethod) {
        if (deserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            annotatedMethod.fixAccess();
        }
        JavaType javaTypeResolveType = basicBeanDescription.bindingsForBeanType().resolveType(annotatedMethod.getParameterType(0));
        BeanProperty.Std std = new BeanProperty.Std(str, javaTypeResolveType, basicBeanDescription.getClassAnnotations(), annotatedMethod);
        JavaType javaTypeResolveType2 = resolveType(deserializationConfig, basicBeanDescription, javaTypeResolveType, annotatedMethod, std);
        if (javaTypeResolveType2 != javaTypeResolveType) {
            std = std.withType(javaTypeResolveType2);
        }
        JsonDeserializer<Object> jsonDeserializerFindDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, annotatedMethod, std);
        JavaType javaTypeModifyTypeByAnnotation = modifyTypeByAnnotation(deserializationConfig, annotatedMethod, javaTypeResolveType2, str);
        SettableBeanProperty.MethodProperty methodProperty = new SettableBeanProperty.MethodProperty(str, javaTypeModifyTypeByAnnotation, (TypeDeserializer) javaTypeModifyTypeByAnnotation.getTypeHandler(), basicBeanDescription.getClassAnnotations(), annotatedMethod);
        if (jsonDeserializerFindDeserializerFromAnnotation != null) {
            methodProperty.setValueDeserializer(jsonDeserializerFindDeserializerFromAnnotation);
        }
        AnnotationIntrospector.ReferenceProperty referencePropertyFindReferenceType = deserializationConfig.getAnnotationIntrospector().findReferenceType(annotatedMethod);
        if (referencePropertyFindReferenceType != null && referencePropertyFindReferenceType.isManagedReference()) {
            methodProperty.setManagedReferenceName(referencePropertyFindReferenceType.getName());
        }
        return methodProperty;
    }

    protected SettableBeanProperty constructSettableProperty(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, String str, AnnotatedField annotatedField) {
        if (deserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            annotatedField.fixAccess();
        }
        JavaType javaTypeResolveType = basicBeanDescription.bindingsForBeanType().resolveType(annotatedField.getGenericType());
        BeanProperty.Std std = new BeanProperty.Std(str, javaTypeResolveType, basicBeanDescription.getClassAnnotations(), annotatedField);
        JavaType javaTypeResolveType2 = resolveType(deserializationConfig, basicBeanDescription, javaTypeResolveType, annotatedField, std);
        if (javaTypeResolveType2 != javaTypeResolveType) {
            std = std.withType(javaTypeResolveType2);
        }
        JsonDeserializer<Object> jsonDeserializerFindDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, annotatedField, std);
        JavaType javaTypeModifyTypeByAnnotation = modifyTypeByAnnotation(deserializationConfig, annotatedField, javaTypeResolveType2, str);
        SettableBeanProperty.FieldProperty fieldProperty = new SettableBeanProperty.FieldProperty(str, javaTypeModifyTypeByAnnotation, (TypeDeserializer) javaTypeModifyTypeByAnnotation.getTypeHandler(), basicBeanDescription.getClassAnnotations(), annotatedField);
        if (jsonDeserializerFindDeserializerFromAnnotation != null) {
            fieldProperty.setValueDeserializer(jsonDeserializerFindDeserializerFromAnnotation);
        }
        AnnotationIntrospector.ReferenceProperty referencePropertyFindReferenceType = deserializationConfig.getAnnotationIntrospector().findReferenceType(annotatedField);
        if (referencePropertyFindReferenceType != null && referencePropertyFindReferenceType.isManagedReference()) {
            fieldProperty.setManagedReferenceName(referencePropertyFindReferenceType.getName());
        }
        return fieldProperty;
    }

    protected SettableBeanProperty constructSetterlessProperty(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, String str, AnnotatedMethod annotatedMethod) {
        if (deserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            annotatedMethod.fixAccess();
        }
        JavaType type = annotatedMethod.getType(basicBeanDescription.bindingsForBeanType());
        JsonDeserializer<Object> jsonDeserializerFindDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, annotatedMethod, new BeanProperty.Std(str, type, basicBeanDescription.getClassAnnotations(), annotatedMethod));
        JavaType javaTypeModifyTypeByAnnotation = modifyTypeByAnnotation(deserializationConfig, annotatedMethod, type, str);
        SettableBeanProperty.SetterlessProperty setterlessProperty = new SettableBeanProperty.SetterlessProperty(str, javaTypeModifyTypeByAnnotation, (TypeDeserializer) javaTypeModifyTypeByAnnotation.getTypeHandler(), basicBeanDescription.getClassAnnotations(), annotatedMethod);
        if (jsonDeserializerFindDeserializerFromAnnotation != null) {
            setterlessProperty.setValueDeserializer(jsonDeserializerFindDeserializerFromAnnotation);
        }
        return setterlessProperty;
    }

    protected boolean isPotentialBeanType(Class<?> cls) {
        String strCanBeABeanType = ClassUtil.canBeABeanType(cls);
        if (strCanBeABeanType != null) {
            throw new IllegalArgumentException("Can not deserialize Class " + cls.getName() + " (of type " + strCanBeABeanType + ") as a Bean");
        }
        if (ClassUtil.isProxyType(cls)) {
            throw new IllegalArgumentException("Can not deserialize Proxy class " + cls.getName() + " as a Bean");
        }
        String strIsLocalType = ClassUtil.isLocalType(cls);
        if (strIsLocalType != null) {
            throw new IllegalArgumentException("Can not deserialize Class " + cls.getName() + " (of type " + strIsLocalType + ") as a Bean");
        }
        return true;
    }

    protected boolean isIgnorableType(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, Class<?> cls, Map<Class<?>, Boolean> map) {
        Boolean boolIsIgnorableType = map.get(cls);
        if (boolIsIgnorableType == null) {
            boolIsIgnorableType = deserializationConfig.getAnnotationIntrospector().isIgnorableType(((BasicBeanDescription) deserializationConfig.introspectClassAnnotations(cls)).getClassInfo());
            if (boolIsIgnorableType == null) {
                boolIsIgnorableType = Boolean.FALSE;
            }
        }
        return boolIsIgnorableType.booleanValue();
    }
}
