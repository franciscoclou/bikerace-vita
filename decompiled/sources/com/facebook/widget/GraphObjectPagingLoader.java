package com.facebook.widget;

import android.content.Context;
import android.os.Handler;
import android.support.v4.a.a;
import com.facebook.FacebookException;
import com.facebook.FacebookRequestError;
import com.facebook.Request;
import com.facebook.RequestBatch;
import com.facebook.Response;
import com.facebook.internal.CacheableRequestBatch;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObjectList;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class GraphObjectPagingLoader<T extends GraphObject> extends a<SimpleGraphObjectCursor<T>> {
    private boolean appendResults;
    private Request currentRequest;
    private SimpleGraphObjectCursor<T> cursor;
    private final Class<T> graphObjectClass;
    private boolean loading;
    private Request nextRequest;
    private OnErrorListener onErrorListener;
    private Request originalRequest;
    private boolean skipRoundtripIfCached;

    public interface OnErrorListener {
        void onError(FacebookException facebookException, GraphObjectPagingLoader<?> graphObjectPagingLoader);
    }

    interface PagedResults extends GraphObject {
        GraphObjectList<GraphObject> getData();
    }

    public GraphObjectPagingLoader(Context context, Class<T> cls) {
        super(context);
        this.appendResults = false;
        this.loading = false;
        this.graphObjectClass = cls;
    }

    public OnErrorListener getOnErrorListener() {
        return this.onErrorListener;
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.onErrorListener = onErrorListener;
    }

    public SimpleGraphObjectCursor<T> getCursor() {
        return this.cursor;
    }

    public void clearResults() {
        this.nextRequest = null;
        this.originalRequest = null;
        this.currentRequest = null;
        deliverResult((SimpleGraphObjectCursor) null);
    }

    public boolean isLoading() {
        return this.loading;
    }

    public void startLoading(Request request, boolean z) {
        this.originalRequest = request;
        startLoading(request, z, 0L);
    }

    public void refreshOriginalRequest(long j) {
        if (this.originalRequest == null) {
            throw new FacebookException("refreshOriginalRequest may not be called until after startLoading has been called.");
        }
        startLoading(this.originalRequest, false, j);
    }

    public void followNextLink() {
        if (this.nextRequest != null) {
            this.appendResults = true;
            this.currentRequest = this.nextRequest;
            this.currentRequest.setCallback(new Request.Callback() { // from class: com.facebook.widget.GraphObjectPagingLoader.1
                @Override // com.facebook.Request.Callback
                public void onCompleted(Response response) {
                    GraphObjectPagingLoader.this.requestCompleted(response);
                }
            });
            this.loading = true;
            Request.executeBatchAsync((RequestBatch) putRequestIntoBatch(this.currentRequest, this.skipRoundtripIfCached));
        }
    }

    @Override // android.support.v4.a.a
    public void deliverResult(SimpleGraphObjectCursor<T> simpleGraphObjectCursor) {
        SimpleGraphObjectCursor<T> simpleGraphObjectCursor2 = this.cursor;
        this.cursor = simpleGraphObjectCursor;
        if (isStarted()) {
            super.deliverResult(simpleGraphObjectCursor);
            if (simpleGraphObjectCursor2 != null && simpleGraphObjectCursor2 != simpleGraphObjectCursor && !simpleGraphObjectCursor2.isClosed()) {
                simpleGraphObjectCursor2.close();
            }
        }
    }

    @Override // android.support.v4.a.a
    protected void onStartLoading() {
        super.onStartLoading();
        if (this.cursor != null) {
            deliverResult((SimpleGraphObjectCursor) this.cursor);
        }
    }

    private void startLoading(Request request, boolean z, long j) {
        this.skipRoundtripIfCached = z;
        this.appendResults = false;
        this.nextRequest = null;
        this.currentRequest = request;
        this.currentRequest.setCallback(new Request.Callback() { // from class: com.facebook.widget.GraphObjectPagingLoader.2
            @Override // com.facebook.Request.Callback
            public void onCompleted(Response response) {
                GraphObjectPagingLoader.this.requestCompleted(response);
            }
        });
        this.loading = true;
        final CacheableRequestBatch cacheableRequestBatchPutRequestIntoBatch = putRequestIntoBatch(request, z);
        Runnable runnable = new Runnable() { // from class: com.facebook.widget.GraphObjectPagingLoader.3
            @Override // java.lang.Runnable
            public void run() {
                Request.executeBatchAsync(cacheableRequestBatchPutRequestIntoBatch);
            }
        };
        if (j == 0) {
            runnable.run();
        } else {
            new Handler().postDelayed(runnable, j);
        }
    }

    private CacheableRequestBatch putRequestIntoBatch(Request request, boolean z) {
        CacheableRequestBatch cacheableRequestBatch = new CacheableRequestBatch(request);
        cacheableRequestBatch.setForceRoundTrip(z ? false : true);
        return cacheableRequestBatch;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestCompleted(Response response) {
        if (response.getRequest() == this.currentRequest) {
            this.loading = false;
            this.currentRequest = null;
            FacebookRequestError error = response.getError();
            FacebookException exception = error == null ? null : error.getException();
            if (response.getGraphObject() == null && exception == null) {
                exception = new FacebookException("GraphObjectPagingLoader received neither a result nor an error.");
            }
            if (exception != null) {
                this.nextRequest = null;
                if (this.onErrorListener != null) {
                    this.onErrorListener.onError(exception, this);
                    return;
                }
                return;
            }
            addResults(response);
        }
    }

    private void addResults(Response response) {
        SimpleGraphObjectCursor simpleGraphObjectCursor = (this.cursor == null || !this.appendResults) ? new SimpleGraphObjectCursor() : new SimpleGraphObjectCursor(this.cursor);
        PagedResults pagedResults = (PagedResults) response.getGraphObjectAs(PagedResults.class);
        boolean isFromCache = response.getIsFromCache();
        GraphObjectList<U> graphObjectListCastToListOf = pagedResults.getData().castToListOf(this.graphObjectClass);
        boolean z = graphObjectListCastToListOf.size() > 0;
        if (z) {
            this.nextRequest = response.getRequestForPagedResults(Response.PagingDirection.NEXT);
            simpleGraphObjectCursor.addGraphObjects(graphObjectListCastToListOf, isFromCache);
            simpleGraphObjectCursor.setMoreObjectsAvailable(true);
        }
        if (!z) {
            simpleGraphObjectCursor.setMoreObjectsAvailable(false);
            simpleGraphObjectCursor.setFromCache(isFromCache);
            this.nextRequest = null;
        }
        if (!isFromCache) {
            this.skipRoundtripIfCached = false;
        }
        deliverResult(simpleGraphObjectCursor);
    }
}
