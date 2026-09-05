package org.a.a.a.a.a;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.a.a.a.a.a.b.t;

/* JADX INFO: compiled from: CommsTokenStore.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final String f1591a = h.class.getName();
    private Hashtable c;
    private String d;
    private org.a.a.a.a.k e = null;
    org.a.a.a.a.b.a b = org.a.a.a.a.b.b.a("org.eclipse.paho.client.mqttv3.internal.nls.logcat", f1591a);

    public h(String str) {
        this.b.a(str);
        this.c = new Hashtable();
        this.d = str;
        this.b.a(f1591a, "<Init>", "308");
    }

    public org.a.a.a.a.p a(t tVar) {
        return (org.a.a.a.a.p) this.c.get(tVar.e());
    }

    public org.a.a.a.a.p a(String str) {
        return (org.a.a.a.a.p) this.c.get(str);
    }

    public org.a.a.a.a.p b(t tVar) {
        if (tVar != null) {
            return b(tVar.e());
        }
        return null;
    }

    public org.a.a.a.a.p b(String str) {
        org.a.a.a.a.p pVar;
        this.b.b(f1591a, "removeToken", "306", new Object[]{str});
        if (str != null) {
            synchronized (this.c) {
                org.a.a.a.a.p pVar2 = (org.a.a.a.a.p) this.c.get(str);
                if (pVar2 != null) {
                    synchronized (pVar2) {
                        pVar = (org.a.a.a.a.p) this.c.remove(str);
                    }
                    return pVar;
                }
            }
        }
        return null;
    }

    protected org.a.a.a.a.j a(org.a.a.a.a.a.b.o oVar) {
        org.a.a.a.a.j jVar;
        synchronized (this.c) {
            String string = new Integer(oVar.j()).toString();
            if (this.c.containsKey(string)) {
                jVar = (org.a.a.a.a.j) this.c.get(string);
                this.b.b(f1591a, "restoreToken", "302", new Object[]{string, oVar, jVar});
            } else {
                jVar = new org.a.a.a.a.j(this.d);
                jVar.f1606a.a(string);
                this.c.put(string, jVar);
                this.b.b(f1591a, "restoreToken", "303", new Object[]{string, oVar, jVar});
            }
        }
        return jVar;
    }

    protected void a(org.a.a.a.a.p pVar, t tVar) {
        synchronized (this.c) {
            if (this.e == null) {
                String strE = tVar.e();
                this.b.b(f1591a, "saveToken", "300", new Object[]{strE, tVar});
                a(pVar, strE);
            } else {
                throw this.e;
            }
        }
    }

    protected void a(org.a.a.a.a.p pVar, String str) {
        synchronized (this.c) {
            this.b.b(f1591a, "saveToken", "307", new Object[]{str, pVar.toString()});
            pVar.f1606a.a(str);
            this.c.put(str, pVar);
        }
    }

    protected void a(org.a.a.a.a.k kVar) {
        synchronized (this.c) {
            this.b.b(f1591a, "quiesce", "309", new Object[]{kVar});
            this.e = kVar;
        }
    }

    public void a() {
        synchronized (this.c) {
            this.b.a(f1591a, "open", "310");
            this.e = null;
        }
    }

    public org.a.a.a.a.j[] b() {
        org.a.a.a.a.j[] jVarArr;
        synchronized (this.c) {
            this.b.a(f1591a, "getOutstandingDelTokens", "311");
            Vector vector = new Vector();
            Enumeration enumerationElements = this.c.elements();
            while (enumerationElements.hasMoreElements()) {
                org.a.a.a.a.p pVar = (org.a.a.a.a.p) enumerationElements.nextElement();
                if (pVar != null && (pVar instanceof org.a.a.a.a.j) && !pVar.f1606a.n()) {
                    vector.addElement(pVar);
                }
            }
            jVarArr = (org.a.a.a.a.j[]) vector.toArray(new org.a.a.a.a.j[vector.size()]);
        }
        return jVarArr;
    }

    public Vector c() {
        Vector vector;
        synchronized (this.c) {
            this.b.a(f1591a, "getOutstandingTokens", "312");
            vector = new Vector();
            Enumeration enumerationElements = this.c.elements();
            while (enumerationElements.hasMoreElements()) {
                org.a.a.a.a.p pVar = (org.a.a.a.a.p) enumerationElements.nextElement();
                if (pVar != null) {
                    vector.addElement(pVar);
                }
            }
        }
        return vector;
    }

    public void d() {
        this.b.b(f1591a, "clear", "305", new Object[]{new Integer(this.c.size())});
        synchronized (this.c) {
            this.c.clear();
        }
    }

    public int e() {
        int size;
        synchronized (this.c) {
            size = this.c.size();
        }
        return size;
    }

    public String toString() {
        String string;
        String property = System.getProperty("line.separator", "\n");
        StringBuffer stringBuffer = new StringBuffer();
        synchronized (this.c) {
            Enumeration enumerationElements = this.c.elements();
            while (enumerationElements.hasMoreElements()) {
                stringBuffer.append("{" + ((org.a.a.a.a.p) enumerationElements.nextElement()).f1606a + "}" + property);
            }
            string = stringBuffer.toString();
        }
        return string;
    }
}
