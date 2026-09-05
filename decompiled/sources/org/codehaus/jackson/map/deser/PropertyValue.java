package org.codehaus.jackson.map.deser;

import java.io.IOException;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
abstract class PropertyValue {
    public final PropertyValue next;
    public final Object value;

    public abstract void assign(Object obj);

    protected PropertyValue(PropertyValue propertyValue, Object obj) {
        this.next = propertyValue;
        this.value = obj;
    }

    final class Regular extends PropertyValue {
        final SettableBeanProperty _property;

        public Regular(PropertyValue propertyValue, Object obj, SettableBeanProperty settableBeanProperty) {
            super(propertyValue, obj);
            this._property = settableBeanProperty;
        }

        @Override // org.codehaus.jackson.map.deser.PropertyValue
        public void assign(Object obj) {
            this._property.set(obj, this.value);
        }
    }

    final class Any extends PropertyValue {
        final SettableAnyProperty _property;
        final String _propertyName;

        public Any(PropertyValue propertyValue, Object obj, SettableAnyProperty settableAnyProperty, String str) {
            super(propertyValue, obj);
            this._property = settableAnyProperty;
            this._propertyName = str;
        }

        @Override // org.codehaus.jackson.map.deser.PropertyValue
        public void assign(Object obj) throws IOException {
            this._property.set(obj, this._propertyName, this.value);
        }
    }

    final class Map extends PropertyValue {
        final Object _key;

        public Map(PropertyValue propertyValue, Object obj, Object obj2) {
            super(propertyValue, obj);
            this._key = obj2;
        }

        @Override // org.codehaus.jackson.map.deser.PropertyValue
        public void assign(Object obj) {
            ((java.util.Map) obj).put(this._key, this.value);
        }
    }
}
