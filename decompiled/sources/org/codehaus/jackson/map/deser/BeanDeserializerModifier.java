package org.codehaus.jackson.map.deser;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class BeanDeserializerModifier {
    public BeanDeserializerBuilder updateBuilder(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, BeanDeserializerBuilder beanDeserializerBuilder) {
        return beanDeserializerBuilder;
    }

    public JsonDeserializer<?> modifyDeserializer(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, JsonDeserializer<?> jsonDeserializer) {
        return jsonDeserializer;
    }
}
