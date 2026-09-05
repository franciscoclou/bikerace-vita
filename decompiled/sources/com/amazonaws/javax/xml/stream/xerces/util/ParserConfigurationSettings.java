package com.amazonaws.javax.xml.stream.xerces.util;

import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponentManager;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLConfigurationException;
import java.util.Hashtable;
import java.util.Vector;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ParserConfigurationSettings implements XMLComponentManager {
    static int counter = 1;
    protected Hashtable fFeatures;
    protected XMLComponentManager fParentSettings;
    protected Hashtable fProperties;
    protected Vector fRecognizedFeatures;
    protected Vector fRecognizedProperties;

    public ParserConfigurationSettings() {
        this(null);
    }

    public ParserConfigurationSettings(XMLComponentManager xMLComponentManager) {
        this.fRecognizedFeatures = new Vector();
        this.fRecognizedProperties = new Vector();
        this.fFeatures = new Hashtable();
        this.fProperties = new Hashtable();
        this.fParentSettings = xMLComponentManager;
    }

    public void addRecognizedFeatures(String[] strArr) {
        int length = strArr != null ? strArr.length : 0;
        for (int i = 0; i < length; i++) {
            String str = strArr[i];
            if (!this.fRecognizedFeatures.contains(str)) {
                this.fRecognizedFeatures.addElement(str);
            }
        }
    }

    public void setFeature(String str, boolean z) {
        checkFeature(str);
        this.fFeatures.put(str, z ? Boolean.TRUE : Boolean.FALSE);
    }

    public void addRecognizedProperties(String[] strArr) {
        int length = strArr != null ? strArr.length : 0;
        for (int i = 0; i < length; i++) {
            String str = strArr[i];
            if (!this.fRecognizedProperties.contains(str)) {
                this.fRecognizedProperties.addElement(str);
            }
        }
    }

    public void setProperty(String str, Object obj) {
        checkProperty(str);
        this.fProperties.put(str, obj);
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponentManager
    public boolean getFeature(String str) {
        Boolean bool = (Boolean) this.fFeatures.get(str);
        if (bool != null) {
            return bool.booleanValue();
        }
        checkFeature(str);
        return false;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponentManager
    public Object getProperty(String str) {
        Object obj = this.fProperties.get(str);
        if (obj == null) {
            checkProperty(str);
        }
        return obj;
    }

    protected void checkFeature(String str) {
        if (!this.fRecognizedFeatures.contains(str)) {
            if (this.fParentSettings != null) {
                this.fParentSettings.getFeature(str);
                return;
            }
            throw new XMLConfigurationException((short) 0, str);
        }
    }

    protected void checkProperty(String str) {
        if (!this.fRecognizedProperties.contains(str)) {
            if (this.fParentSettings != null) {
                this.fParentSettings.getProperty(str);
                return;
            }
            throw new XMLConfigurationException((short) 0, str);
        }
    }
}
