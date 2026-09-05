package com.topfreegames.bikerace;

import android.util.Xml;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: RemoteConfig.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class bc {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f1133a = null;

    private bc() {
    }

    /* synthetic */ bc(bc bcVar) {
        this();
    }

    public List<bd> a(InputStream inputStream) throws IOException {
        try {
            XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
            xmlPullParserNewPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
            xmlPullParserNewPullParser.setInput(inputStream, null);
            xmlPullParserNewPullParser.nextTag();
            return b(xmlPullParserNewPullParser);
        } finally {
            inputStream.close();
        }
    }

    private bd a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, f1133a, "item");
        String attributeValue = xmlPullParser.getAttributeValue(null, "name");
        String strC = c(xmlPullParser);
        xmlPullParser.require(3, f1133a, "item");
        return new bd(attributeValue, strC, null);
    }

    private List<bd> b(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        xmlPullParser.require(2, f1133a, "config");
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equals("item")) {
                    arrayList.add(a(xmlPullParser));
                } else {
                    d(xmlPullParser);
                }
            }
        }
        return arrayList;
    }

    private String c(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (xmlPullParser.next() != 4) {
            return "";
        }
        String text = xmlPullParser.getText();
        xmlPullParser.nextTag();
        return text;
    }

    private void d(XmlPullParser xmlPullParser) {
        if (xmlPullParser.getEventType() != 2) {
            throw new IllegalStateException();
        }
        int i = 1;
        while (i != 0) {
            switch (xmlPullParser.next()) {
                case 2:
                    i++;
                    break;
                case 3:
                    i--;
                    break;
            }
        }
    }
}
