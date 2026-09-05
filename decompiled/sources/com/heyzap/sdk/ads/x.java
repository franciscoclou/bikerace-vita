package com.heyzap.sdk.ads;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* JADX INFO: compiled from: VideoControlView.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class x extends FrameLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f803a;
    public View b;
    public TextView c;
    public y d;
    private TextView e;
    private SimpleDateFormat f;
    private RelativeLayout g;
    private RelativeLayout h;

    public x(Context context, w wVar) {
        super(context);
        this.f803a = "Skip";
        setBackgroundColor(0);
        setOnClickListener(new View.OnClickListener() { // from class: com.heyzap.sdk.ads.x.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (x.this.d != null) {
                    x.this.d.c();
                }
            }
        });
        b();
    }

    public void a(int i, final float f) {
        final DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        if (this.f == null) {
            this.f = new SimpleDateFormat("s", Locale.US);
        }
        final SpannableString spannableString = new SpannableString(i >= 1000 ? this.f.format(Integer.valueOf(i)) : "");
        spannableString.setSpan(new StyleSpan(1), 0, spannableString.length(), 0);
        ((Activity) getContext()).runOnUiThread(new Runnable() { // from class: com.heyzap.sdk.ads.x.2
            @Override // java.lang.Runnable
            public void run() {
                if (spannableString != null) {
                    x.this.c.setText(spannableString);
                }
                ViewGroup.LayoutParams layoutParams = x.this.b.getLayoutParams();
                layoutParams.width = (int) (f * displayMetrics.widthPixels);
                x.this.b.setLayoutParams(layoutParams);
            }
        });
    }

    public void a() {
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.heyzap.sdk.ads.x.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (x.this.d != null) {
                    x.this.d.b();
                }
            }
        };
        this.h = new RelativeLayout(getContext());
        this.h.setBackgroundColor(0);
        this.h.setOnClickListener(onClickListener);
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(R.drawable.ic_menu_close_clear_cancel);
        imageView.setPadding(0, com.heyzap.internal.l.a(getContext(), 10), com.heyzap.internal.l.a(getContext(), 10), 0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(11);
        this.h.addView(imageView, layoutParams);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(com.heyzap.internal.l.a(getContext(), 100), com.heyzap.internal.l.a(getContext(), 100));
        layoutParams2.gravity = 53;
        addView(this.h, layoutParams2);
    }

    /* JADX WARN: Type inference failed for: r0v18, types: [com.heyzap.sdk.ads.x$5] */
    public void a(Boolean bool, long j) {
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.heyzap.sdk.ads.x.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (x.this.d != null) {
                    x.this.d.a();
                }
            }
        };
        this.g = new RelativeLayout(getContext());
        this.g.setBackgroundColor(0);
        this.g.setOnClickListener(onClickListener);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(0);
        linearLayout.setBackgroundColor(0);
        linearLayout.setGravity(16);
        linearLayout.setPadding(0, com.heyzap.internal.l.a(getContext(), 9), com.heyzap.internal.l.a(getContext(), 9), 0);
        this.e = new TextView(getContext());
        this.e.setTextSize(20.0f);
        this.e.setTextColor(-1);
        this.e.setGravity(17);
        this.e.setShadowLayer(0.01f, -2.0f, 2.0f, -7829368);
        int iA = com.heyzap.internal.l.b() < 11 ? com.heyzap.internal.l.a(getContext(), 7) : 0;
        this.e.setPadding(com.heyzap.internal.l.a(getContext(), 7), com.heyzap.internal.l.a(getContext(), -2), iA, 0);
        linearLayout.addView(this.e, new LinearLayout.LayoutParams(-2, -2));
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(R.drawable.ic_media_next);
        if (com.heyzap.internal.l.b() < 11) {
            imageView.setPadding(0, 0, iA, 0);
        }
        linearLayout.addView(imageView, new LinearLayout.LayoutParams(-2, -2));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(11);
        this.g.addView(linearLayout, layoutParams);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(com.heyzap.internal.l.a(getContext(), 200), com.heyzap.internal.l.a(getContext(), 150));
        layoutParams2.gravity = 5;
        addView(this.g, layoutParams2);
        if (!bool.booleanValue()) {
            this.g.setVisibility(0);
            SpannableString spannableString = new SpannableString(this.f803a);
            spannableString.setSpan(new StyleSpan(1), 0, spannableString.length(), 0);
            this.e.setText(spannableString);
            return;
        }
        this.g.setEnabled(false);
        new CountDownTimer(j, 100L) { // from class: com.heyzap.sdk.ads.x.5
            @Override // android.os.CountDownTimer
            public void onTick(long j2) {
                SpannableString spannableString2 = new SpannableString("Skip in " + String.format("%d", Integer.valueOf((int) Math.ceil(j2 / 1000.0d))));
                spannableString2.setSpan(new StyleSpan(1), 0, spannableString2.length(), 0);
                x.this.e.setText(spannableString2);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                x.this.g.setEnabled(true);
                SpannableString spannableString2 = new SpannableString(x.this.f803a);
                spannableString2.setSpan(new StyleSpan(1), 0, spannableString2.length(), 0);
                x.this.e.setText(spannableString2);
                x.this.e.setTextColor(-1);
            }
        }.start();
    }

    public void b() {
        this.b = new RelativeLayout(getContext());
        this.b.setBackgroundColor(16777215);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(0, com.heyzap.internal.l.a(getContext(), 4));
        layoutParams.gravity = 83;
        addView(this.b, layoutParams);
        this.c = new TextView(getContext());
        this.c.setTextColor(-1);
        this.c.setGravity(17);
        if (!com.heyzap.internal.l.g(getContext())) {
            this.c.setPadding(10, 10, 10, 10);
        }
        this.c.setGravity(3);
        this.c.setTextSize(40.0f);
        this.c.setShadowLayer(0.01f, -2.0f, 2.0f, -7829368);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 83;
        layoutParams2.leftMargin = com.heyzap.internal.l.a(getContext(), 12);
        addView(this.c, layoutParams2);
    }

    public void a(y yVar) {
        this.d = yVar;
    }
}
