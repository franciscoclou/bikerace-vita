package org.codehaus.jackson;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class JsonGenerationException extends JsonProcessingException {
    static final long serialVersionUID = 123;

    public JsonGenerationException(Throwable th) {
        super(th);
    }

    public JsonGenerationException(String str) {
        super(str, (JsonLocation) null);
    }

    public JsonGenerationException(String str, Throwable th) {
        super(str, (JsonLocation) null, th);
    }
}
