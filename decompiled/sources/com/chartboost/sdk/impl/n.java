package com.chartboost.sdk.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.chartboost.sdk.Chartboost;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a f473a;

    public interface a {
        void a(boolean z, String str);
    }

    public n(a aVar) {
        this.f473a = aVar;
    }

    public void a(final String str, final Activity activity) {
        try {
            String scheme = new URI(str).getScheme();
            if (scheme == null) {
                if (this.f473a != null) {
                    this.f473a.a(false, str);
                }
            } else if (!scheme.equals("http") && !scheme.equals("https")) {
                a(str, (Context) activity);
            } else {
                l.a().execute(new Runnable() { // from class: com.chartboost.sdk.impl.n.1
                    @Override // java.lang.Runnable
                    public void run() throws Throwable {
                        String str2;
                        HttpURLConnection httpURLConnection;
                        String str3 = str;
                        if (m.a()) {
                            HttpURLConnection httpURLConnection2 = null;
                            try {
                                Chartboost chartboostSharedChartboost = Chartboost.sharedChartboost();
                                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                                try {
                                    httpURLConnection.setInstanceFollowRedirects(false);
                                    httpURLConnection.setConnectTimeout(chartboostSharedChartboost.getTimeout());
                                    httpURLConnection.setReadTimeout(chartboostSharedChartboost.getTimeout());
                                    String headerField = httpURLConnection.getHeaderField("Location");
                                    if (headerField != null) {
                                        str3 = headerField;
                                    }
                                    if (httpURLConnection != null) {
                                        httpURLConnection.disconnect();
                                    }
                                } catch (Exception e) {
                                    if (httpURLConnection != null) {
                                        httpURLConnection.disconnect();
                                        str2 = str3;
                                    }
                                    a(str2);
                                } catch (Throwable th) {
                                    httpURLConnection2 = httpURLConnection;
                                    th = th;
                                    if (httpURLConnection2 != null) {
                                        httpURLConnection2.disconnect();
                                    }
                                    throw th;
                                }
                            } catch (Exception e2) {
                                httpURLConnection = null;
                            } catch (Throwable th2) {
                                th = th2;
                            }
                            str2 = str3;
                        } else {
                            str2 = str3;
                        }
                        a(str2);
                    }

                    public void a(final String str2) {
                        final Activity activity2 = activity;
                        Runnable runnable = new Runnable() { // from class: com.chartboost.sdk.impl.n.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                n.this.a(str2, (Context) activity2);
                            }
                        };
                        if (activity != null) {
                            activity.runOnUiThread(runnable);
                        } else {
                            Chartboost.sharedChartboost().getHandler().post(runnable);
                        }
                    }
                });
            }
        } catch (URISyntaxException e) {
            if (this.f473a != null) {
                this.f473a.a(false, str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, Context context) {
        if (context == null) {
            context = Chartboost.sharedChartboost().getContext();
        }
        if (context == null) {
            if (this.f473a != null) {
                this.f473a.a(false, str);
                return;
            }
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
            }
            intent.setData(Uri.parse(str));
            context.startActivity(intent);
        } catch (Exception e) {
            if (str.startsWith("market://")) {
                try {
                    str = "http://market.android.com/" + str.substring(9);
                    Intent intent2 = new Intent("android.intent.action.VIEW");
                    if (!(context instanceof Activity)) {
                        intent2.addFlags(268435456);
                    }
                    intent2.setData(Uri.parse(str));
                    context.startActivity(intent2);
                } catch (Exception e2) {
                    if (this.f473a != null) {
                        this.f473a.a(false, str);
                        return;
                    }
                    return;
                }
            } else if (this.f473a != null) {
                this.f473a.a(false, str);
            }
        }
        if (this.f473a != null) {
            this.f473a.a(true, str);
        }
    }
}
