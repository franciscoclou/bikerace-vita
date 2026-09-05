package com.amazonaws.e;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class a<T> implements t<com.amazonaws.b, T> {
    protected final Class<? extends com.amazonaws.b> exceptionClass;

    public a() {
        this(com.amazonaws.b.class);
    }

    public a(Class<? extends com.amazonaws.b> cls) {
        this.exceptionClass = cls;
    }

    protected com.amazonaws.b newException(String str) {
        return this.exceptionClass.getConstructor(String.class).newInstance(str);
    }
}
