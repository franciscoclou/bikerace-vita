package org.codehaus.jackson.map.introspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ClassIntrospector;
import org.codehaus.jackson.map.util.Annotations;
import org.codehaus.jackson.map.util.ArrayBuilders;
import org.codehaus.jackson.map.util.ClassUtil;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class AnnotatedClass extends Annotated {
    private static final AnnotationMap[] NO_ANNOTATION_MAPS = new AnnotationMap[0];
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final Class<?> _class;
    protected AnnotationMap _classAnnotations;
    protected List<AnnotatedConstructor> _constructors;
    protected List<AnnotatedMethod> _creatorMethods;
    protected AnnotatedConstructor _defaultConstructor;
    protected List<AnnotatedField> _fields;
    protected List<AnnotatedField> _ignoredFields;
    protected List<AnnotatedMethod> _ignoredMethods;
    protected AnnotatedMethodMap _memberMethods;
    protected final ClassIntrospector.MixInResolver _mixInResolver;
    protected final Class<?> _primaryMixIn;
    protected final Collection<Class<?>> _superTypes;

    private AnnotatedClass(Class<?> cls, List<Class<?>> list, AnnotationIntrospector annotationIntrospector, ClassIntrospector.MixInResolver mixInResolver) {
        this._class = cls;
        this._superTypes = list;
        this._annotationIntrospector = annotationIntrospector;
        this._mixInResolver = mixInResolver;
        this._primaryMixIn = this._mixInResolver == null ? null : this._mixInResolver.findMixInClassFor(this._class);
    }

    public static AnnotatedClass construct(Class<?> cls, AnnotationIntrospector annotationIntrospector, ClassIntrospector.MixInResolver mixInResolver) {
        AnnotatedClass annotatedClass = new AnnotatedClass(cls, ClassUtil.findSuperTypes(cls, null), annotationIntrospector, mixInResolver);
        annotatedClass.resolveClassAnnotations();
        return annotatedClass;
    }

    public static AnnotatedClass constructWithoutSuperTypes(Class<?> cls, AnnotationIntrospector annotationIntrospector, ClassIntrospector.MixInResolver mixInResolver) {
        AnnotatedClass annotatedClass = new AnnotatedClass(cls, Collections.emptyList(), annotationIntrospector, mixInResolver);
        annotatedClass.resolveClassAnnotations();
        return annotatedClass;
    }

    @Override // org.codehaus.jackson.map.introspect.Annotated
    public Class<?> getAnnotated() {
        return this._class;
    }

    @Override // org.codehaus.jackson.map.introspect.Annotated
    public int getModifiers() {
        return this._class.getModifiers();
    }

    @Override // org.codehaus.jackson.map.introspect.Annotated
    public String getName() {
        return this._class.getName();
    }

    @Override // org.codehaus.jackson.map.introspect.Annotated
    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        if (this._classAnnotations == null) {
            return null;
        }
        return (A) this._classAnnotations.get(cls);
    }

    @Override // org.codehaus.jackson.map.introspect.Annotated
    public Type getGenericType() {
        return this._class;
    }

    @Override // org.codehaus.jackson.map.introspect.Annotated
    public Class<?> getRawType() {
        return this._class;
    }

    public Annotations getAnnotations() {
        return this._classAnnotations;
    }

    public boolean hasAnnotations() {
        return this._classAnnotations.size() > 0;
    }

    public AnnotatedConstructor getDefaultConstructor() {
        return this._defaultConstructor;
    }

    public List<AnnotatedConstructor> getConstructors() {
        return this._constructors == null ? Collections.emptyList() : this._constructors;
    }

    public List<AnnotatedMethod> getStaticMethods() {
        return this._creatorMethods == null ? Collections.emptyList() : this._creatorMethods;
    }

    public Iterable<AnnotatedMethod> memberMethods() {
        return this._memberMethods;
    }

    public Iterable<AnnotatedMethod> ignoredMemberMethods() {
        return this._ignoredMethods == null ? Collections.emptyList() : this._ignoredMethods;
    }

    public int getMemberMethodCount() {
        return this._memberMethods.size();
    }

    public AnnotatedMethod findMethod(String str, Class<?>[] clsArr) {
        return this._memberMethods.find(str, clsArr);
    }

    public int getFieldCount() {
        if (this._fields == null) {
            return 0;
        }
        return this._fields.size();
    }

    public Iterable<AnnotatedField> fields() {
        return this._fields == null ? Collections.emptyList() : this._fields;
    }

    public Iterable<AnnotatedField> ignoredFields() {
        return this._ignoredFields == null ? Collections.emptyList() : this._ignoredFields;
    }

    protected void resolveClassAnnotations() {
        this._classAnnotations = new AnnotationMap();
        if (this._annotationIntrospector != null) {
            if (this._primaryMixIn != null) {
                _addClassMixIns(this._classAnnotations, this._class, this._primaryMixIn);
            }
            for (Annotation annotation : this._class.getDeclaredAnnotations()) {
                if (this._annotationIntrospector.isHandled(annotation)) {
                    this._classAnnotations.addIfNotPresent(annotation);
                }
            }
            for (Class<?> cls : this._superTypes) {
                _addClassMixIns(this._classAnnotations, cls);
                Annotation[] declaredAnnotations = cls.getDeclaredAnnotations();
                for (Annotation annotation2 : declaredAnnotations) {
                    if (this._annotationIntrospector.isHandled(annotation2)) {
                        this._classAnnotations.addIfNotPresent(annotation2);
                    }
                }
            }
            _addClassMixIns(this._classAnnotations, Object.class);
        }
    }

    protected void _addClassMixIns(AnnotationMap annotationMap, Class<?> cls) {
        if (this._mixInResolver != null) {
            _addClassMixIns(annotationMap, cls, this._mixInResolver.findMixInClassFor(cls));
        }
    }

    protected void _addClassMixIns(AnnotationMap annotationMap, Class<?> cls, Class<?> cls2) {
        if (cls2 != null) {
            for (Annotation annotation : cls2.getDeclaredAnnotations()) {
                if (this._annotationIntrospector.isHandled(annotation)) {
                    annotationMap.addIfNotPresent(annotation);
                }
            }
            Iterator<Class<?>> it = ClassUtil.findSuperTypes(cls2, cls).iterator();
            while (it.hasNext()) {
                for (Annotation annotation2 : it.next().getDeclaredAnnotations()) {
                    if (this._annotationIntrospector.isHandled(annotation2)) {
                        annotationMap.addIfNotPresent(annotation2);
                    }
                }
            }
        }
    }

    public void resolveCreators(boolean z) {
        this._constructors = null;
        for (Constructor<?> constructor : this._class.getDeclaredConstructors()) {
            switch (constructor.getParameterTypes().length) {
                case 0:
                    this._defaultConstructor = _constructConstructor(constructor, true);
                    break;
                default:
                    if (z) {
                        if (this._constructors == null) {
                            this._constructors = new ArrayList();
                        }
                        this._constructors.add(_constructConstructor(constructor, false));
                    }
                    break;
            }
        }
        if (this._primaryMixIn != null && (this._defaultConstructor != null || this._constructors != null)) {
            _addConstructorMixIns(this._primaryMixIn);
        }
        if (this._annotationIntrospector != null) {
            if (this._defaultConstructor != null && this._annotationIntrospector.isIgnorableConstructor(this._defaultConstructor)) {
                this._defaultConstructor = null;
            }
            if (this._constructors != null) {
                int size = this._constructors.size();
                while (true) {
                    size--;
                    if (size >= 0) {
                        if (this._annotationIntrospector.isIgnorableConstructor(this._constructors.get(size))) {
                            this._constructors.remove(size);
                        }
                    }
                }
            }
        }
        this._creatorMethods = null;
        if (z) {
            for (Method method : this._class.getDeclaredMethods()) {
                if (Modifier.isStatic(method.getModifiers()) && method.getParameterTypes().length >= 1) {
                    if (this._creatorMethods == null) {
                        this._creatorMethods = new ArrayList();
                    }
                    this._creatorMethods.add(_constructCreatorMethod(method));
                }
            }
            if (this._primaryMixIn != null && this._creatorMethods != null) {
                _addFactoryMixIns(this._primaryMixIn);
            }
            if (this._annotationIntrospector != null && this._creatorMethods != null) {
                int size2 = this._creatorMethods.size();
                while (true) {
                    size2--;
                    if (size2 >= 0) {
                        if (this._annotationIntrospector.isIgnorableMethod(this._creatorMethods.get(size2))) {
                            this._creatorMethods.remove(size2);
                        }
                    } else {
                        return;
                    }
                }
            }
        }
    }

    protected void _addConstructorMixIns(Class<?> cls) {
        MemberKey[] memberKeyArr;
        int size = this._constructors == null ? 0 : this._constructors.size();
        MemberKey[] memberKeyArr2 = null;
        for (Constructor<?> constructor : cls.getDeclaredConstructors()) {
            switch (constructor.getParameterTypes().length) {
                case 0:
                    if (this._defaultConstructor != null) {
                        _addMixOvers(constructor, this._defaultConstructor, false);
                    }
                    break;
                default:
                    if (memberKeyArr2 == null) {
                        memberKeyArr = new MemberKey[size];
                        for (int i = 0; i < size; i++) {
                            memberKeyArr[i] = new MemberKey(this._constructors.get(i).getAnnotated());
                        }
                    } else {
                        memberKeyArr = memberKeyArr2;
                    }
                    MemberKey memberKey = new MemberKey(constructor);
                    int i2 = 0;
                    while (true) {
                        if (i2 >= size) {
                            memberKeyArr2 = memberKeyArr;
                        } else if (!memberKey.equals(memberKeyArr[i2])) {
                            i2++;
                        } else {
                            _addMixOvers(constructor, this._constructors.get(i2), true);
                            memberKeyArr2 = memberKeyArr;
                        }
                        break;
                    }
                    break;
            }
        }
    }

    protected void _addFactoryMixIns(Class<?> cls) {
        MemberKey[] memberKeyArr;
        MemberKey[] memberKeyArr2 = null;
        int size = this._creatorMethods.size();
        for (Method method : cls.getDeclaredMethods()) {
            if (Modifier.isStatic(method.getModifiers()) && method.getParameterTypes().length != 0) {
                if (memberKeyArr2 == null) {
                    memberKeyArr = new MemberKey[size];
                    for (int i = 0; i < size; i++) {
                        memberKeyArr[i] = new MemberKey(this._creatorMethods.get(i).getAnnotated());
                    }
                } else {
                    memberKeyArr = memberKeyArr2;
                }
                MemberKey memberKey = new MemberKey(method);
                int i2 = 0;
                while (true) {
                    if (i2 < size) {
                        if (!memberKey.equals(memberKeyArr[i2])) {
                            i2++;
                        } else {
                            _addMixOvers(method, this._creatorMethods.get(i2), true);
                            memberKeyArr2 = memberKeyArr;
                            break;
                        }
                    } else {
                        memberKeyArr2 = memberKeyArr;
                        break;
                    }
                }
            }
        }
    }

    public void resolveMemberMethods(MethodFilter methodFilter, boolean z) {
        Class<?> clsFindMixInClassFor;
        this._memberMethods = new AnnotatedMethodMap();
        AnnotatedMethodMap annotatedMethodMap = new AnnotatedMethodMap();
        _addMemberMethods(this._class, methodFilter, this._memberMethods, this._primaryMixIn, annotatedMethodMap);
        for (Class<?> cls : this._superTypes) {
            _addMemberMethods(cls, methodFilter, this._memberMethods, this._mixInResolver == null ? null : this._mixInResolver.findMixInClassFor(cls), annotatedMethodMap);
        }
        if (this._mixInResolver != null && (clsFindMixInClassFor = this._mixInResolver.findMixInClassFor(Object.class)) != null) {
            _addMethodMixIns(methodFilter, this._memberMethods, clsFindMixInClassFor, annotatedMethodMap);
        }
        if (this._annotationIntrospector != null) {
            if (!annotatedMethodMap.isEmpty()) {
                for (AnnotatedMethod annotatedMethod : annotatedMethodMap) {
                    try {
                        Method declaredMethod = Object.class.getDeclaredMethod(annotatedMethod.getName(), annotatedMethod.getParameterClasses());
                        if (declaredMethod != null) {
                            AnnotatedMethod annotatedMethod_constructMethod = _constructMethod(declaredMethod);
                            _addMixOvers(annotatedMethod.getAnnotated(), annotatedMethod_constructMethod, false);
                            this._memberMethods.add(annotatedMethod_constructMethod);
                        }
                    } catch (Exception e) {
                    }
                }
            }
            Iterator<AnnotatedMethod> it = this._memberMethods.iterator();
            while (it.hasNext()) {
                AnnotatedMethod next = it.next();
                if (this._annotationIntrospector.isIgnorableMethod(next)) {
                    it.remove();
                    if (z) {
                        this._ignoredMethods = ArrayBuilders.addToList(this._ignoredMethods, next);
                    }
                }
            }
        }
    }

    protected void _addMemberMethods(Class<?> cls, MethodFilter methodFilter, AnnotatedMethodMap annotatedMethodMap, Class<?> cls2, AnnotatedMethodMap annotatedMethodMap2) {
        if (cls2 != null) {
            _addMethodMixIns(methodFilter, annotatedMethodMap, cls2, annotatedMethodMap2);
        }
        if (cls != null) {
            for (Method method : cls.getDeclaredMethods()) {
                if (_isIncludableMethod(method, methodFilter)) {
                    AnnotatedMethod annotatedMethodFind = annotatedMethodMap.find(method);
                    if (annotatedMethodFind == null) {
                        AnnotatedMethod annotatedMethod_constructMethod = _constructMethod(method);
                        annotatedMethodMap.add(annotatedMethod_constructMethod);
                        AnnotatedMethod annotatedMethodRemove = annotatedMethodMap2.remove(method);
                        if (annotatedMethodRemove != null) {
                            _addMixOvers(annotatedMethodRemove.getAnnotated(), annotatedMethod_constructMethod, false);
                        }
                    } else {
                        _addMixUnders(method, annotatedMethodFind);
                        if (annotatedMethodFind.getDeclaringClass().isInterface() && !method.getDeclaringClass().isInterface()) {
                            annotatedMethodMap.add(annotatedMethodFind.withMethod(method));
                        }
                    }
                }
            }
        }
    }

    protected void _addMethodMixIns(MethodFilter methodFilter, AnnotatedMethodMap annotatedMethodMap, Class<?> cls, AnnotatedMethodMap annotatedMethodMap2) {
        for (Method method : cls.getDeclaredMethods()) {
            if (_isIncludableMethod(method, methodFilter)) {
                AnnotatedMethod annotatedMethodFind = annotatedMethodMap.find(method);
                if (annotatedMethodFind != null) {
                    _addMixUnders(method, annotatedMethodFind);
                } else {
                    annotatedMethodMap2.add(_constructMethod(method));
                }
            }
        }
    }

    public void resolveFields(boolean z) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        _addFields(linkedHashMap, this._class);
        if (this._annotationIntrospector != null) {
            Iterator it = linkedHashMap.entrySet().iterator();
            while (it.hasNext()) {
                AnnotatedField annotatedField = (AnnotatedField) ((Map.Entry) it.next()).getValue();
                if (this._annotationIntrospector.isIgnorableField(annotatedField)) {
                    it.remove();
                    if (z) {
                        this._ignoredFields = ArrayBuilders.addToList(this._ignoredFields, annotatedField);
                    }
                }
            }
        }
        if (linkedHashMap.isEmpty()) {
            this._fields = Collections.emptyList();
        } else {
            this._fields = new ArrayList(linkedHashMap.size());
            this._fields.addAll(linkedHashMap.values());
        }
    }

    protected void _addFields(Map<String, AnnotatedField> map, Class<?> cls) {
        Class<?> clsFindMixInClassFor;
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass != null) {
            _addFields(map, superclass);
            for (Field field : cls.getDeclaredFields()) {
                if (_isIncludableField(field)) {
                    map.put(field.getName(), _constructField(field));
                }
            }
            if (this._mixInResolver != null && (clsFindMixInClassFor = this._mixInResolver.findMixInClassFor(cls)) != null) {
                _addFieldMixIns(clsFindMixInClassFor, map);
            }
        }
    }

    protected void _addFieldMixIns(Class<?> cls, Map<String, AnnotatedField> map) {
        AnnotatedField annotatedField;
        for (Field field : cls.getDeclaredFields()) {
            if (_isIncludableField(field) && (annotatedField = map.get(field.getName())) != null) {
                Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
                for (Annotation annotation : declaredAnnotations) {
                    if (this._annotationIntrospector.isHandled(annotation)) {
                        annotatedField.addOrOverride(annotation);
                    }
                }
            }
        }
    }

    protected AnnotatedMethod _constructMethod(Method method) {
        return this._annotationIntrospector == null ? new AnnotatedMethod(method, _emptyAnnotationMap(), null) : new AnnotatedMethod(method, _collectRelevantAnnotations(method.getDeclaredAnnotations()), null);
    }

    protected AnnotatedConstructor _constructConstructor(Constructor<?> constructor, boolean z) {
        if (this._annotationIntrospector == null) {
            return new AnnotatedConstructor(constructor, _emptyAnnotationMap(), _emptyAnnotationMaps(constructor.getParameterTypes().length));
        }
        return new AnnotatedConstructor(constructor, _collectRelevantAnnotations(constructor.getDeclaredAnnotations()), z ? null : _collectRelevantAnnotations(constructor.getParameterAnnotations()));
    }

    protected AnnotatedMethod _constructCreatorMethod(Method method) {
        return this._annotationIntrospector == null ? new AnnotatedMethod(method, _emptyAnnotationMap(), _emptyAnnotationMaps(method.getParameterTypes().length)) : new AnnotatedMethod(method, _collectRelevantAnnotations(method.getDeclaredAnnotations()), _collectRelevantAnnotations(method.getParameterAnnotations()));
    }

    protected AnnotatedField _constructField(Field field) {
        return this._annotationIntrospector == null ? new AnnotatedField(field, _emptyAnnotationMap()) : new AnnotatedField(field, _collectRelevantAnnotations(field.getDeclaredAnnotations()));
    }

    protected AnnotationMap[] _collectRelevantAnnotations(Annotation[][] annotationArr) {
        int length = annotationArr.length;
        AnnotationMap[] annotationMapArr = new AnnotationMap[length];
        for (int i = 0; i < length; i++) {
            annotationMapArr[i] = _collectRelevantAnnotations(annotationArr[i]);
        }
        return annotationMapArr;
    }

    protected AnnotationMap _collectRelevantAnnotations(Annotation[] annotationArr) {
        AnnotationMap annotationMap = new AnnotationMap();
        if (annotationArr != null) {
            for (Annotation annotation : annotationArr) {
                if (this._annotationIntrospector.isHandled(annotation)) {
                    annotationMap.add(annotation);
                }
            }
        }
        return annotationMap;
    }

    private AnnotationMap _emptyAnnotationMap() {
        return new AnnotationMap();
    }

    private AnnotationMap[] _emptyAnnotationMaps(int i) {
        if (i == 0) {
            return NO_ANNOTATION_MAPS;
        }
        AnnotationMap[] annotationMapArr = new AnnotationMap[i];
        for (int i2 = 0; i2 < i; i2++) {
            annotationMapArr[i2] = _emptyAnnotationMap();
        }
        return annotationMapArr;
    }

    protected boolean _isIncludableMethod(Method method, MethodFilter methodFilter) {
        return ((methodFilter != null && !methodFilter.includeMethod(method)) || method.isSynthetic() || method.isBridge()) ? false : true;
    }

    private boolean _isIncludableField(Field field) {
        if (field.isSynthetic()) {
            return false;
        }
        int modifiers = field.getModifiers();
        return (Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers)) ? false : true;
    }

    protected void _addMixOvers(Constructor<?> constructor, AnnotatedConstructor annotatedConstructor, boolean z) {
        for (Annotation annotation : constructor.getDeclaredAnnotations()) {
            if (this._annotationIntrospector.isHandled(annotation)) {
                annotatedConstructor.addOrOverride(annotation);
            }
        }
        if (z) {
            Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
            int length = parameterAnnotations.length;
            for (int i = 0; i < length; i++) {
                for (Annotation annotation2 : parameterAnnotations[i]) {
                    annotatedConstructor.addOrOverrideParam(i, annotation2);
                }
            }
        }
    }

    protected void _addMixOvers(Method method, AnnotatedMethod annotatedMethod, boolean z) {
        for (Annotation annotation : method.getDeclaredAnnotations()) {
            if (this._annotationIntrospector.isHandled(annotation)) {
                annotatedMethod.addOrOverride(annotation);
            }
        }
        if (z) {
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            int length = parameterAnnotations.length;
            for (int i = 0; i < length; i++) {
                for (Annotation annotation2 : parameterAnnotations[i]) {
                    annotatedMethod.addOrOverrideParam(i, annotation2);
                }
            }
        }
    }

    protected void _addMixUnders(Method method, AnnotatedMethod annotatedMethod) {
        for (Annotation annotation : method.getDeclaredAnnotations()) {
            if (this._annotationIntrospector.isHandled(annotation)) {
                annotatedMethod.addIfNotPresent(annotation);
            }
        }
    }

    public String toString() {
        return "[AnnotedClass " + this._class.getName() + "]";
    }
}
