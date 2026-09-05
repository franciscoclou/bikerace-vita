package com.topfreegames.bikerace.multiplayer;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.bikerace.ap;
import com.topfreegames.bikerace.az;
import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/* JADX INFO: compiled from: LinkUrlRetriever.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class j {
    public static void a(Context context, String str, String str2) {
        a(context, str, str2, null);
    }

    public static void a(Context context, final String str, final String str2, final k kVar) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        if (str == null) {
            throw new IllegalArgumentException("Id cannot be null!");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("Name cannot be null!");
        }
        final Context applicationContext = context.getApplicationContext();
        if (((BikeRaceApplication) applicationContext).e()) {
            new Thread(new Runnable() { // from class: com.topfreegames.bikerace.multiplayer.j.1
                @Override // java.lang.Runnable
                public void run() {
                    String str3 = String.format("bikerace://newgame?id=%1$s&name=%2$s", Uri.encode(str), Uri.encode(str2));
                    SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("com.topfreegames.bikerace.link", 0);
                    String string = sharedPreferences.getString(str3, "");
                    if (string.length() <= 0 && (string = j.b(az.a(), str3)) != null) {
                        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                        editorEdit.putString(str3, string);
                        editorEdit.commit();
                    }
                    if (string != null && string.length() > 0) {
                        str3 = string;
                    }
                    if (kVar != null) {
                        kVar.a(str3);
                    }
                }
            }).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(String str, String str2) {
        Header firstHeader;
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(str);
        httpPost.setHeader("Content-Type", "application/json");
        try {
            StringEntity stringEntity = new StringEntity(String.format("{\"url\":\"%1$s\"}", str2));
            stringEntity.setContentEncoding(XMLStreamWriterImpl.UTF_8);
            httpPost.setEntity(stringEntity);
            HttpResponse httpResponseExecute = defaultHttpClient.execute(httpPost);
            if (httpResponseExecute == null || (firstHeader = httpResponseExecute.getFirstHeader("Location")) == null) {
                return null;
            }
            return Uri.decode(firstHeader.getValue());
        } catch (ClientProtocolException e) {
            if (!ap.d()) {
                return null;
            }
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            if (!ap.d()) {
                return null;
            }
            e2.printStackTrace();
            return null;
        }
    }
}
