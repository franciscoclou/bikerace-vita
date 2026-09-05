package org.codehaus.jackson.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@JacksonAnnotation
@Deprecated
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonWriteNullProperties {
    boolean value() default true;
}
