package com.heyzap.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.util.EntityUtils;

/* JADX INFO: compiled from: AsyncHttpResponseHandler.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Handler f739a;

    public d() {
        if (Looper.myLooper() != null) {
            this.f739a = new Handler() { // from class: com.heyzap.a.d.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    d.this.a(message);
                }
            };
        }
    }

    public void a() {
    }

    public void b() {
    }

    public void a(String str) {
    }

    public void a(Throwable th) {
    }

    public void a(Throwable th, String str) {
        a(th);
    }

    protected void b(String str) {
        b(a(0, str));
    }

    protected void b(Throwable th, String str) {
        b(a(1, new Object[]{th, str}));
    }

    protected void c() {
        b(a(2, (Object) null));
    }

    protected void d() {
        b(a(3, (Object) null));
    }

    protected void c(String str) {
        a(str);
    }

    protected void c(Throwable th, String str) {
        a(th, str);
    }

    protected void a(Message message) {
        switch (message.what) {
            case 0:
                c((String) message.obj);
                break;
            case 1:
                Object[] objArr = (Object[]) message.obj;
                c((Throwable) objArr[0], (String) objArr[1]);
                break;
            case 2:
                a();
                break;
            case 3:
                b();
                break;
        }
    }

    protected void b(Message message) {
        if (this.f739a != null) {
            this.f739a.sendMessage(message);
        } else {
            a(message);
        }
    }

    protected Message a(int i, Object obj) {
        if (this.f739a != null) {
            return this.f739a.obtainMessage(i, obj);
        }
        Message message = new Message();
        message.what = i;
        message.obj = obj;
        return message;
    }

    void a(HttpResponse httpResponse) {
        String string = null;
        StatusLine statusLine = httpResponse.getStatusLine();
        try {
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                string = EntityUtils.toString(new BufferedHttpEntity(entity), XMLStreamWriterImpl.UTF_8);
            }
        } catch (IOException e) {
            b(e, null);
        }
        if (statusLine.getStatusCode() >= 300) {
            b(new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase()), string);
        } else {
            b(string);
        }
    }
}
