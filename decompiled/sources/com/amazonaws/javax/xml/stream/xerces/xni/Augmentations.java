package com.amazonaws.javax.xml.stream.xerces.xni;

import java.util.Enumeration;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface Augmentations {
    Object getItem(String str);

    Enumeration keys();

    Object putItem(String str, Object obj);

    void removeAllItems();

    Object removeItem(String str);
}
