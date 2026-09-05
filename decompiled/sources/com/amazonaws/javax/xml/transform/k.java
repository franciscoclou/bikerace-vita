package com.amazonaws.javax.xml.transform;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: TransformerException.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class k extends Exception {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    g f148a;
    Throwable b;

    public Throwable a() {
        return this.b;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        if (this.b == this) {
            return null;
        }
        return this.b;
    }

    @Override // java.lang.Throwable
    public synchronized Throwable initCause(Throwable th) {
        if (this.b != null) {
            throw new IllegalStateException("Can't overwrite cause");
        }
        if (th == this) {
            throw new IllegalArgumentException("Self-causation not permitted");
        }
        this.b = th;
        return this;
    }

    public String b() {
        if (this.f148a == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        String strA = this.f148a.a();
        int iB = this.f148a.b();
        int iC = this.f148a.c();
        if (strA != null) {
            stringBuffer.append("; SystemID: ");
            stringBuffer.append(strA);
        }
        if (iB != 0) {
            stringBuffer.append("; Line#: ");
            stringBuffer.append(iB);
        }
        if (iC != 0) {
            stringBuffer.append("; Column#: ");
            stringBuffer.append(iC);
        }
        return stringBuffer.toString();
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        printStackTrace(new PrintWriter((OutputStream) System.err, true));
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream printStream) {
        printStackTrace(new PrintWriter(printStream));
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter printWriter) {
        Throwable th;
        String strB;
        if (printWriter == null) {
            printWriter = new PrintWriter((OutputStream) System.err, true);
        }
        try {
            String strB2 = b();
            if (strB2 != null) {
                printWriter.println(strB2);
            }
            super.printStackTrace(printWriter);
        } catch (Throwable th2) {
        }
        Throwable thA = a();
        int i = 0;
        while (i < 10 && thA != null) {
            printWriter.println("---------");
            try {
                if ((thA instanceof k) && (strB = ((k) thA).b()) != null) {
                    printWriter.println(strB);
                }
                thA.printStackTrace(printWriter);
            } catch (Throwable th3) {
                printWriter.println("Could not print stack trace...");
            }
            try {
                Method method = thA.getClass().getMethod("getException", (Class[]) null);
                if (method != null) {
                    th = (Throwable) method.invoke(thA, (Object[]) null);
                    if (thA == th) {
                        break;
                    }
                } else {
                    th = null;
                }
            } catch (IllegalAccessException e) {
                th = null;
            } catch (NoSuchMethodException e2) {
                th = null;
            } catch (InvocationTargetException e3) {
                th = null;
            }
            i++;
            thA = th;
        }
        printWriter.flush();
    }
}
