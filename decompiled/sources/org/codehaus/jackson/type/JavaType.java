package org.codehaus.jackson.type;

import java.lang.reflect.Modifier;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class JavaType {
    protected final Class<?> _class;
    protected final int _hashCode;
    protected Object _typeHandler;
    protected Object _valueHandler;

    protected abstract JavaType _narrow(Class<?> cls);

    public abstract boolean equals(Object obj);

    public abstract StringBuilder getErasedSignature(StringBuilder sb);

    public abstract StringBuilder getGenericSignature(StringBuilder sb);

    public abstract boolean isContainerType();

    public abstract JavaType narrowContentsBy(Class<?> cls);

    public abstract String toCanonical();

    public abstract String toString();

    public abstract JavaType widenContentsBy(Class<?> cls);

    public abstract JavaType withContentTypeHandler(Object obj);

    public abstract JavaType withTypeHandler(Object obj);

    protected JavaType(Class<?> cls, int i) {
        this._class = cls;
        this._hashCode = cls.getName().hashCode() + i;
    }

    public final JavaType narrowBy(Class<?> cls) {
        if (cls != this._class) {
            _assertSubclass(cls, this._class);
            JavaType javaType_narrow = _narrow(cls);
            if (this._valueHandler != null) {
                javaType_narrow.setValueHandler(this._valueHandler);
            }
            if (this._typeHandler != null) {
                javaType_narrow = javaType_narrow.withTypeHandler(this._typeHandler);
            }
            return javaType_narrow;
        }
        return this;
    }

    public final JavaType forcedNarrowBy(Class<?> cls) {
        if (cls != this._class) {
            JavaType javaType_narrow = _narrow(cls);
            if (this._valueHandler != null) {
                javaType_narrow.setValueHandler(this._valueHandler);
            }
            if (this._typeHandler != null) {
                javaType_narrow = javaType_narrow.withTypeHandler(this._typeHandler);
            }
            return javaType_narrow;
        }
        return this;
    }

    public final JavaType widenBy(Class<?> cls) {
        if (cls != this._class) {
            _assertSubclass(this._class, cls);
            return _widen(cls);
        }
        return this;
    }

    protected JavaType _widen(Class<?> cls) {
        return _narrow(cls);
    }

    public void setValueHandler(Object obj) {
        if (obj != null && this._valueHandler != null) {
            throw new IllegalStateException("Trying to reset value handler for type [" + toString() + "]; old handler of type " + this._valueHandler.getClass().getName() + ", new handler of type " + obj.getClass().getName());
        }
        this._valueHandler = obj;
    }

    @Deprecated
    public void setTypeHandler(Object obj) {
        if (obj != null && this._typeHandler != null) {
            throw new IllegalStateException("Trying to reset type handler for type [" + toString() + "]; old handler of type " + this._typeHandler.getClass().getName() + ", new handler of type " + obj.getClass().getName());
        }
        this._typeHandler = obj;
    }

    public final Class<?> getRawClass() {
        return this._class;
    }

    public final boolean hasRawClass(Class<?> cls) {
        return this._class == cls;
    }

    public boolean isAbstract() {
        return Modifier.isAbstract(this._class.getModifiers());
    }

    public boolean isConcrete() {
        return (this._class.getModifiers() & 1536) == 0 || this._class.isPrimitive();
    }

    public boolean isThrowable() {
        return Throwable.class.isAssignableFrom(this._class);
    }

    public boolean isArrayType() {
        return false;
    }

    public final boolean isEnumType() {
        return this._class.isEnum();
    }

    public final boolean isInterface() {
        return this._class.isInterface();
    }

    public final boolean isPrimitive() {
        return this._class.isPrimitive();
    }

    public final boolean isFinal() {
        return Modifier.isFinal(this._class.getModifiers());
    }

    public boolean isCollectionLikeType() {
        return false;
    }

    public boolean isMapLikeType() {
        return false;
    }

    public boolean hasGenericTypes() {
        return containedTypeCount() > 0;
    }

    public JavaType getKeyType() {
        return null;
    }

    public JavaType getContentType() {
        return null;
    }

    public int containedTypeCount() {
        return 0;
    }

    public JavaType containedType(int i) {
        return null;
    }

    public String containedTypeName(int i) {
        return null;
    }

    public <T> T getValueHandler() {
        return (T) this._valueHandler;
    }

    public <T> T getTypeHandler() {
        return (T) this._typeHandler;
    }

    public String getGenericSignature() {
        StringBuilder sb = new StringBuilder(40);
        getGenericSignature(sb);
        return sb.toString();
    }

    public String getErasedSignature() {
        StringBuilder sb = new StringBuilder(40);
        getErasedSignature(sb);
        return sb.toString();
    }

    protected void _assertSubclass(Class<?> cls, Class<?> cls2) {
        if (!this._class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Class " + cls.getName() + " is not assignable to " + this._class.getName());
        }
    }

    public final int hashCode() {
        return this._hashCode;
    }
}
