package com.topfreegames.bikerace.l.b;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.amazonaws.f.k;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* JADX INFO: compiled from: SqliteGameSessionRepository.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b implements com.topfreegames.bikerace.l.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final a f1284a;
    private volatile boolean b = false;
    private volatile boolean c = false;

    public b(a aVar) {
        this.f1284a = aVar;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0080  */
    public com.topfreegames.bikerace.b.a a(String str, boolean z) throws Throwable {
        Cursor cursorQuery;
        Cursor cursor = null;
        try {
            try {
                cursorQuery = c().query("game_sessions", new String[]{"json"}, String.format("%s=?", "id"), new String[]{str}, null, null, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.getCount() == 1) {
                            cursorQuery.moveToFirst();
                            com.topfreegames.bikerace.b.a aVarA = com.topfreegames.bikerace.l.a.a(cursorQuery.getBlob(0));
                            Log.i("SqliteGameSessionRepository", String.format("Retrived GameSession(id:%s) from local database.", str));
                            if (aVarA != null && z && !aVarA.i().booleanValue()) {
                                aVarA = null;
                            }
                            if (cursorQuery == null) {
                                return aVarA;
                            }
                            cursorQuery.close();
                            return aVarA;
                        }
                    } catch (IOException e) {
                        e = e;
                        e.printStackTrace();
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                    } catch (ClassNotFoundException e2) {
                        e = e2;
                        e.printStackTrace();
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Throwable th) {
                th = th;
                if (0 != 0) {
                    cursor.close();
                }
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            cursorQuery = null;
        } catch (ClassNotFoundException e4) {
            e = e4;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
        return null;
    }

    @Override // com.topfreegames.bikerace.l.b
    public List<com.topfreegames.bikerace.b.a> a(String[] strArr, String str, boolean z) throws Throwable {
        this.c = false;
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str2 : strArr) {
            com.topfreegames.bikerace.b.a aVarA = a(str2, z);
            if (aVarA != null && (!z || aVarA.i().booleanValue())) {
                arrayList.add(aVarA);
            }
            if (this.c || Thread.interrupted()) {
                throw new InterruptedException();
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v18 */
    /* JADX WARN: Type inference failed for: r8v19 */
    /* JADX WARN: Type inference failed for: r8v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r8v20 */
    /* JADX WARN: Type inference failed for: r8v21 */
    /* JADX WARN: Type inference failed for: r8v22 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r8v5, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r8v6, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9 */
    private Date a(String str) throws Throwable {
        ?? Query;
        Date date;
        ?? r8 = 0;
        r8 = 0;
        r8 = 0;
        r8 = 0;
        ?? r9 = 0;
        ?? r10 = 0;
        ?? r11 = 0;
        try {
            try {
                try {
                    Query = c().query("game_sessions", new String[]{"synced_at"}, String.format("%s=?", "id"), new String[]{str}, null, null, null);
                    if (Query != 0) {
                        try {
                            Query.moveToFirst();
                            date = b().parse(Query.getString(0));
                            Query = Query;
                            if (Query != 0) {
                                Query.close();
                                Query = Query;
                            }
                        } catch (CursorIndexOutOfBoundsException e) {
                            r9 = Query;
                            Query = 0;
                            Query = 0;
                            date = new Date(0L);
                            r8 = r9;
                            if (r9 != 0) {
                                r9.close();
                                r8 = r9;
                            }
                        } catch (NullPointerException e2) {
                            r10 = Query;
                            Query = 0;
                            Query = 0;
                            date = new Date(0L);
                            r8 = r10;
                            if (r10 != 0) {
                                r10.close();
                                r8 = r10;
                            }
                        } catch (ParseException e3) {
                            date = new Date(0L);
                            if (Query != 0) {
                                Query.close();
                            }
                            return date;
                        } catch (Exception e4) {
                            r11 = Query;
                            Query = 0;
                            Query = 0;
                            date = new Date(0L);
                            r8 = r11;
                            if (r11 != 0) {
                                r11.close();
                                r8 = r11;
                            }
                        }
                    } else {
                        if (Query != 0) {
                            Query.close();
                        }
                        date = new Date(0L);
                        Query = Query;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (r8 != 0) {
                        r8.close();
                    }
                    throw th;
                }
            } catch (CursorIndexOutOfBoundsException e5) {
            } catch (NullPointerException e6) {
            } catch (ParseException e7) {
                Query = 0;
            } catch (Exception e8) {
            }
            return date;
        } catch (Throwable th2) {
            th = th2;
            r8 = Query;
        }
    }

    @Override // com.topfreegames.bikerace.l.b
    public void a(com.topfreegames.bikerace.b.a aVar) {
        a(aVar, a(aVar.a()), new Date());
    }

    @Override // com.topfreegames.bikerace.l.c
    public void a(com.topfreegames.bikerace.b.a aVar, Date date, Date date2) {
        if (aVar.a() == null) {
            throw new IllegalArgumentException("Unable to save gameSession without an ID: " + aVar);
        }
        SQLiteDatabase sQLiteDatabaseD = d();
        ContentValues contentValues = new ContentValues();
        try {
            contentValues.put("id", aVar.a());
            contentValues.put("json", com.topfreegames.bikerace.l.a.a(aVar));
            contentValues.put("updated_at", b().format(date2));
            contentValues.put("synced_at", b().format(date));
            if (sQLiteDatabaseD.replace("game_sessions", null, contentValues) == -1) {
                throw new RuntimeException(String.format("Unable to save gameSession(id:%s) to local database.", aVar.a()));
            }
            Log.i("SqliteGameSessionRepository", String.format("Saved gameSession(id:%s) in local database.", aVar.a()));
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to serialize gameSession: " + aVar, e);
        }
    }

    public SimpleDateFormat b() {
        return this.f1284a.a();
    }

    public SQLiteDatabase c() {
        return this.f1284a.getReadableDatabase();
    }

    public SQLiteDatabase d() {
        return this.f1284a.getWritableDatabase();
    }

    /* JADX WARN: Code duplicated, block: B:49:0x0101  */
    @Override // com.topfreegames.bikerace.l.c
    public List<com.topfreegames.bikerace.b.a> a(String[] strArr) throws Throwable {
        Cursor cursorQuery;
        Cursor cursor = null;
        this.b = false;
        SQLiteDatabase sQLiteDatabaseC = c();
        try {
            try {
                ArrayList arrayList = new ArrayList(strArr.length);
                for (int i = 0; i < strArr.length; i++) {
                    arrayList.add("?");
                    if (this.b || Thread.interrupted()) {
                        throw new InterruptedException();
                    }
                }
                cursorQuery = sQLiteDatabaseC.query("game_sessions", new String[]{"json"}, String.format("%s in (%s) AND strftime('%s',%s) > strftime('%s',%s)", "id", k.a(", ", (String[]) arrayList.toArray(new String[0])), "%s", "updated_at", "%s", "synced_at"), strArr, null, null, "synced_at");
                try {
                    ArrayList arrayList2 = new ArrayList();
                    if (cursorQuery != null) {
                        cursorQuery.moveToFirst();
                        Log.i("SqliteGameSessionRepository", String.format("Fetched %d unsynced games from local database.", Integer.valueOf(cursorQuery.getCount())));
                        while (!cursorQuery.isAfterLast()) {
                            com.topfreegames.bikerace.b.a aVarA = com.topfreegames.bikerace.l.a.a(cursorQuery.getBlob(0));
                            Log.i("SqliteGameSessionRepository", String.format("Retrived gameSession (with id %s) from database: %s", aVarA.a(), aVarA));
                            arrayList2.add(aVarA);
                            cursorQuery.moveToNext();
                            if (this.b || Thread.interrupted()) {
                                cursorQuery.close();
                                throw new InterruptedException();
                            }
                        }
                        if (cursorQuery == null) {
                            return arrayList2;
                        }
                        cursorQuery.close();
                        return arrayList2;
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                } catch (IOException e) {
                    e = e;
                    e.printStackTrace();
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                } catch (ClassNotFoundException e2) {
                    e = e2;
                    e.printStackTrace();
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                } catch (Exception e3) {
                    e = e3;
                    e.printStackTrace();
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                }
            } catch (Throwable th) {
                th = th;
                cursor = null;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (IOException e4) {
            e = e4;
            cursorQuery = null;
        } catch (ClassNotFoundException e5) {
            e = e5;
            cursorQuery = null;
        } catch (Exception e6) {
            e = e6;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return null;
    }

    @Override // com.topfreegames.bikerace.l.b
    public int b(com.topfreegames.bikerace.b.a aVar) {
        if (aVar.a() == null) {
            throw new IllegalArgumentException("Unable to save gameSession without an ID: " + aVar);
        }
        return d().delete("game_sessions", String.format("%s=?", "id"), new String[]{aVar.a()});
    }

    @Override // com.topfreegames.bikerace.l.c
    public void a() {
        this.b = true;
        this.c = true;
    }
}
