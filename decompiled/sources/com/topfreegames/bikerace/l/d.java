package com.topfreegames.bikerace.l;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: compiled from: GzipDataPointsSerde.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class d {
    private static byte[] a(byte[] bArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(bArr);
        gZIPOutputStream.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return byteArray;
    }

    private static String b(String str) {
        try {
            return new String(a(str.getBytes("ISO-8859-1")), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            throw new IllegalArgumentException("Unable to gzip string.", e2);
        }
    }

    public static String a(List<com.topfreegames.bikerace.multiplayer.a> list) {
        ArrayList arrayList = new ArrayList(list.size() * 3);
        for (com.topfreegames.bikerace.multiplayer.a aVar : list) {
            arrayList.add(Long.valueOf(aVar.f1299a));
            arrayList.add(Long.valueOf(aVar.b));
            arrayList.add(Long.valueOf(aVar.c));
        }
        return b(new JSONArray((Collection) arrayList).toString());
    }

    public static List<com.topfreegames.bikerace.multiplayer.a> a(String str) {
        if ("".equals(str) || "null".equals(str)) {
            return null;
        }
        String strC = c(str);
        try {
            JSONArray jSONArray = new JSONArray(strC);
            ArrayList arrayList = new ArrayList(jSONArray.length() / 3);
            for (int i = 0; i < jSONArray.length(); i += 3) {
                arrayList.add(new com.topfreegames.bikerace.multiplayer.a(jSONArray.getLong(i), jSONArray.getLong(i + 1), jSONArray.getLong(i + 2)));
            }
            return arrayList;
        } catch (JSONException e) {
            throw new IllegalArgumentException(String.format("Invalid JSON array string: '%s'", strC), e);
        }
    }

    private static String c(String str) {
        try {
            return new String(b(str.getBytes("ISO-8859-1")), XMLStreamWriterImpl.UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            throw new IllegalArgumentException("Invalid gzip format.", e2);
        }
    }

    private static byte[] b(byte[] bArr) throws IOException {
        int i = 0;
        if (bArr.length > 4) {
            GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(bArr));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (i != -1) {
                i = gZIPInputStream.read();
                if (i != -1) {
                    byteArrayOutputStream.write(i);
                }
            }
            gZIPInputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        }
        return new byte[0];
    }
}
