package org.codehaus.jackson.map.ser;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializerFactory;
import org.codehaus.jackson.map.type.ClassKey;
import org.codehaus.jackson.type.JavaType;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class CustomSerializerFactory extends BeanSerializerFactory {
    protected HashMap<ClassKey, JsonSerializer<?>> _directClassMappings;
    protected JsonSerializer<?> _enumSerializerOverride;
    protected HashMap<ClassKey, JsonSerializer<?>> _interfaceMappings;
    protected HashMap<ClassKey, JsonSerializer<?>> _transitiveClassMappings;

    public CustomSerializerFactory() {
        this(null);
    }

    public CustomSerializerFactory(SerializerFactory.Config config) {
        super(config);
        this._directClassMappings = null;
        this._transitiveClassMappings = null;
        this._interfaceMappings = null;
    }

    @Override // org.codehaus.jackson.map.ser.BeanSerializerFactory, org.codehaus.jackson.map.SerializerFactory
    public SerializerFactory withConfig(SerializerFactory.Config config) {
        if (getClass() != CustomSerializerFactory.class) {
            throw new IllegalStateException("Subtype of CustomSerializerFactory (" + getClass().getName() + ") has not properly overridden method 'withAdditionalSerializers': can not instantiate subtype with additional serializer definitions");
        }
        return new CustomSerializerFactory(config);
    }

    public <T> void addGenericMapping(Class<? extends T> cls, JsonSerializer<T> jsonSerializer) {
        ClassKey classKey = new ClassKey(cls);
        if (cls.isInterface()) {
            if (this._interfaceMappings == null) {
                this._interfaceMappings = new HashMap<>();
            }
            this._interfaceMappings.put(classKey, jsonSerializer);
        } else {
            if (this._transitiveClassMappings == null) {
                this._transitiveClassMappings = new HashMap<>();
            }
            this._transitiveClassMappings.put(classKey, jsonSerializer);
        }
    }

    public <T> void addSpecificMapping(Class<? extends T> cls, JsonSerializer<T> jsonSerializer) {
        ClassKey classKey = new ClassKey(cls);
        if (cls.isInterface()) {
            throw new IllegalArgumentException("Can not add specific mapping for an interface (" + cls.getName() + ")");
        }
        if (Modifier.isAbstract(cls.getModifiers())) {
            throw new IllegalArgumentException("Can not add specific mapping for an abstract class (" + cls.getName() + ")");
        }
        if (this._directClassMappings == null) {
            this._directClassMappings = new HashMap<>();
        }
        this._directClassMappings.put(classKey, jsonSerializer);
    }

    public void setEnumSerializer(JsonSerializer<?> jsonSerializer) {
        this._enumSerializerOverride = jsonSerializer;
    }

    @Override // org.codehaus.jackson.map.ser.BeanSerializerFactory, org.codehaus.jackson.map.ser.BasicSerializerFactory, org.codehaus.jackson.map.SerializerFactory
    public JsonSerializer<Object> createSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanProperty beanProperty) {
        JsonSerializer<?> jsonSerializerFindCustomSerializer = findCustomSerializer(javaType.getRawClass(), serializationConfig);
        return jsonSerializerFindCustomSerializer != null ? jsonSerializerFindCustomSerializer : super.createSerializer(serializationConfig, javaType, beanProperty);
    }

    protected JsonSerializer<?> findCustomSerializer(Class<?> cls, SerializationConfig serializationConfig) {
        JsonSerializer<?> jsonSerializer;
        ClassKey classKey = new ClassKey(cls);
        if (this._directClassMappings == null || (jsonSerializer = this._directClassMappings.get(classKey)) == null) {
            if (cls.isEnum() && this._enumSerializerOverride != null) {
                return this._enumSerializerOverride;
            }
            if (this._transitiveClassMappings != null) {
                for (Class<?> superclass = cls; superclass != null; superclass = superclass.getSuperclass()) {
                    classKey.reset(superclass);
                    JsonSerializer<?> jsonSerializer2 = this._transitiveClassMappings.get(classKey);
                    if (jsonSerializer2 != null) {
                        return jsonSerializer2;
                    }
                }
            }
            if (this._interfaceMappings != null) {
                classKey.reset(cls);
                JsonSerializer<?> jsonSerializer3 = this._interfaceMappings.get(classKey);
                if (jsonSerializer3 == null) {
                    while (cls != null) {
                        JsonSerializer<?> jsonSerializer_findInterfaceMapping = _findInterfaceMapping(cls, classKey);
                        if (jsonSerializer_findInterfaceMapping == null) {
                            cls = cls.getSuperclass();
                        } else {
                            return jsonSerializer_findInterfaceMapping;
                        }
                    }
                } else {
                    return jsonSerializer3;
                }
            }
            return null;
        }
        return jsonSerializer;
    }

    protected JsonSerializer<?> _findInterfaceMapping(Class<?> cls, ClassKey classKey) {
        for (Class<?> cls2 : cls.getInterfaces()) {
            classKey.reset(cls2);
            JsonSerializer<?> jsonSerializer = this._interfaceMappings.get(classKey);
            if (jsonSerializer == null) {
                JsonSerializer<?> jsonSerializer_findInterfaceMapping = _findInterfaceMapping(cls2, classKey);
                if (jsonSerializer_findInterfaceMapping != null) {
                    return jsonSerializer_findInterfaceMapping;
                }
            } else {
                return jsonSerializer;
            }
        }
        return null;
    }
}
