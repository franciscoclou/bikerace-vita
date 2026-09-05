package org.codehaus.jackson.map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface ContextualKeyDeserializer {
    KeyDeserializer createContextual(DeserializationConfig deserializationConfig, BeanProperty beanProperty);
}
