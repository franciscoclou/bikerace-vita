package com.topfreegames.bikerace.e;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.amazonaws.javax.xml.stream.XMLStreamConstants;
import com.topfreegames.bikerace.ap;
import java.util.Iterator;

/* JADX INFO: compiled from: BikeUnlockDialog.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b extends a {
    private static /* synthetic */ int[] o;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private View f1181a;
    private ImageView b;
    private View c;
    private View d;
    private View e;
    private View f;
    private View g;
    private View h;
    private ViewGroup i;
    private ImageView j;
    private TextView k;
    private View l;
    private View m;
    private com.topfreegames.bikerace.c n;

    static /* synthetic */ int[] a() {
        int[] iArr = o;
        if (iArr == null) {
            iArr = new int[com.topfreegames.bikerace.c.valuesCustom().length];
            try {
                iArr[com.topfreegames.bikerace.c.ACROBATIC.ordinal()] = 12;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.ARMY.ordinal()] = 17;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.BEAT.ordinal()] = 13;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.BRONZE.ordinal()] = 8;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.COP.ordinal()] = 6;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.EASTER.ordinal()] = 21;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.GHOST.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.GIRL.ordinal()] = 11;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.GOLD.ordinal()] = 10;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.HALLOWEEN.ordinal()] = 18;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.KIDS.ordinal()] = 3;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.NINJA.ordinal()] = 5;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.NOT_USED_IOS_DAILY_WORLD_BIKE_INDEX_HOLDER.ordinal()] = 22;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.REGULAR.ordinal()] = 1;
            } catch (NoSuchFieldError e14) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.RETRO.ordinal()] = 7;
            } catch (NoSuchFieldError e15) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.SANTA.ordinal()] = 20;
            } catch (NoSuchFieldError e16) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.SILVER.ordinal()] = 9;
            } catch (NoSuchFieldError e17) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.SPAM.ordinal()] = 14;
            } catch (NoSuchFieldError e18) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.SUPER.ordinal()] = 2;
            } catch (NoSuchFieldError e19) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.THANKSGIVING.ordinal()] = 19;
            } catch (NoSuchFieldError e20) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.ULTRA.ordinal()] = 15;
            } catch (NoSuchFieldError e21) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_ARGENTINA.ordinal()] = 35;
            } catch (NoSuchFieldError e22) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_AUSTRALIA.ordinal()] = 25;
            } catch (NoSuchFieldError e23) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_BELGIUM.ordinal()] = 32;
            } catch (NoSuchFieldError e24) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_BRAZIL.ordinal()] = 29;
            } catch (NoSuchFieldError e25) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_ENGLAND.ordinal()] = 24;
            } catch (NoSuchFieldError e26) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_FRANCE.ordinal()] = 27;
            } catch (NoSuchFieldError e27) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_GERMANY.ordinal()] = 28;
            } catch (NoSuchFieldError e28) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_ITALY.ordinal()] = 34;
            } catch (NoSuchFieldError e29) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_JAPAN.ordinal()] = 31;
            } catch (NoSuchFieldError e30) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_MEXICO.ordinal()] = 33;
            } catch (NoSuchFieldError e31) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_NETHERLANDS.ordinal()] = 26;
            } catch (NoSuchFieldError e32) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_SPAIN.ordinal()] = 30;
            } catch (NoSuchFieldError e33) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_USA.ordinal()] = 23;
            } catch (NoSuchFieldError e34) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.ZOMBIE.ordinal()] = 16;
            } catch (NoSuchFieldError e35) {
            }
            o = iArr;
        }
        return iArr;
    }

    public b(Context context, com.topfreegames.bikerace.a.c cVar, final d dVar) {
        super(context, 2131492932);
        this.f1181a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.n = com.topfreegames.bikerace.a.f.a(cVar);
        this.f1181a = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903041, (ViewGroup) null);
        this.c = this.f1181a.findViewById(2131296263);
        this.b = (ImageView) this.f1181a.findViewById(2131296267);
        this.d = this.f1181a.findViewById(2131296264);
        this.e = this.f1181a.findViewById(2131296268);
        this.f = this.f1181a.findViewById(2131296266);
        this.g = this.f1181a.findViewById(2131296265);
        this.h = this.f1181a.findViewById(2131296269);
        this.i = (ViewGroup) this.f1181a.findViewById(2131296274);
        this.j = (ImageView) this.f1181a.findViewById(2131296272);
        this.k = (TextView) this.f1181a.findViewById(2131296270);
        this.l = this.f1181a.findViewById(2131296275);
        this.m = this.f1181a.findViewById(2131296276);
        this.l.setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.e.b.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                b.this.dismiss();
                new Thread(new Runnable() { // from class: com.topfreegames.bikerace.e.b.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        b.this.dismiss();
                    }
                }).start();
            }
        });
        this.m.setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.e.b.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                b.this.dismiss();
                final d dVar2 = dVar;
                new Thread(new Runnable() { // from class: com.topfreegames.bikerace.e.b.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        b.this.dismiss();
                        if (dVar2 != null) {
                            dVar2.a(b.this.n);
                        }
                    }
                }).start();
            }
        });
        if (cVar != null) {
            this.i.removeAllViews();
            boolean z = !cVar.d();
            Iterator<com.topfreegames.bikerace.a.a> it = cVar.b().iterator();
            while (it.hasNext()) {
                this.i.addView(new com.topfreegames.bikerace.views.s(getContext(), it.next(), z, false));
            }
        }
        a(context, this.f1181a);
        setContentView(this.f1181a);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().setFlags(1024, 1024);
        getWindow().setLayout(-1, -1);
    }

    private boolean b() {
        try {
            c cVar = new c(this, null);
            Animation animationLoadAnimation = AnimationUtils.loadAnimation(getContext(), 2130968576);
            animationLoadAnimation.setAnimationListener(cVar);
            this.b.startAnimation(animationLoadAnimation);
            Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(getContext(), 2130968579);
            animationLoadAnimation2.setAnimationListener(cVar);
            this.d.startAnimation(animationLoadAnimation2);
            Animation animationLoadAnimation3 = AnimationUtils.loadAnimation(getContext(), 2130968580);
            animationLoadAnimation3.setAnimationListener(cVar);
            this.e.startAnimation(animationLoadAnimation3);
            Animation animationLoadAnimation4 = AnimationUtils.loadAnimation(getContext(), 2130968578);
            animationLoadAnimation4.setAnimationListener(cVar);
            this.f.startAnimation(animationLoadAnimation4);
            Animation animationLoadAnimation5 = AnimationUtils.loadAnimation(getContext(), 2130968577);
            animationLoadAnimation5.setAnimationListener(cVar);
            this.g.startAnimation(animationLoadAnimation5);
            return true;
        } catch (Exception e) {
            if (ap.d()) {
                e.printStackTrace();
            }
            return false;
        }
    }

    private boolean c() {
        if (!g() || !b() || this.c == null) {
            return false;
        }
        this.c.setVisibility(0);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        try {
            if (this.c != null) {
                this.c.setVisibility(8);
            }
            if (this.b != null) {
                this.b.setVisibility(8);
            }
            if (this.d != null) {
                this.d.setVisibility(8);
            }
            if (this.e != null) {
                this.e.setVisibility(8);
            }
            if (this.f != null) {
                this.f.setVisibility(8);
            }
            if (this.g != null) {
                this.g.setVisibility(8);
            }
        } catch (Exception e) {
            if (ap.d()) {
                e.printStackTrace();
            }
            dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.h != null) {
            this.h.setVisibility(0);
        }
    }

    private void f() {
        if (this.h != null) {
            this.h.setVisibility(8);
        }
    }

    @Override // com.topfreegames.bikerace.e.a, android.app.Dialog
    public void show() {
        if (this.n != null) {
            if (c()) {
                f();
                super.show();
                return;
            } else {
                dismiss();
                return;
            }
        }
        dismiss();
    }

    private boolean g() {
        int i;
        String string;
        try {
            switch (a()[this.n.ordinal()]) {
                case 4:
                    i = 2130837515;
                    string = getContext().getString(2131099773);
                    break;
                case 5:
                    i = 2130837525;
                    string = getContext().getString(2131099794);
                    break;
                case 6:
                    i = 2130837527;
                    string = getContext().getString(2131099798);
                    break;
                case 7:
                    i = 2130837529;
                    string = getContext().getString(2131099782);
                    break;
                case 8:
                    i = 2130837510;
                    string = getContext().getString(2131099786);
                    break;
                case 9:
                    i = 2130837532;
                    string = getContext().getString(2131099790);
                    break;
                case XMLStreamConstants.ATTRIBUTE /* 10 */:
                    i = 2130837519;
                    string = getContext().getString(2131099792);
                    break;
                case XMLStreamConstants.DTD /* 11 */:
                    i = 2130837517;
                    string = getContext().getString(2131099780);
                    break;
                case XMLStreamConstants.CDATA /* 12 */:
                    i = 2130837506;
                    string = getContext().getString(2131099784);
                    break;
                case XMLStreamConstants.NAMESPACE /* 13 */:
                    i = 2130837522;
                    string = getContext().getString(2131099800);
                    break;
                case XMLStreamConstants.NOTATION_DECLARATION /* 14 */:
                    i = 2130837513;
                    string = getContext().getString(2131099796);
                    break;
                case XMLStreamConstants.ENTITY_DECLARATION /* 15 */:
                default:
                    return false;
                case 16:
                    i = 2130837536;
                    string = getContext().getString(2131099805);
                    break;
                case 17:
                    i = 2130837508;
                    string = getContext().getString(2131099788);
                    break;
                case 18:
                    i = 2130837521;
                    string = getContext().getString(2131099807);
                    break;
                case 19:
                    i = 2130837534;
                    string = getContext().getString(2131099809);
                    break;
                case 20:
                    i = 2130837531;
                    string = getContext().getString(2131099811);
                    break;
                case 21:
                    i = 2130837511;
                    string = getContext().getString(2131099813);
                    break;
            }
            if (this.b != null) {
                this.b.setImageDrawable(getContext().getResources().getDrawable(i));
            }
            if (this.j != null) {
                this.j.setImageDrawable(getContext().getResources().getDrawable(i));
            }
            if (this.k != null) {
                this.k.setText(string);
            }
            return true;
        } catch (Exception e) {
            if (!ap.d()) {
                return false;
            }
            e.printStackTrace();
            return false;
        }
    }
}
