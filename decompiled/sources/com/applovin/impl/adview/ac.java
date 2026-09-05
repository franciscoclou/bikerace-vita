package com.applovin.impl.adview;

import android.content.Context;
import android.view.View;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class ac extends View {
    protected ac(Context context) {
        super(context);
    }

    public static ac a(Context context, ad adVar) {
        return adVar.equals(ad.WhiteXOnTransparentGrey) ? new j(context) : new k(context);
    }

    public abstract void a(float f);
}
