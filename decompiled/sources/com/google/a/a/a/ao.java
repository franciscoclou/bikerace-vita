package com.google.a.a.a;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import java.util.HashSet;

/* JADX INFO: compiled from: PersistentAnalyticsStore.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ao extends SQLiteOpenHelper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ an f582a;
    private boolean b;
    private long c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ao(an anVar, Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        this.f582a = anVar;
        this.c = 0L;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0048  */
    private boolean a(String str, SQLiteDatabase sQLiteDatabase) throws Throwable {
        Cursor cursor;
        Cursor cursor2 = null;
        try {
            Cursor cursorQuery = sQLiteDatabase.query("SQLITE_MASTER", new String[]{"name"}, "name=?", new String[]{str}, null, null, null);
            try {
                boolean zMoveToFirst = cursorQuery.moveToFirst();
                if (cursorQuery == null) {
                    return zMoveToFirst;
                }
                cursorQuery.close();
                return zMoveToFirst;
            } catch (SQLiteException e) {
                cursor = cursorQuery;
                try {
                    ah.d("Error querying for table " + str);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return false;
                } catch (Throwable th) {
                    cursor2 = cursor;
                    th = th;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                cursor2 = cursorQuery;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (SQLiteException e2) {
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public SQLiteDatabase getWritableDatabase() {
        if (this.b && this.c + 3600000 > this.f582a.h.a()) {
            throw new SQLiteException("Database creation failed");
        }
        SQLiteDatabase writableDatabase = null;
        this.b = true;
        this.c = this.f582a.h.a();
        try {
            writableDatabase = super.getWritableDatabase();
        } catch (SQLiteException e) {
            this.f582a.e.getDatabasePath(this.f582a.f).delete();
        }
        if (writableDatabase == null) {
            writableDatabase = super.getWritableDatabase();
        }
        this.b = false;
        return writableDatabase;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        if (Build.VERSION.SDK_INT < 15) {
            Cursor cursorRawQuery = sQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", null);
            try {
                cursorRawQuery.moveToFirst();
                cursorRawQuery.close();
            } catch (Throwable th) {
                cursorRawQuery.close();
                throw th;
            }
        }
        if (!a("hits2", sQLiteDatabase)) {
            sQLiteDatabase.execSQL(an.f580a);
        } else {
            a(sQLiteDatabase);
        }
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("SELECT * FROM hits2 WHERE 0", null);
        HashSet hashSet = new HashSet();
        try {
            for (String str : cursorRawQuery.getColumnNames()) {
                hashSet.add(str);
            }
            cursorRawQuery.close();
            if (!hashSet.remove("hit_id") || !hashSet.remove("hit_url") || !hashSet.remove("hit_string") || !hashSet.remove("hit_time")) {
                throw new SQLiteException("Database column missing");
            }
            boolean z = hashSet.remove("hit_app_id") ? false : true;
            if (!hashSet.isEmpty()) {
                throw new SQLiteException("Database has extra columns");
            }
            if (z) {
                sQLiteDatabase.execSQL("ALTER TABLE hits2 ADD COLUMN hit_app_id");
            }
        } catch (Throwable th) {
            cursorRawQuery.close();
            throw th;
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        s.a(sQLiteDatabase.getPath());
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
