package com.amazonaws.javax.xml.stream.xerces.xni;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface XMLDTDContentModelHandler {
    public static final short OCCURS_ONE_OR_MORE = 4;
    public static final short OCCURS_ZERO_OR_MORE = 3;
    public static final short OCCURS_ZERO_OR_ONE = 2;
    public static final short SEPARATOR_CHOICE = 0;
    public static final short SEPARATOR_SEQUENCE = 1;

    void any(Augmentations augmentations);

    void element(String str, Augmentations augmentations);

    void empty(Augmentations augmentations);

    void endContentModel(Augmentations augmentations);

    void endGroup(Augmentations augmentations);

    void occurrence(short s, Augmentations augmentations);

    void pcdata(Augmentations augmentations);

    void separator(short s, Augmentations augmentations);

    void startContentModel(String str, Augmentations augmentations);

    void startGroup(Augmentations augmentations);
}
