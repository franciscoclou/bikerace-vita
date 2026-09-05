package org.codehaus.jackson.map.introspect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Map;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.BeanDescription;
import org.codehaus.jackson.map.ClassIntrospector;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.type.JavaType;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class BasicClassIntrospector extends ClassIntrospector<BasicBeanDescription> {
    public static final GetterMethodFilter DEFAULT_GETTER_FILTER = new GetterMethodFilter();
    public static final SetterMethodFilter DEFAULT_SETTER_FILTER = new SetterMethodFilter();
    public static final SetterAndGetterMethodFilter DEFAULT_SETTER_AND_GETTER_FILTER = new SetterAndGetterMethodFilter();
    public static final BasicClassIntrospector instance = new BasicClassIntrospector();

    @Override // org.codehaus.jackson.map.ClassIntrospector
    public /* bridge */ /* synthetic */ BeanDescription forClassAnnotations(MapperConfig mapperConfig, Class cls, ClassIntrospector.MixInResolver mixInResolver) {
        return forClassAnnotations((MapperConfig<?>) mapperConfig, (Class<?>) cls, mixInResolver);
    }

    @Override // org.codehaus.jackson.map.ClassIntrospector
    public /* bridge */ /* synthetic */ BeanDescription forDirectClassAnnotations(MapperConfig mapperConfig, Class cls, ClassIntrospector.MixInResolver mixInResolver) {
        return forDirectClassAnnotations((MapperConfig<?>) mapperConfig, (Class<?>) cls, mixInResolver);
    }

    public class GetterMethodFilter implements MethodFilter {
        private GetterMethodFilter() {
        }

        @Override // org.codehaus.jackson.map.introspect.MethodFilter
        public boolean includeMethod(Method method) {
            return ClassUtil.hasGetterSignature(method);
        }
    }

    public class SetterMethodFilter implements MethodFilter {
        @Override // org.codehaus.jackson.map.introspect.MethodFilter
        public boolean includeMethod(Method method) {
            if (Modifier.isStatic(method.getModifiers())) {
                return false;
            }
            switch (method.getParameterTypes().length) {
                case 1:
                    return true;
                case 2:
                    return true;
                default:
                    return false;
            }
        }
    }

    public final class SetterAndGetterMethodFilter extends SetterMethodFilter {
        @Override // org.codehaus.jackson.map.introspect.BasicClassIntrospector.SetterMethodFilter, org.codehaus.jackson.map.introspect.MethodFilter
        public boolean includeMethod(Method method) {
            if (super.includeMethod(method)) {
                return true;
            }
            if (!ClassUtil.hasGetterSignature(method)) {
                return false;
            }
            Class<?> returnType = method.getReturnType();
            return Collection.class.isAssignableFrom(returnType) || Map.class.isAssignableFrom(returnType);
        }
    }

    @Override // org.codehaus.jackson.map.ClassIntrospector
    public BasicBeanDescription forSerialization(SerializationConfig serializationConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        boolean zIsAnnotationProcessingEnabled = serializationConfig.isAnnotationProcessingEnabled();
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        Class<?> rawClass = javaType.getRawClass();
        if (!zIsAnnotationProcessingEnabled) {
            annotationIntrospector = null;
        }
        AnnotatedClass annotatedClassConstruct = AnnotatedClass.construct(rawClass, annotationIntrospector, mixInResolver);
        annotatedClassConstruct.resolveMemberMethods(getSerializationMethodFilter(serializationConfig), false);
        annotatedClassConstruct.resolveCreators(true);
        annotatedClassConstruct.resolveFields(false);
        return new BasicBeanDescription(serializationConfig, javaType, annotatedClassConstruct);
    }

    @Override // org.codehaus.jackson.map.ClassIntrospector
    public BasicBeanDescription forDeserialization(DeserializationConfig deserializationConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        boolean zIsAnnotationProcessingEnabled = deserializationConfig.isAnnotationProcessingEnabled();
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        Class<?> rawClass = javaType.getRawClass();
        if (!zIsAnnotationProcessingEnabled) {
            annotationIntrospector = null;
        }
        AnnotatedClass annotatedClassConstruct = AnnotatedClass.construct(rawClass, annotationIntrospector, mixInResolver);
        annotatedClassConstruct.resolveMemberMethods(getDeserializationMethodFilter(deserializationConfig), true);
        annotatedClassConstruct.resolveCreators(true);
        annotatedClassConstruct.resolveFields(true);
        return new BasicBeanDescription(deserializationConfig, javaType, annotatedClassConstruct);
    }

    @Override // org.codehaus.jackson.map.ClassIntrospector
    public BasicBeanDescription forCreation(DeserializationConfig deserializationConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        boolean zIsAnnotationProcessingEnabled = deserializationConfig.isAnnotationProcessingEnabled();
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        Class<?> rawClass = javaType.getRawClass();
        if (!zIsAnnotationProcessingEnabled) {
            annotationIntrospector = null;
        }
        AnnotatedClass annotatedClassConstruct = AnnotatedClass.construct(rawClass, annotationIntrospector, mixInResolver);
        annotatedClassConstruct.resolveCreators(true);
        return new BasicBeanDescription(deserializationConfig, javaType, annotatedClassConstruct);
    }

    @Override // org.codehaus.jackson.map.ClassIntrospector
    public BasicBeanDescription forClassAnnotations(MapperConfig<?> mapperConfig, Class<?> cls, ClassIntrospector.MixInResolver mixInResolver) {
        boolean zIsAnnotationProcessingEnabled = mapperConfig.isAnnotationProcessingEnabled();
        AnnotationIntrospector annotationIntrospector = mapperConfig.getAnnotationIntrospector();
        if (!zIsAnnotationProcessingEnabled) {
            annotationIntrospector = null;
        }
        return new BasicBeanDescription(mapperConfig, mapperConfig.constructType(cls), AnnotatedClass.construct(cls, annotationIntrospector, mixInResolver));
    }

    @Override // org.codehaus.jackson.map.ClassIntrospector
    public BasicBeanDescription forDirectClassAnnotations(MapperConfig<?> mapperConfig, Class<?> cls, ClassIntrospector.MixInResolver mixInResolver) {
        boolean zIsAnnotationProcessingEnabled = mapperConfig.isAnnotationProcessingEnabled();
        AnnotationIntrospector annotationIntrospector = mapperConfig.getAnnotationIntrospector();
        if (!zIsAnnotationProcessingEnabled) {
            annotationIntrospector = null;
        }
        return new BasicBeanDescription(mapperConfig, mapperConfig.constructType(cls), AnnotatedClass.constructWithoutSuperTypes(cls, annotationIntrospector, mixInResolver));
    }

    protected MethodFilter getSerializationMethodFilter(SerializationConfig serializationConfig) {
        return DEFAULT_GETTER_FILTER;
    }

    protected MethodFilter getDeserializationMethodFilter(DeserializationConfig deserializationConfig) {
        return deserializationConfig.isEnabled(DeserializationConfig.Feature.USE_GETTERS_AS_SETTERS) ? DEFAULT_SETTER_AND_GETTER_FILTER : DEFAULT_SETTER_FILTER;
    }
}
