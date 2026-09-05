package org.codehaus.jackson.map.type;

import org.codehaus.jackson.type.JavaType;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class MapType extends MapLikeType {
    private MapType(Class<?> cls, JavaType javaType, JavaType javaType2) {
        super(cls, javaType, javaType2);
    }

    public static MapType construct(Class<?> cls, JavaType javaType, JavaType javaType2) {
        return new MapType(cls, javaType, javaType2);
    }

    @Override // org.codehaus.jackson.map.type.MapLikeType, org.codehaus.jackson.type.JavaType
    protected JavaType _narrow(Class<?> cls) {
        return new MapType(cls, this._keyType, this._valueType);
    }

    @Override // org.codehaus.jackson.map.type.MapLikeType, org.codehaus.jackson.type.JavaType
    public JavaType narrowContentsBy(Class<?> cls) {
        return cls == this._valueType.getRawClass() ? this : new MapType(this._class, this._keyType, this._valueType.narrowBy(cls)).copyHandlers(this);
    }

    @Override // org.codehaus.jackson.map.type.MapLikeType, org.codehaus.jackson.type.JavaType
    public JavaType widenContentsBy(Class<?> cls) {
        return cls == this._valueType.getRawClass() ? this : new MapType(this._class, this._keyType, this._valueType.widenBy(cls)).copyHandlers(this);
    }

    @Override // org.codehaus.jackson.map.type.MapLikeType
    public JavaType narrowKey(Class<?> cls) {
        return cls == this._keyType.getRawClass() ? this : new MapType(this._class, this._keyType.narrowBy(cls), this._valueType).copyHandlers(this);
    }

    @Override // org.codehaus.jackson.map.type.MapLikeType
    public JavaType widenKey(Class<?> cls) {
        return cls == this._keyType.getRawClass() ? this : new MapType(this._class, this._keyType.widenBy(cls), this._valueType).copyHandlers(this);
    }

    @Override // org.codehaus.jackson.map.type.MapLikeType, org.codehaus.jackson.type.JavaType
    public MapType withTypeHandler(Object obj) {
        MapType mapType = new MapType(this._class, this._keyType, this._valueType);
        mapType._typeHandler = obj;
        return mapType;
    }

    @Override // org.codehaus.jackson.map.type.MapLikeType, org.codehaus.jackson.type.JavaType
    public MapType withContentTypeHandler(Object obj) {
        return new MapType(this._class, this._keyType, this._valueType.withTypeHandler(obj));
    }

    @Override // org.codehaus.jackson.map.type.MapLikeType, org.codehaus.jackson.type.JavaType
    public String toString() {
        return "[map type; class " + this._class.getName() + ", " + this._keyType + " -> " + this._valueType + "]";
    }
}
