package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.e.b;
import com.amazonaws.f.a.c;
import com.amazonaws.services.dynamodb.model.LimitExceededException;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class LimitExceededExceptionUnmarshaller extends b {
    public LimitExceededExceptionUnmarshaller() {
        super(LimitExceededException.class);
    }

    @Override // com.amazonaws.e.b, com.amazonaws.e.t
    public com.amazonaws.b unmarshall(c cVar) {
        String errorCode = parseErrorCode(cVar);
        if (errorCode == null || !errorCode.equals("LimitExceededException")) {
            return null;
        }
        return (LimitExceededException) super.unmarshall(cVar);
    }
}
