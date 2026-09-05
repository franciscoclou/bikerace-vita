package com.topfreegames.bikerace;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: GameAnalytics.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private SharedPreferences f1353a;
    private bb b;
    private boolean c = false;
    private u d = new u();

    public t(Context context) {
        this.f1353a = null;
        this.b = null;
        this.f1353a = context.getSharedPreferences("com.topfreegames.bikerace.analytics", 0);
        this.b = ((BikeRaceApplication) context.getApplicationContext()).a(false);
    }

    public static void a(Context context) {
        com.topfreegames.a.a.a(context);
    }

    public static void b(Context context) {
        com.topfreegames.a.a.b(context);
    }

    public void a(float f) {
        if (x()) {
            HashMap map = new HashMap();
            map.put("Param.Display.FPS", Float.toString(f));
            map.put("Param.Device.Model", Build.MODEL);
            map.put("Param.Device.Manufacturer", Build.MANUFACTURER);
            map.put("Param.Device.Version", Build.VERSION.RELEASE);
            if (f <= 0.0f) {
                com.topfreegames.a.a.a("Event.DisplayFPS.Below0", map);
                return;
            }
            if (f <= 15.0f) {
                com.topfreegames.a.a.a("Event.DisplayFPS.Between_0_15", map);
                return;
            }
            if (f <= 25.0f) {
                com.topfreegames.a.a.a("Event.DisplayFPS.Between_15_25", map);
            } else if (f <= 40.0f) {
                com.topfreegames.a.a.a("Event.DisplayFPS.Between_25_40", map);
            } else {
                com.topfreegames.a.a.a("Event.DisplayFPS.Above_40", map);
            }
        }
    }

    public void a(long j, long j2) {
        long j3 = j - j2;
        long jAbs = Math.abs(j3);
        long j4 = jAbs / 86400000;
        long j5 = jAbs - (86400000 * j4);
        long j6 = j5 / 3600000;
        long j7 = j5 - (3600000 * j6);
        long j8 = j7 / 60000;
        long j9 = (j7 - (60000 * j8)) / 1000;
        Object[] objArr = new Object[5];
        objArr[0] = j3 > 0 ? "-" : "+";
        objArr[1] = Long.valueOf(j4);
        objArr[2] = Long.valueOf(j6);
        objArr[3] = Long.valueOf(j8);
        objArr[4] = Long.valueOf(j9);
        String str = String.format("%s %d:%d:%d:%d", objArr);
        HashMap map = new HashMap();
        map.put("Param.Device.Model", Build.MODEL);
        map.put("Param.Device.Manufacturer", Build.MANUFACTURER);
        map.put("Param.Device.Version", Build.VERSION.RELEASE);
        map.put("Param.Clock.Difference", str);
        if (jAbs < 60000) {
            com.topfreegames.a.a.a("Event.Clock.Correct", map);
            return;
        }
        if (jAbs < 18000000) {
            com.topfreegames.a.a.a("Event.Clock.Wrong.Below_30min", map);
            return;
        }
        if (jAbs < 3600000) {
            com.topfreegames.a.a.a("Event.Clock.Wrong.Between_30min_1h", map);
            return;
        }
        if (jAbs < 10800000) {
            com.topfreegames.a.a.a("Event.Clock.Wrong.Between_1h_and_3h", map);
        } else if (jAbs < 86400000) {
            com.topfreegames.a.a.a("Event.Clock.Wrong.Between_3h_24h", map);
        } else {
            com.topfreegames.a.a.a("Event.Clock.Wrong.Above_24h", map);
        }
    }

    public void a(int i, int i2, boolean z) {
        c(i, i2);
        HashMap map = new HashMap();
        String string = Integer.toString(i);
        String string2 = Integer.toString(i2);
        map.put("Param.World_Level", String.format("%s_%s", string, string2));
        map.put("Param.Level.ID", string2);
        map.put("Param.World.ID", string);
        map.put("Param.Level.TimesPlayed", Integer.toString(d(i, i2)));
        map.put("Param.NumRaces.Single", Integer.toString(r()));
        map.put("Param.Multiplayer.Enable", Boolean.toString(z));
        if (z) {
            com.topfreegames.a.a.a("Event.Race.Multi.Start", map);
            return;
        }
        com.topfreegames.a.a.a("Event.Race.Single.Start", map);
        String str = String.format("OnlyOnceStartWL_%s_%s", string, string2);
        if (this.f1353a.getBoolean(str, true)) {
            com.topfreegames.a.a.a("Event.Race.Start.OnlyOnce", map);
            this.f1353a.edit().putBoolean(str, false).commit();
        }
    }

    public void a(int i, int i2, int i3, int i4) {
        HashMap map = new HashMap();
        String string = Integer.toString(i);
        String string2 = Integer.toString(i2);
        map.put("Param.World_Level", String.format("%s_%s", string, string2));
        map.put("Param.Level.ID", string2);
        map.put("Param.World.ID", string);
        map.put("Param.Multiplayer.Enable", Boolean.toString(false));
        map.put("Param.Race.Stars", Integer.toString(i3));
        map.put("Param.Stars.Total", Integer.toString(i4));
        com.topfreegames.a.a.a("Event.Race.Single.End", map);
        String str = String.format("OnlyOnceEndWL_%s_%s", string, string2);
        if (this.f1353a.getBoolean(str, true)) {
            com.topfreegames.a.a.a("Event.Race.End.OnlyOnce", map);
            this.f1353a.edit().putBoolean(str, false).commit();
        }
    }

    public void a(int i, int i2, int i3) {
        HashMap map = new HashMap();
        String string = Integer.toString(i);
        String string2 = Integer.toString(i2);
        map.put("Param.World_Level", String.format("%s_%s", string, string2));
        map.put("Param.Level.ID", string2);
        map.put("Param.World.ID", string);
        map.put("Param.Multiplayer.Enable", Boolean.toString(true));
        map.put("Param.Multiplayer.NumWins", Integer.toString(i3));
        com.topfreegames.a.a.a("Event.Race.Multi.End", map);
    }

    public void b(int i, int i2, boolean z) {
        HashMap map = new HashMap();
        String string = Integer.toString(i);
        String string2 = Integer.toString(i2);
        map.put("Param.World_Level", String.format("%s_%s", string, string2));
        map.put("Param.Level.ID", string2);
        map.put("Param.World.ID", string);
        map.put("Param.Multiplayer.Enable", Boolean.toString(z));
        map.put("Param.Level.TimesPlayed", Integer.toString(d(i, i2)));
        com.topfreegames.a.a.a("Event.Race.Retry", map);
    }

    public void a(int i, int i2) {
        HashMap map = new HashMap();
        String string = Integer.toString(i);
        String string2 = Integer.toString(i2);
        map.put("Param.World_Level", String.format("%s_%s", string, string2));
        map.put("Param.Level.ID", string2);
        map.put("Param.World.ID", string);
        map.put("Param.Multiplayer.Enable", Boolean.toString(false));
        com.topfreegames.a.a.a("Event.Race.Skip", map);
    }

    public void b(int i, int i2) {
        HashMap map = new HashMap();
        String string = Integer.toString(i);
        String string2 = Integer.toString(i2);
        map.put("Param.World_Level", String.format("%s_%s", string, string2));
        map.put("Param.Level.ID", string2);
        map.put("Param.World.ID", string);
        com.topfreegames.a.a.a("Event.Race.Next", map);
    }

    public void c(int i, int i2, boolean z) {
        HashMap map = new HashMap();
        String string = Integer.toString(i);
        String string2 = Integer.toString(i2);
        map.put("Param.World_Level", String.format("%s_%s", string, string2));
        map.put("Param.Level.ID", string2);
        map.put("Param.World.ID", string);
        com.topfreegames.a.a.a("Event.Race.Pause", map);
    }

    public void d(int i, int i2, boolean z) {
        HashMap map = new HashMap();
        String string = Integer.toString(i);
        String string2 = Integer.toString(i2);
        map.put("Param.World_Level", String.format("%s_%s", string, string2));
        map.put("Param.Level.ID", string2);
        map.put("Param.World.ID", string);
        com.topfreegames.a.a.a("Event.Race.Close", map);
    }

    public void a() {
        if (!this.c) {
            this.c = true;
            u();
            HashMap map = new HashMap();
            map.put("Param.App.TimesOpen", Integer.toString(q()));
            com.topfreegames.a.a.a("Event.App.Open", map);
        }
    }

    public void b() {
        this.c = false;
        com.topfreegames.a.a.a("Event.App.Close");
    }

    public void e(int i, int i2, boolean z) {
        HashMap map = new HashMap();
        map.put("Param.World.ID", Integer.toString(i));
        map.put("Param.World.Stars", Integer.toString(i2));
        com.topfreegames.a.a.a("Event.Menu.World.Select", map);
    }

    public void f(int i, int i2, boolean z) {
        HashMap map = new HashMap();
        map.put("Param.World.ID", Integer.toString(i));
        map.put("Param.Level.ID", Integer.toString(i2));
        com.topfreegames.a.a.a("Event.Menu.Level.Select", map);
    }

    public void c() {
        com.topfreegames.a.a.a("Event.Menu.Options.Enter");
    }

    public void g(int i, int i2, boolean z) {
        HashMap map = new HashMap();
        map.put("Param.Level.ID", Integer.toString(i2));
        map.put("Param.World.ID", Integer.toString(i));
        map.put("Param.Multiplayer.Enable", Boolean.toString(z));
        com.topfreegames.a.a.a("Event.Menu.Shop.Enter", map);
    }

    public void d() {
        com.topfreegames.a.a.a("Event.Menu.World.Enter");
    }

    public void a(int i) {
        HashMap map = new HashMap();
        map.put("Param.World.ID", Integer.toString(i));
        com.topfreegames.a.a.a("Event.Menu.Level.Enter", map);
    }

    public void a(c cVar) {
        HashMap map = new HashMap();
        map.put("Param.Bike.Type", cVar.toString());
        com.topfreegames.a.a.a("Event.Shop.Bike.Unlock", map);
    }

    public void b(c cVar) {
        HashMap map = new HashMap();
        map.put("Param.Bike.Type", cVar.toString());
        com.topfreegames.a.a.a("Event.Shop.Bike.AchievementUnlock", map);
    }

    public void a(c cVar, String str) {
        HashMap map = new HashMap();
        map.put("Param.Bike.Type", cVar.toString());
        com.topfreegames.a.a.a("Event.Shop.Bike.Bought", map);
        com.topfreegames.a.a.a(String.format("Event.Shop.Bike.Bought", str), map);
    }

    public void c(c cVar) {
        HashMap map = new HashMap();
        map.put("Param.Bike.Type", cVar.toString());
        com.topfreegames.a.a.a("Event.Shop.Bike.Refuned", map);
    }

    public void d(c cVar) {
        HashMap map = new HashMap();
        map.put("Param.Bike.Type", cVar.toString());
        com.topfreegames.a.a.a("Event.Shop.Bike.Canceled", map);
    }

    public void a(String str) {
        HashMap map = new HashMap();
        map.put("Param.Shop.ProductId", str);
        com.topfreegames.a.a.a("Event.Shop.TryAgain", map);
    }

    public void a(int i, String str) {
        HashMap map = new HashMap();
        map.put("Param.World.ID", Integer.toString(i));
        com.topfreegames.a.a.a("Event.Shop.World.Bought", map);
        com.topfreegames.a.a.a(String.format("Event.Shop.Bought.%s", str), map);
    }

    public void b(int i) {
        HashMap map = new HashMap();
        map.put("Param.World.ID", Integer.toString(i));
        com.topfreegames.a.a.a("Event.Shop.World.Refunded", map);
    }

    public void c(int i) {
        HashMap map = new HashMap();
        map.put("Param.World.ID", Integer.toString(i));
        com.topfreegames.a.a.a("Event.Shop.World.Canceled", map);
    }

    public void e() {
        com.topfreegames.a.a.a("Event.Shop.NoAds.Unlock");
    }

    public void b(String str) {
        com.topfreegames.a.a.a("Event.Shop.NoAds.Bought");
        com.topfreegames.a.a.a(String.format("Event.Shop.Bought.%s", str));
    }

    public void a(boolean z) {
        HashMap map = new HashMap();
        map.put("Param.Music.Enable", Boolean.toString(z));
        com.topfreegames.a.a.a("Event.Options.Music.Change", map);
    }

    public void b(boolean z) {
        HashMap map = new HashMap();
        map.put("Param.SoundFx.Enable", Boolean.toString(z));
        com.topfreegames.a.a.a("Event.Options.SoundFx.Change", map);
    }

    public void c(boolean z) {
        HashMap map = new HashMap();
        map.put("Param.Push.Enable", Boolean.toString(z));
        com.topfreegames.a.a.a("Event.Options.Push.Change", map);
    }

    public void f() {
        com.topfreegames.a.a.a("Event.Options.Help.Enter");
    }

    public void g() {
        com.topfreegames.a.a.a("Event.Menu.Multiplayer.Games.Enter");
    }

    public void h() {
        com.topfreegames.a.a.a("Event.Menu.Multiplayer.Friends.Play.Enter");
    }

    public void i() {
        com.topfreegames.a.a.a("Event.Menu.Multiplayer.Friends.Invite.Enter");
    }

    public void j() {
        com.topfreegames.a.a.a("Event.Menu.Multiplayer.Ranking.Enter");
    }

    public void a(Throwable th) {
        HashMap map = new HashMap();
        map.put("Param.Data.Error", c(th));
        map.put("Param.Device.Model", Build.MODEL);
        map.put("Param.Device.Manufacturer", Build.MANUFACTURER);
        map.put("Param.Device.Version", Build.VERSION.RELEASE);
        com.topfreegames.a.a.a("Event.Data.Load.Error", map);
    }

    public void b(Throwable th) {
        HashMap map = new HashMap();
        map.put("Param.Data.Error", c(th));
        map.put("Param.Device.Model", Build.MODEL);
        map.put("Param.Device.Manufacturer", Build.MANUFACTURER);
        map.put("Param.Device.Version", Build.VERSION.RELEASE);
        com.topfreegames.a.a.a("Event.Data.Save.Error", map);
    }

    public void a(String str, Throwable th) {
        HashMap map = new HashMap();
        map.put("Param.Random.Error", c(th));
        map.put("Param.Random.Where", str);
        map.put("Param.Device.Model", Build.MODEL);
        map.put("Param.Device.Manufacturer", Build.MANUFACTURER);
        map.put("Param.Device.Version", Build.VERSION.RELEASE);
        com.topfreegames.a.a.a("Event.Random.Error", map);
    }

    public void b(String str, Throwable th) {
        HashMap map = new HashMap();
        map.put("Param.Multiplayer.Error.Exception", c(th));
        map.put("Param.Multiplayer.Error.Where", str);
        map.put("Param.Device.Model", Build.MODEL);
        map.put("Param.Device.Manufacturer", Build.MANUFACTURER);
        map.put("Param.Device.Version", Build.VERSION.RELEASE);
        com.topfreegames.a.a.a("Event.Multiplayer.Service.Error", map);
    }

    public void c(String str, Throwable th) {
        HashMap map = new HashMap();
        map.put("Param.Facebook.Where", str);
        map.put("Param.Facebook.Error", c(th));
        map.put("Param.Device.Model", Build.MODEL);
        map.put("Param.Device.Manufacturer", Build.MANUFACTURER);
        map.put("Param.Device.Version", Build.VERSION.RELEASE);
        com.topfreegames.a.a.a("Event.Facebook.Error", map);
    }

    public void a(String str, String str2, Throwable th) {
        com.topfreegames.a.a.a("Event.Error", str, String.valueOf("[Dev: " + Build.MANUFACTURER + "  m=" + Build.MODEL + "  d=" + Build.DEVICE + " v=" + Build.VERSION.RELEASE + "]") + "  \n" + ("[Desc: " + str2 + "\n" + c(th) + "]"));
        com.b.a.d.a(th);
    }

    public void a(String str, String str2) {
        HashMap map = new HashMap();
        map.put("Param.Warning.Description", str2);
        map.put("Param.Warning.Where", str);
        map.put("Param.Device.Model", Build.MODEL);
        map.put("Param.Device.Manufacturer", Build.MANUFACTURER);
        map.put("Param.Device.Version", Build.VERSION.RELEASE);
        com.topfreegames.a.a.a("Event.Warning", map);
    }

    public void a(Throwable th, String str, String str2, String str3, String str4) {
        HashMap map = new HashMap();
        map.put("Param.Warning.Description", c(th));
        map.put("Param.Warning.ExceptionType", str);
        map.put("Param.Warning.ExceptionCauseLevel1", str2);
        map.put("Param.Warning.ExceptionCauseLevel2", str3);
        map.put("Param.Warning.ExceptionCauseLevel3", str4);
        com.topfreegames.a.a.a("Event.Warning.Random", map);
    }

    public void b(int i, int i2, int i3, int i4) {
        HashMap map = new HashMap();
        map.put("Param.World.ExpectedID", Integer.toString(i));
        map.put("Param.Level.ExpectedID", Integer.toString(i2));
        map.put("Param.World.ID", Integer.toString(i3));
        map.put("Param.Level.ID", Integer.toString(i4));
        com.topfreegames.a.a.a("Event.Warning.Ghost", map);
    }

    public void k() {
        com.topfreegames.a.a.a("Event.RatingPopup.UserCameBack", y());
    }

    public void l() {
        com.topfreegames.a.a.a("Event.RatingPopup.Displayed", y());
    }

    public void d(boolean z) {
        w();
        com.topfreegames.a.a.a(z ? "Event.RatingPopup.Yes" : "Event.RatingPopup.No", y());
    }

    public void e(boolean z) {
        w();
        com.topfreegames.a.a.a(z ? "Event.TwoStepRating.First.Yes" : "Event.TwoStepRating.First.No", y());
    }

    public void m() {
        com.topfreegames.a.a.a("Event.TwoStepRating.First.Displayed", y());
    }

    public void f(boolean z) {
        w();
        com.topfreegames.a.a.a(z ? "Event.TwoStepRating.Second.Yes" : "Event.TwoStepRating.Second.No", y());
    }

    public void n() {
        com.topfreegames.a.a.a("Event.TwoStepRating.Second.Displayed", y());
    }

    public void o() {
        HashMap map = new HashMap();
        map.put("Param.Memory.Max", Long.toString(Runtime.getRuntime().maxMemory() / 1024));
        map.put("Param.Memory.Free", Long.toString(Runtime.getRuntime().freeMemory() / 1024));
        map.put("Param.Device.Model", Build.MODEL);
        map.put("Param.Device.Manufacturer", Build.MANUFACTURER);
        map.put("Param.Device.Device", Build.DEVICE);
        map.put("Param.Device.Version", Build.VERSION.RELEASE);
        com.topfreegames.a.a.a("Event.Warning.LowMem", map);
    }

    public void a(String str, int i, int i2) {
        HashMap map = new HashMap();
        map.put("Param.ABTest.HyphotesisGroup", String.valueOf(str) + "_" + Integer.toString(i));
        map.put("Param.ABTest.Group", Integer.toString(i));
        map.put("Param.ABTest.Version", Integer.toString(i2));
        com.topfreegames.a.a.a("Event.ABTest", map);
    }

    public void d(String str, Throwable th) {
        HashMap map = new HashMap();
        map.put("Param.Achievement.Where", str);
        map.put("Param.Achievement.Error", c(th));
        map.put("Param.Device.Model", Build.MODEL);
        map.put("Param.Device.Manufacturer", Build.MANUFACTURER);
        map.put("Param.Device.Version", Build.VERSION.RELEASE);
        com.topfreegames.a.a.a("Event.Achievement.Error", map);
    }

    public void p() {
        com.topfreegames.a.a.a("Event.Retention.LocalNotification.Click", y());
    }

    public void a(com.topfreegames.bikerace.worldcup.a aVar) {
        com.topfreegames.a.a.a(String.format("Event.Worldcup.Part.%s.%s", aVar.a(), aVar.b()), new HashMap());
    }

    public void b(com.topfreegames.bikerace.worldcup.a aVar) {
        com.topfreegames.a.a.a(String.format("Event.Worldcup.Repeated.%s.%s", aVar.a(), aVar.b()), new HashMap());
    }

    public void e(c cVar) {
        com.topfreegames.a.a.a(String.format("Event.Worldcup.BikeComplete.%s", cVar), new HashMap());
    }

    private void u() {
        int i = this.f1353a.getInt("NumTimesAppOpenend", 0);
        SharedPreferences.Editor editorEdit = this.f1353a.edit();
        editorEdit.putInt("NumTimesAppOpenend", i + 1);
        editorEdit.commit();
    }

    public int q() {
        return this.f1353a.getInt("NumTimesAppOpenend", 0);
    }

    private void c(int i, int i2) {
        String strE = e(i, i2);
        int i3 = this.f1353a.getInt(strE, 0);
        SharedPreferences.Editor editorEdit = this.f1353a.edit();
        editorEdit.putInt(strE, i3 + 1);
        editorEdit.commit();
        v();
    }

    private int d(int i, int i2) {
        return this.f1353a.getInt(e(i, i2), 0);
    }

    private void v() {
        int i = this.f1353a.getInt("NumRacesSingle", 0);
        SharedPreferences.Editor editorEdit = this.f1353a.edit();
        editorEdit.putInt("NumRacesSingle", i + 1);
        editorEdit.commit();
    }

    public int r() {
        return this.f1353a.getInt("NumRacesSingle", 0);
    }

    private void w() {
        SharedPreferences.Editor editorEdit = this.f1353a.edit();
        editorEdit.putBoolean("UserHasRated", true);
        editorEdit.commit();
    }

    public boolean s() {
        return this.f1353a.getBoolean("UserHasRated", false);
    }

    private static String e(int i, int i2) {
        return "W" + Integer.toString(i) + "L" + Integer.toString(i2);
    }

    private String c(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    private boolean x() {
        int i = this.f1353a.getInt("DisplayFPSCallsBeforeSend", -1);
        SharedPreferences.Editor editorEdit = this.f1353a.edit();
        boolean z = false;
        if (i <= 0) {
            z = true;
            editorEdit.putInt("DisplayFPSCallsBeforeSend", 50);
        } else {
            editorEdit.putInt("DisplayFPSCallsBeforeSend", i - 1);
        }
        editorEdit.commit();
        return z;
    }

    public u t() {
        return this.d;
    }

    private Map<String, String> y() {
        HashMap map = new HashMap();
        map.put("Param.RatingPopup.TestVersion", this.b.aF());
        return map;
    }
}
