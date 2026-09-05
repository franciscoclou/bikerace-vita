package com.topfreegames.bikerace.f;

import android.app.Activity;
import android.content.Context;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.bikerace.bb;
import com.topfreegames.e.b.a.m;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: compiled from: GiftManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.topfreegames.e.b.a.i f1228a = com.topfreegames.e.b.a.i.a();
    private bb b;
    private String c;
    private c d;

    public e(String str, Context context) {
        this.b = null;
        this.c = null;
        this.d = null;
        this.c = str;
        this.d = new c(context, str);
        this.d.d();
        this.b = ((BikeRaceApplication) context.getApplicationContext()).a(false);
    }

    public void a(String str, Activity activity, final f fVar) {
        if (str == null) {
            throw new IllegalArgumentException("To User cannot be null!");
        }
        if (activity == null) {
            throw new IllegalArgumentException("Activity cannot be null!");
        }
        String string = activity.getResources().getString(2131099935);
        com.topfreegames.e.b.a.d dVar = null;
        if (fVar != null) {
            dVar = new com.topfreegames.e.b.a.d() { // from class: com.topfreegames.bikerace.f.e.1
                @Override // com.topfreegames.e.b.e
                public void a() {
                    fVar.b();
                }

                @Override // com.topfreegames.e.b.a.d
                public void a(boolean z) {
                    if (z) {
                        fVar.a();
                    } else {
                        fVar.b();
                    }
                }
            };
        }
        this.f1228a.a(str, string, a.a(b.GIVE_ONE_TRACK), activity, dVar);
    }

    public void a(String str, String str2, Activity activity, final f fVar) {
        if (str == null) {
            throw new IllegalArgumentException("To User cannot be null!");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("Track cannot be null!");
        }
        if (activity == null) {
            throw new IllegalArgumentException("Activity cannot be null!");
        }
        String string = activity.getResources().getString(2131099936);
        com.topfreegames.e.b.a.d dVar = null;
        if (fVar != null) {
            dVar = new com.topfreegames.e.b.a.d() { // from class: com.topfreegames.bikerace.f.e.2
                @Override // com.topfreegames.e.b.e
                public void a() {
                    if (fVar != null) {
                        fVar.b();
                    }
                }

                @Override // com.topfreegames.e.b.a.d
                public void a(boolean z) {
                    if (fVar != null) {
                        if (z) {
                            fVar.a();
                        } else {
                            fVar.b();
                        }
                    }
                }
            };
        }
        this.f1228a.a(str, string, a.a(str2), activity, dVar);
    }

    public void b(String str, Activity activity, final f fVar) {
        if (str == null) {
            throw new IllegalArgumentException("To User cannot be null!");
        }
        if (activity == null) {
            throw new IllegalArgumentException("Activity cannot be null!");
        }
        String string = activity.getResources().getString(2131099937);
        com.topfreegames.e.b.a.d dVar = null;
        if (fVar != null) {
            dVar = new com.topfreegames.e.b.a.d() { // from class: com.topfreegames.bikerace.f.e.3
                @Override // com.topfreegames.e.b.e
                public void a() {
                    if (fVar != null) {
                        fVar.b();
                    }
                }

                @Override // com.topfreegames.e.b.a.d
                public void a(boolean z) {
                    if (fVar != null) {
                        if (z) {
                            fVar.a();
                        } else {
                            fVar.b();
                        }
                    }
                }
            };
        }
        this.f1228a.a(str, string, a.a(b.ASK_TRACK), activity, dVar);
    }

    public void a(g gVar) {
        a(this.c, gVar);
    }

    private void a(String str, final g gVar) {
        this.f1228a.a(str, new m() { // from class: com.topfreegames.bikerace.f.e.4
            @Override // com.topfreegames.e.b.e
            public void a() {
            }

            @Override // com.topfreegames.e.b.a.m
            public void a(String str2, String str3, String str4, String str5, String str6) {
                e.this.d.b(new a(str2, str3, str4, str6));
                e.this.a();
                if (gVar != null) {
                    gVar.a(e.this.b());
                }
            }
        });
    }

    public void a(a aVar) {
        aVar.f();
        this.d.d(aVar);
        b(aVar);
    }

    private void b(a aVar) {
        this.d.e(aVar);
        this.f1228a.a(aVar.a(), new com.topfreegames.e.b.a.f() { // from class: com.topfreegames.bikerace.f.e.5
            @Override // com.topfreegames.e.b.e
            public void a() {
            }

            @Override // com.topfreegames.e.b.a.f
            public void a(String str, boolean z) {
                if (z) {
                    e.this.d.a(str);
                }
            }
        });
    }

    public void a() {
        List<a> listB = this.d.b();
        int size = listB.size();
        for (int i = 0; i < size; i++) {
            a aVar = listB.get(i);
            if (aVar.b(c())) {
                this.d.c(aVar);
            }
        }
    }

    private static String c(a aVar) {
        return String.valueOf(aVar.c()) + aVar.g();
    }

    public a[] b() {
        List<a> listA = this.d.a();
        HashMap map = new HashMap();
        int size = listA.size();
        for (int i = 0; i < size; i++) {
            a aVar = listA.get(i);
            String strC = c(aVar);
            if (map.containsKey(strC)) {
                if (!((a) map.get(strC)).e() && aVar.e()) {
                    map.put(strC, aVar);
                }
            } else {
                map.put(strC, aVar);
            }
        }
        return (a[]) new ArrayList(map.values()).toArray(new a[map.values().size()]);
    }

    public long c() {
        return this.b.f();
    }

    public int d() {
        int i = 0;
        for (a aVar : b()) {
            if (!aVar.e() && !aVar.b(c())) {
                i++;
            }
        }
        return i;
    }
}
