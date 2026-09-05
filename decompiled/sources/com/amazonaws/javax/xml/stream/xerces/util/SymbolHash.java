package com.amazonaws.javax.xml.stream.xerces.util;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class SymbolHash {
    protected Entry[] fBuckets;
    protected int fNum;
    protected int fTableSize;

    public SymbolHash() {
        this.fTableSize = 101;
        this.fNum = 0;
        this.fBuckets = new Entry[this.fTableSize];
    }

    public SymbolHash(int i) {
        this.fTableSize = 101;
        this.fNum = 0;
        this.fTableSize = i;
        this.fBuckets = new Entry[this.fTableSize];
    }

    public void put(Object obj, Object obj2) {
        int iHashCode = (obj.hashCode() & Integer.MAX_VALUE) % this.fTableSize;
        Entry entrySearch = search(obj, iHashCode);
        if (entrySearch != null) {
            entrySearch.value = obj2;
            return;
        }
        this.fBuckets[iHashCode] = new Entry(obj, obj2, this.fBuckets[iHashCode]);
        this.fNum++;
    }

    public Object get(Object obj) {
        Entry entrySearch = search(obj, (obj.hashCode() & Integer.MAX_VALUE) % this.fTableSize);
        if (entrySearch != null) {
            return entrySearch.value;
        }
        return null;
    }

    public int getLength() {
        return this.fNum;
    }

    public int getValues(Object[] objArr, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < this.fTableSize && i2 < this.fNum; i3++) {
            Entry entry = this.fBuckets[i3];
            while (entry != null) {
                objArr[i + i2] = entry.value;
                entry = entry.next;
                i2++;
            }
        }
        return this.fNum;
    }

    public SymbolHash makeClone() {
        SymbolHash symbolHash = new SymbolHash(this.fTableSize);
        symbolHash.fNum = this.fNum;
        for (int i = 0; i < this.fTableSize; i++) {
            if (this.fBuckets[i] != null) {
                symbolHash.fBuckets[i] = this.fBuckets[i].makeClone();
            }
        }
        return symbolHash;
    }

    public void clear() {
        for (int i = 0; i < this.fTableSize; i++) {
            this.fBuckets[i] = null;
        }
        this.fNum = 0;
    }

    protected Entry search(Object obj, int i) {
        for (Entry entry = this.fBuckets[i]; entry != null; entry = entry.next) {
            if (obj.equals(entry.key)) {
                return entry;
            }
        }
        return null;
    }

    public final class Entry {
        public Object key;
        public Entry next;
        public Object value;

        public Entry() {
            this.key = null;
            this.value = null;
            this.next = null;
        }

        public Entry(Object obj, Object obj2, Entry entry) {
            this.key = obj;
            this.value = obj2;
            this.next = entry;
        }

        public Entry makeClone() {
            Entry entry = new Entry();
            entry.key = this.key;
            entry.value = this.value;
            if (this.next != null) {
                entry.next = this.next.makeClone();
            }
            return entry;
        }
    }
}
