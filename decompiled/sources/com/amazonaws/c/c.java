package com.amazonaws.c;

import com.amazonaws.e.t;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c implements j<com.amazonaws.b> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List<t<com.amazonaws.b, Node>> f86a;

    public c(List<t<com.amazonaws.b, Node>> list) {
        this.f86a = list;
    }

    @Override // com.amazonaws.c.j
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public com.amazonaws.b b(i iVar) throws SAXException, IOException {
        Document documentA = com.amazonaws.f.n.a(iVar.c());
        Iterator<t<com.amazonaws.b, Node>> it = this.f86a.iterator();
        while (it.hasNext()) {
            com.amazonaws.b bVarUnmarshall = it.next().unmarshall(documentA);
            if (bVarUnmarshall != null) {
                bVarUnmarshall.setStatusCode(iVar.d());
                return bVarUnmarshall;
            }
        }
        throw new com.amazonaws.a("Unable to unmarshall error response from service");
    }

    @Override // com.amazonaws.c.j
    public boolean a() {
        return false;
    }
}
