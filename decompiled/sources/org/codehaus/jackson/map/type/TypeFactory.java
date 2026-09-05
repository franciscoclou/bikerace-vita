package org.codehaus.jackson.map.type;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.map.util.ArrayBuilders;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class TypeFactory {
    protected final TypeModifier[] _modifiers;
    protected final TypeParser _parser;

    @Deprecated
    public static final TypeFactory instance = new TypeFactory();
    private static final JavaType[] NO_TYPES = new JavaType[0];

    private TypeFactory() {
        this._parser = new TypeParser(this);
        this._modifiers = null;
    }

    protected TypeFactory(TypeParser typeParser, TypeModifier[] typeModifierArr) {
        this._parser = typeParser;
        this._modifiers = typeModifierArr;
    }

    public TypeFactory withModifier(TypeModifier typeModifier) {
        return this._modifiers == null ? new TypeFactory(this._parser, new TypeModifier[]{typeModifier}) : new TypeFactory(this._parser, (TypeModifier[]) ArrayBuilders.insertInListNoDup(this._modifiers, typeModifier));
    }

    public static TypeFactory defaultInstance() {
        return instance;
    }

    public static JavaType unknownType() {
        return defaultInstance()._unknownType();
    }

    public static Class<?> rawClass(Type type) {
        return type instanceof Class ? (Class) type : defaultInstance().constructType(type).getRawClass();
    }

    @Deprecated
    public static JavaType type(Type type) {
        return instance._constructType(type, null);
    }

    @Deprecated
    public static JavaType type(Type type, Class<?> cls) {
        return instance.constructType(type, cls);
    }

    @Deprecated
    public static JavaType type(Type type, JavaType javaType) {
        return instance.constructType(type, javaType);
    }

    @Deprecated
    public static JavaType type(Type type, TypeBindings typeBindings) {
        return instance._constructType(type, typeBindings);
    }

    @Deprecated
    public static JavaType type(TypeReference<?> typeReference) {
        return instance.constructType(typeReference.getType());
    }

    @Deprecated
    public static JavaType arrayType(Class<?> cls) {
        return instance.constructArrayType(instance.constructType(cls));
    }

    @Deprecated
    public static JavaType arrayType(JavaType javaType) {
        return instance.constructArrayType(javaType);
    }

    @Deprecated
    public static JavaType collectionType(Class<? extends Collection> cls, Class<?> cls2) {
        return instance.constructCollectionType(cls, instance.constructType(cls2));
    }

    @Deprecated
    public static JavaType collectionType(Class<? extends Collection> cls, JavaType javaType) {
        return instance.constructCollectionType(cls, javaType);
    }

    @Deprecated
    public static JavaType mapType(Class<? extends Map> cls, Class<?> cls2, Class<?> cls3) {
        return instance.constructMapType(cls, type(cls2), instance.constructType(cls3));
    }

    @Deprecated
    public static JavaType mapType(Class<? extends Map> cls, JavaType javaType, JavaType javaType2) {
        return instance.constructMapType(cls, javaType, javaType2);
    }

    @Deprecated
    public static JavaType parametricType(Class<?> cls, Class<?>... clsArr) {
        return instance.constructParametricType(cls, clsArr);
    }

    @Deprecated
    public static JavaType parametricType(Class<?> cls, JavaType... javaTypeArr) {
        return instance.constructParametricType(cls, javaTypeArr);
    }

    public static JavaType fromCanonical(String str) {
        return instance.constructFromCanonical(str);
    }

    @Deprecated
    public static JavaType specialize(JavaType javaType, Class<?> cls) {
        return instance.constructSpecializedType(javaType, cls);
    }

    @Deprecated
    public static JavaType fastSimpleType(Class<?> cls) {
        return instance.uncheckedSimpleType(cls);
    }

    @Deprecated
    public static JavaType[] findParameterTypes(Class<?> cls, Class<?> cls2) {
        return instance.findTypeParameters(cls, cls2);
    }

    @Deprecated
    public static JavaType[] findParameterTypes(Class<?> cls, Class<?> cls2, TypeBindings typeBindings) {
        return instance.findTypeParameters(cls, cls2, typeBindings);
    }

    @Deprecated
    public static JavaType[] findParameterTypes(JavaType javaType, Class<?> cls) {
        return instance.findTypeParameters(javaType, cls);
    }

    @Deprecated
    public static JavaType fromClass(Class<?> cls) {
        return instance._fromClass(cls, null);
    }

    @Deprecated
    public static JavaType fromTypeReference(TypeReference<?> typeReference) {
        return type(typeReference.getType());
    }

    @Deprecated
    public static JavaType fromType(Type type) {
        return instance._constructType(type, null);
    }

    public JavaType constructSpecializedType(JavaType javaType, Class<?> cls) {
        if (!(javaType instanceof SimpleType) || (!cls.isArray() && !Map.class.isAssignableFrom(cls) && !Collection.class.isAssignableFrom(cls))) {
            return javaType.narrowBy(cls);
        }
        if (!javaType.getRawClass().isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Class " + cls.getClass().getName() + " not subtype of " + javaType);
        }
        JavaType javaType_fromClass = instance._fromClass(cls, new TypeBindings(this, javaType.getRawClass()));
        Object valueHandler = javaType.getValueHandler();
        if (valueHandler != null) {
            javaType_fromClass.setValueHandler(valueHandler);
        }
        Object typeHandler = javaType.getTypeHandler();
        if (typeHandler != null) {
            return javaType_fromClass.withTypeHandler(typeHandler);
        }
        return javaType_fromClass;
    }

    public JavaType constructFromCanonical(String str) {
        return this._parser.parse(str);
    }

    public JavaType[] findTypeParameters(JavaType javaType, Class<?> cls) {
        Class<?> rawClass = javaType.getRawClass();
        if (rawClass == cls) {
            int iContainedTypeCount = javaType.containedTypeCount();
            if (iContainedTypeCount == 0) {
                return null;
            }
            JavaType[] javaTypeArr = new JavaType[iContainedTypeCount];
            for (int i = 0; i < iContainedTypeCount; i++) {
                javaTypeArr[i] = javaType.containedType(i);
            }
            return javaTypeArr;
        }
        return findTypeParameters(rawClass, cls, new TypeBindings(this, javaType));
    }

    public JavaType[] findTypeParameters(Class<?> cls, Class<?> cls2) {
        return findTypeParameters(cls, cls2, new TypeBindings(this, cls));
    }

    public JavaType[] findTypeParameters(Class<?> cls, Class<?> cls2, TypeBindings typeBindings) {
        HierarchicType hierarchicType_findSuperTypeChain = _findSuperTypeChain(cls, cls2);
        if (hierarchicType_findSuperTypeChain == null) {
            throw new IllegalArgumentException("Class " + cls.getName() + " is not a subtype of " + cls2.getName());
        }
        while (hierarchicType_findSuperTypeChain.getSuperType() != null) {
            hierarchicType_findSuperTypeChain = hierarchicType_findSuperTypeChain.getSuperType();
            Class<?> rawClass = hierarchicType_findSuperTypeChain.getRawClass();
            TypeBindings typeBindings2 = new TypeBindings(this, rawClass);
            if (hierarchicType_findSuperTypeChain.isGeneric()) {
                Type[] actualTypeArguments = hierarchicType_findSuperTypeChain.asGeneric().getActualTypeArguments();
                TypeVariable<Class<?>>[] typeParameters = rawClass.getTypeParameters();
                int length = actualTypeArguments.length;
                for (int i = 0; i < length; i++) {
                    typeBindings2.addBinding(typeParameters[i].getName(), instance._constructType(actualTypeArguments[i], typeBindings));
                }
            }
            typeBindings = typeBindings2;
        }
        if (hierarchicType_findSuperTypeChain.isGeneric()) {
            return typeBindings.typesAsArray();
        }
        return null;
    }

    public JavaType constructType(Type type) {
        return _constructType(type, null);
    }

    public JavaType constructType(Type type, TypeBindings typeBindings) {
        return _constructType(type, typeBindings);
    }

    public JavaType constructType(TypeReference<?> typeReference) {
        return _constructType(typeReference.getType(), null);
    }

    public JavaType constructType(Type type, Class<?> cls) {
        return _constructType(type, new TypeBindings(this, cls));
    }

    public JavaType constructType(Type type, JavaType javaType) {
        return _constructType(type, new TypeBindings(this, javaType));
    }

    public JavaType _constructType(Type type, TypeBindings typeBindings) {
        JavaType javaType_fromWildcard;
        if (type instanceof Class) {
            Class<?> cls = (Class) type;
            if (typeBindings == null) {
                typeBindings = new TypeBindings(this, cls);
            }
            javaType_fromWildcard = _fromClass(cls, typeBindings);
        } else if (type instanceof ParameterizedType) {
            javaType_fromWildcard = _fromParamType((ParameterizedType) type, typeBindings);
        } else if (type instanceof GenericArrayType) {
            javaType_fromWildcard = _fromArrayType((GenericArrayType) type, typeBindings);
        } else if (type instanceof TypeVariable) {
            javaType_fromWildcard = _fromVariable((TypeVariable) type, typeBindings);
        } else if (type instanceof WildcardType) {
            javaType_fromWildcard = _fromWildcard((WildcardType) type, typeBindings);
        } else {
            throw new IllegalArgumentException("Unrecognized Type: " + type.toString());
        }
        if (this._modifiers != null && !javaType_fromWildcard.isContainerType()) {
            TypeModifier[] typeModifierArr = this._modifiers;
            int length = typeModifierArr.length;
            int i = 0;
            while (i < length) {
                JavaType javaTypeModifyType = typeModifierArr[i].modifyType(javaType_fromWildcard, type, typeBindings, this);
                i++;
                javaType_fromWildcard = javaTypeModifyType;
            }
        }
        return javaType_fromWildcard;
    }

    protected ArrayType constructArrayType(Class<?> cls) {
        return ArrayType.construct(_constructType(cls, null));
    }

    protected ArrayType constructArrayType(JavaType javaType) {
        return ArrayType.construct(javaType);
    }

    public CollectionType constructCollectionType(Class<? extends Collection> cls, Class<?> cls2) {
        return CollectionType.construct((Class<?>) cls, constructType(cls2));
    }

    public CollectionType constructCollectionType(Class<? extends Collection> cls, JavaType javaType) {
        return CollectionType.construct((Class<?>) cls, javaType);
    }

    public CollectionLikeType constructCollectionLikeType(Class<?> cls, Class<?> cls2) {
        return CollectionLikeType.construct(cls, constructType(cls2));
    }

    public CollectionLikeType constructCollectionLikeType(Class<?> cls, JavaType javaType) {
        return CollectionLikeType.construct(cls, javaType);
    }

    public MapType constructMapType(Class<? extends Map> cls, JavaType javaType, JavaType javaType2) {
        return MapType.construct((Class<?>) cls, javaType, javaType2);
    }

    public MapType constructMapType(Class<? extends Map> cls, Class<?> cls2, Class<?> cls3) {
        return MapType.construct((Class<?>) cls, constructType(cls2), constructType(cls3));
    }

    public MapLikeType constructMapLikeType(Class<?> cls, JavaType javaType, JavaType javaType2) {
        return MapLikeType.construct(cls, javaType, javaType2);
    }

    public MapLikeType constructMapLikeType(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        return MapType.construct(cls, constructType(cls2), constructType(cls3));
    }

    public JavaType constructSimpleType(Class<?> cls, JavaType[] javaTypeArr) {
        TypeVariable<Class<?>>[] typeParameters = cls.getTypeParameters();
        if (typeParameters.length != javaTypeArr.length) {
            throw new IllegalArgumentException("Parameter type mismatch for " + cls.getName() + ": expected " + typeParameters.length + " parameters, was given " + javaTypeArr.length);
        }
        String[] strArr = new String[typeParameters.length];
        int length = typeParameters.length;
        for (int i = 0; i < length; i++) {
            strArr[i] = typeParameters[i].getName();
        }
        return new SimpleType(cls, strArr, javaTypeArr);
    }

    public JavaType uncheckedSimpleType(Class<?> cls) {
        return new SimpleType(cls, null, null);
    }

    public JavaType constructParametricType(Class<?> cls, Class<?>... clsArr) {
        int length = clsArr.length;
        JavaType[] javaTypeArr = new JavaType[length];
        for (int i = 0; i < length; i++) {
            javaTypeArr[i] = _fromClass(clsArr[i], null);
        }
        return constructParametricType(cls, javaTypeArr);
    }

    public JavaType constructParametricType(Class<?> cls, JavaType... javaTypeArr) {
        if (cls.isArray()) {
            if (javaTypeArr.length != 1) {
                throw new IllegalArgumentException("Need exactly 1 parameter type for arrays (" + cls.getName() + ")");
            }
            return constructArrayType(javaTypeArr[0]);
        }
        if (Map.class.isAssignableFrom(cls)) {
            if (javaTypeArr.length != 2) {
                throw new IllegalArgumentException("Need exactly 2 parameter types for Map types (" + cls.getName() + ")");
            }
            return constructMapType((Class<? extends Map>) cls, javaTypeArr[0], javaTypeArr[1]);
        }
        if (Collection.class.isAssignableFrom(cls)) {
            if (javaTypeArr.length != 1) {
                throw new IllegalArgumentException("Need exactly 1 parameter type for Collection types (" + cls.getName() + ")");
            }
            return constructCollectionType((Class<? extends Collection>) cls, javaTypeArr[0]);
        }
        return constructSimpleType(cls, javaTypeArr);
    }

    protected JavaType _fromClass(Class<?> cls, TypeBindings typeBindings) {
        if (cls.isArray()) {
            return ArrayType.construct(_constructType(cls.getComponentType(), null));
        }
        if (cls.isEnum()) {
            return new SimpleType(cls);
        }
        if (Map.class.isAssignableFrom(cls)) {
            return _mapType(cls);
        }
        if (Collection.class.isAssignableFrom(cls)) {
            return _collectionType(cls);
        }
        return new SimpleType(cls);
    }

    protected JavaType _fromParameterizedClass(Class<?> cls, List<JavaType> list) {
        if (cls.isArray()) {
            return ArrayType.construct(_constructType(cls.getComponentType(), null));
        }
        if (cls.isEnum()) {
            return new SimpleType(cls);
        }
        if (Map.class.isAssignableFrom(cls)) {
            if (list.size() > 0) {
                return MapType.construct(cls, list.get(0), list.size() >= 2 ? list.get(1) : _unknownType());
            }
            return _mapType(cls);
        }
        if (Collection.class.isAssignableFrom(cls)) {
            if (list.size() >= 1) {
                return CollectionType.construct(cls, list.get(0));
            }
            return _collectionType(cls);
        }
        if (list.size() == 0) {
            return new SimpleType(cls);
        }
        return constructSimpleType(cls, (JavaType[]) list.toArray(new JavaType[list.size()]));
    }

    protected JavaType _fromParamType(ParameterizedType parameterizedType, TypeBindings typeBindings) {
        JavaType[] javaTypeArr;
        Class<?> cls = (Class) parameterizedType.getRawType();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        int length = actualTypeArguments == null ? 0 : actualTypeArguments.length;
        if (length == 0) {
            javaTypeArr = NO_TYPES;
        } else {
            javaTypeArr = new JavaType[length];
            for (int i = 0; i < length; i++) {
                javaTypeArr[i] = _constructType(actualTypeArguments[i], typeBindings);
            }
        }
        if (Map.class.isAssignableFrom(cls)) {
            JavaType[] javaTypeArrFindTypeParameters = findTypeParameters(constructSimpleType(cls, javaTypeArr), Map.class);
            if (javaTypeArrFindTypeParameters.length != 2) {
                throw new IllegalArgumentException("Could not find 2 type parameters for Map class " + cls.getName() + " (found " + javaTypeArrFindTypeParameters.length + ")");
            }
            return MapType.construct(cls, javaTypeArrFindTypeParameters[0], javaTypeArrFindTypeParameters[1]);
        }
        if (Collection.class.isAssignableFrom(cls)) {
            JavaType[] javaTypeArrFindTypeParameters2 = findTypeParameters(constructSimpleType(cls, javaTypeArr), Collection.class);
            if (javaTypeArrFindTypeParameters2.length != 1) {
                throw new IllegalArgumentException("Could not find 1 type parameter for Collection class " + cls.getName() + " (found " + javaTypeArrFindTypeParameters2.length + ")");
            }
            return CollectionType.construct(cls, javaTypeArrFindTypeParameters2[0]);
        }
        if (length == 0) {
            return new SimpleType(cls);
        }
        return constructSimpleType(cls, javaTypeArr);
    }

    protected JavaType _fromArrayType(GenericArrayType genericArrayType, TypeBindings typeBindings) {
        return ArrayType.construct(_constructType(genericArrayType.getGenericComponentType(), typeBindings));
    }

    protected JavaType _fromVariable(TypeVariable<?> typeVariable, TypeBindings typeBindings) {
        if (typeBindings == null) {
            return _unknownType();
        }
        String name = typeVariable.getName();
        JavaType javaTypeFindType = typeBindings.findType(name);
        if (javaTypeFindType == null) {
            Type[] bounds = typeVariable.getBounds();
            typeBindings._addPlaceholder(name);
            return _constructType(bounds[0], typeBindings);
        }
        return javaTypeFindType;
    }

    protected JavaType _fromWildcard(WildcardType wildcardType, TypeBindings typeBindings) {
        return _constructType(wildcardType.getUpperBounds()[0], typeBindings);
    }

    private JavaType _mapType(Class<?> cls) {
        JavaType[] javaTypeArrFindTypeParameters = findTypeParameters(cls, Map.class);
        if (javaTypeArrFindTypeParameters == null) {
            return MapType.construct(cls, _unknownType(), _unknownType());
        }
        if (javaTypeArrFindTypeParameters.length != 2) {
            throw new IllegalArgumentException("Strange Map type " + cls.getName() + ": can not determine type parameters");
        }
        return MapType.construct(cls, javaTypeArrFindTypeParameters[0], javaTypeArrFindTypeParameters[1]);
    }

    private JavaType _collectionType(Class<?> cls) {
        JavaType[] javaTypeArrFindTypeParameters = findTypeParameters(cls, Collection.class);
        if (javaTypeArrFindTypeParameters == null) {
            return CollectionType.construct(cls, _unknownType());
        }
        if (javaTypeArrFindTypeParameters.length != 1) {
            throw new IllegalArgumentException("Strange Collection type " + cls.getName() + ": can not determine type parameters");
        }
        return CollectionType.construct(cls, javaTypeArrFindTypeParameters[0]);
    }

    protected JavaType _resolveVariableViaSubTypes(HierarchicType hierarchicType, String str, TypeBindings typeBindings) {
        if (hierarchicType != null && hierarchicType.isGeneric()) {
            TypeVariable<Class<?>>[] typeParameters = hierarchicType.getRawClass().getTypeParameters();
            int length = typeParameters.length;
            for (int i = 0; i < length; i++) {
                if (str.equals(typeParameters[i].getName())) {
                    Type type = hierarchicType.asGeneric().getActualTypeArguments()[i];
                    if (type instanceof TypeVariable) {
                        return _resolveVariableViaSubTypes(hierarchicType.getSubType(), ((TypeVariable) type).getName(), typeBindings);
                    }
                    return _constructType(type, typeBindings);
                }
            }
        }
        return _unknownType();
    }

    protected JavaType _unknownType() {
        return new SimpleType(Object.class, null, null);
    }

    protected static HierarchicType _findSuperTypeChain(Class<?> cls, Class<?> cls2) {
        return cls2.isInterface() ? _findSuperInterfaceChain(cls, cls2) : _findSuperClassChain(cls, cls2);
    }

    protected static HierarchicType _findSuperClassChain(Type type, Class<?> cls) {
        HierarchicType hierarchicType_findSuperClassChain;
        HierarchicType hierarchicType = new HierarchicType(type);
        Class<?> rawClass = hierarchicType.getRawClass();
        if (rawClass != cls) {
            Type genericSuperclass = rawClass.getGenericSuperclass();
            if (genericSuperclass != null && (hierarchicType_findSuperClassChain = _findSuperClassChain(genericSuperclass, cls)) != null) {
                hierarchicType_findSuperClassChain.setSubType(hierarchicType);
                hierarchicType.setSuperType(hierarchicType_findSuperClassChain);
                return hierarchicType;
            }
            return null;
        }
        return hierarchicType;
    }

    protected static HierarchicType _findSuperInterfaceChain(Type type, Class<?> cls) {
        HierarchicType hierarchicType_findSuperInterfaceChain;
        HierarchicType hierarchicType = new HierarchicType(type);
        Class<?> rawClass = hierarchicType.getRawClass();
        if (rawClass != cls) {
            Type[] genericInterfaces = rawClass.getGenericInterfaces();
            if (genericInterfaces != null) {
                for (Type type2 : genericInterfaces) {
                    HierarchicType hierarchicType_findSuperInterfaceChain2 = _findSuperInterfaceChain(type2, cls);
                    if (hierarchicType_findSuperInterfaceChain2 != null) {
                        hierarchicType_findSuperInterfaceChain2.setSubType(hierarchicType);
                        hierarchicType.setSuperType(hierarchicType_findSuperInterfaceChain2);
                        return hierarchicType;
                    }
                }
            }
            Type genericSuperclass = rawClass.getGenericSuperclass();
            if (genericSuperclass != null && (hierarchicType_findSuperInterfaceChain = _findSuperInterfaceChain(genericSuperclass, cls)) != null) {
                hierarchicType_findSuperInterfaceChain.setSubType(hierarchicType);
                hierarchicType.setSuperType(hierarchicType_findSuperInterfaceChain);
                return hierarchicType;
            }
            return null;
        }
        return hierarchicType;
    }
}
