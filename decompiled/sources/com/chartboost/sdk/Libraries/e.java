package com.chartboost.sdk.Libraries;

import java.math.BigInteger;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static j f382a = new j();
    private static i b = new i(null);
    private static f c = new f(0 == true ? 1 : 0);
    private static b d = new b(0 == true ? 1 : 0);
    private static h e = new h(0 == true ? 1 : 0);

    public abstract class c extends a {
    }

    public abstract class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f383a = null;

        public abstract String a();

        public abstract boolean a(Object obj);

        public boolean a(Object obj, StringBuilder sb) {
            boolean zA = a(obj);
            if (!zA) {
                sb.append(this.f383a != null ? this.f383a : a());
            }
            return zA;
        }
    }

    /* JADX INFO: renamed from: com.chartboost.sdk.Libraries.e$e, reason: collision with other inner class name */
    class C0001e extends a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Class<?> f385a;

        public C0001e(Class<?> cls) {
            this.f385a = cls;
        }

        @Override // com.chartboost.sdk.Libraries.e.a
        public boolean a(Object obj) {
            return this.f385a.isInstance(obj);
        }

        @Override // com.chartboost.sdk.Libraries.e.a
        public String a() {
            return "object must be an instance of " + this.f385a.getName() + ".";
        }
    }

    class j extends C0001e {
        public j() {
            super(String.class);
        }
    }

    class i extends a {
        private i() {
        }

        /* synthetic */ i(i iVar) {
            this();
        }

        @Override // com.chartboost.sdk.Libraries.e.a
        public boolean a(Object obj) {
            return (obj instanceof Number) || Integer.TYPE.isInstance(obj) || Long.TYPE.isInstance(obj) || Short.TYPE.isInstance(obj) || Float.TYPE.isInstance(obj) || Double.TYPE.isInstance(obj) || Byte.TYPE.isInstance(obj);
        }

        @Override // com.chartboost.sdk.Libraries.e.a
        public String a() {
            return "object must be a number (primitive type or derived from Number).";
        }
    }

    public static a a() {
        return b;
    }

    class f extends a {
        private f() {
        }

        /* synthetic */ f(f fVar) {
            this();
        }

        @Override // com.chartboost.sdk.Libraries.e.a
        public boolean a(Object obj) {
            return Integer.class.isInstance(obj) || Long.class.isInstance(obj) || Short.class.isInstance(obj) || Byte.class.isInstance(obj) || BigInteger.class.isInstance(obj) || Integer.TYPE.isInstance(obj) || Long.TYPE.isInstance(obj) || Short.TYPE.isInstance(obj) || Byte.TYPE.isInstance(obj);
        }

        @Override // com.chartboost.sdk.Libraries.e.a
        public String a() {
            return "object must be a number w/o decimals (int, long, short, or byte).";
        }
    }

    class b extends a {
        private b() {
        }

        /* synthetic */ b(b bVar) {
            this();
        }

        @Override // com.chartboost.sdk.Libraries.e.a
        public boolean a(Object obj) {
            return Boolean.class.isInstance(obj) || Boolean.TYPE.isInstance(obj);
        }

        @Override // com.chartboost.sdk.Libraries.e.a
        public String a() {
            return "object must be a boolean.";
        }
    }

    class h extends a {
        private h() {
        }

        /* synthetic */ h(h hVar) {
            this();
        }

        @Override // com.chartboost.sdk.Libraries.e.a
        public boolean a(Object obj) {
            return obj == null || obj == JSONObject.NULL;
        }

        @Override // com.chartboost.sdk.Libraries.e.a
        public String a() {
            return "object must be null.";
        }
    }

    class k extends a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected String f387a = null;
        private a[] b;

        public k(a[] aVarArr) {
            this.b = aVarArr;
        }

        @Override // com.chartboost.sdk.Libraries.e.a
        public boolean a(Object obj) {
            for (int i = 0; i < this.b.length; i++) {
                if (!this.b[i].a(obj)) {
                    this.f387a = "object failed to match: <" + this.b[i].a() + ">";
                    return false;
                }
            }
            return true;
        }

        @Override // com.chartboost.sdk.Libraries.e.a
        public String a() {
            if (this.f387a != null) {
                return this.f387a;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("object must match ALL of the following: ");
            for (int i = 0; i < this.b.length; i++) {
                sb.append("<");
                sb.append(this.b[i].a());
                sb.append(">");
                if (i < this.b.length - 1) {
                    sb.append(", ");
                }
            }
            return sb.toString();
        }
    }

    public static a a(a... aVarArr) {
        return new k(aVarArr);
    }

    public class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f386a;
        private a b;

        public g(String str, a aVar) {
            this.f386a = str;
            this.b = aVar;
        }
    }

    public static g a(String str, a aVar) {
        return new g(str, aVar);
    }

    class d extends a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected g[] f384a;
        protected String b = null;

        public d(g[] gVarArr) {
            this.f384a = gVarArr;
        }

        @Override // com.chartboost.sdk.Libraries.e.a
        public boolean a(Object obj) {
            if (obj instanceof Map) {
                Map map = (Map) obj;
                for (Map.Entry entry : map.entrySet()) {
                    if (!(entry.getKey() instanceof String)) {
                        this.b = "key '" + entry.getKey().toString() + "' is not a string";
                        return false;
                    }
                }
                if (this.f384a != null && this.f384a.length >= 1) {
                    for (int i = 0; i < this.f384a.length; i++) {
                        String str = this.f384a[i].f386a;
                        a aVar = this.f384a[i].b;
                        if (!map.containsKey(str)) {
                            if (!aVar.a(null)) {
                                this.b = "no key for required mapping '" + str + "' : <" + aVar.a() + ">";
                                return false;
                            }
                        } else if (!aVar.a(map.get(str))) {
                            this.b = "key '" + str + "' fails to match: <" + aVar.a() + ">";
                            return false;
                        }
                    }
                }
                return true;
            }
            if (!(obj instanceof JSONObject)) {
                return false;
            }
            JSONObject jSONObject = (JSONObject) obj;
            if (this.f384a != null && this.f384a.length >= 1) {
                for (int i2 = 0; i2 < this.f384a.length; i2++) {
                    String str2 = this.f384a[i2].f386a;
                    a aVar2 = this.f384a[i2].b;
                    try {
                        if (!aVar2.a(jSONObject.get(str2))) {
                            this.b = "key '" + str2 + "' fails to match: <" + aVar2.a() + ">";
                            return false;
                        }
                        continue;
                    } catch (JSONException e) {
                        if (!aVar2.a(null)) {
                            this.b = "no key for required mapping '" + str2 + "' : <" + aVar2.a() + ">";
                            return false;
                        }
                    }
                }
            }
            return true;
        }

        @Override // com.chartboost.sdk.Libraries.e.a
        public String a() {
            if (this.b != null) {
                return this.b;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("object must contain the following key-value schema: {\n");
            for (int i = 0; i < this.f384a.length; i++) {
                sb.append("<");
                sb.append(this.f384a[i].f386a);
                sb.append(": [");
                sb.append(this.f384a[i].b.a());
                sb.append("]>");
                if (i < this.f384a.length - 1) {
                    sb.append(",\n");
                }
            }
            sb.append("}");
            return sb.toString();
        }
    }

    public static a a(g... gVarArr) {
        return new d(gVarArr);
    }
}
