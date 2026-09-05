package com.amazonaws.javax.xml.stream.xerces.util;

import com.amazonaws.javax.xml.stream.xerces.xni.Augmentations;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AugmentationsImpl implements Augmentations {
    private AugmentationsItemsContainer fAugmentationsContainer = new SmallContainer();

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.Augmentations
    public Object putItem(String str, Object obj) {
        Object objPutItem = this.fAugmentationsContainer.putItem(str, obj);
        if (objPutItem == null && this.fAugmentationsContainer.isFull()) {
            this.fAugmentationsContainer = this.fAugmentationsContainer.expand();
        }
        return objPutItem;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.Augmentations
    public Object getItem(String str) {
        return this.fAugmentationsContainer.getItem(str);
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.Augmentations
    public Object removeItem(String str) {
        return this.fAugmentationsContainer.removeItem(str);
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.Augmentations
    public Enumeration keys() {
        return this.fAugmentationsContainer.keys();
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.Augmentations
    public void removeAllItems() {
        this.fAugmentationsContainer.clear();
    }

    public String toString() {
        return this.fAugmentationsContainer.toString();
    }

    abstract class AugmentationsItemsContainer {
        public abstract void clear();

        public abstract AugmentationsItemsContainer expand();

        public abstract Object getItem(Object obj);

        public abstract boolean isFull();

        public abstract Enumeration keys();

        public abstract Object putItem(Object obj, Object obj2);

        public abstract Object removeItem(Object obj);

        AugmentationsItemsContainer() {
        }
    }

    class SmallContainer extends AugmentationsItemsContainer {
        static final int SIZE_LIMIT = 10;
        final Object[] fAugmentations;
        int fNumEntries;

        SmallContainer() {
            super();
            this.fAugmentations = new Object[20];
            this.fNumEntries = 0;
        }

        @Override // com.amazonaws.javax.xml.stream.xerces.util.AugmentationsImpl.AugmentationsItemsContainer
        public Enumeration keys() {
            return new SmallContainerKeyEnumeration(this);
        }

        @Override // com.amazonaws.javax.xml.stream.xerces.util.AugmentationsImpl.AugmentationsItemsContainer
        public Object getItem(Object obj) {
            for (int i = 0; i < this.fNumEntries * 2; i += 2) {
                if (this.fAugmentations[i].equals(obj)) {
                    return this.fAugmentations[i + 1];
                }
            }
            return null;
        }

        @Override // com.amazonaws.javax.xml.stream.xerces.util.AugmentationsImpl.AugmentationsItemsContainer
        public Object putItem(Object obj, Object obj2) {
            for (int i = 0; i < this.fNumEntries * 2; i += 2) {
                if (this.fAugmentations[i].equals(obj)) {
                    Object obj3 = this.fAugmentations[i + 1];
                    this.fAugmentations[i + 1] = obj2;
                    return obj3;
                }
            }
            this.fAugmentations[this.fNumEntries * 2] = obj;
            this.fAugmentations[(this.fNumEntries * 2) + 1] = obj2;
            this.fNumEntries++;
            return null;
        }

        @Override // com.amazonaws.javax.xml.stream.xerces.util.AugmentationsImpl.AugmentationsItemsContainer
        public Object removeItem(Object obj) {
            int i = 0;
            while (i < this.fNumEntries * 2) {
                if (!this.fAugmentations[i].equals(obj)) {
                    i += 2;
                } else {
                    Object obj2 = this.fAugmentations[i + 1];
                    while (i < (this.fNumEntries * 2) - 2) {
                        this.fAugmentations[i] = this.fAugmentations[i + 2];
                        this.fAugmentations[i + 1] = this.fAugmentations[i + 3];
                        i += 2;
                    }
                    this.fAugmentations[(this.fNumEntries * 2) - 2] = null;
                    this.fAugmentations[(this.fNumEntries * 2) - 1] = null;
                    this.fNumEntries--;
                    return obj2;
                }
            }
            return null;
        }

        @Override // com.amazonaws.javax.xml.stream.xerces.util.AugmentationsImpl.AugmentationsItemsContainer
        public void clear() {
            for (int i = 0; i < this.fNumEntries * 2; i += 2) {
                this.fAugmentations[i] = null;
                this.fAugmentations[i + 1] = null;
            }
            this.fNumEntries = 0;
        }

        @Override // com.amazonaws.javax.xml.stream.xerces.util.AugmentationsImpl.AugmentationsItemsContainer
        public boolean isFull() {
            return this.fNumEntries == 10;
        }

        @Override // com.amazonaws.javax.xml.stream.xerces.util.AugmentationsImpl.AugmentationsItemsContainer
        public AugmentationsItemsContainer expand() {
            LargeContainer largeContainer = AugmentationsImpl.this.new LargeContainer();
            for (int i = 0; i < this.fNumEntries * 2; i += 2) {
                largeContainer.putItem(this.fAugmentations[i], this.fAugmentations[i + 1]);
            }
            return largeContainer;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(new StringBuffer().append("SmallContainer - fNumEntries == ").append(this.fNumEntries).toString());
            for (int i = 0; i < 20; i += 2) {
                stringBuffer.append("\nfAugmentations[");
                stringBuffer.append(i);
                stringBuffer.append("] == ");
                stringBuffer.append(this.fAugmentations[i]);
                stringBuffer.append("; fAugmentations[");
                stringBuffer.append(i + 1);
                stringBuffer.append("] == ");
                stringBuffer.append(this.fAugmentations[i + 1]);
            }
            return stringBuffer.toString();
        }

        class SmallContainerKeyEnumeration implements Enumeration {
            Object[] enumArray;
            int next = 0;
            private final /* synthetic */ SmallContainer this$1;

            SmallContainerKeyEnumeration(SmallContainer smallContainer) {
                this.this$1 = smallContainer;
                this.enumArray = new Object[this.this$1.fNumEntries];
                for (int i = 0; i < smallContainer.fNumEntries; i++) {
                    this.enumArray[i] = smallContainer.fAugmentations[i * 2];
                }
            }

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.next < this.enumArray.length;
            }

            @Override // java.util.Enumeration
            public Object nextElement() {
                if (this.next >= this.enumArray.length) {
                    throw new NoSuchElementException();
                }
                Object obj = this.enumArray[this.next];
                this.enumArray[this.next] = null;
                this.next++;
                return obj;
            }
        }
    }

    class LargeContainer extends AugmentationsItemsContainer {
        final Hashtable fAugmentations;

        LargeContainer() {
            super();
            this.fAugmentations = new Hashtable();
        }

        @Override // com.amazonaws.javax.xml.stream.xerces.util.AugmentationsImpl.AugmentationsItemsContainer
        public Object getItem(Object obj) {
            return this.fAugmentations.get(obj);
        }

        @Override // com.amazonaws.javax.xml.stream.xerces.util.AugmentationsImpl.AugmentationsItemsContainer
        public Object putItem(Object obj, Object obj2) {
            return this.fAugmentations.put(obj, obj2);
        }

        @Override // com.amazonaws.javax.xml.stream.xerces.util.AugmentationsImpl.AugmentationsItemsContainer
        public Object removeItem(Object obj) {
            return this.fAugmentations.remove(obj);
        }

        @Override // com.amazonaws.javax.xml.stream.xerces.util.AugmentationsImpl.AugmentationsItemsContainer
        public Enumeration keys() {
            return this.fAugmentations.keys();
        }

        @Override // com.amazonaws.javax.xml.stream.xerces.util.AugmentationsImpl.AugmentationsItemsContainer
        public void clear() {
            this.fAugmentations.clear();
        }

        @Override // com.amazonaws.javax.xml.stream.xerces.util.AugmentationsImpl.AugmentationsItemsContainer
        public boolean isFull() {
            return false;
        }

        @Override // com.amazonaws.javax.xml.stream.xerces.util.AugmentationsImpl.AugmentationsItemsContainer
        public AugmentationsItemsContainer expand() {
            return this;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("LargeContainer");
            Enumeration enumerationKeys = this.fAugmentations.keys();
            while (enumerationKeys.hasMoreElements()) {
                Object objNextElement = enumerationKeys.nextElement();
                stringBuffer.append("\nkey == ");
                stringBuffer.append(objNextElement);
                stringBuffer.append("; value == ");
                stringBuffer.append(this.fAugmentations.get(objNextElement));
            }
            return stringBuffer.toString();
        }
    }
}
