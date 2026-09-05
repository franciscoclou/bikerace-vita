package android.support.v4.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* JADX INFO: compiled from: ViewPager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class bf extends ViewGroup.LayoutParams {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f51a;
    public int b;
    float c;
    boolean d;
    int e;
    int f;

    public bf() {
        super(-1, -1);
        this.c = 0.0f;
    }

    public bf(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = 0.0f;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.f36a);
        this.b = typedArrayObtainStyledAttributes.getInteger(0, 48);
        typedArrayObtainStyledAttributes.recycle();
    }
}
