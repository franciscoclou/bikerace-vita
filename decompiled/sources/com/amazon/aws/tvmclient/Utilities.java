package com.amazon.aws.tvmclient;

import com.amazonaws.a.b;
import com.amazonaws.a.f;
import com.amazonaws.a.p;
import com.amazonaws.f.e;
import com.amazonaws.j;
import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import java.util.Date;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class Utilities {
    public static String getTimestamp() {
        return new e().a(new Date());
    }

    public static String getTimestamp(Date date) {
        return new e().a(date);
    }

    public static String extractElement(String str, String str2) {
        if (str.indexOf(str2) != -1) {
            int iIndexOf = str.indexOf("\"", str.indexOf(str2));
            return str.substring(iIndexOf + 1, str.indexOf("\"", iIndexOf + 1));
        }
        return null;
    }

    public static String getSignature(String str, String str2) {
        return new Signer().getSignature(str, str2);
    }

    class Signer extends f {
        Signer() {
        }

        public String getSignature(String str, String str2) {
            try {
                return super.signAndBase64Encode(str.getBytes(XMLStreamWriterImpl.UTF_8), str2, p.HmacSHA256);
            } catch (Exception e) {
                return null;
            }
        }

        @Override // com.amazonaws.a.o
        public void sign(j<?> jVar, b bVar) {
        }

        @Override // com.amazonaws.a.f
        protected void addSessionCredentials(j<?> jVar, com.amazonaws.a.e eVar) {
        }
    }
}
