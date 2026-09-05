package com.topfreegames.e;

import android.content.Context;
import android.util.Log;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.engine.data.DataNode;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

/* JADX INFO: compiled from: TopFacebookRandomAppUserRequestManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class k {
    private static k b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.amazonaws.services.sqs.b f1547a;
    private Context c;
    private Integer e;
    private List<String> h;
    private j i;
    private String d = a.b();
    private int g = 0;
    private Calendar f = null;

    public static k a(Context context) {
        if (b == null) {
            b = new k(context);
        }
        return b;
    }

    private k(Context context) {
        this.e = 0;
        this.c = context;
        this.e = 0;
        c();
    }

    private void a() {
        if (this.f1547a == null) {
            this.f1547a = ((BikeRaceApplication) this.c.getApplicationContext()).c().c().sqs();
        }
    }

    public void a(List<String> list, String str, j jVar) {
        a();
        this.i = jVar;
        this.h = null;
        if (list != null) {
            this.h = new Vector(list);
        }
        this.g = 0;
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        if (this.f == null || gregorianCalendar.get(6) > this.f.get(6) || gregorianCalendar.get(1) > this.f.get(1)) {
            this.f = gregorianCalendar;
            this.e = 0;
        }
        if (this.e.intValue() < 5) {
            a(str, this.d);
            a(this.d);
        } else if (this.e.intValue() < 6) {
            this.e = Integer.valueOf(this.e.intValue() + 1);
            a(str, this.d);
            a((String) null, i.EXCEEDED_MAX_DAILY_REQUESTS);
        } else {
            a((String) null, i.EXCEEDED_MAX_DAILY_REQUESTS);
        }
        b();
    }

    private void a(String str) {
        a();
        this.g++;
        final ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(str);
        new Thread(new Runnable() { // from class: com.topfreegames.e.k.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    try {
                        List<Message> messages = k.this.f1547a.a(receiveMessageRequest).get().getMessages();
                        k.this.a(messages.size() > 0 ? messages.get(0) : null);
                    } catch (Exception e) {
                        Log.d("requestRandomAppUserIdFromQueue", "Exception occurred while requesting message: " + e.toString());
                        k.this.a(e);
                        k.this.a((Message) null);
                    }
                } catch (Throwable th) {
                    k.this.a((Message) null);
                    throw th;
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Message message) {
        String body;
        boolean z = false;
        if (message != null) {
            body = message.getBody();
            if (this.h != null) {
                if (!this.h.contains(body)) {
                    z = true;
                }
            } else if (body != null) {
                z = true;
            }
            if (z) {
                a(message, this.d);
            }
        } else {
            body = null;
        }
        if (z) {
            a(body, i.SUCCESSED);
        } else if (this.g < 5) {
            a(this.d);
        } else {
            a((String) null, i.FAILED);
        }
    }

    private void a(Message message, String str) {
        a();
        if (message != null) {
            try {
                final DeleteMessageRequest deleteMessageRequest = new DeleteMessageRequest(str, message.getReceiptHandle());
                new Thread(new Runnable() { // from class: com.topfreegames.e.k.2
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            k.this.f1547a.a(deleteMessageRequest);
                        } catch (com.amazonaws.b e) {
                            ((BikeRaceApplication) k.this.c.getApplicationContext()).d().a("deleteMessage", e);
                        } catch (com.amazonaws.a e2) {
                            ((BikeRaceApplication) k.this.c.getApplicationContext()).d().a("deleteMessage", e2);
                        }
                    }
                }).start();
            } catch (Exception e) {
                Log.d("deleteMessage", "Exception occurred while deleting message: " + e.toString());
            }
        }
    }

    private void a(String str, String str2) {
        a();
        if (str2 != null && str != null) {
            try {
                if (this.f1547a != null) {
                    final SendMessageRequest sendMessageRequest = new SendMessageRequest(str2, str);
                    new Thread(new Runnable() { // from class: com.topfreegames.e.k.3
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                k.this.f1547a.a(sendMessageRequest);
                            } catch (com.amazonaws.b e) {
                                Log.d("sendToRandom", "Exception ocurred while sending user to random queue: " + e.toString());
                                ((BikeRaceApplication) k.this.c.getApplicationContext()).d().a("sendUserIdToQueue", e);
                            } catch (com.amazonaws.a e2) {
                                Log.d("sendToRandom", "Exception ocurred while sending user to random queue: " + e2.toString());
                                ((BikeRaceApplication) k.this.c.getApplicationContext()).d().a("sendUserIdToQueue", e2);
                            }
                        }
                    }).start();
                }
            } catch (Exception e) {
                Log.d("sendUserIdToRandomAppUsersQueue", "Exception occurred while sending user id to random app users queue: " + e.toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Exception exc) {
        try {
            if (this.c != null && exc != null) {
                String string = exc.getClass().toString();
                String string2 = "";
                String string3 = "";
                String string4 = "";
                Throwable cause = exc.getCause();
                if (cause != null) {
                    string2 = cause.getClass().toString();
                    Throwable cause2 = cause.getCause();
                    if (cause2 != null) {
                        string3 = cause2.getClass().toString();
                        Throwable cause3 = cause2.getCause();
                        if (cause3 != null) {
                            string4 = cause3.getClass().toString();
                        }
                    }
                }
                ((BikeRaceApplication) this.c).d().a(exc, string, string2, string3, string4);
            }
        } catch (Exception e) {
            Log.d("sendRandomGameCreationExceptionToAnalytics", "Exception occurred while sending to analytics");
        }
    }

    /* JADX WARN: Code duplicated, block: B:62:0x0082 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    private synchronized void b() {
        ObjectOutputStream objectOutputStream;
        boolean z = true;
        ObjectOutputStream objectOutputStream2 = null;
        try {
            DataNode dataNode = new DataNode("rootData");
            synchronized (this.f) {
                dataNode.putObject("timeWhenLastPeriodStarted", this.f);
            }
            synchronized (this.e) {
                dataNode.putInteger("numberOfRequisitionsMade", this.e);
            }
            objectOutputStream = new ObjectOutputStream(this.c.openFileOutput("RandomAooUsersFile.temp", 0));
            try {
                try {
                    objectOutputStream.writeObject(dataNode);
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e) {
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                    Log.d("persistInformation", "Exception occurred while persisting information" + e.toString());
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                            z = false;
                        } catch (IOException e3) {
                            z = false;
                        }
                    } else {
                        z = false;
                    }
                }
            } catch (Throwable th) {
                th = th;
                objectOutputStream2 = objectOutputStream;
                if (objectOutputStream2 != null) {
                    try {
                        objectOutputStream2.close();
                    } catch (IOException e4) {
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            e = e5;
            objectOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            if (objectOutputStream2 != null) {
                objectOutputStream2.close();
            }
            throw th;
        }
        if (z) {
            try {
                new File(this.c.getFilesDir(), "RandomAooUsersFile.temp").renameTo(new File(this.c.getFilesDir(), "RandomAppUsersFile.dat"));
            } catch (Exception e6) {
                Log.d("persistInformation", "Exception occurred while persisting information" + e6.toString());
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:47:0x0057 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    private synchronized void c() {
        ObjectInputStream objectInputStream;
        DataNode dataNode = null;
        synchronized (this) {
            try {
                objectInputStream = new ObjectInputStream(this.c.openFileInput("RandomAppUsersFile.dat"));
                try {
                    try {
                        DataNode dataNode2 = (DataNode) objectInputStream.readObject();
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                                dataNode = dataNode2;
                            } catch (IOException e) {
                                dataNode = dataNode2;
                            }
                        } else {
                            dataNode = dataNode2;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        Log.d("getPersistedInformation", "Exception occurred while getting persisted information: " + e.toString());
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e3) {
                            }
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e4) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
                objectInputStream = null;
            } catch (Throwable th2) {
                th = th2;
                objectInputStream = null;
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                throw th;
            }
            try {
                if (dataNode != null) {
                    this.f = (Calendar) dataNode.getObject("timeWhenLastPeriodStarted");
                    this.e = dataNode.getInteger("numberOfRequisitionsMade");
                } else {
                    this.f = null;
                    this.e = 0;
                }
            } catch (Exception e6) {
                Log.d("getPersistedInformation", "Exception occurred while getting persisted information: " + e6.toString());
            }
        }
    }

    private void a(String str, i iVar) {
        if (str != null) {
            this.e = Integer.valueOf(this.e.intValue() + 1);
        }
        if (this.i != null) {
            this.i.a(str, iVar);
        }
    }
}
