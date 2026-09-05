package com.amazonaws.e;

import com.amazonaws.transform.JsonUnmarshallerContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public JsonToken f98a;
    private final JsonParser b;
    private String e;
    private JsonToken h;
    private final com.amazonaws.c.i i;
    private final Stack<String> c = new Stack<>();
    private String d = "";
    private Map<String, String> f = new HashMap();
    private List<JsonUnmarshallerContext.MetadataExpression> g = new ArrayList();

    public c(JsonParser jsonParser, com.amazonaws.c.i iVar) {
        this.b = jsonParser;
        this.i = iVar;
    }

    private void e() {
        if (this.f98a == null) {
            return;
        }
        if (this.f98a == JsonToken.START_OBJECT || this.f98a == JsonToken.START_ARRAY) {
            if (this.e != null) {
                this.c.push(this.e);
                this.e = null;
            }
        } else if (this.f98a == JsonToken.END_OBJECT || this.f98a == JsonToken.END_ARRAY) {
            if (!this.c.isEmpty()) {
                this.c.pop();
            }
            this.e = null;
        } else if (this.f98a == JsonToken.FIELD_NAME) {
            this.e = this.b.getText();
        }
        f();
    }

    private void f() {
        this.d = "";
        Iterator<String> it = this.c.iterator();
        while (it.hasNext()) {
            this.d += "/" + it.next();
        }
        if (this.e != null) {
            this.d += "/" + this.e;
        }
        if (this.d == "") {
            this.d = "/";
        }
    }

    public int a() {
        int size = this.c.size();
        return this.e != null ? size + 1 : size;
    }

    public boolean a(String str, int i) {
        if (str.equals(".")) {
            return true;
        }
        int iIndexOf = -1;
        while (true) {
            iIndexOf = str.indexOf("/", iIndexOf + 1);
            if (iIndexOf <= -1) {
                break;
            }
            if (str.charAt(iIndexOf + 1) != '@') {
                i++;
            }
        }
        return this.d.endsWith(new StringBuilder().append("/").append(str).toString()) && i == a();
    }

    public String b() {
        switch (this.f98a) {
            case VALUE_STRING:
                return this.b.getText();
            case VALUE_FALSE:
                return "false";
            case VALUE_TRUE:
                return "true";
            case VALUE_NULL:
                return null;
            case VALUE_NUMBER_FLOAT:
            case VALUE_NUMBER_INT:
                return this.b.getNumberValue().toString();
            case FIELD_NAME:
                return this.b.getText();
            default:
                throw new RuntimeException("We expected a VALUE token but got: " + this.f98a);
        }
    }

    public JsonToken c() {
        JsonToken jsonTokenNextToken = this.h != null ? this.h : this.b.nextToken();
        this.f98a = jsonTokenNextToken;
        this.h = null;
        e();
        return jsonTokenNextToken;
    }

    public Map<String, String> d() {
        return this.f;
    }

    public String toString() {
        return this.d;
    }
}
