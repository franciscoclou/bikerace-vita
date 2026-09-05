package com.topfreegames.bikerace.multiplayer;

import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.bikerace.ap;

/* JADX INFO: compiled from: MultiplayerManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class t implements com.topfreegames.bikerace.push.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ o f1334a;

    private t(o oVar) {
        this.f1334a = oVar;
    }

    /* synthetic */ t(o oVar, t tVar) {
        this(oVar);
    }

    @Override // com.topfreegames.bikerace.push.a
    public void a(String str, String str2) throws com.topfreegames.bikerace.push.b {
        if (((BikeRaceApplication) this.f1334a.p.getApplicationContext()).e()) {
            try {
                this.f1334a.a(str, str2);
                return;
            } catch (Exception e) {
                if (ap.d()) {
                    e.printStackTrace();
                }
                throw new com.topfreegames.bikerace.push.b();
            }
        }
        throw new com.topfreegames.bikerace.push.b();
    }

    @Override // com.topfreegames.bikerace.push.a
    public void b(String str, String str2) throws com.topfreegames.bikerace.push.b {
        if (((BikeRaceApplication) this.f1334a.p.getApplicationContext()).e()) {
            try {
                this.f1334a.b(str, str2);
                return;
            } catch (Exception e) {
                if (ap.d()) {
                    e.printStackTrace();
                }
                throw new com.topfreegames.bikerace.push.b();
            }
        }
        throw new com.topfreegames.bikerace.push.b();
    }
}
