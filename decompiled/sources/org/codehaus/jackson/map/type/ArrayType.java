package org.codehaus.jackson.map.type;

import java.lang.reflect.Array;
import org.codehaus.jackson.type.JavaType;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class ArrayType extends TypeBase {
    final JavaType _componentType;
    final Object _emptyArray;

    private ArrayType(JavaType javaType, Object obj) {
        super(obj.getClass(), javaType.hashCode());
        this._componentType = javaType;
        this._emptyArray = obj;
    }

    public static ArrayType construct(JavaType javaType) {
        return new ArrayType(javaType, Array.newInstance(javaType.getRawClass(), 0));
    }

    @Override // org.codehaus.jackson.type.JavaType
    public ArrayType withTypeHandler(Object obj) {
        ArrayType arrayType = new ArrayType(this._componentType, this._emptyArray);
        arrayType._typeHandler = obj;
        return arrayType;
    }

    @Override // org.codehaus.jackson.type.JavaType
    public ArrayType withContentTypeHandler(Object obj) {
        return new ArrayType(this._componentType.withTypeHandler(obj), this._emptyArray);
    }

    @Override // org.codehaus.jackson.map.type.TypeBase
    protected String buildCanonicalName() {
        return this._class.getName();
    }

    @Override // org.codehaus.jackson.type.JavaType
    protected JavaType _narrow(Class<?> cls) {
        if (!cls.isArray()) {
            throw new IllegalArgumentException("Incompatible narrowing operation: trying to narrow " + toString() + " to class " + cls.getName());
        }
        return construct(TypeFactory.defaultInstance().constructType(cls.getComponentType()));
    }

    @Override // org.codehaus.jackson.type.JavaType
    public JavaType narrowContentsBy(Class<?> cls) {
        return cls == this._componentType.getRawClass() ? this : construct(this._componentType.narrowBy(cls)).copyHandlers(this);
    }

    @Override // org.codehaus.jackson.type.JavaType
    public JavaType widenContentsBy(Class<?> cls) {
        return cls == this._componentType.getRawClass() ? this : construct(this._componentType.widenBy(cls)).copyHandlers(this);
    }

    @Override // org.codehaus.jackson.type.JavaType
    public boolean isArrayType() {
        return true;
    }

    @Override // org.codehaus.jackson.type.JavaType
    public boolean isAbstract() {
        return false;
    }

    @Override // org.codehaus.jackson.type.JavaType
    public boolean isConcrete() {
        return true;
    }

    @Override // org.codehaus.jackson.type.JavaType
    public boolean hasGenericTypes() {
        return this._componentType.hasGenericTypes();
    }

    @Override // org.codehaus.jackson.type.JavaType
    public String containedTypeName(int i) {
        if (i == 0) {
            return "E";
        }
        return null;
    }

    @Override // org.codehaus.jackson.type.JavaType
    public boolean isContainerType() {
        return true;
    }

    @Override // org.codehaus.jackson.type.JavaType
    public JavaType getContentType() {
        return this._componentType;
    }

    @Override // org.codehaus.jackson.type.JavaType
    public int containedTypeCount() {
        return 1;
    }

    @Override // org.codehaus.jackson.type.JavaType
    public JavaType containedType(int i) {
        if (i == 0) {
            return this._componentType;
        }
        return null;
    }

    @Override // org.codehaus.jackson.map.type.TypeBase, org.codehaus.jackson.type.JavaType
    public StringBuilder getGenericSignature(StringBuilder sb) {
        sb.append('[');
        return this._componentType.getGenericSignature(sb);
    }

    @Override // org.codehaus.jackson.map.type.TypeBase, org.codehaus.jackson.type.JavaType
    public StringBuilder getErasedSignature(StringBuilder sb) {
        sb.append('[');
        return this._componentType.getErasedSignature(sb);
    }

    @Override // org.codehaus.jackson.type.JavaType
    public String toString() {
        return "[array type, component type: " + this._componentType + "]";
    }

    @Override // org.codehaus.jackson.type.JavaType
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return this._componentType.equals(((ArrayType) obj)._componentType);
    }
}
