package com.facebook.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import com.facebook.FacebookException;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ImageDownloader {
    private static final int CACHE_READ_QUEUE_MAX_CONCURRENT = 2;
    private static final int DOWNLOAD_QUEUE_MAX_CONCURRENT = 8;
    private static Handler handler;
    private static WorkQueue downloadQueue = new WorkQueue(8);
    private static WorkQueue cacheReadQueue = new WorkQueue(2);
    private static final Map<RequestKey, DownloaderContext> pendingRequests = new HashMap();

    public static void downloadAsync(ImageRequest imageRequest) {
        if (imageRequest != null) {
            RequestKey requestKey = new RequestKey(imageRequest.getImageUri(), imageRequest.getCallerTag());
            synchronized (pendingRequests) {
                DownloaderContext downloaderContext = pendingRequests.get(requestKey);
                if (downloaderContext != null) {
                    downloaderContext.request = imageRequest;
                    downloaderContext.isCancelled = false;
                    downloaderContext.workItem.moveToFront();
                } else {
                    enqueueCacheRead(imageRequest, requestKey, imageRequest.isCachedRedirectAllowed());
                }
            }
        }
    }

    public static boolean cancelRequest(ImageRequest imageRequest) {
        boolean z;
        RequestKey requestKey = new RequestKey(imageRequest.getImageUri(), imageRequest.getCallerTag());
        synchronized (pendingRequests) {
            DownloaderContext downloaderContext = pendingRequests.get(requestKey);
            if (downloaderContext == null) {
                z = false;
            } else if (downloaderContext.workItem.cancel()) {
                pendingRequests.remove(requestKey);
                z = true;
            } else {
                downloaderContext.isCancelled = true;
                z = true;
            }
        }
        return z;
    }

    public static void prioritizeRequest(ImageRequest imageRequest) {
        RequestKey requestKey = new RequestKey(imageRequest.getImageUri(), imageRequest.getCallerTag());
        synchronized (pendingRequests) {
            DownloaderContext downloaderContext = pendingRequests.get(requestKey);
            if (downloaderContext != null) {
                downloaderContext.workItem.moveToFront();
            }
        }
    }

    public static void clearCache(Context context) {
        ImageResponseCache.clearCache(context);
        UrlRedirectCache.clearCache(context);
    }

    private static void enqueueCacheRead(ImageRequest imageRequest, RequestKey requestKey, boolean z) {
        enqueueRequest(imageRequest, requestKey, cacheReadQueue, new CacheReadWorkItem(imageRequest.getContext(), requestKey, z));
    }

    private static void enqueueDownload(ImageRequest imageRequest, RequestKey requestKey) {
        enqueueRequest(imageRequest, requestKey, downloadQueue, new DownloadImageWorkItem(imageRequest.getContext(), requestKey));
    }

    private static void enqueueRequest(ImageRequest imageRequest, RequestKey requestKey, WorkQueue workQueue, Runnable runnable) {
        synchronized (pendingRequests) {
            DownloaderContext downloaderContext = new DownloaderContext(null);
            downloaderContext.request = imageRequest;
            pendingRequests.put(requestKey, downloaderContext);
            downloaderContext.workItem = workQueue.addActiveWorkItem(runnable);
        }
    }

    private static void issueResponse(RequestKey requestKey, final Exception exc, final Bitmap bitmap, final boolean z) {
        final ImageRequest imageRequest;
        final ImageRequest.Callback callback;
        DownloaderContext downloaderContextRemovePendingRequest = removePendingRequest(requestKey);
        if (downloaderContextRemovePendingRequest != null && !downloaderContextRemovePendingRequest.isCancelled && (callback = (imageRequest = downloaderContextRemovePendingRequest.request).getCallback()) != null) {
            getHandler().post(new Runnable() { // from class: com.facebook.internal.ImageDownloader.1
                @Override // java.lang.Runnable
                public void run() {
                    callback.onCompleted(new ImageResponse(imageRequest, exc, z, bitmap));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void readFromCache(RequestKey requestKey, Context context, boolean z) {
        boolean z2;
        InputStream cachedImageStream;
        URI redirectedUri;
        if (!z || (redirectedUri = UrlRedirectCache.getRedirectedUri(context, requestKey.uri)) == null) {
            z2 = false;
            cachedImageStream = null;
        } else {
            InputStream cachedImageStream2 = ImageResponseCache.getCachedImageStream(redirectedUri, context);
            cachedImageStream = cachedImageStream2;
            z2 = cachedImageStream2 != null;
        }
        if (!z2) {
            cachedImageStream = ImageResponseCache.getCachedImageStream(requestKey.uri, context);
        }
        if (cachedImageStream != null) {
            Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(cachedImageStream);
            Utility.closeQuietly(cachedImageStream);
            issueResponse(requestKey, null, bitmapDecodeStream, z2);
        } else {
            DownloaderContext downloaderContextRemovePendingRequest = removePendingRequest(requestKey);
            if (downloaderContextRemovePendingRequest != null && !downloaderContextRemovePendingRequest.isCancelled) {
                enqueueDownload(downloaderContextRemovePendingRequest.request, requestKey);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:54:0x00de  */
    /* JADX WARN: Failed to find 'out' block for switch in B:6:0x001c. Please report as an issue. */
    /* JADX WARN: Not initialized variable reg: 5, insn: 0x00b8: MOVE (r3 I:??[OBJECT, ARRAY]) = (r5 I:??[OBJECT, ARRAY]), block:B:41:0x00b8 */
    public static void download(RequestKey requestKey, Context context) throws Throwable {
        HttpURLConnection httpURLConnection;
        HttpURLConnection httpURLConnection2;
        HttpURLConnection httpURLConnection3;
        Closeable closeable;
        FacebookException facebookException;
        InputStream inputStreamInterceptAndCacheImageStream;
        Closeable closeable2 = null;
        bitmapDecodeStream = null;
        bitmapDecodeStream = null;
        bitmapDecodeStream = null;
        closeable2 = null;
        bitmapDecodeStream = null;
        Bitmap bitmapDecodeStream = null;
        boolean z = true;
        try {
            HttpURLConnection httpURLConnection4 = (HttpURLConnection) new URL(requestKey.uri.toString()).openConnection();
            try {
                try {
                    httpURLConnection4.setInstanceFollowRedirects(false);
                    try {
                        switch (httpURLConnection4.getResponseCode()) {
                            case 200:
                                inputStreamInterceptAndCacheImageStream = ImageResponseCache.interceptAndCacheImageStream(context, httpURLConnection4);
                                facebookException = null;
                                bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamInterceptAndCacheImageStream);
                                Utility.closeQuietly(inputStreamInterceptAndCacheImageStream);
                                Utility.disconnectQuietly(httpURLConnection4);
                                e = facebookException;
                                break;
                            case 301:
                            case 302:
                                try {
                                    String headerField = httpURLConnection4.getHeaderField("location");
                                    if (Utility.isNullOrEmpty(headerField)) {
                                        z = false;
                                        facebookException = null;
                                        inputStreamInterceptAndCacheImageStream = null;
                                    } else {
                                        URI uri = new URI(headerField);
                                        UrlRedirectCache.cacheUriRedirect(context, requestKey.uri, uri);
                                        DownloaderContext downloaderContextRemovePendingRequest = removePendingRequest(requestKey);
                                        if (downloaderContextRemovePendingRequest == null || downloaderContextRemovePendingRequest.isCancelled) {
                                            z = false;
                                            facebookException = null;
                                            inputStreamInterceptAndCacheImageStream = null;
                                        } else {
                                            enqueueCacheRead(downloaderContextRemovePendingRequest.request, new RequestKey(uri, requestKey.tag), false);
                                            z = false;
                                            facebookException = null;
                                            inputStreamInterceptAndCacheImageStream = null;
                                        }
                                    }
                                    Utility.closeQuietly(inputStreamInterceptAndCacheImageStream);
                                    Utility.disconnectQuietly(httpURLConnection4);
                                    e = facebookException;
                                } catch (IOException e) {
                                    httpURLConnection3 = httpURLConnection4;
                                    e = e;
                                    z = false;
                                    Utility.closeQuietly(0);
                                    Utility.disconnectQuietly(httpURLConnection3);
                                } catch (URISyntaxException e2) {
                                    httpURLConnection2 = httpURLConnection4;
                                    e = e2;
                                    z = false;
                                    Utility.closeQuietly(0);
                                    Utility.disconnectQuietly(httpURLConnection2);
                                }
                                break;
                            default:
                                inputStreamInterceptAndCacheImageStream = httpURLConnection4.getErrorStream();
                                InputStreamReader inputStreamReader = new InputStreamReader(inputStreamInterceptAndCacheImageStream);
                                char[] cArr = new char[XMLChar.MASK_NCNAME];
                                StringBuilder sb = new StringBuilder();
                                while (true) {
                                    int i = inputStreamReader.read(cArr, 0, cArr.length);
                                    if (i <= 0) {
                                        Utility.closeQuietly(inputStreamReader);
                                        facebookException = new FacebookException(sb.toString());
                                        Utility.closeQuietly(inputStreamInterceptAndCacheImageStream);
                                        Utility.disconnectQuietly(httpURLConnection4);
                                        e = facebookException;
                                    } else {
                                        sb.append(cArr, 0, i);
                                    }
                                    break;
                                }
                                break;
                        }
                    } catch (IOException e3) {
                        httpURLConnection3 = httpURLConnection4;
                        e = e3;
                    } catch (URISyntaxException e4) {
                        httpURLConnection2 = httpURLConnection4;
                        e = e4;
                    } catch (Throwable th) {
                        closeable2 = closeable;
                        httpURLConnection = httpURLConnection4;
                        th = th;
                        Utility.closeQuietly(closeable2);
                        Utility.disconnectQuietly(httpURLConnection);
                        throw th;
                    }
                } catch (Throwable th2) {
                    httpURLConnection = httpURLConnection4;
                    th = th2;
                }
            } catch (IOException e5) {
                httpURLConnection3 = httpURLConnection4;
                e = e5;
            } catch (URISyntaxException e6) {
                httpURLConnection2 = httpURLConnection4;
                e = e6;
            }
        } catch (IOException e7) {
            e = e7;
            httpURLConnection3 = null;
        } catch (URISyntaxException e8) {
            e = e8;
            httpURLConnection2 = null;
        } catch (Throwable th3) {
            th = th3;
            httpURLConnection = null;
        }
        if (z) {
            issueResponse(requestKey, e, bitmapDecodeStream, false);
        }
    }

    private static synchronized Handler getHandler() {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        return handler;
    }

    private static DownloaderContext removePendingRequest(RequestKey requestKey) {
        DownloaderContext downloaderContextRemove;
        synchronized (pendingRequests) {
            downloaderContextRemove = pendingRequests.remove(requestKey);
        }
        return downloaderContextRemove;
    }

    class RequestKey {
        private static final int HASH_MULTIPLIER = 37;
        private static final int HASH_SEED = 29;
        Object tag;
        URI uri;

        RequestKey(URI uri, Object obj) {
            this.uri = uri;
            this.tag = obj;
        }

        public int hashCode() {
            return ((this.uri.hashCode() + 1073) * HASH_MULTIPLIER) + this.tag.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof RequestKey)) {
                return false;
            }
            RequestKey requestKey = (RequestKey) obj;
            return requestKey.uri == this.uri && requestKey.tag == this.tag;
        }
    }

    class DownloaderContext {
        boolean isCancelled;
        ImageRequest request;
        WorkQueue.WorkItem workItem;

        private DownloaderContext() {
        }

        /* synthetic */ DownloaderContext(DownloaderContext downloaderContext) {
            this();
        }
    }

    class CacheReadWorkItem implements Runnable {
        private boolean allowCachedRedirects;
        private Context context;
        private RequestKey key;

        CacheReadWorkItem(Context context, RequestKey requestKey, boolean z) {
            this.context = context;
            this.key = requestKey;
            this.allowCachedRedirects = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            ImageDownloader.readFromCache(this.key, this.context, this.allowCachedRedirects);
        }
    }

    class DownloadImageWorkItem implements Runnable {
        private Context context;
        private RequestKey key;

        DownloadImageWorkItem(Context context, RequestKey requestKey) {
            this.context = context;
            this.key = requestKey;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            ImageDownloader.download(this.key, this.context);
        }
    }
}
