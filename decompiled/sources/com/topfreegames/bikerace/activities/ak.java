package com.topfreegames.bikerace.activities;

import com.topfreegames.bikerace.views.DynamicLoadView;
import java.util.ArrayList;

/* JADX INFO: compiled from: ShopActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ak {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ ShopActivity f1065a;
    private ArrayList<com.topfreegames.bikerace.c> b = new ArrayList<>();
    private boolean c = false;
    private boolean d = true;
    private final int e = 2;

    public ak(ShopActivity shopActivity, com.topfreegames.bikerace.c cVar) {
        this.f1065a = shopActivity;
        if (shopActivity.a(com.topfreegames.bikerace.c.EASTER, shopActivity.w.a("AchievGroupBikeEaster"), com.topfreegames.bikerace.al.b(shopActivity), !com.topfreegames.bikerace.al.a(shopActivity))) {
            this.b.add(com.topfreegames.bikerace.c.EASTER);
        }
        if (shopActivity.a(com.topfreegames.bikerace.c.SANTA, shopActivity.w.a("AchievGroupBikeHoliday"), com.topfreegames.bikerace.an.b(shopActivity), !com.topfreegames.bikerace.an.a(shopActivity))) {
            this.b.add(com.topfreegames.bikerace.c.SANTA);
        }
        if (shopActivity.a(com.topfreegames.bikerace.c.THANKSGIVING, shopActivity.w.a("AchievGroupBikeThanksgiving"), com.topfreegames.bikerace.ao.b(shopActivity), !com.topfreegames.bikerace.ao.a(shopActivity))) {
            this.b.add(com.topfreegames.bikerace.c.THANKSGIVING);
        }
        if (shopActivity.a(com.topfreegames.bikerace.c.HALLOWEEN, shopActivity.w.a("AchievGroupBikeHalloween"), com.topfreegames.bikerace.am.b(shopActivity), com.topfreegames.bikerace.am.a(shopActivity) ? false : true)) {
            this.b.add(com.topfreegames.bikerace.c.HALLOWEEN);
        }
        this.b.add(com.topfreegames.bikerace.c.GIRL);
        this.b.add(com.topfreegames.bikerace.c.RETRO);
        this.b.add(com.topfreegames.bikerace.c.ACROBATIC);
        this.b.add(com.topfreegames.bikerace.c.BRONZE);
        this.b.add(com.topfreegames.bikerace.c.NINJA);
        this.b.add(com.topfreegames.bikerace.c.SPAM);
        this.b.add(com.topfreegames.bikerace.c.COP);
        this.b.add(com.topfreegames.bikerace.c.SILVER);
        this.b.add(com.topfreegames.bikerace.c.ZOMBIE);
        this.b.add(com.topfreegames.bikerace.c.BEAT);
        this.b.add(com.topfreegames.bikerace.c.GHOST);
        this.b.add(com.topfreegames.bikerace.c.ARMY);
        this.b.add(com.topfreegames.bikerace.c.GOLD);
        this.b.add(com.topfreegames.bikerace.c.KIDS);
        this.b.add(com.topfreegames.bikerace.c.SUPER);
        this.b.add(com.topfreegames.bikerace.c.ULTRA);
        if (cVar != null || cVar != com.topfreegames.bikerace.c.REGULAR) {
            this.b.remove(cVar);
            this.b.add(0, cVar);
        }
    }

    public void a() {
        this.d = true;
        this.f1065a.b().postDelayed(c(), 100L);
        this.c = true;
    }

    public void b() {
        this.d = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Runnable c() {
        return new Runnable() { // from class: com.topfreegames.bikerace.activities.ak.1
            @Override // java.lang.Runnable
            public void run() {
                for (int i = 0; i < 2 && ak.this.b.size() > 0; i++) {
                    com.topfreegames.bikerace.c cVar = (com.topfreegames.bikerace.c) ak.this.b.remove(0);
                    DynamicLoadView dynamicLoadView = (DynamicLoadView) ak.this.f1065a.a(cVar);
                    if (dynamicLoadView != null) {
                        dynamicLoadView.a();
                        ak.this.f1065a.b(cVar);
                    }
                }
                if (ak.this.b.size() > 0 && ak.this.d) {
                    ak.this.f1065a.b().post(ak.this.c());
                }
            }
        };
    }
}
