package org.codehaus.jackson.map.util;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class PrimitiveArrayBuilder<T> {
    static final int INITIAL_CHUNK_SIZE = 12;
    static final int MAX_CHUNK_SIZE = 262144;
    static final int SMALL_CHUNK_SIZE = 16384;
    Node<T> _bufferHead;
    Node<T> _bufferTail;
    int _bufferedEntryCount;
    T _freeBuffer;

    protected abstract T _constructArray(int i);

    protected PrimitiveArrayBuilder() {
    }

    public T resetAndStart() {
        _reset();
        return this._freeBuffer == null ? _constructArray(12) : this._freeBuffer;
    }

    public final T appendCompletedChunk(T t, int i) {
        int i2;
        Node<T> node = new Node<>(t, i);
        if (this._bufferHead == null) {
            this._bufferTail = node;
            this._bufferHead = node;
        } else {
            this._bufferTail.linkNext(node);
            this._bufferTail = node;
        }
        this._bufferedEntryCount += i;
        if (i < SMALL_CHUNK_SIZE) {
            i2 = i + i;
        } else {
            i2 = (i >> 2) + i;
        }
        return _constructArray(i2);
    }

    public T completeAndClearBuffer(T t, int i) {
        int i2 = i + this._bufferedEntryCount;
        T t_constructArray = _constructArray(i2);
        int iCopyData = 0;
        for (Node<T> next = this._bufferHead; next != null; next = next.next()) {
            iCopyData = next.copyData(t_constructArray, iCopyData);
        }
        System.arraycopy(t, 0, t_constructArray, iCopyData, i);
        int i3 = iCopyData + i;
        if (i3 != i2) {
            throw new IllegalStateException("Should have gotten " + i2 + " entries, got " + i3);
        }
        return t_constructArray;
    }

    protected void _reset() {
        if (this._bufferTail != null) {
            this._freeBuffer = this._bufferTail.getData();
        }
        this._bufferTail = null;
        this._bufferHead = null;
        this._bufferedEntryCount = 0;
    }

    final class Node<T> {
        final T _data;
        final int _dataLength;
        Node<T> _next;

        public Node(T t, int i) {
            this._data = t;
            this._dataLength = i;
        }

        public T getData() {
            return this._data;
        }

        public int copyData(T t, int i) {
            System.arraycopy(this._data, 0, t, i, this._dataLength);
            return this._dataLength + i;
        }

        public Node<T> next() {
            return this._next;
        }

        public void linkNext(Node<T> node) {
            if (this._next != null) {
                throw new IllegalStateException();
            }
            this._next = node;
        }
    }
}
