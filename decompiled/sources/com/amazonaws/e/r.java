package com.amazonaws.e;

import com.amazonaws.javax.xml.stream.XMLEventReader;
import com.amazonaws.javax.xml.stream.events.Attribute;
import com.amazonaws.javax.xml.stream.events.XMLEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class r {
    private XMLEvent b;
    private final XMLEventReader c;
    private Iterator<?> g;
    private final Map<String, String> h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Stack<String> f113a = new Stack<>();
    private String d = "";
    private Map<String, String> e = new HashMap();
    private List<s> f = new ArrayList();

    public r(XMLEventReader xMLEventReader, Map<String, String> map) {
        this.c = xMLEventReader;
        this.h = map;
    }

    private void a(XMLEvent xMLEvent) {
        if (xMLEvent == null) {
            return;
        }
        if (xMLEvent.isEndElement()) {
            this.f113a.pop();
            this.d = "";
            Iterator<String> it = this.f113a.iterator();
            while (it.hasNext()) {
                this.d += "/" + it.next();
            }
            return;
        }
        if (xMLEvent.isStartElement()) {
            this.f113a.push(xMLEvent.asStartElement().getName().b());
            this.d += "/" + xMLEvent.asStartElement().getName().b();
        } else if (xMLEvent.isAttribute()) {
            Attribute attribute = (Attribute) xMLEvent;
            this.d = "";
            Iterator<String> it2 = this.f113a.iterator();
            while (it2.hasNext()) {
                this.d += "/" + it2.next();
            }
            this.d += "/@" + attribute.getName().b();
        }
    }

    public String a() {
        XMLEvent xMLEventPeek;
        if (this.b.isAttribute()) {
            return ((Attribute) this.b).getValue();
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            xMLEventPeek = this.c.peek();
            if (xMLEventPeek.getEventType() != 4) {
                break;
            }
            this.c.nextEvent();
            sb.append(xMLEventPeek.asCharacters().getData());
        }
        if (xMLEventPeek.getEventType() == 2) {
            return sb.toString();
        }
        throw new RuntimeException("Encountered unexpected event: " + xMLEventPeek.toString());
    }

    public void a(String str, int i, String str2) {
        this.f.add(new s(this, str, i, str2));
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
        return i == b() && this.d.endsWith(new StringBuilder().append("/").append(str).toString());
    }

    public int b() {
        return this.f113a.size();
    }

    public boolean c() {
        return this.c.peek().isStartDocument();
    }

    public XMLEvent d() {
        XMLEvent xMLEventPeek;
        if (this.g == null || !this.g.hasNext()) {
            this.b = this.c.nextEvent();
        } else {
            this.b = (XMLEvent) this.g.next();
        }
        if (this.b.isStartElement()) {
            this.g = this.b.asStartElement().getAttributes();
        }
        a(this.b);
        if (this.c.hasNext() && (xMLEventPeek = this.c.peek()) != null && xMLEventPeek.isCharacters()) {
            for (s sVar : this.f) {
                if (a(sVar.f114a, sVar.b)) {
                    this.e.put(sVar.c, xMLEventPeek.asCharacters().getData());
                }
            }
        }
        return this.b;
    }

    public Map<String, String> e() {
        return this.e;
    }
}
