package org.codehaus.jackson;

import java.io.Closeable;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.codehaus.jackson.io.CharacterEscapes;
import org.codehaus.jackson.io.SerializedString;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class JsonGenerator implements Closeable, Versioned {
    protected PrettyPrinter _cfgPrettyPrinter;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    public abstract void copyCurrentEvent(JsonParser jsonParser);

    public abstract void copyCurrentStructure(JsonParser jsonParser);

    public abstract JsonGenerator disable(Feature feature);

    public abstract JsonGenerator enable(Feature feature);

    public abstract void flush();

    public abstract ObjectCodec getCodec();

    public abstract JsonStreamContext getOutputContext();

    public abstract boolean isClosed();

    public abstract boolean isEnabled(Feature feature);

    public abstract JsonGenerator setCodec(ObjectCodec objectCodec);

    public abstract JsonGenerator useDefaultPrettyPrinter();

    public abstract void writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2);

    public abstract void writeBoolean(boolean z);

    public abstract void writeEndArray();

    public abstract void writeEndObject();

    public abstract void writeFieldName(String str);

    public abstract void writeNull();

    public abstract void writeNumber(double d);

    public abstract void writeNumber(float f);

    public abstract void writeNumber(int i);

    public abstract void writeNumber(long j);

    public abstract void writeNumber(String str);

    public abstract void writeNumber(BigDecimal bigDecimal);

    public abstract void writeNumber(BigInteger bigInteger);

    public abstract void writeObject(Object obj);

    public abstract void writeRaw(char c);

    public abstract void writeRaw(String str);

    public abstract void writeRaw(String str, int i, int i2);

    public abstract void writeRaw(char[] cArr, int i, int i2);

    public abstract void writeRawUTF8String(byte[] bArr, int i, int i2);

    public abstract void writeRawValue(String str);

    public abstract void writeRawValue(String str, int i, int i2);

    public abstract void writeRawValue(char[] cArr, int i, int i2);

    public abstract void writeStartArray();

    public abstract void writeStartObject();

    public abstract void writeString(String str);

    public abstract void writeString(char[] cArr, int i, int i2);

    public abstract void writeTree(JsonNode jsonNode);

    public abstract void writeUTF8String(byte[] bArr, int i, int i2);

    public enum Feature {
        AUTO_CLOSE_TARGET(true),
        AUTO_CLOSE_JSON_CONTENT(true),
        QUOTE_FIELD_NAMES(true),
        QUOTE_NON_NUMERIC_NUMBERS(true),
        WRITE_NUMBERS_AS_STRINGS(false),
        FLUSH_PASSED_TO_STREAM(true),
        ESCAPE_NON_ASCII(false);

        final boolean _defaultState;
        final int _mask = 1 << ordinal();

        public static int collectDefaults() {
            int mask = 0;
            for (Feature feature : values()) {
                if (feature.enabledByDefault()) {
                    mask |= feature.getMask();
                }
            }
            return mask;
        }

        Feature(boolean z) {
            this._defaultState = z;
        }

        public boolean enabledByDefault() {
            return this._defaultState;
        }

        public int getMask() {
            return this._mask;
        }
    }

    protected JsonGenerator() {
    }

    public void setSchema(FormatSchema formatSchema) {
        throw new UnsupportedOperationException("Generator of type " + getClass().getName() + " does not support schema of type '" + formatSchema.getSchemaType() + "'");
    }

    public boolean canUseSchema(FormatSchema formatSchema) {
        return false;
    }

    @Override // org.codehaus.jackson.Versioned
    public Version version() {
        return Version.unknownVersion();
    }

    public Object getOutputTarget() {
        return null;
    }

    public JsonGenerator configure(Feature feature, boolean z) {
        if (z) {
            enable(feature);
        } else {
            disable(feature);
        }
        return this;
    }

    @Deprecated
    public void enableFeature(Feature feature) {
        enable(feature);
    }

    @Deprecated
    public void disableFeature(Feature feature) {
        disable(feature);
    }

    @Deprecated
    public void setFeature(Feature feature, boolean z) {
        configure(feature, z);
    }

    @Deprecated
    public boolean isFeatureEnabled(Feature feature) {
        return isEnabled(feature);
    }

    public JsonGenerator setPrettyPrinter(PrettyPrinter prettyPrinter) {
        this._cfgPrettyPrinter = prettyPrinter;
        return this;
    }

    public JsonGenerator setHighestNonEscapedChar(int i) {
        return this;
    }

    public int getHighestEscapedChar() {
        return 0;
    }

    public CharacterEscapes getCharacterEscapes() {
        return null;
    }

    public JsonGenerator setCharacterEscapes(CharacterEscapes characterEscapes) {
        return this;
    }

    public void writeFieldName(SerializedString serializedString) {
        writeFieldName(serializedString.getValue());
    }

    public void writeFieldName(SerializableString serializableString) {
        writeFieldName(serializableString.getValue());
    }

    public void writeString(SerializableString serializableString) {
        writeString(serializableString.getValue());
    }

    public void writeBinary(byte[] bArr, int i, int i2) {
        writeBinary(Base64Variants.getDefaultVariant(), bArr, i, i2);
    }

    public void writeBinary(byte[] bArr) {
        writeBinary(Base64Variants.getDefaultVariant(), bArr, 0, bArr.length);
    }

    public void writeStringField(String str, String str2) {
        writeFieldName(str);
        writeString(str2);
    }

    public final void writeBooleanField(String str, boolean z) {
        writeFieldName(str);
        writeBoolean(z);
    }

    public final void writeNullField(String str) {
        writeFieldName(str);
        writeNull();
    }

    public final void writeNumberField(String str, int i) {
        writeFieldName(str);
        writeNumber(i);
    }

    public final void writeNumberField(String str, long j) {
        writeFieldName(str);
        writeNumber(j);
    }

    public final void writeNumberField(String str, double d) {
        writeFieldName(str);
        writeNumber(d);
    }

    public final void writeNumberField(String str, float f) {
        writeFieldName(str);
        writeNumber(f);
    }

    public final void writeNumberField(String str, BigDecimal bigDecimal) {
        writeFieldName(str);
        writeNumber(bigDecimal);
    }

    public final void writeBinaryField(String str, byte[] bArr) {
        writeFieldName(str);
        writeBinary(bArr);
    }

    public final void writeArrayFieldStart(String str) {
        writeFieldName(str);
        writeStartArray();
    }

    public final void writeObjectFieldStart(String str) {
        writeFieldName(str);
        writeStartObject();
    }

    public final void writeObjectField(String str, Object obj) {
        writeFieldName(str);
        writeObject(obj);
    }
}
