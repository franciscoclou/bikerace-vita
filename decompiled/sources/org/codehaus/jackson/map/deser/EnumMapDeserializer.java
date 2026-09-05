package org.codehaus.jackson.map.deser;

import java.util.EnumMap;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.TypeDeserializer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class EnumMapDeserializer extends StdDeserializer<EnumMap<?, ?>> {
    final EnumResolver<?> _enumResolver;
    final JsonDeserializer<Object> _valueDeserializer;

    public EnumMapDeserializer(EnumResolver<?> enumResolver, JsonDeserializer<Object> jsonDeserializer) {
        super((Class<?>) EnumMap.class);
        this._enumResolver = enumResolver;
        this._valueDeserializer = jsonDeserializer;
    }

    @Override // org.codehaus.jackson.map.JsonDeserializer
    public EnumMap<?, ?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
        if (jsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
            throw deserializationContext.mappingException(EnumMap.class);
        }
        EnumMap<?, ?> enumMapConstructMap = constructMap();
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            Enum enumFindEnum = this._enumResolver.findEnum(jsonParser.getCurrentName());
            if (enumFindEnum == null) {
                throw deserializationContext.weirdStringException(this._enumResolver.getEnumClass(), "value not one of declared Enum instance names");
            }
            enumMapConstructMap.put(enumFindEnum, jsonParser.nextToken() == JsonToken.VALUE_NULL ? null : this._valueDeserializer.deserialize(jsonParser, deserializationContext));
        }
        return enumMapConstructMap;
    }

    @Override // org.codehaus.jackson.map.deser.StdDeserializer, org.codehaus.jackson.map.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }

    private EnumMap<?, ?> constructMap() {
        return new EnumMap<>(this._enumResolver.getEnumClass());
    }
}
