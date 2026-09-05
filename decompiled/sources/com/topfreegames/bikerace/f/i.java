package com.topfreegames.bikerace.f;

/* JADX INFO: compiled from: GiftTasks.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class i extends j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static /* synthetic */ int[] f1234a;

    static /* synthetic */ int[] a() {
        int[] iArr = f1234a;
        if (iArr == null) {
            iArr = new int[b.valuesCustom().length];
            try {
                iArr[b.ASK_TRACK.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[b.GIVE_ONE_TRACK.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[b.GIVE_SPECIFIC_TRACK.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            f1234a = iArr;
        }
        return iArr;
    }

    public i(d dVar, f fVar) {
        super(dVar, fVar);
    }

    @Override // java.lang.Runnable
    public void run() {
        switch (a()[this.b.a().ordinal()]) {
            case 1:
                this.c.a(this.b.b(), this.d.get(), this.e);
                break;
            case 2:
                this.c.a(this.b.b(), this.b.c(), this.d.get(), this.e);
                break;
            case 3:
                this.c.b(this.b.b(), this.d.get(), this.e);
                break;
        }
    }
}
