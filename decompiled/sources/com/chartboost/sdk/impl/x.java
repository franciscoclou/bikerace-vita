package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.google.ads.AdActivity;
import java.net.URI;
import java.net.URLDecoder;
import org.json.JSONObject;
import org.json.JSONTokener;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class x extends com.chartboost.sdk.c {
    private String h;

    public class a extends com.chartboost.sdk.c.b {
        public WebView c;

        public a(Context context, String str) {
            super(context);
            setFocusable(false);
            this.c = x.this.new b(context);
            this.c.setWebViewClient(x.this.new c(x.this));
            addView(this.c);
            this.c.loadDataWithBaseURL("file:///android_asset/", str, "text/html", "utf-8", null);
        }

        @Override // com.chartboost.sdk.c.b
        protected void a(int i, int i2) {
        }
    }

    public x(com.chartboost.sdk.impl.a aVar) {
        super(aVar);
        this.h = null;
    }

    @Override // com.chartboost.sdk.c
    protected com.chartboost.sdk.c.b a(Context context) {
        return new a(context, this.h);
    }

    @Override // com.chartboost.sdk.c
    public void a(JSONObject jSONObject) {
        String strOptString = jSONObject.optString(AdActivity.HTML_PARAM);
        if (strOptString != null) {
            this.h = strOptString;
            a();
        }
    }

    class b extends WebView {
        public b(Context context) {
            super(context);
            setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            setBackgroundColor(0);
            getSettings().setJavaScriptEnabled(true);
        }

        @Override // android.webkit.WebView, android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            if ((i == 4 || i == 3) && x.this.f401a != null) {
                x.this.f401a.a();
            }
            return super.onKeyDown(i, keyEvent);
        }
    }

    class c extends WebViewClient {
        private x b;

        public c(x xVar) {
            this.b = xVar;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (this.b == null || this.b.c == null) {
                return;
            }
            this.b.c.a();
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            if (this.b.d != null) {
                this.b.d.a();
            }
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Exception e;
            String strDecode;
            JSONObject jSONObject;
            try {
                if (new URI(str).getScheme().equals("chartboost")) {
                    String[] strArrSplit = str.split("/");
                    Integer numValueOf = Integer.valueOf(strArrSplit.length);
                    if (numValueOf.intValue() < 3) {
                        if (this.b.f401a == null) {
                            return false;
                        }
                        this.b.f401a.a();
                        return false;
                    }
                    String str2 = strArrSplit[2];
                    if (str2.equals("close")) {
                        if (this.b.f401a != null) {
                            this.b.f401a.a();
                        }
                    } else if (str2.equals("link")) {
                        if (numValueOf.intValue() < 4) {
                            if (this.b.f401a == null) {
                                return false;
                            }
                            this.b.f401a.a();
                            return false;
                        }
                        try {
                            strDecode = URLDecoder.decode(strArrSplit[3], XMLStreamWriterImpl.UTF_8);
                            try {
                                jSONObject = numValueOf.intValue() > 4 ? new JSONObject(new JSONTokener(URLDecoder.decode(strArrSplit[4], XMLStreamWriterImpl.UTF_8))) : null;
                            } catch (Exception e2) {
                                e = e2;
                                e.printStackTrace();
                            }
                        } catch (Exception e3) {
                            e = e3;
                            strDecode = null;
                        }
                        if (this.b.b != null) {
                            this.b.b.a(strDecode, jSONObject);
                        }
                    }
                }
                return true;
            } catch (Exception e4) {
                if (this.b.f401a == null) {
                    return false;
                }
                this.b.f401a.a();
                return false;
            }
        }
    }
}
