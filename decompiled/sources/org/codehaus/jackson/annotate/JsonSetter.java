package org.codehaus.jackson.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
@Target({ElementType.METHOD})
@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonSetter {
    String value() default "";
}
