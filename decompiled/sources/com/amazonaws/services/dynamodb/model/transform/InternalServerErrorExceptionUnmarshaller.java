package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.e.b;
import com.amazonaws.f.a.c;
import com.amazonaws.services.dynamodb.model.InternalServerErrorException;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class InternalServerErrorExceptionUnmarshaller extends b {
    public InternalServerErrorExceptionUnmarshaller() {
        super(InternalServerErrorException.class);
    }

    @Override // com.amazonaws.e.b, com.amazonaws.e.t
    public com.amazonaws.b unmarshall(c cVar) {
        String errorCode = parseErrorCode(cVar);
        if (errorCode == null || !errorCode.equals("InternalServerError")) {
            return null;
        }
        return (InternalServerErrorException) super.unmarshall(cVar);
    }
}
