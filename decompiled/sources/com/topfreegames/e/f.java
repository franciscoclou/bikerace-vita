package com.topfreegames.e;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/* JADX INFO: compiled from: TopFacebookInternalDatabase.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class f extends SQLiteOpenHelper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private SQLiteDatabase f1542a;
    private boolean b;

    f(Context context) {
        super(context, "topFacebookManagerDatabase", (SQLiteDatabase.CursorFactory) null, 1);
        b();
    }

    private void b() {
        if (!this.b && this.f1542a == null) {
            this.b = true;
            new Thread(new Runnable() { // from class: com.topfreegames.e.f.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        f.this.f1542a = f.this.getWritableDatabase();
                    } catch (Exception e) {
                        Log.e("TopFacebookManagerDatabase", "An exception occurred while opening the database");
                    } finally {
                        f.this.b = false;
                    }
                }
            }).start();
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE facebookAppUsers (facebookId TEXT PRIMARY KEY,facebookName TEXT,facebookSquareImage BLOB)");
            sQLiteDatabase.execSQL("CREATE TABLE specialFacebookAppUsers (specialFacebookId TEXT PRIMARY KEY, facebookId TEXT, facebookName TEXT, facebookSquareImage BLOB)");
        } catch (Exception e) {
            Log.d("TopFacebookInternalDatabase", "An exception occurred while creating the tables: " + e.toString());
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    void a(String str, String str2, byte[] bArr) {
        b();
        if (this.f1542a != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("facebookId", str);
            contentValues.put("facebookName", str2);
            contentValues.put("facebookSquareImage", bArr);
            if (this.f1542a.replace("facebookAppUsers", null, contentValues) == -1) {
                System.out.println("Replace failed!");
            }
        }
    }

    void b(String str, String str2, byte[] bArr) {
        b();
        if (this.f1542a != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("facebookId", str);
            contentValues.put("facebookName", str2);
            contentValues.put("facebookSquareImage", bArr);
            contentValues.put("specialFacebookId", "me");
            if (this.f1542a.replace("specialFacebookAppUsers", null, contentValues) == -1) {
                System.out.println("Replace failed!");
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:28:0x008a  */
    /* JADX WARN: Code duplicated, block: B:37:0x0098  */
    g a(String str) throws Throwable {
        Cursor cursorQuery;
        g gVar;
        b();
        if (this.f1542a != null) {
            try {
                cursorQuery = this.f1542a.query("facebookAppUsers", new String[]{"facebookId", "facebookName", "facebookSquareImage"}, "facebookId = ?", new String[]{str}, null, null, null);
                try {
                    try {
                        if (cursorQuery.getCount() == 1) {
                            cursorQuery.moveToFirst();
                            int columnIndex = cursorQuery.getColumnIndex("facebookId");
                            if (columnIndex >= 0) {
                                String string = cursorQuery.getString(columnIndex);
                                int columnIndex2 = cursorQuery.getColumnIndex("facebookName");
                                String string2 = columnIndex2 > 0 ? cursorQuery.getString(columnIndex2) : null;
                                int columnIndex3 = cursorQuery.getColumnIndex("facebookSquareImage");
                                gVar = new g(this, string, string2, columnIndex3 > 0 ? cursorQuery.getBlob(columnIndex3) : null);
                            } else {
                                gVar = null;
                            }
                        } else {
                            gVar = null;
                        }
                        if (cursorQuery != null) {
                            cursorQuery.close();
                            return gVar;
                        }
                        return gVar;
                    } catch (Exception e) {
                        e = e;
                        Log.d("getCurrentUserInformation", "An exception occured: " + e.toString());
                        if (cursorQuery != null) {
                            cursorQuery.close();
                            return null;
                        }
                        return null;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                cursorQuery = null;
            } catch (Throwable th2) {
                th = th2;
                cursorQuery = null;
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                throw th;
            }
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x008c  */
    /* JADX WARN: Code duplicated, block: B:37:0x009a  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v3, types: [android.database.Cursor] */
    g a() throws Throwable {
        Cursor cursorQuery;
        g gVar;
        b();
        if (this.f1542a != null) {
            ?? r1 = "facebookSquareImage";
            try {
                try {
                    cursorQuery = this.f1542a.query("specialFacebookAppUsers", new String[]{"facebookId", "facebookName", "facebookSquareImage"}, "specialFacebookId = ?", new String[]{"me"}, null, null, null);
                    try {
                        if (cursorQuery.getCount() == 1) {
                            cursorQuery.moveToFirst();
                            int columnIndex = cursorQuery.getColumnIndex("facebookId");
                            if (columnIndex >= 0) {
                                String string = cursorQuery.getString(columnIndex);
                                int columnIndex2 = cursorQuery.getColumnIndex("facebookName");
                                String string2 = columnIndex2 > 0 ? cursorQuery.getString(columnIndex2) : null;
                                int columnIndex3 = cursorQuery.getColumnIndex("facebookSquareImage");
                                gVar = new g(this, string, string2, columnIndex3 > 0 ? cursorQuery.getBlob(columnIndex3) : null);
                            } else {
                                gVar = null;
                            }
                        } else {
                            gVar = null;
                        }
                        if (cursorQuery != null) {
                            cursorQuery.close();
                            return gVar;
                        }
                        return gVar;
                    } catch (Exception e) {
                        e = e;
                        Log.d("getCurrentUserInformation", "An exception occured: " + e.toString());
                        if (cursorQuery != null) {
                            cursorQuery.close();
                            return null;
                        }
                        return null;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (r1 != 0) {
                        r1.close();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                cursorQuery = null;
            } catch (Throwable th2) {
                th = th2;
                r1 = 0;
                if (r1 != 0) {
                    r1.close();
                }
                throw th;
            }
        }
        return null;
    }
}
