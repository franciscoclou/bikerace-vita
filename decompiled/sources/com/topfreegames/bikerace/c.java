package com.topfreegames.bikerace;

/* JADX INFO: compiled from: Bike.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum c {
    REGULAR,
    SUPER,
    KIDS,
    GHOST,
    NINJA,
    COP,
    RETRO,
    BRONZE,
    SILVER,
    GOLD,
    GIRL,
    ACROBATIC,
    BEAT,
    SPAM,
    ULTRA,
    ZOMBIE,
    ARMY,
    HALLOWEEN,
    THANKSGIVING,
    SANTA,
    EASTER,
    NOT_USED_IOS_DAILY_WORLD_BIKE_INDEX_HOLDER,
    WORLDCUP_USA,
    WORLDCUP_ENGLAND,
    WORLDCUP_AUSTRALIA,
    WORLDCUP_NETHERLANDS,
    WORLDCUP_FRANCE,
    WORLDCUP_GERMANY,
    WORLDCUP_BRAZIL,
    WORLDCUP_SPAIN,
    WORLDCUP_JAPAN,
    WORLDCUP_BELGIUM,
    WORLDCUP_MEXICO,
    WORLDCUP_ITALY,
    WORLDCUP_ARGENTINA;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static c[] valuesCustom() {
        c[] cVarArrValuesCustom = values();
        int length = cVarArrValuesCustom.length;
        c[] cVarArr = new c[length];
        System.arraycopy(cVarArrValuesCustom, 0, cVarArr, 0, length);
        return cVarArr;
    }

    public static c a(int i) {
        try {
            return valuesCustom()[i];
        } catch (Exception e) {
            return REGULAR;
        }
    }
}
