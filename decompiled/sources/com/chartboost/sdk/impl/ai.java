package com.chartboost.sdk.impl;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ai {
    static final Logger f = Logger.getLogger("org.bson.BSON");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f418a = false;
    private static boolean b = false;
    static bf<List<ap>> g = new bf<>();
    static bf<List<ap>> h = new bf<>();
    protected static Charset i = Charset.forName(XMLStreamWriterImpl.UTF_8);
    static ThreadLocal<ak> j = new ThreadLocal<ak>() { // from class: com.chartboost.sdk.impl.ai.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ak initialValue() {
            return new an();
        }
    };
    static ThreadLocal<aj> k = new ThreadLocal<aj>() { // from class: com.chartboost.sdk.impl.ai.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public aj initialValue() {
            return new am();
        }
    };

    public static String a(int i2) {
        StringBuilder sb = new StringBuilder();
        int i3 = i2;
        for (a aVar : a.valuesCustom()) {
            if ((aVar.j & i3) > 0) {
                sb.append(aVar.k);
                i3 -= aVar.j;
            }
        }
        if (i3 > 0) {
            throw new IllegalArgumentException("some flags could not be recognized.");
        }
        return sb.toString();
    }

    enum a {
        CANON_EQ(XMLChar.MASK_NCNAME, 'c', "Pattern.CANON_EQ"),
        UNIX_LINES(1, 'd', "Pattern.UNIX_LINES"),
        GLOBAL(256, 'g', null),
        CASE_INSENSITIVE(2, 'i', null),
        MULTILINE(8, 'm', null),
        DOTALL(32, 's', "Pattern.DOTALL"),
        LITERAL(16, 't', "Pattern.LITERAL"),
        UNICODE_CASE(64, 'u', "Pattern.UNICODE_CASE"),
        COMMENTS(4, 'x', null);

        private static final Map<Character, a> m = new HashMap();
        public final int j;
        public final char k;
        public final String l;

        /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
        public static a[] valuesCustom() {
            a[] aVarArrValuesCustom = values();
            int length = aVarArrValuesCustom.length;
            a[] aVarArr = new a[length];
            System.arraycopy(aVarArrValuesCustom, 0, aVarArr, 0, length);
            return aVarArr;
        }

        static {
            for (a aVar : valuesCustom()) {
                m.put(Character.valueOf(aVar.k), aVar);
            }
        }

        a(int i, char c, String str) {
            this.j = i;
            this.k = c;
            this.l = str;
        }
    }

    public static Object a(Object obj) {
        List<ap> listA;
        if (a() && g.a() != 0 && obj != null && (listA = g.a(obj.getClass())) != null) {
            Iterator<ap> it = listA.iterator();
            while (it.hasNext()) {
                obj = it.next().a(obj);
            }
        }
        return obj;
    }

    private static boolean a() {
        return f418a || b;
    }
}
