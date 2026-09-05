package org.codehaus.jackson.map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class DeserializationProblemHandler {
    public boolean handleUnknownProperty(DeserializationContext deserializationContext, JsonDeserializer<?> jsonDeserializer, Object obj, String str) {
        return false;
    }
}
