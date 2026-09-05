package com.amazonaws.f;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static e f133a = new e();
    private static Log b = LogFactory.getLog(n.class);
    private static DocumentBuilderFactory c = DocumentBuilderFactory.newInstance();

    public static String a(String str, Node node) {
        return b(str, node);
    }

    private static String a(Node node, String str) {
        int i = 0;
        while (i < str.length()) {
            int iIndexOf = str.indexOf("/", i);
            node = b(node, iIndexOf == -1 ? str.substring(i) : str.substring(i, iIndexOf));
            if (iIndexOf == -1) {
                break;
            }
            i = iIndexOf + 1;
        }
        if (node != null && node.getFirstChild() != null) {
            return node.getFirstChild().getNodeValue();
        }
        if (node != null) {
            return node.getNodeValue();
        }
        return null;
    }

    public static Document a(InputStream inputStream) throws SAXException, IOException {
        g gVar = new g(inputStream);
        Document document = c.newDocumentBuilder().parse(gVar);
        gVar.close();
        return document;
    }

    public static boolean a(Node node) {
        return node == null;
    }

    private static String b(String str, Node node) {
        String strA;
        if (a(node) || (strA = a(node, str)) == null) {
            return null;
        }
        return strA.trim();
    }

    private static Node b(Node node, String str) {
        if (node == null) {
            return null;
        }
        if (node.getNodeName().equals(str)) {
            return node;
        }
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (childNodes.item(i).getNodeName().equals(str)) {
                return childNodes.item(i);
            }
        }
        return null;
    }
}
