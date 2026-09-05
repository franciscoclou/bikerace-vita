package org.codehaus.jackson.map.deser;

import java.util.EnumSet;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.TypeDeserializer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class EnumSetDeserializer extends StdDeserializer<EnumSet<?>> {
    final Class<Enum> _enumClass;
    final EnumDeserializer _enumDeserializer;

    public EnumSetDeserializer(EnumResolver enumResolver) {
        super((Class<?>) EnumSet.class);
        this._enumDeserializer = new EnumDeserializer(enumResolver);
        this._enumClass = enumResolver.getEnumClass();
    }

    @Override // org.codehaus.jackson.map.JsonDeserializer
    public EnumSet<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
        if (!jsonParser.isExpectedStartArrayToken()) {
            throw deserializationContext.mappingException(EnumSet.class);
        }
        EnumSet<?> enumSetConstructSet = constructSet();
        while (true) {
            JsonToken jsonTokenNextToken = jsonParser.nextToken();
            if (jsonTokenNextToken != JsonToken.END_ARRAY) {
                if (jsonTokenNextToken == JsonToken.VALUE_NULL) {
                    throw deserializationContext.mappingException(this._enumClass);
                }
                enumSetConstructSet.add(this._enumDeserializer.deserialize(jsonParser, deserializationContext));
            } else {
                return enumSetConstructSet;
            }
        }
    }

    @Override // org.codehaus.jackson.map.deser.StdDeserializer, org.codehaus.jackson.map.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    private EnumSet constructSet() {
        return EnumSet.noneOf(this._enumClass);
    }
}
