package org.codehaus.jackson.map.ext;

import java.util.Arrays;
import java.util.Collection;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.deser.StdDeserializer;
import org.codehaus.jackson.map.deser.StdScalarDeserializer;
import org.codehaus.jackson.map.util.Provider;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class JodaDeserializers implements Provider<StdDeserializer<?>> {
    @Override // org.codehaus.jackson.map.util.Provider
    public Collection<StdDeserializer<?>> provide() {
        return Arrays.asList(new DateTimeDeserializer(DateTime.class), new DateTimeDeserializer(ReadableDateTime.class), new DateTimeDeserializer(ReadableInstant.class), new LocalDateDeserializer(), new LocalDateTimeDeserializer(), new DateMidnightDeserializer());
    }

    abstract class JodaDeserializer<T> extends StdScalarDeserializer<T> {
        static final DateTimeFormatter _localDateTimeFormat = ISODateTimeFormat.localDateOptionalTimeParser();

        protected JodaDeserializer(Class<T> cls) {
            super(cls);
        }

        protected DateTime parseLocal(JsonParser jsonParser) {
            String strTrim = jsonParser.getText().trim();
            if (strTrim.length() == 0) {
                return null;
            }
            return _localDateTimeFormat.parseDateTime(strTrim);
        }
    }

    public class DateTimeDeserializer<T extends ReadableInstant> extends JodaDeserializer<T> {
        public DateTimeDeserializer(Class<T> cls) {
            super(cls);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                return new DateTime(jsonParser.getLongValue(), DateTimeZone.UTC);
            }
            if (currentToken == JsonToken.VALUE_STRING) {
                String strTrim = jsonParser.getText().trim();
                if (strTrim.length() == 0) {
                    return null;
                }
                return new DateTime(strTrim, DateTimeZone.UTC);
            }
            throw deserializationContext.mappingException(getValueClass());
        }
    }

    public class LocalDateDeserializer extends JodaDeserializer<LocalDate> {
        public LocalDateDeserializer() {
            super(LocalDate.class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
            if (jsonParser.isExpectedStartArrayToken()) {
                jsonParser.nextToken();
                int intValue = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue2 = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue3 = jsonParser.getIntValue();
                if (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "after LocalDate ints");
                }
                return new LocalDate(intValue, intValue2, intValue3);
            }
            switch (jsonParser.getCurrentToken()) {
                case VALUE_NUMBER_INT:
                    return new LocalDate(jsonParser.getLongValue());
                case VALUE_STRING:
                    DateTime local = parseLocal(jsonParser);
                    if (local == null) {
                        return null;
                    }
                    return local.toLocalDate();
                default:
                    throw deserializationContext.wrongTokenException(jsonParser, JsonToken.START_ARRAY, "expected JSON Array, String or Number");
            }
        }
    }

    public class LocalDateTimeDeserializer extends JodaDeserializer<LocalDateTime> {
        public LocalDateTimeDeserializer() {
            super(LocalDateTime.class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
            if (jsonParser.isExpectedStartArrayToken()) {
                jsonParser.nextToken();
                int intValue = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue2 = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue3 = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue4 = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue5 = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue6 = jsonParser.getIntValue();
                int intValue7 = 0;
                if (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    intValue7 = jsonParser.getIntValue();
                    jsonParser.nextToken();
                }
                if (jsonParser.getCurrentToken() != JsonToken.END_ARRAY) {
                    throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "after LocalDateTime ints");
                }
                return new LocalDateTime(intValue, intValue2, intValue3, intValue4, intValue5, intValue6, intValue7);
            }
            switch (jsonParser.getCurrentToken()) {
                case VALUE_NUMBER_INT:
                    return new LocalDateTime(jsonParser.getLongValue());
                case VALUE_STRING:
                    DateTime local = parseLocal(jsonParser);
                    if (local == null) {
                        return null;
                    }
                    return local.toLocalDateTime();
                default:
                    throw deserializationContext.wrongTokenException(jsonParser, JsonToken.START_ARRAY, "expected JSON Array or Number");
            }
        }
    }

    public class DateMidnightDeserializer extends JodaDeserializer<DateMidnight> {
        public DateMidnightDeserializer() {
            super(DateMidnight.class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public DateMidnight deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
            if (jsonParser.isExpectedStartArrayToken()) {
                jsonParser.nextToken();
                int intValue = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue2 = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue3 = jsonParser.getIntValue();
                if (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "after DateMidnight ints");
                }
                return new DateMidnight(intValue, intValue2, intValue3);
            }
            switch (jsonParser.getCurrentToken()) {
                case VALUE_NUMBER_INT:
                    return new DateMidnight(jsonParser.getLongValue());
                case VALUE_STRING:
                    DateTime local = parseLocal(jsonParser);
                    if (local == null) {
                        return null;
                    }
                    return local.toDateMidnight();
                default:
                    throw deserializationContext.wrongTokenException(jsonParser, JsonToken.START_ARRAY, "expected JSON Array, Number or String");
            }
        }
    }
}
