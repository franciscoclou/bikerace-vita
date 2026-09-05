package com.google.ads.util;

import android.R;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.amazonaws.javax.xml.stream.XMLStreamConstants;
import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.google.ads.AdActivity;
import com.google.ads.internal.AdWebView;
import com.google.ads.m;
import com.google.ads.n;
import com.google.ads.o;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
@TargetApi(XMLStreamConstants.DTD)
public class g {

    public class b extends com.google.ads.internal.i {
        public b(com.google.ads.internal.d dVar, Map<String, o> map, boolean z, boolean z2) {
            super(dVar, map, z, z2);
        }

        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            try {
                if ("mraid.js".equalsIgnoreCase(new File(str).getName())) {
                    com.google.ads.internal.c cVarK = this.f681a.k();
                    if (cVarK != null) {
                        cVarK.c(true);
                    } else {
                        this.f681a.a(true);
                    }
                    m.a aVarA = this.f681a.i().d.a().b.a();
                    if (!this.f681a.i().b()) {
                        if (this.b) {
                            String strA = aVarA.f.a();
                            com.google.ads.util.b.a("shouldInterceptRequest(" + strA + ")");
                            return a(strA, webView.getContext());
                        }
                        String strA2 = aVarA.e.a();
                        com.google.ads.util.b.a("shouldInterceptRequest(" + strA2 + ")");
                        return a(strA2, webView.getContext());
                    }
                    String strA3 = aVarA.g.a();
                    com.google.ads.util.b.a("shouldInterceptRequest(" + strA3 + ")");
                    return a(strA3, webView.getContext());
                }
            } catch (IOException e) {
                com.google.ads.util.b.d("IOException fetching MRAID JS.", e);
            } catch (Throwable th) {
                com.google.ads.util.b.d("An unknown error occurred fetching MRAID JS.", th);
            }
            return super.shouldInterceptRequest(webView, str);
        }

        private static WebResourceResponse a(String str, Context context) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                AdUtil.a(httpURLConnection, context.getApplicationContext());
                httpURLConnection.connect();
                return new WebResourceResponse("application/javascript", XMLStreamWriterImpl.UTF_8, new ByteArrayInputStream(AdUtil.a(new InputStreamReader(httpURLConnection.getInputStream())).getBytes(XMLStreamWriterImpl.UTF_8)));
            } finally {
                httpURLConnection.disconnect();
            }
        }
    }

    public class a extends WebChromeClient {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final n f721a;

        public a(n nVar) {
            this.f721a = nVar;
        }

        @Override // android.webkit.WebChromeClient
        public void onCloseWindow(WebView webView) {
            if (webView instanceof AdWebView) {
                ((AdWebView) webView).f();
            }
        }

        @Override // android.webkit.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            String str = "JS: " + consoleMessage.message() + " (" + consoleMessage.sourceId() + ":" + consoleMessage.lineNumber() + ")";
            switch (AnonymousClass1.f720a[consoleMessage.messageLevel().ordinal()]) {
                case 1:
                    com.google.ads.util.b.b(str);
                    break;
                case 2:
                    com.google.ads.util.b.e(str);
                    break;
                case 3:
                case 4:
                    com.google.ads.util.b.c(str);
                    break;
                case 5:
                    com.google.ads.util.b.a(str);
                    break;
            }
            return super.onConsoleMessage(consoleMessage);
        }

        @Override // android.webkit.WebChromeClient
        public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
            m.a aVarA = this.f721a.d.a().b.a();
            long jLongValue = aVarA.l.a().longValue() - j3;
            if (jLongValue <= 0) {
                quotaUpdater.updateQuota(j);
                return;
            }
            if (j == 0) {
                if (j2 > jLongValue || j2 > aVarA.m.a().longValue()) {
                    j2 = 0;
                }
            } else if (j2 == 0) {
                j2 = Math.min(Math.min(aVarA.n.a().longValue(), jLongValue) + j, aVarA.m.a().longValue());
            } else {
                if (j2 <= Math.min(aVarA.m.a().longValue() - j, jLongValue)) {
                    j += j2;
                }
                j2 = j;
            }
            quotaUpdater.updateQuota(j2);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            return a(webView, str, str2, null, jsResult, null, false);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
            return a(webView, str, str2, null, jsResult, null, false);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
            return a(webView, str, str2, null, jsResult, null, false);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            return a(webView, str, str2, str3, null, jsPromptResult, true);
        }

        public void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
            m.a aVarA = this.f721a.d.a().b.a();
            long jLongValue = aVarA.k.a().longValue() - j2;
            long jLongValue2 = aVarA.j.a().longValue() + j;
            if (jLongValue < jLongValue2) {
                quotaUpdater.updateQuota(0L);
            } else {
                quotaUpdater.updateQuota(jLongValue2);
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
            customViewCallback.onCustomViewHidden();
        }

        private static boolean a(WebView webView, String str, String str2, String str3, JsResult jsResult, JsPromptResult jsPromptResult, boolean z) {
            AdActivity adActivityI;
            if ((webView instanceof AdWebView) && (adActivityI = ((AdWebView) webView).i()) != null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(adActivityI);
                builder.setTitle(str);
                if (z) {
                    a(builder, adActivityI, str2, str3, jsPromptResult);
                } else {
                    a(builder, str2, jsResult);
                }
                return true;
            }
            return false;
        }

        private static void a(AlertDialog.Builder builder, String str, final JsResult jsResult) {
            builder.setMessage(str).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.google.ads.util.g.a.3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    jsResult.confirm();
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.google.ads.util.g.a.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    jsResult.cancel();
                }
            }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.google.ads.util.g.a.1
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    jsResult.cancel();
                }
            }).create().show();
        }

        private static void a(AlertDialog.Builder builder, Context context, String str, String str2, final JsPromptResult jsPromptResult) {
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(1);
            TextView textView = new TextView(context);
            textView.setText(str);
            final EditText editText = new EditText(context);
            editText.setText(str2);
            linearLayout.addView(textView);
            linearLayout.addView(editText);
            builder.setView(linearLayout).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.google.ads.util.g.a.6
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    jsPromptResult.confirm(editText.getText().toString());
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.google.ads.util.g.a.5
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    jsPromptResult.cancel();
                }
            }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.google.ads.util.g.a.4
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    jsPromptResult.cancel();
                }
            }).create().show();
        }
    }

    /* JADX INFO: renamed from: com.google.ads.util.g$1, reason: invalid class name */
    /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f720a = new int[ConsoleMessage.MessageLevel.values().length];

        static {
            try {
                f720a[ConsoleMessage.MessageLevel.ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f720a[ConsoleMessage.MessageLevel.WARNING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f720a[ConsoleMessage.MessageLevel.LOG.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f720a[ConsoleMessage.MessageLevel.TIP.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f720a[ConsoleMessage.MessageLevel.DEBUG.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public static void a(WebSettings webSettings, n nVar) {
        Context contextA = nVar.f.a();
        m.a aVarA = nVar.d.a().b.a();
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCacheMaxSize(aVarA.i.a().longValue());
        webSettings.setAppCachePath(new File(contextA.getCacheDir(), "admob").getAbsolutePath());
        webSettings.setDatabaseEnabled(true);
        webSettings.setDatabasePath(contextA.getDatabasePath("admob").getAbsolutePath());
        webSettings.setDomStorageEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
    }

    public static void a(View view) {
        view.setLayerType(1, null);
    }

    public static void b(View view) {
        view.setLayerType(0, null);
    }

    public static void a(Window window) {
        window.setFlags(16777216, 16777216);
    }
}
