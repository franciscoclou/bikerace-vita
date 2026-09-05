package com.amazonaws.javax.xml.stream.xerces.util;

import com.amazonaws.javax.xml.stream.XMLBufferListener;
import com.amazonaws.javax.xml.stream.xerces.xni.Augmentations;
import com.amazonaws.javax.xml.stream.xerces.xni.QName;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLString;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class XMLAttributesImpl implements XMLBufferListener, XMLAttributes {
    protected static final int SIZE_LIMIT = 20;
    protected static final int TABLE_SIZE = 101;
    protected Attribute[] fAttributeTableView;
    protected int[] fAttributeTableViewChainState;
    protected Attribute[] fAttributes;
    protected boolean fIsTableViewConsistent;
    protected int fLargeCount;
    protected int fLength;
    protected boolean fNamespaces;
    protected int fTableViewBuckets;

    public XMLAttributesImpl() {
        this(TABLE_SIZE);
    }

    public XMLAttributesImpl(int i) {
        this.fNamespaces = true;
        this.fLargeCount = 1;
        this.fAttributes = new Attribute[4];
        this.fTableViewBuckets = i;
        for (int i2 = 0; i2 < this.fAttributes.length; i2++) {
            this.fAttributes[i2] = new Attribute();
        }
    }

    public void setNamespaces(boolean z) {
        this.fNamespaces = z;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public int addAttribute(QName qName, String str, String str2) {
        return addAttribute(qName, str, str2, null);
    }

    public int addAttribute(QName qName, String str, String str2, XMLString xMLString) {
        int indexFast;
        if (this.fLength < SIZE_LIMIT) {
            indexFast = (qName.uri == null || qName.uri.equals("")) ? getIndexFast(qName.rawname) : getIndexFast(qName.uri, qName.localpart);
            if (indexFast == -1) {
                int i = this.fLength;
                int i2 = this.fLength;
                this.fLength = i2 + 1;
                if (i2 == this.fAttributes.length) {
                    Attribute[] attributeArr = new Attribute[this.fAttributes.length + 4];
                    System.arraycopy(this.fAttributes, 0, attributeArr, 0, this.fAttributes.length);
                    for (int length = this.fAttributes.length; length < attributeArr.length; length++) {
                        attributeArr[length] = new Attribute();
                    }
                    this.fAttributes = attributeArr;
                    indexFast = i;
                } else {
                    indexFast = i;
                }
            }
        } else if (qName.uri == null || qName.uri.length() == 0 || (indexFast = getIndexFast(qName.uri, qName.localpart)) == -1) {
            if (!this.fIsTableViewConsistent || this.fLength == SIZE_LIMIT) {
                prepareAndPopulateTableView();
                this.fIsTableViewConsistent = true;
            }
            int tableViewBucket = getTableViewBucket(qName.rawname);
            if (this.fAttributeTableViewChainState[tableViewBucket] != this.fLargeCount) {
                int i3 = this.fLength;
                int i4 = this.fLength;
                this.fLength = i4 + 1;
                if (i4 == this.fAttributes.length) {
                    Attribute[] attributeArr2 = new Attribute[this.fAttributes.length << 1];
                    System.arraycopy(this.fAttributes, 0, attributeArr2, 0, this.fAttributes.length);
                    for (int length2 = this.fAttributes.length; length2 < attributeArr2.length; length2++) {
                        attributeArr2[length2] = new Attribute();
                    }
                    this.fAttributes = attributeArr2;
                }
                this.fAttributeTableViewChainState[tableViewBucket] = this.fLargeCount;
                this.fAttributes[i3].next = null;
                this.fAttributeTableView[tableViewBucket] = this.fAttributes[i3];
                indexFast = i3;
            } else {
                Attribute attribute = this.fAttributeTableView[tableViewBucket];
                while (attribute != null && attribute.name.rawname != qName.rawname) {
                    attribute = attribute.next;
                }
                if (attribute == null) {
                    int i5 = this.fLength;
                    int i6 = this.fLength;
                    this.fLength = i6 + 1;
                    if (i6 == this.fAttributes.length) {
                        Attribute[] attributeArr3 = new Attribute[this.fAttributes.length << 1];
                        System.arraycopy(this.fAttributes, 0, attributeArr3, 0, this.fAttributes.length);
                        for (int length3 = this.fAttributes.length; length3 < attributeArr3.length; length3++) {
                            attributeArr3[length3] = new Attribute();
                        }
                        this.fAttributes = attributeArr3;
                    }
                    this.fAttributes[i5].next = this.fAttributeTableView[tableViewBucket];
                    this.fAttributeTableView[tableViewBucket] = this.fAttributes[i5];
                    indexFast = i5;
                } else {
                    indexFast = getIndexFast(qName.rawname);
                }
            }
        }
        Attribute attribute2 = this.fAttributes[indexFast];
        attribute2.name.setValues(qName);
        attribute2.type = str;
        attribute2.value = str2;
        attribute2.xmlValue = xMLString;
        attribute2.nonNormalizedValue = str2;
        attribute2.specified = false;
        if (attribute2.augs != null) {
            attribute2.augs.removeAllItems();
        }
        return indexFast;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public void removeAllAttributes() {
        this.fLength = 0;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public void removeAttributeAt(int i) {
        this.fIsTableViewConsistent = false;
        if (i < this.fLength - 1) {
            Attribute attribute = this.fAttributes[i];
            System.arraycopy(this.fAttributes, i + 1, this.fAttributes, i, (this.fLength - i) - 1);
            this.fAttributes[this.fLength - 1] = attribute;
        }
        this.fLength--;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public void setName(int i, QName qName) {
        this.fAttributes[i].name.setValues(qName);
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public void getName(int i, QName qName) {
        qName.setValues(this.fAttributes[i].name);
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public void setType(int i, String str) {
        this.fAttributes[i].type = str;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public void setValue(int i, String str) {
        setValue(i, str, null);
    }

    public void setValue(int i, String str, XMLString xMLString) {
        Attribute attribute = this.fAttributes[i];
        attribute.value = str;
        attribute.nonNormalizedValue = str;
        attribute.xmlValue = xMLString;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public void setNonNormalizedValue(int i, String str) {
        if (str == null) {
            str = this.fAttributes[i].value;
        }
        this.fAttributes[i].nonNormalizedValue = str;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public String getNonNormalizedValue(int i) {
        return this.fAttributes[i].nonNormalizedValue;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public void setSpecified(int i, boolean z) {
        this.fAttributes[i].specified = z;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public boolean isSpecified(int i) {
        return this.fAttributes[i].specified;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public int getLength() {
        return this.fLength;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public String getType(int i) {
        if (i < 0 || i >= this.fLength) {
            return null;
        }
        return getReportableType(this.fAttributes[i].type);
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public String getType(String str) {
        int index = getIndex(str);
        if (index != -1) {
            return getReportableType(this.fAttributes[index].type);
        }
        return null;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public String getValue(int i) {
        if (i < 0 || i >= this.fLength) {
            return null;
        }
        if (this.fAttributes[i].value == null && this.fAttributes[i].xmlValue != null) {
            this.fAttributes[i].value = this.fAttributes[i].xmlValue.toString();
        }
        return this.fAttributes[i].value;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public String getValue(String str) {
        int index = getIndex(str);
        if (index == -1) {
            return null;
        }
        if (this.fAttributes[index].value == null) {
            this.fAttributes[index].value = this.fAttributes[index].xmlValue.toString();
        }
        return this.fAttributes[index].value;
    }

    public String getName(int i) {
        if (i < 0 || i >= this.fLength) {
            return null;
        }
        return this.fAttributes[i].name.rawname;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public int getIndex(String str) {
        for (int i = 0; i < this.fLength; i++) {
            Attribute attribute = this.fAttributes[i];
            if (attribute.name.rawname != null && attribute.name.rawname.equals(str)) {
                return i;
            }
        }
        return -1;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public int getIndex(String str, String str2) {
        for (int i = 0; i < this.fLength; i++) {
            Attribute attribute = this.fAttributes[i];
            if (attribute.name.localpart != null && attribute.name.localpart.equals(str2)) {
                if (str == attribute.name.uri) {
                    return i;
                }
                if (str != null && attribute.name.uri != null && attribute.name.uri.equals(str)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int getIndexByLocalName(String str) {
        for (int i = 0; i < this.fLength; i++) {
            Attribute attribute = this.fAttributes[i];
            if (attribute.name.localpart != null && attribute.name.localpart.equals(str)) {
                return i;
            }
        }
        return -1;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public String getLocalName(int i) {
        if (!this.fNamespaces) {
            return "";
        }
        if (i < 0 || i >= this.fLength) {
            return null;
        }
        return this.fAttributes[i].name.localpart;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public String getQName(int i) {
        if (i < 0 || i >= this.fLength) {
            return null;
        }
        String str = this.fAttributes[i].name.rawname;
        return str == null ? "" : str;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public QName getQualifiedName(int i) {
        if (i < 0 || i >= this.fLength) {
            return null;
        }
        return this.fAttributes[i].name;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public String getType(String str, String str2) {
        int index;
        if (this.fNamespaces && (index = getIndex(str, str2)) != -1) {
            return getReportableType(this.fAttributes[index].type);
        }
        return null;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public String getPrefix(int i) {
        if (i < 0 || i >= this.fLength) {
            return null;
        }
        String str = this.fAttributes[i].name.prefix;
        return str == null ? "" : str;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public String getURI(int i) {
        if (i < 0 || i >= this.fLength) {
            return null;
        }
        return this.fAttributes[i].name.uri;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public String getValue(String str, String str2) {
        int index;
        if (str == null) {
            index = getIndexByLocalName(str2);
        } else {
            if (str.length() == 0) {
                str = null;
            }
            index = getIndex(str, str2);
        }
        if (index != -1) {
            return getValue(index);
        }
        return null;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public Augmentations getAugmentations(String str, String str2) {
        int index = getIndex(str, str2);
        if (index != -1) {
            return this.fAttributes[index].augs;
        }
        return null;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public Augmentations getAugmentations(String str) {
        int index = getIndex(str);
        if (index != -1) {
            return this.fAttributes[index].augs;
        }
        return null;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes
    public Augmentations getAugmentations(int i) {
        if (i < 0 || i >= this.fLength) {
            return null;
        }
        return this.fAttributes[i].augs;
    }

    public void setAugmentations(int i, Augmentations augmentations) {
        this.fAttributes[i].augs = augmentations;
    }

    public void setURI(int i, String str) {
        this.fAttributes[i].name.uri = str;
    }

    public void setSchemaId(int i, boolean z) {
        this.fAttributes[i].schemaId = z;
    }

    public boolean getSchemaId(int i) {
        if (i < 0 || i >= this.fLength) {
            return false;
        }
        return this.fAttributes[i].schemaId;
    }

    public boolean getSchemaId(String str) {
        int index = getIndex(str);
        if (index != -1) {
            return this.fAttributes[index].schemaId;
        }
        return false;
    }

    public boolean getSchemaId(String str, String str2) {
        int index;
        if (this.fNamespaces && (index = getIndex(str, str2)) != -1) {
            return this.fAttributes[index].schemaId;
        }
        return false;
    }

    public int getIndexFast(String str) {
        for (int i = 0; i < this.fLength; i++) {
            if (this.fAttributes[i].name.rawname == str) {
                return i;
            }
        }
        return -1;
    }

    public void addAttributeNS(QName qName, String str, String str2) {
        Attribute[] attributeArr;
        int i = this.fLength;
        int i2 = this.fLength;
        this.fLength = i2 + 1;
        if (i2 == this.fAttributes.length) {
            if (this.fLength < SIZE_LIMIT) {
                attributeArr = new Attribute[this.fAttributes.length + 4];
            } else {
                attributeArr = new Attribute[this.fAttributes.length << 1];
            }
            System.arraycopy(this.fAttributes, 0, attributeArr, 0, this.fAttributes.length);
            for (int length = this.fAttributes.length; length < attributeArr.length; length++) {
                attributeArr[length] = new Attribute();
            }
            this.fAttributes = attributeArr;
        }
        Attribute attribute = this.fAttributes[i];
        attribute.name.setValues(qName);
        attribute.type = str;
        attribute.value = str2;
        attribute.nonNormalizedValue = str2;
        attribute.specified = false;
        if (attribute.augs != null) {
            attribute.augs.removeAllItems();
        }
    }

    public QName checkDuplicatesNS() {
        if (this.fLength <= SIZE_LIMIT) {
            for (int i = 0; i < this.fLength - 1; i++) {
                Attribute attribute = this.fAttributes[i];
                for (int i2 = i + 1; i2 < this.fLength; i2++) {
                    Attribute attribute2 = this.fAttributes[i2];
                    if (attribute.name.localpart == attribute2.name.localpart && attribute.name.uri == attribute2.name.uri) {
                        return attribute2.name;
                    }
                }
            }
        } else {
            this.fIsTableViewConsistent = false;
            prepareTableView();
            for (int i3 = this.fLength - 1; i3 >= 0; i3--) {
                Attribute attribute3 = this.fAttributes[i3];
                int tableViewBucket = getTableViewBucket(attribute3.name.localpart, attribute3.name.uri);
                if (this.fAttributeTableViewChainState[tableViewBucket] != this.fLargeCount) {
                    this.fAttributeTableViewChainState[tableViewBucket] = this.fLargeCount;
                    attribute3.next = null;
                    this.fAttributeTableView[tableViewBucket] = attribute3;
                } else {
                    for (Attribute attribute4 = this.fAttributeTableView[tableViewBucket]; attribute4 != null; attribute4 = attribute4.next) {
                        if (attribute4.name.localpart == attribute3.name.localpart && attribute4.name.uri == attribute3.name.uri) {
                            return attribute3.name;
                        }
                    }
                    attribute3.next = this.fAttributeTableView[tableViewBucket];
                    this.fAttributeTableView[tableViewBucket] = attribute3;
                }
            }
        }
        return null;
    }

    public int getIndexFast(String str, String str2) {
        for (int i = 0; i < this.fLength; i++) {
            Attribute attribute = this.fAttributes[i];
            if (attribute.name.localpart == str2 && attribute.name.uri == str) {
                return i;
            }
        }
        return -1;
    }

    protected String getReportableType(String str) {
        if (str.indexOf(40) == 0 && str.lastIndexOf(41) == str.length() - 1) {
            return "NMTOKEN";
        }
        return str;
    }

    protected int getTableViewBucket(String str) {
        return (str.hashCode() & Integer.MAX_VALUE) % this.fTableViewBuckets;
    }

    protected int getTableViewBucket(String str, String str2) {
        return str2 == null ? (str.hashCode() & Integer.MAX_VALUE) % this.fTableViewBuckets : ((str.hashCode() + str2.hashCode()) & Integer.MAX_VALUE) % this.fTableViewBuckets;
    }

    protected void cleanTableView() {
        int i = this.fLargeCount + 1;
        this.fLargeCount = i;
        if (i < 0) {
            if (this.fAttributeTableViewChainState != null) {
                for (int i2 = this.fTableViewBuckets - 1; i2 >= 0; i2--) {
                    this.fAttributeTableViewChainState[i2] = 0;
                }
            }
            this.fLargeCount = 1;
        }
    }

    protected void prepareTableView() {
        if (this.fAttributeTableView == null) {
            this.fAttributeTableView = new Attribute[this.fTableViewBuckets];
            this.fAttributeTableViewChainState = new int[this.fTableViewBuckets];
        } else {
            cleanTableView();
        }
    }

    protected void prepareAndPopulateTableView() {
        prepareTableView();
        for (int i = 0; i < this.fLength; i++) {
            Attribute attribute = this.fAttributes[i];
            int tableViewBucket = getTableViewBucket(attribute.name.rawname);
            if (this.fAttributeTableViewChainState[tableViewBucket] != this.fLargeCount) {
                this.fAttributeTableViewChainState[tableViewBucket] = this.fLargeCount;
                attribute.next = null;
                this.fAttributeTableView[tableViewBucket] = attribute;
            } else {
                attribute.next = this.fAttributeTableView[tableViewBucket];
                this.fAttributeTableView[tableViewBucket] = attribute;
            }
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLBufferListener
    public void refresh() {
        if (this.fLength > 0) {
            for (int i = 0; i < this.fLength; i++) {
                getValue(i);
            }
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLBufferListener
    public void refresh(int i) {
    }

    class Attribute {
        public Attribute next;
        public String nonNormalizedValue;
        public boolean schemaId;
        public boolean specified;
        public String type;
        public String value;
        public XMLString xmlValue;
        public QName name = new QName();
        public Augmentations augs = null;

        Attribute() {
        }
    }
}
