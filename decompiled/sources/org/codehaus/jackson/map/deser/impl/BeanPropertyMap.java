package org.codehaus.jackson.map.deser.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.codehaus.jackson.map.deser.SettableBeanProperty;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class BeanPropertyMap {
    private final Bucket[] _buckets;
    private final int _hashMask;
    private final int _size;

    public BeanPropertyMap(Collection<SettableBeanProperty> collection) {
        this._size = collection.size();
        int iFindSize = findSize(this._size);
        this._hashMask = iFindSize - 1;
        Bucket[] bucketArr = new Bucket[iFindSize];
        for (SettableBeanProperty settableBeanProperty : collection) {
            String name = settableBeanProperty.getName();
            int iHashCode = name.hashCode() & this._hashMask;
            bucketArr[iHashCode] = new Bucket(bucketArr[iHashCode], name, settableBeanProperty);
        }
        this._buckets = bucketArr;
    }

    public void assignIndexes() {
        int i = 0;
        for (Bucket bucket : this._buckets) {
            while (bucket != null) {
                bucket.value.assignIndex(i);
                bucket = bucket.next;
                i++;
            }
        }
    }

    private static final int findSize(int i) {
        int i2 = 2;
        while (i2 < (i <= 32 ? i + i : (i >> 2) + i)) {
            i2 += i2;
        }
        return i2;
    }

    public int size() {
        return this._size;
    }

    public Iterator<SettableBeanProperty> allProperties() {
        return new IteratorImpl(this._buckets);
    }

    public SettableBeanProperty find(String str) {
        int iHashCode = this._hashMask & str.hashCode();
        Bucket bucket = this._buckets[iHashCode];
        if (bucket == null) {
            return null;
        }
        if (bucket.key == str) {
            return bucket.value;
        }
        do {
            bucket = bucket.next;
            if (bucket == null) {
                return _findWithEquals(str, iHashCode);
            }
        } while (bucket.key != str);
        return bucket.value;
    }

    public void replace(SettableBeanProperty settableBeanProperty) {
        String name = settableBeanProperty.getName();
        int iHashCode = name.hashCode() & (this._buckets.length - 1);
        boolean z = false;
        Bucket bucket = null;
        for (Bucket bucket2 = this._buckets[iHashCode]; bucket2 != null; bucket2 = bucket2.next) {
            if (!z && bucket2.key.equals(name)) {
                z = true;
            } else {
                bucket = new Bucket(bucket, bucket2.key, bucket2.value);
            }
        }
        if (!z) {
            throw new NoSuchElementException("No entry '" + settableBeanProperty + "' found, can't replace");
        }
        this._buckets[iHashCode] = new Bucket(bucket, name, settableBeanProperty);
    }

    private SettableBeanProperty _findWithEquals(String str, int i) {
        for (Bucket bucket = this._buckets[i]; bucket != null; bucket = bucket.next) {
            if (str.equals(bucket.key)) {
                return bucket.value;
            }
        }
        return null;
    }

    final class Bucket {
        public final String key;
        public final Bucket next;
        public final SettableBeanProperty value;

        public Bucket(Bucket bucket, String str, SettableBeanProperty settableBeanProperty) {
            this.next = bucket;
            this.key = str;
            this.value = settableBeanProperty;
        }
    }

    final class IteratorImpl implements Iterator<SettableBeanProperty> {
        private final Bucket[] _buckets;
        private Bucket _currentBucket;
        private int _nextBucketIndex;

        public IteratorImpl(Bucket[] bucketArr) {
            int i;
            this._buckets = bucketArr;
            int i2 = 0;
            int length = this._buckets.length;
            while (i2 < length) {
                i = i2 + 1;
                Bucket bucket = this._buckets[i2];
                if (bucket != null) {
                    this._currentBucket = bucket;
                    this._nextBucketIndex = i;
                }
                i2 = i;
            }
            i = i2;
            this._nextBucketIndex = i;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this._currentBucket != null;
        }

        @Override // java.util.Iterator
        public SettableBeanProperty next() {
            Bucket bucket = this._currentBucket;
            if (bucket == null) {
                throw new NoSuchElementException();
            }
            Bucket bucket2 = bucket.next;
            while (bucket2 == null && this._nextBucketIndex < this._buckets.length) {
                Bucket[] bucketArr = this._buckets;
                int i = this._nextBucketIndex;
                this._nextBucketIndex = i + 1;
                bucket2 = bucketArr[i];
            }
            this._currentBucket = bucket2;
            return bucket.value;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
