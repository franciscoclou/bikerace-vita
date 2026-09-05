package com.topfreegames.bikerace.j;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import com.topfreegames.bikerace.ap;
import com.topfreegames.bikerace.c;
import com.topfreegames.bikerace.e.n;
import com.topfreegames.bikerace.e.o;
import com.topfreegames.bikerace.z;

/* JADX INFO: compiled from: ShopOffers.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int f1271a = 0;
    private static boolean d = false;
    private SharedPreferences b;
    private Resources c;

    public b(Context context) {
        this.b = null;
        this.c = null;
        this.b = context.getApplicationContext().getSharedPreferences("com.topfreegames.bikerace.offers", 0);
        this.c = context.getApplicationContext().getResources();
    }

    public boolean a() {
        return ap.n();
    }

    public static n a(Context context, String str, o oVar, o oVar2) {
        if (context != null && str != null) {
            Resources resources = context.getResources();
            String string = resources.getString(2131100000);
            String string2 = resources.getString(2131100001);
            if (str.equals(resources.getString(2131099779))) {
                return new n(context, resources.getString(2131099897), string, string2, oVar, oVar2);
            }
            if (str.equals(resources.getString(2131099781))) {
                return new n(context, resources.getString(2131099898), string, string2, oVar, oVar2);
            }
            if (str.equals(resources.getString(2131099783))) {
                return new n(context, resources.getString(2131099899), string, string2, oVar, oVar2);
            }
            if (str.equals(resources.getString(2131099785))) {
                return new n(context, resources.getString(2131099900), string, string2, oVar, oVar2);
            }
            if (str.equals(resources.getString(2131099793))) {
                return new n(context, resources.getString(2131099901), string, string2, oVar, oVar2);
            }
            if (str.equals(resources.getString(2131099795))) {
                return new n(context, resources.getString(2131099902), string, string2, oVar, oVar2);
            }
            if (str.equals(resources.getString(2131099797))) {
                return new n(context, resources.getString(2131099903), string, string2, oVar, oVar2);
            }
            if (str.equals(resources.getString(2131099789))) {
                return new n(context, resources.getString(2131099904), string, string2, oVar, oVar2);
            }
            if (str.equals(resources.getString(2131099799))) {
                return new n(context, resources.getString(2131099905), string, string2, oVar, oVar2);
            }
            if (str.equals(resources.getString(2131099772))) {
                return new n(context, resources.getString(2131099896), string, string2, oVar, oVar2);
            }
            if (str.equals(resources.getString(2131099791))) {
                return new n(context, resources.getString(2131099906), string, string2, oVar, oVar2);
            }
            if (str.equals(resources.getString(2131099766))) {
                return new n(context, resources.getString(2131099895), string, string2, oVar, oVar2);
            }
            if (str.equals(resources.getString(2131099769))) {
                return new n(context, resources.getString(2131099894), string, string2, oVar, oVar2);
            }
            if (str.equals(resources.getString(2131099801))) {
                return new n(context, resources.getString(2131099893), string, string2, oVar, oVar2);
            }
            if (str.equals(resources.getString(2131099804))) {
                return new n(context, resources.getString(2131099907), string, string2, oVar, oVar2);
            }
            if (str.equals(resources.getString(2131099787))) {
                return new n(context, resources.getString(2131099908), string, string2, oVar, oVar2);
            }
        }
        return null;
    }

    private String c(z zVar) {
        f1271a++;
        if (f1271a % 16 == 1) {
            if (zVar.a(c.SUPER)) {
                return this.c.getString(2131099769);
            }
            f1271a++;
        }
        if (f1271a % 16 == 2) {
            if (zVar.a(c.ULTRA)) {
                return this.c.getString(2131099801);
            }
            f1271a++;
        }
        if (f1271a % 16 == 3) {
            if (zVar.a(c.KIDS)) {
                return this.c.getString(2131099766);
            }
            f1271a++;
        }
        if (f1271a % 16 == 4) {
            if (zVar.a(c.GOLD)) {
                return this.c.getString(2131099791);
            }
            f1271a++;
        }
        if (f1271a % 16 == 5) {
            if (zVar.a(c.ARMY)) {
                return this.c.getString(2131099787);
            }
            f1271a++;
        }
        if (f1271a % 16 == 6) {
            if (zVar.a(c.GHOST)) {
                return this.c.getString(2131099772);
            }
            f1271a++;
        }
        if (f1271a % 16 == 7) {
            if (zVar.a(c.BEAT)) {
                return this.c.getString(2131099799);
            }
            f1271a++;
        }
        if (f1271a % 16 == 8) {
            if (zVar.a(c.ZOMBIE)) {
                return this.c.getString(2131099804);
            }
            f1271a++;
        }
        if (f1271a % 16 == 9) {
            if (zVar.a(c.SILVER)) {
                return this.c.getString(2131099789);
            }
            f1271a++;
        }
        if (f1271a % 16 == 10) {
            if (zVar.a(c.COP)) {
                return this.c.getString(2131099797);
            }
            f1271a++;
        }
        if (f1271a % 16 == 11) {
            if (zVar.a(c.SPAM)) {
                return this.c.getString(2131099795);
            }
            f1271a++;
        }
        if (f1271a % 16 == 12) {
            if (zVar.a(c.NINJA)) {
                return this.c.getString(2131099793);
            }
            f1271a++;
        }
        if (f1271a % 16 == 13) {
            if (zVar.a(c.BRONZE)) {
                return this.c.getString(2131099785);
            }
            f1271a++;
        }
        if (f1271a % 16 == 14) {
            if (zVar.a(c.ACROBATIC)) {
                return this.c.getString(2131099783);
            }
            f1271a++;
        }
        if (f1271a % 16 == 15) {
            if (zVar.a(c.RETRO)) {
                return this.c.getString(2131099781);
            }
            f1271a++;
        }
        if (f1271a % 16 == 0) {
            if (zVar.a(c.GIRL)) {
                return this.c.getString(2131099779);
            }
            f1271a++;
        }
        f1271a = 0;
        return null;
    }

    public String a(z zVar) {
        return a(zVar, false);
    }

    public String b(z zVar) {
        return a(zVar, true);
    }

    private String a(z zVar, boolean z) {
        d();
        int iF = f();
        if (iF == 13) {
            if (zVar.a(c.SUPER)) {
                return this.c.getString(2131099769);
            }
            return c(zVar);
        }
        if (iF == 25) {
            if (zVar.a(c.ULTRA)) {
                return this.c.getString(2131099801);
            }
            return c(zVar);
        }
        if (iF == 45) {
            if (zVar.a(c.KIDS)) {
                return this.c.getString(2131099766);
            }
            return c(zVar);
        }
        if (iF == 65) {
            if (zVar.a(c.GHOST)) {
                return this.c.getString(2131099772);
            }
            return c(zVar);
        }
        if (iF == 90) {
            return c(zVar);
        }
        if ((iF > 100 && iF % 40 == 0) || z) {
            return c(zVar);
        }
        return null;
    }

    public void b() {
        d();
        int iF = f();
        if (iF == 13 || iF == 25 || iF == 45 || iF == 65 || iF == 90 || (iF > 100 && iF % 40 == 0)) {
            e();
        }
    }

    private void d() {
        SharedPreferences.Editor editorEdit = this.b.edit();
        editorEdit.putInt("LevelsPlayed", f() + 1);
        editorEdit.commit();
    }

    private void e() {
        SharedPreferences.Editor editorEdit = this.b.edit();
        editorEdit.putInt("LevelsPlayed", f() - 1);
        editorEdit.commit();
    }

    private int f() {
        return this.b.getInt("LevelsPlayed", 0);
    }

    public static synchronized void a(boolean z) {
        d = z;
    }

    public static synchronized boolean c() {
        return d;
    }
}
