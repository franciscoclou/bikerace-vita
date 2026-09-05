package org.codehaus.jackson.map.deser;

import java.io.IOException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonMappingException;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ThrowableDeserializer extends BeanDeserializer {
    protected static final String PROP_NAME_MESSAGE = "message";

    public ThrowableDeserializer(BeanDeserializer beanDeserializer) {
        super(beanDeserializer);
    }

    @Override // org.codehaus.jackson.map.deser.BeanDeserializer
    public Object deserializeFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        int i;
        Object[] objArr;
        Object obj;
        if (this._propertyBasedCreator != null) {
            return _deserializeUsingPropertyBased(jsonParser, deserializationContext);
        }
        if (this._delegatingCreator != null) {
            return this._delegatingCreator.deserialize(jsonParser, deserializationContext);
        }
        if (this._beanType.isAbstract()) {
            throw JsonMappingException.from(jsonParser, "Can not instantiate abstract type " + this._beanType + " (need to add/enable type information?)");
        }
        if (this._stringCreator == null) {
            throw new JsonMappingException("Can not deserialize Throwable of type " + this._beanType + " without having either single-String-arg constructor; or explicit @JsonCreator");
        }
        int i2 = 0;
        Object[] objArr2 = null;
        Object objConstruct = null;
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            SettableBeanProperty settableBeanPropertyFind = this._beanProperties.find(currentName);
            jsonParser.nextToken();
            if (settableBeanPropertyFind != null) {
                if (objConstruct != null) {
                    settableBeanPropertyFind.deserializeAndSet(jsonParser, deserializationContext, objConstruct);
                    i = i2;
                    objArr = objArr2;
                    obj = objConstruct;
                } else {
                    if (objArr2 == null) {
                        int size = this._beanProperties.size();
                        objArr2 = new Object[size + size];
                    }
                    int i3 = i2 + 1;
                    objArr2[i2] = settableBeanPropertyFind;
                    i = i3 + 1;
                    objArr2[i3] = settableBeanPropertyFind.deserialize(jsonParser, deserializationContext);
                    objArr = objArr2;
                    obj = objConstruct;
                }
            } else if (PROP_NAME_MESSAGE.equals(currentName)) {
                objConstruct = this._stringCreator.construct(jsonParser.getText());
                if (objArr2 != null) {
                    for (int i4 = 0; i4 < i2; i4 += 2) {
                        ((SettableBeanProperty) objArr2[i4]).set(objConstruct, objArr2[i4 + 1]);
                    }
                    i = i2;
                    obj = objConstruct;
                    objArr = null;
                } else {
                    i = i2;
                    objArr = objArr2;
                    obj = objConstruct;
                }
            } else if (this._ignorableProps != null && this._ignorableProps.contains(currentName)) {
                jsonParser.skipChildren();
                i = i2;
                objArr = objArr2;
                obj = objConstruct;
            } else if (this._anySetter != null) {
                this._anySetter.deserializeAndSet(jsonParser, deserializationContext, objConstruct, currentName);
                i = i2;
                objArr = objArr2;
                obj = objConstruct;
            } else {
                handleUnknownProperty(jsonParser, deserializationContext, objConstruct, currentName);
                i = i2;
                objArr = objArr2;
                obj = objConstruct;
            }
            jsonParser.nextToken();
            objConstruct = obj;
            objArr2 = objArr;
            i2 = i;
        }
        if (objConstruct == null) {
            Object objConstruct2 = this._stringCreator.construct(null);
            if (objArr2 != null) {
                for (int i5 = 0; i5 < i2; i5 += 2) {
                    ((SettableBeanProperty) objArr2[i5]).set(objConstruct2, objArr2[i5 + 1]);
                }
                return objConstruct2;
            }
            return objConstruct2;
        }
        return objConstruct;
    }
}
