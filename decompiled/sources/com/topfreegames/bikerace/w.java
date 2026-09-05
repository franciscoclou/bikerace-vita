package com.topfreegames.bikerace;

/* JADX INFO: compiled from: GameAudio.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
enum w {
    MENU,
    BIKE_ENGINE_LOW,
    BIKE_ENGINE_MEDIUM,
    BIKE_ENGINE_MEDIUM_HI,
    BIKE_ENGINE_HI_MEDIUM,
    BIKE_ENGINE_HI,
    FALL_IMPACT,
    EXPLOSION,
    WIN,
    HALLOWEEN_RAVEN,
    HALLOWEEN_WITCH,
    HALLOWEEN_SCREAM,
    HALLOWEEN_BELL,
    WORLDCUP_SLOT_BUTTON,
    WORLDCUP_SLOT_RUNNING,
    WORLDCUP_SLOT_STOPPING,
    WORLDCUP_SLOT_COLLECT_PART,
    WORLDCUP_SLOT_COLLECT_MONEY;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static w[] valuesCustom() {
        w[] wVarArrValuesCustom = values();
        int length = wVarArrValuesCustom.length;
        w[] wVarArr = new w[length];
        System.arraycopy(wVarArrValuesCustom, 0, wVarArr, 0, length);
        return wVarArr;
    }
}
