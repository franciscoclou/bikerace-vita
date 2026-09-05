package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.stream.xerces.impl.msg.XMLMessageFormatter;
import com.amazonaws.javax.xml.stream.xerces.util.MessageFormatter;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLLocator;
import com.amazonaws.javax.xml.stream.xerces.xni.XNIException;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class StaxErrorReporter extends XMLErrorReporter {
    protected XMLReporter fXMLReporter = null;

    public StaxErrorReporter(PropertyManager propertyManager) {
        putMessageFormatter(XMLMessageFormatter.XML_DOMAIN, new XMLMessageFormatter());
        reset(propertyManager);
    }

    public StaxErrorReporter() {
        putMessageFormatter(XMLMessageFormatter.XML_DOMAIN, new XMLMessageFormatter());
    }

    public void reset(PropertyManager propertyManager) {
        this.fXMLReporter = (XMLReporter) propertyManager.getProperty(XMLInputFactory.REPORTER);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLErrorReporter
    public void reportError(XMLLocator xMLLocator, String str, String str2, Object[] objArr, short s) {
        String string;
        MessageFormatter messageFormatter = getMessageFormatter(str);
        if (messageFormatter != null) {
            string = messageFormatter.formatMessage(this.fLocale, str2, objArr);
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            stringBuffer.append('#');
            stringBuffer.append(str2);
            int length = objArr != null ? objArr.length : 0;
            if (length > 0) {
                stringBuffer.append('?');
                for (int i = 0; i < length; i++) {
                    stringBuffer.append(objArr[i]);
                    if (i < length - 1) {
                        stringBuffer.append('&');
                    }
                }
            }
            string = stringBuffer.toString();
        }
        switch (s) {
            case 0:
                try {
                    if (this.fXMLReporter != null) {
                        this.fXMLReporter.report(string, "WARNING", null, convertToStaxLocation(xMLLocator));
                        return;
                    }
                    return;
                } catch (XMLStreamException e) {
                    throw new XNIException(e);
                }
            case 1:
                try {
                    if (this.fXMLReporter != null) {
                        this.fXMLReporter.report(string, "ERROR", null, convertToStaxLocation(xMLLocator));
                        return;
                    }
                    return;
                } catch (XMLStreamException e2) {
                    throw new XNIException(e2);
                }
            case 2:
                if (!this.fContinueAfterFatalError) {
                    throw new XNIException(string);
                }
                return;
            default:
                return;
        }
    }

    Location convertToStaxLocation(final XMLLocator xMLLocator) {
        return new Location() { // from class: com.amazonaws.javax.xml.stream.StaxErrorReporter.1
            @Override // com.amazonaws.javax.xml.stream.Location
            public int getColumnNumber() {
                return xMLLocator.getColumnNumber();
            }

            @Override // com.amazonaws.javax.xml.stream.Location
            public int getLineNumber() {
                return xMLLocator.getLineNumber();
            }

            @Override // com.amazonaws.javax.xml.stream.Location
            public String getPublicId() {
                return xMLLocator.getPublicId();
            }

            @Override // com.amazonaws.javax.xml.stream.Location
            public String getSystemId() {
                return xMLLocator.getLiteralSystemId();
            }

            @Override // com.amazonaws.javax.xml.stream.Location
            public int getCharacterOffset() {
                return xMLLocator.getCharacterOffset();
            }
        };
    }
}
