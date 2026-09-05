package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.b;
import com.amazonaws.e.q;
import com.amazonaws.services.sqs.model.BatchEntryIdsNotDistinctException;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class BatchEntryIdsNotDistinctExceptionUnmarshaller extends q {
    public BatchEntryIdsNotDistinctExceptionUnmarshaller() {
        super(BatchEntryIdsNotDistinctException.class);
    }

    @Override // com.amazonaws.e.q, com.amazonaws.e.t
    public b unmarshall(Node node) {
        String errorCode = parseErrorCode(node);
        if (errorCode == null || !errorCode.equals("AWS.SimpleQueueService.BatchEntryIdsNotDistinct")) {
            return null;
        }
        return (BatchEntryIdsNotDistinctException) super.unmarshall(node);
    }
}
