package org.codehaus.jackson.map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface ContextualSerializer<T> {
    JsonSerializer<T> createContextual(SerializationConfig serializationConfig, BeanProperty beanProperty);
}
