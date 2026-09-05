package org.codehaus.jackson.map.module;

import java.util.HashMap;
import org.codehaus.jackson.map.BeanDescription;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.Serializers;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.type.ArrayType;
import org.codehaus.jackson.map.type.ClassKey;
import org.codehaus.jackson.map.type.CollectionLikeType;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.MapLikeType;
import org.codehaus.jackson.map.type.MapType;
import org.codehaus.jackson.type.JavaType;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class SimpleSerializers implements Serializers {
    protected HashMap<ClassKey, JsonSerializer<?>> _classMappings = null;
    protected HashMap<ClassKey, JsonSerializer<?>> _interfaceMappings = null;

    public void addSerializer(JsonSerializer<?> jsonSerializer) {
        Class<?> clsHandledType = jsonSerializer.handledType();
        if (clsHandledType == null || clsHandledType == Object.class) {
            throw new IllegalArgumentException("JsonSerializer of type " + jsonSerializer.getClass().getName() + " does not define valid handledType() -- must either register with method that takes type argument  or make serializer extend 'org.codehaus.jackson.map.ser.std.SerializerBase'");
        }
        _addSerializer(clsHandledType, jsonSerializer);
    }

    public <T> void addSerializer(Class<? extends T> cls, JsonSerializer<T> jsonSerializer) {
        _addSerializer(cls, jsonSerializer);
    }

    private void _addSerializer(Class<?> cls, JsonSerializer<?> jsonSerializer) {
        ClassKey classKey = new ClassKey(cls);
        if (cls.isInterface()) {
            if (this._interfaceMappings == null) {
                this._interfaceMappings = new HashMap<>();
            }
            this._interfaceMappings.put(classKey, jsonSerializer);
        } else {
            if (this._classMappings == null) {
                this._classMappings = new HashMap<>();
            }
            this._classMappings.put(classKey, jsonSerializer);
        }
    }

    @Override // org.codehaus.jackson.map.Serializers
    public JsonSerializer<?> findSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription, BeanProperty beanProperty) {
        JsonSerializer<?> jsonSerializer_findInterfaceMapping;
        JsonSerializer<?> jsonSerializer;
        Class<?> rawClass = javaType.getRawClass();
        ClassKey classKey = new ClassKey(rawClass);
        if (rawClass.isInterface()) {
            if (this._interfaceMappings != null && (jsonSerializer = this._interfaceMappings.get(classKey)) != null) {
                return jsonSerializer;
            }
        } else if (this._classMappings != null) {
            JsonSerializer<?> jsonSerializer2 = this._classMappings.get(classKey);
            if (jsonSerializer2 == null) {
                for (Class<?> superclass = rawClass; superclass != null; superclass = superclass.getSuperclass()) {
                    classKey.reset(superclass);
                    JsonSerializer<?> jsonSerializer3 = this._classMappings.get(classKey);
                    if (jsonSerializer3 != null) {
                        return jsonSerializer3;
                    }
                }
            } else {
                return jsonSerializer2;
            }
        }
        if (this._interfaceMappings != null) {
            JsonSerializer<?> jsonSerializer_findInterfaceMapping2 = _findInterfaceMapping(rawClass, classKey);
            if (jsonSerializer_findInterfaceMapping2 == null) {
                if (!rawClass.isInterface()) {
                    Class<?> superclass2 = rawClass;
                    do {
                        superclass2 = superclass2.getSuperclass();
                        if (superclass2 != null) {
                            jsonSerializer_findInterfaceMapping = _findInterfaceMapping(superclass2, classKey);
                        }
                    } while (jsonSerializer_findInterfaceMapping == null);
                    return jsonSerializer_findInterfaceMapping;
                }
            } else {
                return jsonSerializer_findInterfaceMapping2;
            }
        }
        return null;
    }

    @Override // org.codehaus.jackson.map.Serializers
    public JsonSerializer<?> findArraySerializer(SerializationConfig serializationConfig, ArrayType arrayType, BeanDescription beanDescription, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        return findSerializer(serializationConfig, arrayType, beanDescription, beanProperty);
    }

    @Override // org.codehaus.jackson.map.Serializers
    public JsonSerializer<?> findCollectionSerializer(SerializationConfig serializationConfig, CollectionType collectionType, BeanDescription beanDescription, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        return findSerializer(serializationConfig, collectionType, beanDescription, beanProperty);
    }

    @Override // org.codehaus.jackson.map.Serializers
    public JsonSerializer<?> findCollectionLikeSerializer(SerializationConfig serializationConfig, CollectionLikeType collectionLikeType, BeanDescription beanDescription, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        return findSerializer(serializationConfig, collectionLikeType, beanDescription, beanProperty);
    }

    @Override // org.codehaus.jackson.map.Serializers
    public JsonSerializer<?> findMapSerializer(SerializationConfig serializationConfig, MapType mapType, BeanDescription beanDescription, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer2) {
        return findSerializer(serializationConfig, mapType, beanDescription, beanProperty);
    }

    @Override // org.codehaus.jackson.map.Serializers
    public JsonSerializer<?> findMapLikeSerializer(SerializationConfig serializationConfig, MapLikeType mapLikeType, BeanDescription beanDescription, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer2) {
        return findSerializer(serializationConfig, mapLikeType, beanDescription, beanProperty);
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
