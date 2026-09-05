package com.topfreegames.bikerace.l.b;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.topfreegames.bikerace.l.e;
import com.topfreegames.bikerace.l.f;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: SqliteUserRepository.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final a f1285a;

    public c(a aVar) {
        this.f1285a = aVar;
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x006b: MOVE (r8 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:25:0x006b */
    @Override // com.topfreegames.bikerace.l.f
    public com.topfreegames.bikerace.b.b a(String str) throws Throwable {
        Cursor cursor;
        Cursor cursorQuery;
        Cursor cursor2 = null;
        SQLiteDatabase sQLiteDatabaseB = b();
        try {
            if (sQLiteDatabaseB != null) {
                try {
                    cursorQuery = sQLiteDatabaseB.query("users", new String[]{"json"}, String.format("%s=?", "id"), new String[]{str}, null, null, null);
                    if (cursorQuery != null) {
                        try {
                            if (cursorQuery.getCount() == 1) {
                                cursorQuery.moveToFirst();
                                com.topfreegames.bikerace.b.b bVarA = e.a(new JSONObject(new String(cursorQuery.getBlob(0))));
                                if (cursorQuery == null) {
                                    return bVarA;
                                }
                                cursorQuery.close();
                                return bVarA;
                            }
                        } catch (JSONException e) {
                            e = e;
                            e.printStackTrace();
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                        }
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                } catch (JSONException e2) {
                    e = e2;
                    cursorQuery = null;
                } catch (Throwable th) {
                    th = th;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            cursor2 = cursor;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v5, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [android.database.Cursor] */
    private Date c(String str) throws Throwable {
        Date date;
        ?? Query = 0;
        ?? r8 = 0;
        SQLiteDatabase sQLiteDatabaseB = b();
        try {
            if (sQLiteDatabaseB == null) {
                return new Date(0L);
            }
            try {
                Query = sQLiteDatabaseB.query("users", new String[]{"synced_at"}, String.format("%s=?", "id"), new String[]{str}, null, null, null);
                try {
                    if (Query != 0) {
                        Query.moveToFirst();
                        date = a().parse(Query.getString(0));
                        if (Query != 0) {
                            Query.close();
                        }
                    } else {
                        date = new Date(0L);
                        if (Query != 0) {
                            Query.close();
                        }
                    }
                    return date;
                } catch (ParseException e) {
                    Date date2 = new Date(0L);
                    if (Query == 0) {
                        return date2;
                    }
                    Query.close();
                    return date2;
                }
            } catch (ParseException e2) {
                Query = 0;
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            r8 = Query;
        }
        if (r8 != 0) {
            r8.close();
        }
        throw th;
    }

    public void a(com.topfreegames.bikerace.b.b bVar) {
        a(bVar, c(bVar.c()));
    }

    @Override // com.topfreegames.bikerace.l.f
    public void a(com.topfreegames.bikerace.b.b bVar, Date date) {
        if (bVar.c() == null) {
            throw new IllegalArgumentException("Unable to save user without an ID: " + bVar);
        }
        SQLiteDatabase sQLiteDatabaseC = c();
        if (sQLiteDatabaseC == null) {
            throw new RuntimeException("Unable to save user to local database: " + bVar);
        }
        ContentValues contentValues = new ContentValues();
        try {
            contentValues.put("id", bVar.c());
            contentValues.put("json", e.a(bVar).toString());
            contentValues.put("updated_at", a().format(new Date()));
            contentValues.put("synced_at", a().format(date));
            if (sQLiteDatabaseC.replace("users", null, contentValues) == -1) {
                throw new RuntimeException("Unable to save user to local databse: " + bVar);
            }
        } catch (JSONException e) {
            throw new IllegalArgumentException("Unable to serialize user: " + bVar, e);
        }
    }

    @Override // com.topfreegames.bikerace.l.f
    public void a(String str, String str2) throws Throwable {
        com.topfreegames.bikerace.b.b bVarA = a(str);
        if (bVarA != null) {
            bVarA.d().add(str2);
            a(bVarA);
        }
    }

    @Override // com.topfreegames.bikerace.l.f
    public void b(String str, String str2) throws Throwable {
        com.topfreegames.bikerace.b.b bVarA = a(str);
        if (bVarA != null) {
            bVarA.d().remove(str2);
            a(bVarA);
        }
    }

    private SimpleDateFormat a() {
        return this.f1285a.a();
    }

    private SQLiteDatabase b() {
        try {
            return this.f1285a.getReadableDatabase();
        } catch (Exception e) {
            return null;
        }
    }

    private SQLiteDatabase c() {
        try {
            return this.f1285a.getWritableDatabase();
        } catch (Exception e) {
            return null;
        }
    }

    @Override // com.topfreegames.bikerace.l.f
    public void a(String str, int i) throws Throwable {
        com.topfreegames.bikerace.b.b bVarA = a(str);
        if (bVarA != null) {
            bVarA.a(Integer.valueOf(i));
            a(bVarA, c(bVarA.c()));
        }
    }

    @Override // com.topfreegames.bikerace.l.f
    public void b(String str, int i) throws Throwable {
        com.topfreegames.bikerace.b.b bVarA = a(str);
        if (bVarA != null) {
            bVarA.b(Integer.valueOf(i));
            a(bVarA, c(bVarA.c()));
        }
    }

    @Override // com.topfreegames.bikerace.l.f
    public void d(String str, int i) throws Throwable {
        com.topfreegames.bikerace.b.b bVarA = a(str);
        if (bVarA != null) {
            bVarA.c(Integer.valueOf(bVarA.e().intValue() + i));
            a(bVarA, c(bVarA.c()));
        }
    }

    @Override // com.topfreegames.bikerace.l.f
    public void c(String str, int i) throws Throwable {
        com.topfreegames.bikerace.b.b bVarA = a(str);
        if (bVarA != null) {
            bVarA.c(Integer.valueOf(i));
            a(bVarA, c(bVarA.c()));
        }
    }

    @Override // com.topfreegames.bikerace.l.f
    public boolean b(String str) {
        throw new UnsupportedOperationException();
    }
}
