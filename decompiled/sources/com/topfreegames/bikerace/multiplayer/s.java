package com.topfreegames.bikerace.multiplayer;

import com.amazonaws.javax.xml.stream.XMLStreamConstants;
import com.topfreegames.bikerace.activities.BikeRaceApplication;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: MultiplayerManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class s implements d {
    private static /* synthetic */ int[] b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ o f1332a;

    private s(o oVar) {
        this.f1332a = oVar;
    }

    /* synthetic */ s(o oVar, s sVar) {
        this(oVar);
    }

    static /* synthetic */ int[] b() {
        int[] iArr = b;
        if (iArr == null) {
            iArr = new int[com.topfreegames.bikerace.c.valuesCustom().length];
            try {
                iArr[com.topfreegames.bikerace.c.ACROBATIC.ordinal()] = 12;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.ARMY.ordinal()] = 17;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.BEAT.ordinal()] = 13;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.BRONZE.ordinal()] = 8;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.COP.ordinal()] = 6;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.EASTER.ordinal()] = 21;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.GHOST.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.GIRL.ordinal()] = 11;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.GOLD.ordinal()] = 10;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.HALLOWEEN.ordinal()] = 18;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.KIDS.ordinal()] = 3;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.NINJA.ordinal()] = 5;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.NOT_USED_IOS_DAILY_WORLD_BIKE_INDEX_HOLDER.ordinal()] = 22;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.REGULAR.ordinal()] = 1;
            } catch (NoSuchFieldError e14) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.RETRO.ordinal()] = 7;
            } catch (NoSuchFieldError e15) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.SANTA.ordinal()] = 20;
            } catch (NoSuchFieldError e16) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.SILVER.ordinal()] = 9;
            } catch (NoSuchFieldError e17) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.SPAM.ordinal()] = 14;
            } catch (NoSuchFieldError e18) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.SUPER.ordinal()] = 2;
            } catch (NoSuchFieldError e19) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.THANKSGIVING.ordinal()] = 19;
            } catch (NoSuchFieldError e20) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.ULTRA.ordinal()] = 15;
            } catch (NoSuchFieldError e21) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_ARGENTINA.ordinal()] = 35;
            } catch (NoSuchFieldError e22) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_AUSTRALIA.ordinal()] = 25;
            } catch (NoSuchFieldError e23) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_BELGIUM.ordinal()] = 32;
            } catch (NoSuchFieldError e24) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_BRAZIL.ordinal()] = 29;
            } catch (NoSuchFieldError e25) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_ENGLAND.ordinal()] = 24;
            } catch (NoSuchFieldError e26) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_FRANCE.ordinal()] = 27;
            } catch (NoSuchFieldError e27) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_GERMANY.ordinal()] = 28;
            } catch (NoSuchFieldError e28) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_ITALY.ordinal()] = 34;
            } catch (NoSuchFieldError e29) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_JAPAN.ordinal()] = 31;
            } catch (NoSuchFieldError e30) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_MEXICO.ordinal()] = 33;
            } catch (NoSuchFieldError e31) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_NETHERLANDS.ordinal()] = 26;
            } catch (NoSuchFieldError e32) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_SPAIN.ordinal()] = 30;
            } catch (NoSuchFieldError e33) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.WORLDCUP_USA.ordinal()] = 23;
            } catch (NoSuchFieldError e34) {
            }
            try {
                iArr[com.topfreegames.bikerace.c.ZOMBIE.ordinal()] = 16;
            } catch (NoSuchFieldError e35) {
            }
            b = iArr;
        }
        return iArr;
    }

    @Override // com.topfreegames.bikerace.multiplayer.d
    public void a(final l lVar, com.topfreegames.bikerace.q qVar, com.topfreegames.bikerace.p pVar, com.topfreegames.bikerace.c cVar, boolean z, int i) {
        com.topfreegames.bikerace.b.b bVarC;
        if (qVar == com.topfreegames.bikerace.q.PLAYING_AGAINST) {
            lVar = this.f1332a.a(lVar);
        }
        if (this.f1332a.i != null) {
            new Thread(new Runnable() { // from class: com.topfreegames.bikerace.multiplayer.s.1
                @Override // java.lang.Runnable
                public void run() {
                    if (s.this.f1332a.i == null) {
                        return;
                    }
                    s.this.f1332a.i.a(lVar);
                }
            }).start();
        }
        com.topfreegames.bikerace.a.f fVarA = com.topfreegames.bikerace.a.f.a(this.f1332a.p);
        com.topfreegames.bikerace.z zVarA = ((BikeRaceApplication) this.f1332a.p).a();
        if (pVar == com.topfreegames.bikerace.p.WIN) {
            if (!z) {
                zVarA.w();
                if (fVarA != null) {
                    if (i == 1) {
                        fVarA.c("AchievMultiplayerWinsLastLife");
                        zVarA.x();
                    } else {
                        fVarA.a("AchievMultiplayerWinsLastLife", 0);
                        zVarA.E();
                    }
                }
            }
            if (cVar != null && fVarA != null) {
                switch (b()[cVar.ordinal()]) {
                    case 2:
                        fVarA.c("AchievBeatSuper");
                        break;
                    case 4:
                        fVarA.c("AchievBeatGhost");
                        break;
                    case 5:
                        fVarA.c("AchievBeatNinja");
                        break;
                    case 6:
                        fVarA.c("AchievBeatCop");
                        break;
                    case 8:
                        fVarA.c("AchievBeatBronze");
                        break;
                    case 9:
                        fVarA.c("AchievBeatSilver");
                        break;
                    case XMLStreamConstants.ATTRIBUTE /* 10 */:
                        fVarA.c("AchievBeatGold");
                        break;
                }
            }
        } else if (pVar != com.topfreegames.bikerace.p.SENT) {
            zVarA.D();
            zVarA.E();
            fVarA.a("AchievConsecutiveWins", 0);
            fVarA.a("AchievMultiplayerWinsLastLife", 0);
        }
        if (!z) {
            com.topfreegames.bikerace.worldcup.o.a().a(zVarA, pVar);
        }
        if (qVar == com.topfreegames.bikerace.q.WATCHING) {
            return;
        }
        this.f1332a.u.b(lVar);
        if (pVar != com.topfreegames.bikerace.p.WIN) {
            if (pVar != com.topfreegames.bikerace.p.LOSE) {
                if (pVar == com.topfreegames.bikerace.p.SENT) {
                    com.topfreegames.bikerace.push.g.a(lVar.m(), lVar.f(), lVar.g(), com.topfreegames.bikerace.push.d.CHALLENGE, this.f1332a.f1316a.sqs());
                }
                bVarC = null;
            } else {
                bVarC = this.f1332a.u.c(lVar.f());
            }
        } else {
            bVarC = this.f1332a.u.c(lVar.l());
        }
        if (bVarC != null) {
            Integer numB = bVarC.b();
            if (numB == null) {
                numB = 0;
            }
            this.f1332a.u.a(bVarC.c(), numB.intValue() + 1);
        }
    }

    @Override // com.topfreegames.bikerace.multiplayer.d
    public void a() {
        com.topfreegames.bikerace.b.b bVarC = this.f1332a.u.c(this.f1332a.g());
        if (bVarC != null) {
            Integer numA = bVarC.a();
            if (numA == null) {
                numA = 0;
            }
            this.f1332a.u.b(this.f1332a.g(), numA.intValue() + 1);
        }
    }
}
