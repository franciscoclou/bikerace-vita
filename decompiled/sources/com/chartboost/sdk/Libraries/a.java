package com.chartboost.sdk.Libraries;

import android.graphics.Bitmap;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Map<String, C0000a> f378a = Collections.synchronizedMap(new LinkedHashMap(10, 1.5f, true));
    private long b = 0;
    private long c = 1000000;

    public a() {
        a((int) (Runtime.getRuntime().maxMemory() * 0.15f));
    }

    public void a(long j) {
        this.c = j;
    }

    public C0000a a(String str) {
        if (this.f378a.containsKey(str)) {
            return this.f378a.get(str);
        }
        return null;
    }

    public void a(String str, C0000a c0000a) {
        try {
            if (this.f378a.containsKey(str)) {
                this.b -= a(this.f378a.get(str).b());
            }
            this.f378a.put(str, c0000a);
            this.b += a(c0000a.b());
            b();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void b() {
        if (this.b > this.c) {
            Iterator<Map.Entry<String, C0000a>> it = this.f378a.entrySet().iterator();
            while (it.hasNext()) {
                this.b -= a(it.next().getValue().b());
                it.remove();
                if (this.b <= this.c) {
                    return;
                }
            }
        }
    }

    public void a() {
        this.f378a.clear();
    }

    private static long a(Bitmap bitmap) {
        if (bitmap == null) {
            return 0L;
        }
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    /* JADX INFO: renamed from: com.chartboost.sdk.Libraries.a$a, reason: collision with other inner class name */
    public class C0000a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Bitmap f379a;
        private int b;
        private float c;
        private boolean d;

        public C0000a(Bitmap bitmap, int i, float f) {
            a(bitmap);
            a(i);
            a(f);
            a(true);
        }

        public void a() {
            if (!this.d) {
                try {
                    if (this.f379a != null && !this.f379a.isRecycled()) {
                        this.f379a.recycle();
                    }
                } catch (Exception e) {
                }
            }
        }

        public Bitmap b() {
            return this.f379a;
        }

        public void a(Bitmap bitmap) {
            this.f379a = bitmap;
        }

        public void a(int i) {
            this.b = i;
        }

        public int c() {
            return this.f379a.getWidth() * this.b;
        }

        public int d() {
            return this.f379a.getHeight() * this.b;
        }

        public void a(boolean z) {
            this.d = z;
        }

        public void a(float f) {
            this.c = f;
        }

        public float e() {
            return this.c;
        }
    }
}
