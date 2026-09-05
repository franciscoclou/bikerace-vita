package org.codehaus.jackson.schema;

import com.facebook.internal.ServerProtocol;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class JsonSchema {
    private final ObjectNode schema;

    @JsonCreator
    public JsonSchema(ObjectNode objectNode) {
        this.schema = objectNode;
    }

    @JsonValue
    public ObjectNode getSchemaNode() {
        return this.schema;
    }

    public String toString() {
        return this.schema.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof JsonSchema)) {
            JsonSchema jsonSchema = (JsonSchema) obj;
            if (this.schema == null) {
                return jsonSchema.schema == null;
            }
            return this.schema.equals(jsonSchema.schema);
        }
        return false;
    }

    public static JsonNode getDefaultSchemaNode() {
        ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
        objectNode.put(ServerProtocol.DIALOG_PARAM_TYPE, "any");
        return objectNode;
    }
}
