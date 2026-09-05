package com.topfreegames.bikerace.f;

import android.content.Context;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: Gift.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {
    private static /* synthetic */ int[] g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f1223a;
    private String b;
    private String c;
    private Date d;
    private b e;
    private String f;

    static /* synthetic */ int[] i() {
        int[] iArr = g;
        if (iArr == null) {
            iArr = new int[b.valuesCustom().length];
            try {
                iArr[b.ASK_TRACK.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[b.GIVE_ONE_TRACK.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[b.GIVE_SPECIFIC_TRACK.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            g = iArr;
        }
        return iArr;
    }

    a(String str, String str2, String str3, String str4) {
        this.f1223a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        if (str == null) {
            throw new IllegalArgumentException("Id cannot be null!");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("Sender Id cannot be null!");
        }
        if (str3 == null) {
            throw new IllegalArgumentException("Sender Name cannot be null!");
        }
        if (str4 == null) {
            throw new IllegalArgumentException("Data cannot be null!");
        }
        this.f1223a = str;
        this.c = str2;
        this.b = str3;
        try {
            JSONObject jSONObject = new JSONObject(str4);
            String string = jSONObject.getString("t");
            if (string.equals("at")) {
                this.e = b.ASK_TRACK;
                return;
            }
            if (string.equals("ot")) {
                this.e = b.GIVE_ONE_TRACK;
            } else {
                if (string.equals("st")) {
                    this.e = b.GIVE_SPECIFIC_TRACK;
                    this.f = jSONObject.getJSONObject("si").getString("tc");
                    return;
                }
                throw new IllegalArgumentException("Invalid data");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid data");
        }
    }

    a(String str, String str2, String str3, b bVar, String str4, Date date) {
        this.f1223a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        if (str == null) {
            throw new IllegalArgumentException("Id cannot be null!");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("Sender Id cannot be null!");
        }
        if (str3 == null) {
            throw new IllegalArgumentException("Sender Name cannot be null!");
        }
        if (bVar == null) {
            throw new IllegalArgumentException("Type cannot be null!");
        }
        this.f1223a = str;
        this.c = str2;
        this.b = str3;
        this.e = bVar;
        this.f = str4;
        this.d = date;
    }

    public String a() {
        return this.f1223a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public long a(long j) {
        if (this.d == null) {
            return -1L;
        }
        return (this.d.getTime() + j) - com.topfreegames.c.a.a().getTime();
    }

    public boolean b(long j) {
        return this.d != null && a(j) <= 0;
    }

    public Date d() {
        return this.d;
    }

    public boolean e() {
        return this.d != null;
    }

    void f() {
        this.d = com.topfreegames.c.a.a();
    }

    public b g() {
        return this.e;
    }

    public String h() {
        return this.f;
    }

    public String a(Context context) {
        if (context != null) {
            switch (i()[this.e.ordinal()]) {
                case 1:
                    return context.getString(2131099939);
                case 2:
                    return context.getString(2131099940);
                case 3:
                    return context.getString(2131099941);
            }
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        return this.f1223a.equals(((a) obj).f1223a);
    }

    public static String a(b bVar) {
        if (bVar == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        switch (i()[bVar.ordinal()]) {
            case 1:
                try {
                    jSONObject.put("t", "ot");
                } catch (Exception e) {
                }
                break;
            case 2:
                throw new IllegalArgumentException("Use assembleDataWithTrackId");
            case 3:
                try {
                    jSONObject.put("t", "at");
                } catch (Exception e2) {
                }
                break;
        }
        return jSONObject.toString();
    }

    public static String a(String str) {
        if (str == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("t", "st");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("tc", str);
            jSONObject.put("si", jSONObject2);
            return jSONObject.toString();
        } catch (JSONException e) {
            return null;
        }
    }
}
