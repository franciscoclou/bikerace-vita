package com.amazonaws.e;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class i implements t<Date, c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static i f105a;

    public static i a() {
        if (f105a == null) {
            f105a = new i();
        }
        return f105a;
    }

    @Override // com.amazonaws.e.t
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Date unmarshall(c cVar) {
        String strB = cVar.b();
        if (strB == null) {
            return null;
        }
        try {
            return new Date(DecimalFormat.getInstance(new Locale("en")).parse(strB).longValue() * 1000);
        } catch (ParseException e) {
            throw new com.amazonaws.a("Unable to parse date '" + strB + "':  " + e.getMessage(), e);
        }
    }
}
