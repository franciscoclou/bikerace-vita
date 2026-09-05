package android.support.v4.widget;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.v4.view.ag;
import android.support.v4.view.aw;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class DrawerLayout extends ViewGroup {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f59a = {R.attr.layout_gravity};
    private int b;
    private int c;
    private float d;
    private Paint e;
    private final y f;
    private final y g;
    private final d h;
    private final d i;
    private int j;
    private boolean k;
    private boolean l;
    private int m;
    private int n;
    private boolean o;
    private boolean p;
    private b q;
    private float r;
    private float s;
    private Drawable t;
    private Drawable u;

    public DrawerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = -1728053248;
        this.e = new Paint();
        this.l = true;
        float f = getResources().getDisplayMetrics().density;
        this.b = (int) ((64.0f * f) + 0.5f);
        float f2 = f * 400.0f;
        this.h = new d(this, 3);
        this.i = new d(this, 5);
        this.f = y.a(this, 1.0f, this.h);
        this.f.a(1);
        this.f.a(f2);
        this.h.a(this.f);
        this.g = y.a(this, 1.0f, this.i);
        this.g.a(2);
        this.g.a(f2);
        this.i.a(this.g);
        setFocusableInTouchMode(true);
        ag.a(this, new a(this));
        aw.a(this, false);
    }

    public void setScrimColor(int i) {
        this.c = i;
        invalidate();
    }

    public void setDrawerListener(b bVar) {
        this.q = bVar;
    }

    public void setDrawerLockMode(int i) {
        a(i, 3);
        a(i, 5);
    }

    public void a(int i, int i2) {
        int iA = android.support.v4.view.j.a(i2, ag.e(this));
        if (iA == 3) {
            this.m = i;
        } else if (iA == 5) {
            this.n = i;
        }
        if (i != 0) {
            (iA == 3 ? this.f : this.g).e();
        }
        switch (i) {
            case 1:
                View viewA = a(iA);
                if (viewA != null) {
                    i(viewA);
                }
                break;
            case 2:
                View viewA2 = a(iA);
                if (viewA2 != null) {
                    h(viewA2);
                }
                break;
        }
    }

    public int a(View view) {
        int iE = e(view);
        if (iE == 3) {
            return this.m;
        }
        if (iE == 5) {
            return this.n;
        }
        return 0;
    }

    void a(int i, int i2, View view) {
        int i3 = 1;
        int iA = this.f.a();
        int iA2 = this.g.a();
        if (iA != 1 && iA2 != 1) {
            i3 = (iA == 2 || iA2 == 2) ? 2 : 0;
        }
        if (view != null && i2 == 0) {
            c cVar = (c) view.getLayoutParams();
            if (cVar.b == 0.0f) {
                b(view);
            } else if (cVar.b == 1.0f) {
                c(view);
            }
        }
        if (i3 != this.j) {
            this.j = i3;
            if (this.q != null) {
                this.q.a(i3);
            }
        }
    }

    void b(View view) {
        c cVar = (c) view.getLayoutParams();
        if (cVar.d) {
            cVar.d = false;
            if (this.q != null) {
                this.q.b(view);
            }
            sendAccessibilityEvent(32);
        }
    }

    void c(View view) {
        c cVar = (c) view.getLayoutParams();
        if (!cVar.d) {
            cVar.d = true;
            if (this.q != null) {
                this.q.a(view);
            }
            view.sendAccessibilityEvent(32);
        }
    }

    void a(View view, float f) {
        if (this.q != null) {
            this.q.a(view, f);
        }
    }

    void b(View view, float f) {
        c cVar = (c) view.getLayoutParams();
        if (f != cVar.b) {
            cVar.b = f;
            a(view, f);
        }
    }

    float d(View view) {
        return ((c) view.getLayoutParams()).b;
    }

    int e(View view) {
        return android.support.v4.view.j.a(((c) view.getLayoutParams()).f63a, ag.e(this));
    }

    boolean a(View view, int i) {
        return (e(view) & i) == i;
    }

    View a() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (((c) childAt.getLayoutParams()).d) {
                return childAt;
            }
        }
        return null;
    }

    View a(int i) {
        int iA = android.support.v4.view.j.a(i, ag.e(this)) & 7;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if ((e(childAt) & 7) == iA) {
                return childAt;
            }
        }
        return null;
    }

    static String b(int i) {
        if ((i & 3) == 3) {
            return "LEFT";
        }
        if ((i & 5) == 5) {
            return "RIGHT";
        }
        return Integer.toHexString(i);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.l = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.l = true;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0046 A[PHI: r2
      0x0046: PHI (r2v14 int) = (r2v2 int), (r2v0 int) binds: [B:17:0x0044, B:4:0x0019] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3 = 300;
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824 && mode2 == 1073741824) {
            i3 = size2;
        } else if (isInEditMode()) {
            if (mode != Integer.MIN_VALUE && mode == 0) {
                size = 300;
            }
            if (mode2 == Integer.MIN_VALUE || mode2 != 0) {
                i3 = size2;
            }
        } else {
            throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
        }
        setMeasuredDimension(size, i3);
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                c cVar = (c) childAt.getLayoutParams();
                if (f(childAt)) {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec((size - cVar.leftMargin) - cVar.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec((i3 - cVar.topMargin) - cVar.bottomMargin, 1073741824));
                } else if (g(childAt)) {
                    int iE = e(childAt) & 7;
                    if ((0 & iE) != 0) {
                        throw new IllegalStateException("Child drawer has absolute gravity " + b(iE) + " but this DrawerLayout already has a drawer view along that edge");
                    }
                    childAt.measure(getChildMeasureSpec(i, this.b + cVar.leftMargin + cVar.rightMargin, cVar.width), getChildMeasureSpec(i2, cVar.topMargin + cVar.bottomMargin, cVar.height));
                } else {
                    throw new IllegalStateException("Child " + childAt + " at index " + i4 + " does not have a valid layout_gravity - must be Gravity.LEFT, Gravity.RIGHT or Gravity.NO_GRAVITY");
                }
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        float f;
        this.k = true;
        int i6 = i3 - i;
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                c cVar = (c) childAt.getLayoutParams();
                if (f(childAt)) {
                    childAt.layout(cVar.leftMargin, cVar.topMargin, cVar.leftMargin + childAt.getMeasuredWidth(), cVar.topMargin + childAt.getMeasuredHeight());
                } else {
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (a(childAt, 3)) {
                        i5 = ((int) (measuredWidth * cVar.b)) + (-measuredWidth);
                        f = (measuredWidth + i5) / measuredWidth;
                    } else {
                        i5 = i6 - ((int) (measuredWidth * cVar.b));
                        f = (i6 - i5) / measuredWidth;
                    }
                    boolean z2 = f != cVar.b;
                    switch (cVar.f63a & 112) {
                        case 16:
                            int i8 = i4 - i2;
                            int i9 = (i8 - measuredHeight) / 2;
                            if (i9 < cVar.topMargin) {
                                i9 = cVar.topMargin;
                            } else if (i9 + measuredHeight > i8 - cVar.bottomMargin) {
                                i9 = (i8 - cVar.bottomMargin) - measuredHeight;
                            }
                            childAt.layout(i5, i9, measuredWidth + i5, measuredHeight + i9);
                            break;
                        case 80:
                            int i10 = i4 - i2;
                            childAt.layout(i5, (i10 - cVar.bottomMargin) - childAt.getMeasuredHeight(), measuredWidth + i5, i10 - cVar.bottomMargin);
                            break;
                        default:
                            childAt.layout(i5, cVar.topMargin, measuredWidth + i5, measuredHeight + cVar.topMargin);
                            break;
                    }
                    if (z2) {
                        b(childAt, f);
                    }
                    int i11 = cVar.b > 0.0f ? 0 : 4;
                    if (childAt.getVisibility() != i11) {
                        childAt.setVisibility(i11);
                    }
                }
            }
        }
        this.k = false;
        this.l = false;
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (!this.k) {
            super.requestLayout();
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        int childCount = getChildCount();
        float fMax = 0.0f;
        for (int i = 0; i < childCount; i++) {
            fMax = Math.max(fMax, ((c) getChildAt(i).getLayoutParams()).b);
        }
        this.d = fMax;
        if (this.f.a(true) | this.g.a(true)) {
            ag.b(this);
        }
    }

    private static boolean k(View view) {
        Drawable background = view.getBackground();
        return background != null && background.getOpacity() == -1;
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0055  */
    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View view, long j) {
        int left;
        int height = getHeight();
        boolean zF = f(view);
        int i = 0;
        int width = getWidth();
        int iSave = canvas.save();
        if (zF) {
            int childCount = getChildCount();
            int i2 = 0;
            while (i2 < childCount) {
                View childAt = getChildAt(i2);
                if (childAt == view || childAt.getVisibility() != 0 || !k(childAt) || !g(childAt)) {
                    left = width;
                } else if (childAt.getHeight() < height) {
                    left = width;
                } else if (a(childAt, 3)) {
                    int right = childAt.getRight();
                    if (right <= i) {
                        right = i;
                    }
                    i = right;
                    left = width;
                } else {
                    left = childAt.getLeft();
                    if (left >= width) {
                        left = width;
                    }
                }
                i2++;
                width = left;
            }
            canvas.clipRect(i, 0, width, getHeight());
        }
        int i3 = width;
        boolean zDrawChild = super.drawChild(canvas, view, j);
        canvas.restoreToCount(iSave);
        if (this.d > 0.0f && zF) {
            this.e.setColor((((int) (((this.c & (-16777216)) >>> 24) * this.d)) << 24) | (this.c & 16777215));
            canvas.drawRect(i, 0.0f, i3, getHeight(), this.e);
        } else if (this.t != null && a(view, 3)) {
            int intrinsicWidth = this.t.getIntrinsicWidth();
            int right2 = view.getRight();
            float fMax = Math.max(0.0f, Math.min(right2 / this.f.b(), 1.0f));
            this.t.setBounds(right2, view.getTop(), intrinsicWidth + right2, view.getBottom());
            this.t.setAlpha((int) (255.0f * fMax));
            this.t.draw(canvas);
        } else if (this.u != null && a(view, 5)) {
            int intrinsicWidth2 = this.u.getIntrinsicWidth();
            int left2 = view.getLeft();
            float fMax2 = Math.max(0.0f, Math.min((getWidth() - left2) / this.g.b(), 1.0f));
            this.u.setBounds(left2 - intrinsicWidth2, view.getTop(), left2, view.getBottom());
            this.u.setAlpha((int) (255.0f * fMax2));
            this.u.draw(canvas);
        }
        return zDrawChild;
    }

    boolean f(View view) {
        return ((c) view.getLayoutParams()).f63a == 0;
    }

    boolean g(View view) {
        return (android.support.v4.view.j.a(((c) view.getLayoutParams()).f63a, ag.e(view)) & 7) != 0;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:4:0x0016  */
    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        int iA = android.support.v4.view.v.a(motionEvent);
        boolean zA = this.f.a(motionEvent) | this.g.a(motionEvent);
        switch (iA) {
            case 0:
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                this.r = x;
                this.s = y;
                z = this.d > 0.0f && f(this.f.d((int) x, (int) y));
                this.o = false;
                this.p = false;
                break;
            case 1:
            case 3:
                a(true);
                this.o = false;
                this.p = false;
                z = false;
                break;
            case 2:
                if (!this.f.d(3)) {
                    z = false;
                } else {
                    this.h.a();
                    this.i.a();
                    z = false;
                }
                break;
            default:
                z = false;
                break;
        }
        return zA || z || e() || this.p;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        View viewA;
        this.f.b(motionEvent);
        this.g.b(motionEvent);
        switch (motionEvent.getAction() & 255) {
            case 0:
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                this.r = x;
                this.s = y;
                this.o = false;
                this.p = false;
                return true;
            case 1:
                float x2 = motionEvent.getX();
                float y2 = motionEvent.getY();
                View viewD = this.f.d((int) x2, (int) y2);
                if (viewD == null || !f(viewD)) {
                    z = true;
                } else {
                    float f = x2 - this.r;
                    float f2 = y2 - this.s;
                    int iD = this.f.d();
                    z = (f * f) + (f2 * f2) >= ((float) (iD * iD)) || (viewA = a()) == null || a(viewA) == 2;
                }
                a(z);
                this.o = false;
                return true;
            case 2:
            default:
                return true;
            case 3:
                a(true);
                this.o = false;
                this.p = false;
                return true;
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        this.o = z;
        if (z) {
            a(true);
        }
    }

    public void b() {
        a(false);
    }

    void a(boolean z) {
        int childCount = getChildCount();
        boolean zA = false;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            c cVar = (c) childAt.getLayoutParams();
            if (g(childAt) && (!z || cVar.c)) {
                int width = childAt.getWidth();
                if (a(childAt, 3)) {
                    zA |= this.f.a(childAt, -width, childAt.getTop());
                } else {
                    zA |= this.g.a(childAt, getWidth(), childAt.getTop());
                }
                cVar.c = false;
            }
        }
        this.h.a();
        this.i.a();
        if (zA) {
            invalidate();
        }
    }

    public void h(View view) {
        if (!g(view)) {
            throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
        }
        if (this.l) {
            c cVar = (c) view.getLayoutParams();
            cVar.b = 1.0f;
            cVar.d = true;
        } else if (a(view, 3)) {
            this.f.a(view, 0, view.getTop());
        } else {
            this.g.a(view, getWidth() - view.getWidth(), view.getTop());
        }
        invalidate();
    }

    public void i(View view) {
        if (!g(view)) {
            throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
        }
        if (this.l) {
            c cVar = (c) view.getLayoutParams();
            cVar.b = 0.0f;
            cVar.d = false;
        } else if (a(view, 3)) {
            this.f.a(view, -view.getWidth(), view.getTop());
        } else {
            this.g.a(view, getWidth(), view.getTop());
        }
        invalidate();
    }

    public boolean j(View view) {
        if (g(view)) {
            return ((c) view.getLayoutParams()).b > 0.0f;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    private boolean e() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (((c) getChildAt(i).getLayoutParams()).c) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new c(-1, -1);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof c) {
            return new c((c) layoutParams);
        }
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new c((ViewGroup.MarginLayoutParams) layoutParams) : new c(layoutParams);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof c) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new c(getContext(), attributeSet);
    }

    private boolean f() {
        return g() != null;
    }

    private View g() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (g(childAt) && j(childAt)) {
                return childAt;
            }
        }
        return null;
    }

    void c() {
        if (!this.p) {
            long jUptimeMillis = SystemClock.uptimeMillis();
            MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                getChildAt(i).dispatchTouchEvent(motionEventObtain);
            }
            motionEventObtain.recycle();
            this.p = true;
        }
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || !f()) {
            return super.onKeyDown(i, keyEvent);
        }
        android.support.v4.view.o.b(keyEvent);
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 4) {
            View viewG = g();
            if (viewG != null && a(viewG) == 0) {
                b();
            }
            return viewG != null;
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        View viewA;
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.f60a != 0 && (viewA = a(savedState.f60a)) != null) {
            h(viewA);
        }
        a(savedState.b, 3);
        a(savedState.c, 5);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (g(childAt)) {
                c cVar = (c) childAt.getLayoutParams();
                if (cVar.d) {
                    savedState.f60a = cVar.f63a;
                    break;
                }
            }
        }
        savedState.b = this.m;
        savedState.c = this.n;
        return savedState;
    }

    public class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: android.support.v4.widget.DrawerLayout.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f60a;
        int b;
        int c;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.f60a = 0;
            this.b = 0;
            this.c = 0;
            this.f60a = parcel.readInt();
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
            this.f60a = 0;
            this.b = 0;
            this.c = 0;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f60a);
        }
    }
}
