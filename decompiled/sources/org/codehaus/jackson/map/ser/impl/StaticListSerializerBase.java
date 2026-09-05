package org.codehaus.jackson.map.ser.impl;

import java.lang.reflect.Type;
import java.util.Collection;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.SerializerBase;
import org.codehaus.jackson.node.ObjectNode;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class StaticListSerializerBase<T extends Collection<?>> extends SerializerBase<T> {
    protected final BeanProperty _property;

    protected abstract JsonNode contentSchema();

    protected StaticListSerializerBase(Class<?> cls, BeanProperty beanProperty) {
        super(cls, false);
        this._property = beanProperty;
    }

    @Override // org.codehaus.jackson.map.ser.SerializerBase, org.codehaus.jackson.schema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        ObjectNode objectNodeCreateSchemaNode = createSchemaNode("array", true);
        objectNodeCreateSchemaNode.put("items", contentSchema());
        return objectNodeCreateSchemaNode;
    }
}
