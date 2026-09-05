package com.topfreegames.bikerace.activities;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Display;
import java.util.HashMap;

/* JADX INFO: compiled from: BackgroundManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1055a;
    private int b;
    private Context c;
    private HashMap<b, Integer> d = new HashMap<>();
    private android.support.v4.c.e<b, Bitmap> e;

    public a(Activity activity) {
        this.f1055a = -1;
        this.b = -1;
        this.c = null;
        this.e = null;
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        this.f1055a = defaultDisplay.getWidth();
        this.b = defaultDisplay.getHeight();
        this.c = activity.getApplicationContext();
        this.e = new android.support.v4.c.e<b, Bitmap>((((ActivityManager) activity.getSystemService("activity")).getMemoryClass() * 1048576) / 8) { // from class: com.topfreegames.bikerace.activities.a.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.support.v4.c.e
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public int b(b bVar, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
        this.d.put(b.START, 2130837885);
        this.d.put(b.DEFAULT, 2130837736);
        this.d.put(b.HELP, 2130837757);
        this.d.put(b.MULTIPLAYER, 2130837834);
        this.d.put(b.HOLIDAY, 2130837737);
        this.d.put(b.HALLOWEEN, 2130837745);
        this.d.put(b.THANKSGIVING, 2130837738);
        this.d.put(b.EASTER, 2130837721);
    }

    private Bitmap a(int i) {
        return com.topfreegames.engine.b.a.a(this.c.getResources(), i, this.f1055a, this.b);
    }

    public BitmapDrawable a(b bVar) {
        if (bVar == null) {
            return null;
        }
        Bitmap bitmapA = this.e.a(bVar);
        if (bitmapA == null && (bitmapA = a(this.d.get(bVar).intValue())) != null) {
            this.e.a(bVar, bitmapA);
        }
        Bitmap bitmap = bitmapA;
        if (bitmap == null) {
            return null;
        }
        return new BitmapDrawable(bitmap);
    }

    public void a() {
        this.e.a();
        System.gc();
    }
}
