package com.amazonaws.javax.xml.stream.events;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface StartDocument extends XMLEvent {
    boolean encodingSet();

    String getCharacterEncodingScheme();

    String getSystemId();

    String getVersion();

    boolean isStandalone();

    boolean standaloneSet();
}
