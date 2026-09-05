package android.support.v4.c;

/* JADX INFO: compiled from: ContainerHelpers.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final int[] f27a = new int[0];
    static final long[] b = new long[0];
    static final Object[] c = new Object[0];

    public static int a(int i) {
        return b(i * 4) / 4;
    }

    public static int b(int i) {
        for (int i2 = 4; i2 < 32; i2++) {
            if (i <= (1 << i2) - 12) {
                return (1 << i2) - 12;
            }
        }
        return i;
    }

    public static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    static int a(int[] iArr, int i, int i2) {
        int i3 = 0;
        int i4 = i - 1;
        while (i3 <= i4) {
            int i5 = (i3 + i4) >>> 1;
            int i6 = iArr[i5];
            if (i6 < i2) {
                i3 = i5 + 1;
            } else {
                if (i6 <= i2) {
                    return i5;
                }
                i4 = i5 - 1;
            }
        }
        return i3 ^ (-1);
    }
}
