package com.amazonaws.services.sqs.model.transform;

import com.amazonaws.b;
import com.amazonaws.e.q;
import com.amazonaws.services.sqs.model.QueueDeletedRecentlyException;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class QueueDeletedRecentlyExceptionUnmarshaller extends q {
    public QueueDeletedRecentlyExceptionUnmarshaller() {
        super(QueueDeletedRecentlyException.class);
    }

    @Override // com.amazonaws.e.q, com.amazonaws.e.t
    public b unmarshall(Node node) {
        String errorCode = parseErrorCode(node);
        if (errorCode == null || !errorCode.equals("AWS.SimpleQueueService.QueueDeletedRecently")) {
            return null;
        }
        return (QueueDeletedRecentlyException) super.unmarshall(node);
    }
}
