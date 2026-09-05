package org.a.a.a.a.a;

/* JADX INFO: compiled from: ExceptionHelper.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class i {
    public static org.a.a.a.a.k a(int i) {
        return (i == 4 || i == 5) ? new org.a.a.a.a.o(i) : new org.a.a.a.a.k(i);
    }

    public static org.a.a.a.a.k a(Throwable th) {
        return th.getClass().getName().equals("java.security.GeneralSecurityException") ? new org.a.a.a.a.o(th) : new org.a.a.a.a.k(th);
    }

    public static boolean a(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
