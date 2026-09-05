package org.codehaus.jackson.map;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import org.codehaus.jackson.map.introspect.AnnotatedField;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.AnnotatedParameter;
import org.codehaus.jackson.map.introspect.NopAnnotationIntrospector;
import org.codehaus.jackson.map.introspect.VisibilityChecker;
import org.codehaus.jackson.map.jsontype.NamedType;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.type.JavaType;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class AnnotationIntrospector {
    public abstract Boolean findCachability(AnnotatedClass annotatedClass);

    public abstract Class<? extends JsonDeserializer<?>> findContentDeserializer(Annotated annotated);

    public abstract String findDeserializablePropertyName(AnnotatedField annotatedField);

    public abstract Class<?> findDeserializationContentType(Annotated annotated, JavaType javaType, String str);

    public abstract Class<?> findDeserializationKeyType(Annotated annotated, JavaType javaType, String str);

    public abstract Class<?> findDeserializationType(Annotated annotated, JavaType javaType, String str);

    public abstract String findEnumValue(Enum<?> r1);

    public abstract String findGettablePropertyName(AnnotatedMethod annotatedMethod);

    public abstract Boolean findIgnoreUnknownProperties(AnnotatedClass annotatedClass);

    public abstract Class<? extends KeyDeserializer> findKeyDeserializer(Annotated annotated);

    public abstract String[] findPropertiesToIgnore(AnnotatedClass annotatedClass);

    public abstract String findPropertyNameForParam(AnnotatedParameter annotatedParameter);

    public abstract String findRootName(AnnotatedClass annotatedClass);

    public abstract String findSerializablePropertyName(AnnotatedField annotatedField);

    public abstract String[] findSerializationPropertyOrder(AnnotatedClass annotatedClass);

    public abstract Boolean findSerializationSortAlphabetically(AnnotatedClass annotatedClass);

    public abstract Class<?> findSerializationType(Annotated annotated);

    public abstract JsonSerialize.Typing findSerializationTyping(Annotated annotated);

    public abstract Class<?>[] findSerializationViews(Annotated annotated);

    public abstract String findSettablePropertyName(AnnotatedMethod annotatedMethod);

    public abstract boolean hasAsValueAnnotation(AnnotatedMethod annotatedMethod);

    public abstract boolean isHandled(Annotation annotation);

    public abstract boolean isIgnorableConstructor(AnnotatedConstructor annotatedConstructor);

    public abstract boolean isIgnorableField(AnnotatedField annotatedField);

    public abstract boolean isIgnorableMethod(AnnotatedMethod annotatedMethod);

    public class ReferenceProperty {
        private final String _name;
        private final Type _type;

        public enum Type {
            MANAGED_REFERENCE,
            BACK_REFERENCE
        }

        public ReferenceProperty(Type type, String str) {
            this._type = type;
            this._name = str;
        }

        public static ReferenceProperty managed(String str) {
            return new ReferenceProperty(Type.MANAGED_REFERENCE, str);
        }

        public static ReferenceProperty back(String str) {
            return new ReferenceProperty(Type.BACK_REFERENCE, str);
        }

        public Type getType() {
            return this._type;
        }

        public String getName() {
            return this._name;
        }

        public boolean isManagedReference() {
            return this._type == Type.MANAGED_REFERENCE;
        }

        public boolean isBackReference() {
            return this._type == Type.BACK_REFERENCE;
        }
    }

    public static AnnotationIntrospector nopInstance() {
        return NopAnnotationIntrospector.instance;
    }

    public static AnnotationIntrospector pair(AnnotationIntrospector annotationIntrospector, AnnotationIntrospector annotationIntrospector2) {
        return new Pair(annotationIntrospector, annotationIntrospector2);
    }

    public Collection<AnnotationIntrospector> allIntrospectors() {
        return Collections.singletonList(this);
    }

    public Collection<AnnotationIntrospector> allIntrospectors(Collection<AnnotationIntrospector> collection) {
        collection.add(this);
        return collection;
    }

    public Boolean isIgnorableType(AnnotatedClass annotatedClass) {
        return null;
    }

    public Object findFilterId(AnnotatedClass annotatedClass) {
        return null;
    }

    public VisibilityChecker<?> findAutoDetectVisibility(AnnotatedClass annotatedClass, VisibilityChecker<?> visibilityChecker) {
        return visibilityChecker;
    }

    public TypeResolverBuilder<?> findTypeResolver(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, JavaType javaType) {
        return null;
    }

    public TypeResolverBuilder<?> findPropertyTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        return null;
    }

    public TypeResolverBuilder<?> findPropertyContentTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        return null;
    }

    public List<NamedType> findSubtypes(Annotated annotated) {
        return null;
    }

    public String findTypeName(AnnotatedClass annotatedClass) {
        return null;
    }

    public ReferenceProperty findReferenceType(AnnotatedMember annotatedMember) {
        return null;
    }

    public Object findSerializer(Annotated annotated) {
        return findSerializer(annotated, null);
    }

    @Deprecated
    public Object findSerializer(Annotated annotated, BeanProperty beanProperty) {
        if (beanProperty != null) {
            return findSerializer(annotated);
        }
        return null;
    }

    public Class<? extends JsonSerializer<?>> findKeySerializer(Annotated annotated) {
        return null;
    }

    public Class<? extends JsonSerializer<?>> findContentSerializer(Annotated annotated) {
        return null;
    }

    public JsonSerialize.Inclusion findSerializationInclusion(Annotated annotated, JsonSerialize.Inclusion inclusion) {
        return inclusion;
    }

    public Class<?> findSerializationKeyType(Annotated annotated, JavaType javaType) {
        return null;
    }

    public Class<?> findSerializationContentType(Annotated annotated, JavaType javaType) {
        return null;
    }

    public Object findDeserializer(Annotated annotated) {
        return findDeserializer(annotated, null);
    }

    @Deprecated
    public Object findDeserializer(Annotated annotated, BeanProperty beanProperty) {
        if (beanProperty != null) {
            return findDeserializer(annotated);
        }
        return null;
    }

    public boolean hasAnySetterAnnotation(AnnotatedMethod annotatedMethod) {
        return false;
    }

    public boolean hasAnyGetterAnnotation(AnnotatedMethod annotatedMethod) {
        return false;
    }

    public boolean hasCreatorAnnotation(Annotated annotated) {
        return false;
    }

    public class Pair extends AnnotationIntrospector {
        protected final AnnotationIntrospector _primary;
        protected final AnnotationIntrospector _secondary;

        public Pair(AnnotationIntrospector annotationIntrospector, AnnotationIntrospector annotationIntrospector2) {
            this._primary = annotationIntrospector;
            this._secondary = annotationIntrospector2;
        }

        public static AnnotationIntrospector create(AnnotationIntrospector annotationIntrospector, AnnotationIntrospector annotationIntrospector2) {
            if (annotationIntrospector == null) {
                return annotationIntrospector2;
            }
            return annotationIntrospector2 == null ? annotationIntrospector : new Pair(annotationIntrospector, annotationIntrospector2);
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public Collection<AnnotationIntrospector> allIntrospectors() {
            return allIntrospectors(new ArrayList());
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public Collection<AnnotationIntrospector> allIntrospectors(Collection<AnnotationIntrospector> collection) {
            this._primary.allIntrospectors(collection);
            this._secondary.allIntrospectors(collection);
            return collection;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public boolean isHandled(Annotation annotation) {
            return this._primary.isHandled(annotation) || this._secondary.isHandled(annotation);
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public Boolean findCachability(AnnotatedClass annotatedClass) {
            Boolean boolFindCachability = this._primary.findCachability(annotatedClass);
            if (boolFindCachability == null) {
                return this._secondary.findCachability(annotatedClass);
            }
            return boolFindCachability;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public String findRootName(AnnotatedClass annotatedClass) {
            String strFindRootName;
            String strFindRootName2 = this._primary.findRootName(annotatedClass);
            if (strFindRootName2 == null) {
                return this._secondary.findRootName(annotatedClass);
            }
            return (strFindRootName2.length() > 0 || (strFindRootName = this._secondary.findRootName(annotatedClass)) == null) ? strFindRootName2 : strFindRootName;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public String[] findPropertiesToIgnore(AnnotatedClass annotatedClass) {
            String[] strArrFindPropertiesToIgnore = this._primary.findPropertiesToIgnore(annotatedClass);
            if (strArrFindPropertiesToIgnore == null) {
                return this._secondary.findPropertiesToIgnore(annotatedClass);
            }
            return strArrFindPropertiesToIgnore;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public Boolean findIgnoreUnknownProperties(AnnotatedClass annotatedClass) {
            Boolean boolFindIgnoreUnknownProperties = this._primary.findIgnoreUnknownProperties(annotatedClass);
            if (boolFindIgnoreUnknownProperties == null) {
                return this._secondary.findIgnoreUnknownProperties(annotatedClass);
            }
            return boolFindIgnoreUnknownProperties;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public Boolean isIgnorableType(AnnotatedClass annotatedClass) {
            Boolean boolIsIgnorableType = this._primary.isIgnorableType(annotatedClass);
            if (boolIsIgnorableType == null) {
                return this._secondary.isIgnorableType(annotatedClass);
            }
            return boolIsIgnorableType;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public Object findFilterId(AnnotatedClass annotatedClass) {
            Object objFindFilterId = this._primary.findFilterId(annotatedClass);
            if (objFindFilterId == null) {
                return this._secondary.findFilterId(annotatedClass);
            }
            return objFindFilterId;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public VisibilityChecker<?> findAutoDetectVisibility(AnnotatedClass annotatedClass, VisibilityChecker<?> visibilityChecker) {
            return this._primary.findAutoDetectVisibility(annotatedClass, this._secondary.findAutoDetectVisibility(annotatedClass, visibilityChecker));
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public TypeResolverBuilder<?> findTypeResolver(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, JavaType javaType) {
            TypeResolverBuilder<?> typeResolverBuilderFindTypeResolver = this._primary.findTypeResolver(mapperConfig, annotatedClass, javaType);
            if (typeResolverBuilderFindTypeResolver == null) {
                return this._secondary.findTypeResolver(mapperConfig, annotatedClass, javaType);
            }
            return typeResolverBuilderFindTypeResolver;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public TypeResolverBuilder<?> findPropertyTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
            TypeResolverBuilder<?> typeResolverBuilderFindPropertyTypeResolver = this._primary.findPropertyTypeResolver(mapperConfig, annotatedMember, javaType);
            if (typeResolverBuilderFindPropertyTypeResolver == null) {
                return this._secondary.findPropertyTypeResolver(mapperConfig, annotatedMember, javaType);
            }
            return typeResolverBuilderFindPropertyTypeResolver;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public TypeResolverBuilder<?> findPropertyContentTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
            TypeResolverBuilder<?> typeResolverBuilderFindPropertyContentTypeResolver = this._primary.findPropertyContentTypeResolver(mapperConfig, annotatedMember, javaType);
            if (typeResolverBuilderFindPropertyContentTypeResolver == null) {
                return this._secondary.findPropertyContentTypeResolver(mapperConfig, annotatedMember, javaType);
            }
            return typeResolverBuilderFindPropertyContentTypeResolver;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public List<NamedType> findSubtypes(Annotated annotated) {
            List<NamedType> listFindSubtypes = this._primary.findSubtypes(annotated);
            List<NamedType> listFindSubtypes2 = this._secondary.findSubtypes(annotated);
            if (listFindSubtypes == null || listFindSubtypes.isEmpty()) {
                return listFindSubtypes2;
            }
            if (listFindSubtypes2 == null || listFindSubtypes2.isEmpty()) {
                return listFindSubtypes;
            }
            ArrayList arrayList = new ArrayList(listFindSubtypes.size() + listFindSubtypes2.size());
            arrayList.addAll(listFindSubtypes);
            arrayList.addAll(listFindSubtypes2);
            return arrayList;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public String findTypeName(AnnotatedClass annotatedClass) {
            String strFindTypeName = this._primary.findTypeName(annotatedClass);
            if (strFindTypeName == null || strFindTypeName.length() == 0) {
                return this._secondary.findTypeName(annotatedClass);
            }
            return strFindTypeName;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public ReferenceProperty findReferenceType(AnnotatedMember annotatedMember) {
            ReferenceProperty referencePropertyFindReferenceType = this._primary.findReferenceType(annotatedMember);
            if (referencePropertyFindReferenceType == null) {
                return this._secondary.findReferenceType(annotatedMember);
            }
            return referencePropertyFindReferenceType;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public boolean isIgnorableMethod(AnnotatedMethod annotatedMethod) {
            return this._primary.isIgnorableMethod(annotatedMethod) || this._secondary.isIgnorableMethod(annotatedMethod);
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public boolean isIgnorableConstructor(AnnotatedConstructor annotatedConstructor) {
            return this._primary.isIgnorableConstructor(annotatedConstructor) || this._secondary.isIgnorableConstructor(annotatedConstructor);
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public boolean isIgnorableField(AnnotatedField annotatedField) {
            return this._primary.isIgnorableField(annotatedField) || this._secondary.isIgnorableField(annotatedField);
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public Object findSerializer(Annotated annotated, BeanProperty beanProperty) {
            Object objFindSerializer = this._primary.findSerializer(annotated, beanProperty);
            if (objFindSerializer == null) {
                return this._secondary.findSerializer(annotated, beanProperty);
            }
            return objFindSerializer;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public Object findSerializer(Annotated annotated) {
            Object objFindSerializer = this._primary.findSerializer(annotated);
            if (objFindSerializer == null) {
                return this._secondary.findSerializer(annotated);
            }
            return objFindSerializer;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public Class<? extends JsonSerializer<?>> findKeySerializer(Annotated annotated) {
            Class<? extends JsonSerializer<?>> clsFindKeySerializer = this._primary.findKeySerializer(annotated);
            if (clsFindKeySerializer == null || clsFindKeySerializer == JsonSerializer.None.class) {
                return this._secondary.findKeySerializer(annotated);
            }
            return clsFindKeySerializer;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public Class<? extends JsonSerializer<?>> findContentSerializer(Annotated annotated) {
            Class<? extends JsonSerializer<?>> clsFindContentSerializer = this._primary.findContentSerializer(annotated);
            if (clsFindContentSerializer == null || clsFindContentSerializer == JsonSerializer.None.class) {
                return this._secondary.findContentSerializer(annotated);
            }
            return clsFindContentSerializer;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public JsonSerialize.Inclusion findSerializationInclusion(Annotated annotated, JsonSerialize.Inclusion inclusion) {
            return this._primary.findSerializationInclusion(annotated, this._secondary.findSerializationInclusion(annotated, inclusion));
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public Class<?> findSerializationType(Annotated annotated) {
            Class<?> clsFindSerializationType = this._primary.findSerializationType(annotated);
            if (clsFindSerializationType == null) {
                return this._secondary.findSerializationType(annotated);
            }
            return clsFindSerializationType;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public Class<?> findSerializationKeyType(Annotated annotated, JavaType javaType) {
            Class<?> clsFindSerializationKeyType = this._primary.findSerializationKeyType(annotated, javaType);
            if (clsFindSerializationKeyType == null) {
                return this._secondary.findSerializationKeyType(annotated, javaType);
            }
            return clsFindSerializationKeyType;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public Class<?> findSerializationContentType(Annotated annotated, JavaType javaType) {
            Class<?> clsFindSerializationContentType = this._primary.findSerializationContentType(annotated, javaType);
            if (clsFindSerializationContentType == null) {
                return this._secondary.findSerializationContentType(annotated, javaType);
            }
            return clsFindSerializationContentType;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public JsonSerialize.Typing findSerializationTyping(Annotated annotated) {
            JsonSerialize.Typing typingFindSerializationTyping = this._primary.findSerializationTyping(annotated);
            if (typingFindSerializationTyping == null) {
                return this._secondary.findSerializationTyping(annotated);
            }
            return typingFindSerializationTyping;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public Class<?>[] findSerializationViews(Annotated annotated) {
            Class<?>[] clsArrFindSerializationViews = this._primary.findSerializationViews(annotated);
            if (clsArrFindSerializationViews == null) {
                return this._secondary.findSerializationViews(annotated);
            }
            return clsArrFindSerializationViews;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public String[] findSerializationPropertyOrder(AnnotatedClass annotatedClass) {
            String[] strArrFindSerializationPropertyOrder = this._primary.findSerializationPropertyOrder(annotatedClass);
            if (strArrFindSerializationPropertyOrder == null) {
                return this._secondary.findSerializationPropertyOrder(annotatedClass);
            }
            return strArrFindSerializationPropertyOrder;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public Boolean findSerializationSortAlphabetically(AnnotatedClass annotatedClass) {
            Boolean boolFindSerializationSortAlphabetically = this._primary.findSerializationSortAlphabetically(annotatedClass);
            if (boolFindSerializationSortAlphabetically == null) {
                return this._secondary.findSerializationSortAlphabetically(annotatedClass);
            }
            return boolFindSerializationSortAlphabetically;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public String findGettablePropertyName(AnnotatedMethod annotatedMethod) {
            String strFindGettablePropertyName;
            String strFindGettablePropertyName2 = this._primary.findGettablePropertyName(annotatedMethod);
            if (strFindGettablePropertyName2 == null) {
                return this._secondary.findGettablePropertyName(annotatedMethod);
            }
            return (strFindGettablePropertyName2.length() != 0 || (strFindGettablePropertyName = this._secondary.findGettablePropertyName(annotatedMethod)) == null) ? strFindGettablePropertyName2 : strFindGettablePropertyName;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public boolean hasAsValueAnnotation(AnnotatedMethod annotatedMethod) {
            return this._primary.hasAsValueAnnotation(annotatedMethod) || this._secondary.hasAsValueAnnotation(annotatedMethod);
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public String findEnumValue(Enum<?> r2) {
            String strFindEnumValue = this._primary.findEnumValue(r2);
            if (strFindEnumValue == null) {
                return this._secondary.findEnumValue(r2);
            }
            return strFindEnumValue;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public String findSerializablePropertyName(AnnotatedField annotatedField) {
            String strFindSerializablePropertyName;
            String strFindSerializablePropertyName2 = this._primary.findSerializablePropertyName(annotatedField);
            if (strFindSerializablePropertyName2 == null) {
                return this._secondary.findSerializablePropertyName(annotatedField);
            }
            return (strFindSerializablePropertyName2.length() != 0 || (strFindSerializablePropertyName = this._secondary.findSerializablePropertyName(annotatedField)) == null) ? strFindSerializablePropertyName2 : strFindSerializablePropertyName;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public Object findDeserializer(Annotated annotated) {
            Object objFindDeserializer = this._primary.findDeserializer(annotated);
            if (objFindDeserializer == null) {
                return this._secondary.findDeserializer(annotated);
            }
            return objFindDeserializer;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public Object findDeserializer(Annotated annotated, BeanProperty beanProperty) {
            Object objFindDeserializer = this._primary.findDeserializer(annotated, beanProperty);
            if (objFindDeserializer == null) {
                return this._secondary.findDeserializer(annotated, beanProperty);
            }
            return objFindDeserializer;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public Class<? extends KeyDeserializer> findKeyDeserializer(Annotated annotated) {
            Class<? extends KeyDeserializer> clsFindKeyDeserializer = this._primary.findKeyDeserializer(annotated);
            if (clsFindKeyDeserializer == null || clsFindKeyDeserializer == KeyDeserializer.None.class) {
                return this._secondary.findKeyDeserializer(annotated);
            }
            return clsFindKeyDeserializer;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public Class<? extends JsonDeserializer<?>> findContentDeserializer(Annotated annotated) {
            Class<? extends JsonDeserializer<?>> clsFindContentDeserializer = this._primary.findContentDeserializer(annotated);
            if (clsFindContentDeserializer == null || clsFindContentDeserializer == JsonDeserializer.None.class) {
                return this._secondary.findContentDeserializer(annotated);
            }
            return clsFindContentDeserializer;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public Class<?> findDeserializationType(Annotated annotated, JavaType javaType, String str) {
            Class<?> clsFindDeserializationType = this._primary.findDeserializationType(annotated, javaType, str);
            if (clsFindDeserializationType == null) {
                return this._secondary.findDeserializationType(annotated, javaType, str);
            }
            return clsFindDeserializationType;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public Class<?> findDeserializationKeyType(Annotated annotated, JavaType javaType, String str) {
            Class<?> clsFindDeserializationKeyType = this._primary.findDeserializationKeyType(annotated, javaType, str);
            if (clsFindDeserializationKeyType == null) {
                return this._secondary.findDeserializationKeyType(annotated, javaType, str);
            }
            return clsFindDeserializationKeyType;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public Class<?> findDeserializationContentType(Annotated annotated, JavaType javaType, String str) {
            Class<?> clsFindDeserializationContentType = this._primary.findDeserializationContentType(annotated, javaType, str);
            if (clsFindDeserializationContentType == null) {
                return this._secondary.findDeserializationContentType(annotated, javaType, str);
            }
            return clsFindDeserializationContentType;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public String findSettablePropertyName(AnnotatedMethod annotatedMethod) {
            String strFindSettablePropertyName;
            String strFindSettablePropertyName2 = this._primary.findSettablePropertyName(annotatedMethod);
            if (strFindSettablePropertyName2 == null) {
                return this._secondary.findSettablePropertyName(annotatedMethod);
            }
            return (strFindSettablePropertyName2.length() != 0 || (strFindSettablePropertyName = this._secondary.findSettablePropertyName(annotatedMethod)) == null) ? strFindSettablePropertyName2 : strFindSettablePropertyName;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public boolean hasAnySetterAnnotation(AnnotatedMethod annotatedMethod) {
            return this._primary.hasAnySetterAnnotation(annotatedMethod) || this._secondary.hasAnySetterAnnotation(annotatedMethod);
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public boolean hasAnyGetterAnnotation(AnnotatedMethod annotatedMethod) {
            return this._primary.hasAnyGetterAnnotation(annotatedMethod) || this._secondary.hasAnyGetterAnnotation(annotatedMethod);
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public boolean hasCreatorAnnotation(Annotated annotated) {
            return this._primary.hasCreatorAnnotation(annotated) || this._secondary.hasCreatorAnnotation(annotated);
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public String findDeserializablePropertyName(AnnotatedField annotatedField) {
            String strFindDeserializablePropertyName;
            String strFindDeserializablePropertyName2 = this._primary.findDeserializablePropertyName(annotatedField);
            if (strFindDeserializablePropertyName2 == null) {
                return this._secondary.findDeserializablePropertyName(annotatedField);
            }
            return (strFindDeserializablePropertyName2.length() != 0 || (strFindDeserializablePropertyName = this._secondary.findDeserializablePropertyName(annotatedField)) == null) ? strFindDeserializablePropertyName2 : strFindDeserializablePropertyName;
        }

        @Override // org.codehaus.jackson.map.AnnotationIntrospector
        public String findPropertyNameForParam(AnnotatedParameter annotatedParameter) {
            String strFindPropertyNameForParam = this._primary.findPropertyNameForParam(annotatedParameter);
            if (strFindPropertyNameForParam == null) {
                return this._secondary.findPropertyNameForParam(annotatedParameter);
            }
            return strFindPropertyNameForParam;
        }
    }
}
