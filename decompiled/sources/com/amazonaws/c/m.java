package com.amazonaws.c;

import com.amazonaws.e.t;
import com.amazonaws.e.u;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class m<T> implements j<com.amazonaws.f<T>> {
    private static final Log c = LogFactory.getLog("com.amazonaws.request");
    private static JsonFactory d = new JsonFactory();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f93a = false;
    private t<T, com.amazonaws.e.c> b;

    public m(t<T, com.amazonaws.e.c> tVar) {
        this.b = tVar;
        if (this.b == null) {
            this.b = new u();
        }
    }

    @Override // com.amazonaws.c.j
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public com.amazonaws.f<T> b(i iVar) {
        c.trace("Parsing service response JSON");
        JsonParser jsonParserCreateJsonParser = !this.f93a ? d.createJsonParser(iVar.c()) : null;
        try {
            com.amazonaws.f<T> fVar = new com.amazonaws.f<>();
            com.amazonaws.e.c cVar = new com.amazonaws.e.c(jsonParserCreateJsonParser, iVar);
            a(cVar);
            fVar.a(this.b.unmarshall(cVar));
            Map<String, String> mapD = cVar.d();
            mapD.put("AWS_REQUEST_ID", iVar.b().get("x-amzn-RequestId"));
            fVar.a(new com.amazonaws.l(mapD));
            c.trace("Done parsing service response");
            return fVar;
        } finally {
            if (!this.f93a) {
                try {
                    jsonParserCreateJsonParser.close();
                } catch (Exception e) {
                }
            }
        }
    }

    protected void a(com.amazonaws.e.c cVar) {
    }

    @Override // com.amazonaws.c.j
    public boolean a() {
        return this.f93a;
    }
}
