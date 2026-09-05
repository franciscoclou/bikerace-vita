package com.amazonaws.javax.xml.stream.xerces.util;

import com.amazonaws.javax.xml.stream.xerces.xni.Augmentations;
import com.amazonaws.javax.xml.stream.xerces.xni.QName;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class STAXAttributesImpl implements XMLAttributes {
    int MAGIC_NUMBER;
    protected ArrayList attrList;
    protected HashMap attrMap;
    int attr_index;
    protected ArrayList dupList;
    protected boolean fNamespaces;
    private boolean init;

    public STAXAttributesImpl() {
        this.attr_index = 0;
        this.MAGIC_NUMBER = 2;
        this.fNamespaces = true;
        this.attrList = null;
        this.dupList = null;
        this.init = false;
        this.attrMap = null;
        this.attrList = new ArrayList(2);
        for (int i = 0; i < 2; i++) {
            Attribute attribute = new Attribute();
            attribute.name = new QName();
            this.attrList.add(i, attribute);
        }
    }

    public STAXAttributesImpl(int i) {
        this.attr_index = 0;
        this.MAGIC_NUMBER = 2;
        this.fNamespaces = true;
        this.attrList = null;
        this.dupList = null;
        this.init = false;
        this.attrMap = null;
        this.attrList = new ArrayList(i);
        this.attrMap = new HashMap();
    }

    public void setNamespaces(boolean z) {
        this.fNamespaces = z;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public int addAttribute(QName qName, String str, String str2) {
        Attribute attribute;
        if (this.attr_index >= this.attrList.size()) {
            Attribute attribute2 = new Attribute();
            attribute2.name = new QName();
            this.attrList.add(attribute2);
            attribute2.next = null;
            attribute = attribute2;
        } else {
            Attribute attribute3 = (Attribute) this.attrList.get(this.attr_index);
            attribute3.next = null;
            attribute = attribute3;
        }
        attribute.name.setValues(qName);
        attribute.type = str;
        attribute.value = str2;
        if (this.attr_index < 5) {
            for (int i = 0; i < this.attr_index; i++) {
                Attribute attribute4 = (Attribute) this.attrList.get(i);
                if (attribute4.name.rawname == qName.rawname) {
                    attribute4.value = str2;
                    return i;
                }
            }
        } else {
            if (!this.init) {
                if (this.attrMap == null) {
                    this.attrMap = new HashMap(2, 2.0f);
                }
                for (int i2 = 0; i2 < this.attr_index; i2++) {
                    Attribute attribute5 = (Attribute) this.attrList.get(i2);
                    this.attrMap.put(attribute5.name.rawname, attribute5);
                }
                this.init = true;
            }
            if (this.attrMap.containsKey(qName.rawname)) {
                return getLength();
            }
            this.attrMap.put(qName.rawname, attribute);
        }
        this.attr_index++;
        return getLength() - 1;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public void removeAllAttributes() {
        this.attr_index = 0;
        if (this.attrMap != null) {
            this.attrMap.clear();
        }
        if (this.dupList != null) {
            this.dupList.clear();
        }
        this.init = false;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public void removeAttributeAt(int i) {
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public void setName(int i, QName qName) {
        ((Attribute) this.attrList.get(i)).name.setValues(qName);
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public void getName(int i, QName qName) {
        qName.setValues(((Attribute) this.attrList.get(i)).name);
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public void setType(int i, String str) {
        ((Attribute) this.attrList.get(i)).type = str;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public void setValue(int i, String str) {
        if (i <= this.attr_index) {
            Attribute attribute = (Attribute) this.attrList.get(i);
            attribute.value = str;
            attribute.nonNormalizedValue = str;
        }
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public void setNonNormalizedValue(int i, String str) {
        ((Attribute) this.attrList.get(i)).nonNormalizedValue = str;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public String getNonNormalizedValue(int i) {
        return ((Attribute) this.attrList.get(i)).nonNormalizedValue;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public void setSpecified(int i, boolean z) {
        ((Attribute) this.attrList.get(i)).specified = z;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public boolean isSpecified(int i) {
        return ((Attribute) this.attrList.get(i)).specified;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public int getLength() {
        return this.attr_index;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public String getType(int i) {
        if (i < 0 || i >= this.attrList.size()) {
            return null;
        }
        return getReportableType(((Attribute) this.attrList.get(i)).type);
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public String getType(String str) {
        return null;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public String getValue(int i) {
        return ((Attribute) this.attrList.get(i)).value;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public String getValue(String str) {
        return null;
    }

    public String getName(int i) {
        if (i < 0 || i >= this.attrList.size()) {
            return null;
        }
        return ((Attribute) this.attrList.get(i)).name.rawname;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public int getIndex(String str) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.attr_index) {
                Attribute attribute = (Attribute) this.attrList.get(i2);
                if (attribute.name.rawname == null || !attribute.name.rawname.equals(str)) {
                    i = i2 + 1;
                } else {
                    return i2;
                }
            } else {
                return -1;
            }
        }
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public int getIndex(String str, String str2) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.attr_index) {
                Attribute attribute = (Attribute) this.attrList.get(i2);
                if (attribute.name.localpart != null && attribute.name.localpart.equals(str2)) {
                    if (str == attribute.name.uri) {
                        return i2;
                    }
                    if (str != null && attribute.name.uri != null && attribute.name.uri.equals(str)) {
                        return i2;
                    }
                }
                i = i2 + 1;
            } else {
                return -1;
            }
        }
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public String getLocalName(int i) {
        return !this.fNamespaces ? "" : ((Attribute) this.attrList.get(i)).name.localpart;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public String getQName(int i) {
        return ((Attribute) this.attrList.get(i)).name.rawname;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public QName getQualifiedName(int i) {
        return ((Attribute) this.attrList.get(i)).name;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public String getType(String str, String str2) {
        int index;
        if (this.fNamespaces && (index = getIndex(str, str2)) != -1) {
            return getReportableType(((Attribute) this.attrList.get(index)).type);
        }
        return null;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public String getPrefix(int i) {
        return ((Attribute) this.attrList.get(i)).name.prefix;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public String getURI(int i) {
        return ((Attribute) this.attrList.get(i)).name.uri;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public String getValue(String str, String str2) {
        int index = getIndex(str, str2);
        if (index != -1) {
            return getValue(index);
        }
        return null;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public Augmentations getAugmentations(String str, String str2) {
        return null;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public Augmentations getAugmentations(String str) {
        return null;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public Augmentations getAugmentations(int i) {
        return null;
    }

    public void setAugmentations(int i, Augmentations augmentations) {
    }

    public void setURI(int i, String str) {
        ((Attribute) this.attrList.get(i)).name.uri = str;
    }

    public void setSchemaId(int i, boolean z) {
    }

    public boolean getSchemaId(int i) {
        return false;
    }

    public boolean getSchemaId(String str) {
        return false;
    }

    public boolean getSchemaId(String str, String str2) {
        return false;
    }

    public void addAttributeNS(QName qName, String str, String str2) {
        Attribute attribute;
        if (this.attr_index >= this.attrList.size()) {
            Attribute attribute2 = new Attribute();
            attribute2.name = new QName();
            this.attrList.add(attribute2);
            attribute2.next = null;
            attribute = attribute2;
        } else {
            Attribute attribute3 = (Attribute) this.attrList.get(this.attr_index);
            attribute3.next = null;
            attribute = attribute3;
        }
        attribute.name.setValues(qName);
        attribute.type = str;
        attribute.value = str2;
        if (this.attr_index > this.MAGIC_NUMBER) {
            if (!this.init) {
                if (this.attrMap == null) {
                    this.attrMap = new HashMap(2, 2.0f);
                }
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= this.attr_index) {
                        break;
                    }
                    Attribute attribute4 = (Attribute) this.attrList.get(i2);
                    this.attrMap.put(attribute4.name.localpart, attribute4);
                    i = i2 + 1;
                }
                this.init = true;
            }
            if (this.attrMap.containsKey(qName.localpart)) {
                Attribute attribute5 = (Attribute) this.attrMap.get(qName.localpart);
                attribute.next = attribute5.next;
                attribute5.next = attribute;
                this.attr_index++;
                if (!attribute5.dup) {
                    if (this.dupList == null) {
                        this.dupList = new ArrayList();
                    }
                    this.dupList.add(attribute);
                    attribute5.dup = true;
                    return;
                }
                return;
            }
            this.attrMap.put(qName.localpart, attribute);
            this.attr_index++;
            return;
        }
        this.attr_index++;
    }

    public QName checkDuplicatesNS() {
        int i = 0;
        if (this.attr_index <= this.MAGIC_NUMBER) {
            while (true) {
                int i2 = i;
                if (i2 >= this.attr_index - 1) {
                    break;
                }
                Attribute attribute = (Attribute) this.attrList.get(i2);
                int i3 = i2 + 1;
                while (true) {
                    int i4 = i3;
                    if (i4 < this.attr_index) {
                        Attribute attribute2 = (Attribute) this.attrList.get(i4);
                        if (attribute.name.localpart != attribute2.name.localpart || attribute.name.uri != attribute2.name.uri) {
                            i3 = i4 + 1;
                        } else {
                            return attribute2.name;
                        }
                    }
                }
                i = i2 + 1;
            }
        } else {
            if (this.dupList == null) {
                return null;
            }
            while (true) {
                int i5 = i;
                if (i5 >= this.dupList.size()) {
                    break;
                }
                Attribute attribute3 = (Attribute) this.dupList.get(i5);
                for (Attribute attribute4 = attribute3.next; attribute4 != null; attribute4 = attribute3.next) {
                    if (attribute3.name.localpart == attribute4.name.localpart && attribute3.name.uri == attribute4.name.uri) {
                        return attribute4.name;
                    }
                }
                i = i5 + 1;
            }
        }
        return null;
    }

    protected String getReportableType(String str) {
        if (str.indexOf(40) == 0 && str.lastIndexOf(41) == str.length() - 1) {
            return "NMTOKEN";
        }
        return str;
    }

    protected Attribute getDuplicate(Attribute attribute, QName qName) {
        if (attribute.name.prefix != qName.prefix || attribute.next != null) {
            while (attribute != null) {
                if (attribute.name.rawname != qName.rawname) {
                    attribute = attribute.next;
                } else {
                    return attribute;
                }
            }
            return null;
        }
        return attribute;
    }

    class Attribute {
        Attribute next;
        public String nonNormalizedValue;
        public boolean schemaId;
        public boolean specified;
        public String type;
        public String value;
        public QName name = new QName();
        public boolean dup = false;

        Attribute() {
        }
    }
}
