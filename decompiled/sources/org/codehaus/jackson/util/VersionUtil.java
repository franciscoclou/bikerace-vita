package org.codehaus.jackson.util;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import org.codehaus.jackson.Version;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class VersionUtil {
    public static final String VERSION_FILE = "VERSION.txt";
    private static final Pattern VERSION_SEPARATOR = Pattern.compile("[-_./;:]");

    public static Version versionFor(Class<?> cls) {
        Version version = null;
        try {
            InputStream resourceAsStream = cls.getResourceAsStream(VERSION_FILE);
            if (resourceAsStream != null) {
                try {
                    version = parseVersion(new BufferedReader(new InputStreamReader(resourceAsStream, XMLStreamWriterImpl.UTF_8)).readLine());
                    try {
                        resourceAsStream.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } catch (Throwable th) {
                    try {
                        resourceAsStream.close();
                        throw th;
                    } catch (IOException e2) {
                        throw new RuntimeException(e2);
                    }
                }
            }
        } catch (IOException e3) {
        }
        return version == null ? Version.unknownVersion() : version;
    }

    public static Version parseVersion(String str) {
        if (str == null) {
            return null;
        }
        String strTrim = str.trim();
        if (strTrim.length() == 0) {
            return null;
        }
        String[] strArrSplit = VERSION_SEPARATOR.split(strTrim);
        if (strArrSplit.length >= 2) {
            return new Version(parseVersionPart(strArrSplit[0]), parseVersionPart(strArrSplit[1]), strArrSplit.length > 2 ? parseVersionPart(strArrSplit[2]) : 0, strArrSplit.length > 3 ? strArrSplit[3] : null);
        }
        return null;
    }

    protected static int parseVersionPart(String str) {
        String string = str.toString();
        int length = string.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = string.charAt(i2);
            if (cCharAt > '9' || cCharAt < '0') {
                break;
            }
            i = (i * 10) + (cCharAt - '0');
        }
        return i;
    }
}
