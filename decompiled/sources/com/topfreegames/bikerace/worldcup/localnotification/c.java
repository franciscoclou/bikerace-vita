package com.topfreegames.bikerace.worldcup.localnotification;

/* JADX INFO: compiled from: LocalNotificationConfig.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum c {
    AUTRALIA_BIKE_PROMO(com.topfreegames.bikerace.c.WORLDCUP_AUSTRALIA),
    BRAZIL_BIKE_PROMO(com.topfreegames.bikerace.c.WORLDCUP_BRAZIL),
    ENGLAND_BIKE_PROMO(com.topfreegames.bikerace.c.WORLDCUP_ENGLAND),
    USA_BIKEPROMO(com.topfreegames.bikerace.c.WORLDCUP_USA),
    LASTDAY_BIKEPROMO(com.topfreegames.bikerace.c.WORLDCUP_USA),
    JULY_4(com.topfreegames.bikerace.c.WORLDCUP_USA),
    ITALY_BIKEPROMO(com.topfreegames.bikerace.c.WORLDCUP_ITALY);

    private final com.topfreegames.bikerace.c h;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static c[] valuesCustom() {
        c[] cVarArrValuesCustom = values();
        int length = cVarArrValuesCustom.length;
        c[] cVarArr = new c[length];
        System.arraycopy(cVarArrValuesCustom, 0, cVarArr, 0, length);
        return cVarArr;
    }

    c(com.topfreegames.bikerace.c cVar) {
        this.h = cVar;
    }

    public com.topfreegames.bikerace.c a() {
        return this.h;
    }
}
