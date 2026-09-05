package com.topfreegames.d.b;

import com.amazonaws.a.k;
import com.amazonaws.g;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* JADX INFO: compiled from: TopAmazonSQSAsyncClient.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b extends c implements com.amazonaws.services.sqs.b {
    private ExecutorService f;

    public b() {
        this(new k());
    }

    public b(com.amazonaws.a.c cVar) {
        this(cVar, Executors.newCachedThreadPool());
    }

    public b(com.amazonaws.a.c cVar, ExecutorService executorService) {
        this(cVar, new g(), executorService);
    }

    public b(com.amazonaws.a.c cVar, g gVar, ExecutorService executorService) {
        super(cVar, gVar);
        this.f = executorService;
    }

    @Override // com.amazonaws.services.sqs.b
    public Future<ReceiveMessageResult> a(final ReceiveMessageRequest receiveMessageRequest) {
        return this.f.submit(new Callable<ReceiveMessageResult>() { // from class: com.topfreegames.d.b.b.1
            @Override // java.util.concurrent.Callable
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public ReceiveMessageResult call() {
                return b.this.b(receiveMessageRequest);
            }
        });
    }
}
