package com.amazonaws.javax.xml.stream.xerces.impl.msg;

import com.amazonaws.javax.xml.stream.xerces.util.MessageFormatter;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class XMLMessageFormatter implements MessageFormatter {
    public static final String XMLNS_DOMAIN = "http://www.w3.org/TR/1999/REC-xml-names-19990114";
    public static final String XML_DOMAIN = "http://www.w3.org/TR/1998/REC-xml-19980210";
    private Locale fLocale = null;
    private ResourceBundle fResourceBundle = null;

    @Override // com.amazonaws.javax.xml.stream.xerces.util.MessageFormatter
    public String formatMessage(Locale locale, String str, Object[] objArr) {
        if (this.fResourceBundle == null || locale != this.fLocale) {
            if (locale != null) {
                this.fResourceBundle = PropertyResourceBundle.getBundle("com.amazonaws.javax.xml.stream.xerces.impl.msg.XMLMessages", locale);
                this.fLocale = locale;
            }
            if (this.fResourceBundle == null) {
                this.fResourceBundle = PropertyResourceBundle.getBundle("com.amazonaws.javax.xml.stream.xerces.impl.msg.XMLMessages");
            }
        }
        try {
            String string = this.fResourceBundle.getString(str);
            if (objArr != null) {
                try {
                    string = MessageFormat.format(string, objArr);
                } catch (Exception e) {
                    string = new StringBuffer().append(this.fResourceBundle.getString("FormatFailed")).append(" ").append(this.fResourceBundle.getString(str)).toString();
                }
            }
            if (string != null) {
                return string;
            }
            if (objArr.length > 0) {
                StringBuffer stringBuffer = new StringBuffer(str);
                stringBuffer.append('?');
                for (int i = 0; i < objArr.length; i++) {
                    if (i > 0) {
                        stringBuffer.append('&');
                    }
                    stringBuffer.append(String.valueOf(objArr[i]));
                }
                return str;
            }
            return str;
        } catch (MissingResourceException e2) {
            throw new MissingResourceException(str, this.fResourceBundle.getString("BadMessageKey"), str);
        }
    }
}
