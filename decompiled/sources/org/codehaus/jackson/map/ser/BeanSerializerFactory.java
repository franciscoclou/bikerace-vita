package org.codehaus.jackson.map.ser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializerFactory;
import org.codehaus.jackson.map.Serializers;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.introspect.AnnotatedField;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.introspect.VisibilityChecker;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.map.type.TypeBindings;
import org.codehaus.jackson.map.util.ArrayBuilders;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.type.JavaType;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class BeanSerializerFactory extends BasicSerializerFactory {
    public static final BeanSerializerFactory instance = new BeanSerializerFactory(null);
    protected final SerializerFactory.Config _factoryConfig;

    public class ConfigImpl extends SerializerFactory.Config {
        protected final Serializers[] _additionalKeySerializers;
        protected final Serializers[] _additionalSerializers;
        protected final BeanSerializerModifier[] _modifiers;
        protected static final Serializers[] NO_SERIALIZERS = new Serializers[0];
        protected static final BeanSerializerModifier[] NO_MODIFIERS = new BeanSerializerModifier[0];

        public ConfigImpl() {
            this(null, null, null);
        }

        protected ConfigImpl(Serializers[] serializersArr, Serializers[] serializersArr2, BeanSerializerModifier[] beanSerializerModifierArr) {
            this._additionalSerializers = serializersArr == null ? NO_SERIALIZERS : serializersArr;
            this._additionalKeySerializers = serializersArr2 == null ? NO_SERIALIZERS : serializersArr2;
            this._modifiers = beanSerializerModifierArr == null ? NO_MODIFIERS : beanSerializerModifierArr;
        }

        @Override // org.codehaus.jackson.map.SerializerFactory.Config
        public SerializerFactory.Config withAdditionalSerializers(Serializers serializers) {
            if (serializers == null) {
                throw new IllegalArgumentException("Can not pass null Serializers");
            }
            return new ConfigImpl((Serializers[]) ArrayBuilders.insertInListNoDup(this._additionalSerializers, serializers), this._additionalKeySerializers, this._modifiers);
        }

        @Override // org.codehaus.jackson.map.SerializerFactory.Config
        public SerializerFactory.Config withAdditionalKeySerializers(Serializers serializers) {
            if (serializers == null) {
                throw new IllegalArgumentException("Can not pass null Serializers");
            }
            return new ConfigImpl(this._additionalSerializers, (Serializers[]) ArrayBuilders.insertInListNoDup(this._additionalKeySerializers, serializers), this._modifiers);
        }

        @Override // org.codehaus.jackson.map.SerializerFactory.Config
        public SerializerFactory.Config withSerializerModifier(BeanSerializerModifier beanSerializerModifier) {
            if (beanSerializerModifier == null) {
                throw new IllegalArgumentException("Can not pass null modifier");
            }
            return new ConfigImpl(this._additionalSerializers, this._additionalKeySerializers, (BeanSerializerModifier[]) ArrayBuilders.insertInListNoDup(this._modifiers, beanSerializerModifier));
        }

        @Override // org.codehaus.jackson.map.SerializerFactory.Config
        public boolean hasSerializers() {
            return this._additionalSerializers.length > 0;
        }

        @Override // org.codehaus.jackson.map.SerializerFactory.Config
        public boolean hasKeySerializers() {
            return this._additionalKeySerializers.length > 0;
        }

        @Override // org.codehaus.jackson.map.SerializerFactory.Config
        public boolean hasSerializerModifiers() {
            return this._modifiers.length > 0;
        }

        @Override // org.codehaus.jackson.map.SerializerFactory.Config
        public Iterable<Serializers> serializers() {
            return ArrayBuilders.arrayAsIterable(this._additionalSerializers);
        }

        @Override // org.codehaus.jackson.map.SerializerFactory.Config
        public Iterable<Serializers> keySerializers() {
            return ArrayBuilders.arrayAsIterable(this._additionalKeySerializers);
        }

        @Override // org.codehaus.jackson.map.SerializerFactory.Config
        public Iterable<BeanSerializerModifier> serializerModifiers() {
            return ArrayBuilders.arrayAsIterable(this._modifiers);
        }
    }

    @Deprecated
    protected BeanSerializerFactory() {
        this(null);
    }

    protected BeanSerializerFactory(SerializerFactory.Config config) {
        this._factoryConfig = config == null ? new ConfigImpl() : config;
    }

    @Override // org.codehaus.jackson.map.SerializerFactory
    public SerializerFactory.Config getConfig() {
        return this._factoryConfig;
    }

    @Override // org.codehaus.jackson.map.SerializerFactory
    public SerializerFactory withConfig(SerializerFactory.Config config) {
        if (this._factoryConfig != config) {
            if (getClass() != BeanSerializerFactory.class) {
                throw new IllegalStateException("Subtype of BeanSerializerFactory (" + getClass().getName() + ") has not properly overridden method 'withAdditionalSerializers': can not instantiate subtype with additional serializer definitions");
            }
            return new BeanSerializerFactory(config);
        }
        return this;
    }

    @Override // org.codehaus.jackson.map.ser.BasicSerializerFactory
    protected Iterable<Serializers> customSerializers() {
        return this._factoryConfig.serializers();
    }

    @Override // org.codehaus.jackson.map.ser.BasicSerializerFactory, org.codehaus.jackson.map.SerializerFactory
    public JsonSerializer<Object> createSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanProperty beanProperty) {
        BasicBeanDescription basicBeanDescription = (BasicBeanDescription) serializationConfig.introspect(javaType);
        JsonSerializer<Object> jsonSerializerFindSerializerFromAnnotation = findSerializerFromAnnotation(serializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
        if (jsonSerializerFindSerializerFromAnnotation == null) {
            JavaType javaTypeModifyTypeByAnnotation = modifyTypeByAnnotation(serializationConfig, basicBeanDescription.getClassInfo(), javaType);
            boolean z = javaTypeModifyTypeByAnnotation != javaType;
            if (javaType.isContainerType()) {
                return buildContainerSerializer(serializationConfig, javaTypeModifyTypeByAnnotation, basicBeanDescription, beanProperty, z);
            }
            Iterator<Serializers> it = this._factoryConfig.serializers().iterator();
            while (it.hasNext()) {
                JsonSerializer<?> jsonSerializerFindSerializer = it.next().findSerializer(serializationConfig, javaTypeModifyTypeByAnnotation, basicBeanDescription, beanProperty);
                if (jsonSerializerFindSerializer != null) {
                    return jsonSerializerFindSerializer;
                }
            }
            JsonSerializer<?> jsonSerializerFindSerializerByLookup = findSerializerByLookup(javaTypeModifyTypeByAnnotation, serializationConfig, basicBeanDescription, beanProperty, z);
            if (jsonSerializerFindSerializerByLookup == null) {
                JsonSerializer<?> jsonSerializerFindSerializerByPrimaryType = findSerializerByPrimaryType(javaTypeModifyTypeByAnnotation, serializationConfig, basicBeanDescription, beanProperty, z);
                if (jsonSerializerFindSerializerByPrimaryType == null) {
                    JsonSerializer<Object> jsonSerializerFindBeanSerializer = findBeanSerializer(serializationConfig, javaTypeModifyTypeByAnnotation, basicBeanDescription, beanProperty);
                    if (jsonSerializerFindBeanSerializer == null) {
                        return super.findSerializerByAddonType(serializationConfig, javaTypeModifyTypeByAnnotation, basicBeanDescription, beanProperty, z);
                    }
                    return jsonSerializerFindBeanSerializer;
                }
                return jsonSerializerFindSerializerByPrimaryType;
            }
            return jsonSerializerFindSerializerByLookup;
        }
        return jsonSerializerFindSerializerFromAnnotation;
    }

    @Override // org.codehaus.jackson.map.SerializerFactory
    public JsonSerializer<Object> createKeySerializer(SerializationConfig serializationConfig, JavaType javaType, BeanProperty beanProperty) {
        JsonSerializer<?> jsonSerializerFindSerializer = null;
        if (this._factoryConfig.hasKeySerializers()) {
            BasicBeanDescription basicBeanDescription = (BasicBeanDescription) serializationConfig.introspectClassAnnotations(javaType.getRawClass());
            Iterator<Serializers> it = this._factoryConfig.keySerializers().iterator();
            while (it.hasNext() && (jsonSerializerFindSerializer = it.next().findSerializer(serializationConfig, javaType, basicBeanDescription, beanProperty)) == null) {
            }
        }
        return jsonSerializerFindSerializer;
    }

    public JsonSerializer<Object> findBeanSerializer(SerializationConfig serializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty) {
        if (!isPotentialBeanType(javaType.getRawClass())) {
            return null;
        }
        JsonSerializer<?> jsonSerializerConstructBeanSerializer = constructBeanSerializer(serializationConfig, basicBeanDescription, beanProperty);
        if (!this._factoryConfig.hasSerializerModifiers()) {
            return jsonSerializerConstructBeanSerializer;
        }
        Iterator<BeanSerializerModifier> it = this._factoryConfig.serializerModifiers().iterator();
        while (true) {
            JsonSerializer<?> jsonSerializer = jsonSerializerConstructBeanSerializer;
            if (it.hasNext()) {
                jsonSerializerConstructBeanSerializer = it.next().modifySerializer(serializationConfig, basicBeanDescription, jsonSerializer);
            } else {
                return jsonSerializer;
            }
        }
    }

    public TypeSerializer findPropertyTypeSerializer(JavaType javaType, SerializationConfig serializationConfig, AnnotatedMember annotatedMember, BeanProperty beanProperty) {
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder<?> typeResolverBuilderFindPropertyTypeResolver = annotationIntrospector.findPropertyTypeResolver(serializationConfig, annotatedMember, javaType);
        if (typeResolverBuilderFindPropertyTypeResolver == null) {
            return createTypeSerializer(serializationConfig, javaType, beanProperty);
        }
        return typeResolverBuilderFindPropertyTypeResolver.buildTypeSerializer(serializationConfig, javaType, serializationConfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedMember, serializationConfig, annotationIntrospector), beanProperty);
    }

    public TypeSerializer findPropertyContentTypeSerializer(JavaType javaType, SerializationConfig serializationConfig, AnnotatedMember annotatedMember, BeanProperty beanProperty) {
        JavaType contentType = javaType.getContentType();
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder<?> typeResolverBuilderFindPropertyContentTypeResolver = annotationIntrospector.findPropertyContentTypeResolver(serializationConfig, annotatedMember, javaType);
        if (typeResolverBuilderFindPropertyContentTypeResolver == null) {
            return createTypeSerializer(serializationConfig, contentType, beanProperty);
        }
        return typeResolverBuilderFindPropertyContentTypeResolver.buildTypeSerializer(serializationConfig, contentType, serializationConfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedMember, serializationConfig, annotationIntrospector), beanProperty);
    }

    protected JsonSerializer<Object> constructBeanSerializer(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty) {
        List<BeanPropertyWriter> list;
        List<BeanPropertyWriter> listEmptyList;
        List<BeanPropertyWriter> list2;
        BeanSerializerBuilder beanSerializerBuilderUpdateBuilder;
        if (basicBeanDescription.getBeanClass() == Object.class) {
            throw new IllegalArgumentException("Can not create bean serializer for Object.class");
        }
        BeanSerializerBuilder beanSerializerBuilderConstructBeanSerializerBuilder = constructBeanSerializerBuilder(basicBeanDescription);
        List<BeanPropertyWriter> listFindBeanProperties = findBeanProperties(serializationConfig, basicBeanDescription);
        if (this._factoryConfig.hasSerializerModifiers()) {
            if (listFindBeanProperties == null) {
                listFindBeanProperties = new ArrayList<>();
            }
            Iterator<BeanSerializerModifier> it = this._factoryConfig.serializerModifiers().iterator();
            while (true) {
                list = listFindBeanProperties;
                if (!it.hasNext()) {
                    break;
                }
                listFindBeanProperties = it.next().changeProperties(serializationConfig, basicBeanDescription, list);
            }
        } else {
            list = listFindBeanProperties;
        }
        AnnotatedMethod annotatedMethodFindAnyGetter = basicBeanDescription.findAnyGetter();
        if (list == null || list.size() == 0) {
            if (annotatedMethodFindAnyGetter == null) {
                if (basicBeanDescription.hasKnownClassAnnotations()) {
                    return beanSerializerBuilderConstructBeanSerializerBuilder.createDummy();
                }
                return null;
            }
            listEmptyList = Collections.emptyList();
        } else {
            listEmptyList = sortBeanProperties(serializationConfig, basicBeanDescription, filterBeanProperties(serializationConfig, basicBeanDescription, list));
        }
        if (this._factoryConfig.hasSerializerModifiers()) {
            Iterator<BeanSerializerModifier> it2 = this._factoryConfig.serializerModifiers().iterator();
            while (true) {
                list2 = listEmptyList;
                if (!it2.hasNext()) {
                    break;
                }
                listEmptyList = it2.next().orderProperties(serializationConfig, basicBeanDescription, list2);
            }
        } else {
            list2 = listEmptyList;
        }
        beanSerializerBuilderConstructBeanSerializerBuilder.setProperties(list2);
        beanSerializerBuilderConstructBeanSerializerBuilder.setFilterId(findFilterId(serializationConfig, basicBeanDescription));
        if (annotatedMethodFindAnyGetter != null) {
            if (serializationConfig.isEnabled(SerializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                annotatedMethodFindAnyGetter.fixAccess();
            }
            JavaType type = annotatedMethodFindAnyGetter.getType(basicBeanDescription.bindingsForBeanType());
            beanSerializerBuilderConstructBeanSerializerBuilder.setAnyGetter(new AnyGetterWriter(annotatedMethodFindAnyGetter, MapSerializer.construct(null, type, serializationConfig.isEnabled(SerializationConfig.Feature.USE_STATIC_TYPING), createTypeSerializer(serializationConfig, type.getContentType(), beanProperty), beanProperty, null, null)));
        }
        processViews(serializationConfig, beanSerializerBuilderConstructBeanSerializerBuilder);
        if (this._factoryConfig.hasSerializerModifiers()) {
            Iterator<BeanSerializerModifier> it3 = this._factoryConfig.serializerModifiers().iterator();
            beanSerializerBuilderUpdateBuilder = beanSerializerBuilderConstructBeanSerializerBuilder;
            while (it3.hasNext()) {
                beanSerializerBuilderUpdateBuilder = it3.next().updateBuilder(serializationConfig, basicBeanDescription, beanSerializerBuilderUpdateBuilder);
            }
        } else {
            beanSerializerBuilderUpdateBuilder = beanSerializerBuilderConstructBeanSerializerBuilder;
        }
        return beanSerializerBuilderUpdateBuilder.build();
    }

    protected BeanPropertyWriter constructFilteredBeanWriter(BeanPropertyWriter beanPropertyWriter, Class<?>[] clsArr) {
        return FilteredBeanPropertyWriter.constructViewBased(beanPropertyWriter, clsArr);
    }

    protected PropertyBuilder constructPropertyBuilder(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription) {
        return new PropertyBuilder(serializationConfig, basicBeanDescription);
    }

    protected BeanSerializerBuilder constructBeanSerializerBuilder(BasicBeanDescription basicBeanDescription) {
        return new BeanSerializerBuilder(basicBeanDescription);
    }

    protected Object findFilterId(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription) {
        return serializationConfig.getAnnotationIntrospector().findFilterId(basicBeanDescription.getClassInfo());
    }

    protected boolean isPotentialBeanType(Class<?> cls) {
        return ClassUtil.canBeABeanType(cls) == null && !ClassUtil.isProxyType(cls);
    }

    protected List<BeanPropertyWriter> findBeanProperties(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription) {
        VisibilityChecker<?> defaultVisibilityChecker = serializationConfig.getDefaultVisibilityChecker();
        if (!serializationConfig.isEnabled(SerializationConfig.Feature.AUTO_DETECT_GETTERS)) {
            defaultVisibilityChecker = defaultVisibilityChecker.withGetterVisibility(JsonAutoDetect.Visibility.NONE);
        }
        if (!serializationConfig.isEnabled(SerializationConfig.Feature.AUTO_DETECT_IS_GETTERS)) {
            defaultVisibilityChecker = defaultVisibilityChecker.withIsGetterVisibility(JsonAutoDetect.Visibility.NONE);
        }
        if (!serializationConfig.isEnabled(SerializationConfig.Feature.AUTO_DETECT_FIELDS)) {
            defaultVisibilityChecker = defaultVisibilityChecker.withFieldVisibility(JsonAutoDetect.Visibility.NONE);
        }
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        VisibilityChecker<?> visibilityCheckerFindAutoDetectVisibility = annotationIntrospector.findAutoDetectVisibility(basicBeanDescription.getClassInfo(), defaultVisibilityChecker);
        LinkedHashMap<String, AnnotatedMethod> linkedHashMapFindGetters = basicBeanDescription.findGetters(visibilityCheckerFindAutoDetectVisibility, null);
        LinkedHashMap<String, AnnotatedField> linkedHashMapFindSerializableFields = basicBeanDescription.findSerializableFields(visibilityCheckerFindAutoDetectVisibility, linkedHashMapFindGetters.keySet());
        removeIgnorableTypes(serializationConfig, basicBeanDescription, linkedHashMapFindGetters);
        removeIgnorableTypes(serializationConfig, basicBeanDescription, linkedHashMapFindSerializableFields);
        if (linkedHashMapFindGetters.isEmpty() && linkedHashMapFindSerializableFields.isEmpty()) {
            return null;
        }
        boolean zUsesStaticTyping = usesStaticTyping(serializationConfig, basicBeanDescription, null, null);
        PropertyBuilder propertyBuilderConstructPropertyBuilder = constructPropertyBuilder(serializationConfig, basicBeanDescription);
        ArrayList arrayList = new ArrayList(linkedHashMapFindGetters.size());
        TypeBindings typeBindingsBindingsForBeanType = basicBeanDescription.bindingsForBeanType();
        for (Map.Entry<String, AnnotatedField> entry : linkedHashMapFindSerializableFields.entrySet()) {
            AnnotationIntrospector.ReferenceProperty referencePropertyFindReferenceType = annotationIntrospector.findReferenceType(entry.getValue());
            if (referencePropertyFindReferenceType == null || !referencePropertyFindReferenceType.isBackReference()) {
                arrayList.add(_constructWriter(serializationConfig, typeBindingsBindingsForBeanType, propertyBuilderConstructPropertyBuilder, zUsesStaticTyping, entry.getKey(), entry.getValue()));
            }
        }
        for (Map.Entry<String, AnnotatedMethod> entry2 : linkedHashMapFindGetters.entrySet()) {
            AnnotationIntrospector.ReferenceProperty referencePropertyFindReferenceType2 = annotationIntrospector.findReferenceType(entry2.getValue());
            if (referencePropertyFindReferenceType2 == null || !referencePropertyFindReferenceType2.isBackReference()) {
                arrayList.add(_constructWriter(serializationConfig, typeBindingsBindingsForBeanType, propertyBuilderConstructPropertyBuilder, zUsesStaticTyping, entry2.getKey(), entry2.getValue()));
            }
        }
        return arrayList;
    }

    protected List<BeanPropertyWriter> filterBeanProperties(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, List<BeanPropertyWriter> list) {
        String[] strArrFindPropertiesToIgnore = serializationConfig.getAnnotationIntrospector().findPropertiesToIgnore(basicBeanDescription.getClassInfo());
        if (strArrFindPropertiesToIgnore != null && strArrFindPropertiesToIgnore.length > 0) {
            HashSet hashSetArrayToSet = ArrayBuilders.arrayToSet(strArrFindPropertiesToIgnore);
            Iterator<BeanPropertyWriter> it = list.iterator();
            while (it.hasNext()) {
                if (hashSetArrayToSet.contains(it.next().getName())) {
                    it.remove();
                }
            }
        }
        return list;
    }

    protected List<BeanPropertyWriter> sortBeanProperties(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, List<BeanPropertyWriter> list) {
        boolean zBooleanValue;
        List<String> listFindCreatorPropertyNames = basicBeanDescription.findCreatorPropertyNames();
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        AnnotatedClass classInfo = basicBeanDescription.getClassInfo();
        String[] strArrFindSerializationPropertyOrder = annotationIntrospector.findSerializationPropertyOrder(classInfo);
        Boolean boolFindSerializationSortAlphabetically = annotationIntrospector.findSerializationSortAlphabetically(classInfo);
        if (boolFindSerializationSortAlphabetically == null) {
            zBooleanValue = serializationConfig.isEnabled(SerializationConfig.Feature.SORT_PROPERTIES_ALPHABETICALLY);
        } else {
            zBooleanValue = boolFindSerializationSortAlphabetically.booleanValue();
        }
        if (zBooleanValue || !listFindCreatorPropertyNames.isEmpty() || strArrFindSerializationPropertyOrder != null) {
            return _sortBeanProperties(list, listFindCreatorPropertyNames, strArrFindSerializationPropertyOrder, zBooleanValue);
        }
        return list;
    }

    protected void processViews(SerializationConfig serializationConfig, BeanSerializerBuilder beanSerializerBuilder) {
        List<BeanPropertyWriter> properties = beanSerializerBuilder.getProperties();
        boolean zIsEnabled = serializationConfig.isEnabled(SerializationConfig.Feature.DEFAULT_VIEW_INCLUSION);
        int size = properties.size();
        BeanPropertyWriter[] beanPropertyWriterArr = new BeanPropertyWriter[size];
        int i = 0;
        int i2 = 0;
        while (i < size) {
            BeanPropertyWriter beanPropertyWriter = properties.get(i);
            Class<?>[] views = beanPropertyWriter.getViews();
            if (views == null) {
                if (zIsEnabled) {
                    beanPropertyWriterArr[i] = beanPropertyWriter;
                }
            } else {
                i2++;
                beanPropertyWriterArr[i] = constructFilteredBeanWriter(beanPropertyWriter, views);
            }
            i++;
            i2 = i2;
        }
        if (!zIsEnabled || i2 != 0) {
            beanSerializerBuilder.setFilteredProperties(beanPropertyWriterArr);
        }
    }

    protected <T extends AnnotatedMember> void removeIgnorableTypes(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, Map<String, T> map) {
        if (!map.isEmpty()) {
            AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
            Iterator<Map.Entry<String, T>> it = map.entrySet().iterator();
            HashMap map2 = new HashMap();
            while (it.hasNext()) {
                Class<?> rawType = it.next().getValue().getRawType();
                Boolean boolIsIgnorableType = (Boolean) map2.get(rawType);
                if (boolIsIgnorableType == null) {
                    boolIsIgnorableType = annotationIntrospector.isIgnorableType(((BasicBeanDescription) serializationConfig.introspectClassAnnotations(rawType)).getClassInfo());
                    if (boolIsIgnorableType == null) {
                        boolIsIgnorableType = Boolean.FALSE;
                    }
                    map2.put(rawType, boolIsIgnorableType);
                }
                if (boolIsIgnorableType.booleanValue()) {
                    it.remove();
                }
            }
        }
    }

    protected BeanPropertyWriter _constructWriter(SerializationConfig serializationConfig, TypeBindings typeBindings, PropertyBuilder propertyBuilder, boolean z, String str, AnnotatedMember annotatedMember) {
        if (serializationConfig.isEnabled(SerializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            annotatedMember.fixAccess();
        }
        JavaType type = annotatedMember.getType(typeBindings);
        BeanProperty.Std std = new BeanProperty.Std(str, type, propertyBuilder.getClassAnnotations(), annotatedMember);
        JsonSerializer<Object> jsonSerializerFindSerializerFromAnnotation = findSerializerFromAnnotation(serializationConfig, annotatedMember, std);
        TypeSerializer typeSerializerFindPropertyContentTypeSerializer = null;
        if (ClassUtil.isCollectionMapOrArray(type.getRawClass())) {
            typeSerializerFindPropertyContentTypeSerializer = findPropertyContentTypeSerializer(type, serializationConfig, annotatedMember, std);
        }
        BeanPropertyWriter beanPropertyWriterBuildWriter = propertyBuilder.buildWriter(str, type, jsonSerializerFindSerializerFromAnnotation, findPropertyTypeSerializer(type, serializationConfig, annotatedMember, std), typeSerializerFindPropertyContentTypeSerializer, annotatedMember, z);
        beanPropertyWriterBuildWriter.setViews(serializationConfig.getAnnotationIntrospector().findSerializationViews(annotatedMember));
        return beanPropertyWriterBuildWriter;
    }

    protected List<BeanPropertyWriter> _sortBeanProperties(List<BeanPropertyWriter> list, List<String> list2, String[] strArr, boolean z) {
        Map linkedHashMap;
        int size = list.size();
        if (z) {
            linkedHashMap = new TreeMap();
        } else {
            linkedHashMap = new LinkedHashMap(size * 2);
        }
        for (BeanPropertyWriter beanPropertyWriter : list) {
            linkedHashMap.put(beanPropertyWriter.getName(), beanPropertyWriter);
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(size * 2);
        if (strArr != null) {
            for (String str : strArr) {
                BeanPropertyWriter beanPropertyWriter2 = (BeanPropertyWriter) linkedHashMap.get(str);
                if (beanPropertyWriter2 != null) {
                    linkedHashMap2.put(str, beanPropertyWriter2);
                }
            }
        }
        for (String str2 : list2) {
            BeanPropertyWriter beanPropertyWriter3 = (BeanPropertyWriter) linkedHashMap.get(str2);
            if (beanPropertyWriter3 != null) {
                linkedHashMap2.put(str2, beanPropertyWriter3);
            }
        }
        linkedHashMap2.putAll(linkedHashMap);
        return new ArrayList(linkedHashMap2.values());
    }
}
