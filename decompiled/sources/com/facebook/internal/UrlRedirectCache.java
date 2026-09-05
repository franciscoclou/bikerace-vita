package com.facebook.internal;

import android.content.Context;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import com.facebook.LoggingBehavior;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class UrlRedirectCache {
    private static volatile FileLruCache urlRedirectCache;
    static final String TAG = UrlRedirectCache.class.getSimpleName();
    private static final String REDIRECT_CONTENT_TAG = String.valueOf(TAG) + "_Redirect";

    UrlRedirectCache() {
    }

    static synchronized FileLruCache getCache(Context context) {
        if (urlRedirectCache == null) {
            urlRedirectCache = new FileLruCache(context.getApplicationContext(), TAG, new FileLruCache.Limits());
        }
        return urlRedirectCache;
    }

    static URI getRedirectedUri(Context context, URI uri) throws Throwable {
        InputStreamReader inputStreamReader;
        Throwable th;
        boolean z = false;
        if (uri == null) {
            return null;
        }
        String string = uri.toString();
        try {
            FileLruCache cache = getCache(context);
            String string2 = string;
            InputStreamReader inputStreamReader2 = null;
            while (true) {
                try {
                    InputStream inputStream = cache.get(string2, REDIRECT_CONTENT_TAG);
                    if (inputStream == null) {
                        break;
                    }
                    inputStreamReader = new InputStreamReader(inputStream);
                    try {
                        char[] cArr = new char[XMLChar.MASK_NCNAME];
                        StringBuilder sb = new StringBuilder();
                        while (true) {
                            int i = inputStreamReader.read(cArr, 0, cArr.length);
                            if (i <= 0) {
                                break;
                            }
                            sb.append(cArr, 0, i);
                        }
                        Utility.closeQuietly(inputStreamReader);
                        string2 = sb.toString();
                        inputStreamReader2 = inputStreamReader;
                        z = true;
                    } catch (IOException e) {
                    } catch (URISyntaxException e2) {
                        Utility.closeQuietly(inputStreamReader);
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        Utility.closeQuietly(inputStreamReader);
                        throw th;
                    }
                } catch (IOException e3) {
                    inputStreamReader = inputStreamReader2;
                } catch (URISyntaxException e4) {
                    inputStreamReader = inputStreamReader2;
                } catch (Throwable th3) {
                    th = th3;
                    inputStreamReader = inputStreamReader2;
                }
                Utility.closeQuietly(inputStreamReader);
                return null;
            }
            if (!z) {
                Utility.closeQuietly(inputStreamReader2);
                return null;
            }
            URI uri2 = new URI(string2);
            Utility.closeQuietly(inputStreamReader2);
            return uri2;
        } catch (IOException e5) {
            inputStreamReader = null;
        } catch (URISyntaxException e6) {
            inputStreamReader = null;
        } catch (Throwable th4) {
            inputStreamReader = null;
            th = th4;
        }
        Utility.closeQuietly(inputStreamReader);
        return null;
    }

    static void cacheUriRedirect(Context context, URI uri, URI uri2) throws Throwable {
        OutputStream outputStream;
        Throwable th;
        if (uri != null && uri2 != null) {
            try {
                try {
                    OutputStream outputStreamOpenPutStream = getCache(context).openPutStream(uri.toString(), REDIRECT_CONTENT_TAG);
                    try {
                        outputStreamOpenPutStream.write(uri2.toString().getBytes());
                        Utility.closeQuietly(outputStreamOpenPutStream);
                    } catch (Throwable th2) {
                        outputStream = outputStreamOpenPutStream;
                        th = th2;
                        Utility.closeQuietly(outputStream);
                        throw th;
                    }
                } catch (IOException e) {
                    Utility.closeQuietly(null);
                }
            } catch (Throwable th3) {
                outputStream = null;
                th = th3;
            }
        }
    }

    static void clearCache(Context context) {
        try {
            getCache(context).clearCache();
        } catch (IOException e) {
            Logger.log(LoggingBehavior.CACHE, 5, TAG, "clearCache failed " + e.getMessage());
        }
    }
}
