package org.codehaus.jackson.node;

import java.util.Iterator;
import java.util.Map;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonStreamContext;
import org.codehaus.jackson.JsonToken;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
abstract class NodeCursor extends JsonStreamContext {
    final NodeCursor _parent;

    public abstract boolean currentHasChildren();

    public abstract JsonNode currentNode();

    public abstract JsonToken endToken();

    @Override // org.codehaus.jackson.JsonStreamContext
    public abstract String getCurrentName();

    public abstract JsonToken nextToken();

    public abstract JsonToken nextValue();

    public NodeCursor(int i, NodeCursor nodeCursor) {
        this._type = i;
        this._index = -1;
        this._parent = nodeCursor;
    }

    @Override // org.codehaus.jackson.JsonStreamContext
    public final NodeCursor getParent() {
        return this._parent;
    }

    public final NodeCursor iterateChildren() {
        JsonNode jsonNodeCurrentNode = currentNode();
        if (jsonNodeCurrentNode == null) {
            throw new IllegalStateException("No current node");
        }
        if (jsonNodeCurrentNode.isArray()) {
            return new Array(jsonNodeCurrentNode, this);
        }
        if (jsonNodeCurrentNode.isObject()) {
            return new Object(jsonNodeCurrentNode, this);
        }
        throw new IllegalStateException("Current node of type " + jsonNodeCurrentNode.getClass().getName());
    }

    public final class RootValue extends NodeCursor {
        protected boolean _done;
        JsonNode _node;

        @Override // org.codehaus.jackson.node.NodeCursor, org.codehaus.jackson.JsonStreamContext
        public /* bridge */ /* synthetic */ JsonStreamContext getParent() {
            return super.getParent();
        }

        public RootValue(JsonNode jsonNode, NodeCursor nodeCursor) {
            super(0, nodeCursor);
            this._done = false;
            this._node = jsonNode;
        }

        @Override // org.codehaus.jackson.node.NodeCursor, org.codehaus.jackson.JsonStreamContext
        public String getCurrentName() {
            return null;
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public JsonToken nextToken() {
            if (!this._done) {
                this._done = true;
                return this._node.asToken();
            }
            this._node = null;
            return null;
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public JsonToken nextValue() {
            return nextToken();
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public JsonToken endToken() {
            return null;
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public JsonNode currentNode() {
            return this._node;
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public boolean currentHasChildren() {
            return false;
        }
    }

    public final class Array extends NodeCursor {
        Iterator<JsonNode> _contents;
        JsonNode _currentNode;

        @Override // org.codehaus.jackson.node.NodeCursor, org.codehaus.jackson.JsonStreamContext
        public /* bridge */ /* synthetic */ JsonStreamContext getParent() {
            return super.getParent();
        }

        public Array(JsonNode jsonNode, NodeCursor nodeCursor) {
            super(1, nodeCursor);
            this._contents = jsonNode.getElements();
        }

        @Override // org.codehaus.jackson.node.NodeCursor, org.codehaus.jackson.JsonStreamContext
        public String getCurrentName() {
            return null;
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public JsonToken nextToken() {
            if (!this._contents.hasNext()) {
                this._currentNode = null;
                return null;
            }
            this._currentNode = this._contents.next();
            return this._currentNode.asToken();
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public JsonToken nextValue() {
            return nextToken();
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public JsonToken endToken() {
            return JsonToken.END_ARRAY;
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public JsonNode currentNode() {
            return this._currentNode;
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public boolean currentHasChildren() {
            return ((ContainerNode) currentNode()).size() > 0;
        }
    }

    public final class Object extends NodeCursor {
        Iterator<Map.Entry<String, JsonNode>> _contents;
        Map.Entry<String, JsonNode> _current;
        boolean _needEntry;

        @Override // org.codehaus.jackson.node.NodeCursor, org.codehaus.jackson.JsonStreamContext
        public /* bridge */ /* synthetic */ JsonStreamContext getParent() {
            return super.getParent();
        }

        public Object(JsonNode jsonNode, NodeCursor nodeCursor) {
            super(2, nodeCursor);
            this._contents = ((ObjectNode) jsonNode).getFields();
            this._needEntry = true;
        }

        @Override // org.codehaus.jackson.node.NodeCursor, org.codehaus.jackson.JsonStreamContext
        public String getCurrentName() {
            if (this._current == null) {
                return null;
            }
            return this._current.getKey();
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public JsonToken nextToken() {
            if (this._needEntry) {
                if (!this._contents.hasNext()) {
                    this._current = null;
                    return null;
                }
                this._needEntry = false;
                this._current = this._contents.next();
                return JsonToken.FIELD_NAME;
            }
            this._needEntry = true;
            return this._current.getValue().asToken();
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public JsonToken nextValue() {
            JsonToken jsonTokenNextToken = nextToken();
            if (jsonTokenNextToken == JsonToken.FIELD_NAME) {
                return nextToken();
            }
            return jsonTokenNextToken;
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public JsonToken endToken() {
            return JsonToken.END_OBJECT;
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public JsonNode currentNode() {
            if (this._current == null) {
                return null;
            }
            return this._current.getValue();
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public boolean currentHasChildren() {
            return ((ContainerNode) currentNode()).size() > 0;
        }
    }
}
