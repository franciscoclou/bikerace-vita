package com.amazonaws.javax.xml.transform;

/* JADX INFO: compiled from: TransformerFactory.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class l {
    public abstract f getAssociatedStylesheet(f fVar, String str, String str2, String str3);

    public abstract Object getAttribute(String str);

    public abstract a getErrorListener();

    public abstract boolean getFeature(String str);

    public abstract n getURIResolver();

    public abstract h newTemplates(f fVar);

    public abstract i newTransformer();

    public abstract i newTransformer(f fVar);

    public abstract void setAttribute(String str, Object obj);

    public abstract void setErrorListener(a aVar);

    public abstract void setFeature(String str, boolean z);

    public abstract void setURIResolver(n nVar);

    protected l() {
    }

    public static l newInstance() {
        try {
            return (l) b.a("com.amazonaws.javax.xml.transform.TransformerFactory", "com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl");
        } catch (c e) {
            throw new m(e.a(), e.getMessage());
        }
    }

    public static l newInstance(String str, ClassLoader classLoader) {
        try {
            return (l) b.a(str, classLoader, false);
        } catch (c e) {
            throw new m(e.a(), e.getMessage());
        }
    }
}
