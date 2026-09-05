package org.codehaus.jackson.map.introspect;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.introspect.VisibilityChecker;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface VisibilityChecker<T extends VisibilityChecker<T>> {
    boolean isCreatorVisible(Member member);

    boolean isCreatorVisible(AnnotatedMember annotatedMember);

    boolean isFieldVisible(Field field);

    boolean isFieldVisible(AnnotatedField annotatedField);

    boolean isGetterVisible(Method method);

    boolean isGetterVisible(AnnotatedMethod annotatedMethod);

    boolean isIsGetterVisible(Method method);

    boolean isIsGetterVisible(AnnotatedMethod annotatedMethod);

    boolean isSetterVisible(Method method);

    boolean isSetterVisible(AnnotatedMethod annotatedMethod);

    T with(JsonAutoDetect jsonAutoDetect);

    T withCreatorVisibility(JsonAutoDetect.Visibility visibility);

    T withFieldVisibility(JsonAutoDetect.Visibility visibility);

    T withGetterVisibility(JsonAutoDetect.Visibility visibility);

    T withIsGetterVisibility(JsonAutoDetect.Visibility visibility);

    T withSetterVisibility(JsonAutoDetect.Visibility visibility);

    @JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.ANY, fieldVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY, getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY, isGetterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY, setterVisibility = JsonAutoDetect.Visibility.ANY)
    public class Std implements VisibilityChecker<Std> {
        protected static final Std DEFAULT = new Std((JsonAutoDetect) Std.class.getAnnotation(JsonAutoDetect.class));
        protected final JsonAutoDetect.Visibility _creatorMinLevel;
        protected final JsonAutoDetect.Visibility _fieldMinLevel;
        protected final JsonAutoDetect.Visibility _getterMinLevel;
        protected final JsonAutoDetect.Visibility _isGetterMinLevel;
        protected final JsonAutoDetect.Visibility _setterMinLevel;

        public static Std defaultInstance() {
            return DEFAULT;
        }

        public Std(JsonAutoDetect jsonAutoDetect) {
            JsonMethod[] jsonMethodArrValue = jsonAutoDetect.value();
            this._getterMinLevel = hasMethod(jsonMethodArrValue, JsonMethod.GETTER) ? jsonAutoDetect.getterVisibility() : JsonAutoDetect.Visibility.NONE;
            this._isGetterMinLevel = hasMethod(jsonMethodArrValue, JsonMethod.IS_GETTER) ? jsonAutoDetect.isGetterVisibility() : JsonAutoDetect.Visibility.NONE;
            this._setterMinLevel = hasMethod(jsonMethodArrValue, JsonMethod.SETTER) ? jsonAutoDetect.setterVisibility() : JsonAutoDetect.Visibility.NONE;
            this._creatorMinLevel = hasMethod(jsonMethodArrValue, JsonMethod.CREATOR) ? jsonAutoDetect.creatorVisibility() : JsonAutoDetect.Visibility.NONE;
            this._fieldMinLevel = hasMethod(jsonMethodArrValue, JsonMethod.FIELD) ? jsonAutoDetect.fieldVisibility() : JsonAutoDetect.Visibility.NONE;
        }

        public Std(JsonAutoDetect.Visibility visibility, JsonAutoDetect.Visibility visibility2, JsonAutoDetect.Visibility visibility3, JsonAutoDetect.Visibility visibility4, JsonAutoDetect.Visibility visibility5) {
            this._getterMinLevel = visibility;
            this._isGetterMinLevel = visibility2;
            this._setterMinLevel = visibility3;
            this._creatorMinLevel = visibility4;
            this._fieldMinLevel = visibility5;
        }

        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public Std with(JsonAutoDetect jsonAutoDetect) {
            if (jsonAutoDetect != null) {
                JsonMethod[] jsonMethodArrValue = jsonAutoDetect.value();
                return withGetterVisibility(hasMethod(jsonMethodArrValue, JsonMethod.GETTER) ? jsonAutoDetect.getterVisibility() : JsonAutoDetect.Visibility.NONE).withIsGetterVisibility(hasMethod(jsonMethodArrValue, JsonMethod.IS_GETTER) ? jsonAutoDetect.isGetterVisibility() : JsonAutoDetect.Visibility.NONE).withSetterVisibility(hasMethod(jsonMethodArrValue, JsonMethod.SETTER) ? jsonAutoDetect.setterVisibility() : JsonAutoDetect.Visibility.NONE).withCreatorVisibility(hasMethod(jsonMethodArrValue, JsonMethod.CREATOR) ? jsonAutoDetect.creatorVisibility() : JsonAutoDetect.Visibility.NONE).withFieldVisibility(hasMethod(jsonMethodArrValue, JsonMethod.FIELD) ? jsonAutoDetect.fieldVisibility() : JsonAutoDetect.Visibility.NONE);
            }
            return this;
        }

        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public Std withGetterVisibility(JsonAutoDetect.Visibility visibility) {
            JsonAutoDetect.Visibility visibility2 = visibility == JsonAutoDetect.Visibility.DEFAULT ? DEFAULT._getterMinLevel : visibility;
            return this._getterMinLevel == visibility2 ? this : new Std(visibility2, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
        }

        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public Std withIsGetterVisibility(JsonAutoDetect.Visibility visibility) {
            JsonAutoDetect.Visibility visibility2 = visibility == JsonAutoDetect.Visibility.DEFAULT ? DEFAULT._isGetterMinLevel : visibility;
            return this._isGetterMinLevel == visibility2 ? this : new Std(this._getterMinLevel, visibility2, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
        }

        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public Std withSetterVisibility(JsonAutoDetect.Visibility visibility) {
            JsonAutoDetect.Visibility visibility2 = visibility == JsonAutoDetect.Visibility.DEFAULT ? DEFAULT._setterMinLevel : visibility;
            return this._setterMinLevel == visibility2 ? this : new Std(this._getterMinLevel, this._isGetterMinLevel, visibility2, this._creatorMinLevel, this._fieldMinLevel);
        }

        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public Std withCreatorVisibility(JsonAutoDetect.Visibility visibility) {
            JsonAutoDetect.Visibility visibility2 = visibility == JsonAutoDetect.Visibility.DEFAULT ? DEFAULT._creatorMinLevel : visibility;
            return this._creatorMinLevel == visibility2 ? this : new Std(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, visibility2, this._fieldMinLevel);
        }

        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public Std withFieldVisibility(JsonAutoDetect.Visibility visibility) {
            JsonAutoDetect.Visibility visibility2 = visibility == JsonAutoDetect.Visibility.DEFAULT ? DEFAULT._fieldMinLevel : visibility;
            return this._fieldMinLevel == visibility2 ? this : new Std(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, visibility2);
        }

        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isCreatorVisible(Member member) {
            return this._creatorMinLevel.isVisible(member);
        }

        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isCreatorVisible(AnnotatedMember annotatedMember) {
            return isCreatorVisible(annotatedMember.getMember());
        }

        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isFieldVisible(Field field) {
            return this._fieldMinLevel.isVisible(field);
        }

        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isFieldVisible(AnnotatedField annotatedField) {
            return isFieldVisible(annotatedField.getAnnotated());
        }

        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isGetterVisible(Method method) {
            return this._getterMinLevel.isVisible(method);
        }

        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isGetterVisible(AnnotatedMethod annotatedMethod) {
            return isGetterVisible(annotatedMethod.getAnnotated());
        }

        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isIsGetterVisible(Method method) {
            return this._isGetterMinLevel.isVisible(method);
        }

        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isIsGetterVisible(AnnotatedMethod annotatedMethod) {
            return isIsGetterVisible(annotatedMethod.getAnnotated());
        }

        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isSetterVisible(Method method) {
            return this._setterMinLevel.isVisible(method);
        }

        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isSetterVisible(AnnotatedMethod annotatedMethod) {
            return isSetterVisible(annotatedMethod.getAnnotated());
        }

        private static boolean hasMethod(JsonMethod[] jsonMethodArr, JsonMethod jsonMethod) {
            for (JsonMethod jsonMethod2 : jsonMethodArr) {
                if (jsonMethod2 == jsonMethod || jsonMethod2 == JsonMethod.ALL) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return "[Visibility: getter: " + this._getterMinLevel + ", isGetter: " + this._isGetterMinLevel + ", setter: " + this._setterMinLevel + ", creator: " + this._creatorMinLevel + ", field: " + this._fieldMinLevel + "]";
        }
    }
}
