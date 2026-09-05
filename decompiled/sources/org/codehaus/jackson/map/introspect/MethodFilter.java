package org.codehaus.jackson.map.introspect;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface MethodFilter {
    boolean includeMethod(Method method);
}
