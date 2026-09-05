package org.codehaus.jackson.impl;

import com.amazonaws.javax.xml.stream.XMLStreamConstants;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import com.facebook.internal.NativeProtocol;
import com.flurry.android.Constants;
import java.io.InputStream;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.io.IOContext;
import org.codehaus.jackson.sym.BytesToNameCanonicalizer;
import org.codehaus.jackson.sym.Name;
import org.codehaus.jackson.util.ByteArrayBuilder;
import org.codehaus.jackson.util.CharTypes;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class Utf8StreamParser extends StreamBasedParserBase {
    static final byte BYTE_LF = 10;
    protected ObjectCodec _objectCodec;
    private int _quad1;
    protected int[] _quadBuffer;
    protected final BytesToNameCanonicalizer _symbols;
    protected boolean _tokenIncomplete;
    private static final int[] sInputCodesUtf8 = CharTypes.getInputCodeUtf8();
    private static final int[] sInputCodesLatin1 = CharTypes.getInputCodeLatin1();

    public Utf8StreamParser(IOContext iOContext, int i, InputStream inputStream, ObjectCodec objectCodec, BytesToNameCanonicalizer bytesToNameCanonicalizer, byte[] bArr, int i2, int i3, boolean z) {
        super(iOContext, i, inputStream, bArr, i2, i3, z);
        this._quadBuffer = new int[16];
        this._tokenIncomplete = false;
        this._objectCodec = objectCodec;
        this._symbols = bytesToNameCanonicalizer;
        if (!JsonParser.Feature.CANONICALIZE_FIELD_NAMES.enabledIn(i)) {
            _throwInternal();
        }
    }

    @Override // org.codehaus.jackson.JsonParser
    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    @Override // org.codehaus.jackson.JsonParser
    public void setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
    }

    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
    public String getText() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != JsonToken.VALUE_STRING) {
            return _getText2(jsonToken);
        }
        if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
        }
        return this._textBuffer.contentsAsString();
    }

    protected final String _getText2(JsonToken jsonToken) {
        if (jsonToken == null) {
            return null;
        }
        switch (jsonToken) {
            case FIELD_NAME:
                return this._parsingContext.getCurrentName();
            case VALUE_STRING:
            case VALUE_NUMBER_INT:
            case VALUE_NUMBER_FLOAT:
                return this._textBuffer.contentsAsString();
            default:
                return jsonToken.asString();
        }
    }

    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
    public char[] getTextCharacters() {
        if (this._currToken != null) {
            switch (this._currToken) {
                case FIELD_NAME:
                    if (!this._nameCopied) {
                        String currentName = this._parsingContext.getCurrentName();
                        int length = currentName.length();
                        if (this._nameCopyBuffer == null) {
                            this._nameCopyBuffer = this._ioContext.allocNameCopyBuffer(length);
                        } else if (this._nameCopyBuffer.length < length) {
                            this._nameCopyBuffer = new char[length];
                        }
                        currentName.getChars(0, length, this._nameCopyBuffer, 0);
                        this._nameCopied = true;
                    }
                    return this._nameCopyBuffer;
                case VALUE_STRING:
                    if (this._tokenIncomplete) {
                        this._tokenIncomplete = false;
                        _finishString();
                    }
                    break;
                case VALUE_NUMBER_INT:
                case VALUE_NUMBER_FLOAT:
                    break;
                default:
                    return this._currToken.asCharArray();
            }
            return this._textBuffer.getTextBuffer();
        }
        return null;
    }

    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
    public int getTextLength() {
        if (this._currToken == null) {
            return 0;
        }
        switch (this._currToken) {
            case FIELD_NAME:
                return this._parsingContext.getCurrentName().length();
            case VALUE_STRING:
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    _finishString();
                }
                break;
            case VALUE_NUMBER_INT:
            case VALUE_NUMBER_FLOAT:
                break;
            default:
                return this._currToken.asCharArray().length;
        }
        return this._textBuffer.size();
    }

    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
    public int getTextOffset() {
        if (this._currToken == null) {
            return 0;
        }
        switch (this._currToken) {
            case FIELD_NAME:
            default:
                return 0;
            case VALUE_STRING:
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    _finishString();
                }
                break;
            case VALUE_NUMBER_INT:
            case VALUE_NUMBER_FLOAT:
                break;
        }
        return this._textBuffer.getTextOffset();
    }

    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
    public byte[] getBinaryValue(Base64Variant base64Variant) throws JsonParseException {
        if (this._currToken != JsonToken.VALUE_STRING && (this._currToken != JsonToken.VALUE_EMBEDDED_OBJECT || this._binaryValue == null)) {
            _reportError("Current token (" + this._currToken + ") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
        }
        if (this._tokenIncomplete) {
            try {
                this._binaryValue = _decodeBase64(base64Variant);
                this._tokenIncomplete = false;
            } catch (IllegalArgumentException e) {
                throw _constructError("Failed to decode VALUE_STRING as base64 (" + base64Variant + "): " + e.getMessage());
            }
        }
        return this._binaryValue;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser
    public JsonToken nextToken() throws JsonParseException {
        JsonToken numberText;
        this._numTypesValid = 0;
        if (this._currToken == JsonToken.FIELD_NAME) {
            return _nextAfterName();
        }
        if (this._tokenIncomplete) {
            _skipString();
        }
        int i_skipWSOrEnd = _skipWSOrEnd();
        if (i_skipWSOrEnd < 0) {
            close();
            this._currToken = null;
            return null;
        }
        this._tokenInputTotal = (this._currInputProcessed + ((long) this._inputPtr)) - 1;
        this._tokenInputRow = this._currInputRow;
        this._tokenInputCol = (this._inputPtr - this._currInputRowStart) - 1;
        this._binaryValue = null;
        if (i_skipWSOrEnd == 93) {
            if (!this._parsingContext.inArray()) {
                _reportMismatchedEndMarker(i_skipWSOrEnd, '}');
            }
            this._parsingContext = this._parsingContext.getParent();
            JsonToken jsonToken = JsonToken.END_ARRAY;
            this._currToken = jsonToken;
            return jsonToken;
        }
        if (i_skipWSOrEnd == 125) {
            if (!this._parsingContext.inObject()) {
                _reportMismatchedEndMarker(i_skipWSOrEnd, ']');
            }
            this._parsingContext = this._parsingContext.getParent();
            JsonToken jsonToken2 = JsonToken.END_OBJECT;
            this._currToken = jsonToken2;
            return jsonToken2;
        }
        if (this._parsingContext.expectComma()) {
            if (i_skipWSOrEnd != 44) {
                _reportUnexpectedChar(i_skipWSOrEnd, "was expecting comma to separate " + this._parsingContext.getTypeDesc() + " entries");
            }
            i_skipWSOrEnd = _skipWS();
        }
        if (!this._parsingContext.inObject()) {
            return _nextTokenNotInObject(i_skipWSOrEnd);
        }
        this._parsingContext.setCurrentName(_parseFieldName(i_skipWSOrEnd).getName());
        this._currToken = JsonToken.FIELD_NAME;
        int i_skipWS = _skipWS();
        if (i_skipWS != 58) {
            _reportUnexpectedChar(i_skipWS, "was expecting a colon to separate field name and value");
        }
        int i_skipWS2 = _skipWS();
        if (i_skipWS2 == 34) {
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
            return this._currToken;
        }
        switch (i_skipWS2) {
            case 45:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
                numberText = parseNumberText(i_skipWS2);
                break;
            case 91:
                numberText = JsonToken.START_ARRAY;
                break;
            case 93:
            case 125:
                _reportUnexpectedChar(i_skipWS2, "expected a value");
                _matchToken(JsonToken.VALUE_TRUE);
                numberText = JsonToken.VALUE_TRUE;
                break;
            case 102:
                _matchToken(JsonToken.VALUE_FALSE);
                numberText = JsonToken.VALUE_FALSE;
                break;
            case 110:
                _matchToken(JsonToken.VALUE_NULL);
                numberText = JsonToken.VALUE_NULL;
                break;
            case 116:
                _matchToken(JsonToken.VALUE_TRUE);
                numberText = JsonToken.VALUE_TRUE;
                break;
            case 123:
                numberText = JsonToken.START_OBJECT;
                break;
            default:
                numberText = _handleUnexpectedValue(i_skipWS2);
                break;
        }
        this._nextToken = numberText;
        return this._currToken;
    }

    private final JsonToken _nextTokenNotInObject(int i) {
        if (i == 34) {
            this._tokenIncomplete = true;
            JsonToken jsonToken = JsonToken.VALUE_STRING;
            this._currToken = jsonToken;
            return jsonToken;
        }
        switch (i) {
            case 45:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
                JsonToken numberText = parseNumberText(i);
                this._currToken = numberText;
                return numberText;
            case 91:
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
                JsonToken jsonToken2 = JsonToken.START_ARRAY;
                this._currToken = jsonToken2;
                return jsonToken2;
            case 93:
            case 125:
                _reportUnexpectedChar(i, "expected a value");
                break;
            case 102:
                _matchToken(JsonToken.VALUE_FALSE);
                JsonToken jsonToken3 = JsonToken.VALUE_FALSE;
                this._currToken = jsonToken3;
                return jsonToken3;
            case 110:
                _matchToken(JsonToken.VALUE_NULL);
                JsonToken jsonToken4 = JsonToken.VALUE_NULL;
                this._currToken = jsonToken4;
                return jsonToken4;
            case 116:
                break;
            case 123:
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
                JsonToken jsonToken5 = JsonToken.START_OBJECT;
                this._currToken = jsonToken5;
                return jsonToken5;
            default:
                JsonToken jsonToken_handleUnexpectedValue = _handleUnexpectedValue(i);
                this._currToken = jsonToken_handleUnexpectedValue;
                return jsonToken_handleUnexpectedValue;
        }
        _matchToken(JsonToken.VALUE_TRUE);
        JsonToken jsonToken6 = JsonToken.VALUE_TRUE;
        this._currToken = jsonToken6;
        return jsonToken6;
    }

    private final JsonToken _nextAfterName() {
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        this._currToken = jsonToken;
        return jsonToken;
    }

    @Override // org.codehaus.jackson.impl.JsonParserBase, org.codehaus.jackson.impl.JsonParserMinimalBase, org.codehaus.jackson.JsonParser, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        super.close();
        this._symbols.release();
    }

    protected final JsonToken parseNumberText(int i) {
        int i2;
        int i_verifyNoLeadingZeroes;
        int i3 = 1;
        char[] cArrEmptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        boolean z = i == 45;
        if (z) {
            cArrEmptyAndGetCurrentSegment[0] = '-';
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i4 = this._inputPtr;
            this._inputPtr = i4 + 1;
            i_verifyNoLeadingZeroes = bArr[i4] & Constants.UNKNOWN;
            if (i_verifyNoLeadingZeroes < 48 || i_verifyNoLeadingZeroes > 57) {
                return _handleInvalidNumberStart(i_verifyNoLeadingZeroes, true);
            }
            i2 = 1;
        } else {
            i2 = 0;
            i_verifyNoLeadingZeroes = i;
        }
        if (i_verifyNoLeadingZeroes == 48) {
            i_verifyNoLeadingZeroes = _verifyNoLeadingZeroes();
        }
        int i5 = i2 + 1;
        cArrEmptyAndGetCurrentSegment[i2] = (char) i_verifyNoLeadingZeroes;
        int length = this._inputPtr + cArrEmptyAndGetCurrentSegment.length;
        if (length > this._inputEnd) {
            length = this._inputEnd;
        }
        while (this._inputPtr < length) {
            byte[] bArr2 = this._inputBuffer;
            int i6 = this._inputPtr;
            this._inputPtr = i6 + 1;
            int i7 = bArr2[i6] & Constants.UNKNOWN;
            if (i7 >= 48 && i7 <= 57) {
                i3++;
                cArrEmptyAndGetCurrentSegment[i5] = (char) i7;
                i5++;
            } else {
                if (i7 == 46 || i7 == 101 || i7 == 69) {
                    return _parseFloatText(cArrEmptyAndGetCurrentSegment, i5, i7, z, i3);
                }
                this._inputPtr--;
                this._textBuffer.setCurrentLength(i5);
                return resetInt(z, i3);
            }
        }
        return _parserNumber2(cArrEmptyAndGetCurrentSegment, i5, z, i3);
    }

    private final JsonToken _parserNumber2(char[] cArr, int i, boolean z, int i2) {
        int i3 = i2;
        int i4 = i;
        char[] cArrFinishCurrentSegment = cArr;
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                this._textBuffer.setCurrentLength(i4);
                return resetInt(z, i3);
            }
            byte[] bArr = this._inputBuffer;
            int i5 = this._inputPtr;
            this._inputPtr = i5 + 1;
            int i6 = bArr[i5] & Constants.UNKNOWN;
            if (i6 > 57 || i6 < 48) {
                if (i6 == 46 || i6 == 101 || i6 == 69) {
                    return _parseFloatText(cArrFinishCurrentSegment, i4, i6, z, i3);
                }
                this._inputPtr--;
                this._textBuffer.setCurrentLength(i4);
                return resetInt(z, i3);
            }
            if (i4 >= cArrFinishCurrentSegment.length) {
                cArrFinishCurrentSegment = this._textBuffer.finishCurrentSegment();
                i4 = 0;
            }
            int i7 = i4;
            i4 = i7 + 1;
            cArrFinishCurrentSegment[i7] = (char) i6;
            i3++;
        }
    }

    private final int _verifyNoLeadingZeroes() {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            return 48;
        }
        int i = this._inputBuffer[this._inputPtr] & Constants.UNKNOWN;
        if (i < 48 || i > 57) {
            return 48;
        }
        if (!isEnabled(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
            reportInvalidNumber("Leading zeroes not allowed");
        }
        this._inputPtr++;
        if (i == 48) {
            do {
                if (this._inputPtr < this._inputEnd || loadMore()) {
                    i = this._inputBuffer[this._inputPtr] & Constants.UNKNOWN;
                    if (i < 48 || i > 57) {
                        return 48;
                    }
                    this._inputPtr++;
                } else {
                    return i;
                }
            } while (i == 48);
            return i;
        }
        return i;
    }

    private final JsonToken _parseFloatText(char[] cArr, int i, int i2, boolean z, int i3) {
        int i4;
        char[] cArrFinishCurrentSegment;
        int i5;
        int i6;
        int i7;
        boolean z2;
        int i8;
        int i9;
        int i10 = 0;
        boolean z3 = false;
        if (i2 != 46) {
            i4 = 0;
            cArrFinishCurrentSegment = cArr;
            i5 = i;
        } else {
            int i11 = i + 1;
            cArr[i] = (char) i2;
            while (true) {
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    z3 = true;
                    break;
                }
                byte[] bArr = this._inputBuffer;
                int i12 = this._inputPtr;
                this._inputPtr = i12 + 1;
                i2 = bArr[i12] & Constants.UNKNOWN;
                if (i2 < 48 || i2 > 57) {
                    break;
                }
                i10++;
                if (i11 >= cArr.length) {
                    cArr = this._textBuffer.finishCurrentSegment();
                    i11 = 0;
                }
                int i13 = i11;
                i11 = i13 + 1;
                cArr[i13] = (char) i2;
            }
            if (i10 == 0) {
                reportUnexpectedNumberChar(i2, "Decimal point not followed by a digit");
            }
            i4 = i10;
            i5 = i11;
            cArrFinishCurrentSegment = cArr;
        }
        int i14 = 0;
        if (i2 != 101 && i2 != 69) {
            z2 = z3;
            i8 = i5;
            i9 = 0;
        } else {
            if (i5 >= cArrFinishCurrentSegment.length) {
                cArrFinishCurrentSegment = this._textBuffer.finishCurrentSegment();
                i5 = 0;
            }
            int i15 = i5 + 1;
            cArrFinishCurrentSegment[i5] = (char) i2;
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr2 = this._inputBuffer;
            int i16 = this._inputPtr;
            this._inputPtr = i16 + 1;
            int i17 = bArr2[i16] & Constants.UNKNOWN;
            if (i17 == 45 || i17 == 43) {
                if (i15 >= cArrFinishCurrentSegment.length) {
                    cArrFinishCurrentSegment = this._textBuffer.finishCurrentSegment();
                    i6 = 0;
                } else {
                    i6 = i15;
                }
                int i18 = i6 + 1;
                cArrFinishCurrentSegment[i6] = (char) i17;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr3 = this._inputBuffer;
                int i19 = this._inputPtr;
                this._inputPtr = i19 + 1;
                i17 = bArr3[i19] & Constants.UNKNOWN;
                i7 = i18;
            } else {
                i7 = i15;
            }
            while (true) {
                if (i17 <= 57 && i17 >= 48) {
                    i14++;
                    if (i7 >= cArrFinishCurrentSegment.length) {
                        cArrFinishCurrentSegment = this._textBuffer.finishCurrentSegment();
                        i7 = 0;
                    }
                    int i20 = i7 + 1;
                    cArrFinishCurrentSegment[i7] = (char) i17;
                    if (this._inputPtr >= this._inputEnd && !loadMore()) {
                        i9 = i14;
                        z2 = true;
                        i8 = i20;
                        break;
                    }
                    byte[] bArr4 = this._inputBuffer;
                    int i21 = this._inputPtr;
                    this._inputPtr = i21 + 1;
                    i17 = bArr4[i21] & Constants.UNKNOWN;
                    i7 = i20;
                } else {
                    z2 = z3;
                    int i22 = i14;
                    i8 = i7;
                    i9 = i22;
                    break;
                }
            }
            if (i9 == 0) {
                reportUnexpectedNumberChar(i17, "Exponent indicator not followed by a digit");
            }
        }
        if (!z2) {
            this._inputPtr--;
        }
        this._textBuffer.setCurrentLength(i8);
        return resetFloat(z, i3, i4, i9);
    }

    protected final Name _parseFieldName(int i) {
        if (i != 34) {
            return _handleUnusualFieldName(i);
        }
        if (this._inputPtr + 9 > this._inputEnd) {
            return slowParseFieldName();
        }
        byte[] bArr = this._inputBuffer;
        int[] iArr = sInputCodesLatin1;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        int i3 = bArr[i2] & Constants.UNKNOWN;
        if (iArr[i3] == 0) {
            int i4 = this._inputPtr;
            this._inputPtr = i4 + 1;
            int i5 = bArr[i4] & Constants.UNKNOWN;
            if (iArr[i5] == 0) {
                int i6 = (i3 << 8) | i5;
                int i7 = this._inputPtr;
                this._inputPtr = i7 + 1;
                int i8 = bArr[i7] & Constants.UNKNOWN;
                if (iArr[i8] == 0) {
                    int i9 = (i6 << 8) | i8;
                    int i10 = this._inputPtr;
                    this._inputPtr = i10 + 1;
                    int i11 = bArr[i10] & Constants.UNKNOWN;
                    if (iArr[i11] == 0) {
                        int i12 = (i9 << 8) | i11;
                        int i13 = this._inputPtr;
                        this._inputPtr = i13 + 1;
                        int i14 = bArr[i13] & Constants.UNKNOWN;
                        if (iArr[i14] == 0) {
                            this._quad1 = i12;
                            return parseMediumFieldName(i14, iArr);
                        }
                        if (i14 == 34) {
                            return findName(i12, 4);
                        }
                        return parseFieldName(i12, i14, 4);
                    }
                    if (i11 == 34) {
                        return findName(i9, 3);
                    }
                    return parseFieldName(i9, i11, 3);
                }
                if (i8 == 34) {
                    return findName(i6, 2);
                }
                return parseFieldName(i6, i8, 2);
            }
            if (i5 == 34) {
                return findName(i3, 1);
            }
            return parseFieldName(i3, i5, 1);
        }
        if (i3 == 34) {
            return BytesToNameCanonicalizer.getEmptyName();
        }
        return parseFieldName(0, i3, 0);
    }

    protected final Name parseMediumFieldName(int i, int[] iArr) {
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        int i3 = bArr[i2] & Constants.UNKNOWN;
        if (iArr[i3] != 0) {
            if (i3 == 34) {
                return findName(this._quad1, i, 1);
            }
            return parseFieldName(this._quad1, i, i3, 1);
        }
        int i4 = i3 | (i << 8);
        byte[] bArr2 = this._inputBuffer;
        int i5 = this._inputPtr;
        this._inputPtr = i5 + 1;
        int i6 = bArr2[i5] & Constants.UNKNOWN;
        if (iArr[i6] != 0) {
            if (i6 == 34) {
                return findName(this._quad1, i4, 2);
            }
            return parseFieldName(this._quad1, i4, i6, 2);
        }
        int i7 = (i4 << 8) | i6;
        byte[] bArr3 = this._inputBuffer;
        int i8 = this._inputPtr;
        this._inputPtr = i8 + 1;
        int i9 = bArr3[i8] & Constants.UNKNOWN;
        if (iArr[i9] != 0) {
            if (i9 == 34) {
                return findName(this._quad1, i7, 3);
            }
            return parseFieldName(this._quad1, i7, i9, 3);
        }
        int i10 = (i7 << 8) | i9;
        byte[] bArr4 = this._inputBuffer;
        int i11 = this._inputPtr;
        this._inputPtr = i11 + 1;
        int i12 = bArr4[i11] & Constants.UNKNOWN;
        if (iArr[i12] != 0) {
            if (i12 == 34) {
                return findName(this._quad1, i10, 4);
            }
            return parseFieldName(this._quad1, i10, i12, 4);
        }
        this._quadBuffer[0] = this._quad1;
        this._quadBuffer[1] = i10;
        return parseLongFieldName(i12);
    }

    protected Name parseLongFieldName(int i) {
        int[] iArr = sInputCodesLatin1;
        int i2 = 2;
        int i3 = i;
        while (this._inputEnd - this._inputPtr >= 4) {
            byte[] bArr = this._inputBuffer;
            int i4 = this._inputPtr;
            this._inputPtr = i4 + 1;
            int i5 = bArr[i4] & Constants.UNKNOWN;
            if (iArr[i5] != 0) {
                if (i5 == 34) {
                    return findName(this._quadBuffer, i2, i3, 1);
                }
                return parseEscapedFieldName(this._quadBuffer, i2, i3, i5, 1);
            }
            int i6 = (i3 << 8) | i5;
            byte[] bArr2 = this._inputBuffer;
            int i7 = this._inputPtr;
            this._inputPtr = i7 + 1;
            int i8 = bArr2[i7] & Constants.UNKNOWN;
            if (iArr[i8] != 0) {
                if (i8 == 34) {
                    return findName(this._quadBuffer, i2, i6, 2);
                }
                return parseEscapedFieldName(this._quadBuffer, i2, i6, i8, 2);
            }
            int i9 = (i6 << 8) | i8;
            byte[] bArr3 = this._inputBuffer;
            int i10 = this._inputPtr;
            this._inputPtr = i10 + 1;
            int i11 = bArr3[i10] & Constants.UNKNOWN;
            if (iArr[i11] != 0) {
                if (i11 == 34) {
                    return findName(this._quadBuffer, i2, i9, 3);
                }
                return parseEscapedFieldName(this._quadBuffer, i2, i9, i11, 3);
            }
            int i12 = (i9 << 8) | i11;
            byte[] bArr4 = this._inputBuffer;
            int i13 = this._inputPtr;
            this._inputPtr = i13 + 1;
            i3 = bArr4[i13] & Constants.UNKNOWN;
            if (iArr[i3] != 0) {
                if (i3 == 34) {
                    return findName(this._quadBuffer, i2, i12, 4);
                }
                return parseEscapedFieldName(this._quadBuffer, i2, i12, i3, 4);
            }
            if (i2 >= this._quadBuffer.length) {
                this._quadBuffer = growArrayBy(this._quadBuffer, i2);
            }
            this._quadBuffer[i2] = i12;
            i2++;
        }
        return parseEscapedFieldName(this._quadBuffer, i2, 0, i3, 0);
    }

    protected Name slowParseFieldName() {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(": was expecting closing '\"' for name");
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        int i2 = bArr[i] & Constants.UNKNOWN;
        return i2 == 34 ? BytesToNameCanonicalizer.getEmptyName() : parseEscapedFieldName(this._quadBuffer, 0, 0, i2, 0);
    }

    private final Name parseFieldName(int i, int i2, int i3) {
        return parseEscapedFieldName(this._quadBuffer, 0, i, i2, i3);
    }

    private final Name parseFieldName(int i, int i2, int i3, int i4) {
        this._quadBuffer[0] = i;
        return parseEscapedFieldName(this._quadBuffer, 1, i2, i3, i4);
    }

    /* JADX WARN: Code duplicated, block: B:52:0x00d7 A[PHI: r13
      0x00d7: PHI (r13v2 int) = (r13v1 int), (r13v5 int) binds: [B:4:0x0006, B:20:0x0035] A[DONT_GENERATE, DONT_INLINE]] */
    protected Name parseEscapedFieldName(int[] iArr, int i, int i2, int i3, int i4) {
        int i5;
        int[] iArrGrowArrayBy;
        int i6;
        int[] iArrGrowArrayBy2;
        int i7;
        int i8;
        int[] iArr2;
        int i9;
        int i10;
        int i11;
        int[] iArr3;
        int[] iArr4 = sInputCodesLatin1;
        while (true) {
            if (iArr4[i3] == 0) {
                i5 = i2;
                iArrGrowArrayBy = iArr;
                i2 = i3;
            } else {
                if (i3 == 34) {
                    break;
                }
                if (i3 != 92) {
                    _throwUnquotedSpace(i3, "name");
                } else {
                    i3 = _decodeEscaped();
                }
                if (i3 > 127) {
                    if (i4 >= 4) {
                        if (i >= iArr.length) {
                            iArr = growArrayBy(iArr, iArr.length);
                            this._quadBuffer = iArr;
                        }
                        i6 = i + 1;
                        iArr[i] = i2;
                        i4 = 0;
                        i2 = 0;
                        iArrGrowArrayBy2 = iArr;
                    } else {
                        i6 = i;
                        iArrGrowArrayBy2 = iArr;
                    }
                    if (i3 < 2048) {
                        i10 = (i3 >> 6) | 192 | (i2 << 8);
                        iArr3 = iArrGrowArrayBy2;
                        i11 = i4 + 1;
                    } else {
                        int i12 = (i3 >> 12) | 224 | (i2 << 8);
                        int i13 = i4 + 1;
                        if (i13 >= 4) {
                            if (i6 >= iArrGrowArrayBy2.length) {
                                iArrGrowArrayBy2 = growArrayBy(iArrGrowArrayBy2, iArrGrowArrayBy2.length);
                                this._quadBuffer = iArrGrowArrayBy2;
                            }
                            iArrGrowArrayBy2[i6] = i12;
                            i8 = i6 + 1;
                            iArr2 = iArrGrowArrayBy2;
                            i9 = 0;
                            i7 = 0;
                        } else {
                            i7 = i12;
                            i8 = i6;
                            iArr2 = iArrGrowArrayBy2;
                            i9 = i13;
                        }
                        i10 = (i7 << 8) | ((i3 >> 6) & 63) | XMLChar.MASK_NCNAME;
                        i11 = i9 + 1;
                        int i14 = i8;
                        iArr3 = iArr2;
                        i6 = i14;
                    }
                    i2 = (i3 & 63) | XMLChar.MASK_NCNAME;
                    i4 = i11;
                    i = i6;
                    iArrGrowArrayBy = iArr3;
                    i5 = i10;
                } else {
                    i5 = i2;
                    iArrGrowArrayBy = iArr;
                    i2 = i3;
                }
            }
            if (i4 < 4) {
                i4++;
                i2 |= i5 << 8;
                iArr = iArrGrowArrayBy;
            } else {
                if (i >= iArrGrowArrayBy.length) {
                    iArrGrowArrayBy = growArrayBy(iArrGrowArrayBy, iArrGrowArrayBy.length);
                    this._quadBuffer = iArrGrowArrayBy;
                }
                iArrGrowArrayBy[i] = i5;
                i4 = 1;
                i++;
                iArr = iArrGrowArrayBy;
            }
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in field name");
            }
            byte[] bArr = this._inputBuffer;
            int i15 = this._inputPtr;
            this._inputPtr = i15 + 1;
            i3 = bArr[i15] & Constants.UNKNOWN;
        }
        if (i4 > 0) {
            if (i >= iArr.length) {
                iArr = growArrayBy(iArr, iArr.length);
                this._quadBuffer = iArr;
            }
            iArr[i] = i2;
            i++;
        }
        Name nameFindName = this._symbols.findName(iArr, i);
        if (nameFindName == null) {
            return addName(iArr, i, i4);
        }
        return nameFindName;
    }

    protected final Name _handleUnusualFieldName(int i) {
        int[] iArrGrowArrayBy;
        int i2;
        int i3;
        int i4;
        if (i == 39 && isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) {
            return _parseApostropheFieldName();
        }
        if (!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
            _reportUnexpectedChar(i, "was expecting double-quote to start field name");
        }
        int[] inputCodeUtf8JsNames = CharTypes.getInputCodeUtf8JsNames();
        if (inputCodeUtf8JsNames[i] != 0) {
            _reportUnexpectedChar(i, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int i5 = 0;
        int i6 = 0;
        int i7 = i;
        int i8 = 0;
        int[] iArrGrowArrayBy2 = this._quadBuffer;
        while (true) {
            if (i5 < 4) {
                int i9 = i5 + 1;
                i3 = i7 | (i6 << 8);
                i4 = i8;
                iArrGrowArrayBy = iArrGrowArrayBy2;
                i2 = i9;
            } else {
                if (i8 >= iArrGrowArrayBy2.length) {
                    iArrGrowArrayBy2 = growArrayBy(iArrGrowArrayBy2, iArrGrowArrayBy2.length);
                    this._quadBuffer = iArrGrowArrayBy2;
                }
                int i10 = i8 + 1;
                iArrGrowArrayBy2[i8] = i6;
                iArrGrowArrayBy = iArrGrowArrayBy2;
                i2 = 1;
                i3 = i7;
                i4 = i10;
            }
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in field name");
            }
            int i11 = this._inputBuffer[this._inputPtr] & Constants.UNKNOWN;
            if (inputCodeUtf8JsNames[i11] != 0) {
                break;
            }
            this._inputPtr++;
            i6 = i3;
            i5 = i2;
            iArrGrowArrayBy2 = iArrGrowArrayBy;
            i8 = i4;
            i7 = i11;
        }
        if (i2 > 0) {
            if (i4 >= iArrGrowArrayBy.length) {
                iArrGrowArrayBy = growArrayBy(iArrGrowArrayBy, iArrGrowArrayBy.length);
                this._quadBuffer = iArrGrowArrayBy;
            }
            iArrGrowArrayBy[i4] = i3;
            i4++;
        }
        Name nameFindName = this._symbols.findName(iArrGrowArrayBy, i4);
        return nameFindName == null ? addName(iArrGrowArrayBy, i4, i2) : nameFindName;
    }

    /* JADX WARN: Code duplicated, block: B:63:0x011a A[PHI: r5
      0x011a: PHI (r5v2 int) = (r5v1 int), (r5v1 int), (r5v12 int) binds: [B:22:0x0053, B:24:0x0057, B:29:0x0064] A[DONT_GENERATE, DONT_INLINE]] */
    protected final Name _parseApostropheFieldName() {
        int[] iArr;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int[] iArr2;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int[] iArr3;
        int i13;
        int i14;
        int i15;
        int[] iArr4;
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(": was expecting closing ''' for name");
        }
        byte[] bArr = this._inputBuffer;
        int i16 = this._inputPtr;
        this._inputPtr = i16 + 1;
        int i_decodeEscaped = bArr[i16] & Constants.UNKNOWN;
        if (i_decodeEscaped == 39) {
            return BytesToNameCanonicalizer.getEmptyName();
        }
        int[] iArrGrowArrayBy = this._quadBuffer;
        int[] iArr5 = sInputCodesLatin1;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        while (i_decodeEscaped != 39) {
            if (i_decodeEscaped == 34 || iArr5[i_decodeEscaped] == 0) {
                i2 = i18;
                i3 = i19;
                i4 = i17;
                i5 = i_decodeEscaped;
            } else {
                if (i_decodeEscaped != 92) {
                    _throwUnquotedSpace(i_decodeEscaped, "name");
                } else {
                    i_decodeEscaped = _decodeEscaped();
                }
                if (i_decodeEscaped > 127) {
                    if (i17 >= 4) {
                        if (i19 >= iArrGrowArrayBy.length) {
                            iArrGrowArrayBy = growArrayBy(iArrGrowArrayBy, iArrGrowArrayBy.length);
                            this._quadBuffer = iArrGrowArrayBy;
                        }
                        int i20 = i19 + 1;
                        iArrGrowArrayBy[i19] = i18;
                        i10 = 0;
                        i3 = i20;
                        i9 = 0;
                    } else {
                        int i21 = i17;
                        i9 = i18;
                        i3 = i19;
                        i10 = i21;
                    }
                    if (i_decodeEscaped < 2048) {
                        int i22 = i10 + 1;
                        i14 = (i9 << 8) | (i_decodeEscaped >> 6) | 192;
                        iArr4 = iArrGrowArrayBy;
                        i15 = i22;
                    } else {
                        int i23 = (i9 << 8) | (i_decodeEscaped >> 12) | 224;
                        int i24 = i10 + 1;
                        if (i24 >= 4) {
                            if (i3 >= iArrGrowArrayBy.length) {
                                iArrGrowArrayBy = growArrayBy(iArrGrowArrayBy, iArrGrowArrayBy.length);
                                this._quadBuffer = iArrGrowArrayBy;
                            }
                            iArrGrowArrayBy[i3] = i23;
                            i12 = i3 + 1;
                            iArr3 = iArrGrowArrayBy;
                            i13 = 0;
                            i11 = 0;
                        } else {
                            i11 = i23;
                            i12 = i3;
                            iArr3 = iArrGrowArrayBy;
                            i13 = i24;
                        }
                        i14 = (i11 << 8) | ((i_decodeEscaped >> 6) & 63) | XMLChar.MASK_NCNAME;
                        i15 = i13 + 1;
                        int i25 = i12;
                        iArr4 = iArr3;
                        i3 = i25;
                    }
                    i2 = i14;
                    i4 = i15;
                    iArrGrowArrayBy = iArr4;
                    i5 = (i_decodeEscaped & 63) | XMLChar.MASK_NCNAME;
                } else {
                    i2 = i18;
                    i3 = i19;
                    i4 = i17;
                    i5 = i_decodeEscaped;
                }
            }
            if (i4 < 4) {
                int i26 = i4 + 1;
                i7 = i5 | (i2 << 8);
                i8 = i3;
                iArr2 = iArrGrowArrayBy;
                i6 = i26;
            } else {
                if (i3 >= iArrGrowArrayBy.length) {
                    iArrGrowArrayBy = growArrayBy(iArrGrowArrayBy, iArrGrowArrayBy.length);
                    this._quadBuffer = iArrGrowArrayBy;
                }
                int i27 = i3 + 1;
                iArrGrowArrayBy[i3] = i2;
                iArr2 = iArrGrowArrayBy;
                i6 = 1;
                i7 = i5;
                i8 = i27;
            }
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in field name");
            }
            byte[] bArr2 = this._inputBuffer;
            int i28 = this._inputPtr;
            this._inputPtr = i28 + 1;
            i_decodeEscaped = bArr2[i28] & Constants.UNKNOWN;
            int i29 = i6;
            iArrGrowArrayBy = iArr2;
            i18 = i7;
            i19 = i8;
            i17 = i29;
        }
        if (i17 > 0) {
            if (i19 >= iArrGrowArrayBy.length) {
                iArrGrowArrayBy = growArrayBy(iArrGrowArrayBy, iArrGrowArrayBy.length);
                this._quadBuffer = iArrGrowArrayBy;
            }
            iArrGrowArrayBy[i19] = i18;
            iArr = iArrGrowArrayBy;
            i = i19 + 1;
        } else {
            iArr = iArrGrowArrayBy;
            i = i19;
        }
        Name nameFindName = this._symbols.findName(iArr, i);
        return nameFindName == null ? addName(iArr, i, i17) : nameFindName;
    }

    private final Name findName(int i, int i2) {
        Name nameFindName = this._symbols.findName(i);
        if (nameFindName == null) {
            this._quadBuffer[0] = i;
            return addName(this._quadBuffer, 1, i2);
        }
        return nameFindName;
    }

    private final Name findName(int i, int i2, int i3) {
        Name nameFindName = this._symbols.findName(i, i2);
        if (nameFindName == null) {
            this._quadBuffer[0] = i;
            this._quadBuffer[1] = i2;
            return addName(this._quadBuffer, 2, i3);
        }
        return nameFindName;
    }

    private final Name findName(int[] iArr, int i, int i2, int i3) {
        if (i >= iArr.length) {
            iArr = growArrayBy(iArr, iArr.length);
            this._quadBuffer = iArr;
        }
        int i4 = i + 1;
        iArr[i] = i2;
        Name nameFindName = this._symbols.findName(iArr, i4);
        if (nameFindName == null) {
            return addName(iArr, i4, i3);
        }
        return nameFindName;
    }

    /* JADX WARN: Code duplicated, block: B:52:0x0114 A[PHI: r2 r3
      0x0114: PHI (r2v6 int) = (r2v5 int), (r2v17 int) binds: [B:8:0x0033, B:30:0x00a4] A[DONT_GENERATE, DONT_INLINE]
      0x0114: PHI (r3v4 int) = (r3v3 int), (r3v9 int) binds: [B:8:0x0033, B:30:0x00a4] A[DONT_GENERATE, DONT_INLINE]] */
    private final Name addName(int[] iArr, int i, int i2) {
        int i3;
        char[] cArrExpandCurrentSegment;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9 = ((i << 2) - 4) + i2;
        if (i2 < 4) {
            i3 = iArr[i - 1];
            iArr[i - 1] = i3 << ((4 - i2) << 3);
        } else {
            i3 = 0;
        }
        char[] cArrEmptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int i10 = 0;
        int i11 = 0;
        while (i11 < i9) {
            int i12 = (iArr[i11 >> 2] >> ((3 - (i11 & 3)) << 3)) & 255;
            int i13 = i11 + 1;
            if (i12 <= 127) {
                cArrExpandCurrentSegment = cArrEmptyAndGetCurrentSegment;
                i4 = i12;
                i5 = i13;
                i6 = i10;
            } else {
                if ((i12 & 224) == 192) {
                    i7 = i12 & 31;
                    i8 = 1;
                } else if ((i12 & 240) == 224) {
                    i7 = i12 & 15;
                    i8 = 2;
                } else if ((i12 & 248) == 240) {
                    i7 = i12 & 7;
                    i8 = 3;
                } else {
                    _reportInvalidInitial(i12);
                    i7 = 1;
                    i8 = 1;
                }
                if (i13 + i8 > i9) {
                    _reportInvalidEOF(" in field name");
                }
                int i14 = iArr[i13 >> 2] >> ((3 - (i13 & 3)) << 3);
                i13++;
                if ((i14 & 192) != 128) {
                    _reportInvalidOther(i14);
                }
                i12 = (i7 << 6) | (i14 & 63);
                if (i8 > 1) {
                    int i15 = iArr[i13 >> 2] >> ((3 - (i13 & 3)) << 3);
                    i13++;
                    if ((i15 & 192) != 128) {
                        _reportInvalidOther(i15);
                    }
                    i12 = (i12 << 6) | (i15 & 63);
                    if (i8 > 2) {
                        int i16 = iArr[i13 >> 2] >> ((3 - (i13 & 3)) << 3);
                        i13++;
                        if ((i16 & 192) != 128) {
                            _reportInvalidOther(i16 & 255);
                        }
                        i12 = (i12 << 6) | (i16 & 63);
                    }
                }
                if (i8 > 2) {
                    int i17 = i12 - NativeProtocol.MESSAGE_GET_ACCESS_TOKEN_REQUEST;
                    if (i10 >= cArrEmptyAndGetCurrentSegment.length) {
                        cArrEmptyAndGetCurrentSegment = this._textBuffer.expandCurrentSegment();
                    }
                    cArrEmptyAndGetCurrentSegment[i10] = (char) (55296 + (i17 >> 10));
                    int i18 = (i17 & 1023) | 56320;
                    i5 = i13;
                    i6 = i10 + 1;
                    cArrExpandCurrentSegment = cArrEmptyAndGetCurrentSegment;
                    i4 = i18;
                } else {
                    cArrExpandCurrentSegment = cArrEmptyAndGetCurrentSegment;
                    i4 = i12;
                    i5 = i13;
                    i6 = i10;
                }
            }
            if (i6 >= cArrExpandCurrentSegment.length) {
                cArrExpandCurrentSegment = this._textBuffer.expandCurrentSegment();
            }
            i10 = i6 + 1;
            cArrExpandCurrentSegment[i6] = (char) i4;
            i11 = i5;
            cArrEmptyAndGetCurrentSegment = cArrExpandCurrentSegment;
        }
        String str = new String(cArrEmptyAndGetCurrentSegment, 0, i10);
        if (i2 < 4) {
            iArr[i - 1] = i3;
        }
        return this._symbols.addName(str, iArr, i);
    }

    @Override // org.codehaus.jackson.impl.JsonParserBase
    protected void _finishString() {
        int i = this._inputPtr;
        if (i >= this._inputEnd) {
            loadMoreGuaranteed();
            i = this._inputPtr;
        }
        char[] cArrEmptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = sInputCodesUtf8;
        int iMin = Math.min(this._inputEnd, cArrEmptyAndGetCurrentSegment.length + i);
        byte[] bArr = this._inputBuffer;
        int i2 = i;
        int i3 = 0;
        while (i2 < iMin) {
            int i4 = bArr[i2] & Constants.UNKNOWN;
            if (iArr[i4] != 0) {
                if (i4 != 34) {
                    break;
                }
                this._inputPtr = i2 + 1;
                this._textBuffer.setCurrentLength(i3);
                return;
            }
            cArrEmptyAndGetCurrentSegment[i3] = (char) i4;
            i3++;
            i2++;
        }
        this._inputPtr = i2;
        _finishString2(cArrEmptyAndGetCurrentSegment, i3);
    }

    private final void _finishString2(char[] cArr, int i) {
        int i2;
        int[] iArr = sInputCodesUtf8;
        byte[] bArr = this._inputBuffer;
        while (true) {
            int i3 = this._inputPtr;
            if (i3 >= this._inputEnd) {
                loadMoreGuaranteed();
                i3 = this._inputPtr;
            }
            if (i >= cArr.length) {
                cArr = this._textBuffer.finishCurrentSegment();
                i = 0;
            }
            int iMin = Math.min(this._inputEnd, (cArr.length - i) + i3);
            while (true) {
                if (i3 < iMin) {
                    int i4 = i3 + 1;
                    int i_decodeUtf8_3 = bArr[i3] & Constants.UNKNOWN;
                    if (iArr[i_decodeUtf8_3] != 0) {
                        this._inputPtr = i4;
                        if (i_decodeUtf8_3 != 34) {
                            switch (iArr[i_decodeUtf8_3]) {
                                case 1:
                                    i_decodeUtf8_3 = _decodeEscaped();
                                    break;
                                case 2:
                                    i_decodeUtf8_3 = _decodeUtf8_2(i_decodeUtf8_3);
                                    break;
                                case 3:
                                    if (this._inputEnd - this._inputPtr >= 2) {
                                        i_decodeUtf8_3 = _decodeUtf8_3fast(i_decodeUtf8_3);
                                    } else {
                                        i_decodeUtf8_3 = _decodeUtf8_3(i_decodeUtf8_3);
                                    }
                                    break;
                                case 4:
                                    int i_decodeUtf8_4 = _decodeUtf8_4(i_decodeUtf8_3);
                                    int i5 = i + 1;
                                    cArr[i] = (char) (55296 | (i_decodeUtf8_4 >> 10));
                                    if (i5 >= cArr.length) {
                                        cArr = this._textBuffer.finishCurrentSegment();
                                        i5 = 0;
                                    }
                                    i = i5;
                                    i_decodeUtf8_3 = (i_decodeUtf8_4 & 1023) | 56320;
                                    break;
                                default:
                                    if (i_decodeUtf8_3 < 32) {
                                        _throwUnquotedSpace(i_decodeUtf8_3, "string value");
                                    } else {
                                        _reportInvalidChar(i_decodeUtf8_3);
                                    }
                                    break;
                            }
                            if (i >= cArr.length) {
                                cArr = this._textBuffer.finishCurrentSegment();
                                i2 = 0;
                            } else {
                                i2 = i;
                            }
                            i = i2 + 1;
                            cArr[i2] = (char) i_decodeUtf8_3;
                            break;
                        }
                        this._textBuffer.setCurrentLength(i);
                        return;
                    }
                    cArr[i] = (char) i_decodeUtf8_3;
                    i3 = i4;
                    i++;
                } else {
                    this._inputPtr = i3;
                    break;
                }
            }
        }
    }

    protected void _skipString() {
        this._tokenIncomplete = false;
        int[] iArr = sInputCodesUtf8;
        byte[] bArr = this._inputBuffer;
        while (true) {
            int i = this._inputPtr;
            int i2 = this._inputEnd;
            if (i >= i2) {
                loadMoreGuaranteed();
                i = this._inputPtr;
                i2 = this._inputEnd;
            }
            while (true) {
                if (i < i2) {
                    int i3 = i + 1;
                    int i4 = bArr[i] & Constants.UNKNOWN;
                    if (iArr[i4] != 0) {
                        this._inputPtr = i3;
                        if (i4 != 34) {
                            switch (iArr[i4]) {
                                case 1:
                                    _decodeEscaped();
                                    break;
                                case 2:
                                    _skipUtf8_2(i4);
                                    break;
                                case 3:
                                    _skipUtf8_3(i4);
                                    break;
                                case 4:
                                    _skipUtf8_4(i4);
                                    break;
                                default:
                                    if (i4 < 32) {
                                        _throwUnquotedSpace(i4, "string value");
                                    } else {
                                        _reportInvalidChar(i4);
                                    }
                                    break;
                            }
                        }
                        return;
                    }
                    i = i3;
                } else {
                    this._inputPtr = i;
                    break;
                }
            }
        }
    }

    protected JsonToken _handleUnexpectedValue(int i) {
        switch (i) {
            case 39:
                if (isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) {
                    return _handleApostropheValue();
                }
                break;
            case 43:
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    _reportInvalidEOFInValue();
                }
                byte[] bArr = this._inputBuffer;
                int i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                return _handleInvalidNumberStart(bArr[i2] & Constants.UNKNOWN, false);
            case 78:
                if (_matchToken("NaN", 1)) {
                    if (isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                        return resetAsNaN("NaN", Double.NaN);
                    }
                    _reportError("Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
                }
                byte[] bArr2 = this._inputBuffer;
                int i3 = this._inputPtr;
                this._inputPtr = i3 + 1;
                _reportUnexpectedChar(bArr2[i3] & Constants.UNKNOWN, "expected 'NaN' or a valid value");
                break;
        }
        _reportUnexpectedChar(i, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
        return null;
    }

    protected JsonToken _handleApostropheValue() {
        int i_decodeUtf8_3;
        int i;
        char[] cArrEmptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = sInputCodesUtf8;
        byte[] bArr = this._inputBuffer;
        int i2 = 0;
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            if (i2 >= cArrEmptyAndGetCurrentSegment.length) {
                cArrEmptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                i2 = 0;
            }
            int i3 = this._inputEnd;
            int length = this._inputPtr + (cArrEmptyAndGetCurrentSegment.length - i2);
            if (length >= i3) {
                length = i3;
            }
            while (this._inputPtr < length) {
                int i4 = this._inputPtr;
                this._inputPtr = i4 + 1;
                int i5 = bArr[i4] & Constants.UNKNOWN;
                if (i5 != 39 && iArr[i5] == 0) {
                    cArrEmptyAndGetCurrentSegment[i2] = (char) i5;
                    i2++;
                } else {
                    if (i5 != 39) {
                        switch (iArr[i5]) {
                            case 1:
                                i_decodeUtf8_3 = i5 == 34 ? i5 : _decodeEscaped();
                                break;
                            case 2:
                                i_decodeUtf8_3 = _decodeUtf8_2(i5);
                                break;
                            case 3:
                                if (this._inputEnd - this._inputPtr >= 2) {
                                    i_decodeUtf8_3 = _decodeUtf8_3fast(i5);
                                } else {
                                    i_decodeUtf8_3 = _decodeUtf8_3(i5);
                                }
                                break;
                            case 4:
                                int i_decodeUtf8_4 = _decodeUtf8_4(i5);
                                int i6 = i2 + 1;
                                cArrEmptyAndGetCurrentSegment[i2] = (char) (55296 | (i_decodeUtf8_4 >> 10));
                                if (i6 >= cArrEmptyAndGetCurrentSegment.length) {
                                    cArrEmptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                                    i2 = 0;
                                } else {
                                    i2 = i6;
                                }
                                i_decodeUtf8_3 = 56320 | (i_decodeUtf8_4 & 1023);
                                break;
                            default:
                                if (i5 < 32) {
                                    _throwUnquotedSpace(i5, "string value");
                                }
                                _reportInvalidChar(i5);
                                break;
                        }
                        if (i2 >= cArrEmptyAndGetCurrentSegment.length) {
                            cArrEmptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                            i = 0;
                        } else {
                            i = i2;
                        }
                        i2 = i + 1;
                        cArrEmptyAndGetCurrentSegment[i] = (char) i_decodeUtf8_3;
                        break;
                    }
                    this._textBuffer.setCurrentLength(i2);
                    return JsonToken.VALUE_STRING;
                }
            }
        }
    }

    protected JsonToken _handleInvalidNumberStart(int i, boolean z) {
        if (i == 73) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOFInValue();
            }
            byte[] bArr = this._inputBuffer;
            int i2 = this._inputPtr;
            this._inputPtr = i2 + 1;
            i = bArr[i2];
            if (i == 78) {
                String str = z ? "-INF" : "+INF";
                if (_matchToken(str, 3)) {
                    if (isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                        return resetAsNaN(str, z ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
                    }
                    _reportError("Non-standard token '" + str + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
                }
            } else if (i == 110) {
                String str2 = z ? "-Infinity" : "+Infinity";
                if (_matchToken(str2, 3)) {
                    if (isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                        return resetAsNaN(str2, z ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
                    }
                    _reportError("Non-standard token '" + str2 + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
                }
            }
        }
        reportUnexpectedNumberChar(i, "expected digit (0-9) to follow minus sign, for valid numeric value");
        return null;
    }

    protected void _matchToken(JsonToken jsonToken) {
        byte[] bArrAsByteArray = jsonToken.asByteArray();
        int length = bArrAsByteArray.length;
        for (int i = 1; i < length; i++) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            if (bArrAsByteArray[i] != this._inputBuffer[this._inputPtr]) {
                _reportInvalidToken(jsonToken.asString().substring(0, i), "'null', 'true' or 'false'");
            }
            this._inputPtr++;
        }
    }

    protected final boolean _matchToken(String str, int i) {
        int length = str.length();
        do {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in a value");
            }
            if (this._inputBuffer[this._inputPtr] != str.charAt(i)) {
                _reportInvalidToken(str.substring(0, i), "'null', 'true', 'false' or NaN");
            }
            this._inputPtr++;
            i++;
        } while (i < length);
        if ((this._inputPtr < this._inputEnd || loadMore()) && Character.isJavaIdentifierPart((char) _decodeCharForError(this._inputBuffer[this._inputPtr] & Constants.UNKNOWN))) {
            this._inputPtr++;
            _reportInvalidToken(str.substring(0, i), "'null', 'true', 'false' or NaN");
        }
        return true;
    }

    protected void _reportInvalidToken(String str, String str2) {
        StringBuilder sb = new StringBuilder(str);
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                break;
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c_decodeCharForError = (char) _decodeCharForError(bArr[i]);
            if (!Character.isJavaIdentifierPart(c_decodeCharForError)) {
                break;
            }
            this._inputPtr++;
            sb.append(c_decodeCharForError);
        }
        _reportError("Unrecognized token '" + sb.toString() + "': was expecting " + str2);
    }

    private final int _skipWS() throws JsonParseException {
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                byte[] bArr = this._inputBuffer;
                int i = this._inputPtr;
                this._inputPtr = i + 1;
                int i2 = bArr[i] & Constants.UNKNOWN;
                if (i2 > 32) {
                    if (i2 != 47) {
                        return i2;
                    }
                    _skipComment();
                } else if (i2 != 32) {
                    if (i2 == 10) {
                        _skipLF();
                    } else if (i2 == 13) {
                        _skipCR();
                    } else if (i2 != 9) {
                        _throwInvalidSpace(i2);
                    }
                }
            } else {
                throw _constructError("Unexpected end-of-input within/between " + this._parsingContext.getTypeDesc() + " entries");
            }
        }
    }

    private final int _skipWSOrEnd() {
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                byte[] bArr = this._inputBuffer;
                int i = this._inputPtr;
                this._inputPtr = i + 1;
                int i2 = bArr[i] & Constants.UNKNOWN;
                if (i2 > 32) {
                    if (i2 == 47) {
                        _skipComment();
                    } else {
                        return i2;
                    }
                } else if (i2 != 32) {
                    if (i2 == 10) {
                        _skipLF();
                    } else if (i2 == 13) {
                        _skipCR();
                    } else if (i2 != 9) {
                        _throwInvalidSpace(i2);
                    }
                }
            } else {
                _handleEOF();
                return -1;
            }
        }
    }

    private final void _skipComment() {
        if (!isEnabled(JsonParser.Feature.ALLOW_COMMENTS)) {
            _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(" in a comment");
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        int i2 = bArr[i] & Constants.UNKNOWN;
        if (i2 == 47) {
            _skipCppComment();
        } else if (i2 == 42) {
            _skipCComment();
        } else {
            _reportUnexpectedChar(i2, "was expecting either '*' or '/' for a comment");
        }
    }

    private final void _skipCComment() {
        int[] inputCodeComment = CharTypes.getInputCodeComment();
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                byte[] bArr = this._inputBuffer;
                int i = this._inputPtr;
                this._inputPtr = i + 1;
                int i2 = bArr[i] & Constants.UNKNOWN;
                int i3 = inputCodeComment[i2];
                if (i3 != 0) {
                    switch (i3) {
                        case XMLStreamConstants.ATTRIBUTE /* 10 */:
                            _skipLF();
                            break;
                        case XMLStreamConstants.NAMESPACE /* 13 */:
                            _skipCR();
                            break;
                        case 42:
                            if (this._inputBuffer[this._inputPtr] == 47) {
                                this._inputPtr++;
                                return;
                            }
                            break;
                        default:
                            _reportInvalidChar(i2);
                            break;
                    }
                }
            } else {
                _reportInvalidEOF(" in a comment");
                return;
            }
        }
    }

    private final void _skipCppComment() {
        int[] inputCodeComment = CharTypes.getInputCodeComment();
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                byte[] bArr = this._inputBuffer;
                int i = this._inputPtr;
                this._inputPtr = i + 1;
                int i2 = bArr[i] & Constants.UNKNOWN;
                int i3 = inputCodeComment[i2];
                if (i3 != 0) {
                    switch (i3) {
                        case XMLStreamConstants.ATTRIBUTE /* 10 */:
                            _skipLF();
                            return;
                        case XMLStreamConstants.NAMESPACE /* 13 */:
                            _skipCR();
                            return;
                        case 42:
                            break;
                        default:
                            _reportInvalidChar(i2);
                            break;
                    }
                }
            } else {
                return;
            }
        }
    }

    protected final char _decodeEscaped() {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(" in character escape sequence");
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte b = bArr[i];
        switch (b) {
            case 34:
            case 47:
            case 92:
                return (char) b;
            case 98:
                return '\b';
            case 102:
                return '\f';
            case 110:
                return '\n';
            case 114:
                return '\r';
            case 116:
                return '\t';
            case 117:
                int i2 = 0;
                for (int i3 = 0; i3 < 4; i3++) {
                    if (this._inputPtr >= this._inputEnd && !loadMore()) {
                        _reportInvalidEOF(" in character escape sequence");
                    }
                    byte[] bArr2 = this._inputBuffer;
                    int i4 = this._inputPtr;
                    this._inputPtr = i4 + 1;
                    byte b2 = bArr2[i4];
                    int iCharToHex = CharTypes.charToHex(b2);
                    if (iCharToHex < 0) {
                        _reportUnexpectedChar(b2, "expected a hex-digit for character escape sequence");
                    }
                    i2 = (i2 << 4) | iCharToHex;
                }
                return (char) i2;
            default:
                return _handleUnrecognizedCharacterEscape((char) _decodeCharForError(b));
        }
    }

    protected int _decodeCharForError(int i) {
        char c;
        if (i < 0) {
            if ((i & 224) == 192) {
                i &= 31;
                c = 1;
            } else if ((i & 240) == 224) {
                i &= 15;
                c = 2;
            } else if ((i & 248) == 240) {
                i &= 7;
                c = 3;
            } else {
                _reportInvalidInitial(i & 255);
                c = 1;
            }
            int iNextByte = nextByte();
            if ((iNextByte & 192) != 128) {
                _reportInvalidOther(iNextByte & 255);
            }
            int i2 = (i << 6) | (iNextByte & 63);
            if (c > 1) {
                int iNextByte2 = nextByte();
                if ((iNextByte2 & 192) != 128) {
                    _reportInvalidOther(iNextByte2 & 255);
                }
                int i3 = (i2 << 6) | (iNextByte2 & 63);
                if (c > 2) {
                    int iNextByte3 = nextByte();
                    if ((iNextByte3 & 192) != 128) {
                        _reportInvalidOther(iNextByte3 & 255);
                    }
                    return (i3 << 6) | (iNextByte3 & 63);
                }
                return i3;
            }
            return i2;
        }
        return i;
    }

    private final int _decodeUtf8_2(int i) {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & Constants.UNKNOWN, this._inputPtr);
        }
        return (b & 63) | ((i & 31) << 6);
    }

    private final int _decodeUtf8_3(int i) {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        int i2 = i & 15;
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b = bArr[i3];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & Constants.UNKNOWN, this._inputPtr);
        }
        int i4 = (i2 << 6) | (b & 63);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i5 = this._inputPtr;
        this._inputPtr = i5 + 1;
        byte b2 = bArr2[i5];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & Constants.UNKNOWN, this._inputPtr);
        }
        return (i4 << 6) | (b2 & 63);
    }

    private final int _decodeUtf8_3fast(int i) {
        int i2 = i & 15;
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b = bArr[i3];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & Constants.UNKNOWN, this._inputPtr);
        }
        int i4 = (i2 << 6) | (b & 63);
        byte[] bArr2 = this._inputBuffer;
        int i5 = this._inputPtr;
        this._inputPtr = i5 + 1;
        byte b2 = bArr2[i5];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & Constants.UNKNOWN, this._inputPtr);
        }
        return (i4 << 6) | (b2 & 63);
    }

    private final int _decodeUtf8_4(int i) {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & Constants.UNKNOWN, this._inputPtr);
        }
        int i3 = (b & 63) | ((i & 7) << 6);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i4 = this._inputPtr;
        this._inputPtr = i4 + 1;
        byte b2 = bArr2[i4];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & Constants.UNKNOWN, this._inputPtr);
        }
        int i5 = (i3 << 6) | (b2 & 63);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr3 = this._inputBuffer;
        int i6 = this._inputPtr;
        this._inputPtr = i6 + 1;
        byte b3 = bArr3[i6];
        if ((b3 & 192) != 128) {
            _reportInvalidOther(b3 & Constants.UNKNOWN, this._inputPtr);
        }
        return ((i5 << 6) | (b3 & 63)) - NativeProtocol.MESSAGE_GET_ACCESS_TOKEN_REQUEST;
    }

    private final void _skipUtf8_2(int i) {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & Constants.UNKNOWN, this._inputPtr);
        }
    }

    private final void _skipUtf8_3(int i) {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & Constants.UNKNOWN, this._inputPtr);
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b2 = bArr2[i3];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & Constants.UNKNOWN, this._inputPtr);
        }
    }

    private final void _skipUtf8_4(int i) {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & Constants.UNKNOWN, this._inputPtr);
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b2 = bArr2[i3];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & Constants.UNKNOWN, this._inputPtr);
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr3 = this._inputBuffer;
        int i4 = this._inputPtr;
        this._inputPtr = i4 + 1;
        byte b3 = bArr3[i4];
        if ((b3 & 192) != 128) {
            _reportInvalidOther(b3 & Constants.UNKNOWN, this._inputPtr);
        }
    }

    protected final void _skipCR() {
        if ((this._inputPtr < this._inputEnd || loadMore()) && this._inputBuffer[this._inputPtr] == 10) {
            this._inputPtr++;
        }
        this._currInputRow++;
        this._currInputRowStart = this._inputPtr;
    }

    protected final void _skipLF() {
        this._currInputRow++;
        this._currInputRowStart = this._inputPtr;
    }

    private int nextByte() {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        return bArr[i] & Constants.UNKNOWN;
    }

    protected void _reportInvalidChar(int i) {
        if (i < 32) {
            _throwInvalidSpace(i);
        }
        _reportInvalidInitial(i);
    }

    protected void _reportInvalidInitial(int i) {
        _reportError("Invalid UTF-8 start byte 0x" + Integer.toHexString(i));
    }

    protected void _reportInvalidOther(int i) {
        _reportError("Invalid UTF-8 middle byte 0x" + Integer.toHexString(i));
    }

    protected void _reportInvalidOther(int i, int i2) {
        this._inputPtr = i2;
        _reportInvalidOther(i);
    }

    public static int[] growArrayBy(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        int length = iArr.length;
        int[] iArr2 = new int[length + i];
        System.arraycopy(iArr, 0, iArr2, 0, length);
        return iArr2;
    }

    @Override // org.codehaus.jackson.impl.JsonParserBase
    protected byte[] _decodeBase64(Base64Variant base64Variant) {
        ByteArrayBuilder byteArrayBuilder_getByteArrayBuilder = _getByteArrayBuilder();
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            int i2 = bArr[i] & Constants.UNKNOWN;
            if (i2 > 32) {
                int iDecodeBase64Char = base64Variant.decodeBase64Char(i2);
                if (iDecodeBase64Char < 0) {
                    if (i2 == 34) {
                        return byteArrayBuilder_getByteArrayBuilder.toByteArray();
                    }
                    iDecodeBase64Char = _decodeBase64Escape(base64Variant, i2, 0);
                    if (iDecodeBase64Char < 0) {
                        continue;
                    }
                }
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr2 = this._inputBuffer;
                int i3 = this._inputPtr;
                this._inputPtr = i3 + 1;
                int i4 = bArr2[i3] & Constants.UNKNOWN;
                int iDecodeBase64Char2 = base64Variant.decodeBase64Char(i4);
                if (iDecodeBase64Char2 < 0) {
                    iDecodeBase64Char2 = _decodeBase64Escape(base64Variant, i4, 1);
                }
                int i5 = iDecodeBase64Char2 | (iDecodeBase64Char << 6);
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr3 = this._inputBuffer;
                int i6 = this._inputPtr;
                this._inputPtr = i6 + 1;
                int i7 = bArr3[i6] & Constants.UNKNOWN;
                int iDecodeBase64Char3 = base64Variant.decodeBase64Char(i7);
                if (iDecodeBase64Char3 < 0) {
                    if (iDecodeBase64Char3 != -2) {
                        if (i7 == 34 && !base64Variant.usesPadding()) {
                            byteArrayBuilder_getByteArrayBuilder.append(i5 >> 4);
                            return byteArrayBuilder_getByteArrayBuilder.toByteArray();
                        }
                        iDecodeBase64Char3 = _decodeBase64Escape(base64Variant, i7, 2);
                    }
                    if (iDecodeBase64Char3 == -2) {
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        byte[] bArr4 = this._inputBuffer;
                        int i8 = this._inputPtr;
                        this._inputPtr = i8 + 1;
                        int i9 = bArr4[i8] & Constants.UNKNOWN;
                        if (!base64Variant.usesPaddingChar(i9)) {
                            throw reportInvalidChar(base64Variant, i9, 3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
                        }
                        byteArrayBuilder_getByteArrayBuilder.append(i5 >> 4);
                    }
                }
                int i10 = (i5 << 6) | iDecodeBase64Char3;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr5 = this._inputBuffer;
                int i11 = this._inputPtr;
                this._inputPtr = i11 + 1;
                int i12 = bArr5[i11] & Constants.UNKNOWN;
                int iDecodeBase64Char4 = base64Variant.decodeBase64Char(i12);
                if (iDecodeBase64Char4 < 0) {
                    if (iDecodeBase64Char4 != -2) {
                        if (i12 == 34 && !base64Variant.usesPadding()) {
                            byteArrayBuilder_getByteArrayBuilder.appendTwoBytes(i10 >> 2);
                            return byteArrayBuilder_getByteArrayBuilder.toByteArray();
                        }
                        iDecodeBase64Char4 = _decodeBase64Escape(base64Variant, i12, 3);
                    }
                    if (iDecodeBase64Char4 == -2) {
                        byteArrayBuilder_getByteArrayBuilder.appendTwoBytes(i10 >> 2);
                    }
                }
                byteArrayBuilder_getByteArrayBuilder.appendThreeBytes(iDecodeBase64Char4 | (i10 << 6));
            }
        }
    }

    private final int _decodeBase64Escape(Base64Variant base64Variant, int i, int i2) {
        if (i != 92) {
            throw reportInvalidChar(base64Variant, i, i2);
        }
        char c_decodeEscaped = _decodeEscaped();
        if (c_decodeEscaped <= ' ' && i2 == 0) {
            return -1;
        }
        int iDecodeBase64Char = base64Variant.decodeBase64Char((int) c_decodeEscaped);
        if (iDecodeBase64Char < 0) {
            throw reportInvalidChar(base64Variant, c_decodeEscaped, i2);
        }
        return iDecodeBase64Char;
    }

    protected IllegalArgumentException reportInvalidChar(Base64Variant base64Variant, int i, int i2) {
        return reportInvalidChar(base64Variant, i, i2, null);
    }

    protected IllegalArgumentException reportInvalidChar(Base64Variant base64Variant, int i, int i2, String str) {
        String str2;
        if (i <= 32) {
            str2 = "Illegal white space character (code 0x" + Integer.toHexString(i) + ") as character #" + (i2 + 1) + " of 4-char base64 unit: can only used between units";
        } else if (base64Variant.usesPaddingChar(i)) {
            str2 = "Unexpected padding character ('" + base64Variant.getPaddingChar() + "') as character #" + (i2 + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character";
        } else if (!Character.isDefined(i) || Character.isISOControl(i)) {
            str2 = "Illegal character (code 0x" + Integer.toHexString(i) + ") in base64 content";
        } else {
            str2 = "Illegal character '" + ((char) i) + "' (code 0x" + Integer.toHexString(i) + ") in base64 content";
        }
        if (str != null) {
            str2 = str2 + ": " + str;
        }
        return new IllegalArgumentException(str2);
    }
}
