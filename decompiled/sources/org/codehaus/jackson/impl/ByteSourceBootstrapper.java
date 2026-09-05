package org.codehaus.jackson.impl;

import com.flurry.android.Constants;
import java.io.ByteArrayInputStream;
import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.format.InputAccessor;
import org.codehaus.jackson.format.MatchStrength;
import org.codehaus.jackson.io.IOContext;
import org.codehaus.jackson.io.MergedStream;
import org.codehaus.jackson.io.UTF32Reader;
import org.codehaus.jackson.sym.BytesToNameCanonicalizer;
import org.codehaus.jackson.sym.CharsToNameCanonicalizer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class ByteSourceBootstrapper {
    static final byte UTF8_BOM_1 = -17;
    static final byte UTF8_BOM_2 = -69;
    static final byte UTF8_BOM_3 = -65;
    protected boolean _bigEndian;
    private final boolean _bufferRecyclable;
    protected int _bytesPerChar;
    final IOContext _context;
    final InputStream _in;
    final byte[] _inputBuffer;
    private int _inputEnd;
    protected int _inputProcessed;
    private int _inputPtr;

    public ByteSourceBootstrapper(IOContext iOContext, InputStream inputStream) {
        this._bigEndian = true;
        this._bytesPerChar = 0;
        this._context = iOContext;
        this._in = inputStream;
        this._inputBuffer = iOContext.allocReadIOBuffer();
        this._inputPtr = 0;
        this._inputEnd = 0;
        this._inputProcessed = 0;
        this._bufferRecyclable = true;
    }

    public ByteSourceBootstrapper(IOContext iOContext, byte[] bArr, int i, int i2) {
        this._bigEndian = true;
        this._bytesPerChar = 0;
        this._context = iOContext;
        this._in = null;
        this._inputBuffer = bArr;
        this._inputPtr = i;
        this._inputEnd = i + i2;
        this._inputProcessed = -i;
        this._bufferRecyclable = false;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0054  */
    public JsonEncoding detectEncoding() {
        JsonEncoding jsonEncoding;
        boolean z = true;
        if (ensureLoaded(4)) {
            int i = (this._inputBuffer[this._inputPtr] << 24) | ((this._inputBuffer[this._inputPtr + 1] & Constants.UNKNOWN) << 16) | ((this._inputBuffer[this._inputPtr + 2] & Constants.UNKNOWN) << 8) | (this._inputBuffer[this._inputPtr + 3] & Constants.UNKNOWN);
            if (!handleBOM(i) && !checkUTF32(i) && !checkUTF16(i >>> 16)) {
                z = false;
            }
        } else if (!ensureLoaded(2) || !checkUTF16(((this._inputBuffer[this._inputPtr] & Constants.UNKNOWN) << 8) | (this._inputBuffer[this._inputPtr + 1] & Constants.UNKNOWN))) {
            z = false;
        }
        if (!z) {
            jsonEncoding = JsonEncoding.UTF8;
        } else {
            switch (this._bytesPerChar) {
                case 1:
                    jsonEncoding = JsonEncoding.UTF8;
                    break;
                case 2:
                    jsonEncoding = !this._bigEndian ? JsonEncoding.UTF16_LE : JsonEncoding.UTF16_BE;
                    break;
                case 3:
                default:
                    throw new RuntimeException("Internal error");
                case 4:
                    jsonEncoding = !this._bigEndian ? JsonEncoding.UTF32_LE : JsonEncoding.UTF32_BE;
                    break;
            }
        }
        this._context.setEncoding(jsonEncoding);
        return jsonEncoding;
    }

    public Reader constructReader() {
        InputStream mergedStream;
        JsonEncoding encoding = this._context.getEncoding();
        switch (encoding) {
            case UTF32_BE:
            case UTF32_LE:
                return new UTF32Reader(this._context, this._in, this._inputBuffer, this._inputPtr, this._inputEnd, this._context.getEncoding().isBigEndian());
            case UTF16_BE:
            case UTF16_LE:
            case UTF8:
                InputStream inputStream = this._in;
                if (inputStream == null) {
                    mergedStream = new ByteArrayInputStream(this._inputBuffer, this._inputPtr, this._inputEnd);
                } else {
                    mergedStream = this._inputPtr < this._inputEnd ? new MergedStream(this._context, inputStream, this._inputBuffer, this._inputPtr, this._inputEnd) : inputStream;
                }
                return new InputStreamReader(mergedStream, encoding.getJavaName());
            default:
                throw new RuntimeException("Internal error");
        }
    }

    public JsonParser constructParser(int i, ObjectCodec objectCodec, BytesToNameCanonicalizer bytesToNameCanonicalizer, CharsToNameCanonicalizer charsToNameCanonicalizer) {
        JsonEncoding jsonEncodingDetectEncoding = detectEncoding();
        boolean zEnabledIn = JsonParser.Feature.CANONICALIZE_FIELD_NAMES.enabledIn(i);
        boolean zEnabledIn2 = JsonParser.Feature.INTERN_FIELD_NAMES.enabledIn(i);
        if (jsonEncodingDetectEncoding != JsonEncoding.UTF8 || !zEnabledIn) {
            return new ReaderBasedParser(this._context, i, constructReader(), objectCodec, charsToNameCanonicalizer.makeChild(zEnabledIn, zEnabledIn2));
        }
        return new Utf8StreamParser(this._context, i, this._in, objectCodec, bytesToNameCanonicalizer.makeChild(zEnabledIn, zEnabledIn2), this._inputBuffer, this._inputPtr, this._inputEnd, this._bufferRecyclable);
    }

    public static MatchStrength hasJSONFormat(InputAccessor inputAccessor) {
        if (!inputAccessor.hasMoreBytes()) {
            return MatchStrength.INCONCLUSIVE;
        }
        byte bNextByte = inputAccessor.nextByte();
        if (bNextByte == -17) {
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor.nextByte() != -69) {
                return MatchStrength.NO_MATCH;
            }
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor.nextByte() != -65) {
                return MatchStrength.NO_MATCH;
            }
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            bNextByte = inputAccessor.nextByte();
        }
        int iSkipSpace = skipSpace(inputAccessor, bNextByte);
        if (iSkipSpace < 0) {
            return MatchStrength.INCONCLUSIVE;
        }
        if (iSkipSpace == 123) {
            int iSkipSpace2 = skipSpace(inputAccessor);
            if (iSkipSpace2 < 0) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (iSkipSpace2 == 34 || iSkipSpace2 == 125) {
                return MatchStrength.SOLID_MATCH;
            }
            return MatchStrength.NO_MATCH;
        }
        if (iSkipSpace == 91) {
            int iSkipSpace3 = skipSpace(inputAccessor);
            if (iSkipSpace3 < 0) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (iSkipSpace3 == 93 || iSkipSpace3 == 91) {
                return MatchStrength.SOLID_MATCH;
            }
            return MatchStrength.SOLID_MATCH;
        }
        MatchStrength matchStrength = MatchStrength.WEAK_MATCH;
        if (iSkipSpace != 34) {
            if (iSkipSpace > 57 || iSkipSpace < 48) {
                if (iSkipSpace == 45) {
                    int iSkipSpace4 = skipSpace(inputAccessor);
                    if (iSkipSpace4 < 0) {
                        return MatchStrength.INCONCLUSIVE;
                    }
                    return (iSkipSpace4 > 57 || iSkipSpace4 < 48) ? MatchStrength.NO_MATCH : matchStrength;
                }
                if (iSkipSpace == 110) {
                    return tryMatch(inputAccessor, "ull", matchStrength);
                }
                if (iSkipSpace == 116) {
                    return tryMatch(inputAccessor, "rue", matchStrength);
                }
                if (iSkipSpace == 102) {
                    return tryMatch(inputAccessor, "alse", matchStrength);
                }
                return MatchStrength.NO_MATCH;
            }
            return matchStrength;
        }
        return matchStrength;
    }

    private static final MatchStrength tryMatch(InputAccessor inputAccessor, String str, MatchStrength matchStrength) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor.nextByte() != str.charAt(i)) {
                return MatchStrength.NO_MATCH;
            }
        }
        return matchStrength;
    }

    private static final int skipSpace(InputAccessor inputAccessor) {
        if (inputAccessor.hasMoreBytes()) {
            return skipSpace(inputAccessor, inputAccessor.nextByte());
        }
        return -1;
    }

    private static final int skipSpace(InputAccessor inputAccessor, byte b) {
        while (true) {
            int i = b & Constants.UNKNOWN;
            if (i == 32 || i == 13 || i == 10 || i == 9) {
                if (!inputAccessor.hasMoreBytes()) {
                    return -1;
                }
                b = inputAccessor.nextByte();
                int i2 = b & Constants.UNKNOWN;
            } else {
                return i;
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:12:0x003a  */
    /* JADX WARN: Code duplicated, block: B:14:0x003f  */
    /* JADX WARN: Code duplicated, block: B:15:0x004a  */
    /* JADX WARN: Code duplicated, block: B:17:0x0051  */
    /* JADX WARN: Code duplicated, block: B:18:0x005c  */
    /* JADX WARN: Code duplicated, block: B:6:0x000e  */
    private boolean handleBOM(int i) throws CharConversionException {
        int i2;
        switch (i) {
            case -16842752:
                reportWeirdUCS4("3412");
                i2 = i >>> 16;
                if (i2 == 65279) {
                    this._inputPtr += 2;
                    this._bytesPerChar = 2;
                    this._bigEndian = true;
                    return true;
                }
                if (i2 == 65534) {
                    this._inputPtr += 2;
                    this._bytesPerChar = 2;
                    this._bigEndian = false;
                    return true;
                }
                if ((i >>> 8) != 15711167) {
                    return false;
                }
                this._inputPtr += 3;
                this._bytesPerChar = 1;
                this._bigEndian = true;
                return true;
            case -131072:
                this._inputPtr += 4;
                this._bytesPerChar = 4;
                this._bigEndian = false;
                return true;
            case 65279:
                this._bigEndian = true;
                this._inputPtr += 4;
                this._bytesPerChar = 4;
                return true;
            case 65534:
                reportWeirdUCS4("2143");
                reportWeirdUCS4("3412");
                i2 = i >>> 16;
                if (i2 == 65279) {
                    this._inputPtr += 2;
                    this._bytesPerChar = 2;
                    this._bigEndian = true;
                    return true;
                }
                if (i2 == 65534) {
                    this._inputPtr += 2;
                    this._bytesPerChar = 2;
                    this._bigEndian = false;
                    return true;
                }
                if ((i >>> 8) != 15711167) {
                    return false;
                }
                this._inputPtr += 3;
                this._bytesPerChar = 1;
                this._bigEndian = true;
                return true;
            default:
                i2 = i >>> 16;
                if (i2 == 65279) {
                    this._inputPtr += 2;
                    this._bytesPerChar = 2;
                    this._bigEndian = true;
                    return true;
                }
                if (i2 == 65534) {
                    this._inputPtr += 2;
                    this._bytesPerChar = 2;
                    this._bigEndian = false;
                    return true;
                }
                if ((i >>> 8) != 15711167) {
                    return false;
                }
                this._inputPtr += 3;
                this._bytesPerChar = 1;
                this._bigEndian = true;
                return true;
        }
    }

    private boolean checkUTF32(int i) throws CharConversionException {
        if ((i >> 8) == 0) {
            this._bigEndian = true;
        } else if ((16777215 & i) == 0) {
            this._bigEndian = false;
        } else if (((-16711681) & i) == 0) {
            reportWeirdUCS4("3412");
        } else {
            if (((-65281) & i) != 0) {
                return false;
            }
            reportWeirdUCS4("2143");
        }
        this._bytesPerChar = 4;
        return true;
    }

    private boolean checkUTF16(int i) {
        if ((65280 & i) == 0) {
            this._bigEndian = true;
        } else {
            if ((i & 255) != 0) {
                return false;
            }
            this._bigEndian = false;
        }
        this._bytesPerChar = 2;
        return true;
    }

    private void reportWeirdUCS4(String str) throws CharConversionException {
        throw new CharConversionException("Unsupported UCS-4 endianness (" + str + ") detected");
    }

    protected boolean ensureLoaded(int i) throws IOException {
        int i2;
        int i3 = this._inputEnd - this._inputPtr;
        while (i3 < i) {
            if (this._in == null) {
                i2 = -1;
            } else {
                i2 = this._in.read(this._inputBuffer, this._inputEnd, this._inputBuffer.length - this._inputEnd);
            }
            if (i2 < 1) {
                return false;
            }
            this._inputEnd += i2;
            i3 = i2 + i3;
        }
        return true;
    }
}
