package org.codehaus.jackson.map.deser;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.util.ClassUtil;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class CreatorContainer {
    final BasicBeanDescription _beanDesc;
    final boolean _canFixAccess;
    protected Constructor<?> _defaultConstructor;
    AnnotatedConstructor _delegatingConstructor;
    AnnotatedMethod _delegatingFactory;
    AnnotatedConstructor _intConstructor;
    AnnotatedMethod _intFactory;
    AnnotatedConstructor _longConstructor;
    AnnotatedMethod _longFactory;
    AnnotatedConstructor _propertyBasedConstructor;
    AnnotatedMethod _propertyBasedFactory;
    AnnotatedConstructor _strConstructor;
    AnnotatedMethod _strFactory;
    SettableBeanProperty[] _propertyBasedFactoryProperties = null;
    SettableBeanProperty[] _propertyBasedConstructorProperties = null;

    public CreatorContainer(BasicBeanDescription basicBeanDescription, boolean z) {
        this._beanDesc = basicBeanDescription;
        this._canFixAccess = z;
    }

    public void setDefaultConstructor(Constructor<?> constructor) {
        this._defaultConstructor = constructor;
    }

    public void addStringConstructor(AnnotatedConstructor annotatedConstructor) {
        this._strConstructor = verifyNonDup(annotatedConstructor, this._strConstructor, "String");
    }

    public void addIntConstructor(AnnotatedConstructor annotatedConstructor) {
        this._intConstructor = verifyNonDup(annotatedConstructor, this._intConstructor, "int");
    }

    public void addLongConstructor(AnnotatedConstructor annotatedConstructor) {
        this._longConstructor = verifyNonDup(annotatedConstructor, this._longConstructor, "long");
    }

    public void addDelegatingConstructor(AnnotatedConstructor annotatedConstructor) {
        this._delegatingConstructor = verifyNonDup(annotatedConstructor, this._delegatingConstructor, "long");
    }

    public void addPropertyConstructor(AnnotatedConstructor annotatedConstructor, SettableBeanProperty[] settableBeanPropertyArr) {
        this._propertyBasedConstructor = verifyNonDup(annotatedConstructor, this._propertyBasedConstructor, "property-based");
        if (settableBeanPropertyArr.length > 1) {
            HashMap map = new HashMap();
            int length = settableBeanPropertyArr.length;
            for (int i = 0; i < length; i++) {
                String name = settableBeanPropertyArr[i].getName();
                Integer num = (Integer) map.put(name, Integer.valueOf(i));
                if (num != null) {
                    throw new IllegalArgumentException("Duplicate creator property \"" + name + "\" (index " + num + " vs " + i + ")");
                }
            }
        }
        this._propertyBasedConstructorProperties = settableBeanPropertyArr;
    }

    public void addStringFactory(AnnotatedMethod annotatedMethod) {
        this._strFactory = verifyNonDup(annotatedMethod, this._strFactory, "String");
    }

    public void addIntFactory(AnnotatedMethod annotatedMethod) {
        this._intFactory = verifyNonDup(annotatedMethod, this._intFactory, "int");
    }

    public void addLongFactory(AnnotatedMethod annotatedMethod) {
        this._longFactory = verifyNonDup(annotatedMethod, this._longFactory, "long");
    }

    public void addDelegatingFactory(AnnotatedMethod annotatedMethod) {
        this._delegatingFactory = verifyNonDup(annotatedMethod, this._delegatingFactory, "long");
    }

    public void addPropertyFactory(AnnotatedMethod annotatedMethod, SettableBeanProperty[] settableBeanPropertyArr) {
        this._propertyBasedFactory = verifyNonDup(annotatedMethod, this._propertyBasedFactory, "property-based");
        this._propertyBasedFactoryProperties = settableBeanPropertyArr;
    }

    public Constructor<?> getDefaultConstructor() {
        return this._defaultConstructor;
    }

    public Creator.StringBased stringCreator() {
        if (this._strConstructor == null && this._strFactory == null) {
            return null;
        }
        return new Creator.StringBased(this._beanDesc.getBeanClass(), this._strConstructor, this._strFactory);
    }

    public Creator.NumberBased numberCreator() {
        if (this._intConstructor == null && this._intFactory == null && this._longConstructor == null && this._longFactory == null) {
            return null;
        }
        return new Creator.NumberBased(this._beanDesc.getBeanClass(), this._intConstructor, this._intFactory, this._longConstructor, this._longFactory);
    }

    public Creator.Delegating delegatingCreator() {
        if (this._delegatingConstructor == null && this._delegatingFactory == null) {
            return null;
        }
        return new Creator.Delegating(this._beanDesc, this._delegatingConstructor, this._delegatingFactory);
    }

    public Creator.PropertyBased propertyBasedCreator() {
        if (this._propertyBasedConstructor == null && this._propertyBasedFactory == null) {
            return null;
        }
        return new Creator.PropertyBased(this._propertyBasedConstructor, this._propertyBasedConstructorProperties, this._propertyBasedFactory, this._propertyBasedFactoryProperties);
    }

    protected AnnotatedConstructor verifyNonDup(AnnotatedConstructor annotatedConstructor, AnnotatedConstructor annotatedConstructor2, String str) {
        if (annotatedConstructor2 != null) {
            throw new IllegalArgumentException("Conflicting " + str + " constructors: already had " + annotatedConstructor2 + ", encountered " + annotatedConstructor);
        }
        if (this._canFixAccess) {
            ClassUtil.checkAndFixAccess(annotatedConstructor.getAnnotated());
        }
        return annotatedConstructor;
    }

    protected AnnotatedMethod verifyNonDup(AnnotatedMethod annotatedMethod, AnnotatedMethod annotatedMethod2, String str) {
        if (annotatedMethod2 != null) {
            throw new IllegalArgumentException("Conflicting " + str + " factory methods: already had " + annotatedMethod2 + ", encountered " + annotatedMethod);
        }
        if (this._canFixAccess) {
            ClassUtil.checkAndFixAccess(annotatedMethod.getAnnotated());
        }
        return annotatedMethod;
    }
}
