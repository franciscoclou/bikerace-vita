package com.b.a;

import android.app.Activity;
import com.b.a.a.cm;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class at implements com.b.a.a.at<Boolean> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ d f327a;

    at(d dVar) {
        this.f327a = dVar;
    }

    @Override // com.b.a.a.at
    public final /* synthetic */ Boolean a(com.b.a.a.aw awVar) {
        boolean zA = true;
        Activity activityD = cm.a().d();
        if (activityD != null && !activityD.isFinishing() && this.f327a.m()) {
            zA = d.a(this.f327a, activityD, awVar.c);
        }
        return Boolean.valueOf(zA);
    }
}
