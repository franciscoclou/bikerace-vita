package org.codehaus.jackson.map;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.NullNode;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
@Deprecated
public class TreeMapper extends JsonNodeFactory {
    protected ObjectMapper _objectMapper;

    public TreeMapper() {
        this(null);
    }

    public TreeMapper(ObjectMapper objectMapper) {
        this._objectMapper = objectMapper;
    }

    public JsonFactory getJsonFactory() {
        return objectMapper().getJsonFactory();
    }

    public JsonNode readTree(JsonParser jsonParser) {
        if (jsonParser.getCurrentToken() == null && jsonParser.nextToken() == null) {
            return null;
        }
        return objectMapper().readTree(jsonParser);
    }

    public JsonNode readTree(File file) {
        JsonNode jsonNode = (JsonNode) objectMapper().readValue(file, JsonNode.class);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public JsonNode readTree(URL url) {
        JsonNode jsonNode = (JsonNode) objectMapper().readValue(url, JsonNode.class);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public JsonNode readTree(InputStream inputStream) {
        JsonNode jsonNode = (JsonNode) objectMapper().readValue(inputStream, JsonNode.class);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public JsonNode readTree(Reader reader) {
        JsonNode jsonNode = (JsonNode) objectMapper().readValue(reader, JsonNode.class);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public JsonNode readTree(String str) {
        JsonNode jsonNode = (JsonNode) objectMapper().readValue(str, JsonNode.class);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public JsonNode readTree(byte[] bArr) {
        JsonNode jsonNode = (JsonNode) objectMapper().readValue(bArr, 0, bArr.length, JsonNode.class);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public void writeTree(JsonNode jsonNode, File file) throws Throwable {
        objectMapper().writeValue(file, jsonNode);
    }

    public void writeTree(JsonNode jsonNode, Writer writer) throws Throwable {
        objectMapper().writeValue(writer, jsonNode);
    }

    public void writeTree(JsonNode jsonNode, OutputStream outputStream) throws Throwable {
        objectMapper().writeValue(outputStream, jsonNode);
    }

    protected synchronized ObjectMapper objectMapper() {
        if (this._objectMapper == null) {
            this._objectMapper = new ObjectMapper();
        }
        return this._objectMapper;
    }
}
