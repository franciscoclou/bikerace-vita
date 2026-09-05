package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBOrientation;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class u extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private View f492a;
    private q b;
    private OrientationEventListener c;
    private CBOrientation.Difference d;

    public interface a {
        void a();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public u(Context context, a aVar) {
        super(context);
        this.d = null;
        this.f492a = (View) aVar;
        this.b = new q(context);
        addView(this.b, new RelativeLayout.LayoutParams(-1, -1));
        addView(this.f492a, new RelativeLayout.LayoutParams(-1, -1));
        Chartboost chartboostSharedChartboost = Chartboost.sharedChartboost();
        if (chartboostSharedChartboost.getOrientation() != null && chartboostSharedChartboost.getOrientation() != CBOrientation.UNSPECIFIED) {
            this.d = Chartboost.sharedChartboost().getForcedOrientationDifference();
            this.c = new OrientationEventListener(context, 1) { // from class: com.chartboost.sdk.impl.u.1
                @Override // android.view.OrientationEventListener
                public void onOrientationChanged(int i) {
                    CBOrientation.Difference forcedOrientationDifference = Chartboost.sharedChartboost().getForcedOrientationDifference();
                    if (u.this.d != forcedOrientationDifference) {
                        u.this.d = forcedOrientationDifference;
                        ((a) u.this.f492a).a();
                        u.this.invalidate();
                    }
                }
            };
            this.c.enable();
        }
        setOnTouchListener(new View.OnTouchListener() { // from class: com.chartboost.sdk.impl.u.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }

    public void a() {
        if (this.c != null) {
            this.c.disable();
            this.c = null;
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public q b() {
        return this.b;
    }

    public View c() {
        return this.f492a;
    }
}
