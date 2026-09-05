package com.topfreegames.bikerace.activities;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;

/* JADX INFO: compiled from: FontUtil.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Typeface f1098a = null;
    private static Typeface b = null;

    public static final void a(Context context, View view) {
        if (f1098a == null) {
            f1098a = Typeface.createFromAsset(context.getAssets(), context.getResources().getString(2131099651));
        }
        if (b == null) {
            b = Typeface.createFromAsset(context.getAssets(), context.getResources().getString(2131099652));
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt instanceof TextView) {
                    TextView textView = (TextView) childAt;
                    Typeface typeface = textView.getTypeface();
                    int style = typeface != null ? typeface.getStyle() : 0;
                    if (style == 1) {
                        textView.setTypeface(b, style);
                    } else {
                        textView.setTypeface(f1098a, style);
                    }
                    textView.setPaintFlags(textView.getPaintFlags() | XMLChar.MASK_NCNAME);
                } else if (childAt instanceof ViewGroup) {
                    a(context, childAt);
                }
            }
        }
    }
}
