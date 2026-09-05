package com.amazonaws.c;

import com.amazonaws.e.t;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class l implements j<com.amazonaws.b> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List<t<com.amazonaws.b, com.amazonaws.f.a.c>> f92a;

    public l(List<t<com.amazonaws.b, com.amazonaws.f.a.c>> list) {
        this.f92a = list;
    }

    private String a(InputStream inputStream) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    return sb.toString();
                }
                sb.append(line);
            }
        } catch (Exception e) {
            try {
                inputStream.close();
            } catch (Exception e2) {
            }
            throw new com.amazonaws.a("Unable to read error response: " + e.getMessage(), e);
        }
    }

    @Override // com.amazonaws.c.j
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public com.amazonaws.b b(i iVar) {
        com.amazonaws.b bVarA = a(iVar, new com.amazonaws.f.a.c(a(iVar.c())));
        if (bVarA == null) {
            return null;
        }
        bVarA.setServiceName(iVar.a().g());
        bVarA.setStatusCode(iVar.d());
        if (iVar.d() < 500) {
            bVarA.setErrorType(com.amazonaws.c.Client);
        } else {
            bVarA.setErrorType(com.amazonaws.c.Service);
        }
        for (Map.Entry<String, String> entry : iVar.b().entrySet()) {
            if (entry.getKey().equalsIgnoreCase("X-Amzn-RequestId")) {
                bVarA.setRequestId(entry.getValue());
            }
        }
        return bVarA;
    }

    protected com.amazonaws.b a(i iVar, com.amazonaws.f.a.c cVar) {
        Iterator<t<com.amazonaws.b, com.amazonaws.f.a.c>> it = this.f92a.iterator();
        while (it.hasNext()) {
            com.amazonaws.b bVarUnmarshall = it.next().unmarshall(cVar);
            if (bVarUnmarshall != null) {
                bVarUnmarshall.setStatusCode(iVar.d());
                return bVarUnmarshall;
            }
        }
        return null;
    }

    @Override // com.amazonaws.c.j
    public boolean a() {
        return false;
    }
}
