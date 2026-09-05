package com.google.ads.util;

import android.annotation.TargetApi;
import android.view.View;
import android.webkit.WebChromeClient;
import com.amazonaws.javax.xml.stream.XMLStreamConstants;
import com.google.ads.AdSize;
import com.google.ads.internal.AdWebView;
import com.google.ads.n;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
@TargetApi(XMLStreamConstants.NOTATION_DECLARATION)
public class IcsUtil {

    public class a extends g.a {
        public a(n nVar) {
            super(nVar);
        }

        @Override // android.webkit.WebChromeClient
        public void onShowCustomView(View view, int i, WebChromeClient.CustomViewCallback customViewCallback) {
            customViewCallback.onCustomViewHidden();
        }
    }

    public class IcsAdWebView extends AdWebView {
        public IcsAdWebView(n nVar, AdSize adSize) {
            super(nVar, adSize);
        }

        @Override // android.view.View
        public boolean canScrollHorizontally(int i) {
            if (this.f666a.e.a() != null) {
                return !this.f666a.e.a().b();
            }
            return super.canScrollHorizontally(i);
        }

        @Override // android.view.View
        public boolean canScrollVertically(int i) {
            if (this.f666a.e.a() != null) {
                return !this.f666a.e.a().b();
            }
            return super.canScrollVertically(i);
        }
    }
}
