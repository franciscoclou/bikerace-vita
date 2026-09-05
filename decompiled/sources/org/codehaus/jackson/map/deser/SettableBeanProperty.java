package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.introspect.AnnotatedField;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.AnnotatedParameter;
import org.codehaus.jackson.map.util.Annotations;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.util.InternCache;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class SettableBeanProperty implements BeanProperty {
    protected final Annotations _contextAnnotations;
    protected String _managedReferenceName;
    protected NullProvider _nullProvider;
    protected final String _propName;
    protected int _propertyIndex;
    protected final JavaType _type;
    protected JsonDeserializer<Object> _valueDeserializer;
    protected TypeDeserializer _valueTypeDeserializer;

    public abstract void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj);

    @Override // org.codehaus.jackson.map.BeanProperty
    public abstract <A extends Annotation> A getAnnotation(Class<A> cls);

    @Override // org.codehaus.jackson.map.BeanProperty
    public abstract AnnotatedMember getMember();

    public abstract void set(Object obj, Object obj2);

    protected SettableBeanProperty(String str, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations) {
        this._propertyIndex = -1;
        if (str == null || str.length() == 0) {
            this._propName = "";
        } else {
            this._propName = InternCache.instance.intern(str);
        }
        this._type = javaType;
        this._contextAnnotations = annotations;
        this._valueTypeDeserializer = typeDeserializer;
    }

    protected SettableBeanProperty(SettableBeanProperty settableBeanProperty) {
        this._propertyIndex = -1;
        this._propName = settableBeanProperty._propName;
        this._type = settableBeanProperty._type;
        this._contextAnnotations = settableBeanProperty._contextAnnotations;
        this._valueDeserializer = settableBeanProperty._valueDeserializer;
        this._valueTypeDeserializer = settableBeanProperty._valueTypeDeserializer;
        this._nullProvider = settableBeanProperty._nullProvider;
        this._managedReferenceName = settableBeanProperty._managedReferenceName;
        this._propertyIndex = settableBeanProperty._propertyIndex;
    }

    public void setValueDeserializer(JsonDeserializer<Object> jsonDeserializer) {
        if (this._valueDeserializer != null) {
            throw new IllegalStateException("Already had assigned deserializer for property '" + getName() + "' (class " + getDeclaringClass().getName() + ")");
        }
        this._valueDeserializer = jsonDeserializer;
        Object nullValue = this._valueDeserializer.getNullValue();
        this._nullProvider = nullValue == null ? null : new NullProvider(this._type, nullValue);
    }

    public void setManagedReferenceName(String str) {
        this._managedReferenceName = str;
    }

    public void assignIndex(int i) {
        if (this._propertyIndex != -1) {
            throw new IllegalStateException("Property '" + getName() + "' already had index (" + this._propertyIndex + "), trying to assign " + i);
        }
        this._propertyIndex = i;
    }

    @Override // org.codehaus.jackson.map.BeanProperty
    public final String getName() {
        return this._propName;
    }

    @Override // org.codehaus.jackson.map.BeanProperty
    public JavaType getType() {
        return this._type;
    }

    @Override // org.codehaus.jackson.map.BeanProperty
    public <A extends Annotation> A getContextAnnotation(Class<A> cls) {
        return (A) this._contextAnnotations.get(cls);
    }

    protected final Class<?> getDeclaringClass() {
        return getMember().getDeclaringClass();
    }

    @Deprecated
    public String getPropertyName() {
        return this._propName;
    }

    public String getManagedReferenceName() {
        return this._managedReferenceName;
    }

    public boolean hasValueDeserializer() {
        return this._valueDeserializer != null;
    }

    public JsonDeserializer<Object> getValueDeserializer() {
        return this._valueDeserializer;
    }

    public int getCreatorIndex() {
        return -1;
    }

    public int getPropertyIndex() {
        return this._propertyIndex;
    }

    @Deprecated
    public int getProperytIndex() {
        return this._propertyIndex;
    }

    public final Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            if (this._nullProvider == null) {
                return null;
            }
            return this._nullProvider.nullValue(deserializationContext);
        }
        if (this._valueTypeDeserializer != null) {
            return this._valueDeserializer.deserializeWithType(jsonParser, deserializationContext, this._valueTypeDeserializer);
        }
        return this._valueDeserializer.deserialize(jsonParser, deserializationContext);
    }

    protected void _throwAsIOE(Exception exc, Object obj) throws IOException {
        if (exc instanceof IllegalArgumentException) {
            String name = obj == null ? "[NULL]" : obj.getClass().getName();
            StringBuilder sbAppend = new StringBuilder("Problem deserializing property '").append(getPropertyName());
            sbAppend.append("' (expected type: ").append(getType());
            sbAppend.append("; actual type: ").append(name).append(")");
            String message = exc.getMessage();
            if (message != null) {
                sbAppend.append(", problem: ").append(message);
            } else {
                sbAppend.append(" (no error message provided)");
            }
            throw new JsonMappingException(sbAppend.toString(), null, exc);
        }
        _throwAsIOE(exc);
    }

    protected IOException _throwAsIOE(Exception exc) throws IOException {
        Throwable cause;
        if (exc instanceof IOException) {
            throw ((IOException) exc);
        }
        if (exc instanceof RuntimeException) {
            cause = exc;
            throw ((RuntimeException) exc);
        }
        while (true) {
            cause = exc;
            if (cause.getCause() != null) {
                cause = cause.getCause();
            } else {
                throw new JsonMappingException(cause.getMessage(), null, cause);
            }
        }
    }

    public String toString() {
        return "[property '" + getName() + "']";
    }

    public final class MethodProperty extends SettableBeanProperty {
        protected final AnnotatedMethod _annotated;
        protected final Method _setter;

        public MethodProperty(String str, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedMethod annotatedMethod) {
            super(str, javaType, typeDeserializer, annotations);
            this._annotated = annotatedMethod;
            this._setter = annotatedMethod.getAnnotated();
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty, org.codehaus.jackson.map.BeanProperty
        public <A extends Annotation> A getAnnotation(Class<A> cls) {
            return (A) this._annotated.getAnnotation(cls);
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty, org.codehaus.jackson.map.BeanProperty
        public AnnotatedMember getMember() {
            return this._annotated;
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
            set(obj, deserialize(jsonParser, deserializationContext));
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public final void set(Object obj, Object obj2) {
            try {
                this._setter.invoke(obj, obj2);
            } catch (Exception e) {
                _throwAsIOE(e, obj2);
            }
        }
    }

    public final class SetterlessProperty extends SettableBeanProperty {
        protected final AnnotatedMethod _annotated;
        protected final Method _getter;

        public SetterlessProperty(String str, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedMethod annotatedMethod) {
            super(str, javaType, typeDeserializer, annotations);
            this._annotated = annotatedMethod;
            this._getter = annotatedMethod.getAnnotated();
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty, org.codehaus.jackson.map.BeanProperty
        public <A extends Annotation> A getAnnotation(Class<A> cls) {
            return (A) this._annotated.getAnnotation(cls);
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty, org.codehaus.jackson.map.BeanProperty
        public AnnotatedMember getMember() {
            return this._annotated;
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public final void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws JsonMappingException {
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                try {
                    Object objInvoke = this._getter.invoke(obj, new Object[0]);
                    if (objInvoke == null) {
                        throw new JsonMappingException("Problem deserializing 'setterless' property '" + getName() + "': get method returned null");
                    }
                    this._valueDeserializer.deserialize(jsonParser, deserializationContext, objInvoke);
                } catch (Exception e) {
                    _throwAsIOE(e);
                }
            }
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public final void set(Object obj, Object obj2) {
            throw new UnsupportedOperationException("Should never call 'set' on setterless property");
        }
    }

    public final class FieldProperty extends SettableBeanProperty {
        protected final AnnotatedField _annotated;
        protected final Field _field;

        public FieldProperty(String str, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedField annotatedField) {
            super(str, javaType, typeDeserializer, annotations);
            this._annotated = annotatedField;
            this._field = annotatedField.getAnnotated();
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty, org.codehaus.jackson.map.BeanProperty
        public <A extends Annotation> A getAnnotation(Class<A> cls) {
            return (A) this._annotated.getAnnotation(cls);
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty, org.codehaus.jackson.map.BeanProperty
        public AnnotatedMember getMember() {
            return this._annotated;
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
            set(obj, deserialize(jsonParser, deserializationContext));
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public final void set(Object obj, Object obj2) {
            try {
                this._field.set(obj, obj2);
            } catch (Exception e) {
                _throwAsIOE(e, obj2);
            }
        }
    }

    public final class CreatorProperty extends SettableBeanProperty {
        protected final AnnotatedParameter _annotated;
        protected final int _index;

        public CreatorProperty(String str, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedParameter annotatedParameter, int i) {
            super(str, javaType, typeDeserializer, annotations);
            this._annotated = annotatedParameter;
            this._index = i;
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty, org.codehaus.jackson.map.BeanProperty
        public <A extends Annotation> A getAnnotation(Class<A> cls) {
            return (A) this._annotated.getAnnotation(cls);
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty, org.codehaus.jackson.map.BeanProperty
        public AnnotatedMember getMember() {
            return this._annotated;
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public int getCreatorIndex() {
            return this._index;
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
            set(obj, deserialize(jsonParser, deserializationContext));
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public void set(Object obj, Object obj2) {
        }
    }

    public final class ManagedReferenceProperty extends SettableBeanProperty {
        protected final SettableBeanProperty _backProperty;
        protected final boolean _isContainer;
        protected final SettableBeanProperty _managedProperty;
        protected final String _referenceName;

        public ManagedReferenceProperty(String str, SettableBeanProperty settableBeanProperty, SettableBeanProperty settableBeanProperty2, Annotations annotations, boolean z) {
            super(settableBeanProperty.getName(), settableBeanProperty.getType(), settableBeanProperty._valueTypeDeserializer, annotations);
            this._referenceName = str;
            this._managedProperty = settableBeanProperty;
            this._backProperty = settableBeanProperty2;
            this._isContainer = z;
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty, org.codehaus.jackson.map.BeanProperty
        public <A extends Annotation> A getAnnotation(Class<A> cls) {
            return (A) this._managedProperty.getAnnotation(cls);
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty, org.codehaus.jackson.map.BeanProperty
        public AnnotatedMember getMember() {
            return this._managedProperty.getMember();
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
            set(obj, this._managedProperty.deserialize(jsonParser, deserializationContext));
        }

        @Override // org.codehaus.jackson.map.deser.SettableBeanProperty
        public final void set(Object obj, Object obj2) {
            this._managedProperty.set(obj, obj2);
            if (obj2 != null) {
                if (this._isContainer) {
                    if (obj2 instanceof Object[]) {
                        for (Object obj3 : (Object[]) obj2) {
                            if (obj3 != null) {
                                this._backProperty.set(obj3, obj);
                            }
                        }
                        return;
                    }
                    if (obj2 instanceof Collection) {
                        for (Object obj4 : (Collection) obj2) {
                            if (obj4 != null) {
                                this._backProperty.set(obj4, obj);
                            }
                        }
                        return;
                    }
                    if (obj2 instanceof Map) {
                        for (Object obj5 : ((Map) obj2).values()) {
                            if (obj5 != null) {
                                this._backProperty.set(obj5, obj);
                            }
                        }
                        return;
                    }
                    throw new IllegalStateException("Unsupported container type (" + obj2.getClass().getName() + ") when resolving reference '" + this._referenceName + "'");
                }
                this._backProperty.set(obj2, obj);
            }
        }
    }

    public final class NullProvider {
        private final boolean _isPrimitive;
        private final Object _nullValue;
        private final Class<?> _rawType;

        protected NullProvider(JavaType javaType, Object obj) {
            this._nullValue = obj;
            this._isPrimitive = javaType.isPrimitive();
            this._rawType = javaType.getRawClass();
        }

        public Object nullValue(DeserializationContext deserializationContext) throws JsonMappingException {
            if (this._isPrimitive && deserializationContext.isEnabled(DeserializationConfig.Feature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
                throw deserializationContext.mappingException("Can not map JSON null into type " + this._rawType.getName() + " (set DeserializationConfig.Feature.FAIL_ON_NULL_FOR_PRIMITIVES to 'false' to allow)");
            }
            return this._nullValue;
        }
    }
}
