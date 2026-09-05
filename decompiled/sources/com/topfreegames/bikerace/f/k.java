package com.topfreegames.bikerace.f;

import android.app.Activity;
import java.util.LinkedList;

/* JADX INFO: compiled from: GiftTasks.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private LinkedList<j> f1235a = new LinkedList<>();

    public void a(j jVar) {
        if (jVar != null) {
            this.f1235a.add(jVar);
        }
    }

    public void a(e eVar, Activity activity) {
        while (true) {
            j jVarPoll = this.f1235a.poll();
            if (jVarPoll != null) {
                jVarPoll.a(eVar, activity);
                jVarPoll.run();
            } else {
                return;
            }
        }
    }

    public void a() {
        this.f1235a.clear();
    }
}
