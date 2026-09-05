package android.support.v4.view;

import android.R;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import com.facebook.widget.PlacePickerFragment;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ViewPager extends ViewGroup {
    private boolean A;
    private boolean B;
    private int C;
    private int D;
    private int E;
    private float F;
    private float G;
    private float H;
    private float I;
    private int J;
    private VelocityTracker K;
    private int L;
    private int M;
    private int N;
    private int O;
    private boolean P;
    private android.support.v4.widget.e Q;
    private android.support.v4.widget.e R;
    private boolean S;
    private boolean T;
    private boolean U;
    private int V;
    private bh W;
    private bh Z;
    private bg aa;
    private bi ab;
    private Method ac;
    private int ad;
    private ArrayList<View> ae;
    private final Runnable ag;
    private int ah;
    private int b;
    private final ArrayList<be> e;
    private final be f;
    private final Rect g;
    private aa h;
    private int i;
    private int j;
    private Parcelable k;
    private ClassLoader l;
    private Scroller m;
    private bj n;
    private int o;
    private Drawable p;
    private int q;
    private int r;
    private float s;
    private float t;
    private int u;
    private int v;
    private boolean w;
    private boolean x;
    private boolean y;
    private int z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f36a = {R.attr.layout_gravity};
    private static final Comparator<be> c = new Comparator<be>() { // from class: android.support.v4.view.ViewPager.1
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(be beVar, be beVar2) {
            return beVar.b - beVar2.b;
        }
    };
    private static final Interpolator d = new Interpolator() { // from class: android.support.v4.view.ViewPager.2
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    };
    private static final bk af = new bk();

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        removeCallbacks(this.ag);
        super.onDetachedFromWindow();
    }

    private void setScrollState(int i) {
        if (this.ah != i) {
            this.ah = i;
            if (this.ab != null) {
                b(i != 0);
            }
            if (this.W != null) {
                this.W.b(i);
            }
        }
    }

    public void setAdapter(aa aaVar) {
        if (this.h != null) {
            this.h.b(this.n);
            this.h.a((ViewGroup) this);
            for (int i = 0; i < this.e.size(); i++) {
                be beVar = this.e.get(i);
                this.h.a((ViewGroup) this, beVar.b, beVar.f50a);
            }
            this.h.b((ViewGroup) this);
            this.e.clear();
            f();
            this.i = 0;
            scrollTo(0, 0);
        }
        aa aaVar2 = this.h;
        this.h = aaVar;
        this.b = 0;
        if (this.h != null) {
            if (this.n == null) {
                this.n = new bj(this);
            }
            this.h.a((DataSetObserver) this.n);
            this.y = false;
            boolean z = this.S;
            this.S = true;
            this.b = this.h.a();
            if (this.j >= 0) {
                this.h.a(this.k, this.l);
                a(this.j, false, true);
                this.j = -1;
                this.k = null;
                this.l = null;
            } else if (!z) {
                b();
            } else {
                requestLayout();
            }
        }
        if (this.aa != null && aaVar2 != aaVar) {
            this.aa.a(aaVar2, aaVar);
        }
    }

    private void f() {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < getChildCount()) {
                if (!((bf) getChildAt(i2).getLayoutParams()).f51a) {
                    removeViewAt(i2);
                    i2--;
                }
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    public aa getAdapter() {
        return this.h;
    }

    void setOnAdapterChangeListener(bg bgVar) {
        this.aa = bgVar;
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    public void setCurrentItem(int i) {
        this.y = false;
        a(i, !this.S, false);
    }

    public void a(int i, boolean z) {
        this.y = false;
        a(i, z, false);
    }

    public int getCurrentItem() {
        return this.i;
    }

    void a(int i, boolean z, boolean z2) {
        a(i, z, z2, 0);
    }

    void a(int i, boolean z, boolean z2, int i2) {
        if (this.h == null || this.h.a() <= 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (!z2 && this.i == i && this.e.size() != 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (i < 0) {
            i = 0;
        } else if (i >= this.h.a()) {
            i = this.h.a() - 1;
        }
        int i3 = this.z;
        if (i > this.i + i3 || i < this.i - i3) {
            for (int i4 = 0; i4 < this.e.size(); i4++) {
                this.e.get(i4).c = true;
            }
        }
        boolean z3 = this.i != i;
        if (this.S) {
            this.i = i;
            if (z3 && this.W != null) {
                this.W.a(i);
            }
            if (z3 && this.Z != null) {
                this.Z.a(i);
            }
            requestLayout();
            return;
        }
        a(i);
        a(i, z, i2, z3);
    }

    private void a(int i, boolean z, int i2, boolean z2) {
        int iMax;
        be beVarB = b(i);
        if (beVarB != null) {
            iMax = (int) (Math.max(this.s, Math.min(beVarB.e, this.t)) * getClientWidth());
        } else {
            iMax = 0;
        }
        if (z) {
            a(iMax, 0, i2);
            if (z2 && this.W != null) {
                this.W.a(i);
            }
            if (z2 && this.Z != null) {
                this.Z.a(i);
                return;
            }
            return;
        }
        if (z2 && this.W != null) {
            this.W.a(i);
        }
        if (z2 && this.Z != null) {
            this.Z.a(i);
        }
        a(false);
        scrollTo(iMax, 0);
        d(iMax);
    }

    public void setOnPageChangeListener(bh bhVar) {
        this.W = bhVar;
    }

    void setChildrenDrawingOrderEnabledCompat(boolean z) {
        if (Build.VERSION.SDK_INT >= 7) {
            if (this.ac == null) {
                try {
                    this.ac = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", Boolean.TYPE);
                } catch (NoSuchMethodException e) {
                    Log.e("ViewPager", "Can't find setChildrenDrawingOrderEnabled", e);
                }
            }
            try {
                this.ac.invoke(this, Boolean.valueOf(z));
            } catch (Exception e2) {
                Log.e("ViewPager", "Error changing children drawing order", e2);
            }
        }
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i, int i2) {
        if (this.ad == 2) {
            i2 = (i - 1) - i2;
        }
        return ((bf) this.ae.get(i2).getLayoutParams()).f;
    }

    public int getOffscreenPageLimit() {
        return this.z;
    }

    public void setOffscreenPageLimit(int i) {
        if (i < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + i + " too small; defaulting to 1");
            i = 1;
        }
        if (i != this.z) {
            this.z = i;
            b();
        }
    }

    public void setPageMargin(int i) {
        int i2 = this.o;
        this.o = i;
        int width = getWidth();
        a(width, width, i, i2);
        requestLayout();
    }

    public int getPageMargin() {
        return this.o;
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.p = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    public void setPageMarginDrawable(int i) {
        setPageMarginDrawable(getContext().getResources().getDrawable(i));
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.p;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.p;
        if (drawable != null && drawable.isStateful()) {
            drawable.setState(getDrawableState());
        }
    }

    float a(float f) {
        return (float) Math.sin((float) (((double) (f - 0.5f)) * 0.4712389167638204d));
    }

    void a(int i, int i2, int i3) {
        int iAbs;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int i4 = i - scrollX;
        int i5 = i2 - scrollY;
        if (i4 == 0 && i5 == 0) {
            a(false);
            b();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientWidth = getClientWidth();
        int i6 = clientWidth / 2;
        float fA = (i6 * a(Math.min(1.0f, (Math.abs(i4) * 1.0f) / clientWidth))) + i6;
        int iAbs2 = Math.abs(i3);
        if (iAbs2 > 0) {
            iAbs = Math.round(1000.0f * Math.abs(fA / iAbs2)) * 4;
        } else {
            iAbs = (int) (((Math.abs(i4) / ((clientWidth * this.h.a(this.i)) + this.o)) + 1.0f) * 100.0f);
        }
        this.m.startScroll(scrollX, scrollY, i4, i5, Math.min(iAbs, 600));
        ag.b(this);
    }

    be a(int i, int i2) {
        be beVar = new be();
        beVar.b = i;
        beVar.f50a = this.h.a((ViewGroup) this, i);
        beVar.d = this.h.a(i);
        if (i2 < 0 || i2 >= this.e.size()) {
            this.e.add(beVar);
        } else {
            this.e.add(i2, beVar);
        }
        return beVar;
    }

    void a() {
        int i;
        boolean z;
        int iMax;
        boolean z2;
        int iA = this.h.a();
        this.b = iA;
        boolean z3 = this.e.size() < (this.z * 2) + 1 && this.e.size() < iA;
        boolean z4 = false;
        int i2 = this.i;
        boolean z5 = z3;
        int i3 = 0;
        while (i3 < this.e.size()) {
            be beVar = this.e.get(i3);
            int iA2 = this.h.a(beVar.f50a);
            if (iA2 == -1) {
                i = i3;
                z = z4;
                iMax = i2;
                z2 = z5;
            } else if (iA2 == -2) {
                this.e.remove(i3);
                int i4 = i3 - 1;
                if (!z4) {
                    this.h.a((ViewGroup) this);
                    z4 = true;
                }
                this.h.a((ViewGroup) this, beVar.b, beVar.f50a);
                if (this.i == beVar.b) {
                    i = i4;
                    z = z4;
                    iMax = Math.max(0, Math.min(this.i, iA - 1));
                    z2 = true;
                } else {
                    i = i4;
                    z = z4;
                    iMax = i2;
                    z2 = true;
                }
            } else if (beVar.b != iA2) {
                if (beVar.b == this.i) {
                    i2 = iA2;
                }
                beVar.b = iA2;
                i = i3;
                z = z4;
                iMax = i2;
                z2 = true;
            } else {
                i = i3;
                z = z4;
                iMax = i2;
                z2 = z5;
            }
            z5 = z2;
            i2 = iMax;
            z4 = z;
            i3 = i + 1;
        }
        if (z4) {
            this.h.b((ViewGroup) this);
        }
        Collections.sort(this.e, c);
        if (z5) {
            int childCount = getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                bf bfVar = (bf) getChildAt(i5).getLayoutParams();
                if (!bfVar.f51a) {
                    bfVar.c = 0.0f;
                }
            }
            a(i2, false, true);
            requestLayout();
        }
    }

    void b() {
        a(this.i);
    }

    void a(int i) {
        be beVar;
        int i2;
        String hexString;
        int i3;
        be beVar2;
        be beVarA;
        if (this.i == i) {
            beVar = null;
            i2 = 2;
        } else {
            int i4 = this.i < i ? 66 : 17;
            be beVarB = b(this.i);
            this.i = i;
            beVar = beVarB;
            i2 = i4;
        }
        if (this.h == null) {
            g();
            return;
        }
        if (this.y) {
            g();
            return;
        }
        if (getWindowToken() != null) {
            this.h.a((ViewGroup) this);
            int i5 = this.z;
            int iMax = Math.max(0, this.i - i5);
            int iA = this.h.a();
            int iMin = Math.min(iA - 1, i5 + this.i);
            if (iA != this.b) {
                try {
                    hexString = getResources().getResourceName(getId());
                } catch (Resources.NotFoundException e) {
                    hexString = Integer.toHexString(getId());
                }
                throw new IllegalStateException("The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: " + this.b + ", found: " + iA + " Pager id: " + hexString + " Pager class: " + getClass() + " Problematic adapter: " + this.h.getClass());
            }
            int i6 = 0;
            while (true) {
                i3 = i6;
                if (i3 < this.e.size()) {
                    beVar2 = this.e.get(i3);
                    if (beVar2.b < this.i) {
                        i6 = i3 + 1;
                    } else if (beVar2.b != this.i) {
                        break;
                    } else {
                        break;
                    }
                }
                beVar2 = null;
                break;
            }
            be beVarA2 = (beVar2 != null || iA <= 0) ? beVar2 : a(this.i, i3);
            if (beVarA2 != null) {
                int i7 = i3 - 1;
                be beVar3 = i7 >= 0 ? this.e.get(i7) : null;
                int clientWidth = getClientWidth();
                float paddingLeft = clientWidth <= 0 ? 0.0f : (2.0f - beVarA2.d) + (getPaddingLeft() / clientWidth);
                float f = 0.0f;
                int i8 = i3;
                int i9 = i7;
                for (int i10 = this.i - 1; i10 >= 0; i10--) {
                    if (f >= paddingLeft && i10 < iMax) {
                        if (beVar3 == null) {
                            break;
                        }
                        if (i10 == beVar3.b && !beVar3.c) {
                            this.e.remove(i9);
                            this.h.a((ViewGroup) this, i10, beVar3.f50a);
                            i9--;
                            i8--;
                            beVar3 = i9 >= 0 ? this.e.get(i9) : null;
                        }
                    } else if (beVar3 != null && i10 == beVar3.b) {
                        f += beVar3.d;
                        i9--;
                        beVar3 = i9 >= 0 ? this.e.get(i9) : null;
                    } else {
                        f += a(i10, i9 + 1).d;
                        i8++;
                        beVar3 = i9 >= 0 ? this.e.get(i9) : null;
                    }
                }
                float f2 = beVarA2.d;
                int i11 = i8 + 1;
                if (f2 < 2.0f) {
                    be beVar4 = i11 < this.e.size() ? this.e.get(i11) : null;
                    float paddingRight = clientWidth <= 0 ? 0.0f : (getPaddingRight() / clientWidth) + 2.0f;
                    be beVar5 = beVar4;
                    int i12 = i11;
                    int i13 = this.i + 1;
                    while (i13 < iA) {
                        if (f2 >= paddingRight && i13 > iMin) {
                            if (beVar5 == null) {
                                break;
                            }
                            if (i13 == beVar5.b && !beVar5.c) {
                                this.e.remove(i12);
                                this.h.a((ViewGroup) this, i13, beVar5.f50a);
                                beVar5 = i12 < this.e.size() ? this.e.get(i12) : null;
                            }
                        } else if (beVar5 != null && i13 == beVar5.b) {
                            f2 += beVar5.d;
                            i12++;
                            beVar5 = i12 < this.e.size() ? this.e.get(i12) : null;
                        } else {
                            be beVarA3 = a(i13, i12);
                            i12++;
                            f2 += beVarA3.d;
                            beVar5 = i12 < this.e.size() ? this.e.get(i12) : null;
                        }
                        i13++;
                        beVar5 = beVar5;
                        f2 = f2;
                    }
                }
                a(beVarA2, i8, beVar);
            }
            this.h.b((ViewGroup) this, this.i, beVarA2 != null ? beVarA2.f50a : null);
            this.h.b((ViewGroup) this);
            int childCount = getChildCount();
            for (int i14 = 0; i14 < childCount; i14++) {
                View childAt = getChildAt(i14);
                bf bfVar = (bf) childAt.getLayoutParams();
                bfVar.f = i14;
                if (!bfVar.f51a && bfVar.c == 0.0f && (beVarA = a(childAt)) != null) {
                    bfVar.c = beVarA.d;
                    bfVar.e = beVarA.b;
                }
            }
            g();
            if (hasFocus()) {
                View viewFindFocus = findFocus();
                be beVarB2 = viewFindFocus != null ? b(viewFindFocus) : null;
                if (beVarB2 == null || beVarB2.b != this.i) {
                    for (int i15 = 0; i15 < getChildCount(); i15++) {
                        View childAt2 = getChildAt(i15);
                        be beVarA4 = a(childAt2);
                        if (beVarA4 != null && beVarA4.b == this.i && childAt2.requestFocus(i2)) {
                            return;
                        }
                    }
                }
            }
        }
    }

    private void g() {
        if (this.ad != 0) {
            if (this.ae == null) {
                this.ae = new ArrayList<>();
            } else {
                this.ae.clear();
            }
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                this.ae.add(getChildAt(i));
            }
            Collections.sort(this.ae, af);
        }
    }

    private void a(be beVar, int i, be beVar2) {
        be beVar3;
        be beVar4;
        int iA = this.h.a();
        int clientWidth = getClientWidth();
        float f = clientWidth > 0 ? this.o / clientWidth : 0.0f;
        if (beVar2 != null) {
            int i2 = beVar2.b;
            if (i2 < beVar.b) {
                float fA = beVar2.e + beVar2.d + f;
                int i3 = i2 + 1;
                int i4 = 0;
                while (i3 <= beVar.b && i4 < this.e.size()) {
                    be beVar5 = this.e.get(i4);
                    while (true) {
                        beVar4 = beVar5;
                        if (i3 <= beVar4.b || i4 >= this.e.size() - 1) {
                            break;
                        }
                        i4++;
                        beVar5 = this.e.get(i4);
                    }
                    while (i3 < beVar4.b) {
                        fA += this.h.a(i3) + f;
                        i3++;
                    }
                    beVar4.e = fA;
                    fA += beVar4.d + f;
                    i3++;
                }
            } else if (i2 > beVar.b) {
                int size = this.e.size() - 1;
                float fA2 = beVar2.e;
                int i5 = i2 - 1;
                while (i5 >= beVar.b && size >= 0) {
                    be beVar6 = this.e.get(size);
                    while (true) {
                        beVar3 = beVar6;
                        if (i5 >= beVar3.b || size <= 0) {
                            break;
                        }
                        size--;
                        beVar6 = this.e.get(size);
                    }
                    while (i5 > beVar3.b) {
                        fA2 -= this.h.a(i5) + f;
                        i5--;
                    }
                    fA2 -= beVar3.d + f;
                    beVar3.e = fA2;
                    i5--;
                }
            }
        }
        int size2 = this.e.size();
        float f2 = beVar.e;
        int i6 = beVar.b - 1;
        this.s = beVar.b == 0 ? beVar.e : -3.4028235E38f;
        this.t = beVar.b == iA + (-1) ? (beVar.e + beVar.d) - 1.0f : Float.MAX_VALUE;
        for (int i7 = i - 1; i7 >= 0; i7--) {
            be beVar7 = this.e.get(i7);
            float fA3 = f2;
            while (i6 > beVar7.b) {
                fA3 -= this.h.a(i6) + f;
                i6--;
            }
            f2 = fA3 - (beVar7.d + f);
            beVar7.e = f2;
            if (beVar7.b == 0) {
                this.s = f2;
            }
            i6--;
        }
        float f3 = beVar.e + beVar.d + f;
        int i8 = beVar.b + 1;
        for (int i9 = i + 1; i9 < size2; i9++) {
            be beVar8 = this.e.get(i9);
            float fA4 = f3;
            while (i8 < beVar8.b) {
                fA4 = this.h.a(i8) + f + fA4;
                i8++;
            }
            if (beVar8.b == iA - 1) {
                this.t = (beVar8.d + fA4) - 1.0f;
            }
            beVar8.e = fA4;
            f3 = fA4 + beVar8.d + f;
            i8++;
        }
        this.T = false;
    }

    public class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = android.support.v4.b.a.a(new android.support.v4.b.c<SavedState>() { // from class: android.support.v4.view.ViewPager.SavedState.1
            @Override // android.support.v4.b.c
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState a(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.support.v4.b.c
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] a(int i) {
                return new SavedState[i];
            }
        });

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f37a;
        Parcelable b;
        ClassLoader c;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f37a);
            parcel.writeParcelable(this.b, i);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.f37a + "}";
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.f37a = parcel.readInt();
            this.b = parcel.readParcelable(classLoader);
            this.c = classLoader;
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f37a = this.i;
        if (this.h != null) {
            savedState.b = this.h.b();
        }
        return savedState;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (this.h != null) {
            this.h.a(savedState.b, savedState.c);
            a(savedState.f37a, false, true);
        } else {
            this.j = savedState.f37a;
            this.k = savedState.b;
            this.l = savedState.c;
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        ViewGroup.LayoutParams layoutParamsGenerateLayoutParams = !checkLayoutParams(layoutParams) ? generateLayoutParams(layoutParams) : layoutParams;
        bf bfVar = (bf) layoutParamsGenerateLayoutParams;
        bfVar.f51a |= view instanceof bd;
        if (this.w) {
            if (bfVar != null && bfVar.f51a) {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            }
            bfVar.d = true;
            addViewInLayout(view, i, layoutParamsGenerateLayoutParams);
            return;
        }
        super.addView(view, i, layoutParamsGenerateLayoutParams);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (this.w) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    be a(View view) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.e.size()) {
                be beVar = this.e.get(i2);
                if (!this.h.a(view, beVar.f50a)) {
                    i = i2 + 1;
                } else {
                    return beVar;
                }
            } else {
                return null;
            }
        }
    }

    be b(View view) {
        while (true) {
            Object parent = view.getParent();
            if (parent != this) {
                if (parent == null || !(parent instanceof View)) {
                    return null;
                }
                view = (View) parent;
            } else {
                return a(view);
            }
        }
    }

    be b(int i) {
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 < this.e.size()) {
                be beVar = this.e.get(i3);
                if (beVar.b != i) {
                    i2 = i3 + 1;
                } else {
                    return beVar;
                }
            } else {
                return null;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.S = true;
    }

    /* JADX WARN: Code duplicated, block: B:53:0x010a A[PHI: r1
      0x010a: PHI (r1v18 int) = (r1v17 int), (r1v20 int) binds: [B:28:0x0088, B:30:0x008f] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        bf bfVar;
        bf bfVar2;
        int i3;
        int i4;
        int i5;
        setMeasuredDimension(getDefaultSize(0, i), getDefaultSize(0, i2));
        int measuredWidth = getMeasuredWidth();
        this.D = Math.min(measuredWidth / 10, this.C);
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8 && (bfVar2 = (bf) childAt.getLayoutParams()) != null && bfVar2.f51a) {
                int i7 = bfVar2.b & 7;
                int i8 = bfVar2.b & 112;
                int i9 = Integer.MIN_VALUE;
                int i10 = Integer.MIN_VALUE;
                boolean z = i8 == 48 || i8 == 80;
                boolean z2 = i7 == 3 || i7 == 5;
                if (z) {
                    i9 = 1073741824;
                } else if (z2) {
                    i10 = 1073741824;
                }
                if (bfVar2.width != -2) {
                    i3 = 1073741824;
                    i4 = bfVar2.width != -1 ? bfVar2.width : paddingLeft;
                } else {
                    i3 = i9;
                    i4 = paddingLeft;
                }
                if (bfVar2.height != -2) {
                    i10 = 1073741824;
                    if (bfVar2.height != -1) {
                        i5 = bfVar2.height;
                    } else {
                        i5 = measuredHeight;
                    }
                } else {
                    i5 = measuredHeight;
                }
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i4, i3), View.MeasureSpec.makeMeasureSpec(i5, i10));
                if (z) {
                    measuredHeight -= childAt.getMeasuredHeight();
                } else if (z2) {
                    paddingLeft -= childAt.getMeasuredWidth();
                }
            }
        }
        this.u = View.MeasureSpec.makeMeasureSpec(paddingLeft, 1073741824);
        this.v = View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        this.w = true;
        b();
        this.w = false;
        int childCount2 = getChildCount();
        for (int i11 = 0; i11 < childCount2; i11++) {
            View childAt2 = getChildAt(i11);
            if (childAt2.getVisibility() != 8 && ((bfVar = (bf) childAt2.getLayoutParams()) == null || !bfVar.f51a)) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (bfVar.c * paddingLeft), 1073741824), this.v);
            }
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3) {
            a(i, i3, this.o, this.o);
        }
    }

    private void a(int i, int i2, int i3, int i4) {
        if (i2 > 0 && !this.e.isEmpty()) {
            int paddingLeft = (int) ((((i - getPaddingLeft()) - getPaddingRight()) + i3) * (getScrollX() / (((i2 - getPaddingLeft()) - getPaddingRight()) + i4)));
            scrollTo(paddingLeft, getScrollY());
            if (!this.m.isFinished()) {
                this.m.startScroll(paddingLeft, 0, (int) (b(this.i).e * i), 0, this.m.getDuration() - this.m.timePassed());
                return;
            }
            return;
        }
        be beVarB = b(this.i);
        int iMin = (int) ((beVarB != null ? Math.min(beVarB.e, this.t) : 0.0f) * ((i - getPaddingLeft()) - getPaddingRight()));
        if (iMin != getScrollX()) {
            a(false);
            scrollTo(iMin, getScrollY());
        }
    }

    /* JADX WARN: Code duplicated, block: B:39:0x0141  */
    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        be beVarA;
        int i5;
        int i6;
        int iMax;
        int measuredHeight;
        int i7;
        int i8;
        int childCount = getChildCount();
        int i9 = i3 - i;
        int i10 = i4 - i2;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int scrollX = getScrollX();
        int i11 = 0;
        int i12 = 0;
        while (i12 < childCount) {
            View childAt = getChildAt(i12);
            if (childAt.getVisibility() != 8) {
                bf bfVar = (bf) childAt.getLayoutParams();
                if (bfVar.f51a) {
                    int i13 = bfVar.b & 7;
                    int i14 = bfVar.b & 112;
                    switch (i13) {
                        case 1:
                            iMax = Math.max((i9 - childAt.getMeasuredWidth()) / 2, paddingLeft);
                            break;
                        case 2:
                        case 4:
                        default:
                            iMax = paddingLeft;
                            break;
                        case 3:
                            iMax = paddingLeft;
                            paddingLeft = childAt.getMeasuredWidth() + paddingLeft;
                            break;
                        case 5:
                            int measuredWidth = (i9 - paddingRight) - childAt.getMeasuredWidth();
                            paddingRight += childAt.getMeasuredWidth();
                            iMax = measuredWidth;
                            break;
                    }
                    switch (i14) {
                        case 16:
                            measuredHeight = Math.max((i10 - childAt.getMeasuredHeight()) / 2, paddingTop);
                            int i15 = paddingBottom;
                            i7 = paddingTop;
                            i8 = i15;
                            break;
                        case 48:
                            int measuredHeight2 = childAt.getMeasuredHeight() + paddingTop;
                            int i16 = paddingTop;
                            i8 = paddingBottom;
                            i7 = measuredHeight2;
                            measuredHeight = i16;
                            break;
                        case 80:
                            measuredHeight = (i10 - paddingBottom) - childAt.getMeasuredHeight();
                            int measuredHeight3 = paddingBottom + childAt.getMeasuredHeight();
                            i7 = paddingTop;
                            i8 = measuredHeight3;
                            break;
                        default:
                            measuredHeight = paddingTop;
                            int i17 = paddingBottom;
                            i7 = paddingTop;
                            i8 = i17;
                            break;
                    }
                    int i18 = iMax + scrollX;
                    childAt.layout(i18, measuredHeight, childAt.getMeasuredWidth() + i18, childAt.getMeasuredHeight() + measuredHeight);
                    i5 = i11 + 1;
                    i6 = i7;
                    paddingBottom = i8;
                } else {
                    i5 = i11;
                    i6 = paddingTop;
                }
            } else {
                i5 = i11;
                i6 = paddingTop;
            }
            i12++;
            paddingLeft = paddingLeft;
            paddingRight = paddingRight;
            paddingTop = i6;
            i11 = i5;
        }
        int i19 = (i9 - paddingLeft) - paddingRight;
        for (int i20 = 0; i20 < childCount; i20++) {
            View childAt2 = getChildAt(i20);
            if (childAt2.getVisibility() != 8) {
                bf bfVar2 = (bf) childAt2.getLayoutParams();
                if (!bfVar2.f51a && (beVarA = a(childAt2)) != null) {
                    int i21 = ((int) (beVarA.e * i19)) + paddingLeft;
                    if (bfVar2.d) {
                        bfVar2.d = false;
                        childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (bfVar2.c * i19), 1073741824), View.MeasureSpec.makeMeasureSpec((i10 - paddingTop) - paddingBottom, 1073741824));
                    }
                    childAt2.layout(i21, paddingTop, childAt2.getMeasuredWidth() + i21, childAt2.getMeasuredHeight() + paddingTop);
                }
            }
        }
        this.q = paddingTop;
        this.r = i10 - paddingBottom;
        this.V = i11;
        if (this.S) {
            a(this.i, false, 0, false);
        }
        this.S = false;
    }

    @Override // android.view.View
    public void computeScroll() {
        if (!this.m.isFinished() && this.m.computeScrollOffset()) {
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            int currX = this.m.getCurrX();
            int currY = this.m.getCurrY();
            if (scrollX != currX || scrollY != currY) {
                scrollTo(currX, currY);
                if (!d(currX)) {
                    this.m.abortAnimation();
                    scrollTo(0, currY);
                }
            }
            ag.b(this);
            return;
        }
        a(true);
    }

    private boolean d(int i) {
        if (this.e.size() == 0) {
            this.U = false;
            a(0, 0.0f, 0);
            if (this.U) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        be beVarH = h();
        int clientWidth = getClientWidth();
        int i2 = this.o + clientWidth;
        float f = this.o / clientWidth;
        int i3 = beVarH.b;
        float f2 = ((i / clientWidth) - beVarH.e) / (beVarH.d + f);
        this.U = false;
        a(i3, f2, (int) (i2 * f2));
        if (!this.U) {
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        return true;
    }

    protected void a(int i, float f, int i2) {
        int measuredWidth;
        int i3;
        int i4;
        if (this.V > 0) {
            int scrollX = getScrollX();
            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            int width = getWidth();
            int childCount = getChildCount();
            int i5 = 0;
            while (i5 < childCount) {
                View childAt = getChildAt(i5);
                bf bfVar = (bf) childAt.getLayoutParams();
                if (bfVar.f51a) {
                    switch (bfVar.b & 7) {
                        case 1:
                            measuredWidth = Math.max((width - childAt.getMeasuredWidth()) / 2, paddingLeft);
                            int i6 = paddingRight;
                            i3 = paddingLeft;
                            i4 = i6;
                            break;
                        case 2:
                        case 4:
                        default:
                            measuredWidth = paddingLeft;
                            int i7 = paddingRight;
                            i3 = paddingLeft;
                            i4 = i7;
                            break;
                        case 3:
                            int width2 = childAt.getWidth() + paddingLeft;
                            int i8 = paddingLeft;
                            i4 = paddingRight;
                            i3 = width2;
                            measuredWidth = i8;
                            break;
                        case 5:
                            measuredWidth = (width - paddingRight) - childAt.getMeasuredWidth();
                            int measuredWidth2 = paddingRight + childAt.getMeasuredWidth();
                            i3 = paddingLeft;
                            i4 = measuredWidth2;
                            break;
                    }
                    int left = (measuredWidth + scrollX) - childAt.getLeft();
                    if (left != 0) {
                        childAt.offsetLeftAndRight(left);
                    }
                } else {
                    int i9 = paddingRight;
                    i3 = paddingLeft;
                    i4 = i9;
                }
                i5++;
                int i10 = i4;
                paddingLeft = i3;
                paddingRight = i10;
            }
        }
        if (this.W != null) {
            this.W.a(i, f, i2);
        }
        if (this.Z != null) {
            this.Z.a(i, f, i2);
        }
        if (this.ab != null) {
            int scrollX2 = getScrollX();
            int childCount2 = getChildCount();
            for (int i11 = 0; i11 < childCount2; i11++) {
                View childAt2 = getChildAt(i11);
                if (!((bf) childAt2.getLayoutParams()).f51a) {
                    this.ab.a(childAt2, (childAt2.getLeft() - scrollX2) / getClientWidth());
                }
            }
        }
        this.U = true;
    }

    private void a(boolean z) {
        boolean z2 = this.ah == 2;
        if (z2) {
            setScrollingCacheEnabled(false);
            this.m.abortAnimation();
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            int currX = this.m.getCurrX();
            int currY = this.m.getCurrY();
            if (scrollX != currX || scrollY != currY) {
                scrollTo(currX, currY);
            }
        }
        this.y = false;
        boolean z3 = z2;
        for (int i = 0; i < this.e.size(); i++) {
            be beVar = this.e.get(i);
            if (beVar.c) {
                beVar.c = false;
                z3 = true;
            }
        }
        if (z3) {
            if (z) {
                ag.a(this, this.ag);
            } else {
                this.ag.run();
            }
        }
    }

    private boolean a(float f, float f2) {
        return (f < ((float) this.D) && f2 > 0.0f) || (f > ((float) (getWidth() - this.D)) && f2 < 0.0f);
    }

    private void b(boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ag.a(getChildAt(i), z ? 2 : 0, null);
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            this.A = false;
            this.B = false;
            this.J = -1;
            if (this.K == null) {
                return false;
            }
            this.K.recycle();
            this.K = null;
            return false;
        }
        if (action != 0) {
            if (this.A) {
                return true;
            }
            if (this.B) {
                return false;
            }
        }
        switch (action) {
            case 0:
                float x = motionEvent.getX();
                this.H = x;
                this.F = x;
                float y = motionEvent.getY();
                this.I = y;
                this.G = y;
                this.J = v.b(motionEvent, 0);
                this.B = false;
                this.m.computeScrollOffset();
                if (this.ah == 2 && Math.abs(this.m.getFinalX() - this.m.getCurrX()) > this.O) {
                    this.m.abortAnimation();
                    this.y = false;
                    b();
                    this.A = true;
                    c(true);
                    setScrollState(1);
                } else {
                    a(false);
                    this.A = false;
                }
                break;
            case 2:
                int i = this.J;
                if (i != -1) {
                    int iA = v.a(motionEvent, i);
                    float fC = v.c(motionEvent, iA);
                    float f = fC - this.F;
                    float fAbs = Math.abs(f);
                    float fD = v.d(motionEvent, iA);
                    float fAbs2 = Math.abs(fD - this.I);
                    if (f != 0.0f && !a(this.F, f) && a(this, false, (int) f, (int) fC, (int) fD)) {
                        this.F = fC;
                        this.G = fD;
                        this.B = true;
                        return false;
                    }
                    if (fAbs > this.E && 0.5f * fAbs > fAbs2) {
                        this.A = true;
                        c(true);
                        setScrollState(1);
                        this.F = f > 0.0f ? this.H + this.E : this.H - this.E;
                        this.G = fD;
                        setScrollingCacheEnabled(true);
                    } else if (fAbs2 > this.E) {
                        this.B = true;
                    }
                    if (this.A && b(fC)) {
                        ag.b(this);
                    }
                }
                break;
            case 6:
                a(motionEvent);
                break;
        }
        if (this.K == null) {
            this.K = VelocityTracker.obtain();
        }
        this.K.addMovement(motionEvent);
        return this.A;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean zC = false;
        if (this.P) {
            return true;
        }
        if (motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) {
            return false;
        }
        if (this.h == null || this.h.a() == 0) {
            return false;
        }
        if (this.K == null) {
            this.K = VelocityTracker.obtain();
        }
        this.K.addMovement(motionEvent);
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.m.abortAnimation();
                this.y = false;
                b();
                float x = motionEvent.getX();
                this.H = x;
                this.F = x;
                float y = motionEvent.getY();
                this.I = y;
                this.G = y;
                this.J = v.b(motionEvent, 0);
                break;
            case 1:
                if (this.A) {
                    VelocityTracker velocityTracker = this.K;
                    velocityTracker.computeCurrentVelocity(PlacePickerFragment.DEFAULT_RADIUS_IN_METERS, this.M);
                    int iA = (int) ab.a(velocityTracker, this.J);
                    this.y = true;
                    int clientWidth = getClientWidth();
                    int scrollX = getScrollX();
                    be beVarH = h();
                    a(a(beVarH.b, ((scrollX / clientWidth) - beVarH.e) / beVarH.d, iA, (int) (v.c(motionEvent, v.a(motionEvent, this.J)) - this.H)), true, true, iA);
                    this.J = -1;
                    i();
                    zC = this.R.c() | this.Q.c();
                }
                break;
            case 2:
                if (!this.A) {
                    int iA2 = v.a(motionEvent, this.J);
                    float fC = v.c(motionEvent, iA2);
                    float fAbs = Math.abs(fC - this.F);
                    float fD = v.d(motionEvent, iA2);
                    float fAbs2 = Math.abs(fD - this.G);
                    if (fAbs > this.E && fAbs > fAbs2) {
                        this.A = true;
                        c(true);
                        this.F = fC - this.H > 0.0f ? this.H + this.E : this.H - this.E;
                        this.G = fD;
                        setScrollState(1);
                        setScrollingCacheEnabled(true);
                        ViewParent parent = getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                }
                if (this.A) {
                    zC = false | b(v.c(motionEvent, v.a(motionEvent, this.J)));
                }
                break;
            case 3:
                if (this.A) {
                    a(this.i, true, 0, false);
                    this.J = -1;
                    i();
                    zC = this.R.c() | this.Q.c();
                }
                break;
            case 5:
                int iB = v.b(motionEvent);
                this.F = v.c(motionEvent, iB);
                this.J = v.b(motionEvent, iB);
                break;
            case 6:
                a(motionEvent);
                this.F = v.c(motionEvent, v.a(motionEvent, this.J));
                break;
        }
        if (zC) {
            ag.b(this);
        }
        return true;
    }

    private void c(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    private boolean b(float f) {
        boolean z;
        float f2;
        boolean z2 = true;
        boolean zA = false;
        float f3 = this.F - f;
        this.F = f;
        float scrollX = getScrollX() + f3;
        int clientWidth = getClientWidth();
        float f4 = clientWidth * this.s;
        float f5 = clientWidth * this.t;
        be beVar = this.e.get(0);
        be beVar2 = this.e.get(this.e.size() - 1);
        if (beVar.b != 0) {
            f4 = beVar.e * clientWidth;
            z = false;
        } else {
            z = true;
        }
        if (beVar2.b != this.h.a() - 1) {
            f2 = beVar2.e * clientWidth;
            z2 = false;
        } else {
            f2 = f5;
        }
        if (scrollX < f4) {
            if (z) {
                zA = this.Q.a(Math.abs(f4 - scrollX) / clientWidth);
            }
        } else if (scrollX > f2) {
            zA = z2 ? this.R.a(Math.abs(scrollX - f2) / clientWidth) : false;
            f4 = f2;
        } else {
            f4 = scrollX;
        }
        this.F += f4 - ((int) f4);
        scrollTo((int) f4, getScrollY());
        d((int) f4);
        return zA;
    }

    private be h() {
        int i;
        be beVar;
        int clientWidth = getClientWidth();
        float scrollX = clientWidth > 0 ? getScrollX() / clientWidth : 0.0f;
        float f = clientWidth > 0 ? this.o / clientWidth : 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        int i2 = -1;
        int i3 = 0;
        boolean z = true;
        be beVar2 = null;
        while (i3 < this.e.size()) {
            be beVar3 = this.e.get(i3);
            if (z || beVar3.b == i2 + 1) {
                i = i3;
                beVar = beVar3;
            } else {
                be beVar4 = this.f;
                beVar4.e = f2 + f3 + f;
                beVar4.b = i2 + 1;
                beVar4.d = this.h.a(beVar4.b);
                i = i3 - 1;
                beVar = beVar4;
            }
            float f4 = beVar.e;
            float f5 = beVar.d + f4 + f;
            if (!z && scrollX < f4) {
                return beVar2;
            }
            if (scrollX < f5 || i == this.e.size() - 1) {
                return beVar;
            }
            f3 = f4;
            i2 = beVar.b;
            z = false;
            f2 = beVar.d;
            beVar2 = beVar;
            i3 = i + 1;
        }
        return beVar2;
    }

    private int a(int i, float f, int i2, int i3) {
        if (Math.abs(i3) > this.N && Math.abs(i2) > this.L) {
            if (i2 <= 0) {
                i++;
            }
        } else {
            i = (int) ((i >= this.i ? 0.4f : 0.6f) + i + f);
        }
        if (this.e.size() > 0) {
            return Math.max(this.e.get(0).b, Math.min(i, this.e.get(this.e.size() - 1).b));
        }
        return i;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        boolean zA = false;
        int iA = ag.a(this);
        if (iA == 0 || (iA == 1 && this.h != null && this.h.a() > 1)) {
            if (!this.Q.a()) {
                int iSave = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((-height) + getPaddingTop(), this.s * width);
                this.Q.a(height, width);
                zA = false | this.Q.a(canvas);
                canvas.restoreToCount(iSave);
            }
            if (!this.R.a()) {
                int iSave2 = canvas.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate(-getPaddingTop(), (-(this.t + 1.0f)) * width2);
                this.R.a(height2, width2);
                zA |= this.R.a(canvas);
                canvas.restoreToCount(iSave2);
            }
        } else {
            this.Q.b();
            this.R.b();
        }
        if (zA) {
            ag.b(this);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float f;
        super.onDraw(canvas);
        if (this.o > 0 && this.p != null && this.e.size() > 0 && this.h != null) {
            int scrollX = getScrollX();
            int width = getWidth();
            float f2 = this.o / width;
            be beVar = this.e.get(0);
            float f3 = beVar.e;
            int size = this.e.size();
            int i = beVar.b;
            int i2 = this.e.get(size - 1).b;
            int i3 = 0;
            for (int i4 = i; i4 < i2; i4++) {
                while (i4 > beVar.b && i3 < size) {
                    i3++;
                    beVar = this.e.get(i3);
                }
                if (i4 == beVar.b) {
                    f = (beVar.e + beVar.d) * width;
                    f3 = beVar.e + beVar.d + f2;
                } else {
                    float fA = this.h.a(i4);
                    f = (f3 + fA) * width;
                    f3 += fA + f2;
                }
                if (this.o + f > scrollX) {
                    this.p.setBounds((int) f, this.q, (int) (this.o + f + 0.5f), this.r);
                    this.p.draw(canvas);
                }
                if (f > scrollX + width) {
                    return;
                }
            }
        }
    }

    private void a(MotionEvent motionEvent) {
        int iB = v.b(motionEvent);
        if (v.b(motionEvent, iB) == this.J) {
            int i = iB == 0 ? 1 : 0;
            this.F = v.c(motionEvent, i);
            this.J = v.b(motionEvent, i);
            if (this.K != null) {
                this.K.clear();
            }
        }
    }

    private void i() {
        this.A = false;
        this.B = false;
        if (this.K != null) {
            this.K.recycle();
            this.K = null;
        }
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.x != z) {
            this.x = z;
        }
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i) {
        if (this.h == null) {
            return false;
        }
        int clientWidth = getClientWidth();
        int scrollX = getScrollX();
        if (i < 0) {
            return scrollX > ((int) (((float) clientWidth) * this.s));
        }
        if (i > 0) {
            return scrollX < ((int) (((float) clientWidth) * this.t));
        }
        return false;
    }

    protected boolean a(View view, boolean z, int i, int i2, int i3) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (i2 + scrollX >= childAt.getLeft() && i2 + scrollX < childAt.getRight() && i3 + scrollY >= childAt.getTop() && i3 + scrollY < childAt.getBottom() && a(childAt, true, i, (i2 + scrollX) - childAt.getLeft(), (i3 + scrollY) - childAt.getTop())) {
                    return true;
                }
            }
        }
        return z && ag.a(view, -i);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || a(keyEvent);
    }

    public boolean a(KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0) {
            return false;
        }
        switch (keyEvent.getKeyCode()) {
            case 21:
                return c(17);
            case 22:
                return c(66);
            case 61:
                if (Build.VERSION.SDK_INT < 11) {
                    return false;
                }
                if (o.a(keyEvent)) {
                    return c(2);
                }
                if (!o.a(keyEvent, 1)) {
                    return false;
                }
                return c(1);
            default:
                return false;
        }
    }

    /* JADX WARN: Code duplicated, block: B:43:0x00db  */
    /* JADX WARN: Code duplicated, block: B:44:0x00de  */
    public boolean c(int i) {
        View view;
        boolean z;
        boolean zC;
        View viewFindFocus = findFocus();
        if (viewFindFocus == this) {
            view = null;
        } else if (viewFindFocus != null) {
            ViewParent parent = viewFindFocus.getParent();
            while (true) {
                if (!(parent instanceof ViewGroup)) {
                    z = false;
                    break;
                }
                if (parent == this) {
                    z = true;
                    break;
                }
                parent = parent.getParent();
            }
            if (z) {
                view = viewFindFocus;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(viewFindFocus.getClass().getSimpleName());
                for (ViewParent parent2 = viewFindFocus.getParent(); parent2 instanceof ViewGroup; parent2 = parent2.getParent()) {
                    sb.append(" => ").append(parent2.getClass().getSimpleName());
                }
                Log.e("ViewPager", "arrowScroll tried to find focus based on non-child current focused view " + sb.toString());
                view = null;
            }
        } else {
            view = viewFindFocus;
        }
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, view, i);
        if (viewFindNextFocus != null && viewFindNextFocus != view) {
            if (i == 17) {
                int i2 = a(this.g, viewFindNextFocus).left;
                int i3 = a(this.g, view).left;
                if (view != null && i2 >= i3) {
                    zC = c();
                } else {
                    zC = viewFindNextFocus.requestFocus();
                }
            } else if (i == 66) {
                int i4 = a(this.g, viewFindNextFocus).left;
                int i5 = a(this.g, view).left;
                if (view != null && i4 <= i5) {
                    zC = d();
                } else {
                    zC = viewFindNextFocus.requestFocus();
                }
            } else {
                zC = false;
            }
        } else if (i == 17 || i == 1) {
            zC = c();
        } else if (i == 66 || i == 2) {
            zC = d();
        } else {
            zC = false;
        }
        if (zC) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
        }
        return zC;
    }

    private Rect a(Rect rect, View view) {
        Rect rect2 = rect == null ? new Rect() : rect;
        if (view == null) {
            rect2.set(0, 0, 0, 0);
            return rect2;
        }
        rect2.left = view.getLeft();
        rect2.right = view.getRight();
        rect2.top = view.getTop();
        rect2.bottom = view.getBottom();
        ViewParent parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = (ViewGroup) parent;
            rect2.left += viewGroup.getLeft();
            rect2.right += viewGroup.getRight();
            rect2.top += viewGroup.getTop();
            rect2.bottom += viewGroup.getBottom();
            parent = viewGroup.getParent();
        }
        return rect2;
    }

    boolean c() {
        if (this.i <= 0) {
            return false;
        }
        a(this.i - 1, true);
        return true;
    }

    boolean d() {
        if (this.h == null || this.i >= this.h.a() - 1) {
            return false;
        }
        a(this.i + 1, true);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        be beVarA;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                View childAt = getChildAt(i3);
                if (childAt.getVisibility() == 0 && (beVarA = a(childAt)) != null && beVarA.b == this.i) {
                    childAt.addFocusables(arrayList, i, i2);
                }
            }
        }
        if ((descendantFocusability != 262144 || size == arrayList.size()) && isFocusable()) {
            if (((i2 & 1) != 1 || !isInTouchMode() || isFocusableInTouchMode()) && arrayList != null) {
                arrayList.add(this);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addTouchables(ArrayList<View> arrayList) {
        be beVarA;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0 && (beVarA = a(childAt)) != null && beVarA.b == this.i) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int i, Rect rect) {
        int i2;
        be beVarA;
        int i3 = -1;
        int childCount = getChildCount();
        if ((i & 2) != 0) {
            i3 = 1;
            i2 = 0;
        } else {
            i2 = childCount - 1;
            childCount = -1;
        }
        while (i2 != childCount) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (beVarA = a(childAt)) != null && beVarA.b == this.i && childAt.requestFocus(i, rect)) {
                return true;
            }
            i2 += i3;
        }
        return false;
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        be beVarA;
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0 && (beVarA = a(childAt)) != null && beVarA.b == this.i && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new bf();
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof bf) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new bf(getContext(), attributeSet);
    }
}
