package android.support.v4.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.ag;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import java.util.ArrayList;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class SlidingPaneLayout extends ViewGroup {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final u f61a;
    private int b;
    private int c;
    private Drawable d;
    private final int e;
    private boolean f;
    private View g;
    private float h;
    private float i;
    private int j;
    private boolean k;
    private int l;
    private float m;
    private float n;
    private t o;
    private final y p;
    private boolean q;
    private boolean r;
    private final Rect s;
    private final ArrayList<q> t;

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 17) {
            f61a = new x();
        } else if (i >= 16) {
            f61a = new w();
        } else {
            f61a = new v();
        }
    }

    public SlidingPaneLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = -858993460;
        this.r = true;
        this.s = new Rect();
        this.t = new ArrayList<>();
        float f = context.getResources().getDisplayMetrics().density;
        this.e = (int) ((32.0f * f) + 0.5f);
        ViewConfiguration.get(context);
        setWillNotDraw(false);
        ag.a(this, new p(this));
        ag.b(this, 1);
        this.p = y.a(this, 0.5f, new r(this));
        this.p.a(1);
        this.p.a(f * 400.0f);
    }

    public void setParallaxDistance(int i) {
        this.l = i;
        requestLayout();
    }

    public int getParallaxDistance() {
        return this.l;
    }

    public void setSliderFadeColor(int i) {
        this.b = i;
    }

    public int getSliderFadeColor() {
        return this.b;
    }

    public void setCoveredFadeColor(int i) {
        this.c = i;
    }

    public int getCoveredFadeColor() {
        return this.c;
    }

    public void setPanelSlideListener(t tVar) {
        this.o = tVar;
    }

    void a(View view) {
        if (this.o != null) {
            this.o.a(view, this.h);
        }
    }

    void b(View view) {
        if (this.o != null) {
            this.o.a(view);
        }
        sendAccessibilityEvent(32);
    }

    void c(View view) {
        if (this.o != null) {
            this.o.b(view);
        }
        sendAccessibilityEvent(32);
    }

    void d(View view) {
        int bottom;
        int top;
        int right;
        int left;
        int i;
        int paddingLeft = getPaddingLeft();
        int width = getWidth() - getPaddingRight();
        int paddingTop = getPaddingTop();
        int height = getHeight() - getPaddingBottom();
        if (view != null && f(view)) {
            left = view.getLeft();
            right = view.getRight();
            top = view.getTop();
            bottom = view.getBottom();
        } else {
            bottom = 0;
            top = 0;
            right = 0;
            left = 0;
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt != view) {
                int iMax = Math.max(paddingLeft, childAt.getLeft());
                int iMax2 = Math.max(paddingTop, childAt.getTop());
                int iMin = Math.min(width, childAt.getRight());
                int iMin2 = Math.min(height, childAt.getBottom());
                if (iMax >= left && iMax2 >= top && iMin <= right && iMin2 <= bottom) {
                    i = 4;
                } else {
                    i = 0;
                }
                childAt.setVisibility(i);
            } else {
                return;
            }
        }
    }

    void a() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 4) {
                childAt.setVisibility(0);
            }
        }
    }

    private static boolean f(View view) {
        Drawable background;
        if (ag.g(view)) {
            return true;
        }
        return Build.VERSION.SDK_INT < 18 && (background = view.getBackground()) != null && background.getOpacity() == -1;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.r = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.r = true;
        int size = this.t.size();
        for (int i = 0; i < size; i++) {
            this.t.get(i).run();
        }
        this.t.clear();
    }

    /* JADX WARN: Code duplicated, block: B:124:0x0247  */
    /* JADX WARN: Code duplicated, block: B:39:0x00c9 A[PHI: r2
      0x00c9: PHI (r2v12 float) = (r2v11 float), (r2v14 float) binds: [B:35:0x00bb, B:37:0x00c2] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:41:0x00d3  */
    /* JADX WARN: Code duplicated, block: B:44:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:51:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:54:0x0107  */
    /* JADX WARN: Code duplicated, block: B:56:0x0110  */
    /* JADX WARN: Code duplicated, block: B:58:0x0115  */
    /* JADX WARN: Code duplicated, block: B:59:0x011e  */
    /* JADX WARN: Code duplicated, block: B:60:0x0127  */
    /* JADX WARN: Code duplicated, block: B:62:0x012c  */
    /* JADX WARN: Code duplicated, block: B:63:0x0133  */
    /* JADX WARN: Code duplicated, block: B:64:0x013c  */
    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int paddingTop;
        int paddingTop2;
        int iMakeMeasureSpec;
        int iMakeMeasureSpec2;
        int i6;
        int iMakeMeasureSpec3;
        int iMakeMeasureSpec4;
        int measuredHeight;
        int i7;
        boolean z;
        int i8;
        int i9;
        boolean z2;
        float f;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            if (isInEditMode()) {
                if (mode != Integer.MIN_VALUE && mode == 0) {
                    i3 = mode2;
                    i4 = 300;
                    i5 = size2;
                } else {
                    i3 = mode2;
                    i4 = size;
                    i5 = size2;
                }
            } else {
                throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
            }
        } else if (mode2 != 0) {
            i3 = mode2;
            i4 = size;
            i5 = size2;
        } else if (isInEditMode()) {
            if (mode2 == 0) {
                i3 = Integer.MIN_VALUE;
                i4 = size;
                i5 = 300;
            } else {
                i3 = mode2;
                i4 = size;
                i5 = size2;
            }
        } else {
            throw new IllegalStateException("Height must not be UNSPECIFIED");
        }
        switch (i3) {
            case Integer.MIN_VALUE:
                paddingTop = 0;
                paddingTop2 = (i5 - getPaddingTop()) - getPaddingBottom();
                break;
            case 1073741824:
                paddingTop = (i5 - getPaddingTop()) - getPaddingBottom();
                paddingTop2 = paddingTop;
                break;
            default:
                paddingTop = 0;
                paddingTop2 = -1;
                break;
        }
        boolean z3 = false;
        int paddingLeft = (i4 - getPaddingLeft()) - getPaddingRight();
        int childCount = getChildCount();
        if (childCount > 2) {
            Log.e("SlidingPaneLayout", "onMeasure: More than two child views are not supported.");
        }
        this.g = null;
        int i10 = 0;
        int iMin = paddingTop;
        float f2 = 0.0f;
        while (i10 < childCount) {
            View childAt = getChildAt(i10);
            s sVar = (s) childAt.getLayoutParams();
            if (childAt.getVisibility() == 8) {
                sVar.c = false;
                i8 = paddingLeft;
                f = f2;
                i9 = iMin;
                z2 = z3;
            } else if (sVar.f70a > 0.0f) {
                f2 += sVar.f70a;
                if (sVar.width == 0) {
                    i8 = paddingLeft;
                    f = f2;
                    i9 = iMin;
                    z2 = z3;
                } else {
                    i6 = sVar.leftMargin + sVar.rightMargin;
                    if (sVar.width == -2) {
                        iMakeMeasureSpec3 = View.MeasureSpec.makeMeasureSpec(i4 - i6, Integer.MIN_VALUE);
                    } else if (sVar.width == -1) {
                        iMakeMeasureSpec3 = View.MeasureSpec.makeMeasureSpec(i4 - i6, 1073741824);
                    } else {
                        iMakeMeasureSpec3 = View.MeasureSpec.makeMeasureSpec(sVar.width, 1073741824);
                    }
                    if (sVar.height == -2) {
                        iMakeMeasureSpec4 = View.MeasureSpec.makeMeasureSpec(paddingTop2, Integer.MIN_VALUE);
                    } else if (sVar.height == -1) {
                        iMakeMeasureSpec4 = View.MeasureSpec.makeMeasureSpec(paddingTop2, 1073741824);
                    } else {
                        iMakeMeasureSpec4 = View.MeasureSpec.makeMeasureSpec(sVar.height, 1073741824);
                    }
                    childAt.measure(iMakeMeasureSpec3, iMakeMeasureSpec4);
                    int measuredWidth = childAt.getMeasuredWidth();
                    measuredHeight = childAt.getMeasuredHeight();
                    if (i3 == Integer.MIN_VALUE && measuredHeight > iMin) {
                        iMin = Math.min(measuredHeight, paddingTop2);
                    }
                    i7 = paddingLeft - measuredWidth;
                    if (i7 < 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    sVar.b = z;
                    boolean z4 = z | z3;
                    if (sVar.b) {
                        this.g = childAt;
                    }
                    i8 = i7;
                    i9 = iMin;
                    float f3 = f2;
                    z2 = z4;
                    f = f3;
                }
            } else {
                i6 = sVar.leftMargin + sVar.rightMargin;
                if (sVar.width == -2) {
                    iMakeMeasureSpec3 = View.MeasureSpec.makeMeasureSpec(i4 - i6, Integer.MIN_VALUE);
                } else if (sVar.width == -1) {
                    iMakeMeasureSpec3 = View.MeasureSpec.makeMeasureSpec(i4 - i6, 1073741824);
                } else {
                    iMakeMeasureSpec3 = View.MeasureSpec.makeMeasureSpec(sVar.width, 1073741824);
                }
                if (sVar.height == -2) {
                    iMakeMeasureSpec4 = View.MeasureSpec.makeMeasureSpec(paddingTop2, Integer.MIN_VALUE);
                } else if (sVar.height == -1) {
                    iMakeMeasureSpec4 = View.MeasureSpec.makeMeasureSpec(paddingTop2, 1073741824);
                } else {
                    iMakeMeasureSpec4 = View.MeasureSpec.makeMeasureSpec(sVar.height, 1073741824);
                }
                childAt.measure(iMakeMeasureSpec3, iMakeMeasureSpec4);
                int measuredWidth2 = childAt.getMeasuredWidth();
                measuredHeight = childAt.getMeasuredHeight();
                if (i3 == Integer.MIN_VALUE) {
                    iMin = Math.min(measuredHeight, paddingTop2);
                }
                i7 = paddingLeft - measuredWidth2;
                if (i7 < 0) {
                    z = true;
                } else {
                    z = false;
                }
                sVar.b = z;
                boolean z5 = z | z3;
                if (sVar.b) {
                    this.g = childAt;
                }
                i8 = i7;
                i9 = iMin;
                float f4 = f2;
                z2 = z5;
                f = f4;
            }
            i10++;
            z3 = z2;
            iMin = i9;
            f2 = f;
            paddingLeft = i8;
        }
        if (z3 || f2 > 0.0f) {
            int i11 = i4 - this.e;
            for (int i12 = 0; i12 < childCount; i12++) {
                View childAt2 = getChildAt(i12);
                if (childAt2.getVisibility() != 8) {
                    s sVar2 = (s) childAt2.getLayoutParams();
                    if (childAt2.getVisibility() != 8) {
                        boolean z6 = sVar2.width == 0 && sVar2.f70a > 0.0f;
                        int measuredWidth3 = z6 ? 0 : childAt2.getMeasuredWidth();
                        if (!z3 || childAt2 == this.g) {
                            if (sVar2.f70a > 0.0f) {
                                if (sVar2.width == 0) {
                                    if (sVar2.height == -2) {
                                        iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(paddingTop2, Integer.MIN_VALUE);
                                    } else if (sVar2.height == -1) {
                                        iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(paddingTop2, 1073741824);
                                    } else {
                                        iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(sVar2.height, 1073741824);
                                    }
                                } else {
                                    iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(childAt2.getMeasuredHeight(), 1073741824);
                                }
                                if (z3) {
                                    int i13 = i4 - (sVar2.rightMargin + sVar2.leftMargin);
                                    int iMakeMeasureSpec5 = View.MeasureSpec.makeMeasureSpec(i13, 1073741824);
                                    if (measuredWidth3 != i13) {
                                        childAt2.measure(iMakeMeasureSpec5, iMakeMeasureSpec);
                                    }
                                } else {
                                    childAt2.measure(View.MeasureSpec.makeMeasureSpec(((int) ((sVar2.f70a * Math.max(0, paddingLeft)) / f2)) + measuredWidth3, 1073741824), iMakeMeasureSpec);
                                }
                            }
                        } else if (sVar2.width < 0 && (measuredWidth3 > i11 || sVar2.f70a > 0.0f)) {
                            if (z6) {
                                if (sVar2.height == -2) {
                                    iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(paddingTop2, Integer.MIN_VALUE);
                                } else if (sVar2.height == -1) {
                                    iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(paddingTop2, 1073741824);
                                } else {
                                    iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(sVar2.height, 1073741824);
                                }
                            } else {
                                iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(childAt2.getMeasuredHeight(), 1073741824);
                            }
                            childAt2.measure(View.MeasureSpec.makeMeasureSpec(i11, 1073741824), iMakeMeasureSpec2);
                        }
                    }
                }
            }
        }
        setMeasuredDimension(i4, iMin);
        this.f = z3;
        if (this.p.a() != 0 && !z3) {
            this.p.f();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7 = i3 - i;
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int childCount = getChildCount();
        if (this.r) {
            this.h = (this.f && this.q) ? 1.0f : 0.0f;
        }
        int i8 = 0;
        int i9 = paddingLeft;
        while (i8 < childCount) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() == 8) {
                i6 = i9;
            } else {
                s sVar = (s) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                if (sVar.b) {
                    int iMin = (Math.min(paddingLeft, (i7 - paddingRight) - this.e) - i9) - (sVar.leftMargin + sVar.rightMargin);
                    this.j = iMin;
                    sVar.c = ((sVar.leftMargin + i9) + iMin) + (measuredWidth / 2) > i7 - paddingRight;
                    i6 = sVar.leftMargin + ((int) (iMin * this.h)) + i9;
                    i5 = 0;
                } else if (!this.f || this.l == 0) {
                    i5 = 0;
                    i6 = paddingLeft;
                } else {
                    i5 = (int) ((1.0f - this.h) * this.l);
                    i6 = paddingLeft;
                }
                int i10 = i6 - i5;
                childAt.layout(i10, paddingTop, i10 + measuredWidth, childAt.getMeasuredHeight() + paddingTop);
                paddingLeft += childAt.getWidth();
            }
            i8++;
            i9 = i6;
        }
        if (this.r) {
            if (this.f) {
                if (this.l != 0) {
                    a(this.h);
                }
                if (((s) this.g.getLayoutParams()).c) {
                    a(this.g, this.h, this.b);
                }
            } else {
                for (int i11 = 0; i11 < childCount; i11++) {
                    a(getChildAt(i11), 0.0f, this.b);
                }
            }
            d(this.g);
        }
        this.r = false;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3) {
            this.r = true;
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        super.requestChildFocus(view, view2);
        if (!isInTouchMode() && !this.f) {
            this.q = view == this.g;
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        View childAt;
        int iA = android.support.v4.view.v.a(motionEvent);
        if (!this.f && iA == 0 && getChildCount() > 1 && (childAt = getChildAt(1)) != null) {
            this.q = !this.p.b(childAt, (int) motionEvent.getX(), (int) motionEvent.getY());
        }
        if (!this.f || (this.k && iA != 0)) {
            this.p.e();
            return super.onInterceptTouchEvent(motionEvent);
        }
        if (iA == 3 || iA == 1) {
            this.p.e();
            return false;
        }
        switch (iA) {
            case 0:
                this.k = false;
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                this.m = x;
                this.n = y;
                z = this.p.b(this.g, (int) x, (int) y) && e(this.g);
                break;
            case 1:
            default:
                z = false;
                break;
            case 2:
                float x2 = motionEvent.getX();
                float y2 = motionEvent.getY();
                float fAbs = Math.abs(x2 - this.m);
                float fAbs2 = Math.abs(y2 - this.n);
                if (fAbs > this.p.d() && fAbs2 > fAbs) {
                    this.p.e();
                    this.k = true;
                    return false;
                }
                z = false;
                break;
                break;
        }
        return this.p.a(motionEvent) || z;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f) {
            return super.onTouchEvent(motionEvent);
        }
        this.p.b(motionEvent);
        switch (motionEvent.getAction() & 255) {
            case 0:
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                this.m = x;
                this.n = y;
                return true;
            case 1:
                if (!e(this.g)) {
                    return true;
                }
                float x2 = motionEvent.getX();
                float y2 = motionEvent.getY();
                float f = x2 - this.m;
                float f2 = y2 - this.n;
                int iD = this.p.d();
                if ((f * f) + (f2 * f2) >= iD * iD || !this.p.b(this.g, (int) x2, (int) y2)) {
                    return true;
                }
                a(this.g, 0);
                return true;
            default:
                return true;
        }
    }

    private boolean a(View view, int i) {
        if (!this.r && !a(0.0f, i)) {
            return false;
        }
        this.q = false;
        return true;
    }

    private boolean b(View view, int i) {
        if (!this.r && !a(1.0f, i)) {
            return false;
        }
        this.q = true;
        return true;
    }

    public boolean b() {
        return b(this.g, 0);
    }

    public boolean c() {
        return a(this.g, 0);
    }

    public boolean d() {
        return !this.f || this.h == 1.0f;
    }

    public boolean e() {
        return this.f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        s sVar = (s) this.g.getLayoutParams();
        this.h = (i - (getPaddingLeft() + sVar.leftMargin)) / this.j;
        if (this.l != 0) {
            a(this.h);
        }
        if (sVar.c) {
            a(this.g, this.h, this.b);
        }
        a(this.g);
    }

    private void a(View view, float f, int i) {
        s sVar = (s) view.getLayoutParams();
        if (f > 0.0f && i != 0) {
            int i2 = (((int) ((((-16777216) & i) >>> 24) * f)) << 24) | (16777215 & i);
            if (sVar.d == null) {
                sVar.d = new Paint();
            }
            sVar.d.setColorFilter(new PorterDuffColorFilter(i2, PorterDuff.Mode.SRC_OVER));
            if (ag.d(view) != 2) {
                ag.a(view, 2, sVar.d);
            }
            g(view);
            return;
        }
        if (ag.d(view) != 0) {
            if (sVar.d != null) {
                sVar.d.setColorFilter(null);
            }
            q qVar = new q(this, view);
            this.t.add(qVar);
            ag.a(this, qVar);
        }
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View view, long j) {
        boolean zDrawChild;
        s sVar = (s) view.getLayoutParams();
        int iSave = canvas.save(2);
        if (this.f && !sVar.b && this.g != null) {
            canvas.getClipBounds(this.s);
            this.s.right = Math.min(this.s.right, this.g.getLeft());
            canvas.clipRect(this.s);
        }
        if (Build.VERSION.SDK_INT >= 11) {
            zDrawChild = super.drawChild(canvas, view, j);
        } else if (sVar.c && this.h > 0.0f) {
            if (!view.isDrawingCacheEnabled()) {
                view.setDrawingCacheEnabled(true);
            }
            Bitmap drawingCache = view.getDrawingCache();
            if (drawingCache != null) {
                canvas.drawBitmap(drawingCache, view.getLeft(), view.getTop(), sVar.d);
                zDrawChild = false;
            } else {
                Log.e("SlidingPaneLayout", "drawChild: child view " + view + " returned null drawing cache");
                zDrawChild = super.drawChild(canvas, view, j);
            }
        } else {
            if (view.isDrawingCacheEnabled()) {
                view.setDrawingCacheEnabled(false);
            }
            zDrawChild = super.drawChild(canvas, view, j);
        }
        canvas.restoreToCount(iSave);
        return zDrawChild;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(View view) {
        f61a.a(this, view);
    }

    boolean a(float f, int i) {
        if (!this.f) {
            return false;
        }
        if (!this.p.a(this.g, (int) (((s) this.g.getLayoutParams()).leftMargin + getPaddingLeft() + (this.j * f)), this.g.getTop())) {
            return false;
        }
        a();
        ag.b(this);
        return true;
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.p.a(true)) {
            if (!this.f) {
                this.p.f();
            } else {
                ag.b(this);
            }
        }
    }

    public void setShadowDrawable(Drawable drawable) {
        this.d = drawable;
    }

    public void setShadowResource(int i) {
        setShadowDrawable(getResources().getDrawable(i));
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        View childAt = getChildCount() > 1 ? getChildAt(1) : null;
        if (childAt != null && this.d != null) {
            int intrinsicWidth = this.d.getIntrinsicWidth();
            int left = childAt.getLeft();
            this.d.setBounds(left - intrinsicWidth, childAt.getTop(), left, childAt.getBottom());
            this.d.draw(canvas);
        }
    }

    private void a(float f) {
        s sVar = (s) this.g.getLayoutParams();
        boolean z = sVar.c && sVar.leftMargin <= 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt != this.g) {
                int i2 = (int) ((1.0f - this.i) * this.l);
                this.i = f;
                childAt.offsetLeftAndRight(i2 - ((int) ((1.0f - f) * this.l)));
                if (z) {
                    a(childAt, 1.0f - this.i, this.c);
                }
            }
        }
    }

    boolean e(View view) {
        if (view == null) {
            return false;
        }
        return this.f && ((s) view.getLayoutParams()).c && this.h > 0.0f;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new s();
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new s((ViewGroup.MarginLayoutParams) layoutParams) : new s(layoutParams);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof s) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new s(getContext(), attributeSet);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f62a = e() ? d() : this.q;
        return savedState;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.f62a) {
            b();
        } else {
            c();
        }
        this.q = savedState.f62a;
    }

    class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: android.support.v4.widget.SlidingPaneLayout.SavedState.1
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
        boolean f62a;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f62a = parcel.readInt() != 0;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f62a ? 1 : 0);
        }
    }
}
