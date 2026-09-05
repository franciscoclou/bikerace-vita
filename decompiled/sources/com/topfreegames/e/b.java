package com.topfreegames.e;

import android.app.Activity;
import android.content.Context;

/* JADX INFO: compiled from: TopFacebookDialogExhibitionManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b implements com.topfreegames.e.a.g {
    private static b b = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f1502a;
    private d c;

    private b(Context context) {
        this.f1502a = context;
    }

    public static b a(Context context) {
        if (b == null) {
            b = new b(context);
        }
        return b;
    }

    public void a(Activity activity, String str, String str2, String str3, d dVar) {
        if (str2 == null) {
            str2 = activity.getString(2131100009);
        }
        String string = activity.getString(2131100013, new Object[]{str2});
        this.c = dVar;
        com.topfreegames.e.a.a.b().a(activity, string, str, "http://s3.topfreegames.com/bikerace/icone114.png", "http://www.topfreegames.com/bikerace", "Bike Race", activity.getString(2131100012), this);
    }

    private void a(c cVar) {
        if (this.c != null) {
            this.c.a(cVar);
        }
    }

    @Override // com.topfreegames.e.a.g
    public void a(boolean z) {
        c cVar = c.completed;
        if (!z) {
            cVar = c.cancelled;
        }
        a(cVar);
    }

    @Override // com.topfreegames.e.a.g
    public void g() {
        a(c.cancelled);
    }
}
