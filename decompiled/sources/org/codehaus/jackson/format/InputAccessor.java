package org.codehaus.jackson.format;

import java.io.EOFException;
import java.io.InputStream;
import org.codehaus.jackson.JsonFactory;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface InputAccessor {
    boolean hasMoreBytes();

    byte nextByte();

    void reset();

    public class Std implements InputAccessor {
        protected final byte[] _buffer;
        protected int _bufferedAmount;
        protected final InputStream _in;
        protected int _ptr;

        public Std(InputStream inputStream, byte[] bArr) {
            this._in = inputStream;
            this._buffer = bArr;
            this._bufferedAmount = 0;
        }

        public Std(byte[] bArr) {
            this._in = null;
            this._buffer = bArr;
            this._bufferedAmount = bArr.length;
        }

        @Override // org.codehaus.jackson.format.InputAccessor
        public boolean hasMoreBytes() {
            int i;
            if (this._ptr < this._bufferedAmount) {
                return true;
            }
            int length = this._buffer.length - this._ptr;
            if (length >= 1 && (i = this._in.read(this._buffer, this._ptr, length)) > 0) {
                this._bufferedAmount += i;
                return true;
            }
            return false;
        }

        @Override // org.codehaus.jackson.format.InputAccessor
        public byte nextByte() throws EOFException {
            if (this._ptr > (-this._bufferedAmount) && !hasMoreBytes()) {
                throw new EOFException("Could not read more than " + this._ptr + " bytes (max buffer size: " + this._buffer.length + ")");
            }
            byte[] bArr = this._buffer;
            int i = this._ptr;
            this._ptr = i + 1;
            return bArr[i];
        }

        @Override // org.codehaus.jackson.format.InputAccessor
        public void reset() {
            this._ptr = 0;
        }

        public DataFormatMatcher createMatcher(JsonFactory jsonFactory, MatchStrength matchStrength) {
            return new DataFormatMatcher(this._in, this._buffer, this._bufferedAmount, jsonFactory, matchStrength);
        }
    }
}
