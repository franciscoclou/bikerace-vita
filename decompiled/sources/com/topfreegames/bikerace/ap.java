package com.topfreegames.bikerace;

import com.amazonaws.javax.xml.stream.XMLStreamConstants;

/* JADX INFO: compiled from: MainConfig.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ap {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final av f1123a = av.RELEASE_PRO_MULTI_GOOGLE_PLAY;
    private static /* synthetic */ int[] b;

    static /* synthetic */ int[] x() {
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

    public static void a() {
        if (!d() && v()) {
            throw new IllegalStateException("Inconsistent Profile - Err1");
        }
    }

    public static boolean b() {
        return true;
    }

    public static boolean c() {
        return true;
    }

    public static boolean d() {
        switch (x()[f1123a.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case XMLStreamConstants.ATTRIBUTE /* 10 */:
            case XMLStreamConstants.DTD /* 11 */:
            case XMLStreamConstants.CDATA /* 12 */:
            case XMLStreamConstants.NAMESPACE /* 13 */:
            case XMLStreamConstants.NOTATION_DECLARATION /* 14 */:
            case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
            default:
                return true;
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
                return false;
        }
    }

    public static boolean e() {
        switch (x()[f1123a.ordinal()]) {
            case 1:
            case 2:
            case 5:
            case 8:
            case 9:
            case XMLStreamConstants.ATTRIBUTE /* 10 */:
            case XMLStreamConstants.NAMESPACE /* 13 */:
            case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
            case 16:
            case 17:
            case 20:
            case 22:
            case 24:
            case 25:
            case 28:
            case 30:
                return true;
            case 3:
            case 4:
            case 6:
            case 7:
            case XMLStreamConstants.DTD /* 11 */:
            case XMLStreamConstants.CDATA /* 12 */:
            case XMLStreamConstants.NOTATION_DECLARATION /* 14 */:
            case 18:
            case 19:
            case 21:
            case 23:
            case 26:
            case 27:
            case 29:
            default:
                return false;
        }
    }

    public static boolean f() {
        switch (x()[f1123a.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 6:
            case 7:
            case 9:
            case XMLStreamConstants.ATTRIBUTE /* 10 */:
            case XMLStreamConstants.DTD /* 11 */:
            case XMLStreamConstants.CDATA /* 12 */:
            case XMLStreamConstants.NOTATION_DECLARATION /* 14 */:
            case 16:
            case 17:
            case 18:
            case 19:
            case 21:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 29:
            default:
                return false;
            case 5:
            case 8:
            case XMLStreamConstants.NAMESPACE /* 13 */:
            case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
            case 20:
            case 22:
            case 28:
            case 30:
                return true;
        }
    }

    public static boolean g() {
        switch (x()[f1123a.ordinal()]) {
            case 1:
            case 3:
            case 5:
            case 8:
            case XMLStreamConstants.NAMESPACE /* 13 */:
            case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
            case 16:
            case 18:
            case 20:
            case 22:
            case 28:
            case 30:
                return false;
            case 2:
            case 4:
            case 6:
            case 7:
            case 9:
            case XMLStreamConstants.ATTRIBUTE /* 10 */:
            case XMLStreamConstants.DTD /* 11 */:
            case XMLStreamConstants.CDATA /* 12 */:
            case XMLStreamConstants.NOTATION_DECLARATION /* 14 */:
            case 17:
            case 19:
            case 21:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 29:
            default:
                return true;
        }
    }

    public static boolean h() {
        switch (x()[f1123a.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 9:
            case XMLStreamConstants.ATTRIBUTE /* 10 */:
            case XMLStreamConstants.DTD /* 11 */:
            case XMLStreamConstants.CDATA /* 12 */:
            case 16:
            case 17:
            case 18:
            case 19:
            case 24:
            case 25:
            case 26:
            case 27:
                return true;
            case 5:
            case 6:
            case 7:
            case 8:
            case XMLStreamConstants.NAMESPACE /* 13 */:
            case XMLStreamConstants.NOTATION_DECLARATION /* 14 */:
            case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
            case 20:
            case 21:
            case 22:
            case 23:
            case 28:
            case 29:
            case 30:
            default:
                return false;
        }
    }

    public static boolean i() {
        switch (x()[f1123a.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 7:
            case 8:
            case 9:
            case XMLStreamConstants.ATTRIBUTE /* 10 */:
            case XMLStreamConstants.DTD /* 11 */:
            case XMLStreamConstants.CDATA /* 12 */:
            case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
            case 16:
            case 17:
            case 18:
            case 19:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 30:
            default:
                return false;
            case 5:
            case 6:
            case XMLStreamConstants.NAMESPACE /* 13 */:
            case XMLStreamConstants.NOTATION_DECLARATION /* 14 */:
            case 20:
            case 21:
            case 28:
            case 29:
                return true;
        }
    }

    public static boolean j() {
        switch (x()[f1123a.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 8:
            case 9:
            case XMLStreamConstants.ATTRIBUTE /* 10 */:
            case XMLStreamConstants.DTD /* 11 */:
            case XMLStreamConstants.CDATA /* 12 */:
            case XMLStreamConstants.NAMESPACE /* 13 */:
            case XMLStreamConstants.NOTATION_DECLARATION /* 14 */:
            case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            default:
                return false;
            case 7:
            case 23:
                return true;
        }
    }

    public static int k() {
        return 6;
    }

    public static boolean l() {
        switch (x()[f1123a.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 9:
            case XMLStreamConstants.ATTRIBUTE /* 10 */:
            case XMLStreamConstants.DTD /* 11 */:
            case 16:
            case 17:
            case 18:
            case 19:
            case 24:
            case 25:
            case 26:
            default:
                return false;
            case 5:
            case 6:
            case 7:
            case 8:
            case XMLStreamConstants.CDATA /* 12 */:
            case XMLStreamConstants.NAMESPACE /* 13 */:
            case XMLStreamConstants.NOTATION_DECLARATION /* 14 */:
            case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
            case 20:
            case 21:
            case 22:
            case 23:
            case 27:
            case 28:
            case 29:
            case 30:
                return true;
        }
    }

    public static boolean m() {
        switch (x()[f1123a.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 6:
            case 7:
            case 9:
            case XMLStreamConstants.ATTRIBUTE /* 10 */:
            case XMLStreamConstants.DTD /* 11 */:
            case XMLStreamConstants.CDATA /* 12 */:
            case XMLStreamConstants.NOTATION_DECLARATION /* 14 */:
            case 16:
            case 17:
            case 18:
            case 19:
            case 21:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 29:
            default:
                return true;
            case 5:
            case 8:
            case XMLStreamConstants.NAMESPACE /* 13 */:
            case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
            case 20:
            case 22:
            case 28:
            case 30:
                return false;
        }
    }

    public static boolean n() {
        switch (x()[f1123a.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 9:
            case XMLStreamConstants.ATTRIBUTE /* 10 */:
            case XMLStreamConstants.DTD /* 11 */:
            case XMLStreamConstants.CDATA /* 12 */:
            case XMLStreamConstants.NAMESPACE /* 13 */:
            case XMLStreamConstants.NOTATION_DECLARATION /* 14 */:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            default:
                return true;
            case 7:
            case 8:
            case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
            case 22:
            case 23:
            case 30:
                return false;
        }
    }

    public static boolean o() {
        switch (x()[f1123a.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 9:
            case XMLStreamConstants.ATTRIBUTE /* 10 */:
            case XMLStreamConstants.DTD /* 11 */:
            case XMLStreamConstants.CDATA /* 12 */:
            case 16:
            case 17:
            case 18:
            case 19:
            case 24:
            case 25:
            case 26:
            case 27:
                return true;
            case 5:
            case 6:
            case 7:
            case 8:
            case XMLStreamConstants.NAMESPACE /* 13 */:
            case XMLStreamConstants.NOTATION_DECLARATION /* 14 */:
            case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
            case 20:
            case 21:
            case 22:
            case 23:
            case 28:
            case 29:
            case 30:
            default:
                return false;
        }
    }

    public static boolean p() {
        switch (x()[f1123a.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 7:
            case 8:
            case 9:
            case XMLStreamConstants.ATTRIBUTE /* 10 */:
            case XMLStreamConstants.DTD /* 11 */:
            case XMLStreamConstants.CDATA /* 12 */:
            case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
            case 16:
            case 17:
            case 18:
            case 19:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 30:
            default:
                return false;
            case 5:
            case 6:
            case XMLStreamConstants.NAMESPACE /* 13 */:
            case XMLStreamConstants.NOTATION_DECLARATION /* 14 */:
            case 20:
            case 21:
            case 28:
            case 29:
                return true;
        }
    }

    public static boolean q() {
        switch (x()[f1123a.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
            case XMLStreamConstants.ATTRIBUTE /* 10 */:
            case XMLStreamConstants.DTD /* 11 */:
            case XMLStreamConstants.CDATA /* 12 */:
            case XMLStreamConstants.NAMESPACE /* 13 */:
            case XMLStreamConstants.NOTATION_DECLARATION /* 14 */:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            default:
                return false;
            case 8:
            case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
            case 22:
            case 30:
                return true;
        }
    }

    public static boolean r() {
        switch (x()[f1123a.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 9:
            case XMLStreamConstants.ATTRIBUTE /* 10 */:
            case XMLStreamConstants.DTD /* 11 */:
            case XMLStreamConstants.CDATA /* 12 */:
            case 16:
            case 17:
            case 18:
            case 19:
            case 24:
            case 25:
            case 26:
            case 27:
                return true;
            case 5:
            case 6:
            case 7:
            case 8:
            case XMLStreamConstants.NAMESPACE /* 13 */:
            case XMLStreamConstants.NOTATION_DECLARATION /* 14 */:
            case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
            case 20:
            case 21:
            case 22:
            case 23:
            case 28:
            case 29:
            case 30:
            default:
                return false;
        }
    }

    public static boolean s() {
        switch (x()[f1123a.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            default:
                return false;
            case 9:
            case XMLStreamConstants.ATTRIBUTE /* 10 */:
            case XMLStreamConstants.DTD /* 11 */:
            case XMLStreamConstants.CDATA /* 12 */:
            case XMLStreamConstants.NAMESPACE /* 13 */:
            case XMLStreamConstants.NOTATION_DECLARATION /* 14 */:
            case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
                return true;
        }
    }

    public static boolean t() {
        switch (x()[f1123a.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            default:
                return false;
            case 9:
            case XMLStreamConstants.ATTRIBUTE /* 10 */:
            case XMLStreamConstants.DTD /* 11 */:
            case XMLStreamConstants.CDATA /* 12 */:
            case XMLStreamConstants.NAMESPACE /* 13 */:
            case XMLStreamConstants.NOTATION_DECLARATION /* 14 */:
            case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
                return true;
        }
    }

    public static boolean u() {
        return false;
    }

    public static boolean a(int i) {
        return true;
    }

    public static boolean v() {
        return false;
    }
}
