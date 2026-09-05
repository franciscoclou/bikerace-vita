package com.topfreegames.bikerace.l.b;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/* JADX INFO: compiled from: MultiplayerSQLiteOpenHelper.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a extends SQLiteOpenHelper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final SimpleDateFormat f1283a;

    public a(Context context) {
        super(context, "multiplayer", (SQLiteDatabase.CursorFactory) null, 1);
        this.f1283a = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        this.f1283a.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        String str = String.format("CREATE TABLE %s (%s TEXT PRIMARY KEY, %s BLOB, %s TEXT, %s TEXT)", "users", "id", "json", "updated_at", "synced_at");
        String str2 = String.format("CREATE TABLE %s (%s TEXT PRIMARY KEY, %s BLOB, %s TEXT, %s TEXT)", "game_sessions", "id", "json", "updated_at", "synced_at");
        sQLiteDatabase.execSQL(str);
        sQLiteDatabase.execSQL(str2);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        String str = String.format("DROP TABLE IF EXISTS %s", "users");
        String str2 = String.format("DROP TABLE IF EXISTS %s", "game_sessions");
        sQLiteDatabase.execSQL(str);
        sQLiteDatabase.execSQL(str2);
        onCreate(sQLiteDatabase);
    }

    public SimpleDateFormat a() {
        return this.f1283a;
    }
}
