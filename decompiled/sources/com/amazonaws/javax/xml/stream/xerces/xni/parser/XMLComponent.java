package com.amazonaws.javax.xml.stream.xerces.xni.parser;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface XMLComponent {
    Boolean getFeatureDefault(String str);

    Object getPropertyDefault(String str);

    String[] getRecognizedFeatures();

    String[] getRecognizedProperties();

    void reset(XMLComponentManager xMLComponentManager);

    void setFeature(String str, boolean z);

    void setProperty(String str, Object obj);
}
