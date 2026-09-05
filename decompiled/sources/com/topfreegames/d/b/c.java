package com.topfreegames.d.b;

import com.amazonaws.a.m;
import com.amazonaws.c.o;
import com.amazonaws.d;
import com.amazonaws.e;
import com.amazonaws.e.q;
import com.amazonaws.e.r;
import com.amazonaws.e.t;
import com.amazonaws.g;
import com.amazonaws.j;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.amazonaws.services.sqs.model.transform.BatchEntryIdsNotDistinctExceptionUnmarshaller;
import com.amazonaws.services.sqs.model.transform.BatchRequestTooLongExceptionUnmarshaller;
import com.amazonaws.services.sqs.model.transform.DeleteMessageRequestMarshaller;
import com.amazonaws.services.sqs.model.transform.EmptyBatchRequestExceptionUnmarshaller;
import com.amazonaws.services.sqs.model.transform.InvalidAttributeNameExceptionUnmarshaller;
import com.amazonaws.services.sqs.model.transform.InvalidBatchEntryIdExceptionUnmarshaller;
import com.amazonaws.services.sqs.model.transform.InvalidIdFormatExceptionUnmarshaller;
import com.amazonaws.services.sqs.model.transform.InvalidMessageContentsExceptionUnmarshaller;
import com.amazonaws.services.sqs.model.transform.MessageNotInflightExceptionUnmarshaller;
import com.amazonaws.services.sqs.model.transform.OverLimitExceptionUnmarshaller;
import com.amazonaws.services.sqs.model.transform.QueueDeletedRecentlyExceptionUnmarshaller;
import com.amazonaws.services.sqs.model.transform.QueueDoesNotExistExceptionUnmarshaller;
import com.amazonaws.services.sqs.model.transform.QueueNameExistsExceptionUnmarshaller;
import com.amazonaws.services.sqs.model.transform.ReceiptHandleIsInvalidExceptionUnmarshaller;
import com.amazonaws.services.sqs.model.transform.ReceiveMessageRequestMarshaller;
import com.amazonaws.services.sqs.model.transform.ReceiveMessageResultStaxUnmarshaller;
import com.amazonaws.services.sqs.model.transform.SendMessageRequestMarshaller;
import com.amazonaws.services.sqs.model.transform.SendMessageResultStaxUnmarshaller;
import com.amazonaws.services.sqs.model.transform.TooManyEntriesInBatchRequestExceptionUnmarshaller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Node;

/* JADX INFO: compiled from: TopAmazonSQSClient.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c extends d implements com.amazonaws.services.sqs.a {
    protected final List<t<com.amazonaws.b, Node>> e;
    private com.amazonaws.a.c f;
    private m g;

    public c(com.amazonaws.a.c cVar, g gVar) {
        super(gVar);
        this.e = new ArrayList();
        this.f = cVar;
        b();
    }

    private void b() {
        this.e.add(new QueueDeletedRecentlyExceptionUnmarshaller());
        this.e.add(new QueueNameExistsExceptionUnmarshaller());
        this.e.add(new EmptyBatchRequestExceptionUnmarshaller());
        this.e.add(new InvalidMessageContentsExceptionUnmarshaller());
        this.e.add(new InvalidBatchEntryIdExceptionUnmarshaller());
        this.e.add(new OverLimitExceptionUnmarshaller());
        this.e.add(new TooManyEntriesInBatchRequestExceptionUnmarshaller());
        this.e.add(new InvalidIdFormatExceptionUnmarshaller());
        this.e.add(new QueueDoesNotExistExceptionUnmarshaller());
        this.e.add(new InvalidAttributeNameExceptionUnmarshaller());
        this.e.add(new BatchRequestTooLongExceptionUnmarshaller());
        this.e.add(new ReceiptHandleIsInvalidExceptionUnmarshaller());
        this.e.add(new MessageNotInflightExceptionUnmarshaller());
        this.e.add(new BatchEntryIdsNotDistinctExceptionUnmarshaller());
        this.e.add(new q());
        a("queue.amazonaws.com");
        this.g = new a();
        this.d.addAll(new com.amazonaws.b.b().a("/com/amazonaws/services/sqs/request.handlers"));
    }

    @Override // com.amazonaws.services.sqs.a
    public SendMessageResult a(SendMessageRequest sendMessageRequest) {
        return (SendMessageResult) a(new SendMessageRequestMarshaller().marshall(sendMessageRequest), new SendMessageResultStaxUnmarshaller());
    }

    public ReceiveMessageResult b(ReceiveMessageRequest receiveMessageRequest) {
        return (ReceiveMessageResult) a(new ReceiveMessageRequestMarshaller().marshall(receiveMessageRequest), new ReceiveMessageResultStaxUnmarshaller());
    }

    @Override // com.amazonaws.services.sqs.a
    public void a(DeleteMessageRequest deleteMessageRequest) {
        a(new DeleteMessageRequestMarshaller().marshall(deleteMessageRequest), null);
    }

    private <X, Y extends e> X a(j<Y> jVar, t<X, r> tVar) {
        jVar.a(this.f96a);
        for (Map.Entry<String, String> entry : jVar.a().copyPrivateRequestParameters().entrySet()) {
            jVar.b(entry.getKey(), entry.getValue());
        }
        com.amazonaws.a.b credentials = this.f.getCredentials();
        e eVarA = jVar.a();
        if (eVarA != null && eVarA.getRequestCredentials() != null) {
            credentials = eVarA.getRequestCredentials();
        }
        com.amazonaws.c.d dVarA = a();
        dVarA.a(this.g);
        dVarA.a(credentials);
        return (X) this.c.a((j<?>) jVar, (com.amazonaws.c.j) new o(tVar), (com.amazonaws.c.j<com.amazonaws.b>) new com.amazonaws.c.c(this.e), dVarA);
    }
}
