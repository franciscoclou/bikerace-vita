package com.amazonaws.b;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b {
    public List<c> a(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            InputStream resourceAsStream = getClass().getResourceAsStream(str);
            if (resourceAsStream == null) {
                return arrayList;
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    return arrayList;
                }
                String strTrim = line.trim();
                if (!strTrim.equals("")) {
                    Object objNewInstance = getClass().getClassLoader().loadClass(strTrim).newInstance();
                    if (!(objNewInstance instanceof c)) {
                        throw new com.amazonaws.a("Unable to instantiate request handler chain for client.  Listed request handler ('" + strTrim + "') does not implement the RequestHandler interface.");
                    }
                    arrayList.add((c) objNewInstance);
                }
            }
        } catch (Exception e) {
            throw new com.amazonaws.a("Unable to instantiate request handler chain for client: " + e.getMessage(), e);
        }
    }
}
