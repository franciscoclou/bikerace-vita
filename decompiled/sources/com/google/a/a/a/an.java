package com.google.a.a.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.google.android.gms.analytics.internal.Command;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.impl.client.DefaultHttpClient;

/* JADX INFO: compiled from: PersistentAnalyticsStore.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class an implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f580a = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER);", "hits2", "hit_id", "hit_time", "hit_url", "hit_string", "hit_app_id");
    private final ao b;
    private volatile n c;
    private final g d;
    private final Context e;
    private final String f;
    private long g;
    private k h;

    an(g gVar, Context context) {
        this(gVar, context, "google_analytics_v2.db");
    }

    an(g gVar, Context context, String str) {
        this.e = context.getApplicationContext();
        this.f = str;
        this.d = gVar;
        this.h = new k() { // from class: com.google.a.a.a.an.1
            @Override // com.google.a.a.a.k
            public long a() {
                return System.currentTimeMillis();
            }
        };
        this.b = new ao(this, this.e, this.f);
        this.c = new as(new DefaultHttpClient(), this.e);
        this.g = 0L;
    }

    @Override // com.google.a.a.a.f
    public void a(long j) {
        SQLiteDatabase sQLiteDatabaseA = a("Error opening database for clearHits");
        if (sQLiteDatabaseA != null) {
            if (j == 0) {
                sQLiteDatabaseA.delete("hits2", null, null);
            } else {
                sQLiteDatabaseA.delete("hits2", "hit_app_id = ?", new String[]{Long.valueOf(j).toString()});
            }
            this.d.a(d() == 0);
        }
    }

    @Override // com.google.a.a.a.f
    public void a(Map<String, String> map, long j, String str, Collection<Command> collection) throws Throwable {
        c();
        f();
        a(map, collection);
        a(map, j, str);
    }

    private void a(Map<String, String> map, Collection<Command> collection) {
        String strSubstring = "&_v".substring(1);
        if (collection != null) {
            for (Command command : collection) {
                if ("appendVersion".equals(command.a())) {
                    map.put(strSubstring, command.b());
                    return;
                }
            }
        }
    }

    private void f() throws Throwable {
        int iD = (d() - 2000) + 1;
        if (iD > 0) {
            List<String> listA = a(iD);
            ah.c("Store full, deleting " + listA.size() + " hits to make room.");
            a((String[]) listA.toArray(new String[0]));
        }
    }

    private void a(Map<String, String> map, long j, String str) {
        long j2;
        SQLiteDatabase sQLiteDatabaseA = a("Error opening database for putHit");
        if (sQLiteDatabaseA != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hit_string", a(map));
            contentValues.put("hit_time", Long.valueOf(j));
            if (!map.containsKey("AppUID")) {
                j2 = 0;
            } else {
                try {
                    j2 = Long.parseLong(map.get("AppUID"));
                } catch (NumberFormatException e) {
                    j2 = 0;
                }
            }
            contentValues.put("hit_app_id", Long.valueOf(j2));
            if (str == null) {
                str = "http://www.google-analytics.com/collect";
            }
            if (str.length() == 0) {
                ah.d("Empty path: not sending hit");
                return;
            }
            contentValues.put("hit_url", str);
            try {
                sQLiteDatabaseA.insert("hits2", null, contentValues);
                this.d.a(false);
            } catch (SQLiteException e2) {
                ah.d("Error storing hit");
            }
        }
    }

    static String a(Map<String, String> map) {
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            arrayList.add(ag.a(entry.getKey()) + "=" + ag.a(entry.getValue()));
        }
        return TextUtils.join("&", arrayList);
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0082  */
    List<String> a(int i) throws Throwable {
        Cursor cursorQuery;
        ArrayList arrayList = new ArrayList();
        if (i <= 0) {
            ah.d("Invalid maxHits specified. Skipping");
            return arrayList;
        }
        SQLiteDatabase sQLiteDatabaseA = a("Error opening database for peekHitIds.");
        if (sQLiteDatabaseA == null) {
            return arrayList;
        }
        try {
            cursorQuery = sQLiteDatabaseA.query("hits2", new String[]{"hit_id"}, null, null, null, null, String.format("%s ASC", "hit_id"), Integer.toString(i));
            try {
                try {
                    if (cursorQuery.moveToFirst()) {
                        do {
                            arrayList.add(String.valueOf(cursorQuery.getLong(0)));
                        } while (cursorQuery.moveToNext());
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                } catch (SQLiteException e) {
                    e = e;
                    ah.d("Error in peekHits fetching hitIds: " + e.getMessage());
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                }
            } catch (Throwable th) {
                th = th;
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                throw th;
            }
        } catch (SQLiteException e2) {
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
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:35:0x00fa  */
    public List<af> b(int i) throws Throwable {
        SQLiteException sQLiteException;
        Cursor cursor;
        ArrayList arrayList;
        ArrayList arrayList2 = new ArrayList();
        SQLiteDatabase sQLiteDatabaseA = a("Error opening database for peekHits");
        if (sQLiteDatabaseA == null) {
            return arrayList2;
        }
        Cursor cursor2 = null;
        try {
            Cursor cursorQuery = sQLiteDatabaseA.query("hits2", new String[]{"hit_id", "hit_time"}, null, null, null, null, String.format("%s ASC", "hit_id"), Integer.toString(i));
            try {
                try {
                    ArrayList<af> arrayList3 = new ArrayList();
                    try {
                        if (cursorQuery.moveToFirst()) {
                            do {
                                arrayList3.add(new af(null, cursorQuery.getLong(0), cursorQuery.getLong(1)));
                            } while (cursorQuery.moveToNext());
                        }
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        try {
                            try {
                                Cursor cursorQuery2 = sQLiteDatabaseA.query("hits2", new String[]{"hit_id", "hit_string", "hit_url"}, null, null, null, null, String.format("%s ASC", "hit_id"), Integer.toString(i));
                                try {
                                    if (cursorQuery2.moveToFirst()) {
                                        int i2 = 0;
                                        while (true) {
                                            if (((SQLiteCursor) cursorQuery2).getWindow().getNumRows() > 0) {
                                                ((af) arrayList3.get(i2)).a(cursorQuery2.getString(1));
                                                ((af) arrayList3.get(i2)).b(cursorQuery2.getString(2));
                                            } else {
                                                ah.d(String.format("HitString for hitId %d too large.  Hit will be deleted.", Long.valueOf(((af) arrayList3.get(i2)).b())));
                                            }
                                            int i3 = i2 + 1;
                                            if (!cursorQuery2.moveToNext()) {
                                                break;
                                            }
                                            i2 = i3;
                                        }
                                    }
                                    if (cursorQuery2 != null) {
                                        cursorQuery2.close();
                                    }
                                    return arrayList3;
                                } catch (SQLiteException e) {
                                    e = e;
                                    cursorQuery = cursorQuery2;
                                    ah.d("Error in peekHits fetching hitString: " + e.getMessage());
                                    ArrayList arrayList4 = new ArrayList();
                                    boolean z = false;
                                    for (af afVar : arrayList3) {
                                        if (TextUtils.isEmpty(afVar.a())) {
                                            if (z) {
                                                break;
                                            }
                                            z = true;
                                        }
                                        arrayList4.add(afVar);
                                    }
                                    if (cursorQuery != null) {
                                        cursorQuery.close();
                                    }
                                    return arrayList4;
                                } catch (Throwable th) {
                                    th = th;
                                    cursorQuery = cursorQuery2;
                                    if (cursorQuery != null) {
                                        cursorQuery.close();
                                    }
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        } catch (SQLiteException e2) {
                            e = e2;
                        }
                    } catch (SQLiteException e3) {
                        sQLiteException = e3;
                        cursor = cursorQuery;
                        arrayList = arrayList3;
                        try {
                            ah.d("Error in peekHits fetching hitIds: " + sQLiteException.getMessage());
                            if (cursor == null) {
                                return arrayList;
                            }
                            cursor.close();
                            return arrayList;
                        } catch (Throwable th3) {
                            th = th3;
                            cursor2 = cursor;
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                            throw th;
                        }
                    }
                } catch (SQLiteException e4) {
                    sQLiteException = e4;
                    cursor = cursorQuery;
                    arrayList = arrayList2;
                }
            } catch (Throwable th4) {
                th = th4;
                cursor2 = cursorQuery;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (SQLiteException e5) {
            sQLiteException = e5;
            cursor = null;
            arrayList = arrayList2;
        } catch (Throwable th5) {
            th = th5;
        }
    }

    int c() {
        long jA = this.h.a();
        if (jA <= this.g + 86400000) {
            return 0;
        }
        this.g = jA;
        SQLiteDatabase sQLiteDatabaseA = a("Error opening database for deleteStaleHits.");
        if (sQLiteDatabaseA == null) {
            return 0;
        }
        int iDelete = sQLiteDatabaseA.delete("hits2", "HIT_TIME < ?", new String[]{Long.toString(this.h.a() - 2592000000L)});
        this.d.a(d() == 0);
        return iDelete;
    }

    @Deprecated
    void a(Collection<af> collection) {
        if (collection == null || collection.isEmpty()) {
            ah.d("Empty/Null collection passed to deleteHits.");
            return;
        }
        String[] strArr = new String[collection.size()];
        Iterator<af> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            strArr[i] = String.valueOf(it.next().b());
            i++;
        }
        a(strArr);
    }

    void a(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            ah.d("Empty hitIds passed to deleteHits.");
            return;
        }
        SQLiteDatabase sQLiteDatabaseA = a("Error opening database for deleteHits.");
        if (sQLiteDatabaseA != null) {
            try {
                sQLiteDatabaseA.delete("hits2", String.format("HIT_ID in (%s)", TextUtils.join(",", Collections.nCopies(strArr.length, "?"))), strArr);
                this.d.a(d() == 0);
            } catch (SQLiteException e) {
                ah.d("Error deleting hits " + strArr);
            }
        }
    }

    int d() {
        Cursor cursorRawQuery = null;
        int i = 0;
        SQLiteDatabase sQLiteDatabaseA = a("Error opening database for getNumStoredHits.");
        try {
            if (sQLiteDatabaseA != null) {
                try {
                    cursorRawQuery = sQLiteDatabaseA.rawQuery("SELECT COUNT(*) from hits2", null);
                    i = cursorRawQuery.moveToFirst() ? (int) cursorRawQuery.getLong(0) : 0;
                } catch (SQLiteException e) {
                    ah.d("Error getting numStoredHits");
                }
            }
            return i;
        } finally {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
        }
    }

    @Override // com.google.a.a.a.f
    public void a() throws Throwable {
        ah.c("Dispatch running...");
        if (this.c.a()) {
            List<af> listB = b(40);
            if (listB.isEmpty()) {
                ah.c("...nothing to dispatch");
                this.d.a(true);
                return;
            }
            int iA = this.c.a(listB);
            ah.c("sent " + iA + " of " + listB.size() + " hits");
            a(listB.subList(0, Math.min(iA, listB.size())));
            if (iA == listB.size() && d() > 0) {
                u.a().c();
            }
        }
    }

    @Override // com.google.a.a.a.f
    public n b() {
        return this.c;
    }

    private SQLiteDatabase a(String str) {
        try {
            return this.b.getWritableDatabase();
        } catch (SQLiteException e) {
            ah.d(str);
            return null;
        }
    }
}
