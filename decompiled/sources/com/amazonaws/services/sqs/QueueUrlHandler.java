package com.amazonaws.services.sqs;

import com.amazonaws.j;
import java.net.URI;
import java.net.URISyntaxException;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class QueueUrlHandler extends com.amazonaws.b.a {
    private static final String QUEUE_URL_PARAMETER = "QueueUrl";

    @Override // com.amazonaws.b.a, com.amazonaws.b.c
    public void beforeRequest(j<?> jVar) {
        if (jVar.d().get(QUEUE_URL_PARAMETER) != null) {
            String strRemove = jVar.d().remove(QUEUE_URL_PARAMETER);
            try {
                jVar.a(new URI(strRemove).getPath());
            } catch (URISyntaxException e) {
                throw new com.amazonaws.a("Unable to parse SQS queue URL '" + strRemove + "'", e);
            }
        }
    }
}
