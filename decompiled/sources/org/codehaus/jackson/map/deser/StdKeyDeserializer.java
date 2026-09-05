package org.codehaus.jackson.map.deser;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.codehaus.jackson.io.NumberInput;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.KeyDeserializer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class StdKeyDeserializer extends KeyDeserializer {
    protected final Class<?> _keyClass;

    protected abstract Object _parse(String str, DeserializationContext deserializationContext);

    protected StdKeyDeserializer(Class<?> cls) {
        this._keyClass = cls;
    }

    @Override // org.codehaus.jackson.map.KeyDeserializer
    public final Object deserializeKey(String str, DeserializationContext deserializationContext) throws JsonMappingException {
        if (str == null) {
            return null;
        }
        try {
            Object obj_parse = _parse(str, deserializationContext);
            if (obj_parse != null) {
                return obj_parse;
            }
            throw deserializationContext.weirdKeyException(this._keyClass, str, "not a valid representation");
        } catch (Exception e) {
            throw deserializationContext.weirdKeyException(this._keyClass, str, "not a valid representation: " + e.getMessage());
        }
    }

    public Class<?> getKeyClass() {
        return this._keyClass;
    }

    protected int _parseInt(String str) {
        return Integer.parseInt(str);
    }

    protected long _parseLong(String str) {
        return Long.parseLong(str);
    }

    protected double _parseDouble(String str) {
        return NumberInput.parseDouble(str);
    }

    final class BoolKD extends StdKeyDeserializer {
        BoolKD() {
            super(Boolean.class);
        }

        @Override // org.codehaus.jackson.map.deser.StdKeyDeserializer
        public Boolean _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            if ("true".equals(str)) {
                return Boolean.TRUE;
            }
            if ("false".equals(str)) {
                return Boolean.FALSE;
            }
            throw deserializationContext.weirdKeyException(this._keyClass, str, "value not 'true' or 'false'");
        }
    }

    final class ByteKD extends StdKeyDeserializer {
        ByteKD() {
            super(Byte.class);
        }

        @Override // org.codehaus.jackson.map.deser.StdKeyDeserializer
        public Byte _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            int i_parseInt = _parseInt(str);
            if (i_parseInt < -128 || i_parseInt > 127) {
                throw deserializationContext.weirdKeyException(this._keyClass, str, "overflow, value can not be represented as 8-bit value");
            }
            return Byte.valueOf((byte) i_parseInt);
        }
    }

    final class ShortKD extends StdKeyDeserializer {
        ShortKD() {
            super(Integer.class);
        }

        @Override // org.codehaus.jackson.map.deser.StdKeyDeserializer
        public Short _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            int i_parseInt = _parseInt(str);
            if (i_parseInt < -32768 || i_parseInt > 32767) {
                throw deserializationContext.weirdKeyException(this._keyClass, str, "overflow, value can not be represented as 16-bit value");
            }
            return Short.valueOf((short) i_parseInt);
        }
    }

    final class CharKD extends StdKeyDeserializer {
        CharKD() {
            super(Character.class);
        }

        @Override // org.codehaus.jackson.map.deser.StdKeyDeserializer
        public Character _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            if (str.length() == 1) {
                return Character.valueOf(str.charAt(0));
            }
            throw deserializationContext.weirdKeyException(this._keyClass, str, "can only convert 1-character Strings");
        }
    }

    final class IntKD extends StdKeyDeserializer {
        IntKD() {
            super(Integer.class);
        }

        @Override // org.codehaus.jackson.map.deser.StdKeyDeserializer
        public Integer _parse(String str, DeserializationContext deserializationContext) {
            return Integer.valueOf(_parseInt(str));
        }
    }

    final class LongKD extends StdKeyDeserializer {
        LongKD() {
            super(Long.class);
        }

        @Override // org.codehaus.jackson.map.deser.StdKeyDeserializer
        public Long _parse(String str, DeserializationContext deserializationContext) {
            return Long.valueOf(_parseLong(str));
        }
    }

    final class DoubleKD extends StdKeyDeserializer {
        DoubleKD() {
            super(Double.class);
        }

        @Override // org.codehaus.jackson.map.deser.StdKeyDeserializer
        public Double _parse(String str, DeserializationContext deserializationContext) {
            return Double.valueOf(_parseDouble(str));
        }
    }

    final class FloatKD extends StdKeyDeserializer {
        FloatKD() {
            super(Float.class);
        }

        @Override // org.codehaus.jackson.map.deser.StdKeyDeserializer
        public Float _parse(String str, DeserializationContext deserializationContext) {
            return Float.valueOf((float) _parseDouble(str));
        }
    }

    final class EnumKD extends StdKeyDeserializer {
        final EnumResolver<?> _resolver;

        EnumKD(EnumResolver<?> enumResolver) {
            super(enumResolver.getEnumClass());
            this._resolver = enumResolver;
        }

        @Override // org.codehaus.jackson.map.deser.StdKeyDeserializer
        public Enum<?> _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            Enum<?> enumFindEnum = this._resolver.findEnum(str);
            if (enumFindEnum == null) {
                throw deserializationContext.weirdKeyException(this._keyClass, str, "not one of values for Enum class");
            }
            return enumFindEnum;
        }
    }

    final class StringCtorKeyDeserializer extends StdKeyDeserializer {
        final Constructor<?> _ctor;

        public StringCtorKeyDeserializer(Constructor<?> constructor) {
            super(constructor.getDeclaringClass());
            this._ctor = constructor;
        }

        @Override // org.codehaus.jackson.map.deser.StdKeyDeserializer
        public Object _parse(String str, DeserializationContext deserializationContext) {
            return this._ctor.newInstance(str);
        }
    }

    final class StringFactoryKeyDeserializer extends StdKeyDeserializer {
        final Method _factoryMethod;

        public StringFactoryKeyDeserializer(Method method) {
            super(method.getDeclaringClass());
            this._factoryMethod = method;
        }

        @Override // org.codehaus.jackson.map.deser.StdKeyDeserializer
        public Object _parse(String str, DeserializationContext deserializationContext) {
            return this._factoryMethod.invoke(null, str);
        }
    }
}
