package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ResolvableDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.annotate.JsonCachable;
import org.codehaus.jackson.map.deser.impl.BeanPropertyMap;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.type.ClassKey;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.util.TokenBuffer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
@JsonCachable
public class BeanDeserializer extends StdDeserializer<Object> implements ResolvableDeserializer {
    protected final SettableAnyProperty _anySetter;
    protected final Map<String, SettableBeanProperty> _backRefs;
    protected final BeanPropertyMap _beanProperties;
    protected final JavaType _beanType;
    protected final Constructor<?> _defaultConstructor;
    protected final Creator.Delegating _delegatingCreator;
    protected final AnnotatedClass _forClass;
    protected final HashSet<String> _ignorableProps;
    protected final boolean _ignoreAllUnknown;
    protected final Creator.NumberBased _numberCreator;
    protected final BeanProperty _property;
    protected final Creator.PropertyBased _propertyBasedCreator;
    protected final Creator.StringBased _stringCreator;
    protected HashMap<ClassKey, JsonDeserializer<Object>> _subDeserializers;

    public BeanDeserializer(AnnotatedClass annotatedClass, JavaType javaType, BeanProperty beanProperty, CreatorContainer creatorContainer, BeanPropertyMap beanPropertyMap, Map<String, SettableBeanProperty> map, HashSet<String> hashSet, boolean z, SettableAnyProperty settableAnyProperty) {
        super(javaType);
        this._forClass = annotatedClass;
        this._beanType = javaType;
        this._property = beanProperty;
        this._beanProperties = beanPropertyMap;
        this._backRefs = map;
        this._ignorableProps = hashSet;
        this._ignoreAllUnknown = z;
        this._anySetter = settableAnyProperty;
        this._stringCreator = creatorContainer.stringCreator();
        this._numberCreator = creatorContainer.numberCreator();
        this._delegatingCreator = creatorContainer.delegatingCreator();
        this._propertyBasedCreator = creatorContainer.propertyBasedCreator();
        if (this._delegatingCreator != null || this._propertyBasedCreator != null) {
            this._defaultConstructor = null;
        } else {
            this._defaultConstructor = creatorContainer.getDefaultConstructor();
        }
    }

    protected BeanDeserializer(BeanDeserializer beanDeserializer) {
        super(beanDeserializer._beanType);
        this._forClass = beanDeserializer._forClass;
        this._beanType = beanDeserializer._beanType;
        this._property = beanDeserializer._property;
        this._beanProperties = beanDeserializer._beanProperties;
        this._backRefs = beanDeserializer._backRefs;
        this._ignorableProps = beanDeserializer._ignorableProps;
        this._ignoreAllUnknown = beanDeserializer._ignoreAllUnknown;
        this._anySetter = beanDeserializer._anySetter;
        this._defaultConstructor = beanDeserializer._defaultConstructor;
        this._stringCreator = beanDeserializer._stringCreator;
        this._numberCreator = beanDeserializer._numberCreator;
        this._delegatingCreator = beanDeserializer._delegatingCreator;
        this._propertyBasedCreator = beanDeserializer._propertyBasedCreator;
    }

    public boolean hasProperty(String str) {
        return this._beanProperties.find(str) != null;
    }

    public int getPropertyCount() {
        return this._beanProperties.size();
    }

