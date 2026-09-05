package org.codehaus.jackson.sym;

import java.util.Arrays;
import org.codehaus.jackson.util.InternCache;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class BytesToNameCanonicalizer {
    protected static final int DEFAULT_TABLE_SIZE = 64;
    static final int INITIAL_COLLISION_LEN = 32;
    static final int LAST_VALID_BUCKET = 254;
    static final int MAX_ENTRIES_FOR_REUSE = 6000;
    protected static final int MAX_TABLE_SIZE = 65536;
    static final int MIN_HASH_SIZE = 16;
    private int _collCount;
    private int _collEnd;
    private Bucket[] _collList;
    private boolean _collListShared;
    private int _count;
    final boolean _intern;
    private int[] _mainHash;
    private int _mainHashMask;
    private boolean _mainHashShared;
    private Name[] _mainNames;
    private boolean _mainNamesShared;
    private transient boolean _needRehash;
    final BytesToNameCanonicalizer _parent;

    public static BytesToNameCanonicalizer createRoot() {
        return new BytesToNameCanonicalizer(64, true);
    }

    public synchronized BytesToNameCanonicalizer makeChild(boolean z, boolean z2) {
        return new BytesToNameCanonicalizer(this, z2);
    }

    public void release() {
        if (maybeDirty() && this._parent != null) {
            this._parent.mergeChild(this);
            markAsShared();
        }
    }

    private BytesToNameCanonicalizer(int i, boolean z) {
        int i2 = 16;
        this._parent = null;
        this._intern = z;
        if (i < 16) {
            i = 16;
        } else if (((i - 1) & i) != 0) {
            while (i2 < i) {
                i2 += i2;
            }
            i = i2;
        }
        initTables(i);
    }

    private BytesToNameCanonicalizer(BytesToNameCanonicalizer bytesToNameCanonicalizer, boolean z) {
        this._parent = bytesToNameCanonicalizer;
        this._intern = z;
        this._count = bytesToNameCanonicalizer._count;
        this._mainHashMask = bytesToNameCanonicalizer._mainHashMask;
        this._mainHash = bytesToNameCanonicalizer._mainHash;
        this._mainNames = bytesToNameCanonicalizer._mainNames;
        this._collList = bytesToNameCanonicalizer._collList;
        this._collCount = bytesToNameCanonicalizer._collCount;
        this._collEnd = bytesToNameCanonicalizer._collEnd;
        this._needRehash = false;
        this._mainHashShared = true;
        this._mainNamesShared = true;
        this._collListShared = true;
    }

    private void initTables(int i) {
        this._count = 0;
        this._mainHash = new int[i];
        this._mainNames = new Name[i];
        this._mainHashShared = false;
        this._mainNamesShared = false;
        this._mainHashMask = i - 1;
        this._collListShared = true;
        this._collList = null;
        this._collEnd = 0;
        this._needRehash = false;
    }

    private synchronized void mergeChild(BytesToNameCanonicalizer bytesToNameCanonicalizer) {
        if (bytesToNameCanonicalizer._count > this._count) {
            if (bytesToNameCanonicalizer.size() > MAX_ENTRIES_FOR_REUSE) {
                initTables(64);
            } else {
                this._count = bytesToNameCanonicalizer._count;
                this._mainHash = bytesToNameCanonicalizer._mainHash;
                this._mainNames = bytesToNameCanonicalizer._mainNames;
                this._mainHashShared = true;
                this._mainNamesShared = true;
                this._mainHashMask = bytesToNameCanonicalizer._mainHashMask;
                this._collList = bytesToNameCanonicalizer._collList;
                this._collCount = bytesToNameCanonicalizer._collCount;
                this._collEnd = bytesToNameCanonicalizer._collEnd;
            }
        }
    }

    private void markAsShared() {
        this._mainHashShared = true;
        this._mainNamesShared = true;
        this._collListShared = true;
    }

    public int size() {
        return this._count;
    }

    public boolean maybeDirty() {
        return !this._mainHashShared;
    }

    public static Name getEmptyName() {
        return Name1.getEmptyName();
    }

    public Name findName(int i) {
        int iCalcHash = calcHash(i);
        int i2 = this._mainHashMask & iCalcHash;
        int i3 = this._mainHash[i2];
        if ((((i3 >> 8) ^ iCalcHash) << 8) == 0) {
            Name name = this._mainNames[i2];
            if (name == null) {
                return null;
            }
            if (name.equals(i)) {
                return name;
            }
        } else if (i3 == 0) {
            return null;
        }
        int i4 = i3 & 255;
        if (i4 <= 0) {
            return null;
        }
        Bucket bucket = this._collList[i4 - 1];
        if (bucket != null) {
            return bucket.find(iCalcHash, i, 0);
        }
        return null;
    }

    public Name findName(int i, int i2) {
        int iCalcHash = calcHash(i, i2);
        int i3 = this._mainHashMask & iCalcHash;
        int i4 = this._mainHash[i3];
        if ((((i4 >> 8) ^ iCalcHash) << 8) == 0) {
            Name name = this._mainNames[i3];
            if (name == null) {
                return null;
            }
            if (name.equals(i, i2)) {
                return name;
            }
        } else if (i4 == 0) {
            return null;
        }
        int i5 = i4 & 255;
        if (i5 <= 0) {
            return null;
        }
        Bucket bucket = this._collList[i5 - 1];
        if (bucket != null) {
            return bucket.find(iCalcHash, i, i2);
        }
        return null;
    }

    public Name findName(int[] iArr, int i) {
        int iCalcHash = calcHash(iArr, i);
        int i2 = this._mainHashMask & iCalcHash;
        int i3 = this._mainHash[i2];
        if ((((i3 >> 8) ^ iCalcHash) << 8) == 0) {
            Name name = this._mainNames[i2];
            if (name == null || name.equals(iArr, i)) {
                return name;
            }
        } else if (i3 == 0) {
            return null;
        }
        int i4 = i3 & 255;
        if (i4 > 0) {
            Bucket bucket = this._collList[i4 - 1];
            if (bucket != null) {
                return bucket.find(iCalcHash, iArr, i);
            }
        }
        return null;
    }

    public Name addName(String str, int i, int i2) {
        if (this._intern) {
            str = InternCache.instance.intern(str);
        }
        int iCalcHash = i2 == 0 ? calcHash(i) : calcHash(i, i2);
        Name nameConstructName = constructName(iCalcHash, str, i, i2);
        _addSymbol(iCalcHash, nameConstructName);
        return nameConstructName;
    }

    public Name addName(String str, int[] iArr, int i) {
        if (this._intern) {
            str = InternCache.instance.intern(str);
        }
        int iCalcHash = calcHash(iArr, i);
        Name nameConstructName = constructName(iCalcHash, str, iArr, i);
        _addSymbol(iCalcHash, nameConstructName);
        return nameConstructName;
    }

    public static final int calcHash(int i) {
        int i2 = (i >>> 16) ^ i;
        return i2 ^ (i2 >>> 8);
    }

    public static final int calcHash(int i, int i2) {
        int i3 = (i * 31) + i2;
        int i4 = i3 ^ (i3 >>> 16);
        return i4 ^ (i4 >>> 8);
    }

    public static final int calcHash(int[] iArr, int i) {
        int i2 = iArr[0];
        for (int i3 = 1; i3 < i; i3++) {
            i2 = (i2 * 31) + iArr[i3];
        }
        int i4 = (i2 >>> 16) ^ i2;
        return i4 ^ (i4 >>> 8);
    }

    private void _addSymbol(int i, Name name) {
        int iFindBestBucket;
        if (this._mainHashShared) {
            unshareMain();
        }
        if (this._needRehash) {
            rehash();
        }
        this._count++;
        int i2 = i & this._mainHashMask;
        if (this._mainNames[i2] == null) {
            this._mainHash[i2] = i << 8;
            if (this._mainNamesShared) {
                unshareNames();
            }
            this._mainNames[i2] = name;
        } else {
            if (this._collListShared) {
                unshareCollision();
            }
            this._collCount++;
            int i3 = this._mainHash[i2];
            int i4 = i3 & 255;
            if (i4 == 0) {
                if (this._collEnd <= LAST_VALID_BUCKET) {
                    iFindBestBucket = this._collEnd;
                    this._collEnd++;
                    if (iFindBestBucket >= this._collList.length) {
                        expandCollision();
                    }
                } else {
                    iFindBestBucket = findBestBucket();
                }
                this._mainHash[i2] = (i3 & (-256)) | (iFindBestBucket + 1);
            } else {
                iFindBestBucket = i4 - 1;
            }
            this._collList[iFindBestBucket] = new Bucket(name, this._collList[iFindBestBucket]);
        }
        int length = this._mainHash.length;
        if (this._count > (length >> 1)) {
            int i5 = length >> 2;
            if (this._count > length - i5) {
                this._needRehash = true;
            } else if (this._collCount >= i5) {
                this._needRehash = true;
            }
        }
    }

    private void rehash() {
        int iFindBestBucket;
        this._needRehash = false;
        this._mainNamesShared = false;
        int length = this._mainHash.length;
        int i = length + length;
        if (i > 65536) {
            nukeSymbols();
            return;
        }
        this._mainHash = new int[i];
        this._mainHashMask = i - 1;
        Name[] nameArr = this._mainNames;
        this._mainNames = new Name[i];
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            Name name = nameArr[i3];
            if (name != null) {
                i2++;
                int iHashCode = name.hashCode();
                int i4 = this._mainHashMask & iHashCode;
                this._mainNames[i4] = name;
                this._mainHash[i4] = iHashCode << 8;
            }
        }
        int i5 = this._collEnd;
        if (i5 != 0) {
            this._collCount = 0;
            this._collEnd = 0;
            this._collListShared = false;
            Bucket[] bucketArr = this._collList;
            this._collList = new Bucket[bucketArr.length];
            int i6 = 0;
            int i7 = i2;
            while (i6 < i5) {
                int i8 = i7;
                Bucket bucket = bucketArr[i6];
                while (bucket != null) {
                    int i9 = i8 + 1;
                    Name name2 = bucket._name;
                    int iHashCode2 = name2.hashCode();
                    int i10 = this._mainHashMask & iHashCode2;
                    int i11 = this._mainHash[i10];
                    if (this._mainNames[i10] == null) {
                        this._mainHash[i10] = iHashCode2 << 8;
                        this._mainNames[i10] = name2;
                    } else {
                        this._collCount++;
                        int i12 = i11 & 255;
                        if (i12 == 0) {
                            if (this._collEnd <= LAST_VALID_BUCKET) {
                                iFindBestBucket = this._collEnd;
                                this._collEnd++;
                                if (iFindBestBucket >= this._collList.length) {
                                    expandCollision();
                                }
                            } else {
                                iFindBestBucket = findBestBucket();
                            }
                            this._mainHash[i10] = (i11 & (-256)) | (iFindBestBucket + 1);
                        } else {
                            iFindBestBucket = i12 - 1;
                        }
                        this._collList[iFindBestBucket] = new Bucket(name2, this._collList[iFindBestBucket]);
                    }
                    bucket = bucket._next;
                    i8 = i9;
                }
                i6++;
                i7 = i8;
            }
            if (i7 != this._count) {
                throw new RuntimeException("Internal error: count after rehash " + i7 + "; should be " + this._count);
            }
        }
    }

    private void nukeSymbols() {
        this._count = 0;
        Arrays.fill(this._mainHash, 0);
        Arrays.fill(this._mainNames, (Object) null);
        Arrays.fill(this._collList, (Object) null);
        this._collCount = 0;
        this._collEnd = 0;
    }

    private int findBestBucket() {
        Bucket[] bucketArr = this._collList;
        int i = Integer.MAX_VALUE;
        int i2 = -1;
        int i3 = 0;
        int i4 = this._collEnd;
        while (i3 < i4) {
            int length = bucketArr[i3].length();
            if (length >= i) {
                length = i;
            } else {
                if (length == 1) {
                    return i3;
                }
                i2 = i3;
            }
            i3++;
            i = length;
        }
        return i2;
    }

    private void unshareMain() {
        int[] iArr = this._mainHash;
        int length = this._mainHash.length;
        this._mainHash = new int[length];
        System.arraycopy(iArr, 0, this._mainHash, 0, length);
        this._mainHashShared = false;
    }

    private void unshareCollision() {
        Bucket[] bucketArr = this._collList;
        if (bucketArr == null) {
            this._collList = new Bucket[32];
        } else {
            int length = bucketArr.length;
            this._collList = new Bucket[length];
            System.arraycopy(bucketArr, 0, this._collList, 0, length);
        }
        this._collListShared = false;
    }

    private void unshareNames() {
        Name[] nameArr = this._mainNames;
        int length = nameArr.length;
        this._mainNames = new Name[length];
        System.arraycopy(nameArr, 0, this._mainNames, 0, length);
        this._mainNamesShared = false;
    }

    private void expandCollision() {
        Bucket[] bucketArr = this._collList;
        int length = bucketArr.length;
        this._collList = new Bucket[length + length];
        System.arraycopy(bucketArr, 0, this._collList, 0, length);
    }

    private static Name constructName(int i, String str, int i2, int i3) {
        return i3 == 0 ? new Name1(str, i, i2) : new Name2(str, i, i2, i3);
    }

    private static Name constructName(int i, String str, int[] iArr, int i2) {
        if (i2 < 4) {
            switch (i2) {
                case 1:
                    return new Name1(str, i, iArr[0]);
                case 2:
                    return new Name2(str, i, iArr[0], iArr[1]);
                case 3:
                    return new Name3(str, i, iArr[0], iArr[1], iArr[2]);
            }
        }
        int[] iArr2 = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            iArr2[i3] = iArr[i3];
        }
        return new NameN(str, i, iArr2, i2);
    }

    final class Bucket {
        protected final Name _name;
        protected final Bucket _next;

        Bucket(Name name, Bucket bucket) {
            this._name = name;
            this._next = bucket;
        }

        public int length() {
            int i = 1;
            for (Bucket bucket = this._next; bucket != null; bucket = bucket._next) {
                i++;
            }
            return i;
        }

        public Name find(int i, int i2, int i3) {
            if (this._name.hashCode() == i && this._name.equals(i2, i3)) {
                return this._name;
            }
            for (Bucket bucket = this._next; bucket != null; bucket = bucket._next) {
                Name name = bucket._name;
                if (name.hashCode() == i && name.equals(i2, i3)) {
                    return name;
                }
            }
            return null;
        }

        public Name find(int i, int[] iArr, int i2) {
            if (this._name.hashCode() == i && this._name.equals(iArr, i2)) {
                return this._name;
            }
            for (Bucket bucket = this._next; bucket != null; bucket = bucket._next) {
                Name name = bucket._name;
                if (name.hashCode() == i && name.equals(iArr, i2)) {
                    return name;
                }
            }
            return null;
        }
    }
}
