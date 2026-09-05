package com.topfreegames.d.a;

import com.amazon.aws.tvmclient.AnonymousTVMCredentialsProvider;
import com.amazonaws.a.k;
import com.amazonaws.c.l;
import com.amazonaws.c.m;
import com.amazonaws.d;
import com.amazonaws.e;
import com.amazonaws.e.t;
import com.amazonaws.f;
import com.amazonaws.f.a.c;
import com.amazonaws.g;
import com.amazonaws.j;
import com.amazonaws.services.dynamodb.model.BatchGetItemRequest;
import com.amazonaws.services.dynamodb.model.BatchGetItemResult;
import com.amazonaws.services.dynamodb.model.DeleteItemRequest;
import com.amazonaws.services.dynamodb.model.DeleteItemResult;
import com.amazonaws.services.dynamodb.model.PutItemRequest;
import com.amazonaws.services.dynamodb.model.PutItemResult;
import com.amazonaws.services.dynamodb.model.QueryRequest;
import com.amazonaws.services.dynamodb.model.QueryResult;
import com.amazonaws.services.dynamodb.model.UpdateItemRequest;
import com.amazonaws.services.dynamodb.model.UpdateItemResult;
import com.amazonaws.services.dynamodb.model.transform.BatchGetItemRequestMarshaller;
import com.amazonaws.services.dynamodb.model.transform.BatchGetItemResultJsonUnmarshaller;
import com.amazonaws.services.dynamodb.model.transform.ConditionalCheckFailedExceptionUnmarshaller;
import com.amazonaws.services.dynamodb.model.transform.DeleteItemRequestMarshaller;
import com.amazonaws.services.dynamodb.model.transform.DeleteItemResultJsonUnmarshaller;
import com.amazonaws.services.dynamodb.model.transform.InternalServerErrorExceptionUnmarshaller;
import com.amazonaws.services.dynamodb.model.transform.LimitExceededExceptionUnmarshaller;
import com.amazonaws.services.dynamodb.model.transform.ProvisionedThroughputExceededExceptionUnmarshaller;
import com.amazonaws.services.dynamodb.model.transform.PutItemRequestMarshaller;
import com.amazonaws.services.dynamodb.model.transform.PutItemResultJsonUnmarshaller;
import com.amazonaws.services.dynamodb.model.transform.QueryRequestMarshaller;
import com.amazonaws.services.dynamodb.model.transform.QueryResultJsonUnmarshaller;
import com.amazonaws.services.dynamodb.model.transform.ResourceInUseExceptionUnmarshaller;
import com.amazonaws.services.dynamodb.model.transform.ResourceNotFoundExceptionUnmarshaller;
import com.amazonaws.services.dynamodb.model.transform.UpdateItemRequestMarshaller;
import com.amazonaws.services.dynamodb.model.transform.UpdateItemResultJsonUnmarshaller;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* JADX INFO: compiled from: TopAmazonDynamoDBClient.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b extends d implements com.amazonaws.services.dynamodb.a {
    private static final Log g = LogFactory.getLog(com.amazonaws.services.dynamodb.a.class);
    protected List<t<com.amazonaws.b, c>> e;
    private com.amazonaws.a.c f;
    private com.amazonaws.a.a h;
    private long i;

    public b() {
        this(new k(), new g());
    }

    public b(com.amazonaws.a.c cVar, g gVar) {
        super(gVar);
        this.i = 0L;
        this.f = cVar;
        b();
    }

    private void b() {
        this.e = new ArrayList();
        this.e.add(new LimitExceededExceptionUnmarshaller());
        this.e.add(new InternalServerErrorExceptionUnmarshaller());
        this.e.add(new ProvisionedThroughputExceededExceptionUnmarshaller());
        this.e.add(new ResourceInUseExceptionUnmarshaller());
        this.e.add(new ConditionalCheckFailedExceptionUnmarshaller());
        this.e.add(new ResourceNotFoundExceptionUnmarshaller());
        this.e.add(new com.amazonaws.e.b());
        a("dynamodb.us-east-1.amazonaws.com/");
        this.h = new a();
        this.d.addAll(new com.amazonaws.b.b().a("/com/amazonaws/services/dynamodb/request.handlers"));
        this.b = new g(this.b);
        if (this.b.i() == 3) {
            g.debug("Overriding default max error retry value to: 10");
            this.b.a(10);
        }
        a(this.b);
    }

    @Override // com.amazonaws.services.dynamodb.a
    public QueryResult a(QueryRequest queryRequest) {
        return (QueryResult) a(new QueryRequestMarshaller().marshall(queryRequest), new m(new QueryResultJsonUnmarshaller()));
    }

    @Override // com.amazonaws.services.dynamodb.a
    public UpdateItemResult a(UpdateItemRequest updateItemRequest) {
        return (UpdateItemResult) a(new UpdateItemRequestMarshaller().marshall(updateItemRequest), new m(new UpdateItemResultJsonUnmarshaller()));
    }

    @Override // com.amazonaws.services.dynamodb.a
    public PutItemResult a(PutItemRequest putItemRequest) {
        return (PutItemResult) a(new PutItemRequestMarshaller().marshall(putItemRequest), new m(new PutItemResultJsonUnmarshaller()));
    }

    @Override // com.amazonaws.services.dynamodb.a
    public DeleteItemResult a(DeleteItemRequest deleteItemRequest) {
        return (DeleteItemResult) a(new DeleteItemRequestMarshaller().marshall(deleteItemRequest), new m(new DeleteItemResultJsonUnmarshaller()));
    }

    @Override // com.amazonaws.services.dynamodb.a
    public BatchGetItemResult a(BatchGetItemRequest batchGetItemRequest) {
        return (BatchGetItemResult) a(new BatchGetItemRequestMarshaller().marshall(batchGetItemRequest), new m(new BatchGetItemResultJsonUnmarshaller()));
    }

    @Override // com.amazonaws.d
    public void a(String str) {
        super.a(str);
    }

    private <X, Y extends e> X a(j<Y> jVar, com.amazonaws.c.j<f<X>> jVar2) {
        jVar.a(this.f96a);
        com.amazonaws.a.b credentials = this.f.getCredentials();
        e eVarA = jVar.a();
        if (eVarA != null && eVarA.getRequestCredentials() != null) {
            credentials = eVarA.getRequestCredentials();
        }
        com.amazonaws.c.d dVarA = a();
        dVarA.a(this.h);
        dVarA.a(credentials);
        dVarA.a(com.amazonaws.d.b.f97a);
        l lVar = new l(this.e);
        try {
            return (X) this.c.a((j<?>) jVar, (com.amazonaws.c.j) jVar2, (com.amazonaws.c.j<com.amazonaws.b>) lVar, dVarA);
        } catch (com.amazonaws.b e) {
            if ((e.getErrorCode().equals("AccessDeniedException") || e.getErrorCode().equals("IncompleteSignatureException") || e.getErrorCode().equals("MissingAuthenticationTokenException") || e.getErrorCode().equals("ValidationException") || e.getErrorCode().equals("InternalFailure") || e.getErrorCode().equals("InternalServerError")) && System.currentTimeMillis() - this.i > 60000) {
                ((AnonymousTVMCredentialsProvider) this.f).wipe();
                ((AnonymousTVMCredentialsProvider) this.f).clearCredentials();
                dVarA.a(this.f.getCredentials());
                this.i = System.currentTimeMillis();
                return (X) this.c.a((j<?>) jVar, (com.amazonaws.c.j) jVar2, (com.amazonaws.c.j<com.amazonaws.b>) lVar, dVarA);
            }
            throw e;
        } catch (com.amazonaws.a e2) {
            if (System.currentTimeMillis() - this.i > 60000) {
                ((AnonymousTVMCredentialsProvider) this.f).wipe();
                ((AnonymousTVMCredentialsProvider) this.f).clearCredentials();
                dVarA.a(this.f.getCredentials());
                this.i = System.currentTimeMillis();
                return (X) this.c.a((j<?>) jVar, (com.amazonaws.c.j) jVar2, (com.amazonaws.c.j<com.amazonaws.b>) lVar, dVarA);
            }
            throw e2;
        }
    }
}
