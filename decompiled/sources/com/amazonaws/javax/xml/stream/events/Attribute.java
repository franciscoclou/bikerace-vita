package com.amazonaws.javax.xml.stream.events;

import com.amazonaws.javax.xml.a.b;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface Attribute extends XMLEvent {
    String getDTDType();

    b getName();

    String getValue();

    boolean isSpecified();
}
