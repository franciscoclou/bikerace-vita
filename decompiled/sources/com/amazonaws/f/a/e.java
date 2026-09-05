package com.amazonaws.f.a;

import com.amazonaws.javax.xml.stream.XMLStreamConstants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f120a;
    private boolean b;
    private int c;
    private int d;
    private char e;
    private Reader f;
    private boolean g;

    public e(Reader reader) {
        this.f = reader.markSupported() ? reader : new BufferedReader(reader);
        this.b = false;
        this.g = false;
        this.e = (char) 0;
        this.c = 0;
        this.f120a = 1;
        this.d = 1;
    }

    public e(String str) {
        this(new StringReader(str));
    }

    public b a(String str) {
        return new b(str + toString());
    }

    public String a(char c) throws b {
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            char c2 = c();
            switch (c2) {
                case 0:
                case XMLStreamConstants.ATTRIBUTE /* 10 */:
                case XMLStreamConstants.NAMESPACE /* 13 */:
                    throw a("Unterminated string");
                case '\\':
                    char c3 = c();
                    switch (c3) {
                        case '\"':
                        case '\'':
                        case '/':
                        case '\\':
                            stringBuffer.append(c3);
                            break;
                        case 'b':
                            stringBuffer.append('\b');
                            break;
                        case 'f':
                            stringBuffer.append('\f');
                            break;
                        case 'n':
                            stringBuffer.append('\n');
                            break;
                        case 'r':
                            stringBuffer.append('\r');
                            break;
                        case 't':
                            stringBuffer.append('\t');
                            break;
                        case 'u':
                            stringBuffer.append((char) Integer.parseInt(a(4), 16));
                            break;
                        default:
                            throw a("Illegal escape.");
                    }
                    break;
                default:
                    if (c2 == c) {
                        return stringBuffer.toString();
                    }
                    stringBuffer.append(c2);
                    break;
            }
        }
    }

    public String a(int i) throws b {
        if (i == 0) {
            return "";
        }
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = c();
            if (b()) {
                throw a("Substring bounds error");
            }
        }
        return new String(cArr);
    }

    public void a() {
        if (this.g || this.c <= 0) {
            throw new b("Stepping back two steps is not supported");
        }
        this.c--;
        this.f120a--;
        this.g = true;
        this.b = false;
    }

    public boolean b() {
        return this.b && !this.g;
    }

    public char c() throws b {
        int i;
        if (this.g) {
            this.g = false;
            i = this.e;
        } else {
            try {
                i = this.f.read();
                if (i <= 0) {
                    this.b = true;
                    i = 0;
                }
            } catch (IOException e) {
                throw new b(e);
            }
        }
        this.c++;
        if (this.e == '\r') {
            this.d++;
            this.f120a = i != 10 ? 1 : 0;
        } else if (i == 10) {
            this.d++;
            this.f120a = 0;
        } else {
            this.f120a++;
        }
        this.e = (char) i;
        return this.e;
    }

    public char d() {
        char c;
        do {
            c = c();
            if (c == 0) {
                break;
            }
        } while (c <= ' ');
        return c;
    }

    public Object e() throws b {
        char cD = d();
        switch (cD) {
            case '\"':
            case '\'':
                return a(cD);
            case '(':
            case '[':
                a();
                return new a(this);
            case '{':
                a();
                return new c(this);
            default:
                StringBuffer stringBuffer = new StringBuffer();
                while (cD >= ' ' && ",:]}/\\\"[{;=#".indexOf(cD) < 0) {
                    stringBuffer.append(cD);
                    cD = c();
                }
                a();
                String strTrim = stringBuffer.toString().trim();
                if (strTrim.equals("")) {
                    throw a("Missing value");
                }
                return c.g(strTrim);
        }
    }

    public String toString() {
        return " at " + this.c + " [character " + this.f120a + " line " + this.d + "]";
    }
}
