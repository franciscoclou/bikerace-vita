package com.chartboost.sdk.impl;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.UUID;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class af {
    public static ah a() {
        h hVar = null;
        ad adVarB = b();
        adVarB.a(Date.class, new i(adVarB));
        adVarB.a(au.class, new g(adVarB));
        adVarB.a(av.class, new h(hVar));
        adVarB.a(byte[].class, new h(hVar));
        return adVarB;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static ad b() {
        ad adVar = new ad();
        adVar.a(Object[].class, new m(adVar));
        adVar.a(Boolean.class, new q(null));
        adVar.a(aw.class, new a(adVar));
        adVar.a(ax.class, new b(adVar));
        adVar.a(aa.class, new d(adVar));
        adVar.a(ab.class, new e(adVar));
        adVar.a(Iterable.class, new f(adVar));
        adVar.a(Map.class, new j(adVar));
        adVar.a(ay.class, new k(adVar));
        adVar.a(az.class, new l(adVar));
        adVar.a(Number.class, new q(0 == true ? 1 : 0));
        adVar.a(ba.class, new n(adVar));
        adVar.a(Pattern.class, new o(adVar));
        adVar.a(String.class, new p(0 == true ? 1 : 0));
        adVar.a(UUID.class, new r(adVar));
        return adVar;
    }

    abstract class c extends ac {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected final ah f415a;

        c(ah ahVar) {
            this.f415a = ahVar;
        }
    }

    class h extends ac {
        private h() {
        }

        /* synthetic */ h(h hVar) {
            this();
        }

        @Override // com.chartboost.sdk.impl.ah
        public void a(Object obj, StringBuilder sb) {
            sb.append("<Binary Data>");
        }
    }

    class m extends c {
        m(ah ahVar) {
            super(ahVar);
        }

        @Override // com.chartboost.sdk.impl.ah
        public void a(Object obj, StringBuilder sb) {
            sb.append("[ ");
            for (int i = 0; i < Array.getLength(obj); i++) {
                if (i > 0) {
                    sb.append(" , ");
                }
                this.f415a.a(Array.get(obj, i), sb);
            }
            sb.append("]");
        }
    }

    class q extends ac {
        private q() {
        }

        /* synthetic */ q(q qVar) {
            this();
        }

        @Override // com.chartboost.sdk.impl.ah
        public void a(Object obj, StringBuilder sb) {
            sb.append(obj.toString());
        }
    }

    class g extends c {
        g(ah ahVar) {
            super(ahVar);
        }

        @Override // com.chartboost.sdk.impl.ah
        public void a(Object obj, StringBuilder sb) {
            au auVar = (au) obj;
            y yVar = new y();
            yVar.put("$ts", Integer.valueOf(auVar.a()));
            yVar.put("$inc", Integer.valueOf(auVar.b()));
            this.f415a.a(yVar, sb);
        }
    }

    class a extends c {
        a(ah ahVar) {
            super(ahVar);
        }

        @Override // com.chartboost.sdk.impl.ah
        public void a(Object obj, StringBuilder sb) {
            y yVar = new y();
            yVar.put("$code", ((aw) obj).a());
            this.f415a.a(yVar, sb);
        }
    }

    class b extends c {
        b(ah ahVar) {
            super(ahVar);
        }

        @Override // com.chartboost.sdk.impl.ah
        public void a(Object obj, StringBuilder sb) {
            ax axVar = (ax) obj;
            y yVar = new y();
            yVar.put("$code", axVar.a());
            yVar.put("$scope", axVar.b());
            this.f415a.a(yVar, sb);
        }
    }

    class i extends c {
        i(ah ahVar) {
            super(ahVar);
        }

        @Override // com.chartboost.sdk.impl.ah
        public void a(Object obj, StringBuilder sb) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            simpleDateFormat.setCalendar(new GregorianCalendar(new SimpleTimeZone(0, "GMT")));
            this.f415a.a(new y("$date", simpleDateFormat.format((Date) obj)), sb);
        }
    }

    class d extends c {
        d(ah ahVar) {
            super(ahVar);
        }

        @Override // com.chartboost.sdk.impl.ah
        public void a(Object obj, StringBuilder sb) {
            sb.append("{ ");
            aa aaVar = (aa) obj;
            boolean z = true;
            for (String str : aaVar.keySet()) {
                if (z) {
                    z = false;
                } else {
                    sb.append(" , ");
                }
                ae.a(sb, str);
                sb.append(" : ");
                this.f415a.a(aaVar.a(str), sb);
            }
            sb.append("}");
        }
    }

    class e extends c {
        e(ah ahVar) {
            super(ahVar);
        }

        @Override // com.chartboost.sdk.impl.ah
        public void a(Object obj, StringBuilder sb) {
            ab abVar = (ab) obj;
            y yVar = new y();
            yVar.put("$ref", abVar.b());
            yVar.put("$id", abVar.a());
            this.f415a.a(yVar, sb);
        }
    }

    class f extends c {
        f(ah ahVar) {
            super(ahVar);
        }

        @Override // com.chartboost.sdk.impl.ah
        public void a(Object obj, StringBuilder sb) {
            boolean z = true;
            sb.append("[ ");
            for (Object obj2 : (Iterable) obj) {
                if (z) {
                    z = false;
                } else {
                    sb.append(" , ");
                }
                this.f415a.a(obj2, sb);
            }
            sb.append("]");
        }
    }

    class j extends c {
        j(ah ahVar) {
            super(ahVar);
        }

        @Override // com.chartboost.sdk.impl.ah
        public void a(Object obj, StringBuilder sb) {
            sb.append("{ ");
            boolean z = true;
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                if (z) {
                    z = false;
                } else {
                    sb.append(" , ");
                }
                ae.a(sb, entry.getKey().toString());
                sb.append(" : ");
                this.f415a.a(entry.getValue(), sb);
            }
            sb.append("}");
        }
    }

    class k extends c {
        k(ah ahVar) {
            super(ahVar);
        }

        @Override // com.chartboost.sdk.impl.ah
        public void a(Object obj, StringBuilder sb) {
            this.f415a.a(new y("$maxKey", 1), sb);
        }
    }

    class l extends c {
        l(ah ahVar) {
            super(ahVar);
        }

        @Override // com.chartboost.sdk.impl.ah
        public void a(Object obj, StringBuilder sb) {
            this.f415a.a(new y("$minKey", 1), sb);
        }
    }

    class n extends c {
        n(ah ahVar) {
            super(ahVar);
        }

        @Override // com.chartboost.sdk.impl.ah
        public void a(Object obj, StringBuilder sb) {
            this.f415a.a(new y("$oid", obj.toString()), sb);
        }
    }

    class o extends c {
        o(ah ahVar) {
            super(ahVar);
        }

        @Override // com.chartboost.sdk.impl.ah
        public void a(Object obj, StringBuilder sb) {
            y yVar = new y();
            yVar.put("$regex", obj.toString());
            if (((Pattern) obj).flags() != 0) {
                yVar.put("$options", z.a(((Pattern) obj).flags()));
            }
            this.f415a.a(yVar, sb);
        }
    }

    class p extends ac {
        private p() {
        }

        /* synthetic */ p(p pVar) {
            this();
        }

        @Override // com.chartboost.sdk.impl.ah
        public void a(Object obj, StringBuilder sb) {
            ae.a(sb, (String) obj);
        }
    }

    class r extends c {
        r(ah ahVar) {
            super(ahVar);
        }

        @Override // com.chartboost.sdk.impl.ah
        public void a(Object obj, StringBuilder sb) {
            y yVar = new y();
            yVar.put("$uuid", ((UUID) obj).toString());
            this.f415a.a(yVar, sb);
        }
    }
}
