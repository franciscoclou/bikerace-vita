package org.codehaus.jackson.impl;

import com.amazonaws.javax.xml.stream.XMLStreamConstants;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonStreamContext;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.io.NumberInput;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class JsonParserMinimalBase extends JsonParser {
    protected static final int INT_APOSTROPHE = 39;
    protected static final int INT_ASTERISK = 42;
    protected static final int INT_BACKSLASH = 92;
    protected static final int INT_COLON = 58;
    protected static final int INT_COMMA = 44;
    protected static final int INT_CR = 13;
    protected static final int INT_LBRACKET = 91;
    protected static final int INT_LCURLY = 123;
    protected static final int INT_LF = 10;
    protected static final int INT_QUOTE = 34;
    protected static final int INT_RBRACKET = 93;
    protected static final int INT_RCURLY = 125;
    protected static final int INT_SLASH = 47;
    protected static final int INT_SPACE = 32;
    protected static final int INT_TAB = 9;
    protected static final int INT_b = 98;
    protected static final int INT_f = 102;
    protected static final int INT_n = 110;
    protected static final int INT_r = 114;
    protected static final int INT_t = 116;
    protected static final int INT_u = 117;

    protected abstract void _handleEOF();

    @Override // org.codehaus.jackson.JsonParser, java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    @Override // org.codehaus.jackson.JsonParser
    public abstract byte[] getBinaryValue(Base64Variant base64Variant);

    @Override // org.codehaus.jackson.JsonParser
    public abstract String getCurrentName();

    @Override // org.codehaus.jackson.JsonParser
    public abstract JsonStreamContext getParsingContext();

    @Override // org.codehaus.jackson.JsonParser
    public abstract String getText();

    @Override // org.codehaus.jackson.JsonParser
    public abstract char[] getTextCharacters();

    @Override // org.codehaus.jackson.JsonParser
    public abstract int getTextLength();

    @Override // org.codehaus.jackson.JsonParser
    public abstract int getTextOffset();

    @Override // org.codehaus.jackson.JsonParser
    public abstract boolean hasTextCharacters();

    @Override // org.codehaus.jackson.JsonParser
    public abstract boolean isClosed();

    @Override // org.codehaus.jackson.JsonParser
    public abstract JsonToken nextToken();

    protected JsonParserMinimalBase() {
    }

    protected JsonParserMinimalBase(int i) {
        super(i);
    }

    @Override // org.codehaus.jackson.JsonParser
    public JsonParser skipChildren() {
        if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
            int i = 1;
            while (true) {
                JsonToken jsonTokenNextToken = nextToken();
                if (jsonTokenNextToken == null) {
                    _handleEOF();
                } else {
                    switch (jsonTokenNextToken) {
                        case START_OBJECT:
                        case START_ARRAY:
                            i++;
                            break;
                        case END_OBJECT:
                        case END_ARRAY:
                            i--;
                            if (i != 0) {
                            }
                            break;
                    }
                }
            }
        }
        return this;
    }

    @Override // org.codehaus.jackson.JsonParser
    public boolean getValueAsBoolean(boolean z) {
        if (this._currToken == null) {
            return z;
        }
        switch (this._currToken) {
            case VALUE_NUMBER_INT:
                return getIntValue() != 0;
            case VALUE_TRUE:
                return true;
            case VALUE_FALSE:
            case VALUE_NULL:
                return false;
            case VALUE_EMBEDDED_OBJECT:
                Object embeddedObject = getEmbeddedObject();
                if (embeddedObject instanceof Boolean) {
                    return ((Boolean) embeddedObject).booleanValue();
                }
                break;
            case VALUE_STRING:
                break;
            default:
                return z;
        }
        if ("true".equals(getText().trim())) {
            return true;
        }
        return z;
    }

    @Override // org.codehaus.jackson.JsonParser
    public int getValueAsInt(int i) {
        if (this._currToken != null) {
            switch (AnonymousClass1.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()]) {
                case 5:
                case XMLStreamConstants.DTD /* 11 */:
                    return getIntValue();
                case 6:
                    return 1;
                case 7:
                case 8:
                    return 0;
                case 9:
                    Object embeddedObject = getEmbeddedObject();
                    if (embeddedObject instanceof Number) {
                        return ((Number) embeddedObject).intValue();
                    }
                    return i;
                case 10:
                    return NumberInput.parseAsInt(getText(), i);
                default:
                    return i;
            }
        }
        return i;
    }

    @Override // org.codehaus.jackson.JsonParser
    public long getValueAsLong(long j) {
        if (this._currToken != null) {
            switch (AnonymousClass1.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()]) {
                case 5:
                case XMLStreamConstants.DTD /* 11 */:
                    return getLongValue();
                case 6:
                    return 1L;
                case 7:
                case 8:
                    return 0L;
                case 9:
                    Object embeddedObject = getEmbeddedObject();
                    if (embeddedObject instanceof Number) {
                        return ((Number) embeddedObject).longValue();
                    }
                    return j;
                case 10:
                    return NumberInput.parseAsLong(getText(), j);
                default:
                    return j;
            }
        }
        return j;
    }

    @Override // org.codehaus.jackson.JsonParser
    public double getValueAsDouble(double d) {
        if (this._currToken != null) {
            switch (AnonymousClass1.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()]) {
                case 5:
                case XMLStreamConstants.DTD /* 11 */:
                    return getDoubleValue();
                case 6:
                    return 1.0d;
                case 7:
                case 8:
                    return 0.0d;
                case 9:
                    Object embeddedObject = getEmbeddedObject();
                    if (embeddedObject instanceof Number) {
                        return ((Number) embeddedObject).doubleValue();
                    }
                    return d;
                case 10:
                    return NumberInput.parseAsDouble(getText(), d);
                default:
                    return d;
            }
        }
        return d;
    }

    protected void _reportUnexpectedChar(int i, String str) throws JsonParseException {
        String str2 = "Unexpected character (" + _getCharDesc(i) + ")";
        if (str != null) {
            str2 = str2 + ": " + str;
        }
        _reportError(str2);
    }

    protected void _reportInvalidEOF() throws JsonParseException {
        _reportInvalidEOF(" in " + this._currToken);
    }

    protected void _reportInvalidEOF(String str) throws JsonParseException {
        _reportError("Unexpected end-of-input" + str);
    }

    protected void _reportInvalidEOFInValue() throws JsonParseException {
        _reportInvalidEOF(" in a value");
    }

    protected void _throwInvalidSpace(int i) throws JsonParseException {
        _reportError("Illegal character (" + _getCharDesc((char) i) + "): only regular white space (\\r, \\n, \\t) is allowed between tokens");
    }

    protected void _throwUnquotedSpace(int i, String str) throws JsonParseException {
        if (!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS) || i >= 32) {
            _reportError("Illegal unquoted character (" + _getCharDesc((char) i) + "): has to be escaped using backslash to be included in " + str);
        }
    }

    protected char _handleUnrecognizedCharacterEscape(char c) throws JsonParseException {
        if (!isEnabled(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER) && (c != INT_APOSTROPHE || !isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES))) {
            _reportError("Unrecognized character escape " + _getCharDesc(c));
        }
        return c;
    }

    protected static final String _getCharDesc(int i) {
        char c = (char) i;
        if (Character.isISOControl(c)) {
            return "(CTRL-CHAR, code " + i + ")";
        }
        if (i > 255) {
            return "'" + c + "' (code " + i + " / 0x" + Integer.toHexString(i) + ")";
        }
        return "'" + c + "' (code " + i + ")";
    }

    protected final void _reportError(String str) throws JsonParseException {
        throw _constructError(str);
    }

    protected final void _wrapError(String str, Throwable th) throws JsonParseException {
        throw _constructError(str, th);
    }

    protected final void _throwInternal() {
        throw new RuntimeException("Internal error: this code path should never get executed");
    }

    protected final JsonParseException _constructError(String str, Throwable th) {
        return new JsonParseException(str, getCurrentLocation(), th);
    }
}
