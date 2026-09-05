package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* JADX INFO: compiled from: DrawerLayout.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c extends ViewGroup.MarginLayoutParams {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f63a;
    float b;
    boolean c;
    boolean d;

    public c(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f63a = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, DrawerLayout.f59a);
        this.f63a = typedArrayObtainStyledAttributes.getInt(0, 0);
        typedArrayObtainStyledAttributes.recycle();
    }

    public c(int i, int i2) {
        super(i, i2);
        this.f63a = 0;
    }

    public c(c cVar) {
        super((ViewGroup.MarginLayoutParams) cVar);
        this.f63a = 0;
        this.f63a = cVar.f63a;
    }

    public c(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
        this.f63a = 0;
    }

    public c(ViewGroup.MarginLayoutParams marginLayoutParams) {
        super(marginLayoutParams);
        this.f63a = 0;
    }
}
