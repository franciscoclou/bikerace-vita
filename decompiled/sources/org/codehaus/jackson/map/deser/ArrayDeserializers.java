package org.codehaus.jackson.map.deser;

import java.util.HashMap;
import org.codehaus.jackson.Base64Variants;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.util.ArrayBuilders;
import org.codehaus.jackson.map.util.ObjectBuffer;
import org.codehaus.jackson.type.JavaType;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ArrayDeserializers {
    static final ArrayDeserializers instance = new ArrayDeserializers();
    HashMap<JavaType, JsonDeserializer<Object>> _allDeserializers = new HashMap<>();

    private ArrayDeserializers() {
        add(Boolean.TYPE, new BooleanDeser());
        add(Byte.TYPE, new ByteDeser());
        add(Short.TYPE, new ShortDeser());
        add(Integer.TYPE, new IntDeser());
        add(Long.TYPE, new LongDeser());
        add(Float.TYPE, new FloatDeser());
        add(Double.TYPE, new DoubleDeser());
        add(String.class, new StringDeser());
        add(Character.TYPE, new CharDeser());
    }

    public static HashMap<JavaType, JsonDeserializer<Object>> getAll() {
        return instance._allDeserializers;
    }

    private void add(Class<?> cls, JsonDeserializer<?> jsonDeserializer) {
        this._allDeserializers.put(TypeFactory.defaultInstance().constructType(cls), jsonDeserializer);
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    abstract class ArrayDeser<T> extends StdDeserializer<T> {
        protected ArrayDeser(Class<T> cls) {
            super((Class<?>) cls);
        }

        @Override // org.codehaus.jackson.map.deser.StdDeserializer, org.codehaus.jackson.map.JsonDeserializer
        public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    final class StringDeser extends ArrayDeser<String[]> {
        public StringDeser() {
            super(String[].class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public String[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            int i;
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ObjectBuffer objectBufferLeaseObjectBuffer = deserializationContext.leaseObjectBuffer();
            Object[] objArrResetAndStart = objectBufferLeaseObjectBuffer.resetAndStart();
            int i2 = 0;
            while (true) {
                JsonToken jsonTokenNextToken = jsonParser.nextToken();
                if (jsonTokenNextToken != JsonToken.END_ARRAY) {
                    String text = jsonTokenNextToken == JsonToken.VALUE_NULL ? null : jsonParser.getText();
                    if (i2 >= objArrResetAndStart.length) {
                        objArrResetAndStart = objectBufferLeaseObjectBuffer.appendCompletedChunk(objArrResetAndStart);
                        i = 0;
                    } else {
                        i = i2;
                    }
                    i2 = i + 1;
                    objArrResetAndStart[i] = text;
                } else {
                    String[] strArr = (String[]) objectBufferLeaseObjectBuffer.completeAndClearBuffer(objArrResetAndStart, i2, String.class);
                    deserializationContext.returnObjectBuffer(objectBufferLeaseObjectBuffer);
                    return strArr;
                }
            }
        }

        private final String[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
            if (!deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                throw deserializationContext.mappingException(this._valueClass);
            }
            String[] strArr = new String[1];
            strArr[0] = jsonParser.getCurrentToken() == JsonToken.VALUE_NULL ? null : jsonParser.getText();
            return strArr;
        }
    }

    @JacksonStdImpl
    final class CharDeser extends ArrayDeser<char[]> {
        public CharDeser() {
            super(char[].class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public char[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_STRING) {
                char[] textCharacters = jsonParser.getTextCharacters();
                int textOffset = jsonParser.getTextOffset();
                int textLength = jsonParser.getTextLength();
                char[] cArr = new char[textLength];
                System.arraycopy(textCharacters, textOffset, cArr, 0, textLength);
                return cArr;
            }
            if (jsonParser.isExpectedStartArrayToken()) {
                StringBuilder sb = new StringBuilder(64);
                while (true) {
                    JsonToken jsonTokenNextToken = jsonParser.nextToken();
                    if (jsonTokenNextToken != JsonToken.END_ARRAY) {
                        if (jsonTokenNextToken != JsonToken.VALUE_STRING) {
                            throw deserializationContext.mappingException(Character.TYPE);
                        }
                        String text = jsonParser.getText();
                        if (text.length() != 1) {
                            throw JsonMappingException.from(jsonParser, "Can not convert a JSON String of length " + text.length() + " into a char element of char array");
                        }
                        sb.append(text.charAt(0));
                    } else {
                        return sb.toString().toCharArray();
                    }
                }
            } else {
                if (currentToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                    Object embeddedObject = jsonParser.getEmbeddedObject();
                    if (embeddedObject == null) {
                        return null;
                    }
                    if (embeddedObject instanceof char[]) {
                        return (char[]) embeddedObject;
                    }
                    if (embeddedObject instanceof String) {
                        return ((String) embeddedObject).toCharArray();
                    }
                    if (embeddedObject instanceof byte[]) {
                        return Base64Variants.getDefaultVariant().encode((byte[]) embeddedObject, false).toCharArray();
                    }
                }
                throw deserializationContext.mappingException(this._valueClass);
            }
        }
    }

    @JacksonStdImpl
    final class BooleanDeser extends ArrayDeser<boolean[]> {
        public BooleanDeser() {
            super(boolean[].class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public boolean[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            int i;
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.BooleanBuilder booleanBuilder = deserializationContext.getArrayBuilders().getBooleanBuilder();
            boolean[] zArrResetAndStart = booleanBuilder.resetAndStart();
            int i2 = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                boolean z_parseBooleanPrimitive = _parseBooleanPrimitive(jsonParser, deserializationContext);
                if (i2 >= zArrResetAndStart.length) {
                    i = 0;
                    zArrResetAndStart = booleanBuilder.appendCompletedChunk(zArrResetAndStart, i2);
                } else {
                    i = i2;
                }
                i2 = i + 1;
                zArrResetAndStart[i] = z_parseBooleanPrimitive;
            }
            return booleanBuilder.completeAndClearBuffer(zArrResetAndStart, i2);
        }

        private final boolean[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
            if (!deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                throw deserializationContext.mappingException(this._valueClass);
            }
            return new boolean[]{_parseBooleanPrimitive(jsonParser, deserializationContext)};
        }
    }

    @JacksonStdImpl
    final class ByteDeser extends ArrayDeser<byte[]> {
        public ByteDeser() {
            super(byte[].class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public byte[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException, JsonParseException {
            byte byteValue;
            int i;
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_STRING) {
                return jsonParser.getBinaryValue(deserializationContext.getBase64Variant());
            }
            if (currentToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                Object embeddedObject = jsonParser.getEmbeddedObject();
                if (embeddedObject == null) {
                    return null;
                }
                if (embeddedObject instanceof byte[]) {
                    return (byte[]) embeddedObject;
                }
            }
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.ByteBuilder byteBuilder = deserializationContext.getArrayBuilders().getByteBuilder();
            byte[] bArrResetAndStart = byteBuilder.resetAndStart();
            int i2 = 0;
            while (true) {
                JsonToken jsonTokenNextToken = jsonParser.nextToken();
                if (jsonTokenNextToken != JsonToken.END_ARRAY) {
                    if (jsonTokenNextToken == JsonToken.VALUE_NUMBER_INT || jsonTokenNextToken == JsonToken.VALUE_NUMBER_FLOAT) {
                        byteValue = jsonParser.getByteValue();
                    } else {
                        if (jsonTokenNextToken != JsonToken.VALUE_NULL) {
                            throw deserializationContext.mappingException(this._valueClass.getComponentType());
                        }
                        byteValue = 0;
                    }
                    if (i2 >= bArrResetAndStart.length) {
                        i = 0;
                        bArrResetAndStart = byteBuilder.appendCompletedChunk(bArrResetAndStart, i2);
                    } else {
                        i = i2;
                    }
                    i2 = i + 1;
                    bArrResetAndStart[i] = byteValue;
                } else {
                    return byteBuilder.completeAndClearBuffer(bArrResetAndStart, i2);
                }
            }
        }

        private final byte[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException, JsonParseException {
            byte byteValue;
            if (!deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                throw deserializationContext.mappingException(this._valueClass);
            }
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                byteValue = jsonParser.getByteValue();
            } else {
                if (currentToken != JsonToken.VALUE_NULL) {
                    throw deserializationContext.mappingException(this._valueClass.getComponentType());
                }
                byteValue = 0;
            }
            return new byte[]{byteValue};
        }
    }

    @JacksonStdImpl
    final class ShortDeser extends ArrayDeser<short[]> {
        public ShortDeser() {
            super(short[].class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public short[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            int i;
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.ShortBuilder shortBuilder = deserializationContext.getArrayBuilders().getShortBuilder();
            short[] sArrResetAndStart = shortBuilder.resetAndStart();
            int i2 = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                short s_parseShortPrimitive = _parseShortPrimitive(jsonParser, deserializationContext);
                if (i2 >= sArrResetAndStart.length) {
                    i = 0;
                    sArrResetAndStart = shortBuilder.appendCompletedChunk(sArrResetAndStart, i2);
                } else {
                    i = i2;
                }
                i2 = i + 1;
                sArrResetAndStart[i] = s_parseShortPrimitive;
            }
            return shortBuilder.completeAndClearBuffer(sArrResetAndStart, i2);
        }

        private final short[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
            if (!deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                throw deserializationContext.mappingException(this._valueClass);
            }
            return new short[]{_parseShortPrimitive(jsonParser, deserializationContext)};
        }
    }

    @JacksonStdImpl
    final class IntDeser extends ArrayDeser<int[]> {
        public IntDeser() {
            super(int[].class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public int[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            int i;
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.IntBuilder intBuilder = deserializationContext.getArrayBuilders().getIntBuilder();
            int[] iArrResetAndStart = intBuilder.resetAndStart();
            int i2 = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                int i_parseIntPrimitive = _parseIntPrimitive(jsonParser, deserializationContext);
                if (i2 >= iArrResetAndStart.length) {
                    i = 0;
                    iArrResetAndStart = intBuilder.appendCompletedChunk(iArrResetAndStart, i2);
                } else {
                    i = i2;
                }
                i2 = i + 1;
                iArrResetAndStart[i] = i_parseIntPrimitive;
            }
            return intBuilder.completeAndClearBuffer(iArrResetAndStart, i2);
        }

        private final int[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
            if (!deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                throw deserializationContext.mappingException(this._valueClass);
            }
            return new int[]{_parseIntPrimitive(jsonParser, deserializationContext)};
        }
    }

    @JacksonStdImpl
    final class LongDeser extends ArrayDeser<long[]> {
        public LongDeser() {
            super(long[].class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public long[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            int i;
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.LongBuilder longBuilder = deserializationContext.getArrayBuilders().getLongBuilder();
            long[] jArrResetAndStart = longBuilder.resetAndStart();
            int i2 = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                long j_parseLongPrimitive = _parseLongPrimitive(jsonParser, deserializationContext);
                if (i2 >= jArrResetAndStart.length) {
                    i = 0;
                    jArrResetAndStart = longBuilder.appendCompletedChunk(jArrResetAndStart, i2);
                } else {
                    i = i2;
                }
                i2 = i + 1;
                jArrResetAndStart[i] = j_parseLongPrimitive;
            }
            return longBuilder.completeAndClearBuffer(jArrResetAndStart, i2);
        }

        private final long[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
            if (!deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                throw deserializationContext.mappingException(this._valueClass);
            }
            return new long[]{_parseLongPrimitive(jsonParser, deserializationContext)};
        }
    }

    @JacksonStdImpl
    final class FloatDeser extends ArrayDeser<float[]> {
        public FloatDeser() {
            super(float[].class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public float[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            int i;
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.FloatBuilder floatBuilder = deserializationContext.getArrayBuilders().getFloatBuilder();
            float[] fArrResetAndStart = floatBuilder.resetAndStart();
            int i2 = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                float f_parseFloatPrimitive = _parseFloatPrimitive(jsonParser, deserializationContext);
                if (i2 >= fArrResetAndStart.length) {
                    i = 0;
                    fArrResetAndStart = floatBuilder.appendCompletedChunk(fArrResetAndStart, i2);
                } else {
                    i = i2;
                }
                i2 = i + 1;
                fArrResetAndStart[i] = f_parseFloatPrimitive;
            }
            return floatBuilder.completeAndClearBuffer(fArrResetAndStart, i2);
        }

        private final float[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
            if (!deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                throw deserializationContext.mappingException(this._valueClass);
            }
            return new float[]{_parseFloatPrimitive(jsonParser, deserializationContext)};
        }
    }

    @JacksonStdImpl
    final class DoubleDeser extends ArrayDeser<double[]> {
        public DoubleDeser() {
            super(double[].class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public double[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            int i;
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.DoubleBuilder doubleBuilder = deserializationContext.getArrayBuilders().getDoubleBuilder();
            double[] dArrResetAndStart = doubleBuilder.resetAndStart();
            int i2 = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                double d_parseDoublePrimitive = _parseDoublePrimitive(jsonParser, deserializationContext);
                if (i2 >= dArrResetAndStart.length) {
                    i = 0;
                    dArrResetAndStart = doubleBuilder.appendCompletedChunk(dArrResetAndStart, i2);
                } else {
                    i = i2;
                }
                i2 = i + 1;
                dArrResetAndStart[i] = d_parseDoublePrimitive;
            }
            return doubleBuilder.completeAndClearBuffer(dArrResetAndStart, i2);
        }

        private final double[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
            if (!deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                throw deserializationContext.mappingException(this._valueClass);
            }
            return new double[]{_parseDoublePrimitive(jsonParser, deserializationContext)};
        }
    }
}
