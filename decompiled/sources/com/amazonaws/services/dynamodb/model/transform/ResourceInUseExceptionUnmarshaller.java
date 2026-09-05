package com.amazonaws.services.dynamodb.model.transform;

import com.amazonaws.e.b;
import com.amazonaws.f.a.c;
import com.amazonaws.services.dynamodb.model.ResourceInUseException;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ResourceInUseExceptionUnmarshaller extends b {
    public ResourceInUseExceptionUnmarshaller() {
        super(ResourceInUseException.class);
    }

    @Override // com.amazonaws.e.b, com.amazonaws.e.t
    public com.amazonaws.b unmarshall(c cVar) {
        String errorCode = parseErrorCode(cVar);
        if (errorCode == null || !errorCode.equals("ResourceInUseException")) {
            return null;
        }
        return (ResourceInUseException) super.unmarshall(cVar);
    }
}
