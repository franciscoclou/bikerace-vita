package com.b.a;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.widget.ScrollView;
import android.widget.TextView;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class aw implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ bb f328a;
    final /* synthetic */ d b;
    private /* synthetic */ Activity c;
    private /* synthetic */ ab d;
    private /* synthetic */ com.b.a.a.ap e;

    aw(d dVar, Activity activity, bb bbVar, ab abVar, com.b.a.a.ap apVar) {
        this.b = dVar;
        this.c = activity;
        this.f328a = bbVar;
        this.d = abVar;
        this.e = apVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.c);
        ax axVar = new ax(this);
        float f = this.c.getResources().getDisplayMetrics().density;
        int iA = d.a(this.b, f, 5);
        TextView textView = new TextView(this.c);
        textView.setAutoLinkMask(15);
        textView.setText(this.d.b());
        textView.setTextAppearance(this.c, R.style.TextAppearance.Medium);
        textView.setPadding(iA, iA, iA, iA);
        textView.setFocusable(false);
        ScrollView scrollView = new ScrollView(this.c);
        scrollView.setPadding(d.a(this.b, f, 14), d.a(this.b, f, 2), d.a(this.b, f, 10), d.a(this.b, f, 12));
        scrollView.addView(textView);
        builder.setView(scrollView).setTitle(this.d.a()).setCancelable(false).setNeutralButton(this.d.c(), axVar);
        if (this.e.d) {
            builder.setNegativeButton(this.d.e(), new ay(this));
        }
        if (this.e.f) {
            builder.setPositiveButton(this.d.d(), new az(this));
        }
        builder.show();
    }
}
