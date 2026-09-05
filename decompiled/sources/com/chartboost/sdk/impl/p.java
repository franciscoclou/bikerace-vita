package com.chartboost.sdk.impl;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBOrientation;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static /* synthetic */ int[] f481a;
    private static /* synthetic */ int[] b;

    public interface a {
        void a(com.chartboost.sdk.impl.a aVar);
    }

    static /* synthetic */ int[] a() {
        int[] iArr = f481a;
        if (iArr == null) {
            iArr = new int[CBOrientation.Difference.valuesCustom().length];
            try {
                iArr[CBOrientation.Difference.ANGLE_0.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[CBOrientation.Difference.ANGLE_180.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[CBOrientation.Difference.ANGLE_270.ordinal()] = 4;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[CBOrientation.Difference.ANGLE_90.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            f481a = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] b() {
        int[] iArr = b;
        if (iArr == null) {
            iArr = new int[b.valuesCustom().length];
            try {
                iArr[b.CBAnimationTypeBounce.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[b.CBAnimationTypeNone.ordinal()] = 6;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[b.CBAnimationTypePerspectiveRotate.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[b.CBAnimationTypePerspectiveZoom.ordinal()] = 3;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[b.CBAnimationTypeSlideFromBottom.ordinal()] = 4;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[b.CBAnimationTypeSlideFromTop.ordinal()] = 5;
            } catch (NoSuchFieldError e6) {
            }
            b = iArr;
        }
        return iArr;
    }

    public enum b {
        CBAnimationTypePerspectiveRotate,
        CBAnimationTypeBounce,
        CBAnimationTypePerspectiveZoom,
        CBAnimationTypeSlideFromBottom,
        CBAnimationTypeSlideFromTop,
        CBAnimationTypeNone;

        /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
        public static b[] valuesCustom() {
            b[] bVarArrValuesCustom = values();
            int length = bVarArrValuesCustom.length;
            b[] bVarArr = new b[length];
            System.arraycopy(bVarArrValuesCustom, 0, bVarArr, 0, length);
            return bVarArr;
        }

        public static b a(int i) {
            if (i != 0 && i > 0 && i <= valuesCustom().length) {
                return valuesCustom()[i - 1];
            }
            return null;
        }
    }

    public static void a(b bVar, com.chartboost.sdk.impl.a aVar) {
        a(bVar, aVar, null);
    }

    public static void a(b bVar, com.chartboost.sdk.impl.a aVar, a aVar2) {
        b(bVar, aVar, aVar2, true);
    }

    public static void b(b bVar, com.chartboost.sdk.impl.a aVar, a aVar2) {
        c(bVar, aVar, aVar2, false);
    }

    private static void b(final b bVar, final com.chartboost.sdk.impl.a aVar, final a aVar2, final Boolean bool) {
        final View viewC;
        if (bVar == b.CBAnimationTypeNone) {
            if (aVar2 != null) {
                aVar2.a(aVar);
            }
        } else if (aVar != null && aVar.h != null && (viewC = aVar.h.c()) != null) {
            ViewTreeObserver viewTreeObserver = viewC.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.chartboost.sdk.impl.p.1
                    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                    public void onGlobalLayout() {
                        viewC.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        p.c(bVar, aVar, aVar2, bool);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(b bVar, final com.chartboost.sdk.impl.a aVar, final a aVar2, Boolean bool) {
        View viewC;
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        r rVar;
        ScaleAnimation scaleAnimation;
        TranslateAnimation translateAnimation;
        r rVar2;
        ScaleAnimation scaleAnimation2;
        TranslateAnimation translateAnimation2;
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(new AlphaAnimation(1.0f, 1.0f));
        if (aVar != null && aVar.h != null && (viewC = aVar.h.c()) != null) {
            float width = viewC.getWidth();
            float height = viewC.getHeight();
            float f7 = (1.0f - 0.4f) / 2.0f;
            CBOrientation.Difference forcedOrientationDifference = Chartboost.sharedChartboost().getForcedOrientationDifference();
            switch (b()[bVar.ordinal()]) {
                case 1:
                    switch (a()[forcedOrientationDifference.ordinal()]) {
                        case 2:
                            rVar = bool.booleanValue() ? new r(60.0f, 0.0f, width / 2.0f, height / 2.0f, false) : new r(0.0f, -60.0f, width / 2.0f, height / 2.0f, false);
                            break;
                        case 3:
                            rVar = bool.booleanValue() ? new r(60.0f, 0.0f, width / 2.0f, height / 2.0f, true) : new r(0.0f, -60.0f, width / 2.0f, height / 2.0f, true);
                            break;
                        case 4:
                            rVar = bool.booleanValue() ? new r(-60.0f, 0.0f, width / 2.0f, height / 2.0f, false) : new r(0.0f, 60.0f, width / 2.0f, height / 2.0f, false);
                            break;
                        default:
                            if (bool.booleanValue()) {
                                rVar = new r(-60.0f, 0.0f, width / 2.0f, height / 2.0f, true);
                            } else {
                                rVar = new r(0.0f, 60.0f, width / 2.0f, height / 2.0f, true);
                            }
                            break;
                    }
                    rVar.setDuration(600L);
                    rVar.setFillAfter(true);
                    animationSet.addAnimation(rVar);
                    if (bool.booleanValue()) {
                        scaleAnimation = new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f);
                    } else {
                        scaleAnimation = new ScaleAnimation(1.0f, 0.4f, 1.0f, 0.4f);
                    }
                    scaleAnimation.setDuration(600L);
                    scaleAnimation.setFillAfter(true);
                    animationSet.addAnimation(scaleAnimation);
                    switch (a()[forcedOrientationDifference.ordinal()]) {
                        case 2:
                            translateAnimation = bool.booleanValue() ? new TranslateAnimation(width * f7, 0.0f, (-height) * 0.4f, 0.0f) : new TranslateAnimation(0.0f, width * f7, 0.0f, height);
                            break;
                        case 3:
                            translateAnimation = !bool.booleanValue() ? new TranslateAnimation(0.0f, (-width) * 0.4f, 0.0f, height * f7) : new TranslateAnimation(width, 0.0f, height * f7, 0.0f);
                            break;
                        case 4:
                            translateAnimation = !bool.booleanValue() ? new TranslateAnimation(0.0f, width * f7, 0.0f, (-height) * 0.4f) : new TranslateAnimation(width * f7, 0.0f, height, 0.0f);
                            break;
                        default:
                            if (bool.booleanValue()) {
                                translateAnimation = new TranslateAnimation((-width) * 0.4f, 0.0f, height * f7, 0.0f);
                            } else {
                                translateAnimation = new TranslateAnimation(0.0f, width, 0.0f, height * f7);
                            }
                            break;
                    }
                    translateAnimation.setDuration(600L);
                    translateAnimation.setFillAfter(true);
                    animationSet.addAnimation(translateAnimation);
                    break;
                case 2:
                    if (bool.booleanValue()) {
                        ScaleAnimation scaleAnimation3 = new ScaleAnimation(0.6f, 1.1f, 0.6f, 1.1f, 1, 0.5f, 1, 0.5f);
                        scaleAnimation3.setDuration(Math.round(600 * 0.6f));
                        scaleAnimation3.setStartOffset(0L);
                        scaleAnimation3.setFillAfter(true);
                        animationSet.addAnimation(scaleAnimation3);
                        ScaleAnimation scaleAnimation4 = new ScaleAnimation(1.0f, 0.81818175f, 1.0f, 0.81818175f, 1, 0.5f, 1, 0.5f);
                        scaleAnimation4.setDuration(Math.round(600 * 0.19999999f));
                        scaleAnimation4.setStartOffset(Math.round(600 * 0.6f));
                        scaleAnimation4.setFillAfter(true);
                        animationSet.addAnimation(scaleAnimation4);
                        ScaleAnimation scaleAnimation5 = new ScaleAnimation(1.0f, 1.1111112f, 1.0f, 1.1111112f, 1, 0.5f, 1, 0.5f);
                        scaleAnimation5.setDuration(Math.round(600 * 0.099999964f));
                        scaleAnimation5.setStartOffset(Math.round(600 * 0.8f));
                        scaleAnimation5.setFillAfter(true);
                        animationSet.addAnimation(scaleAnimation5);
                    } else {
                        ScaleAnimation scaleAnimation6 = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 1, 0.5f, 1, 0.5f);
                        scaleAnimation6.setDuration(600L);
                        scaleAnimation6.setStartOffset(0L);
                        scaleAnimation6.setFillAfter(true);
                        animationSet.addAnimation(scaleAnimation6);
                    }
                    break;
                case 3:
                    switch (a()[forcedOrientationDifference.ordinal()]) {
                        case 2:
                            rVar2 = bool.booleanValue() ? new r(-60.0f, 0.0f, width / 2.0f, height / 2.0f, true) : new r(0.0f, 60.0f, width / 2.0f, height / 2.0f, true);
                            break;
                        case 3:
                            rVar2 = bool.booleanValue() ? new r(60.0f, 0.0f, width / 2.0f, height / 2.0f, false) : new r(0.0f, -60.0f, width / 2.0f, height / 2.0f, false);
                            break;
                        case 4:
                            rVar2 = bool.booleanValue() ? new r(60.0f, 0.0f, width / 2.0f, height / 2.0f, true) : new r(0.0f, -60.0f, width / 2.0f, height / 2.0f, true);
                            break;
                        default:
                            if (bool.booleanValue()) {
                                rVar2 = new r(-60.0f, 0.0f, width / 2.0f, height / 2.0f, false);
                            } else {
                                rVar2 = new r(0.0f, 60.0f, width / 2.0f, height / 2.0f, false);
                            }
                            break;
                    }
                    rVar2.setDuration(600L);
                    rVar2.setFillAfter(true);
                    animationSet.addAnimation(rVar2);
                    if (bool.booleanValue()) {
                        scaleAnimation2 = new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f);
                    } else {
                        scaleAnimation2 = new ScaleAnimation(1.0f, 0.4f, 1.0f, 0.4f);
                    }
                    scaleAnimation2.setDuration(600L);
                    scaleAnimation2.setFillAfter(true);
                    animationSet.addAnimation(scaleAnimation2);
                    switch (a()[forcedOrientationDifference.ordinal()]) {
                        case 2:
                            translateAnimation2 = !bool.booleanValue() ? new TranslateAnimation(0.0f, (-width) * 0.4f, 0.0f, height * f7) : new TranslateAnimation(width, 0.0f, height * f7, 0.0f);
                            break;
                        case 3:
                            translateAnimation2 = !bool.booleanValue() ? new TranslateAnimation(0.0f, width * f7, 0.0f, (-height) * 0.4f) : new TranslateAnimation(width * f7, 0.0f, height, 0.0f);
                            break;
                        case 4:
                            translateAnimation2 = bool.booleanValue() ? new TranslateAnimation((-width) * 0.4f, 0.0f, height * f7, 0.0f) : new TranslateAnimation(0.0f, width, 0.0f, height * f7);
                            break;
                        default:
                            if (bool.booleanValue()) {
                                translateAnimation2 = new TranslateAnimation(width * f7, 0.0f, (-height) * 0.4f, 0.0f);
                            } else {
                                translateAnimation2 = new TranslateAnimation(0.0f, width * f7, 0.0f, height);
                            }
                            break;
                    }
                    translateAnimation2.setDuration(600L);
                    translateAnimation2.setFillAfter(true);
                    animationSet.addAnimation(translateAnimation2);
                    break;
                case 4:
                    switch (a()[forcedOrientationDifference.ordinal()]) {
                        case 1:
                            f6 = bool.booleanValue() ? height : 0.0f;
                            if (bool.booleanValue()) {
                                height = 0.0f;
                            }
                            f4 = 0.0f;
                            f5 = 0.0f;
                            break;
                        case 2:
                            float f8 = bool.booleanValue() ? -width : 0.0f;
                            f4 = bool.booleanValue() ? 0.0f : -width;
                            height = 0.0f;
                            f5 = f8;
                            f6 = 0.0f;
                            break;
                        case 3:
                            f6 = bool.booleanValue() ? -height : 0.0f;
                            height = bool.booleanValue() ? 0.0f : -height;
                            f5 = 0.0f;
                            f4 = 0.0f;
                            break;
                        case 4:
                            float f9 = bool.booleanValue() ? width : 0.0f;
                            if (bool.booleanValue()) {
                                width = 0.0f;
                            }
                            height = 0.0f;
                            f4 = width;
                            f5 = f9;
                            f6 = 0.0f;
                            break;
                        default:
                            height = 0.0f;
                            f6 = 0.0f;
                            f4 = 0.0f;
                            f5 = 0.0f;
                            break;
                    }
                    TranslateAnimation translateAnimation3 = new TranslateAnimation(f5, f4, f6, height);
                    translateAnimation3.setDuration(600L);
                    translateAnimation3.setFillAfter(true);
                    animationSet.addAnimation(translateAnimation3);
                    break;
                case 5:
                    switch (a()[forcedOrientationDifference.ordinal()]) {
                        case 1:
                            f3 = bool.booleanValue() ? -height : 0.0f;
                            f = bool.booleanValue() ? 0.0f : -height;
                            width = 0.0f;
                            f2 = 0.0f;
                            break;
                        case 2:
                            float f10 = bool.booleanValue() ? width : 0.0f;
                            if (bool.booleanValue()) {
                                width = 0.0f;
                            }
                            f = 0.0f;
                            f2 = f10;
                            f3 = 0.0f;
                            break;
                        case 3:
                            f3 = bool.booleanValue() ? height : 0.0f;
                            if (bool.booleanValue()) {
                                height = 0.0f;
                            }
                            f = height;
                            width = 0.0f;
                            f2 = 0.0f;
                            break;
                        case 4:
                            float f11 = bool.booleanValue() ? -width : 0.0f;
                            width = bool.booleanValue() ? 0.0f : -width;
                            f = 0.0f;
                            f2 = f11;
                            f3 = 0.0f;
                            break;
                        default:
                            f = 0.0f;
                            f3 = 0.0f;
                            width = 0.0f;
                            f2 = 0.0f;
                            break;
                    }
                    TranslateAnimation translateAnimation4 = new TranslateAnimation(f2, width, f3, f);
                    translateAnimation4.setDuration(600L);
                    translateAnimation4.setFillAfter(true);
                    animationSet.addAnimation(translateAnimation4);
                    break;
            }
            if (bVar != b.CBAnimationTypeNone) {
                Chartboost.sharedChartboost().getHandler().postDelayed(new Runnable() { // from class: com.chartboost.sdk.impl.p.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (aVar2 != null) {
                            aVar2.a(aVar);
                        }
                    }
                }, 600L);
                viewC.startAnimation(animationSet);
            } else if (aVar2 != null) {
                aVar2.a(aVar);
            }
        }
    }
}
