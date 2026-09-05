package com.topfreegames.bikerace.worldcup.views;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.topfreegames.bikerace.ap;
import com.topfreegames.bikerace.worldcup.l;

/* JADX INFO: compiled from: WorldCupBikeUnlockDialog.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a extends com.topfreegames.bikerace.e.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private View f1479a;
    private ImageView b;
    private View c;
    private View d;
    private View e;
    private View f;
    private View g;
    private com.topfreegames.bikerace.c h;
    private c i;

    public a(Context context, com.topfreegames.bikerace.c cVar, c cVar2) {
        super(context, 2131492932);
        this.f1479a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.f1479a = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903113, (ViewGroup) null);
        this.c = this.f1479a.findViewById(2131296818);
        this.b = (ImageView) this.f1479a.findViewById(2131296822);
        this.d = this.f1479a.findViewById(2131296819);
        this.e = this.f1479a.findViewById(2131296823);
        this.f = this.f1479a.findViewById(2131296821);
        this.g = this.f1479a.findViewById(2131296820);
        a(context, this.f1479a);
        setContentView(this.f1479a);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().setFlags(1024, 1024);
        getWindow().setLayout(-1, -1);
        this.h = cVar;
        this.i = cVar2;
    }

    private boolean a() {
        try {
            b bVar = new b(this, null);
            Animation animationLoadAnimation = AnimationUtils.loadAnimation(getContext(), 2130968576);
            animationLoadAnimation.setAnimationListener(bVar);
            this.b.startAnimation(animationLoadAnimation);
            Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(getContext(), 2130968579);
            animationLoadAnimation2.setAnimationListener(bVar);
            this.d.startAnimation(animationLoadAnimation2);
            Animation animationLoadAnimation3 = AnimationUtils.loadAnimation(getContext(), 2130968580);
            animationLoadAnimation3.setAnimationListener(bVar);
            this.e.startAnimation(animationLoadAnimation3);
            Animation animationLoadAnimation4 = AnimationUtils.loadAnimation(getContext(), 2130968578);
            animationLoadAnimation4.setAnimationListener(bVar);
            this.f.startAnimation(animationLoadAnimation4);
            Animation animationLoadAnimation5 = AnimationUtils.loadAnimation(getContext(), 2130968577);
            animationLoadAnimation5.setAnimationListener(bVar);
            this.g.startAnimation(animationLoadAnimation5);
            return true;
        } catch (Exception e) {
            if (ap.d()) {
                e.printStackTrace();
            }
            return false;
        }
    }

    private boolean b() {
        if (!c() || !a() || this.c == null) {
            return false;
        }
        this.c.setVisibility(0);
        return true;
    }

    @Override // com.topfreegames.bikerace.e.a, android.app.Dialog
    public void show() {
        if (this.h != null) {
            if (b()) {
                super.show();
                return;
            } else {
                dismiss();
                return;
            }
        }
        dismiss();
    }

    private boolean c() {
        try {
            int iB = l.b(this.h);
            if (this.b != null) {
                this.b.setImageDrawable(getContext().getResources().getDrawable(iB));
            }
            return true;
        } catch (Exception e) {
            if (ap.d()) {
                e.printStackTrace();
            }
            return false;
        }
    }
}
