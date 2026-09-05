package com.amazonaws.javax.xml.stream.events;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface EntityDeclaration extends XMLEvent {
    String getBaseURI();

    String getName();

    String getNotationName();

    String getPublicId();

    String getReplacementText();

    String getSystemId();
}
