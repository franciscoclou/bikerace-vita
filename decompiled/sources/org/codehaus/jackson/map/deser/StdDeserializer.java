package org.codehaus.jackson.map.deser;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.codehaus.jackson.Base64Variants;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.io.NumberInput;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ResolvableDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.util.TokenBuffer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class StdDeserializer<T> extends JsonDeserializer<T> {
    protected final Class<?> _valueClass;

    protected StdDeserializer(Class<?> cls) {
        this._valueClass = cls;
    }

    protected StdDeserializer(JavaType javaType) {
        this._valueClass = javaType == null ? null : javaType.getRawClass();
    }

    public Class<?> getValueClass() {
        return this._valueClass;
    }

    public JavaType getValueType() {
        return null;
    }

    protected boolean isDefaultSerializer(JsonDeserializer<?> jsonDeserializer) {
        return (jsonDeserializer == null || jsonDeserializer.getClass().getAnnotation(JacksonStdImpl.class) == null) ? false : true;
    }

    @Override // org.codehaus.jackson.map.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    protected final boolean _parseBooleanPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_TRUE) {
            return true;
        }
        if (currentToken != JsonToken.VALUE_FALSE && currentToken != JsonToken.VALUE_NULL) {
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                return jsonParser.getIntValue() != 0;
            }
            if (currentToken == JsonToken.VALUE_STRING) {
                String strTrim = jsonParser.getText().trim();
                if ("true".equals(strTrim)) {
                    return true;
                }
                if ("false".equals(strTrim) || strTrim.length() == 0) {
                    return Boolean.FALSE.booleanValue();
                }
                throw deserializationContext.weirdStringException(this._valueClass, "only \"true\" or \"false\" recognized");
            }
            throw deserializationContext.mappingException(this._valueClass);
        }
        return false;
    }

    protected final Boolean _parseBoolean(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_TRUE) {
            return Boolean.TRUE;
        }
        if (currentToken == JsonToken.VALUE_FALSE) {
            return Boolean.FALSE;
        }
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.VALUE_NUMBER_INT) {
            return jsonParser.getIntValue() == 0 ? Boolean.FALSE : Boolean.TRUE;
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String strTrim = jsonParser.getText().trim();
            if ("true".equals(strTrim)) {
                return Boolean.TRUE;
            }
            if ("false".equals(strTrim) || strTrim.length() == 0) {
                return Boolean.FALSE;
            }
            throw deserializationContext.weirdStringException(this._valueClass, "only \"true\" or \"false\" recognized");
        }
        throw deserializationContext.mappingException(this._valueClass);
    }

    protected final Short _parseShort(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Short.valueOf(jsonParser.getShortValue());
        }
        int i_parseIntPrimitive = _parseIntPrimitive(jsonParser, deserializationContext);
        if (i_parseIntPrimitive < -32768 || i_parseIntPrimitive > 32767) {
            throw deserializationContext.weirdStringException(this._valueClass, "overflow, value can not be represented as 16-bit value");
        }
        return Short.valueOf((short) i_parseIntPrimitive);
    }

    protected final short _parseShortPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
        int i_parseIntPrimitive = _parseIntPrimitive(jsonParser, deserializationContext);
        if (i_parseIntPrimitive < -32768 || i_parseIntPrimitive > 32767) {
            throw deserializationContext.weirdStringException(this._valueClass, "overflow, value can not be represented as 16-bit value");
        }
        return (short) i_parseIntPrimitive;
    }

    protected final int _parseIntPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return jsonParser.getIntValue();
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String strTrim = jsonParser.getText().trim();
            try {
                int length = strTrim.length();
                if (length > 9) {
                    long j = Long.parseLong(strTrim);
                    if (j < -2147483648L || j > 2147483647L) {
                        throw deserializationContext.weirdStringException(this._valueClass, "Overflow: numeric value (" + strTrim + ") out of range of int (-2147483648 - 2147483647)");
                    }
                    return (int) j;
                }
                if (length != 0) {
                    return NumberInput.parseInt(strTrim);
                }
                return 0;
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid int value");
            }
        }
        if (currentToken != JsonToken.VALUE_NULL) {
            throw deserializationContext.mappingException(this._valueClass);
        }
        return 0;
    }

    protected final Integer _parseInteger(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Integer.valueOf(jsonParser.getIntValue());
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String strTrim = jsonParser.getText().trim();
            try {
                int length = strTrim.length();
                if (length > 9) {
                    long j = Long.parseLong(strTrim);
                    if (j < -2147483648L || j > 2147483647L) {
                        throw deserializationContext.weirdStringException(this._valueClass, "Overflow: numeric value (" + strTrim + ") out of range of Integer (-2147483648 - 2147483647)");
                    }
                    return Integer.valueOf((int) j);
                }
                if (length != 0) {
                    return Integer.valueOf(NumberInput.parseInt(strTrim));
                }
                return null;
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid Integer value");
            }
        }
        if (currentToken != JsonToken.VALUE_NULL) {
            throw deserializationContext.mappingException(this._valueClass);
        }
        return null;
    }

    protected final Long _parseLong(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Long.valueOf(jsonParser.getLongValue());
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String strTrim = jsonParser.getText().trim();
            if (strTrim.length() == 0) {
                return null;
            }
            try {
                return Long.valueOf(NumberInput.parseLong(strTrim));
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid Long value");
            }
        }
        if (currentToken != JsonToken.VALUE_NULL) {
            throw deserializationContext.mappingException(this._valueClass);
        }
        return null;
    }

    protected final long _parseLongPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return jsonParser.getLongValue();
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String strTrim = jsonParser.getText().trim();
            if (strTrim.length() == 0) {
                return 0L;
            }
            try {
                return NumberInput.parseLong(strTrim);
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid long value");
            }
        }
        if (currentToken != JsonToken.VALUE_NULL) {
            throw deserializationContext.mappingException(this._valueClass);
        }
        return 0L;
    }

    protected final Float _parseFloat(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Float.valueOf(jsonParser.getFloatValue());
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String strTrim = jsonParser.getText().trim();
            if (strTrim.length() == 0) {
                return null;
            }
            switch (strTrim.charAt(0)) {
                case '-':
                    if ("-Infinity".equals(strTrim) || "-INF".equals(strTrim)) {
                        return Float.valueOf(Float.NEGATIVE_INFINITY);
                    }
                    break;
                case 'I':
                    if ("Infinity".equals(strTrim) || "INF".equals(strTrim)) {
                        return Float.valueOf(Float.POSITIVE_INFINITY);
                    }
                    break;
                case 'N':
                    if ("NaN".equals(strTrim)) {
                        return Float.valueOf(Float.NaN);
                    }
                    break;
            }
            try {
                return Float.valueOf(Float.parseFloat(strTrim));
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid Float value");
            }
        }
        if (currentToken != JsonToken.VALUE_NULL) {
            throw deserializationContext.mappingException(this._valueClass);
        }
        return null;
    }

    protected final float _parseFloatPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return jsonParser.getFloatValue();
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String strTrim = jsonParser.getText().trim();
            if (strTrim.length() == 0) {
                return 0.0f;
            }
            switch (strTrim.charAt(0)) {
                case '-':
                    if ("-Infinity".equals(strTrim) || "-INF".equals(strTrim)) {
                        return Float.NEGATIVE_INFINITY;
                    }
                    break;
                case 'I':
                    if ("Infinity".equals(strTrim) || "INF".equals(strTrim)) {
                        return Float.POSITIVE_INFINITY;
                    }
                    break;
                case 'N':
                    if ("NaN".equals(strTrim)) {
                        return Float.NaN;
                    }
                    break;
            }
            try {
                return Float.parseFloat(strTrim);
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid float value");
            }
        }
        if (currentToken != JsonToken.VALUE_NULL) {
            throw deserializationContext.mappingException(this._valueClass);
        }
        return 0.0f;
    }

    protected final Double _parseDouble(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Double.valueOf(jsonParser.getDoubleValue());
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String strTrim = jsonParser.getText().trim();
            if (strTrim.length() == 0) {
                return null;
            }
            switch (strTrim.charAt(0)) {
                case '-':
                    if ("-Infinity".equals(strTrim) || "-INF".equals(strTrim)) {
                        return Double.valueOf(Double.NEGATIVE_INFINITY);
                    }
                    break;
                case 'I':
                    if ("Infinity".equals(strTrim) || "INF".equals(strTrim)) {
                        return Double.valueOf(Double.POSITIVE_INFINITY);
                    }
                    break;
                case 'N':
                    if ("NaN".equals(strTrim)) {
                        return Double.valueOf(Double.NaN);
                    }
                    break;
            }
            try {
                return Double.valueOf(parseDouble(strTrim));
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid Double value");
            }
        }
        if (currentToken != JsonToken.VALUE_NULL) {
            throw deserializationContext.mappingException(this._valueClass);
        }
        return null;
    }

    protected final double _parseDoublePrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return jsonParser.getDoubleValue();
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String strTrim = jsonParser.getText().trim();
            if (strTrim.length() == 0) {
                return 0.0d;
            }
            switch (strTrim.charAt(0)) {
                case '-':
                    if ("-Infinity".equals(strTrim) || "-INF".equals(strTrim)) {
                        return Double.NEGATIVE_INFINITY;
                    }
                    break;
                case 'I':
                    if ("Infinity".equals(strTrim) || "INF".equals(strTrim)) {
                        return Double.POSITIVE_INFINITY;
                    }
                    break;
                case 'N':
                    if ("NaN".equals(strTrim)) {
                        return Double.NaN;
                    }
                    break;
            }
            try {
                return parseDouble(strTrim);
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid double value");
            }
        }
        if (currentToken != JsonToken.VALUE_NULL) {
            throw deserializationContext.mappingException(this._valueClass);
        }
        return 0.0d;
    }

    protected Date _parseDate(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        try {
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                return new Date(jsonParser.getLongValue());
            }
            if (currentToken == JsonToken.VALUE_STRING) {
                String strTrim = jsonParser.getText().trim();
                if (strTrim.length() == 0) {
                    return null;
                }
                return deserializationContext.parseDate(strTrim);
            }
            throw deserializationContext.mappingException(this._valueClass);
        } catch (IllegalArgumentException e) {
            throw deserializationContext.weirdStringException(this._valueClass, "not a valid representation (error: " + e.getMessage() + ")");
        }
    }

    protected static final double parseDouble(String str) {
        if (NumberInput.NASTY_SMALL_DOUBLE.equals(str)) {
            return Double.MIN_NORMAL;
        }
        return Double.parseDouble(str);
    }

    protected JsonDeserializer<Object> findDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) {
        return deserializerProvider.findValueDeserializer(deserializationConfig, javaType, beanProperty);
    }

    protected void handleUnknownProperty(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) throws JsonMappingException {
        if (obj == null) {
            obj = getValueClass();
        }
        if (!deserializationContext.handleUnknownProperty(jsonParser, this, obj, str)) {
            reportUnknownProperty(deserializationContext, obj, str);
            jsonParser.skipChildren();
        }
    }

    protected void reportUnknownProperty(DeserializationContext deserializationContext, Object obj, String str) throws JsonMappingException {
        if (deserializationContext.isEnabled(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES)) {
            throw deserializationContext.unknownFieldException(obj, str);
        }
    }

    public abstract class PrimitiveOrWrapperDeserializer<T> extends StdScalarDeserializer<T> {
        final T _nullValue;

        protected PrimitiveOrWrapperDeserializer(Class<T> cls, T t) {
            super(cls);
            this._nullValue = t;
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public final T getNullValue() {
            return this._nullValue;
        }
    }

    @JacksonStdImpl
    public final class StringDeserializer extends StdScalarDeserializer<String> {
        public StringDeserializer() {
            super(String.class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_STRING) {
                return jsonParser.getText();
            }
            if (currentToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                Object embeddedObject = jsonParser.getEmbeddedObject();
                if (embeddedObject == null) {
                    return null;
                }
                if (embeddedObject instanceof byte[]) {
                    return Base64Variants.getDefaultVariant().encode((byte[]) embeddedObject, false);
                }
                return embeddedObject.toString();
            }
            if (currentToken.isScalarValue()) {
                return jsonParser.getText();
            }
            throw deserializationContext.mappingException(this._valueClass);
        }

        @Override // org.codehaus.jackson.map.deser.StdScalarDeserializer, org.codehaus.jackson.map.deser.StdDeserializer, org.codehaus.jackson.map.JsonDeserializer
        public String deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            return deserialize(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public final class ClassDeserializer extends StdScalarDeserializer<Class<?>> {
        public ClassDeserializer() {
            super(Class.class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public Class<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING) {
                String text = jsonParser.getText();
                if (text.indexOf(46) < 0) {
                    if ("int".equals(text)) {
                        return Integer.TYPE;
                    }
                    if ("long".equals(text)) {
                        return Long.TYPE;
                    }
                    if ("float".equals(text)) {
                        return Float.TYPE;
                    }
                    if ("double".equals(text)) {
                        return Double.TYPE;
                    }
                    if ("boolean".equals(text)) {
                        return Boolean.TYPE;
                    }
                    if ("byte".equals(text)) {
                        return Byte.TYPE;
                    }
                    if ("char".equals(text)) {
                        return Character.TYPE;
                    }
                    if ("short".equals(text)) {
                        return Short.TYPE;
                    }
                    if ("void".equals(text)) {
                        return Void.TYPE;
                    }
                }
                try {
                    return Class.forName(jsonParser.getText());
                } catch (ClassNotFoundException e) {
                    throw deserializationContext.instantiationException(this._valueClass, e);
                }
            }
            throw deserializationContext.mappingException(this._valueClass);
        }
    }

    @JacksonStdImpl
    public final class BooleanDeserializer extends PrimitiveOrWrapperDeserializer<Boolean> {
        public BooleanDeserializer(Class<Boolean> cls, Boolean bool) {
            super(cls, bool);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public Boolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseBoolean(jsonParser, deserializationContext);
        }

        @Override // org.codehaus.jackson.map.deser.StdScalarDeserializer, org.codehaus.jackson.map.deser.StdDeserializer, org.codehaus.jackson.map.JsonDeserializer
        public Boolean deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            return _parseBoolean(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public final class ByteDeserializer extends PrimitiveOrWrapperDeserializer<Byte> {
        public ByteDeserializer(Class<Byte> cls, Byte b) {
            super(cls, b);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public Byte deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
            int i_parseIntPrimitive = _parseIntPrimitive(jsonParser, deserializationContext);
            if (i_parseIntPrimitive < -128 || i_parseIntPrimitive > 127) {
                throw deserializationContext.weirdStringException(this._valueClass, "overflow, value can not be represented as 8-bit value");
            }
            return Byte.valueOf((byte) i_parseIntPrimitive);
        }
    }

    @JacksonStdImpl
    public final class ShortDeserializer extends PrimitiveOrWrapperDeserializer<Short> {
        public ShortDeserializer(Class<Short> cls, Short sh) {
            super(cls, sh);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public Short deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseShort(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public final class CharacterDeserializer extends PrimitiveOrWrapperDeserializer<Character> {
        public CharacterDeserializer(Class<Character> cls, Character ch) {
            super(cls, ch);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public Character deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                int intValue = jsonParser.getIntValue();
                if (intValue >= 0 && intValue <= 65535) {
                    return Character.valueOf((char) intValue);
                }
            } else if (currentToken == JsonToken.VALUE_STRING) {
                String text = jsonParser.getText();
                if (text.length() == 1) {
                    return Character.valueOf(text.charAt(0));
                }
            }
            throw deserializationContext.mappingException(this._valueClass);
        }
    }

    @JacksonStdImpl
    public final class IntegerDeserializer extends PrimitiveOrWrapperDeserializer<Integer> {
        public IntegerDeserializer(Class<Integer> cls, Integer num) {
            super(cls, num);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseInteger(jsonParser, deserializationContext);
        }

        @Override // org.codehaus.jackson.map.deser.StdScalarDeserializer, org.codehaus.jackson.map.deser.StdDeserializer, org.codehaus.jackson.map.JsonDeserializer
        public Integer deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            return _parseInteger(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public final class LongDeserializer extends PrimitiveOrWrapperDeserializer<Long> {
        public LongDeserializer(Class<Long> cls, Long l) {
            super(cls, l);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public Long deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseLong(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public final class FloatDeserializer extends PrimitiveOrWrapperDeserializer<Float> {
        public FloatDeserializer(Class<Float> cls, Float f) {
            super(cls, f);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public Float deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseFloat(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public final class DoubleDeserializer extends PrimitiveOrWrapperDeserializer<Double> {
        public DoubleDeserializer(Class<Double> cls, Double d) {
            super(cls, d);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public Double deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseDouble(jsonParser, deserializationContext);
        }

        @Override // org.codehaus.jackson.map.deser.StdScalarDeserializer, org.codehaus.jackson.map.deser.StdDeserializer, org.codehaus.jackson.map.JsonDeserializer
        public Double deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            return _parseDouble(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public final class NumberDeserializer extends StdScalarDeserializer<Number> {
        public NumberDeserializer() {
            super(Number.class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public Number deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
            Number numberValueOf;
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                if (deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_INTEGER_FOR_INTS)) {
                    return jsonParser.getBigIntegerValue();
                }
                return jsonParser.getNumberValue();
            }
            if (currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                if (deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return jsonParser.getDecimalValue();
                }
                return Double.valueOf(jsonParser.getDoubleValue());
            }
            if (currentToken == JsonToken.VALUE_STRING) {
                String strTrim = jsonParser.getText().trim();
                try {
                    if (strTrim.indexOf(46) >= 0) {
                        if (deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                            numberValueOf = new BigDecimal(strTrim);
                        } else {
                            numberValueOf = new Double(strTrim);
                        }
                    } else if (deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_INTEGER_FOR_INTS)) {
                        numberValueOf = new BigInteger(strTrim);
                    } else {
                        long j = Long.parseLong(strTrim);
                        if (j <= 2147483647L && j >= -2147483648L) {
                            numberValueOf = Integer.valueOf((int) j);
                        } else {
                            numberValueOf = Long.valueOf(j);
                        }
                    }
                    return numberValueOf;
                } catch (IllegalArgumentException e) {
                    throw deserializationContext.weirdStringException(this._valueClass, "not a valid number");
                }
            }
            throw deserializationContext.mappingException(this._valueClass);
        }

        @Override // org.codehaus.jackson.map.deser.StdScalarDeserializer, org.codehaus.jackson.map.deser.StdDeserializer, org.codehaus.jackson.map.JsonDeserializer
        public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            switch (jsonParser.getCurrentToken()) {
                case VALUE_NUMBER_INT:
                case VALUE_NUMBER_FLOAT:
                case VALUE_STRING:
                    return deserialize(jsonParser, deserializationContext);
                default:
                    return typeDeserializer.deserializeTypedFromScalar(jsonParser, deserializationContext);
            }
        }
    }

    public final class AtomicBooleanDeserializer extends StdScalarDeserializer<AtomicBoolean> {
        public AtomicBooleanDeserializer() {
            super(AtomicBoolean.class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public AtomicBoolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return new AtomicBoolean(_parseBooleanPrimitive(jsonParser, deserializationContext));
        }
    }

    public class AtomicReferenceDeserializer extends StdScalarDeserializer<AtomicReference<?>> implements ResolvableDeserializer {
        protected final BeanProperty _property;
        protected final JavaType _referencedType;
        protected JsonDeserializer<?> _valueDeserializer;

        public AtomicReferenceDeserializer(JavaType javaType, BeanProperty beanProperty) {
            super(AtomicReference.class);
            this._referencedType = javaType;
            this._property = beanProperty;
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public AtomicReference<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return new AtomicReference<>(this._valueDeserializer.deserialize(jsonParser, deserializationContext));
        }

        @Override // org.codehaus.jackson.map.ResolvableDeserializer
        public void resolve(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider) {
            this._valueDeserializer = deserializerProvider.findValueDeserializer(deserializationConfig, this._referencedType, this._property);
        }
    }

    @JacksonStdImpl
    public class BigDecimalDeserializer extends StdScalarDeserializer<BigDecimal> {
        public BigDecimalDeserializer() {
            super(BigDecimal.class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public BigDecimal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                return jsonParser.getDecimalValue();
            }
            if (currentToken == JsonToken.VALUE_STRING) {
                String strTrim = jsonParser.getText().trim();
                if (strTrim.length() == 0) {
                    return null;
                }
                try {
                    return new BigDecimal(strTrim);
                } catch (IllegalArgumentException e) {
                    throw deserializationContext.weirdStringException(this._valueClass, "not a valid representation");
                }
            }
            throw deserializationContext.mappingException(this._valueClass);
        }
    }

    @JacksonStdImpl
    public class BigIntegerDeserializer extends StdScalarDeserializer<BigInteger> {
        public BigIntegerDeserializer() {
            super(BigInteger.class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public BigInteger deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                switch (jsonParser.getNumberType()) {
                    case INT:
                    case LONG:
                        return BigInteger.valueOf(jsonParser.getLongValue());
                }
            }
            if (currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                return jsonParser.getDecimalValue().toBigInteger();
            }
            if (currentToken != JsonToken.VALUE_STRING) {
                throw deserializationContext.mappingException(this._valueClass);
            }
            String strTrim = jsonParser.getText().trim();
            if (strTrim.length() == 0) {
                return null;
            }
            try {
                return new BigInteger(strTrim);
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid representation");
            }
        }
    }

    @JacksonStdImpl
    public class CalendarDeserializer extends StdScalarDeserializer<Calendar> {
        Class<? extends Calendar> _calendarClass;

        public CalendarDeserializer() {
            this(null);
        }

        public CalendarDeserializer(Class<? extends Calendar> cls) {
            super(Calendar.class);
            this._calendarClass = cls;
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public Calendar deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
            Date date_parseDate = _parseDate(jsonParser, deserializationContext);
            if (date_parseDate == null) {
                return null;
            }
            if (this._calendarClass == null) {
                return deserializationContext.constructCalendar(date_parseDate);
            }
            try {
                Calendar calendarNewInstance = this._calendarClass.newInstance();
                calendarNewInstance.setTimeInMillis(date_parseDate.getTime());
                return calendarNewInstance;
            } catch (Exception e) {
                throw deserializationContext.instantiationException(this._calendarClass, e);
            }
        }
    }

    public class SqlDateDeserializer extends StdScalarDeserializer<java.sql.Date> {
        public SqlDateDeserializer() {
            super(java.sql.Date.class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public java.sql.Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            Date date_parseDate = _parseDate(jsonParser, deserializationContext);
            if (date_parseDate == null) {
                return null;
            }
            return new java.sql.Date(date_parseDate.getTime());
        }
    }

    public class StackTraceElementDeserializer extends StdScalarDeserializer<StackTraceElement> {
        public StackTraceElementDeserializer() {
            super(StackTraceElement.class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public StackTraceElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
            if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
                String text = "";
                String text2 = "";
                String text3 = "";
                int intValue = -1;
                while (true) {
                    JsonToken jsonTokenNextValue = jsonParser.nextValue();
                    if (jsonTokenNextValue != JsonToken.END_OBJECT) {
                        String currentName = jsonParser.getCurrentName();
                        if ("className".equals(currentName)) {
                            text = jsonParser.getText();
                        } else if ("fileName".equals(currentName)) {
                            text3 = jsonParser.getText();
                        } else if ("lineNumber".equals(currentName)) {
                            if (jsonTokenNextValue.isNumeric()) {
                                intValue = jsonParser.getIntValue();
                            } else {
                                throw JsonMappingException.from(jsonParser, "Non-numeric token (" + jsonTokenNextValue + ") for property 'lineNumber'");
                            }
                        } else if ("methodName".equals(currentName)) {
                            text2 = jsonParser.getText();
                        } else if (!"nativeMethod".equals(currentName)) {
                            handleUnknownProperty(jsonParser, deserializationContext, this._valueClass, currentName);
                        }
                    } else {
                        return new StackTraceElement(text, text2, text3, intValue);
                    }
                }
            } else {
                throw deserializationContext.mappingException(this._valueClass);
            }
        }
    }

    @JacksonStdImpl
    public class TokenBufferDeserializer extends StdScalarDeserializer<TokenBuffer> {
        public TokenBufferDeserializer() {
            super(TokenBuffer.class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public TokenBuffer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            TokenBuffer tokenBuffer = new TokenBuffer(jsonParser.getCodec());
            tokenBuffer.copyCurrentStructure(jsonParser);
            return tokenBuffer;
        }
    }
}
