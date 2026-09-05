package com.topfreegames.bikerace;

import java.io.File;

/* JADX INFO: compiled from: GameSinglePlayerBestRacesData.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ae {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ ad f1118a;

    public ae(ad adVar) {
        this.f1118a = adVar;
    }

    public void a(int i, int i2, int i3) {
        a(i);
        int i4 = i - 1;
        int i5 = i2 - 1;
        this.f1118a.f1115a.removeChild(ad.e(i4, i5));
        this.f1118a.b.deleteFile(String.format("BikeRaceSPBR_%s.dat", ad.e(i4, i5)));
        for (int i6 = i5 + 1; i6 < i3; i6++) {
            try {
                String strE = ad.e(i4, i6);
                String strE2 = ad.e(i4, i6 - 1);
                new File(this.f1118a.b.getFilesDir(), String.format("BikeRaceSPBR_%s.dat", strE)).renameTo(new File(this.f1118a.b.getFilesDir(), String.format("BikeRaceSPBR_%s.dat", strE2)));
                this.f1118a.f1115a.changeChildKey(strE, strE2);
            } catch (Exception e) {
            }
        }
    }

    private void a(int i) {
        if (i != 999) {
            throw new IllegalArgumentException("Invalid world for LevelEditor");
        }
    }
}