    @Override // org.codehaus.jackson.map.ResolvableDeserializer
    public void resolve(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider) {
        SettableBeanProperty settableBeanPropertyFindBackReference;
        Iterator<SettableBeanProperty> itAllProperties = this._beanProperties.allProperties();
        while (itAllProperties.hasNext()) {
            SettableBeanProperty next = itAllProperties.next();
            if (!next.hasValueDeserializer()) {
                next.setValueDeserializer(findDeserializer(deserializationConfig, deserializerProvider, next.getType(), next));
            }
            String managedReferenceName = next.getManagedReferenceName();
            if (managedReferenceName != null) {
                JsonDeserializer<Object> jsonDeserializer = next._valueDeserializer;
                boolean z = false;
                if (jsonDeserializer instanceof BeanDeserializer) {
                    settableBeanPropertyFindBackReference = ((BeanDeserializer) jsonDeserializer).findBackReference(managedReferenceName);
                } else {
                    if (!(jsonDeserializer instanceof ContainerDeserializer)) {
                        if (jsonDeserializer instanceof AbstractDeserializer) {
                            throw new IllegalArgumentException("Can not handle managed/back reference for abstract types (property " + this._beanType.getRawClass().getName() + "." + next.getName() + ")");
                        }
                        throw new IllegalArgumentException("Can not handle managed/back reference '" + managedReferenceName + "': type for value deserializer is not BeanDeserializer or ContainerDeserializer, but " + jsonDeserializer.getClass().getName());
                    }
                    JsonDeserializer<Object> contentDeserializer = ((ContainerDeserializer) jsonDeserializer).getContentDeserializer();
                    if (!(contentDeserializer instanceof BeanDeserializer)) {
                        throw new IllegalArgumentException("Can not handle managed/back reference '" + managedReferenceName + "': value deserializer is of type ContainerDeserializer, but content type is not handled by a BeanDeserializer  (instead it's of type " + contentDeserializer.getClass().getName() + ")");
                    }
                    settableBeanPropertyFindBackReference = ((BeanDeserializer) contentDeserializer).findBackReference(managedReferenceName);
                    z = true;
                }
                if (settableBeanPropertyFindBackReference == null) {
                    throw new IllegalArgumentException("Can not handle managed/back reference '" + managedReferenceName + "': no back reference property found from type " + next.getType());
                }
                JavaType javaType = this._beanType;
                JavaType type = settableBeanPropertyFindBackReference.getType();
                if (!type.getRawClass().isAssignableFrom(javaType.getRawClass())) {
                    throw new IllegalArgumentException("Can not handle managed/back reference '" + managedReferenceName + "': back reference type (" + type.getRawClass().getName() + ") not compatible with managed type (" + javaType.getRawClass().getName() + ")");
                }
                this._beanProperties.replace(new SettableBeanProperty.ManagedReferenceProperty(managedReferenceName, next, settableBeanPropertyFindBackReference, this._forClass.getAnnotations(), z));
            }
        }
        if (this._anySetter != null && !this._anySetter.hasValueDeserializer()) {
            this._anySetter.setValueDeserializer(findDeserializer(deserializationConfig, deserializerProvider, this._anySetter.getType(), this._anySetter.getProperty()));
        }
        if (this._delegatingCreator != null) {
            this._delegatingCreator.setDeserializer(findDeserializer(deserializationConfig, deserializerProvider, this._delegatingCreator.getValueType(), new BeanProperty.Std(null, this._delegatingCreator.getValueType(), this._forClass.getAnnotations(), this._delegatingCreator.getCreator())));
        }
        if (this._propertyBasedCreator != null) {
            for (SettableBeanProperty settableBeanProperty : this._propertyBasedCreator.properties()) {
                if (!settableBeanProperty.hasValueDeserializer()) {
                    settableBeanProperty.setValueDeserializer(findDeserializer(deserializationConfig, deserializerProvider, settableBeanProperty.getType(), settableBeanProperty));
                }
            }
        }
    }

