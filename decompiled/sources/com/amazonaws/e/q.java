package com.amazonaws.e;

import org.w3c.dom.Node;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class q extends a<Node> {
    public q() {
    }

    protected q(Class<? extends com.amazonaws.b> cls) {
        super(cls);
    }

    public String getErrorPropertyPath(String str) {
        return "ErrorResponse/Error/" + str;
    }

    public String parseErrorCode(Node node) {
        return com.amazonaws.f.n.a("ErrorResponse/Error/Code", node);
    }

    @Override // com.amazonaws.e.t
    public com.amazonaws.b unmarshall(Node node) {
        String errorCode = parseErrorCode(node);
        String strA = com.amazonaws.f.n.a("ErrorResponse/Error/Type", node);
        String strA2 = com.amazonaws.f.n.a("ErrorResponse/RequestId", node);
        com.amazonaws.b bVarNewException = newException(com.amazonaws.f.n.a("ErrorResponse/Error/Message", node));
        bVarNewException.setErrorCode(errorCode);
        bVarNewException.setRequestId(strA2);
        if (strA == null) {
            bVarNewException.setErrorType(com.amazonaws.c.Unknown);
        } else if (strA.equalsIgnoreCase("Receiver")) {
            bVarNewException.setErrorType(com.amazonaws.c.Service);
        } else if (strA.equalsIgnoreCase("Sender")) {
            bVarNewException.setErrorType(com.amazonaws.c.Client);
        }
        return bVarNewException;
    }
}
