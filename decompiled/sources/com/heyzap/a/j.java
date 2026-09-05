package com.heyzap.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;

/* JADX INFO: compiled from: SDKCookieStore.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class j implements CookieStore {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final SimpleDateFormat f745a = new SimpleDateFormat("EEE, dd-MMM-yyyy hh:mm:ss z");
    private final ConcurrentHashMap<String, Cookie> b = new ConcurrentHashMap<>();
    private final SharedPreferences c;
    private Context d;

    public j(Context context) {
        this.c = context.getSharedPreferences("CookiePrefsFile", 3);
        this.d = context;
        a(e.a(context));
    }

    @Override // org.apache.http.client.CookieStore
    public void addCookie(Cookie cookie) {
        String name = cookie.getName();
        if (!cookie.isExpired(new Date())) {
            this.b.put(name, cookie);
        } else {
            this.b.remove(name);
        }
        e.a(this.d, a());
    }

    @Override // org.apache.http.client.CookieStore
    public void clear() {
        this.b.clear();
        e.a(this.d, a());
    }

    @Override // org.apache.http.client.CookieStore
    public boolean clearExpired(Date date) {
        boolean z;
        boolean z2 = false;
        SharedPreferences.Editor editorEdit = this.c.edit();
        Iterator<Map.Entry<String, Cookie>> it = this.b.entrySet().iterator();
        while (true) {
            z = z2;
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<String, Cookie> next = it.next();
            String key = next.getKey();
            if (next.getValue().isExpired(date)) {
                this.b.remove(key);
                editorEdit.remove("cookie_" + key);
                z2 = true;
            } else {
                z2 = z;
            }
        }
        if (z) {
            editorEdit.putString("names", TextUtils.join(",", this.b.keySet()));
        }
        editorEdit.commit();
        return z;
    }

    @Override // org.apache.http.client.CookieStore
    public List<Cookie> getCookies() {
        return new ArrayList(this.b.values());
    }

    public void a(String str) {
        new ArrayList();
        for (String str2 : str.split(";\\s+")) {
            com.heyzap.internal.k.a("cookieString", str2);
            String[] strArrSplit = str2.split(";");
            if (strArrSplit.length > 0) {
                String[] strArrSplit2 = strArrSplit[0].split("=");
                if (strArrSplit2.length == 2) {
                    BasicClientCookie basicClientCookie = new BasicClientCookie(strArrSplit2[0], strArrSplit2[1]);
                    basicClientCookie.setDomain("android.heyzap.com");
                    addCookie(basicClientCookie);
                }
            }
        }
    }

    public String a() {
        ArrayList arrayList = new ArrayList();
        Iterator<Cookie> it = this.b.values().iterator();
        while (it.hasNext()) {
            arrayList.add(a(it.next()));
        }
        return TextUtils.join("; ", arrayList);
    }

    public String a(Cookie cookie) {
        return String.format("%s=%s", cookie.getName(), cookie.getValue());
    }
}
