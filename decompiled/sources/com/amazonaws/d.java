package com.amazonaws;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected URI f96a;
    protected g b;
    protected com.amazonaws.c.a c;
    protected final List<com.amazonaws.b.c> d = Collections.synchronizedList(new LinkedList());

    public d(g gVar) {
        this.b = gVar;
        this.c = new com.amazonaws.c.a(gVar);
    }

    protected com.amazonaws.c.d a() {
        return new com.amazonaws.c.d(this.d);
    }

    public void a(g gVar) {
        this.b = gVar;
        this.c = new com.amazonaws.c.a(gVar);
    }

    public void a(String str) {
        if (!str.contains("://")) {
            str = this.b.a().toString() + "://" + str;
        }
        try {
            this.f96a = new URI(str);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
