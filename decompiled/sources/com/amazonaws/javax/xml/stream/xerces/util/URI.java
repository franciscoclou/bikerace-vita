package com.amazonaws.javax.xml.stream.xerces.util;

import java.io.IOException;
import java.io.Serializable;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class URI implements Serializable {
    private static boolean DEBUG = false;
    private static final String MARK_CHARACTERS = "-_.!~*'()";
    private static final String RESERVED_CHARACTERS = ";/?:@&=+$,[]";
    private static final String SCHEME_CHARACTERS = "+-.";
    private static final String USERINFO_CHARACTERS = ";:&=+$,";
    private String m_fragment;
    private String m_host;
    private String m_path;
    private int m_port;
    private String m_queryString;
    private String m_scheme;
    private String m_userinfo;

    public class MalformedURIException extends IOException {
        public MalformedURIException() {
        }

        public MalformedURIException(String str) {
            super(str);
        }
    }

    public URI() {
        this.m_scheme = null;
        this.m_userinfo = null;
        this.m_host = null;
        this.m_port = -1;
        this.m_path = null;
        this.m_queryString = null;
        this.m_fragment = null;
    }

    public URI(URI uri) {
        this.m_scheme = null;
        this.m_userinfo = null;
        this.m_host = null;
        this.m_port = -1;
        this.m_path = null;
        this.m_queryString = null;
        this.m_fragment = null;
        initialize(uri);
    }

    public URI(String str) {
        this((URI) null, str);
    }

    public URI(URI uri, String str) throws MalformedURIException {
        this.m_scheme = null;
        this.m_userinfo = null;
        this.m_host = null;
        this.m_port = -1;
        this.m_path = null;
        this.m_queryString = null;
        this.m_fragment = null;
        initialize(uri, str);
    }

    public URI(String str, String str2) throws MalformedURIException {
        this.m_scheme = null;
        this.m_userinfo = null;
        this.m_host = null;
        this.m_port = -1;
        this.m_path = null;
        this.m_queryString = null;
        this.m_fragment = null;
        if (str == null || str.trim().length() == 0) {
            throw new MalformedURIException("Cannot construct URI with null/empty scheme!");
        }
        if (str2 == null || str2.trim().length() == 0) {
            throw new MalformedURIException("Cannot construct URI with null/empty scheme-specific part!");
        }
        setScheme(str);
        setPath(str2);
    }

    public URI(String str, String str2, String str3, String str4, String str5) {
        this(str, null, str2, -1, str3, str4, str5);
    }

    public URI(String str, String str2, String str3, int i, String str4, String str5, String str6) throws MalformedURIException {
        this.m_scheme = null;
        this.m_userinfo = null;
        this.m_host = null;
        this.m_port = -1;
        this.m_path = null;
        this.m_queryString = null;
        this.m_fragment = null;
        if (str == null || str.trim().length() == 0) {
            throw new MalformedURIException("Scheme is required!");
        }
        if (str3 == null) {
            if (str2 != null) {
                throw new MalformedURIException("Userinfo may not be specified if host is not specified!");
            }
            if (i != -1) {
                throw new MalformedURIException("Port may not be specified if host is not specified!");
            }
        }
        if (str4 != null) {
            if (str4.indexOf(63) != -1 && str5 != null) {
                throw new MalformedURIException("Query string cannot be specified in path and query string!");
            }
            if (str4.indexOf(35) != -1 && str6 != null) {
                throw new MalformedURIException("Fragment cannot be specified in both the path and fragment!");
            }
        }
        setScheme(str);
        setHost(str3);
        setPort(i);
        setUserinfo(str2);
        setPath(str4);
        setQueryString(str5);
        setFragment(str6);
    }

    private void initialize(URI uri) {
        this.m_scheme = uri.getScheme();
        this.m_userinfo = uri.getUserinfo();
        this.m_host = uri.getHost();
        this.m_port = uri.getPort();
        this.m_path = uri.getPath();
        this.m_queryString = uri.getQueryString();
        this.m_fragment = uri.getFragment();
    }

    private void initialize(URI uri, String str) throws MalformedURIException {
        int length;
        int iLastIndexOf;
        int iLastIndexOf2;
        if (uri == null && (str == null || str.trim().length() == 0)) {
            throw new MalformedURIException("Cannot initialize URI with empty parameters.");
        }
        if (str == null || str.trim().length() == 0) {
            initialize(uri);
            return;
        }
        String strTrim = str.trim();
        int length2 = strTrim.length();
        int iIndexOf = strTrim.indexOf(58);
        int iIndexOf2 = strTrim.indexOf(47);
        int iIndexOf3 = strTrim.indexOf(63);
        int iIndexOf4 = strTrim.indexOf(35);
        if (iIndexOf < 2 || ((iIndexOf > iIndexOf2 && iIndexOf2 != -1) || ((iIndexOf > iIndexOf3 && iIndexOf3 != -1) || (iIndexOf > iIndexOf4 && iIndexOf4 != -1)))) {
            if (uri == null && iIndexOf4 != 0) {
                throw new MalformedURIException("No scheme found in URI.");
            }
            length = 0;
        } else {
            initializeScheme(strTrim);
            length = this.m_scheme.length() + 1;
        }
        if (length + 1 < length2 && strTrim.substring(length).startsWith("//")) {
            int i = length + 2;
            length = i;
            while (length < length2) {
                char cCharAt = strTrim.charAt(length);
                if (cCharAt == '/' || cCharAt == '?' || cCharAt == '#') {
                    break;
                } else {
                    length++;
                }
            }
            if (length > i) {
                initializeAuthority(strTrim.substring(i, length));
            } else {
                this.m_host = "";
            }
        }
        initializePath(strTrim.substring(length));
        if (uri != null) {
            if (this.m_path.length() == 0 && this.m_scheme == null && this.m_host == null) {
                this.m_scheme = uri.getScheme();
                this.m_userinfo = uri.getUserinfo();
                this.m_host = uri.getHost();
                this.m_port = uri.getPort();
                this.m_path = uri.getPath();
                if (this.m_queryString == null) {
                    this.m_queryString = uri.getQueryString();
                    return;
                }
                return;
            }
            if (this.m_scheme == null) {
                this.m_scheme = uri.getScheme();
                if (this.m_host == null) {
                    this.m_userinfo = uri.getUserinfo();
                    this.m_host = uri.getHost();
                    this.m_port = uri.getPort();
                    if (this.m_path.length() <= 0 || !this.m_path.startsWith("/")) {
                        String str2 = new String();
                        String path = uri.getPath();
                        if (path != null && (iLastIndexOf2 = path.lastIndexOf(47)) != -1) {
                            str2 = path.substring(0, iLastIndexOf2 + 1);
                        }
                        String strConcat = str2.concat(this.m_path);
                        while (true) {
                            int iIndexOf5 = strConcat.indexOf("/./");
                            if (iIndexOf5 == -1) {
                                break;
                            } else {
                                strConcat = strConcat.substring(0, iIndexOf5 + 1).concat(strConcat.substring(iIndexOf5 + 3));
                            }
                        }
                        if (strConcat.endsWith("/.")) {
                            strConcat = strConcat.substring(0, strConcat.length() - 1);
                        }
                        int iLastIndexOf3 = 1;
                        while (true) {
                            int iIndexOf6 = strConcat.indexOf("/../", iLastIndexOf3);
                            if (iIndexOf6 <= 0) {
                                break;
                            }
                            String strSubstring = strConcat.substring(0, strConcat.indexOf("/../"));
                            iLastIndexOf3 = strSubstring.lastIndexOf(47);
                            if (iLastIndexOf3 != -1) {
                                if (!strSubstring.substring(iLastIndexOf3).equals("..")) {
                                    strConcat = strConcat.substring(0, iLastIndexOf3 + 1).concat(strConcat.substring(iIndexOf6 + 4));
                                } else {
                                    iLastIndexOf3 = iIndexOf6 + 4;
                                }
                            } else {
                                iLastIndexOf3 = iIndexOf6 + 4;
                            }
                        }
                        if (strConcat.endsWith("/..") && (iLastIndexOf = strConcat.substring(0, strConcat.length() - 3).lastIndexOf(47)) != -1) {
                            strConcat = strConcat.substring(0, iLastIndexOf + 1);
                        }
                        this.m_path = strConcat;
                    }
                }
            }
        }
    }

    private void initializeScheme(String str) throws MalformedURIException {
        int length = str.length();
        int i = 0;
        while (i < length) {
            char cCharAt = str.charAt(i);
            if (cCharAt == ':' || cCharAt == '/' || cCharAt == '?' || cCharAt == '#') {
                break;
            } else {
                i++;
            }
        }
        String strSubstring = str.substring(0, i);
        if (strSubstring.length() == 0) {
            throw new MalformedURIException("No scheme found in URI.");
        }
        setScheme(strSubstring);
    }

    /* JADX WARN: Code duplicated, block: B:35:0x008f  */
    private void initializeAuthority(String str) throws MalformedURIException {
        char cCharAt;
        int i;
        int i2;
        int length = str.length();
        String str2 = null;
        if (str.indexOf(64, 0) != -1) {
            char cCharAt2 = 0;
            int i3 = 0;
            while (i3 < length) {
                cCharAt2 = str.charAt(i3);
                if (cCharAt2 == '@') {
                    break;
                } else {
                    i3++;
                }
            }
            String strSubstring = str.substring(0, i3);
            i = i3 + 1;
            cCharAt = cCharAt2;
            str2 = strSubstring;
        } else {
            cCharAt = 0;
            i = 0;
        }
        int i4 = i;
        while (i4 < length) {
            cCharAt = str.charAt(i4);
            if (cCharAt == ':') {
                break;
            } else {
                i4++;
            }
        }
        String strSubstring2 = str.substring(i, i4);
        if (strSubstring2.length() <= 0 || cCharAt != ':') {
            i2 = -1;
        } else {
            int i5 = i4 + 1;
            int i6 = i5;
            while (i6 < length) {
                i6++;
            }
            String strSubstring3 = str.substring(i5, i6);
            if (strSubstring3.length() > 0) {
                for (int i7 = 0; i7 < strSubstring3.length(); i7++) {
                    if (!isDigit(strSubstring3.charAt(i7))) {
                        throw new MalformedURIException(new StringBuffer().append(strSubstring3).append(" is invalid. Port should only contain digits!").toString());
                    }
                }
                try {
                    i2 = Integer.parseInt(strSubstring3);
                } catch (NumberFormatException e) {
                    i2 = -1;
                }
            } else {
                i2 = -1;
            }
        }
        setHost(strSubstring2);
        setPort(i2);
        setUserinfo(str2);
    }

    private void initializePath(String str) throws MalformedURIException {
        if (str == null) {
            throw new MalformedURIException("Cannot initialize path from null string!");
        }
        int length = str.length();
        char cCharAt = 0;
        int i = 0;
        while (i < length) {
            cCharAt = str.charAt(i);
            if (cCharAt == '?' || cCharAt == '#') {
                break;
            }
            if (cCharAt == '%') {
                if (i + 2 >= length || !isHex(str.charAt(i + 1)) || !isHex(str.charAt(i + 2))) {
                    throw new MalformedURIException("Path contains invalid escape sequence!");
                }
            } else if (!isReservedCharacter(cCharAt) && !isUnreservedCharacter(cCharAt)) {
                throw new MalformedURIException(new StringBuffer().append("Path contains invalid character: ").append(cCharAt).toString());
            }
            i++;
        }
        this.m_path = str.substring(0, i);
        if (cCharAt == '?') {
            int i2 = i + 1;
            i = i2;
            while (i < length) {
                cCharAt = str.charAt(i);
                if (cCharAt == '#') {
                    break;
                }
                if (cCharAt == '%') {
                    if (i + 2 >= length || !isHex(str.charAt(i + 1)) || !isHex(str.charAt(i + 2))) {
                        throw new MalformedURIException("Query string contains invalid escape sequence!");
                    }
                } else if (!isReservedCharacter(cCharAt) && !isUnreservedCharacter(cCharAt)) {
                    throw new MalformedURIException(new StringBuffer().append("Query string contains invalid character:").append(cCharAt).toString());
                }
                i++;
            }
            this.m_queryString = str.substring(i2, i);
        }
        if (cCharAt == '#') {
            int i3 = i + 1;
            int i4 = i3;
            while (i4 < length) {
                char cCharAt2 = str.charAt(i4);
                if (cCharAt2 == '%') {
                    if (i4 + 2 >= length || !isHex(str.charAt(i4 + 1)) || !isHex(str.charAt(i4 + 2))) {
                        throw new MalformedURIException("Fragment contains invalid escape sequence!");
                    }
                } else if (!isReservedCharacter(cCharAt2) && !isUnreservedCharacter(cCharAt2)) {
                    throw new MalformedURIException(new StringBuffer().append("Fragment contains invalid character:").append(cCharAt2).toString());
                }
                i4++;
            }
            this.m_fragment = str.substring(i3, i4);
        }
    }

    public String getScheme() {
        return this.m_scheme;
    }

    public String getSchemeSpecificPart() {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.m_userinfo != null || this.m_host != null || this.m_port != -1) {
            stringBuffer.append("//");
        }
        if (this.m_userinfo != null) {
            stringBuffer.append(this.m_userinfo);
            stringBuffer.append('@');
        }
        if (this.m_host != null) {
            stringBuffer.append(this.m_host);
        }
        if (this.m_port != -1) {
            stringBuffer.append(':');
            stringBuffer.append(this.m_port);
        }
        if (this.m_path != null) {
            stringBuffer.append(this.m_path);
        }
        if (this.m_queryString != null) {
            stringBuffer.append('?');
            stringBuffer.append(this.m_queryString);
        }
        if (this.m_fragment != null) {
            stringBuffer.append('#');
            stringBuffer.append(this.m_fragment);
        }
        return stringBuffer.toString();
    }

    public String getUserinfo() {
        return this.m_userinfo;
    }

    public String getHost() {
        return this.m_host;
    }

    public int getPort() {
        return this.m_port;
    }

    public String getPath(boolean z, boolean z2) {
        StringBuffer stringBuffer = new StringBuffer(this.m_path);
        if (z && this.m_queryString != null) {
            stringBuffer.append('?');
            stringBuffer.append(this.m_queryString);
        }
        if (z2 && this.m_fragment != null) {
            stringBuffer.append('#');
            stringBuffer.append(this.m_fragment);
        }
        return stringBuffer.toString();
    }

    public String getPath() {
        return this.m_path;
    }

    public String getQueryString() {
        return this.m_queryString;
    }

    public String getFragment() {
        return this.m_fragment;
    }

    public void setScheme(String str) throws MalformedURIException {
        if (str == null) {
            throw new MalformedURIException("Cannot set scheme from null string!");
        }
        if (!isConformantSchemeName(str)) {
            throw new MalformedURIException("The scheme is not conformant.");
        }
        this.m_scheme = str.toLowerCase();
    }

    public void setUserinfo(String str) throws MalformedURIException {
        if (str == null) {
            this.m_userinfo = null;
        } else {
            if (this.m_host == null) {
                throw new MalformedURIException("Userinfo cannot be set when host is null!");
            }
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char cCharAt = str.charAt(i);
                if (cCharAt == '%') {
                    if (i + 2 >= length || !isHex(str.charAt(i + 1)) || !isHex(str.charAt(i + 2))) {
                        throw new MalformedURIException("Userinfo contains invalid escape sequence!");
                    }
                } else if (!isUnreservedCharacter(cCharAt) && USERINFO_CHARACTERS.indexOf(cCharAt) == -1) {
                    throw new MalformedURIException(new StringBuffer().append("Userinfo contains invalid character:").append(cCharAt).toString());
                }
            }
        }
        this.m_userinfo = str;
    }

    public void setHost(String str) throws MalformedURIException {
        if (str == null || str.trim().length() == 0) {
            this.m_host = str;
            this.m_userinfo = null;
            this.m_port = -1;
        } else if (!isWellFormedAddress(str)) {
            throw new MalformedURIException("Host is not a well formed address!");
        }
        this.m_host = str;
    }

    public void setPort(int i) throws MalformedURIException {
        if (i >= 0 && i <= 65535) {
            if (this.m_host == null) {
                throw new MalformedURIException("Port cannot be set when host is null!");
            }
        } else if (i != -1) {
            throw new MalformedURIException("Invalid port number!");
        }
        this.m_port = i;
    }

    public void setPath(String str) throws MalformedURIException {
        if (str == null) {
            this.m_path = null;
            this.m_queryString = null;
            this.m_fragment = null;
            return;
        }
        initializePath(str);
    }

    public void appendPath(String str) throws MalformedURIException {
        if (str != null && str.trim().length() != 0) {
            if (!isURIString(str)) {
                throw new MalformedURIException("Path contains invalid character!");
            }
            if (this.m_path == null || this.m_path.trim().length() == 0) {
                if (str.startsWith("/")) {
                    this.m_path = str;
                    return;
                } else {
                    this.m_path = new StringBuffer().append("/").append(str).toString();
                    return;
                }
            }
            if (this.m_path.endsWith("/")) {
                if (str.startsWith("/")) {
                    this.m_path = this.m_path.concat(str.substring(1));
                    return;
                } else {
                    this.m_path = this.m_path.concat(str);
                    return;
                }
            }
            if (str.startsWith("/")) {
                this.m_path = this.m_path.concat(str);
            } else {
                this.m_path = this.m_path.concat(new StringBuffer().append("/").append(str).toString());
            }
        }
    }

    public void setQueryString(String str) throws MalformedURIException {
        if (str == null) {
            this.m_queryString = null;
            return;
        }
        if (!isGenericURI()) {
            throw new MalformedURIException("Query string can only be set for a generic URI!");
        }
        if (getPath() == null) {
            throw new MalformedURIException("Query string cannot be set when path is null!");
        }
        if (!isURIString(str)) {
            throw new MalformedURIException("Query string contains invalid character!");
        }
        this.m_queryString = str;
    }

    public void setFragment(String str) throws MalformedURIException {
        if (str == null) {
            this.m_fragment = null;
            return;
        }
        if (!isGenericURI()) {
            throw new MalformedURIException("Fragment can only be set for a generic URI!");
        }
        if (getPath() == null) {
            throw new MalformedURIException("Fragment cannot be set when path is null!");
        }
        if (!isURIString(str)) {
            throw new MalformedURIException("Fragment contains invalid character!");
        }
        this.m_fragment = str;
    }

    public boolean equals(Object obj) {
        if (obj instanceof URI) {
            URI uri = (URI) obj;
            if (((this.m_scheme == null && uri.m_scheme == null) || (this.m_scheme != null && uri.m_scheme != null && this.m_scheme.equals(uri.m_scheme))) && (((this.m_userinfo == null && uri.m_userinfo == null) || (this.m_userinfo != null && uri.m_userinfo != null && this.m_userinfo.equals(uri.m_userinfo))) && (((this.m_host == null && uri.m_host == null) || (this.m_host != null && uri.m_host != null && this.m_host.equals(uri.m_host))) && this.m_port == uri.m_port && (((this.m_path == null && uri.m_path == null) || (this.m_path != null && uri.m_path != null && this.m_path.equals(uri.m_path))) && (((this.m_queryString == null && uri.m_queryString == null) || (this.m_queryString != null && uri.m_queryString != null && this.m_queryString.equals(uri.m_queryString))) && ((this.m_fragment == null && uri.m_fragment == null) || (this.m_fragment != null && uri.m_fragment != null && this.m_fragment.equals(uri.m_fragment)))))))) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.m_scheme != null) {
            stringBuffer.append(this.m_scheme);
            stringBuffer.append(':');
        }
        stringBuffer.append(getSchemeSpecificPart());
        return stringBuffer.toString();
    }

    public boolean isGenericURI() {
        return this.m_host != null;
    }

    public static boolean isConformantSchemeName(String str) {
        if (str == null || str.trim().length() == 0) {
            return false;
        }
        if (!isAlpha(str.charAt(0))) {
            return false;
        }
        for (int i = 1; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (!isAlphanum(cCharAt) && SCHEME_CHARACTERS.indexOf(cCharAt) == -1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isWellFormedAddress(String str) {
        if (str == null) {
            return false;
        }
        String strTrim = str.trim();
        int length = strTrim.length();
        if (length == 0 || length > 255) {
            return false;
        }
        if (strTrim.startsWith(".") || strTrim.startsWith("-")) {
            return false;
        }
        int iLastIndexOf = strTrim.lastIndexOf(46);
        if (strTrim.endsWith(".")) {
            iLastIndexOf = strTrim.substring(0, iLastIndexOf).lastIndexOf(46);
        }
        if (iLastIndexOf + 1 < length && isDigit(str.charAt(iLastIndexOf + 1))) {
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                char cCharAt = strTrim.charAt(i2);
                if (cCharAt == '.') {
                    if (!isDigit(strTrim.charAt(i2 - 1)) || (i2 + 1 < length && !isDigit(strTrim.charAt(i2 + 1)))) {
                        return false;
                    }
                    i++;
                } else if (!isDigit(cCharAt)) {
                    return false;
                }
            }
            if (i != 3) {
                return false;
            }
        } else {
            for (int i3 = 0; i3 < length; i3++) {
                char cCharAt2 = strTrim.charAt(i3);
                if (cCharAt2 == '.') {
                    if (!isAlphanum(strTrim.charAt(i3 - 1))) {
                        return false;
                    }
                    if (i3 + 1 < length && !isAlphanum(strTrim.charAt(i3 + 1))) {
                        return false;
                    }
                } else if (!isAlphanum(cCharAt2) && cCharAt2 != '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean isHex(char c) {
        return isDigit(c) || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
    }

    private static boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private static boolean isAlphanum(char c) {
        return isAlpha(c) || isDigit(c);
    }

    private static boolean isReservedCharacter(char c) {
        return RESERVED_CHARACTERS.indexOf(c) != -1;
    }

    private static boolean isUnreservedCharacter(char c) {
        return isAlphanum(c) || MARK_CHARACTERS.indexOf(c) != -1;
    }

    private static boolean isURIString(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        int i = 0;
        while (i < length) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '%') {
                if (i + 2 >= length || !isHex(str.charAt(i + 1)) || !isHex(str.charAt(i + 2))) {
                    return false;
                }
                i += 2;
            } else if (!isReservedCharacter(cCharAt) && !isUnreservedCharacter(cCharAt)) {
                return false;
            }
            i++;
        }
        return true;
    }
}
