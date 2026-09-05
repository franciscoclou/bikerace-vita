package com.topfreegames.bikerace;

/* JADX INFO: compiled from: MainConfig.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class as {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final com.topfreegames.a.c f1124a = com.topfreegames.a.c.FLURRY;
    private static /* synthetic */ int[] b;

    static /* synthetic */ int[] c() {
        int[] iArr = b;
        if (iArr == null) {
            iArr = new int[av.valuesCustom().length];
            try {
                iArr[av.DEBUG_FREE_MULTI_AMAZON.ordinal()] = 6;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[av.DEBUG_FREE_MULTI_GOOGLE_PLAY.ordinal()] = 4;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[av.DEBUG_FREE_MULTI_GOOGLE_REVIEW.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[av.DEBUG_FREE_MULTI_SAMSUNG.ordinal()] = 7;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[av.DEBUG_FREE_SINGLE_AMAZON.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[av.DEBUG_FREE_SINGLE_BLACKBERRY.ordinal()] = 8;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[av.DEBUG_FREE_SINGLE_GOOGLE_PLAY.ordinal()] = 2;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[av.DEBUG_FREE_SINGLE_GOOGLE_REVIEW.ordinal()] = 1;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[av.DEBUG_PRO_MULTI_AMAZON.ordinal()] = 14;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[av.DEBUG_PRO_MULTI_GOOGLE_PLAY.ordinal()] = 12;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[av.DEBUG_PRO_MULTI_GOOGLE_REVIEW.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[av.DEBUG_PRO_SINGLE_AMAZON.ordinal()] = 13;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr[av.DEBUG_PRO_SINGLE_BLACKBERRY.ordinal()] = 15;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr[av.DEBUG_PRO_SINGLE_GOOGLE_PLAY.ordinal()] = 10;
            } catch (NoSuchFieldError e14) {
            }
            try {
                iArr[av.DEBUG_PRO_SINGLE_GOOGLE_REVIEW.ordinal()] = 9;
            } catch (NoSuchFieldError e15) {
            }
            try {
                iArr[av.RELEASE_FREE_MULTI_AMAZON.ordinal()] = 21;
            } catch (NoSuchFieldError e16) {
            }
            try {
                iArr[av.RELEASE_FREE_MULTI_GOOGLE_PLAY.ordinal()] = 19;
            } catch (NoSuchFieldError e17) {
            }
            try {
                iArr[av.RELEASE_FREE_MULTI_GOOGLE_REVIEW.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                iArr[av.RELEASE_FREE_MULTI_SAMSUNG.ordinal()] = 23;
            } catch (NoSuchFieldError e19) {
            }
            try {
                iArr[av.RELEASE_FREE_SINGLE_AMAZON.ordinal()] = 20;
            } catch (NoSuchFieldError e20) {
            }
            try {
                iArr[av.RELEASE_FREE_SINGLE_BLACKBERRY.ordinal()] = 22;
            } catch (NoSuchFieldError e21) {
            }
            try {
                iArr[av.RELEASE_FREE_SINGLE_GOOGLE_PLAY.ordinal()] = 17;
            } catch (NoSuchFieldError e22) {
            }
            try {
                iArr[av.RELEASE_FREE_SINGLE_GOOGLE_REVIEW.ordinal()] = 16;
            } catch (NoSuchFieldError e23) {
            }
            try {
                iArr[av.RELEASE_PRO_MULTI_AMAZON.ordinal()] = 29;
            } catch (NoSuchFieldError e24) {
            }
            try {
                iArr[av.RELEASE_PRO_MULTI_GOOGLE_PLAY.ordinal()] = 27;
            } catch (NoSuchFieldError e25) {
            }
            try {
                iArr[av.RELEASE_PRO_MULTI_GOOGLE_REVIEW.ordinal()] = 26;
            } catch (NoSuchFieldError e26) {
            }
            try {
                iArr[av.RELEASE_PRO_SINGLE_AMAZON.ordinal()] = 28;
            } catch (NoSuchFieldError e27) {
            }
            try {
                iArr[av.RELEASE_PRO_SINGLE_BLACKBERRY.ordinal()] = 30;
            } catch (NoSuchFieldError e28) {
            }
            try {
                iArr[av.RELEASE_PRO_SINGLE_GOOGLE_PLAY.ordinal()] = 25;
            } catch (NoSuchFieldError e29) {
            }
            try {
                iArr[av.RELEASE_PRO_SINGLE_GOOGLE_REVIEW.ordinal()] = 24;
            } catch (NoSuchFieldError e30) {
            }
            b = iArr;
        }
        return iArr;
    }

    public static com.topfreegames.a.c a() {
        return f1124a;
    }

    public static String b() {
        if (f1124a == com.topfreegames.a.c.FLURRY) {
            if (ap.d()) {
                return "CKC3ZWZXQWQF4DYTK3J8";
            }
            switch (c()[ap.f1123a.ordinal()]) {
                case 16:
                case 17:
                case 18:
                case 19:
                    return "WYSTVWC2257NHM6FQXYM";
                case 20:
                case 21:
                case 28:
                case 29:
                    return "HT588P8NCQFMQK5DQJTG";
                case 22:
                case 30:
                    return "X7Z3ZQNFH3QJXK3YTX3G";
                case 23:
                    return "23XQJ5YR9XB7G96Z8J2Z";
                case 24:
                case 25:
                case 26:
                case 27:
                    return "JTDJFUTP4NXLG43ZEXIH";
                default:
                    return "CKC3ZWZXQWQF4DYTK3J8";
            }
        }
        return "";
    }
}
