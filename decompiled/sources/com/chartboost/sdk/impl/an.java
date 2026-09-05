package com.chartboost.sdk.impl;

import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class an implements ak {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected ar f421a;

    public byte[] a(al alVar) {
        aq aqVar = new aq();
        a(aqVar);
        b(alVar);
        a();
        return aqVar.c();
    }

    public void a(ar arVar) {
        if (this.f421a != null) {
            throw new IllegalStateException("in the middle of something");
        }
        this.f421a = arVar;
    }

    public void a() {
        this.f421a = null;
    }

    protected boolean a(String str, al alVar) {
        return false;
    }

    protected boolean a(String str, Object obj) {
        return false;
    }

    public int b(al alVar) {
        return b((String) null, alVar);
    }

    protected int b(String str, al alVar) {
        if (alVar == null) {
            throw new NullPointerException("can't save a null object");
        }
        int iA = this.f421a.a();
        byte b = alVar instanceof List ? (byte) 4 : (byte) 3;
        if (a(str, alVar)) {
            return this.f421a.a() - iA;
        }
        if (str != null) {
            a(b, str);
        }
        int iA2 = this.f421a.a();
        this.f421a.c(0);
        List list = null;
        boolean z = b == 3 && str == null;
        if (b == 3) {
            if (z && alVar.b("_id")) {
                b("_id", alVar.a("_id"));
            }
            Object objA = alVar.a("_transientFields");
            if (objA instanceof List) {
                list = (List) objA;
            }
        }
        if (alVar instanceof Map) {
            for (Map.Entry entry : ((Map) alVar).entrySet()) {
                if (!z || !((String) entry.getKey()).equals("_id")) {
                    if (list == null || !list.contains(entry.getKey())) {
                        b((String) entry.getKey(), entry.getValue());
                    }
                }
            }
        } else {
            for (String str2 : alVar.keySet()) {
                if (!z || !str2.equals("_id")) {
                    if (list == null || !list.contains(str2)) {
                        b(str2, alVar.a(str2));
                    }
                }
            }
        }
        this.f421a.write(0);
        this.f421a.a(iA2, this.f421a.a() - iA2);
        return this.f421a.a() - iA;
    }

    protected void b(String str, Object obj) {
        if (!str.equals("_transientFields")) {
            if (str.equals("$where") && (obj instanceof String)) {
                a((byte) 13, str);
                b(obj.toString());
                return;
            }
            Object objA = ai.a(obj);
            if (objA == null) {
                a(str);
                return;
            }
            if (objA instanceof Date) {
                a(str, (Date) objA);
                return;
            }
            if (objA instanceof Number) {
                a(str, (Number) objA);
                return;
            }
            if (objA instanceof Character) {
                a(str, objA.toString());
                return;
            }
            if (objA instanceof String) {
                a(str, objA.toString());
                return;
            }
            if (objA instanceof ba) {
                a(str, (ba) objA);
                return;
            }
            if (objA instanceof al) {
                b(str, (al) objA);
                return;
            }
            if (objA instanceof Boolean) {
                a(str, (Boolean) objA);
                return;
            }
            if (objA instanceof Pattern) {
                a(str, (Pattern) objA);
                return;
            }
            if (objA instanceof Map) {
                a(str, (Map) objA);
                return;
            }
            if (objA instanceof Iterable) {
                a(str, (Iterable) objA);
                return;
            }
            if (objA instanceof byte[]) {
                a(str, (byte[]) objA);
                return;
            }
            if (objA instanceof av) {
                a(str, (av) objA);
                return;
            }
            if (objA instanceof UUID) {
                a(str, (UUID) objA);
                return;
            }
            if (objA.getClass().isArray()) {
                c(str, objA);
                return;
            }
            if (objA instanceof bb) {
                a(str, (bb) objA);
                return;
            }
            if (objA instanceof au) {
                a(str, (au) objA);
                return;
            }
            if (objA instanceof ax) {
                a(str, (ax) objA);
                return;
            }
            if (objA instanceof aw) {
                a(str, (aw) objA);
                return;
            }
            if (objA instanceof ab) {
                ao aoVar = new ao();
                aoVar.put("$ref", ((ab) objA).b());
                aoVar.put("$id", ((ab) objA).a());
                b(str, (al) aoVar);
                return;
            }
            if (objA instanceof az) {
                d(str);
            } else if (objA instanceof ay) {
                e(str);
            } else if (!a(str, objA)) {
                throw new IllegalArgumentException("can't serialize " + objA.getClass());
            }
        }
    }

    private void c(String str, Object obj) {
        a((byte) 4, str);
        int iA = this.f421a.a();
        this.f421a.c(0);
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            b(String.valueOf(i), Array.get(obj, i));
        }
        this.f421a.write(0);
        this.f421a.a(iA, this.f421a.a() - iA);
    }

    private void a(String str, Iterable iterable) {
        a((byte) 4, str);
        int iA = this.f421a.a();
        this.f421a.c(0);
        Iterator it = iterable.iterator();
        int i = 0;
        while (it.hasNext()) {
            b(String.valueOf(i), it.next());
            i++;
        }
        this.f421a.write(0);
        this.f421a.a(iA, this.f421a.a() - iA);
    }

    private void a(String str, Map map) {
        a((byte) 3, str);
        int iA = this.f421a.a();
        this.f421a.c(0);
        for (Map.Entry entry : map.entrySet()) {
            b(entry.getKey().toString(), entry.getValue());
        }
        this.f421a.write(0);
        this.f421a.a(iA, this.f421a.a() - iA);
    }

    protected void a(String str) {
        a((byte) 10, str);
    }

    protected void a(String str, au auVar) {
        a((byte) 17, str);
        this.f421a.c(auVar.b());
        this.f421a.c(auVar.a());
    }

    protected void a(String str, ax axVar) {
        a((byte) 15, str);
        int iA = this.f421a.a();
        this.f421a.c(0);
        b(axVar.a());
        b(axVar.b());
        this.f421a.a(iA, this.f421a.a() - iA);
    }

    protected void a(String str, aw awVar) {
        a((byte) 13, str);
        this.f421a.a();
        b(awVar.a());
    }

    protected void a(String str, Boolean bool) {
        a((byte) 8, str);
        this.f421a.write(bool.booleanValue() ? 1 : 0);
    }

    protected void a(String str, Date date) {
        a((byte) 9, str);
        this.f421a.a(date.getTime());
    }

    protected void a(String str, Number number) {
        if ((number instanceof Integer) || (number instanceof Short) || (number instanceof Byte) || (number instanceof AtomicInteger)) {
            a((byte) 16, str);
            this.f421a.c(number.intValue());
        } else if ((number instanceof Long) || (number instanceof AtomicLong)) {
            a((byte) 18, str);
            this.f421a.a(number.longValue());
        } else {
            if ((number instanceof Float) || (number instanceof Double)) {
                a((byte) 1, str);
                this.f421a.a(number.doubleValue());
                return;
            }
            throw new IllegalArgumentException("can't serialize " + number.getClass());
        }
    }

    protected void a(String str, byte[] bArr) {
        a(str, 0, bArr);
    }

    protected void a(String str, av avVar) {
        a(str, avVar.a(), avVar.b());
    }

    private void a(String str, int i, byte[] bArr) {
        a((byte) 5, str);
        int length = bArr.length;
        if (i == 2) {
            length += 4;
        }
        this.f421a.c(length);
        this.f421a.write(i);
        if (i == 2) {
            this.f421a.c(length - 4);
        }
        int iA = this.f421a.a();
        this.f421a.write(bArr);
        ag.a(this.f421a.a() - iA, bArr.length);
    }

    protected void a(String str, UUID uuid) {
        a((byte) 5, str);
        this.f421a.c(16);
        this.f421a.write(3);
        this.f421a.a(uuid.getMostSignificantBits());
        this.f421a.a(uuid.getLeastSignificantBits());
    }

    protected void a(String str, bb bbVar) {
        a(str, bbVar.a(), (byte) 14);
    }

    protected void a(String str, String str2) {
        a(str, str2, (byte) 2);
    }

    private void a(String str, String str2, byte b) {
        a(b, str);
        b(str2);
    }

    protected void a(String str, ba baVar) {
        a((byte) 7, str);
        this.f421a.d(baVar.c());
        this.f421a.d(baVar.d());
        this.f421a.d(baVar.e());
    }

    private void a(String str, Pattern pattern) {
        a((byte) 11, str);
        c(pattern.pattern());
        c(ai.a(pattern.flags()));
    }

    private void d(String str) {
        a((byte) -1, str);
    }

    private void e(String str) {
        a((byte) 127, str);
    }

    protected void a(byte b, String str) {
        this.f421a.write(b);
        c(str);
    }

    protected void b(String str) {
        int iA = this.f421a.a();
        this.f421a.c(0);
        this.f421a.a(iA, c(str));
    }

    protected int c(String str) {
        int length = str.length();
        int iCharCount = 0;
        int i = 0;
        while (iCharCount < length) {
            int iCodePointAt = Character.codePointAt(str, iCharCount);
            if (iCodePointAt < 128) {
                this.f421a.write((byte) iCodePointAt);
                i++;
            } else if (iCodePointAt < 2048) {
                this.f421a.write((byte) ((iCodePointAt >> 6) + 192));
                this.f421a.write((byte) ((iCodePointAt & 63) + XMLChar.MASK_NCNAME));
                i += 2;
            } else if (iCodePointAt < 65536) {
                this.f421a.write((byte) ((iCodePointAt >> 12) + 224));
                this.f421a.write((byte) (((iCodePointAt >> 6) & 63) + XMLChar.MASK_NCNAME));
                this.f421a.write((byte) ((iCodePointAt & 63) + XMLChar.MASK_NCNAME));
                i += 3;
            } else {
                this.f421a.write((byte) ((iCodePointAt >> 18) + 240));
                this.f421a.write((byte) (((iCodePointAt >> 12) & 63) + XMLChar.MASK_NCNAME));
                this.f421a.write((byte) (((iCodePointAt >> 6) & 63) + XMLChar.MASK_NCNAME));
                this.f421a.write((byte) ((iCodePointAt & 63) + XMLChar.MASK_NCNAME));
                i += 4;
            }
            iCharCount += Character.charCount(iCodePointAt);
        }
        this.f421a.write(0);
        return i + 1;
    }
}
