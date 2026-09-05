package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.b;
import com.amazonaws.e.q;
import com.amazonaws.services.sqs.model.OverLimitException;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class OverLimitExceptionUnmarshaller extends q {
    public OverLimitExceptionUnmarshaller() {
        super(OverLimitException.class);
    }

    @Override // com.amazonaws.e.q, com.amazonaws.e.t
    public b unmarshall(Node node) {
        String errorCode = parseErrorCode(node);
        if (errorCode == null || !errorCode.equals("OverLimit")) {
            return null;
        }
        return (OverLimitException) super.unmarshall(node);
    }
}
