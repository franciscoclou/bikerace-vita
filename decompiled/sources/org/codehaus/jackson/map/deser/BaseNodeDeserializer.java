package org.codehaus.jackson.map.deser;

import com.amazonaws.javax.xml.stream.XMLStreamConstants;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;

/* JADX INFO: compiled from: JsonNodeDeserializer.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
abstract class BaseNodeDeserializer<N extends JsonNode> extends StdDeserializer<N> {
    public BaseNodeDeserializer(Class<N> cls) {
        super((Class<?>) cls);
    }

    @Override // org.codehaus.jackson.map.deser.StdDeserializer, org.codehaus.jackson.map.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    protected void _reportProblem(JsonParser jsonParser, String str) throws JsonMappingException {
        throw new JsonMappingException(str, jsonParser.getTokenLocation());
    }

    protected void _handleDuplicateField(String str, ObjectNode objectNode, JsonNode jsonNode, JsonNode jsonNode2) {
    }

    protected final ObjectNode deserializeObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
        ObjectNode objectNode = deserializationContext.getNodeFactory().objectNode();
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            JsonNode jsonNodeDeserializeAny = deserializeAny(jsonParser, deserializationContext);
            JsonNode jsonNodePut = objectNode.put(currentName, jsonNodeDeserializeAny);
            if (jsonNodePut != null) {
                _handleDuplicateField(currentName, objectNode, jsonNodePut, jsonNodeDeserializeAny);
            }
            currentToken = jsonParser.nextToken();
        }
        return objectNode;
    }

    protected final ArrayNode deserializeArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
        ArrayNode arrayNode = deserializationContext.getNodeFactory().arrayNode();
        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
            arrayNode.add(deserializeAny(jsonParser, deserializationContext));
        }
        return arrayNode;
    }

    /* JADX INFO: renamed from: org.codehaus.jackson.map.deser.BaseNodeDeserializer$1, reason: invalid class name */
    /* JADX INFO: compiled from: JsonNodeDeserializer.java */
    /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];

        static {
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.START_OBJECT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.START_ARRAY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NULL.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_EMBEDDED_OBJECT.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.END_OBJECT.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.END_ARRAY.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
        }
    }

    protected final JsonNode deserializeAny(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
        JsonNodeFactory nodeFactory = deserializationContext.getNodeFactory();
        switch (AnonymousClass1.$SwitchMap$org$codehaus$jackson$JsonToken[jsonParser.getCurrentToken().ordinal()]) {
            case 1:
            case 2:
                return deserializeObject(jsonParser, deserializationContext);
            case 3:
                return deserializeArray(jsonParser, deserializationContext);
            case 4:
                return nodeFactory.textNode(jsonParser.getText());
            case 5:
                JsonParser.NumberType numberType = jsonParser.getNumberType();
                if (numberType == JsonParser.NumberType.BIG_INTEGER || deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_INTEGER_FOR_INTS)) {
                    return nodeFactory.numberNode(jsonParser.getBigIntegerValue());
                }
                if (numberType == JsonParser.NumberType.INT) {
                    return nodeFactory.numberNode(jsonParser.getIntValue());
                }
                return nodeFactory.numberNode(jsonParser.getLongValue());
            case 6:
                if (jsonParser.getNumberType() == JsonParser.NumberType.BIG_DECIMAL || deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return nodeFactory.numberNode(jsonParser.getDecimalValue());
                }
                return nodeFactory.numberNode(jsonParser.getDoubleValue());
            case 7:
                return nodeFactory.booleanNode(true);
            case 8:
                return nodeFactory.booleanNode(false);
            case 9:
                return nodeFactory.nullNode();
            case XMLStreamConstants.ATTRIBUTE /* 10 */:
                return nodeFactory.POJONode(jsonParser.getEmbeddedObject());
            default:
                throw deserializationContext.mappingException(getValueClass());
        }
    }
}
