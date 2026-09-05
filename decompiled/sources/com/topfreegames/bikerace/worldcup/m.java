package com.topfreegames.bikerace.worldcup;

import android.content.Context;
import android.content.SharedPreferences;
import com.topfreegames.bikerace.ba;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/* JADX INFO: compiled from: WorldCupLikelihoodUpdater.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final List<a> f1460a;
    private static final byte[] b;
    private static final String c;

    static {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_AUSTRALIA, b.SUIT));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_AUSTRALIA, b.FRONT));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_AUSTRALIA, b.HELMET));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_AUSTRALIA, b.BACK));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_BRAZIL, b.SUIT));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_BRAZIL, b.FRONT));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_BRAZIL, b.HELMET));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_BRAZIL, b.BACK));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_USA, b.SUIT));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_USA, b.FRONT));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_USA, b.HELMET));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_USA, b.BACK));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_FRANCE, b.SUIT));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_FRANCE, b.FRONT));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_FRANCE, b.HELMET));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_FRANCE, b.BACK));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_GERMANY, b.SUIT));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_GERMANY, b.FRONT));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_GERMANY, b.HELMET));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_GERMANY, b.BACK));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_JAPAN, b.SUIT));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_JAPAN, b.FRONT));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_JAPAN, b.HELMET));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_JAPAN, b.BACK));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_NETHERLANDS, b.SUIT));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_NETHERLANDS, b.FRONT));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_NETHERLANDS, b.HELMET));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_NETHERLANDS, b.BACK));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_SPAIN, b.SUIT));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_SPAIN, b.FRONT));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_SPAIN, b.HELMET));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_SPAIN, b.BACK));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_ENGLAND, b.SUIT));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_ENGLAND, b.FRONT));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_ENGLAND, b.HELMET));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_ENGLAND, b.BACK));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_ARGENTINA, b.SUIT));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_ARGENTINA, b.FRONT));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_ARGENTINA, b.HELMET));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_ARGENTINA, b.BACK));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_MEXICO, b.SUIT));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_MEXICO, b.FRONT));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_MEXICO, b.HELMET));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_MEXICO, b.BACK));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_BELGIUM, b.SUIT));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_BELGIUM, b.FRONT));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_BELGIUM, b.HELMET));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_BELGIUM, b.BACK));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_ITALY, b.SUIT));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_ITALY, b.FRONT));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_ITALY, b.HELMET));
        arrayList.add(new a(com.topfreegames.bikerace.c.WORLDCUP_ITALY, b.BACK));
        f1460a = Collections.unmodifiableList(arrayList);
        b = new byte[]{-107, 72, -45, 43, 116, 103};
        c = ba.a();
    }

    public static Map<a, Integer> a(Context context, n nVar) {
        ArrayList arrayList;
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.topfreegames.bikerace.worldcup.likelihoodupdater", 0);
        c(context, nVar);
        List<Integer> listB = b(sharedPreferences.getString("likelihoodText", ""));
        List<Integer> listB2 = b(sharedPreferences.getString("likelihoodTextNew", ""));
        if (listB2 == null || listB == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList();
            arrayList.addAll(listB);
            arrayList.addAll(listB2);
        }
        Map<a, Integer> mapD = d(arrayList);
        if (mapD != null) {
            return mapD;
        }
        return null;
    }

    public static List<Integer> b(Context context, n nVar) {
        List<Integer> listB = b(context.getSharedPreferences("com.topfreegames.bikerace.worldcup.likelihoodupdater", 0).getString("likelihoodText", ""));
        if (listB == null || listB.size() != 5) {
            return null;
        }
        return listB;
    }

    private static void c(Context context, final n nVar) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences("com.topfreegames.bikerace.worldcup.likelihoodupdater", 0);
        if (com.topfreegames.c.a.a().getTime() - sharedPreferences.getLong("lastUpdate", -1L) > 7200000) {
            new Thread(new Runnable() { // from class: com.topfreegames.bikerace.worldcup.m.1
                @Override // java.lang.Runnable
                public void run() {
                    ArrayList arrayList;
                    String strB = m.b();
                    if (strB != null) {
                        String[] strArrSplit = strB.split("\\r?\\n");
                        if (strArrSplit.length < 3) {
                            return;
                        }
                        List<Integer> listC = strArrSplit[0] != null ? m.c(m.b(strArrSplit[0])) : null;
                        List listB = m.b(strArrSplit[1]);
                        List listB2 = m.b(strArrSplit[2]);
                        if (listB2 == null || listB == null) {
                            arrayList = null;
                        } else {
                            arrayList = new ArrayList();
                            arrayList.addAll(listB);
                            arrayList.addAll(listB2);
                        }
                        Map<a, Integer> mapD = arrayList != null ? m.d(arrayList) : null;
                        if (mapD != null && listC != null) {
                            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                            editorEdit.putLong("lastUpdate", com.topfreegames.c.a.a().getTime());
                            editorEdit.putString("starLevelsText", strArrSplit[0]);
                            editorEdit.putString("likelihoodText", strArrSplit[1]);
                            editorEdit.putString("likelihoodTextNew", strArrSplit[2]);
                            editorEdit.commit();
                            if (nVar != null) {
                                nVar.a(listC, mapD);
                            }
                        }
                    }
                }
            }).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b() {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(c).openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            String strA = a(inputStream);
            inputStream.close();
            return strA;
        } catch (IOException e) {
            return null;
        } catch (Exception e2) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<Integer> b(String str) {
        String strA = com.topfreegames.bikerace.m.g.a(str, "ASCII", b);
        if (strA == null || strA.length() <= 0) {
            return null;
        }
        String[] strArrSplit = strA.split(",");
        try {
            ArrayList arrayList = new ArrayList();
            for (String str2 : strArrSplit) {
                arrayList.add(Integer.valueOf(Integer.parseInt(str2)));
            }
            return arrayList;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<Integer> c(List<Integer> list) {
        if (list == null || list.size() != 5) {
            return null;
        }
        return list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<a, Integer> d(List<Integer> list) {
        if (list == null || list.size() < f1460a.size()) {
            return null;
        }
        HashMap map = new HashMap();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= f1460a.size()) {
                return map;
            }
            map.put(f1460a.get(i2), list.get(i2));
            i = i2 + 1;
        }
    }

    static String a(InputStream inputStream) {
        Scanner scannerUseDelimiter = new Scanner(inputStream).useDelimiter("\\A");
        return scannerUseDelimiter.hasNext() ? scannerUseDelimiter.next() : "";
    }
}
