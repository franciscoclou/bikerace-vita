package com.heyzap.sdk.ads.a;

import android.content.Context;
import com.heyzap.a.g;
import com.heyzap.internal.k;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: FramePingManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    List<a> f775a;
    int c;
    String d;
    private int f;
    private boolean g;
    private Context h;
    int b = 0;
    HashMap<String, Long[]> e = new HashMap<>();

    public d(Context context, List<a> list, int i, String str) {
        this.c = -1;
        this.h = context;
        this.f775a = list;
        this.d = str;
        this.c = list.size() * i;
        this.f = i;
        k.a("trying these hosts: ", list);
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.e.put(list.get(i2).d, new Long[i]);
        }
        this.g = false;
        new Thread(new Runnable() { // from class: com.heyzap.sdk.ads.a.d.1
            @Override // java.lang.Runnable
            public void run() {
                d.this.b();
            }
        }).start();
    }

    public void a() {
        g gVar = new g();
        try {
            gVar.a("ping_results", c().a().toString());
            com.heyzap.internal.a.b(this.h, "/in_game_api/demo/log_ping_results", gVar, null);
        } catch (org.b.a.b e) {
            e.printStackTrace();
        }
    }

    public void b() {
        k.a("checking finished", Boolean.valueOf(this.g));
        if (!this.g) {
            this.b++;
            k.a("checking pings sent vs total", Integer.valueOf(this.b), Integer.valueOf(this.c));
            if (this.b > this.c) {
                this.g = true;
                a();
            } else {
                final a aVar = this.f775a.get(this.b % this.f775a.size());
                k.a("sending ping", aVar.d);
                new b(aVar, this.d + this.b, new c() { // from class: com.heyzap.sdk.ads.a.d.2
                    @Override // com.heyzap.sdk.ads.a.c
                    public void a(Throwable th) {
                        k.a("ping failed", th);
                        th.printStackTrace();
                        d.this.e.get(aVar.d)[(d.this.b - 1) / d.this.f775a.size()] = -1L;
                        d.this.b();
                    }

                    @Override // com.heyzap.sdk.ads.a.c
                    public void a(long j) {
                        k.b("ping success");
                        k.a(d.this.e, aVar, Integer.valueOf((d.this.b / d.this.f775a.size()) - 1), d.this.e.get(aVar));
                        d.this.e.get(aVar.d)[(d.this.b - 1) / d.this.f775a.size()] = Long.valueOf(j);
                        d.this.b();
                    }
                });
            }
        }
    }

    public f c() {
        f fVar = new f();
        for (Map.Entry<String, Long[]> entry : this.e.entrySet()) {
            Long[] value = entry.getValue();
            int i = 0;
            int i2 = 0;
            long jLongValue = 0;
            long jMax = -1;
            long jMin = 1000000;
            for (int i3 = 0; i3 < this.f; i3++) {
                if (value[i3] != null) {
                    i2++;
                    if (value[i3].longValue() == -1) {
                        i++;
                    } else {
                        jMax = Math.max(jMax, value[i3].longValue());
                        jMin = Math.min(jMin, value[i3].longValue());
                        jLongValue += value[i3].longValue();
                    }
                }
            }
            e eVar = new e();
            eVar.b = i2;
            eVar.f778a = i;
            if (i2 - i > 0) {
                eVar.d = jMax;
                eVar.e = jMin;
                eVar.c = jLongValue / ((long) (i2 - i));
            }
            eVar.f = entry.getKey();
            fVar.add(eVar);
        }
        return fVar;
    }
}
