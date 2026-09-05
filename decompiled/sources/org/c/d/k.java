package org.c.d;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: compiled from: Token.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class k implements Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f1623a;
    private final String b;
    private final String c;
    private final long d;

    public k(String str, String str2, long j) {
        this(str, str2, j, null);
    }

    public k(String str, String str2, long j, String str3) {
        org.c.g.c.a((Object) str, "Token can't be null");
        org.c.g.c.a((Object) str2, "Secret can't be null");
        this.f1623a = str;
        this.b = str2;
        this.c = str3;
        if (j >= 0) {
            this.d = com.topfreegames.c.a.a().getTime() + (1000 * j);
        } else {
            this.d = -1L;
        }
    }

    public String a() {
        return this.f1623a;
    }

    public String b() {
        return this.b;
    }

    public long c() {
        return this.d;
    }

    public boolean d() {
        return this.d > 0 && com.topfreegames.c.a.a().getTime() >= this.d;
    }

    public String toString() {
        return String.format("Token[%s, %s, %s]", this.f1623a, this.b, new Date(this.d).toString());
    }

    public boolean e() {
        return "".equals(this.f1623a) && "".equals(this.b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        k kVar = (k) obj;
        return this.f1623a.equals(kVar.f1623a) && this.b.equals(kVar.b) && this.d == kVar.d;
    }

    public int hashCode() {
        return (this.f1623a.hashCode() * 31) + this.b.hashCode() + Long.valueOf(this.d).hashCode();
    }
}