    @Override // org.codehaus.jackson.map.JsonDeserializer
    public final Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            jsonParser.nextToken();
            return deserializeFromObject(jsonParser, deserializationContext);
        }
        switch (currentToken) {
            case VALUE_STRING:
                return deserializeFromString(jsonParser, deserializationContext);
            case VALUE_NUMBER_INT:
            case VALUE_NUMBER_FLOAT:
                return deserializeFromNumber(jsonParser, deserializationContext);
            case VALUE_EMBEDDED_OBJECT:
                return jsonParser.getEmbeddedObject();
            case VALUE_TRUE:
            case VALUE_FALSE:
            case START_ARRAY:
                return deserializeUsingCreator(jsonParser, deserializationContext);
            case FIELD_NAME:
            case END_OBJECT:
                return deserializeFromObject(jsonParser, deserializationContext);
            default:
                throw deserializationContext.mappingException(getBeanClass());
        }
    }

    @Override // org.codehaus.jackson.map.JsonDeserializer
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            SettableBeanProperty settableBeanPropertyFind = this._beanProperties.find(currentName);
            jsonParser.nextToken();
            if (settableBeanPropertyFind != null) {
                try {
                    settableBeanPropertyFind.deserializeAndSet(jsonParser, deserializationContext, obj);
                } catch (Exception e) {
                    wrapAndThrow(e, obj, currentName, deserializationContext);
                }
            } else if (this._ignorableProps != null && this._ignorableProps.contains(currentName)) {
                jsonParser.skipChildren();
            } else if (this._anySetter != null) {
                this._anySetter.deserializeAndSet(jsonParser, deserializationContext, obj, currentName);
            } else {
                handleUnknownProperty(jsonParser, deserializationContext, obj, currentName);
            }
            currentToken = jsonParser.nextToken();
        }
        return obj;
    }

    @Override // org.codehaus.jackson.map.deser.StdDeserializer, org.codehaus.jackson.map.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }

    public final Class<?> getBeanClass() {
        return this._beanType.getRawClass();
    }

    @Override // org.codehaus.jackson.map.deser.StdDeserializer
    public JavaType getValueType() {
        return this._beanType;
    }

    public Iterator<SettableBeanProperty> properties() {
        if (this._beanProperties == null) {
            throw new IllegalStateException("Can only call before BeanDeserializer has been resolved");
        }
        return this._beanProperties.allProperties();
    }

    public SettableBeanProperty findBackReference(String str) {
        if (this._backRefs == null) {
            return null;
        }
        return this._backRefs.get(str);
    }

    public Object deserializeFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (this._defaultConstructor == null) {
            if (this._propertyBasedCreator != null) {
                return _deserializeUsingPropertyBased(jsonParser, deserializationContext);
            }
            if (this._delegatingCreator != null) {
                return this._delegatingCreator.deserialize(jsonParser, deserializationContext);
            }
            if (this._beanType.isAbstract()) {
                throw JsonMappingException.from(jsonParser, "Can not instantiate abstract type " + this._beanType + " (need to add/enable type information?)");
            }
            throw JsonMappingException.from(jsonParser, "No suitable constructor found for type " + this._beanType + ": can not instantiate from JSON object (need to add/enable type information?)");
        }
        Object objConstructDefaultInstance = constructDefaultInstance();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            SettableBeanProperty settableBeanPropertyFind = this._beanProperties.find(currentName);
            if (settableBeanPropertyFind != null) {
                try {
                    settableBeanPropertyFind.deserializeAndSet(jsonParser, deserializationContext, objConstructDefaultInstance);
                } catch (Exception e) {
                    wrapAndThrow(e, objConstructDefaultInstance, currentName, deserializationContext);
                }
            } else if (this._ignorableProps != null && this._ignorableProps.contains(currentName)) {
                jsonParser.skipChildren();
            } else if (this._anySetter != null) {
                try {
                    this._anySetter.deserializeAndSet(jsonParser, deserializationContext, objConstructDefaultInstance, currentName);
                } catch (Exception e2) {
                    wrapAndThrow(e2, objConstructDefaultInstance, currentName, deserializationContext);
                }
            } else {
                handleUnknownProperty(jsonParser, deserializationContext, objConstructDefaultInstance, currentName);
            }
            jsonParser.nextToken();
        }
        return objConstructDefaultInstance;
    }

    public Object deserializeFromString(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
        if (this._stringCreator != null) {
            return this._stringCreator.construct(jsonParser.getText());
        }
        if (this._delegatingCreator != null) {
            return this._delegatingCreator.deserialize(jsonParser, deserializationContext);
        }
        if (deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getTextLength() == 0) {
            return null;
        }
        throw deserializationContext.instantiationException(getBeanClass(), "no suitable creator method found to deserialize from JSON String");
    }

    public Object deserializeFromNumber(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
        if (this._numberCreator != null) {
            switch (jsonParser.getNumberType()) {
                case INT:
                    return this._numberCreator.construct(jsonParser.getIntValue());
                case LONG:
                    return this._numberCreator.construct(jsonParser.getLongValue());
            }
        }
        if (this._delegatingCreator != null) {
            return this._delegatingCreator.deserialize(jsonParser, deserializationContext);
        }
        throw deserializationContext.instantiationException(getBeanClass(), "no suitable creator method found to deserialize from JSON Number");
    }

    public Object deserializeUsingCreator(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (this._delegatingCreator != null) {
            try {
                return this._delegatingCreator.deserialize(jsonParser, deserializationContext);
            } catch (Exception e) {
                wrapInstantiationProblem(e, deserializationContext);
            }
        }
        throw deserializationContext.mappingException(getBeanClass());
    }

    protected final Object _deserializeUsingPropertyBased(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Creator.PropertyBased propertyBased = this._propertyBasedCreator;
        PropertyValueBuffer propertyValueBufferStartBuilding = propertyBased.startBuilding(jsonParser, deserializationContext);
        JsonToken currentToken = jsonParser.getCurrentToken();
        TokenBuffer tokenBuffer = null;
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            SettableBeanProperty settableBeanPropertyFindCreatorProperty = propertyBased.findCreatorProperty(currentName);
            if (settableBeanPropertyFindCreatorProperty != null) {
                if (propertyValueBufferStartBuilding.assignParameter(settableBeanPropertyFindCreatorProperty.getCreatorIndex(), settableBeanPropertyFindCreatorProperty.deserialize(jsonParser, deserializationContext))) {
                    jsonParser.nextToken();
                    try {
                        Object objBuild = propertyBased.build(propertyValueBufferStartBuilding);
                        if (objBuild.getClass() != this._beanType.getRawClass()) {
                            return handlePolymorphic(jsonParser, deserializationContext, objBuild, tokenBuffer);
                        }
                        return deserialize(jsonParser, deserializationContext, tokenBuffer != null ? handleUnknownProperties(deserializationContext, objBuild, tokenBuffer) : objBuild);
                    } catch (Exception e) {
                        wrapAndThrow(e, this._beanType.getRawClass(), currentName, deserializationContext);
                    }
                } else {
                    continue;
                }
            } else {
                SettableBeanProperty settableBeanPropertyFind = this._beanProperties.find(currentName);
                if (settableBeanPropertyFind != null) {
                    propertyValueBufferStartBuilding.bufferProperty(settableBeanPropertyFind, settableBeanPropertyFind.deserialize(jsonParser, deserializationContext));
                } else if (this._ignorableProps != null && this._ignorableProps.contains(currentName)) {
                    jsonParser.skipChildren();
                } else if (this._anySetter != null) {
                    propertyValueBufferStartBuilding.bufferAnyProperty(this._anySetter, currentName, this._anySetter.deserialize(jsonParser, deserializationContext));
                } else {
                    if (tokenBuffer == null) {
                        tokenBuffer = new TokenBuffer(jsonParser.getCodec());
                    }
                    tokenBuffer.writeFieldName(currentName);
                    tokenBuffer.copyCurrentStructure(jsonParser);
                }
            }
            currentToken = jsonParser.nextToken();
        }
        try {
            Object objBuild2 = propertyBased.build(propertyValueBufferStartBuilding);
            if (tokenBuffer == null) {
                return objBuild2;
            }
            if (objBuild2.getClass() != this._beanType.getRawClass()) {
                return handlePolymorphic(null, deserializationContext, objBuild2, tokenBuffer);
            }
            return handleUnknownProperties(deserializationContext, objBuild2, tokenBuffer);
        } catch (Exception e2) {
            wrapInstantiationProblem(e2, deserializationContext);
            return null;
        }
    }

    @Override // org.codehaus.jackson.map.deser.StdDeserializer
    protected void handleUnknownProperty(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) throws JsonMappingException {
        if (this._ignoreAllUnknown || (this._ignorableProps != null && this._ignorableProps.contains(str))) {
            jsonParser.skipChildren();
        } else {
            super.handleUnknownProperty(jsonParser, deserializationContext, obj, str);
        }
    }

    protected Object handleUnknownProperties(DeserializationContext deserializationContext, Object obj, TokenBuffer tokenBuffer) throws JsonMappingException {
        tokenBuffer.writeEndObject();
        JsonParser jsonParserAsParser = tokenBuffer.asParser();
        while (jsonParserAsParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParserAsParser.getCurrentName();
            jsonParserAsParser.nextToken();
            handleUnknownProperty(jsonParserAsParser, deserializationContext, obj, currentName);
        }
        return obj;
    }

    protected Object handlePolymorphic(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, TokenBuffer tokenBuffer) {
        Object objDeserialize;
        JsonDeserializer<Object> jsonDeserializer_findSubclassDeserializer = _findSubclassDeserializer(deserializationContext, obj, tokenBuffer);
        if (jsonDeserializer_findSubclassDeserializer != null) {
            if (tokenBuffer != null) {
                tokenBuffer.writeEndObject();
                JsonParser jsonParserAsParser = tokenBuffer.asParser();
                jsonParserAsParser.nextToken();
                objDeserialize = jsonDeserializer_findSubclassDeserializer.deserialize(jsonParserAsParser, deserializationContext, obj);
            } else {
                objDeserialize = obj;
            }
            if (jsonParser != null) {
                return jsonDeserializer_findSubclassDeserializer.deserialize(jsonParser, deserializationContext, objDeserialize);
            }
            return objDeserialize;
        }
        Object objHandleUnknownProperties = tokenBuffer != null ? handleUnknownProperties(deserializationContext, obj, tokenBuffer) : obj;
        if (jsonParser != null) {
            return deserialize(jsonParser, deserializationContext, objHandleUnknownProperties);
        }
        return objHandleUnknownProperties;
    }

    protected JsonDeserializer<Object> _findSubclassDeserializer(DeserializationContext deserializationContext, Object obj, TokenBuffer tokenBuffer) {
        JsonDeserializer<Object> jsonDeserializerFindValueDeserializer;
        DeserializerProvider deserializerProvider;
        synchronized (this) {
            jsonDeserializerFindValueDeserializer = this._subDeserializers == null ? null : this._subDeserializers.get(new ClassKey(obj.getClass()));
        }
        if (jsonDeserializerFindValueDeserializer == null && (deserializerProvider = deserializationContext.getDeserializerProvider()) != null) {
            jsonDeserializerFindValueDeserializer = deserializerProvider.findValueDeserializer(deserializationContext.getConfig(), deserializationContext.constructType(obj.getClass()), this._property);
            if (jsonDeserializerFindValueDeserializer != null) {
                synchronized (this) {
                    if (this._subDeserializers == null) {
                        this._subDeserializers = new HashMap<>();
                    }
                    this._subDeserializers.put(new ClassKey(obj.getClass()), jsonDeserializerFindValueDeserializer);
                }
            }
        }
        return jsonDeserializerFindValueDeserializer;
    }

    protected Object constructDefaultInstance() {
        try {
            return this._defaultConstructor.newInstance(new Object[0]);
        } catch (Exception e) {
            ClassUtil.unwrapAndThrowAsIAE(e);
            return null;
        }
    }

    public void wrapAndThrow(Throwable th, Object obj, String str, DeserializationContext deserializationContext) throws IOException {
        Throwable cause = th;
        while ((cause instanceof InvocationTargetException) && cause.getCause() != null) {
            cause = cause.getCause();
        }
        if (cause instanceof Error) {
            throw ((Error) cause);
        }
        boolean z = deserializationContext == null || deserializationContext.isEnabled(DeserializationConfig.Feature.WRAP_EXCEPTIONS);
        if (cause instanceof IOException) {
            if (!z || !(cause instanceof JsonMappingException)) {
                throw ((IOException) cause);
            }
        } else if (!z && (cause instanceof RuntimeException)) {
            throw ((RuntimeException) cause);
        }
        throw JsonMappingException.wrapWithPath(cause, obj, str);
    }

    public void wrapAndThrow(Throwable th, Object obj, int i, DeserializationContext deserializationContext) throws IOException {
        Throwable cause = th;
        while ((cause instanceof InvocationTargetException) && cause.getCause() != null) {
            cause = cause.getCause();
        }
        if (cause instanceof Error) {
            throw ((Error) cause);
        }
        boolean z = deserializationContext == null || deserializationContext.isEnabled(DeserializationConfig.Feature.WRAP_EXCEPTIONS);
        if (cause instanceof IOException) {
            if (!z || !(cause instanceof JsonMappingException)) {
                throw ((IOException) cause);
            }
        } else if (!z && (cause instanceof RuntimeException)) {
            throw ((RuntimeException) cause);
        }
        throw JsonMappingException.wrapWithPath(cause, obj, i);
    }

    protected void wrapInstantiationProblem(Throwable th, DeserializationContext deserializationContext) throws IOException {
        Throwable cause = th;
        while ((cause instanceof InvocationTargetException) && cause.getCause() != null) {
            cause = cause.getCause();
        }
        if (cause instanceof Error) {
            throw ((Error) cause);
        }
        boolean z = deserializationContext == null || deserializationContext.isEnabled(DeserializationConfig.Feature.WRAP_EXCEPTIONS);
        if (cause instanceof IOException) {
            throw ((IOException) cause);
        }
        if (!z && (cause instanceof RuntimeException)) {
            throw ((RuntimeException) cause);
        }
        throw deserializationContext.instantiationException(this._beanType.getRawClass(), cause);
    }

    @Deprecated
    public void wrapAndThrow(Throwable th, Object obj, String str) throws IOException {
        wrapAndThrow(th, obj, str, (DeserializationContext) null);
    }

    @Deprecated
    public void wrapAndThrow(Throwable th, Object obj, int i) throws IOException {
        wrapAndThrow(th, obj, i, (DeserializationContext) null);
    }
}
