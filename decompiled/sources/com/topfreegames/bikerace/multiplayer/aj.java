package com.topfreegames.bikerace.multiplayer;

import com.topfreegames.bikerace.ap;

/* JADX INFO: compiled from: RacePlayer.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class aj {
    static float c = (float) Math.pow(10.0d, 2.0d);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f1306a;
    boolean b;
    private ak d;

    public aj(ak akVar, float f) {
        this.d = null;
        this.b = false;
        this.d = akVar;
        this.f1306a = (int) (Math.abs(f) * 10.0f);
        if (this.f1306a >= this.d.b()) {
            this.f1306a = this.d.b() - 1;
        }
        this.b = false;
        if (this.f1306a < this.d.b() - 1 && this.f1306a > 3) {
            long jAbs = 0;
            for (int i = 0; i < 3; i++) {
                a aVarA = this.d.a(this.f1306a - i);
                a aVarA2 = this.d.a((this.f1306a - i) - 1);
                jAbs = jAbs + Math.abs(aVarA.f1299a - aVarA2.f1299a) + Math.abs(aVarA.b - aVarA2.b) + Math.abs(aVarA.c - aVarA2.c);
            }
            a aVarA3 = this.d.a(this.f1306a + 1);
            a aVarA4 = this.d.a(this.f1306a);
            if (Math.abs(aVarA3.c - aVarA4.c) + Math.abs(aVarA3.f1299a - aVarA4.f1299a) + Math.abs(aVarA3.b - aVarA4.b) <= jAbs) {
                this.b = true;
            }
        }
    }

    public void a(float f, com.topfreegames.bikerace.a aVar) {
        a aVarA;
        int i = (int) (10.0f * f);
        float f2 = (10.0f * f) - i;
        float f3 = aVar.e().f1553a;
        float f4 = aVar.e().b;
        float fB = aVar.b();
        try {
            if (i < this.d.b()) {
                if (i < this.f1306a) {
                    aVarA = this.d.a(i);
                } else if (i > this.f1306a && this.b) {
                    aVarA = this.d.a(this.f1306a + 1);
                } else {
                    aVarA = this.d.a(this.f1306a);
                }
                float f5 = aVarA.f1299a / c;
                float f6 = aVarA.b / c;
                float f7 = aVarA.c / c;
                if (i < this.f1306a || (i == this.f1306a && this.b)) {
                    a aVarA2 = this.d.a(i + 1);
                    float f8 = aVarA2.f1299a / c;
                    float f9 = aVarA2.b / c;
                    float f10 = aVarA2.c / c;
                    f5 = (f5 * (1.0f - f2)) + (f8 * f2);
                    f6 = (f6 * (1.0f - f2)) + (f2 * f9);
                    if (f10 - f7 > 3.141592653589793d) {
                        f7 = (float) (((double) f7) + 6.283185307179586d);
                    } else if (f10 - f7 < -3.141592653589793d) {
                        f7 = (float) (((double) f7) - 6.283185307179586d);
                    }
                    f7 = (f7 * (1.0f - f2)) + (f10 * f2);
                }
                aVar.a(f5, f6, f7, 0.1f);
            }
        } catch (Exception e) {
            if (ap.d()) {
                e.printStackTrace();
            }
            aVar.a(f3, f4, fB, 0.1f);
        }
    }
}
