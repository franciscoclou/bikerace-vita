package com.topfreegames.bikerace.h;

import android.util.FloatMath;

/* JADX INFO: compiled from: LevelBoardsBuilder.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class c {
    public static void a(b bVar, com.topfreegames.engine.a.b[] bVarArr) {
        com.topfreegames.engine.a.b[] bVarArr2 = new com.topfreegames.engine.a.b[bVarArr.length];
        for (int i = 0; i < bVarArr.length; i++) {
            bVarArr2[i] = new com.topfreegames.engine.a.b(bVarArr[i]);
        }
        if (bVar.d() > 0) {
            bVarArr2[0].b(bVar.c().b);
            b(bVar, bVarArr2);
        } else {
            b(bVar, bVarArr2);
        }
    }

    public static void a(b bVar, int i, com.topfreegames.bikerace.d dVar, float f, float f2) {
        a(bVar, i, dVar, f, f2, 1.0f);
    }

    public static void a(b bVar, int i, com.topfreegames.bikerace.d dVar, float f, float f2, float f3) {
        com.topfreegames.bikerace.d dVar2 = new com.topfreegames.bikerace.d(dVar);
        dVar2.f1168a.b(bVar.c().b);
        dVar2.b.b(bVar.c().b);
        b(bVar, i, dVar2, f, f2, f3);
    }

    public static com.topfreegames.bikerace.d a(double d, double d2, double d3, double d4) {
        return new com.topfreegames.bikerace.d(new com.topfreegames.engine.a.b((float) d, (float) d2), new com.topfreegames.engine.a.b((float) d3, (float) d4));
    }

    public static com.topfreegames.bikerace.d a(float f, float f2, float f3, float f4) {
        return new com.topfreegames.bikerace.d(new com.topfreegames.engine.a.b(f, f2), new com.topfreegames.engine.a.b(f3, f4));
    }

    public static void a(b bVar, int i, float f, float f2) {
        com.topfreegames.engine.a.b bVar2 = new com.topfreegames.engine.a.b();
        for (int iD = bVar.d() - i; iD < bVar.d() - 1; iD++) {
            com.topfreegames.bikerace.d dVarA = bVar.a(iD);
            com.topfreegames.bikerace.d dVarA2 = bVar.a(iD + 1);
            if (dVarA.b.f(dVarA2.f1168a)) {
                float fA = dVarA2.a() - dVarA.a();
                if (fA / f2 > 1.00001f) {
                    float fTan = ((float) Math.tan(fA / 2.0f)) * f;
                    float fTan2 = (float) Math.tan(f2);
                    bVar2.a(dVarA.b).c(dVarA.f1168a);
                    bVar2.b(1.0f - (fTan / bVar2.c()));
                    dVarA.b.a(dVarA.f1168a).b(bVar2);
                    bVar2.a(dVarA2.f1168a).c(dVarA2.b);
                    bVar2.b(1.0f - (((fTan * fTan2) / (FloatMath.sin(fA) - (FloatMath.cos(fA) * fTan2))) / bVar2.c()));
                    dVarA2.f1168a.a(dVarA2.b).b(bVar2);
                    bVar.a(new com.topfreegames.bikerace.d(dVarA.b, dVarA2.f1168a), iD + 1);
                }
            }
        }
    }

    private static void b(b bVar, com.topfreegames.engine.a.b[] bVarArr) {
        com.topfreegames.engine.a.b bVar2 = new com.topfreegames.engine.a.b();
        com.topfreegames.engine.a.b bVar3 = new com.topfreegames.engine.a.b();
        int length = bVarArr.length - 1;
        bVar2.a(bVarArr[0]);
        for (int i = 0; i < length - 1; i++) {
            bVar3.a(bVar2).b(bVarArr[i + 1]);
            bVar.a(new com.topfreegames.bikerace.d(bVar2, bVar3));
            bVar2.a(bVar3);
        }
        bVar3.a(bVar2).b(bVarArr[length]);
        bVar.a(new com.topfreegames.bikerace.d(bVar2, bVar3));
    }

    private static void b(b bVar, int i, com.topfreegames.bikerace.d dVar, float f, float f2, float f3) {
        float f4 = f2 / i;
        float f5 = f4 * f;
        float fA = dVar.a();
        com.topfreegames.engine.a.b[] bVarArr = new com.topfreegames.engine.a.b[i + 1];
        bVarArr[0] = new com.topfreegames.engine.a.b(dVar.f1168a);
        for (int i2 = 1; i2 <= i; i2++) {
            bVarArr[i2] = new com.topfreegames.engine.a.b(FloatMath.cos(fA) * f5, FloatMath.sin(fA) * f5 * f3);
            fA += f4;
        }
        b(bVar, bVarArr);
    }
}
