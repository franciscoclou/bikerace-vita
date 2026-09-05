package com.amazonaws.javax.xml.stream.events;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface Namespace extends Attribute {
    String getNamespaceURI();

    String getPrefix();

    boolean isDefaultNamespaceDeclaration();
}
