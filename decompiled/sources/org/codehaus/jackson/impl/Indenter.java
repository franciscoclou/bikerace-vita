package org.codehaus.jackson.impl;

import org.codehaus.jackson.JsonGenerator;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface Indenter {
    boolean isInline();

    void writeIndentation(JsonGenerator jsonGenerator, int i);
}
