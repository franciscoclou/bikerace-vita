package com.topfreegames.bikerace.a;

import android.content.Context;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.bikerace.ap;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: AchievementsManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class f implements h {
    private static f d = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f820a;
    private volatile List<c> b = new ArrayList();
    private g c = null;
    private e e = new e();
    private boolean f = true;
    private boolean g = false;

    private f(Context context) {
        this.f820a = null;
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        this.f820a = context.getApplicationContext();
        x();
    }

    public static com.topfreegames.bikerace.c a(c cVar) {
        String strA = cVar.a();
        if (strA.equals("AchievGroupBikeGirl")) {
            return com.topfreegames.bikerace.c.GIRL;
        }
        if (strA.equals("AchievGroupBikeRetro")) {
            return com.topfreegames.bikerace.c.RETRO;
        }
        if (strA.equals("AchievGroupBikeAcrobatic")) {
            return com.topfreegames.bikerace.c.ACROBATIC;
        }
        if (strA.equals("AchievGroupBikeBronze")) {
            return com.topfreegames.bikerace.c.BRONZE;
        }
        if (strA.equals("AchievGroupBikeNinja")) {
            return com.topfreegames.bikerace.c.NINJA;
        }
        if (strA.equals("AchievGroupBikeCreateGame")) {
            return com.topfreegames.bikerace.c.SPAM;
        }
        if (strA.equals("AchievGroupBikeCop")) {
            return com.topfreegames.bikerace.c.COP;
        }
        if (strA.equals("AchievGroupBikeSilver")) {
            return com.topfreegames.bikerace.c.SILVER;
        }
        if (strA.equals("AchievGroupBikeZombie")) {
            return com.topfreegames.bikerace.c.ZOMBIE;
        }
        if (strA.equals("AchievGroupBikeBeatOthers")) {
            return com.topfreegames.bikerace.c.BEAT;
        }
        if (strA.equals("AchievGroupBike")) {
            return com.topfreegames.bikerace.c.GHOST;
        }
        if (strA.equals("AchievGroupBikeGold")) {
            return com.topfreegames.bikerace.c.GOLD;
        }
        if (strA.equals("AchievGroupBikeArmy")) {
            return com.topfreegames.bikerace.c.ARMY;
        }
        if (strA.equals("AchievGroupBikeHalloween")) {
            return com.topfreegames.bikerace.c.HALLOWEEN;
        }
        if (strA.equals("AchievGroupBikeThanksgiving")) {
            return com.topfreegames.bikerace.c.THANKSGIVING;
        }
        if (strA.equals("AchievGroupBikeHoliday")) {
            return com.topfreegames.bikerace.c.SANTA;
        }
        if (strA.equals("AchievGroupBikeEaster")) {
            return com.topfreegames.bikerace.c.EASTER;
        }
        return null;
    }

    public static f a(Context context) {
        if (d == null) {
            synchronized (f.class) {
                if (d == null) {
                    d = new f(context.getApplicationContext());
                }
            }
        }
        return d;
    }

    public void a(c cVar, boolean z) {
        cVar.b(z);
        a();
    }

    public c a(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Id cannot be null!");
        }
        synchronized (this.b) {
            for (c cVar : this.b) {
                if (cVar.a().equals(str)) {
                    return cVar;
                }
            }
            return null;
        }
    }

    public List<a> b(String str) {
        a aVarA;
        if (str == null) {
            throw new IllegalArgumentException("Id cannot be null!");
        }
        ArrayList arrayList = new ArrayList();
        synchronized (this.b) {
            for (c cVar : this.b) {
                if (cVar.d() && (aVarA = cVar.a(str)) != null && !aVarA.b()) {
                    arrayList.add(aVarA);
                }
            }
        }
        return arrayList;
    }

    @Override // com.topfreegames.bikerace.a.h
    public void a(a aVar) {
        boolean z;
        synchronized (this.b) {
            Iterator<c> it = this.b.iterator();
            while (true) {
                if (it.hasNext()) {
                    final c next = it.next();
                    if (next.f()) {
                        if (this.c != null) {
                            new Thread(new Runnable() { // from class: com.topfreegames.bikerace.a.f.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (f.this.c != null) {
                                        f.this.c.a(next);
                                    }
                                }
                            }).start();
                            z = false;
                            break;
                        }
                    }
                }
                z = true;
                break;
            }
        }
        if (z) {
            b(aVar);
        }
    }

    public void c(String str) {
        synchronized (this.b) {
            Iterator<a> it = b(str).iterator();
            while (it.hasNext()) {
                it.next().c();
            }
        }
    }

    public void a(i iVar) {
        this.e.a(iVar);
    }

    @Override // com.topfreegames.bikerace.a.h
    public void a() {
        if (!this.f) {
            this.g = true;
        } else {
            new Thread(new Runnable() { // from class: com.topfreegames.bikerace.a.f.2
                /* JADX WARN: Code duplicated, block: B:86:0x014f  */
                @Override // java.lang.Runnable
                public void run() {
                    ObjectOutputStream objectOutputStream;
                    Exception exc;
                    IOException iOException;
                    boolean z;
                    FileOutputStream fileOutputStream = null;
                    synchronized (f.this.b) {
                        try {
                            try {
                                try {
                                    FileOutputStream fileOutputStreamOpenFileOutput = f.this.f820a.openFileOutput("BikeRaceAch.tmp", 0);
                                    try {
                                        objectOutputStream = new ObjectOutputStream(fileOutputStreamOpenFileOutput);
                                        try {
                                            ArrayList arrayList = new ArrayList();
                                            Iterator it = f.this.b.iterator();
                                            while (it.hasNext()) {
                                                arrayList.add(((c) it.next()).g());
                                            }
                                            objectOutputStream.writeObject(arrayList);
                                            if (fileOutputStreamOpenFileOutput != null) {
                                                try {
                                                    fileOutputStreamOpenFileOutput.getFD().sync();
                                                    z = true;
                                                } catch (IOException e) {
                                                    z = false;
                                                }
                                            } else {
                                                z = true;
                                            }
                                            if (objectOutputStream != null) {
                                                try {
                                                    objectOutputStream.close();
                                                } catch (IOException e2) {
                                                    z = false;
                                                }
                                            }
                                        } catch (IOException e3) {
                                            fileOutputStream = fileOutputStreamOpenFileOutput;
                                            iOException = e3;
                                            if (ap.d()) {
                                                iOException.printStackTrace();
                                            }
                                            ((BikeRaceApplication) f.this.f820a).d().a(getClass().getName(), "save", iOException);
                                            if (fileOutputStream != null) {
                                                try {
                                                    fileOutputStream.getFD().sync();
                                                } catch (IOException e4) {
                                                }
                                            }
                                            if (objectOutputStream != null) {
                                                try {
                                                    objectOutputStream.close();
                                                    z = false;
                                                } catch (IOException e5) {
                                                    z = false;
                                                }
                                            } else {
                                                z = false;
                                            }
                                        } catch (Exception e6) {
                                            fileOutputStream = fileOutputStreamOpenFileOutput;
                                            exc = e6;
                                            if (ap.d()) {
                                                exc.printStackTrace();
                                            }
                                            ((BikeRaceApplication) f.this.f820a).d().a(getClass().getName(), "save", exc);
                                            if (fileOutputStream != null) {
                                                try {
                                                    fileOutputStream.getFD().sync();
                                                } catch (IOException e7) {
                                                }
                                            }
                                            if (objectOutputStream != null) {
                                                try {
                                                    objectOutputStream.close();
                                                    z = false;
                                                } catch (IOException e8) {
                                                    z = false;
                                                }
                                            } else {
                                                z = false;
                                            }
                                        } catch (Throwable th) {
                                            th = th;
                                            fileOutputStream = fileOutputStreamOpenFileOutput;
                                            if (fileOutputStream != null) {
                                                try {
                                                    fileOutputStream.getFD().sync();
                                                } catch (IOException e9) {
                                                }
                                            }
                                            if (objectOutputStream == null) {
                                                throw th;
                                            }
                                            try {
                                                objectOutputStream.close();
                                                throw th;
                                            } catch (IOException e10) {
                                                throw th;
                                            }
                                        }
                                    } catch (IOException e11) {
                                        objectOutputStream = null;
                                        fileOutputStream = fileOutputStreamOpenFileOutput;
                                        iOException = e11;
                                    } catch (Exception e12) {
                                        objectOutputStream = null;
                                        fileOutputStream = fileOutputStreamOpenFileOutput;
                                        exc = e12;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        objectOutputStream = null;
                                        fileOutputStream = fileOutputStreamOpenFileOutput;
                                    }
                                } catch (Throwable th3) {
                                    throw th3;
                                }
                            } catch (IOException e13) {
                                iOException = e13;
                                objectOutputStream = null;
                            } catch (Exception e14) {
                                exc = e14;
                                objectOutputStream = null;
                            } catch (Throwable th4) {
                                th = th4;
                                objectOutputStream = null;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                        }
                    }
                    if (z) {
                        try {
                            new File(f.this.f820a.getFilesDir(), "BikeRaceAch.tmp").renameTo(new File(f.this.f820a.getFilesDir(), "BikeRaceAch.dat"));
                            f.this.g = false;
                        } catch (NullPointerException e15) {
                            ((BikeRaceApplication) f.this.f820a.getApplicationContext()).d().b(e15);
                        }
                    }
                }
            }).start();
        }
    }

    public void a(g gVar) {
        if (gVar == null) {
            throw new IllegalArgumentException("Delegate cannot be null!");
        }
        this.c = gVar;
    }

    public void a(String str, int i) {
        synchronized (this.b) {
            Iterator<a> it = b(str).iterator();
            while (it.hasNext()) {
                it.next().a(i);
            }
        }
    }

    public void b(a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("Achievement cannot be null!");
        }
        this.e.a(aVar);
    }

    public void b(i iVar) {
        this.e.b(iVar);
    }

    public void b() {
        this.c = null;
    }

    private void d() {
        synchronized (this.b) {
            if (a("AchievGroupBikeAcrobatic") == null) {
                a aVar = new a("AchievTrickWheely", 1, this.f820a.getString(2131099981), this);
                a aVar2 = new a("AchievTrickFrontFlip", 2, this.f820a.getString(2131099982), this);
                a aVar3 = new a("AchievTrickBackFlip", 4, this.f820a.getString(2131099983), this);
                c cVar = new c("AchievGroupBikeAcrobatic");
                cVar.a(aVar);
                cVar.a(aVar2);
                cVar.a(aVar3);
                this.b.add(cVar);
                a(cVar, "AchievGroupBikeGirl");
                a(cVar, "AchievGroupBikeRetro");
            }
        }
    }

    private void e() {
        synchronized (this.b) {
            if (a("AchievGroupBikeArmy") == null) {
                a aVar = new a("AchievGetStarsUserCreated", 65, this.f820a.getString(2131099992), this);
                a aVar2 = new a("AchievGiftUserCreated", 1, this.f820a.getString(2131099993), this);
                c cVar = new c("AchievGroupBikeArmy");
                cVar.a(aVar);
                cVar.a(aVar2);
                this.b.add(cVar);
                a(cVar, "AchievGroupBikeGirl");
                a(cVar, "AchievGroupBikeRetro");
                a(cVar, "AchievGroupBikeAcrobatic");
                a(cVar, "AchievGroupBikeBronze");
                a(cVar, "AchievGroupBikeNinja");
                a(cVar, "AchievGroupBikeCreateGame");
                a(cVar, "AchievGroupBikeCop");
                a(cVar, "AchievGroupBikeSilver");
                a(cVar, "AchievGroupBikeZombie");
                a(cVar, "AchievGroupBikeBeatOthers");
                a(cVar, "AchievGroupBike");
                c cVarA = a("AchievGroupBikeGold");
                if (cVarA != null) {
                    cVarA.a(cVar);
                }
            }
        }
    }

    private void f() {
        synchronized (this.b) {
            if (a("AchievGroupBikeBeatOthers") == null) {
                a aVar = new a("AchievBeatNinja", 1, this.f820a.getString(2131099977), this);
                a aVar2 = new a("AchievBeatGhost", 1, this.f820a.getString(2131099978), this);
                a aVar3 = new a("AchievBeatCop", 1, this.f820a.getString(2131099979), this);
                a aVar4 = new a("AchievBeatSuper", 1, this.f820a.getString(2131099980), this);
                c cVar = new c("AchievGroupBikeBeatOthers");
                cVar.a(aVar);
                cVar.a(aVar3);
                cVar.a(aVar4);
                cVar.a(aVar2);
                this.b.add(cVar);
                a(cVar, "AchievGroupBikeGirl");
                a(cVar, "AchievGroupBikeRetro");
                a(cVar, "AchievGroupBikeAcrobatic");
                a(cVar, "AchievGroupBikeBronze");
                a(cVar, "AchievGroupBikeNinja");
                a(cVar, "AchievGroupBikeCreateGame");
                a(cVar, "AchievGroupBikeCop");
                a(cVar, "AchievGroupBikeSilver");
            }
        }
    }

    private void g() {
        synchronized (this.b) {
            if (a("AchievGroupBikeBronze") == null) {
                a aVar = new a("AchievAllStarsDesert", 24, this.f820a.getString(2131099962), this);
                a aVar2 = new a("AchievAllStarsArtic", 24, this.f820a.getString(2131099963), this);
                a aVar3 = new a("AchievAllStarsDunes", 24, this.f820a.getString(2131099964), this);
                a aVar4 = new a("AchievConsecutiveWins", 3, this.f820a.getString(2131099974), this);
                c cVar = new c("AchievGroupBikeBronze");
                cVar.a(aVar);
                cVar.a(aVar2);
                cVar.a(aVar3);
                cVar.a(aVar4);
                this.b.add(cVar);
                a(cVar, "AchievGroupBikeGirl");
                a(cVar, "AchievGroupBikeRetro");
                a(cVar, "AchievGroupBikeAcrobatic");
            }
        }
    }

    private void h() {
        synchronized (this.b) {
            if (a("AchievGroupBikeCop") == null) {
                a aVar = new a("AchievNumberFacebookFriends", 5, this.f820a.getString(2131099961), this);
                c cVar = new c("AchievGroupBikeCop");
                cVar.a(aVar);
                this.b.add(cVar);
                a(cVar, "AchievGroupBikeGirl");
                a(cVar, "AchievGroupBikeRetro");
                a(cVar, "AchievGroupBikeAcrobatic");
                a(cVar, "AchievGroupBikeBronze");
                a(cVar, "AchievGroupBikeNinja");
                a(cVar, "AchievGroupBikeCreateGame");
            }
        }
    }

    private void i() {
        synchronized (this.b) {
            if (a("AchievGroupBikeCreateGame") == null) {
                a aVar = new a("AchievCreateGameFacebook", 1, this.f820a.getString(2131099984), this);
                a aVar2 = new a("AchievCreateGameRandom", 1, this.f820a.getString(2131099985), this);
                a aVar3 = new a("AchievCreateGameSMS", 1, this.f820a.getString(2131099986), this);
                a aVar4 = new a("AchievCreateGameEmail", 1, this.f820a.getString(2131099987), this);
                c cVar = new c("AchievGroupBikeCreateGame");
                cVar.a(aVar);
                cVar.a(aVar2);
                cVar.a(aVar3);
                cVar.a(aVar4);
                this.b.add(cVar);
                a(cVar, "AchievGroupBikeGirl");
                a(cVar, "AchievGroupBikeRetro");
                a(cVar, "AchievGroupBikeAcrobatic");
                a(cVar, "AchievGroupBikeBronze");
                a(cVar, "AchievGroupBikeNinja");
            }
        }
    }

    private void a(c cVar, String str) {
        c cVarA = a(str);
        if (cVarA != null) {
            cVar.a(cVarA);
            return;
        }
        if (ap.d()) {
            System.err.println("dependency: null " + str);
        }
        ((BikeRaceApplication) this.f820a.getApplicationContext()).d().d("addZombie", new NullPointerException(str));
    }

    private void j() {
        synchronized (this.b) {
            if (a("AchievGroupBike") == null) {
                a aVar = new a("AchievNumberFacebookFriends", 20, this.f820a.getString(2131099961), this);
                c cVar = new c("AchievGroupBike");
                cVar.a(aVar);
                this.b.add(cVar);
                a(cVar, "AchievGroupBikeGirl");
                a(cVar, "AchievGroupBikeRetro");
                a(cVar, "AchievGroupBikeAcrobatic");
                a(cVar, "AchievGroupBikeBronze");
                a(cVar, "AchievGroupBikeNinja");
                a(cVar, "AchievGroupBikeCreateGame");
                a(cVar, "AchievGroupBikeCop");
                a(cVar, "AchievGroupBikeSilver");
                a(cVar, "AchievGroupBikeBeatOthers");
            }
        }
    }

    private void k() {
        synchronized (this.b) {
            if (a("AchievGroupBikeGirl") == null) {
                a aVar = new a("AchievGetStars", 12, this.f820a.getString(2131099955), this);
                a aVar2 = new a("AchievTrickBackFlip", 1, this.f820a.getString(2131099956), this);
                c cVar = new c("AchievGroupBikeGirl");
                cVar.a(aVar);
                cVar.a(aVar2);
                this.b.add(cVar);
            }
        }
    }

    private void l() {
        synchronized (this.b) {
            if (a("AchievGroupBikeGold") == null) {
                a aVar = new a("AchievAllStarsDesert2", 24, this.f820a.getString(2131099968), this);
                a aVar2 = new a("AchievAllStarsArtic2", 24, this.f820a.getString(2131099969), this);
                a aVar3 = new a("AchievConsecutiveWins", 12, this.f820a.getString(2131099974), this);
                a aVar4 = new a("AchievBeatSilver", 1, this.f820a.getString(2131099976), this);
                a aVar5 = new a("AchievMultiplayerWins", 5000, this.f820a.getString(2131099957), this);
                c cVar = new c("AchievGroupBikeGold");
                cVar.a(aVar);
                cVar.a(aVar2);
                cVar.a(aVar4);
                cVar.a(aVar3);
                cVar.a(aVar5);
                this.b.add(cVar);
                a(cVar, "AchievGroupBikeGirl");
                a(cVar, "AchievGroupBikeRetro");
                a(cVar, "AchievGroupBikeAcrobatic");
                a(cVar, "AchievGroupBikeBronze");
                a(cVar, "AchievGroupBikeNinja");
                a(cVar, "AchievGroupBikeCreateGame");
                a(cVar, "AchievGroupBikeCop");
                a(cVar, "AchievGroupBikeSilver");
                a(cVar, "AchievGroupBikeBeatOthers");
                a(cVar, "AchievGroupBike");
            }
        }
    }

    private void m() {
        synchronized (this.b) {
            if (a("AchievGroupBikeHalloween") == null) {
                a aVar = new a("AchievConsecutiveWins", 10, this.f820a.getString(2131099974), this);
                a aVar2 = new a("AchievAllStarsHalloween", 24, this.f820a.getString(2131099970), this);
                c cVar = new c("AchievGroupBikeHalloween");
                cVar.a(aVar);
                cVar.a(aVar2);
                this.b.add(cVar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        k();
        q();
        d();
        g();
        p();
        i();
        h();
        r();
        f();
        j();
        l();
        v();
        e();
        m();
        s();
        t();
        u();
        a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        synchronized (this.b) {
            c cVarA = a("AchievGroupBikeRetro");
            if (cVarA.b().size() <= 1) {
                a aVar = new a("AchievLikeBikeRacePage", 1, this.f820a.getString(2131099959), this);
                a aVar2 = new a("AchievTopFreeGamesPage", 1, this.f820a.getString(2131099960), this);
                cVarA.a(aVar);
                cVarA.a(aVar2);
            }
            v();
            e();
            m();
            s();
            t();
            u();
        }
    }

    private void p() {
        synchronized (this.b) {
            if (a("AchievGroupBikeNinja") == null) {
                a aVar = new a("AchievMultiplayerWins", 250, this.f820a.getString(2131099957), this.f820a.getString(2131099958), this);
                c cVar = new c("AchievGroupBikeNinja");
                cVar.a(aVar);
                this.b.add(cVar);
                a(cVar, "AchievGroupBikeGirl");
                a(cVar, "AchievGroupBikeRetro");
                a(cVar, "AchievGroupBikeAcrobatic");
                a(cVar, "AchievGroupBikeBronze");
            }
        }
    }

    private void q() {
        synchronized (this.b) {
            if (a("AchievGroupBikeRetro") == null) {
                a aVar = new a("AchievMultiplayerWins", 5, this.f820a.getString(2131099957), this.f820a.getString(2131099958), this);
                a aVar2 = new a("AchievLikeBikeRacePage", 1, this.f820a.getString(2131099959), this);
                a aVar3 = new a("AchievTopFreeGamesPage", 1, this.f820a.getString(2131099960), this);
                c cVar = new c("AchievGroupBikeRetro");
                cVar.a(aVar);
                cVar.a(aVar2);
                cVar.a(aVar3);
                this.b.add(cVar);
                a(cVar, "AchievGroupBikeGirl");
            }
        }
    }

    private void r() {
        synchronized (this.b) {
            if (a("AchievGroupBikeSilver") == null) {
                a aVar = new a("AchievAllStarsHills", 24, this.f820a.getString(2131099965), this);
                a aVar2 = new a("AchievAllStarsBeach", 24, this.f820a.getString(2131099966), this);
                a aVar3 = new a("AchievAllStarsSavanna", 24, this.f820a.getString(2131099967), this);
                a aVar4 = new a("AchievConsecutiveWins", 7, this.f820a.getString(2131099974), this);
                a aVar5 = new a("AchievBeatBronze", 1, this.f820a.getString(2131099975), this);
                c cVar = new c("AchievGroupBikeSilver");
                cVar.a(aVar);
                cVar.a(aVar2);
                cVar.a(aVar3);
                cVar.a(aVar4);
                cVar.a(aVar5);
                this.b.add(cVar);
                a(cVar, "AchievGroupBikeGirl");
                a(cVar, "AchievGroupBikeRetro");
                a(cVar, "AchievGroupBikeAcrobatic");
                a(cVar, "AchievGroupBikeBronze");
                a(cVar, "AchievGroupBikeNinja");
                a(cVar, "AchievGroupBikeCreateGame");
                a(cVar, "AchievGroupBikeCop");
            }
        }
    }

    private void s() {
        synchronized (this.b) {
            if (a("AchievGroupBikeThanksgiving") == null) {
                a aVar = new a("AchievMultiplayerWins", 100, this.f820a.getString(2131099957), this);
                a aVar2 = new a("AchievAllStarsThanksgiving", 24, this.f820a.getString(2131099971), this);
                c cVar = new c("AchievGroupBikeThanksgiving");
                cVar.a(aVar2);
                cVar.a(aVar);
                this.b.add(cVar);
            }
        }
    }

    private void t() {
        synchronized (this.b) {
            if (a("AchievGroupBikeHoliday") == null) {
                a aVar = new a("AchievConsecutiveWins", 12, this.f820a.getString(2131099974), this);
                a aVar2 = new a("AchievAllStarsHoliday3", 24, this.f820a.getString(2131099972), this);
                c cVar = new c("AchievGroupBikeHoliday");
                cVar.a(aVar);
                cVar.a(aVar2);
                this.b.add(cVar);
            }
        }
    }

    private void u() {
        synchronized (this.b) {
            if (a("AchievGroupBikeEaster") == null) {
                a aVar = new a("AchievAllStarsEaster", 24, this.f820a.getString(2131099973), this);
                a aVar2 = new a("AchievEasterEggs", 26, this.f820a.getString(2131099994), this);
                c cVar = new c("AchievGroupBikeEaster");
                cVar.a(aVar2);
                cVar.a(aVar);
                this.b.add(cVar);
            }
        }
    }

    private void v() {
        synchronized (this.b) {
            if (a("AchievGroupBikeZombie") == null) {
                a aVar = new a("AchievDieXTimes", 50, this.f820a.getString(2131099988), this);
                a aVar2 = new a("AchievUserCreatedLevel", 1, this.f820a.getString(2131099989), this);
                a aVar3 = new a("AchievHighSpeed", 1, this.f820a.getString(2131099990), this);
                a aVar4 = new a("AchievMultiplayerWinsLastLife", 4, this.f820a.getString(2131099991), this);
                c cVar = new c("AchievGroupBikeZombie");
                cVar.a(aVar);
                cVar.a(aVar2);
                cVar.a(aVar3);
                cVar.a(aVar4);
                this.b.add(cVar);
                a(cVar, "AchievGroupBikeGirl");
                a(cVar, "AchievGroupBikeRetro");
                a(cVar, "AchievGroupBikeAcrobatic");
                a(cVar, "AchievGroupBikeBronze");
                a(cVar, "AchievGroupBikeNinja");
                a(cVar, "AchievGroupBikeCreateGame");
                a(cVar, "AchievGroupBikeCop");
                a(cVar, "AchievGroupBikeSilver");
                c cVarA = a("AchievGroupBikeBeatOthers");
                if (cVarA != null) {
                    cVarA.a(cVar);
                }
                c cVarA2 = a("AchievGroupBike");
                if (cVarA2 != null) {
                    cVarA2.a(cVar);
                }
                c cVarA3 = a("AchievGroupBikeGold");
                if (cVarA3 != null) {
                    cVarA3.a(cVar);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        if (!((BikeRaceApplication) this.f820a).f()) {
            c("AchievCreateGameSMS");
        }
    }

    private void x() {
        new Thread(new Runnable() { // from class: com.topfreegames.bikerace.a.f.3
            @Override // java.lang.Runnable
            public void run() {
                ObjectInputStream objectInputStream;
                ObjectInputStream objectInputStream2;
                ObjectInputStream objectInputStream3;
                ObjectInputStream objectInputStream4;
                ObjectInputStream objectInputStream5;
                ObjectInputStream objectInputStream6;
                ObjectInputStream objectInputStream7;
                ObjectInputStream objectInputStream8;
                synchronized (f.this.b) {
                    ObjectInputStream objectInputStream9 = null;
                    try {
                        try {
                            try {
                                ObjectInputStream objectInputStream10 = new ObjectInputStream(f.this.f820a.getApplicationContext().openFileInput("BikeRaceAch.dat"));
                                try {
                                    List list = (List) objectInputStream10.readObject();
                                    f.this.b = new ArrayList();
                                    if (list != null) {
                                        Iterator it = list.iterator();
                                        while (it.hasNext()) {
                                            f.this.b.add(new c((d) it.next(), f.this));
                                        }
                                    }
                                    if (objectInputStream10 != null) {
                                        try {
                                            objectInputStream10.close();
                                        } catch (IOException e) {
                                        }
                                    }
                                } catch (EOFException e2) {
                                    objectInputStream8 = objectInputStream10;
                                    e = e2;
                                    if (ap.d()) {
                                        e.printStackTrace();
                                    }
                                    ((BikeRaceApplication) f.this.f820a).d().a(getClass().getName(), "load", e);
                                    if (objectInputStream8 != null) {
                                        try {
                                            objectInputStream8.close();
                                        } catch (IOException e3) {
                                        }
                                    }
                                } catch (FileNotFoundException e4) {
                                    objectInputStream9 = objectInputStream10;
                                    if (objectInputStream9 != null) {
                                        try {
                                            objectInputStream9.close();
                                        } catch (IOException e5) {
                                        }
                                    }
                                } catch (IOException e6) {
                                    objectInputStream7 = objectInputStream10;
                                    e = e6;
                                    if (ap.d()) {
                                        e.printStackTrace();
                                    }
                                    ((BikeRaceApplication) f.this.f820a).d().a(getClass().getName(), "load", e);
                                    if (objectInputStream7 != null) {
                                        try {
                                            objectInputStream7.close();
                                        } catch (IOException e7) {
                                        }
                                    }
                                } catch (ArrayIndexOutOfBoundsException e8) {
                                    objectInputStream6 = objectInputStream10;
                                    e = e8;
                                    if (ap.d()) {
                                        e.printStackTrace();
                                    }
                                    ((BikeRaceApplication) f.this.f820a).d().a(getClass().getName(), "load", e);
                                    if (objectInputStream6 != null) {
                                        try {
                                            objectInputStream6.close();
                                        } catch (IOException e9) {
                                        }
                                    }
                                } catch (ClassNotFoundException e10) {
                                    objectInputStream5 = objectInputStream10;
                                    e = e10;
                                    if (ap.d()) {
                                        e.printStackTrace();
                                    }
                                    ((BikeRaceApplication) f.this.f820a).d().a(getClass().getName(), "load", e);
                                    if (objectInputStream5 != null) {
                                        try {
                                            objectInputStream5.close();
                                        } catch (IOException e11) {
                                        }
                                    }
                                } catch (IllegalArgumentException e12) {
                                    objectInputStream4 = objectInputStream10;
                                    e = e12;
                                    if (ap.d()) {
                                        e.printStackTrace();
                                    }
                                    ((BikeRaceApplication) f.this.f820a).d().a(getClass().getName(), "load", e);
                                    if (objectInputStream4 != null) {
                                        try {
                                            objectInputStream4.close();
                                        } catch (IOException e13) {
                                        }
                                    }
                                } catch (IndexOutOfBoundsException e14) {
                                    objectInputStream3 = objectInputStream10;
                                    e = e14;
                                    if (ap.d()) {
                                        e.printStackTrace();
                                    }
                                    ((BikeRaceApplication) f.this.f820a).d().a(getClass().getName(), "load", e);
                                    if (objectInputStream3 != null) {
                                        try {
                                            objectInputStream3.close();
                                        } catch (IOException e15) {
                                        }
                                    }
                                } catch (Exception e16) {
                                    objectInputStream2 = objectInputStream10;
                                    e = e16;
                                    if (ap.d()) {
                                        e.printStackTrace();
                                    }
                                    ((BikeRaceApplication) f.this.f820a).d().a(getClass().getName(), "load", e);
                                    if (objectInputStream2 != null) {
                                        try {
                                            objectInputStream2.close();
                                        } catch (IOException e17) {
                                        }
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    objectInputStream = objectInputStream10;
                                    if (objectInputStream != null) {
                                        try {
                                            objectInputStream.close();
                                        } catch (IOException e18) {
                                        }
                                    }
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                throw th2;
                            }
                        } catch (EOFException e19) {
                            e = e19;
                            objectInputStream8 = null;
                        } catch (FileNotFoundException e20) {
                        } catch (IOException e21) {
                            e = e21;
                            objectInputStream7 = null;
                        } catch (ArrayIndexOutOfBoundsException e22) {
                            e = e22;
                            objectInputStream6 = null;
                        } catch (ClassNotFoundException e23) {
                            e = e23;
                            objectInputStream5 = null;
                        } catch (IllegalArgumentException e24) {
                            e = e24;
                            objectInputStream4 = null;
                        } catch (IndexOutOfBoundsException e25) {
                            e = e25;
                            objectInputStream3 = null;
                        } catch (Exception e26) {
                            e = e26;
                            objectInputStream2 = null;
                        } catch (Throwable th3) {
                            objectInputStream = null;
                            th = th3;
                        }
                        if (f.this.b.size() <= 0) {
                            f.this.y();
                        }
                        f.this.o();
                        f.this.w();
                        f.this.n();
                        f.this.g = false;
                    } catch (Throwable th4) {
                        th = th4;
                    }
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        this.b = new ArrayList();
        n();
    }

    public void a(boolean z) {
        this.f = z;
    }

    public boolean c() {
        return this.g;
    }
}
