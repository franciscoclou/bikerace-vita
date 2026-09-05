package org.codehaus.jackson.map.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class HierarchicType {
    protected final Type _actualType;
    protected final ParameterizedType _genericType;
    protected final Class<?> _rawClass;
    protected HierarchicType _subType;
    protected HierarchicType _superType;

    public HierarchicType(Type type) {
        this._actualType = type;
        if (type instanceof Class) {
            this._rawClass = (Class) type;
            this._genericType = null;
        } else {
            if (type instanceof ParameterizedType) {
                this._genericType = (ParameterizedType) type;
                this._rawClass = (Class) this._genericType.getRawType();
                return;
            }
            throw new IllegalArgumentException("Type " + type.getClass().getName() + " can not be used to construct HierarchicType");
        }
    }

    public void setSuperType(HierarchicType hierarchicType) {
        this._superType = hierarchicType;
    }

    public HierarchicType getSuperType() {
        return this._superType;
    }

    public void setSubType(HierarchicType hierarchicType) {
        this._subType = hierarchicType;
    }

    public HierarchicType getSubType() {
        return this._subType;
    }

    public boolean isGeneric() {
        return this._genericType != null;
    }

    public ParameterizedType asGeneric() {
        return this._genericType;
    }

    public Class<?> getRawClass() {
        return this._rawClass;
    }

    public String toString() {
        return this._genericType != null ? this._genericType.toString() : this._rawClass.getName();
    }
}
