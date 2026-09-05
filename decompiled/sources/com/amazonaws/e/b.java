package com.amazonaws.e;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b extends a<com.amazonaws.f.a.c> {
    public b() {
    }

    protected b(Class<? extends com.amazonaws.b> cls) {
        super(cls);
    }

    public String parseErrorCode(com.amazonaws.f.a.c cVar) {
        if (!cVar.c("__type")) {
            return null;
        }
        String strB = cVar.b("__type");
        return strB.substring(strB.lastIndexOf("#") + 1);
    }

    public String parseMessage(com.amazonaws.f.a.c cVar) {
        return cVar.c("message") ? cVar.b("message") : "";
    }

    @Override // com.amazonaws.e.t
    public com.amazonaws.b unmarshall(com.amazonaws.f.a.c cVar) {
        com.amazonaws.b bVarNewException = newException(parseMessage(cVar));
        bVarNewException.setErrorCode(parseErrorCode(cVar));
        return bVarNewException;
    }
}
