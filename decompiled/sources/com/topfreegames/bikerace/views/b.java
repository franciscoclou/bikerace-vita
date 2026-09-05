package com.topfreegames.bikerace.views;

import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

/* JADX INFO: compiled from: AchievementNotificationView.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class b implements Animation.AnimationListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ a f1395a;

    private b(a aVar) {
        this.f1395a = aVar;
    }

    /* synthetic */ b(a aVar, b bVar) {
        this(aVar);
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
        new Handler().post(new Runnable() { // from class: com.topfreegames.bikerace.views.b.1
            @Override // java.lang.Runnable
            public void run() {
                ViewGroup viewGroup = (ViewGroup) b.this.f1395a.getParent();
                if (viewGroup != null) {
                    viewGroup.removeView(b.this.f1395a);
                }
                if (b.this.f1395a.f1394a != null) {
                    b.this.f1395a.f1394a.removeView(b.this.f1395a);
                }
                View viewFindViewById = b.this.f1395a.findViewById(2131296259);
                if (viewFindViewById != null) {
                    viewFindViewById.setVisibility(8);
                }
                View viewFindViewById2 = b.this.f1395a.findViewById(2131296261);
                if (viewFindViewById2 != null) {
                    viewFindViewById2.setVisibility(8);
                }
                View viewFindViewById3 = b.this.f1395a.findViewById(2131296260);
                if (viewFindViewById3 != null) {
                    viewFindViewById3.setVisibility(8);
                }
                b.this.f1395a.setVisibility(8);
                b.this.f1395a.f1394a = null;
                if (b.this.f1395a.b != null) {
                    b.this.f1395a.b.b(null);
                }
                b.this.f1395a.b = null;
            }
        });
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationRepeat(Animation animation) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationStart(Animation animation) {
    }
}
