package org.c.e;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.c.d.h;
import org.c.d.i;
import org.c.d.j;
import org.c.d.k;
import org.c.d.m;

/* JADX INFO: compiled from: OAuth10aServiceImpl.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a implements c {
    private static /* synthetic */ int[] c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private org.c.d.a f1626a;
    private org.c.a.a.b b;

    static /* synthetic */ int[] b() {
        int[] iArr = c;
        if (iArr == null) {
            iArr = new int[j.valuesCustom().length];
            try {
                iArr[j.Header.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[j.QueryString.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            c = iArr;
        }
        return iArr;
    }

    public a(org.c.a.a.b bVar, org.c.d.a aVar) {
        this.b = bVar;
        this.f1626a = aVar;
    }

    public k a(Map<String, String> map, int i, TimeUnit timeUnit) {
        return a(map, new b(i, timeUnit));
    }

    @Override // org.c.e.c
    public k a(Map<String, String> map) {
        return a(map, 2, TimeUnit.SECONDS);
    }

    public k a(Map<String, String> map, h hVar) {
        this.f1626a.a("obtaining request token from " + this.b.b());
        org.c.d.c cVar = new org.c.d.c(this.b.k(), this.b.b());
        this.f1626a.a("setting oauth_callback to " + this.f1626a.c());
        cVar.a("oauth_callback", this.f1626a.c());
        a(cVar, map);
        a(cVar, org.c.d.b.f1615a);
        a(cVar);
        this.f1626a.a("sending request...");
        i iVarA = cVar.a(hVar);
        String strB = iVarA.b();
        this.f1626a.a("response status code: " + iVarA.d());
        this.f1626a.a("response body: " + strB);
        return this.b.g().a(strB);
    }

    private void a(org.c.d.c cVar, k kVar) {
        cVar.a("oauth_timestamp", this.b.i().a());
        cVar.a("oauth_nonce", this.b.i().b());
        cVar.a("oauth_consumer_key", this.f1626a.a());
        cVar.a("oauth_signature_method", this.b.h().a());
        cVar.a("oauth_version", a());
        if (this.f1626a.f()) {
            cVar.a("scope", this.f1626a.e());
        }
        if (cVar.i() != null) {
            cVar.a("oauth_body_hash", a(cVar.i().getBytes()));
        }
        cVar.a("oauth_signature", b(cVar, kVar));
        this.f1626a.a("appended additional OAuth parameters: " + org.c.g.a.a(cVar.a()));
    }

    private static String a(byte[] bArr) {
        try {
            return com.topfreegames.bikerace.m.c.a(MessageDigest.getInstance("SHA-1").digest(bArr)).trim();
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    private void a(org.c.d.c cVar, Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            cVar.c(entry.getKey(), entry.getValue());
        }
    }

    public k a(k kVar, m mVar, int i, TimeUnit timeUnit) {
        return a(kVar, mVar, new b(i, timeUnit));
    }

    @Override // org.c.e.c
    public k a(k kVar, m mVar) {
        return a(kVar, mVar, 2, TimeUnit.SECONDS);
    }

    public k a(k kVar, m mVar, h hVar) {
        this.f1626a.a("obtaining access token from " + this.b.a());
        org.c.d.c cVar = new org.c.d.c(this.b.j(), this.b.a());
        cVar.a("oauth_token", kVar.a());
        cVar.a("oauth_verifier", mVar.a());
        this.f1626a.a("setting token to: " + kVar + " and verifier to: " + mVar);
        a(cVar, kVar);
        a(cVar);
        this.f1626a.a("sending request...");
        i iVarA = cVar.a(hVar);
        String strB = iVarA.b();
        this.f1626a.a("response status code: " + iVarA.d());
        this.f1626a.a("response body: " + strB);
        return this.b.d().a(strB);
    }

    @Override // org.c.e.c
    public void a(k kVar, org.c.d.c cVar) {
        this.f1626a.a("signing request: " + cVar.d());
        if (!kVar.e()) {
            cVar.a("oauth_token", kVar.a());
        }
        this.f1626a.a("setting token to: " + kVar);
        a(cVar, kVar);
        a(cVar);
    }

    @Override // org.c.e.c
    public boolean a(k kVar, org.c.d.c cVar, i iVar) {
        return this.b.c().a(kVar, this.f1626a.b(), cVar, iVar, this.b.h());
    }

    public String a() {
        return XMLStreamWriterImpl.DEFAULT_XML_VERSION;
    }

    @Override // org.c.e.c
    public String a(k kVar) {
        return this.b.a(kVar);
    }

    private String b(org.c.d.c cVar, k kVar) {
        this.f1626a.a("generating signature...");
        this.f1626a.a("using base64 encoder: " + org.c.f.a.b());
        String strA = this.b.e().a(cVar);
        String strA2 = this.b.h().a(strA, this.f1626a.b(), kVar.b());
        this.f1626a.a("base string is: " + strA);
        this.f1626a.a("signature is: " + strA2);
        return strA2;
    }

    private void a(org.c.d.c cVar) {
        switch (b()[this.f1626a.d().ordinal()]) {
            case 1:
                this.f1626a.a("using Http Header signature");
                cVar.b("Authorization", this.b.f().a(cVar));
                break;
            case 2:
                this.f1626a.a("using Querystring signature");
                for (Map.Entry<String, String> entry : cVar.a().entrySet()) {
                    cVar.d(entry.getKey(), entry.getValue());
                }
                break;
        }
    }
}
