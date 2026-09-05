package com.facebook;

import android.content.Context;
import com.facebook.internal.CacheableRequestBatch;
import com.facebook.internal.FileLruCache;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObjectList;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class Response {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final String BODY_KEY = "body";
    private static final String CODE_KEY = "code";
    private static final int INVALID_SESSION_FACEBOOK_ERROR_CODE = 190;
    public static final String NON_JSON_RESPONSE_PROPERTY = "FACEBOOK_NON_JSON_RESULT";
    private static final String RESPONSE_CACHE_TAG = "ResponseCache";
    private static final String RESPONSE_LOG_TAG = "Response";
    private static FileLruCache responseCache;
    private final HttpURLConnection connection;
    private final FacebookRequestError error;
    private final GraphObject graphObject;
    private final GraphObjectList<GraphObject> graphObjectList;
    private final boolean isFromCache;
    private final Request request;

    interface PagedResults extends GraphObject {
        GraphObjectList<GraphObject> getData();

        PagingInfo getPaging();
    }

    public enum PagingDirection {
        NEXT,
        PREVIOUS;

        /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
        public static PagingDirection[] valuesCustom() {
            PagingDirection[] pagingDirectionArrValuesCustom = values();
            int length = pagingDirectionArrValuesCustom.length;
            PagingDirection[] pagingDirectionArr = new PagingDirection[length];
            System.arraycopy(pagingDirectionArrValuesCustom, 0, pagingDirectionArr, 0, length);
            return pagingDirectionArr;
        }
    }

    interface PagingInfo extends GraphObject {
        String getNext();

        String getPrevious();
    }

    static {
        $assertionsDisabled = !Response.class.desiredAssertionStatus();
    }

    Response(Request request, HttpURLConnection httpURLConnection, GraphObject graphObject, boolean z) {
        this.request = request;
        this.connection = httpURLConnection;
        this.graphObject = graphObject;
        this.graphObjectList = null;
        this.isFromCache = z;
        this.error = null;
    }

    Response(Request request, HttpURLConnection httpURLConnection, GraphObjectList<GraphObject> graphObjectList, boolean z) {
        this.request = request;
        this.connection = httpURLConnection;
        this.graphObject = null;
        this.graphObjectList = graphObjectList;
        this.isFromCache = z;
        this.error = null;
    }

    Response(Request request, HttpURLConnection httpURLConnection, FacebookRequestError facebookRequestError) {
        this.request = request;
        this.connection = httpURLConnection;
        this.graphObject = null;
        this.graphObjectList = null;
        this.isFromCache = false;
        this.error = facebookRequestError;
    }

    public final FacebookRequestError getError() {
        return this.error;
    }

    public final GraphObject getGraphObject() {
        return this.graphObject;
    }

    public final <T extends GraphObject> T getGraphObjectAs(Class<T> cls) {
        if (this.graphObject == null) {
            return null;
        }
        if (cls == null) {
            throw new NullPointerException("Must pass in a valid interface that extends GraphObject");
        }
        return (T) this.graphObject.cast(cls);
    }

    public final GraphObjectList<GraphObject> getGraphObjectList() {
        return this.graphObjectList;
    }

    public final <T extends GraphObject> GraphObjectList<T> getGraphObjectListAs(Class<T> cls) {
        if (this.graphObjectList == null) {
            return null;
        }
        return (GraphObjectList<T>) this.graphObjectList.castToListOf(cls);
    }

    public final HttpURLConnection getConnection() {
        return this.connection;
    }

    public Request getRequest() {
        return this.request;
    }

    public Request getRequestForPagedResults(PagingDirection pagingDirection) {
        String previous;
        PagingInfo paging;
        if (this.graphObject == null || (paging = ((PagedResults) this.graphObject.cast(PagedResults.class)).getPaging()) == null) {
            previous = null;
        } else if (pagingDirection == PagingDirection.NEXT) {
            previous = paging.getNext();
        } else {
            previous = paging.getPrevious();
        }
        if (Utility.isNullOrEmpty(previous)) {
            return null;
        }
        if (previous != null && previous.equals(this.request.getUrlForSingleRequest())) {
            return null;
        }
        try {
            return new Request(this.request.getSession(), new URL(previous));
        } catch (MalformedURLException e) {
            return null;
        }
    }

    public String toString() {
        String str;
        try {
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(this.connection != null ? this.connection.getResponseCode() : 200);
            str = String.format("%d", objArr);
        } catch (IOException e) {
            str = "unknown";
        }
        return "{Response:  responseCode: " + str + ", graphObject: " + this.graphObject + ", error: " + this.error + ", isFromCache:" + this.isFromCache + "}";
    }

    public final boolean getIsFromCache() {
        return this.isFromCache;
    }

    static FileLruCache getResponseCache() {
        Context staticContext;
        if (responseCache == null && (staticContext = Session.getStaticContext()) != null) {
            responseCache = new FileLruCache(staticContext, RESPONSE_CACHE_TAG, new FileLruCache.Limits());
        }
        return responseCache;
    }

    /* JADX WARN: Code duplicated, block: B:26:0x005e A[Catch: FacebookException -> 0x009c, JSONException -> 0x00b4, IOException -> 0x00d2, SecurityException -> 0x00f0, all -> 0x010e, Merged into TryCatch #8 {all -> 0x010e, FacebookException -> 0x009c, JSONException -> 0x00b4, IOException -> 0x00d2, SecurityException -> 0x00f0, blocks: (B:24:0x0056, B:26:0x005e, B:27:0x0062, B:37:0x008a, B:41:0x0094, B:45:0x009d, B:48:0x00b5, B:51:0x00d3, B:54:0x00f1), top: B:67:0x0056 }] */
    /* JADX WARN: Code duplicated, block: B:37:0x008a A[Catch: FacebookException -> 0x009c, JSONException -> 0x00b4, IOException -> 0x00d2, SecurityException -> 0x00f0, all -> 0x010e, Merged into TryCatch #8 {all -> 0x010e, FacebookException -> 0x009c, JSONException -> 0x00b4, IOException -> 0x00d2, SecurityException -> 0x00f0, blocks: (B:24:0x0056, B:26:0x005e, B:27:0x0062, B:37:0x008a, B:41:0x0094, B:45:0x009d, B:48:0x00b5, B:51:0x00d3, B:54:0x00f1), top: B:67:0x0056 }, TRY_ENTER] */
    static List<Response> fromHttpConnection(HttpURLConnection httpURLConnection, RequestBatch requestBatch) {
        FileLruCache fileLruCache;
        InputStream inputStream;
        List<Response> listConstructErrorResponses;
        InputStream inputStreamInterceptAndPut;
        InputStream inputStream2;
        String str = null;
        try {
            if (requestBatch instanceof CacheableRequestBatch) {
                CacheableRequestBatch cacheableRequestBatch = (CacheableRequestBatch) requestBatch;
                FileLruCache responseCache2 = getResponseCache();
                String cacheKeyOverride = cacheableRequestBatch.getCacheKeyOverride();
                if (Utility.isNullOrEmpty(cacheKeyOverride)) {
                    if (requestBatch.size() == 1) {
                        cacheKeyOverride = requestBatch.get(0).getUrlForSingleRequest();
                    } else {
                        Logger.log(LoggingBehavior.REQUESTS, RESPONSE_CACHE_TAG, "Not using cache for cacheable request because no key was specified");
                    }
                }
                if (!cacheableRequestBatch.getForceRoundTrip() && responseCache2 != null && !Utility.isNullOrEmpty(cacheKeyOverride)) {
                    try {
                        try {
                            InputStream inputStream3 = responseCache2.get(cacheKeyOverride);
                            if (inputStream3 != null) {
                                try {
                                    listConstructErrorResponses = createResponsesFromStream(inputStream3, null, requestBatch, true);
                                    Utility.closeQuietly(inputStream3);
                                } catch (FacebookException e) {
                                    inputStream2 = inputStream3;
                                    Utility.closeQuietly(inputStream2);
                                    str = cacheKeyOverride;
                                    inputStream = inputStream2;
                                    fileLruCache = responseCache2;
                                    if (httpURLConnection.getResponseCode() >= 400) {
                                        inputStream = httpURLConnection.getErrorStream();
                                    } else {
                                        inputStream = httpURLConnection.getInputStream();
                                        if (fileLruCache != null) {
                                            inputStream = inputStreamInterceptAndPut;
                                        }
                                    }
                                    listConstructErrorResponses = createResponsesFromStream(inputStream, httpURLConnection, requestBatch, false);
                                }
                            } else {
                                Utility.closeQuietly(inputStream3);
                                fileLruCache = responseCache2;
                                String str2 = cacheKeyOverride;
                                inputStream = inputStream3;
                                str = str2;
                            }
                        } catch (FacebookException e2) {
                            inputStream2 = null;
                        }
                    } catch (IOException e3) {
                        Utility.closeQuietly(null);
                        fileLruCache = responseCache2;
                        String str3 = cacheKeyOverride;
                        inputStream = null;
                        str = str3;
                    } catch (JSONException e4) {
                        Utility.closeQuietly(null);
                        fileLruCache = responseCache2;
                        String str4 = cacheKeyOverride;
                        inputStream = null;
                        str = str4;
                    } catch (Throwable th) {
                        Utility.closeQuietly(null);
                        throw th;
                    }
                    return listConstructErrorResponses;
                }
                fileLruCache = responseCache2;
                String str5 = cacheKeyOverride;
                inputStream = null;
                str = str5;
            } else {
                fileLruCache = null;
                inputStream = null;
            }
            if (httpURLConnection.getResponseCode() >= 400) {
                inputStream = httpURLConnection.getErrorStream();
            } else {
                inputStream = httpURLConnection.getInputStream();
                if (fileLruCache != null && str != null && inputStream != null && (inputStreamInterceptAndPut = fileLruCache.interceptAndPut(str, inputStream)) != null) {
                    inputStream = inputStreamInterceptAndPut;
                }
            }
            listConstructErrorResponses = createResponsesFromStream(inputStream, httpURLConnection, requestBatch, false);
        } catch (FacebookException e5) {
            Logger.log(LoggingBehavior.REQUESTS, RESPONSE_LOG_TAG, "Response <Error>: %s", e5);
            listConstructErrorResponses = constructErrorResponses(requestBatch, httpURLConnection, e5);
        } catch (JSONException e6) {
            Logger.log(LoggingBehavior.REQUESTS, RESPONSE_LOG_TAG, "Response <Error>: %s", e6);
            listConstructErrorResponses = constructErrorResponses(requestBatch, httpURLConnection, new FacebookException(e6));
        } catch (IOException e7) {
            Logger.log(LoggingBehavior.REQUESTS, RESPONSE_LOG_TAG, "Response <Error>: %s", e7);
            listConstructErrorResponses = constructErrorResponses(requestBatch, httpURLConnection, new FacebookException(e7));
        } catch (SecurityException e8) {
            Logger.log(LoggingBehavior.REQUESTS, RESPONSE_LOG_TAG, "Response <Error>: %s", e8);
            listConstructErrorResponses = constructErrorResponses(requestBatch, httpURLConnection, new FacebookException(e8));
        } finally {
            Utility.closeQuietly(inputStream);
        }
        return listConstructErrorResponses;
    }

    static List<Response> createResponsesFromStream(InputStream inputStream, HttpURLConnection httpURLConnection, RequestBatch requestBatch, boolean z) throws Throwable {
        String streamToString = Utility.readStreamToString(inputStream);
        Logger.log(LoggingBehavior.INCLUDE_RAW_RESPONSES, RESPONSE_LOG_TAG, "Response (raw)\n  Size: %d\n  Response:\n%s\n", Integer.valueOf(streamToString.length()), streamToString);
        return createResponsesFromString(streamToString, httpURLConnection, requestBatch, z);
    }

    static List<Response> createResponsesFromString(String str, HttpURLConnection httpURLConnection, RequestBatch requestBatch, boolean z) {
        List<Response> listCreateResponsesFromObject = createResponsesFromObject(httpURLConnection, requestBatch, new JSONTokener(str).nextValue(), z);
        Logger.log(LoggingBehavior.REQUESTS, RESPONSE_LOG_TAG, "Response\n  Id: %s\n  Size: %d\n  Responses:\n%s\n", requestBatch.getId(), Integer.valueOf(str.length()), listCreateResponsesFromObject);
        return listCreateResponsesFromObject;
    }

    private static List<Response> createResponsesFromObject(HttpURLConnection httpURLConnection, List<Request> list, Object obj, boolean z) {
        Object obj2;
        if (!$assertionsDisabled && httpURLConnection == null && !z) {
            throw new AssertionError();
        }
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        if (size == 1) {
            Request request = list.get(0);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(BODY_KEY, obj);
                jSONObject.put(CODE_KEY, httpURLConnection != null ? httpURLConnection.getResponseCode() : 200);
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(jSONObject);
                obj2 = jSONArray;
            } catch (IOException e) {
                arrayList.add(new Response(request, httpURLConnection, new FacebookRequestError(httpURLConnection, e)));
                obj2 = obj;
            } catch (JSONException e2) {
                arrayList.add(new Response(request, httpURLConnection, new FacebookRequestError(httpURLConnection, e2)));
                obj2 = obj;
            }
        } else {
            obj2 = obj;
        }
        if (!(obj2 instanceof JSONArray) || ((JSONArray) obj2).length() != size) {
            throw new FacebookException("Unexpected number of results");
        }
        JSONArray jSONArray2 = (JSONArray) obj2;
        for (int i = 0; i < jSONArray2.length(); i++) {
            Request request2 = list.get(i);
            try {
                arrayList.add(createResponseFromObject(request2, httpURLConnection, jSONArray2.get(i), z, obj));
            } catch (FacebookException e3) {
                arrayList.add(new Response(request2, httpURLConnection, new FacebookRequestError(httpURLConnection, e3)));
            } catch (JSONException e4) {
                arrayList.add(new Response(request2, httpURLConnection, new FacebookRequestError(httpURLConnection, e4)));
            }
        }
        return arrayList;
    }

    private static Response createResponseFromObject(Request request, HttpURLConnection httpURLConnection, Object obj, boolean z, Object obj2) throws JSONException {
        Session session;
        if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            FacebookRequestError facebookRequestErrorCheckResponseAndCreateError = FacebookRequestError.checkResponseAndCreateError(jSONObject, obj2, httpURLConnection);
            if (facebookRequestErrorCheckResponseAndCreateError != null) {
                if (facebookRequestErrorCheckResponseAndCreateError.getErrorCode() == INVALID_SESSION_FACEBOOK_ERROR_CODE && (session = request.getSession()) != null) {
                    session.closeAndClearTokenInformation();
                }
                return new Response(request, httpURLConnection, facebookRequestErrorCheckResponseAndCreateError);
            }
            Object stringPropertyAsJSON = Utility.getStringPropertyAsJSON(jSONObject, BODY_KEY, NON_JSON_RESPONSE_PROPERTY);
            if (stringPropertyAsJSON instanceof JSONObject) {
                return new Response(request, httpURLConnection, GraphObject.Factory.create((JSONObject) stringPropertyAsJSON), z);
            }
            if (stringPropertyAsJSON instanceof JSONArray) {
                return new Response(request, httpURLConnection, (GraphObjectList<GraphObject>) GraphObject.Factory.createList((JSONArray) stringPropertyAsJSON, GraphObject.class), z);
            }
            obj = JSONObject.NULL;
        }
        if (obj == JSONObject.NULL) {
            return new Response(request, httpURLConnection, (GraphObject) null, z);
        }
        throw new FacebookException("Got unexpected object type in response, class: " + obj.getClass().getSimpleName());
    }

    static List<Response> constructErrorResponses(List<Request> list, HttpURLConnection httpURLConnection, FacebookException facebookException) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(new Response(list.get(i), httpURLConnection, new FacebookRequestError(httpURLConnection, facebookException)));
        }
        return arrayList;
    }
}
