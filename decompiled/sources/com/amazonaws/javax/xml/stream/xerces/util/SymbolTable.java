package com.amazonaws.javax.xml.stream.xerces.util;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class SymbolTable {
    protected static final int TABLE_SIZE = 173;
    protected Entry[] fBuckets;
    protected int fTableSize;
    protected char[] symbolAsArray;

    public SymbolTable() {
        this(TABLE_SIZE);
    }

    public SymbolTable(int i) {
        this.symbolAsArray = null;
        this.fBuckets = null;
        this.fTableSize = i;
        this.fBuckets = new Entry[this.fTableSize];
    }

    public String addSymbol(String str) {
        int iHash = hash(str);
        int i = iHash % this.fTableSize;
        int length = str.length();
        for (Entry entry = this.fBuckets[i]; entry != null; entry = entry.next) {
            if (length == entry.characters.length && iHash == entry.hashCode && str.regionMatches(0, entry.symbol, 0, length)) {
                this.symbolAsArray = entry.characters;
                return entry.symbol;
            }
        }
        Entry entry2 = new Entry(str, this.fBuckets[i]);
        entry2.hashCode = iHash;
        this.symbolAsArray = entry2.characters;
        this.fBuckets[i] = entry2;
        return entry2.symbol;
    }

    public String addSymbol(char[] cArr, int i, int i2) {
        int iHash = hash(cArr, i, i2);
        int i3 = iHash % this.fTableSize;
        for (Entry entry = this.fBuckets[i3]; entry != null; entry = entry.next) {
            if (i2 == entry.characters.length && iHash == entry.hashCode) {
                int i4 = 0;
                while (true) {
                    if (i4 < i2) {
                        if (cArr[i + i4] != entry.characters[i4]) {
                            break;
                        }
                        i4++;
                    } else {
                        this.symbolAsArray = entry.characters;
                        return entry.symbol;
                    }
                }
            }
        }
        Entry entry2 = new Entry(cArr, i, i2, this.fBuckets[i3]);
        this.fBuckets[i3] = entry2;
        entry2.hashCode = iHash;
        this.symbolAsArray = entry2.characters;
        return entry2.symbol;
    }

    public int hash(String str) {
        int length = str.length();
        int iCharAt = 0;
        for (int i = 0; i < length; i++) {
            iCharAt = (iCharAt * 37) + str.charAt(i);
        }
        return 134217727 & iCharAt;
    }

    public int hash(char[] cArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 = (i3 * 37) + cArr[i + i4];
        }
        return 134217727 & i3;
    }

    public boolean containsSymbol(String str) {
        int iHash = hash(str);
        int i = iHash % this.fTableSize;
        int length = str.length();
        for (Entry entry = this.fBuckets[i]; entry != null; entry = entry.next) {
            if (length == entry.characters.length && iHash == entry.hashCode && str.regionMatches(0, entry.symbol, 0, length)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsSymbol(char[] cArr, int i, int i2) {
        int iHash = hash(cArr, i, i2);
        for (Entry entry = this.fBuckets[iHash % this.fTableSize]; entry != null; entry = entry.next) {
            if (i2 == entry.characters.length && iHash == entry.hashCode) {
                for (int i3 = 0; i3 < i2; i3++) {
                    if (cArr[i + i3] == entry.characters[i3]) {
                    }
                }
                return true;
            }
        }
        return false;
    }

    public char[] getCharArray() {
        return this.symbolAsArray;
    }

    public final class Entry {
        public char[] characters;
        int hashCode = 0;
        public Entry next;
        public String symbol;

        public Entry(String str, Entry entry) {
            this.symbol = str.intern();
            this.characters = new char[str.length()];
            str.getChars(0, this.characters.length, this.characters, 0);
            this.next = entry;
        }

        public Entry(char[] cArr, int i, int i2, Entry entry) {
            this.characters = new char[i2];
            System.arraycopy(cArr, i, this.characters, 0, i2);
            this.symbol = new String(this.characters).intern();
            this.next = entry;
        }
    }
}
