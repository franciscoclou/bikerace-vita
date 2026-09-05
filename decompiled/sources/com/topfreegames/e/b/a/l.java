package com.topfreegames.e.b.a;

import android.util.Log;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: TopFacebookAppRequestManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class l implements Callable<Boolean> {
    private static /* synthetic */ int[] c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ i f1520a;
    private k b;

    static /* synthetic */ int[] b() {
        int[] iArr = c;
        if (iArr == null) {
            iArr = new int[j.valuesCustom().length];
            try {
                iArr[j.CREATE_APP_USER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[j.CREATE_USER_USER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[j.DELETE.ordinal()] = 4;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[j.READ.ordinal()] = 3;
            } catch (NoSuchFieldError e4) {
            }
            c = iArr;
        }
        return iArr;
    }

    public l(i iVar, k kVar) {
        this.f1520a = iVar;
        if (kVar == null) {
            throw new IllegalArgumentException("Request cannot be null!");
        }
        this.b = kVar;
    }

    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Boolean call() {
        try {
            switch (b()[this.b.a().ordinal()]) {
                case 1:
                    ((b) this.b.b()).a();
                    break;
                case 2:
                    ((e) this.b.b()).a();
                    break;
                case 3:
                    ((n) this.b.b()).a();
                    break;
                case 4:
                    ((g) this.b.b()).a();
                    break;
            }
        } catch (Exception e) {
            Log.v(i.class.getSimpleName(), Log.getStackTraceString(e));
        } finally {
            this.f1520a.c.remove(this);
        }
        return true;
    }
}
