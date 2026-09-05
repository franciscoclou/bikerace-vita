package com.amazonaws.javax.xml.stream.events;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface Characters extends XMLEvent {
    String getData();

    boolean isCData();

    boolean isIgnorableWhiteSpace();

    boolean isWhiteSpace();
}
