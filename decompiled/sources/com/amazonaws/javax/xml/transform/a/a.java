package com.amazonaws.javax.xml.transform.a;

import com.amazonaws.javax.xml.transform.d;
import org.w3c.dom.Node;

/* JADX INFO: compiled from: DOMResult.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Node f138a = null;
    private Node b = null;
    private String c = null;

    public a() {
        a((Node) null);
        b(null);
        a((String) null);
    }

    public void a(Node node) {
        if (this.b != null) {
            if (node == null) {
                throw new IllegalStateException("Cannot create a DOMResult when the nextSibling is contained by the \"null\" node.");
            }
            if ((node.compareDocumentPosition(this.b) & 16) == 0) {
                throw new IllegalArgumentException("Cannot create a DOMResult when the nextSibling is not contained by the node.");
            }
        }
        this.f138a = node;
    }

    public Node a() {
        return this.f138a;
    }

    public void b(Node node) {
        if (node != null) {
            if (this.f138a == null) {
                throw new IllegalStateException("Cannot create a DOMResult when the nextSibling is contained by the \"null\" node.");
            }
            if ((this.f138a.compareDocumentPosition(node) & 16) == 0) {
                throw new IllegalArgumentException("Cannot create a DOMResult when the nextSibling is not contained by the node.");
            }
        }
        this.b = node;
    }

    public void a(String str) {
        this.c = str;
    }

    @Override // com.amazonaws.javax.xml.transform.d
    public String getSystemId() {
        return this.c;
    }
}
