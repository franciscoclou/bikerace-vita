package com.flurry.sdk;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class ey implements fc {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static ey f558a;
    private final List<fc> b = b();

    public static synchronized ey a() {
        if (f558a == null) {
            f558a = new ey();
        }
        return f558a;
    }

    private ey() {
    }

    @Override // com.flurry.sdk.fc
    public void f(Context context) {
        Iterator<fc> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().f(context);
        }
    }

    @Override // com.flurry.sdk.fc
    public void g(Context context) {
        Iterator<fc> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().g(context);
        }
    }

    private static List<fc> b() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ez("com.flurry.android.impl.appcloud.AppCloudModule", 10));
        arrayList.add(new ez("com.flurry.android.impl.ads.FlurryAdModule", 10));
        return Collections.unmodifiableList(arrayList);
    }
}
