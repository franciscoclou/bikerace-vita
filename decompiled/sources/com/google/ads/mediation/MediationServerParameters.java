package com.google.ads.mediation;

import com.google.ads.util.b;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class MediationServerParameters {

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Parameter {
        String name();

        boolean required() default true;
    }

    public class MappingException extends Exception {
        public MappingException(String str) {
            super(str);
        }
    }

    public void load(Map<String, String> map) throws MappingException {
        String str;
        HashMap map2 = new HashMap();
        for (Field field : getClass().getFields()) {
            Parameter parameter = (Parameter) field.getAnnotation(Parameter.class);
            if (parameter != null) {
                map2.put(parameter.name(), field);
            }
        }
        if (map2.isEmpty()) {
            b.e("No server options fields detected.  To suppress this message either add a field with the @Parameter annotation, or override the load() method");
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Field field2 = (Field) map2.remove(entry.getKey());
            if (field2 != null) {
                try {
                    field2.set(this, entry.getValue());
                } catch (IllegalAccessException e) {
                    b.b("Server Option '" + entry.getKey() + "' could not be set: Illegal Access");
                } catch (IllegalArgumentException e2) {
                    b.b("Server Option '" + entry.getKey() + "' could not be set: Bad Type");
                }
            } else {
                b.e("Unexpected Server Option: " + entry.getKey() + " = '" + entry.getValue() + "'");
            }
        }
        String str2 = null;
        for (Field field3 : map2.values()) {
            if (((Parameter) field3.getAnnotation(Parameter.class)).required()) {
                b.b("Required Server Option missing: " + ((Parameter) field3.getAnnotation(Parameter.class)).name());
                str = (str2 == null ? "" : str2 + ", ") + ((Parameter) field3.getAnnotation(Parameter.class)).name();
            } else {
                str = str2;
            }
            str2 = str;
        }
        if (str2 != null) {
            throw new MappingException("Required Server Option(s) missing: " + str2);
        }
        a();
    }

    protected void a() {
    }
}
