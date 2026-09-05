package com.amazonaws.javax.xml.stream.util;

import java.util.Iterator;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ReadOnlyIterator implements Iterator {
    Iterator iterator;

    public ReadOnlyIterator() {
        this.iterator = null;
    }

    public ReadOnlyIterator(Iterator it) {
        this.iterator = null;
        this.iterator = it;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.iterator != null) {
            return this.iterator.hasNext();
        }
        return false;
    }

    @Override // java.util.Iterator
    public Object next() {
        if (this.iterator != null) {
            return this.iterator.next();
        }
        return null;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Remove operation is not supported");
    }
}
