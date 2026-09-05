package com.topfreegames.bikerace;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/* JADX INFO: compiled from: GameUI.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class af {
    public static void a(Handler handler) {
        if (handler != null) {
            Message messageObtainMessage = handler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("Type", "Error");
            messageObtainMessage.setData(bundle);
            handler.sendMessage(messageObtainMessage);
        }
    }

    public static void b(Handler handler) {
        if (handler != null) {
            Message messageObtainMessage = handler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("Type", "Help");
            messageObtainMessage.setData(bundle);
            handler.sendMessage(messageObtainMessage);
        }
    }

    public static void c(Handler handler) {
        if (handler != null) {
            Message messageObtainMessage = handler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("Type", "Loading");
            messageObtainMessage.setData(bundle);
            handler.sendMessage(messageObtainMessage);
        }
    }

    public static void a(Handler handler, boolean z, int i, int i2) {
        if (handler != null) {
            Message messageObtainMessage = handler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("Type", "Pause");
            bundle.putBoolean("PauseMultiplayer", z);
            bundle.putInt("WorldID", i);
            bundle.putInt("LevelID", i2);
            messageObtainMessage.setData(bundle);
            handler.sendMessage(messageObtainMessage);
        }
    }

    public static void a(Handler handler, boolean z) {
        if (handler != null) {
            Message messageObtainMessage = handler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("Type", "Play");
            bundle.putBoolean("IsWatchingReplay", z);
            messageObtainMessage.setData(bundle);
            handler.sendMessage(messageObtainMessage);
        }
    }

    public static void a(Handler handler, String str, float f, boolean z) {
        if (handler != null) {
            Message messageObtainMessage = handler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("Type", "ToStart");
            bundle.putString("ToStartMsg", str);
            bundle.putFloat("ToStartBestTime", f);
            bundle.putBoolean("ToStartSinglePlayer", z);
            messageObtainMessage.setData(bundle);
            handler.sendMessage(messageObtainMessage);
        }
    }

    public static void a(Handler handler, String str) {
        if (handler != null) {
            Message messageObtainMessage = handler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("Type", "WatchingReplay");
            bundle.putString("ReplayMsg", str);
            messageObtainMessage.setData(bundle);
            handler.sendMessage(messageObtainMessage);
        }
    }

    public static void a(Handler handler, int i, float f, int i2, float f2, float f3, boolean z) {
        if (handler != null) {
            Message messageObtainMessage = handler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("Type", "Won");
            bundle.putInt("Text", i);
            bundle.putFloat("Time", f);
            bundle.putInt("NumStars", i2);
            bundle.putFloat("NextStarTime", f2);
            bundle.putFloat("BestTime", f3);
            bundle.putBoolean("CustomLevel", z);
            messageObtainMessage.setData(bundle);
            handler.sendMessage(messageObtainMessage);
        }
    }

    public static void a(Handler handler, String str, String str2, float f, int i, String str3, String str4, float f2, int i2, boolean z, p pVar, boolean z2) {
        if (handler != null) {
            Message messageObtainMessage = handler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("Type", "MultiplayerResult");
            bundle.putString("UserID", str);
            bundle.putString("UserName", str2);
            bundle.putFloat("UserTime", f);
            bundle.putInt("UserWins", i);
            bundle.putString("OpponentID", str3);
            bundle.putString("OpponentName", str4);
            bundle.putFloat("OpponentTime", f2);
            bundle.putInt("OpponentWins", i2);
            bundle.putBoolean("IsWatching", z);
            bundle.putInt("Result", pVar.ordinal());
            bundle.putBoolean("IsReplay", z2);
            messageObtainMessage.setData(bundle);
            handler.sendMessage(messageObtainMessage);
        }
    }

    public static void d(Handler handler) {
        if (handler != null) {
            Message messageObtainMessage = handler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("Type", "SkipDialog");
            messageObtainMessage.setData(bundle);
            handler.sendMessage(messageObtainMessage);
        }
    }

    public static void e(Handler handler) {
        if (handler != null) {
            Message messageObtainMessage = handler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("Type", "RatingDialog");
            messageObtainMessage.setData(bundle);
            handler.sendMessage(messageObtainMessage);
        }
    }

    public static void f(Handler handler) {
        if (handler != null) {
            Message messageObtainMessage = handler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("Type", "MultiUnlockDialog");
            messageObtainMessage.setData(bundle);
            handler.sendMessage(messageObtainMessage);
        }
    }

    public static void b(Handler handler, String str) {
        if (handler != null) {
            Message messageObtainMessage = handler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("Type", "ShopOffer");
            bundle.putString("OfferProductId", str);
            messageObtainMessage.setData(bundle);
            handler.sendMessage(messageObtainMessage);
        }
    }

    public static void g(Handler handler) {
        if (handler != null) {
            Message messageObtainMessage = handler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("Type", "ZoomOut");
            messageObtainMessage.setData(bundle);
            handler.sendMessage(messageObtainMessage);
        }
    }

    public static void h(Handler handler) {
        if (handler != null) {
            Message messageObtainMessage = handler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("Type", "Interstitial");
            messageObtainMessage.setData(bundle);
            handler.sendMessage(messageObtainMessage);
        }
    }
}
