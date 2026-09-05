package org.codehaus.jackson.map.ser;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.introspect.AnnotatedField;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.util.Annotations;
import org.codehaus.jackson.type.JavaType;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class PropertyBuilder {
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final BasicBeanDescription _beanDesc;
    protected final SerializationConfig _config;
    protected Object _defaultBean;
    protected final JsonSerialize.Inclusion _outputProps;

    public PropertyBuilder(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription) {
        this._config = serializationConfig;
        this._beanDesc = basicBeanDescription;
        this._outputProps = basicBeanDescription.findSerializationInclusion(serializationConfig.getSerializationInclusion());
        this._annotationIntrospector = this._config.getAnnotationIntrospector();
    }

    public Annotations getClassAnnotations() {
        return this._beanDesc.getClassAnnotations();
    }

    protected BeanPropertyWriter buildWriter(String str, JavaType javaType, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, TypeSerializer typeSerializer2, AnnotatedMember annotatedMember, boolean z) {
        Method annotated;
        Field annotated2;
        JavaType javaTypeWithContentTypeHandler;
        if (annotatedMember instanceof AnnotatedField) {
            annotated = null;
            annotated2 = ((AnnotatedField) annotatedMember).getAnnotated();
        } else {
            annotated = ((AnnotatedMethod) annotatedMember).getAnnotated();
            annotated2 = null;
        }
        JavaType javaTypeFindSerializationType = findSerializationType(annotatedMember, z, javaType);
        if (typeSerializer2 != null) {
            if (javaTypeFindSerializationType == null) {
                javaTypeFindSerializationType = javaType;
            }
            if (javaTypeFindSerializationType.getContentType() == null) {
                throw new IllegalStateException("Problem trying to create BeanPropertyWriter for property '" + str + "' (of type " + this._beanDesc.getType() + "); serialization type " + javaTypeFindSerializationType + " has no content");
            }
            javaTypeWithContentTypeHandler = javaTypeFindSerializationType.withContentTypeHandler(typeSerializer2);
            javaTypeWithContentTypeHandler.getContentType();
        } else {
            javaTypeWithContentTypeHandler = javaTypeFindSerializationType;
        }
        Object defaultValue = null;
        boolean z2 = false;
        JsonSerialize.Inclusion inclusionFindSerializationInclusion = this._annotationIntrospector.findSerializationInclusion(annotatedMember, this._outputProps);
        if (inclusionFindSerializationInclusion != null) {
            switch (inclusionFindSerializationInclusion) {
                case NON_DEFAULT:
                    defaultValue = getDefaultValue(str, annotated, annotated2);
                    if (defaultValue == null) {
                        z2 = true;
                    }
                    break;
                case NON_NULL:
                    z2 = true;
                    break;
            }
        }
        return new BeanPropertyWriter(annotatedMember, this._beanDesc.getClassAnnotations(), str, javaType, jsonSerializer, typeSerializer, javaTypeWithContentTypeHandler, annotated, annotated2, z2, defaultValue);
    }

    protected JavaType findSerializationType(Annotated annotated, boolean z, JavaType javaType) {
        JavaType javaTypeForcedNarrowBy;
        boolean z2;
        JsonSerialize.Typing typingFindSerializationTyping;
        boolean z3 = true;
        Class<?> clsFindSerializationType = this._annotationIntrospector.findSerializationType(annotated);
        if (clsFindSerializationType != null) {
            Class<?> rawClass = javaType.getRawClass();
            if (clsFindSerializationType.isAssignableFrom(rawClass)) {
                javaTypeForcedNarrowBy = javaType.widenBy(clsFindSerializationType);
            } else {
                if (!rawClass.isAssignableFrom(clsFindSerializationType)) {
                    throw new IllegalArgumentException("Illegal concrete-type annotation for method '" + annotated.getName() + "': class " + clsFindSerializationType.getName() + " not a super-type of (declared) class " + rawClass.getName());
                }
                javaTypeForcedNarrowBy = javaType.forcedNarrowBy(clsFindSerializationType);
            }
            z = true;
        } else {
            javaTypeForcedNarrowBy = javaType;
        }
        JavaType javaTypeModifySecondaryTypesByAnnotation = BeanSerializerFactory.modifySecondaryTypesByAnnotation(this._config, annotated, javaTypeForcedNarrowBy);
        if (javaTypeModifySecondaryTypesByAnnotation != javaTypeForcedNarrowBy) {
            javaTypeForcedNarrowBy = javaTypeModifySecondaryTypesByAnnotation;
            z2 = true;
        } else {
            z2 = z;
        }
        if (z2 || (typingFindSerializationTyping = this._annotationIntrospector.findSerializationTyping(annotated)) == null) {
            z3 = z2;
        } else if (typingFindSerializationTyping != JsonSerialize.Typing.STATIC) {
            z3 = false;
        }
        if (z3) {
            return javaTypeForcedNarrowBy;
        }
        return null;
    }

    protected Object getDefaultBean() {
        if (this._defaultBean == null) {
            this._defaultBean = this._beanDesc.instantiateBean(this._config.isEnabled(SerializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS));
            if (this._defaultBean == null) {
                throw new IllegalArgumentException("Class " + this._beanDesc.getClassInfo().getAnnotated().getName() + " has no default constructor; can not instantiate default bean value to support 'properties=JsonSerialize.Inclusion.NON_DEFAULT' annotation");
            }
        }
        return this._defaultBean;
    }

    protected Object getDefaultValue(String str, Method method, Field field) {
        Object objInvoke;
        Object defaultBean = getDefaultBean();
        try {
            if (method != null) {
                objInvoke = method.invoke(defaultBean, new Object[0]);
            } else {
                objInvoke = field.get(defaultBean);
            }
            return objInvoke;
        } catch (Exception e) {
            return _throwWrapped(e, str, defaultBean);
        }
    }

    protected Object _throwWrapped(Exception exc, String str, Object obj) {
        Throwable cause = exc;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }
        if (cause instanceof Error) {
            throw ((Error) cause);
        }
        if (cause instanceof RuntimeException) {
            throw ((RuntimeException) cause);
        }
        throw new IllegalArgumentException("Failed to get property '" + str + "' of default " + obj.getClass().getName() + " instance");
    }
}
