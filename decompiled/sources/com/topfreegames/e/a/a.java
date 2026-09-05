package com.topfreegames.e.a;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.Session;
import com.facebook.SessionLoginBehavior;
import com.facebook.SessionState;
import com.facebook.widget.WebDialog;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.bikerace.ap;
import com.topfreegames.e.b.n;
import com.topfreegames.e.b.p;
import com.topfreegames.e.b.q;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* JADX INFO: compiled from: TopFacebookManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a implements com.topfreegames.e.b.b, com.topfreegames.e.b.g, com.topfreegames.e.b.j, com.topfreegames.e.b.m, p, com.topfreegames.e.j {
    private static /* synthetic */ int[] A;
    private static final String b = String.valueOf(com.topfreegames.e.a.a()) + "_USER_INFO";
    private static a z = null;
    private Timer d;
    private Timer e;
    private boolean g;
    private g l;
    private Context m;
    private com.topfreegames.e.h n;
    private android.support.v4.c.e<String, com.topfreegames.e.l> o;
    private String s;
    private com.topfreegames.e.k u;
    private i v;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Session.StatusCallback f1491a = new Session.StatusCallback() { // from class: com.topfreegames.e.a.a.1
        private static /* synthetic */ int[] b;

        static /* synthetic */ int[] a() {
            int[] iArr = b;
            if (iArr == null) {
                iArr = new int[b.valuesCustom().length];
                try {
                    iArr[b.IDLE.ordinal()] = 3;
                } catch (NoSuchFieldError e) {
                }
                try {
                    iArr[b.LOGGING_IN.ordinal()] = 1;
                } catch (NoSuchFieldError e2) {
                }
                try {
                    iArr[b.LOGGING_OUT.ordinal()] = 2;
                } catch (NoSuchFieldError e3) {
                }
                b = iArr;
            }
            return iArr;
        }

        @Override // com.facebook.Session.StatusCallback
        public void call(Session session, SessionState sessionState, Exception exc) {
            switch (a()[a.this.c.ordinal()]) {
                case 1:
                    if (exc == null) {
                        if (session.isOpened()) {
                            a.this.b(true, true);
                            a.this.c = b.IDLE;
                        }
                    } else {
                        a.this.b(false, true);
                        Session.setActiveSession(null);
                        a.this.c = b.IDLE;
                        if (ap.d()) {
                            exc.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    if (exc == null) {
                        if (session.isClosed()) {
                            a.this.g();
                            Session.setActiveSession(null);
                            a.this.a(true);
                            a.this.c = b.IDLE;
                        }
                    } else {
                        a.this.a(false);
                        a.this.c = b.IDLE;
                        if (ap.d()) {
                            exc.printStackTrace();
                        }
                    }
                    break;
            }
        }
    };
    private b c = b.IDLE;
    private List<f> f = new Vector();
    private Hashtable<com.topfreegames.e.b.f, j> h = new Hashtable<>();
    private Hashtable<String, List<com.topfreegames.e.b.f>> i = new Hashtable<>();
    private Hashtable<Object, List<com.topfreegames.e.b.f>> j = new Hashtable<>();
    private Hashtable<Object, List<Future<Boolean>>> k = new Hashtable<>();
    private List<String> p = new Vector();
    private List<String> q = new Vector();
    private List<String> r = new Vector();
    private ExecutorService t = Executors.newSingleThreadExecutor();
    private q w = q.b();
    private com.topfreegames.e.b.c x = com.topfreegames.e.b.c.b();
    private volatile boolean y = false;

    static /* synthetic */ int[] f() {
        int[] iArr = A;
        if (iArr == null) {
            iArr = new int[com.topfreegames.e.e.valuesCustom().length];
            try {
                iArr[com.topfreegames.e.e.ALL_FRIENDS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[com.topfreegames.e.e.FRIENDS_DONT_HAVE_APP.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[com.topfreegames.e.e.FRIENDS_HAVE_APP.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            A = iArr;
        }
        return iArr;
    }

    public static synchronized void a(Context context) {
        if (z == null) {
            synchronized (a.class) {
                z = new a(context);
            }
        }
    }

    public static synchronized a b() {
        if (z == null) {
            throw new IllegalStateException("Call init() first!");
        }
        return z;
    }

    private a(Context context) {
        this.m = context;
        this.n = com.topfreegames.e.h.a(this.m);
        this.u = com.topfreegames.e.k.a(this.m);
        this.o = new android.support.v4.c.e<String, com.topfreegames.e.l>((((ActivityManager) context.getSystemService("activity")).getMemoryClass() * 1048576) / 10) { // from class: com.topfreegames.e.a.a.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.support.v4.c.e
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public int b(String str, com.topfreegames.e.l lVar) {
                int length = str.length() * 2;
                Bitmap bitmapC = lVar.c();
                if (bitmapC != null) {
                    length += bitmapC.getHeight() * bitmapC.getRowBytes();
                }
                if (length < 0) {
                    return 0;
                }
                return length;
            }
        };
    }

    public void c() {
        synchronized (this.o) {
            this.o.a();
        }
    }

    public Session.StatusCallback d() {
        return this.f1491a;
    }

    public void a(j jVar) {
        if (jVar != null) {
            synchronized (this.h) {
                if (this.h.values().contains(jVar)) {
                    ArrayList arrayList = new ArrayList();
                    for (Map.Entry<com.topfreegames.e.b.f, j> entry : this.h.entrySet()) {
                        if (entry != null && jVar == entry.getValue()) {
                            arrayList.add(entry.getKey());
                        }
                    }
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        this.h.remove((com.topfreegames.e.b.f) it.next());
                    }
                }
            }
        }
    }

    public void a(Object obj) {
        if (obj != null) {
            synchronized (this.j) {
                List<com.topfreegames.e.b.f> listRemove = this.j.remove(obj);
                if (listRemove != null) {
                    for (com.topfreegames.e.b.f fVar : listRemove) {
                        synchronized (this.h) {
                            this.h.remove(fVar);
                        }
                        if (fVar instanceof n) {
                            n nVar = (n) fVar;
                            synchronized (this.i) {
                                this.i.remove(nVar.b());
                            }
                        }
                    }
                }
            }
            synchronized (this.k) {
                List<Future<Boolean>> listRemove2 = this.k.remove(obj);
                if (listRemove2 != null) {
                    Iterator<Future<Boolean>> it = listRemove2.iterator();
                    while (it.hasNext()) {
                        it.next().cancel(true);
                    }
                }
            }
        }
    }

    public com.topfreegames.e.l a(String str, boolean z2) {
        return a(str, z2, true);
    }

    public List<String> a(Dictionary<String, com.topfreegames.e.l> dictionary) {
        Vector vector = new Vector();
        if (dictionary != null) {
            Enumeration<String> enumerationKeys = dictionary.keys();
            ArrayList arrayList = new ArrayList();
            while (enumerationKeys.hasMoreElements()) {
                arrayList.add(dictionary.get(enumerationKeys.nextElement()));
            }
            Collections.sort(arrayList, new e(this, null));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                vector.add(((com.topfreegames.e.l) it.next()).a());
            }
        }
        return vector;
    }

    @Override // com.topfreegames.e.b.e
    public void a() {
        if (this.m != null) {
            SharedPreferences.Editor editorEdit = this.m.getSharedPreferences(b, 0).edit();
            editorEdit.remove("access_token");
            editorEdit.remove("access_expires");
            editorEdit.commit();
        }
        Session.setActiveSession(null);
        g();
        synchronized (this.j) {
            Iterator<List<com.topfreegames.e.b.f>> it = this.j.values().iterator();
            while (it.hasNext()) {
                Iterator<com.topfreegames.e.b.f> it2 = it.next().iterator();
                while (it2.hasNext()) {
                    it2.next().d();
                }
            }
        }
        e();
    }

    @Override // com.topfreegames.e.b.b
    public void a(Bitmap bitmap, String str, com.topfreegames.e.b.a aVar, boolean z2) {
        try {
            h hVar = (h) this.h.get(aVar);
            a(bitmap, str);
            a(bitmap, hVar, str);
            this.h.remove(aVar);
        } catch (Exception e) {
            Log.e("handleReceivedPicture", "An exception occurred while retrieving the user pictures: " + e.toString());
            try {
                ((BikeRaceApplication) this.m.getApplicationContext()).d().c("handleReceivedPicture", e);
            } catch (Exception e2) {
            }
        }
    }

    @Override // com.topfreegames.e.b.j
    public void a(Dictionary<String, com.topfreegames.e.l> dictionary, com.topfreegames.e.b.h hVar, boolean z2) {
        if (dictionary != null) {
            try {
                Enumeration<String> enumerationKeys = dictionary.keys();
                while (enumerationKeys.hasMoreElements()) {
                    String strNextElement = enumerationKeys.nextElement();
                    com.topfreegames.e.l lVar = dictionary.get(strNextElement);
                    a(lVar, strNextElement);
                    b(lVar, strNextElement);
                }
            } catch (Exception e) {
                Log.e("handleReceivedSeveralUsersInfo", "An exception occurred while retrieving the users info: " + e.toString());
                try {
                    ((BikeRaceApplication) this.m.getApplicationContext()).d().c("handleReceivedSeveralUsersInfo", e);
                    return;
                } catch (Exception e2) {
                    return;
                }
            }
        }
        a(dictionary, (k) this.h.get(hVar));
        this.h.remove(hVar);
        b(dictionary);
    }

    @Override // com.topfreegames.e.b.m
    public void a(Dictionary<String, com.topfreegames.e.l> dictionary, com.topfreegames.e.b.k kVar, com.topfreegames.e.e eVar, boolean z2) {
        Hashtable hashtable;
        if (dictionary == null) {
            hashtable = null;
        } else {
            try {
                Hashtable hashtable2 = new Hashtable();
                Vector vector = new Vector();
                Enumeration<String> enumerationKeys = dictionary.keys();
                while (enumerationKeys.hasMoreElements()) {
                    vector.add(enumerationKeys.nextElement());
                }
                a(eVar, vector);
                for (String str : vector) {
                    com.topfreegames.e.l lVar = dictionary.get(str);
                    a(lVar, str);
                    hashtable2.put(lVar.a(), lVar);
                }
                b(hashtable2);
                hashtable = hashtable2;
            } catch (Exception e) {
                Log.e("handleReceivedUserFriendsInfo", "An exception occurred while retrieving the user friends: " + e.toString());
                try {
                    ((BikeRaceApplication) this.m.getApplicationContext()).d().c("handleReceivedUserFriendsInfo", e);
                    return;
                } catch (Exception e2) {
                    return;
                }
            }
        }
        a(hashtable, eVar, (l) this.h.get(kVar));
        this.h.remove(kVar);
    }

    @Override // com.topfreegames.e.j
    public void a(String str, com.topfreegames.e.i iVar) {
        if (this.v != null) {
            this.v.a(str, iVar);
            this.v = null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:41:0x00c1  */
    @Override // com.topfreegames.e.b.p
    public void a(com.topfreegames.e.l lVar, n nVar, boolean z2, boolean z3) {
        String strB;
        boolean z4;
        try {
            if (lVar != null) {
                String strA = lVar.a();
                if (strA != null) {
                    a(lVar, strA);
                    if (z2) {
                        this.s = strA;
                    }
                    b(lVar, strA);
                    if (lVar.c() == null) {
                        this.x.a(new com.topfreegames.e.b.a(this, strA, 600000L));
                    }
                }
                if (z2 && this.g) {
                    this.n.b(lVar);
                }
            } else if (z2 && this.g && (lVar = this.n.a()) != null) {
                this.s = lVar.a();
                if (lVar.c() == null) {
                    this.x.a(new com.topfreegames.e.b.a(this, lVar.a(), 600000L));
                }
            }
            a(lVar, z2, (m) this.h.get(nVar));
            this.h.remove(nVar);
            if (nVar != null && (strB = nVar.b()) != null) {
                List<com.topfreegames.e.b.f> list = this.i.get(strB);
                if (list == null) {
                    z4 = true;
                } else {
                    list.remove(nVar);
                    if (list.size() <= 0) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                }
                this.i.remove(Boolean.valueOf(z4));
            }
        } catch (Exception e) {
            Log.e("handleReceivedUserInfo", "An exception occurred while retrieving the user info: " + e.toString());
            try {
                ((BikeRaceApplication) this.m.getApplicationContext()).d().c("handleReceivedUserInfo", e);
            } catch (Exception e2) {
            }
        }
    }

    public void a(Activity activity, boolean z2) {
        a(activity, 120000L, z2);
    }

    public void a(Activity activity, long j, final boolean z2) {
        if (activity != null) {
            Session activeSession = Session.getActiveSession();
            if (activeSession == null || !activeSession.isOpened() || z2) {
                this.d = new Timer();
                this.d.schedule(new TimerTask() { // from class: com.topfreegames.e.a.a.3
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        synchronized (this) {
                            try {
                                a.this.b(false, z2);
                            } catch (Exception e) {
                                if (com.topfreegames.e.a.c()) {
                                    e.printStackTrace();
                                }
                                try {
                                    ((BikeRaceApplication) a.this.m.getApplicationContext()).d().c("performLogout", e);
                                } catch (Exception e2) {
                                }
                            }
                        }
                    }
                }, j);
                if (!z2) {
                    if (activeSession == null || !activeSession.isOpened()) {
                        this.c = b.LOGGING_IN;
                        if (!a(activity) && Session.openActiveSession(activity, false, this.f1491a) == null) {
                            b(false, z2);
                            this.c = b.IDLE;
                            return;
                        }
                        return;
                    }
                    return;
                }
                Session sessionH = h();
                if (activeSession == null) {
                    if (!a(activity)) {
                        Session.OpenRequest openRequest = new Session.OpenRequest(activity);
                        openRequest.setLoginBehavior(SessionLoginBehavior.SSO_WITH_FALLBACK);
                        openRequest.setCallback(this.f1491a);
                        this.c = b.LOGGING_IN;
                        Session.setActiveSession(sessionH);
                        sessionH.openForRead(openRequest);
                        return;
                    }
                    return;
                }
                if (!activeSession.isOpened()) {
                    Session.OpenRequest openRequest2 = new Session.OpenRequest(activity);
                    openRequest2.setLoginBehavior(SessionLoginBehavior.SSO_WITH_FALLBACK);
                    openRequest2.setCallback(this.f1491a);
                    this.c = b.LOGGING_IN;
                    Session.setActiveSession(sessionH);
                    sessionH.openForRead(openRequest2);
                    return;
                }
                b(true, z2);
                return;
            }
            b(true, z2);
        }
    }

    private boolean a(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(b, 0);
        String string = sharedPreferences.getString("access_token", null);
        if (string == null) {
            return false;
        }
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putString("access_token", null);
        editorEdit.commit();
        AccessToken accessTokenCreateFromExistingAccessToken = AccessToken.createFromExistingAccessToken(string, null, null, null, null);
        this.c = b.LOGGING_IN;
        Session sessionH = h();
        Session.setActiveSession(sessionH);
        sessionH.open(accessTokenCreateFromExistingAccessToken, this.f1491a);
        return true;
    }

    public void b(Context context) {
        a(context, 60000L);
    }

    public void a(Context context, long j) {
        Session activeSession = Session.getActiveSession();
        if (activeSession != null && activeSession.isOpened()) {
            activeSession.addCallback(this.f1491a);
            this.c = b.LOGGING_OUT;
            activeSession.closeAndClearTokenInformation();
            this.e = new Timer();
            this.e.schedule(new TimerTask() { // from class: com.topfreegames.e.a.a.4
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        try {
                            a.this.a(false);
                        } catch (Exception e) {
                            if (com.topfreegames.e.a.c()) {
                                e.printStackTrace();
                            }
                            try {
                                ((BikeRaceApplication) a.this.m.getApplicationContext()).d().c("performLogout", e);
                            } catch (Exception e2) {
                            }
                        }
                    }
                }
            }, j);
        }
    }

    public void a(Activity activity, String str, String str2, String str3, String str4, String str5, String str6, g gVar) {
        this.l = gVar;
        if (Session.getActiveSession() != null && str2 != null && activity != null) {
            Bundle bundle = new Bundle();
            bundle.putString("to", str2);
            if (str != null) {
                bundle.putString("description", str);
            }
            if (str3 != null) {
                bundle.putString("picture", str3);
            }
            if (str4 != null) {
                bundle.putString("link", str4);
            }
            if (str6 != null) {
                bundle.putString("caption", str6);
            }
            if (str5 != null) {
                bundle.putString("name", str5);
            }
            final WebDialog.FeedDialogBuilder feedDialogBuilder = new WebDialog.FeedDialogBuilder(activity, Session.getActiveSession());
            feedDialogBuilder.setTo(str2);
            feedDialogBuilder.setPicture(str3);
            feedDialogBuilder.setDescription(str);
            feedDialogBuilder.setLink(str4);
            feedDialogBuilder.setCaption(str6);
            feedDialogBuilder.setName(str5);
            feedDialogBuilder.setOnCompleteListener(new WebDialog.OnCompleteListener() { // from class: com.topfreegames.e.a.a.5
                @Override // com.facebook.widget.WebDialog.OnCompleteListener
                public void onComplete(Bundle bundle2, FacebookException facebookException) {
                    if (facebookException == null) {
                        if (bundle2.getString("post_id") != null) {
                            a.this.a(true, true);
                            return;
                        } else {
                            a.this.a(true, false);
                            return;
                        }
                    }
                    a.this.a(false, false);
                }
            });
            activity.runOnUiThread(new Runnable() { // from class: com.topfreegames.e.a.a.6
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        feedDialogBuilder.build().show();
                    } catch (Exception e) {
                    }
                }
            });
        }
    }

    public void a(Activity activity, f fVar) {
    }

    public void a(f fVar) {
        if (this.f != null && !this.f.contains(fVar)) {
            synchronized (this.f) {
                this.f.add(fVar);
            }
        }
    }

    public void a(boolean z2, l lVar, long j, Object obj) {
        a(z2, lVar, j, com.topfreegames.e.e.FRIENDS_DONT_HAVE_APP, obj);
    }

    public void a(boolean z2, l lVar, Object obj) {
        a(z2, lVar, 600000L, obj);
    }

    public void b(boolean z2, l lVar, long j, Object obj) {
        a(z2, lVar, j, com.topfreegames.e.e.FRIENDS_HAVE_APP, obj);
    }

    public void b(boolean z2, l lVar, Object obj) {
        b(z2, lVar, 600000L, obj);
    }

    public void a(boolean z2, m mVar, long j, Object obj) {
        boolean z3 = false;
        if (this.s != null) {
            com.topfreegames.e.l lVarA = a(this.s, true, false);
            if (lVarA == null || (z2 && lVarA.c() == null)) {
                z3 = true;
            }
            if (!z3) {
                a(lVarA, true, mVar);
            }
        } else {
            z3 = true;
        }
        if (z3) {
            a(new n(new WeakReference(this), "me", z2, null, j), mVar, true, obj);
        }
    }

    public void a(boolean z2, m mVar, Object obj) {
        a(z2, mVar, 600000L, obj);
    }

    public void a(List<String> list, h hVar, long j, Object obj) {
        this.t.submit(new c(this, list, hVar, j, obj));
    }

    public void a(List<String> list, h hVar, Object obj) {
        a(list, hVar, 600000L, obj);
    }

    public void a(List<String> list, String str, i iVar) {
        this.v = iVar;
        this.u.a(list, str, this);
    }

    public void a(String str, boolean z2, m mVar, long j, Object obj) {
        a(this.t.submit(new d(this, str, z2, mVar, j, obj)), obj);
    }

    public void a(String str, boolean z2, m mVar, Object obj) {
        a(str, z2, mVar, 600000L, obj);
    }

    public void a(List<String> list, boolean z2, k kVar, long j, Object obj) {
        if (list != null) {
            Dictionary<String, com.topfreegames.e.l> hashtable = new Hashtable<>();
            for (String str : list) {
                com.topfreegames.e.l lVarA = a(str, true, false);
                if (lVarA != null) {
                    hashtable.put(str, lVarA);
                }
            }
            if (hashtable.size() == list.size()) {
                a(hashtable, kVar);
                return;
            }
            com.topfreegames.e.b.h hVar = new com.topfreegames.e.b.h(this);
            if (kVar != null) {
                this.h.put(hVar, kVar);
                a(hVar, obj);
            }
            hVar.a(list, z2, null, j);
        }
    }

    public void a(List<String> list, boolean z2, k kVar, Object obj) {
        a(list, z2, kVar, 600000L, obj);
    }

    public void e() {
        synchronized (this.k) {
            Iterator<List<Future<Boolean>>> it = this.k.values().iterator();
            while (it.hasNext()) {
                Iterator<Future<Boolean>> it2 = it.next().iterator();
                while (it2.hasNext()) {
                    it2.next().cancel(true);
                }
            }
            this.k.clear();
        }
        this.w.c();
        this.x.c();
        this.y = true;
    }

    private void a(com.topfreegames.e.b.f fVar, Object obj) {
        if (obj != null) {
            synchronized (this.j) {
                List<com.topfreegames.e.b.f> arrayList = this.j.get(obj);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(fVar);
                this.j.put(obj, arrayList);
            }
        }
    }

    private void a(Future<Boolean> future, Object obj) {
        if (obj != null) {
            synchronized (this.k) {
                List<Future<Boolean>> arrayList = this.k.get(future);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(future);
                this.k.put(obj, arrayList);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        this.s = null;
        a(com.topfreegames.e.e.ALL_FRIENDS);
        a(com.topfreegames.e.e.FRIENDS_HAVE_APP);
        a(com.topfreegames.e.e.FRIENDS_DONT_HAVE_APP);
    }

    private void a(com.topfreegames.e.e eVar) {
        switch (f()[eVar.ordinal()]) {
            case 1:
                if (this.p != null) {
                    synchronized (this.p) {
                        this.p.clear();
                        break;
                    }
                    return;
                }
                return;
            case 2:
                if (this.q != null) {
                    synchronized (this.q) {
                        this.q.clear();
                        break;
                    }
                    return;
                }
                return;
            case 3:
                if (this.r != null) {
                    synchronized (this.r) {
                        this.r.clear();
                        break;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    private Session h() {
        Session session = new Session(this.m);
        session.addCallback(this.f1491a);
        return session;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<String> list, h hVar, long j, Object obj) throws InterruptedException {
        this.y = false;
        for (String str : list) {
            com.topfreegames.e.l lVarA = a(str, true, false);
            Bitmap bitmapC = null;
            if (lVarA != null) {
                bitmapC = lVarA.c();
            }
            if (bitmapC != null) {
                a(bitmapC, hVar, str);
            } else {
                com.topfreegames.e.b.a aVar = new com.topfreegames.e.b.a(this, str, 600000L);
                if (hVar != null) {
                    this.h.put(aVar, (j) hVar);
                    a(aVar, obj);
                }
                this.x.a(aVar);
            }
            if (this.y || Thread.interrupted()) {
                throw new InterruptedException();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, boolean z2, m mVar, long j, Object obj) {
        boolean z3 = true;
        com.topfreegames.e.l lVarA = a(str, true, false);
        if (lVarA != null && (!z2 || lVarA.c() != null)) {
            z3 = false;
        }
        if (z3) {
            a(new n(new WeakReference(this), str, z2, null, j), mVar, false, obj);
        } else {
            a(lVarA, false, mVar);
        }
    }

    private void a(com.topfreegames.e.l lVar, String str) {
        com.topfreegames.e.l lVarA;
        if (str != null && lVar != null) {
            String strB = lVar.b();
            Bitmap bitmapC = lVar.c();
            if ((strB == null || bitmapC == null) && (lVarA = a(str, true, false)) != null) {
                if (strB == null) {
                    lVar.a(lVarA.b());
                }
                if (bitmapC == null) {
                    lVar.a(lVarA.c());
                }
            }
        }
    }

    private List<String> b(com.topfreegames.e.e eVar) {
        switch (f()[eVar.ordinal()]) {
            case 1:
                return this.p;
            case 2:
                return this.q;
            case 3:
                return this.r;
            default:
                return null;
        }
    }

    private com.topfreegames.e.l a(String str, boolean z2, boolean z3) {
        com.topfreegames.e.l lVarA;
        if (this.o != null) {
            synchronized (this.o) {
                lVarA = this.o.a(str);
            }
        } else {
            lVarA = null;
        }
        if (lVarA == null && z2 && (lVarA = this.n.a(str)) != null) {
            b(lVarA, str);
        }
        if (lVarA == null && z3) {
            Vector vector = new Vector();
            vector.add(str);
            a((List<String>) vector, false, (k) null, (Object) null);
        }
        return lVarA;
    }

    private void a(com.topfreegames.e.l lVar) {
        if (lVar != null && this.n != null) {
            synchronized (this.n) {
                this.n.a(lVar);
            }
        }
    }

    private void a(n nVar, m mVar, boolean z2, Object obj) {
        boolean z3;
        List<com.topfreegames.e.b.f> arrayList;
        synchronized (this.h) {
            if (mVar == null) {
                z3 = true;
            } else {
                String strB = nVar.b();
                if (strB == null) {
                    z3 = true;
                    arrayList = null;
                } else {
                    List<com.topfreegames.e.b.f> list = this.i.get(strB);
                    if (list == null) {
                        z3 = true;
                        arrayList = list;
                    } else {
                        boolean z4 = true;
                        for (com.topfreegames.e.b.f fVar : list) {
                            if (fVar instanceof n) {
                                j jVar = this.h.get(fVar);
                                if (jVar != null) {
                                    if (jVar instanceof m) {
                                        a((com.topfreegames.e.l) null, z2, (m) jVar);
                                    }
                                    this.h.put(fVar, mVar);
                                    a(fVar, obj);
                                }
                                z4 = false;
                            }
                        }
                        arrayList = list;
                        z3 = z4;
                    }
                }
                if (z3) {
                    this.h.put(nVar, mVar);
                    a(nVar, obj);
                    if (strB != null) {
                        if (arrayList == null) {
                            arrayList = new ArrayList<>();
                        }
                        arrayList.add(nVar);
                        this.i.put(strB, arrayList);
                    }
                }
            }
            if (z3) {
                this.w.a(nVar);
            }
        }
    }

    private void b(com.topfreegames.e.l lVar, String str) {
        if (this.o != null && str != null && lVar != null) {
            synchronized (this.o) {
                this.o.a(str, lVar);
            }
            a(lVar);
        }
    }

    private void a(String str) {
        if (this.o != null && str != null) {
            synchronized (this.o) {
                this.o.b(str);
            }
        }
    }

    private void a(boolean z2, l lVar, long j, com.topfreegames.e.e eVar, Object obj) {
        boolean z3 = true;
        List<String> listB = b(eVar);
        if (listB != null && listB.size() > 0) {
            Dictionary<String, com.topfreegames.e.l> hashtable = new Hashtable<>();
            synchronized (listB) {
                for (String str : listB) {
                    com.topfreegames.e.l lVarA = a(str, true, false);
                    if (lVarA != null) {
                        hashtable.put(str, lVarA);
                    }
                }
            }
            a(hashtable, eVar, lVar);
            z3 = false;
        }
        if (z3) {
            com.topfreegames.e.b.k kVar = new com.topfreegames.e.b.k(this);
            if (lVar != null) {
                this.h.put(kVar, lVar);
                a(kVar, obj);
            }
            kVar.a("me", z2, eVar, j);
            return;
        }
        new com.topfreegames.e.b.k(this).a("me", z2, eVar, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z2, boolean z3) {
        if (this.l != null) {
            if (z2) {
                this.l.a(z3);
            } else {
                this.l.g();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z2, boolean z3) {
        synchronized (this) {
            if (this.d != null) {
                this.d.cancel();
                this.d = null;
            }
            this.g = z2;
            if (this.f != null) {
                synchronized (this.f) {
                    Iterator<f> it = this.f.iterator();
                    while (it.hasNext()) {
                        it.next().a(z2, z3);
                    }
                    this.f.clear();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z2) {
        synchronized (this) {
            if (this.e != null) {
                this.e.cancel();
                this.e = null;
            }
            if (this.f != null) {
                synchronized (this.f) {
                    Iterator<f> it = this.f.iterator();
                    while (it.hasNext()) {
                        it.next().b(z2);
                    }
                    this.f.clear();
                }
            }
            this.n.b(null);
            this.g = false;
        }
    }

    private void a(Dictionary<String, com.topfreegames.e.l> dictionary, com.topfreegames.e.e eVar, l lVar) {
        if (lVar != null) {
            lVar.a(dictionary, eVar);
        }
    }

    private void a(Bitmap bitmap, h hVar, String str) {
        if (hVar != null) {
            hVar.a(bitmap, str);
        }
    }

    private void a(com.topfreegames.e.l lVar, boolean z2, m mVar) {
        if (mVar != null) {
            mVar.a(lVar, z2);
        }
    }

    private void a(Dictionary<String, com.topfreegames.e.l> dictionary, k kVar) {
        if (kVar != null) {
            kVar.a(dictionary);
        }
    }

    private void a(Bitmap bitmap, String str) {
        if (bitmap != null && str != null) {
            com.topfreegames.e.l lVarA = a(str, true, false);
            if (lVarA == null) {
                this.w.a(new n(new WeakReference(this), str, false, bitmap, 600000L));
            } else {
                a(str);
                lVarA.a(bitmap);
                a(lVarA);
                b(lVarA, str);
            }
        }
    }

    private void b(Dictionary<String, com.topfreegames.e.l> dictionary) {
        if (dictionary != null) {
            Enumeration<String> enumerationKeys = dictionary.keys();
            while (enumerationKeys.hasMoreElements()) {
                String strNextElement = enumerationKeys.nextElement();
                b(dictionary.get(strNextElement), strNextElement);
            }
        }
    }

    private void a(com.topfreegames.e.e eVar, List<String> list) {
        List<String> listB = b(eVar);
        if (listB != null) {
            synchronized (listB) {
                listB.clear();
                listB.addAll(list);
            }
        }
    }
}
