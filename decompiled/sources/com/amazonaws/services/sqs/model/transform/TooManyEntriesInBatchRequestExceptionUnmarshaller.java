package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.b;
import com.amazonaws.e.q;
import com.amazonaws.services.sqs.model.TooManyEntriesInBatchRequestException;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class TooManyEntriesInBatchRequestExceptionUnmarshaller extends q {
    public TooManyEntriesInBatchRequestExceptionUnmarshaller() {
        super(TooManyEntriesInBatchRequestException.class);
    }

    @Override // com.amazonaws.e.q, com.amazonaws.e.t
    public b unmarshall(Node node) {
        String errorCode = parseErrorCode(node);
        if (errorCode == null || !errorCode.equals("AWS.SimpleQueueService.TooManyEntriesInBatchRequest")) {
            return null;
        }
        return (TooManyEntriesInBatchRequestException) super.unmarshall(node);
    }
}
