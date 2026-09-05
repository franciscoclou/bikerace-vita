package android.support.v4.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* JADX INFO: compiled from: SlidingPaneLayout.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class s extends ViewGroup.MarginLayoutParams {
    private static final int[] e = {R.attr.layout_weight};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f70a;
    boolean b;
    boolean c;
    Paint d;

    public s() {
        super(-1, -1);
        this.f70a = 0.0f;
    }

    public s(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
        this.f70a = 0.0f;
    }

    public s(ViewGroup.MarginLayoutParams marginLayoutParams) {
        super(marginLayoutParams);
        this.f70a = 0.0f;
    }

    public s(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f70a = 0.0f;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, e);
        this.f70a = typedArrayObtainStyledAttributes.getFloat(0, 0.0f);
        typedArrayObtainStyledAttributes.recycle();
    }
}
