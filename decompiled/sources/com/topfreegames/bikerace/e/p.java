package com.topfreegames.bikerace.e;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/* JADX INFO: compiled from: GenericDialogRemoteImageContent.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class p extends a {
    public p(Context context, final String str) {
        super(context, 2131492932);
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        if (str == null) {
            throw new IllegalArgumentException("Link cannot be null!");
        }
        View viewInflate = ((LayoutInflater) context.getApplicationContext().getSystemService("layout_inflater")).inflate(2130903065, (ViewGroup) null);
        a(context.getApplicationContext(), viewInflate);
        final ImageView imageView = (ImageView) viewInflate.findViewById(2131296375);
        final View viewFindViewById = viewInflate.findViewById(2131296376);
        new Thread(new Runnable() { // from class: com.topfreegames.bikerace.e.p.1
            @Override // java.lang.Runnable
            public void run() {
                final byte[] bArrB = p.b(str);
                if (bArrB != null) {
                    ImageView imageView2 = imageView;
                    final View view = viewFindViewById;
                    final ImageView imageView3 = imageView;
                    imageView2.post(new Runnable() { // from class: com.topfreegames.bikerace.e.p.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                view.setVisibility(8);
                                imageView3.setImageBitmap(BitmapFactory.decodeByteArray(bArrB, 0, bArrB.length));
                            } catch (Exception e) {
                            }
                        }
                    });
                }
            }
        }).start();
        viewInflate.findViewById(2131296377).setOnClickListener(new View.OnClickListener() { // from class: com.topfreegames.bikerace.e.p.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                p.this.cancel();
            }
        });
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setContentView(viewInflate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] b(String str) {
        try {
            InputStream inputStreamC = c(str);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[4096];
            while (true) {
                int i = inputStreamC.read(bArr);
                if (i >= 0) {
                    byteArrayOutputStream.write(bArr, 0, i);
                } else {
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static InputStream c(String str) throws ProtocolException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setReadTimeout(10000);
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setDoInput(true);
        httpURLConnection.connect();
        return httpURLConnection.getInputStream();
    }
}
