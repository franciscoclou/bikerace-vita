package org.codehaus.jackson.map.ext;

import java.util.Collection;
import java.util.Map;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.deser.StdDeserializer;
import org.codehaus.jackson.map.util.Provider;
import org.codehaus.jackson.type.JavaType;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class OptionalHandlerFactory {
    private static final String CLASS_NAME_DOM_DOCUMENT = "org.w3c.dom.Node";
    private static final String CLASS_NAME_DOM_NODE = "org.w3c.dom.Node";
    private static final String DESERIALIZERS_FOR_JAVAX_XML = "org.codehaus.jackson.map.ext.CoreXMLDeserializers";
    private static final String DESERIALIZERS_FOR_JODA_DATETIME = "org.codehaus.jackson.map.ext.JodaDeserializers";
    private static final String DESERIALIZER_FOR_DOM_DOCUMENT = "org.codehaus.jackson.map.ext.DOMDeserializer$DocumentDeserializer";
    private static final String DESERIALIZER_FOR_DOM_NODE = "org.codehaus.jackson.map.ext.DOMDeserializer$NodeDeserializer";
    private static final String PACKAGE_PREFIX_JAVAX_XML = "javax.xml.";
    private static final String PACKAGE_PREFIX_JODA_DATETIME = "org.joda.time.";
    private static final String SERIALIZERS_FOR_JAVAX_XML = "org.codehaus.jackson.map.ext.CoreXMLSerializers";
    private static final String SERIALIZERS_FOR_JODA_DATETIME = "org.codehaus.jackson.map.ext.JodaSerializers";
    private static final String SERIALIZER_FOR_DOM_NODE = "org.codehaus.jackson.map.ext.DOMSerializer";
    public static final OptionalHandlerFactory instance = new OptionalHandlerFactory();

    protected OptionalHandlerFactory() {
    }

    public JsonSerializer<?> findSerializer(SerializationConfig serializationConfig, JavaType javaType) {
        String str;
        Class<?> rawClass = javaType.getRawClass();
        String name = rawClass.getName();
        if (name.startsWith(PACKAGE_PREFIX_JODA_DATETIME)) {
            str = SERIALIZERS_FOR_JODA_DATETIME;
        } else if (name.startsWith(PACKAGE_PREFIX_JAVAX_XML) || hasSupertypeStartingWith(rawClass, PACKAGE_PREFIX_JAVAX_XML)) {
            str = SERIALIZERS_FOR_JAVAX_XML;
        } else {
            if (doesImplement(rawClass, "org.w3c.dom.Node")) {
                return (JsonSerializer) instantiate(SERIALIZER_FOR_DOM_NODE);
            }
            return null;
        }
        Object objInstantiate = instantiate(str);
        if (objInstantiate == null) {
            return null;
        }
        Collection<Map.Entry> collectionProvide = ((Provider) objInstantiate).provide();
        for (Map.Entry entry : collectionProvide) {
            if (rawClass == entry.getKey()) {
                return (JsonSerializer) entry.getValue();
            }
        }
        for (Map.Entry entry2 : collectionProvide) {
            if (((Class) entry2.getKey()).isAssignableFrom(rawClass)) {
                return (JsonSerializer) entry2.getValue();
            }
        }
        return null;
    }

    public JsonDeserializer<?> findDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider) {
        String str;
        Class<?> rawClass = javaType.getRawClass();
        String name = rawClass.getName();
        if (name.startsWith(PACKAGE_PREFIX_JODA_DATETIME)) {
            str = DESERIALIZERS_FOR_JODA_DATETIME;
        } else if (name.startsWith(PACKAGE_PREFIX_JAVAX_XML) || hasSupertypeStartingWith(rawClass, PACKAGE_PREFIX_JAVAX_XML)) {
            str = DESERIALIZERS_FOR_JAVAX_XML;
        } else {
            if (doesImplement(rawClass, "org.w3c.dom.Node")) {
                return (JsonDeserializer) instantiate(DESERIALIZER_FOR_DOM_DOCUMENT);
            }
            if (doesImplement(rawClass, "org.w3c.dom.Node")) {
                return (JsonDeserializer) instantiate(DESERIALIZER_FOR_DOM_NODE);
            }
            return null;
        }
        Object objInstantiate = instantiate(str);
        if (objInstantiate == null) {
            return null;
        }
        Collection<StdDeserializer> collectionProvide = ((Provider) objInstantiate).provide();
        for (StdDeserializer stdDeserializer : collectionProvide) {
            if (rawClass == stdDeserializer.getValueClass()) {
                return stdDeserializer;
            }
        }
        for (StdDeserializer stdDeserializer2 : collectionProvide) {
            if (stdDeserializer2.getValueClass().isAssignableFrom(rawClass)) {
                return stdDeserializer2;
            }
        }
        return null;
    }

    private Object instantiate(String str) {
        try {
            return Class.forName(str).newInstance();
        } catch (Exception | LinkageError e) {
            return null;
        }
    }

    private boolean doesImplement(Class<?> cls, String str) {
        while (cls != null) {
            if (cls.getName().equals(str) || hasInterface(cls, str)) {
                return true;
            }
            cls = cls.getSuperclass();
        }
        return false;
    }

    private boolean hasInterface(Class<?> cls, String str) {
        Class<?>[] interfaces = cls.getInterfaces();
        for (Class<?> cls2 : interfaces) {
            if (cls2.getName().equals(str)) {
                return true;
            }
        }
        for (Class<?> cls3 : interfaces) {
            if (hasInterface(cls3, str)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasSupertypeStartingWith(Class<?> cls, String str) {
        for (Class<? super Object> superclass = cls.getSuperclass(); superclass != null; superclass = superclass.getSuperclass()) {
            if (superclass.getName().startsWith(str)) {
                return true;
            }
        }
        while (cls != null) {
            if (hasInterfaceStartingWith(cls, str)) {
                return true;
            }
            cls = cls.getSuperclass();
        }
        return false;
    }

    private boolean hasInterfaceStartingWith(Class<?> cls, String str) {
        Class<?>[] interfaces = cls.getInterfaces();
        for (Class<?> cls2 : interfaces) {
            if (cls2.getName().startsWith(str)) {
                return true;
            }
        }
        for (Class<?> cls3 : interfaces) {
            if (hasInterfaceStartingWith(cls3, str)) {
                return true;
            }
        }
        return false;
    }
}
