package org.codehaus.jackson.map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface ContextualDeserializer<T> {
    JsonDeserializer<T> createContextual(DeserializationConfig deserializationConfig, BeanProperty beanProperty);
}
