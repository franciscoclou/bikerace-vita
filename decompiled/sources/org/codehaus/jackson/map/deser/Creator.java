package org.codehaus.jackson.map.deser;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.type.TypeBindings;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.type.JavaType;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
abstract class Creator {
    private Creator() {
    }

    final class StringBased {
        protected final Constructor<?> _ctor;
        protected final Method _factoryMethod;
        protected final Class<?> _valueClass;

        public StringBased(Class<?> cls, AnnotatedConstructor annotatedConstructor, AnnotatedMethod annotatedMethod) {
            this._valueClass = cls;
            this._ctor = annotatedConstructor == null ? null : annotatedConstructor.getAnnotated();
            this._factoryMethod = annotatedMethod != null ? annotatedMethod.getAnnotated() : null;
        }

        /* JADX WARN: Code restructure failed: missing block: B:16:0x0010, code lost:
        
            r0 = null;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Object construct(String str) {
            Object objInvoke;
            try {
                if (this._ctor != null) {
                    objInvoke = this._ctor.newInstance(str);
                } else {
                    objInvoke = this._factoryMethod != null ? this._factoryMethod.invoke(this._valueClass, str) : null;
                }
            } catch (Exception e) {
                ClassUtil.unwrapAndThrowAsIAE(e);
            }
            return objInvoke;
        }
    }

    final class NumberBased {
        protected final Constructor<?> _intCtor;
        protected final Method _intFactoryMethod;
        protected final Constructor<?> _longCtor;
        protected final Method _longFactoryMethod;
        protected final Class<?> _valueClass;

        public NumberBased(Class<?> cls, AnnotatedConstructor annotatedConstructor, AnnotatedMethod annotatedMethod, AnnotatedConstructor annotatedConstructor2, AnnotatedMethod annotatedMethod2) {
            this._valueClass = cls;
            this._intCtor = annotatedConstructor == null ? null : annotatedConstructor.getAnnotated();
            this._longCtor = annotatedConstructor2 == null ? null : annotatedConstructor2.getAnnotated();
            this._intFactoryMethod = annotatedMethod == null ? null : annotatedMethod.getAnnotated();
            this._longFactoryMethod = annotatedMethod2 != null ? annotatedMethod2.getAnnotated() : null;
        }

        /* JADX WARN: Code restructure failed: missing block: B:16:0x0014, code lost:
        
            r0 = construct(r6);
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Object construct(int i) {
            Object objConstruct;
            try {
                if (this._intCtor != null) {
                    objConstruct = this._intCtor.newInstance(Integer.valueOf(i));
                } else {
                    objConstruct = this._intFactoryMethod != null ? this._intFactoryMethod.invoke(this._valueClass, Integer.valueOf(i)) : construct(i);
                }
            } catch (Exception e) {
                ClassUtil.unwrapAndThrowAsIAE(e);
            }
            return objConstruct;
        }

        /* JADX WARN: Code restructure failed: missing block: B:16:0x0014, code lost:
        
            r0 = null;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Object construct(long j) {
            Object objInvoke;
            try {
                if (this._longCtor != null) {
                    objInvoke = this._longCtor.newInstance(Long.valueOf(j));
                } else {
                    objInvoke = this._longFactoryMethod != null ? this._longFactoryMethod.invoke(this._valueClass, Long.valueOf(j)) : null;
                }
            } catch (Exception e) {
                ClassUtil.unwrapAndThrowAsIAE(e);
            }
            return objInvoke;
        }
    }

    final class Delegating {
        protected final AnnotatedMember _creator;
        protected final Constructor<?> _ctor;
        protected JsonDeserializer<Object> _deserializer;
        protected final Method _factoryMethod;
        protected final JavaType _valueType;

        public Delegating(BasicBeanDescription basicBeanDescription, AnnotatedConstructor annotatedConstructor, AnnotatedMethod annotatedMethod) {
            TypeBindings typeBindingsBindingsForBeanType = basicBeanDescription.bindingsForBeanType();
            if (annotatedConstructor != null) {
                this._creator = annotatedConstructor;
                this._ctor = annotatedConstructor.getAnnotated();
                this._factoryMethod = null;
                this._valueType = typeBindingsBindingsForBeanType.resolveType(annotatedConstructor.getParameterType(0));
                return;
            }
            if (annotatedMethod != null) {
                this._creator = annotatedMethod;
                this._ctor = null;
                this._factoryMethod = annotatedMethod.getAnnotated();
                this._valueType = typeBindingsBindingsForBeanType.resolveType(annotatedMethod.getParameterType(0));
                return;
            }
            throw new IllegalArgumentException("Internal error: neither delegating constructor nor factory method passed");
        }

        public JavaType getValueType() {
            return this._valueType;
        }

        public AnnotatedMember getCreator() {
            return this._creator;
        }

        public void setDeserializer(JsonDeserializer<Object> jsonDeserializer) {
            this._deserializer = jsonDeserializer;
        }

        public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            Object objInvoke = null;
            Object objDeserialize = this._deserializer.deserialize(jsonParser, deserializationContext);
            try {
                if (this._ctor != null) {
                    objInvoke = this._ctor.newInstance(objDeserialize);
                } else {
                    objInvoke = this._factoryMethod.invoke(null, objDeserialize);
                }
            } catch (Exception e) {
                ClassUtil.unwrapAndThrowAsIAE(e);
            }
            return objInvoke;
        }
    }

    final class PropertyBased {
        protected final Constructor<?> _ctor;
        protected final Object[] _defaultValues;
        protected final Method _factoryMethod;
        protected final HashMap<String, SettableBeanProperty> _properties;

        public PropertyBased(AnnotatedConstructor annotatedConstructor, SettableBeanProperty[] settableBeanPropertyArr, AnnotatedMethod annotatedMethod, SettableBeanProperty[] settableBeanPropertyArr2) {
            if (annotatedConstructor != null) {
                this._ctor = annotatedConstructor.getAnnotated();
                this._factoryMethod = null;
            } else if (annotatedMethod != null) {
                this._ctor = null;
                this._factoryMethod = annotatedMethod.getAnnotated();
                settableBeanPropertyArr = settableBeanPropertyArr2;
            } else {
                throw new IllegalArgumentException("Internal error: neither delegating constructor nor factory method passed");
            }
            this._properties = new HashMap<>();
            int length = settableBeanPropertyArr.length;
            Object[] objArr = null;
            for (int i = 0; i < length; i++) {
                SettableBeanProperty settableBeanProperty = settableBeanPropertyArr[i];
                this._properties.put(settableBeanProperty.getName(), settableBeanProperty);
                if (settableBeanProperty.getType().isPrimitive()) {
                    objArr = objArr == null ? new Object[length] : objArr;
                    objArr[i] = ClassUtil.defaultValue(settableBeanProperty.getType().getRawClass());
                }
            }
            this._defaultValues = objArr;
        }

        public Collection<SettableBeanProperty> properties() {
            return this._properties.values();
        }

        public SettableBeanProperty findCreatorProperty(String str) {
            return this._properties.get(str);
        }

        public PropertyValueBuffer startBuilding(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return new PropertyValueBuffer(jsonParser, deserializationContext, this._properties.size());
        }

        public Object build(PropertyValueBuffer propertyValueBuffer) {
            Object objInvoke = null;
            try {
                if (this._ctor != null) {
                    objInvoke = this._ctor.newInstance(propertyValueBuffer.getParameters(this._defaultValues));
                } else {
                    objInvoke = this._factoryMethod.invoke(null, propertyValueBuffer.getParameters(this._defaultValues));
                }
                for (PropertyValue propertyValueBuffered = propertyValueBuffer.buffered(); propertyValueBuffered != null; propertyValueBuffered = propertyValueBuffered.next) {
                    propertyValueBuffered.assign(objInvoke);
                }
            } catch (Exception e) {
                ClassUtil.throwRootCause(e);
            }
            return objInvoke;
        }
    }
}
