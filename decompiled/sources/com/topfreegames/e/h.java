package com.topfreegames.e;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.ByteArrayOutputStream;

/* JADX INFO: compiled from: TopFacebookPersistanceManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class h {
    private static h b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private f f1545a;

    public static h a(Context context) {
        if (b == null) {
            b = new h(context);
        }
        return b;
    }

    private h(Context context) {
        this.f1545a = new f(context);
    }

    public l a(String str) {
        g gVarA;
        String strA;
        if (this.f1545a == null || (gVarA = this.f1545a.a(str)) == null || (strA = gVarA.a()) == null) {
            return null;
        }
        String strB = gVarA.b();
        byte[] bArrC = gVarA.c();
        return new l(strA, strB, bArrC != null ? BitmapFactory.decodeByteArray(bArrC, 0, bArrC.length) : null);
    }

    public l a() {
        g gVarA;
        String strA;
        if (this.f1545a == null || (gVarA = this.f1545a.a()) == null || (strA = gVarA.a()) == null) {
            return null;
        }
        String strB = gVarA.b();
        byte[] bArrC = gVarA.c();
        return new l(strA, strB, bArrC != null ? BitmapFactory.decodeByteArray(bArrC, 0, bArrC.length) : null);
    }

    public void a(l lVar) {
        if (lVar != null && this.f1545a != null) {
            String strA = lVar.a();
            String strB = lVar.b();
            Bitmap bitmapC = lVar.c();
            byte[] byteArray = null;
            if (bitmapC != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmapC.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byteArray = byteArrayOutputStream.toByteArray();
            }
            this.f1545a.a(strA, strB, byteArray);
        }
    }

    public void b(l lVar) {
        byte[] byteArray = null;
        if (lVar != null && this.f1545a != null) {
            String strA = lVar.a();
            String strB = lVar.b();
            Bitmap bitmapC = lVar.c();
            if (bitmapC != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmapC.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byteArray = byteArrayOutputStream.toByteArray();
            }
            this.f1545a.b(strA, strB, byteArray);
            return;
        }
        if (lVar == null) {
            this.f1545a.b(null, null, null);
        }
    }
}
