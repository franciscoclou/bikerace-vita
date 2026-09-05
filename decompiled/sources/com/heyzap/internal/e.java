package com.heyzap.internal;

import android.content.Context;
import android.os.AsyncTask;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: compiled from: DownloadTask.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class e extends AsyncTask<String, Integer, String> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public f f755a;
    public long b;
    public URL c;
    private Context d;

    public e(Context context, f fVar) {
        this.d = context;
        this.f755a = fVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code duplicated, block: B:127:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:130:? A[Catch: Exception -> 0x018b, all -> 0x01e3, SYNTHETIC, TRY_LEAVE, TryCatch #5 {Exception -> 0x018b, blocks: (B:15:0x009a, B:17:0x009f, B:19:0x00a4, B:79:0x01e7, B:81:0x01ec, B:83:0x01f1, B:49:0x017c, B:51:0x0181, B:53:0x0186, B:70:0x01d5, B:72:0x01da, B:74:0x01df, B:75:0x01e2), top: B:108:0x0010, outer: #2 }] */
    /* JADX WARN: Code duplicated, block: B:17:0x009f A[Catch: Exception -> 0x018b, all -> 0x01e3, IOException -> 0x0221, TRY_LEAVE, TryCatch #5 {Exception -> 0x018b, blocks: (B:15:0x009a, B:17:0x009f, B:19:0x00a4, B:79:0x01e7, B:81:0x01ec, B:83:0x01f1, B:49:0x017c, B:51:0x0181, B:53:0x0186, B:70:0x01d5, B:72:0x01da, B:74:0x01df, B:75:0x01e2), top: B:108:0x0010, outer: #2 }] */
    /* JADX WARN: Code duplicated, block: B:51:0x0181 A[Catch: Exception -> 0x018b, all -> 0x01e3, IOException -> 0x0208, TRY_LEAVE, TryCatch #5 {Exception -> 0x018b, blocks: (B:15:0x009a, B:17:0x009f, B:19:0x00a4, B:79:0x01e7, B:81:0x01ec, B:83:0x01f1, B:49:0x017c, B:51:0x0181, B:53:0x0186, B:70:0x01d5, B:72:0x01da, B:74:0x01df, B:75:0x01e2), top: B:108:0x0010, outer: #2 }] */
    /* JADX WARN: Code duplicated, block: B:53:0x0186 A[Catch: Exception -> 0x018b, all -> 0x01e3, TRY_ENTER, TRY_LEAVE, TryCatch #5 {Exception -> 0x018b, blocks: (B:15:0x009a, B:17:0x009f, B:19:0x00a4, B:79:0x01e7, B:81:0x01ec, B:83:0x01f1, B:49:0x017c, B:51:0x0181, B:53:0x0186, B:70:0x01d5, B:72:0x01da, B:74:0x01df, B:75:0x01e2), top: B:108:0x0010, outer: #2 }] */
    /* JADX WARN: Code duplicated, block: B:72:0x01da A[Catch: Exception -> 0x018b, all -> 0x01e3, IOException -> 0x01f5, TRY_LEAVE, TryCatch #5 {Exception -> 0x018b, blocks: (B:15:0x009a, B:17:0x009f, B:19:0x00a4, B:79:0x01e7, B:81:0x01ec, B:83:0x01f1, B:49:0x017c, B:51:0x0181, B:53:0x0186, B:70:0x01d5, B:72:0x01da, B:74:0x01df, B:75:0x01e2), top: B:108:0x0010, outer: #2 }] */
    /* JADX WARN: Code duplicated, block: B:74:0x01df A[Catch: Exception -> 0x018b, all -> 0x01e3, TRY_ENTER, TryCatch #5 {Exception -> 0x018b, blocks: (B:15:0x009a, B:17:0x009f, B:19:0x00a4, B:79:0x01e7, B:81:0x01ec, B:83:0x01f1, B:49:0x017c, B:51:0x0181, B:53:0x0186, B:70:0x01d5, B:72:0x01da, B:74:0x01df, B:75:0x01e2), top: B:108:0x0010, outer: #2 }] */
    /* JADX WARN: Code duplicated, block: B:81:0x01ec A[Catch: Exception -> 0x018b, all -> 0x01e3, IOException -> 0x021f, TRY_LEAVE, TryCatch #5 {Exception -> 0x018b, blocks: (B:15:0x009a, B:17:0x009f, B:19:0x00a4, B:79:0x01e7, B:81:0x01ec, B:83:0x01f1, B:49:0x017c, B:51:0x0181, B:53:0x0186, B:70:0x01d5, B:72:0x01da, B:74:0x01df, B:75:0x01e2), top: B:108:0x0010, outer: #2 }] */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String doInBackground(String... strArr) {
        if (strArr.length == 0) {
            return null;
        }
        String str = strArr[0];
        String str2 = strArr[1];
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                try {
                    this.c = new URL(str);
                    HttpURLConnection httpURLConnection2 = (HttpURLConnection) this.c.openConnection();
                    try {
                        httpURLConnection2.connect();
                        this.b = System.currentTimeMillis();
                        if (httpURLConnection2.getResponseCode() != 200) {
                            if (this.f755a != null) {
                                this.f755a.a(this, this.c, new Throwable("HTTP " + httpURLConnection2.getResponseCode() + " " + httpURLConnection2.getResponseMessage()));
                            }
                            String str3 = "Server returned HTTP " + httpURLConnection2.getResponseCode() + " " + httpURLConnection2.getResponseMessage();
                            if (0 != 0) {
                                try {
                                    fileOutputStream.close();
                                    if (0 != 0) {
                                        inputStream.close();
                                    }
                                } catch (IOException e) {
                                }
                            } else if (0 != 0) {
                                inputStream.close();
                            }
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                            return str3;
                        }
                        if (this.f755a != null) {
                            this.f755a.a(this.c);
                        }
                        int contentLength = httpURLConnection2.getContentLength();
                        InputStream inputStream2 = httpURLConnection2.getInputStream();
                        try {
                            File file = new File(this.d.getCacheDir() + "/" + str2);
                            if (file.exists()) {
                                file.delete();
                                file = new File(this.d.getCacheDir() + "/" + str2);
                            }
                            FileOutputStream fileOutputStream2 = new FileOutputStream(file, false);
                            try {
                                byte[] bArr = new byte[4096];
                                long j = 0;
                                while (true) {
                                    int i = inputStream2.read(bArr);
                                    if (i == -1) {
                                        if (fileOutputStream2 != null) {
                                            try {
                                                fileOutputStream2.close();
                                                if (inputStream2 != null) {
                                                    inputStream2.close();
                                                }
                                            } catch (IOException e2) {
                                            }
                                        } else if (inputStream2 != null) {
                                            inputStream2.close();
                                        }
                                        if (httpURLConnection2 == null) {
                                            break;
                                        }
                                        httpURLConnection2.disconnect();
                                        break;
                                    }
                                    if (isCancelled()) {
                                        if (this.f755a != null) {
                                            this.f755a.b(this.c);
                                        }
                                        inputStream2.close();
                                        fileOutputStream2.close();
                                        throw new Exception("cancelled");
                                    }
                                    j += (long) i;
                                    if (contentLength > 0) {
                                        publishProgress(Integer.valueOf((int) ((100 * j) / ((long) contentLength))));
                                    }
                                    fileOutputStream2.write(bArr, 0, i);
                                }
                                if (this.f755a != null) {
                                    this.f755a.a(this.c, str2, System.currentTimeMillis() - this.b);
                                }
                                return null;
                            } catch (Exception e3) {
                                fileOutputStream = fileOutputStream2;
                                inputStream = inputStream2;
                                httpURLConnection = httpURLConnection2;
                                e = e3;
                                if (!e.getMessage().equals("cancelled")) {
                                    e.printStackTrace();
                                    if (this.f755a != null) {
                                        this.f755a.a(this, this.c, e);
                                    }
                                }
                                String string = e.toString();
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                        if (inputStream != null) {
                                            inputStream.close();
                                        }
                                    } catch (IOException e4) {
                                        if (httpURLConnection != null) {
                                            return string;
                                        }
                                        httpURLConnection.disconnect();
                                        return string;
                                    }
                                } else if (inputStream != null) {
                                    inputStream.close();
                                }
                                if (httpURLConnection != null) {
                                    return string;
                                }
                                httpURLConnection.disconnect();
                                return string;
                            } catch (Throwable th) {
                                fileOutputStream = fileOutputStream2;
                                inputStream = inputStream2;
                                httpURLConnection = httpURLConnection2;
                                th = th;
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                        if (inputStream != null) {
                                            inputStream.close();
                                        }
                                    } catch (IOException e5) {
                                        if (httpURLConnection != null) {
                                            throw th;
                                        }
                                        httpURLConnection.disconnect();
                                        throw th;
                                    }
                                } else if (inputStream != null) {
                                    inputStream.close();
                                }
                                if (httpURLConnection != null) {
                                    throw th;
                                }
                                httpURLConnection.disconnect();
                                throw th;
                            }
                        } catch (Exception e6) {
                            inputStream = inputStream2;
                            e = e6;
                            httpURLConnection = httpURLConnection2;
                        } catch (Throwable th2) {
                            inputStream = inputStream2;
                            th = th2;
                            httpURLConnection = httpURLConnection2;
                        }
                    } catch (Exception e7) {
                        httpURLConnection = httpURLConnection2;
                        e = e7;
                    } catch (Throwable th3) {
                        httpURLConnection = httpURLConnection2;
                        th = th3;
                    }
                } catch (Exception e8) {
                    e = e8;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onProgressUpdate(Integer... numArr) {
        if (this.f755a != null) {
            this.f755a.a(numArr[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(String str) {
    }

    @Override // android.os.AsyncTask
    protected void onCancelled() {
        if (this.f755a != null) {
            this.f755a.b(this.c);
        }
    }
}
